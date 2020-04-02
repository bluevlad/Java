package web.gosi;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import web.gosi.service.PubService;
import web.util.CommonUtil;
import web.util.file.FileUtil;
import web.util.paging.Paging;

@RequestMapping(value="/pub")
@Controller
public class PubController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private PubService pubservice;

    @Resource(name="fileUtil")
    private FileUtil fileUtil;

    @Inject
    private FileSystemResource fsResource;

	/**
	* @Method Name : pub_default
	* @작성일 : 2015. 05.
	* @Method 설명 : 주요공고/지표 관리
	* @param model
	* @param request
	* @return String
	* @throws Exception
	*/
	@RequestMapping(value="/pub_default.do")
	public String pub_default(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("list", pubservice.getPubCategory(params));
		model.addAttribute("params", params);
		return "/gosi/pub/default";
	}
	
	@RequestMapping(value="/pub_tree.frm")
	public String pub_tree(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("list", pubservice.getPubCategory(params));
		model.addAttribute("params", params);
		return "/gosi/pub/tree";
	}

	@RequestMapping(value="/pub_detail.frm")
	public String pub_detail(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("IDX", CommonUtil.isNull(request.getParameter("IDX"), ""));

		model.addAttribute("detail", pubservice.getPubCategoryOne(params));
		model.addAttribute("params", params);
		return "/gosi/pub/detail";
	}
	
	@RequestMapping(value="/pub_write.frm")
	public String pub_write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("IDX", CommonUtil.isNull(request.getParameter("IDX"), ""));
		params.put("P_IDX", CommonUtil.isNull(request.getParameter("P_IDX"), ""));

		model.addAttribute("detail", pubservice.getPubCategoryOne(params));
		model.addAttribute("params", params);
		return "/gosi/pub/write";
	}

	/**
	 * @Method Name : pub_insertPubCategory
	 * @작성일 : 2015. 05.
	 * @Method 설명 : 주요공고/지표 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/pub_insertPubCategory.json")
	@ResponseBody
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String pub_insertPubCategory(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		int isInsert = pubservice.insertPubCategory(params);
		String result = isInsert + "";
		
		return result;
	}

	/**
	 * @Method Name : pub_save
	 * @작성일 : 2015. 05.
	 * @Method 설명 : 주요공고/지표 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/pub_updatePubCategory.json")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String pub_updatePubCategory(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		pubservice.updatePubCategory(params);
		
		return "forward:/pub/pub_detail.frm?IDX="+params.get("IDX");
	}
	
	/**
	 * @Method Name : Gosi_modify
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 국가고시 샘플아이디 수정 페이지
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/pub_deletePubCategory.json")
	public String pub_deletePubCategory(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		int isInsert = pubservice.deletePubCategory(params);
		String result = isInsert + "";
		
		return result;
	}
    @RequestMapping(value="/getPubCate")
    @ResponseBody
    public List<HashMap<String, String>>  getPubCate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

        return pubservice.getPubCategorySel(params);
    }
	
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
		
		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));			
		/* 페이징 */
		
        List<HashMap<String, String>> list = pubservice.getPubList(params);
		int listCount = pubservice.getPubListCount(params);
		
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
		
		model.addAttribute("pub_category", pubservice.getPubCategorySel(params));
		model.addAttribute("pub_gubun", pubservice.getPubGubun(params));
		model.addAttribute("pub_list", list);
		model.addAttribute("pub_list_count", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
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

		model.addAttribute("pub_category", pubservice.getPubCategorySel(params));
		model.addAttribute("pub_gubun", pubservice.getPubGubun(params));
		
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
		
		model.addAttribute("pubDetail", pubservice.getPubOne(params));
		model.addAttribute("pub_gubun", pubservice.getPubGubun(params));
		model.addAttribute("pub_files", pubservice.getPubFiles(params));

		params.put("LVL", "1");
		model.addAttribute("main_pub", pubservice.getPubCategorySel(params));
		params.put("LVL", "2");
		model.addAttribute("sub_pub", pubservice.getPubCategorySel(params));

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

        params.put("IDX", CommonUtil.isNull(request.getParameter("IDX"), ""));
        params.put("NM", CommonUtil.isNull(request.getParameter("NM"), ""));
        params.put("P_IDX", CommonUtil.isNull(request.getParameter("P_IDX"), ""));
        params.put("LVL", CommonUtil.isNull(request.getParameter("LVL"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));

        params.put("PUB_NO", CommonUtil.isNull(request.getParameter("PUB_NO"), ""));
        params.put("PUB_YEAR", CommonUtil.isNull(request.getParameter("PUB_YEAR"), ""));
		params.put("PUB_CAT", CommonUtil.isNull(request.getParameter("PUB_CAT"), ""));
		params.put("MAIN_PID", CommonUtil.isNull(request.getParameter("MAIN_PID"), ""));
		params.put("SUB_PID", CommonUtil.isNull(request.getParameter("SUB_PID"), ""));
		params.put("PUB_TITLE", CommonUtil.isNull(request.getParameter("PUB_TITLE"), ""));
		params.put("PUB_CONTENTS", CommonUtil.isNull(request.getParameter("PUB_CONTENTS"), ""));
		params.put("FILE_NO", CommonUtil.isNull(request.getParameter("FILE_NO"), ""));
		params.put("FILE_NAME", CommonUtil.isNull(request.getParameter("FILE_NAME"), ""));
		params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"), ""));

		params.put("SEARCH_YEAR", CommonUtil.isNull(request.getParameter("SEARCH_YEAR"), ""));
		params.put("SEARCH_GUBUN", CommonUtil.isNull(request.getParameter("SEARCH_GUBUN"), ""));
		params.put("S_YEAR", CommonUtil.isNull(request.getParameter("S_YEAR"), ""));
		params.put("S_MAIN_PID", CommonUtil.isNull(request.getParameter("S_MAIN_PID"), ""));
		params.put("S_SUB_PID", CommonUtil.isNull(request.getParameter("S_SUB_PID"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

}