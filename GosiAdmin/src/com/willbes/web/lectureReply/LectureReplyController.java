package com.willbes.web.lectureReply;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.lectureReply.service.LectureReplyService;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @FileName   : LectureReplyController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 11
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 수강후기 게시판
 */
@RequestMapping(value="/lectureReply")
@Controller
public class LectureReplyController {


    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private LectureReplyService lectureReplyService;



	/**
	 * @Method Name  : lectureReplyList
	 * @Date         : 2013. 11
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	강좌  리스트
	 * @param model
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/lectureReplyList.do")
	public String commonCodeList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		List<HashMap<String, Object>> list = null;

		// 검색조건
		String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
		String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("SEARCHTYPE", SEARCHTYPE);
		searchMap.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));

		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		//리스트
		list = lectureReplyService.lectureReplyList(searchMap);


		// 총 건수 -
		int listCount = lectureReplyService.lectureReplyListCount(searchMap);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		//메뉴 param
		searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));
		//메뉴 param


		model.addAttribute("list", list);
		model.addAttribute("searchMap", searchMap);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "lectureReply/lectureReplyList";
	}

	/**
	 * @Method Name  : lectureReplyDetail
	 * @Date         : 2013. 11
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 후기 상세
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/lectureReplyDetail.do")
	public String commonCodeDetail(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();
		int currentPageReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPageReply"), "1"));
		int pageRowReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRowReply"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPageReply - 1) * pageRowReply;
		int endNo = startNo + pageRowReply;

		params.put("currentPageReply", currentPageReply);
		params.put("pageRowReply", pageRowReply);
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("currentPage",request.getParameter("currentPage"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("DETAIL_LECCODE",request.getParameter("DETAIL_LECCODE"));



		HashMap<String, Object> detail  =	lectureReplyService.lectureReplyDetail(params);
		List<HashMap<String, Object>>  replyList  =	lectureReplyService.lectureReplyDetailList(params);
		// 총 건수 -
		int replyListCount = lectureReplyService.lectureReplyDetailListCount(params);
		//페이징 처리
		String pagingHtml = new Paging(currentPageReply, replyListCount, pageRowReply).getPagingHtml().toString();

		model.addAttribute("params",params);
		model.addAttribute("detail",detail);
		model.addAttribute("replyList",replyList);

		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", replyListCount);
		model.addAttribute("currentPageReply", currentPageReply);
		model.addAttribute("pageRowReply", pageRowReply);
		model.addAttribute("totalPage", (int) Math.ceil((double) replyListCount / pageRowReply));

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
		return "/lectureReply/lectureReplyDetail";
	}



	/**
	 * @Method Name  : replyDelete
	 * @Date         : 2013. 11.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 후기 삭제
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/replyDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String replyDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new  HashMap<String, Object>();

		params.put("DELETE_LECCODE",request.getParameter("DELETE_LECCODE"));
		params.put("DELETE_SEQ",request.getParameter("DELETE_SEQ"));
		//삭제
		lectureReplyService.replyDelete(params);


		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param
 		return "forward:/lectureReply/lectureReplyDetail.do";
	}

}
