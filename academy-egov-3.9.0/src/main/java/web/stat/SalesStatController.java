package com.willbes.web.stat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.stat.service.SalesStatService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   	: SalesStatController.java
 * @Project    		: willbesAdmin
 * @Date       		: 2013. 11. 11. 
 * @Author     		: rainend
 * @변경이력    	:
 * @프로그램 설명 	: 경영관리
 */
@RequestMapping(value="/stat")
@Controller
public class SalesStatController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
	private SalesStatService  service;
	
	/**
	 * @Method Name : list
	 * @Method 설명 : 강사 목록
	 */	
	@RequestMapping(value="/teacher/list.do")
	public String list(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> list = service.teacherList(params);

		List<HashMap<String, String>> tlist = service.teacherSubjectList(params);
		
		model.addAttribute("list", list);
		model.addAttribute("tlist", tlist);
		model.addAttribute("params", params);
		
		return "/stat/teacher/list";
	}
	
	/**
	 * @Method Name : SalesStat
	 * @Method 설명 : 강사 매출 상세 내역
	 */	
	@RequestMapping(value="/teacher/SalesStat.do")
	public String SalesStat(ModelMap model, HttpServletRequest request) throws Exception {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String PRF_ID 	= CommonUtil.isNull(request.getParameter("PRF_ID"),  	"");
		
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("PRF_ID", 				PRF_ID);

		List<HashMap<String, String>> list = service.teacherSalesStatList(params); 
		HashMap<String, String> view  = service.teacherOne(params);

		model.addAttribute("list", list);
		model.addAttribute("view", view);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("params", params);
		
		return "/stat/teacher/SalesStat";
	}
	
	/**
	 * @Method Name : SalesMake
	 * @Method 설명 : 강사 매출 통계 산출
	 */	
	@RequestMapping(value="/teacher/SalesMake.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String SalesMake(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		
		setParam(params, request, model);

		params.put("YEAR", CommonUtil.isNull(request.getParameter("S_YAER"), ""));
		params.put("S_TYPE", CommonUtil.isNull(request.getParameter("S_TYPE"), ""));
		if ("ON".equals(params.get("S_TYPE"))){
			service.makeOnSalesStat(params);
		}else{
			service.makeOffSalesStat(params);
		}

		return "forward:/stat/teacher/list.do";
	}	

	/**
	 * @Method Name : SalesList
	 * @Method 설명 : 회원가입후 판매 통계
	 */	
	@RequestMapping(value="/sales/SalesList.do")
	public String SalesList(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		HashMap<String, String> saleDetail  = service.userBuyStatList(params);
		
		model.addAttribute("item", saleDetail);
		model.addAttribute("params", params);
		
		return "/stat/sales/list";
	}

	/**
	 * @Method Name : searchKeywordList
	 * @Method 설명 : 회원가입후 판매 통계
	 */	
	@RequestMapping(value="/search/searchKeywordList.do")
	public String searchKeywordList(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String ch1 = null;
    	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
		TimeZone.setDefault(tz);
		Date d1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		ch1 = sdf1.format(d1);

		params.put("sdate", CommonUtil.isNull(request.getParameter("sdate"), ch1));
		params.put("edate", CommonUtil.isNull(request.getParameter("edate"), ch1));

        /* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = service.searchKeywordList(params); 
		int listCount = service.searchKeywordCount(params);
		int srcSum = service.searchKeywordSum(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("srcSum", srcSum);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		
		return "/stat/search/list";
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
		
		params.put("searchStartDate", CommonUtil.isNull(request.getParameter("searchStartDate"), "20150101"));
		params.put("searchEndDate", CommonUtil.isNull(request.getParameter("searchEndDate"), "20151231"));
		
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID")));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
	}	
	
}