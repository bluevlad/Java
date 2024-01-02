package com.willbes.web.survey;

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

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.adminManagement.service.AdminManagementCodeService;
import com.willbes.web.survey.service.SurveyService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/survey")
@Controller
public class SurveyController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private SurveyService  surveyservice;
    
    @Autowired
    private AdminManagementCodeService codeService;

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 12.
	 * @Method 설명 : 설문 문항 목록
	 */
	@RequestMapping(value="/bank/list.do")
	public String bankList(ModelMap model, HttpServletRequest request) throws Exception {
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

		List<HashMap<String, String>> list = surveyservice.bankList(params);
		int listCount = surveyservice.bankListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/bank/list";
	}

	@RequestMapping(value="/bank/write.do")
	public String bankWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, Object> cParams = new  HashMap<String, Object>();
		cParams.put("SYS_CD", "QUETYPE");
		model.addAttribute("queType", codeService.getBaConfigCodeList(cParams));
		model.addAttribute("params", params);

		return "/survey/bank/write";
	}

	@RequestMapping(value="/bank/modify.do")
	public String bankModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, Object> cParams = new  HashMap<String, Object>();
		cParams.put("SYS_CD", "QUETYPE");
		model.addAttribute("queType", codeService.getBaConfigCodeList(cParams));

		HashMap<String, String> view = surveyservice.bankView(params);
		model.addAttribute("view", view);
		model.addAttribute("params", params);
		return "/survey/bank/modify";
	}

	@RequestMapping(value="/bank/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String bankInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.bankInsert(params);

		return "forward://survey/bank/list.do";
	}

	@RequestMapping(value="/bank/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String bankUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.bankUpdate(params);

		return "forward:/survey/bank/list.do";
	}

	@RequestMapping(value="/bank/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String bankDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.bankDelete(params);

		return "forward:/survey/bank/list.do";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 12.
	 * @Method 설명 : 설문 문항 목록
	 */
	@RequestMapping(value="/set/list.do")
	public String setList(ModelMap model, HttpServletRequest request) throws Exception {
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

		List<HashMap<String, String>> list = surveyservice.surveySetList(params);
		int listCount = surveyservice.surveySetListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/set/list";
	}

	@RequestMapping(value="/set/write.do")
	public String setWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params", params);

		return "/survey/set/write";
	}

	@RequestMapping(value="/set/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String setInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.surveySetInsert(params);

		return "forward://survey/set/list.do";
	}

	@RequestMapping(value="/set/modify.do")
	public String setModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> view = surveyservice.surveySetView(params);

		model.addAttribute("item", surveyservice.surveySetItemList(params));
		
		model.addAttribute("view", view);
		model.addAttribute("params", params);

		return "/survey/set/modify";
	}

	@RequestMapping(value="/set/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String setUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.surveySetUpdate(params);

        String[] v_queids = request.getParameterValues("v_queids");

        if(v_queids != null){
            for(int i=0; i<v_queids.length; i++){
                params.put("QUEID", v_queids[i]);
                params.put("QSEQ", request.getParameter("qseq_"+v_queids[i]));
        		surveyservice.surveySetItemUpdate(params);
            }
        }
		
		return "forward:/survey/set/modify.do";
	}

	@RequestMapping(value="/set/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String setDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.surveySetDelete(params);

		return "forward:/survey/set/list.do";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 12.
	 * @Method 설명 : 설문 문항 목록
	 */
	@RequestMapping(value="/set/itemList.pop")
	public String itemList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

        String upt = request.getParameter("upt");
		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = surveyservice.bankList(params);
		int listCount = surveyservice.bankListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("upt", upt);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/set/itemList_pop";
	}

	@RequestMapping(value="/set/itemInsert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String itemInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
        String[] v_queids = request.getParameterValues("v_queids");

        if(v_queids != null){
            for(int i=0; i<v_queids.length; i++){
                params.put("QUEID", v_queids[i]);
        		surveyservice.surveySetItemInsert(params);
            }
        }

        return "redirect:/survey/set/itemList.pop?upt=Y&SETID="+params.get("SETID");
	}

	@RequestMapping(value="/set/itemDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String itemDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
   		surveyservice.surveySetItemDelete(params);
   		
		return "forward:/survey/set/modify.do";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2017. 04.
	 * @Method 설명 : 설문 목록
	 */
	@RequestMapping(value="/survey/list.do")
	public String surveyList(ModelMap model, HttpServletRequest request) throws Exception {
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

		List<HashMap<String, String>> list = surveyservice.surveyList(params);
		int listCount = surveyservice.surveyListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/survey/list";
	}

	@RequestMapping(value="/survey/view.do")
	public String surveyView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> view = surveyservice.surveyView(params);
		List<HashMap<String, String>> sList = surveyservice.surveySetItemList(params);
		List<HashMap<String, String>> rList = surveyservice.surveyRstList(params);
		List<HashMap<String, String>> aList = surveyservice.answerList(params);
		
		model.addAttribute("view", view);
		model.addAttribute("sList", sList);
		model.addAttribute("rList", rList);
		model.addAttribute("aList", aList);
		model.addAttribute("params", params);

		return "/survey/survey/view";
	}

	@RequestMapping(value="/survey/write.do")
	public String surveyWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> view = surveyservice.surveyView(params);

		model.addAttribute("view", view);
		model.addAttribute("params", params);

		return "/survey/survey/write";
	}

	@RequestMapping(value="/survey/modify.do")
	public String surveyModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		HashMap<String, String> view = surveyservice.surveyView(params);

		model.addAttribute("view", view);
		model.addAttribute("params", params);

		return "/survey/survey/modify";
	}

	@RequestMapping(value="/survey/insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String surveyInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		setParam(params, request, model);
		surveyservice.surveyInsert(params);

		return "forward:/survey/survey/list.do";
	}

	/**
	 * @Method Name : setListPop
	 * @작성일 : 2014. 12.
	 * @Method 설명 : 설문 문항 목록
	 */
	@RequestMapping(value="/survey/setList.pop")
	public String setListPop(ModelMap model, HttpServletRequest request) throws Exception {
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

		List<HashMap<String, String>> list = surveyservice.surveySetList(params);
		int listCount = surveyservice.surveySetListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/survey/survey/setList_pop";
	}

	@RequestMapping(value="/survey/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String surveyUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		surveyservice.surveyUpdate(params);

		return "forward:/survey/survey/list.do";
	}

	@RequestMapping(value="/survey/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String surveyDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		surveyservice.surveyDelete(params);

		return "forward:/survey/survey/list.do";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 12.
	 * @Method 설명 : 설문 문항 목록
	 */
	@RequestMapping(value="/survey/userList.pop")
	public String userList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("QSEQ", CommonUtil.isNull(request.getParameter("QSEQ"), ""));
		List<HashMap<String, String>> aList = surveyservice.answerList(params);
		
		model.addAttribute("aList", aList);
		model.addAttribute("params", params);

		return "/survey/survey/userList_pop";
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
		params.put("UPT_ID", loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

		params.put("QUEOWNER", CommonUtil.isNull(request.getParameter("QUEOWNER"), ""));
		params.put("QUEID", CommonUtil.isNull(request.getParameter("QUEID"), ""));
		params.put("SETID", CommonUtil.isNull(request.getParameter("SETID"), ""));
		params.put("SETTITLE", CommonUtil.isNull(request.getParameter("SETTITLE"), ""));
		params.put("SETID", CommonUtil.isNull(request.getParameter("SETID"), ""));
		params.put("SETDESC", CommonUtil.isNull(request.getParameter("SETDESC"), ""));
		params.put("SURVEYID", CommonUtil.isNull(request.getParameter("SURVEYID"), ""));
		params.put("SURVEYTITLE", CommonUtil.isNull(request.getParameter("SURVEYTITLE"), ""));
		params.put("SURVEYDESC", CommonUtil.isNull(request.getParameter("SURVEYDESC"), ""));
		params.put("SURVEY_SDAT", CommonUtil.isNull(request.getParameter("SURVEY_SDAT"), ""));
		params.put("SURVEY_EDAT", CommonUtil.isNull(request.getParameter("SURVEY_EDAT"), ""));

		params.put("QUETITLE", CommonUtil.isNull(request.getParameter("QUETITLE"), ""));
		params.put("QUEDESC", CommonUtil.isNull(request.getParameter("QUEDESC"), ""));
		params.put("QUEVIW1", CommonUtil.isNull(request.getParameter("QUEVIW1"), ""));
		params.put("QUEVIW2", CommonUtil.isNull(request.getParameter("QUEVIW2"), ""));
		params.put("QUEVIW3", CommonUtil.isNull(request.getParameter("QUEVIW3"), ""));
		params.put("QUEVIW4", CommonUtil.isNull(request.getParameter("QUEVIW4"), ""));
		params.put("QUEVIW5", CommonUtil.isNull(request.getParameter("QUEVIW5"), ""));
		params.put("QUEVIW6", CommonUtil.isNull(request.getParameter("QUEVIW6"), ""));
		params.put("QUEVIW7", CommonUtil.isNull(request.getParameter("QUEVIW7"), ""));
		params.put("QUEVIW8", CommonUtil.isNull(request.getParameter("QUEVIW8"), ""));
		params.put("QUEVIW9", CommonUtil.isNull(request.getParameter("QUEVIW9"), ""));
		params.put("QUEVIW10", CommonUtil.isNull(request.getParameter("QUEVIW10"), ""));
		params.put("HINT1", CommonUtil.isNull(request.getParameter("HINT1"), ""));
		params.put("HINT2", CommonUtil.isNull(request.getParameter("HINT2"), ""));
		params.put("HINT3", CommonUtil.isNull(request.getParameter("HINT3"), ""));
		params.put("HINT4", CommonUtil.isNull(request.getParameter("HINT4"), ""));
		params.put("HINT5", CommonUtil.isNull(request.getParameter("HINT5"), ""));
		params.put("HINT6", CommonUtil.isNull(request.getParameter("HINT6"), ""));
		params.put("HINT7", CommonUtil.isNull(request.getParameter("HINT7"), ""));
		params.put("HINT8", CommonUtil.isNull(request.getParameter("HINT8"), ""));
		params.put("HINT9", CommonUtil.isNull(request.getParameter("HINT9"), ""));
		params.put("HINT10", CommonUtil.isNull(request.getParameter("HINT10"), ""));
		params.put("QUECOUNT", CommonUtil.isNull(request.getParameter("QUECOUNT"), ""));
		params.put("QUETYPE", CommonUtil.isNull(request.getParameter("QUETYPE"), ""));
		params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
		params.put("S_MENU", CommonUtil.isNull(request.getParameter("S_MENU"), ""));

	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
	}
}
