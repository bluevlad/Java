package com.willbes.web.freeOrder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.freeOrder.service.FreeOrderService;
import com.willbes.web.login.service.LoginService;
import com.willbes.web.productOrder.service.ProductOrderService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/freeOrder")
@Controller
public class FreeOrderController {

    /** log */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private FreeOrderService freeOrderService;

    @Autowired
    private LoginService loginservice;

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    // 수강신청 리스트
    @RequestMapping(value="/list.do")
    public String list(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String message = CommonUtil.isNull(req.getParameter("message"), "");

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "N");
        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("cmd", cmd);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        int listCount = 0;

        List list = null;
        if(!cmd.equals("N")){
            //리스트
            list = freeOrderService.getMaMemberFreeOrderList(searchMap);
            // 총 건수
            listCount = freeOrderService.getMaMemberListFreeOrderCount(searchMap);

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

        return "freeOrder/Tbl_FreeOrder_Lst";
    }

    //수강신청 등록 팝업
    @RequestMapping(value="/pop_add2.pop")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_add2(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List order_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String userid = CommonUtil.isNull(req.getParameter("userid"), "");
        String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");
        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("cmdcnt", CommonUtil.isNull(req.getParameter("cmdcnt"), "one"));
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("userid", userid);
        searchMap.put("package_no", package_no);
        searchMap.put("leccode", leccode);

        if(leccode != null && !"".equals(leccode)) {
            //수강신청 등록 팝업 리스트
            list = freeOrderService.getCbLecMstBean(searchMap);
            if(log.isDebugEnabled()){
                log.debug("***** list : "+ list) ;
            }

            if(list.size() > 0){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map = (HashMap) list.get(0);
                searchMap.put("subject_jang",map.get("SUBJECT_JANG"));
                searchMap.put("subject_pass",map.get("SUBJECT_PASS"));
                searchMap.put("seq",map.get("SEQ"));
                searchMap.put("leccode",map.get("LECCODE"));
                searchMap.put("cat_cd",map.get("CAT_CD"));
                searchMap.put("cat_nm",map.get("CAT_NM"));
                searchMap.put("menu_id",map.get("MENU_ID"));
                searchMap.put("menu_nm",map.get("MENU_NM"));
                searchMap.put("subject_teacher",map.get("SUBJECT_TEACHER"));
                searchMap.put("user_nm",map.get("USER_NM"));
                searchMap.put("subject_title",map.get("SUBJECT_TITLE"));
                searchMap.put("subject_desc",map.get("SUBJECT_DESC"));
                searchMap.put("subject_memo",map.get("SUBJECT_MEMO"));
                searchMap.put("subject_keyword",map.get("SUBJECT_KEYWORD"));
                searchMap.put("subject_period",map.get("SUBJECT_PERIOD"));
                searchMap.put("subject_off_open_year",map.get("SUBJECT_OFF_OPEN_YEAR"));
                searchMap.put("subject_off_open_month",map.get("SUBJECT_OFF_OPEN_MONTH"));
                searchMap.put("subject_discount",map.get("SUBJECT_DISCOUNT"));
                searchMap.put("subject_point",map.get("SUBJECT_POINT"));
                searchMap.put("subject_movie",map.get("SUBJECT_MOVIE"));
                searchMap.put("subject_pmp",map.get("SUBJECT_PMP"));
                searchMap.put("subject_movie_pmp",map.get("SUBJECT_MOVIE_PMP"));
                searchMap.put("subject_sumnail",map.get("SUBJECT_SUMNAIL"));
                searchMap.put("subject_event_image",map.get("SUBJECT_EVENT_IMAGE"));
                searchMap.put("subject_outside",map.get("SUBJECT_OUTSIDE"));

                searchMap.put("subject_option","VOD+MP4");
                searchMap.put("subject_isuse",map.get("SUBJECT_ISUSE"));
                searchMap.put("update_id",map.get("UPDATE_ID"));
                searchMap.put("reg_dt",map.get("REG_DT"));
                searchMap.put("update_dt",map.get("UPDATE_DT"));
                searchMap.put("subject_sjt_cd",map.get("SUBJECT_SJT_CD"));
                searchMap.put("lec_type_choice",map.get("LEC_TYPE_CHOICE"));
                searchMap.put("subject_off_open_day",map.get("SUBJECT_OFF_OPEN_DAY"));
                searchMap.put("subject_vod_default_path",map.get("SUBJECT_VOD_DEFAULT_PATH"));
                searchMap.put("subject_wide_default_path",map.get("SUBJECT_WIDE_DEFAULT_PATH"));
                searchMap.put("subject_pmp_default_path",map.get("SUBJECT_PMP_DEFAULT_PATH"));
                searchMap.put("subject_price",map.get("SUBJECT_PRICE"));
                searchMap.put("subject_mp4_default_path",map.get("SUBJECT_MP4_DEFAULT_PATH"));
                searchMap.put("re_course",map.get("RE_COURSE"));
                searchMap.put("subject_movie_mp4",map.get("SUBJECT_MOVIE_MP4"));
                searchMap.put("subject_movie_vod_mp4",map.get("SUBJECT_MOVIE_VOD_MP4"));
                searchMap.put("lec_schedule",map.get("LEC_SCHEDULE"));
                searchMap.put("to_date",map.get("TO_DATE"));
            }
        }
        //상태코드 셀렉트박스 리스트
        order_list = productOrderService.getOrderStatuscodeList(searchMap);

        output.addAttribute("order_list", order_list);
        output.addAttribute("searchMap", searchMap);

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert2";
    }
    
