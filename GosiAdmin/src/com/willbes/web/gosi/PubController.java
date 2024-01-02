package com.willbes.web.gosi;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.board.service.BoardService;
import com.willbes.web.gosi.service.PubService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/pub")
@Controller
public class PubController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="boardServiceImpl")
    private BoardService boardService;

    @Autowired
    private PubService pubservice;

    @Resource(name="fileUtil")
    private FileUtil fileUtil;

    @Inject
    private FileSystemResource fsResource;
	
	/**
	 * @Method Name : pub_board_list
	 * @작성일 : 2015. 05.
	 * @Method 설명 : 주요공고/지표 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/pub_board_list.do")
	public String pub_board_list(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

        HashMap<String, String> boardInfoMap = boardService.getBoardKind(params);
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_NAME"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_TYPE"), ""));
        params.put("ATTACH_FILE_YN",CommonUtil.isNull(boardInfoMap.get("ATTACH_FILE_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(boardInfoMap.get("OPEN_YN"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(boardInfoMap.get("REPLY_YN"), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(boardInfoMap.get("ONOFF_DIV"), ""));

        /* 페이징 */  
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));			
		/* 페이징 */

		//동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
        List<HashMap<String, String>> rankList = boardService.getRankCode(params);
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), rankList.get(0).get("CODE")));
		
        List<HashMap<String, String>> list = pubservice.getPubList(params);
		int listCount = pubservice.getPubListCount(params);
		
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
		
        model.addAttribute("rankList", rankList);
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		model.addAttribute("params", params);
		
		return "/gosi/pub/board_list";
	}
	
	/**
	 * @Method Name : pub_add
	 * @작성일 : 2015. 05.
	 * @Method 설명 : 주요공고/지표 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/pub_board_add.do")
	public String pub_add(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params", params);
		return "/gosi/pub/board_add";
	}
	
	@RequestMapping(value="/pub_board_insert.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String pub_insert(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		//에디터를 선택했을 때 첨부파일 저장

		String rootPath = fsResource.getPath();
		String subPath = "pub/";

		pubservice.Pub_Insert(params);

		int maxPubNo = pubservice.getMaxPubNo(params);
		params.put("PUB_NO", String.valueOf(maxPubNo));
		List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){
                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));

                if("null".equals(file_path)) continue;
                params.put("FILE_PATH", file_path);
                params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));

                pubservice.AttachFile_insert(params);
            }
        }

		model.addAttribute("params", params);
		return "forward:/pub/pub_board_list.do";
	}
	
	@RequestMapping(value="/pub_board_modify.do")
	public String pub_modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
        //수정 체크박스를 위한 코드리스트
        List<HashMap<String, String>> list = boardService.getRankCode(params);
        model.addAttribute("gbList",list);
        //해당글의 코드데이터
        List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
        model.addAttribute("boardCodeList",boardCodeList);

        // 등록된 글 data
        HashMap<String, String> detailView = pubservice.getPubOne(params);
        List<HashMap<String, String>> boardAttachFile = pubservice.getPubFiles(params);

        model.addAttribute("detailView",detailView);
        model.addAttribute("boardAttachFile",boardAttachFile);

		model.addAttribute("params", params);
		
		return "/gosi/pub/board_modify";
	}
	
	@RequestMapping(value="/pub_board_update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String pub_update(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		//에디터를 선택했을 때 첨부파일 저장
		String rootPath = fsResource.getPath();
		String subPath = "pub/";
		
        List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){
                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));

                if("null".equals(file_path)) continue;
                params.put("FILE_PATH", file_path);
                params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));

                pubservice.AttachFile_insert(params);
            }
        }
		
		pubservice.Pub_Update(params);										

		model.addAttribute("params", params);
		return "forward:/pub/pub_board_list.do";
	}
	
	@RequestMapping(value="/pub_board_delete.do")
	@ResponseBody
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String pub_delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		String Result = "";
		
		pubservice.Pub_delete(params);
		Result = "Y";

		List<HashMap<String, String>> pub_files = pubservice.getPubFiles(params);
		
        //원본글의 파일존재 여부에따라
		String rootPath = fsResource.getPath();
        if(pub_files != null && pub_files.isEmpty() == false) {
            for(int j =0; j < pub_files.size(); j++){
        		HashMap<String, String> pfile = pub_files.get(0);
                String filePath = String.valueOf(rootPath+pfile.get("FILE_PATH"));
                File file = new File(filePath);
                file.delete();
            }
        }
		
		return Result;
	}
	
	@RequestMapping(value="/AttachFile_delete.do")
	@ResponseBody
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String AttachFile_delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		List<HashMap<String, String>> pub_files = pubservice.getPubFiles(params);
		HashMap<String, String> pfile = pub_files.get(0);
		
        //원본글의 파일존재 여부에따라
		String rootPath = fsResource.getPath();
        if(pfile != null){
            String filePath = String.valueOf(rootPath+pfile.get("FILE_PATH"));
            //파일 삭제
            File file = new File(filePath);
            file.delete();
        }

		String Result = "";
		pubservice.AttachFile_delete(params);
		Result = "Y";

		return Result;
	}

    /**
     * @Method Name : setParam
     * @작성일 : 2013. 10.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMapdj
     * @throws Exception
     */
    public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));

        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",request.getParameter("BOARD_MNG_NAME"));
        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), "NOTICE_008"));
        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));

        params.put("SEARCHONOFFDIV",CommonUtil.isNull(request.getParameter("SEARCHONOFFDIV"), ""));
		params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), "001"));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		
        model.addAttribute("SEARCHONOFFDIV", params.get("SEARCHONOFFDIV"));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

}