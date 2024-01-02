package com.willbes.web.mocktest.mouigosa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelRead;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.mocktest.mouigosa.service.MouigosaService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/mouigosa")
@Controller
public class MouigosaController {

    /** log */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private MouigosaService mouigosaService;

    @Inject
    private FileSystemResource fsResource;	//DI

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    @Resource(name="excelRead")
    ExcelRead excelUtil;

    // 모의고사 과목 리스트
    @RequestMapping(value="/mouigosaList.do")
    public String mouigosaList(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("S_currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("S_pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", URLDecoder.decode(S_searchKeyWord,"UTF-8"));

        searchMap.put("S_currentPage", currentPage);
        searchMap.put("S_pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        // 모의고사 과목 리스트
        List<?> list = mouigosaService.getMouigosaList(searchMap);
        // 총 건수
        int listCount = mouigosaService.getCount(searchMap);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaList";
    }

    // 모의고사 과목 등록 팝업화면 불러오기
    /* 2015.05.08. by kckim. 사용되지 않는 코드 주석 처리*/
    @RequestMapping(value="/mouigosaClass.do")
    public String mouigosaClass(HttpServletRequest request, ModelMap model) {
        HashMap<String, Object> params = new HashMap<String,  Object>();

        model.addAttribute("ClassList", mouigosaService.getClass(params));
        model.addAttribute("SubjectList", mouigosaService.getClassSubject(params));

        return "mocktest/mouigosa/mouigosaClass";
    }

    // 모의고사 과목 등록 팝업화면 불러오기
    @RequestMapping(value="/mouigosaClassSubject.pop")
    public String mouigosaClassSubject(HttpServletRequest request, ModelMap model) {
        HashMap<String, Object> params = new HashMap<String,  Object>();

        model.addAttribute("SubjectList", mouigosaService.getClassSubject(params));

        return "mocktest/mouigosa/mouigosaClassSubject";
    }

    // for ajax
    // 과목에 대한 교수코드 가져오기
    @RequestMapping(value="/subCode.do")
    @ResponseBody
    public List<?> subCode(ModelMap output, HttpServletRequest req) {

        // 검색조건
        String SUBJECT_CD = CommonUtil.isNull(req.getParameter("SUBJECT_CD"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("SUBJECT_CD", SUBJECT_CD);

        //교수 리스트
        List<?> sub_list = mouigosaService.getSubCode(searchMap);

        return sub_list;
    }

    // for ajax
    // 년.회차,과목 중복체크
    @RequestMapping(value="/duplication.do")
    @ResponseBody
    public List<?> duplication(ModelMap output, HttpServletRequest req) {

        // 검색조건
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(req.getParameter("EXAMROUND"), "");
        String SUBJECT_CD = CommonUtil.isNull(req.getParameter("SUBJECT_CD"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);

        //중복체크
        List<?> list = mouigosaService.getDuplication(searchMap);

        return list;
    }

    // 과목 등록
    @RequestMapping(value="/mouigosaInsert.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String insertMouigosa(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {
        Calendar oCalendar = Calendar.getInstance( );

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String EXAMYEAR = CommonUtil.isNull(request.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(request.getParameter("EXAMROUND"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");
        String PROFCODE = CommonUtil.isNull(request.getParameter("PROFCODE"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");
        String QUESTIONNUM = CommonUtil.isNull(request.getParameter("QUESTIONNUM"), "");
        String QUESTIONREGISTRATIONOPTION = CommonUtil.isNull(request.getParameter("QUESTIONREGISTRATIONOPTION"), "");
        String OPENSTATE = CommonUtil.isNull(request.getParameter("OPENSTATE"), "");
        String FEE_PROF = CommonUtil.isNull(request.getParameter("FEE_PROF"), "");
        String CODE_NM = CommonUtil.isNull(request.getParameter("CODE_NM"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        String imgFile_sts = CommonUtil.isNull(request.getParameter("imgFile_sts"), "");
        String imgFile2_sts = CommonUtil.isNull(request.getParameter("imgFile2_sts"), "");

        //MultipartFile imgFile = multipartRequest.getFile("imgFile");
        //MultipartFile imgFile2 = multipartRequest.getFile("imgFile2");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);
        searchMap.put("PROFCODE", PROFCODE);
        searchMap.put("ENTRYNUM", ENTRYNUM);
        searchMap.put("QUESTIONNUM", QUESTIONNUM);
        searchMap.put("QUESTIONREGISTRATIONOPTION", QUESTIONREGISTRATIONOPTION);
        searchMap.put("OPENSTATE", OPENSTATE);
        searchMap.put("FEE_PROF", FEE_PROF);
        searchMap.put("CODE_NM", CODE_NM);
        searchMap.put("USER_ID", USER_ID);

        if (imgFile_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolQ/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("fileFullPath");
            String sFilelds = String.valueOf(fileInfoList.get(0).get("fileId"));
            int fileId = mouigosaService.getFileCount(searchMap);

            searchMap.put("QUESTIONFID", fileId);
        }

        if (imgFile2_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolA/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile2");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("fileFullPath");
            String sFilelds = String.valueOf(fileInfoList.get(0).get("fileId"));
            int fileId = mouigosaService.getFileCount(searchMap);

            searchMap.put("ANSWERFID", fileId);
        }

        /* 트랜젝션 처리를 위해 Service 로 이동
        mouigosaService.insertMouigosa(searchMap);
        int ITEMID = mouigosaService.getItemIdMax(searchMap);
        searchMap.put("ITEMID", ITEMID);
        for (int i = 1; i <= Integer.parseInt(QUESTIONNUM); i++) {
            mouigosaService.insertQuestionMouigosa(searchMap);
        }
        */
        int ITEMID = mouigosaService.insertMouigosaWthQuestion(searchMap);
        model.addAttribute("ITEMID", ITEMID);

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_searchFlag", S_searchFlag);
        model.addAttribute("S_searchKeyWord", URLEncoder.encode(S_searchKeyWord,"UTF-8"));
        model.addAttribute("S_currentPage", S_currentPage);
        model.addAttribute("S_pageRow", S_pageRow);

        model.addAttribute("searchMap", searchMap);

        return "redirect:/mouigosa/mouigosaQuestionList.do";
    }

    // 모의고사 과목 수정 팝업화면 불러오기 상세
    /*
     * 2015.05.08. by kckim. 사용되지 않는 코드 주석 처리
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/updateMouigosa.pop")
    public String updateMouigosaDetail(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {

        // 검색조건
        String ITEMID = CommonUtil.isNull(req.getParameter("ITEMID"), "");

        String S_EXAMYEAR = CommonUtil.isNull(req.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(req.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(req.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(URLDecoder.decode(req.getParameter("S_searchKeyWord"),"UTF-8"), "");
        String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

        if (log.isDebugEnabled()) {
            log.debug("***** 모의고사 과목 수정 팝업화면 불러오기 상세 파라미터 ***** ");
            log.debug("***** ITEMID : " + ITEMID);

            log.debug("***** S_EXAMYEAR : " + S_EXAMYEAR);
            log.debug("***** S_EXAMROUND : " + S_EXAMROUND);
            log.debug("***** S_searchFlag : " + S_searchFlag);
            log.debug("***** S_searchKeyWord : " + S_searchKeyWord);
            log.debug("***** currentPage : " + currentPage);
            log.debug("***** pageRow : " + pageRow);
        }

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("ITEMID", ITEMID);

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", S_searchKeyWord);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        //상세 조회
        List<?> list = mouigosaService.getUpdateDetail(searchMap);
        //과목 리스트
        List<?> subject_list = mouigosaService.getSubjectList(searchMap);
        if(log.isDebugEnabled()){
            log.debug("***** list : "+ list) ;
            log.debug("***** subject_list : "+ subject_list) ;
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("SUBJECT_CD",map.get("SUBJECT_CD"));
        searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
        searchMap.put("PROFCODE",map.get("PROFCODE"));
        searchMap.put("USER_NM",map.get("USER_NM"));
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
        searchMap.put("OPENSTATE",map.get("OPENSTATE"));
        searchMap.put("STSCNT",map.get("STSCNT"));
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));

        output.addAttribute("searchMap", searchMap);
        output.addAttribute("subject_list", subject_list);
        output.addAttribute("sts", "U");

        return "mocktest/mouigosa/mouigosaIns";
    }
    */

    // 과목 수정
    @RequestMapping(value="/mouigosaUpdate.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String updateMouigosa(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest)
            throws IllegalStateException, IOException {
        Calendar oCalendar = Calendar.getInstance( );

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String EXAMYEAR = CommonUtil.isNull(request.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(request.getParameter("EXAMROUND"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");
        String PROFCODE = CommonUtil.isNull(request.getParameter("PROFCODE"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");
        String QUESTIONNUM = CommonUtil.isNull(request.getParameter("QUESTIONNUM"), "");
        String QUESTIONREGISTRATIONOPTION = CommonUtil.isNull(request.getParameter("QUESTIONREGISTRATIONOPTION"), "");
        String OPENSTATE = CommonUtil.isNull(request.getParameter("OPENSTATE"), "");
        String FEE_PROF = CommonUtil.isNull(request.getParameter("FEE_PROF"), "");
        String CODE_NM = CommonUtil.isNull(request.getParameter("CODE_NM"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        String imgFile_sts = CommonUtil.isNull(request.getParameter("imgFile_sts"), "");
        String imgFile2_sts = CommonUtil.isNull(request.getParameter("imgFile2_sts"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);
        searchMap.put("PROFCODE", PROFCODE);
        searchMap.put("ENTRYNUM", ENTRYNUM);
        searchMap.put("QUESTIONNUM", QUESTIONNUM);
        searchMap.put("QUESTIONREGISTRATIONOPTION", QUESTIONREGISTRATIONOPTION);
        searchMap.put("OPENSTATE", OPENSTATE);
        searchMap.put("FEE_PROF", FEE_PROF);
        searchMap.put("CODE_NM", CODE_NM);
        searchMap.put("USER_ID", USER_ID);

        if (imgFile_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolQ/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("fileFullPath");
            String sFilelds = String.valueOf(fileInfoList.get(0).get("fileId"));
            int fileId = mouigosaService.getFileCount(searchMap);
            searchMap.put("QUESTIONFID", fileId);
        }

        if (imgFile2_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolA/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile2");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("filePath");
            String sFilelds = String.valueOf(fileInfoList.get(0).get("fileId"));
            int fileId = mouigosaService.getFileCount(searchMap);
            searchMap.put("ANSWERFID", fileId);
        }

        /* 트랜젝션 처리를 위해 Service 로 이동
        int QUESTIONNUMMAX = mouigosaService.getQuestionNumMax(searchMap);
        if (QUESTIONNUMMAX > Integer.parseInt(QUESTIONNUM)) {
            int QUESTIONNUMMAX_M = QUESTIONNUMMAX - Integer.parseInt(QUESTIONNUM);
            for (int i = 1; i <= QUESTIONNUMMAX_M; i++) {
                mouigosaService.deleteQuestionMouigosa(searchMap);
            }
        }else if (QUESTIONNUMMAX < Integer.parseInt(QUESTIONNUM)) {
            int QUESTIONNUMMAX_M = Integer.parseInt(QUESTIONNUM) - QUESTIONNUMMAX;
            for (int i = 1; i <= QUESTIONNUMMAX_M; i++) {
                mouigosaService.insertQuestionMouigosa(searchMap);
            }
        }
        mouigosaService.updateMouigosa(searchMap);
        */

        mouigosaService.updateMouigosaWthQuestion(searchMap);

        model.addAttribute("ITEMID", ITEMID);

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_searchFlag", S_searchFlag);
        model.addAttribute("S_searchKeyWord", URLEncoder.encode(S_searchKeyWord,"UTF-8"));
        model.addAttribute("S_currentPage", S_currentPage);
        model.addAttribute("S_pageRow", S_pageRow);

        return "redirect:/mouigosa/mouigosaQuestionList.do";
    }

    // 과목 삭제
    @RequestMapping(value="/mouigosaDelete.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String deleteMouigosa(ModelMap model, HttpServletRequest request) {
        // 검색조건
        //String[] deleteIds = request.getParameterValues("deleteIds");
        String deleteIds = request.getParameter("deleteIds");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("deleteIds", deleteIds);
        mouigosaService.deleteMouigosaQuestion(searchMap);

        return "redirect:/mouigosa/mouigosaList.do";
    }

    // 모의고사 시험시작 팝업
    @RequestMapping(value="/printMouigosa.pop")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String printMouigosa(ModelMap output, HttpServletRequest req) {

        String ITEMID = CommonUtil.isNull(req.getParameter("ITEMID"), "");
        String sts = CommonUtil.isNull(req.getParameter("sts"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(req.getParameter("QUESTIONNUMBER"), "");
        String QUESTIONREGISTRATIONOPTION = CommonUtil.isNull(req.getParameter("QUESTIONREGISTRATIONOPTION"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("ITEMID", ITEMID);
        searchMap.put("sts", sts);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        searchMap.put("QUESTIONREGISTRATIONOPTION", QUESTIONREGISTRATIONOPTION);

        //문제 프린트 팝업 리스트
        List<?> question_list = mouigosaService.getQuestionList(searchMap);

        output.addAttribute("question_list", question_list);

        output.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/printPop";
    }

    // 모의고사 문제 리스트
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/mouigosaQuestionList.do")
    public String mouigosaQuestionList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
    	
        Map<String, Object> searchMap = new HashMap<String, Object>();

        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "TM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", URLDecoder.decode(S_searchKeyWord,"UTF-8"));
        searchMap.put("S_currentPage", S_currentPage);
        searchMap.put("S_pageRow", S_pageRow);
        searchMap.put("ITEMID", ITEMID);

        if (!ITEMID.equals("")) {
            //상세 조회
            List<?> list = mouigosaService.getUpdateDetail(searchMap);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map = (HashMap<String, Object>) list.get(0);
            searchMap.put("ITEMID",map.get("ITEMID"));
            searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
            searchMap.put("EXAMROUND",map.get("EXAMROUND"));
            searchMap.put("SUBJECT_CD",map.get("SUBJECT_CD"));
            searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
            searchMap.put("PROFCODE",map.get("PROFCODE"));
            searchMap.put("PROF_NM",map.get("PROF_NM"));
            searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
            searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
            searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
            searchMap.put("OPENSTATE",map.get("OPENSTATE"));
            searchMap.put("STSCNT",map.get("STSCNT"));
            searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
            searchMap.put("FEE_PROF",map.get("FEE_PROF"));
            searchMap.put("CODE_NM",map.get("CODE_NM"));

            searchMap.put("QUESTIONFID",map.get("QUESTIONFID"));
            searchMap.put("FILEPATH",map.get("FILEPATH"));
            searchMap.put("ANSWERFID",map.get("ANSWERFID"));
            searchMap.put("FILEPATH2",map.get("FILEPATH2"));
            searchMap.put("QUESTIONFILENAME",map.get("QUESTIONFILENAME"));
            searchMap.put("ANSWEREXPLAINFILENAME",map.get("ANSWEREXPLAINFILENAME"));
        }else{
            searchMap.put("FEE_PROF","50");
        }

        // 문제 리스트
        List<?> list = mouigosaService.getMouigosaQuestionList(searchMap);
        //과목 리스트
        List<?> subject_list = mouigosaService.getSubjectList(searchMap);

        model.addAttribute("list", list);
        model.addAttribute("subject_list", subject_list);
        if (ITEMID.equals("")) {
            model.addAttribute("sts", "I");
        }else{
            model.addAttribute("sts", "U");
        }
        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaQuestionList";
    }

    // 모의고사 문제 등록 팝업화면 불러오기
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/createQuestionMouigosa.pop")
    public String createQuestionMouigosa(ModelMap model, HttpServletRequest request)
            throws UnsupportedEncodingException {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(URLDecoder.decode(request.getParameter("S_searchKeyWord"),"UTF-8"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", S_searchKeyWord);
        searchMap.put("S_currentPage", S_currentPage);
        searchMap.put("S_pageRow", S_pageRow);

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);

        //과목 상세 조회
        List<?> list = mouigosaService.getUpdateDetail(searchMap);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("SUBJECT_CD",map.get("SUBJECT_CD"));
        searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
        searchMap.put("PROFCODE",map.get("PROFCODE"));
        searchMap.put("PROF_NM",map.get("PROF_NM"));
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
        searchMap.put("OPENSTATE",map.get("OPENSTATE"));
        searchMap.put("STSCNT",map.get("STSCNT"));
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));

        //과목영역 리스트
        List<?> subject_area_list = mouigosaService.getSubjectAreaList(searchMap);
        model.addAttribute("subject_area_list", subject_area_list);

        model.addAttribute("sts", "I");
        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaQuestionIns";
    }

    // 문제 등록,수정
    @RequestMapping(value="/mouigosaQuestionCrud.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String insertQuestionMouigosa(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest)
            throws IllegalStateException, IOException {

        Calendar oCalendar = Calendar.getInstance( );

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String imgFile_sts = CommonUtil.isNull(request.getParameter("imgFile_sts"), "");
        String imgFile2_sts = CommonUtil.isNull(request.getParameter("imgFile2_sts"), "");
        String imgFile3_sts = CommonUtil.isNull(request.getParameter("imgFile3_sts"), "");

        String QUESTIONRANGE = CommonUtil.isNull(request.getParameter("QUESTIONRANGE"), "");
        //		String imgFile = CommonUtil.isNull(req.getParameter("imgFile"), "");
        //		String imgFile2 = CommonUtil.isNull(req.getParameter("imgFile2"), "");
        MultipartFile imgFile = multipartRequest.getFile("imgFile");
        MultipartFile imgFile2 = multipartRequest.getFile("imgFile2");
        MultipartFile imgFile3 = multipartRequest.getFile("imgFile3");
        MultipartFile smaFile = multipartRequest.getFile("smaFile");

        String LEVELDIFFICULTY = CommonUtil.isNull(request.getParameter("LEVELDIFFICULTY"), "");
        String QUESTIONPATTERN = CommonUtil.isNull(request.getParameter("QUESTIONPATTERN"), "A");
        String ANSWERNUMBER = "";
        String[] answer = (String[])request.getParameterValues("ANSWERNUMBER");
        StringBuilder builder = new StringBuilder();
        for(String s : answer) {
            builder.append(s).append(",");
        }
        ANSWERNUMBER = (builder.toString()).substring(0, builder.toString().length()-1);
        String QUESTION = CommonUtil.isNull(request.getParameter("QUESTION"), "");
        String ANSWEREXPLAIN = CommonUtil.isNull(request.getParameter("ANSWEREXPLAIN"), "");
        String QUESTIONREGISTRATIONOPTION = CommonUtil.isNull(request.getParameter("QUESTIONREGISTRATIONOPTION"), "");
        String QUESTIONFILEID = CommonUtil.isNull(request.getParameter("QUESTIONFILEID"), "");
        String ANSWEREXPLAINFILEID = CommonUtil.isNull(request.getParameter("ANSWEREXPLAINFILEID"), "");
        String SMAQUESTIONFILEID = CommonUtil.isNull(request.getParameter("SMAQUESTIONFILEID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String QUESTIONNUM = CommonUtil.isNull(request.getParameter("QUESTIONNUM"), "");
        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        String QUESTION3 = CommonUtil.isNull(request.getParameter("QUESTION3"), "");
        String ANSWEREXPLAIN3 = CommonUtil.isNull(request.getParameter("ANSWEREXPLAIN3"), "");

        QUESTION3 = QUESTION3.replaceAll("\r\n", "<br>");
        ANSWEREXPLAIN3 = ANSWEREXPLAIN3.replaceAll("\r\n", "<br>");
        QUESTION3 = QUESTION3.replaceAll("\u0020", "&nbsp;");
        ANSWEREXPLAIN3 = ANSWEREXPLAIN3.replaceAll("\u0020", "&nbsp;");

        String QUESTION4 = CommonUtil.isNull(request.getParameter("QUESTION4"), "");
        String ANSWEREXPLAIN4 = CommonUtil.isNull(request.getParameter("ANSWEREXPLAIN4"), "");

        QUESTION4 = QUESTION4.replaceAll("\r\n", "<br>");
        ANSWEREXPLAIN4 = ANSWEREXPLAIN4.replaceAll("\r\n", "<br>");
        QUESTION4 = QUESTION4.replaceAll("\u0020", "&nbsp;");
        ANSWEREXPLAIN4 = ANSWEREXPLAIN4.replaceAll("\u0020", "&nbsp;");

        String sts = CommonUtil.isNull(request.getParameter("sts"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("QUESTIONRANGE", QUESTIONRANGE);
        searchMap.put("LEVELDIFFICULTY", LEVELDIFFICULTY);
        searchMap.put("QUESTIONPATTERN", QUESTIONPATTERN);
        searchMap.put("ANSWERNUMBER", ANSWERNUMBER);
        searchMap.put("QUESTIONREGISTRATIONOPTION", QUESTIONREGISTRATIONOPTION);
        searchMap.put("QUESTIONFILEID", QUESTIONFILEID);
        searchMap.put("ANSWEREXPLAINFILEID", ANSWEREXPLAINFILEID);
        searchMap.put("SMAQUESTIONFILEID", SMAQUESTIONFILEID);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        searchMap.put("ITEMID", ITEMID);
        searchMap.put("USER_ID", USER_ID);
        if (!QUESTION3.equals("")) {
            searchMap.put("QUESTION", QUESTION3);
            searchMap.put("ANSWEREXPLAIN", ANSWEREXPLAIN3);
        }else{
            searchMap.put("QUESTION", QUESTION4);
            searchMap.put("ANSWEREXPLAIN", ANSWEREXPLAIN4);
        }
        //searchMap.put("imgFile", imgFile);
        //searchMap.put("imgFile2", imgFile2);

        if (imgFile_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolQ/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("filePath");
            int fileId = mouigosaService.getFileCount(searchMap);
            searchMap.put("QUESTIONFILEID", fileId);
        }

        if (imgFile2_sts.equals("Y")) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolA/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("imgFile2");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("filePath");
            int fileId = mouigosaService.getFileCount(searchMap);
            searchMap.put("ANSWEREXPLAINFILEID", fileId);
        }

        if (!smaFile.equals("") && smaFile != null) {
            String rootPath = fsResource.getPath();
            String subPath = "subjItemPoolS/"+oCalendar.get(Calendar.YEAR)+(oCalendar.get(Calendar.MONTH) + 1)+"/";
            List<MultipartFile> uploadFileList = multipartRequest.getFiles("smaFile");
            List<HashMap<String, Object>> fileInfoList = fileUtil.uploadFilesWthField(uploadFileList, rootPath, subPath);

            String filePath = (String) fileInfoList.get(0).get("filePath");
            int fileId = mouigosaService.getFileCount(searchMap);
            searchMap.put("SMAQUESTIONFILEID", fileId);
        }

        if (imgFile3_sts.equals("Y")) {

            String rootPath = fsResource.getPath();
            String subPath = "excel/";

            MultipartFile uploadFile = multipartRequest.getFile("imgFile3");
            HashMap<String, Object> fileMap = fileUtil.uploadFileWthField(uploadFile, rootPath, subPath);
            String fileFullPath = (String) fileMap.get("fileFullPath");

            ArrayList<HashMap<String, String>> read = excelUtil.readExcelXLS(rootPath+fileFullPath);

            if (Integer.parseInt(QUESTIONNUM) >= read.size()) {
                searchMap.put("excelDATAs", read);
                /*
                for (int k = 0; k < read.size(); k++) {
                    //QUESTIONNUMBER = (String)read.get(k).get("번호");
                    QUESTION = (String)read.get(k).get("문제와지문");
                    QUESTIONRANGE = (String)read.get(k).get("영역");
                    LEVELDIFFICULTY = (String)read.get(k).get("난이도");
                    ANSWERNUMBER = (String)read.get(k).get("정답");
                    ANSWEREXPLAIN = (String)read.get(k).get("해설");

                    QUESTION = QUESTION.replaceAll("\r\n", "<br>");
                    ANSWEREXPLAIN = ANSWEREXPLAIN.replaceAll("\r\n", "<br>");
                    QUESTION = QUESTION.replaceAll("\u0020", "&nbsp;");
                    ANSWEREXPLAIN = ANSWEREXPLAIN.replaceAll("\u0020", "&nbsp;");

                    if (log.isDebugEnabled()) {
                        log.debug("***** 옵션4(엑셀) 업로드 *****===> " + k+1);
                        log.debug("***** QUESTIONNUMBER : " + QUESTIONNUMBER);
                        log.debug("***** QUESTION : " + QUESTION);
                        log.debug("***** QUESTIONRANGE : " + QUESTIONRANGE);
                        log.debug("***** LEVELDIFFICULTY : " + LEVELDIFFICULTY);
                        log.debug("***** ANSWERNUMBER : " + ANSWERNUMBER);
                        log.debug("***** ANSWEREXPLAIN : " + ANSWEREXPLAIN);
                    }

                    searchMap.put("QUESTIONNUMBER", k+1);
                    searchMap.put("QUESTION", QUESTION);
                    searchMap.put("QUESTIONRANGE", QUESTIONRANGE);
                    searchMap.put("LEVELDIFFICULTY", LEVELDIFFICULTY);
                    searchMap.put("ANSWERNUMBER", ANSWERNUMBER);
                    searchMap.put("ANSWEREXPLAIN", ANSWEREXPLAIN);

                    mouigosaService.updateQuestionMouigosa(searchMap);
                }
                */
                mouigosaService.updateQuestionMouigosas(searchMap);
            }
        }else{
            if (sts.equals("U")) {
                mouigosaService.updateQuestionMouigosa(searchMap); // 문제 수정
            } else {
                mouigosaService.insertQuestionMouigosa(searchMap); // 문제 등록
            }
        }

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_searchFlag", S_searchFlag);
        model.addAttribute("S_searchKeyWord", URLEncoder.encode(S_searchKeyWord,"UTF-8"));
        model.addAttribute("S_currentPage", S_currentPage);
        model.addAttribute("S_pageRow", S_pageRow);

        model.addAttribute("ITEMID", ITEMID);

        model.addAttribute("searchMap",searchMap);

        return "redirect:/mouigosa/mouigosaQuestionList.do";
    }

    // 모의고사 문제 수정 팝업화면 불러오기 상세
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/updateQuestionMouigosa.pop")
    public String updateQuestionMouigosaDetail(ModelMap model, HttpServletRequest request)
            throws UnsupportedEncodingException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(URLDecoder.decode(request.getParameter("S_searchKeyWord"),"UTF-8"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", S_searchKeyWord);
        searchMap.put("S_currentPage", S_currentPage);
        searchMap.put("S_pageRow", S_pageRow);

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);

        //과목영역 리스트
        List<?> subject_area_list = mouigosaService.getSubjectAreaList(searchMap);
        //문제 상세 조회
        List<?> list = mouigosaService.getUpdateQuestionDetail(searchMap);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("QUESTION",map.get("QUESTION"));
        searchMap.put("QUESTIONFILEID",map.get("QUESTIONFILEID"));
        searchMap.put("ANSWEREXPLAIN",map.get("ANSWEREXPLAIN"));
        searchMap.put("ANSWEREXPLAINFILEID",map.get("ANSWEREXPLAINFILEID"));
        searchMap.put("SMAQUESTIONFILEID",map.get("SMAQUESTIONFILEID"));
        searchMap.put("QUESTIONPATTERN",map.get("QUESTIONPATTERN"));
        searchMap.put("ANSWERNUMBER",map.get("ANSWERNUMBER"));
        searchMap.put("QUESTIONRANGE",map.get("QUESTIONRANGE"));
        searchMap.put("LEVELDIFFICULTY",map.get("LEVELDIFFICULTY"));
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
        searchMap.put("QUESTIONFILENAME",map.get("QUESTIONFILENAME"));
        searchMap.put("ANSWEREXPLAINFILENAME",map.get("ANSWEREXPLAINFILENAME"));
        searchMap.put("QUESTIONFILEPATH",map.get("QUESTIONFILEPATH"));
        searchMap.put("ANSWEREXPLAINFILEPATH",map.get("ANSWEREXPLAINFILEPATH"));
        searchMap.put("SMAQUESTIONFILEPATH",map.get("SMAQUESTIONFILEPATH"));

        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
        searchMap.put("PROF_NM",map.get("PROF_NM"));

        model.addAttribute("subject_area_list", subject_area_list);

        model.addAttribute("sts", "U");
        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaQuestionIns";
    }

    // 문제지정 팝업
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/updateQuestionAllMouigosa.pop")
    public String updateQuestionAllMouigosa(ModelMap model, HttpServletRequest request)
            throws UnsupportedEncodingException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(URLDecoder.decode(request.getParameter("S_searchKeyWord"),"UTF-8"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "1");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", S_searchKeyWord);
        searchMap.put("S_currentPage", S_currentPage);
        searchMap.put("S_pageRow", S_pageRow);

        searchMap.put("ITEMID", ITEMID);

        //상세 조회
        List<?> list = mouigosaService.getUpdateDetail(searchMap);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("SUBJECT_CD",map.get("SUBJECT_CD"));
        searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
        searchMap.put("PROFCODE",map.get("PROFCODE"));
        searchMap.put("PROF_NM",map.get("PROF_NM"));
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
        searchMap.put("OPENSTATE",map.get("OPENSTATE"));
        searchMap.put("STSCNT",map.get("STSCNT"));
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));

        //문제지정 리스트
        list = mouigosaService.getQuestionAllList(searchMap);
        //년
        List<?> list2 = mouigosaService.getExamYear(searchMap);
        //회
        List<?> list3 = mouigosaService.getExamRound(searchMap);
        //문제
        List<?> list4 = mouigosaService.getQuestionNumber(searchMap);

        model.addAttribute("list", list);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("list4", list4);

        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaQuestionAllIns";
    }

    // 문제지정 팝업 수정
    @RequestMapping(value="/mouigosaQuestionAllUpdate.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String mouigosaQuestionAllUpdate(ModelMap model, HttpServletRequest request) throws IllegalStateException, IOException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);
        searchMap.put("USER_ID", USER_ID);

        //문제지정 수정
        String[] EXAMYEAR = request.getParameterValues("EXAMYEAR");
        String[] EXAMROUND = request.getParameterValues("EXAMROUND");
        String[] QUESTIONNUMBER2 = request.getParameterValues("QUESTIONNUMBER2");
        String[] QUESTIONNUMBER = request.getParameterValues("QUESTIONNUMBER");

        if (ITEMID != null) {
            searchMap.put("EXAMYEAR", EXAMYEAR);
            searchMap.put("EXAMROUND", EXAMROUND);
            searchMap.put("QUESTIONNUMBER2", QUESTIONNUMBER2);
            searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
            /*
             for (int i = 0; i < EXAMYEAR.length; i++) {
                if (EXAMYEAR[i].length() > 0) {
                    searchMap.put("EXAMYEAR", EXAMYEAR[i]);
                    searchMap.put("EXAMROUND", EXAMROUND[i]);
                    searchMap.put("QUESTIONNUMBER2", QUESTIONNUMBER2[i]);
                    searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER[i]);

                    if(log.isDebugEnabled()){
                        log.debug("************ EXAMYEAR : "+ EXAMYEAR[i]);
                        log.debug("************ EXAMROUND : "+ EXAMROUND[i]);
                        log.debug("************ QUESTIONNUMBER2 : "+ QUESTIONNUMBER2[i]);
                        log.debug("************ QUESTIONNUMBER : "+ QUESTIONNUMBER[i]);
                    }

                    //수정 데이터를 위한 문제 리스트
                    List<?> list = mouigosaService.getQuestionAllList2(searchMap);

                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map = (HashMap<String, Object>) list.get(0);
                    searchMap.put("QUESTION",map.get("QUESTION"));
                    searchMap.put("QUESTIONFILEID",map.get("QUESTIONFILEID"));
                    searchMap.put("ANSWEREXPLAIN",map.get("ANSWEREXPLAIN"));
                    searchMap.put("ANSWEREXPLAINFILEID",map.get("ANSWEREXPLAINFILEID"));
                    searchMap.put("ANSWERNUMBER",map.get("ANSWERNUMBER"));
                    searchMap.put("QUESTIONRANGE",map.get("QUESTIONRANGE"));
                    searchMap.put("LEVELDIFFICULTY",map.get("LEVELDIFFICULTY"));

                    mouigosaService.updateQuestionAll(searchMap);
                }
            } */
            mouigosaService.updateQuestionAll(searchMap);
        }

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_searchFlag", S_searchFlag);
        model.addAttribute("S_searchKeyWord", URLEncoder.encode(S_searchKeyWord,"UTF-8"));
        model.addAttribute("S_currentPage", S_currentPage);
        model.addAttribute("S_pageRow", S_pageRow);

        model.addAttribute("ITEMID", ITEMID);

        model.addAttribute("searchMap",searchMap);

        return "redirect:/mouigosa/mouigosaQuestionList.do";
    }

    // 불러오기 팝업
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/updateQuestionOnlyMouigosa.pop")
    public String updateQuestionOnlyMouigosa(ModelMap model, HttpServletRequest request)
            throws UnsupportedEncodingException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(URLDecoder.decode(request.getParameter("S_searchKeyWord"),"UTF-8"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "1");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String SUBJECT_CD = CommonUtil.isNull(request.getParameter("SUBJECT_CD"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_searchFlag", S_searchFlag);
        searchMap.put("S_searchKeyWord", S_searchKeyWord);
        searchMap.put("S_currentPage", S_currentPage);
        searchMap.put("S_pageRow", S_pageRow);

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);

        //과목영역 리스트
        List<?> subject_area_list = mouigosaService.getSubjectAreaList(searchMap);
        //문제 상세 조회
        List<?> list = mouigosaService.getUpdateQuestionDetail(searchMap);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("QUESTION",map.get("QUESTION"));
        searchMap.put("QUESTIONFILEID",map.get("QUESTIONFILEID"));
        searchMap.put("ANSWEREXPLAIN",map.get("ANSWEREXPLAIN"));
        searchMap.put("ANSWEREXPLAINFILEID",map.get("ANSWEREXPLAINFILEID"));
        searchMap.put("QUESTIONPATTERN",map.get("QUESTIONPATTERN"));
        searchMap.put("ANSWERNUMBER",map.get("ANSWERNUMBER"));
        searchMap.put("QUESTIONRANGE",map.get("QUESTIONRANGE"));
        searchMap.put("LEVELDIFFICULTY",map.get("LEVELDIFFICULTY"));
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONREGISTRATIONOPTION",map.get("QUESTIONREGISTRATIONOPTION"));
        searchMap.put("QUESTIONFILENAME",map.get("QUESTIONFILENAME"));
        searchMap.put("ANSWEREXPLAINFILENAME",map.get("ANSWEREXPLAINFILENAME"));
        searchMap.put("QUESTIONFILEPATH",map.get("QUESTIONFILEPATH"));
        searchMap.put("ANSWEREXPLAINFILEPATH",map.get("ANSWEREXPLAINFILEPATH"));

        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("EXAMYEAR",map.get("EXAMYEAR"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("SUBJECT_NM",map.get("SUBJECT_NM"));
        searchMap.put("PROF_NM",map.get("PROF_NM"));

        // 검색조건
        String S_EXAMYEAR2 = CommonUtil.isNull(request.getParameter("S_EXAMYEAR2"), "");
        String S_EXAMROUND2 = CommonUtil.isNull(request.getParameter("S_EXAMROUND2"), "");
        String S_QUESTIONRANGE2 = CommonUtil.isNull(request.getParameter("S_QUESTIONRANGE2"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        searchMap.put("S_EXAMYEAR2", S_EXAMYEAR2);
        searchMap.put("S_EXAMROUND2", S_EXAMROUND2);
        searchMap.put("S_QUESTIONRANGE2", S_QUESTIONRANGE2);

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        // 블러오기 팝업 리스트
        list = mouigosaService.getMouigosaPopList(searchMap);

        // 블러오기 팝업 리스트 총 건수
        int listCount = mouigosaService.getPopCount(searchMap);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("subject_area_list", subject_area_list);

        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaQuestionOnlyIns";
    }

    // 불러오기 팝업 수정
    @RequestMapping(value="/mouigosaQuestionOnlyUpdate.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String mouigosaQuestionOnlyUpdate(ModelMap model, HttpServletRequest request) throws IllegalStateException, IOException {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_searchFlag = CommonUtil.isNull(request.getParameter("S_searchFlag"), "");
        String S_searchKeyWord = CommonUtil.isNull(request.getParameter("S_searchKeyWord"), "");
        String S_currentPage = CommonUtil.isNull(request.getParameter("S_currentPage"), "");
        String S_pageRow = CommonUtil.isNull(request.getParameter("S_pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        String ITEMID2 = CommonUtil.isNull(request.getParameter("ITEMID2"), "");
        String QUESTIONNUMBER2 = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER2"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        searchMap.put("USER_ID", USER_ID);
        searchMap.put("ITEMID2", ITEMID2);
        searchMap.put("QUESTIONNUMBER2", QUESTIONNUMBER2);

        mouigosaService.updateQuestionOnly(searchMap);

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_searchFlag", S_searchFlag);
        model.addAttribute("S_searchKeyWord", URLEncoder.encode(S_searchKeyWord,"UTF-8"));
        model.addAttribute("S_currentPage", S_currentPage);
        model.addAttribute("S_pageRow", S_pageRow);

        model.addAttribute("ITEMID", ITEMID);

        model.addAttribute("searchMap",searchMap);

        return "redirect:/mouigosa/mouigosaQuestionList.do";
    }

    // 모의고사 영역별 과목 리스트
    @RequestMapping(value="/AreaMain.do")
    public String AreaMain(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        model.addAttribute("params", params);
        return "mocktest/sbjArea/AreaMain";
    }

    // 모의고사 영역별 과목 리스트
    @RequestMapping(value="/AreaList.frm")
    public String subjectAreaList(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        // 모의고사 영역별 과목 리스트
        List<HashMap<String, String>> list = mouigosaService.getAreaSubjectListAll(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "mocktest/sbjArea/AreaList";
    }

    // 모의고사 영역별 과목 리스트
    @RequestMapping(value="/AreaView.frm")
    public String viewAreaList(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("USEFLAG", "Y");

        // 모의고사 영역별 과목 리스트
        List<HashMap<String, String>> list = mouigosaService.getSubjectAreaList(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "mocktest/sbjArea/AreaView";
    }

    // 과목 등록
    @RequestMapping(value="/areaInsert.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String insertArea(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECTAREA", CommonUtil.isNull(request.getParameter("SUBJECTAREA"),""));
        params.put("AREAORDER", CommonUtil.isNull(request.getParameter("AREAORDER"), ""));

        mouigosaService.insertAreaSubject(params);

        model.addAttribute("params", params);

        return "redirect:/mouigosa/AreaView.frm?SUBJECT_CD="+params.get("SUBJECT_CD");
    }

    // 과목 수정
    @RequestMapping(value="/areaUpdate.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String updateArea(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        String[] V_ID = request.getParameterValues("V_ID");
        
		if(V_ID != null){
			for(int i=0; i<V_ID.length; i++){																	// 주교재 루프
				params.put("ID", V_ID[i]);
				params.put("SUBJECTAREA", request.getParameter(V_ID[i]+"_SUBJECTAREA"));
				params.put("AREAORDER", request.getParameter(V_ID[i]+"_AREAORDER"));
				params.put("USEFLAG", request.getParameter(V_ID[i]+"_USEFLAG"));

				mouigosaService.updateAreaSubject(params);
			}
		}

        model.addAttribute("params", params);

        return "redirect:/mouigosa/AreaView.frm?SUBJECT_CD="+params.get("SUBJECT_CD");
    }

    // 모의고사 직종별 과목 메인
    @RequestMapping(value="/KindMain.do")
    public String KindMain(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        model.addAttribute("params", params);
        return "mocktest/kindSubject/KindMain";
    }

    // 모의고사 직종 리스트
    @RequestMapping(value="/KindList.frm")
    public String KindList(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        // 직종 리스트

        List<HashMap<String, Object>> kindList = mouigosaService.getClass(params);

        model.addAttribute("list", kindList);
        model.addAttribute("params", params);

        return "mocktest/kindSubject/KindList";
    }

    // 모의고사 직종별 과목 상세화면
    @RequestMapping(value="/SubjectList.frm")
    public String SubjectList(HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("CLASSCODE", CommonUtil.isNull(request.getParameter("CLASSCODE"),""));
        params.put("CLASSSERIESCODE", CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"),""));

        // 모의고사 영역별 과목 리스트
        List<HashMap<String, String>> list = mouigosaService.getClassSubject(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "mocktest/kindSubject/SubjectList";
    }

    // 과목 등록
    @RequestMapping(value="/kindSubjectInsert.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String kindSubjectInsert(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("CLASSCODE", CommonUtil.isNull(request.getParameter("CLASSCODE"),""));
        params.put("CLASSSERIESCODE", CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"),""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECTTYPEDIVISION", CommonUtil.isNull(request.getParameter("SUBJECTTYPEDIVISION"), ""));
        params.put("SUBJECTORDER", CommonUtil.isNull(request.getParameter("SUBJECTORDER"), ""));

        mouigosaService.insertTccsrsSubject(params);

        model.addAttribute("params", params);

        return "redirect:/mouigosa/SubjectList.frm?CLASSCODE="+params.get("CLASSCODE")+"&CLASSSERIESCODE="+params.get("CLASSSERIESCODE");
    }

    // 과목 수정
    @RequestMapping(value="/kindSubjectUpdate.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String kindSubjectUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("CLASSCODE", CommonUtil.isNull(request.getParameter("CLASSCODE"),""));
        params.put("CLASSSERIESCODE", CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"),""));
        String[] V_ID = request.getParameterValues("V_ID");
        
		if(V_ID != null){
			for(int i=0; i<V_ID.length; i++){
				params.put("ID", V_ID[i]);
				params.put("SUBJECTTYPEDIVISION", request.getParameter(V_ID[i]+"_DIV"));
				params.put("SUBJECTORDER", request.getParameter(V_ID[i]+"_OD"));

				mouigosaService.updateTccsrsSubject(params);
			}
		}

        model.addAttribute("params", params);

        return "redirect:/mouigosa/SubjectList.frm?CLASSCODE="+params.get("CLASSCODE")+"&CLASSSERIESCODE="+params.get("CLASSSERIESCODE");
    }

    // 과목 등록
    @RequestMapping(value="/kindSubjectDelete.do")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String kindSubjectDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("CLASSCODE", CommonUtil.isNull(request.getParameter("CLASSCODE"),""));
        params.put("CLASSSERIESCODE", CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"),""));
        params.put("ID", CommonUtil.isNull(request.getParameter("ID"),""));

        mouigosaService.deleteTccsrsSubject(params);

        model.addAttribute("params", params);

        return "redirect:/mouigosa/SubjectList.frm?CLASSCODE="+params.get("CLASSCODE")+"&CLASSSERIESCODE="+params.get("CLASSSERIESCODE");
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 04.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USERID", loginInfo.get("USER_ID"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "TM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
    }

}
