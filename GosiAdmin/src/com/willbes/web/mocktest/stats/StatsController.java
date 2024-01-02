package com.willbes.web.mocktest.stats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.mocktest.mouigosa.service.MouigosaService;
import com.willbes.web.mocktest.offExamReg.service.OffExamRegService;
import com.willbes.web.mocktest.stats.service.StatsService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/stats")
@Controller
public class StatsController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private StatsService statsservice;
    @Autowired
    private MouigosaService mouigosaService;

    private OffExamRegService offexmresService;
    /**
     * @Method Name : statsPersonList
     * @작성일 : 2013. 10.
     * @Method 설명 : 개인별
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsPersonList.do")
    public String statsPersonList(ModelMap model, HttpServletRequest request) throws Exception {
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

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        List<HashMap<String, String>> list = statsservice.statsList(params);
        int listCount = statsservice.statsListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list",list);
        model.addAttribute("totalCount",listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml",pagingHtml);
        model.addAttribute("params",params);

        return "mocktest/stats/personList";
    }

    /**
     * @Method Name : statsPersonView
     * @작성일 : 2013. 10.
     * @Method 설명 : 개인별
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsPersonView.do")
    public String statsPersonView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("IDENTYID", request.getParameter("IDENTYID"));
        params.put("MOCKCODE", request.getParameter("MOCKCODE"));

        int CHECKCNT = statsservice.statsTotalCnt(params);
        if(CHECKCNT<1){
            model.addAttribute("returnData","back");
        }else{

            List<HashMap<String, String>> totalinfolist = statsservice.statsTotalInfoList(params);
            params.put("CLASSCODE", totalinfolist.get(0).get("CLASSCODE"));
            params.put("CLASSSERIESCODE", totalinfolist.get(0).get("CLASSSERIESCODE"));

            /* 그래프 1 */
            List<HashMap<String, String>> totalinfo1 = statsservice.statsTotalInfo1(params);
            HashMap<String, String> totalinfo1_map = new  HashMap<String, String>();
            for(int i=0; i<totalinfo1.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo1.get(i);
                if("총점".equals(map.get("TIT"))){
                    totalinfo1_map.put("T_O", map.get("VAL1"));
                    totalinfo1_map.put("T_A", map.get("VAL2"));
                }
                if("평균".equals(map.get("TIT"))){
                    totalinfo1_map.put("PA_O", map.get("VAL1"));
                    totalinfo1_map.put("PA_A", map.get("VAL2"));
                }
                if("전체평균".equals(map.get("TIT"))){
                    totalinfo1_map.put("TA_O", map.get("VAL1"));
                    totalinfo1_map.put("TA_A", map.get("VAL2"));
                }
                if("석차".equals(map.get("TIT"))){
                    totalinfo1_map.put("R_O", map.get("VAL1"));
                    totalinfo1_map.put("R_A", map.get("VAL2"));
                }
                if("응시인원".equals(map.get("TIT"))){
                    totalinfo1_map.put("TO_O", map.get("VAL1"));
                    totalinfo1_map.put("TO_A", map.get("VAL2"));
                }
                if("상위점수".equals(map.get("TIT"))){
                    totalinfo1_map.put("TO_SO", map.get("VAL1"));
                    totalinfo1_map.put("TO_SA", map.get("VAL2"));
                }
            }
            totalinfo1_map.put("TOPPER_O", String.valueOf(Math.round((Float.valueOf(totalinfo1_map.get("R_O").toString()) / Float.valueOf(totalinfo1_map.get("TO_O").toString())) * 100)));
            totalinfo1_map.put("TOPPER_A", String.valueOf(Math.round((Float.valueOf(totalinfo1_map.get("R_A").toString()) / Float.valueOf(totalinfo1_map.get("TO_A").toString())) * 100)));
            /* 그래프 1 */

            /* 그래프 2 테이블*/
            List<HashMap<String, String>> totalinfo2_TblH = statsservice.statsTotalInfo2_TblH(params);
            int TblCols = 0;
            for(int i=0; i<totalinfo2_TblH.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2_TblH.get(i);
                if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                    TblCols += 1;
                }else{
                    TblCols += 2;
                }
            }

            String TblArr[][] = new String[4][TblCols+1];
            int j = 0;
            Float TOPPERSCORE = 0.0F;
            for(int i=0; i<totalinfo2_TblH.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2_TblH.get(i);
                params.put("SUBJECT_CD", map.get("SUBJECT_CD"));

                if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                    params.put("GUBN", "");
                    List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                            TOPPERSCORE += Float.valueOf(map2.get("VAL"));
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;

                }else{
                    params.put("GUBN", "");
                    List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;
                    params.put("GUBN", "AD");
                    totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                            TOPPERSCORE += Float.valueOf(map2.get("VAL"));
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;
                }
            }

            TblArr[0][TblCols] = totalinfo1_map.get("PA_A").toString();
            TblArr[1][TblCols] = totalinfo1_map.get("TA_A").toString();
            TblArr[2][TblCols] = totalinfo1_map.get("TO_SA").toString();
            TblArr[3][TblCols] = totalinfo1_map.get("R_A").toString()+"/"+totalinfo1_map.get("TO_A").toString();
            /* 그래프 2 테이블*/

            model.addAttribute("totalinfolist",totalinfolist);
            model.addAttribute("totalinfo1_map",totalinfo1_map);
            model.addAttribute("totalinfo2_TblH",totalinfo2_TblH);
            model.addAttribute("TblArr",TblArr);
        }
        model.addAttribute("params",params);

        return "mocktest/stats/personView";
    }

    /**
     * @Method Name : statsPersonSubjectList
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목별 문항분석
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsPersonSubjectList.do")
    public String statsPersonSubjectList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("IDENTYID", request.getParameter("IDENTYID"));
        params.put("MOCKCODE", request.getParameter("MOCKCODE"));
        params.put("USER_ID",request.getParameter("USER_ID"));

        int CHECKCNT = statsservice.statsTotalCnt(params);
        if(CHECKCNT<1){
            model.addAttribute("returnData","back");
        }else{
            model.addAttribute("subjectlist",statsservice.statsSubjectList(params));
            model.addAttribute("subjectanswerlist",statsservice.statsSubjectAnswerList(params));
            model.addAttribute("subjectinfolist",statsservice.statsSubjectInfoList(params));
        }
        model.addAttribute("params",params);

        return "mocktest/stats/personSubjectStat";
    }

    /**
     * @Method Name : statsPersonTotalList
     * @작성일 : 2013. 10.
     * @Method 설명 : 전체성적분석
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsPersonTotalList.do")
    public String statsPersonTotalList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("IDENTYID", request.getParameter("IDENTYID"));
        params.put("MOCKCODE", request.getParameter("MOCKCODE"));
        params.put("USER_ID",request.getParameter("USER_ID"));

        int CHECKCNT = statsservice.statsTotalCnt(params);
        if(CHECKCNT<1){
            model.addAttribute("returnData","back");
        }else{

            List<HashMap<String, String>> totalinfolist = statsservice.statsTotalInfoList(params);
            params.put("CLASSCODE", totalinfolist.get(0).get("CLASSCODE"));
            params.put("CLASSSERIESCODE", totalinfolist.get(0).get("CLASSSERIESCODE"));

            /* 그래프 1 */
            List<HashMap<String, String>> totalinfo1 = statsservice.statsTotalInfo1(params);
            HashMap<String, String> totalinfo1_map = new  HashMap<String, String>();
            for(int i=0; i<totalinfo1.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo1.get(i);
                if("총점".equals(map.get("TIT"))){
                    totalinfo1_map.put("T_O", map.get("VAL1"));
                    totalinfo1_map.put("T_A", map.get("VAL2"));
                }
                if("평균".equals(map.get("TIT"))){
                    totalinfo1_map.put("PA_O", map.get("VAL1"));
                    totalinfo1_map.put("PA_A", map.get("VAL2"));
                }
                if("전체평균".equals(map.get("TIT"))){
                    totalinfo1_map.put("TA_O", map.get("VAL1"));
                    totalinfo1_map.put("TA_A", map.get("VAL2"));
                }
                if("석차".equals(map.get("TIT"))){
                    totalinfo1_map.put("R_O", map.get("VAL1"));
                    totalinfo1_map.put("R_A", map.get("VAL2"));
                }
                if("응시인원".equals(map.get("TIT"))){
                    totalinfo1_map.put("TO_O", map.get("VAL1"));
                    totalinfo1_map.put("TO_A", map.get("VAL2"));
                }
                if("상위점수".equals(map.get("TIT"))){
                    totalinfo1_map.put("TO_SO", map.get("VAL1"));
                    totalinfo1_map.put("TO_SA", map.get("VAL2"));
                }
            }
            totalinfo1_map.put("TOPPER_O", String.valueOf(Math.round((Float.valueOf(totalinfo1_map.get("R_O").toString()) / Float.valueOf(totalinfo1_map.get("TO_O").toString())) * 100)));
            totalinfo1_map.put("TOPPER_A", String.valueOf(Math.round((Float.valueOf(totalinfo1_map.get("R_A").toString()) / Float.valueOf(totalinfo1_map.get("TO_A").toString())) * 100)));
            /* 그래프 1 */

            /* 그래프 2 */
            List<HashMap<String, String>> totalinfo2 = statsservice.statsTotalInfo2(params);
            HashMap<String, Object> totalinfo2_map = new  HashMap<String, Object>();
            totalinfo2_map.put("val_5", "0");
            totalinfo2_map.put("val_10", "0");
            totalinfo2_map.put("val_15", "0");
            totalinfo2_map.put("val_20", "0");
            totalinfo2_map.put("val_25", "0");
            totalinfo2_map.put("val_30", "0");
            totalinfo2_map.put("val_35", "0");
            totalinfo2_map.put("val_40", "0");
            totalinfo2_map.put("val_45", "0");
            totalinfo2_map.put("val_50", "0");
            totalinfo2_map.put("val_55", "0");
            totalinfo2_map.put("val_60", "0");
            totalinfo2_map.put("val_65", "0");
            totalinfo2_map.put("val_70", "0");
            totalinfo2_map.put("val_75", "0");
            totalinfo2_map.put("val_80", "0");
            totalinfo2_map.put("val_85", "0");
            totalinfo2_map.put("val_90", "0");
            totalinfo2_map.put("val_95", "0");
            totalinfo2_map.put("val_100", "0");

            for(int i=0; i<totalinfo2.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2.get(i);
                int curScore = Integer.parseInt(String.valueOf(map.get("AVER")));
                if(0<=curScore && curScore<=5){
                    totalinfo2_map.put("val_5", Integer.parseInt(totalinfo2_map.get("val_5").toString())+1);
                }else if(5<curScore && curScore<=10){
                    totalinfo2_map.put("val_10", Integer.parseInt(totalinfo2_map.get("val_10").toString())+1);
                }else if(10<curScore && curScore<=15){
                    totalinfo2_map.put("val_15", Integer.parseInt(totalinfo2_map.get("val_15").toString())+1);
                }else if(15<curScore && curScore<=20){
                    totalinfo2_map.put("val_20", Integer.parseInt(totalinfo2_map.get("val_20").toString())+1);
                }else if(20<curScore && curScore<=25){
                    totalinfo2_map.put("val_25", Integer.parseInt(totalinfo2_map.get("val_25").toString())+1);
                }else if(25<curScore && curScore<=30){
                    totalinfo2_map.put("val_30", Integer.parseInt(totalinfo2_map.get("val_30").toString())+1);
                }else if(30<curScore && curScore<=35){
                    totalinfo2_map.put("val_35", Integer.parseInt(totalinfo2_map.get("val_35").toString())+1);
                }else if(35<curScore && curScore<=40){
                    totalinfo2_map.put("val_40", Integer.parseInt(totalinfo2_map.get("val_40").toString())+1);
                }else if(40<curScore && curScore<=45){
                    totalinfo2_map.put("val_45", Integer.parseInt(totalinfo2_map.get("val_45").toString())+1);
                }else if(45<curScore && curScore<=50){
                    totalinfo2_map.put("val_50", Integer.parseInt(totalinfo2_map.get("val_50").toString())+1);
                }else if(50<curScore && curScore<=55){
                    totalinfo2_map.put("val_55", Integer.parseInt(totalinfo2_map.get("val_55").toString())+1);
                }else if(55<curScore && curScore<=60){
                    totalinfo2_map.put("val_60", Integer.parseInt(totalinfo2_map.get("val_60").toString())+1);
                }else if(60<curScore && curScore<=65){
                    totalinfo2_map.put("val_65", Integer.parseInt(totalinfo2_map.get("val_65").toString())+1);
                }else if(65<curScore && curScore<=70){
                    totalinfo2_map.put("val_70", Integer.parseInt(totalinfo2_map.get("val_70").toString())+1);
                }else if(70<curScore && curScore<=75){
                    totalinfo2_map.put("val_75", Integer.parseInt(totalinfo2_map.get("val_75").toString())+1);
                }else if(75<curScore && curScore<=80){
                    totalinfo2_map.put("val_80", Integer.parseInt(totalinfo2_map.get("val_80").toString())+1);
                }else if(80<curScore && curScore<=85){
                    totalinfo2_map.put("val_85", Integer.parseInt(totalinfo2_map.get("val_85").toString())+1);
                }else if(85<curScore && curScore<=90){
                    totalinfo2_map.put("val_90", Integer.parseInt(totalinfo2_map.get("val_90").toString())+1);
                }else if(90<curScore && curScore<=95){
                    totalinfo2_map.put("val_95", Integer.parseInt(totalinfo2_map.get("val_95").toString())+1);
                }else if(95<curScore && curScore<=100){
                    totalinfo2_map.put("val_100", Integer.parseInt(totalinfo2_map.get("val_100").toString())+1);
                }
            }
            /* 그래프 2 */

            /* 그래프 2 테이블*/
            List<HashMap<String, String>> totalinfo2_TblH = statsservice.statsTotalInfo2_TblH(params);
            int TblCols = 0;
            for(int i=0; i<totalinfo2_TblH.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2_TblH.get(i);
                if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                    TblCols += 1;
                }else{
                    TblCols += 2;
                }
            }

            String TblArr[][] = new String[4][TblCols];
            int j = 0;
            for(int i=0; i<totalinfo2_TblH.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2_TblH.get(i);
                params.put("SUBJECT_CD", map.get("SUBJECT_CD"));

                if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                    params.put("GUBN", "");
                    List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;

                }else{
                    params.put("GUBN", "");
                    List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;
                    params.put("GUBN", "AD");
                    totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                        if("석차".equals(map2.get("TIT"))){
                            TblArr[k][j] = map2.get("VAL");
                        }
                    }
                    j += 1;
                }
            }
            /* 그래프 2 테이블*/

            /* 그래프 3 */
            String Tbl3Arr[][] = new String[totalinfo2_TblH.size()][4];
            //List<HashMap<String, String>> totalinfo3list = new ArrayList<HashMap<String, String>>();
            for(int i=0; i<totalinfo2_TblH.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)totalinfo2_TblH.get(i);
                params.put("SUBJECT_CD", map.get("SUBJECT_CD"));
                List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalInfo2_Tbl(params);
                Tbl3Arr[i][0] = map.get("SUBJECT_NM");
                if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                    params.put("GUBN", "");
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            Tbl3Arr[i][1] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            Tbl3Arr[i][2] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            Tbl3Arr[i][3] = map2.get("VAL");
                        }
                    }
                }else{
                    params.put("GUBN", "AD");
                    for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                        HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                        if("본인".equals(map2.get("TIT"))){
                            Tbl3Arr[i][1] = map2.get("VAL");
                        }
                        if("전체".equals(map2.get("TIT"))){
                            Tbl3Arr[i][2] = map2.get("VAL");
                        }
                        if("상위".equals(map2.get("TIT"))){
                            Tbl3Arr[i][3] = map2.get("VAL");
                        }
                    }
                }
            }
            /* 그래프 3 */

            /* 그래프 4 */
            /*
            List<HashMap<String, String>> chartAvg = new ArrayList<HashMap<String, String>>();

            List<HashMap<String, String>> statsTotalInfo4SubjectList = statsservice.statsTotalInfo4SubjectList(params);
            List<HashMap<String, String>> statsTotalInfo4MockList = statsservice.statsTotalInfo4MockList(params);
            String Tbl4Arr[][] = new String[statsTotalInfo4MockList.size()+1][statsTotalInfo4SubjectList.size()+2];
            for(int i=0; i<statsTotalInfo4SubjectList.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)statsTotalInfo4SubjectList.get(i);
                Tbl4Arr[0][i+1] = map.get("SUBJECT_NM");
            }
            Tbl4Arr[0][statsTotalInfo4SubjectList.size()+1] = "평균";
            for(int i=0; i<statsTotalInfo4MockList.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)statsTotalInfo4MockList.get(i);
                Tbl4Arr[i+1][0] = map.get("EXAMROUND") + "회";
            }
            for(int i=0; i<statsTotalInfo4MockList.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)statsTotalInfo4MockList.get(i);
                HashMap<String, String> params4 = new  HashMap<String, String>();

                params4.put("MOCKCODE", map.get("MOCKCODE"));
                params4.put("IDENTYID", params.get("IDENTYID"));

                List<HashMap<String, String>> statsTotalInfo4MockSubjectScoreList = statsservice.statsTotalInfo4MockSubjectScoreList(params4);

                Float AVER_F = 0.0f;
                int AVER = 0;
                for(int k=0; k<statsTotalInfo4MockSubjectScoreList.size(); k++){
                    HashMap<String, String> map2 = (HashMap<String, String>)statsTotalInfo4MockSubjectScoreList.get(k);

                    AVER_F += Integer.parseInt(map2.get("ADJUSTGRADE").toString());
                    for(int s=0; s<statsTotalInfo4SubjectList.size(); s++){
                        HashMap<String, String> map3 = (HashMap<String, String>)statsTotalInfo4SubjectList.get(s);
                        if(map2.get("SUBJECT_NM").equals(map3.get("SUBJECT_NM"))){
                            Tbl4Arr[i+1][s+1] = map2.get("ADJUSTGRADE");
                        }else{
                            if(Tbl4Arr[i+1][s+1]==null || Tbl4Arr[i+1][s+1]==""){
                                Tbl4Arr[i+1][s+1] = "-";
                            }
                        }
                    }
                }

                AVER_F = (AVER_F / statsTotalInfo4MockSubjectScoreList.size());
                AVER = Math.round(AVER_F);
                Tbl4Arr[i+1][statsTotalInfo4SubjectList.size()+1] = String.valueOf(AVER);

                HashMap<String, String> setMap = new  HashMap<String, String>();
                setMap.put("TIT", Tbl4Arr[i+1][0]);
                setMap.put("VAL", String.valueOf(AVER));
                chartAvg.add(setMap);

            }
            */
            /* 그래프 4 */

            model.addAttribute("totalinfolist",totalinfolist);
            model.addAttribute("totalinfo1_map",totalinfo1_map);
            model.addAttribute("totalinfo2_map",totalinfo2_map);
            model.addAttribute("totalinfo2_TblH",totalinfo2_TblH);
            model.addAttribute("TblArr",TblArr);
            model.addAttribute("Tbl3Arr",Tbl3Arr);
