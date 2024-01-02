package com.willbes.web.manage;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.MafUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.manage.service.lectureYearService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   	: lectureYearController.java
 * @Project    		: willbesWebAdmin
 * @Date       		: 2016. 05. 01. 
 * @Author     		: 
 * @변경이력    	:
 * @프로그램 설명 	: 년회원 패키지 매출
 */
@RequestMapping(value="/lectureYear")
@Controller
public class lectureYearController {
	
    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
	private lectureYearService  service;
	
	@RequestMapping(value="/onPayList.do")
	public String onCategorySales(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String SEARCHTYPE 			= CommonUtil.isNull(request.getParameter("SEARCHTYPE"),  	"C");
		
		
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("SEARCHTYPE", 				SEARCHTYPE);

		model.addAttribute("list", 				service.onPayList(params));
		model.addAttribute("SEARCHTYPE", 	SEARCHTYPE);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("params", params);
		
		return "/lectureYear/onPayList";
	}
	
	@RequestMapping(value="/onOrderList.do")
	public String onOrderList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);

		model.addAttribute("list", 				service.onOrderList(params));
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("params", params);
		
		return "/lectureYear/onOrderList";
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

        String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
        List<HashMap<String, String>> list = service.onOrderList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_프리패스_매출관리";

        List<String> headerList = new ArrayList<String>();
        headerList.add("과정명");
        headerList.add("수강료합계");
        headerList.add("환불액합계");
        headerList.add("수강인원");
        headerList.add("환불인원");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            newMap.put(i++, map.get("SUBJECT_TITLE").toString());
            newMap.put(i++, String.valueOf(map.get("PRICE")));
            newMap.put(i++, String.valueOf(map.get("REP_PRICE")));
            newMap.put(i++, String.valueOf(map.get("CNT")));
            newMap.put(i++, String.valueOf(map.get("REP_CNT")));
            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
	}
    
    @RequestMapping(value="/payUserList.pop")
	public String payList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		String LECCODE 	= CommonUtil.isNull(request.getParameter("LECCODE"),  	"");
		String STATUSCODE 	= CommonUtil.isNull(request.getParameter("STATUSCODE"),  	"");
		params.put("STATUSCODE", 			STATUSCODE);
		params.put("LECCODE", 			LECCODE);
		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */
		List<HashMap<String, String>> list = service.payUserListByLeccode(params);
		int listCount = service.payUserListByLeccodeCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		model.addAttribute("params", params);
		return "/lectureYear/onOrderUserList_pop";
	}
    
	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/payList_excel.do")
	public ExcelDownloadView payexcelDownload(ModelMap output, HttpServletRequest req) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, output);
		
		//엑셀 리스트
		String searchStartDate 	= CommonUtil.isNull(req.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(req.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		String LECCODE 	= CommonUtil.isNull(req.getParameter("LECCODE"),  	"");
		String STATUSCODE 	= CommonUtil.isNull(req.getParameter("STATUSCODE"),  	"");
		params.put("STATUSCODE", 			STATUSCODE);
		params.put("LECCODE", 			LECCODE);
		/* 페이징 */
		int count = service.payUserListByLeccodeCount(params);
		params.put("startNo", "0");
		params.put("endNo", String.valueOf(count+1));
		List<HashMap<String, String>> list = service.payUserListByLeccode(params);
	
		Date date = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String excelName = simpleDate.format(date) + "_프리패스수강인원";
		List<String> headerList = new ArrayList<String>();
		headerList.add("수강자");
		headerList.add("아이디");
		headerList.add("과목명");
		headerList.add("수강시작일");
		headerList.add("수강종료일");
		headerList.add("수강상태");
	
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : list) {
			HashMap newMap = new HashMap();
			int i = 0;
			
			
			newMap.put(i++, CommonUtil.isNull(map.get("USER_NM")));
			newMap.put(i++, CommonUtil.isNull(map.get("USER_ID")));
			newMap.put(i++, CommonUtil.isNull(map.get("SUBJECT_TITLE")));
			newMap.put(i++, CommonUtil.isNull(String.valueOf(map.get("START_DATE")).substring(0, 10)));
			newMap.put(i++, CommonUtil.isNull(String.valueOf(map.get("END_DATE")).substring(0, 10)));		
			newMap.put(i++, CommonUtil.isNull(map.get("STATUSCODE_NM")));			
	
			newList.add(newMap);
		}
	
		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);
	
		return new ExcelDownloadView();
	}
	
	@RequestMapping(value="/onLecOrderView.do")
	public String onLecOrderView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		// 검색조건
		String searchStartDate = CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		params.put("searchStartDate", searchStartDate);
		params.put("searchEndDate", searchEndDate);

		model.addAttribute("list", service.onLecOrderView(params));
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("params", params);
		
		return "/lectureYear/onLecOrderView";
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
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("SEARCHGUBN", "T");
        
		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID")));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));

    }

}