package web.boardViewManagement;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import egovframework.rte.fdl.property.EgovPropertyService;
import web.boardViewManagement.service.BoardViewManagementService;
import web.productOrder.service.ProductOrderService;
import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.paging.Paging;

/**
 * @FileName   : BoardManagementController.java
 * @Project    :
 * @Date       : 2015. 05. 28.
 * @Author     :
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/boardViewManagement")
@Controller
public class BoardViewManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private BoardViewManagementService boardViewManagementService;

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * @Method Name  : commonCodeList
     * @Date         : 2015. 05. 28.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	 리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/boardViewMngList.do")
    public String boardMngList(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, Object> searchMap = new HashMap<String, Object>();
        setParam(searchMap, request, model);

        // 검색조건
        int currentPage = Integer.parseInt(String.valueOf(searchMap.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(searchMap.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        String SEARCHONOFFDIV = CommonUtil.isNull(request.getParameter("SEARCHONOFFDIV"), "");
        searchMap.put("SEARCHONOFFDIV", SEARCHONOFFDIV);

        String searchMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"));
        if(searchMenuType.indexOf("OM") > -1) {
            searchMap.put("ONOFF_DIV", "O");
        } else {
            searchMap.put("ONOFF_DIV", "F");
        }

        // 리스트
        List<HashMap<String, Object>> list = boardViewManagementService.getBoardMngList(searchMap);

        // 총 건수 - 공통코드
        int listCount = boardViewManagementService.getBoardMngListCount(searchMap);

        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        model.addAttribute("searchMap", searchMap);

        return "boardViewManagement/boardViewMngList";
    }


	/**
	 * @Method Name  : commonCodeInsert
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 코드관리 등록 화면
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngInsert.do")
	public String boardMngInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));


		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
		String userName = sessionMap.get("USER_NM");
		String userId = sessionMap.get("USER_ID");
		params.put("USERNAME", userName);
		params.put("USERID", userId);
		model.addAttribute("params",params);

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		List<HashMap<String, String>> boardTypeList = boardViewManagementService.getBoardTypeList();
		model.addAttribute("boardTypeList", boardTypeList);

 		return "/boardViewManagement/boardViewMngInsert";
	}


	/**
	 * @Method Name  : commonCodeInsertProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 코드관리 등록 프로세스
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngInsertProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String boardMngInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();
		params.put("SEARCHTYPE", "");
		params.put("SEARCHKEYWORD" , "");

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));

		params.put("BOARD_NAME", CommonUtil.isNull(request.getParameter("BOARD_NAME"), ""));
		params.put("BOARD_TYPE", CommonUtil.isNull(request.getParameter("BOARD_TYPE"), ""));
		params.put("ATTACH_FILE_YN", CommonUtil.isNull(request.getParameter("ATTACH_FILE_YN"), ""));
		params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
		params.put("REPLY_YN", CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));
		params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
		String userName = sessionMap.get("USER_NM");
		String userId = sessionMap.get("USER_ID");
		params.put("USERNAME", userName);
		params.put("REG_ID", userId);
		params.put("UPD_ID", userId);

		//코드 등록
		boardViewManagementService.boardMngInsertProcess(params);
		model.addAttribute("params",params);
		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

 		return "forward:/boardViewManagement/boardViewMngList.do";
	}

	/**
	 * @Method Name  : commonCodeDetail
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 코드관리 상세
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngDetail.do")
	public String boardMngDetail(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));

		params.put("DETAIL_BOARD_SEQ",request.getParameter("DETAIL_BOARD_SEQ")); //공통코드


		//코드 select
		HashMap<String, Object> detail  =	boardViewManagementService.boardMngDetail(params);

		model.addAttribute("params",params);
		model.addAttribute("detail",detail);
		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
		return "/boardViewManagement/boardViewMngDetail";
	}


	/**
	 * @Method Name  : commonCodeUpdateProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 공통코드 수정 process
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngUpdateProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String boardMngUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));

		params.put("DETAIL_BOARD_SEQ",request.getParameter("DETAIL_BOARD_SEQ"));
		params.put("BOARD_NAME", CommonUtil.isNull(request.getParameter("BOARD_NAME"), ""));
		params.put("BOARD_TYPE", CommonUtil.isNull(request.getParameter("BOARD_TYPE"), ""));
		params.put("ATTACH_FILE_YN", CommonUtil.isNull(request.getParameter("ATTACH_FILE_YN"), ""));
		params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
		params.put("REPLY_YN", CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));
		params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));



		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
		String userName = sessionMap.get("USER_NM");
		String userId = sessionMap.get("USER_ID");
		params.put("USERNAME", userName);
		params.put("UPD_ID", userId);
		//코드 수정 등록
		boardViewManagementService.boardMngUpdateProcess(params);

		model.addAttribute("params",params);

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
 		return "forward:/boardViewManagement/boardViewMngList.do";
	}


	/**
	 * @Method Name  : commonCodeDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 코드관리 상세페이지  삭제
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String boardMngDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("SEARCHTYPE", "");
		params.put("SEARCHKEYWORD" , "");

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));

		params.put("DETAIL_BOARD_SEQ",request.getParameter("DETAIL_BOARD_SEQ"));
		// 삭제
		boardViewManagementService.boardMngDelete(params);

		model.addAttribute("params",params);
		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
 		return "redirect:/boardViewManagement/boardViewMngList.do";
	}


	/**
	 * @Method Name  : commonCodeCheckDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 코드관리 리스트 체크박스  일괄 삭제
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardViewMngCheckDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String boardMngCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("SEARCHTYPE", "");
		params.put("SEARCHKEYWORD" , "");

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), "15"));

		params.put("deleteIds",request.getParameter("deleteIds"));
		// 일괄 삭제
		boardViewManagementService.boardMngCheckDelete(params);

		model.addAttribute("params",params);
		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
		return "redirect:/boardViewManagement/boardViewMngList.do";
	}


	/**
	 * @Method Name  : boardCSMngList
	 * @Date         : 2015. 07. 27.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	상담리스트
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/boardCSMngList.do")
	public String boardCSMngList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 검색조건
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), "20"));

		String sdate = CommonUtil.isNull(request.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(request.getParameter("edate"), "");

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		String ch1 = null;
		Date d1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		ch1 = sdf1.format(d1);

		if(sdate.equals("")){
			sdate = ch1;
		}

		if(edate.equals("")){
			edate = ch1;
		}

		params.put("sdate", sdate);
		params.put("edate", edate);

		String searchMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"));
		if(searchMenuType.indexOf("OM") > -1) {
			params.put("ONOFF_DIV", "O");
		} else {
			params.put("ONOFF_DIV", "F");
		}
		params.put("CS_DIV", CommonUtil.isNull(request.getParameter("CS_DIV"), ""));		

		List<HashMap<String, String>> list = boardViewManagementService.boardCSList(params);
		int listCount = boardViewManagementService.boardCSListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		//Cs상담내역 등록 셀렉트박스
		params.put("CODE_VAL", "CSBOARD_GUBUN");
		model.addAttribute("csccode_list", productOrderService.getCsccode(params));
		
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "boardViewManagement/boardCSList";
	}

	/**
	 * @Method Name  : boardCSInsert
	 * @Date         : 2016. 09. 06.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	비회원 상담 내역 등록
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/boardCSInsert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String boardCSInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		params.put("STS", CommonUtil.isNull(request.getParameter("STS"), ""));
		params.put("STS_TM", CommonUtil.isNull(request.getParameter("STS_TM"), ""));
		params.put("ccode_top", CommonUtil.isNull(request.getParameter("ccode_top"), ""));
		params.put("ccode", CommonUtil.isNull(request.getParameter("ccode"), ""));
		params.put("actionflag", CommonUtil.isNull(request.getParameter("actionflag"), ""));
		params.put("content", CommonUtil.isNull(request.getParameter("cscontent"), ""));
		params.put("username", CommonUtil.isNull(request.getParameter("username"), ""));
		
		
		//Cs상담내역 등록
		productOrderService.insertBoardCs(params);
		
		return "forward:/boardViewManagement/boardCSMngList.do";
	}

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/boardCSMngListexcel.do")
	public View excelDownload(ModelMap output, HttpServletRequest request) throws Exception {

		String sdate = CommonUtil.isNull(request.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(request.getParameter("edate"), "");

		String ch1 = null;
		Date d1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		ch1 = sdf1.format(d1);

		if(sdate.equals("")){
			sdate = ch1;
		}

		if(edate.equals("")){
			edate = ch1;
		}

		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("sdate", sdate);
		params.put("edate", edate);
		params.put("startNo", "0");
		params.put("endNo", "1000");
		params.put("CS_DIV", CommonUtil.isNull(request.getParameter("CS_DIV"), ""));		

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = boardViewManagementService.boardCSList(params);

		Date date = new Date();
		    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "CS전화응대통계_리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("접수시간");
		headerList.add("상담자");
		headerList.add("고객명");
		headerList.add("구분");
		headerList.add("분류");
		headerList.add("조치");
		headerList.add("질의내용");

		int dCount = 0;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			dCount++;
			int i = 0;
			newMap.put(i++, String.valueOf(dCount));
			newMap.put(i++, map.get("REG_DT").toString());
			if(map.get("ADMIN_NM") == null){
				newMap.put(i++, "");		
			}else{
				newMap.put(i++, map.get("ADMIN_NM").toString());		
			}				
			newMap.put(i++, map.get("USER_NM").toString());
			newMap.put(i++, map.get("B_TYPE").toString());
			newMap.put(i++, map.get("BOARD_TYPE").toString());
			newMap.put(i++, map.get("B_ACT").toString());
			newMap.put(i++, map.get("SUBJECT").toString());
			newList.add(newMap);  	// 한줄 추가
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
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
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("USER_ID",loginInfo.get("USER_ID"));
		params.put("REG_NM",loginInfo.get("USER_NM"));

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD", URLDecoder.decode(CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""),"UTF-8"));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHISUSE", CommonUtil.isNull(request.getParameter("SEARCHISUSE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

    }
}
