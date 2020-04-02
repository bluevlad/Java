package web.memberManagement;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import web.memberManagement.service.MemberManagementService;
import web.productOrder.service.ProductOrderService;
import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.paging.Paging;

/**
 * @FileName   : MemberManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 회원관리 메뉴
 */
@RequestMapping(value="/memberManagement")
@Controller
public class MemberManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private MemberManagementService  memberManagementService;

    @Autowired
    private ProductOrderService productOrderService;

//    @Resource(name="sendMail")
//    SendMail sendMail;

    /**
     * @Method Name  : memberList
     * @Date         : 2020.03
     * @Author       : rainend
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
        String oldm = CommonUtil.isNull(request.getParameter("oldm"), "");
        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        params.put("oldm", oldm);
        
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
        	params.put("SDATE", "20090101");

        }
		
        if(params.get("EDATE").equals("")){
            String eDate = CommonUtil.getCurrentDate();
            params.put("EDATE", eDate);
        }

        List<HashMap<String, Object>> list = null;
        int listCount = 0;
        if (oldm.equals("Y")) {
            // 회원 리스트
            list = memberManagementService.getOldMemberList(params);
            // 총 건수 -회원
            listCount = memberManagementService.getOldMemberListCount(params);
        }else{
            // 회원 리스트
            list = memberManagementService.getMemberList(params);
            // 총 건수 -회원
            listCount = memberManagementService.getMemberListCount(params);
        }
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberManagement/memberList";
    }
    
    @RequestMapping(value="/prime_memberList.do")
    public String prime_memberList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
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
        	cMonth = calendar.get(Calendar.MONTH)+1;
        	cDay = calendar.get(Calendar.DAY_OF_MONTH)-1;
        	
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
        	String sDate = CommonUtil.getCurrentDate();
        	params.put("SDATE", sDate);

        }
		
        if(params.get("EDATE").equals("")){
            String eDate = CommonUtil.getCurrentDate();
            params.put("EDATE", eDate);
        }

        // 회원 리스트
        List<HashMap<String, Object>> list = memberManagementService.getPrimeMemberList(params);
        // 총 건수 -회원
        int listCount = memberManagementService.getPrimeMemberListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberManagement/prime_memberList";
    }

    /**
     * @Method Name  : oldMemberList
     * @Date         : 2016. 06.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 이관 회원조회
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/oldMemberList.do")
    public String oldMemberList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        // 검색조건
        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        // 회원 리스트
        List<HashMap<String, Object>> list = memberManagementService.getOldMemberList(params);
        // 총 건수 -회원
        int listCount = memberManagementService.getOldMemberListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberManagement/oldMemberList";
    }

    /**
     * @Method Name  : memberList
     * @Date         : 2015. 05. 12.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	남서울대 회원조회
     */
    @RequestMapping(value="/memberCoopList.do")
    public String memberCoopList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        params.put("COOP_CD", "NSU");

        // 회원 리스트
        List<HashMap<String, Object>>list = memberManagementService.getMemberList(params);
        // 총 건수 -회원
        int listCount = memberManagementService.getMemberListCount(params);

        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/memberManagement/memberCoopList";
    }

    /**
     * @Method Name  : memberInsert
     * @Date         : 2013. 10.
     * @Author       : rainend
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

        model.addAttribute("categoryList", memberManagementService.getCategoryList());

        model.addAttribute("params", params);

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
     * @Date         : 2020.03
     * @Author       : rainend
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
        //params.put("SEX", CommonUtil.isNull(request.getParameter("SEX"), ""));  //X
        params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "USER"));
        params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
        params.put("BIRTH_DAY", CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
        params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("CATEGORY_CODE", CommonUtil.isNull(request.getParameter("CATEGORY_CODE"), ""));
        params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
        params.put("TEL_NO", CommonUtil.isNull(request.getParameter("TEL_NO"), ""));
        params.put("USER_POINT", CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
        params.put("ZIP_CODE", CommonUtil.isNull(request.getParameter("ZIP_CODE"), ""));
        params.put("ADDRESS1", CommonUtil.isNull(request.getParameter("ADDRESS1"), ""));
        params.put("ADDRESS2", CommonUtil.isNull(request.getParameter("ADDRESS2"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        params.put("REMARK", CommonUtil.isNull(request.getParameter("REMARK"), ""));

        //추가된 내용
        params.put("JOIN_CHANNEL",CommonUtil.isNull(request.getParameter("JOIN_CHANNEL"), ""));
        params.put("JOB",CommonUtil.isNull(request.getParameter("JOB"), ""));
        params.put("EXAM_REQ",CommonUtil.isNull(request.getParameter("EXAM_REQ"), ""));
        params.put("SBJ_REQ",CommonUtil.isNull(request.getParameter("SBJ_REQ"), ""));
        params.put("F_CAT_CD",CommonUtil.isNull(request.getParameter("F_CAT_CD"), ""));
        params.put("F_AREA",CommonUtil.isNull(request.getParameter("F_AREA"), ""));
        params.put("S_CAT_CD",CommonUtil.isNull(request.getParameter("S_CAT_CD"), ""));
        params.put("S_AREA",CommonUtil.isNull(request.getParameter("S_AREA"), ""));
        params.put("INFO_REQ",CommonUtil.isNull(request.getParameter("INFO_REQ"), ""));
        params.put("EVENT_REQ",CommonUtil.isNull(request.getParameter("EVENT_REQ"), ""));
        params.put("EVENT_REQ_ETC",CommonUtil.isNull(request.getParameter("event_req_etc_txt"), ""));
        params.put("ISOK_SMS",CommonUtil.isNull(request.getParameter("isok_sms"), ""));
        params.put("ISOK_EMAIL",CommonUtil.isNull(request.getParameter("isok_email"), ""));
		params.put("U_AREA",CommonUtil.isNull(request.getParameter("U_AREA"), ""));

        params.put("OLD_Y", CommonUtil.isNull(request.getParameter("OLD_Y"), ""));

        //회원 등록
        memberManagementService.memberInsertProcess(params);
        memberManagementService.OldmemberDeleteProcess(params);
        model.addAttribute("params",params);

        return "redirect:/memberManagement/memberList.do";
    }

    /**
     * @Method Name  : memberDetail
     * @Date         : 2020.03
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

        try {
            params.put("USER_ID", request.getParameter("USER_ID"));

            model.addAttribute("categoryList", memberManagementService.getCategoryList());
            model.addAttribute("detail", memberManagementService.memberDetail(params));

            model.addAttribute("params",params);
        }catch(Exception e){
            System.out.println("Member view error : " +e.toString());
        }
        return "/memberManagement/memberDetail";
    }

    /**
     * @Method Name  : memberCoopDetail
     * @Date         : 2015. 05. 12.
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 남서울대 회원조회 상세
     */
    @RequestMapping(value="/memberCoopDetail.do")
    public String memberCoopDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("USER_ID",request.getParameter("USER_ID"));

        List<HashMap<String, String>> categoryList = memberManagementService.getCategoryList();
        //코드 select
        HashMap<String, Object> detail  =	memberManagementService.memberDetail(params);

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("detail",detail);
        model.addAttribute("params",params);

        return "/memberManagement/memberCoopDetail";
    }

    /**
     * @Method Name  : memberUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
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

        //추가된 내용
        params.put("JOIN_CHANNEL",CommonUtil.isNull(request.getParameter("JOIN_CHANNEL"), ""));
        params.put("JOB",CommonUtil.isNull(request.getParameter("JOB"), ""));
        params.put("EXAM_REQ",CommonUtil.isNull(request.getParameter("EXAM_REQ"), ""));
        params.put("SBJ_REQ",CommonUtil.isNull(request.getParameter("SBJ_REQ"), ""));
        params.put("F_CAT_CD",CommonUtil.isNull(request.getParameter("F_CAT_CD"), ""));
        params.put("F_AREA",CommonUtil.isNull(request.getParameter("F_AREA"), ""));
        params.put("S_CAT_CD",CommonUtil.isNull(request.getParameter("S_CAT_CD"), ""));
        params.put("S_AREA",CommonUtil.isNull(request.getParameter("S_AREA"), ""));
        params.put("INFO_REQ",CommonUtil.isNull(request.getParameter("INFO_REQ"), ""));
        params.put("EVENT_REQ",CommonUtil.isNull(request.getParameter("EVENT_REQ"), ""));
        params.put("EVENT_REQ_ETC",CommonUtil.isNull(request.getParameter("event_req_etc_txt"), ""));
        params.put("ISOK_SMS",CommonUtil.isNull(request.getParameter("ISOK_SMS"), ""));
        params.put("ISOK_EMAIL",CommonUtil.isNull(request.getParameter("ISOK_EMAIL"), ""));
		params.put("U_AREA",CommonUtil.isNull(request.getParameter("U_AREA"), "")); 

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
     * @Date         : 2020.03
     * @Author       : rainend
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
     * @Date         : 2020.03
     * @Author       : rainend
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
     * @Date         : 2020.03
     * @Author       : rainend
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
     * @Date         : 2020.03
     * @Author       : rainend
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
     * @Date         : 2020.03
     * @Author       : rainend
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
     * @Date         : 2020.03
     * @Author       : rainend
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
			/*
			 * sendMail.sendMail(String.valueOf(params.get("SUBJECT")),
			 * String.valueOf(params.get("CONT")), String.valueOf(params.get("FROM_EMAIL")),
			 * toUser);
			 */        }

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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/prime_excel.do")
    public View prime_excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        // 회원 리스트
        List<HashMap<String, Object>> list = memberManagementService.getPrimeMemberList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_제휴회원 수강명단";

        List<String> headerList = new ArrayList<String>();
        headerList.add("회원ID");
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
            if(null != map.get("UPD_DT") && !"".equals(map.get("UPD_DT"))) {
                newMap.put(i++, map.get("UPD_DT").toString());
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

        String USER_POSITION = CommonUtil.isNull(request.getParameter("USER_POSITION"), "COP");
        params.put("USER_POSITION", USER_POSITION);

        String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
        String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
        params.put("SEARCHTYPE", SEARCHTYPE);
        params.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));
        params.put("USER_POSITION", CommonUtil.isNull(request.getParameter("USER_POSITION"), "COP"));
        params.put("COOPNM", CommonUtil.isNull(request.getParameter("COOPNM"), "PRIME"));

        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
    }
}
