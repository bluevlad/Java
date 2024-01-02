package com.willbes.web.board;

import java.io.File;
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

import com.willbes.cmm.util.DateUtil;
import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.adminManagement.service.AdminManagementCodeService;
import com.willbes.web.board.service.BoardService;
import com.willbes.web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/board")
@Controller
public class BoardController {

    @Inject
    private FileSystemResource fsResource;    //DI

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private TeacherService teacherservice;

    @Resource(name="boardServiceImpl")
    private BoardService boardService;
    
    @Autowired
    private AdminManagementCodeService codeService;
    /**
     * @Method Name : boardList
     * @작성일 : 2015. 6
     * @Method 설명 : 게시판 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/boardList.pop")
    public String boardList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        HashMap<String, String> boardInfoMap = boardService.getBoardKind(params);
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_NAME"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_TYPE"), ""));
        params.put("ATTACH_FILE_YN",CommonUtil.isNull(boardInfoMap.get("ATTACH_FILE_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(boardInfoMap.get("OPEN_YN"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(boardInfoMap.get("REPLY_YN"), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(boardInfoMap.get("ONOFF_DIV"), ""));

        //동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
        List<HashMap<String, String>> rankList = boardService.getRankCode(params);
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), rankList.get(0).get("CODE")));
        //params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), "SEARCHSUBJECT"));

        //해당 게시판 글 리스트
        List<HashMap<String, String>> list = boardService.boardList(params);
        int listCount = boardService.listCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
        
        if("NOTICE_008".equals(params.get("BOARD_MNG_SEQ"))){
        	HashMap<String, Object> codeParams = new  HashMap<String, Object>();
    		codeParams.put("SYS_CD", "PUB_TYPE");
    		model.addAttribute("pub_gubun", codeService.getBaConfigCodeList(codeParams));
    		codeParams.put("SYS_CD", "PUB_AREA");
    		model.addAttribute("pub_area", codeService.getBaConfigCodeList(codeParams));
        }
        model.addAttribute("rankList", rankList);
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
            List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
            model.addAttribute("SEARCHPRFIDs", teacher_list);
        }

        return "board/boardList";
    }

    @RequestMapping(value="/Is_RE_Update.pop")
    public String Is_RE_Update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

//        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));

        String[] UPDATE_IS_ARR = request.getParameterValues("UPDATE_IS_ARR");
        String[] UPDATE_RE_ARR = request.getParameterValues("UPDATE_RE_ARR");
        String[] UPDATE_OPEN_ARR = request.getParameterValues("UPDATE_OPEN_ARR");
        String[] UPDATE_MAIN_ARR = request.getParameterValues("UPDATE_MAIN_ARR");

        if(UPDATE_IS_ARR != null){
            for(int i=0; i<UPDATE_IS_ARR.length; i++){
                String a = UPDATE_IS_ARR[i];
                String IS_CHK = a.substring(0, 1);
                String IS_BOARD_SEQ = a.substring(2, a.length());
                params.put("IS_CHK", IS_CHK);
                params.put("IS_BOARD_SEQ", IS_BOARD_SEQ);
                boardService.updateISSUE(params);
            }
        }
        if(UPDATE_RE_ARR != null){
            for(int i=0; i<UPDATE_RE_ARR.length; i++){
                String a = UPDATE_RE_ARR[i];
                String RE_CHK = a.substring(0, 1);
                String RE_BOARD_SEQ = a.substring(2, a.length());
                params.put("RE_CHK", RE_CHK);
                params.put("RE_BOARD_SEQ", RE_BOARD_SEQ);
                boardService.updateRECOMMEND(params);
            }
        }
        if(UPDATE_OPEN_ARR != null){
            for(int i=0; i<UPDATE_OPEN_ARR.length; i++){
                String a = UPDATE_OPEN_ARR[i];
                String OPEN_CHK = a.substring(0, 1);
                String IS_BOARD_SEQ = a.substring(2, a.length());
                params.put("OPEN_CHK", OPEN_CHK);
                params.put("IS_BOARD_SEQ", IS_BOARD_SEQ);
                boardService.updateOPEN_YN(params);
            }
        }
        if(UPDATE_MAIN_ARR != null){
            for(int i=0; i<UPDATE_MAIN_ARR.length; i++){
                String a = UPDATE_MAIN_ARR[i];
                String MAIN_CHK = a.substring(0, 1);
                String IS_BOARD_SEQ = a.substring(2, a.length());
                params.put("MAIN_CHK", MAIN_CHK);
                params.put("IS_BOARD_SEQ", IS_BOARD_SEQ);
                boardService.updateMAIN_YN(params);
            }
        }

        String ret_param = "?SEARCHONOFFDIV="+params.get("SEARCHONOFFDIV");
        ret_param = ret_param +"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
        ret_param = ret_param +"&SEARCHCATEGORY="+params.get("SEARCHCATEGORY");
        ret_param = ret_param +"&SEARCHKIND="+params.get("SEARCHKIND");
        ret_param = ret_param +"&SEARCHTEXT="+params.get("SEARCHTEXT");
        return "redirect:/board/boardList.pop"+ret_param;
    }

    /**
     * @Method Name : boardFAQList
     * @작성일 : 2015. 6
     * @Method 설명 : FAQ 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQList.pop")
    public String boardFAQList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        //파라미터의 게시판 정보
        HashMap<String, String> boardInfoMap = boardService.getBoardKind(params);
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_NAME"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(boardInfoMap.get("BOARD_MNG_TYPE"), ""));
        params.put("ATTACH_FILE_YN",CommonUtil.isNull(boardInfoMap.get("ATTACH_FILE_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(boardInfoMap.get("OPEN_YN"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(boardInfoMap.get("REPLY_YN"), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(boardInfoMap.get("ONOFF_DIV"), ""));

        //동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
        List<HashMap<String, String>> rankList = boardService.getRankCode(params);
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), rankList.get(0).get("CODE")));
        //params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), "SEARCHSUBJECT"));

        //해당 게시판 글 리스트
        List<HashMap<String, String>> list = boardService.boardFAQList(params);
        int listCount = boardService.boardFAQListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("rankList", rankList);
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
            List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
            model.addAttribute("SEARCHPRFIDs", teacher_list);
        }

        return "board/boardFAQList";
    }

    /**
     * @Method Name : boardWrite
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardWrite.pop")
    public String boardWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        model.addAttribute("gbList",boardService.getRankCode(params));

        if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
            params.put("FLAG", "O");
            List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
            model.addAttribute("SEARCHPRFIDs", teacher_list);
        }
        if("NOTICE_008".equals(params.get("BOARD_MNG_SEQ"))){
        	HashMap<String, Object> codeParams = new  HashMap<String, Object>();
    		codeParams.put("SYS_CD", "PUB_TYPE");
    		model.addAttribute("pub_gubun", codeService.getBaConfigCodeList(codeParams));
    		codeParams.put("SYS_CD", "PUB_AREA");
    		model.addAttribute("pub_area", codeService.getBaConfigCodeList(codeParams));
        }
        model.addAttribute("params",params);

        return "/board/boardWrite";
    }

    /**
     * @Method Name : boardInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardInsertProcess.pop")
    public String boardInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CREATENAME",request.getParameter("CREATENAME"));
        params.put("REG_ID",request.getParameter("REG_ID"));

        params.put("ISSUE",CommonUtil.isNull(request.getParameter("ISSUE"), ""));
        params.put("RECOMMEND",CommonUtil.isNull(request.getParameter("RECOMMEND"), ""));

        params.put("SUBJECT",request.getParameter("SUBJECT"));
        params.put("CONTENT",request.getParameter("CONTENT"));
        params.put("BOARD_REPLY", CommonUtil.isNull(request.getParameter("BOARD_REPLY"), ""));

        params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"), ""));
        params.put("PROF_ID", CommonUtil.isNull(request.getParameter("PROF_ID"), ""));
        params.put("MOCKCODE", CommonUtil.isNull(request.getParameter("MOCKCODE"), ""));

        String rootPath = fsResource.getPath();
//        String subPath = "board/";
        String subPath = "board/"+DateUtil.dateFormat("YYYYMM")+"/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("uploadFileThumbNail");
        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
            int THUMB_X = 0;
            if(null != request.getParameter("THUMB_X") && !"".equals(request.getParameter("THUMB_X"))) {
                THUMB_X = Integer.parseInt(request.getParameter("THUMB_X"));
            }
            int THUMB_Y = 0;
            if(null != request.getParameter("THUMB_Y") && !"".equals(request.getParameter("THUMB_Y"))) {
                THUMB_Y = Integer.parseInt(request.getParameter("THUMB_Y"));
            }
            HashMap<String, Object> fileMap = null;
            if(THUMB_X > 0) {
                fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath, THUMB_X , THUMB_Y, "");
            } else {
                fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            }
            params.put("THUMBNAIL_FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("THUMBNAIL_FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("THUMBNAIL_FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }else{
            params.put("THUMBNAIL_FILE_PATH","" );
            params.put("THUMBNAIL_FILE_NAME","" );
            params.put("THUMBNAIL_FILE_REAL_NAME","");
        }

        @SuppressWarnings("unused")
        Object BOARD_SEQ = boardService.boardInsertProcess(params);

        List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){
                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));

                if("null".equals(file_path)) continue;
                params.put("FILE_PATH", file_path);
                params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
                params.put("REAL_FILE_NAME", uploadFile.get(j).getOriginalFilename().toString() );
                params.put("BOARD_SEQ", params.get("BOARD_SEQ"));

                boardService.insertBoardFile(params);
            }
        }

        if(null == params.get("SEARCHONOFFDIV") || "".equals(params.get("SEARCHONOFFDIV").toString())
                || !"T".equals(params.get("SEARCHONOFFDIV").toString())) {
            //게시판에서 선택한 직급 구분 codeStr 배열화
            String [] code_arr = request.getParameter("codeStr").split("/");
            HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
            for (int i = 0; i < code_arr.length; i++) {
                boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
                boardTestClassMap.put("BOARD_SEQ", params.get("BOARD_SEQ"));
                boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
                boardService.insertBoardCatInfo(boardTestClassMap);
            }
        }

        model.addAttribute("params", params);
        return "redirect:/board/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : boardFAQWrite
     * @작성일 : 2015. 6.
     * @Method 설명 : FAQ 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQWrite.pop")
    public String boardFAQWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        model.addAttribute("gbList",boardService.getRankCode(params));
        // 게시판타입가져오기
        //HashMap<String, String> boardKind = boardService.getBoardKind(params);
        if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
            List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
            model.addAttribute("SEARCHPRFIDs", teacher_list);
        }
        model.addAttribute("params",params);

        return "/board/boardFAQWrite";
    }

    /**
     * @Method Name : boardFAQInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQInsertProcess.pop")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardFAQInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CREATENAME",request.getParameter("CREATENAME"));
        params.put("REG_ID",request.getParameter("REG_ID"));

        params.put("SUBJECT",CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("ANSWER",CommonUtil.isNull(request.getParameter("ANSWER"), ""));

        params.put("PROF_ID",CommonUtil.isNull(request.getParameter("PROF_ID"), ""));

        String rootPath = fsResource.getPath();
//      String subPath = "board/";
        String subPath = "board/"+DateUtil.dateFormat("YYYYMM")+"/";

        MultipartFile uploadFile = multiRequest.getFile("uploadFile");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile.getOriginalFilename().toString() );
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
        }

        @SuppressWarnings("unused")
        Object BOARD_SEQ = boardService.boardFAQInsertProcess(params);

        if(null == params.get("SEARCHONOFFDIV") || "".equals(params.get("SEARCHONOFFDIV").toString())
                || !"T".equals(params.get("SEARCHONOFFDIV").toString())) {
            //게시판에서 선택한 직급 구분 codeStr 배열화
            String [] code_arr = request.getParameter("codeStr").split("/");
            HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
            for (int i = 0; i < code_arr.length; i++) {
                boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
                boardTestClassMap.put("BOARD_SEQ", params.get("BOARD_SEQ"));
                boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
                boardService.insertBoardCatInfo(boardTestClassMap);
            }
        }

        model.addAttribute("params", params);
        return "redirect:/board/boardFAQList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : boardView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 보기 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardView.pop")
    public String boardView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        //조회수 증가
        //boardService.updateBoardHits(params);
        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);
        List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
        List<HashMap<String, String>> boardAttachFile_Img = boardService.boardAttachFile_Img(params);

        if(!String.valueOf(detailView.get("PARENT_BOARD_SEQ")).equals("0") || String.valueOf(detailView.get("PARENT_BOARD_SEQ")) != null){
            params.put("PARENT_BOARD_SEQ", String.valueOf(detailView.get("PARENT_BOARD_SEQ")));
            HashMap<String, Object> detailView_Origin = boardService.getBoardDetail_Origin(params);
            model.addAttribute("detailView_Origin",detailView_Origin);
        }

        //해당글이 리플을 가지고있는지 여부
        int replyCount = boardService.getIsReply(params);
        params.put("ISBOARDREPLY", String.valueOf(replyCount));

        model.addAttribute("detailView",detailView);
        model.addAttribute("boardAttachFile",boardAttachFile);
        model.addAttribute("boardAttachFile_Img",boardAttachFile_Img);
        model.addAttribute("params",params);

        return "/board/boardView";
    }
    /**
     * @Method Name : boardFAQView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 보기 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQView.pop")
    public String boardFAQView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        //조회수 증가
        //boardService.updateBoardHits(params);
        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);
        detailView.put("ISIMAGE","N");
        if(detailView.get("FILE_NAME") != null && detailView.get("FILE_NAME").toString() !=""  ){
            String isImage = detailView.get("FILE_NAME").toString().split("[.]")[1];
            if(isImage.equals("jpg")||isImage.equals("JPG")||isImage.equals("jpeg")||isImage.equals("JPEG")||isImage.equals("png")||isImage.equals("PNG")||isImage.equals("bmp")||isImage.equals("BMP")||isImage.equals("gif")||isImage.equals("GIF")){
                detailView.put("ISIMAGE","Y");
            }
        }
        //해당글이 리플을 가지고있는지 여부
        //int replyCount = boardService.getIsReply(params);
        //params.put("ISBOARDREPLY", String.valueOf(replyCount));
        model.addAttribute("detailView",detailView);
        model.addAttribute("params",params);

        return "/board/boardFAQView";
    }

    /**
     * @Method Name : boardModify
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardModify.pop")
    public String boardModifiy(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        if(params.get("BOARDVIEWPARENT_SEQ").toString().equals("0")){ //원글일때
            //수정 체크박스를 위한 코드리스트
            List<HashMap<String, String>> list = boardService.getRankCode(params);
            model.addAttribute("gbList",list);
            //해당글의 코드데이터
            List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
            model.addAttribute("boardCodeList",boardCodeList);
            // 등록된 글 data
            HashMap<String, Object> detailView = boardService.getBoardDetail(params);
            List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
            params.put("board_gubun", "ORIGIN");
            model.addAttribute("detailView",detailView);
            model.addAttribute("boardAttachFile",boardAttachFile);

            if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
                List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
                model.addAttribute("SEARCHPRFIDs", teacher_list);
            }
            if("NOTICE_008".equals(params.get("BOARD_MNG_SEQ"))){
            	HashMap<String, Object> codeParams = new  HashMap<String, Object>();
        		codeParams.put("SYS_CD", "PUB_TYPE");
        		model.addAttribute("pub_gubun", codeService.getBaConfigCodeList(codeParams));
        		codeParams.put("SYS_CD", "PUB_AREA");
        		model.addAttribute("pub_area", codeService.getBaConfigCodeList(codeParams));
            }
        }else{ //답변일때
            // 등록된 글 data
            HashMap<String, Object> detailView = boardService.getBoardDetail(params);
            List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
            params.put("board_gubun", "REPLY");
            model.addAttribute("detailView",detailView);
            model.addAttribute("boardAttachFile",boardAttachFile);
        }
        model.addAttribute("params",params);

        return "/board/boardModify";
    }

    /**
     * @Method Name : boardFAQModify
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQModify.pop")
    public String boardFAQModify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        //수정 체크박스를 위한 코드리스트
        List<HashMap<String, String>> list = boardService.getRankCode(params);
        //해당글의 코드데이터
        List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);
        params.put("board_gubun", "ORIGIN");
        model.addAttribute("detailView",detailView);
        model.addAttribute("gbList",list);
        model.addAttribute("boardCodeList",boardCodeList);
        model.addAttribute("params",params);

        return "/board/boardFAQModify";
    }
    /**
     * @Method Name : boardReplyModify
     * @작성일 : 2015. 6.
     * @Method 설명 : 답변글 수정 화면
     * @param request
     * @return
     */
    /*@RequestMapping(value="/boardReplyModify.do")
    public String boardReplyModify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARDENVID",CommonUtil.isNull(request.getParameter("BOARDENVID"), "1"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));

        HashMap<String, Object> detailView = boardService.getBoardDetail(params);
        model.addAttribute("detailView",detailView);

        model.addAttribute("params",params);

        return "/board/boardReplyModify";
    }*/