//            model.addAttribute("Tbl4Arr",Tbl4Arr);
//            model.addAttribute("chartAvg",chartAvg);
        }
        model.addAttribute("params",params);

        return "mocktest/stats/personTotalStat";
    }

    /**
     * @Method Name : statsTotalList
     * @작성일 : 2013. 10.
     * @Method 설명 : 전체
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsTotalList.do")
    public String statsTotalList(ModelMap model, HttpServletRequest request) throws Exception {
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

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        List<HashMap<String, String>> list = statsservice.statsTotalList(params);
        int listCount = statsservice.statsTotalListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list",list);
        model.addAttribute("totalCount",listCount);
        model.addAttribute("pagingHtml",pagingHtml);
        model.addAttribute("params",params);

        return "mocktest/stats/totalList";
    }

    /**
     * @Method Name : statsTotalView
     * @작성일 : 2013. 10.
     * @Method 설명 : 전체 상세
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/statsTotalView.do")
    public String statsTotalView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHTYPE",request.getParameter("SEARCHTYPE"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("MOCKCODE", request.getParameter("MOCKCODE"));

        int CHECKCNT = statsservice.statsTotalCnt(params);
        if(CHECKCNT<1){
            model.addAttribute("returnData","back");
        }else{
            List<HashMap<String, String>> totalViewInfo = statsservice.statsTotalViewInfo(params);
            List<HashMap<String, String>> totalViewClassseriesList = statsservice.statsTotalViewClassseriesList(params);
            List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
            for(int c=0; c<totalViewClassseriesList.size(); c++){
                HashMap<String, String> classMap = (HashMap<String, String>)totalViewClassseriesList.get(c);
                params.put("CLASSCODE", classMap.get("CLASSCODE"));
                params.put("CLASSSERIESCODE", classMap.get("CLASSSERIESCODE"));

                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("TITLE", classMap.get("CLASSSERIESNM"));

                List<HashMap<String, String>> totalinfo_TblH = statsservice.statsTotalView_TblH(params);
                resultMap.put("TblH", totalinfo_TblH);
                int TblCols = 0;
                for(int i=0; i<totalinfo_TblH.size(); i++){
                    HashMap<String, String> map = (HashMap<String, String>)totalinfo_TblH.get(i);
                    if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                        TblCols += 1;
                    }else{
                        TblCols += 2;
                    }
                }

                String TblArr[][] = new String[3][TblCols+1];
                int j = 0;

                for(int i=0; i<totalinfo_TblH.size(); i++){
                    HashMap<String, String> map = (HashMap<String, String>)totalinfo_TblH.get(i);
                    params.put("SUBJECT_CD", map.get("SUBJECT_CD"));

                    if("1".equals(map.get("SUBJECTTYPEDIVISION"))){
                        params.put("GUBN", "");
                        List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalSubjectView(params);
                        for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                            HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                            TblArr[k][j] = map2.get("VAL");
                        }
                        j += 1;

                    }
                    else{
                        params.put("GUBN", "");
                        List<HashMap<String, String>> totalinfo2_Tbllist = statsservice.statsTotalSubjectView(params);
                        for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                            HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                            TblArr[k][j] = map2.get("VAL");
                        }
                        j += 1;
                        params.put("GUBN", "AD");
                        totalinfo2_Tbllist = statsservice.statsTotalSubjectView(params);
                        for(int k=0; k<totalinfo2_Tbllist.size(); k++){
                            HashMap<String, String> map2 = (HashMap<String, String>)totalinfo2_Tbllist.get(k);
                            TblArr[k][j] = map2.get("VAL");
                        }
                        j += 1;
                    }
                }

                List<HashMap<String, String>> totalSubjectViewAver = statsservice.statsTotalSubjectViewAver(params);
                for(int k=0; k<totalSubjectViewAver.size(); k++){
                    HashMap<String, String> map2 = (HashMap<String, String>)totalSubjectViewAver.get(k);
                    TblArr[k][TblCols] = map2.get("VAL");
                }

                resultMap.put("TblArr", TblArr);
                resultList.add(resultMap);
            }
            model.addAttribute("totalViewInfo",totalViewInfo);
            model.addAttribute("totalViewClassseriesList",totalViewClassseriesList);
            model.addAttribute("resultList",resultList);
        }
        model.addAttribute("params",params);

        return "mocktest/stats/totalView";
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
    @RequestMapping(value="/statsOffererList.do")
    public String statsOffererList(ModelMap model, HttpServletRequest request) throws Exception {
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

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));
        params.put("SEARCHEXAMTYPE",CommonUtil.isNull(request.getParameter("SEARCHEXAMTYPE"), ""));
        params.put("SEARCHCLASSCODE",CommonUtil.isNull(request.getParameter("SEARCHCLASSCODE"), ""));
        params.put("SEARCHCLASSSERIESCODE",CommonUtil.isNull(request.getParameter("SEARCHCLASSSERIESCODE"), ""));
        params.put("SEARCHSUBJECT",CommonUtil.isNull(request.getParameter("SEARCHSUBJECT"), ""));

        List<HashMap<String, String>> list = statsservice.offererList(params);
        int listCount = statsservice.offererListCount(params);
        int onCntY = statsservice.offererOnCntY(params);
        int onCntN = statsservice.offererOnCntN(params);
        int offCntY = statsservice.offererOffCntY(params);
        int offCntN = statsservice.offererOffCntN(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        // 직급목록
        List<?> registration_list = mouigosaService.getRegistrationList(params);

        model.addAttribute("list",list);
        model.addAttribute("totalCount",listCount);
        model.addAttribute("pagingHtml",pagingHtml);
        model.addAttribute("registration_list",registration_list);
        model.addAttribute("onCntY",onCntY);
        model.addAttribute("onCntN",onCntN);
        model.addAttribute("offCntY",offCntY);
        model.addAttribute("offCntN",offCntN);
        model.addAttribute("params",params);

        return "mocktest/stats/offererList";
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
    @RequestMapping(value="/statsOffererListExcel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHYEAR",CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
        params.put("SEARCHROUND",CommonUtil.isNull(request.getParameter("SEARCHROUND"), ""));

        params.put("SEARCHEXAMTYPE",CommonUtil.isNull(request.getParameter("SEARCHEXAMTYPE"), ""));
        params.put("SEARCHCLASSCODE",CommonUtil.isNull(request.getParameter("SEARCHCLASSCODE"), ""));
        params.put("SEARCHCLASSSERIESCODE",CommonUtil.isNull(request.getParameter("SEARCHCLASSSERIESCODE"), ""));
        params.put("SEARCHSUBJECT",CommonUtil.isNull(request.getParameter("SEARCHSUBJECT"), ""));

		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();
		String ddate = new SimpleDateFormat("yyyyMMdd").format(date);
        
        List<HashMap<String, String>> list = statsservice.offererListExcel(params);
        String excelName = ddate+"_접수자 명단";
        List<String> headerList = new ArrayList<String>();
        headerList.add("주문번호");
        headerList.add("년");
        headerList.add("회차");
        headerList.add("모의고사명");
        headerList.add("과목");
        headerList.add("ON/OFF");
        headerList.add("수험번호");
        headerList.add("성명");
        headerList.add("전화번호");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            newMap.put(i++, map.get("ORDERNO").toString());
            newMap.put(i++, map.get("EXAMYEAR").toString());
            newMap.put(i++, map.get("EXAMROUND").toString());
            newMap.put(i++, map.get("MOCKNAME").toString());
            newMap.put(i++, map.get("SUBJECT_NM").toString());
            if("0".equals(map.get("EXAMTYPE"))){
                newMap.put(i++, "온라인");
            }else{
                newMap.put(i++, "오프라인");
            }
            newMap.put(i++, map.get("IDENTYID").toString().substring(4,11));
            newMap.put(i++, map.get("USER_NM").toString());
            newMap.put(i++, map.get("PHONE_NO").toString());

            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
    }

    /**
     * @Method Name : getSubjectList
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/getSubjectList.do")
    @ResponseBody
    public List<?> getSubjectList(ModelMap model, HttpServletRequest request) {
        // 검색조건
        String SEARCHCLASSCODE = CommonUtil.isNull(request.getParameter("SEARCHCLASSCODE"), "");
        String SEARCHCLASSSERIESCODE = CommonUtil.isNull(request.getParameter("SEARCHCLASSSERIESCODE"), "");

        HashMap<String, String> searchMap = new  HashMap<String, String>();
        searchMap.put("SEARCHCLASSCODE", SEARCHCLASSCODE);
        searchMap.put("SEARCHCLASSSERIESCODE", SEARCHCLASSSERIESCODE);

        //과목 리스트
        List<?> sub_list = statsservice.getSubjectList(searchMap);
        return sub_list;
    }

    /**
     * @Method Name : makeResult
     * @작성일 : 2015. 12.
     * @Method 설명 : 모의고사 결과 집계
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/fn_makeAdj.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeResult(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("MOCKCODE", request.getParameter("MOCKCODE"));

        String Result = "";
//        offexmresService.updateAdjustGradeTmockgradeByMockcode(params);

        Result = "Y";

        return Result;
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
