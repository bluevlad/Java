package com.willbes.web.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.board.service.BoardService;
import com.willbes.web.popup.service.PopupNewService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/popupNew")
@Controller
public class PopupNewController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;
	@Autowired
	private PopupNewService popupNewservice;

    @Resource(name="boardServiceImpl")
	private BoardService boardservice;

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 팝업 목록
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
		params.put("POPUP_YN", "Y");
		List<HashMap<String, String>> categoryList = boardservice.getRankCode(params);
		List<HashMap<String, Object>> list = popupNewservice.popupNewList(params);
		int listCount = popupNewservice.popupNewListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();



		for (int i = 0; i < list.size(); i++) {
			List cateList = Arrays.asList(list.get(i).get("CATEGORY_CODE").toString().split(","));
			if(categoryList.size()==cateList.size()){
				list.get(i).put("ISALL", "Y");
			}else{
				list.get(i).put("ISALL", "N");
			}
			List<String> cateNameList = new ArrayList<String>();
			for (int j = 0; j < categoryList.size(); j++) {
				for (int j2 = 0; j2 < cateList.size(); j2++) {
					if(categoryList.get(j).get("CODE").toString().equals(cateList.get(j2).toString())){
						cateNameList.add(categoryList.get(j).get("NAME").toString());
					}
				}
			}
			list.get(i).put("CODENAME", cateNameList);
		}
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/popupNew/list";
	}



	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("POPUP_YN", "Y");
		List<HashMap<String, String>> categoryList = boardservice.getRankCode(params);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("params", params);
		return "/popupNew/write";
	}


	@RequestMapping(value="/save.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String save(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		//popup_upload
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		popupFileProcess(params, multipartRequest);
		popupNewservice.popupNewInsert(params);
		return "forward:/popupNew/list.do";
	}


	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("POPUP_YN", "Y");
		List<HashMap<String, String>> categoryList = boardservice.getRankCode(params);
		HashMap<String, String> view = popupNewservice.popupNewView(params);
		List popupCateList = Arrays.asList(view.get("CATEGORY_CODE").toString().split(","));

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("view", view);
		model.addAttribute("popupCateList", popupCateList);
		model.addAttribute("params", params);
		return "/popupNew/modify";
	}


	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String update(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		popupFileProcess(params, multipartRequest);

		String rootPath = fsResource.getPath();
		if("Y".equals(params.get("POPUP_IMG_DEL")) || !"".equals(params.get("POPUP_IMG")))
			fileUtil.deleteFile(rootPath + params.get("POPUP_IMG_DEL_NM"));
		if("Y".equals(params.get("THUMBNAIL_DEL")) || !"".equals(params.get("THUMBNAIL")))
			fileUtil.deleteFile(rootPath + params.get("THUMBNAIL_DEL_NM"));

		popupNewservice.popupNewUpdate(params);
		return "forward:/popupNew/list.do";
	}


	@RequestMapping(value="/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String rootPath = fsResource.getPath();

		if(!"".equals(params.get("POPUP_IMG_DEL_NM")))
			fileUtil.deleteFile(rootPath + params.get("POPUP_IMG_DEL_NM"));
		if(!"".equals(params.get("THUMBNAIL_DEL_NM")))
			fileUtil.deleteFile(rootPath + params.get("THUMBNAIL_DEL_NM"));
		popupNewservice.popupNewDelete(params);
		return "forward:/popupNew/list.do";
	}

	@RequestMapping(value="/openY.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String openYN(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		popupNewservice.popupNewCheckOPENY(params);
		return "forward:/popupNew/list.do";
	}

	@RequestMapping(value="/openN.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String openN(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		popupNewservice.popupNewCheckOPENN(params);
		return "forward:/popupNew/list.do";
	}

	@RequestMapping(value="/allDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String allDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String rootPath = fsResource.getPath();

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		String NO_ARR = "";
		for (int i = 0; i < DEL_ARR.length; i++) {
			String[] temp_arr = DEL_ARR[i].split(",");
			NO_ARR += "'"+temp_arr[0]+"'" + ",";

			if(temp_arr[1] != null && temp_arr[1].toString() != ""){
				fileUtil.deleteFile(rootPath + temp_arr[1].toString());
			}
			if(temp_arr[2] != null && temp_arr[2].toString() != ""){
				fileUtil.deleteFile(rootPath + temp_arr[2].toString());
			}
		}
		NO_ARR = NO_ARR.substring(0, NO_ARR.length()-1 );
		params.put("NO_ARR", NO_ARR);
		popupNewservice.popupNewCheckDelete(params);
		return "forward:/popupNew/list.do";
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
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

		if(request.getParameter("ONOFF_DIV") == null){
			String searchMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"));
			if(searchMenuType.indexOf("OM") > -1) {
				params.put("ONOFF_DIV", "O");
			} else {
				params.put("ONOFF_DIV", "F");
			}
		}else{
			params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
		}
		params.put("NO", CommonUtil.isNull(request.getParameter("NO"), ""));
		params.put("CATEGORY_CODE", CommonUtil.isNull(request.getParameter("codeStr"), ""));
		params.put("START_DATE", CommonUtil.isNull(request.getParameter("START_DATE"), ""));
		params.put("START_TIME", CommonUtil.isNull(request.getParameter("START_TIME"), ""));
		params.put("END_DATE", CommonUtil.isNull(request.getParameter("END_DATE"), ""));
		params.put("END_TIME", CommonUtil.isNull(request.getParameter("END_TIME"), ""));
		params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("POPUP_IMG", CommonUtil.isNull(request.getParameter("POPUP_IMG"), ""));
		params.put("THUMBNAIL", CommonUtil.isNull(request.getParameter("THUMBNAIL"), ""));
		params.put("LINK_ADDR", CommonUtil.isNull(request.getParameter("LINK_ADDR"), ""));
		params.put("LINK_TARGET", CommonUtil.isNull(request.getParameter("LINK_TARGET"), ""));
		params.put("POPUP_IMG_NM", CommonUtil.isNull(request.getParameter("POPUP_IMG_NM"), ""));
		params.put("THUMBNAIL_NM", CommonUtil.isNull(request.getParameter("THUMBNAIL_NM"), ""));

		params.put("POPUP_IMG_DEL", CommonUtil.isNull(request.getParameter("POPUP_IMG_DEL"), ""));
		params.put("POPUP_IMG_DEL_NM", CommonUtil.isNull(request.getParameter("POPUP_IMG_DEL_NM"), ""));
		params.put("THUMBNAIL_DEL", CommonUtil.isNull(request.getParameter("THUMBNAIL_DEL"), ""));
		params.put("THUMBNAIL_DEL_NM", CommonUtil.isNull(request.getParameter("THUMBNAIL_DEL_NM"), ""));

		params.put("OPEN_YN_ARR", CommonUtil.isNull(request.getParameter("OPEN_YN_ARR"), ""));
		
		params.put("POPUP_TYPE", CommonUtil.isNull(request.getParameter("POPUP_TYPE"), ""));
		params.put("POPUP_WIDTH", CommonUtil.isNull(request.getParameter("POPUP_WIDTH"), ""));
		params.put("POPUP_HEIGHT", CommonUtil.isNull(request.getParameter("POPUP_HEIGHT"), ""));
		params.put("POPUP_LEFT", CommonUtil.isNull(request.getParameter("POPUP_LEFT"), ""));
		params.put("POPUP_TOP", CommonUtil.isNull(request.getParameter("POPUP_TOP"), ""));
		
	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

	/**
	 * @Method Name : popupFileProcess
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파일처리 프로세스
	 * @param params
	 * @param request
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap<String, String> popupFileProcess(HashMap<String, String> params, MultipartHttpServletRequest multipartRequest) throws Exception {
		MultipartFile POPUP_IMG = multipartRequest.getFile("POPUP_IMG");
		MultipartFile THUMBNAIL = multipartRequest.getFile("THUMBNAIL");

		String rootPath = fsResource.getPath();
		String subPath = "popup/";

		if(POPUP_IMG != null && POPUP_IMG.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(POPUP_IMG, rootPath, subPath);
			params.put("POPUP_IMG", fileMap1.get("fileFullPath").toString());
			params.put("POPUP_IMG_NM", POPUP_IMG.getOriginalFilename().toString());
			Thread.sleep(100);
		}
		if(THUMBNAIL != null && THUMBNAIL.isEmpty() == false) {
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(THUMBNAIL, rootPath, subPath);
			params.put("THUMBNAIL", fileMap2.get("fileFullPath").toString());
			params.put("THUMBNAIL_NM", THUMBNAIL.getOriginalFilename().toString());
			Thread.sleep(100);
		}

		return params;
	}
}
