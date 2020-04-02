package web.coop;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.paging.Paging;
import web.coop.service.CoopOrderService;
import web.productOrder.service.ProductOrderService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/coopOrder")
@Controller
public class CoopOrderController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    /* last modified 2014-08-20 */
    @Autowired
    private CoopOrderService CoopOrderService;

    @Autowired
    private ProductOrderService productOrderService;

    // 전체 상품 주문 관리 리스트
    @RequestMapping(value="/list.do")
    public String list(ModelMap output, HttpServletRequest req) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, req, output);

        List list = null;
        List list_second = null;
        List order_list = null;
        List payment_list = null;

        HashMap<String, String> sessionMap = (HashMap<String, String>)req.getSession().getAttribute("AdmUserInfo");
        String manager_id = sessionMap.get("USER_ID");

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

        Map<String, Object> searchMap = new HashMap<String, Object>();
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
            month6.add(Calendar.MONTH, -2);
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

        //리스트
        list = CoopOrderService.getOrderListDB(searchMap);

        // 총 건수
        int listCount = CoopOrderService.getOrderListCount(searchMap);

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
        output.addAttribute("params", params);
        output.addAttribute("searchMap", searchMap);
        output.addAttribute("pagingHtml", pagingHtml);
        output.addAttribute("totalCount", listCount);
        output.addAttribute("currentPage", currentPage);
        output.addAttribute("pageRow", pageRow);
        output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        return "/coop/coopOrder/Tbl_Order_Lst";
    }

    @RequestMapping(value="/onPayDetail.do")
    public String onPayDetail(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
        // 검색조건
        String searchStartDate 		= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
        String searchEndDate 		= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("COOP_CD", 			"NSU");
        params.put("searchStartDate", 	searchStartDate);
        params.put("searchEndDate", 	    searchEndDate);

        List<HashMap<String,Object>> list = CoopOrderService.onPayDetail(params);

        model.addAttribute("list", 						list);
        model.addAttribute("searchStartDate", 		searchStartDate);
        model.addAttribute("searchEndDate", 		searchEndDate);
        model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        return "/coop/coopOrder/onPayDetail";
    }

    // 엑셀리스트
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/excel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        // 검색조건
        String searchStartDate      = CommonUtil.isNull(request.getParameter("searchStartDate"),    CommonUtil.getCurrentDate());
        String searchEndDate        = CommonUtil.isNull(request.getParameter("searchEndDate"),      CommonUtil.getCurrentDate());

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("COOP_CD",          "NSU");
        params.put("searchStartDate",   searchStartDate);
        params.put("searchEndDate",    searchEndDate);

        List<HashMap<String,Object>> list = CoopOrderService.onPayDetail(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

        String excelName = "남서울대매출관리(" + searchStartDate + "~" + searchEndDate + ")_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("아이디");
        headerList.add("이름");
        headerList.add("강의명");
        headerList.add("신청일");
        headerList.add("수강시작");
        headerList.add("수강종료");
        headerList.add("금액");
        headerList.add("결제");
        headerList.add("수수료율");
        headerList.add("수수료");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

        if(null != list && list.size() > 0) {
            for(int j=0; j < list.size(); j++) {
                HashMap<String, Object> map = (HashMap<String, Object>)list.get(j);

                HashMap newMap = new HashMap();
                int i = 0;
                if(null != map.get("USERID") && !"".equals(map.get("USERID"))) {
                    newMap.put(i++, map.get("USERID").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM"))) {
                    newMap.put(i++, map.get("USER_NM").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("SUBJECT_TITLE") && !"".equals(map.get("SUBJECT_TITLE"))) {
                    newMap.put(i++, map.get("SUBJECT_TITLE").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("REG_DT") && !"".equals(map.get("REG_DT"))) {
                    newMap.put(i++, map.get("REG_DT").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("START_DATE") && !"".equals(map.get("START_DATE"))) {
                    newMap.put(i++, map.get("START_DATE").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("END_DATE") && !"".equals(map.get("END_DATE"))) {
                    newMap.put(i++, map.get("END_DATE").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("TOTAL_PAY") && !"".equals(map.get("TOTAL_PAY"))) {
                    newMap.put(i++, map.get("TOTAL_PAY"));
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("PAYCODE") && !"".equals(map.get("PAYCODE"))) {
                    newMap.put(i++, map.get("PAYCODE").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("SUSU") && !"".equals(map.get("SUSU"))) {
                    newMap.put(i++, map.get("SUSU"));
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("SUSU_PAY") && !"".equals(map.get("SUSU_PAY"))) {
                    newMap.put(i++, map.get("SUSU_PAY"));
                } else {
                    newMap.put(i++, "");
                }

                newList.add(newMap);
            }
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("AdmUserInfo");
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("ADMIN_ROLE",loginInfo.get("ADMIN_ROLE")); 
        
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        
		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));

        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
    }

}