    /**
     * 게시판 수정 첨부 파일 삭제 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardDeleteFile.do")
    @ResponseBody
    public HashMap<String, String> boardDeleteFile(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("FILE_PATH", request.getParameter("FILE_PATH"));
        params.put("BOARD_SEQ", request.getParameter("BOARD_SEQ"));
        params.put("FILETYPE", request.getParameter("FILETYPE"));

        //삭제한 파일ID로 게시글 파일 ID 널처리
        //boardService.updateBoardFile(params);
        boardService.Modify_deleteBoardFileData(params);
        String rootPath = fsResource.getPath();
        @SuppressWarnings("unused")
        String filePath = rootPath+params.get("FILE_PATH");
        File file = new File(String.valueOf(rootPath+params.get("FILE_PATH")));
        file.delete();

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
     * @Method Name : boardInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardUpdateProcess.pop")
    public String boardUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        //게시글이 원글인지 답글인지 구분자
        params.put("board_gubun",CommonUtil.isNull(request.getParameter("board_gubun"), ""));

        params.put("CREATENAME",request.getParameter("CREATENAME"));
        params.put("REG_ID",request.getParameter("REG_ID"));

        params.put("ISSUE",CommonUtil.isNull(request.getParameter("ISSUE"), ""));
        params.put("RECOMMEND",CommonUtil.isNull(request.getParameter("RECOMMEND"), ""));

        params.put("SUBJECT",request.getParameter("SUBJECT"));
        params.put("CONTENT",request.getParameter("CONTENT"));

        params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"), ""));
        params.put("PROF_ID", CommonUtil.isNull(request.getParameter("PROF_ID"), ""));
        params.put("MOCKCODE", CommonUtil.isNull(request.getParameter("MOCKCODE"), ""));

        String rootPath = fsResource.getPath();
//        String subPath = "board/";
        String subPath = "board/"+DateUtil.dateFormat("YYYYMM")+"/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("uploadFileThumbNail");
        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
            int THUMB_X = 0;
            if(null != request.getParameter("THUMB_X") && !"".equals(request.getParameter("THUMB_X"))) {
                THUMB_X = Integer.parseInt(request.getParameter("THUMB_X"));
            }
            int THUMB_Y = 0;
            if(null != request.getParameter("THUMB_Y") && !"".equals(request.getParameter("THUMB_Y"))) {
                THUMB_Y = Integer.parseInt(request.getParameter("THUMB_Y"));
            }
            HashMap<String, Object> fileMap = null;
            if(THUMB_X > 0) {
                fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath, THUMB_X , THUMB_Y, "");
            } else {
                fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            }
            params.put("THUMBNAIL_FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("THUMBNAIL_FILE_NAME", String.valueOf(fileMap.get("fileName"))); //원본 파일명
            params.put("THUMBNAIL_FILE_REAL_NAME", String.valueOf(fileMap.get("fileNewName"))); //실제 파일명
        }else{
            params.put("THUMBNAIL_FILE_PATH","" );
            params.put("THUMBNAIL_FILE_NAME","" );
            params.put("THUMBNAIL_FILE_REAL_NAME","");
        }

        @SuppressWarnings("unused")
        Object bContentId = boardService.boardUpdateProcess(params);

        List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){
                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));

                if("null".equals(file_path)) continue;
                params.put("FILE_PATH", file_path);
                params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
                params.put("REAL_FILE_NAME", uploadFile.get(j).getOriginalFilename().toString() );
                params.put("BOARD_SEQ", params.get("BOARDVIEW_SEQ"));

                if(params.get("board_gubun").toString().equals("ORIGIN")){
                    boardService.insertBoardFile(params);
                }else{
                    boardService.insertBoardReplyFile(params);
                }
            }
        }

        if(null == params.get("SEARCHONOFFDIV") || "".equals(params.get("SEARCHONOFFDIV").toString())
                || !"T".equals(params.get("SEARCHONOFFDIV").toString())) {
            //게시글이 원본글일때만
            if(params.get("board_gubun").toString().equals("ORIGIN")){
                //기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
                boardService.deleteBoardCatInfo(params);

                //게시판에서 선택한 직급 구분 codeStr 배열화
                String [] code_arr = request.getParameter("codeStr").split("/");
                HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
                for (int i = 0; i < code_arr.length; i++) {
                    boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
                    boardTestClassMap.put("BOARD_SEQ", params.get("BOARDVIEW_SEQ"));
                    boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
                    boardService.insertBoardCatInfo(boardTestClassMap);
                }

                //원본글이 코드값이 변경되면 하위 답변에 대한 코드값도 변경되야해서
                //답변글 있는지 체크
                HashMap<String, String> getCodeParam  = new HashMap<String, String>();
                getCodeParam.put("BOARD_MNG_SEQ", String.valueOf(params.get("BOARD_MNG_SEQ")));
                getCodeParam.put("BOARDVIEW_SEQ", String.valueOf(params.get("BOARDVIEW_SEQ")));
                List<HashMap<String, String>> replayData = boardService.getReplyData(getCodeParam);
                if(replayData.size()  > 0 ){
                    for (int k = 0; k < replayData.size(); k++) {
                        HashMap<String, Object> updateReplyParam = new HashMap<String, Object>();
                        updateReplyParam.put("BOARD_MNG_SEQ", String.valueOf(params.get("BOARD_MNG_SEQ")));
                        updateReplyParam.put("BOARDVIEW_SEQ", replayData.get(k).get("BOARD_SEQ"));
                        //기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
                        boardService.deleteBoardCatInfo(updateReplyParam);

                        HashMap<String, Object> boardTestClassMap2 = new HashMap<String, Object>();
                        for (int i = 0; i < code_arr.length; i++) {
                            boardTestClassMap2.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
                            boardTestClassMap2.put("BOARD_SEQ", replayData.get(k).get("BOARD_SEQ"));
                            boardTestClassMap2.put("CATEGORY_CODE", code_arr[i]);
                            boardService.insertBoardCatInfo(boardTestClassMap2);
                        }
                    }
                }
            }
        }
        model.addAttribute("params", params);

        return "redirect:/board/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ")+"&SEARCHCATEGORY="+params.get("SEARCHCATEGORY");
    }

    /**
     * @Method Name : boardFAQUpdateProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQUpdateProcess.pop")
    public String boardFAQUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CREATENAME",CommonUtil.isNull(request.getParameter("CREATENAME") , ""));
        params.put("REG_ID",CommonUtil.isNull(request.getParameter("REG_ID") , ""));

        params.put("SUBJECT",CommonUtil.isNull(request.getParameter("SUBJECT") , ""));
        params.put("ANSWER",CommonUtil.isNull(request.getParameter("ANSWER") , ""));

        String rootPath = fsResource.getPath();
//        String subPath = "board/";
        String subPath = "board/"+DateUtil.dateFormat("YYYYMM")+"/";

        MultipartFile uploadFile = multiRequest.getFile("uploadFile");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile.getOriginalFilename().toString() );
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
        }

        @SuppressWarnings("unused")
        Object bContentId = boardService.boardFAQUpdateProcess(params);

        //기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
        boardService.deleteBoardCatInfo(params);

        //게시판에서 선택한 직급 구분 codeStr 배열화
        String [] code_arr = request.getParameter("codeStr").split("/");
        HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
        for (int i = 0; i < code_arr.length; i++) {
            boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
            boardTestClassMap.put("BOARD_SEQ", params.get("BOARDVIEW_SEQ"));
            boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
            boardService.insertBoardCatInfo(boardTestClassMap);
        }
        model.addAttribute("params", params);

        return "redirect:/board/boardFAQList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : boardReplyUpdateProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 답글  수정 프로세스
     * @param request
     * @return
     */
    /*@RequestMapping(value="/boardReplyUpdateProcess.do")
    public String boardReplyUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("BOARDENVID",request.getParameter("BOARDENVID"));
        params.put("BOARDTYPE",request.getParameter("BOARDTYPE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));

        params.put("ISTOP",request.getParameter("ISTOP"));
        params.put("ISOPEN",request.getParameter("ISOPEN"));

        params.put("writer",request.getParameter("writer"));
        params.put("writerId",request.getParameter("writerId"));
        params.put("title",request.getParameter("title"));
        params.put("content",request.getParameter("content"));

        params.put("ISATTACHFILEID",CommonUtil.isNull(request.getParameter("ISATTACHFILEID"), ""));

        String rootPath = fsResource.getPath();
        String subPath = "board/";

        //파일업로드하는게 있으면
        MultipartFile uploadFile = multiRequest.getFile("uploadFile");
        if(uploadFile != null){
            if(uploadFile.isEmpty() == false) {
                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
                String fileId =  String.valueOf(fileMap.get("attachFileId"));
                params.put("ATTACHFILEID",fileId );
            }else{
                params.put("ATTACHFILEID", "");
            }
        }

        @SuppressWarnings("unused")
        Object bContentId = boardService.boardReplyUpdateProcess(params);

        model.addAttribute("params", params);

        return "forward:/board/boardList.do";
    }*/

