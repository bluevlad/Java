package com.willbes.web.memberManagement;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.jdbc.oracore.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.willbes.cmm.service.CmmUseService;
import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.mail.SendMail;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.memberManagement.service.MemberManagementService;
import com.willbes.web.productOrder.service.ProductOrderService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   : MemberManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 10. 28.
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 회원관리 메뉴
 */
@RequestMapping(value="/memberManagement")
@Controller
public class MemberManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Autowired
    CmmUseService cmmUseService;

    @Autowired
    private MemberManagementService  memberManagementService;

    @Autowired
    private ProductOrderService productOrderService;

    @Resource(name="sendMail")
    SendMail sendMail;

    @RequestMapping(value="/memberSearch.do")
    public String memberSearch(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        model.addAttribute("params", params);

        String eDate = CommonUtil.getCurrentDate();
       	params.put("SDATE", "20090101");
        params.put("EDATE", eDate);
        
        return "/memberManagement/memberSearch";
    }

    /**
     * @Method Name  : memberList
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :	회원조회  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/memberList.do")
    public String memberList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        // 검색조건
        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        
        if(params.get("SDATE").equals("")){
            //String sDate = CommonUtil.getCurrentDate();
            //params.put("SDATE", sDate);
            String temp;
 			int cYear;
        	int cMonth;
        	int cDay;
        	String MM = "";
        	String DD = "";
        	
        	Calendar calendar = new GregorianCalendar(Locale.KOREA);
        	cYear = calendar.get(Calendar.YEAR);
        	cMonth = calendar.get(Calendar.MONTH) - 5;
        	cDay = calendar.get(Calendar.DAY_OF_MONTH);
        	
        	if(cMonth < 10){
        		MM = "0" + String.valueOf(cMonth);
        	}else{
        		MM = String.valueOf(cMonth);
        	}
        	
        	if(cDay < 10){
        		DD = "0" + String.valueOf(cDay);
        	}else{
        		DD = String.valueOf(cDay);
        	}
        	
        	temp = String.valueOf(cYear) + MM + DD; // 임시변수에 담아준다

        }
		
        if(params.get("EDATE").equals("")){
            String eDate = CommonUtil.getCurrentDate();
            params.put("EDATE", eDate);
        }

        List<HashMap<String, Object>> list = null;
        int listCount = 0;
        // 회원 리스트
        list = memberManagementService.getMemberList(params);
        // 총 건수 -회원
        listCount = memberManagementService.getMemberListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberManagement/memberList";
    }

    /**
     * @Method Name  : memberInsert
     * @Date         : 2013. 10.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원조회  등록 화면
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberInsert.do")
    public String memberInsert(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        model.addAttribute("categoryList",memberManagementService.getCategoryList());
        model.addAttribute("params",params);

        return "/memberManagement/memberInsert";
    }

    /**
     * @Method Name : idCheck
     * @작성일 : 2013. 10.
     * @Method 설명 : 회원 id 중복체크
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/idCheck.do")
    @ResponseBody
    public String idCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));

        int listCount = memberManagementService.memberIdCheck(params);
        String returnStr = "Y";
        if(listCount > 0)
            returnStr = "N";

        return returnStr;
    }

    /**
     * @Method Name  : memberInsertProcess
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원조회 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberInsertProcess.do")
    @Transactional( readOnly=false,  rollbackFor=Exception.class)
    public String memberInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");
        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "USER"));
        params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
        params.put("BIRTH_DAY", CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
        params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("CATEGORY_CODE", CommonUtil.isNull(request.getParameter("CATEGORY_CODE"), ""));
        params.put("USER_POINT", CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
        params.put("ZIP_CODE", CommonUtil.isNull(request.getParameter("ZIP_CODE"), ""));
        params.put("ADDRESS1", CommonUtil.isNull(request.getParameter("ADDRESS1"), ""));
        params.put("ADDRESS2", CommonUtil.isNull(request.getParameter("ADDRESS2"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("REMARK", CommonUtil.isNull(request.getParameter("REMARK"), ""));
        params.put("ISOK_SMS",CommonUtil.isNull(request.getParameter("isok_sms"), ""));
        params.put("ISOK_EMAIL",CommonUtil.isNull(request.getParameter("isok_email"), ""));

        //회원 등록
        memberManagementService.memberInsertProcess(params);
        model.addAttribute("params",params);

        return "redirect:/memberManagement/memberList.do";
    }

    /**
     * @Method Name  : memberDetail
     * @Date         : 2023. 01. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	회원조회 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberDetail.do")
    public String memberDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("USER_ID",request.getParameter("USER_ID"));

        model.addAttribute("categoryList", memberManagementService.getCategoryList());
        model.addAttribute("detail", memberManagementService.memberDetail(params));
        model.addAttribute("params",params);
        
        return "/memberManagement/memberDetail";
    }

    /**
     * @Method Name  : memberUpdateProcess
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원조회 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "USER"));
        params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
        params.put("BIRTH_DAY", CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
        params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("CATEGORY_CODE", CommonUtil.isNull(request.getParameter("CATEGORY_CODE"), ""));
        params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
        params.put("TEL_NO", CommonUtil.isNull(request.getParameter("TEL_NO"), ""));
        params.put("USER_POINT", CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
        params.put("BEFORE_USER_POINT", CommonUtil.isNull(request.getParameter("BEFORE_USER_POINT"), "0"));
        params.put("ZIP_CODE", CommonUtil.isNull(request.getParameter("ZIP_CODE"), ""));
        params.put("ADDRESS1", CommonUtil.isNull(request.getParameter("ADDRESS1"), ""));
        params.put("ADDRESS2", CommonUtil.isNull(request.getParameter("ADDRESS2"), ""));
        params.put("REMARK", CommonUtil.isNull(request.getParameter("REMARK"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("ISOK_SMS",CommonUtil.isNull(request.getParameter("ISOK_SMS"), ""));
        params.put("ISOK_EMAIL",CommonUtil.isNull(request.getParameter("ISOK_EMAIL"), ""));

        int before_point = 0;
        int point = 0;
        String comment1 = "";
        String temp_point = "";

        before_point = Integer.parseInt(CommonUtil.isNull(request.getParameter("BEFORE_USER_POINT"), "0"));
        point = Integer.parseInt(CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));

        //회원 수정
        memberManagementService.memberUpdateProcess(params);

        // 관리자가 포인트 변경시 마일리지 히스토리 남기기 (나호원 사원)
        if(before_point != point){
            if(before_point > point){
                temp_point = String.valueOf((before_point - point)*-1);
            }else{
                temp_point = String.valueOf((point - before_point ));
            }

            comment1 = "관리자에 의한 수정 (" + before_point + " --> " + point +")";

            params.put("userid", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
            params.put("comment1", comment1);
            params.put("temp_point", temp_point);

            productOrderService.insertMileageHistory3(params);
        }
        model.addAttribute("params",params);

        return "forward:/memberManagement/memberList.do";
    }


    /**
     * @Method Name  : memberDelete
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원조회 상세페이지  삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("DETAIL_CODE_NO",request.getParameter("DETAIL_CODE_NO")); //상세코드번호
        //회원 삭제
        memberManagementService.memberDelete(params);
        memberManagementService.memberCategoryDelete(params);

        model.addAttribute("params",params);

        return "redirect:/memberManagement/memberList.do";
    }

    /**
     * @Method Name  : memberCheckDelete
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원조회 리스트 체크박스  일괄 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberCheckDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                params.put("USER_ID", DEL_ARR[i]);
                memberManagementService.memberDelete(params);
                memberManagementService.memberCategoryDelete(params);
            }
        }
        model.addAttribute("params",params);

        return "forward:/memberManagement/memberList.do";
    }

    /**
     * @Method Name : getZipCode
     * @작성일 : 2013. 10.
     * @Method 설명 : 우편번호 팝업
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/getZipCode.pop")
    public String getZipCode(ModelMap model, HttpServletRequest request) throws Exception {
        return "/memberManagement/memberZipCode";
    }

    /**
     * @Method Name : getZipCode
     * @작성일 : 2013. 10.
     * @Method 설명 :우편번호 검색
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/searchZipCode.pop")
    public String searchZipCode(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        params.put("SEARCHAREA",CommonUtil.isNull(request.getParameter("SEARCHAREA"), ""));
        List<HashMap<String, String>> ZIPLIST = memberManagementService.searchZipCode(params);

        model.addAttribute("ZIPLIST",ZIPLIST);
        model.addAttribute("params",params);
        return "/memberManagement/memberZipCode";
    }

    /**
     * @Method Name  : memberAdminCheckMessage
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 회원관리  회원조회 리스트 체크박스 쪽지
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberCheckMessage.pop")
    public String memberCheckMessage(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        String user_id = request.getParameter("MESSAGEID");
        String user_nm = request.getParameter("MESSAGENM");
        params.put("USER_ID", user_id);
        params.put("USER_NM", user_nm);
        model.addAttribute("params",params);
        return "/memberManagement/memberMessage";
    }

    /**
     * @Method Name  : memberSendMessage
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 쪽지
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberSendMessage.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberSendMessage(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        int insertCount =0;
        String resultStr="N";
        String USER_ID =  request.getParameter("USER_ID");
        String[] USERID_ARR = USER_ID.split(",");
        params.put("CONT", CommonUtil.isNull(request.getParameter("CONT"), ""));

        if(USERID_ARR != null){
            for(int i=0; i<USERID_ARR.length; i++){
                params.put("FROM_USERID", USERID_ARR[i]);
                memberManagementService.memberSendMessage(params);
                insertCount++;
            }
        }
        if(insertCount > 0) {
            resultStr="Y";
        }
        return resultStr;
    }

    /**
     * @Method Name  : memberCheckEmail
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 메일
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberCheckEmail.pop")
    public String memberCheckEmail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        String user_id = request.getParameter("MESSAGEID");
        String user_nm = request.getParameter("MESSAGENM");
        params.put("USER_ID", user_id);
        params.put("USER_NM", user_nm);
        model.addAttribute("params",params);
        return "/memberManagement/memberEmail";
    }

    /**
     * @Method Name  : memberSendEmail
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 mail
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberSendEmail.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberSendEmail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        int insertCount =0;
        String resultStr="N";
        String USER_ID =  request.getParameter("USER_ID");
        String[] USERID_ARR = USER_ID.split(",");

        params.put("SUBJECT", CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("CONT", CommonUtil.isNull(request.getParameter("CONT"), ""));

        List<String> emailList = new ArrayList<String>();

        if(USERID_ARR != null){
            for(int i=0; i<USERID_ARR.length; i++){
                params.put("USER_ID", USERID_ARR[i]);
                HashMap<String, String> email =  memberManagementService.getMemberAdminEmail(params);
                if(CommonUtil.isEmail(String.valueOf(email.get("EMAIL")))){
                    emailList.add(String.valueOf(email.get("EMAIL")));
                    params.put("FROM_USERID", USERID_ARR[i]);
                    memberManagementService.MemberAdminInsertEmail(params);
                    insertCount++;
                }
            }
            String[] toUser = new String[emailList.size()];
            toUser = (String[])emailList.toArray(toUser);
            sendMail.sendMail(String.valueOf(params.get("SUBJECT")),
                    String.valueOf(params.get("CONT")),
                    String.valueOf(params.get("FROM_EMAIL")),
                    toUser);

            //			for(int j=0; j<USERID_ARR.length; j++){
            //				params.put("USER_ID", USERID_ARR[j]);
            //				HashMap<String, String> email2 =  memberManagementService.getMemberAdminEmail(params);
            //				if(CommonUtil.isEmail(String.valueOf(email2.get("EMAIL")))){
            //					params.put("FROM_USERID", USERID_ARR[j]);
            //					memberManagementService.MemberAdminInsertEmail(params);
            //					insertCount++;
            //				}
            //			}
        }

        if(insertCount > 0) {
            resultStr="Y";
        }
        return resultStr;
    }

    /**
     * @Method Name : list
     * @작성일 : 2013. 11.
     * @Method 설명 : 단과 강의 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/memberStat.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String searchStartDate = CommonUtil.isNull(request.getParameter("searchStartDate"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("searchEndDate"), "");
        String selType = CommonUtil.isNull(request.getParameter("selType"), "YY");
        params.put("searchStartDate", searchStartDate);
        params.put("searchEndDate", searchEndDate);
        params.put("selType", selType);

        List<HashMap<String, String>> list = memberManagementService.getMemberStatList(params);

        model.addAttribute("list", list);
        model.addAttribute("searchStartDate", searchStartDate);
        model.addAttribute("searchEndDate", searchEndDate);
        model.addAttribute("selType", selType);
        model.addAttribute("params", params);

        return "/memberManagement/memberStat";
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
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        // 회원 리스트
        List<HashMap<String, Object>> list = memberManagementService.getMemberList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_회원명단";

        List<String> headerList = new ArrayList<String>();
        headerList.add("회원ID");
        headerList.add("회원명");
        headerList.add("상태");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, Object> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID"))) {
                newMap.put(i++, map.get("USER_ID").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM"))) {
                newMap.put(i++, map.get("USER_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ISUSENM") && !"".equals(map.get("ISUSENM"))) {
                newMap.put(i++, map.get("ISUSENM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("REG_DT") && !"".equals(map.get("REG_DT"))) {
                newMap.put(i++, map.get("REG_DT").toString());
            } else {
                newMap.put(i++, "");
            }

            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
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
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("USERID", loginInfo.get("USER_ID"));
        params.put("admin_id", loginInfo.get("USER_ID"));
        params.put("SEND_ID", loginInfo.get("USER_ID"));
        params.put("FROM_EMAIL", loginInfo.get("EMAIL"));

        String userName = "";
        if (loginInfo.get("USER_ROLE").equals("ADMIN")){
            userName = loginInfo.get("USER_NICKNM");
        }else{
            userName = loginInfo.get("USER_NM");
        }
        params.put("USER_NM", userName);
        params.put("USERNAME", userName);

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        model.addAttribute("currentPage", params.get("currentPage"));
        model.addAttribute("pageRow", params.get("pageRow"));

        String USER_POSITION = CommonUtil.isNull(request.getParameter("USER_POSITION"), "PUB");
        params.put("USER_POSITION", USER_POSITION);

        String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
        String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
        params.put("SEARCHTYPE", SEARCHTYPE);
        params.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));

        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
    }
}
