package web.mocktest.offerer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.paging.Paging;
import web.mocktest.offerer.service.OffererService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/offerer/refund")
@Controller
public class OffererRefndController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private OffererService offererservice;

    /**
     * @Method Name : offererList
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list.do")
    public String offererList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        /* 페이징 */
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHEXAMTYPE",request.getParameter("SEARCHEXAMTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //결제 상태 코드 설정
        List<String> slist = new ArrayList<String>();
        slist.add("1");
        slist.add("2");
        slist.add("3");
        params.put("PAYSTTUS_LIST", slist);

        List<HashMap<String, Object>> list = offererservice.offererList(params);
        int listCount = offererservice.offererListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalCount",listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("params", params);

        return "mocktest/offerer/refund/list";
    }

    /**
     * @Method Name : statsOffererList
     * @작성일 : 2013. 10.
     * @Method 설명 : 응시자목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/excel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHEXAMTYPE",request.getParameter("SEARCHEXAMTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //결제 상태 코드 설정
        List<String> slist = new ArrayList<String>();
        slist.add("1");
        slist.add("2");
        slist.add("3");
        params.put("PAYSTTUS_LIST", slist);

        int listCount = offererservice.offererListCount(params);
        /* 페이징 */
        int startNo = 0;
        int endNo = listCount;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = offererservice.offererList(params);

        //		List<HashMap<String, String>> list = statsservice.offererListExcel(params);
        String excelName = "신청자 명단";
        List<String> headerList = new ArrayList<String>();
        headerList.add("주문번호");
        headerList.add("시험구분");
        headerList.add("이름");
        headerList.add("아이디");
        headerList.add("응시번호");
        headerList.add("연락처");
        headerList.add("신청구분");
        headerList.add("등록일");
        headerList.add("상품명");
        headerList.add("결제금액");
        headerList.add("결제상태");
        headerList.add("결제구분");
        headerList.add("결제일");
        headerList.add("응시표출력");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, Object> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            newMap.put(i++, map.get("ORDERNO").toString()); //주문번호
            int examtype = Integer.parseInt(String.valueOf(map.get("EXAMTYPE")));  //시험구분
            if(examtype == 0){
                newMap.put(i++, "온라인");
            }else if(examtype == 1){
                newMap.put(i++, "오프라인");
            }else{
                newMap.put(i++, " ");
            }
            newMap.put(i++, map.get("USER_NM").toString()); //이름
            newMap.put(i++, map.get("USER_ID").toString()); //아이디
            newMap.put(i++, map.get("IDENTYID").toString());	 //응시번호
            newMap.put(i++, map.get("PHONE_NO").toString());	 //연락처
            int receipttype = Integer.parseInt(String.valueOf(map.get("RECEIPTTYPE")));  //신청구분
            if(receipttype == 0){
                newMap.put(i++, "온라인접수");
            }else if(receipttype == 1){
                newMap.put(i++, "방문접수");
            }else if(receipttype == 2){
                newMap.put(i++, "데스크접수");
            }else{
                newMap.put(i++, " ");
            }
            newMap.put(i++, map.get("CTIME").toString()); //등록일
            newMap.put(i++, map.get("MOCKNAME").toString()); //상품명
            newMap.put(i++, String.valueOf(map.get("PAYMENTAMOUNT"))); //결제금액
            int paymentstate = Integer.parseInt(String.valueOf(map.get("PAYMENTSTATE")));  //결제상태
            if(paymentstate == 0){
                newMap.put(i++, "대기중");
            }else if(paymentstate == 1){
                newMap.put(i++, "결제완료");
            }else if(paymentstate == 2){
                newMap.put(i++, "환불완료");
            }else if(paymentstate == 3){
                newMap.put(i++, "취소완료");
            }else{
                newMap.put(i++, " ");
            }
            int paymenttype = Integer.parseInt(String.valueOf(map.get("PAYMENTTYPE")));  //결제구분
            if(paymenttype == 0){ //결제구분
                newMap.put(i++, "카드");
            }else if(paymenttype == 1){
                newMap.put(i++, "현금");
            }else if(paymenttype == 2){
                newMap.put(i++, "예금");
            }else{
                newMap.put(i++, " ");
            }
            newMap.put(i++, map.get("PCTIME").toString()); //결제일
            int printflag = Integer.parseInt(String.valueOf(map.get("PRINTFLAG")));  //응시표출력
            if(printflag == 0){ //응시표출력
                newMap.put(i++, "미출력");
            }else if(printflag == 1){
                newMap.put(i++, "출력");
            }else{
                newMap.put(i++, " ");
            }

            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
    }

    /**
     * @Method Name : offererDelete
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 주문삭제
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererDelete.do")
    public String offererDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("ORDERNO",request.getParameter("ORDERNO"));
        params.put("TID",request.getParameter("TID"));

        offererservice.offererDelete(params);

        model.addAttribute("params", params);

        return "forward:/offerer/refund/list.do";
    }

    /**
     * @Method Name : returnMsg
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 등록 오프라인 체크 메서드
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/returnMsg.do")
    public String returnMsg(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("returnCode", request.getParameter("returnCode"));
        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        model.addAttribute("params", params);

        return "mocktest/offerer/returnMsg";
    }

    /**
     * @Method Name : offererRefund
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 환불폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererRefund.do")
    public String offererRefund(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), ""));

        model.addAttribute("view", offererservice.offererView(params));
        model.addAttribute("subjectview", offererservice.offererSubjectView(params));

        model.addAttribute("params",params);

        return "mocktest/offerer/refund/offererRefund";
    }

    /**
     * @Method Name : offererRefundSave
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 환불 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererRefundSave.do")
    public String offererRefundSave(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("TID",request.getParameter("TID"));
        params.put("REFUNDAMOUNT",request.getParameter("REFUNDAMOUNT"));
        params.put("REFUNDTYPE","2");
        params.put("DEPOSITBANK",request.getParameter("DEPOSITBANK"));
        params.put("ACCOUNTNUMBER",request.getParameter("ACCOUNTNUMBER"));
        params.put("REFUNDREASON",request.getParameter("REFUNDREASON"));

        offererservice.offererRefundInsert(params);

        model.addAttribute("params",params);

        return "forward:/offerer/refund/list.do";
    }

    /**
     * @Method Name : offererRefundCancel
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 환불 취소 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererRefundCancel.do")
    public String offererRefundCancel(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("TID", CommonUtil.isNull(request.getParameter("TID")));
        params.put("IDENTYID", CommonUtil.isNull(request.getParameter("IDENTYID")));

        offererservice.offererRefundDelete(params);

        model.addAttribute("params",params);

        return "forward:/offerer/refund/list.do";
    }

    /**
     * @Method Name : offererRefundCancel
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 환불 취소 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererCardPaymentDelete.do")
    public String offererCardPaymentDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("IDENTYID", CommonUtil.isNull(request.getParameter("IDENTYID")));

        offererservice.offererPaymentCardDelete(params);

        model.addAttribute("params",params);

        return "forward:/offerer/refund/list.do";
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
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("PRINTUSER",loginInfo.get("USER_ID"));
        params.put("USERNAME", loginInfo.get("USER_NM"));
        params.put("USERID", loginInfo.get("USER_ID"));

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
    }

}
