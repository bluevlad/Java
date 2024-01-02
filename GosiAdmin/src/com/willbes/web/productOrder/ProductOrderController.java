package com.willbes.web.productOrder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.MafUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.file.service.MultipartFileService;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.lecture.service.SubjectService;
import com.willbes.web.lecture.service.TeacherService;
import com.willbes.web.productOrder.service.ProductOrderService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/productOrder")
@Controller
public class ProductOrderController {

	/* last modified 2014-08-20 */

	/** log */
	private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
    private ProductOrderService productOrderService;

	@Autowired
	private SubjectService subjectservice;

	@Autowired
	private TeacherService teacherservice;

	@Resource(name="fileUtil")
	FileUtil fileUtil;
	
		
	// 전체 상품 주문 관리 리스트
	@RequestMapping(value="/list_s.do")
	public String list_s(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;
		List order_list = null;
		List payment_list = null;
		
		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String manager_id = sessionMap.get("USER_ID");
		
		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		menuParams.put("MANAGER_ID", manager_id);
		output.addAttribute("menuParams", menuParams);
		//메뉴 param
		
		String message = CommonUtil.isNull(req.getParameter("message"), "");
		
	    String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
	    String forward_url = null;
		
		int page = Integer.parseInt(CommonUtil.isNull(req.getParameter("page"), "1"));
		int position = Integer.parseInt(CommonUtil.isNull(req.getParameter("position"), "1"));
		int maxRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("maxRow"), "15"));
		
	    
	    String schtype = CommonUtil.isNull(req.getParameter("schtype"), "");
	    String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
	    String sortfield = CommonUtil.isNull(req.getParameter("sortfield"), "");
	    String sort = CommonUtil.isNull(req.getParameter("sort"), "");
	    String sys_cd = CommonUtil.isNull(req.getParameter("sys_cd"), "");
	    
	    int code_no = Integer.parseInt(CommonUtil.isNull(req.getParameter("code_no"), "1"));
	    String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
	    String edate = CommonUtil.isNull(req.getParameter("edate"), "");
	    String returnurl = CommonUtil.isNull(req.getParameter("returnurl"), "");
	        
		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), "15"));
		
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String prodgbn = CommonUtil.isNull(req.getParameter("prodgbn"), "");
		String payall = CommonUtil.isNull(req.getParameter("payall"), "0");
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("message", message);
		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
		searchMap.put("cmd", cmd);
		searchMap.put("forward_url", forward_url);
		searchMap.put("page", page);
		searchMap.put("position", position);			
		searchMap.put("maxRow", maxRow);
		searchMap.put("schtype", schtype);
		searchMap.put("keyword", keyword);
		searchMap.put("sortfield", sortfield);			
		searchMap.put("sort", sort);
		searchMap.put("sys_cd", sys_cd);
		searchMap.put("code_no", code_no);
		searchMap.put("sdate", sdate);			
		searchMap.put("edate", edate);
		searchMap.put("returnurl", returnurl);
		
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);			
		searchMap.put("paycode", paycode);
		searchMap.put("prodgbn", prodgbn);
		searchMap.put("payall", payall);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));
		
		if("".equals(search_date_type)){
			search_date_type = "등록일";
		}
		
		if(sdate.equals("")){
			
			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);
			
			sdate = Delday6;
		}
		
		if(edate.equals("")){
			String ch1 = null;
	    	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);
			
			edate = ch1;
		}
		
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);			
		searchMap.put("paycode", paycode);
		searchMap.put("prodgbn", prodgbn);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);			
		searchMap.put("edate", edate);
		searchMap.put("flag", "");
		
		//상태코드 셀렉트박스 리스트
		order_list = productOrderService.getOrderStatuscodeList(searchMap);
		
		//지급방법 셀렉트박스 리스트
		payment_list = productOrderService.getPaymentList(searchMap);
				
		output.addAttribute("order_list", order_list);
		output.addAttribute("payment_list", payment_list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		
		return "productOrder/Tbl_Order_Lst";
	}

	// 전체 상품 주문 관리 리스트
	@RequestMapping(value="/list.do")
	public String list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;
		List order_list = null;
		List payment_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String manager_id = sessionMap.get("USER_ID");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		menuParams.put("MANAGER_ID", manager_id);
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String message = CommonUtil.isNull(req.getParameter("message"), "");

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
        String forward_url = null;

		int page = Integer.parseInt(CommonUtil.isNull(req.getParameter("page"), "1"));
		int position = Integer.parseInt(CommonUtil.isNull(req.getParameter("position"), "1"));
        int maxRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("maxRow"), propertiesService.getInt("pageUnit")+""));


        String schtype = CommonUtil.isNull(req.getParameter("schtype"), "");
        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
        String sortfield = CommonUtil.isNull(req.getParameter("sortfield"), "");
        String sort = CommonUtil.isNull(req.getParameter("sort"), "");
        String sys_cd = CommonUtil.isNull(req.getParameter("sys_cd"), "");

        int code_no = Integer.parseInt(CommonUtil.isNull(req.getParameter("code_no"), "1"));
        String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        String returnurl = CommonUtil.isNull(req.getParameter("returnurl"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String prodgbn = CommonUtil.isNull(req.getParameter("prodgbn"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "0");
    	String ucancel = CommonUtil.isNull(req.getParameter("ucancel"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("message", message);
		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
		searchMap.put("cmd", cmd);
		searchMap.put("forward_url", forward_url);
		searchMap.put("page", page);
		searchMap.put("position", position);
		searchMap.put("maxRow", maxRow);
		searchMap.put("schtype", schtype);
		searchMap.put("keyword", keyword);
		searchMap.put("sortfield", sortfield);
		searchMap.put("sort", sort);
		searchMap.put("sys_cd", sys_cd);
		searchMap.put("code_no", code_no);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("returnurl", returnurl);

		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("prodgbn", prodgbn);
		searchMap.put("payall", payall);
		searchMap.put("ucancel", ucancel);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		if("".equals(search_date_type)){
    		search_date_type = "등록일";
    	}

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);

			sdate = Delday6;
			
		}

		if(edate.equals("")){
			String ch1 = null;
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);

			edate = ch1;
		}
		
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("flag", "");

		//상태코드 셀렉트박스 리스트
		order_list = productOrderService.getOrderStatuscodeList(searchMap);

		//지급방법 셀렉트박스 리스트
		payment_list = productOrderService.getPaymentList(searchMap);

		//리스트
		list = productOrderService.getOrderListDB(searchMap);

		// 총 건수
		int listCount = productOrderService.getOrderListCount(searchMap);

		if (list.size() > 0) {
			for (int k = 0; k < list.size(); k++) {
				HashMap map = (HashMap) list.get(k);
				String orderno = (String) map.get("ORDERNO");

				searchMap.put("orderno", orderno);

				//2번째 리스트
				list_second = productOrderService.getTblOrderMgntListDB(searchMap);

				output.addAttribute("list_second" + Integer.toString(k), list_second);
			}
		}


		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("order_list", order_list);
		output.addAttribute("payment_list", payment_list);
		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "productOrder/Tbl_Order_Lst";
	}


	// 쿠폰 삭제
		@RequestMapping(value="/Coupon_Del.do")
		@ResponseBody
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String Coupon_Del(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
			HashMap<String, String> params = new HashMap<String, String>();

			params.put("USERID",CommonUtil.isNull(req.getParameter("USERID"), ""));
			params.put("CCODE",CommonUtil.isNull(req.getParameter("CCODE"), ""));
			params.put("IDX",CommonUtil.isNull(req.getParameter("IDX"), ""));

			productOrderService.Coupon_Del(params);

			String flag = "Y";

			return flag;
		}

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/tbl_order_list_excel.do")
	public View excelDownload(ModelMap output, HttpServletRequest req) throws Exception {

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String prodgbn = CommonUtil.isNull(req.getParameter("prodgbn"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "0");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

		if("".equals(search_date_type)){
    		search_date_type = "등록일";
    	}

		if(sdate.equals("")){
			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
			java.util.Date date6 = month6.getTime();
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(date6);
		}

		if(edate.equals("")){
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            edate = sdf1.format(d1);
		}

		HashMap<String, String> params = new  HashMap<String, String>();
		params.put("orderstatuscode",orderstatuscode);
		params.put("search_date_type",search_date_type);
		params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
		params.put("searchtype",searchtype);
		params.put("paycode",paycode);
		params.put("prodgbn",prodgbn);
		params.put("payall",payall);
		params.put("sdate",sdate);
		params.put("edate",edate);

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = productOrderService.getOrderExcelListDB(params);

		Date date = new Date();
  	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "전체상품주문리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("주문자");
		headerList.add("입금자");
		headerList.add("계좌번호");
		headerList.add("등록일");
		headerList.add("상품명");
		headerList.add("구입금액");
		headerList.add("상태");
		headerList.add("입금일");
		headerList.add("입금확인");

		int dCount = 0;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			dCount++;
			int i = 0;
			newMap.put(i++, String.valueOf(dCount));
			newMap.put(i++, map.get("ORDERNO").toString());
			newMap.put(i++, map.get("ORDERS_USERNAME").toString());
			newMap.put(i++, map.get("APPROVALS_PAYNAME").toString());
			newMap.put(i++, map.get("APPROVALS_PAYCODENAME").toString());
			newMap.put(i++, map.get("ORDERS_REGDATE").toString());

			params.put("orderno",map.get("ORDERNO").toString());
			//2번째 리스트
            List list_second = productOrderService.getTblOrderMgntListDB(params);

			String courier = "";
			int tot_sum = 0;
			int ori_price = 0;
			// 상품 갯수
			int rCnt =  Integer.parseInt(String.valueOf(map.get("ROWCNT")));

			String rePriceNullChk = "";
			int rePrice = 0;
			if(map.get("APPROVALS_REPRICE") == null ) {
				rePriceNullChk = "null";
				rePrice = 0;
			} else {
				rePriceNullChk = "";
				rePrice = Integer.parseInt(String.valueOf(map.get("APPROVALS_REPRICE")));
			}

			if (list_second.size() > 0) {
				for (int k = 0; k < list_second.size(); k++) {
					HashMap map2 = (HashMap) list_second.get(k);

					//상품명
					int index = k+1;
					String name2 = map2.get("NAME").toString();
					name2 = index + ". " +name2;

					if(k > 0){
						newMap = new HashMap();
						i = 0;
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
					}
					newMap.put(i++, name2);

					//구입금액
					newMap.put(i++, String.valueOf(map2.get("PRICE")));

					//상태
					String STATUSCODE2 = "";
					if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 100){
						STATUSCODE2 = "입금확인중";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 105){
						STATUSCODE2 = "입금완료";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 110){
						STATUSCODE2 = "배송준비중";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 120){
						STATUSCODE2 = "배송중";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 130){
						STATUSCODE2 = "배송완료";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 140){
						STATUSCODE2 = "취소요청";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 150){
						STATUSCODE2 = "취소완료";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 160){
						STATUSCODE2 = "교환요청";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 170){
						STATUSCODE2 = "교환배송중";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 180){
						STATUSCODE2 = "교환완료";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 220){
						STATUSCODE2 = "환불요청";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 230){
						STATUSCODE2 = "환불완료";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 240){
						STATUSCODE2 = "단과수강취소";
					}else if(Integer.parseInt(map2.get("STATUSCODE").toString().substring(3)) == 000){
						STATUSCODE2 = "환불완료";
					}
					// 상품주문상태
					newMap.put(i++, STATUSCODE2);

					//입금일
					String confirmdate2 = "";
					if(map2.get("CONFIRMDATE").toString().length() > 0){
						confirmdate2 = map2.get("CONFIRMDATE").toString();
					} else {
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						confirmdate2 = sdf.format(d);
					}
					newMap.put(i++, confirmdate2);

					//입금확인
					newMap.put(i++, map2.get("ISCONFIRM").toString()); //.replace("-", "")

					newList.add(newMap);  	// 한줄 추가

					if (rCnt == (k+1)) {
						// 택배비 + 환불택배비 등 표시
						ori_price = Integer.parseInt(String.valueOf(map2.get("PRICE")));
						String STATUSCODE = map2.get("STATUSCODE").toString();

						if (STATUSCODE.equals("DLV150") || STATUSCODE.equals("DLV230")) {
							ori_price = 0;
						}

						tot_sum = tot_sum + ori_price;

						int courierInt = tot_sum - Integer.parseInt(String.valueOf(map.get("APPROVALS_POINT")));
						courier = "   " + Integer.toString(courierInt) + "원 + (택배비:" + String.valueOf(map.get("APPROVALS_ADDPRICE")) + "원)";

						if (rePriceNullChk != null){
							courier = courier + ", 환불택배비:" + rePrice;
						}

						if(Integer.parseInt(String.valueOf(map.get("APPROVALS_POINT"))) != 0){
							courier = courier + " + POINT:" + String.valueOf(map.get("APPROVALS_POINT"));
						}

						int total = tot_sum + Integer.parseInt(String.valueOf(map.get("APPROVALS_ADDPRICE"))) + rePrice;
						courier = courier + " = " + Integer.toString(total);
						newMap = new HashMap();
						i = 0;
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, courier);
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");
						newMap.put(i++, "");

						newList.add(newMap);	// 한줄 추가
					}
				}
			}else{
				newMap.put(i++, "");
				newMap.put(i++, "");
				newMap.put(i++, "");
				newMap.put(i++, "");
				newMap.put(i++, "");

				newList.add(newMap);
			}

		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

	// 전체상품주문관리 주문번호
	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String update(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);


		//리스트
		list = productOrderService.getTblApprovalsViewList(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		Map<String, Object> pay_list = new HashMap<String, Object>();
		pay_list.put("PRICE",map.get("PRICE"));
		pay_list.put("ADDPRICE",map.get("ADDPRICE"));
		pay_list.put("PAYCODENAME",map.get("PAYCODENAME"));
		pay_list.put("ACCTNONAME",map.get("ACCTNONAME"));
		pay_list.put("VCDBANK",map.get("VCDBANK"));
		pay_list.put("PAYNAME",map.get("PAYNAME"));
		pay_list.put("PAYCODE",map.get("PAYCODE"));
		pay_list.put("POINT",map.get("POINT"));
		pay_list.put("RETURNVALUE",map.get("RETURNVALUE"));
		pay_list.put("VACCT",map.get("VACCT"));
		pay_list.put("STATUSCODE",map.get("STATUSCODE"));
		pay_list.put("STATUS_NM",map.get("STATUS_NM"));
		pay_list.put("STATUS_DT",map.get("STATUS_DT"));
		pay_list.put("USE_DEL_COUPON_CNT",map.get("USE_DEL_COUPON_CNT"));

		// 총 건수
		int deliverscount = productOrderService.getTblDeliversViewListCount(searchMap);

		searchMap.put("deliverscount", deliverscount);

		if(deliverscount > 0){
			del_list = productOrderService.getTblDeliversViewList(searchMap);

			map = new HashMap<String, Object>();
			map = (HashMap) del_list.get(0);
			Map<String, Object> del_list2 = new HashMap<String, Object>();
			del_list2.put("SENDNO",map.get("SENDNO"));
			del_list2.put("USERNAME",map.get("USERNAME"));
			del_list2.put("TELNO",map.get("TELNO"));
			del_list2.put("CELLNO",map.get("CELLNO"));
			del_list2.put("ZIPCD",map.get("ZIPCD"));
			del_list2.put("ZIPCD_SET1",map.get("ZIPCD_SET1"));
			del_list2.put("ZIPCD_SET2",map.get("ZIPCD_SET2"));
			del_list2.put("ADDR",map.get("ADDR"));
			del_list2.put("MEMO",map.get("MEMO"));
			del_list2.put("SENDDATE",map.get("SENDDATE"));
			del_list2.put("DLEORDER",map.get("DLEORDER"));
			del_list2.put("DLEORDER_NM",map.get("DLEORDER_NM"));

			output.addAttribute("del_list", del_list2);

    	}

		order_list = productOrderService.getTblOrdersViewList(searchMap);

		map = new HashMap<String, Object>();
		map = (HashMap) order_list.get(0);
		Map<String, Object> order_list2 = new HashMap<String, Object>();
		order_list2.put("REG_DT",map.get("REG_DT"));
		order_list2.put("USER_NM",map.get("USER_NM"));
		order_list2.put("TEL_NO",map.get("TEL_NO"));
		order_list2.put("PHONE_NO",map.get("PHONE_NO"));
		order_list2.put("ZIP_CODE",map.get("ZIP_CODE"));
		order_list2.put("ADDRESS1",map.get("ADDRESS1"));
		order_list2.put("EMAIL",map.get("EMAIL"));
		order_list2.put("BIRTH_DT",map.get("BIRTH_DT"));

		lec_list = productOrderService.getLecMstViewList(searchMap);

		output.addAttribute("deliverscount", deliverscount);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", pay_list);
		output.addAttribute("order_list", order_list2);
		output.addAttribute("lec_list", lec_list);

		return "productOrder/Tbl_Order_Mod";
	}

	// 카드변경
	@RequestMapping(value="/pay_kind_update.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pay_kind_update(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno_val"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);


		// 카드변경
		productOrderService.setPayKindUpdate(searchMap);

    	output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		output.addAttribute("message", URLEncoder.encode("정상적으로 수정되었습니다.","UTF-8"));

		if("3".equals(payall)) {
			return "redirect:/productOrder/list_0.do";
		}else{
			return "redirect:/productOrder/list.do";
		}
	}

	// 입금상태저장
	@RequestMapping(value="/staus_update.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String staus_update(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String[] mgntno = req.getParameterValues("mgntno");
        String[] orderno = req.getParameterValues("orderno");
        String[] delivers_cellno = req.getParameterValues("delivers_cellno");
        String[] orders_mgntNo_count = req.getParameterValues("orders_mgntNo_count");
        String[] point = req.getParameterValues("point");
        String[] statuscode = req.getParameterValues("statuscode");
        String[] statuscode_old = req.getParameterValues("statuscode_old");
        String[] confirmdate = req.getParameterValues("confirmdate");
        String[] confirmdatelast = req.getParameterValues("confirmdatelast");
        String[] isconfirm = req.getParameterValues("isconfirm");
        String[] userids = req.getParameterValues("userid");
        String[] mgntno_change = req.getParameterValues("mgntno_change");

        String userid = sessionMap.get("USER_ID");
        String temp_orderno = "";

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);


		Date date = new Date();
  	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");

        if(mgntno!=null && mgntno.length>0 && orderno!=null && orderno.length>0){
            for(int i=0; i<mgntno.length; i++){

         	   if(mgntno_change[i].equals(mgntno[i])){ //주문자 화면에서 수정된 목록만을 수정하기 위함
         		   if(statuscode[i].length() > 0){
         			   String mgntno2 = mgntno[i];
         			   String orderno2 = orderno[i];
         			   int point2 = Integer.parseInt(point[i]);
         			   String userids2 = userids[i];
         			   String delivers_cellno2 = delivers_cellno[i];
         			   String orders_mgntNo_count2 = orders_mgntNo_count[i];
         			   String statuscode2 = statuscode[i];
         			   String confirmdate2 = confirmdate[i]+" "+confirmdatelast[i];
         			   String isconfirm2 = "";

         			   int iscount = 0;
         			   int point_sum = 0;
         			   String s_isconfrimdate2 = "";
         			   String mylecture_end_date = "";
         			   String s_isconfirm = "";

         			   if(isconfirm[i] == null || isconfirm[i].length() == 0){
         				   isconfirm2 = simpleDate.format(date);
         			   } else {
         				   isconfirm2 = isconfirm[i];
         			   }

         			   searchMap.put("mgntno2", mgntno2);
         			   searchMap.put("orderno2", orderno2);
         			   searchMap.put("point2", point2);
         			   searchMap.put("userids2", userids2);
         			   searchMap.put("delivers_cellno2", delivers_cellno2);
         			   searchMap.put("orders_mgntNo_count2", orders_mgntNo_count2);
         			   searchMap.put("statuscode2", statuscode2);
         			   searchMap.put("confirmdate2", confirmdate2);
         			   searchMap.put("isconfirm2", isconfirm2);
         			   searchMap.put("userid", userid);

         			   searchMap.put("orders_userid", userids2);
         			   searchMap.put("point", point2);
         			   searchMap.put("orderno", orderno2);
         			   searchMap.put("mgntno", mgntno2);


          			  point_sum = productOrderService.getDepositStatusCount(searchMap);

          			   if("DLV140".equals(statuscode2) || "DLV150".equals(statuscode2) || "DLV160".equals(statuscode2) || "DLV170".equals(statuscode2) || "DLV180".equals(statuscode2) || "DLV220".equals(statuscode2) || "DLV230".equals(statuscode2) || "DLV240".equals(statuscode2)) {
 	            			  // 취소처리함. tb_order_mgnt_no의 iscancel = 1로 수정함.
 	            			  productOrderService.updateDepositStatus1(searchMap);

 	            			  if(!temp_orderno.equals(orderno2)){	// 여러개 강의를 포인트로 구매했을때 취소완료하면 포인트가 중복으로 들어가는것을 방지하기위해 추가됨.
	 	            				 
	 	            				 productOrderService.BookPointDel(searchMap); // 결제취소시 적립된 도서 포인트 회수
	 	            				 
	 	            				 if(point_sum > 0){
	 	 	            				   // 포인트가 있다면 사용자정보에 포인트 적립
	 	 	   	            			   productOrderService.updateDepositStatus2(searchMap);
	 	 	   	            			if(point2 > 0){
	 	 	   	            				productOrderService.insertMileageHistory(searchMap);
	 	 	   	            			}
	 	 	            			 }
	 	            			 temp_orderno = orderno2;
 	            			}
 	            		if(mgntno2.substring(0, 1).equals("Y")){
 	            			productOrderService.Delete_Year_Package_Point(searchMap);
 	            		}	
						
          			   }

         			   //도서 재고가 다시 입고되었을 경우 재고 더해줌 취소완료/교환완료/환불완료
         			  if("DLV150".equals(statuscode2) || "DLV180".equals(statuscode2) || "DLV230".equals(statuscode2)){
         				 productOrderService.updateBookStockPlus(searchMap);
         			  }

         			   if("0".equals(isconfirm2)) {
         				   s_isconfirm = "";
         			   }else{
         				   s_isconfirm = isconfirm2;
         			   }

	            		searchMap.put("s_isconfirm", s_isconfirm);

	            		if("DLV105".equals(statuscode2)) {
	            		
	            			productOrderService.BookPointIns(searchMap); // 입금완료시  도서 포인트 적립
	            			// 입금완료 상태로 변경시 tb_order_mgnt_no 업데이트
	            			productOrderService.updateDepositStatus3(searchMap);
	            			// tb_mylecture의 playyn = Y 로 변경함
	            			productOrderService.updateDepositStatus4(searchMap);

	            			//TB_MYLECTURE 테이블 START_DATE,END_DATE 수정 프로시저 작업 제외
	            		}

	            		if("DLV100".equals(statuscode2) || "DLV110".equals(statuscode2) || "DLV120".equals(statuscode2) || "DLV125".equals(statuscode2) || "DLV130".equals(statuscode2)) {
            			  // 입금상태를 입금확인중, 배송준비중, 배송중으로 변경시 tb_order_mgnt_no 업데이트
            			  productOrderService.updateDepositStatus5(searchMap);
	            		}

         		   }
         	   }
            }
        }

        output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		output.addAttribute("message", URLEncoder.encode("저장되었습니다.","UTF-8"));

		if("3".equals(payall)) {
			return "redirect:/productOrder/list_0.do";
		}else{
			return "redirect:/productOrder/list.do";
		}
	}

	//전체상품주문관리 주문번호 상세 수정하기 버튼 파라미터
	@RequestMapping(value="/insert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String insert(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String sendno = CommonUtil.isNull(req.getParameter("sendno"), "");
    	String d_name = CommonUtil.isNull(req.getParameter("d_name"), "");
    	String d_telno = CommonUtil.isNull(req.getParameter("d_telno"), "");
    	String d_cellno = CommonUtil.isNull(req.getParameter("d_cellno"), "");
    	String zipcd = CommonUtil.isNull(req.getParameter("zipcd"), "");
    	String juso = CommonUtil.isNull(req.getParameter("juso"), "");
    	String d_memo = CommonUtil.isNull(req.getParameter("d_memo"), "");
    	String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String payname = CommonUtil.isNull(req.getParameter("payname"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("sendno", sendno);
    	searchMap.put("d_name", d_name);
    	searchMap.put("d_telno", d_telno);
    	searchMap.put("d_cellno", d_cellno);
    	searchMap.put("zipcd", zipcd);
    	searchMap.put("juso", juso);
    	searchMap.put("d_memo", d_memo);
    	searchMap.put("orderno", orderno);
    	searchMap.put("payname", payname);

    	productOrderService.updateDelivers(searchMap);
    	productOrderService.updateApprovals(searchMap);

    	output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);
    	output.addAttribute("message", URLEncoder.encode("수정되었습니다.","UTF-8"));

		return "redirect:/productOrder/update.do";
	}

	//상품명 팝업
	@RequestMapping(value="/plyer_gostop.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String plyer_gostop(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List pmp_list = null;
    	List mobile_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String userid = CommonUtil.isNull(req.getParameter("userid"), "");
    	String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");
    	String searchLeccode = CommonUtil.isNull(req.getParameter("searchLeccode"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);
	    searchMap.put("orderno", orderno);
	    searchMap.put("package_no", package_no);
	    searchMap.put("searchLeccode", searchLeccode);

	    // 상품명 팝업 pmp 다운받은 내역 조회
	    pmp_list = productOrderService.getPmpdownloadListPop(searchMap);//
	    mobile_list = productOrderService.getMobiledownloadListPop(searchMap);//

	    //아이디 ,이름 조회
		list = productOrderService.getTblOrderList(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		if(list.size() > 0 && list != null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map = (HashMap) list.get(0);
			searchMap.put("USER_ID",map.get("USER_ID"));
			searchMap.put("USER_NM",map.get("USER_NM"));
//			searchMap.put("DanIntCount",map.get("DANINTCOUNT")); //단과할인 % 가져오기
		}

		//목록 리스트
		list = productOrderService.getOrderListPopDB(searchMap);

		output.addAttribute("pmp_list", pmp_list);
		output.addAttribute("mobile_list", mobile_list);
		output.addAttribute("list", list);
		output.addAttribute("listSize", list.size());
		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Order_Period_Pop";
	}

	//상품명 클릭 팝업 수정하기 버튼
	@RequestMapping(value="/play_gostop_in_update.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String play_gostop_in_update(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String [] lecture_no2 = req.getParameterValues("lecture_no2");
    	String [] mylecture_start_date = req.getParameterValues("mylecture_start_date");
    	String [] mylecture_period = req.getParameterValues("mylecture_period");
    	String [] mylecture_paused_date1 = req.getParameterValues("mylecture_paused_date1");
    	String [] mylecture_paused_period1 = req.getParameterValues("mylecture_paused_period1");
    	String [] mylecture_paused_date2 = req.getParameterValues("mylecture_paused_date2");
    	String [] mylecture_paused_period2 = req.getParameterValues("mylecture_paused_period2");
    	String [] mylecture_paused_date3 = req.getParameterValues("mylecture_paused_date3");
    	String [] mylecture_paused_period3 = req.getParameterValues("mylecture_paused_period3");

    	String user_id = CommonUtil.isNull(req.getParameter("userid"), "");
    	String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");
    	String lecture_no = CommonUtil.isNull(req.getParameter("lecture_no"), "");
    	String searchLeccode = CommonUtil.isNull(req.getParameter("searchLeccode"), "");
    	String Flag2 = CommonUtil.isNull(req.getParameter("Flag2"), "");

    	if(lecture_no2!=null && lecture_no2.length >0 ){
            for(int i=0; i<lecture_no2.length ; i ++){

            	int mylecture_paused_count = 0;
            	int mylecture_paused_period = 0;
            	int mylecture_paused_period1_int = 0;


            	 Map<String, Object> searchMap = new HashMap<String, Object>();
            	 searchMap.put("user_id", user_id);
    			 searchMap.put("orderno", orderno);
    			 searchMap.put("package_no", package_no);
    			 searchMap.put("lecture_no", lecture_no);
    			 searchMap.put("lecture_no2", lecture_no2[i]);

    			 searchMap.put("mylecture_start_date", mylecture_start_date[i]);
    			 searchMap.put("mylecture_period", mylecture_period[i]);
    			 searchMap.put("mylecture_paused_date1", mylecture_paused_date1[i]);

    			 if(mylecture_paused_period1[i].equals("") || mylecture_paused_period1[i].equals("0")){
    				 searchMap.put("mylecture_paused_period1", "0");
    				 mylecture_paused_period1_int = 0;
                 } else {
                	 searchMap.put("mylecture_paused_period1", mylecture_paused_period1[i]);
                	 mylecture_paused_period1_int = Integer.parseInt(mylecture_paused_period1[i]);
                 }

    			 searchMap.put("mylecture_paused_date2", mylecture_paused_date2[i]);

    			 searchMap.put("mylecture_paused_period2", mylecture_paused_period2[i]);
    			 searchMap.put("mylecture_paused_date3", mylecture_paused_date3[i]);
    			 searchMap.put("mylecture_paused_period3", mylecture_paused_period3[i]);

    			 list = productOrderService.getMylecture(searchMap);

				HashMap<String, Object> map = new HashMap<String, Object>();
				map = (HashMap) list.get(0);
				searchMap.put("PERIOD",map.get("PERIOD"));
				searchMap.put("START_DATE",map.get("START_DATE"));

				String PERIOD = String.valueOf(map.get("PERIOD"));
				//String START_DATE = (String) map.get("START_DATE").toString();

				/*if(!"".equals(PERIOD) || PERIOD != null){
					if(!"".equals(mylecture_paused_date1[i])){
						mylecture_paused_count = mylecture_paused_count + 1;
						mylecture_paused_period = mylecture_paused_period + mylecture_paused_period1_int;

						searchMap.put("mylecture_paused_count", mylecture_paused_count);
						searchMap.put("mylecture_paused_period", mylecture_paused_period);

						if(!"".equals(mylecture_paused_date2[i])){
    						mylecture_paused_count = mylecture_paused_count + 1;
    						mylecture_paused_period = mylecture_paused_period + Integer.parseInt(mylecture_paused_period2[i]);

    						searchMap.put("mylecture_paused_count", mylecture_paused_count);
    						searchMap.put("mylecture_paused_period", mylecture_paused_period);

    						if(!"".equals(mylecture_paused_date3[i])){
        						mylecture_paused_count = mylecture_paused_count + 1;
        						mylecture_paused_period = mylecture_paused_period + Integer.parseInt(mylecture_paused_period3[i]);

        						searchMap.put("mylecture_paused_count", mylecture_paused_count);
        						searchMap.put("mylecture_paused_period", mylecture_paused_period);
            				}
        				}
    				}
				}*/

				if(!"".equals(PERIOD) || PERIOD != null){
					if(!"0".equals(mylecture_paused_period1[i]) || !"".equals(mylecture_paused_period1[i])){
						mylecture_paused_count = mylecture_paused_count + 1;
						mylecture_paused_period = mylecture_paused_period + mylecture_paused_period1_int;

						searchMap.put("mylecture_paused_count", mylecture_paused_count);
						searchMap.put("mylecture_paused_period", mylecture_paused_period);

						if(!"0".equals(mylecture_paused_period2[i]) || !"".equals(mylecture_paused_period2[i])){
    						mylecture_paused_count = mylecture_paused_count + 1;
    						mylecture_paused_period = mylecture_paused_period + Integer.parseInt(mylecture_paused_period2[i]);

    						searchMap.put("mylecture_paused_count", mylecture_paused_count);
    						searchMap.put("mylecture_paused_period", mylecture_paused_period);

    						if(!"0".equals(mylecture_paused_period3[i]) || !"".equals(mylecture_paused_period3[i])){
        						mylecture_paused_count = mylecture_paused_count + 1;
        						mylecture_paused_period = mylecture_paused_period + Integer.parseInt(mylecture_paused_period3[i]);

        						searchMap.put("mylecture_paused_count", mylecture_paused_count);
        						searchMap.put("mylecture_paused_period", mylecture_paused_period);
            				}
        				}
    				}
				}

				if(mylecture_paused_period > 0){
					searchMap.put("mylecture_paused_period_sts", "1");
				}else{
					searchMap.put("mylecture_paused_period_sts", "0");
				}

				if(mylecture_paused_count < 3){
					searchMap.put("mylecture_paused_date3", "");
					searchMap.put("mylecture_paused_period3", "0");

					if(mylecture_paused_count < 2){
    					searchMap.put("mylecture_paused_date2", "");
    					searchMap.put("mylecture_paused_period2", "0");

    					if(mylecture_paused_count < 1){
        					searchMap.put("mylecture_paused_date1", "");
        					searchMap.put("mylecture_paused_period1", "0");
        				}
    				}
				}


				if(!"".equals(mylecture_period[i]) || mylecture_period[i] != null && !"".equals(mylecture_start_date[i]) || mylecture_start_date[i] != null){
					productOrderService.updateMylecture1(searchMap);
				}

				if(!"".equals(mylecture_period[i]) || mylecture_period[i] != null && "".equals(mylecture_start_date[i]) || mylecture_start_date[i] == null){
					productOrderService.updateMylecture2(searchMap);
				}

				if("".equals(mylecture_period[i]) || mylecture_period[i] == null && !"".equals(mylecture_start_date[i]) || mylecture_start_date[i] != null){
					productOrderService.updateMylecture3(searchMap);
				}

				if("".equals(mylecture_period[i]) || mylecture_period[i] == null && "".equals(mylecture_start_date[i]) || mylecture_start_date[i] == null){
					productOrderService.updateMylecture4(searchMap);
				}

				if(Flag2.equals("S")){
					int s_count = productOrderService.getMylectureCount(searchMap);

					if(s_count > 0){
						productOrderService.updateMylecture5(searchMap);
					}else{
						productOrderService.insertMylecture6(searchMap);
					}
//					productOrderService.updateEndDateMyLecture(searchMap);
				}
	    		productOrderService.updateEndDateMyLecture(searchMap);	//2015-01-20 강의종료일 = 수강일 + 중지기간
            }
    	}

    	output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);
    	output.addAttribute("userid", user_id);
    	output.addAttribute("package_no", package_no);
    	output.addAttribute("searchLeccode", searchLeccode);

		return "redirect:/productOrder/plyer_gostop.pop";
	}

	//주문번호 상세페이지 상품구분 셀렉트 박스 onchange
	@RequestMapping(value="/wmv_pmp_update.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String wmv_pmp_update(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		String[] wmv_check = req.getParameterValues("wmv_check");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);

		for (int i = 0; i < wmv_check.length; i++) {
			 if (wmv_check[i].length() > 0) {
				 String [] tmp = null;
				 tmp = wmv_check[i].split("#");

				 String wmv_pmp = tmp[0];
				 String ori_wmv_pmp = tmp[1];
				 orderno = tmp[2];
				 String mgntno = tmp[3];

				 searchMap.put("wmv_pmp", wmv_pmp);
				 searchMap.put("ori_wmv_pmp", ori_wmv_pmp);
				 searchMap.put("orderno", orderno);
				 searchMap.put("mgntno", mgntno);

				 productOrderService.updateDeliversWmv(searchMap);
			 }
		}

		output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

	   	output.addAttribute("orderno", orderno);

	   	output.addAttribute("message", URLEncoder.encode("변경되었습니다.","UTF-8"));

		return "redirect:/productOrder/update.do";
	}

    //주문번호 상세페이지 진도율수정 버튼
    @RequestMapping(value="/update_study_per.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String update_study_per(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {

        HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
        String study_percent = CommonUtil.isNull(req.getParameter("study_percent"), "");

        String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
        String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
        String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
        String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
        String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
        String payall = CommonUtil.isNull(req.getParameter("payall"), "");
        String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String realprice = CommonUtil.isNull(req.getParameter("realprice"), "");
        String iscancle = CommonUtil.isNull(req.getParameter("iscancle"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();

        searchMap.put("orderno", orderno);
        searchMap.put("mgntno", mgntno);
        searchMap.put("study_percent", study_percent);

        searchMap.put("orderstatuscode", orderstatuscode);
        searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
        searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
        searchMap.put("searchtype", searchtype);
        searchMap.put("paycode", paycode);
        searchMap.put("payall", payall);
        searchMap.put("sdate", sdate);
        searchMap.put("edate", edate);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);


        searchMap.put("realprice", realprice);
        searchMap.put("iscancle", iscancle);

        productOrderService.updateStudy_Per(searchMap);

        output.addAttribute("orderstatuscode", orderstatuscode);
        output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
        output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
        output.addAttribute("searchtype", searchtype);
        output.addAttribute("paycode", paycode);
        output.addAttribute("payall", payall);
        output.addAttribute("sdate", sdate);
        output.addAttribute("edate", edate);
        output.addAttribute("currentPage", currentPage);
        output.addAttribute("pageRow", pageRow);

        output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

        output.addAttribute("orderno", orderno);

        output.addAttribute("message", URLEncoder.encode("수정되었습니다.","UTF-8"));

        return "redirect:/productOrder/update.do";
    }

	//주문번호 상세페이지 금액수정 버튼 - 20130118 각각의 판매가 마다 금액수정을 만들기 위해 추가됨
	@RequestMapping(value="/submit_money.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String submit_money(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String realprice = CommonUtil.isNull(req.getParameter("realprice"), "");
    	String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String iscancle = CommonUtil.isNull(req.getParameter("iscancle"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);
	    searchMap.put("realprice", realprice);
	    searchMap.put("mgntno", mgntno);
	    searchMap.put("iscancle", iscancle);

	    productOrderService.updateMoney1(searchMap);

	    int totalPrice = productOrderService.getMoneySum(searchMap);

	    searchMap.put("totalPrice", totalPrice);

	    productOrderService.updateMoney2(searchMap);

	    output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);

    	output.addAttribute("message", URLEncoder.encode("수정되었습니다.","UTF-8"));

		return "redirect:/productOrder/update.do";
	}

	//구입금액
	@RequestMapping(value="/pop_refund.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_refund(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		menuParams.put("MANAGER_ID", CommonUtil.isNull(req.getParameter("MANAGER_ID"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(URLDecoder.decode(req.getParameter("search_date_type"),"UTF-8"), "");
    	String searchkey = CommonUtil.isNull(URLDecoder.decode(req.getParameter("searchkey"),"UTF-8"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int PlusPoint = 0;
		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String mode = CommonUtil.isNull(req.getParameter("mode"), "");
    	String orders_userid = CommonUtil.isNull(req.getParameter("orders_userid"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("mgntno", mgntno);
	    searchMap.put("orderno", orderno);
	    searchMap.put("orders_userid", orders_userid);
	    searchMap.put("mode", mode);
		
		if(mgntno.substring(0, 1).equals("L")){
			PlusPoint = productOrderService.getPlusPoint(searchMap);
		}
		
	    TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ch = sdf.format(d);

			searchMap.put("ch", ch);

	    list = productOrderService.getCourse_type_code(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();

		if(list.size() > 0 && list != null){
			map = (HashMap) list.get(0);
			searchMap.put("course_type_code",map.get("COURSE_TYPE_CODE"));
		}

		list = productOrderService.getPlayyn(searchMap);

		if(list.size() > 0 && list != null){
			map = (HashMap) list.get(0);
			searchMap.put("playyn",map.get("PLAYYN"));
			searchMap.put("MYLECTURE_PAUSED_COUNT",map.get("MYLECTURE_PAUSED_COUNT"));
			searchMap.put("STUDY_PERCENT",map.get("STUDY_PERCENT"));
			searchMap.put("DAY_PRICE",map.get("DAY_PRICE"));
			searchMap.put("START_DATE",map.get("START_DATE"));
		}

		list = productOrderService.getPoint(searchMap);

		if(list.size() > 0 && list != null){
			map = (HashMap) list.get(0);
			searchMap.put("point",map.get("POINT"));
		}

    	list = productOrderService.getTblOrderMgntNoPopViewList(searchMap);

    	Map<String, Object> searchMap2 = new HashMap<String, Object>();

    	if(list.size() > 0 && list != null){
	    	map = (HashMap) list.get(0);
	    	searchMap2 = new HashMap<String, Object>();
	    	searchMap2.put("orderNo",map.get("ORDERNO"));
	    	searchMap2.put("mgntNo",map.get("MGNTNO"));
	    	searchMap2.put("USER_NM",map.get("USER_NM"));
	    	searchMap2.put("USER_ID",map.get("USER_ID"));
	    	searchMap2.put("payName",map.get("PAYNAME"));
	    	searchMap2.put("point",map.get("POINT"));
	    	searchMap2.put("name",map.get("NAME"));
	    	searchMap2.put("cnt",map.get("CNT"));
	    	searchMap2.put("price",map.get("PRICE"));
			searchMap2.put("statusCode",map.get("STATUSCODE"));
			searchMap2.put("confirmDate",map.get("CONFIRMDATE"));
	    	searchMap2.put("cancelDate",map.get("CANCELDATE"));
	    	searchMap2.put("isconfirm",map.get("ISCONFIRM"));
			searchMap2.put("realprice",map.get("REALPRICE"));
			searchMap2.put("isCancel",map.get("ISCANCEL"));
	    	searchMap2.put("approvals_payCodeName",map.get("APPROVALS_PAYCODENAME"));
	    	searchMap2.put("statusName",map.get("STATUSNAME"));
	    	searchMap2.put("REG_DT",map.get("REG_DT"));
	    	searchMap2.put("TO_DT",map.get("TO_DT"));
    	}

    	int count = productOrderService.getTblOrderMgntNoPopViewCount(searchMap);

    	int price_Sum = productOrderService.getPrice_Sum(searchMap);

    	int cnt = productOrderService.getOldRefundViewCount(searchMap);

		output.addAttribute("list", list);
		output.addAttribute("count", count);
		output.addAttribute("price_Sum", price_Sum);
		output.addAttribute("searchMap2", searchMap2);
		output.addAttribute("PlusPoint", PlusPoint);
		
    	if(cnt > 0){

    		list = productOrderService.getRefund_Point(searchMap);

			if(log.isDebugEnabled()){
				log.debug("***** list : "+ list) ;
			}

			if(list.size() > 0 && list != null){
				map = (HashMap) list.get(0);
				searchMap.put("refund_point",map.get("REFUND_POINT"));
			}

			list = productOrderService.getOldRefundView(searchMap);

			Map<String, Object> searchMap3 = new HashMap<String, Object>();

			if(list.size() > 0 && list != null){
				map = (HashMap) list.get(0);
		    	searchMap3.put("oldRefund",map.get("OLDREFUND"));
		    	searchMap3.put("memo",map.get("MEMO"));
		    	searchMap3.put("confirmDate",map.get("CONFIRMDATE"));
			}

			searchMap.put("mode", "update");

			output.addAttribute("searchMap3", searchMap3);
			output.addAttribute("refund_view", list);
			output.addAttribute("searchMap", searchMap);
			
			return "productOrder/Tbl_Refund_Lst_Pop_modify"; //수정

    	} else {
    		searchMap.put("mode", "insert");
    		output.addAttribute("searchMap", searchMap);

    		return "productOrder/Tbl_Refund_Lst_Pop"; //등록
    	}
	}

	//구입금액 팝업 등록 버튼
	@RequestMapping(value="/pop_refund_insert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_refund_insert(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		menuParams.put("MANAGER_ID", CommonUtil.isNull(req.getParameter("MANAGER_ID"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String mode = CommonUtil.isNull(req.getParameter("mode"), "");
    	String price = CommonUtil.isNull(req.getParameter("price"), "0");
    	String memo = CommonUtil.isNull(req.getParameter("memo"), "");
    	String confirmdate = CommonUtil.isNull(req.getParameter("confirmdate"), "");
    	String playyn = CommonUtil.isNull(req.getParameter("playyn"), "Y");
    	String orders_userid = CommonUtil.isNull(req.getParameter("orders_userid"), "");
    	String point_gap = CommonUtil.isNull(req.getParameter("point_gap"), "");

    	String userid = CommonUtil.isNull(req.getParameter("MANAGER_ID"), "");

    	if (playyn.equals("")) {
    		playyn = "Y";
		}

	    String point = "";

	    if(mode.equals("delete")){
    		point = CommonUtil.isNull(req.getParameter("refund_point"), "0");
    	}else{
    		point = CommonUtil.isNull(req.getParameter("point"), "0");
    	}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("mgntno", mgntno);
	    searchMap.put("orderno", orderno);
	    searchMap.put("price", price);
	    searchMap.put("mode", mode);
	    searchMap.put("memo", memo);
	    searchMap.put("confirmdate", confirmdate);
	    searchMap.put("playyn", playyn);
	    searchMap.put("orders_userid", orders_userid);
	    searchMap.put("point", point);
	    searchMap.put("point_gap", point_gap);
	    searchMap.put("userid", userid);

	    if(orderno!=null && orderno.length() > 0 ){
	    	if(mode.equals("insert")){
	    		//if(Integer.parseInt(price) > 0 ){
		    		productOrderService.insertOrderMgntNo1(searchMap);
		    		productOrderService.updateOrderMgntNo2(searchMap);
		    	//}

		    	if(Integer.parseInt(point) > 0 ){
		    		//productOrderService.insertOrderMgntNo2(searchMap);
		    		productOrderService.updateApprovals2(searchMap);
		    		productOrderService.insertMileageHistory(searchMap);
		    		productOrderService.updateMaMember(searchMap);
		    	}

	    	}else if(mode.equals("update")){
	    		productOrderService.updateOrderMgntNo1(searchMap);

	    		if(Integer.parseInt(point) > 0 ){
	    			productOrderService.updateMileageHistory(searchMap);
	    			productOrderService.updateMaMember2(searchMap);
	    			productOrderService.updateApprovals3(searchMap);
	    		}

	    	}else if(mode.equals("delete")){
	    		productOrderService.deleteOrderMgntNo(searchMap);
	    		productOrderService.updateOrderMgntNo3(searchMap);

	    		if(Integer.parseInt(point) > 0 ){
	    			productOrderService.updateMaMember3(searchMap);
	    			productOrderService.updateApprovals4(searchMap);
	    			productOrderService.insertMileageHistory2(searchMap);
	    		}
	    	}

	    	if(playyn.equals("N")){ //'N'일때 체크일때
		    	productOrderService.updateMylecture_1(searchMap);
		    }else{
		    	productOrderService.updateMylecture_2(searchMap);
		    }

	    	if(mode.equals("delete")){
	    		productOrderService.updateMylecture_3(searchMap);
	    	}
	    }

		output.addAttribute("searchMap", searchMap);
	    output.addAttribute("message", "등록완료");

	    return "productOrder/Tbl_Refund_Lst_Pop";
	}

	//택배비 팝업
	@RequestMapping(value="/deliver_refund.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String deliver_refund(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(URLDecoder.decode(req.getParameter("search_date_type"),"UTF-8"), "");
    	String searchkey = CommonUtil.isNull(URLDecoder.decode(req.getParameter("searchkey"),"UTF-8"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);

		list = productOrderService.getTblDeliver_refund_list(searchMap);

	    if(list.size() > 0 && list != null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map = (HashMap) list.get(0);
			searchMap.put("addprice",map.get("ADDPRICE"));
	    }

		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_deliver_refund";
	}

	//택배비 팝업 환불버튼
	@RequestMapping(value="/deliver_refund_insert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String deliver_refund_insert(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String price = CommonUtil.isNull(req.getParameter("price"), "");
    	String confirmdate = CommonUtil.isNull(req.getParameter("confirmdate"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);
	    searchMap.put("price", price);
	    searchMap.put("confirmdate", confirmdate);

	    productOrderService.updateApprovals5(searchMap);

	    output.addAttribute("message", "등록완료");

	    return "productOrder/Tbl_deliver_refund";
	}

	//상품명 팝업
	@RequestMapping(value="/view.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String view(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("leccode", leccode);

		//list = productOrderService.getCbLecMstBean(searchMap); //강사정보 팝업 추후개발(쿼리있음)

	    output.addAttribute("list", list);
	    output.addAttribute("searchMap", searchMap);

	    return "productOrder/lecmst_viw_pop";
	}

	// 전체상품주문관리 주문번호 상세페이지 상태코드 삭제 버튼 - 20130122 주문내역에서 환불금액 삭제하기 위해 새로 추가함.
	@RequestMapping(value="/refund_money_delete.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String refund_money_delete(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String iscancle = CommonUtil.isNull(req.getParameter("iscancle"), "");
    	String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("mgntno", mgntno);
	    searchMap.put("iscancle", iscancle);
	    searchMap.put("orderno", orderno);

	    productOrderService.refund_money_delete(searchMap);

	    output.addAttribute("orderstatuscode", orderstatuscode);
	    output.addAttribute("search_date_type", URLEncoder.encode(search_date_type,"UTF-8"));
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("payall", payall);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);

    	output.addAttribute("message", URLEncoder.encode("삭제되었습니다.","UTF-8"));

		return "redirect:/productOrder/update.do";
	}

	// SMS보내기 팝업
	@RequestMapping(value="/add.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		//String[] userphone = req.getParameterValues("userphone"); //문자 받는 사람들의 전화 번호 [ck추가]
		String userphone = CommonUtil.isNull(req.getParameter("userphone"), "");
		String smsname = CommonUtil.isNull(req.getParameter("smsname"), "");
    	//String smsname = CommonUtil.isNull(URLDecoder.decode(req.getParameter("smsname"),"UTF-8"), "");// 문자 받는 사람들의 이름[ck추가]

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String username = sessionMap.get("USER_NM");
		String userid = sessionMap.get("USER_ID");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);
		searchMap.put("username", username);
		searchMap.put("userphone", userphone);
		searchMap.put("smsname", URLDecoder.decode(smsname,"UTF-8"));

		output.addAttribute("searchMap", searchMap);

		return "productOrder/SmsSend_Add";
	}

	// SMS보내기
	@RequestMapping(value="/insertSms.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String insertSms(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String phone_no = CommonUtil.isNull(req.getParameter("userphone"), ""); //받는 사람 전화번호
    	String[] userphone = phone_no.split("/");
    	String send_no = CommonUtil.isNull(req.getParameter("send_no"), ""); //보내는 사람 전화번호
    	String message = CommonUtil.isNull(req.getParameter("tran_msg"), ""); //메세지 내용
    	String is_mms = ""; //MMS 메세지 여부
    	
       	if (message.getBytes().length > 80) {
    		is_mms = "Y";
    	}else{
    		is_mms = "N";
    	}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("phone_no", phone_no);
		searchMap.put("userphone", userphone);
		searchMap.put("send_no", send_no);
		searchMap.put("message", message);
		searchMap.put("is_mms", is_mms);

		for(int i =0; i<userphone.length; i++){
			searchMap.put("phone", userphone[i].toString());
			if(userphone[i] != null && !"".equals(userphone[i]))
				productOrderService.insertSendMsgMultiSendUser(searchMap);
		}

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("message", "등록완료");

		return "productOrder/SmsSend_Add";
	}

	// 사용자 팝업
	@RequestMapping(value="/MemberView1.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String MemberView1(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List Tm_Class_list = null;
		List Off_Class_list = null;
    	List Cs_list = null;
    	List My_coupon_list = null;
    	List csccode_list = null;
    	List momo_list = null;
    	List<HashMap<String, String>> tm_list = null;
    	List<HashMap<String, String>> vocCodelist = null;
    	List<HashMap<String, String>> dutyCodelist = null;

    	HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USER_ID = sessionMap.get("USER_ID");  	// 관리자 아이디
		String USER_NM = sessionMap.get("USER_NM");		// 관리자 이름

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String userid = CommonUtil.isNull(req.getParameter("userid"), "");	// 회원 아이디
		String username = CommonUtil.isNull(req.getParameter("username"), "");  // 회원 이름

		String STS = CommonUtil.isNull(req.getParameter("STS"), "");
		String STS_TM = CommonUtil.isNull(req.getParameter("STS_TM"), "");
		String ccode_top = CommonUtil.isNull(req.getParameter("ccode_top"), "");
		String ccode = CommonUtil.isNull(req.getParameter("ccode"), "");
		String actionflag = CommonUtil.isNull(req.getParameter("actionflag"), "");
		String cscontent = CommonUtil.isNull(req.getParameter("cscontent"), "");


		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);

		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);
		searchMap.put("username", username);

		searchMap.put("STS", STS);
		searchMap.put("STS_TM", STS_TM);
		searchMap.put("ccode_top", ccode_top);
		searchMap.put("ccode", ccode);
		searchMap.put("actionflag", actionflag);
		searchMap.put("content", cscontent);


		//TM상담내용 등록처리
		if(STS_TM.equals("Y")){
			searchMap.put("DUTYCODE", CommonUtil.isNull(req.getParameter("TMBDUTYCODE"), ""));
			searchMap.put("VOCCODE", CommonUtil.isNull(req.getParameter("TMBVOCCODE"), ""));
			searchMap.put("TMBCONTENT", CommonUtil.isNull(req.getParameter("tmbcontent"), ""));
			productOrderService.insertTmBoard(searchMap);
		}
		// TM상담내용 목록 조회
		tm_list = productOrderService.getTmBoardList(searchMap);
		HashMap<String, String> params = new HashMap<String, String>();
		vocCodelist = productOrderService.getVOCCODEList(params);	// 상담상태(결과)
		dutyCodelist = productOrderService.getDUTYCODEList(params);	// 직급 카테고리(독서실 포함)

		//CS상담내용 등록처리
		if(STS.equals("Y")){
			productOrderService.insertBoardCs(searchMap);
		}

		//개인 상세 정보
		list = productOrderService.getTmMember_View(searchMap);


        //searchMap.put("SYS_CD", "CSBOARD_GUBUN"); //노량진 개발 SVN
        searchMap.put("CODE_VAL", "CSBOARD_GUBUN");

		//Cs상담내역 등록 셀렉트박스
		csccode_list = productOrderService.getCsccode(searchMap);



		//Cs상담내역 리스트
		Cs_list = productOrderService.Cs_board_list(searchMap);

		// Cs상담내역 리스트 총 건수
//		int Cs_pop_allCount = productOrderService.getCsBoardListCount(searchMap);

		//내쿠폰 리스트
		My_coupon_list = productOrderService.getTm_mycoupon_list_admin(searchMap);

		// 내쿠폰 리스트 총 건수
//		int mycoupon_listCount = productOrderService.getTm_mycoupon_listCount_admin(searchMap);


		//사용자 수강내역
		Tm_Class_list = productOrderService.Tm_Class_List(searchMap);
		//사용자 학원 수강내역
		Off_Class_list = productOrderService.Off_Class_List(searchMap);

		// 사용자 수강내역 총 건수
//		int Tm_list_allcount = productOrderService.getTmClassListCount(searchMap);



		//문자,쪽지 리스트
		momo_list = productOrderService.getMemoList(searchMap);

		// 문자,쪽지 리스트 총 건수
//		int momo_list_allcount = productOrderService.getMemoListCount(searchMap);



		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", list);
		output.addAttribute("tm_list", tm_list);
		output.addAttribute("vocCodelist", vocCodelist);	// 상담상태(결과)
		output.addAttribute("dutyCodelist", dutyCodelist);	// 직급 카테고리(독서실 포함)
		output.addAttribute("csccode_list", csccode_list);
		output.addAttribute("Cs_list", Cs_list);
//		output.addAttribute("Cs_pop_allCount", Cs_pop_allCount);
		output.addAttribute("My_coupon_list", My_coupon_list);
//		output.addAttribute("mycoupon_listCount", mycoupon_listCount);
		output.addAttribute("Tm_Class_list", Tm_Class_list);
		output.addAttribute("Off_Class_List", Off_Class_list);
//		output.addAttribute("Tm_list_allcount", Tm_list_allcount);
		output.addAttribute("momo_list", momo_list);
//		output.addAttribute("momo_list_allcount", momo_list_allcount);

		return "productOrder/Tm_MemberView1";
	}


	//for ajax
	// CS상담내용 셀렉트박스 리스트 가져오기
	@RequestMapping(value="/ccode_top.do")
	@ResponseBody
	public List ccode_top(ModelMap output, HttpServletRequest req) {
		List list = null;

		// 검색조건
		String ccode_top = CommonUtil.isNull(req.getParameter("ccode_top"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** CS상담내용 셀렉트박스 리스트 가져오기 파라미터 ***** ");
			log.debug("***** ccode_top : " + ccode_top);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
        //searchMap.put("SYS_CD", ccode_top); //노량진 개발 SVN
        searchMap.put("CODE_VAL", ccode_top);

		//CS상담내용 셀렉트박스 리스트 가져오기
		list = productOrderService.getCsccode(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		return list;
	}

	//for ajax
	// 사용자 팝업 메모 및 point 변경 수정
	@RequestMapping(value="/MemoModify1.do")
	@ResponseBody
	public List MemoModify1(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		// 검색조건
		String userid = CommonUtil.isNull(req.getParameter("userid"), "");
		String point = CommonUtil.isNull(req.getParameter("point"), "0");
		String memo = CommonUtil.isNull(URLDecoder.decode(req.getParameter("memo"),"UTF-8"), "");
		String comment1 = CommonUtil.isNull(URLDecoder.decode(req.getParameter("comment1"),"UTF-8"), "");
		int temp_point = 0;

		Map<String, Object> searchMap = new HashMap<String, Object>();
		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		searchMap.put("userid", userid);

		int before_point = productOrderService.getSelectBefore_Point(searchMap);
		String admin_id = sessionMap.get("USER_ID");

		if(comment1.equals("")|| comment1 == ""){
			comment1 = "관리자에 의한 수정 (" + before_point + " --> " + point +")";
		}else{
			comment1 = comment1 + " " + "(" +before_point + " --> " + point + ")";
		}


	    if(before_point > Integer.parseInt(point)){
	    	temp_point = (before_point - Integer.parseInt(point))*-1;
	    }else{
	    	temp_point = ( Integer.parseInt(point) - before_point );
	    }

		//int temp = 0;
	    //temp = before_point - Integer.parseInt(point);
	    //temp = temp * -1;
	    //before_point = String.valueOf(temp);

		searchMap.put("point", point);
		searchMap.put("temp_point", temp_point);
		searchMap.put("memo", memo);
		searchMap.put("admin_id", admin_id);
		searchMap.put("comment1", comment1);

		//사용자 팝업 메모 및 point 변경 수정
		productOrderService.updateMemo(searchMap);

		if(before_point != Integer.parseInt(point)){
			productOrderService.insertMileageHistory3(searchMap);
		}

		return list;
	}


	// 신규 쿠폰발행 팝업
	@RequestMapping(value="/TmCouponInsert.pop")
	//@ResponseBody   ajax사용사 사용되는것으로 여기는 사용하면 안됨.(페이지 못찾는 현상 발생)
	public String TmCouponIns(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		String userid = CommonUtil.isNull(req.getParameter("userid"), "");

    	HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USER_ID = sessionMap.get("USER_ID");
		String USER_NM = sessionMap.get("USER_NM");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String CCODE = null;
		HashMap<String, String> params = new  HashMap<String, String>();
		Calendar cal = Calendar.getInstance( );

		params.put("PREFIX", "C" + cal.get(Calendar.YEAR));

		List<HashMap<String, String>> getCcode = productOrderService.getCcode(params);

		if(getCcode.size() > 0)
			CCODE = params.get("PREFIX") + getCcode.get(0).get("CCODE");
		else
			CCODE = params.get("PREFIX") + "00001";

		params.put("CCODE", CCODE.replace(" ", ""));
		CCODE = CCODE.replace(" ", "");

    	String CNAME = CommonUtil.isNull(req.getParameter("CNAME"), "");
    	String CCONTENT = CommonUtil.isNull(req.getParameter("CCONTENT"), "");
    	String EXPDATES = CommonUtil.isNull(req.getParameter("EXPDATES"), "");
    	String EXPDATEE = CommonUtil.isNull(req.getParameter("EXPDATEE"), "");
    	String REGTYPE = CommonUtil.isNull(req.getParameter("REGTYPE"), "");
        String EXPDAY = CommonUtil.isNull(req.getParameter("EXPDAY"), "");
    	String TCLASS = CommonUtil.isNull(req.getParameter("TCLASS"), "");
    	String TCLASSCAT = CommonUtil.isNull(req.getParameter("TCLASSCAT"), "");
    	String REGPRICE = CommonUtil.isNull(req.getParameter("REGPRICE"), "");
        String PUB_COUPON_GUBUN = CommonUtil.isNull(req.getParameter("PUB_COUPON_GUBUN"), "");
        String ADD_FLAG = CommonUtil.isNull(req.getParameter("ADD_FLAG"), "");

		String msg = "";

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);

		searchMap.put("CCODE", CCODE);
		searchMap.put("CNAME", CNAME);
		searchMap.put("CCONTENT", CCONTENT);
		searchMap.put("REGTYPE", REGTYPE);
		searchMap.put("REGPRICE", REGPRICE);
		searchMap.put("EXPDATES", EXPDATES);
		searchMap.put("EXPDATEE", EXPDATEE);
		searchMap.put("EXPDAY", EXPDAY);
		searchMap.put("TCLASS", TCLASS);
		searchMap.put("TCLASSCAT", TCLASSCAT);
		searchMap.put("PUB_COUPON_GUBUN", PUB_COUPON_GUBUN);
		searchMap.put("ADD_FLAG", ADD_FLAG);

		productOrderService.insertTmCoupon(searchMap); //쿠폰정보를 넣기하기 위함

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("userid", userid);

		//return "productOrder/Tm_Coupon_Pop_Add";
		return "forward:/productOrder/TmCoupon.pop"+"?userid="+userid;
	}


	// 쿠폰발행 팝업
	@RequestMapping(value="/TmCoupon.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String TmCoupon(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

    	HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USER_ID = sessionMap.get("USER_ID");
		String USER_NM = sessionMap.get("USER_NM");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
    	String search_date_type = CommonUtil.isNull(req.getParameter("search_date_type"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
    	String payall = CommonUtil.isNull(req.getParameter("payall"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String userid = CommonUtil.isNull(req.getParameter("userid"), "");

		String STS = CommonUtil.isNull(req.getParameter("STS"), "");
		String ccode = CommonUtil.isNull(req.getParameter("code1"), "");
        String eday = CommonUtil.isNull(req.getParameter("Expday1"), "");
		String msg = "";

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);

		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("search_date_type", URLDecoder.decode(search_date_type,"UTF-8"));
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("paycode", paycode);
		searchMap.put("payall", payall);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);

		searchMap.put("ccode", ccode);
		searchMap.put("eday", eday);

		if(STS.equals("Y")){
			if(!userid.equals("")){

//				if(ccode.equals("C110531561") || ccode.equals("C110531562")){
					// 쿠폰발행 여부
					int count = productOrderService.getCouponCount(searchMap);
					if(count > 0){
						msg = "이미 쿠폰이 발행되었습니다.";
					}else{
						productOrderService.insertMyCoupon(searchMap);
						msg = "선택한 쿠폰이 정상적으로 사용자에게 발행되었습니다.";
					}
//				}else{
//					productOrderService.insertMyCoupon(searchMap);
//					msg = "정상적으로 사용자에게 쿠폰 발행 되었습니다.";
//				}

			}else{
				msg = "로그인후 쿠폰을 발행할 수 있습니다.";
			}
		}

		//쿠폰발행 팝업
		list = productOrderService.getTmCoupon(searchMap);

		// 쿠폰발행 팝업 총 건수
		int listCount = productOrderService.getTmCouponCount(searchMap);


		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", list);
		output.addAttribute("listCount", listCount);

		output.addAttribute("msg", msg);

		return "productOrder/Tm_Coupon_Pop";
	}

	// 포인트 사용내역 팝업
	@RequestMapping(value="/TmPointHistory.pop")
	public String TmPointHistory(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {

		List<HashMap<String, String>> list = null;
		List<HashMap<String, String>> memInfo = null;
		String userid = CommonUtil.isNull(req.getParameter("userid"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("userid", userid);
		//수강생 정보
		memInfo = productOrderService.getTmMember_View(searchMap);


		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		//포인트 사용 내역
		list = productOrderService.getTmPointHistory(params);

		output.addAttribute("meminfo", memInfo);
		output.addAttribute("list", list);

		return "productOrder/Tm_PointHistory_Pop";
	}






	// off 리스트
	@RequestMapping(value="/off_list.do")
	public String off_list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;
		List order_list = null;
		List payment_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String message = CommonUtil.isNull(req.getParameter("message"), "");

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
        String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String ordertype = CommonUtil.isNull(req.getParameter("ordertype"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String orderpaycode = CommonUtil.isNull(req.getParameter("orderpaycode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");
    	
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		//searchMap.put("message", message);
		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
		searchMap.put("cmd", cmd);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);

		searchMap.put("ordertype", ordertype);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("orderpaycode", orderpaycode);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));
		
		searchMap.put("s_cat_cd", s_cat_cd);
		
		//장바구니 테이블 삭제하기
		if(!USER_ID.equals("")){
			productOrderService.deleteAllCart(searchMap);
		}

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -3);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);

			sdate = Delday6;
		}

		if(edate.equals("")){
			String ch1 = null;
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);

			edate = ch1;
		}

		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("flag", "");
		
		//카테고리 셀렉트박스 리스트
		List category_list = productOrderService.getCaCatCdList(searchMap);
		output.addAttribute("category_list", category_list);
		
		//리스트
		list = productOrderService.getOffOrderListDB(searchMap);
		// 총 건수
		int listCount = productOrderService.getOffOrderListCount(searchMap);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "productOrder/Tbl_Off_Order_Lst";
	}




	//off 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/tbl_off_order_list_excel.do")
	public View tbl_off_order_list_excel(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
	 	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
	 	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
	 	String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
	 	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
	    String edate = CommonUtil.isNull(req.getParameter("edate"), "");
	    String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -1);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyy-MM-dd").format(date6);

			sdate = Delday6;
		}

		if(edate.equals("")){
			String ch1 = null;
     	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			ch1 = sdf1.format(d1);

			edate = ch1;
		}

		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("orderstatuscode",orderstatuscode);
		params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
		params.put("searchtype",searchtype);
		params.put("paycode",paycode);
		params.put("sdate",sdate);
		params.put("edate",edate);
		params.put("s_cat_cd",s_cat_cd);

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = productOrderService.getOffOrderExcelListDB(params);

		if(log.isDebugEnabled()){
			log.debug("***** exe_list : "+ exe_list) ;
			log.debug("***** exe_list.size() : "+ exe_list.size()) ;
		}

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "강의신청리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("신청구분");
		headerList.add("성명(전화)");
		headerList.add("직종명");
		headerList.add("등록일");

		headerList.add("강의명");
		headerList.add("결제금액");
		headerList.add("총합");
		headerList.add("결제상태");
		headerList.add("결제종류");

		headerList.add("환불은행");
		headerList.add("환불계좌");
		headerList.add("예금주");

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(map.get("RNUM")));
			newMap.put(i++, map.get("ORDERNO").toString());
			newMap.put(i++, map.get("ORDER_TYPE").toString());
			newMap.put(i++, map.get("ORDERS_USERNAME").toString());
			newMap.put(i++, map.get("CATEGORY_NM").toString());
			newMap.put(i++, map.get("ORDERS_REGDATE").toString());

			params.put("orderno",map.get("ORDERNO").toString());

			if(log.isDebugEnabled()){
				log.debug("***** ORDERNO : "+ map.get("ORDERNO").toString()) ;
			}

			//2번째 리스트
			list_second = productOrderService.getOffTblOrderMgntListDB(params);

			if(log.isDebugEnabled()){
				log.debug("***** list_second : "+ list_second) ;
				log.debug("***** list_second.size() : "+ list_second.size()) ;
			}

			String name = "";
			String courier = "";
			String PRICE = "";
			String statuscode = "";

			int tot_sum = 0;
			int ori_price = 0;

			String rePriceNullChk = "";
			int rePrice = 0;
			if(map.get("APPROVALS_REPRICE") == null ) {
				rePriceNullChk = "null";
				rePrice = 0;
			} else {
				rePriceNullChk = "";
				rePrice = Integer.parseInt(String.valueOf(map.get("APPROVALS_REPRICE")));
			}

			if (list_second.size() > 0) {
				for (int k = 0; k < list_second.size(); k++) {
					HashMap map2 = (HashMap) list_second.get(k);

					//강의명
					int index = k+1;
					String name2 = map2.get("NAME").toString();
					name2 = index + "." +name2;

					if(k == 0){
						name = name2;
					}else{
						name = name + " , " + name2;
					}

					//결제금액
					if(k == 0){
						PRICE = String.valueOf(map2.get("PRICE"));
					}else{
						PRICE = PRICE + " , " + String.valueOf(map2.get("PRICE"));
					}

					//총합
					ori_price = Integer.parseInt(String.valueOf(map2.get("PRICE")));
					String STATUSCODE = map2.get("STATUSCODE").toString();

					if (STATUSCODE.equals("DLV150") || STATUSCODE.equals("DLV230")) {
						ori_price = 0;
					}

					tot_sum = tot_sum + ori_price;

					int total = tot_sum + Integer.parseInt(String.valueOf(map.get("APPROVALS_ADDPRICE"))) + rePrice;
					courier = Integer.toString(total);

					//결제상태

					if(k == 0){
						if (map2.get("STATUSCODENM") != null ) {
							statuscode = map2.get("STATUSCODENM").toString();
						}
					}else{
						if (map2.get("STATUSCODENM") != null ) {
							statuscode = statuscode + " , " + map2.get("STATUSCODENM").toString();
						}
					}

				}
			}

			newMap.put(i++, name);
			newMap.put(i++, PRICE);
			newMap.put(i++, courier);
			newMap.put(i++, statuscode);

			if (map.get("PRICE_STS") != null) {
				newMap.put(i++, map.get("PRICE_STS").toString());
			} else {
				newMap.put(i++, " ");
			}
			if (map.get("ACC_BANK_NAME") != null) {
				newMap.put(i++, map.get("ACC_BANK_NAME").toString());
			} else {
				newMap.put(i++, " ");
			}
			if (map.get("ACC_BANK_NUM") != null) {
				newMap.put(i++, map.get("ACC_BANK_NUM").toString());
			} else {
				newMap.put(i++, " ");
			}
			if (map.get("ACC_BANK_DEPOSITOR") != null) {
				newMap.put(i++, map.get("ACC_BANK_DEPOSITOR").toString());
			} else {
				newMap.put(i++, " ");
			}

			newList.add(newMap);
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}


	/**
	 * @Method Name : offererPrint
	 * @작성일 : 2013. 09.
	 * @Method 설명 : 신청자관리 응시표출력 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/offererPrint.pop")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String offererPrint(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String PRINT_STS = CommonUtil.isNull(req.getParameter("PRINT_STS"), "");
		String MGNTNO = CommonUtil.isNull(req.getParameter("MGNTNO"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 수강증 출력 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
			log.debug("***** PRINT_STS : " + PRINT_STS);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("MGNTNO", MGNTNO);
		searchMap.put("PRINT_STS", PRINT_STS);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		list = productOrderService.getPrintPop(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("CNT",map.get("CNT"));

		//장바구니 리스트
		list = productOrderService.getSubCodeUp(searchMap);

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", list);

		return "productOrder/offererPrint";
	}

	// for ajax
	// 수강증 출력
	@RequestMapping(value="/updatePrint.do")
	@ResponseBody
	public List updatePrint(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 수강증 출력 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("ORDERNO", ORDERNO);

		//수강증출력
		productOrderService.updatePrintOffOrders1(searchMap);

		/* 주문정보 히스토리 */
		searchMap.put("REG_ID", USERID);
		searchMap.put("PRICE", "0");
		searchMap.put("ACT_TYPE", "T");
		productOrderService.insertOffOrderLog(searchMap);
		/* 주문정보 히스토리 */
		
		return list;
	}

	// for ajax
	// 수강증 출력 복원
	@RequestMapping(value="/updateRePrint.do")
	@ResponseBody
	public List updateRePrint(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 수강증 출력 복원 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("ORDERNO", ORDERNO);

		//수강증 출력 복원
		productOrderService.updatePrintOffOrders2(searchMap);

		return list;
	}

	// 수강신청 등록버튼
	@RequestMapping(value="/createPage.do")
	public String createPage(ModelMap output, HttpServletRequest req) {
		List list = null;
		List registration_list = null;

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		output.addAttribute("sts", "I");
		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Off_Order_Ins";
	}


	//for ajax
	//id체크
	@RequestMapping(value="/idChk.do")
	@ResponseBody
	public List idChk(ModelMap output, HttpServletRequest req) {
		List list = null;

		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** id체크 파라미터 ***** ");
			log.debug("***** USER_ID : " + USER_ID);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);

		//id체크
		list = productOrderService.getIdChk(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		return list;
	}


	//품목 선택 팝업
	@RequestMapping(value="/pop_subject_add.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_subject_add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List category_list = null;
    	List lec_list = null;
    	List subject_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");

		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");
		String s_sjt_cd = CommonUtil.isNull(req.getParameter("s_sjt_cd"), "");
		String s_menu_id = CommonUtil.isNull(req.getParameter("s_menu_id"), "");
		String search_type = CommonUtil.isNull(req.getParameter("search_type"), "");
		String search_keyword = CommonUtil.isNull(req.getParameter("search_keyword"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), "20"));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("p_currentPage", currentPage);
		searchMap.put("p_pageRow", pageRow);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("USER_ID", USER_ID);

		searchMap.put("s_cat_cd", s_cat_cd);
		searchMap.put("s_sjt_cd", s_sjt_cd);
		searchMap.put("s_menu_id", s_menu_id);
		searchMap.put("search_type", search_type);
		searchMap.put("search_keyword", search_keyword);

		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		//카테고리 셀렉트박스 리스트
		category_list = productOrderService.getCaCatCdList(searchMap);

		//학습형태 셀렉트박스 리스트
//		lec_list = productOrderService.getVwMenuMstTree_lec(searchMap);

		//과목 셀렉트박스 리스트
		subject_list = productOrderService.getCaSubjectCdList(searchMap);

		//강의선택 팝업  리스트
		list = productOrderService.getCbLecMstFreeOrderList(searchMap);

		//강의선택 팝업  카운트
		int listCount = productOrderService.getCbLecMstListFreeOrderCount(searchMap);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("category_list", category_list);
		output.addAttribute("lec_list", lec_list);
		output.addAttribute("subject_list", subject_list);
		output.addAttribute("list", list);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "productOrder/Tbl_FreeOrder_subejct_pop";
	}

	//품목변경 팝업
	@RequestMapping(value="/pop_subject_mod.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_subject_mod(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List category_list = null;
    	List lec_list = null;
    	List subject_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");
		String s_sjt_cd = CommonUtil.isNull(req.getParameter("s_sjt_cd"), "");
		String s_menu_id = CommonUtil.isNull(req.getParameter("s_menu_id"), "");
		String search_type = CommonUtil.isNull(req.getParameter("search_type"), "");
		String search_keyword = CommonUtil.isNull(req.getParameter("search_keyword"), "");

		String Num = CommonUtil.isNull(req.getParameter("Num"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("USER_ID", USER_ID);

		searchMap.put("s_cat_cd", s_cat_cd);
		searchMap.put("s_sjt_cd", s_sjt_cd);
		searchMap.put("s_menu_id", s_menu_id);
		searchMap.put("search_type", search_type);
		searchMap.put("search_keyword", search_keyword);
		searchMap.put("Num", Num);

		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		//카테고리 셀렉트박스 리스트
		category_list = productOrderService.getCaCatCdList(searchMap);

		//학습형태 셀렉트박스 리스트
		lec_list = productOrderService.getVwMenuMstTree_lec(searchMap);

		//과목 셀렉트박스 리스트
		subject_list = productOrderService.getCaSubjectCdList(searchMap);

		//강의선택 팝업  리스트
		list = productOrderService.getCbLecMstFreeOrderList(searchMap);

		//강의선택 팝업  카운트
		int listCount = productOrderService.getCbLecMstListFreeOrderCount(searchMap);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("category_list", category_list);
		output.addAttribute("lec_list", lec_list);
		output.addAttribute("subject_list", subject_list);
		output.addAttribute("list", list);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "productOrder/Tbl_Order_subejct_mod_pop";
	}

	//for ajax
	// 상세 품목리스트 가져오기
	@RequestMapping(value="/subCodeUp.do")
	@ResponseBody
	public List subCodeUp(ModelMap output, HttpServletRequest req) {
		List sub_list = null;
		List list = null;

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String MGNTNO = CommonUtil.isNull(req.getParameter("MGNTNO"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 상세품목 리스트 가져오기 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("MGNTNO", MGNTNO);

		//장바구니 리스트
		sub_list = productOrderService.getSubCodeUp(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** sub_list : "+ sub_list) ;
		}

		return sub_list;
	}

	//for ajax
	// 장바구니 등록 및 리스트 가져오기
	@RequestMapping(value="/subCode.do")
	@ResponseBody
	public List subCode(ModelMap output, HttpServletRequest req) {
		List sub_list = null;
		List list = null;

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 장바구니 등록 및 리스트 가져오기 파라미터 ***** ");
			log.debug("***** USER_ID : " + USER_ID);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);

		//장바구니 리스트
		sub_list = productOrderService.getSubCode2(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** sub_list : "+ sub_list) ;
		}

		return sub_list;
	}

	//for ajax
	// 장바구니 등록 및 리스트 가져오기
	@RequestMapping(value="/subCode2.do")
	@ResponseBody
	public List subCode2(ModelMap output, HttpServletRequest req) {
		List sub_list = null;
		List list = null;

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String LECCODE = CommonUtil.isNull(req.getParameter("LECCODE"), "");
		String KIND_TYPE = CommonUtil.isNull(req.getParameter("KIND_TYPE"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 장바구니 등록 및 리스트 가져오기 파라미터 ***** ");
			log.debug("***** USER_ID : " + USER_ID);
			log.debug("***** LECCODE : " + LECCODE);
			log.debug("***** KIND_TYPE : " + KIND_TYPE);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("LECCODE", LECCODE);
		searchMap.put("KIND_TYPE", KIND_TYPE);

		// id 없을시 자동생성
		if (USER_ID.equals("U")) {
			list = productOrderService.getUCount(searchMap);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map = (HashMap) list.get(0);

			USER_ID = map.get("USER_ID").toString();
			searchMap.put("USER_ID", USER_ID);
		}

		//장바구니 등록
		productOrderService.insertCart(searchMap);

		//장바구니 리스트
		sub_list = productOrderService.getSubCode2(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** sub_list : "+ sub_list) ;
		}

		return sub_list;
	}


	// for ajax
	// 장바구니 삭제
	@RequestMapping(value="/deleteCart.do")
	@ResponseBody
	public List deleteCart(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String LECCODE = CommonUtil.isNull(req.getParameter("LECCODE"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 장바구니 삭제 파라미터 ***** ");
			log.debug("***** USER_ID : " + USER_ID);
			log.debug("***** LECCODE : " + LECCODE);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("LECCODE", LECCODE);

		//삭제
		productOrderService.deleteCart(searchMap);

		//장바구니 리스트
		list = productOrderService.getSubCode2(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		return list;
	}

	// for ajax
	// 장바구니 비우기
	@RequestMapping(value="/deleteAllCart.do")
	@ResponseBody
	public List deleteAllCart(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		if (log.isDebugEnabled()) {
			log.debug("***** 장바구니 비우기 파라미터 ***** ");
			log.debug("***** USER_ID : " + USER_ID);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);

		//삭제
		productOrderService.deleteAllCart(searchMap);

		//장바구니 리스트
		list = productOrderService.getSubCode2(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		return list;
	}

	//변경 팝업
	@RequestMapping(value="/pop_price_change.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_price_change(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List category_list = null;
    	List lec_list = null;
    	List subject_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String LECCODE = CommonUtil.isNull(req.getParameter("LECCODE"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("LECCODE", LECCODE);

		//변경 팝업  리스트
		list = productOrderService.getChangePrice(searchMap);

		if(log.isDebugEnabled()){
			log.debug("***** list : "+ list) ;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);

		searchMap.put("LECCODE", map.get("LECCODE"));
		searchMap.put("SUBJECT_REAL_PRICE", map.get("SUBJECT_REAL_PRICE"));
		searchMap.put("LEC_SCHEDULE", map.get("LEC_SCHEDULE"));
		searchMap.put("DAY_PRICE", map.get("DAY_PRICE"));
		searchMap.put("USE_SCHEDULE", map.get("USE_SCHEDULE"));
		searchMap.put("PRICE", map.get("PRICE"));

		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_price_pop";
	}

	// 수강신청 등록
	@RequestMapping(value="/registrationInsert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String registrationInsert(ModelMap output, HttpServletRequest req) {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String USER_NM = CommonUtil.isNull(req.getParameter("USER_NM"), "");
		String PHONE_NO = CommonUtil.isNull(req.getParameter("PHONE_NO"), "");
		String EMAIL = CommonUtil.isNull(req.getParameter("EMAIL"), "");
		String PRICE_DISCOUNT_TYPE = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_TYPE"), "");
		String PRICE_DISCOUNT = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT"), "0");
		String PRICE_DISCOUNT_REASON = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_REASON"), "");
		String PRICE = CommonUtil.isNull(req.getParameter("PRICE"), "0");
		String PRICE_CARD = CommonUtil.isNull(req.getParameter("PRICE_CARD"), "0");
		String CARD_NAME = CommonUtil.isNull(req.getParameter("CARD_NAME"), "");
		String PRICE_CASH = CommonUtil.isNull(req.getParameter("PRICE_CASH"), "0");
		String PRICE_BANK = CommonUtil.isNull(req.getParameter("PRICE_BANK"), "0");
		String PRICE_UNPAID = CommonUtil.isNull(req.getParameter("PRICE_UNPAID"), "0");
		String DUE_DT = CommonUtil.isNull(req.getParameter("DUE_DT"), "");
		String MEMO = CommonUtil.isNull(req.getParameter("MEMO"), "");
		String REG_DT = CommonUtil.isNull(req.getParameter("REG_DT"), "");

		String PAYCODE = "";

		if (Long.parseLong(PRICE_CARD) > 0) {  //카드
			PAYCODE = "PAY110";
		}else if (Long.parseLong(PRICE_BANK) > 0) {  //예금 - 윌비스통장 직접 입금
			PAYCODE = "PAY100";
		}else if (Long.parseLong(PRICE_CASH) > 0) { //현금
			PAYCODE = "PAY140";
		}else{  //전액할인 - 무료수강
			PAYCODE = "PAY160";
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);
		searchMap.put("PHONE_NO", PHONE_NO);
		searchMap.put("EMAIL", EMAIL);
		searchMap.put("PRICE_DISCOUNT_TYPE", PRICE_DISCOUNT_TYPE);
		searchMap.put("PRICE_DISCOUNT", PRICE_DISCOUNT);
		searchMap.put("PRICE_DISCOUNT_REASON", PRICE_DISCOUNT_REASON);
		searchMap.put("PRICE", PRICE);
		searchMap.put("PRICE_CARD", PRICE_CARD);
		searchMap.put("CARD_NAME", CARD_NAME);
		searchMap.put("PRICE_CASH", PRICE_CASH);
		searchMap.put("PRICE_BANK", PRICE_BANK);
		searchMap.put("PRICE_UNPAID", PRICE_UNPAID);
		searchMap.put("DUE_DT", DUE_DT);
		searchMap.put("MEMO", MEMO);
		searchMap.put("REG_DT", REG_DT);
		searchMap.put("USERID", USERID);
		searchMap.put("PAYCODE", PAYCODE);

		//주문번호 생성
		list = productOrderService.getMCount(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);

		String ORDERNO = map.get("ORDERNO").toString();
		searchMap.put("ORDERNO", ORDERNO);

		productOrderService.insertOffOrders(searchMap);
		productOrderService.insertOffApprovals(searchMap);

		//품목 등록
		String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];
					 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

					 searchMap.put("MGNTNO", LECCODE2);
					 searchMap.put("SUBJECT_REAL_PRICE", SUBJECT_REAL_PRICE2);

					 productOrderService.insertOffOrderMgntNo(searchMap);
					 productOrderService.insertOffMylecture(searchMap);
				 }
			}
		}
		
		/* 주문정보 히스토리 */
		searchMap.put("REG_ID", USERID);
		searchMap.put("ACT_TYPE", "C");
		productOrderService.insertOffOrderLog(searchMap);
		/* 주문정보 히스토리 */

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_list.do";
	}


	// 수강신청 수정 상세 -- 2014.09.20
	@RequestMapping(value="/updatePage.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String updatePage(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List registration_list = null;
		List sub_list = null;

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");
		String MGNTNO = CommonUtil.isNull(req.getParameter("MGNTNO"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);
		searchMap.put("MGNTNO", MGNTNO);

		//상세 조회
		list = productOrderService.getUpdateDetail(searchMap);

		//장바구니 리스트
		sub_list = productOrderService.getSubList(searchMap);
		int sub_size = sub_list.size();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("ORDERNO",map.get("ORDERNO"));
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("PHONE_NO",map.get("PHONE_NO"));
		searchMap.put("EMAIL",map.get("EMAIL"));
		searchMap.put("PRICE_DISCOUNT_TYPE",map.get("PRICE_DISCOUNT_TYPE"));
		searchMap.put("PRICE_DISCOUNT",map.get("PRICE_DISCOUNT"));
		searchMap.put("PRICE_DISCOUNT_REASON",map.get("PRICE_DISCOUNT_REASON"));
		searchMap.put("PRICE",map.get("PRICE"));
		searchMap.put("PRICE_CARD",map.get("PRICE_CARD"));
		searchMap.put("PRICE_CASH",map.get("PRICE_CASH"));
		searchMap.put("PRICE_BANK",map.get("PRICE_BANK"));
		searchMap.put("PRICE_UNPAID",map.get("PRICE_UNPAID"));
		searchMap.put("CARD_NAME",map.get("CARD_NAME"));
		searchMap.put("MEMO",map.get("MEMO"));
		searchMap.put("DUE_DT",map.get("DUE_DT"));
		searchMap.put("REG_DT",map.get("REG_DT"));
		searchMap.put("ISCONFIRM",map.get("ISCONFIRM"));
		searchMap.put("TICKET_PRINT_DT",map.get("TICKET_PRINT_DT"));
		searchMap.put("REFUND_PAY",map.get("REFUND_PAY"));
		searchMap.put("REFUND_CARD",map.get("REFUND_CARD"));
		searchMap.put("REFUND_CASH",map.get("REFUND_CASH"));
		searchMap.put("ACC_BANK_NAME",map.get("ACC_BANK_NAME"));
		searchMap.put("ACC_BANK_NUM",map.get("ACC_BANK_NUM"));
		searchMap.put("ACC_BANK_DEPOSITOR",map.get("ACC_BANK_DEPOSITOR"));
		searchMap.put("REFUND_MEMO",map.get("REFUND_MEMO"));
		searchMap.put("SET_DATE",map.get("SET_DATE"));

		output.addAttribute("sts", "U");
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("sub_list", sub_list);
		output.addAttribute("sub_size", sub_size);

		return "productOrder/Tbl_Off_Order_Mod";
	}

	// 수강신청 수정
	@RequestMapping(value="/orderUpdate.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String orderUpdate(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String USER_NM = CommonUtil.isNull(req.getParameter("USER_NM"), "");
		String PHONE_NO = CommonUtil.isNull(req.getParameter("PHONE_NO"), "");
		String EMAIL = CommonUtil.isNull(req.getParameter("EMAIL"), "");
		String PRICE_DISCOUNT_TYPE = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_TYPE"), "");
		String PRICE_DISCOUNT = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT"), "0");
		String PRICE_DISCOUNT_REASON = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_REASON"), "");
		String PRICE = CommonUtil.isNull(req.getParameter("PRICE"), "0");
		String PRICE_CARD = CommonUtil.isNull(req.getParameter("PRICE_CARD"), "0");
		String CARD_NAME = CommonUtil.isNull(req.getParameter("CARD_NAME"), "");
		String PRICE_CASH = CommonUtil.isNull(req.getParameter("PRICE_CASH"), "0");
		String PRICE_BANK = CommonUtil.isNull(req.getParameter("PRICE_BANK"), "0");
		String PRICE_UNPAID = CommonUtil.isNull(req.getParameter("PRICE_UNPAID"), "0");
		String DUE_DT = CommonUtil.isNull(req.getParameter("DUE_DT"), "");
		String MEMO = CommonUtil.isNull(req.getParameter("MEMO"), "");
		String REG_DT = CommonUtil.isNull(req.getParameter("REG_DT"), "");
		String ISCONFIRM = CommonUtil.isNull(req.getParameter("ISCONFIRM"), "");
		String TYPE = CommonUtil.isNull(req.getParameter("TYPE"), "");

		String PAYCODE = "";

		if (Long.parseLong(PRICE_CARD) > 0) {  //카드
			PAYCODE = "PAY110";
		}else if (Long.parseLong(PRICE_BANK) > 0) {  //예금 - 윌비스통장 직접 입금
			PAYCODE = "PAY100";
		}else if (Long.parseLong(PRICE_CASH) > 0) { //현금
			PAYCODE = "PAY140";
		}else{  //전액할인 - 무료수강
			PAYCODE = "PAY160";
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);
		searchMap.put("PHONE_NO", PHONE_NO);
		searchMap.put("EMAIL", EMAIL);
		searchMap.put("PRICE_DISCOUNT_TYPE", PRICE_DISCOUNT_TYPE);
		searchMap.put("PRICE_DISCOUNT", PRICE_DISCOUNT);
		searchMap.put("PRICE_DISCOUNT_REASON", PRICE_DISCOUNT_REASON);
		searchMap.put("PRICE", PRICE);
		searchMap.put("PRICE_CARD", PRICE_CARD);
		searchMap.put("CARD_NAME", CARD_NAME);
		searchMap.put("PRICE_CASH", PRICE_CASH);
		searchMap.put("PRICE_BANK", PRICE_BANK);
		searchMap.put("PRICE_UNPAID", PRICE_UNPAID);
		searchMap.put("DUE_DT", DUE_DT);
		searchMap.put("MEMO", MEMO);
		searchMap.put("REG_DT", REG_DT);
		searchMap.put("ISCONFIRM", ISCONFIRM);
		searchMap.put("USERID", USERID);
		searchMap.put("PAYCODE", PAYCODE);
		searchMap.put("ORDERNO", ORDERNO);


		productOrderService.updateOffOrders(searchMap);

		Map<String, String> searchMap1 = new HashMap<String, String>();
		searchMap1.put("ORDERNO", ORDERNO);

		// 결제정보를 업데이트하기 전에 기존에 생성된 정보가 있는지 확인한다.
		List<HashMap<String, String>> offApproval = productOrderService.getOffApprovalsCount(searchMap1);
			// 결제 정보가 있으면 업데이트한다.
		if (offApproval.size() > 0) {
			// 결제 정보가 있으면 업데이트한다.
			productOrderService.updateOffApprovals(searchMap);
		} else {
			// 결제정보가 없으면 생성한다.
			productOrderService.insertOffApprovals(searchMap);
		}

		//품목 등록
		String[] PRD = req.getParameterValues("PRD");
		String ORG_LECCODE = "";
		String NEW_LECCODE = "";
		String PKG_LECCODE = "";
		String REAL_PRICE = "";

		 if (PRD != null) {
			for (int i = 0; i < PRD.length; i++) {
				ORG_LECCODE = req.getParameter("ORG_LECTURE_NO_"+PRD[i]);
				NEW_LECCODE = req.getParameter("MGNTNO_"+PRD[i]);
				PKG_LECCODE = req.getParameter("ORG_PACKGE_NO_"+PRD[i]);
				REAL_PRICE = req.getParameter("REALPRICE_"+PRD[i]);

				 if ("M".equals(req.getParameter("O_TYPE_"+PRD[i]))) { //변경 과목이 단과 및 종합반 대표 과목일 경우

					 if ("D".equals(req.getParameter("L_TYPE_"+PRD[i]))) { //변경 과목이 단과반일 경우
						 searchMap.put("MGNTNO", NEW_LECCODE);
						 searchMap.put("REAL_PRICE", REAL_PRICE);
						 searchMap.put("ORG_MGNTNO", ORG_LECCODE);
						 productOrderService.updateOffMgntNo(searchMap);

						 searchMap.put("PACKAGE_NO", NEW_LECCODE);
						 searchMap.put("LECTURE_NO", NEW_LECCODE);
						 searchMap.put("REAL_PRICE", REAL_PRICE);
						 searchMap.put("ORG_PACKAGE_NO", PKG_LECCODE);
						 searchMap.put("ORG_LECTURE_NO", ORG_LECCODE);
						 productOrderService.modifyOffMylecture(searchMap);
					 } else {
						 searchMap.put("REAL_PRICE", 0);
						 searchMap.put("MGNTNO", PKG_LECCODE);
						 searchMap.put("ORG_MGNTNO", PKG_LECCODE);
						 productOrderService.updateOffMgntNo(searchMap);
					 }
				 } else{ //변경과목이 종합반의 부속 과목일 경우 가격만 업데이트 한다.
					 searchMap.put("PACKAGE_NO", PKG_LECCODE);
					 searchMap.put("LECTURE_NO", ORG_LECCODE);
					 searchMap.put("REAL_PRICE", REAL_PRICE);

					 searchMap.put("ORG_PACKAGE_NO", PKG_LECCODE);
					 searchMap.put("ORG_LECTURE_NO", ORG_LECCODE);

					 productOrderService.modifyOffMylecture(searchMap);

					 searchMap.put("ORG_MGNTNO", PKG_LECCODE);
					 productOrderService.updateOffMgntNo_N(searchMap);
				 }
			}
		}

			/* 주문정보 히스토리 */
			searchMap.put("REG_ID", USERID);
			searchMap.put("ACT_TYPE", "U");
			productOrderService.insertOffOrderLog(searchMap);
			/* 주문정보 히스토리 */
		 
		output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_list.do";
	}

	// 수강신청 수정
	@RequestMapping(value="/registrationUpdate.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String registrationUpdate(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String USER_NM = CommonUtil.isNull(req.getParameter("USER_NM"), "");
		String PHONE_NO = CommonUtil.isNull(req.getParameter("PHONE_NO"), "");
		String EMAIL = CommonUtil.isNull(req.getParameter("EMAIL"), "");
		String PRICE_DISCOUNT_TYPE = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_TYPE"), "");
		String PRICE_DISCOUNT = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT"), "0");
		String PRICE_DISCOUNT_REASON = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_REASON"), "");
		String PRICE = CommonUtil.isNull(req.getParameter("PRICE"), "0");
		String PRICE_CARD = CommonUtil.isNull(req.getParameter("PRICE_CARD"), "0");
		String CARD_NAME = CommonUtil.isNull(req.getParameter("CARD_NAME"), "");
		String PRICE_CASH = CommonUtil.isNull(req.getParameter("PRICE_CASH"), "0");
		String PRICE_BANK = CommonUtil.isNull(req.getParameter("PRICE_BANK"), "0");
		String PRICE_UNPAID = CommonUtil.isNull(req.getParameter("PRICE_UNPAID"), "0");
		String DUE_DT = CommonUtil.isNull(req.getParameter("DUE_DT"), "");
		String MEMO = CommonUtil.isNull(req.getParameter("MEMO"), "");
		String REG_DT = CommonUtil.isNull(req.getParameter("REG_DT"), "");
		String TYPE = CommonUtil.isNull(req.getParameter("TYPE"), "");

		String PAYCODE = "";

		if (Long.parseLong(PRICE_CARD) > 0) {  //카드
			PAYCODE = "PAY110";
		}else if (Long.parseLong(PRICE_BANK) > 0) {  //예금 - 윌비스통장 직접 입금
			PAYCODE = "PAY100";
		}else if (Long.parseLong(PRICE_CASH) > 0) { //현금
			PAYCODE = "PAY140";
		}else{  //전액할인 - 무료수강
			PAYCODE = "PAY160";
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);
		searchMap.put("PHONE_NO", PHONE_NO);
		searchMap.put("EMAIL", EMAIL);
		searchMap.put("PRICE_DISCOUNT_TYPE", PRICE_DISCOUNT_TYPE);
		searchMap.put("PRICE_DISCOUNT", PRICE_DISCOUNT);
		searchMap.put("PRICE_DISCOUNT_REASON", PRICE_DISCOUNT_REASON);
		searchMap.put("PRICE", PRICE);
		searchMap.put("PRICE_CARD", PRICE_CARD);
		searchMap.put("CARD_NAME", CARD_NAME);
		searchMap.put("PRICE_CASH", PRICE_CASH);
		searchMap.put("PRICE_BANK", PRICE_BANK);
		searchMap.put("PRICE_UNPAID", PRICE_UNPAID);
		searchMap.put("DUE_DT", DUE_DT);
		searchMap.put("MEMO", MEMO);
		searchMap.put("REG_DT", REG_DT);
		searchMap.put("USERID", USERID);
		searchMap.put("PAYCODE", PAYCODE);
		searchMap.put("ORDERNO", ORDERNO);


		productOrderService.updateOffOrders(searchMap);

		Map<String, String> searchMap1 = new HashMap<String, String>();
		searchMap1.put("ORDERNO", ORDERNO);

		// 결제정보를 업데이트하기 전에 기존에 생성된 정보가 있는지 확인한다.
		List<HashMap<String, String>> offApproval = productOrderService.getOffApprovalsCount(searchMap1);
		if (offApproval.size() > 0) {
			// 결제 정보가 있으면 업데이트한다.
			productOrderService.updateOffApprovals(searchMap);
		} else {
			// 결제정보가 없으면 생성한다.
			productOrderService.insertOffApprovals(searchMap);
		}

		if(TYPE.equals("N")) {
			productOrderService.deleteOffOrderMgntNo(searchMap);
			productOrderService.deleteOffMylecture(searchMap);
		} else {
			productOrderService.deleteOffOrderMgntNo(searchMap);
		}

		//품목 등록
		String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];
					 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

					 searchMap.put("MGNTNO", LECCODE2);
					 searchMap.put("SUBJECT_REAL_PRICE", SUBJECT_REAL_PRICE2);

					 if(TYPE.equals("N")) {
						 productOrderService.insertOffOrderMgntNo(searchMap);
						 productOrderService.insertOffMylecture(searchMap);
					 } else {
						 productOrderService.insertOffOrderMgntNo(searchMap);
					 }
				 }
			}
		}

		/* 주문정보 히스토리 */
		searchMap.put("REG_ID", USERID);
		searchMap.put("ACT_TYPE", "U");
		productOrderService.insertOffOrderLog(searchMap);
		/* 주문정보 히스토리 */
		
		output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_list.do";
	}

	// 수강신청 삭제
	@RequestMapping(value="/registrationDelete.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String registrationDelete(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);

		/* 주문정보 히스토리 */
		searchMap.put("REG_ID", USERID);
		searchMap.put("PRICE", "0");
		searchMap.put("ACT_TYPE", "D");
		productOrderService.insertOffOrderLog(searchMap);
		/* 주문정보 히스토리 */
		
		productOrderService.deleteAllCart(searchMap);

		productOrderService.deleteOffOrders(searchMap);
		productOrderService.deleteOffApprovals(searchMap);
		productOrderService.deleteOffOrderMgntNo(searchMap);
		productOrderService.deleteOffMylecture(searchMap);

		output.addAttribute("orderstatuscode", orderstatuscode);
		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("paycode", paycode);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_list.do";
	}


	// off 환불관리 리스트
	@RequestMapping(value="/off_refund_list.do")
	public String off_refund_list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;
		List order_list = null;
		List payment_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String message = CommonUtil.isNull(req.getParameter("message"), "");

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
        String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("USER_ID", USER_ID);
		//searchMap.put("message", message);
		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
		searchMap.put("cmd", cmd);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -1);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);

			sdate = Delday6;
		}

		if(edate.equals("")){
			String ch1 = null;
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);

			edate = ch1;
		}

		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);

		//리스트
		list = productOrderService.getOffOrderListDB(searchMap);

		// 총 건수
		int listCount = productOrderService.getOffOrderListCount(searchMap);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "productOrder/Tbl_Off_Refund_Order_Lst";
	}


	// 환불관리 수정 상세
	@RequestMapping(value="/updateRefundPage.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String updateRefundPage(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List registration_list = null;

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");
		String MGNTNO = CommonUtil.isNull(req.getParameter("MGNTNO"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);
		searchMap.put("MGNTNO", MGNTNO);

		//상세 조회
		list = productOrderService.getUpdateDetail(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("ORDERNO",map.get("ORDERNO"));
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("PHONE_NO",map.get("PHONE_NO"));
		searchMap.put("EMAIL",map.get("EMAIL"));
		searchMap.put("PRICE_DISCOUNT_TYPE",map.get("PRICE_DISCOUNT_TYPE"));
		searchMap.put("PRICE_DISCOUNT",map.get("PRICE_DISCOUNT"));
		searchMap.put("PRICE_DISCOUNT_REASON",map.get("PRICE_DISCOUNT_REASON"));
		searchMap.put("PRICE",map.get("PRICE"));
		searchMap.put("PRICE_CARD",map.get("PRICE_CARD"));
		searchMap.put("PRICE_CASH",map.get("PRICE_CASH"));
		searchMap.put("PRICE_BANK",map.get("PRICE_BANK"));
		searchMap.put("PRICE_UNPAID",map.get("PRICE_UNPAID"));
		searchMap.put("CARD_NAME",map.get("CARD_NAME"));
		searchMap.put("MEMO",map.get("MEMO"));
		searchMap.put("DUE_DT",map.get("DUE_DT"));
		searchMap.put("REG_DT",map.get("REG_DT"));
		searchMap.put("TICKET_PRINT_DT",map.get("TICKET_PRINT_DT"));
		searchMap.put("REFUND_PAY",map.get("REFUND_PAY"));
		searchMap.put("REFUND_CARD",map.get("REFUND_CARD"));
		searchMap.put("REFUND_CASH",map.get("REFUND_CASH"));
		searchMap.put("ACC_BANK_DEPOSITOR",map.get("ACC_BANK_DEPOSITOR"));
		searchMap.put("ACC_BANK_NAME",map.get("ACC_BANK_NAME"));
		searchMap.put("ACC_BANK_NUM",map.get("ACC_BANK_NUM"));
		searchMap.put("REFUND_MEMO",map.get("REFUND_MEMO"));
		searchMap.put("SET_DATE",map.get("SET_DATE"));


		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Off_Refund_Order_Ins";
	}

	// 환불관리 수정 상세
	@RequestMapping(value="/updateRefundPage2.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String updateRefundPage2(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List registration_list = null;

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String paycode = CommonUtil.isNull(req.getParameter("paycode"), "");
		String orderstatuscode = CommonUtil.isNull(req.getParameter("orderstatuscode"), "");
		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
		String edate = CommonUtil.isNull(req.getParameter("edate"), "");
		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("paycode", paycode);
		searchMap.put("orderstatuscode", orderstatuscode);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);

		//상세 조회
		list = productOrderService.getUpdateDetail(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("ORDERNO",map.get("ORDERNO"));
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("PHONE_NO",map.get("PHONE_NO"));
		searchMap.put("EMAIL",map.get("EMAIL"));
		searchMap.put("PRICE_DISCOUNT_TYPE",map.get("PRICE_DISCOUNT_TYPE"));
		searchMap.put("PRICE_DISCOUNT",map.get("PRICE_DISCOUNT"));
		searchMap.put("PRICE_DISCOUNT_REASON",map.get("PRICE_DISCOUNT_REASON"));
		searchMap.put("PRICE",map.get("PRICE"));
		searchMap.put("PRICE_CARD",map.get("PRICE_CARD"));
		searchMap.put("PRICE_CASH",map.get("PRICE_CASH"));
		searchMap.put("PRICE_BANK",map.get("PRICE_BANK"));
		searchMap.put("PRICE_UNPAID",map.get("PRICE_UNPAID"));
		searchMap.put("CARD_NAME",map.get("CARD_NAME"));
		searchMap.put("MEMO",map.get("MEMO"));
		searchMap.put("DUE_DT",map.get("DUE_DT"));
		searchMap.put("REG_DT",map.get("REG_DT"));
		searchMap.put("TICKET_PRINT_DT",map.get("TICKET_PRINT_DT"));
		searchMap.put("REFUND_PAY",map.get("REFUND_PAY"));
		searchMap.put("REFUND_CARD",map.get("REFUND_CARD"));
		searchMap.put("REFUND_CASH",map.get("REFUND_CASH"));
		searchMap.put("ACC_BANK_DEPOSITOR",map.get("ACC_BANK_DEPOSITOR"));
		searchMap.put("ACC_BANK_NAME",map.get("ACC_BANK_NAME"));
		searchMap.put("ACC_BANK_NUM",map.get("ACC_BANK_NUM"));
		searchMap.put("REFUND_MEMO",map.get("REFUND_MEMO"));
		searchMap.put("SET_DATE",map.get("SET_DATE"));


		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Off_Refund_Order_Ins";
	}

	// 환불관리 확인버튼
	@RequestMapping(value="/refundCrud.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String refundCrud(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String REFUND_CARD = CommonUtil.isNull(req.getParameter("REFUND_CARD"), "0");
		String REFUND_CASH = CommonUtil.isNull(req.getParameter("REFUND_CASH"), "0");
		String ACC_BANK_DEPOSITOR = CommonUtil.isNull(req.getParameter("ACC_BANK_DEPOSITOR"), "");
		String ACC_BANK_NAME = CommonUtil.isNull(req.getParameter("ACC_BANK_NAME"), "");
		String ACC_BANK_NUM = CommonUtil.isNull(req.getParameter("ACC_BANK_NUM"), "");
		String REFUND_MEMO = CommonUtil.isNull(req.getParameter("REFUND_MEMO"), "");
		String SET_DATE = CommonUtil.isNull(req.getParameter("SET_DATE"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");


		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("REFUND_CARD", REFUND_CARD);
		searchMap.put("REFUND_CASH", REFUND_CASH);
		searchMap.put("ACC_BANK_NAME", ACC_BANK_NAME);
		searchMap.put("ACC_BANK_NUM", ACC_BANK_NUM);
		searchMap.put("ACC_BANK_DEPOSITOR", ACC_BANK_DEPOSITOR);
		searchMap.put("REFUND_MEMO", REFUND_MEMO);
		searchMap.put("SET_DATE", SET_DATE);
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);
		searchMap.put("USERID", USERID);

		if(STATUSCODE.equals("DLV230")){ //환불완료
			productOrderService.updateOffRefund(searchMap);

		}else{ //환불완료가 아니면 mgnt_no 테이블 환불등록 insert
			//품목 등록
			String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
			String[] LECCODE = req.getParameterValues("LECCODE");

			if (LECCODE != null) {
				for (int i = 0; i < LECCODE.length; i++) {
					 if (LECCODE[i].length() > 0) {

						 String LECCODE2 = LECCODE[i];
						 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

						 searchMap.put("MGNTNO", LECCODE2);
						 searchMap.put("SUBJECT_REAL_PRICE", "-"+SUBJECT_REAL_PRICE2);

						 if(log.isDebugEnabled()){
							log.debug("************ MGNTNO : "+ LECCODE2);
							log.debug("************ SUBJECT_REAL_PRICE : "+ SUBJECT_REAL_PRICE2);
						 }

						 productOrderService.insertRefundOffOrderMgntNo(searchMap);
						 productOrderService.updateOffMylectureRefund(searchMap);
					 }
				}
			}

			productOrderService.insertOffRefund(searchMap);
		}

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_refund_list.do";
	}

	// 환불관리 환불 후 재입력 버튼
	@RequestMapping(value="/refundReCrud.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String refundReCrud(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String REFUND_CARD = CommonUtil.isNull(req.getParameter("REFUND_CARD"), "0");
		String REFUND_CASH = CommonUtil.isNull(req.getParameter("REFUND_CASH"), "0");
		String ACC_BANK_NAME = CommonUtil.isNull(req.getParameter("ACC_BANK_NAME"), "");
		String ACC_BANK_NUM = CommonUtil.isNull(req.getParameter("ACC_BANK_NUM"), "");
		String ACC_BANK_DEPOSITOR = CommonUtil.isNull(req.getParameter("ACC_BANK_DEPOSITOR"), "");
		String REFUND_MEMO = CommonUtil.isNull(req.getParameter("REFUND_MEMO"), "");
		String SET_DATE = CommonUtil.isNull(req.getParameter("SET_DATE"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("REFUND_CARD", REFUND_CARD);
		searchMap.put("REFUND_CASH", REFUND_CASH);
		searchMap.put("ACC_BANK_NAME", ACC_BANK_NAME);
		searchMap.put("ACC_BANK_NUM", ACC_BANK_NUM);
		searchMap.put("ACC_BANK_DEPOSITOR", ACC_BANK_DEPOSITOR);
		searchMap.put("REFUND_MEMO", REFUND_MEMO);
		searchMap.put("SET_DATE", SET_DATE);
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);
		searchMap.put("USERID", USERID);

		//품목 등록
		String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];
					 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

					 searchMap.put("MGNTNO", LECCODE2);
					 searchMap.put("SUBJECT_REAL_PRICE", "-"+SUBJECT_REAL_PRICE2);

					 if(log.isDebugEnabled()){
						log.debug("************ MGNTNO : "+ LECCODE2);
						log.debug("************ SUBJECT_REAL_PRICE : "+ SUBJECT_REAL_PRICE2);
					 }

					 searchMap.put("REFUND", "Y");

					 productOrderService.insertRefundOffOrderMgntNo(searchMap);
					 productOrderService.updateOffMylecture(searchMap);
				 }
			}
		}

		productOrderService.insertOffRefund(searchMap);

		//상세 조회
		list = productOrderService.getUpdateDetail(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("ORDERNO",map.get("ORDERNO"));
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("PHONE_NO",map.get("PHONE_NO"));
		searchMap.put("EMAIL",map.get("EMAIL"));
		searchMap.put("PRICE_DISCOUNT_TYPE",map.get("PRICE_DISCOUNT_TYPE"));
		searchMap.put("PRICE_DISCOUNT",map.get("PRICE_DISCOUNT"));
		searchMap.put("PRICE_DISCOUNT_REASON",map.get("PRICE_DISCOUNT_REASON"));
		searchMap.put("PRICE",map.get("PRICE"));
		searchMap.put("PRICE_CARD",map.get("PRICE_CARD"));
		searchMap.put("PRICE_CASH",map.get("PRICE_CASH"));
		searchMap.put("PRICE_BANK",map.get("PRICE_BANK"));
		searchMap.put("PRICE_UNPAID",map.get("PRICE_UNPAID"));
		searchMap.put("CARD_NAME",map.get("CARD_NAME"));
		searchMap.put("MEMO",map.get("MEMO"));
		searchMap.put("DUE_DT",map.get("DUE_DT"));
		searchMap.put("REG_DT",map.get("REG_DT"));
		searchMap.put("TICKET_PRINT_DT",map.get("TICKET_PRINT_DT"));
		searchMap.put("REFUND_PAY",map.get("REFUND_PAY"));
		searchMap.put("REFUND_CARD",map.get("REFUND_CARD"));
		searchMap.put("REFUND_CASH",map.get("REFUND_CASH"));
		searchMap.put("ACC_BANK_DEPOSITOR",map.get("ACC_BANK_DEPOSITOR"));
		searchMap.put("ACC_BANK_NAME",map.get("ACC_BANK_NAME"));
		searchMap.put("ACC_BANK_NUM",map.get("ACC_BANK_NUM"));
		searchMap.put("REFUND_MEMO",map.get("REFUND_MEMO"));
		searchMap.put("SET_DATE",map.get("SET_DATE"));

		output.addAttribute("sts", "U");
		output.addAttribute("sts2", "R");
		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Off_Order_Ins2";
	}


	// 환불관리 환불 후 재입력 버튼
	@RequestMapping(value="/refundNewReCrud.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String refundNewReCrud(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List sub_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String REFUND_CARD = CommonUtil.isNull(req.getParameter("REFUND_CARD"), "0");
		String REFUND_CASH = CommonUtil.isNull(req.getParameter("REFUND_CASH"), "0");
		String ACC_BANK_NAME = CommonUtil.isNull(req.getParameter("ACC_BANK_NAME"), "");
		String ACC_BANK_NUM = CommonUtil.isNull(req.getParameter("ACC_BANK_NUM"), "");
		String ACC_BANK_DEPOSITOR = CommonUtil.isNull(req.getParameter("ACC_BANK_DEPOSITOR"), "");
		String REFUND_MEMO = CommonUtil.isNull(req.getParameter("REFUND_MEMO"), "");
		String SET_DATE = CommonUtil.isNull(req.getParameter("SET_DATE"), "");

		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String STATUSCODE = CommonUtil.isNull(req.getParameter("STATUSCODE"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", searchkey);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("REFUND_CARD", REFUND_CARD);
		searchMap.put("REFUND_CASH", REFUND_CASH);
		searchMap.put("ACC_BANK_NAME", ACC_BANK_NAME);
		searchMap.put("ACC_BANK_NUM", ACC_BANK_NUM);
		searchMap.put("ACC_BANK_DEPOSITOR", ACC_BANK_DEPOSITOR);
		searchMap.put("REFUND_MEMO", REFUND_MEMO);
		searchMap.put("SET_DATE", SET_DATE);
		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("STATUSCODE", STATUSCODE);
		searchMap.put("USERID", USERID);

		//품목 등록
		String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];
					 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

					 searchMap.put("MGNTNO", LECCODE2);
					 searchMap.put("SUBJECT_REAL_PRICE", "-"+SUBJECT_REAL_PRICE2);

					 if(log.isDebugEnabled()){
						log.debug("************ MGNTNO : "+ LECCODE2);
						log.debug("************ SUBJECT_REAL_PRICE : "+ SUBJECT_REAL_PRICE2);
					 }

//					 searchMap.put("REFUND", "Y");

					 productOrderService.insertRefundOffOrderMgntNo(searchMap);
					 productOrderService.updateOffMylectureRefund(searchMap);
				 }
			}
		}

		productOrderService.insertOffRefund(searchMap);

		//상세 조회
		list = productOrderService.getUpdateDetail(searchMap);

		searchMap.put("REFUND", "Y");
		//장바구니 리스트
		sub_list = productOrderService.getSubList(searchMap);
		int sub_size = sub_list.size();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);
		searchMap.put("ORDERNO",map.get("ORDERNO"));
		searchMap.put("USER_ID",map.get("USER_ID"));
		searchMap.put("USER_NM",map.get("USER_NM"));
		searchMap.put("PHONE_NO",map.get("PHONE_NO"));
		searchMap.put("EMAIL",map.get("EMAIL"));
		searchMap.put("PRICE_DISCOUNT_TYPE",map.get("PRICE_DISCOUNT_TYPE"));
		searchMap.put("PRICE_DISCOUNT",map.get("PRICE_DISCOUNT"));
		searchMap.put("PRICE_DISCOUNT_REASON",map.get("PRICE_DISCOUNT_REASON"));
		searchMap.put("PRICE",map.get("PRICE"));
		searchMap.put("PRICE_CARD",map.get("PRICE_CARD"));
		searchMap.put("PRICE_CASH",map.get("PRICE_CASH"));
		searchMap.put("PRICE_BANK",map.get("PRICE_BANK"));
		searchMap.put("PRICE_UNPAID",map.get("PRICE_UNPAID"));
		searchMap.put("CARD_NAME",map.get("CARD_NAME"));
		searchMap.put("MEMO",map.get("MEMO"));
		searchMap.put("DUE_DT",map.get("DUE_DT"));
		searchMap.put("REG_DT",map.get("REG_DT"));
		searchMap.put("TICKET_PRINT_DT",map.get("TICKET_PRINT_DT"));
		searchMap.put("REFUND_PAY",map.get("REFUND_PAY"));
		searchMap.put("REFUND_CARD",map.get("REFUND_CARD"));
		searchMap.put("REFUND_CASH",map.get("REFUND_CASH"));
		searchMap.put("ACC_BANK_DEPOSITOR",map.get("ACC_BANK_DEPOSITOR"));
		searchMap.put("ACC_BANK_NAME",map.get("ACC_BANK_NAME"));
		searchMap.put("ACC_BANK_NUM",map.get("ACC_BANK_NUM"));
		searchMap.put("REFUND_MEMO",map.get("REFUND_MEMO"));
		searchMap.put("SET_DATE",map.get("SET_DATE"));

		output.addAttribute("sts", "U");
		output.addAttribute("sts2", "R");
		output.addAttribute("sub_list", sub_list);
		output.addAttribute("sub_size", sub_size);
		output.addAttribute("searchMap", searchMap);

		return "productOrder/Tbl_Off_Order_New_Ins";
	}

	// 환불관리 등록
	@RequestMapping(value="/registrationRefundInsert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String registrationRefundInsert(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String USER_NM = CommonUtil.isNull(req.getParameter("USER_NM"), "");
		String PHONE_NO = CommonUtil.isNull(req.getParameter("PHONE_NO"), "");
		String EMAIL = CommonUtil.isNull(req.getParameter("EMAIL"), "");
		String PRICE_DISCOUNT_TYPE = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_TYPE"), "");
		String PRICE_DISCOUNT = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT"), "0");
		String PRICE_DISCOUNT_REASON = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_REASON"), "");
		String PRICE = CommonUtil.isNull(req.getParameter("PRICE"), "0");
		String PRICE_CARD = CommonUtil.isNull(req.getParameter("PRICE_CARD"), "0");
		String CARD_NAME = CommonUtil.isNull(req.getParameter("CARD_NAME"), "");
		String PRICE_CASH = CommonUtil.isNull(req.getParameter("PRICE_CASH"), "0");
		String PRICE_BANK = CommonUtil.isNull(req.getParameter("PRICE_BANK"), "0");
		String PRICE_UNPAID = CommonUtil.isNull(req.getParameter("PRICE_UNPAID"), "0");
		String DUE_DT = CommonUtil.isNull(req.getParameter("DUE_DT"), "");
		String MEMO = CommonUtil.isNull(req.getParameter("MEMO"), "");
		String REG_DT = CommonUtil.isNull(req.getParameter("REG_DT"), "");

		String PAYCODE = "";

		if (Long.parseLong(PRICE_CARD) > 0) {  //카드
			PAYCODE = "PAY110";
		}else if (Long.parseLong(PRICE_BANK) > 0) {  //예금 - 윌비스통장 직접 입금
			PAYCODE = "PAY100";
		}else if (Long.parseLong(PRICE_CASH) > 0) { //현금
			PAYCODE = "PAY140";
		}else{  //전액할인 - 무료수강
			PAYCODE = "PAY160";
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);
		searchMap.put("PHONE_NO", PHONE_NO);
		searchMap.put("EMAIL", EMAIL);
		searchMap.put("PRICE_DISCOUNT_TYPE", PRICE_DISCOUNT_TYPE);
		searchMap.put("PRICE_DISCOUNT", PRICE_DISCOUNT);
		searchMap.put("PRICE_DISCOUNT_REASON", PRICE_DISCOUNT_REASON);
		searchMap.put("PRICE", PRICE);
		searchMap.put("PRICE_CARD", PRICE_CARD);
		searchMap.put("CARD_NAME", CARD_NAME);
		searchMap.put("PRICE_CASH", PRICE_CASH);
		searchMap.put("PRICE_BANK", PRICE_BANK);
		searchMap.put("PRICE_UNPAID", PRICE_UNPAID);
		searchMap.put("DUE_DT", DUE_DT);
		searchMap.put("MEMO", MEMO);
		searchMap.put("REG_DT", REG_DT);
		searchMap.put("USERID", USERID);
		searchMap.put("PAYCODE", PAYCODE);

		//주문번호 생성
		list = productOrderService.getMCount(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);

		String ORDERNO = map.get("ORDERNO").toString();
		searchMap.put("ORDERNO", ORDERNO);

		if (log.isDebugEnabled()) {
			log.debug("***** 수강신청 등록 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
		}

		productOrderService.insertOffOrders(searchMap);
		productOrderService.insertOffApprovals(searchMap);

		//품목 등록
		String[] SUBJECT_REAL_PRICE = req.getParameterValues("SUBJECT_REAL_PRICE2");
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];
					 String SUBJECT_REAL_PRICE2 = SUBJECT_REAL_PRICE[i];

					 searchMap.put("MGNTNO", LECCODE2);
					 searchMap.put("SUBJECT_REAL_PRICE", SUBJECT_REAL_PRICE2);

					 if(log.isDebugEnabled()){
							log.debug("************ MGNTNO : "+ LECCODE2);
							log.debug("************ SUBJECT_REAL_PRICE : "+ SUBJECT_REAL_PRICE2);
						}

					 productOrderService.insertOffOrderMgntNo(searchMap);
					 productOrderService.insertOffMylecture(searchMap);
				 }
			}
		}

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_refund_list.do";
	}


	// 환불관리 등록
	@RequestMapping(value="/registrationRefundNewInsert.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String registrationRefundNewInsert(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");
		String USER_NM = CommonUtil.isNull(req.getParameter("USER_NM"), "");
		String PHONE_NO = CommonUtil.isNull(req.getParameter("PHONE_NO"), "");
		String EMAIL = CommonUtil.isNull(req.getParameter("EMAIL"), "");
		String PRICE_DISCOUNT_TYPE = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_TYPE"), "");
		String PRICE_DISCOUNT = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT"), "0");
		String PRICE_DISCOUNT_REASON = CommonUtil.isNull(req.getParameter("PRICE_DISCOUNT_REASON"), "");
		String PRICE = CommonUtil.isNull(req.getParameter("PRICE"), "0");
		String PRICE_CARD = CommonUtil.isNull(req.getParameter("PRICE_CARD"), "0");
		String CARD_NAME = CommonUtil.isNull(req.getParameter("CARD_NAME"), "");
		String PRICE_CASH = CommonUtil.isNull(req.getParameter("PRICE_CASH"), "0");
		String PRICE_BANK = CommonUtil.isNull(req.getParameter("PRICE_BANK"), "0");
		String PRICE_UNPAID = CommonUtil.isNull(req.getParameter("PRICE_UNPAID"), "0");
		String DUE_DT = CommonUtil.isNull(req.getParameter("DUE_DT"), "");
		String MEMO = CommonUtil.isNull(req.getParameter("MEMO"), "");
		String REG_DT = CommonUtil.isNull(req.getParameter("REG_DT"), "");

		String PAYCODE = "";

		if (Long.parseLong(PRICE_CARD) > 0) {  //카드
			PAYCODE = "PAY110";
		}else if (Long.parseLong(PRICE_BANK) > 0) {  //예금 - 윌비스통장 직접 입금
			PAYCODE = "PAY100";
		}else if (Long.parseLong(PRICE_CASH) > 0) { //현금
			PAYCODE = "PAY140";
		}else{  //전액할인 - 무료수강
			PAYCODE = "PAY160";
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USER_NM", USER_NM);
		searchMap.put("PHONE_NO", PHONE_NO);
		searchMap.put("EMAIL", EMAIL);
		searchMap.put("PRICE_DISCOUNT_TYPE", PRICE_DISCOUNT_TYPE);
		searchMap.put("PRICE_DISCOUNT", PRICE_DISCOUNT);
		searchMap.put("PRICE_DISCOUNT_REASON", PRICE_DISCOUNT_REASON);
		searchMap.put("PRICE", PRICE);
		searchMap.put("PRICE_CARD", PRICE_CARD);
		searchMap.put("CARD_NAME", CARD_NAME);
		searchMap.put("PRICE_CASH", PRICE_CASH);
		searchMap.put("PRICE_BANK", PRICE_BANK);
		searchMap.put("PRICE_UNPAID", PRICE_UNPAID);
		searchMap.put("DUE_DT", DUE_DT);
		searchMap.put("MEMO", MEMO);
		searchMap.put("REG_DT", REG_DT);
		searchMap.put("ISCONFIRM", REG_DT);
		searchMap.put("USERID", USERID);
		searchMap.put("PAYCODE", PAYCODE);

		//주문번호 생성
		list = productOrderService.getMCount(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) list.get(0);

		String ORDERNO = map.get("ORDERNO").toString();
		searchMap.put("ORDERNO", ORDERNO);

		if (log.isDebugEnabled()) {
			log.debug("***** 수강신청 등록 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
		}

		productOrderService.insertOffOrders(searchMap);
		productOrderService.insertOffApprovals(searchMap);

		//품목 등록
		String[] PRD = req.getParameterValues("PRD");
		String ORG_LECCODE = "";
		String NEW_LECCODE = "";
		String PKG_LECCODE = "";
		String REAL_PRICE = "";

		 if (PRD != null) {
			for (int i = 0; i < PRD.length; i++) {
				ORG_LECCODE = req.getParameter("ORG_LECTURE_NO_"+PRD[i]);
				NEW_LECCODE = req.getParameter("MGNTNO_"+PRD[i]);
				REAL_PRICE = req.getParameter("REALPRICE_"+PRD[i]);

				 if ("M".equals(req.getParameter("O_TYPE_"+PRD[i]))) { //변경 과목이 단과 및 종합반 대표 과목일 경우

					 if ("D".equals(req.getParameter("L_TYPE_"+PRD[i]))) { //변경 과목이 단과반일 경우
						 searchMap.put("MGNTNO", NEW_LECCODE);
						 searchMap.put("PACKAGE_NO", NEW_LECCODE);
						 searchMap.put("LECTURE_NO", NEW_LECCODE);
						 searchMap.put("SUBJECT_REAL_PRICE", REAL_PRICE);

						 productOrderService.insertOffOrderMgntNo(searchMap);
						 productOrderService.insertOffMylecture(searchMap);
					 } else {
						 searchMap.put("MGNTNO", NEW_LECCODE);
						 searchMap.put("SUBJECT_REAL_PRICE", 0);
						 productOrderService.insertOffOrderMgntNo(searchMap);
					 }
				 } else{ //변경과목이 종합반의 부속 과목일 경우 가격만 업데이트 한다.
					 searchMap.put("MGNTNO", NEW_LECCODE);
					 searchMap.put("PACKAGE_NO", NEW_LECCODE);
					 searchMap.put("LECTURE_NO", ORG_LECCODE);
					 searchMap.put("SUBJECT_REAL_PRICE", REAL_PRICE);

					 searchMap.put("REAL_PRICE", REAL_PRICE);
					 searchMap.put("ORG_MGNTNO", NEW_LECCODE);

					 productOrderService.updateOffMgntNo_N(searchMap);
					 productOrderService.insertOffMylecture_N(searchMap);
				 }
			}
		}

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_refund_list.do";
	}


	// 환불관리 환불취소
	@RequestMapping(value="/refundDelete.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String refundDelete(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
		String USERID = sessionMap.get("USER_ID");

		String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
		String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
		String currentPage = CommonUtil.isNull(req.getParameter("currentPage"), "");
		String pageRow = CommonUtil.isNull(req.getParameter("pageRow"), "");

		// 검색조건
		String ORDERNO = CommonUtil.isNull(req.getParameter("ORDERNO"), "");
		String USER_ID = CommonUtil.isNull(req.getParameter("USER_ID"), "");



		if (log.isDebugEnabled()) {
			log.debug("***** 환불관리  환불취소 파라미터 ***** ");
			log.debug("***** ORDERNO : " + ORDERNO);
			log.debug("***** USER_ID : " + USER_ID);
			log.debug("***** USERID : " + USERID);
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("ORDERNO", ORDERNO);
		searchMap.put("USER_ID", USER_ID);
		searchMap.put("USERID", USERID);

		//품목 등록
		String[] LECCODE = req.getParameterValues("LECCODE");

		if (LECCODE != null) {
			for (int i = 0; i < LECCODE.length; i++) {
				 if (LECCODE[i].length() > 0) {

					 String LECCODE2 = LECCODE[i];

					 searchMap.put("MGNTNO", LECCODE2);

					 if(log.isDebugEnabled()){
						log.debug("************ MGNTNO : "+ LECCODE2);
					 }

					 searchMap.put("REFUND", "Y");

					 productOrderService.deleteOffCancelOrderMgntNo(searchMap);
					 productOrderService.updateOffCancelMylecture(searchMap);
				 }
			}
		}

		productOrderService.deleteOffCancelRefund(searchMap);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		return "redirect:/productOrder/off_refund_list.do";
	}


	// 쿠폰발행 팝업
	@RequestMapping(value="/TmCouponInsertAdd.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String TmCouponInsertAdd(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<String, String>();
		String userid = CommonUtil.isNull(req.getParameter("userid"), "");

		output.addAttribute("params", params);
		output.addAttribute("userid", userid);
		return "productOrder/Tm_Coupon_Pop_Add";
	}

	//쿠폰 과목 검색 팝업
	@RequestMapping(value="/TmCouponSubjectList.pop")
	public String TmCouponSubjectList(ModelMap model, HttpServletRequest request) throws Exception {


		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */
		List<HashMap<String, String>> list = subjectservice.subjectList(params);
		int listCount = subjectservice.subjectListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "productOrder/Tm_Coupon_Subject_pop";
	}

	//쿠폰 과목 검색 팝업
	@RequestMapping(value="/TmCouponTeacherList.pop")
	public String TmCouponTeacherList(ModelMap model, HttpServletRequest request) throws Exception {


		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */
		List<HashMap<String, String>> list = teacherservice.teacherList(params);
		int listCount = teacherservice.teacherListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "productOrder/Tm_Coupon_Teacher_pop";
	}

	// 환불관리 환불취소
	@RequestMapping(value="/insertMylecture.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String insertMylecture(ModelMap model, HttpServletRequest req) throws UnsupportedEncodingException {
		HashMap<String, String> params = new  HashMap<String, String>();

		// 검색조건
		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
		String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");

		params.put("ORDERNO", orderno);
		params.put("MGNTNO", package_no);

		if(package_no.substring(0, 1).equals("D")) {
			productOrderService.insertMyLecture(params);
		} else if(package_no.substring(0, 1).equals("N"))  {
			productOrderService.insertMyLectureN(params);
		} else if(package_no.substring(0, 1).equals("J"))  {
			productOrderService.insertMyLectureN(params);
		} else if(package_no.substring(0, 1).equals("P"))  {
			productOrderService.insertMyLectureN(params);
		}

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		
		model.addAttribute("userid", CommonUtil.isNull(req.getParameter("userid"), ""));
		model.addAttribute("orderno", orderno);
		model.addAttribute("package_no", package_no);
		model.addAttribute("orderstatuscode", CommonUtil.isNull(req.getParameter("orderstatuscode"), ""));
		model.addAttribute("search_date_type", CommonUtil.isNull(req.getParameter("search_date_type"), ""));
		model.addAttribute("searchkey", CommonUtil.isNull(req.getParameter("searchkey"), ""));
		model.addAttribute("searchtype", CommonUtil.isNull(req.getParameter("searchtype"), ""));
		model.addAttribute("paycode", CommonUtil.isNull(req.getParameter("paycode"), ""));
		model.addAttribute("payall", CommonUtil.isNull(req.getParameter("payall"), ""));
		model.addAttribute("sdate", CommonUtil.isNull(req.getParameter("sdate"), ""));
		model.addAttribute("edate", CommonUtil.isNull(req.getParameter("edate"), ""));
		model.addAttribute("currentPage", CommonUtil.isNull(req.getParameter("currentPage"), ""));
		model.addAttribute("pageRow", CommonUtil.isNull(req.getParameter("pageRow"), ""));

		model.addAttribute("params", params);

		return "redirect:/productOrder/plyer_gostop.pop";
	}
	
	@RequestMapping(value="/CoopLecture_Cancel.do")
    @ResponseBody
    public String CoopLecture_Chk(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();

        String result = "";
        String orderno = CommonUtil.isNull(request.getParameter("ORDERNO"), "");
        String orderid = CommonUtil.isNull(request.getParameter("ORDERID"), "");

		HttpURLConnection httpConn = null;  
		String urlParameters=""; //파라메타값
		String targetURL="http://b2b.siwonschool.com/outer/?proc=refund&site=willbes&uid="+orderid+"&oid="+orderno;

		URL url = new URL(targetURL);
		httpConn = (HttpURLConnection)url.openConnection();
		
		//헤더 선언
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		//httpConn.setRequestProperty("Cookie", "cookievalue="+ cookie);
		
		httpConn.setUseCaches (false);
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);    
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(), "utf-8"));
		pw.write(urlParameters);
		pw.flush();
		pw.close();
		
		//Get Response
		InputStream is = httpConn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line = "";
		
		while((line = rd.readLine()) != null) {
		 result = line;
		}
		
		if(result.indexOf("[") != -1){
			result = result.substring(result.indexOf("]")+1, result.length());
		}
						
        return result;
    }

}
