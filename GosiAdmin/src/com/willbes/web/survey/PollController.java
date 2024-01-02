package com.willbes.web.survey;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.survey.service.PollService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/poll")
@Controller
public class PollController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private PollService pollservice;


	/**
	 * @Method Name : list
	 * @작성일 : 2014. 01.
	 * @Method 설명 : 쪽지 목록
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
		
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"),"GOSI_MST"));
		
		List<HashMap<String, String>> list = pollservice.pollList(params);
		int listCount = pollservice.pollListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/poll/list";
	}

	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params", params);

		return "/survey/poll/write";
	}

	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> view = pollservice.pollView(params);
		model.addAttribute("view", view);
		model.addAttribute("params", params);
		return "/survey/poll/modify";
	}

	@RequestMapping(value="/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String insert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		pollservice.pollInsert(params);

		return "forward:/poll/list.do";
	}

	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String update(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		pollservice.pollUpdate(params);

		return "forward:/poll/list.do";
	}

	@RequestMapping(value="/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		pollservice.pollDelete(params);

		return "forward:/poll/list.do";
	}

	// 설문 문항 리스트
	@RequestMapping(value="/item_list.pop")
	public String item_list(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		//리스트
		model.addAttribute("list", pollservice.pollItemList(params));
		model.addAttribute("params", params);

		return "/survey/poll/ItemList_pop";
	}

	// 설문 문항 리스트
	@RequestMapping(value="/user_list.pop")
	public String user_list(ModelMap model, HttpServletRequest req) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, model);

		//리스트
		model.addAttribute("list", pollservice.pollResultList(params));
		model.addAttribute("params", params);

		return "/survey/poll/UserList_pop";
	}

	@RequestMapping(value="/ItemInsert.json")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@ResponseBody
	public String ItemInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		
		pollservice.pollItemInsert(params);

		return "Y";
	}

	@RequestMapping(value="/ItemUpdate.json")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@ResponseBody
	public String ItemUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		
        String[] v_POLL_ITEM_ID = request.getParameterValues("v_POLL_ITEM_ID");

        if(v_POLL_ITEM_ID != null){
            for(int i=0; i<v_POLL_ITEM_ID.length; i++){
                params.put("POLL_ITEM_ID", v_POLL_ITEM_ID[i]);
                params.put("SEQ", request.getParameter("SEQ_"+v_POLL_ITEM_ID[i]));
                params.put("VIW", request.getParameter("VIW_"+v_POLL_ITEM_ID[i]));
                pollservice.pollItemUpdate(params);
            }
        }

		return "Y";
	}

	@RequestMapping(value="/ItemDelete.json")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@ResponseBody
	public String ItemDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		
		pollservice.pollItemDelete(params);

		return "Y";
	}

	/**
	 * @Method Name : setParam
	 * @작성일 : 2014. 01.
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
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

		params.put("POLL_ID", CommonUtil.isNull(request.getParameter("POLL_ID"), ""));
		params.put("POLL_ITEM_ID", CommonUtil.isNull(request.getParameter("POLL_ITEM_ID"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("VIW", CommonUtil.isNull(request.getParameter("VIW"), ""));
		params.put("START_DT", CommonUtil.isNull(request.getParameter("START_DT"), ""));
		params.put("END_DT", CommonUtil.isNull(request.getParameter("END_DT"), ""));
		params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
		params.put("IS_SHOW", CommonUtil.isNull(request.getParameter("IS_SHOW"), ""));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"), ""));

		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
	}
}
