package web.coop;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.property.EgovPropertyService;
import web.adminManagement.service.AdminManagementCodeService;
import web.coop.service.CoopBoardManagementService;
import web.util.CommonUtil;
import web.util.file.FileUtil;
import web.util.paging.Paging;

/**
 * @FileName   : CoopBoardManagementController.java
 * @Project    : willbesWebAdmin
 * @Date       : 2016. 02. 01.
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 제휴사 게시판 관리
 */
@RequestMapping(value="/CoopBoardMng")
@Controller
public class CoopBoardManagementController {

    @Inject
    private FileSystemResource fsResource;    //DI

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private CoopBoardManagementService CoopBoardManagementService;

    private AdminManagementCodeService AdminManagementCodeService;

    /**
     * @Method Name  : boardList
     * @Date         : 2016. 02. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	제휴사 게시판 관리 -  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/boardList.do")
    public String boardList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        // 검색조건
        String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
        String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), "50"));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("SEARCHTYPE", SEARCHTYPE);
        //params.put("COOP_AREA", CommonUtil.isNull(request.getParameter("COOP_AREA"), ""));
        //params.put("COOP_CATE", CommonUtil.isNull(request.getParameter("COOP_CATE"), ""));
        params.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));

        params.put("currentPage", currentPage);
        params.put("pageRow", pageRow);
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

		params.put("SEARCHKEYWORD", "COOP_AREA");
		List<HashMap<String, Object>> codeAreaList = CoopBoardManagementService.getCoopCodeList(params);
		params.put("SEARCHKEYWORD", "COOP_HSPT");
		List<HashMap<String, Object>> codeHsptList = CoopBoardManagementService.getCoopCodeList(params);

        //리스트
        List<HashMap<String, Object>> list = CoopBoardManagementService.getCoopboardList(params);
        // 총 건수
        int listCount = CoopBoardManagementService.getCoopboardListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("params", params);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("codeAreaList", codeAreaList);
        model.addAttribute("codeHsptList", codeHsptList);

        return "/coop/board/boardList";
    }

    /**
     * @Method Name  : boardWrite
     * @Date         : 2016. 02. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴게시판 관리 - 등록
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardWrite.do")
    public String boardWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

		params.put("SEARCHKEYWORD", "COOP_AREA");
		List<HashMap<String, Object>> codeAreaList = CoopBoardManagementService.getCoopCodeList(params);
		params.put("SEARCHKEYWORD", "COOP_HSPT");
		List<HashMap<String, Object>> codeHsptList = CoopBoardManagementService.getCoopCodeList(params);

        model.addAttribute("params", params);
        model.addAttribute("codeAreaList", codeAreaList);
        model.addAttribute("codeHsptList", codeHsptList);

        return "/coop/board/boardWrite";
    }

    /**
     * @Method Name  : insertCoopboard
     * @Date         : 2016. 02. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴게시판 관리 - 등록
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardModify.do")
    public String boardModify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

		params.put("SEARCHKEYWORD", "COOP_AREA");
		List<HashMap<String, Object>> codeAreaList = CoopBoardManagementService.getCoopCodeList(params);
		params.put("SEARCHKEYWORD", "COOP_HSPT");
		List<HashMap<String, Object>> codeHsptList = CoopBoardManagementService.getCoopCodeList(params);

        HashMap<String, Object> detail = CoopBoardManagementService.CoopboardView(params);

        model.addAttribute("params", params);
        model.addAttribute("detail", detail);
        model.addAttribute("codeAreaList", codeAreaList);
        model.addAttribute("codeHsptList", codeHsptList);

        return "/coop/board/boardModify";
    }
    
        /**
     * 게시판 수정 첨부 파일 삭제 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/CoopboardDeleteFile.do")
    @ResponseBody
    public HashMap<String, String> boardDeleteFile(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("FILE_PATH", request.getParameter("FILE_PATH"));
        params.put("COOP_BOARD_SEQ", request.getParameter("COOP_BOARD_SEQ"));
        params.put("FILETYPE", request.getParameter("FILETYPE"));

        //삭제한 파일ID로 게시글 파일 ID 널처리
        //boardService.updateBoardFile(params);
        CoopBoardManagementService.CoopboardDeleteFile(params);

        HashMap<String, String> result = new HashMap<String, String>();
        try {
            result.put("successmessage", "삭제 완료");
        } catch (Exception e) {
            result.put("errormessage", "삭제 실패");
            return result;
        }
        return result;
    }


    /**
     * @Method Name  : commonCodeInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 코드관리 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/insertCoopboard.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String insertCoopboard(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        
        params.put("TOP_MENU_ID",	CommonUtil.isNull(multiRequest.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(multiRequest.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(multiRequest.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(multiRequest.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(multiRequest.getParameter("MENU_NM"), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(multiRequest.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(multiRequest.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(multiRequest.getParameter("SEARCHTEXT"), ""));
        params.put("COOP_BOARD_SEQ", CommonUtil.isNull(multiRequest.getParameter("COOP_BOARD_SEQ"), ""));
        params.put("OPEN_YN", CommonUtil.isNull(multiRequest.getParameter("OPEN_YN"), ""));
        params.put("CREATENAME", CommonUtil.isNull(multiRequest.getParameter("CREATENAME"), ""));
        params.put("SUBJECT", CommonUtil.isNull(multiRequest.getParameter("SUBJECT"), ""));
        params.put("CONTENT", CommonUtil.isNull(multiRequest.getParameter("CONTENT"), ""));
        params.put("FILE_PATH", CommonUtil.isNull(multiRequest.getParameter("FILE_PATH"), ""));
        params.put("FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("FILE_NAME"), ""));
        params.put("REAL_FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("REAL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_PATH", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_PATH"), ""));
        params.put("THUMBNAIL_FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_REAL_NAME", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_REAL_NAME"), ""));
        params.put("NOTICE_TOP_YN", CommonUtil.isNull(multiRequest.getParameter("NOTICE_TOP_YN"), ""));
        params.put("RECOMMEND", CommonUtil.isNull(multiRequest.getParameter("RECOMMEND"), ""));
        params.put("COOP_TYPE", CommonUtil.isNull(multiRequest.getParameter("COOP_TYPE"), ""));
        params.put("COOP_AREA", CommonUtil.isNull(multiRequest.getParameter("COOP_AREA"), ""));
        params.put("COOP_CATE", CommonUtil.isNull(multiRequest.getParameter("COOP_CATE"), ""));
        params.put("SEARCH_COOP_AREA", CommonUtil.isNull(multiRequest.getParameter("SEARCH_COOP_AREA"), ""));
        params.put("SEARCH_COOP_CATE", CommonUtil.isNull(multiRequest.getParameter("SEARCH_COOP_CATE"), ""));
        
        String rootPath = fsResource.getPath();
        String subPath = "board/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("THUMBNAIL_FILE_NAME");
        MultipartFile uploadFile = multiRequest.getFile("FILE_NAME");

        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
        	HashMap<String, Object> fileMap = null;
            fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            params.put("THUMBNAIL_FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("THUMBNAIL_FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("THUMBNAIL_FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }

        if(uploadFile != null && uploadFile.isEmpty() == false) {
        	HashMap<String, Object> fileMap = null;
            fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }

        //게시물
        CoopBoardManagementService.insertCoopboard(params);
        model.addAttribute("params",params);

        return "forward:/CoopBoardMng/boardList.do";
    }

    /**
     * @Method Name  : updateCoopboard
     * @Date         : 2016. 02. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴게시판 관리 - 수정
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateCoopboard.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String updateCoopboardProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        
        params.put("TOP_MENU_ID",	CommonUtil.isNull(multiRequest.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(multiRequest.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(multiRequest.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(multiRequest.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(multiRequest.getParameter("MENU_NM"), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(multiRequest.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(multiRequest.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(multiRequest.getParameter("SEARCHTEXT"), ""));
        params.put("COOP_BOARD_SEQ", CommonUtil.isNull(multiRequest.getParameter("COOP_BOARD_SEQ"), ""));
        params.put("OPEN_YN", CommonUtil.isNull(multiRequest.getParameter("OPEN_YN"), ""));
        params.put("CREATENAME", CommonUtil.isNull(multiRequest.getParameter("CREATENAME"), ""));
        params.put("SUBJECT", CommonUtil.isNull(multiRequest.getParameter("SUBJECT"), ""));
        params.put("CONTENT", CommonUtil.isNull(multiRequest.getParameter("CONTENT"), ""));
        params.put("FILE_PATH", CommonUtil.isNull(multiRequest.getParameter("FILE_PATH"), ""));
        params.put("FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("FILE_NAME"), ""));
        params.put("REAL_FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("REAL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_PATH", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_PATH"), ""));
        params.put("THUMBNAIL_FILE_NAME", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_REAL_NAME", CommonUtil.isNull(multiRequest.getParameter("THUMBNAIL_FILE_REAL_NAME"), ""));
        params.put("NOTICE_TOP_YN", CommonUtil.isNull(multiRequest.getParameter("NOTICE_TOP_YN"), ""));
        params.put("RECOMMEND", CommonUtil.isNull(multiRequest.getParameter("RECOMMEND"), ""));
        params.put("COOP_TYPE", CommonUtil.isNull(multiRequest.getParameter("COOP_TYPE"), ""));
        params.put("COOP_AREA", CommonUtil.isNull(multiRequest.getParameter("COOP_AREA"), ""));
        params.put("COOP_CATE", CommonUtil.isNull(multiRequest.getParameter("COOP_CATE"), ""));
        params.put("SEARCH_COOP_AREA", CommonUtil.isNull(multiRequest.getParameter("SEARCH_COOP_AREA"), ""));
        params.put("SEARCH_COOP_CATE", CommonUtil.isNull(multiRequest.getParameter("SEARCH_COOP_CATE"), ""));
		
        String rootPath = fsResource.getPath();
        String subPath = "board/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("THUMBNAIL_FILE_NAME");
        MultipartFile uploadFile = multiRequest.getFile("FILE_NAME");

        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
        	HashMap<String, Object> fileMap = null;
            fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            params.put("THUMBNAIL_FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("THUMBNAIL_FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("THUMBNAIL_FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }

        if(uploadFile != null && uploadFile.isEmpty() == false) {
        	HashMap<String, Object> fileMap = null;
            fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }
        CoopBoardManagementService.updateCoopboard(params);

        model.addAttribute("params",params);

        return "forward:/CoopBoardMng/boardList.do";
    }

    /**
     * @Method Name  : commonCodeDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 코드관리 상세페이지  삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deleteCoopboard.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String deleteCoopboard(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("DETAIL_BOARD_MNG_SEQ",request.getParameter("DETAIL_BOARD_MNG_SEQ"));

        // 삭제
        CoopBoardManagementService.deleteCoopboard(params);

        model.addAttribute("params",params);

        return "redirect:/CoopBoardMng/boardList.do";
    }

    /**
     * @Method Name  : updateCoopRank
     * @Date         : 2016. 02. 01.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴게시판 관리 - 수정
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateCoopRank.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String updateCoopRank(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        
        String[] v_coop_seq = request.getParameterValues("v_coop_seq");
        String[] v_coop_rank = request.getParameterValues("v_coop_rank");

		if(v_coop_seq != null){
			for(int i=0; i<v_coop_seq.length; i++){
				params.put("COOP_BOARD_SEQ", v_coop_seq[i]);
				params.put("COOP_RANK", request.getParameter("v_coop_rank_"+v_coop_seq[i]));
		        CoopBoardManagementService.updateCoopRank(params);
			}
		}

        model.addAttribute("params",params);

        return "forward:/CoopBoardMng/boardList.do";
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("AdmUserInfo");
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        
		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));

        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("COOP_BOARD_SEQ", CommonUtil.isNull(request.getParameter("COOP_BOARD_SEQ"), ""));
        params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
        params.put("CREATENAME", CommonUtil.isNull(request.getParameter("CREATENAME"), ""));
        params.put("SUBJECT", CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("CONTENT", CommonUtil.isNull(request.getParameter("CONTENT"), ""));
        params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"), ""));
        params.put("FILE_NAME", CommonUtil.isNull(request.getParameter("FILE_NAME"), ""));
        params.put("REAL_FILE_NAME", CommonUtil.isNull(request.getParameter("REAL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_PATH", CommonUtil.isNull(request.getParameter("THUMBNAIL_FILE_PATH"), ""));
        params.put("THUMBNAIL_FILE_NAME", CommonUtil.isNull(request.getParameter("THUMBNAIL_FILE_NAME"), ""));
        params.put("THUMBNAIL_FILE_REAL_NAME", CommonUtil.isNull(request.getParameter("THUMBNAIL_FILE_REAL_NAME"), ""));
        params.put("NOTICE_TOP_YN", CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
        params.put("RECOMMEND", CommonUtil.isNull(request.getParameter("RECOMMEND"), ""));
        params.put("COOP_TYPE", CommonUtil.isNull(request.getParameter("COOP_TYPE"), ""));
        params.put("COOP_AREA", CommonUtil.isNull(request.getParameter("COOP_AREA"), ""));
        params.put("COOP_CATE", CommonUtil.isNull(request.getParameter("COOP_CATE"), ""));
        params.put("SEARCH_COOP_AREA", CommonUtil.isNull(request.getParameter("SEARCH_COOP_AREA"), ""));
        params.put("SEARCH_COOP_CATE", CommonUtil.isNull(request.getParameter("SEARCH_COOP_CATE"), ""));
    }
}
