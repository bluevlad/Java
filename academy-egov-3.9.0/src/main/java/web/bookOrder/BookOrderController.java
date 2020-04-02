package web.bookOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.inicis.inipay.escrow.INIescrow;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.property.EgovPropertyService;
import web.bookOrder.service.BookOrderService;
import web.productOrder.service.ProductOrderService;
import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.excel.ExcelRead;
import web.util.file.FileUtil;
import web.util.paging.Paging;


@RequestMapping(value="/bookOrder")
@Controller
public class BookOrderController {

	/** log */
	private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    protected EgovProperties MirProperties;

    @Autowired
	private BookOrderService bookOrderService;

    @Autowired
	private ProductOrderService productOrderService;

	@Inject
	private FileSystemResource fsResource;	//DI

	@Resource(name="fileUtil")
	FileUtil fileUtil;

	@Resource(name="excelRead")
	ExcelRead excelUtil;

	// 교재배송 리스트 (택배)
	@RequestMapping(value="/ju_list.do")
	public String ju_list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		//int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int pageRow = 300;

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String message = CommonUtil.isNull(req.getParameter("message"), "");

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

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));

		//리스트
		list = bookOrderService.getJuOrderListDB(searchMap);

		// 총 건수
		int listCount = bookOrderService.getJuOrderListCount(searchMap);

		if (list.size() > 0) {
			for (int k = 0; k < list.size(); k++) {
				HashMap map = (HashMap) list.get(k);
				String orderno = (String) map.get("ORDERNO");

				searchMap.put("orderno", orderno);

				//2번째 리스트
				list_second = bookOrderService.getTblJuOrderMgntListDB(searchMap);

				output.addAttribute("list_second" + Integer.toString(k), list_second);
			}
		}

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "bookOrder/Tbl_JuOrder_Lst";
	}

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/juorder_list_excel.do")
	public View excelDownload(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
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

		params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
		params.put("searchtype",searchtype);
		params.put("sdate",sdate);
		params.put("edate",edate);

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = bookOrderService.getJuOrderExelListDB(params);

		Date date = new Date();
  	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = simpleDate.format(date) + "_택배조회";

		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
 		
 		String Month = "";
 		String Day = "";

 		  	
    	if(oCalendar.get(Calendar.MONTH) + 1 < 10){
    		Month = "0" + String.valueOf(oCalendar.get(Calendar.MONTH) + 1);
    	}else{
    		Month = String.valueOf(oCalendar.get(Calendar.MONTH) + 1);
    	}
    	
    	if(oCalendar.get(Calendar.DAY_OF_MONTH) < 10){
    		Day = "0" + oCalendar.get(Calendar.DAY_OF_MONTH);
    	}else{
    		Day = String.valueOf(oCalendar.get(Calendar.DAY_OF_MONTH));
    	}

		List<String> headerList = new ArrayList<String>();
		//headerList.add("NO");
		//headerList.add("이름");
		headerList.add("수령인");
		headerList.add("핸드폰");
		headerList.add("집번호");
		headerList.add("우편번호");
		headerList.add("주소");
		headerList.add("");
		headerList.add("메모");
		headerList.add("NO");
		headerList.add("교재명");		
		//headerList.add("등록일");
		//headerList.add("주문번호_pass");

		int dCount = 1;  

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			//newMap.put(i++, String.valueOf(dCount));
			//newMap.put(i++, CommonUtil.isNull(map.get("ORDERS_USERNAME")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_USERNAME")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_CELLNO")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_TELNO")));
			newMap.put(i++, "Z_" + CommonUtil.isNull(map.get("DELIVERS_ZIPCD")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_ADDR")));
			newMap.put(i++, "");
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_MEMO")));
			if(dCount < 10){
				newMap.put(i++, CommonUtil.isNull(Month + Day +"-9990"+String.valueOf(dCount)));
			}else{
				newMap.put(i++, CommonUtil.isNull(Month + Day +"-999"+String.valueOf(dCount)));
			}			
			newMap.put(i++, CommonUtil.isNull(map.get("MULTI_BOOK_NM")));			
			//newMap.put(i++, CommonUtil.isNull(map.get("TO_DATE")));
			//newMap.put(i++, CommonUtil.isNull(map.get("ORDERNO")) + "_pass");
			
			dCount++;
			newList.add(newMap);
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}
	
	@RequestMapping(value="/juorder_list_excel2.do")
	//public String excelDownload2(@RequestParam Map<String,Object> params, ModelMap output,HttpServletResponse req) throws Exception
	public String excelDownload2(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
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

		params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
		params.put("searchtype",searchtype);
		params.put("sdate",sdate);
		params.put("edate",edate);

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = bookOrderService.getJuOrderExelListDB(params);

		Date date = new Date();
  	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = simpleDate.format(date) + "_택배조회";

		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
 		
 		String Month = "";
 		String Day = "";

 		  	
    	if(oCalendar.get(Calendar.MONTH) + 1 < 10){
    		Month = "0" + String.valueOf(oCalendar.get(Calendar.MONTH) + 1);
    	}else{
    		Month = String.valueOf(oCalendar.get(Calendar.MONTH) + 1);
    	}
    	
    	if(oCalendar.get(Calendar.DAY_OF_MONTH) < 10){
    		Day = "0" + oCalendar.get(Calendar.DAY_OF_MONTH);
    	}else{
    		Day = String.valueOf(oCalendar.get(Calendar.DAY_OF_MONTH));
    	}

		List<String> headerList = new ArrayList<String>();
		//headerList.add("NO");
		//headerList.add("이름");
		headerList.add("수령인");
		headerList.add("핸드폰");
		headerList.add("집번호");
		headerList.add("우편번호");
		headerList.add("주소");
		headerList.add("");
		headerList.add("메모");
		headerList.add("NO");
		headerList.add("교재명");		
		//headerList.add("등록일");
		headerList.add("주문번호");

		int dCount = 1;  

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			//newMap.put(i++, String.valueOf(dCount));
			//newMap.put(i++, CommonUtil.isNull(map.get("ORDERS_USERNAME")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_USERNAME")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_CELLNO")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_TELNO")));
			newMap.put(i++, "Z_" + CommonUtil.isNull(map.get("DELIVERS_ZIPCD")));
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_ADDR")));
			newMap.put(i++, "");
			newMap.put(i++, CommonUtil.isNull(map.get("DELIVERS_MEMO")));
			if(dCount < 10){
				newMap.put(i++, CommonUtil.isNull(Month + Day +"-9990"+String.valueOf(dCount)));
			}else{
				newMap.put(i++, CommonUtil.isNull(Month + Day +"-999"+String.valueOf(dCount)));
			}			
			newMap.put(i++, CommonUtil.isNull(map.get("MULTI_BOOK_NM")));			
			//newMap.put(i++, CommonUtil.isNull(map.get("TO_DATE")));
			newMap.put(i++, CommonUtil.isNull(map.get("ORDERNO")));
			
			dCount++;
			newList.add(newMap);
		}
		
		String NO = Month+Day;
		
		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);
		output.addAttribute("exe_list", exe_list);
		output.addAttribute("NO", NO);
		
 	return "bookOrder/Tbl_JuOrder_Excel_Lst";
}

	// 교재배송관리 주문번호 상세
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sts = CommonUtil.isNull(req.getParameter("sts"), "");

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sts", sts);
		searchMap.put("searchtype", searchtype);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);

		//리스트
		list = bookOrderService.getTblApprovalsViewList(searchMap);

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
		pay_list.put("STATUS_DT",map.get("STATUS_DT"));

		// 총 건수
		int deliverscount = bookOrderService.getTblDeliversViewListCount(searchMap);

		searchMap.put("deliverscount", deliverscount);

		if(deliverscount > 0){
			del_list = bookOrderService.getTblDeliversViewList(searchMap);

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
			del_list2.put("DLEORDER",map.get("DLEORDER"));
			del_list2.put("DLEORDER_NM",map.get("DLEORDER_NM"));
			del_list2.put("SENDDATE",map.get("SENDDATE"));

			output.addAttribute("del_list", del_list2);

			if(log.isDebugEnabled()){
				log.debug("***** del_list : "+ del_list) ;
			}
    	}

		order_list = bookOrderService.getTblOrdersViewList(searchMap);

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

		lec_list = bookOrderService.getLecMstViewList(searchMap);

		output.addAttribute("deliverscount", deliverscount);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", pay_list);
		output.addAttribute("order_list", order_list2);
		output.addAttribute("lec_list", lec_list);

		return "bookOrder/Tbl_Order_Mod";
	}

	// 주문번호 상세 수정하기 버튼 파라미터
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sts = CommonUtil.isNull(req.getParameter("sts"), "");

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
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
		searchMap.put("sts", sts);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
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

    	bookOrderService.updateDelivers(searchMap);
    	bookOrderService.updateApprovals(searchMap);

    	output.addAttribute("sts", sts);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);

		return "redirect:/bookOrder/update.do";
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sts = CommonUtil.isNull(req.getParameter("sts"), "");

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		String[] wmv_check = req.getParameterValues("wmv_check");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sts", sts);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
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

				 bookOrderService.updateDeliversWmv(searchMap);
			 }
		}

		output.addAttribute("sts", sts);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

	   	output.addAttribute("orderno", orderno);

		return "redirect:/bookOrder/update.do";
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sts = CommonUtil.isNull(req.getParameter("sts"), "");

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
    	String realprice = CommonUtil.isNull(req.getParameter("realprice"), "");
    	String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String iscancle = CommonUtil.isNull(req.getParameter("iscancle"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sts", sts);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);
	    searchMap.put("realprice", realprice);
	    searchMap.put("mgntno", mgntno);
	    searchMap.put("iscancle", iscancle);

	    bookOrderService.updateMoney1(searchMap);

	    int totalPrice = bookOrderService.getMoneySum(searchMap);

	    searchMap.put("totalPrice", totalPrice);

	    bookOrderService.updateMoney2(searchMap);

	    output.addAttribute("sts", sts);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);

		return "redirect:/bookOrder/update.do";
	}

	// 주문번호 상세페이지 상태코드 삭제 버튼 - 20130122 주문내역에서 환불금액 삭제하기 위해 새로 추가함.
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

		String sts = CommonUtil.isNull(req.getParameter("sts"), "");

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String mgntno = CommonUtil.isNull(req.getParameter("mgntno"), "");
    	String iscancle = CommonUtil.isNull(req.getParameter("iscancle"), "");
    	String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sts", sts);

		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("mgntno", mgntno);
	    searchMap.put("iscancle", iscancle);
	    searchMap.put("orderno", orderno);

	    bookOrderService.refund_money_delete(searchMap);

	    output.addAttribute("sts", sts);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

    	output.addAttribute("orderno", orderno);

		return "redirect:/bookOrder/update.do";
	}
	// 송장저장
	@RequestMapping(value="/juupdate.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String juupdate(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;
		List del_list = null;
    	List order_list = null;
    	List lec_list = null;

    	boolean ini_ret;
    	
		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		String dlvdate = CommonUtil.isNull(req.getParameter("dlvdate"), "");

        String[] orderno = req.getParameterValues("orderno2");
        String[] sendno = req.getParameterValues("sendno");
        String[] dleorder = req.getParameterValues("dleorder");
        String[] paycode = req.getParameterValues("paycode");

        String userid = sessionMap.get("USER_ID");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("userid", userid);

		if(orderno!=null && orderno.length>0 ){
            for(int i=0; i<orderno.length; i++){
	           	 if(sendno[i].length() > 0){
	           		String orderno2 = orderno[i];
	           		String sendno2 = sendno[i];
          		  	String dleorder2 = dleorder[i];
          		  	String paycode2 = paycode[i];

	          		searchMap.put("orderno", orderno2);
	          		searchMap.put("sendno", sendno2);
	          		searchMap.put("dleorder", dleorder2);
	          		searchMap.put("dlvdate", dlvdate);
	          		 
	          		if(!paycode2.equals("PAY110")){
	          			order_list = productOrderService.getLecMstViewList(searchMap);
	          			HashMap<String, Object> ordview = (HashMap) order_list.get(0);
//		          		HashMap ordview = (HashMap) order_list.get(0);

	          			del_list = bookOrderService.getTblDeliversViewList(searchMap);
	          			HashMap<String, Object> delview = (HashMap) del_list.get(0);
//		          		HashMap delview = (HashMap) del_list.get(0);
		          		
		          		searchMap.put("price", ordview.get("price"));
		          		searchMap.put("tid", ordview.get("TID"));

		          		searchMap.put("dlv_recvname", delview.get("userName"));
		          		searchMap.put("dlv_recvpost", delview.get("zipcd"));
		          		searchMap.put("dlv_recvaddr", delview.get("addr"));
		          		searchMap.put("dlv_recvtel", delview.get("cellNo"));
	          		 	
		          		if(ordview.get("TID") != null){
			          		if(ordview.get("TID").toString().substring(0,1).equals("I")){
			          			ini_ret = INICISescrow(searchMap); // 이니시스 에스크로 송장 등록
			          		}else{
				          		escrow(output, searchMap); // LG이니시스 에스크로 송장 등록
			          		}
		          		}
	          		 }

	           		bookOrderService.updateSendNo1(searchMap);
	           		bookOrderService.updateSendNo2(searchMap);
	           	 }
            }
        }

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		output.addAttribute("message", URLEncoder.encode("저장되었습니다.","UTF-8"));

		return "redirect:/bookOrder/ju_list.do";
	}

	// 교재배송관리 'P' 팝업
	@RequestMapping(value="/pop_print.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_print(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List inf_list = null;
    	List orderinf_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String printtype = CommonUtil.isNull(req.getParameter("printtype"), "");
        String printnocode = CommonUtil.isNull(req.getParameter("printnocode"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);
		searchMap.put("printtype", printtype);
		searchMap.put("printnocode", printnocode);

		//리스트
		list = bookOrderService.getPopPrintView(searchMap);

		//리스트
		inf_list = bookOrderService.getPopPrintInfoView(searchMap);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap) inf_list.get(0);
		Map<String, Object> inf_list2 = new HashMap<String, Object>();
		inf_list2.put("sendNo",map.get("SENDNO"));
		inf_list2.put("userName",map.get("USERNAME"));
		inf_list2.put("BIRTH_DAY",map.get("BIRTH_DAY"));
		inf_list2.put("telno",map.get("TELNO"));
		inf_list2.put("cellno",map.get("CELLNO"));
		inf_list2.put("zipcd",map.get("ZIPCD"));
		inf_list2.put("addr",map.get("ADDR"));
		inf_list2.put("memo",map.get("MEMO"));
		inf_list2.put("statusCode",map.get("STATUSCODE"));
		inf_list2.put("sendDate",map.get("SENDDATE"));
		inf_list2.put("DLEORDER",map.get("DLEORDER"));
		inf_list2.put("DLEORDER_NM",map.get("DLEORDER_NM"));


		//리스트
		orderinf_list = bookOrderService.getPopPrintOrderInfoView(searchMap);

		map = new HashMap<String, Object>();
		map = (HashMap) orderinf_list.get(0);
		Map<String, Object> orderinf_list2 = new HashMap<String, Object>();
		orderinf_list2.put("userid",map.get("USERID"));
		orderinf_list2.put("username",map.get("USERNAME"));
		orderinf_list2.put("BIRTH_DAY",map.get("BIRTH_DAY"));
		orderinf_list2.put("PHONE_NO",map.get("PHONE_NO"));
		orderinf_list2.put("telno",map.get("TELNO"));
		orderinf_list2.put("cellno",map.get("CELLNO"));
		orderinf_list2.put("zipcd",map.get("ZIPCD"));
		orderinf_list2.put("addr",map.get("ADDR"));
		orderinf_list2.put("email",map.get("EMAIL"));
		orderinf_list2.put("regdate",map.get("REGDATE"));
		orderinf_list2.put("to_date",map.get("TO_DATE"));

		output.addAttribute("searchMap", searchMap);
		output.addAttribute("list", list);
		output.addAttribute("inf_list", inf_list2);
		output.addAttribute("orderinf_list", orderinf_list2);

		return "bookOrder/Pop_Print";
	}

	// 체크항목 프린트 팝업
	@RequestMapping(value="/pop_all_print.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_all_print(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List inf_list = null;
    	List orderinf_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String printtype = CommonUtil.isNull(req.getParameter("printtype"), "");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		//searchMap.put("orderno", orderno);
		searchMap.put("printtype", printtype);

		if (orderno.length() > 0) {
			 String [] tmp = null;
			 tmp = orderno.split("/");

			 searchMap.put("cnt", tmp.length);

			 for (int i = 0; i < tmp.length; i++) {
				 String orderno2 = tmp[i];

				 searchMap.put("orderno", orderno2);

				//리스트
				list = bookOrderService.getPopPrintView(searchMap);

				output.addAttribute("list" + Integer.toString(i), list);

				//리스트
				inf_list = bookOrderService.getPopPrintInfoView(searchMap);

				HashMap<String, Object> map = new HashMap<String, Object>();
				map = (HashMap) inf_list.get(0);
				Map<String, Object> inf_list2 = new HashMap<String, Object>();
				inf_list2.put("sendNo",map.get("SENDNO"));
				inf_list2.put("userName",map.get("USERNAME"));
				inf_list2.put("BIRTH_DAY",map.get("BIRTH_DAY"));
				inf_list2.put("telno",map.get("TELNO"));
				inf_list2.put("cellno",map.get("CELLNO"));
				inf_list2.put("zipcd",map.get("ZIPCD"));
				inf_list2.put("addr",map.get("ADDR"));
				inf_list2.put("memo",map.get("MEMO"));
				inf_list2.put("statusCode",map.get("STATUSCODE"));
				inf_list2.put("sendDate",map.get("SENDDATE"));
				inf_list2.put("DLEORDER",map.get("DLEORDER"));
				inf_list2.put("DLEORDER_NM",map.get("DLEORDER_NM"));

				output.addAttribute("inf_list" + Integer.toString(i), inf_list2);

				//리스트
				orderinf_list = bookOrderService.getPopPrintOrderInfoView(searchMap);

				map = new HashMap<String, Object>();
				map = (HashMap) orderinf_list.get(0);
				Map<String, Object> orderinf_list2 = new HashMap<String, Object>();
				orderinf_list2.put("userid",map.get("USERID"));
				orderinf_list2.put("username",map.get("USERNAME"));
				orderinf_list2.put("BIRTH_DAY",map.get("BIRTH_DAY"));
				orderinf_list2.put("PHONE_NO",map.get("PHONE_NO"));
				orderinf_list2.put("telno",map.get("TELNO"));
				orderinf_list2.put("cellno",map.get("CELLNO"));
				orderinf_list2.put("zipcd",map.get("ZIPCD"));
				orderinf_list2.put("addr",map.get("ADDR"));
				orderinf_list2.put("email",map.get("EMAIL"));
				orderinf_list2.put("regdate",map.get("REGDATE"));
				orderinf_list2.put("to_date",map.get("TO_DATE"));

				output.addAttribute("orderinf_list" + Integer.toString(i), orderinf_list2);
			 }
		 }

		output.addAttribute("searchMap", searchMap);

		return "bookOrder/Pop_All_Print";
	}


	// 송장엑셀업로드
	@RequestMapping(value="/exelUpload.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String exelUpload(ModelMap output, HttpServletRequest req, MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {

		List list = null;
		List inf_list = null;
    	List orderinf_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String imgFile3_sts = CommonUtil.isNull(req.getParameter("imgFile3_sts"), "");

        MultipartFile imgFile3 = multipartRequest.getFile("imgFile3");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("imgFile3_sts", imgFile3_sts);

		if (imgFile3_sts.equals("Y")) {

			String rootPath = fsResource.getPath();
			String subPath = "excel/";

			MultipartFile uploadFile = multipartRequest.getFile("imgFile3");
			HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
			String fileFullPath = (String) fileMap.get("fileFullPath");

			ArrayList<HashMap<String, String>> read = excelUtil.readExcelXLS(rootPath+fileFullPath);
			
			if (read.size() > 0) {
				for (int k = 0; k < read.size(); k++) {
					String orderno = (String)read.get(k).get("주문번호");
					String rec_name = (String)read.get(k).get("수령자");
					String phone_no = (String)read.get(k).get("전화1");
					String prd_nm = (String)read.get(k).get("상품명");
					String sendno = String.valueOf(read.get(k).get("송장번호"));

					if (sendno != null && !sendno.equals("")) {
						searchMap.put("orderno", orderno);
						searchMap.put("rec_name", rec_name);
						searchMap.put("sendno", sendno);

						bookOrderService.updateSendNo1(searchMap);
		           		bookOrderService.updateSendNo2(searchMap);
					}
					
				}
			}

		}

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		output.addAttribute("message", URLEncoder.encode("저장되었습니다.","UTF-8"));

		return "redirect:/bookOrder/ju_list.do";
	}

	// 교재배송 리스트 (직접수령)
	@RequestMapping(value="/jic_list.do")
	public String jic_list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param

		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String message = CommonUtil.isNull(req.getParameter("message"), "");

		if(sdate.equals("")){
			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
			java.util.Date date6 = month6.getTime();
			sdate = new SimpleDateFormat("yyyyMMdd").format(date6);
		}

		if(edate.equals("")){
			TimeZone.setDefault(new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" ));
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			edate = sdf1.format(new Date());
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));

		//리스트
		list = bookOrderService.getJicOrderListDB(searchMap);

		// 총 건수
		int listCount = bookOrderService.getJicOrderListCount(searchMap);

		if (list.size() > 0) {
			for (int k = 0; k < list.size(); k++) {
				HashMap map = (HashMap) list.get(k);
				String orderno = (String) map.get("ORDERNO");

				searchMap.put("orderno", orderno);

				//2번째 리스트
				list_second = bookOrderService.getTblJuOrderMgntListDB(searchMap);

				output.addAttribute("list_second" + Integer.toString(k), list_second);
			}
		}

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "bookOrder/Tbl_JicOrder_Lst";
	}

    // 엑셀리스트
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/jiorder_list_excel.do")
    public View excelJuOrderDownload(ModelMap output, HttpServletRequest req) throws Exception {
        HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
        output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
        output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
        String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
        String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

        if(sdate.equals("")){
            Calendar month6 = Calendar.getInstance();
            month6.add(Calendar.MONTH, -6);
            java.util.Date date6 = month6.getTime();
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(date6);
        }

        if(edate.equals("")){
            TimeZone.setDefault(new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" ));
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            edate = sdf1.format(new Date());
        }

        HashMap<String, String> params = new  HashMap<String, String>();

        params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
        params.put("searchtype",searchtype);
        params.put("sdate",sdate);
        params.put("edate",edate);

        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        //엑셀 리스트
        List<HashMap<String, String>> list = bookOrderService.getJicOrderListDB(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

        String excelName = "직접수령조회(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("주문번호");
        headerList.add("주문자");
        headerList.add("수령인");
        headerList.add("결제구분");
        headerList.add("교재명");
        headerList.add("저자");
        headerList.add("지불금액");
        headerList.add("등록일");
        headerList.add("수령여부");

        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("ORDERNO") && !"".equals(map.get("ORDERNO"))) {
                newMap.put(i++, map.get("ORDERNO").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ORDERS_USERNAME") && !"".equals(map.get("ORDERS_USERNAME"))) {
                newMap.put(i++, map.get("ORDERS_USERNAME").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("DELIVERS_USERNAME") && !"".equals(map.get("DELIVERS_USERNAME"))) {
                newMap.put(i++, map.get("DELIVERS_USERNAME").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("APPROVALS_PAYCODE_NM") && !"".equals(map.get("APPROVALS_PAYCODE_NM"))) {
                newMap.put(i++, map.get("APPROVALS_PAYCODE_NM").toString());
            } else {
                newMap.put(i++, "");
            }

            params.put("orderno", (String) map.get("ORDERNO"));
            //2번째 리스트
            List list_second = bookOrderService.getTblJuOrderMgntListDB(params);
            if (list_second.size() > 0) {
                for (int k = 0; k < list_second.size(); k++) {
                    HashMap map2 = (HashMap) list_second.get(k);
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
                    }
                    newMap.put(i++, name2);
                    if(null != map2.get("AUTHOR") && !"".equals(map2.get("AUTHOR"))) {
                        newMap.put(i++, map2.get("AUTHOR").toString());
                    } else {
                        newMap.put(i++, "");
                    }
                    if(k > 0){
                        newMap.put(i++, "");
                        newMap.put(i++, "");
                        newMap.put(i++, "");

                        newList.add(newMap);
                    }
                }
            } else {
                newMap.put(i++, "");
                newMap.put(i++, "");
            }

            if(null != map.get("APPROVALS_ADDPRICE") && !"".equals(map.get("APPROVALS_ADDPRICE"))) {
                newMap.put(i++, map.get("APPROVALS_ADDPRICE"));
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("DELIVERS_REGDATE") && !"".equals(map.get("DELIVERS_REGDATE"))) {
                newMap.put(i++, map.get("DELIVERS_REGDATE").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("DELIVERS_SENDDATE") && !"".equals(map.get("DELIVERS_SENDDATE"))) {
                if(null != map.get("STATUSCODE") && "DLV130".equals(map.get("STATUSCODE").toString())) {
                    newMap.put(i++, map.get("DELIVERS_SENDDATE").toString());
                } else {
                    newMap.put(i++, "발송준비");
                }
            } else {
                newMap.put(i++, "발송준비");
            }

            newList.add(newMap);
        }

        output.addAttribute("excelName", excelName);
        output.addAttribute("headerList", headerList);
        output.addAttribute("dataList", newList);

        return new ExcelDownloadView();
    }

	// 수령완료
	@RequestMapping(value="/jicupdate.do")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String jicupdate(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
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
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");

        String userid = sessionMap.get("USER_ID");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);
		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);

		searchMap.put("orderno", orderno);

		searchMap.put("userid", userid);

		bookOrderService.updateSendNo3(searchMap);
   		bookOrderService.updateSendNo4(searchMap);

		output.addAttribute("searchkey", URLEncoder.encode(searchkey,"UTF-8"));
		output.addAttribute("searchtype", searchtype);
		output.addAttribute("sdate", sdate);
		output.addAttribute("edate", edate);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);

		output.addAttribute("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		output.addAttribute("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		output.addAttribute("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));

		output.addAttribute("message", URLEncoder.encode("저장되었습니다.","UTF-8"));

		return "redirect:/bookOrder/jic_list.do";
	}

	// 교재매출내역 리스트
	@RequestMapping(value="/tol_list.do")
	public String tol_list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("menuParams", menuParams);
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param

		String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");
        String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String message = CommonUtil.isNull(req.getParameter("message"), "");

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

		Map<String, Object> searchMap = new HashMap<String, Object>();

		searchMap.put("sdate", sdate);
		searchMap.put("edate", edate);
		searchMap.put("searchkey", URLDecoder.decode(searchkey,"UTF-8"));
		searchMap.put("searchtype", searchtype);

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		searchMap.put("message", URLDecoder.decode(message,"UTF-8"));

		//리스트
		list = bookOrderService.getTolOrderListDB(searchMap);

		// 총 건수
		int listCount = bookOrderService.getTolOrderListCount(searchMap);

		if (list.size() > 0) {
			for (int k = 0; k < list.size(); k++) {
				HashMap map = (HashMap) list.get(k);
				String orderno = (String) map.get("ORDERNO");

				searchMap.put("orderno", orderno);

				//2번째 리스트
				list_second = bookOrderService.getPutLecMstDB(searchMap);

				output.addAttribute("list_second" + Integer.toString(k), list_second);
			}
		}

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		output.addAttribute("list", list);
		output.addAttribute("searchMap", searchMap);
		output.addAttribute("pagingHtml", pagingHtml);
		output.addAttribute("totalCount", listCount);
		output.addAttribute("currentPage", currentPage);
		output.addAttribute("pageRow", pageRow);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "bookOrder/Tbl_TolOrder_Lst";
	}

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/tolorder_list_excel.do")
	public View tolorder_list_excel(ModelMap output, HttpServletRequest req) throws Exception {
		List list = null;
		List list_second = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
		output.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		output.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		output.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		output.addAttribute("menuParams", menuParams);
		//메뉴 param

    	String searchkey = CommonUtil.isNull(req.getParameter("searchkey"), "");
    	String searchtype = CommonUtil.isNull(req.getParameter("searchtype"), "");
    	String sdate = CommonUtil.isNull(req.getParameter("sdate"), "");
        String edate = CommonUtil.isNull(req.getParameter("edate"), "");

		if(sdate.equals("")){

			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -6);
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

		params.put("searchkey",URLDecoder.decode(searchkey,"UTF-8"));
		params.put("searchtype",searchtype);
		params.put("sdate",sdate);
		params.put("edate",edate);

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = bookOrderService.getTolOrderExelListDB(params);

		Date date = new Date();
  	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = simpleDate.format(date) + "_교재매출내역";

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("주문자");
		headerList.add("상품명");
		headerList.add("저자");

		headerList.add("출판사");
		headerList.add("주문일");
		headerList.add("정가");
		headerList.add("판매가");
		headerList.add("수량");

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			params.put("orderno",map.get("ORDERNO").toString());

			//2번째 리스트
			list_second = bookOrderService.getPutLecMstDB(params);

			String name = "";
			String author = "";
			String book_publishers = "";
			String price = "";
			String discount_price = "";
			String cnt = "";

			if (list_second.size() > 0) {
				for (int k = 0; k < list_second.size(); k++) {
					HashMap newMap = new HashMap();
					int i = 0;
					newMap.put(i++, String.valueOf(map.get("RNUM")));
					newMap.put(i++, map.get("ORDERNO").toString());
					newMap.put(i++, map.get("USERNAME").toString() + "(" + map.get("USERID").toString() + ")");

					HashMap map2 = (HashMap) list_second.get(k);
					//상품명
					name = map2.get("NAME").toString() + "(" + String.valueOf(map2.get("CNT")) + ")";
					//저자
					author = map2.get("AUTHOR").toString();
					//출판사
					book_publishers = map2.get("BOOK_PUBLISHERS").toString();
					//정가
					price = String.valueOf(map2.get("PRICE"));
					//판매가
					discount_price = String.valueOf(map2.get("DISCOUNT_PRICE"));
					//수량
					cnt = String.valueOf(map2.get("CNT"));
					newMap.put(i++, name);
					newMap.put(i++, author);
					newMap.put(i++, book_publishers);
					newMap.put(i++, map.get("SENDDATE").toString());
					newMap.put(i++, price);
					newMap.put(i++, discount_price);
					newMap.put(i++,	cnt);
					newList.add(newMap);
				}
			}
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}
	
	// 엘지 유플러스 에스크로서비스(배송결과등록)
	public boolean escrow(ModelMap output, Map<String, Object> searchMap) throws Exception {

		output.addAttribute("SENDNO", searchMap.get("sendno"));
		output.addAttribute("ORDERNO", searchMap.get("orderno"));
		output.addAttribute("DLVDATE", searchMap.get("dlvdate"));
		
		
		String service_url = "";

		// 테스트용
		//service_url = "http://pgweb.uplus.co.kr:7085/pg/wmp/mertadmin/jsp/escrow/rcvdlvinfo.jsp"; 
		// 서비스용
		service_url = "https://pgweb.uplus.co.kr/pg/wmp/mertadmin/jsp/escrow/rcvdlvinfo.jsp"; 
	
		String mid ="";
		String oid ="";
		String productid ="";                                                                                      
		String orderdate ="";
		String dlvtype ="";
		String rcvdate ="";
		String rcvname ="";
		String rcvrelation ="";
		String dlvdate ="";
		String dlvcompcode ="";
		String dlvcomp ="";
		String dlvno ="";
		String dlvworker ="";
		String dlvworkertel  ="";
		String hashdata ="";
		String mertkey ="";
		
		
		mid = "hanlimgosi";																	// 상점ID
		oid =String.valueOf(searchMap.get("orderno"));						// 주문번호
		dlvtype = "03"	;																			// 등록내용구분
		dlvdate = String.valueOf(searchMap.get("dlvdate"));				// 발송일자
		dlvcompcode = "HJ";																	// 배송회사코드(한진택배)
		dlvno = String.valueOf(searchMap.get("sendno"));					// 운송장번호

		//productid = request.getParameter("productid");				// 상품ID
		//orderdate = request.getParameter("orderdate");				// 주문일자
		//rcvdate = request.getParameter("rcvdate");						// 실수령일자
		//rcvname = request.getParameter("rcvname");					// 실수령인명
		//rcvrelation = request.getParameter("rcvrelation");			// 관계
		//dlvcomp = request.getParameter("dlvcompe");					// 배송회사명
		//dlvworker = request.getParameter("dlvworker");				// 배송자명
		//dlvworkertel = request.getParameter("dlvworkertel");	// 배송자전화번호
	
	    boolean resp = false;
		mertkey = "cd944b4d359a2030179fdf6ce74b34cf"; //LG유플러스에서 발급한 상점키로 변경해 주시기 바랍니다.
			
		
		//******************************//
		// 보안용 인증키 생성 - 시작
		//******************************//
	    StringBuffer sb = new StringBuffer();
		if("03".equals(dlvtype))
		{
			// 발송정보
			sb.append(mid);
			sb.append(oid);
			sb.append(dlvdate);
			sb.append(dlvcompcode);
			sb.append(dlvno);
			sb.append(mertkey);
			
		}
		else if("01".equals(dlvtype))
		{
			// 수령정보 
			sb.append(mid);
			sb.append(oid);
			sb.append(dlvtype);
			sb.append(rcvdate);		
			sb.append(mertkey);
		}	
	    
	    byte[] bNoti = sb.toString().getBytes();
	
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] digest = md.digest(bNoti);
	
	    StringBuffer strBuf = new StringBuffer();
	
	    for (int i=0 ; i < digest.length ; i++) 
		{
	        int c = digest[i] & 0xff;
	        if (c <= 15)
			{
	            strBuf.append("0");
	        }
	        strBuf.append(Integer.toHexString(c));
	    }
	
	    hashdata = strBuf.toString();
		//******************************//
		// 보안용 인증키 생성 - 끝
		//******************************//
	
		//******************************//
		// 전송할 파라미터 문자열 생성 - 시작
		//******************************//
		String sendMsg = "";
		StringBuffer msgBuf = new StringBuffer();
		msgBuf.append("mid=" + mid + "&" );	
		msgBuf.append("oid=" + oid+"&");
		msgBuf.append("dlvtype=" + dlvtype + "&" );
		msgBuf.append("dlvdate=" + dlvdate + "&" );
		msgBuf.append("dlvcompcode=" + dlvcompcode + "&" );	
		msgBuf.append("dlvno=" + dlvno + "&" );
		msgBuf.append("hashdata=" + hashdata );
		
		//msgBuf.append("productid=" + productid + "&" );
		//msgBuf.append("orderdate=" + orderdate + "&" );	
		//msgBuf.append("rcvdate=" + rcvdate + "&" );
		//msgBuf.append("rcvname=" + rcvname + "&" );
		//msgBuf.append("rcvrelation=" + rcvrelation + "&" );			
		//msgBuf.append("dlvworker=" + dlvworker + "&" );
		//msgBuf.append("dlvworkertel=" + dlvworkertel + "&" );
	
		sendMsg = msgBuf.toString();

		StringBuffer errmsg = new StringBuffer();
		//******************************//
		// 전송할 파라미터 문자열 생성 - 끝
		//******************************//
		
		//*************************************//
		// HTTP로 배송결과 등록
		//*************************************//
		URL url = new URL(service_url);
		resp = sendRCVInfo(sendMsg,url,errmsg);
	
	    if(resp)
		{                                   //결과연동이 성공이면
	        System.out.println("OK");     
	    } 
		else 
		{                                    //결과 연동이 실패이면
	        System.out.println("FAIL");      
	    }
		//return "bookOrder/escrow";
		return resp;
	}
	
	// 에스크로서비스(배송결과등록) - 이니페이 2016.02.25
	public boolean INICISescrow(Map<String, Object> searchMap) throws Exception {

		INIescrow iniescrow = new INIescrow();
		
		/*********************
		 * 2. 지불 정보 설정 *
		 *********************/
		iniescrow.SetField("inipayhome", "D:/willbes/INIpay50");	// 이니페이 홈디렉터리(상점수정 필요)
		iniescrow.SetField("tid", searchMap.get("tid"));             	// 거래아이디
		iniescrow.SetField("mid", "willbes012");             		// 상점아이디
		iniescrow.SetField("admin", "1111");   												           // 키패스워드(상점아이디에 따라 변경)
	  //***********************************************************************************************************
	  //* admin 은 키패스워드 변수명입니다. 수정하시면 안됩니다. 1111의 부분만 수정해서 사용하시기 바랍니다.      *
	  //* 키패스워드는 상점관리자 페이지(https://iniweb.inicis.com)의 비밀번호가 아닙니다. 주의해 주시기 바랍니다.*
	  //* 키패스워드는 숫자 4자리로만 구성됩니다. 이 값은 키파일 발급시 결정됩니다.                               *
	  //* 키패스워드 값을 확인하시려면 상점측에 발급된 키파일 안의 readme.txt 파일을 참조해 주십시오.             *
	  //***********************************************************************************************************
		iniescrow.SetField("type", "escrow"); 				                         // 고정 (절대 수정 불가)
		iniescrow.SetField("escrowtype", "dlv"); 				                     // 고정 (절대 수정 불가)
		iniescrow.SetField("debug","true");                                          // 로그모드("true"로 설정하면 상세한 로그가 생성됨)
	    iniescrow.SetField("crypto", "execure");								     // Extrus 암호화모듈 사용(고정)
		
		iniescrow.SetField("oid", String.valueOf(searchMap.get("orderno")));
		iniescrow.SetField("dlv_report", "I"); //에스크로 등록형태. 등록:I, 변경:U String
		iniescrow.SetField("dlv_invoice", String.valueOf(searchMap.get("sendno"))); //운송장번호
		iniescrow.SetField("dlv_name", "윌비스"); //배송등록자
		
		iniescrow.SetField("dlv_excode", "korex"); //택배사코드 - 대한통운 2016-12-01
		iniescrow.SetField("dlv_exname", "대한통운"); //택배사명
		iniescrow.SetField("dlv_charge", "BH"); //배송비 지급형태 SH : 판매자부담, BH : 구매자부담
		
		iniescrow.SetField("dlv_invoiceday", String.valueOf(searchMap.get("dlvdate"))); //배송등록 확인일자
		iniescrow.SetField("dlv_sendname", "윌비스"); //searchMap.get("dlv_sendname")); //송신자 이름
		iniescrow.SetField("dlv_sendpost", "151895");  //송신자 우편번호, 구분자 없이
		iniescrow.SetField("dlv_sendaddr1", "서울시 관악구 호암로 26길"); //송신자 주소1
		iniescrow.SetField("dlv_sendaddr2", "13 세정빌딩 2층"); //송신자 주소2
		iniescrow.SetField("dlv_sendtel", "1544-5006"); //송신자 전화번호

		iniescrow.SetField("dlv_recvname", String.valueOf(searchMap.get("dlv_recvname"))); //수신자 이름
		iniescrow.SetField("dlv_recvpost", String.valueOf(searchMap.get("dlv_recvpost"))); //수신자 우편번호
		iniescrow.SetField("dlv_recvaddr", String.valueOf(searchMap.get("dlv_recvaddr"))); //수신자 주소1 
		iniescrow.SetField("dlv_recvtel", String.valueOf(searchMap.get("dlv_recvtel"))); // 수신자 전화번호
		
//		iniescrow.SetField("dlv_goodscode",request.getParameter("dlv_goodscode")); //상품코드
//		iniescrow.SetField("dlv_goods",request.getParameter("dlv_goods")); //상품명
//		iniescrow.SetField("dlv_goodcnt",request.getParameter("dlv_goodcnt")); //상품수량
		iniescrow.SetField("price", String.valueOf(searchMap.get("price"))); //상품가격
//		iniescrow.SetField("dlv_reserved1",request.getParameter("dlv_reserved1")); //상품상품옵션1
//		iniescrow.SetField("dlv_reserved2",request.getParameter("dlv_reserved2")); //상품상품옵션2
//		iniescrow.SetField("dlv_reserved3",request.getParameter("dlv_reserved3")); //상품상품옵션3
	    
		/*********************
		 * 3. 배송 등록 요청 *
		 *********************/
		iniescrow.startAction();

		/**********************
		 * 4. 배송 등록  결과 *
		 **********************/
		 String tid        = iniescrow.GetResult("tid"); 		  // 거래번호
		 String resultCode = iniescrow.GetResult("ResultCode");	  // 결과코드 ("00"이면 성공)
		 String resultMsg  = iniescrow.GetResult("ResultMsg"); 	  // 결과내용 (결과에 대한 설명)
		 String dlv_date   = iniescrow.GetResult("DLV_Date");     // 등록날짜 (결과에 대한 설명)
		 String dlv_time   = iniescrow.GetResult("DLV_Time");     // 등록시각 (결과에 대한 설명)

	    if(resultCode.equals("00"))	{   //결과연동이 성공이면
			return true;
	    } 
		else {                                    //결과 연동이 실패이면
			return false;
	    }
	    
	}
	
	//*************************************************
	// 아래부분 절대 수정하지 말것
	//*************************************************
	private boolean sendRCVInfo(String sendMsg, URL url, StringBuffer errmsg) throws Exception{
        OutputStreamWriter wr = null;
        BufferedReader br = null;
        HttpURLConnection conn = null;
        boolean result = false;
		String errormsg = null;

        try {
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(sendMsg);
            wr.flush();

            for (int i=0; ; i++) {
                String headerName = conn.getHeaderFieldKey(i);                
                String headerValue = conn.getHeaderField(i);

                if (headerName == null && headerValue == null) {
                    break;
                }
                if (headerName == null) {
                    headerName = "Version";
                }

                errmsg.append(headerName + ":" + headerValue + "\n");
            }


            br = new BufferedReader(new InputStreamReader(conn.getInputStream ()));

            String in;
            StringBuffer sb = new StringBuffer();
            while(((in = br.readLine ()) != null )){
                sb.append(in);
            }
            errmsg.append(sb.toString().trim());
            if ( sb.toString().trim().equals("OK")){
                result = true;
            }else{
				errormsg = sb.toString().trim();
			}
        } catch ( Exception ex ) {
            errmsg.append("EXCEPTION : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if ( wr != null) wr.close();
                if ( br != null) br.close();
            } catch(Exception e){
            }
        }
        return result;

    }	
    

}
