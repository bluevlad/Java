package web.mocktest.mouigosa;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import web.util.CommonUtil;
import web.util.excel.ExcelRead;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.mocktest.mouigosa.service.MouigosaService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/mouigosa/reg")
@Controller
public class MouigosaRegController {

    /** log */
    private Logger log = Logger.getLogger(this.getClass());
    
    @Inject
    private FileSystemResource fsResource;    //DI
    
    @Resource(name="fileUtil")
    FileUtil fileUtil;
    
    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Resource(name="excelRead")
    ExcelRead excelUtil;
    @Autowired
    private MouigosaService mouigosaService;

    // 모의고사 등록 리스트
    @RequestMapping(value="/mouigosaRegistrationList.do")
    public String mouigosaRegistrationList(ModelMap model, HttpServletRequest request) throws Exception {

    	HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		// 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("S_EXAMYEAR", S_EXAMYEAR);
        params.put("S_EXAMROUND", S_EXAMROUND);
        params.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        params.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        params.put("S_CLASSCODE", S_CLASSCODE);

        params.put("currentPage", currentPage);
        params.put("pageRow", pageRow);
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        // 모의고사 리스트
        List<?> list = mouigosaService.getMouigosaRegistrationList(params);

        //'직급' 리스트
        List<?> registration_list = mouigosaService.getRegistrationList(params);
        // 총 건수
        int listCount = mouigosaService.getRegistrationCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("registration_list", registration_list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("params", params);

        return "mocktest/mouigosa/mouigosaRegistrationList";
    }

    // 모의고사 등록 화면
    @RequestMapping(value="/createRegistrationMouigosa.do")
    public String createRegistrationMouigosa(ModelMap model, HttpServletRequest request) throws Exception {

    	HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        String currentPage = CommonUtil.isNull(request.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(request.getParameter("pageRow"), "");

        params.put("S_EXAMYEAR", S_EXAMYEAR);
        params.put("S_EXAMROUND", S_EXAMROUND);
        params.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        params.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        params.put("S_CLASSCODE", S_CLASSCODE);
        params.put("currentPage", currentPage);
        params.put("pageRow", pageRow);

        //'직급' 리스트
        List<?> registration_list = mouigosaService.getRegistrationList(params);

        model.addAttribute("sts", "I");
        model.addAttribute("registration_list", registration_list);
        model.addAttribute("params", params);

        return "mocktest/mouigosa/mouigosaRegistrationIns";
    }
    
    // 모의고사 등록 수정 불러오기 상세
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/updateRegistrationMouigosa.do")
    public String updateRegistrationMouigosaDetail(ModelMap model, HttpServletRequest request) throws Exception {

    	HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        String currentPage = CommonUtil.isNull(request.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(request.getParameter("pageRow"), "");

        String MOCKCODE = CommonUtil.isNull(request.getParameter("MOCKCODE"), "");

        params.put("S_EXAMYEAR", S_EXAMYEAR);
        params.put("S_EXAMROUND", S_EXAMROUND);
        params.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        params.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        params.put("S_CLASSCODE", S_CLASSCODE);
        params.put("currentPage", currentPage);
        params.put("pageRow", pageRow);
        params.put("MOCKCODE", MOCKCODE);

        //상세 조회
        List<?> list = mouigosaService.getUpdateRegistrationDetail(params);

        //'직급' 리스트
        List<?> registration_list = mouigosaService.getRegistrationList(params);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        params.put("ID",map.get("ID"));
        params.put("MOCKCODE",map.get("MOCKCODE"));
        params.put("MOCKNAME",map.get("MOCKNAME"));
        params.put("EXAMYEAR",map.get("EXAMYEAR"));
        params.put("EXAMROUND",map.get("EXAMROUND"));
        params.put("OFFCLOSEPERSONNUMBER",map.get("OFFCLOSEPERSONNUMBER"));
        params.put("CLASSCODE",map.get("CLASSCODE"));
        params.put("EXAMSTARTTIME",map.get("EXAMSTARTTIME"));
        params.put("EXAMENDTIME",map.get("EXAMENDTIME"));
        params.put("EXAMPERIOD",map.get("EXAMPERIOD"));
        params.put("EXAMTIME",map.get("EXAMTIME"));
        params.put("RECEIPTSTARTTIME",map.get("RECEIPTSTARTTIME"));
        params.put("RECEIPTENDTIME",map.get("RECEIPTENDTIME"));
        params.put("EXAMCOST",map.get("EXAMCOST"));
        params.put("DISCOUNTRATIO",map.get("DISCOUNTRATIO"));
        params.put("SALEAMOUNTS",map.get("SALEAMOUNTS"));
        params.put("USEFLAG",map.get("USEFLAG"));
        params.put("ISEXAMTYPEON",map.get("ISEXAMTYPEON"));
        params.put("ISEXAMTYPEOFF",map.get("ISEXAMTYPEOFF"));
        params.put("EXAMPERIODTYPE",map.get("EXAMPERIODTYPE"));

        //직렬 상세 조회
        List<?> subject_list = mouigosaService.getUpdateMouigosaTmockclsclsseriesDetail(params);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2 = (HashMap<String, Object>) subject_list.get(0);
        params.put("CLASSSERIESCODE",map2.get("CLASSSERIESCODE"));
        params.put("ITEMID_1",map2.get("ITEMID_1"));
        params.put("ITEMID_2",map2.get("ITEMID_2"));
        model.addAttribute("registration_list", registration_list);
        model.addAttribute("sts", "U");

        model.addAttribute("params", params);

        return "mocktest/mouigosa/mouigosaRegistrationIns";
    }

    // 모의고사 등록 수정
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/mouigosaRegistrationUpdate.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String updateRegistrationMouigosa(ModelMap model, HttpServletRequest request) {

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        String currentPage = CommonUtil.isNull(request.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(request.getParameter("pageRow"), "");

        String ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("ISEXAMTYPEON"), "0");
        String ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("ISEXAMTYPEOFF"), "0");
        String MOCKCODE = CommonUtil.isNull(request.getParameter("MOCKCODE"), "");
        String MOCKNAME = CommonUtil.isNull(request.getParameter("MOCKNAME"), "");
        String EXAMYEAR = CommonUtil.isNull(request.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(request.getParameter("EXAMROUND"), "");
        String OFFCLOSEPERSONNUMBER = CommonUtil.isNull(request.getParameter("OFFCLOSEPERSONNUMBER"), "");
        String CLASSCODE = CommonUtil.isNull(request.getParameter("CLASSCODE"), "");
        String EXAMSTARTTIME = CommonUtil.isNull(request.getParameter("EXAMSTARTTIME"), "");
        String EXAMENDTIME = CommonUtil.isNull(request.getParameter("EXAMENDTIME"), "");
        String EXAMPERIOD = CommonUtil.isNull(request.getParameter("EXAMPERIOD"), "");
        String EXAMTIME = CommonUtil.isNull(request.getParameter("EXAMTIME"), "");
        String RECEIPTSTARTTIME = CommonUtil.isNull(request.getParameter("RECEIPTSTARTTIME"), "");
        String RECEIPTENDTIME = CommonUtil.isNull(request.getParameter("RECEIPTENDTIME"), "");
        String EXAMCOST = CommonUtil.isNull(request.getParameter("EXAMCOST"), "");
        String DISCOUNTRATIO = CommonUtil.isNull(request.getParameter("DISCOUNTRATIO"), "");
        String SALEAMOUNTS = CommonUtil.isNull(request.getParameter("SALEAMOUNTS"), "");
        String USEFLAG = CommonUtil.isNull(request.getParameter("USEFLAG"), "");
        String EXAMPERIODTYPE = CommonUtil.isNull(request.getParameter("EXAMPERIODTYPE"), "");
        String CLASSSERIESCODE = CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        if (ISEXAMTYPEON.equals("")) {
            ISEXAMTYPEON = "0";
        }
        if (ISEXAMTYPEOFF.equals("")) {
            ISEXAMTYPEOFF = "0";
        }

        if (log.isDebugEnabled()) {
            //log.debug("***** 과목 등록 파라미터 ***** ");
            //log.debug("***** S_EXAMYEAR : " + S_EXAMYEAR);
            //log.debug("***** S_EXAMROUND : " + S_EXAMROUND);
            //log.debug("***** S_ISEXAMTYPEON : " + S_ISEXAMTYPEON);
            //log.debug("***** S_ISEXAMTYPEOFF : " + S_ISEXAMTYPEOFF);
            //log.debug("***** S_CLASSCODE : " + S_CLASSCODE);
            //log.debug("***** currentPage : " + currentPage);
            //log.debug("***** pageRow : " + pageRow);

            //log.debug("***** ISEXAMTYPEON : " + ISEXAMTYPEON);
            //log.debug("***** ISEXAMTYPEOFF : " + ISEXAMTYPEOFF);
            //log.debug("***** MOCKCODE : " + MOCKCODE);
            //log.debug("***** MOCKNAME : " + MOCKNAME);
            //log.debug("***** EXAMYEAR : " + EXAMYEAR);
            //log.debug("***** EXAMROUND : " + EXAMROUND);
            //log.debug("***** OFFCLOSEPERSONNUMBER : " + OFFCLOSEPERSONNUMBER);
            //log.debug("***** CLASSCODE : " + CLASSCODE);
            //log.debug("***** EXAMSTARTTIME : " + EXAMSTARTTIME);
            //log.debug("***** EXAMENDTIME : " + EXAMENDTIME);
            //log.debug("***** EXAMPERIOD : " + EXAMPERIOD);
            //log.debug("***** EXAMTIME : " + EXAMTIME);
            //log.debug("***** RECEIPTSTARTTIME : " + RECEIPTSTARTTIME);
            //log.debug("***** RECEIPTENDTIME : " + RECEIPTENDTIME);
            //log.debug("***** EXAMCOST : " + EXAMCOST);
            //log.debug("***** DISCOUNTRATIO : " + DISCOUNTRATIO);
            //log.debug("***** SALEAMOUNTS : " + SALEAMOUNTS);
            //log.debug("***** USEFLAG : " + USEFLAG);
            //log.debug("***** EXAMPERIODTYPE : " + EXAMPERIODTYPE);
            //log.debug("***** CLASSSERIESCODE : " + CLASSSERIESCODE);
            //log.debug("***** USER_ID : " + USER_ID);
        }

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("ISEXAMTYPEON", ISEXAMTYPEON);
        searchMap.put("ISEXAMTYPEOFF", ISEXAMTYPEOFF);
        searchMap.put("MOCKCODE", MOCKCODE);
        searchMap.put("MOCKNAME", MOCKNAME);
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("OFFCLOSEPERSONNUMBER", OFFCLOSEPERSONNUMBER);
        searchMap.put("CLASSCODE", CLASSCODE);
        searchMap.put("EXAMSTARTTIME", EXAMSTARTTIME);
        searchMap.put("EXAMENDTIME", EXAMENDTIME);
        searchMap.put("EXAMPERIOD", EXAMPERIOD);
        searchMap.put("EXAMTIME", EXAMTIME);
        searchMap.put("RECEIPTSTARTTIME", RECEIPTSTARTTIME);
        searchMap.put("RECEIPTENDTIME", RECEIPTENDTIME);
        searchMap.put("EXAMCOST", EXAMCOST);
        searchMap.put("DISCOUNTRATIO", DISCOUNTRATIO);
        searchMap.put("SALEAMOUNTS", SALEAMOUNTS);
        searchMap.put("USEFLAG", USEFLAG);
        searchMap.put("EXAMPERIODTYPE", EXAMPERIODTYPE);
        searchMap.put("CLASSSERIESCODE", CLASSSERIESCODE);
        searchMap.put("USER_ID", USER_ID);
        searchMap.put("USERID", USER_ID);

        // 모의고사 수정
        //mouigosaService.updateRegistrationMouigosa(searchMap);
        // 직렬 삭제
        //mouigosaService.deleteMouigosaTmockclsclsseries(searchMap);

        //직렬 삭제 후 등록
        String[] getInsertIds = request.getParameterValues("insertIds");
        if (log.isDebugEnabled()) {
            //log.debug("***** 직렬 삭제 후 등록 파라미터 ***** ");
            //log.debug("***** getInsertIds : " + getInsertIds);
            //log.debug("***** getInsertIds.length : " + getInsertIds.length);
        }

        for (int i = 0; i < getInsertIds.length; i++) {
            if (getInsertIds[i].length() > 0) {
                String [] tmp = null;
                tmp = getInsertIds[i].split(",");
                if(log.isDebugEnabled()){
                    //log.debug("************ tmp.length : "+ tmp.length);
                }

                for (int j = 0; j < tmp.length; j++) {
                    String CLASSSERIESCODE2 = tmp[j];
                    searchMap.put("CLASSSERIESCODE2", CLASSSERIESCODE2);
                    if(log.isDebugEnabled()){
                        //log.debug("************ CLASSSERIESCODE2 : "+ CLASSSERIESCODE2);
                    }

                    //직렬 삭제 후 등록
                    //mouigosaService.insertMouigosaTmockclsclsseries(searchMap);
                }
            }
        }

        // 모의고사 등록 필수 , 선택과목 삭제
        //mouigosaService.deleteMouigosaTmocksubject(searchMap);

        //필수 과목 등록
        String[] SUBJECTPERIOD1 = request.getParameterValues("SUBJECTPERIOD1");
        String[] ITEMID1 = request.getParameterValues("ITEMID1");
        String[] SUBJECT_CD1 = request.getParameterValues("SUBJECT_CD1");
        String[] SUBJECTORDER1 = request.getParameterValues("SUBJECTORDER1");

        if (ITEMID1 != null) {
            if (log.isDebugEnabled()) {
                //log.debug("***** 필수 과목 등록 파라미터 ***** ");
                //log.debug("***** SUBJECTPERIOD1 : " + SUBJECTPERIOD1);
                //log.debug("***** SUBJECTPERIOD1.length : " + SUBJECTPERIOD1.length);
                //log.debug("***** ITEMID1 : " + ITEMID1);
                //log.debug("***** ITEMID1.length : " + ITEMID1.length);
                //log.debug("***** SUBJECT_CD1 : " + SUBJECT_CD1);
                //log.debug("***** getInsertIds.SUBJECT_CD1 : " + SUBJECT_CD1.length);
                //log.debug("***** SUBJECTORDER1 : " + SUBJECTORDER1);
                //log.debug("***** getInsertIds.SUBJECTORDER1 : " + SUBJECTORDER1.length);
            }

            for (int i = 0; i < SUBJECTPERIOD1.length; i++) {
                if (SUBJECTPERIOD1[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD1[i];
                    String ITEMID = ITEMID1[i];
                    String SUBJECT_CD = SUBJECT_CD1[i];
                    String SUBJECTORDER = SUBJECTORDER1[i];

                    searchMap.put("SUBJECTTYPEDIVISION", "1");
                    searchMap.put("ITEMID", ITEMID);
                    searchMap.put("SUBJECT_CD", SUBJECT_CD);
                    searchMap.put("SUBJECTORDER", SUBJECTORDER);
                    searchMap.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    if(log.isDebugEnabled()){
                        //log.debug("************ SUBJECTPERIOD : "+ SUBJECTPERIOD);
                        //log.debug("************ ITEMID : "+ ITEMID);
                        //log.debug("************ SUBJECT_CD : "+ SUBJECT_CD);
                        //log.debug("************ SUBJECTORDER : "+ SUBJECTORDER);
                    }

                    //mouigosaService.insertMouigosaTmocksubject(searchMap);
                }
            }
        }

        //필수 과목 체크 삭제
        String[] deleteIds = request.getParameterValues("deleteIds");

        if (log.isDebugEnabled()) {
            //log.debug("***** 필수 과목 체크 삭제 파라미터 ***** ");
            //log.debug("***** deleteIds : " + deleteIds);
            //log.debug("***** deleteIds.length : " + deleteIds.length);
        }

        for (int i = 0; i < deleteIds.length; i++) {
            if (deleteIds[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds[i].split(",");
                if(log.isDebugEnabled()){
                    //log.debug("************ tmp.length : "+ tmp.length);
                }

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    searchMap.put("ITEMID", ITEMID);
                    searchMap.put("SUBJECTTYPEDIVISION", "1");
                    if(log.isDebugEnabled()){
                        //log.debug("************ ITEMID : "+ ITEMID);
                    }

                    //mouigosaService.deleteMouigosaTmockSubject_ck(searchMap);
                }
            }
        }

        //선택 과목 등록
        String[] SUBJECTPERIOD2 = request.getParameterValues("SUBJECTPERIOD2");
        String[] ITEMID2 = request.getParameterValues("ITEMID2");
        String[] SUBJECT_CD2 = request.getParameterValues("SUBJECT_CD2");
        String[] SUBJECTORDER2 = request.getParameterValues("SUBJECTORDER2");

        if (ITEMID2 != null) {
            if (log.isDebugEnabled()) {
                //log.debug("***** 선택 과목 등록 파라미터 ***** ");
                //log.debug("***** SUBJECTPERIOD2 : " + SUBJECTPERIOD2);
                //log.debug("***** SUBJECTPERIOD2.length : " + SUBJECTPERIOD2.length);
                //log.debug("***** ITEMID2 : " + ITEMID2);
                //log.debug("***** ITEMID2.length : " + ITEMID2.length);
                //log.debug("***** SUBJECT_CD2 : " + SUBJECT_CD2);
                //log.debug("***** SUBJECT_CD2.length : " + SUBJECT_CD2.length);
                //log.debug("***** SUBJECTORDER2 : " + SUBJECTORDER2);
                //log.debug("***** getInsertIds.SUBJECTORDER2 : " + SUBJECTORDER2.length);
            }

            for (int i = 0; i < SUBJECTPERIOD2.length; i++) {
                if (SUBJECTPERIOD2[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD2[i];
                    String ITEMID = ITEMID2[i];
                    String SUBJECT_CD = SUBJECT_CD2[i];
                    String SUBJECTORDER = SUBJECTORDER2[i];

                    searchMap.put("SUBJECTTYPEDIVISION", "2");
                    searchMap.put("ITEMID", ITEMID);
                    searchMap.put("SUBJECT_CD", SUBJECT_CD);
                    searchMap.put("SUBJECTORDER", SUBJECTORDER);
                    searchMap.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    if(log.isDebugEnabled()){
                        //log.debug("************ SUBJECTPERIOD : "+ SUBJECTPERIOD);
                        //log.debug("************ ITEMID : "+ ITEMID);
                        //log.debug("************ SUBJECT_CD : "+ SUBJECT_CD);
                        //log.debug("************ SUBJECTORDER : "+ SUBJECTORDER);
                    }

                    //mouigosaService.insertMouigosaTmocksubject(searchMap);
                }
            }
        }

        //선택 과목 체크 삭제
        String[] deleteIds2 = request.getParameterValues("deleteIds2");
        if (log.isDebugEnabled()) {
            //log.debug("***** 선택 과목 체크 삭제 파라미터 ***** ");
            //log.debug("***** deleteIds2 : " + deleteIds2);
            //log.debug("***** deleteIds2.length : " + deleteIds2.length);
        }

        for (int i = 0; i < deleteIds2.length; i++) {
            if (deleteIds2[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds2[i].split(",");
                if(log.isDebugEnabled()){
                    //log.debug("************ tmp.length : "+ tmp.length);
                }

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    searchMap.put("ITEMID", ITEMID);
                    searchMap.put("SUBJECTTYPEDIVISION", "2");
                    if(log.isDebugEnabled()){
                        //log.debug("************ ITEMID : "+ ITEMID);
                    }

                    //mouigosaService.deleteMouigosaTmockSubject_ck(searchMap);
                }
            }
        }

        // 접수자 과목 변경을위한 수정된 필수 리스트
        searchMap.put("SUBJECTTYPEDIVISION", "1");
        List<?> list = mouigosaService.getTmocksubjectDetailList(searchMap);
        if (list.size() > 0) {
            for (int k = 0; k < list.size(); k++) {
                HashMap<String, String> map = (HashMap<String, String>) list.get(k);
                String IDENTYID = (String) map.get("IDENTYID");
                String ITEMID = String.valueOf(map.get("ITEMID"));
                String SUBJECTTYPEDIVISION = String.valueOf(map.get("SUBJECTTYPEDIVISION") );
                String ITEMID_2 = String.valueOf(map.get("ITEMID_2"));

                if (log.isDebugEnabled()) {
                    //log.debug("***** 옵션4(엑셀) 업로드 *****===> " + k+1);
                    //log.debug("***** IDENTYID : " + IDENTYID);
                    //log.debug("***** ITEMID : " + ITEMID);
                    //log.debug("***** SUBJECTTYPEDIVISION : " + SUBJECTTYPEDIVISION);
                    //log.debug("***** ITEMID_2 : " + ITEMID_2);
                }

                searchMap.put("IDENTYID", IDENTYID);
                searchMap.put("ITEMID", ITEMID);
                searchMap.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);
                searchMap.put("ITEMID_2", ITEMID_2);

                //접수자 과목 변경을 위한 필수과목 수정
                //mouigosaService.updateTuserChoiceSubject(searchMap);
            }
        }

        // 접수자 과목 변경을위한 수정된 선택 리스트
        searchMap.put("SUBJECTTYPEDIVISION", "2");
        list = mouigosaService.getTmocksubjectDetailList(searchMap);
        if (list.size() > 0) {
            for (int k = 0; k < list.size(); k++) {
                HashMap<String, String> map = (HashMap<String, String>) list.get(k);
                String IDENTYID = (String) map.get("IDENTYID");
                String ITEMID = String.valueOf(map.get("ITEMID"));
                String SUBJECTTYPEDIVISION = String.valueOf(map.get("SUBJECTTYPEDIVISION"));
                String ITEMID_2 = String.valueOf( map.get("ITEMID_2"));

                if (log.isDebugEnabled()) {
                    //log.debug("***** 옵션4(엑셀) 업로드 *****===> " + k+1);
                    //log.debug("***** IDENTYID : " + IDENTYID);
                    //log.debug("***** ITEMID : " + ITEMID);
                    //log.debug("***** SUBJECTTYPEDIVISION : " + SUBJECTTYPEDIVISION);
                    //log.debug("***** ITEMID_2 : " + ITEMID_2);
                }

                searchMap.put("IDENTYID", IDENTYID);
                searchMap.put("ITEMID", ITEMID);
                searchMap.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);
                searchMap.put("ITEMID_2", ITEMID_2);

                //접수자 과목 변경을 위한 선택과목 수정
                //mouigosaService.updateTuserChoiceSubject(searchMap);
            }
        }

        //직렬 삭제 후 등록
        //String[] getInsertIds = request.getParameterValues("insertIds");
        searchMap.put("insertIds", getInsertIds);

        //필수 과목 등록
        //String[] SUBJECTPERIOD1 = request.getParameterValues("SUBJECTPERIOD1");
        //String[] ITEMID1 = request.getParameterValues("ITEMID1");
        //String[] SUBJECT_CD1 = request.getParameterValues("SUBJECT_CD1");
        //String[] SUBJECTORDER1 = request.getParameterValues("SUBJECTORDER1");
        searchMap.put("SUBJECTPERIOD1", SUBJECTPERIOD1);
        searchMap.put("ITEMID1", ITEMID1);
        searchMap.put("SUBJECT_CD1", SUBJECT_CD1);
        searchMap.put("SUBJECTORDER1", SUBJECTORDER1);

        //필수 과목 삭제 체크
        //String[] deleteIds = request.getParameterValues("deleteIds");
        searchMap.put("deleteIds", deleteIds);

        //선택 과목 등록
        //String[] SUBJECTPERIOD2 = request.getParameterValues("SUBJECTPERIOD2");
        //String[] ITEMID2 = request.getParameterValues("ITEMID2");
        //String[] SUBJECT_CD2 = request.getParameterValues("SUBJECT_CD2");
        //String[] SUBJECTORDER2 = request.getParameterValues("SUBJECTORDER2");
        searchMap.put("SUBJECTPERIOD2", SUBJECTPERIOD2);
        searchMap.put("ITEMID2", ITEMID2);
        searchMap.put("SUBJECT_CD2", SUBJECT_CD2);
        searchMap.put("SUBJECTORDER2", SUBJECTORDER2);

        //선택 과목 삭제 체크
        //String[] deleteIds2 = request.getParameterValues("deleteIds2");
        searchMap.put("deleteIds2", deleteIds2);

        mouigosaService.updateRegistrationMouigosaWthSubj(searchMap);

        model.addAttribute("S_EXAMYEAR", S_EXAMYEAR);
        model.addAttribute("S_EXAMROUND", S_EXAMROUND);
        model.addAttribute("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        model.addAttribute("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        model.addAttribute("S_CLASSCODE", S_CLASSCODE);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);

        return "redirect:/mouigosa/reg/mouigosaRegistrationList.do";
    }

    //for ajax
    // '직급'에 대한 직렬코드 가져오기
    @RequestMapping(value="/subCode2.do")
    @ResponseBody
    public List<?> subCode2(ModelMap output, HttpServletRequest req) {
        // 검색조건
        String CLASSCODE = CommonUtil.isNull(req.getParameter("CLASSCODE"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("CLASSCODE", CLASSCODE);

        //직렬 리스트
        List<?> sub_list = mouigosaService.getSubCode2(searchMap);

        return sub_list;
    }

    //for ajax
    // 필수과목(교수) 비동기
    @RequestMapping(value="/subCode3.do")
    @ResponseBody
    public List<?> subCode3(ModelMap output, HttpServletRequest req) {
        // 검색조건
        String CLASSCODE = CommonUtil.isNull(req.getParameter("CLASSCODE"), "");
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(req.getParameter("EXAMROUND"), "");
        String MOCKCODE = CommonUtil.isNull(req.getParameter("MOCKCODE"), "");
        String sts = CommonUtil.isNull(req.getParameter("sts"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("CLASSCODE", CLASSCODE);
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("MOCKCODE", MOCKCODE);

        List<?> sub_list = null;
        // 필수과목(교수) 비동기
        if (sts.equals("I")) {
            sub_list = mouigosaService.getSubCode3_ins(searchMap);
        }else{
            sub_list = mouigosaService.getSubCode3(searchMap);
        }

        return sub_list;
    }

    //for ajax
    // 선택과목(교수) 비동기
    @RequestMapping(value="/subCode4.do")
    @ResponseBody
    public List<?> subCode4(ModelMap output, HttpServletRequest req) {
        // 검색조건
        String CLASSCODE = CommonUtil.isNull(req.getParameter("CLASSCODE"), "");
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(req.getParameter("EXAMROUND"), "");
        String MOCKCODE = CommonUtil.isNull(req.getParameter("MOCKCODE"), "");
        String sts = CommonUtil.isNull(req.getParameter("sts"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("CLASSCODE", CLASSCODE);
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("MOCKCODE", MOCKCODE);

        List<?> sub_list = null;
        // 선택과목(교수) 비동기
        if (sts.equals("I")) {
            sub_list = mouigosaService.getSubCode4_ins(searchMap);
        }else{
            sub_list = mouigosaService.getSubCode4(searchMap);
        }

        return sub_list;
    }

    // for ajax
    // 년에 대한 회차 가져오기
    @RequestMapping(value="/getRegistrationExamRound.do")
    @ResponseBody
    public List<?> getRegistrationExamRound(ModelMap output, HttpServletRequest req) {
        // 검색조건
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("EXAMYEAR", EXAMYEAR);

        //교수 리스트
        List<?> sub_list = mouigosaService.getRegistrationExamRound(searchMap);

        return sub_list;
    }

    // for ajax
    // 년,회차에 대한 과목코드 가져오기
    @RequestMapping(value="/getRegistrationSubject.do")
    @ResponseBody
    public List<?> getRegistrationSubject(ModelMap output, HttpServletRequest req) {
        // 검색조건
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(req.getParameter("EXAMROUND"), "");
        String CLASSCODE = CommonUtil.isNull(req.getParameter("CLASSCODE"), "");
        String SUBJECTTYPEDIVISION = CommonUtil.isNull(req.getParameter("SUBJECTTYPEDIVISION"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("CLASSCODE", CLASSCODE);
        searchMap.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);

        //교수 리스트
        List<?> sub_list = mouigosaService.getRegistrationSubject(searchMap);

        return sub_list;
    }

    // 필수/선택과목 불러오기 팝업
    @RequestMapping(value="/updateRegistrationOnlyMouigosa.pop")
    public String updateRegistrationOnlyMouigosa(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {

        // 검색조건
        String EXAMYEAR = CommonUtil.isNull(req.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(req.getParameter("EXAMROUND"), "");
        String SUBJECT_CD = CommonUtil.isNull(req.getParameter("SUBJECT_CD"), "");
        String PROFCODE = CommonUtil.isNull(req.getParameter("PROFCODE"), "");
        String SUBJECTTYPEDIVISION = CommonUtil.isNull(req.getParameter("SUBJECTTYPEDIVISION"), "");
        String CLASSCODE = CommonUtil.isNull(req.getParameter("CLASSCODE"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("SUBJECT_CD", SUBJECT_CD);
        searchMap.put("PROFCODE", PROFCODE);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));
        searchMap.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);
        searchMap.put("CLASSCODE", CLASSCODE);

        //년 리스트
        List<?> exam_list = mouigosaService.getRegistrationExamYear(searchMap);

        // 필수/선택과목 불러오기 팝업 리스트
        List<?> list = mouigosaService.getRegistrationPopList(searchMap);

        // 필수/선택과목 블러오기 팝업 리스트 총 건수
        int listCount = mouigosaService.getRegistrationPopCount(searchMap);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        output.addAttribute("list", list);
        output.addAttribute("exam_list", exam_list);
        output.addAttribute("pagingHtml", pagingHtml);
        output.addAttribute("totalCount", listCount);
        output.addAttribute("currentPage", currentPage);
        output.addAttribute("pageRow", pageRow);

        output.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/mouigosaRegistrationOnlyIns";
    }

    //for ajax
    // 모의고사 등록 max값
    @RequestMapping(value="/registrationMax.do")
    @ResponseBody
    public List<?> registrationMax(ModelMap output, HttpServletRequest req) {

        Map<String, Object> searchMap = new HashMap<String, Object>();

        //모의고사 등록 max값
        List<?> list = mouigosaService.getRegistrationMax(searchMap);

        return list;
    }
   
    // 모의고사 등록 등록
    @RequestMapping(value="/mouigosaRegistrationInsert.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String insertRegistrationMouigosa(ModelMap model, HttpServletRequest request) {
        // 검색조건
        String ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("ISEXAMTYPEON"), "0");
        String ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("ISEXAMTYPEOFF"), "0");
        String MOCKCODE = CommonUtil.isNull(request.getParameter("MOCKCODE"), "");
        String MOCKNAME = CommonUtil.isNull(request.getParameter("MOCKNAME"), "");
        String EXAMYEAR = CommonUtil.isNull(request.getParameter("EXAMYEAR"), "");
        String EXAMROUND = CommonUtil.isNull(request.getParameter("EXAMROUND"), "");
        String OFFCLOSEPERSONNUMBER = CommonUtil.isNull(request.getParameter("OFFCLOSEPERSONNUMBER"), "");
        String CLASSCODE = CommonUtil.isNull(request.getParameter("CLASSCODE"), "");
        String EXAMSTARTTIME = CommonUtil.isNull(request.getParameter("EXAMSTARTTIME"), "");
        String EXAMENDTIME = CommonUtil.isNull(request.getParameter("EXAMENDTIME"), "");
        String EXAMPERIOD = CommonUtil.isNull(request.getParameter("EXAMPERIOD"), "");
        String EXAMTIME = CommonUtil.isNull(request.getParameter("EXAMTIME"), "");
        String RECEIPTSTARTTIME = CommonUtil.isNull(request.getParameter("RECEIPTSTARTTIME"), "");
        String RECEIPTENDTIME = CommonUtil.isNull(request.getParameter("RECEIPTENDTIME"), "");
        String EXAMCOST = CommonUtil.isNull(request.getParameter("EXAMCOST"), "");
        String DISCOUNTRATIO = CommonUtil.isNull(request.getParameter("DISCOUNTRATIO"), "");
        String SALEAMOUNTS = CommonUtil.isNull(request.getParameter("SALEAMOUNTS"), "");
        String USEFLAG = CommonUtil.isNull(request.getParameter("USEFLAG"), "");
        String EXAMPERIODTYPE = CommonUtil.isNull(request.getParameter("EXAMPERIODTYPE"), "");
        String CLASSSERIESCODE = CommonUtil.isNull(request.getParameter("CLASSSERIESCODE"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

        if (ISEXAMTYPEON.equals("")) {
            ISEXAMTYPEON = "0";
        }
        if (ISEXAMTYPEOFF.equals("")) {
            ISEXAMTYPEOFF = "0";
        }

        if (log.isDebugEnabled()) {

        }

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("ISEXAMTYPEON", ISEXAMTYPEON);
        searchMap.put("ISEXAMTYPEOFF", ISEXAMTYPEOFF);
        searchMap.put("MOCKCODE", MOCKCODE);
        searchMap.put("MOCKNAME", MOCKNAME);
        searchMap.put("EXAMYEAR", EXAMYEAR);
        searchMap.put("EXAMROUND", EXAMROUND);
        searchMap.put("OFFCLOSEPERSONNUMBER", OFFCLOSEPERSONNUMBER);
        searchMap.put("CLASSCODE", CLASSCODE);
        searchMap.put("EXAMSTARTTIME", EXAMSTARTTIME);
        searchMap.put("EXAMENDTIME", EXAMENDTIME);
        searchMap.put("EXAMPERIOD", EXAMPERIOD);
        searchMap.put("EXAMTIME", EXAMTIME);
        searchMap.put("RECEIPTSTARTTIME", RECEIPTSTARTTIME);
        searchMap.put("RECEIPTENDTIME", RECEIPTENDTIME);
        searchMap.put("EXAMCOST", EXAMCOST);
        searchMap.put("DISCOUNTRATIO", DISCOUNTRATIO);
        searchMap.put("SALEAMOUNTS", SALEAMOUNTS);
        searchMap.put("USEFLAG", USEFLAG);
        searchMap.put("EXAMPERIODTYPE", EXAMPERIODTYPE);
        searchMap.put("CLASSSERIESCODE", CLASSSERIESCODE);
        searchMap.put("USER_ID", USER_ID);
        searchMap.put("USERID", USER_ID);

        //직렬 등록
        String[] getInsertIds = request.getParameterValues("insertIds");
        searchMap.put("insertIds", getInsertIds);

        //필수 과목 등록
        String[] SUBJECTPERIOD1 = request.getParameterValues("SUBJECTPERIOD1");
        String[] ITEMID1 = request.getParameterValues("ITEMID1");
        String[] SUBJECT_CD1 = request.getParameterValues("SUBJECT_CD1");
        String[] SUBJECTORDER1 = request.getParameterValues("SUBJECTORDER1");
        searchMap.put("SUBJECTPERIOD1", SUBJECTPERIOD1);
        searchMap.put("ITEMID1", ITEMID1);
        searchMap.put("SUBJECT_CD1", SUBJECT_CD1);
        searchMap.put("SUBJECTORDER1", SUBJECTORDER1);

        //필수 과목 삭제 체크
        String[] deleteIds = request.getParameterValues("deleteIds");
        searchMap.put("deleteIds", deleteIds);

        //선택 과목 등록
        String[] SUBJECTPERIOD2 = request.getParameterValues("SUBJECTPERIOD2");
        String[] ITEMID2 = request.getParameterValues("ITEMID2");
        String[] SUBJECT_CD2 = request.getParameterValues("SUBJECT_CD2");
        String[] SUBJECTORDER2 = request.getParameterValues("SUBJECTORDER2");
        searchMap.put("SUBJECTPERIOD2", SUBJECTPERIOD2);
        searchMap.put("ITEMID2", ITEMID2);
        searchMap.put("SUBJECT_CD2", SUBJECT_CD2);
        searchMap.put("SUBJECTORDER2", SUBJECTORDER2);

        //선택 과목 삭제 체크
        String[] deleteIds2 = request.getParameterValues("deleteIds2");
        searchMap.put("deleteIds2", deleteIds2);

        mouigosaService.insertRegistrationMouigosaWthSubj(searchMap);

        return "redirect:/mouigosa/reg/mouigosaRegistrationList.do";
    }
       
    // 동형모의고사 문제 등록 등록
    @RequestMapping(value="/mouigosaQuestionIns.do")
    @ResponseBody
    public String mouigosaQuestionIns(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        
        String DAP = CommonUtil.isNull(request.getParameter("DAP"), "");
        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");

        
        params.put("ANSWERNUMBER", DAP);
        params.put("ITEMID", ITEMID);
        params.put("USER_ID",loginInfo.get("USER_ID"));
        params.put("QUESTIONNUMBER",QUESTIONNUMBER);
        
        String rootPath = fsResource.getPath();
        String subPath = "board/";
		String QUESTIONFID = "";
		String ANSWERFID = "";
		String SMAQUESTION = "";
		

        MultipartFile uploadFile1 = multiRequest.getFile("imgFile1");
		

        if(uploadFile1 != null && uploadFile1.isEmpty() == false) {
        	
        	QUESTIONFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile1, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            params.put("REAL_FILE_NAME", uploadFile1.getOriginalFilename().toString() );
            params.put("QUESTIONFID", QUESTIONFID);
            params.put("ATTACHFILEID", QUESTIONFID);

            mouigosaService.D_ATTACHFILE_Insert(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("FILE_SIZE", "");
            params.put("ATTACHFILEID", QUESTIONFID);
            params.put("QUESTIONFID", QUESTIONFID);
        }
        
       
        MultipartFile uploadFile2 = multiRequest.getFile("imgFile2");
        if(uploadFile2 != null && uploadFile2.isEmpty() == false) {
        	
        	ANSWERFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile2, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile2.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);

            mouigosaService.D_ATTACHFILE_Insert(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
        }
        
        MultipartFile uploadFile3 = multiRequest.getFile("imgFile3");
        if(uploadFile3 != null && uploadFile3.isEmpty() == false) {
        	
        	SMAQUESTION = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile3, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile3.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", SMAQUESTION);
            params.put("SMAQUESTION", SMAQUESTION);

            mouigosaService.D_ATTACHFILE_Insert(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", SMAQUESTION);
            params.put("SMAQUESTION", SMAQUESTION);
        }
		
		mouigosaService.D_LecPool_Upd(params);
		

        
        return "Y";
    }

    // 모의고사 등록 삭제
    @RequestMapping(value="/deleteRegistrationMouigosa.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String deleteRegistrationMouigosa(ModelMap model, HttpServletRequest request) {
        // 검색조건
        String[] deleteIds = request.getParameterValues("deleteIds");
        if (log.isDebugEnabled()) {
            //log.debug("***** 모의고사 등록 삭제 파라미터 ***** ");
            //log.debug("***** deleteIds : " + deleteIds);
            //log.debug("***** deleteIds.length : " + deleteIds.length);
        }

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        /*
        for (int i = 0; i < deleteIds.length; i++) {
            if (deleteIds[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds[i].split(",");
                if(log.isDebugEnabled()){
                    log.debug("************ tmp.length : "+ tmp.length);
                }

                for (int j = 0; j < tmp.length; j++) {
                    String MOCKCODE = tmp[j];
                    searchMap.put("MOCKCODE", MOCKCODE);
                    if(log.isDebugEnabled()){
                        log.debug("************ MOCKCODE : "+ MOCKCODE);
                    }

                    //모의고사 삭제
                    mouigosaService.deleteMouigosaTmockregistration(searchMap);
                    //직렬 삭제
                    mouigosaService.deleteMouigosaTmockclsclsseries2(searchMap);
                    //모의고사 등록 필수 , 선택과목 삭제
                    mouigosaService.deleteMouigosaTmocksubject(searchMap);
                    //응시자선택과목 삭제
                    mouigosaService.deleteMouigosaTuserChoiceSubject(searchMap);
                    //신청자관리 삭제
                    mouigosaService.deleteMouigosaTofferer(searchMap);
                    //성적 삭제
                    mouigosaService.deleteMouigosaTmockGrade(searchMap);
                    //응시자답변 삭제
                    mouigosaService.deleteMouigosaTexamineeAnswer(searchMap);
                    //오답노트 삭제
                    mouigosaService.deleteMouigosaTwrongAnswerNote(searchMap);
                }
            }
        }
         */

        searchMap.put("deleteIds", deleteIds);

        mouigosaService.deleteRegistrationMouigosa(searchMap);

        return "redirect:/mouigosa/reg/mouigosaRegistrationList.do";
    }

    // 모의고사 목록 팝업
    @RequestMapping(value="/searchExamList.pop")
    public String searchExamList(ModelMap model, HttpServletRequest request) {
        Map<String, Object> searchMap = new HashMap<String, Object>();

        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

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
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        searchMap.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        searchMap.put("S_CLASSCODE", S_CLASSCODE);

        // 모의고사 리스트
        List<?> list = mouigosaService.getMouigosaRegistrationList(searchMap);
        // 총 건수
        int listCount = mouigosaService.getRegistrationCount(searchMap);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("registration_list", mouigosaService.getRegistrationList(searchMap));//'직급' 리스트

        searchMap.put("SRCHCODE", CommonUtil.isNull(request.getParameter("SRCHCODE"), ""));
        searchMap.put("SRCHTXT", CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));

        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/searchExamList_pop";
    }

    // 동형 모의고사 등록 리스트
    @RequestMapping(value="/D_mouigosaRegistrationList.do")
    public String D_mouigosaRegistrationList(ModelMap model, HttpServletRequest request) throws Exception  {

    	HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

        // 검색조건
        String SEARCH_TXT = CommonUtil.isNull(request.getParameter("SEARCH_TXT"), "");
        String SEARCH_TYPE = CommonUtil.isNull(request.getParameter("SEARCH_TYPE"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();

        searchMap.put("SEARCH_TXT", SEARCH_TXT);
        searchMap.put("SEARCH_TYPE", SEARCH_TYPE);

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        // 모의고사 리스트
        List<?> list = mouigosaService.D_getMouigosaRegistrationList(params);

        // 총 건수
        int listCount = mouigosaService.D_getRegistrationCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("params", params);

        return "mocktest/mouigosa/D_mouigosaRegistrationList";
    }
    
    // 동형모의고사 문제 등록 팝업화면 불러오기
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/D_createQuestionMouigosa.pop")
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


        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");



        model.addAttribute("ITEMID", ITEMID);
        model.addAttribute("QUESTIONNUMBER", QUESTIONNUMBER);
        model.addAttribute("ENTRYNUM", ENTRYNUM);



        return "mocktest/mouigosa/D_mouigosaQuestionIns";
    }
    
    
    // 동형모의고사 문제 수정
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/D_mouigosaQuestionUpd.pop")
    public String mouigosaQuestionUpd(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {


        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Object> searchMap2 = new HashMap<String, Object>();
        Map<String, Object> searchMap3 = new HashMap<String, Object>();
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));
		model.addAttribute("ENTRYNUM", ENTRYNUM);

        searchMap.put("ITEMID", ITEMID);
        searchMap.put("QUESTIONNUMBER", QUESTIONNUMBER);
        

        //문제 상세 조회
        List<?> list1 = mouigosaService.getPoolImgFile1(searchMap);
        List<?> list2 = mouigosaService.getPoolImgFile2(searchMap);
        List<?> list3 = mouigosaService.getPoolImgFile3(searchMap);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list1.get(0);
        searchMap.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("QUESTIONFILEID",map.get("QUESTIONFILEID"));
        searchMap.put("FILEPATH",map.get("FILEPATH"));
        searchMap.put("FILENAME",map.get("FILENAME"));
        searchMap.put("ANSWERNUMBER",map.get("ANSWERNUMBER"));
        
        map = (HashMap<String, Object>) list2.get(0);
        searchMap2.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
        searchMap2.put("ITEMID",map.get("ITEMID"));
        searchMap2.put("ANSWEREXPLAINFILEID",map.get("ANSWEREXPLAINFILEID"));
        searchMap2.put("FILEPATH",map.get("FILEPATH"));
        searchMap2.put("FILENAME",map.get("FILENAME"));
        
        if(list3.size() > 0){
	        map = (HashMap<String, Object>) list3.get(0);
	        searchMap3.put("QUESTIONNUMBER",map.get("QUESTIONNUMBER"));
	        searchMap3.put("ITEMID",map.get("ITEMID"));
	        searchMap3.put("SMAQUESTIONFILEID",map.get("SMAQUESTIONFILEID"));
	        searchMap3.put("FILEPATH",map.get("FILEPATH"));
	        searchMap3.put("FILENAME",map.get("FILENAME"));
        }else{
        	searchMap3.put("QUESTIONNUMBER","");
	        searchMap3.put("ITEMID","");
	        searchMap3.put("SMAQUESTIONFILEID","");
	        searchMap3.put("FILEPATH","");
	        searchMap3.put("FILENAME","");
        }


       
        model.addAttribute("searchMap", searchMap);
        model.addAttribute("searchMap2", searchMap2);
        model.addAttribute("searchMap3", searchMap3);

        return "mocktest/mouigosa/D_mouigosaQuestionUpd";
    }
 
     // 동형모의고사 문제 등록 등록
    @RequestMapping(value="/D_mouigosaQuestionModify.do")
    @ResponseBody
    public String D_mouigosaQuestionModify(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        
        String DAP = CommonUtil.isNull(request.getParameter("DAP"), "");
        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String QUESTIONNUMBER = CommonUtil.isNull(request.getParameter("QUESTIONNUMBER"), "");
        String imgFile3NM = CommonUtil.isNull(request.getParameter("imgFile3NM"), "");

        
        params.put("ANSWERNUMBER", DAP);
        params.put("ITEMID", ITEMID);
        params.put("USER_ID",loginInfo.get("USER_ID"));
        params.put("QUESTIONNUMBER",QUESTIONNUMBER);
        
        String rootPath = fsResource.getPath();
        String subPath = "board/";
		String QUESTIONFID = "";
		String ANSWERFID = "";
		String SMAQUESTION = "";
		

        MultipartFile uploadFile1 = multiRequest.getFile("imgFile1");
		

        if(uploadFile1 != null && uploadFile1.isEmpty() == false) {
        
				QUESTIONFID = CommonUtil.isNull(request.getParameter("QUESTIONFID"), "");
        		HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile1, rootPath, subPath);
            	params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            	params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            	params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            	params.put("REAL_FILE_NAME", uploadFile1.getOriginalFilename().toString() );
            	params.put("QUESTIONFID", QUESTIONFID);
            	params.put("ATTACHFILEID", QUESTIONFID);

            	mouigosaService.D_ATTACHFILE_Update(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("FILE_SIZE", "");
            params.put("ATTACHFILEID", QUESTIONFID);
            params.put("QUESTIONFID", QUESTIONFID);
        }
        
       
        MultipartFile uploadFile2 = multiRequest.getFile("imgFile2");
        if(uploadFile2 != null && uploadFile2.isEmpty() == false) {
        	
        	ANSWERFID = CommonUtil.isNull(request.getParameter("ANSWERFID"), "");
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile2, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            params.put("REAL_FILE_NAME", uploadFile2.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
            
            mouigosaService.D_ATTACHFILE_Update(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("FILE_SIZE", "");
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
        }
        
        MultipartFile uploadFile3 = multiRequest.getFile("imgFile3");
        if(uploadFile3 != null && uploadFile3.isEmpty() == false) {
        	
        	
        	if(imgFile3NM.equals("")){
        		SMAQUESTION = mouigosaService.getQA_fileno(params);           
        	}else{
        		SMAQUESTION = CommonUtil.isNull(request.getParameter("SMAQUESTION"), "");
        	}
        	     	
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile3, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            params.put("REAL_FILE_NAME", uploadFile3.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", SMAQUESTION);
            params.put("SMAQUESTION", SMAQUESTION);
			
			if(imgFile3NM.equals("")){
        		mouigosaService.D_ATTACHFILE_Insert(params);
        		mouigosaService.D_LecPool_Img3_Upd(params);
        	}else{
        		mouigosaService.D_ATTACHFILE_Update(params);
        	}
            
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("FILE_SIZE", "");
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", SMAQUESTION);
            params.put("SMAQUESTION", SMAQUESTION);
        }
		
		mouigosaService.D_LecPool_Upd2(params);
		
        
        return "Y";
    }

    // 동형 모의고사 등록 화면
    @RequestMapping(value="/D_createRegistrationMouigosa.do")
    public String D_createRegistrationMouigosa(ModelMap model, HttpServletRequest request) {

        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        String currentPage = CommonUtil.isNull(request.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(request.getParameter("pageRow"), "");

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
        searchMap.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        searchMap.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        searchMap.put("S_CLASSCODE", S_CLASSCODE);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        //'직급' 리스트
        List<?> registration_list = mouigosaService.getRegistrationList(searchMap);
        if(log.isDebugEnabled()){
            //log.debug("***** registration_list : "+ registration_list) ;
        }

        model.addAttribute("sts", "I");
        model.addAttribute("registration_list", registration_list);

        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/D_mouigosaRegistrationIns";
    }
    
    // 동형모의고사 등록 수정 불러오기 상세
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/D_updateRegistrationMouigosa.do")
    public String D_updateRegistrationMouigosa(ModelMap model, HttpServletRequest request) {

        // 검색조건

        String currentPage = CommonUtil.isNull(request.getParameter("currentPage"), "");
        String pageRow = CommonUtil.isNull(request.getParameter("pageRow"), "");

        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Object> searchMap2 = new HashMap<String, Object>();
        Map<String, Object> searchMap3 = new HashMap<String, Object>();
        
        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("ITEMID", ITEMID);

        //상세 조회
        List<?> list = mouigosaService.D_getUpdateRegistrationDetail(searchMap);
        List<?> poollist = mouigosaService.D_getPoolList(searchMap);

		List<?> uploadFile1 = mouigosaService.getuploadFile1(searchMap);
		List<?> uploadFile2 = mouigosaService.getuploadFile2(searchMap);
		
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>) list.get(0);
        
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        
        if(uploadFile1.size() > 0){
        	
        	map2 = (HashMap<String, Object>) uploadFile1.get(0);
        	
       		searchMap2.put("ITEMID",map2.get("ITEMID"));
        	searchMap2.put("QUESTIONFID",map2.get("QUESTIONFID"));
        	searchMap2.put("ATTACHFILEID",map2.get("ATTACHFILEID"));
        	searchMap2.put("FILENAME",map2.get("FILENAME"));
        	searchMap2.put("FILEPATH",map2.get("FILEPATH"));
        	searchMap2.put("FILESIZE",map2.get("FILESIZE"));
        	searchMap2.put("REGDATE",map2.get("REGDATE"));
        }else{
       		searchMap2.put("ITEMID","");
        	searchMap2.put("QUESTIONFID","");
        	searchMap2.put("ATTACHFILEID","");
        	searchMap2.put("FILENAME","");
        	searchMap2.put("FILEPATH","");
        	searchMap2.put("FILESIZE","");
        	searchMap2.put("REGDATE","");        
        }

        if(uploadFile2.size() > 0){
        	
        	map3 = (HashMap<String, Object>) uploadFile2.get(0);
        	
        	searchMap3.put("ITEMID",map3.get("ITEMID"));
        	searchMap3.put("ANSWERFID",map3.get("ANSWERFID"));
        	searchMap3.put("ATTACHFILEID",map3.get("ATTACHFILEID"));
        	searchMap3.put("FILENAME",map3.get("FILENAME"));
        	searchMap3.put("FILEPATH",map3.get("FILEPATH"));
        	searchMap3.put("FILESIZE",map3.get("FILESIZE"));
        	searchMap3.put("REGDATE",map3.get("REGDATE"));
        }else{
        	searchMap3.put("ITEMID","");
        	searchMap3.put("ANSWERFID","");
        	searchMap3.put("ATTACHFILEID","");
        	searchMap3.put("FILENAME","");
        	searchMap3.put("FILEPATH","");
        	searchMap3.put("FILESIZE","");
        	searchMap3.put("REGDATE","");    
        }
                
        searchMap.put("ITEMID",map.get("ITEMID"));
        searchMap.put("ITEMNM",map.get("ITEMNM"));
        searchMap.put("LECCODE",map.get("LECCODE"));
        searchMap.put("EXAMROUND",map.get("EXAMROUND"));
        searchMap.put("PROFCODE",map.get("PROFCODE"));    
        searchMap.put("ENTRYNUM",map.get("ENTRYNUM"));
        searchMap.put("QUESTIONNUM",map.get("QUESTIONNUM"));
        searchMap.put("OPENSTATE",map.get("OPENSTATE"));
        searchMap.put("UPD_ID",map.get("UPD_ID"));
        searchMap.put("UPD_DT",map.get("UPD_DT"));
        searchMap.put("QUESTIONFID",map.get("QUESTIONFID"));
        searchMap.put("ANSWERFID",map.get("ANSWERFID"));
        searchMap.put("EXAMTIME",map.get("EXAMTIME"));
        
        model.addAttribute("poollist",poollist);
        model.addAttribute("searchMap2",searchMap2);
        model.addAttribute("searchMap3",searchMap3);
        model.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/D_mouigosaRegistrationUpd";
    }

    // 동형모의고사 삭제
    @RequestMapping(value="/D_deleteRegistrationMouigosa.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String D_deleteRegistrationMouigosa(ModelMap model, HttpServletRequest request) {
        // 검색조건
        //String[] deleteIds = request.getParameterValues("deleteIds");
        String deleteIds = request.getParameter("deleteIds");
		String[] DeleteId = deleteIds.split(",");
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		for(int i=0; i<DeleteId.length; i++){
			
			searchMap.put("ITEMID", CommonUtil.isNull(DeleteId[i],"0"));
			
			mouigosaService.deleteTitem(searchMap);
			mouigosaService.deleteTitemPool(searchMap);
	    }

	        searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
	        searchMap.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
	        searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
	        searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
	        model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
	        model.addAttribute("MENU_ID", searchMap.get("MENU_ID"));
	        model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
	        model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));

	        return "redirect:/mouigosa/reg/D_mouigosaRegistrationList.do";
    }

    // 동형모의고사 수정
    @RequestMapping(value="/D_mouigosaRegistrationUpdate.do")
    @ResponseBody
    public String D_mouigosaRegistrationUpdate(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        String ITEMID = CommonUtil.isNull(request.getParameter("ITEMID"), "");
        String MOCKNAME = CommonUtil.isNull(request.getParameter("MOCKNAME"), "");
        String MOCKLECCODE = CommonUtil.isNull(request.getParameter("MOCKLECCODE"), "");
        String MOCKPRFCODE = CommonUtil.isNull(request.getParameter("MOCKPRFCODE"), "");
        String QUESTIONNUM = CommonUtil.isNull(request.getParameter("QUESTIONNUM"), "");
        String ORI_QUESTIONNUM = CommonUtil.isNull(request.getParameter("ORI_QUESTIONNUM"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");
        String EXAMTIME = CommonUtil.isNull(request.getParameter("EXAMTIME"), "");        
        String MOCKISUSE = CommonUtil.isNull(request.getParameter("MOCKISUSE"), "0");
        int QUESTIONNUM_GAP = 0;    
        	
        params.put("ITEMID", ITEMID);
        params.put("MOCKNAME", MOCKNAME);
        params.put("MOCKLECCODE", MOCKLECCODE);
        params.put("MOCKPRFCODE", MOCKPRFCODE);
        params.put("QUESTIONNUM", QUESTIONNUM);
        params.put("ENTRYNUM", ENTRYNUM);
        params.put("EXAMTIME", EXAMTIME);
        params.put("MOCKISUSE", MOCKISUSE);
        params.put("USER_ID",loginInfo.get("USER_ID"));
        
        if(Integer.parseInt(QUESTIONNUM) != Integer.parseInt(ORI_QUESTIONNUM)){
        	
	    	if(Integer.parseInt(QUESTIONNUM) > Integer.parseInt(ORI_QUESTIONNUM)){
	    		QUESTIONNUM_GAP = Integer.parseInt(QUESTIONNUM) - Integer.parseInt(ORI_QUESTIONNUM); // 플러스
	    		for(int i=1; i<=QUESTIONNUM_GAP; i++){
	    			mouigosaService.QUESTIONNUM_INS(params);
	    		}	    			    		
	    	}else{
	    		QUESTIONNUM_GAP = Integer.parseInt(ORI_QUESTIONNUM) - Integer.parseInt(QUESTIONNUM); // 마이너스
	    		for(int i=1; i<=QUESTIONNUM_GAP; i++){
	    			mouigosaService.QUESTIONNUM_DEL(params);
	    		}
	    		
	    	}
        }

		String rootPath = fsResource.getPath();
        String subPath = "board/";
		String QUESTIONFID = "";
		String ANSWERFID = "";
		

        MultipartFile uploadFile1 = multiRequest.getFile("uploadFile1");
		

        if(uploadFile1 != null && uploadFile1.isEmpty() == false) {
        	
        	QUESTIONFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile1, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            params.put("REAL_FILE_NAME", uploadFile1.getOriginalFilename().toString() );
            params.put("QUESTIONFID", QUESTIONFID);
            params.put("ATTACHFILEID", QUESTIONFID);
            params.put("FLAG", "0");
            mouigosaService.D_ATTACHFILE_Insert(params);
            mouigosaService.D_ATTACHFILE_update(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("FILE_SIZE", "");
            params.put("ATTACHFILEID", QUESTIONFID);
            params.put("QUESTIONFID", QUESTIONFID);
        }
        
       
        MultipartFile uploadFile2 = multiRequest.getFile("uploadFile2");
        if(uploadFile2 != null && uploadFile2.isEmpty() == false) {
        	
        	ANSWERFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile2, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile2.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
            params.put("FLAG", "1");
            mouigosaService.D_ATTACHFILE_Insert(params);
            mouigosaService.D_ATTACHFILE_update(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
        }
        
		
		mouigosaService.D_Mock_Update(params);

        return "Y";
    }
    
    @RequestMapping(value="/D_AttachDeleteFile.do")
    @ResponseBody
    public String Schedule_delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("ITEMID", CommonUtil.isNull(request.getParameter("ITEMID"),""));
        params.put("FILENO", CommonUtil.isNull(request.getParameter("FILENO"),""));
        params.put("FLAG", CommonUtil.isNull(request.getParameter("FLAG"),""));
		
		mouigosaService.D_AttachDeleteFile1(params);
        mouigosaService.D_AttachDeleteFile2(params);
        model.addAttribute("params", params);

        return "Y";
    }
    
    // 동형모의고사 등록 등록
    @RequestMapping(value="/D_mouigosaRegistrationInsert.do")
    @ResponseBody
    public String D_mouigosaRegistrationInsert(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        
        String MOCKNAME = CommonUtil.isNull(request.getParameter("MOCKNAME"), "");
        String MOCKLECCODE = CommonUtil.isNull(request.getParameter("MOCKLECCODE"), "");
        String MOCKPRFCODE = CommonUtil.isNull(request.getParameter("MOCKPRFCODE"), "");
        String QUESTIONNUM = CommonUtil.isNull(request.getParameter("QUESTIONNUM"), "");
        String ENTRYNUM = CommonUtil.isNull(request.getParameter("ENTRYNUM"), "");
        String EXAMTIME = CommonUtil.isNull(request.getParameter("EXAMTIME"), "");        
        String MOCKISUSE = CommonUtil.isNull(request.getParameter("MOCKISUSE"), "0");
        
        params.put("MOCKNAME", MOCKNAME);
        params.put("MOCKLECCODE", MOCKLECCODE);
        params.put("MOCKPRFCODE", MOCKPRFCODE);
        params.put("QUESTIONNUM", QUESTIONNUM);
        params.put("ENTRYNUM", ENTRYNUM);
        params.put("EXAMTIME", EXAMTIME);
        params.put("MOCKISUSE", MOCKISUSE);
        params.put("USER_ID",loginInfo.get("USER_ID"));

        //counselService.Sch_Delete(params);
        //model.addAttribute("params", params);
        
        String rootPath = fsResource.getPath();
        String subPath = "board/";
		String QUESTIONFID = "";
		String ANSWERFID = "";
		

        MultipartFile uploadFile1 = multiRequest.getFile("uploadFile1");
		

        if(uploadFile1 != null && uploadFile1.isEmpty() == false) {
        	
        	QUESTIONFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile1, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("FILE_SIZE", String.valueOf(fileMap.get("fileSize")));
            params.put("REAL_FILE_NAME", uploadFile1.getOriginalFilename().toString() );
            params.put("QUESTIONFID", QUESTIONFID);
            params.put("ATTACHFILEID", QUESTIONFID);
            mouigosaService.D_ATTACHFILE_Insert(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("FILE_SIZE", "");
            params.put("ATTACHFILEID", QUESTIONFID);
            params.put("QUESTIONFID", QUESTIONFID);
        }
        
       
        MultipartFile uploadFile2 = multiRequest.getFile("uploadFile2");
        if(uploadFile2 != null && uploadFile2.isEmpty() == false) {
        	
        	ANSWERFID = mouigosaService.getQA_fileno(params);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile2, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile2.getOriginalFilename().toString() );
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
            mouigosaService.D_ATTACHFILE_Insert(params);
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
            params.put("ATTACHFILEID", ANSWERFID);
            params.put("ANSWERFID", ANSWERFID);
        }

		
		mouigosaService.D_Mock_Insert(params);
		
		int num = Integer.parseInt(String.valueOf(params.get("QUESTIONNUM")));
		
		for(int x=1; x <=num; x++){
			params.put("QUESTIONNUMBER", String.valueOf(x));
			
			mouigosaService.D_Mock_Pool_Insert(params);
		}
        return "Y";
    }

    // 동형모의고사 시험시작 팝업
    @RequestMapping(value="/D_printMouigosa.pop")
    //@Transactional(readOnly=false, rollbackFor=Exception.class)
    public String printMouigosa(ModelMap output, HttpServletRequest req) {

        String ITEMID = CommonUtil.isNull(req.getParameter("ITEMID"), "");


        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("ITEMID", ITEMID);


        //문제 프린트 팝업 리스트
        List<?> question_list = mouigosaService.getD_QuestionList(searchMap);

        output.addAttribute("question_list", question_list);
        output.addAttribute("searchMap", searchMap);

        return "mocktest/mouigosa/D_printPop";
    }

	/**
	 * @Method Name : setParam
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파라미터 SETTING
	 * @param params
	 * @param request
	 * @return HashMap
	 * @throws Exception
	 */
	public void setParam(HashMap<String, Object> params, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("SEARCH_TYPE", CommonUtil.isNull(request.getParameter("SEARCH_TYPE"), ""));
		params.put("SEARCH_TXT", CommonUtil.isNull(request.getParameter("SEARCH_TXT"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
	}

}