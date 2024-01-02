package com.willbes.web.mocktest.lectureFees;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelRead;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.mocktest.lectureFees.excel.ExcelDownloadView2;
import com.willbes.web.mocktest.lectureFees.service.LectureFeesService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/lectureFees")
@Controller
public class LectureFeesController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="excelRead")
    ExcelRead excelUtil;

    @Autowired
    private LectureFeesService lectureFeesService;

    /**
     * @Method Name : LectureFeesService
     * @작성일 : 2013. 9.
     * @Method 설명 : 강사료정산 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/lectureFeesList.do")
    public String lectureFeesList(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new  HashMap<String, String>();
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
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        List<HashMap<String, String>> list = lectureFeesService.lectureFeesList(params);
        int listCount = lectureFeesService.lectureFeesListCount(params);
        params.put("totalCount",String.valueOf(listCount));

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("params", params);

        return "mocktest/lectureFees/lectureFeesList";
    }

    /**
     * @Method Name : LectureFeesService
     * @작성일 : 2013. 9.
     * @Method 설명 : 강사료정산 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/lectureFeesExcelDownLoad.do")
    public void lectureFeesExcelDownLoad(ModelMap model, HttpServletRequest request , HttpServletResponse res) throws Exception {

        HashMap<String, String> params = new  HashMap<String, String>();
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
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("PROFCODE",CommonUtil.isNull(request.getParameter("PROFCODE"), ""));
        params.put("TMEMBERNAME",CommonUtil.isNull(request.getParameter("TMEMBERNAME"), ""));

        ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>)lectureFeesService.lectureFeesDetailExcelList(params);
        ArrayList<HashMap<String, String>> refundlist = (ArrayList<HashMap<String, String>>)lectureFeesService.lectureFeesRefundDetailExcelList(params);

        String excelName = params.get("TMEMBERNAME")+"_강사료정산";
        List<String> headerList = new ArrayList<String>();
        headerList.add("이름/ID");
        headerList.add("응시형태");
        headerList.add("모의고사명");
        headerList.add("신청일");
        headerList.add("결제금액");
        headerList.add("강사료지급률");
        headerList.add("입금구분");
        headerList.add("수수료");
        headerList.add("강사지급액");

        double cardTotal = 0; //카드 입금 총합
        double realTimeTotal=0; //계좌 입금 총합
        double cashTotal=0; //현금 입금 총합
        double vaconTotal=0; //가상계좌 입금 총합

        double cardChargeTotal = 0; //카드 수수료 총합
        double realTimeChargeTotal=0; //계좌이체 수수료 총합
        double vaconChargeTotal=0; //가상계좌이체 수수료 총합

        double supplyMoneyTotal = 0; //공급가액
        double moneyTotal = 0; //정산합계
        double TechTotal = 0; //강사료 합계
        double onechenTotal = 0; //원천세 합계
        double juminTotal = 0; //주민세 합계

        double realMoneyTotal = 0; //실지급액

        for(HashMap<String, String> map : list) {
            //공급가액
            supplyMoneyTotal = supplyMoneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //정산
            moneyTotal = moneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //강사료
            //double temp = Double.parseDouble(map.get("PROPERCENT"))/100; 2015.05.15 사용하는 곳이 없어 주석처리
            TechTotal =   TechTotal +((Integer.parseInt(String.valueOf(map.get("MONEY")))-Integer.parseInt(String.valueOf(map.get("COMMITION"))) )*(Double.parseDouble(map.get("PROPERCENT"))/100)) ;
            onechenTotal = onechenTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.03);
            juminTotal = juminTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(map.get("PAYMENTTYPE")).equals("카드")) {
                cardTotal=cardTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                cardChargeTotal=cardChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("계좌이체") ){
                realTimeTotal=realTimeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                realTimeChargeTotal=realTimeChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("현금")) {
                cashTotal=cashTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("가상계좌")){
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
        }
        //정산 합계 - 전체 금액에서 수수료를 뺀 값으로 수정 - 2013.10.16
        moneyTotal = moneyTotal -(cardChargeTotal+realTimeChargeTotal+vaconChargeTotal);
        //실지급액
        realMoneyTotal = TechTotal - (onechenTotal+juminTotal);
        //10원단위 절삭
        int int_realMoneyTotal = (int)(realMoneyTotal/100)*100;

        DecimalFormat df = new DecimalFormat("#,##0");

        ExcelDownloadView2 excel = new ExcelDownloadView2();

        String[] colname = {"이름/ID","응시형태","모의고사명","신청일","결제금액","강사료지급률%","입금구분","수수료" , "강사지급액"};
        String[] keys = {"USRNAMEID","EXAMTYPE","MOCKNAME","REG_DT","MONEY","PROPERCENT","PAYMENTPARAM","COMMITION","ALLOWANCE"};
        int listNext = list.size()+2;
        excel.setFIRSTLINE(1);
        excel.makeMerge(0,0, 0, 0, 0, colname.length-1,params.get("TMEMBERNAME")+" 강사님의 수강료지급 현황입니다."); //병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"신용카드 입금 : "+df.format(cardTotal)+" 원");   listNext++; //병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"계좌이체 입금 : "+df.format(realTimeTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"            현금 : "+df.format(cashTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"가상계좌 입금 : "+df.format(vaconTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"       공급가액 : "+df.format(supplyMoneyTotal)+" 원"); listNext++;//병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,""); listNext++;//공백 병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"신용카드 수수료 : "+df.format(cardChargeTotal)+" 원");  listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"계좌이체 수수료 : "+df.format(realTimeChargeTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"가상계좌 수수료 : "+df.format(vaconChargeTotal)+" 원"); listNext++;//병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"");   listNext++;//공백 병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"정산합계 : "+df.format(moneyTotal)+" 원"); 	listNext++;//병합
        // excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"정산합계 : "+moneyTotal+" 원"); 	listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"강사료 : "+df.format(TechTotal)+" 원"); 		listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"원천세 : "+df.format(onechenTotal)+" 원"); 	listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"주민세 : "+df.format(juminTotal)+" 원"); 		listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"실지급액 : "+df.format(Math.floor(int_realMoneyTotal))+" 원"); 		listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"");   listNext++;//공백 병합

        excel.makeMerge(listNext,0, listNext, 0, listNext, colname.length-1,params.get("TMEMBERNAME")+" 강사님의 환불 현황입니다.");listNext++; //병합
        // excel.makeMerge2(0, listNext, 0, 0, 8,params.get("TMEMBERNAME")+" AAAAAAAAAAAAAAA"); //병합
        excel.setrowName(colname); //컬럼명 들어감
        excel.setkeys(keys); //받을 키값
        excel.setfileName(excelName); //파일이름
        //  excel.setTITLECOLOR((short)11);  //타이블 색상
        excel.setCONTENTCOLOR((short)0); //데이터 색상
        res= excel.DbtoExcel(list, false, res);

        excel.setFIRSTLINE(listNext);
        res = excel.DbtoExcel(refundlist, false, res);

        listNext= listNext + refundlist.size()+2;

        // 환불현황
        double recardTotal = 0; //카드 입금 총합
        double recashTotal=0; //현금 입금 총합
        double rerealTimeTotal=0; //계좌 입금 총합
        double revaconTotal=0; //가상계좌 입금 총합

        double recardChargeTotal = 0; //카드 수수료 총합
        double rerealTimeChargeTotal=0; //계좌이체 수수료 총합
        double revaconChargeTotal=0; //가상계좌이체 수수료 총합

        double remoneyTotal = 0; //정산합계
        double reTechTotal = 0; //강사료 합계
        double reonechenTotal = 0; //원천세 합계
        double rejuminTotal = 0; //주민세 합계

        double rerealMoneyTotal = 0; //실지급액

        for(HashMap<String, String> map : refundlist) {
            //정산
            remoneyTotal = remoneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //강사료
            reTechTotal = reTechTotal+Integer.parseInt(String.valueOf(map.get("ALLOWANCE")));
            reonechenTotal = reonechenTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.03);
            rejuminTotal = rejuminTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(map.get("PAYMENTTYPE")).equals("카드")) {
                recardTotal=recardTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                recardChargeTotal=recardChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("계좌이체")){
                rerealTimeTotal=rerealTimeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                rerealTimeChargeTotal=rerealTimeChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("현금")) {
                recashTotal=recashTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("가상계좌")){
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
        }
        //실지급액
        rerealMoneyTotal = reTechTotal - (reonechenTotal+rejuminTotal);
        //int int_rerealMoneyTotal = (int)rerealMoneyTotal; 2015.05.15 사용하는 곳이 없어 주석처리

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"신용카드 입금 : "+df.format(recardTotal)+" 원");   listNext++; //병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"가상계좌 입금 : "+df.format(revaconTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"  실시간  입금 : "+df.format(rerealTimeTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"            현금 : "+df.format(recashTotal)+" 원"); listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"환불 총금액 : "+df.format(remoneyTotal)+" 원"); listNext++;//병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,""); listNext++;//공백 병합

        // excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"신용카드 수수료 : "+df.format(recardChargeTotal)+" 원");  listNext++;//병합
        // excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"가상계좌 수수료 : "+df.format(revaconChargeTotal)+" 원"); listNext++;//병합
        // excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"실시간계좌 수수료 : "+df.format(rerealTimeChargeTotal)+" 원");  listNext++;//병합

        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"");   listNext++;//공백 병합

        // excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"정산합계 : "+df.format(remoneyTotal)+" 원"); 	listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"강사료 : "+df.format(reTechTotal)+" 원"); 		listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"원천세 : "+df.format(reonechenTotal)+" 원"); 	listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"주민세 : "+df.format(rejuminTotal)+" 원"); 		listNext++;//병합
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"실환불액 : "+df.format(rerealMoneyTotal*-1)+" 원"); 		listNext++;//병합
        int silTotalMomey = (int)((int_realMoneyTotal - (rerealMoneyTotal*-1))/100)*100;
        excel.makeMerge2(listNext,0, listNext, 0, listNext, colname.length-1,"실 지급액 : "+df.format(silTotalMomey)+" 원"); 		listNext++;//병합

        excel.excelDownLoad(res);
    }

    /**
     * @Method Name : lectureFeesDetailView
     * @작성일 : 2013. 9.
     * @Method 설명 : 강사료정산 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/lectureFeesDetailView.do")
    public String lectureFeesDetailView(ModelMap model, HttpServletRequest request , HttpServletResponse res) throws Exception {

        HashMap<String, String> params = new  HashMap<String, String>();
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
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("PROFCODE",CommonUtil.isNull(request.getParameter("PROFCODE"), ""));
        params.put("TMEMBERNAME",CommonUtil.isNull(request.getParameter("TMEMBERNAME"), ""));

        ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>)lectureFeesService.lectureFeesDetailExcelList(params);
        ArrayList<HashMap<String, String>> refundlist = (ArrayList<HashMap<String, String>>)lectureFeesService.lectureFeesRefundDetailExcelList(params);

        double cardTotal = 0; //카드 입금 총합
        double realTimeTotal=0; //계좌 입금 총합
        double cashTotal=0; //현금 입금 총합
        double vaconTotal=0; //가상계좌 입금 총합

        double cardChargeTotal = 0; //카드 수수료 총합
        double realTimeChargeTotal=0; //계좌이체 수수료 총합
        double vaconChargeTotal=0; //가상계좌이체 수수료 총합

        double moneyTotal = 0; //정산합계
        double supplyMoneyTotal = 0; //공급가액  2013.10.16  추가
        double TechTotal = 0; //강사료 합계
        double onechenTotal = 0; //원천세 합계
        double juminTotal = 0; //주민세 합계

        double realMoneyTotal = 0; //실지급액

        for(HashMap<String, String> map : list) {

            //공급가액 2013.10.16  추가
            supplyMoneyTotal = supplyMoneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //정산
            moneyTotal = moneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //강사료
            //double temp = Double.parseDouble(map.get("PROPERCENT"))/100; 2015.05.15 사용하는 곳이 없어 주석처리
            //2013.10.16  수정
            TechTotal =   TechTotal +((Integer.parseInt(String.valueOf(map.get("MONEY")))-Integer.parseInt(String.valueOf(map.get("COMMITION"))) )*(Double.parseDouble(map.get("PROPERCENT"))/100)) ;
            onechenTotal = onechenTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.03);
            juminTotal = juminTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(map.get("PAYMENTTYPE")).equals("카드")) {
                cardTotal=cardTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                cardChargeTotal=cardChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("계좌이체")){
                realTimeTotal=realTimeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                realTimeChargeTotal=realTimeChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("현금")) {
                cashTotal=cashTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("가상계좌")){
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
        }
        //정산 합계 - 전체 금액에서 수수료를 뺀 값으로 수정 - 2013.10.16
        moneyTotal = moneyTotal -(cardChargeTotal+realTimeChargeTotal+vaconChargeTotal);
        //실지급액 2013.10.16  수정,
        //realMoneyTotal = TechTotal - (onechenTotal+juminTotal)-(cardChargeTotal+realTimeChargeTotal+vaconChargeTotal);
        realMoneyTotal = TechTotal - (onechenTotal+juminTotal);
        //10원단위 절삭
        int int_realMoneyTotal = (int)(realMoneyTotal/100)*100;
        DecimalFormat df = new DecimalFormat("#,##0");

        HashMap<String, Object> lfMap = new HashMap<String, Object>();
        lfMap.put("cardTotal", df.format(cardTotal)+" 원");
        lfMap.put("realTimeTotal", df.format(realTimeTotal)+" 원");
        lfMap.put("cashTotal", df.format(cashTotal)+" 원");
        lfMap.put("vaconTotal", df.format(vaconTotal)+" 원");
        lfMap.put("supplyMoneyTotal", df.format(supplyMoneyTotal)+" 원");

        lfMap.put("cardChargeTotal", df.format(cardChargeTotal)+" 원");
        lfMap.put("realTimeChargeTotal", df.format(realTimeChargeTotal)+" 원");
        lfMap.put("vaconChargeTotal", df.format(vaconChargeTotal)+" 원");

        lfMap.put("moneyTotal", df.format(moneyTotal)+" 원");
        lfMap.put("TechTotal", df.format(TechTotal)+" 원");
        lfMap.put("onechenTotal", df.format(onechenTotal)+" 원");
        lfMap.put("juminTotal", df.format(juminTotal)+" 원");
        //		lfMap.put("realMoneyTotal", realMoneyTotal+" 원");
        lfMap.put("realMoneyTotal", df.format(int_realMoneyTotal)+" 원"); //2013.10.16  수정
        lfMap.put("totalCount", df.format(list.size())+" 원");

        // 환불현황
        double recardTotal = 0; //카드 입금 총합
        double rerealTimeTotal=0; //계좌 입금 총합
        double recashTotal=0; //현금 입금 총합
        double revaconTotal=0; //가상계좌 입금 총합

        double recardChargeTotal = 0; //카드 수수료 총합
        double rerealTimeChargeTotal=0; //계좌이체 수수료 총합
        double revaconChargeTotal=0; //가상계좌이체 수수료 총합

        double remoneyTotal = 0; //환불 총금액
        double reTechTotal = 0; //강사료 합계
        double reonechenTotal = 0; //원천세 합계
        double rejuminTotal = 0; //주민세 합계

        double rerealMoneyTotal = 0; //실지급액

        for(HashMap<String, String> map : refundlist) {
            //정산
            remoneyTotal = remoneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //강사료
            reTechTotal = reTechTotal+Integer.parseInt(String.valueOf(map.get("ALLOWANCE")));
            reonechenTotal = reonechenTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.03);
            rejuminTotal = rejuminTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(map.get("PAYMENTTYPE")).equals("카드")) {
                recardTotal=recardTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                recardChargeTotal=recardChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("계좌이체")){
                rerealTimeTotal=rerealTimeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                rerealTimeChargeTotal=rerealTimeChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("현금")) {
                recashTotal=recashTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("가상계좌")){
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
        }
        //실지급액
        rerealMoneyTotal = reTechTotal - (reonechenTotal+rejuminTotal);
        // int int_rerealMoneyTotal = (int)(rerealMoneyTotal/100)*100; //2013.10.16  추가, 2015.05.15 사용하는 곳이 없어 주석처리

        HashMap<String, Object> lfRefundMap = new HashMap<String, Object>();
        lfRefundMap.put("recardTotal", df.format(recardTotal)+" 원");
        lfRefundMap.put("rerealTimeTotal", df.format(rerealTimeTotal)+" 원");
        lfRefundMap.put("recashTotal", df.format(recashTotal)+" 원");
        lfRefundMap.put("revaconTotal", df.format(revaconTotal)+" 원");
        lfRefundMap.put("remoneyTotal", df.format(remoneyTotal)+" 원");

        lfRefundMap.put("reTechTotal", df.format(reTechTotal)+" 원");
        lfRefundMap.put("rejuminTotal", df.format(rejuminTotal)+" 원");
        lfRefundMap.put("reonechenTotal", df.format(reonechenTotal)+" 원");
        lfRefundMap.put("rerealMoneyTotal", df.format((rerealMoneyTotal)*-1)+" 원");
        int silTotalMomey = (int)((int_realMoneyTotal - (rerealMoneyTotal*-1))/100)*100;
        lfRefundMap.put("silMoenyTotal", df.format(silTotalMomey)+" 원");
        lfRefundMap.put("totalCount", df.format(refundlist.size())+" 원");

        model.addAttribute("lfMap", lfMap);
        model.addAttribute("lfRefundMap", lfRefundMap);
        model.addAttribute("list", list);
        model.addAttribute("refundlist", refundlist);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);

        model.addAttribute("params", params);

        return "mocktest/lectureFees/lectureFeesDetailView";
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
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USERNAME", loginInfo.get("USERNAME"));
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