    //수강신청 등록 팝업
    @RequestMapping(value="/pop_add.pop")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List order_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String userid = CommonUtil.isNull(req.getParameter("userid"), "");
        String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");
        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("cmdcnt", CommonUtil.isNull(req.getParameter("cmdcnt"), "one"));
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("userid", userid);
        searchMap.put("package_no", package_no);
        searchMap.put("leccode", leccode);

        if(leccode != null && !"".equals(leccode)) {
            //수강신청 등록 팝업 리스트
            list = freeOrderService.getCbLecMstBean(searchMap);

            if(list.size() > 0){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map = (HashMap) list.get(0);
                searchMap.put("subject_jang",map.get("SUBJECT_JANG"));
                searchMap.put("subject_pass",map.get("SUBJECT_PASS"));
                searchMap.put("seq",map.get("SEQ"));
                searchMap.put("leccode",map.get("LECCODE"));
                searchMap.put("cat_cd",map.get("CAT_CD"));
                searchMap.put("cat_nm",map.get("CAT_NM"));
                searchMap.put("menu_id",map.get("MENU_ID"));
                searchMap.put("menu_nm",map.get("MENU_NM"));
                searchMap.put("subject_teacher",map.get("SUBJECT_TEACHER"));
                searchMap.put("user_nm",map.get("USER_NM"));
                searchMap.put("subject_title",map.get("SUBJECT_TITLE"));
                searchMap.put("subject_desc",map.get("SUBJECT_DESC"));
                searchMap.put("subject_memo",map.get("SUBJECT_MEMO"));
                searchMap.put("subject_keyword",map.get("SUBJECT_KEYWORD"));
                searchMap.put("subject_period",map.get("SUBJECT_PERIOD"));
                searchMap.put("subject_off_open_year",map.get("SUBJECT_OFF_OPEN_YEAR"));
                searchMap.put("subject_off_open_month",map.get("SUBJECT_OFF_OPEN_MONTH"));
                searchMap.put("subject_discount",map.get("SUBJECT_DISCOUNT"));
                searchMap.put("subject_point",map.get("SUBJECT_POINT"));
                searchMap.put("subject_movie",map.get("SUBJECT_MOVIE"));
                searchMap.put("subject_pmp",map.get("SUBJECT_PMP"));
                searchMap.put("subject_movie_pmp",map.get("SUBJECT_MOVIE_PMP"));
                searchMap.put("subject_sumnail",map.get("SUBJECT_SUMNAIL"));
                searchMap.put("subject_event_image",map.get("SUBJECT_EVENT_IMAGE"));
                searchMap.put("subject_outside",map.get("SUBJECT_OUTSIDE"));

                searchMap.put("subject_option","VOD+MP4");
                searchMap.put("subject_isuse",map.get("SUBJECT_ISUSE"));
                searchMap.put("update_id",map.get("UPDATE_ID"));
                searchMap.put("reg_dt",map.get("REG_DT"));
                searchMap.put("update_dt",map.get("UPDATE_DT"));
                searchMap.put("subject_sjt_cd",map.get("SUBJECT_SJT_CD"));
                searchMap.put("lec_type_choice",map.get("LEC_TYPE_CHOICE"));
                searchMap.put("subject_off_open_day",map.get("SUBJECT_OFF_OPEN_DAY"));
                searchMap.put("subject_vod_default_path",map.get("SUBJECT_VOD_DEFAULT_PATH"));
                searchMap.put("subject_wide_default_path",map.get("SUBJECT_WIDE_DEFAULT_PATH"));
                searchMap.put("subject_pmp_default_path",map.get("SUBJECT_PMP_DEFAULT_PATH"));
                searchMap.put("subject_price",map.get("SUBJECT_PRICE"));
                searchMap.put("subject_mp4_default_path",map.get("SUBJECT_MP4_DEFAULT_PATH"));
                searchMap.put("re_course",map.get("RE_COURSE"));
                searchMap.put("subject_movie_mp4",map.get("SUBJECT_MOVIE_MP4"));
                searchMap.put("subject_movie_vod_mp4",map.get("SUBJECT_MOVIE_VOD_MP4"));
                searchMap.put("lec_schedule",map.get("LEC_SCHEDULE"));
                searchMap.put("to_date",map.get("TO_DATE"));
            }
        }
        //상태코드 셀렉트박스 리스트
        order_list = productOrderService.getOrderStatuscodeList(searchMap);

