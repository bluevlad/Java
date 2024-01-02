package com.willbes.web.memberAdminManagement;

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
import com.willbes.platform.util.mail.SendMail;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.memberAdminManagement.service.MemberAdminManagementService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   : MemberAdminManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 10. 28.
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 운영자관리 메뉴
 */
@RequestMapping(value="/memberAdminManagement")
@Controller
public class MemberAdminManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private MemberAdminManagementService  memberAdminManagementService;

    @Resource(name="sendMail")
    SendMail sendMail;

    /**
     * @Method Name  : memberAdminList
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :	운영자조회  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/memberAdminList.do")
    public String memberAdminList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        // 검색조건
        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        // 운영자 리스트
        List<HashMap<String, Object>> list = memberAdminManagementService.getMemberList(params);
        // 총 건수 -운영자
        int listCount = memberAdminManagementService.getMemberListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberAdminManagement/memberAdminList";
    }

    /**
     * @Method Name  : memberAdminInsert
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자조회  등록 화면
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminInsert.do")
    public String memberAdminInsert(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        List<HashMap<String, String>> siteList = memberAdminManagementService.getSiteList();
        model.addAttribute("categoryList",siteList);
        model.addAttribute("params",params);

        return "/memberAdminManagement/memberAdminInsert";
    }

    /**
     * @Method Name : idCheck
     * @작성일 : 2013. 10.
     * @Method 설명 : 운영자 id 중복체크
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/idAdminCheck.do")
    @ResponseBody
    public String idAdminCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        int listCount = memberAdminManagementService.memberIdCheck(params);
        String returnStr = "Y";
        if(listCount > 0)
            returnStr = "N";
        return returnStr;
    }

    /**
     * @Method Name  : memberAdminInsertProcess
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자관리 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminInsertProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("USER_POSITION", CommonUtil.isNull(request.getParameter("USER_POSITION"), ""));
        params.put("USER_NICKNM", CommonUtil.isNull(request.getParameter("USER_NICKNM"), ""));
        params.put("ADMIN_ROLE", CommonUtil.isNull(request.getParameter("ADMIN_ROLE"), ""));
        params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "ADMIN"));
        params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
        params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
        params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("MEMO", CommonUtil.isNull(request.getParameter("MEMO"), ""));
        params.put("SEX", CommonUtil.isNull(request.getParameter("SEX"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        //운영자 등록
        memberAdminManagementService.memberInsertProcess(params);
        model.addAttribute("params",params);

        return "redirect:/memberAdminManagement/memberAdminList.do";
    }

    /**
     * @Method Name  : memberAdminDetail
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자관리 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminDetail.do")
    public String memberAdminDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("USER_ID",request.getParameter("USER_ID"));

        List<HashMap<String, String>> siteList = memberAdminManagementService.getSiteList();
        //코드 select
        HashMap<String, Object> detail  =	memberAdminManagementService.memberDetail(params);

        model.addAttribute("categoryList",siteList);
        model.addAttribute("detail",detail);
        model.addAttribute("params",params);
        model.addAttribute("GUBUN",CommonUtil.isNull(request.getParameter("GUBUN"), ""));

        return "/memberAdminManagement/memberAdminDetail";
    }

    /**
     * @Method Name  : memberAdminUpdateProcess
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자관리 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("USER_POSITION", CommonUtil.isNull(request.getParameter("USER_POSITION"), ""));
        params.put("USER_NICKNM", CommonUtil.isNull(request.getParameter("USER_NICKNM"), ""));
        params.put("ADMIN_ROLE", CommonUtil.isNull(request.getParameter("ADMIN_ROLE"), ""));
        params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
        params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
        params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("MEMO", CommonUtil.isNull(request.getParameter("MEMO"), ""));
        params.put("SEX", CommonUtil.isNull(request.getParameter("SEX"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        //운영자 수정
        memberAdminManagementService.memberUpdateProcess(params);
        model.addAttribute("params",params);

        return "forward:/memberAdminManagement/memberAdminList.do";
    }

    /**
     * @Method Name  : memberAdminDelete
     * @Date         : 2013. 10.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자관리 상세페이지  삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("USER_ID",request.getParameter("USER_ID")); //운영자 ID
        //운영자 삭제
        memberAdminManagementService.memberDelete(params);
        model.addAttribute("params",params);

        return "redirect:/memberAdminManagement/memberAdminList.do";
    }

    /**
     * @Method Name  : memberAdminCheckDelete
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      : 운영자관리 리스트 체크박스  일괄 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminCheckDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                params.put("USER_ID", DEL_ARR[i]);
                memberAdminManagementService.memberDelete(params);
            }
        }
        model.addAttribute("params",params);

        return "forward:/memberAdminManagement/memberAdminList.do";
    }

    /**
     * @Method Name  : memberAdminCheckMessage
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 쪽지
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminCheckMessage.pop")
    public String memberAdminCheckMessage(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        String user_id = request.getParameter("MESSAGEID");
        String user_nm = request.getParameter("MESSAGENM");
        params.put("USER_ID", user_id);
        params.put("USER_NM", user_nm);
        model.addAttribute("params",params);
        return "/memberAdminManagement/memberAdminMessage";
    }

    /**
     * @Method Name  : memberAdminCheckMessage
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 쪽지
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminSendMessage.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminSendMessage(ModelMap model, HttpServletRequest request) throws Exception {
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
                memberAdminManagementService.memberAdminSendMessage(params);
                insertCount++;
            }
        }
        if(insertCount > 0) {
            resultStr="Y";
        }
        return resultStr;
    }

    /**
     * @Method Name  : memberAdminCheckEmail
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 메일
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminCheckEmail.pop")
    public String memberAdminCheckEmail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        String user_id = request.getParameter("MESSAGEID");
        String user_nm = request.getParameter("MESSAGENM");
        params.put("USER_ID", user_id);
        params.put("USER_NM", user_nm);
        model.addAttribute("params",params);

        return "/memberAdminManagement/memberAdminEmail";
    }

    /**
     * @Method Name  : memberAdminCheckMessage
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :  리스트 체크박스 mail
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/memberAdminSendEmail.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String memberAdminSendEmail(ModelMap model, HttpServletRequest request) throws Exception {
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
                HashMap<String, String> email =  memberAdminManagementService.getMemberAdminEmail(params);
                if(CommonUtil.isEmail(String.valueOf(email.get("EMAIL")))){
                    emailList.add(String.valueOf(email.get("EMAIL")));
                    params.put("FROM_USERID", USERID_ARR[i]);

                    memberAdminManagementService.MemberAdminInsertEmail(params);
                    insertCount++;
                }
            }
            String[] toUser = new String[emailList.size()];
            toUser = (String[])emailList.toArray(toUser);
            sendMail.sendMail(String.valueOf(params.get("SUBJECT")),
                    String.valueOf(params.get("SUBJECT")),
                    String.valueOf(params.get("FROM_EMAIL")),
                    toUser);

            //			for(int j=0; j<USERID_ARR.length; j++){
            //				params.put("USER_ID", USERID_ARR[j]);
            //				HashMap<String, String> email2 =  memberAdminManagementService.getMemberAdminEmail(params);
            //				if(CommonUtil.isEmail(String.valueOf(email2.get("EMAIL")))){
            //					params.put("FROM_USERID", USERID_ARR[j]);
            //					memberAdminManagementService.MemberAdminInsertEmail(params);
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

        // 회원 리스트
        List<HashMap<String, Object>> list = memberAdminManagementService.getMemberList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String excelName = "관리자 명단_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("회원ID");
        headerList.add("회원명");
        headerList.add("별명");
        headerList.add("회원권한");
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
            if(null != map.get("USER_NICKNM") && !"".equals(map.get("USER_NICKNM"))) {
                newMap.put(i++, map.get("USER_NICKNM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ADMIN_NM") && !"".equals(map.get("ADMIN_NM"))) {
                newMap.put(i++, map.get("ADMIN_NM").toString());
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
     * @Method Name  : memberAdminList
     * @Date         : 2013. 10. 28.
     * @Author       : seojeong
     * @변경이력      :
     * @Method 설명      :	운영자조회  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/AdminLoginList.do")
    public String AdminLoginList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
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
            String sDate = CommonUtil.getCurrentDate();
            params.put("SDATE", sDate);
        }
		
        if(params.get("EDATE").equals("")){
            String eDate = CommonUtil.getCurrentDate();
            params.put("EDATE", eDate);
        }

        // 운영자 리스트
        List<HashMap<String, Object>> list = memberAdminManagementService.getAdminLoginList(params);
        // 총 건수 -운영자
        int listCount = memberAdminManagementService.getAdminLoginListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberAdminManagement/AdminLoginList";
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
        params.put("USERID", loginInfo.get("USER_ID"));
        params.put("admin_id",loginInfo.get("USER_ID"));
        params.put("SEND_ID",loginInfo.get("USER_ID"));
        params.put("FROM_EMAIL",loginInfo.get("EMAIL"));

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

        String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
        String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
        String SDATE = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String EDATE = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SEARCHTYPE", SEARCHTYPE);
        params.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));
        params.put("SDATE", SDATE);
        params.put("EDATE", EDATE);
    }

}