    /**
     * @Method Name : offExamView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 답변 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyWrite.pop")
    public String boardReplyWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        // 등록된 원본글 data
        model.addAttribute("detailView",boardService.getBoardDetail(params));
        model.addAttribute("params",params);

        return "/board/boardReplyWrite";
    }

    /**
     * @Method Name : boardReplyInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판 답변글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyInsertProcess.pop")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardReplyInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CREATENAME",CommonUtil.isNull(request.getParameter("CREATENAME"), ""));
        params.put("REG_ID",CommonUtil.isNull(request.getParameter("REG_ID"), ""));

        params.put("SUBJECT",CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("CONTENT",CommonUtil.isNull(request.getParameter("CONTENT"), ""));
        params.put("PROF_ID",CommonUtil.isNull(request.getParameter("PROF_ID"), ""));
        params.put("MOCKCODE", CommonUtil.isNull(request.getParameter("MOCKCODE"), ""));

        String rootPath = fsResource.getPath();
//        String subPath = "board/";
        String subPath = "board/"+DateUtil.dateFormat("YYYYMM")+"/";

        HashMap<String, String> params2 = new  HashMap<String, String>();
        params2.put("BOARDVIEW_SEQ", params.get("BOARDVIEW_SEQ").toString());
        int isReply = boardService.getIsReply(params2);

        if(isReply > 0){
            model.addAttribute("params", params);
            model.addAttribute("MESSAGE", "답글이 이미 등록되었습니다.");
            return "/board/message";
        }

        MultipartFile uploadFile = multiRequest.getFile("uploadFile");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            params.put("FILE_PATH", String.valueOf(fileMap.get("fileFullPath")));
            params.put("FILE_NAME", String.valueOf(fileMap.get("fileName")));
            params.put("REAL_FILE_NAME", uploadFile.getOriginalFilename().toString() );
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
        }

        @SuppressWarnings("unused")
        Object bContentId = boardService.boardReplyInsertProcess(params);

        if(null == params.get("SEARCHONOFFDIV") || "".equals(params.get("SEARCHONOFFDIV").toString())
                || !"T".equals(params.get("SEARCHONOFFDIV").toString())) {
            //원본글의 게시판 코드 리스트 가져오기
            HashMap<String, String> getCodeParam  = new HashMap<String, String>();
            getCodeParam.put("BOARD_MNG_SEQ", String.valueOf(params.get("BOARD_MNG_SEQ")));
            getCodeParam.put("BOARDVIEW_SEQ", String.valueOf(params.get("BOARDVIEW_SEQ")));
            List<HashMap<String, String>> codeList = boardService.getboardCodeList(getCodeParam);

            //게시판에서 선택한 직급 구분 codeStr 배열화
            for (int i = 0; i < codeList.size(); i++) {
                HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
                boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
                boardTestClassMap.put("BOARD_SEQ", params.get("bContentId"));
                boardTestClassMap.put("CATEGORY_CODE", codeList.get(i).get("CATEGORY_CODE"));

                boardService.insertBoardCatInfo(boardTestClassMap);
            }
        }
        model.addAttribute("params", params);

        return "forward:/board/boardList.pop";
    }

    /**
     * @Method Name : boardReplyDelete
     * @작성일 : 2015. 6.
     * @Method 설명 : 리플글 삭제프로세스
     * @param request
     * @return
     */
    /* @RequestMapping(value="/boardReplyDelete.pop")
    public String boardReplyDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("BOARDENVID",request.getParameter("BOARDENVID"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));

        params.put("writer",request.getParameter("writer"));
        params.put("writerId",request.getParameter("writerId"));
        params.put("ISTOP",request.getParameter("ISTOP"));
        params.put("ISOPEN",request.getParameter("ISOPEN"));
        params.put("title",request.getParameter("title"));
        params.put("content",request.getParameter("content"));

        //게시글 데이터 지우고, 게시글코드 지우고, 파일 지우고, 파일DB 지우고
        boardService.deleteBoardData(params);
        boardService.deleteBoardCatInfo(params);
        boardService.deleteBoardFile(params);

        params.put("ATTACHFILEID",request.getParameter("ATTACHFILEID"));
        if(String.valueOf(params.get("ATTACHFILEID")) != null  && String.valueOf(params.get("ATTACHFILEID")) != ""){
            //파일 삭제
            String filePath = String.valueOf(boardService.getFileInfo(params).get("FILEPATH"));
            if(filePath != null && filePath==""){
                File file = new File(filePath);
                file.delete();
            }
        }
        model.addAttribute("params", params);

        return "forward:/board/boardList.pop";
    }*/

