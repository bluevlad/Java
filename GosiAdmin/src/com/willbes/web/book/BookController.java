package com.willbes.web.book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.book.service.BookService;
import com.willbes.web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/book")
@Controller
public class BookController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Inject
    private FileSystemResource fsResource;
    @Resource(name="fileUtil")
    private FileUtil fileUtil;
    @Autowired
    private BookService bookservice;
    @Autowired
    private TeacherService teacherservice;

    /**
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        List<HashMap<String, String>> list = bookservice.bookList(params);
        int listCount = bookservice.bookListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("kindlist", kindlist);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/book/list";
    }

    /**
     * @Method Name : write
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 등록폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/write.do")
    public String write(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        params.put("SEARCHCODEISUSE", "Y");
        List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
        List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

        model.addAttribute("kindlist", kindlist);
        model.addAttribute("formlist", formlist);
        model.addAttribute("subjectteacherlist", subjectteacherlist);
        model.addAttribute("params", params);
        return "/book/write";
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/save.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String save(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        // book_upload
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        lecFileProcess(params, multipartRequest);

        String[] SUBJECT_SJT_CD_ARR = request.getParameterValues("SUBJECT_SJT_CD");
        String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
        String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");

        params.put("SEQ", String.valueOf(bookservice.getCaBookSeq(params)));
        for(int j=0; j<CATEGORY_CD_ARR.length; j++){
            params.put("CATEGORY_CD", CATEGORY_CD_ARR[j]);

            for(int k=0; k<LEARNING_CD_ARR.length; k++){
                params.put("LEARNING_CD", LEARNING_CD_ARR[k]);
                String SUBJECT_SJT_CD = "";

                for(int i=0; i<SUBJECT_SJT_CD_ARR.length; i++){
                    if(!"".equals(SUBJECT_SJT_CD)){
                        SUBJECT_SJT_CD += ",";
                    }
                    SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
                }
                params.put("SUBJECT_SJT_CD", SUBJECT_SJT_CD);
                params.put("RSC_ID", bookservice.getCaBookRscId(params));
                bookservice.bookInsert(params);
            }
        }
        return "redirect:/book/list.do";
    }

    /**
     * @Method Name : modify
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 수정폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/modify.do")
    public String modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> view = bookservice.bookView(params);
        List<HashMap<String, String>> viewlist = bookservice.bookViewList(params);
        HashMap<String, String> vparams = new  HashMap<String, String>();
        String sdelyn = "Y";		// 관련글 삭제 가능여부
        String rdelyn = "Y";		// 현재 선택글 삭제가능여부

        int CheckCnt = bookservice.bookUseCheck(params);
        if(CheckCnt > 0)
            rdelyn = "N";

        for (int j = 0; j < viewlist.size(); j++) {
            vparams.put("RSC_ID", viewlist.get(j).get("RSC_ID"));
            CheckCnt = bookservice.bookUseCheck(vparams);
            if(CheckCnt > 0)
                sdelyn = "N";
        }

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        params.put("SEARCHCODEISUSE", "Y");
        List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
        List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

        model.addAttribute("kindlist", kindlist);
        model.addAttribute("formlist", formlist);
        model.addAttribute("subjectteacherlist", subjectteacherlist);
        model.addAttribute("view", view);
        model.addAttribute("viewlist", viewlist);
        model.addAttribute("rdelyn", rdelyn);
        model.addAttribute("sdelyn", sdelyn);
        model.addAttribute("params", params);
        return "/book/modify";
    }

    /**
     * @Method Name : update
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String update(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        lecFileProcess(params, multipartRequest);
        /*
		String rootPath = fsResource.getPath();
		if("Y".equals(params.get("ATTACH_FILE_DEL")) || !"".equals(params.get("ATTACH_FILE")))
			fileUtil.deleteFile(rootPath + params.get("ATTACH_FILE_DELNM"));
		if("Y".equals(params.get("ATTACH_IMG_S_DEL")) || !"".equals(params.get("ATTACH_IMG_S")))
			fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_S_DELNM"));
		if("Y".equals(params.get("ATTACH_IMG_M_DEL")) || !"".equals(params.get("ATTACH_IMG_M")))
			fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_M_DELNM"));
		if("Y".equals(params.get("ATTACH_IMG_L_DEL")) || !"".equals(params.get("ATTACH_IMG_L")))
			fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_L_DELNM"));
		if("Y".equals(params.get("ATTACH_DETAIL_INFO_DEL")) || !"".equals(params.get("ATTACH_DETAIL_INFO")))
			fileUtil.deleteFile(rootPath + params.get("ATTACH_DETAIL_INFO_DELNM"));
         */
        String[] SUBJECT_SJT_CD_ARR = request.getParameterValues("SUBJECT_SJT_CD");
        params.put("UPDATE_FLAG", CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
        String SUBJECT_SJT_CD = "";
        for(int i=0; i<SUBJECT_SJT_CD_ARR.length; i++){
            if(!"".equals(SUBJECT_SJT_CD)){
                SUBJECT_SJT_CD += ",";
            }
            SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
        }
        params.put("SUBJECT_SJT_CD", SUBJECT_SJT_CD);
        bookservice.bookUpdate(params);
        return "forward:/book/modify.do";
    }

    /**
     * @Method Name : delete
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/delete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        String rootPath = fsResource.getPath();
        /*
		fileUtil.deleteFile(rootPath + params.get("ATTACH_FILE_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_S_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_M_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_L_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_DETAIL_INFO_DELNM"));
         */
        bookservice.bookDelete(params);
        return "forward:/book/list.do";
    }


    /**
     * @Method Name : delete
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 관련도서 전체 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/allDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String allDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        /*
		String rootPath = fsResource.getPath();
		fileUtil.deleteFile(rootPath + params.get("ATTACH_FILE_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_S_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_M_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_IMG_L_DELNM"));
		fileUtil.deleteFile(rootPath + params.get("ATTACH_DETAIL_INFO_DELNM"));
         */
        params.put("GUBN","all");
        bookservice.bookDelete(params);
        return "forward:/book/list.do";
    }

    /**
     * @Method Name : sellList
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 판매 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/sellList.pop")
    public String sellList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookservice.bookSellList(params);
        int listCount = bookservice.bookSellListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/book/sellList";
    }

    /**
     * @Method Name : sellListExcel
     * @작성일 : 2013. 10.
     * @Method 설명 : 교재 판매 목록 엑셀
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/sellListExcel.do")
    public View sellListExcel(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookservice.bookSellListExcel(params);
        String excelName = "교재주문정보";
        List<String> headerList = new ArrayList<String>();
        headerList.add("주문번호");
        headerList.add("주문자명");
        headerList.add("주문자아이디");
        headerList.add("결제수단");
        headerList.add("입금일");
        headerList.add("상품명");
        headerList.add("구입금액");
        headerList.add("상태");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            newMap.put(i++, map.get("ORDERNO").toString());
            newMap.put(i++, map.get("ORDERS_USERNAME").toString());
            newMap.put(i++, map.get("ORDERS_USERID").toString());
            newMap.put(i++, map.get("PAY_NM").toString());
            newMap.put(i++, map.get("ORDERS_REGDATE").toString());
            newMap.put(i++, map.get("BOOK_NM").toString());
            newMap.put(i++, map.get("PRICE").toString());
            newMap.put(i++, map.get("STATUS_NM").toString());
            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
    }

    /**
     * @Method Name : excelDownload
     * @작성일 : 2015. 06.
     * @Method 설명 : 회원 명단
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/excel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        List<HashMap<String, String>> list = bookservice.bookList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_교재목록";

        List<String> headerList = new ArrayList<String>();
        headerList.add("관리번호");
        headerList.add("분류");
        headerList.add("카테고리");
        headerList.add("상품명");
        headerList.add("관련도서");
        headerList.add("저자");
        headerList.add("출판사");
        headerList.add("재고");
        headerList.add("상태");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("RSC_ID") && !"".equals(map.get("RSC_ID"))) {
                newMap.put(i++, map.get("RSC_ID").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("CATEGORY_NM") && !"".equals(map.get("CATEGORY_NM"))) {
                newMap.put(i++, map.get("CATEGORY_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("LEARNING_NM") && !"".equals(map.get("LEARNING_NM"))) {
                newMap.put(i++, map.get("LEARNING_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("BOOK_NM") && !"".equals(map.get("BOOK_NM"))) {
                newMap.put(i++, map.get("BOOK_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("RELCNT") && !"".equals(String.valueOf(map.get("RELCNT")))) {
                String cnt = String.valueOf(map.get("RELCNT"));
                if(Integer.parseInt(cnt) > 0) {
                    newMap.put(i++, map.get("RELCNT"));
                } else {
                    newMap.put(i++, "없음");
                }
            } else {
                newMap.put(i++, "없음");
            }
            if(null != map.get("BOOK_AUTHOR") && !"".equals(map.get("BOOK_AUTHOR"))) {
                newMap.put(i++, map.get("BOOK_AUTHOR").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("BOOK_PUBLISHERS") && !"".equals(map.get("BOOK_PUBLISHERS"))) {
                newMap.put(i++, map.get("BOOK_PUBLISHERS").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("BOOK_STOCK") && !"".equals(map.get("BOOK_STOCK"))) {
                newMap.put(i++, map.get("BOOK_STOCK"));
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("COVER_TYPE") && !"".equals(map.get("COVER_TYPE"))) {
                newMap.put(i++, map.get("COVER_TYPE").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("REG_DT") && !"".equals(map.get("REG_DT"))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
                newMap.put(i++, (sdf.format(map.get("REG_DT"))).substring(0, 10));
            } else {
                newMap.put(i++, "");
            }

            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
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
    public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("RSC_ID", CommonUtil.isNull(request.getParameter("RSC_ID"), ""));
        params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
        params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
        params.put("BOOK_NM", CommonUtil.isNull(request.getParameter("BOOK_NM"), ""));
        params.put("BOOK_INFO", CommonUtil.isNull(request.getParameter("BOOK_INFO"), ""));
        params.put("BOOK_MEMO", CommonUtil.isNull(request.getParameter("BOOK_MEMO"), ""));
        params.put("BOOK_KEYWORD", CommonUtil.isNull(request.getParameter("BOOK_KEYWORD"), ""));
        params.put("ISSUE_DATE", CommonUtil.isNull(request.getParameter("ISSUE_DATE"), ""));
        params.put("COVER_TYPE", CommonUtil.isNull(request.getParameter("COVER_TYPE"), ""));
        params.put("BOOK_CONTENTS", CommonUtil.isNull(request.getParameter("BOOK_CONTENTS"), ""));
        params.put("PRICE", CommonUtil.isNull(request.getParameter("PRICE"), ""));
        params.put("DISCOUNT", CommonUtil.isNull(request.getParameter("DISCOUNT"), ""));
        params.put("DISCOUNT_PRICE", CommonUtil.isNull(request.getParameter("DISCOUNT_PRICE"), ""));
        params.put("POINT", CommonUtil.isNull(request.getParameter("POINT"), ""));
        params.put("BOOK_PUBLISHERS", CommonUtil.isNull(request.getParameter("BOOK_PUBLISHERS"), ""));
        params.put("BOOK_AUTHOR", CommonUtil.isNull(request.getParameter("BOOK_AUTHOR"), ""));
        params.put("BOOK_SUPPLEMENTDATA", CommonUtil.isNull(request.getParameter("BOOK_SUPPLEMENTDATA"), ""));
        params.put("BOOK_PRINTINGDATE", CommonUtil.isNull(request.getParameter("BOOK_PRINTINGDATE"), ""));
        params.put("BOOK_MAIN", CommonUtil.isNull(request.getParameter("BOOK_MAIN"), ""));
        params.put("BOOK_SUB", CommonUtil.isNull(request.getParameter("BOOK_SUB"), ""));
        params.put("BOOK_STUDENTBOOK", CommonUtil.isNull(request.getParameter("BOOK_STUDENTBOOK"), ""));
        params.put("BOOK_STOCK", CommonUtil.isNull(request.getParameter("BOOK_STOCK"), ""));
        params.put("FREE_POST", CommonUtil.isNull(request.getParameter("FREE_POST"), ""));
        params.put("BOOK_DATE", CommonUtil.isNull(request.getParameter("BOOK_DATE"), ""));
        params.put("NEW_BOOK", CommonUtil.isNull(request.getParameter("NEW_BOOK"), ""));
        params.put("MAIN_VIEW", CommonUtil.isNull(request.getParameter("MAIN_VIEW"), ""));
        params.put("USE_YN", CommonUtil.isNull(request.getParameter("USE_YN"), ""));

        params.put("BOOK_PAGE", CommonUtil.isNull(request.getParameter("BOOK_PAGE"), ""));
        params.put("BOOK_FORMAT", CommonUtil.isNull(request.getParameter("BOOK_FORMAT"), ""));
        
        params.put("ATTACH_FILE_DEL", CommonUtil.isNull(request.getParameter("ATTACH_FILE_DEL"), ""));
        params.put("ATTACH_IMG_L_DEL", CommonUtil.isNull(request.getParameter("ATTACH_IMG_L_DEL"), ""));
        params.put("ATTACH_IMG_M_DEL", CommonUtil.isNull(request.getParameter("ATTACH_IMG_M_DEL"), ""));
        params.put("ATTACH_IMG_S_DEL", CommonUtil.isNull(request.getParameter("ATTACH_IMG_S_DEL"), ""));
        params.put("ATTACH_DETAIL_INFO_DEL", CommonUtil.isNull(request.getParameter("ATTACH_DETAIL_INFO_DEL"), ""));
        params.put("ATTACH_FILE_DELNM", CommonUtil.isNull(request.getParameter("ATTACH_FILE_DELNM"), ""));
        params.put("ATTACH_IMG_L_DELNM", CommonUtil.isNull(request.getParameter("ATTACH_IMG_L_DELNM"), ""));
        params.put("ATTACH_IMG_M_DELNM", CommonUtil.isNull(request.getParameter("ATTACH_IMG_M_DELNM"), ""));
        params.put("ATTACH_IMG_S_DELNM", CommonUtil.isNull(request.getParameter("ATTACH_IMG_S_DELNM"), ""));
        params.put("ATTACH_DETAIL_INFO_DELNM", CommonUtil.isNull(request.getParameter("ATTACH_DETAIL_INFO_DELNM"), ""));
        params.put("ATTACH_FILE", "");
        params.put("ATTACH_IMG_L", "");
        params.put("ATTACH_IMG_M", "");
        params.put("ATTACH_IMG_S", "");
        params.put("ATTACH_DETAIL_INFO", "");

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

    /**
     * @Method Name : lecFileProcess
     * @작성일 : 2013. 10.
     * @Method 설명 : 파일처리 프로세스
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    public HashMap<String, String> lecFileProcess(HashMap<String, String> params, MultipartHttpServletRequest multipartRequest) throws Exception {
        MultipartFile ATTACH_FILE = multipartRequest.getFile("ATTACH_FILE");
        MultipartFile ATTACH_IMG_L = multipartRequest.getFile("ATTACH_IMG_L");
        MultipartFile ATTACH_IMG_M = multipartRequest.getFile("ATTACH_IMG_M");
        MultipartFile ATTACH_IMG_S = multipartRequest.getFile("ATTACH_IMG_S");
        MultipartFile ATTACH_DETAIL_INFO = multipartRequest.getFile("ATTACH_DETAIL_INFO");

        Calendar cal = Calendar.getInstance( );
        String rootPath = fsResource.getPath();
        String subPath = "book_upload/";

        if(ATTACH_FILE != null && ATTACH_FILE.isEmpty() == false) {
            HashMap<String, Object> fileMap1 = fileUtil.uploadFile(ATTACH_FILE, rootPath, subPath);
            params.put("ATTACH_FILE", fileMap1.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(ATTACH_IMG_L != null && ATTACH_IMG_L.isEmpty() == false) {
            HashMap<String, Object> fileMap2 = fileUtil.uploadFile(ATTACH_IMG_L, rootPath, subPath);
            params.put("ATTACH_IMG_L", fileMap2.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(ATTACH_IMG_M != null && ATTACH_IMG_M.isEmpty() == false) {
            HashMap<String, Object> fileMap3 = fileUtil.uploadFile(ATTACH_IMG_M, rootPath, subPath);
            params.put("ATTACH_IMG_M", fileMap3.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(ATTACH_IMG_S != null && ATTACH_IMG_S.isEmpty() == false) {
            HashMap<String, Object> fileMap4 = fileUtil.uploadFile(ATTACH_IMG_S, rootPath, subPath);
            params.put("ATTACH_IMG_S", fileMap4.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(ATTACH_DETAIL_INFO != null && ATTACH_DETAIL_INFO.isEmpty() == false) {
            HashMap<String, Object> fileMap5 = fileUtil.uploadFile(ATTACH_DETAIL_INFO, rootPath, subPath);
            params.put("ATTACH_DETAIL_INFO", fileMap5.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        return params;
    }
}
