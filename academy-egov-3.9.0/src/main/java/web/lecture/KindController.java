package web.lecture;

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

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.lecture.service.KindService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/kind")
@Controller
public class KindController {

	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	private KindService kindservice;

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 목록
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

		params.put("Seq_Update", "N");
		List<HashMap<String, String>> list = kindservice.kindList(params);
		int listCount = kindservice.kindListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/kind/list";
	}

	@RequestMapping(value="/Seq_Update.do")
	public String Seq_Update(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(0));
		params.put("endNo", String.valueOf(1000));
		/* 페이징 */
		params.put("Seq_Update", "Y");
		List<HashMap<String, String>> list = kindservice.kindList(params);
		int listCount = kindservice.kindListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/kind/seq_update";
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 직종 순번 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/seqUpdate.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String seqUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String[] NUM = request.getParameterValues("Num");
		String[] SEQARR = request.getParameterValues("SEQ");
		String[] CODE = request.getParameterValues("CODE_NO");
		String[] CODE_NM = request.getParameterValues("CODE_NM");
		if(SEQARR != null){
			for(int i=0; i<SEQARR.length; i++){
				params.put("NUM", NUM[i]);
				params.put("SEQ", SEQARR[i]);
				params.put("CODE", CODE[i]);
				params.put("CODE_NM", CODE_NM[i]);

				kindservice.SeqUpdate(params);
			}
		}
		return "forward:/kind/Seq_Update.do";
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 목록에서 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/listDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String listDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				params.put("CODE", DEL_ARR[i]);
				kindservice.kindDelete(params);
			}
		}
		return "forward:/kind/list.do";
	}

	/**
	 * @Method Name : write
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 등록폼
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
		return "/lecture/kind/write";
	}

	/**
	 * @Method Name : codeCheck
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 코드중복체크
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/codeCheck.do")
	@ResponseBody
	public String codeCheck(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		int listCount = kindservice.kindCheck(params);
		String returnStr = "Y";
		if(listCount > 0)
			returnStr = "N";
		return returnStr;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/save.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String save(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		kindservice.kindInsert(params);
		return "redirect:/kind/list.do";
	}

	/**
	 * @Method Name : modify
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 수정폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> list = kindservice.kindView(params);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		return "/lecture/kind/modify";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 수정 프로세스
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
		kindservice.kindUpdate(params);
		return "forward:/kind/list.do";
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 카테고리 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		kindservice.kindDelete(params);
		return "forward:/kind/list.do";
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
	@SuppressWarnings("unchecked")
	public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHCODE", CommonUtil.isNull(request.getParameter("CODE"), ""));

		params.put("CODE", CommonUtil.isNull(request.getParameter("CODE"), ""));
		params.put("NAME", CommonUtil.isNull(request.getParameter("NAME"), ""));
		params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
		params.put("USE_ON", CommonUtil.isNull(request.getParameter("USE_ON"), ""));
		params.put("USE_OFF", CommonUtil.isNull(request.getParameter("USE_OFF"), ""));
		params.put("P_CODE", CommonUtil.isNull(request.getParameter("P_CODE"), ""));
		params.put("ORDR", CommonUtil.isNull(request.getParameter("ORDR"), ""));

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

		params.put("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "C"));
		model.addAttribute("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "C"));
	}

}
