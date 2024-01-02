package com.willbes.web.productOrder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.MafUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.freeOrder.service.FreeOrderService;
import com.willbes.web.productOrder.service.CouponMngService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/Coupon")
@Controller
public class CouponMngController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	/* last modified 2015-04-24 */
	@Autowired
	private CouponMngService CouponMngService;

	@Autowired
	private FreeOrderService freeOrderService;

	// 전체 상품 주문 관리 리스트
	@RequestMapping(value="/list.do")
	public String list(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		List list = null;

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		//리스트
		list = CouponMngService.getCouponList(params);

		// 총 건수
		int listCount = CouponMngService.getCouponListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/coupon/Coupon_Lst";
	}

	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		Calendar month = Calendar.getInstance();
		java.util.Date date = month.getTime();
		String day = new SimpleDateFormat("yyMMdd").format(date);

		model.addAttribute("day", day);

		return "/coupon/Coupon_write";
	}

	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		params.put("CCODE", CommonUtil.isNull(req.getParameter("CCODE")));
		model.addAttribute("view", CouponMngService.getCouponOne(params));

		return "/coupon/Coupon_modify";
	}

	/**
	 * @Method Name : insert
	 * @작성일 : 2015. 04.
	 * @Method 설명 : 쿠폰 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String insert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		CouponMngService.insertCoupon(params);

		return "forward:/Coupon/list.do";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2015. 04.
	 * @Method 설명 : 쿠폰 정보 변경
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String update(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		CouponMngService.updateCoupon(params);

		return "forward:/Coupon/list.do";
	}

	// 쿠폰 발급 수강생 리스트
	@RequestMapping(value="/user_list.pop")
	public String user_list(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		List list = null;

		String SEARCHTYPE = CommonUtil.isNull(req.getParameter("SEARCHTYPE"), "");
		String SEARCHTEXT = CommonUtil.isNull(req.getParameter("SEARCHTEXT"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		params.put("CCODE", CommonUtil.isNull(req.getParameter("CCODE")));
		params.put("SEARCHTYPE", SEARCHTYPE);
		params.put("SEARCHTEXT", SEARCHTEXT);

		//리스트
		list = CouponMngService.getCouponUserList(params);

		// 총 건수
		int listCount = CouponMngService.getCouponUserListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("SEARCHTEXT", URLEncoder.encode(SEARCHTEXT,"UTF-8"));
		model.addAttribute("SEARCHTYPE", SEARCHTYPE);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/coupon/UserList_pop";
	}

	// 제휴사 수강권/쿠폰 리스트
	@RequestMapping(value="/coop_coupon_list.do")
	public String coop_coupon_list(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		List list = null;

		params.put("S_COOP_CD", CommonUtil.isNull(req.getParameter("S_COOP_CD")));

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		//리스트
		list = CouponMngService.getCoopLectureList(params);

		// 총 건수
		int listCount = CouponMngService.getCoopLectureListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("coop_list", CouponMngService.getCoopList(params));
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/coupon/Coop_Coupon_Lst";
	}

	// 제휴사 쿠폰 발급 리스트
	@RequestMapping(value="/coop_coupon_all.pop")
	public String coop_coupon_all(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		List list = null;

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		params.put("COOP_CD", CommonUtil.isNull(req.getParameter("COOP_CD")));
		params.put("LECCODE", CommonUtil.isNull(req.getParameter("LECCODE")));

		//리스트
		list = CouponMngService.getCoopCouponList(params);
		// 총 건수
		int listCount = CouponMngService.getCoopCouponListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/coupon/Coop_Coupon_All_pop";
	}

	// 제휴사 주문 관리 리스트
	@RequestMapping(value="/coop_coupon_ord.pop")
	public String coop_coupon_ord(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		List list = null;

		int currentPage = Integer.parseInt(CommonUtil.isNull(req.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(req.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		params.put("COOP_CD", CommonUtil.isNull(req.getParameter("COOP_CD")));
		params.put("LECCODE", CommonUtil.isNull(req.getParameter("LECCODE")));
		params.put("ISUSE", "Y"); //인증받은 쿠폰

		//리스트
		list = CouponMngService.getCoopCouponList(params);

		// 총 건수
		int listCount = CouponMngService.getCoopCouponListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/coupon/Coop_Coupon_Ord_pop";
	}

	@RequestMapping(value="/CoopCouponWrite.do")
	public String CoopCouponWrite(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		model.addAttribute("coop_list", CouponMngService.getCoopList(params));

		return "/coupon/Coop_Coupon_Write";
	}

	/**
	 * @Method Name : insert
	 * @작성일 : 2015. 04.
	 * @Method 설명 : 제휴사 쿠폰 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/CoopCouponInsert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String CoopCouponInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD")));
		params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE")));
		params.put("COUPON_NM", CommonUtil.isNull(request.getParameter("COUPON_NM")));
		params.put("COUPON_CNT", CommonUtil.isNull(request.getParameter("COUPON_CNT")));
		params.put("ST_DT", MafUtil.getFormatedText(CommonUtil.isNull(request.getParameter("ST_DT")), "????-??-??"));
		params.put("ED_DT", MafUtil.getFormatedText(CommonUtil.isNull(request.getParameter("ED_DT")), "????-??-??"));

		CouponMngService.insertCoopCoupon(params);

		return "forward:/Coupon/coop_coupon_list.do";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2015. 04.
	 * @Method 설명 : 제휴사 쿠폰 정보 변경
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/CoopCouponDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String CoopCouponDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD")));
		params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE")));
		CouponMngService.deleteCoopCoupon(params);

		return "forward:/Coupon/coop_coupon_list.do";
	}

	//강의선택 팝업
	@RequestMapping(value="/pop_subject_add.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_subject_add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List category_list = null;
    	List lec_list = null;
    	List subject_list = null;

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

		return "/coupon/coop_subejct_pop";
	}

	// 공무원 쿠폰 사용 현황
	@RequestMapping(value="/CouponOrderList.do")
	public String CouponOrderList(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		Calendar month = Calendar.getInstance();
		java.util.Date date = month.getTime();
		String day = new SimpleDateFormat("yyyyMMdd").format(date);

		if ("".equals(params.get("EDATE"))) {
			params.put("EDATE", day);
		}

		List list = CouponMngService.getCouponOrderList(params);
		model.addAttribute("list", list);
		model.addAttribute("params", params);

		return "/coupon/Coupon_Order_Lst";
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
	public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID", loginInfo.get("USER_ID"));
		params.put("UPD_ID", loginInfo.get("USER_ID"));

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));

		params.put("CCODE", CommonUtil.isNull(request.getParameter("CCODE"), ""));
		params.put("CNAME", CommonUtil.isNull(request.getParameter("CNAME"), ""));
		params.put("CCONTENT", CommonUtil.isNull(request.getParameter("CCONTENT"), ""));
		params.put("REGTYPE", CommonUtil.isNull(request.getParameter("REGTYPE"), ""));
		params.put("REGPRICE", CommonUtil.isNull(request.getParameter("REGPRICE"), ""));
		params.put("EXPDATES", CommonUtil.isNull(request.getParameter("EXPDATES"), ""));
		params.put("EXPDATEE", CommonUtil.isNull(request.getParameter("EXPDATEE"), ""));
		params.put("EXPDAY", CommonUtil.isNull(request.getParameter("EXPDAY"), ""));
		params.put("TCLASS", CommonUtil.isNull(request.getParameter("TCLASS"), ""));
		params.put("TCLASSCAT", CommonUtil.isNull(request.getParameter("TCLASSCAT"), ""));
		params.put("PUB_COUPON_GUBUN", CommonUtil.isNull(request.getParameter("PUB_COUPON_GUBUN"), ""));
		params.put("TERM", CommonUtil.isNull(request.getParameter("TERM"), ""));
		params.put("DAN_JONG", CommonUtil.isNull(request.getParameter("DAN_JONG"), ""));
		params.put("DAN_MENU", CommonUtil.isNull(request.getParameter("DAN_MENU"), ""));
		params.put("SUBJECT", CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
		params.put("TEACHER", CommonUtil.isNull(request.getParameter("TEACHER"), ""));
		params.put("PRICE", CommonUtil.isNull(request.getParameter("PRICE"), ""));
		params.put("ADD_FLAG", CommonUtil.isNull(request.getParameter("ADD_FLAG"), ""));
		params.put("IS_USE", CommonUtil.isNull(request.getParameter("IS_USE"), ""));

		params.put("PTYPE", CommonUtil.isNull(request.getParameter("PTYPE"), ""));
		params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
		params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
		params.put("RTYPE", CommonUtil.isNull(request.getParameter("RTYPE"), ""));
		params.put("CTYPE", CommonUtil.isNull(request.getParameter("CTYPE"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
	}

}