        output.addAttribute("order_list", order_list);
        output.addAttribute("searchMap", searchMap);

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert";
    }
    
    //다중강의선택 팝업
    @RequestMapping(value="/pop_subject_add2.pop")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_subject_add2(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List category_list = null;
        List lec_list = null;
        List subject_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int p_currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("p_currentPage"), "1"));
        int p_pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("p_pageRow"), propertiesService.getInt("pageUnit")+""));

        String userid = CommonUtil.isNull(req.getParameter("userid"), "");

        String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");
        String s_sjt_cd = CommonUtil.isNull(req.getParameter("s_sjt_cd"), "");
        String s_menu_id = CommonUtil.isNull(req.getParameter("s_menu_id"), "");
        String search_type = CommonUtil.isNull(req.getParameter("search_type"), "");
        String search_keyword = CommonUtil.isNull(req.getParameter("search_keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("p_currentPage", p_currentPage);
        searchMap.put("p_pageRow", p_pageRow);

        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("userid", userid);

        searchMap.put("s_cat_cd", s_cat_cd);
        searchMap.put("s_sjt_cd", s_sjt_cd);
        searchMap.put("s_menu_id", s_menu_id);
        searchMap.put("search_type", search_type);
        searchMap.put("search_keyword", search_keyword);

        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        //카테고리 셀렉트박스 리스트
        category_list = freeOrderService.getCaCatCdList(searchMap);

        //학습형태 셀렉트박스 리스트
        lec_list = freeOrderService.getVwMenuMstTree_lec(searchMap);

        //과목 셀렉트박스 리스트
        subject_list = freeOrderService.getCaSubjectCdList(searchMap);

        //강의선택 팝업  리스트
        list = freeOrderService.getCbLecMstFreeOrderList(searchMap);

        //강의선택 팝업  카운트
        int listCount = freeOrderService.getCbLecMstListFreeOrderCount(searchMap);

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

        return "freeOrder/Tbl_FreeOrder_subejct_pop2";
    }
    
    //강의선택 팝업
    @RequestMapping(value="/pop_subject_add.pop")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_subject_add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List category_list = null;
        List lec_list = null;
        List subject_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int p_currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("p_currentPage"), "1"));
        int p_pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("p_pageRow"), propertiesService.getInt("pageUnit")+""));

        String userid = CommonUtil.isNull(req.getParameter("userid"), "");

        String s_cat_cd = CommonUtil.isNull(req.getParameter("s_cat_cd"), "");
        String s_sjt_cd = CommonUtil.isNull(req.getParameter("s_sjt_cd"), "");
        String s_menu_id = CommonUtil.isNull(req.getParameter("s_menu_id"), "");
        String search_type = CommonUtil.isNull(req.getParameter("search_type"), "");
        String search_keyword = CommonUtil.isNull(req.getParameter("search_keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("p_currentPage", p_currentPage);
        searchMap.put("p_pageRow", p_pageRow);

        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("userid", userid);

        searchMap.put("s_cat_cd", s_cat_cd);
        searchMap.put("s_sjt_cd", s_sjt_cd);
        searchMap.put("s_menu_id", s_menu_id);
        searchMap.put("search_type", search_type);
        searchMap.put("search_keyword", search_keyword);

        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        //카테고리 셀렉트박스 리스트
        category_list = freeOrderService.getCaCatCdList(searchMap);

        //학습형태 셀렉트박스 리스트
        lec_list = freeOrderService.getVwMenuMstTree_lec(searchMap);

        //과목 셀렉트박스 리스트
        subject_list = freeOrderService.getCaSubjectCdList(searchMap);

        //강의선택 팝업  리스트
        list = freeOrderService.getCbLecMstFreeOrderList(searchMap);

        //강의선택 팝업  카운트
        int listCount = freeOrderService.getCbLecMstListFreeOrderCount(searchMap);

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

        return "freeOrder/Tbl_FreeOrder_subejct_pop";
    }

    // 다중수강신청 등록 팝업 등록처리
    @RequestMapping(value="/pop_subject_add_insert2.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_subject_add_insert2(ModelMap output, HttpServletRequest req) throws IllegalStateException, IOException {

        List list = null;

        HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
        String USER_ID = sessionMap.get("USER_ID");

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        // 검색조건
        String userid2 = CommonUtil.isNull(req.getParameter("userid"), "");
        userid2 = userid2.replace(',', '/');
        //String[] userid = req.getParameterValues("userid");
        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");     
        leccode = leccode.substring(0, leccode.length()-1);

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String start_date = CommonUtil.isNull(req.getParameter("start_date"), "");
        String subject_period = CommonUtil.isNull(req.getParameter("subject_period"), "");
        String statuscode = CommonUtil.isNull(req.getParameter("statuscode"), "");
        String subject_price = CommonUtil.isNull(req.getParameter("subject_price"), "");
        String wmv_check = CommonUtil.isNull(req.getParameter("wmv_check"), "");
        String memo = CommonUtil.isNull(req.getParameter("memo"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));

        searchMap.put("USER_ID", USER_ID);
        searchMap.put("userid2", userid2);


        String msg = "";

        TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
        TimeZone.setDefault(tz);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String todate = sdf.format(d);

        if (USER_ID.length() > 0) { 		// 관리자아이디 세션 체크
            if (userid2.length() > 0) {      // 수강등록 아이디 체크
                String [] tmp = null;
                String [] tmp2 = null;
                tmp = userid2.split("/");
                tmp2 = leccode.split("/");

                for (int i = 0; i < tmp.length; i++) {

                    String userid = tmp[i].trim();
                  
                    if (!"".equals(userid)) {

                        searchMap.put("userid", userid);
                        //아이디 존재여부
                        list = freeOrderService.getMUser(searchMap);
                        if(list.size() > 0){
                        	for(int l=0; l < tmp2.length; l++){
                        	
                        	String Leccode = tmp2[l].trim();
                        	searchMap.put("leccode", Leccode);
					        searchMap.put("mgntno", Leccode);
					        searchMap.put("start_date", start_date);
					        searchMap.put("subject_period", subject_period);
					        searchMap.put("statuscode", statuscode);
					        searchMap.put("subject_price", subject_price);
					        searchMap.put("wmv_check", wmv_check);
					        searchMap.put("memo", memo);
                            searchMap.put("ORDERNO_COUNT", "M");
                            //채번
                            list = freeOrderService.getMCount(searchMap);

                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map = (HashMap) list.get(0);

                            String s_orderno = map.get("ORDERNO_COUNT").toString();

                            searchMap.put("orderno", s_orderno);
                            String userIp 		= "";
                            userIp 				= req.getRemoteAddr();

                            searchMap.put("USER_IP", userIp);

                            freeOrderService.insertLecture1(searchMap);
                            freeOrderService.insertLecture2(searchMap);
                            freeOrderService.insertLecture3(searchMap);

                            if(Leccode.substring(0, 1).equals("D")){ //수강신청시 단과만
                                if (Integer.parseInt(start_date) >= Integer.parseInt(todate)) {
                                    searchMap.put("start_date", start_date);
                                }else if (Integer.parseInt(start_date) < Integer.parseInt(todate)) {
                                    //searchMap.put("start_date", todate);
                                    searchMap.put("start_date", start_date);
                                }
                                searchMap.put("Leccode", Leccode);
                                freeOrderService.insertLecture4(searchMap);
                            }else{ //수강신청시 종합반
                                list = freeOrderService.getLeccode(searchMap);
                                if (list.size() > 0) {
                                    for (int j = 0; j < list.size(); j++) {
                                        map = new HashMap<String, Object>();
                                        map = (HashMap) list.get(j);

                                        searchMap.put("Leccode", map.get("MST_LECCODE"));
                                        if (Integer.parseInt(start_date) >= Integer.parseInt(todate)) {
                                            searchMap.put("start_date", start_date);
                                        }else if (Integer.parseInt(start_date) < Integer.parseInt(todate)) {
                                            //searchMap.put("start_date", todate);
                                            searchMap.put("start_date", start_date);
                                        }
                                        
                                        // 수강신청 테이블에 삽입한다. (TB_MYLECTURE)
                                        freeOrderService.insertLecture4(searchMap);
                                    }
                                }
                            }                      
                          } // 다중수강 for 문
                        } // 수강등록 아이디 체크
                    } // 수강등록 아이디 공백 체크
                } // 수강등록 아이디 for 문
            }
        } else {
            msg = "관리자의 아이디가 정확하지 않습니다. 재로그인이 필요 합니다.";
        }

        output.addAttribute("msg", msg);
        output.addAttribute("message", "등록완료");

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert2";
    }

    // 수강신청 등록 팝업 등록처리
    @RequestMapping(value="/pop_subject_add_insert.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_subject_add_insert(ModelMap output, HttpServletRequest req) throws IllegalStateException, IOException {

        List list = null;

        HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
        String USER_ID = sessionMap.get("USER_ID");

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        // 검색조건
        String userid2 = CommonUtil.isNull(req.getParameter("userid"), "");
        userid2 = userid2.replace(',', '/');
        //String[] userid = req.getParameterValues("userid");
        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");
        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String start_date = CommonUtil.isNull(req.getParameter("start_date"), "");
        String subject_period = CommonUtil.isNull(req.getParameter("subject_period"), "");
        String statuscode = CommonUtil.isNull(req.getParameter("statuscode"), "");
        String subject_price = CommonUtil.isNull(req.getParameter("subject_price"), "");
        String wmv_check = CommonUtil.isNull(req.getParameter("wmv_check"), "");
        String memo = CommonUtil.isNull(req.getParameter("memo"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));

        searchMap.put("USER_ID", USER_ID);
        searchMap.put("userid2", userid2);
        searchMap.put("leccode", leccode);
        searchMap.put("mgntno", leccode);
        searchMap.put("orderno", orderno);
        searchMap.put("start_date", start_date);
        searchMap.put("subject_period", subject_period);
        searchMap.put("statuscode", statuscode);
        searchMap.put("subject_price", subject_price);
        searchMap.put("wmv_check", wmv_check);
        searchMap.put("memo", memo);

        String msg = "";

        TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
        TimeZone.setDefault(tz);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String todate = sdf.format(d);

        if (USER_ID.length() > 0) {
            if (userid2.length() > 0) {
                String [] tmp = null;
                tmp = userid2.split("/");

                for (int i = 0; i < tmp.length; i++) {

                    String userid = tmp[i].trim();
                  
                    if (!"".equals(userid)) {

                        searchMap.put("userid", userid);
                        //아이디 존재여부
                        list = freeOrderService.getMUser(searchMap);
                        if(list.size() > 0){
                            searchMap.put("ORDERNO_COUNT", "M");
                            //채번
                            list = freeOrderService.getMCount(searchMap);

                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map = (HashMap) list.get(0);

                            String s_orderno = map.get("ORDERNO_COUNT").toString();

                            searchMap.put("orderno", s_orderno);
                            String userIp 		= "";
                            userIp 				= req.getRemoteAddr();

                            searchMap.put("USER_IP", userIp);

                            freeOrderService.insertLecture1(searchMap);
                            freeOrderService.insertLecture2(searchMap);
                            freeOrderService.insertLecture3(searchMap);

                            if(leccode.substring(0, 1).equals("D")){ //수강신청시 단과만
                                if (Integer.parseInt(start_date) >= Integer.parseInt(todate)) {
                                    searchMap.put("start_date", start_date);
                                }else if (Integer.parseInt(start_date) < Integer.parseInt(todate)) {
                                    //searchMap.put("start_date", todate); 전제신과장님 요청
                                    searchMap.put("start_date", start_date); 
                                }
                                searchMap.put("Leccode", leccode);
                                freeOrderService.insertLecture4(searchMap);
                            }else if(leccode.substring(0, 1).equals("J") || leccode.substring(0, 1).equals("N") || leccode.substring(0, 1).equals("P")){ //수강신청시 종합반
                                list = freeOrderService.getLeccode(searchMap);
                                if (list.size() > 0) {
                                    for (int j = 0; j < list.size(); j++) {
                                        map = new HashMap<String, Object>();
                                        map = (HashMap) list.get(j);

                                        searchMap.put("Leccode", map.get("MST_LECCODE"));
                                        if (Integer.parseInt(start_date) >= Integer.parseInt(todate)) {
                                            searchMap.put("start_date", start_date);
                                        }else if (Integer.parseInt(start_date) < Integer.parseInt(todate)) {
                                            //searchMap.put("start_date", todate); 전제신과장님 요청
                                            searchMap.put("start_date", start_date);
                                        }
                                        
                                        // 수강신청 테이블에 삽입한다. (TB_MYLECTURE)
                                        freeOrderService.insertLecture4(searchMap);
                                    }
                                }
                            }else{
                            	freeOrderService.insertLecture5(searchMap);
                            }
                        }
                    }
                }
            }
        } else {
            msg = "관리자의 아이디가 정확하지 않습니다. 재로그인이 필요 합니다.";
        }

        output.addAttribute("msg", msg);
        output.addAttribute("message", "등록완료");

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert";
    }

    // 수강변경 리스트
    @RequestMapping(value="/changelist.do")
    public String changelist(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String message = CommonUtil.isNull(req.getParameter("message"), "");

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "N");
        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "").trim();
        if (!"".equals(keyword) && keyword.length() > 0 ){
            cmd = "Y";
        }

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("message", URLDecoder.decode(message,"UTF-8"));
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("cmd", cmd);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        int listCount = 0;

        if(!cmd.equals("N")){
            //리스트
            list = freeOrderService.getChangeList(searchMap);
            // 총 건수
            listCount = freeOrderService.getChangeListCount(searchMap);

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

        return "freeOrder/Tbl_ChangeLecture_Lst";
    }

    // 수강변경 상세
    @RequestMapping(value="/viewChange.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String viewChange(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List del_list = null;
        List order_list = null;
        List lec_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("cmd", cmd);
        searchMap.put("keyword", keyword);
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("orderno", orderno);
        searchMap.put("package_no", package_no);

        //리스트
        list = freeOrderService.getTblApprovalsViewList(searchMap);

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
        pay_list.put("TID",map.get("TID"));

        // 총 건수
        int deliverscount = freeOrderService.getTblDeliversViewListCount(searchMap);
        searchMap.put("deliverscount", deliverscount);

        if(deliverscount > 0){
            del_list = freeOrderService.getTblDeliversViewList(searchMap);

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

        order_list = freeOrderService.getChangeViewList2(searchMap);

        map = new HashMap<String, Object>();
        map = (HashMap) order_list.get(0);
        Map<String, Object> order_list2 = new HashMap<String, Object>();
        order_list2.put("ORDERNO",map.get("ORDERNO"));
        order_list2.put("REG_DT",map.get("REG_DT"));
        order_list2.put("USER_NM",map.get("USER_NM"));
        order_list2.put("TEL_NO",map.get("TEL_NO"));
        order_list2.put("PHONE_NO",map.get("PHONE_NO"));
        order_list2.put("ZIP_CODE",map.get("ZIP_CODE"));
        order_list2.put("ADDRESS1",map.get("ADDRESS1"));
        order_list2.put("EMAIL",map.get("EMAIL"));
        order_list2.put("BIRTH_DT",map.get("BIRTH_DT"));

        lec_list = freeOrderService.getChangeViewList(searchMap);

        output.addAttribute("deliverscount", deliverscount);
        output.addAttribute("searchMap", searchMap);
        output.addAttribute("list", pay_list);
        output.addAttribute("order_list", order_list2);
        output.addAttribute("lec_list", lec_list);
        output.addAttribute("approval_list", list);

        return "freeOrder/Tbl_ChangeLecture_View";
    }

    //수강변경 팝업
    @RequestMapping(value="/pop_change.pop")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_change(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
        List list = null;
        List order_list = null;

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String cmd = CommonUtil.isNull(req.getParameter("cmd"), "");
        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String userid = CommonUtil.isNull(req.getParameter("userid"), "");
        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String package_no = CommonUtil.isNull(req.getParameter("package_no"), "");

        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Object> searchMap2 = new HashMap<String, Object>();

        searchMap.put("cmd", cmd);
        searchMap.put("cmdcnt", CommonUtil.isNull(req.getParameter("cmdcnt"), "one"));
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("userid", userid);
        searchMap.put("orderno", orderno);
        searchMap.put("package_no", package_no);

        searchMap.put("leccode", leccode);

        //수강변경 팝업 리스트(이전)
        list = freeOrderService.getCbLecMstBean_2(searchMap);

        if(list.size() > 0){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map = (HashMap) list.get(0);
            searchMap2.put("cmdcnt", CommonUtil.isNull(req.getParameter("cmdcnt"), "one"));
            searchMap2.put("subject_jang",map.get("SUBJECT_JANG"));
            searchMap2.put("subject_pass",map.get("SUBJECT_PASS"));
            searchMap2.put("seq",map.get("SEQ"));
            searchMap2.put("leccode",map.get("LECCODE"));
            searchMap2.put("cat_cd",map.get("CAT_CD"));
            searchMap2.put("cat_nm",map.get("CAT_NM"));
            searchMap2.put("menu_id",map.get("MENU_ID"));
            searchMap2.put("menu_nm",map.get("MENU_NM"));
            searchMap2.put("subject_teacher",map.get("SUBJECT_TEACHER"));
            searchMap2.put("user_nm",map.get("USER_NM"));
            searchMap2.put("subject_title",map.get("SUBJECT_TITLE"));
            searchMap2.put("subject_desc",map.get("SUBJECT_DESC"));
            searchMap2.put("subject_memo",map.get("SUBJECT_MEMO"));
            searchMap2.put("subject_keyword",map.get("SUBJECT_KEYWORD"));
            searchMap2.put("subject_period",map.get("SUBJECT_PERIOD"));
            searchMap2.put("subject_off_open_year",map.get("SUBJECT_OFF_OPEN_YEAR"));
            searchMap2.put("subject_off_open_month",map.get("SUBJECT_OFF_OPEN_MONTH"));
            searchMap2.put("subject_discount",map.get("SUBJECT_DISCOUNT"));
            searchMap2.put("subject_point",map.get("SUBJECT_POINT"));
            searchMap2.put("subject_movie",map.get("SUBJECT_MOVIE"));
            searchMap2.put("subject_pmp",map.get("SUBJECT_PMP"));
            searchMap2.put("subject_movie_pmp",map.get("SUBJECT_MOVIE_PMP"));
            searchMap2.put("subject_sumnail",map.get("SUBJECT_SUMNAIL"));
            searchMap2.put("subject_event_image",map.get("SUBJECT_EVENT_IMAGE"));
            searchMap2.put("subject_outside",map.get("SUBJECT_OUTSIDE"));

            searchMap.put("subject_option","VOD+MP4");
            searchMap2.put("subject_isuse",map.get("SUBJECT_ISUSE"));
            searchMap2.put("update_id",map.get("UPDATE_ID"));
            searchMap2.put("reg_dt",map.get("REG_DT"));
            searchMap2.put("update_dt",map.get("UPDATE_DT"));
            searchMap2.put("subject_sjt_cd",map.get("SUBJECT_SJT_CD"));
            searchMap2.put("lec_type_choice",map.get("LEC_TYPE_CHOICE"));
            searchMap2.put("subject_off_open_day",map.get("SUBJECT_OFF_OPEN_DAY"));
            searchMap2.put("subject_vod_default_path",map.get("SUBJECT_VOD_DEFAULT_PATH"));
            searchMap2.put("subject_pmp_default_path",map.get("SUBJECT_PMP_DEFAULT_PATH"));
            searchMap2.put("subject_price",map.get("SUBJECT_PRICE"));
            searchMap2.put("subject_mp4_default_path",map.get("SUBJECT_MP4_DEFAULT_PATH"));
            searchMap2.put("re_course",map.get("RE_COURSE"));
            searchMap2.put("subject_movie_mp4",map.get("SUBJECT_MOVIE_MP4"));
            searchMap2.put("subject_movie_vod_mp4",map.get("SUBJECT_MOVIE_VOD_MP4"));
            searchMap2.put("lec_schedule",map.get("LEC_SCHEDULE"));
            searchMap2.put("to_date",map.get("TO_DATE"));
        }

        if(leccode != null || !"".equals(leccode)) {
            //수강신청 등록 팝업 리스트
            list = freeOrderService.getCbLecMstBean(searchMap);

            if(list.size() > 0){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map = (HashMap) list.get(0);
                searchMap.put("cmdcnt", CommonUtil.isNull(req.getParameter("cmdcnt"), "one"));
                searchMap.put("subject_jang",map.get("SUBJECT_JANG"));
                searchMap.put("subject_pass",map.get("SUBJECT_PASS"));
                searchMap.put("seq",map.get("SEQ"));
                searchMap.put("leccode",map.get("LECCODE"));
                searchMap.put("cat_cd",map.get("CAT_CD"));
                searchMap.put("cat_nm",map.get("CAT_NM"));
                searchMap.put("menu_id",map.get("MENU_ID"));
                searchMap.put("menu_nm",map.get("MENU_NM"));
                searchMap.put("subject_teacher",map.get("SUBJECT_TEACHER"));
                searchMap.put("user_nm",map.get("USER_NM"));
                searchMap.put("subject_title",map.get("SUBJECT_TITLE"));
                searchMap.put("subject_desc",map.get("SUBJECT_DESC"));
                searchMap.put("subject_memo",map.get("SUBJECT_MEMO"));
                searchMap.put("subject_keyword",map.get("SUBJECT_KEYWORD"));
                searchMap.put("subject_period",map.get("SUBJECT_PERIOD"));
                searchMap.put("subject_off_open_year",map.get("SUBJECT_OFF_OPEN_YEAR"));
                searchMap.put("subject_off_open_month",map.get("SUBJECT_OFF_OPEN_MONTH"));
                searchMap.put("subject_discount",map.get("SUBJECT_DISCOUNT"));
                searchMap.put("subject_point",map.get("SUBJECT_POINT"));
                searchMap.put("subject_movie",map.get("SUBJECT_MOVIE"));
                searchMap.put("subject_pmp",map.get("SUBJECT_PMP"));
                searchMap.put("subject_movie_pmp",map.get("SUBJECT_MOVIE_PMP"));
                searchMap.put("subject_sumnail",map.get("SUBJECT_SUMNAIL"));
                searchMap.put("subject_event_image",map.get("SUBJECT_EVENT_IMAGE"));
                searchMap.put("subject_outside",map.get("SUBJECT_OUTSIDE"));

                searchMap.put("subject_option","VOD+MP4");
                searchMap.put("subject_isuse",map.get("SUBJECT_ISUSE"));
                searchMap.put("update_id",map.get("UPDATE_ID"));
                searchMap.put("reg_dt",map.get("REG_DT"));
                searchMap.put("update_dt",map.get("UPDATE_DT"));
                searchMap.put("subject_sjt_cd",map.get("SUBJECT_SJT_CD"));
                searchMap.put("lec_type_choice",map.get("LEC_TYPE_CHOICE"));
                searchMap.put("subject_off_open_day",map.get("SUBJECT_OFF_OPEN_DAY"));
                searchMap.put("subject_vod_default_path",map.get("SUBJECT_VOD_DEFAULT_PATH"));
                searchMap.put("subject_pmp_default_path",map.get("SUBJECT_PMP_DEFAULT_PATH"));
                searchMap.put("subject_price",map.get("SUBJECT_PRICE"));
                searchMap.put("subject_mp4_default_path",map.get("SUBJECT_MP4_DEFAULT_PATH"));
                searchMap.put("re_course",map.get("RE_COURSE"));
                searchMap.put("subject_movie_mp4",map.get("SUBJECT_MOVIE_MP4"));
                searchMap.put("subject_movie_vod_mp4",map.get("SUBJECT_MOVIE_VOD_MP4"));
                searchMap.put("lec_schedule",map.get("LEC_SCHEDULE"));
                searchMap.put("to_date",map.get("TO_DATE"));

            }
        }

        //상태코드 셀렉트박스 리스트
        order_list = productOrderService.getOrderStatuscodeList(searchMap);

        output.addAttribute("order_list", order_list);
        output.addAttribute("searchMap", searchMap);
        output.addAttribute("searchMap2", searchMap2);

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert";
    }

    // 수강변경 등록 팝업 수정처리
    @RequestMapping(value="/pop_subject_add_update.do")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public String pop_subject_add_update(ModelMap output, HttpServletRequest req) throws IllegalStateException, IOException {

        HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");
        String USER_ID = sessionMap.get("USER_ID");

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(req.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(req.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(req.getParameter("L_MENU_NM"), ""));
        output.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(req.getParameter("keyword"), "");

        int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        // 검색조건
        String userid = CommonUtil.isNull(req.getParameter("userid"), "");
        String orderno = CommonUtil.isNull(req.getParameter("orderno"), "");
        String leccode = CommonUtil.isNull(req.getParameter("leccode"), "");
        String leccode_2 = CommonUtil.isNull(req.getParameter("leccode_2"), "");
        String start_date = CommonUtil.isNull(req.getParameter("start_date"), "");
        String subject_period = CommonUtil.isNull(req.getParameter("subject_period"), "");
        String statuscode = CommonUtil.isNull(req.getParameter("statuscode"), "");
        String subject_price = CommonUtil.isNull(req.getParameter("subject_price"), "");
        String wmv_check = CommonUtil.isNull(req.getParameter("wmv_check"), "");
        String memo = CommonUtil.isNull(req.getParameter("memo"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);

        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));

        searchMap.put("USER_ID", USER_ID);
        searchMap.put("userid", userid);
        searchMap.put("leccode", leccode);
        searchMap.put("leccode_2", leccode_2);
        searchMap.put("mgntno", leccode);
        searchMap.put("orderno", orderno);
        searchMap.put("start_date", start_date);
        searchMap.put("subject_period", subject_period);
        searchMap.put("statuscode", statuscode);
        searchMap.put("subject_price", subject_price);
        searchMap.put("wmv_check", wmv_check);
        searchMap.put("memo", memo);

        freeOrderService.updateChangeLec(searchMap);
        freeOrderService.insertChangeLec1(searchMap);
        freeOrderService.insertChangeLec2(searchMap);
        freeOrderService.insertChangeLec3(searchMap);

        String msg = "";

        output.addAttribute("searchMap", searchMap);
        output.addAttribute("msg", msg);
        output.addAttribute("message", "변경완료");

        return "freeOrder/Tbl_FreeOrder_subejct_pop_insert";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="/excel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(request.getParameter("keyword"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("startNo", "0");
        searchMap.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        List list = freeOrderService.getMaMemberFreeOrderList(searchMap);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String excelName = "수강신청목록_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("이름");
        headerList.add("아이디");
        headerList.add("생년월일");
        headerList.add("전화번호");
        headerList.add("휴대폰");
        headerList.add("이메일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

        if(null != list && list.size() > 0) {
            for(int j=0; j < list.size(); j++) {
                HashMap<String, Object> map = (HashMap<String, Object>)list.get(j);

                HashMap newMap = new HashMap();
                int i = 0;
                if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM"))) {
                    newMap.put(i++, map.get("USER_NM").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID"))) {
                    newMap.put(i++, map.get("USER_ID").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("BIRTH_DAY") && !"".equals(map.get("BIRTH_DAY"))) {
                    newMap.put(i++, map.get("BIRTH_DAY").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("TEL_NO") && !"".equals(map.get("TEL_NO"))) {
                    newMap.put(i++, map.get("TEL_NO").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("PHONE_NO") && !"".equals(map.get("PHONE_NO"))) {
                    newMap.put(i++, map.get("PHONE_NO").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("EMAIL") && !"".equals(map.get("EMAIL"))) {
                    newMap.put(i++, map.get("EMAIL").toString());
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="/excel_change.do")
    public View excelChangeDownload(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {

        //메뉴 param
        HashMap<String, String> menuParams = new HashMap<String, String>();
        menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("menuParams", menuParams);
        //메뉴 param

        String keyword = CommonUtil.isNull(request.getParameter("keyword"), "");

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
        searchMap.put("startNo", "0");
        searchMap.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        List list = freeOrderService.getChangeList(searchMap);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String excelName = "수강변경목록_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("주문번호");
        headerList.add("이름");
        headerList.add("아이디");
        headerList.add("강의코드");
        headerList.add("강의제목");
        headerList.add("수강료");
        headerList.add("결제상태");
        headerList.add("신청일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

        if(null != list && list.size() > 0) {
            for(int j=0; j < list.size(); j++) {
                HashMap<String, Object> map = (HashMap<String, Object>)list.get(j);

                HashMap newMap = new HashMap();
                int i = 0;
                if(null != map.get("ORDERNO") && !"".equals(map.get("ORDERNO"))) {
                    newMap.put(i++, map.get("ORDERNO").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM"))) {
                    newMap.put(i++, map.get("USER_NM").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID"))) {
                    newMap.put(i++, map.get("USER_ID").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("PACKAGE_NO") && !"".equals(map.get("PACKAGE_NO"))) {
                    newMap.put(i++, map.get("PACKAGE_NO").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("PACKAGE_NAME") && !"".equals(map.get("PACKAGE_NAME"))) {
                    newMap.put(i++, map.get("PACKAGE_NAME").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("PRICE") && !"".equals(map.get("PRICE"))) {
                    newMap.put(i++, map.get("PRICE"));
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("STATUSNAME") && !"".equals(map.get("STATUSNAME"))) {
                    newMap.put(i++, map.get("STATUSNAME").toString());
                } else {
                    newMap.put(i++, "");
                }
                if(null != map.get("REGDATE") && !"".equals(map.get("REGDATE"))) {
                    newMap.put(i++, map.get("REGDATE").toString());
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
     * @Method Name : chkIP
     */
    @RequestMapping(value="/chkIP")
    @ResponseBody
    public String chkIP(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();

        HttpSession session = request.getSession();
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		
		params.put("USERID", loginInfo.get("USER_ID"));
        params.put("USER_IP", request.getRemoteAddr());
        params.put("LOG_CNT", "2");
        params.put("isAdmin", "Y");
        params.put("isLec", "Y");
        
        List<HashMap<String, String>> isLogin = loginservice.login_ip(params);
        if (isLogin.size() > 0) { //기존아이피로 로그인되었을경우
            return "Y";
        }else{ //새로운 아이피로 로그인되었을경우
            return "N";
        }

    }

    /**
     * @Method Name : sendSMS
     * @작성일 : 2017. 6.
     * @Method 설명 : SMS 인증 문자 메세지
     * @param request
     * @return
     */
    @RequestMapping(value="/sendSMS")
    public String loginSMS(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        HttpSession session = request.getSession();
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		
		params.put("USERID", loginInfo.get("USER_ID"));
        
        List<HashMap<String, String>> result = loginservice.login(params);
        if (result.size() > 0) {
            HashMap<String, String> login = result.get(0);

            Random rnd = new Random(System.currentTimeMillis());
            String code  = "";
            for(int i=0 ; i < 5 ; i++){
                int a = rnd.nextInt(9)+1;
                code += String.valueOf(a);
            }
            
            String message = "윌비스 공무원 관리자 수강등록 인증 요청! ("+login.get("USER_NM")+"("+login.get("USER_NICKNM")+")님 인증요청) 인증번호[" +code+"]";
            params.put("MESSAGE", message);
//            params.put("PHONE_NO", "01071768003"); //배원장님 전화번호
            params.put("PHONE_NO", "01089492047"); //최진영 차장 전화번호 - 테스트

            params.put("COMPANY_NO", login.get("PHONE_NO"));
            params.put("isLec", "Y");

            session.setAttribute("SMSCODE", code);

//            loginservice.insertSMS(params);

            model.addAttribute("params" , params);
            model.addAttribute("SMSCODE" , code);
        }

        return "/freeOrder/sendSMS_pop";
    }

    @RequestMapping(value="/PhoneCheck")
    @ResponseBody
    public String loginPhoneCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();

        String code = CommonUtil.isNull(request.getParameter("smsCode"), "");
        HttpSession session = request.getSession();
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
        
        String SMSCODE = (String) session.getAttribute("SMSCODE");

        if(code.equals(SMSCODE)){
            //관리자 로그인 기록
    		params.put("USERID", loginInfo.get("USER_ID"));
            params.put("USER_IP", request.getRemoteAddr());
            params.put("isLec", "Y");
            loginservice.mbAccessInsert(params);
            return "Y";
        }else{
            return "N";
        }
    }

}
