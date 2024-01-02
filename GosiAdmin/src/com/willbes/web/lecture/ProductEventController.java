package com.willbes.web.lecture;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.book.service.BookService;
import com.willbes.web.lecture.service.LectureService;
import com.willbes.web.lecture.service.ProductEventService;
import com.willbes.web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/productevent")
@Controller
public class ProductEventController {

	/** log */
	private Logger logger = LoggerFactory.getLogger(getClass());
//	private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private ProductEventService productevent;
	@Autowired
	private LectureService lectureservice;
	@Autowired
	private BookService bookservice;
	@Autowired
	private TeacherService teacherservice;

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/list.do")
	public String list(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = productevent.list(params);
		int listCount = productevent.listCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/event/list";
	}

	/**
	 * @Method Name : write
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 등록폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params", params);
		return "/lecture/event/write";
	}

	/**
	 * @Method Name : modify
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 수정폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> item = productevent.getOne(params);
		List<HashMap<String, String>> list_prd = productevent.list_prd(params);

		model.addAttribute("item", item);
		model.addAttribute("list_prd", list_prd);
		model.addAttribute("params", params);
		return "/lecture/event/modify";
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String save(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		productevent.insert(params);				// TB_LEC_MST (INSERT)

		return "redirect:/productevent/list.do";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 변경 프로세스
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

		productevent.update(params);				// TB_LEC_MST (INSERT)

		return "redirect:/productevent/list.do";
	}

	//강의선택 팝업
	@RequestMapping(value="/pop_subject_add.pop")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String pop_subject_add(ModelMap output, HttpServletRequest req) throws UnsupportedEncodingException {
		List list = null;
		List category_list = null;
    	List lec_list = null;
    	List subject_list = null;

		HashMap<String, String> sessionMap = (HashMap)req.getSession().getAttribute("AdmUserInfo");

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
		String EVENT_ID = CommonUtil.isNull(req.getParameter("EVENT_ID"), "");
		String INS = CommonUtil.isNull(req.getParameter("INS"), "");

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
		category_list = productevent.getCaCatCdList(searchMap);

		//학습형태 셀렉트박스 리스트
		lec_list = productevent.getVwMenuMstTree_lec(searchMap);

		//과목 셀렉트박스 리스트
		subject_list = productevent.getCaSubjectCdList(searchMap);

		//강의선택 팝업  리스트
		list = productevent.getCbLecMstFreeOrderList(searchMap);

		//강의선택 팝업  카운트
		int listCount = productevent.getCbLecMstListFreeOrderCount(searchMap);

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
		output.addAttribute("EVENT_ID", EVENT_ID);
		output.addAttribute("INS", INS);
		output.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/lecture/event/subejct_pop";
	}

	/**
	 * @Method Name : lec_insert
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 - 강의 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/lec_insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String lec_insert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String[] v_leccode = request.getParameterValues("v_leccode");
		String event_id = params.get("EVENT_ID");

		params.put("EVENT_ID", String.valueOf(event_id));

		if (v_leccode != null && !"".equals(v_leccode)) {
			for(int i=0; i<v_leccode.length; i++){
				params.put("LECCODE", v_leccode[i]);
				productevent.lec_insert(params);
			}
		}
//		model.addAttribute("event_id", event_id);
//		model.addAttribute("INS", "Y");
		return "redirect:/productevent/pop_subject_add.pop?EVENT_ID="+event_id+"&INS=Y";
	}

	/**
	 * @Method Name : lec_delete
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 - 강의 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/lec_delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String[] i_leccode = request.getParameterValues("i_leccode");
		String event_id = params.get("EVENT_ID");

		params.put("EVENT_ID", String.valueOf(event_id));

		if (i_leccode != null && !"".equals(i_leccode)) {
			for(int i=0; i<i_leccode.length; i++){
				params.put("LECCODE", i_leccode[i]);
				productevent.lec_delete(params);				// 강의삭제
			}
		}

		return "redirect:/productevent/list.do";
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

		params.put("EVENT_ID", CommonUtil.isNull(request.getParameter("EVENT_ID"), ""));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("EVENT_ID", CommonUtil.isNull(request.getParameter("EVENT_ID"), ""));

	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

		params.put("EVENT_NM", CommonUtil.isNull(request.getParameter("EVENT_NM"), ""));
		params.put("EVENT_TYPE", CommonUtil.isNull(request.getParameter("EVENT_TYPE"), ""));
		params.put("EVENT_AMOUNT", CommonUtil.isNull(request.getParameter("EVENT_AMOUNT"), ""));
		params.put("ST_DATE", CommonUtil.isNull(request.getParameter("ST_DATE"), ""));
		params.put("ED_DATE", CommonUtil.isNull(request.getParameter("ED_DATE"), ""));
		params.put("EVENT_YN", CommonUtil.isNull(request.getParameter("EVENT_YN"), ""));
	}

}