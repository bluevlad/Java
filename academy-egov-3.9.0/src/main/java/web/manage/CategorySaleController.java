package web.manage;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.MafUtil;
import web.manage.excel.MovieSalesExcel;
import web.manage.excel.TeacherExcelDownload;
import web.manage.excel.offAllSalesExcel;
import web.manage.excel.offLecSalesExcel;
import web.manage.excel.offReceivedExcel;
import web.manage.excel.onReceivedExcel;
import web.manage.service.CategorySaleService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   		: CategorySaleController.java
 * @Project    		: willbesWebAdmin
 * @Date       		: 2013. 11. 11. 
 * @Author     		: rainend
 * @변경이력    		:
 * @프로그램 설명 	: 경영관리
 */
@RequestMapping(value="/manage")
@Controller
public class CategorySaleController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** log */
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private CategorySaleService  service;
	
	@RequestMapping(value="/onCategorySales.do")
	public String onCategorySales(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String SEARCHTYPE 			= CommonUtil.isNull(request.getParameter("SEARCHTYPE"),  	"C");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("SEARCHTYPE", 				SEARCHTYPE);

		if (SEARCHTYPE.equals("C")) {
			model.addAttribute("list", service.onCategorySalesList(params));
		}else if(SEARCHTYPE.equals("S")){
			model.addAttribute("list", service.onSubjectSalesList(params));
		}else if(SEARCHTYPE.equals("L")){
			model.addAttribute("list", service.onLearningSalesList(params));
		}else if(SEARCHTYPE.equals("T")){
			model.addAttribute("list", service.onTeacherSalesList(params));
		} else {
			model.addAttribute("list", service.onCategorySalesList(params));
		}
		model.addAttribute("listSum", 			service.onCategorySalesListSum(params));
		model.addAttribute("SEARCHTYPE", 	SEARCHTYPE);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 		CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 	CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/onCategorySales";
	}

	@RequestMapping(value="/offCategorySales.do")
	public String offCategorySales(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		
		model.addAttribute("list", 					service.offCategorySalesList(params));
		model.addAttribute("listSum", 				service.offCategorySalesListSum(params));
		
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", 	searchEndDate);
		model.addAttribute("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 		CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 		CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/offCategorySales";
	}

	@RequestMapping(value="/onTeacherPay.do")
	public String onTeacherPayList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName 	= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacherName", 		searchTeacherName);
		
		List<HashMap<String,Object>> list = service.onTeacherPayList(params);
		
		long totalPrice = 0;
		long totalTeacherPrice = 0;
		long totalCount = 0;
		long totalRefund = 0;
		for(HashMap<String,Object> data : list) {
			totalPrice += Long.parseLong(String.valueOf(data.get("TOTAL_PRICE")));
			totalTeacherPrice += Long.parseLong(String.valueOf(data.get("REAL_MONEY")));
			totalRefund += Long.parseLong(String.valueOf(data.get("REFUND_PAY")));
			totalCount += Long.parseLong(String.valueOf(data.get("USER_COUNT")));
		}
		
		model.addAttribute("list", 						list);
		model.addAttribute("totalPrice",				totalPrice);
		model.addAttribute("totalTeacherPrice", 	totalTeacherPrice);
		model.addAttribute("totalCount", 			totalCount);
		model.addAttribute("totalRefund", 			totalRefund);
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("searchTeacherName",searchTeacherName);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/onTeacherPay";
	}	
	
	@RequestMapping(value="/onTeacherPayDetail.do")
	public String onTeacherPayDetail(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 			= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 			= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacher 			= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchTeacherName		= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacher", 				searchTeacher);
		
		List<HashMap<String,Object>> list = service.onTeacherPayDetail(params);
		List<HashMap<String,Object>> list2 = service.onTeacherPayReturnDetail(params);
		
		model.addAttribute("list", 							list);
		model.addAttribute("list2", 							list2);
		model.addAttribute("searchStartDate", 		searchStartDate);
		model.addAttribute("searchEndDate", 			searchEndDate);
		model.addAttribute("searchTeacher", 			searchTeacher);
		model.addAttribute("searchTeacherName", 	searchTeacherName);
		model.addAttribute("TOP_MENU_ID", 			CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 				CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 				CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/onTeacherPayDetail";
	}
	
	@RequestMapping(value="/onTeacherPayExcel.do")
	public View onTeacherPayExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 			= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 			= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacher 			= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchTeacherName		=  CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		searchTeacherName = searchTeacherName.replace("/", "_");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacher", 				searchTeacher);
		
		List<HashMap<String,Object>> list = service.onTeacherPayDetail(params);
		List<HashMap<String,Object>> list2 = service.onTeacherPayReturnDetail(params);
		
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("searchTeacherName", searchTeacherName);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);
				
		return new TeacherExcelDownload();
	}
	
	@RequestMapping(value="/offTeacherPay.do")
	public String offTeacherPayList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName 	= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	" ");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacherName",		searchTeacherName);
		
		List<HashMap<String,Object>> list = service.offTeacherPayList(params);
		
		int totalPrice = 0;
		int totalTeacherPrice = 0;
		int totalCount = 0;
		for(HashMap<String,Object> data : list) {
			totalPrice += Integer.parseInt(String.valueOf(data.get("TOTAL_PRICE")));
			totalTeacherPrice += Integer.parseInt(String.valueOf(data.get("REAL_MONEY")));
			totalCount += Integer.parseInt(String.valueOf(data.get("USER_COUNT")));
		}
		
		model.addAttribute("list", 						list);
		model.addAttribute("totalPrice",				totalPrice);
		model.addAttribute("totalTeacherPrice", 	totalTeacherPrice);
		model.addAttribute("totalCount", 			totalCount);
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("searchTeacherName",searchTeacherName);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/offTeacherPay";
	}	
	
	@RequestMapping(value="/offTeacherPayDetail.do")
	public String offTeacherPayDetail(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 			= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 			= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacher 			= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchTeacherName		= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacher", 				searchTeacher);
		
		List<HashMap<String,Object>> list = service.offTeacherPayDetail(params);
		List<HashMap<String,Object>> list2 = service.offTeacherPayReturnDetail(params);
		
		model.addAttribute("list", 							list);
		model.addAttribute("list2", 							list2);
		model.addAttribute("searchStartDate", 		searchStartDate);
		model.addAttribute("searchEndDate", 			searchEndDate);
		model.addAttribute("searchTeacher", 			searchTeacher);
		model.addAttribute("searchTeacherName", 	searchTeacherName);
		model.addAttribute("TOP_MENU_ID", 			CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 				CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 				CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/offTeacherPayDetail";
	}
	
	@RequestMapping(value="/offTeacherPayExcel.do")
	public View offTeacherPayExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 			= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 			= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacher 			= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchTeacherName		= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		params.put("searchTeacher", 				searchTeacher);
		
		List<HashMap<String,Object>> list = service.offTeacherPayDetail(params);
		List<HashMap<String,Object>> list2 = service.offTeacherPayReturnDetail(params);
		
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("searchTeacherName", searchTeacherName);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);
				
		return new TeacherExcelDownload();
	}
	
	//동영상 선수금 내역 초기화면
	@RequestMapping(value="/onAdvanceReceived_ready.do")
	public String onAdvanceReceived_ready(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchDate", searchDate);
		
		model.addAttribute("searchDate", 		searchDate);
		model.addAttribute("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 		CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 		CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
				
		return "/manage/onAdvanceReceived_ready";
		
	}
	
	@RequestMapping(value="/onAdvanceReceived.json")
	@ResponseBody 
	public String onAdvanceReceived(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		// 검색조건
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		
		params.put("searchDate", searchDate);
		
		String cnt = service.onAdvanceReceivedCount(params);

        String msg = "총 "+ cnt+ "건수가 엑셀다운로드되며, 소요시간은 " +(Integer.parseInt(cnt)/1000)*1.6+"초가 예상됩니다.\n 엑셀다운로드를 진행하시겠습니까?" ;
				
		return msg;
	}
	
	@RequestMapping(value="/onReceivedExcel.do")
	public View onReceivedExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchDate", 			searchDate);
		
		List<HashMap<String,Object>> list = service.onAdvanceReceivedList(params);
		
		model.addAttribute("list", list);
		model.addAttribute("searchDate", searchDate);
				
		return new onReceivedExcel();
	}
	
	@RequestMapping(value="/offAdvanceReceived_ready.do")
	public String offAdvanceReceived_ready(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchDate", searchDate);
		
		model.addAttribute("searchDate", 		searchDate);
		model.addAttribute("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 		CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 		CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
				
		return "/manage/offAdvanceReceived_ready";
		
	}
	
	@RequestMapping(value="/offAdvanceReceived.do")
	public String offAdvanceReceived(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		String LEC = CommonUtil.isNull(request.getParameter("LEC"), "D");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchDate", searchDate);

		List<HashMap<String,Object>> list = null;
		
		if (LEC.equals("D")){
			 list = service.offAdvanceReceivedList_D(params);
		}else{
			 list = service.offAdvanceReceivedList_P(params);
		}

		List<HashMap<Integer, Object>> newList = new ArrayList<HashMap<Integer, Object>>();
    	int sub_price = 0;
    	int teacher_price = 0;
    	int rest_price = 0;
    	int use_price = 0;

		for(HashMap<String, Object> data : list) {
			sub_price += Integer.parseInt(String.valueOf(MafUtil.nvl(data.get("SUB_PRICE"),"0")));
			teacher_price += Integer.parseInt(String.valueOf(MafUtil.nvl(data.get("TEACHER_PRICE"),"0")));
			rest_price += Integer.parseInt(String.valueOf(MafUtil.nvl(data.get("REST_PRICE"),"0")));
			use_price += Integer.parseInt(String.valueOf(MafUtil.nvl(data.get("USE_PRICE"),"0")));
		}
		
		model.addAttribute("list", 					list);
		model.addAttribute("sub_price", 	sub_price);
		model.addAttribute("teacher_price", 	teacher_price);
		model.addAttribute("rest_price", 	rest_price);
		model.addAttribute("use_price", 				use_price);
		model.addAttribute("searchDate", 		searchDate);
		model.addAttribute("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 		CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 		CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("MENU_NM", 		CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
				
		return "/manage/offAdvanceReceived";
	}
	
	@RequestMapping(value="/offReceivedExcel.do")
	public View offReceivedExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchDate = CommonUtil.isNull(request.getParameter("searchDate"), 	CommonUtil.getCurrentDate());
		String LEC = CommonUtil.isNull(request.getParameter("LEC"), "D");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchDate", searchDate);
		
		List<HashMap<String,Object>> list = null;
		
		if (LEC.equals("D")){
			 list = service.offAdvanceReceivedList_D(params);
		}else{
			 list = service.offAdvanceReceivedList_P(params);
		}

		model.addAttribute("LEC", LEC);
		model.addAttribute("list", list);
		model.addAttribute("searchDate", searchDate);
		
		return new offReceivedExcel();
	}
	
	@RequestMapping(value="/offLecSales.do")
	public String offLecSalesList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		
		/*
		List<HashMap<String,Object>> list = service.offLecSalesList(params);
		
		int totalPrice = 0;
		int totalPay = 0;
		int totalUsePay = 0;
		int totalRestPay = 0;
		for(HashMap<String,Object> data : list) {
			totalPrice += Integer.parseInt(String.valueOf(data.get("SUBJECT_REAL_PRICE")));
			totalPay += Integer.parseInt(String.valueOf(data.get("PRICE")));
			totalUsePay += Integer.parseInt(String.valueOf(data.get("USE_PRICE")));
			totalRestPay += Integer.parseInt(String.valueOf(data.get("REST_PRICE")));
		}
		
		model.addAttribute("list", 						list);
		model.addAttribute("totalPrice",				totalPrice);
		model.addAttribute("totalPay",				totalPay);
		model.addAttribute("totalUsePay",				totalUsePay);
		model.addAttribute("totalRestPay",				totalRestPay);
		*/
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/offLecSales";
	}
	
	//off 엑셀리스트
	@RequestMapping(value="/offLecSalesExcel.do")
	public View offLecSalesExcel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		List<HashMap<String,Object>> list = service.offLecSalesList(params);

		model.addAttribute("list", list);
		model.addAttribute("PTYPE", "D");
		model.addAttribute("searchStartDate", params.get("searchStartDate"));
		model.addAttribute("searchEndDate", params.get("searchEndDate"));

		return new offLecSalesExcel();
	}
	
	@RequestMapping(value="/offLecSalesJong.do")
	public String offLecSalesJongList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 				searchEndDate);
		
		/*
		List<HashMap<String,Object>> list = service.offLecSalesJongList(params);
		
		int totalPrice = 0;
		int totalPay = 0;
		int totalUsePay = 0;
		int totalRestPay = 0;
		for(HashMap<String,String> data : list) {
			totalPrice += Integer.parseInt(String.valueOf(data.get("SUBJECT_REAL_PRICE")));
			totalPay += Integer.parseInt(String.valueOf(data.get("PRICE")));
			totalUsePay += Integer.parseInt(String.valueOf(data.get("USE_PRICE")));
			totalRestPay += Integer.parseInt(String.valueOf(data.get("REST_PRICE")));
		}
		
		model.addAttribute("list", 						list);
		model.addAttribute("totalPrice",				totalPrice);
		model.addAttribute("totalPay",				totalPay);
		model.addAttribute("totalUsePay",				totalUsePay);
		model.addAttribute("totalRestPay",				totalRestPay);
		*/
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/offLecSalesJong";
	}
	
	//off 엑셀리스트
	@RequestMapping(value="/offLecSalesJongExcel.do")
	public View offLecSalesJongExcel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		//엑셀 리스트
		List<HashMap<String,Object>> list = service.offLecSalesJongList(params);

		model.addAttribute("list", list);
		model.addAttribute("PTYPE", "P");
		model.addAttribute("searchStartDate", params.get("searchStartDate"));
		model.addAttribute("searchEndDate", params.get("searchEndDate"));
				
		return new offLecSalesExcel();
	}
	
	
	@RequestMapping(value="/teacherdashBoardList.pop")
    public String dashBoardList1(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
      //  setParam(params, request, model);

        String searchYear			 			= CommonUtil.isNull(request.getParameter("searchYear"), CommonUtil.getCurrentYear());
        String searchMonth		 				= CommonUtil.isNull(request.getParameter("searchMonth"), CommonUtil.getCurrentMonth());
        String searchUserId		 				=  CommonUtil.isNull(request.getParameter("userid"),"");
        String Teachernm		 				= CommonUtil.isNull(request.getParameter("Teachernm"),"");
        String startdate		 				= CommonUtil.isNull(request.getParameter("startdate"),"");
        String enddate		 				= CommonUtil.isNull(request.getParameter("enddate"),"");
        String currentYear						= CommonUtil.getCurrentYear();
        String prevYear							= String.valueOf(Integer.parseInt(CommonUtil.getCurrentYear())-1);
        String sqlMonth1							= "";
        String sqlMonth2							= "";
        for(int i = 1; i <= 12; i++) {
            String temp = String.valueOf(i);
            if(temp.length() == 1) temp = "0" + temp;

            String temp1 = prevYear + temp;
            String temp2 = currentYear + temp;
            if(sqlMonth1.length() > 0) sqlMonth1 += ", ";
            sqlMonth1 += "SUM(DECODE(A.YYYYMM,'"+temp1+"', NVL(B.TOTAL_PRICE,0), 0)) AS P_"+temp;
            sqlMonth1 += ", SUM(DECODE(A.YYYYMM,'"+temp2+"', NVL(B.TOTAL_PRICE,0), 0)) AS C_"+temp;

            if(sqlMonth2.length() > 0) sqlMonth2 += "UNION ALL ";
            sqlMonth2 += "SELECT '"+temp1+"' YYYYMM FROM DUAL ";
            sqlMonth2 += "UNION ALL SELECT '"+temp2+"' YYYYMM FROM DUAL ";
        }
        
        params.put("searchUserId", 			searchUserId);
        params.put("searchDate",				searchYear + searchMonth);
        params.put("currentYear",				currentYear);
        params.put("prevYear",					prevYear);
        params.put("sqlMonth1",               	sqlMonth1);
        params.put("sqlMonth2",               	sqlMonth2);
        params.put("Teachernm",               	Teachernm);
        params.put("startdate",               	startdate);
        params.put("enddate",               	enddate);

//        if(params.get("topMenuType").equals("O")) {
            model.addAttribute("dashBoard1",	service.dashBoardList1(params));
            model.addAttribute("dashBoard2",	service.dashBoardList2(params));
            model.addAttribute("dashBoard3",	service.dashBoardList3(params));
            model.addAttribute("dashBoard4",	service.dashBoardList4(params));
/*        } else {
            model.addAttribute("dashBoard1",	service.dashBoardOffList1(params));
            model.addAttribute("dashBoard2",	service.dashBoardOffList2(params));
            model.addAttribute("dashBoard3",	service.dashBoardOffList3(params));
        }*/
//        model.addAttribute("categoryList", 		indexservice.getCategory(params));
//        model.addAttribute("subjectByCategory", 		indexservice.getMovieSubjectByCategory(params));
        model.addAttribute("searchYear", 		searchYear);
        model.addAttribute("searchMonth",	searchMonth);
        model.addAttribute("currentYear",		currentYear);
        model.addAttribute("prevYear",			prevYear);
        model.addAttribute("Teachernm",			Teachernm);
        model.addAttribute("startdate",			startdate);
        model.addAttribute("enddate",			enddate);
        return "/manage/dashboard/teacherdashBoardList";
    }
	
	@RequestMapping(value="/학원매출내역.do")
	public String 학원매출내역(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate = CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", searchStartDate);
		params.put("searchEndDate", searchEndDate);

		/*
		List<HashMap<String,Object>> list = service.학원매출내역(params);
		
		int totalPrice = 0;
		int totalAdd = 0;
		int totalRefund = 0;
		for(HashMap<String,Object> data : list) {
			totalPrice += Integer.parseInt(String.valueOf(data.get("PRICE")));
			totalAdd += Integer.parseInt(String.valueOf(data.get("ADDPRICE")));
			totalRefund += Integer.parseInt(String.valueOf(data.get("REF_PRICE")));
		}
		
		model.addAttribute("list", 						list);
		model.addAttribute("totalPrice",				totalPrice);
		model.addAttribute("totalAdd",				totalAdd);
		model.addAttribute("totalRefund",				totalRefund);
		 */
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));		
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		
		return "/manage/학원매출내역";
	}
	
	//off 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/학원매출Excel.do")
	public View 학원매출Excel(ModelMap output, HttpServletRequest req) throws Exception {

		String searchStartDate 			= CommonUtil.isNull(req.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 			= CommonUtil.isNull(req.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		
		HashMap<String, Object> params = new  HashMap<String, Object>();
		params.put("searchStartDate", searchStartDate);
		params.put("searchEndDate", searchEndDate);
		
		//엑셀 리스트
		List<HashMap<String, Object>> list = service.학원매출내역(params);
			
		output.addAttribute("list", list);
		output.put("searchStartDate", 			searchStartDate);
		output.put("searchEndDate", 				searchEndDate);
				
		return new offAllSalesExcel();
	}
	
	@RequestMapping(value="/MovieSales.do")
	public String MovieSales(ModelMap model, HttpServletRequest request) throws  Exception {

		HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		/*
		List<HashMap<String,Object>> list = null;
				
		if (params.get("SEARCHTYPE").equals("D")){
			 list = service.MovieSalesD(params);
		}else if (params.get("SEARCHTYPE").equals("P")){
			 list = service.MovieSalesP(params);
		}else if (params.get("SEARCHTYPE").equals("Y")){
			 list = service.MovieSalesY(params);
		}else if (params.get("SEARCHTYPE").equals("L")){
			 list = service.BookSales(params);
		}else{
			 list = service.MovieSalesD(params);
		}
		
		int totalPrice = 0;
		int totalRefund = 0;
		for(HashMap<String,Object> data : list) {
			totalPrice += Integer.parseInt(String.valueOf(data.get("PRICE")));
			totalRefund += Integer.parseInt(String.valueOf(data.get("CANCEL_PRICE")));
		}
		
		model.addAttribute("list", list);
		model.addAttribute("totalPrice",	totalPrice);
		model.addAttribute("totalRefund", totalRefund);
		*/
		model.addAttribute("params", params);
		
		return "/manage/MovieSales";
	}
	
	//off 엑셀리스트
	@RequestMapping(value="/MovieSalesExcel.do")
	public View MovieSalesExcel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		List<HashMap<String,Object>> list = null;
		
		//엑셀 리스트
		if (params.get("SEARCHTYPE").equals("D")){
			 list = service.MovieSalesD(params);
		}else if (params.get("SEARCHTYPE").equals("P")){
			 list = service.MovieSalesP(params);
		}else if (params.get("SEARCHTYPE").equals("Y")){
			 list = service.MovieSalesY(params);
		}else if (params.get("SEARCHTYPE").equals("L")){
			 list = service.BookSales(params);
		}else{
			 list = service.MovieSalesD(params);
		}

		model.addAttribute("list", list);
		model.addAttribute("searchStartDate", params.get("searchStartDate"));
		model.addAttribute("searchEndDate", params.get("searchEndDate"));
		model.addAttribute("SEARCHTYPE", params.get("SEARCHTYPE"));

		return new MovieSalesExcel();
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
		HashMap<String, Object> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID", loginInfo.get("USER_ID"));
		params.put("UPD_ID", loginInfo.get("USER_ID"));

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "D"));
		params.put("searchStartDate", CommonUtil.isNull(request.getParameter("searchStartDate"), CommonUtil.getCurrentDate()));
		params.put("searchEndDate", CommonUtil.isNull(request.getParameter("searchEndDate"), CommonUtil.getCurrentDate()));

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