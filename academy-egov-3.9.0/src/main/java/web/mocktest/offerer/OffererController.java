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

@RequestMapping(value="/offerer")
@Controller
public class OffererController {

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
    @RequestMapping(value="/offererList.do")
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
        params.put("MOCKCODE",CommonUtil.isNull(request.getParameter("MOCKCODE"), ""));

        //결제 상태 코드 설정
        List<String> slist = new ArrayList<String>();
        slist.add("0");
        slist.add("1");
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

        return "mocktest/offerer/offererList";
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
    @RequestMapping(value="/offererListExcel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHEXAMTYPE",request.getParameter("SEARCHEXAMTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("MOCKCODE",CommonUtil.isNull(request.getParameter("MOCKCODE"), ""));

        //결제 상태 코드 설정
        List<String> slist = new ArrayList<String>();
        slist.add("0");
        slist.add("1");
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
        headerList.add("직렬");
        headerList.add("지역");
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
            newMap.put(i++, map.get("CLASSSERIESCODENM").toString()); //상품명
            newMap.put(i++, map.get("AREANM").toString()); //상품명
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
            newMap.put(i++, map.get("PCREATETIME").toString()); //결제일
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
     * @Method Name : offererWrite
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 등록폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererWrite.do")
    public String offererWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        model.addAttribute("params",params);

        return "mocktest/offerer/offererWrite";
    }

    /**
     * @Method Name : offererWriteMoui
     * @작성일 : 2013. 10.
     * @Method 설명 : 신청자관리 등록폼 품목선택 팝업
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererWriteMoui.pop")
    public String offererWriteMoui(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SRCHMOCCODE",CommonUtil.isNull(request.getParameter("SRCHMOCCODE"), ""));

        List<HashMap<String, String>> mouiList = offererservice.offererMouiInfoList(params);
        model.addAttribute("AreaList", offererservice.getAreaList(params));
        
        if(null != mouiList && mouiList.size()>1) {
            /* 페이징 */
            int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
            int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
            int startNo = (currentPage - 1) * pageRow;
            int endNo = startNo + pageRow;
            params.put("startNo", String.valueOf(startNo));
            params.put("endNo", String.valueOf(endNo));
            /* 페이징 */

            mouiList = offererservice.offererMouiInfoList(params);
            int listCount = offererservice.offererMouiInfoListCount(params);
            String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

            model.addAttribute("list", mouiList);
            model.addAttribute("totalCount",listCount);
            model.addAttribute("pageRow", pageRow);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
            model.addAttribute("pagingHtml", pagingHtml);

            model.addAttribute("params",params);

            return "mocktest/offerer/offererExamList_pop";
        } else {
            model.addAttribute("list", offererservice.offererMouiList(params));
            model.addAttribute("classlist", offererservice.offererMouiClassInfo(params));
            model.addAttribute("clslist", offererservice.offererClsClsList(params));
            model.addAttribute("params",params);

            return "mocktest/offerer/offererWriteMoui";
        }
    }

    /**
     * @Method Name : offererSave
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자 관리 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererSave.do")
    public String offererSave(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("EXAMTYPE",request.getParameter("EXAMTYPE"));
        params.put("EXAMCODE",request.getParameter("EXAMCODE"));
        params.put("CLASSCODE",request.getParameter("CLASSCODE"));
        params.put("CLASSSERIESCODE",request.getParameter("CLASSSERIESCODE"));
        params.put("AREA", request.getParameter("AREACODE"));
        params.put("EXAMYEAR",request.getParameter("EXAMYEAR"));
        params.put("EXAMROUND",request.getParameter("EXAMROUND"));
        params.put("RECEIPTTYPE",request.getParameter("RECEIPTTYPE"));
        params.put("PAYMENTSTATE",request.getParameter("PAYMENTSTATE"));
        params.put("PAYMENTTARGETAMOUNT",request.getParameter("PAYMENTTARGETAMOUNT"));
        params.put("ADDDISCOUNTRATIO",request.getParameter("ADDDISCOUNTRATIO"));
        params.put("ADDDISCOUNTAMOUNT",request.getParameter("ADDDISCOUNTAMOUNT"));
        params.put("PAYMENTAMOUNT",request.getParameter("PAYMENTAMOUNT"));
        params.put("USER_NM",request.getParameter("USER_NM"));
        params.put("PHONE_NO",request.getParameter("PHONE_NO"));
        params.put("ADDDISCOUNTREASON",request.getParameter("ADDDISCOUNTREASON"));
        params.put("PAYMENTTYPE",request.getParameter("PAYMENTTYPE"));
        params.put("CARDKIND",request.getParameter("CARDKIND"));
        params.put("NOTE",request.getParameter("NOTE"));
        params.put("USER_ID",request.getParameter("USER_ID"));

        String ORDERNO; // 주문번호
        String IDENTYID;    // 응시번호

        // 주문번호 : 온라인/오프라인 구분 (1자리) + 년도(2자리)
        ORDERNO = request.getParameter("EXAMYEAR").substring(request.getParameter("EXAMYEAR").length()-1, request.getParameter("EXAMYEAR").length());
        params.put("TEMP_ORDERNO", ORDERNO);

        // 응시번호 - 직종번호
        IDENTYID = request.getParameter("CLASSSERIESCODE");
        params.put("TEMP_IDENTYID", IDENTYID);

        // 결제상태 및 프린트출력 기본값 setting
        params.put("PAYMENTSTATE", "1");

        String[] CHOICEITEMARR = request.getParameterValues("CHOICEITEMARR");
        params.put("CHOICEITEMARR", CHOICEITEMARR);

        offererservice.offererInsert(params);

        model.addAttribute("params", params);

        return "redirect:/offerer/offererList.do";
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
     * @Method Name : offererView
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 주문상세
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererView.do")
    public String offererView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        model.addAttribute("view", offererservice.offererView(params));
        model.addAttribute("subjectview", offererservice.offererSubjectView(params));

        model.addAttribute("params",params);

        return "mocktest/offerer/offererView";
    }

    /**
     * @Method Name : offererModify
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 주문상품 수정폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererModify.do")
    public String offererModify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("SEARCHSDATE",CommonUtil.isNull(request.getParameter("SEARCHSDATE"), ""));
        params.put("SEARCHEDATE",CommonUtil.isNull(request.getParameter("SEARCHEDATE"), ""));
        params.put("SEARCHRECEIPTTYPE",request.getParameter("SEARCHRECEIPTTYPE"));
        params.put("SEARCHPAYMENT",request.getParameter("SEARCHPAYMENT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        model.addAttribute("view", offererservice.offererView(params));
        model.addAttribute("subjectview", offererservice.offererSubjectView(params));

        model.addAttribute("params",params);

        return "mocktest/offerer/offererModify";
    }

    /**
     * @Method Name : offererModifyMoui
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 주문수정폼 품목선택 팝업
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererModifyMoui.pop")
    public String offererModifyMoui(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("MOCKCODE",request.getParameter("MOCKCODE"));

        if(request.getParameter("SEARCHYEAR")!=null && !"".equals(request.getParameter("SEARCHYEAR"))){
            params.put("SEARCHYEAR", request.getParameter("SEARCHYEAR"));
            params.put("SEARCHROUND", request.getParameter("SEARCHROUND"));
        }

        List<HashMap<String, String>> viewlist = offererservice.offererView(params);
        List<HashMap<String, String>> subjectview = offererservice.offererSubjectView(params);
        List<HashMap<String, String>> list = offererservice.offererModifyMouiList(params);
        List<HashMap<String, String>> classlist = offererservice.offererModifyMouiClassInfo(params);

        model.addAttribute("AreaList", offererservice.getAreaList(params));

        model.addAttribute("view", viewlist);
        model.addAttribute("subjectview", subjectview);
        model.addAttribute("list", list);
        model.addAttribute("classlist", classlist);
        model.addAttribute("searchsel", offererservice.offererMouiSearchSel(params));
        model.addAttribute("clslist", offererservice.offererClsClsList(params));
        model.addAttribute("params", params);

        return "mocktest/offerer/offererModifyMoui";
    }

    /**
     * @Method Name : offererModifyUpdate
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererModifyUpdate.do")
    public String offererModifyUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        // HashMap<String, String> params = new  HashMap<String, String>();
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("EXAMTYPE",request.getParameter("EXAMTYPE"));
        params.put("RECEIPTTYPE", request.getParameter("RECEIPTTYPE"));
        params.put("PAYMENTTYPE",request.getParameter("PAYMENTTYPE"));
        params.put("NOTE",request.getParameter("NOTE"));
        params.put("IDENTYID",request.getParameter("IDENTYID"));
        
        //경찰은 시험내용 변경 수정 없이 기본정보만 수정
        params.put("PAYMENTSTATE", request.getParameter("PAYMENTSTATE"));
        params.put("PAYMENTTARGETAMOUNT",request.getParameter("PAYMENTTARGETAMOUNT"));
        params.put("ADDDISCOUNTRATIO",request.getParameter("ADDDISCOUNTRATIO"));
        params.put("ADDDISCOUNTAMOUNT",request.getParameter("ADDDISCOUNTAMOUNT"));
        params.put("ADDDISCOUNTREASON",request.getParameter("ADDDISCOUNTREASON"));
        params.put("PAYMENTAMOUNT",request.getParameter("PAYMENTAMOUNT"));
        params.put("CARDKIND",request.getParameter("CARDKIND"));
        params.put("ORDERNO", request.getParameter("ORDERNO"));
        params.put("EXAMCODE",request.getParameter("EXAMCODE"));
        params.put("CLASSCODE",request.getParameter("CLASSCODE"));
        params.put("CLASSSERIESCODE",request.getParameter("CLASSSERIESCODE"));
        params.put("EXAMYEAR",request.getParameter("EXAMYEAR"));
        params.put("EXAMROUND",request.getParameter("EXAMROUND"));
        
		
		String[] CHOICEITEMARR = request.getParameterValues("CHOICEITEMARR");
        params.put("CHOICEITEMARR", CHOICEITEMARR);
        
        // offererservice.offererModifyUpdate(params);
        offererservice.offererUpdate(params);
        
        model.addAttribute("params", params);

        return "forward:/offerer/offererList.do";
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

        params.put("IDENTYID", request.getParameter("IDENTYID"));
        params.put("ORDERNO", request.getParameter("ORDERNO"));
        params.put("TID", request.getParameter("TID"));

        offererservice.offererDelete(params);

        model.addAttribute("params", params);

        return "forward:/offerer/offererList.do";
    }

    /**
     * @Method Name : offererPrintDelete
     * @작성일 : 2013. 09.
     * @Method 설명 : 신청자관리 응시표출력 복원 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/offererPrintDelete.do")
    public String offererPrintDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        offererservice.offererPrintDelete(params);

        model.addAttribute("params", params);

        return "forward:/offerer/offererModify.do";
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
    public String offererPrint(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("IDENTYID",request.getParameter("IDENTYID"));
        params.put("MOCKCODE",request.getParameter("MOCKCODE"));
        offererservice.offererPrintUpdate(params);

        List<HashMap<String, String>> viewslist = offererservice.offererSubjectView(params);
        model.addAttribute("view", offererservice.offererView(params));
        model.addAttribute("viewslist", viewslist);

        model.addAttribute("params", params);

        return "mocktest/offerer/offererPrint";
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
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("PRINTUSER", loginInfo.get("USER_ID"));
        params.put("USERNAME", loginInfo.get("USER_NM"));
        params.put("USERID", loginInfo.get("USER_ID"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

}