    /**
     * 게시판 수정 파일 삭제 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getReplyData.do")
    @ResponseBody
    public HashMap<String, String> getReplyData(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> replayData = boardService.getReplyData(params);
        HashMap<String, String> result = new HashMap<String, String>();
        if(replayData.size()  > 0){
            result.put("message","N");
        }else{
            result.put("message","Y");
        }
        return result;
    }

    /**
     * @Method Name : boardReplyDelete
     * @작성일 : 2015. 6.
     * @Method 설명 : 리플글 삭제프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardDelete.pop")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        //원본글
        boardService.deleteBoardData(params);
        boardService.deleteBoardFileData(params);
        boardService.deleteBoardCatInfo(params);

        params.put("FILE_PATH",CommonUtil.isNull(String.valueOf(request.getParameter("FILE_PATH")), ""));
        //원본글의 파일존재 여부에따라
        if(String.valueOf(params.get("FILE_PATH")) != null  && String.valueOf(params.get("FILE_PATH")) != ""){
            String rootPath = fsResource.getPath();
            String filePath = String.valueOf(rootPath+params.get("FILE_PATH"));
            //파일 삭제
            File file = new File(filePath);
            file.delete();
        }
        //원본글 end

        model.addAttribute("params", params);

        return "redirect:/board/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : boardFAQDelete
     * @작성일 : 2015. 6.
     * @Method 설명 : 리플글 삭제프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardFAQDelete.pop")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardFAQDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        //원본글
        boardService.deleteBoardData(params);
        boardService.deleteBoardCatInfo(params);

        params.put("FILE_PATH",CommonUtil.isNull(String.valueOf(request.getParameter("FILE_PATH")), ""));
        //원본글의 파일존재 여부에따라
        if(String.valueOf(params.get("FILE_PATH")) != null  && String.valueOf(params.get("FILE_PATH")) != ""){
            String rootPath = fsResource.getPath();
            String filePath = String.valueOf(rootPath+params.get("FILE_PATH"));
            //파일 삭제
            File file = new File(filePath);
            file.delete();
        }
        //원본글 end
        model.addAttribute("params", params);

        return "redirect:/board/boardFAQList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }
    
    /**
	 * @Method Name  : updateBoardReply
	 * @Date         : 2016. 11.
	 * @변경이력      :
	 * @Method 설명      :  미응답게시판 처리상태 변경
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
    @RequestMapping(value="/updateBoardReply.do")
	@ResponseBody
	public HashMap<String, String> updateBoardReply(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		HashMap<String, String> returnMsg = new HashMap<String, String>();
		params.put("BOARDVIEW_MNG_SEQ", CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
		params.put("BOARDVIEW_SEQ", CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
		params.put("BOARD_REPLY", CommonUtil.isNull(request.getParameter("BOARD_REPLY"), ""));
		boardService.updateBoardReply(params);
		returnMsg.put("resultmsg", params.get("BOARD_REPLY").toString());
		return returnMsg;
	}
    
    /**
     * 상담게시판 관리자 코멘트
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/board_reply.do")
    public String board_reply(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);
        String REAL_BOARD_MNG_SEQ = "";
        String REAL_BOARD_SEQ = "";
        String BOARD_MNG_SEQ =  CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""); //생성게시판,전체게시판
        String BOARDVIEW_MNG_SEQ =  CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""); //미응답게시판
        String BOARDVIEWPARENT_SEQ =  CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""); //답변 상세
        String BOARDVIEW_SEQ =  CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""); //원본글 상세
        if(!"".equals(BOARDVIEW_MNG_SEQ)){
        	REAL_BOARD_MNG_SEQ = BOARDVIEW_MNG_SEQ;
        }else{
        	REAL_BOARD_MNG_SEQ = BOARD_MNG_SEQ;
        }
        
        if(!"".equals(BOARDVIEWPARENT_SEQ)&&!"0".equals(BOARDVIEWPARENT_SEQ)){
        	REAL_BOARD_SEQ = BOARDVIEWPARENT_SEQ;
        }else{
        	REAL_BOARD_SEQ = BOARDVIEW_SEQ;
        }
            /* 페이징 */
            int listCount = 0;
            int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("cmmtCurrentPage"), "1"));
            int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("cmmtpageRow"), "10"));

            int startNo = (currentPage - 1) * pageRow;
            int endNo = startNo + pageRow;

            params.put("startNo", String.valueOf(startNo));
            params.put("endNo", String.valueOf(endNo));
            /* 페이징 */
            
            params.put("CMMT_BOARD_MNG_SEQ", REAL_BOARD_MNG_SEQ);
            params.put("CMMT_BOARD_SEQ", REAL_BOARD_SEQ);

            List<HashMap<String, String>> boardCommentList = boardService.selectBoardCmmntList(params);
            listCount =  boardService.selectBoardCmmntListCount(params);
            String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

            model.addAttribute("boardCommentList", boardCommentList);
            model.addAttribute("params", params);
            model.addAttribute("pagingHtml", pagingHtml);
            model.addAttribute("listCount", listCount);

        return "/board/board_reply_view";
    }
    
    @RequestMapping(value="/boardCommentInsert.do")
    @ResponseBody
    public HashMap<String, String> boardCommentInsert(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        String CMMT_BOARD_MNG_SEQ =  CommonUtil.isNull(request.getParameter("CMMT_BOARD_MNG_SEQ"), "");
        String CMMT_BOARD_SEQ =  CommonUtil.isNull(request.getParameter("CMMT_BOARD_SEQ"), "");
        String TITLE =  CommonUtil.isNull(request.getParameter("TITLE"), "");
        String CONTENT =  CommonUtil.isNull(request.getParameter("CONTENT"), "");
        
        params.put("CMMT_BOARD_MNG_SEQ", CMMT_BOARD_MNG_SEQ);
        params.put("CMMT_BOARD_SEQ", CMMT_BOARD_SEQ);
        params.put("TITLE", TITLE);
        params.put("CONTENT", CONTENT);

        String result = "0";
        if(params.get("USER_ID")!=null&&!"".equals(params.get("USER_ID"))){
        	   boardService.insertBoardCmmnt(params);
               result = "1";
        }

        params.put("result", result);

        return params;
    }

    @RequestMapping(value="/boardCommentDelete.do")
    @ResponseBody
    public HashMap<String, String> boardCommentDelete(ModelMap model, HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        String DELETE_SEQ =  CommonUtil.isNull(request.getParameter("DELETE_SEQ"), "");
        
        params.put("DELETE_SEQ", DELETE_SEQ);
        String result = "0";
        if(params.get("USER_ID")!=null&&!"".equals(params.get("USER_ID"))){
	        boardService.deleteBoardCmmnt(params);
	        result = "1";
        }

        params.put("result", result);

        return params;
    }
    
    /**
     * @Method Name : boardReplyView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 답변 보기 화면
     * @param request
     * @return
     */
    /*@RequestMapping(value="/boardReplyView.pop")
    public String boardReplyView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARDENVID",CommonUtil.isNull(request.getParameter("BOARDENVID"), "1"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));

        //조회수 증가
        //boardService.updateBoardHits(params);
        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);

        //해당글이 리플을 가지고있는지 여부
        int replyCount = boardService.getIsReply(params);
        params.put("ISBOARDREPLY", String.valueOf(replyCount));

        model.addAttribute("detailView",detailView);
        model.addAttribute("params",params);

        return "/board/boardReplyView";
    }*/

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 04.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("USERID", loginInfo.get("USER_ID"));
        //params.put("USER_NM", loginInfo.get("USER_NM"));
        //params.put("USERNAME", loginInfo.get("USER_NM"));

        String userName = "";
        if (loginInfo.get("USER_ROLE").equals("ADMIN")){
            userName = loginInfo.get("USER_NICKNM");
        }else{
            userName = loginInfo.get("USER_NM");
        }
        params.put("USER_NM", userName);
        params.put("USERNAME", userName);
        
        model.addAttribute("loginInfo", loginInfo);
        
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        model.addAttribute("currentPage", params.get("currentPage"));
        model.addAttribute("pageRow", params.get("pageRow"));

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        model.addAttribute("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
        params.put("SEARCHONOFFDIV",CommonUtil.isNull(request.getParameter("SEARCHONOFFDIV"), ""));
        model.addAttribute("SEARCHONOFFDIV", params.get("SEARCHONOFFDIV"));

        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",request.getParameter("BOARD_MNG_NAME"));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHPRFID",CommonUtil.isNull(request.getParameter("SEARCHPRFID"), ""));

        params.put("NOTICE_TOP_YN",CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(String.valueOf(request.getParameter("OPEN_YN")), ""));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));

        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
        params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
        params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));
        
        params.put("ONOFF_DIV",CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
		params.put("BOARDVIEW_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
		params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
		
		params.put("BOARDALL_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARDALL_MNG_SEQ"), ""));
		
        params.put("DIVICE_TYPE",CommonUtil.isNull((request.getParameter("DIVICE_TYPE")), "3"));
        
        params.put("EXAM_TYPE",CommonUtil.isNull(request.getParameter("EXAM_TYPE"), ""));
        params.put("EXAM_AREA",CommonUtil.isNull((request.getParameter("EXAM_AREA")), ""));
        params.put("SCH_EXAM_TYPE",CommonUtil.isNull((request.getParameter("SCH_EXAM_TYPE")), ""));
        params.put("SCH_EXAM_AREA",CommonUtil.isNull((request.getParameter("SCH_EXAM_AREA")), ""));
        
        params.put("UPDATE_MAIN",CommonUtil.isNull((request.getParameter("UPDATE_MAIN")), ""));
    }
}
