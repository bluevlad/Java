package com.willbes.web.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.board.service.BoardService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/intgboard")
@Controller
public class IntgBoardMngController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="boardServiceImpl")
    private BoardService boardService;

    /**
     * @Method Name : boardList
     * @작성일 : 2015. 06
     * @Method 설명 :  게시판 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/list.do")
    public String boardList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        //동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
        List<HashMap<String, String>> rankList = boardService.getCategoryCode(params);

        params.put("SEARCHONOFFDIV",CommonUtil.isNull(request.getParameter("SEARCHONOFFDIV"), ""));
        params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));
        params.put("SEARCHBRDTYPE",CommonUtil.isNull(request.getParameter("SEARCHBRDTYPE"), ""));
        params.put("SDATE",CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE",CommonUtil.isNull(request.getParameter("EDATE"), ""));

        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), "SEARCHSUBJECT"));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        String SEARCHCATEGORY = CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), "");
        List<String> slist = new ArrayList<String>();
        if(null != SEARCHCATEGORY && SEARCHCATEGORY.length() >1) {
            String[] cats = SEARCHCATEGORY.split(",");
            for(String cat : cats) {
                if(null != cat && cat.length()>0) {
                    slist.add(cat);
                }
            }
            params.put("CATE_LIST", slist);
        } else {
            params.put("CATE_LIST", null);
        }

        //해당 게시판 글 리스트
        List<HashMap<String, String>> list = boardService.selectIntgBoardList(params);
        int listCount = boardService.getIntgBoardListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("rankList", rankList);
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        return "/boardAll/intg/list";
    }

    /*    @RequestMapping(value="/Is_RE_Update.do")
    public String Is_RE_Update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));

        String[] UPDATE_IS_ARR = request.getParameterValues("UPDATE_IS_ARR");
        String[] UPDATE_RE_ARR = request.getParameterValues("UPDATE_RE_ARR");
        String[] UPDATE_OPEN_ARR = request.getParameterValues("UPDATE_OPEN_ARR");

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

        return "redirect:/intgboard/list.do?BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

     *//**
     * @Method Name : boardWrite
     * @작성일 : 2015. 06.
     * @Method 설명 : 게시판글 등록화면
     * @param request
     * @return
     *//*
    @RequestMapping(value="/add.do")
    public String boardWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",request.getParameter("BOARD_MNG_NAME"));
        params.put("NOTICE_TOP_YN",CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(String.valueOf(request.getParameter("OPEN_YN")), ""));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

        List<HashMap<String, String>> list = boardService.getRankCode(params);
        // 게시판타입가져오기
        //HashMap<String, String> boardKind = boardService.getBoardKind(params);

        model.addAttribute("params",params);
        model.addAttribute("gbList",list);

        return "/boardAll/intg/add";
    }

      *//**
      * @Method Name : boardInsertProcess
      * @작성일 : 2015. 06.
      * @Method 설명 : 게시판글 등록프로세스
      * @param request
      * @return
      *//*
    @RequestMapping(value="/boardInsertProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",request.getParameter("BOARD_MNG_NAME"));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //params.put("codeStr",request.getParameter("codeStr"));
        params.put("CREATENAME",request.getParameter("CREATENAME"));
        params.put("REG_ID",request.getParameter("REG_ID"));

        params.put("NOTICE_TOP_YN",CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
        params.put("ISSUE",CommonUtil.isNull(request.getParameter("ISSUE"), ""));
        params.put("RECOMMEND",CommonUtil.isNull(request.getParameter("RECOMMEND"), ""));

        params.put("SUBJECT",request.getParameter("SUBJECT"));
        params.put("CONTENT",request.getParameter("CONTENT"));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        String rootPath = fsResource.getPath();
        String subPath = "board/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("uploadFileThumbNail");
        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
            //HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            String file_path =  String.valueOf(fileMap.get("fileFullPath"));
            String file_name =  String.valueOf(fileMap.get("fileName"));

            params.put("THUMBNAIL_FILE_PATH",file_path );
            params.put("THUMBNAIL_FILE_NAME",file_name );
            params.put("THUMBNAIL_FILE_REAL_NAME",uploadFileThumbNail.getOriginalFilename().toString() );
        }else{
            params.put("THUMBNAIL_FILE_PATH","" );
            params.put("THUMBNAIL_FILE_NAME","" );
            params.put("THUMBNAIL_FILE_REAL_NAME","");
        }

        Object BOARD_SEQ = boardService.boardInsertProcess(params);

        List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){

                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));
                String file_name =  String.valueOf(fileMap.get("fileName"));

                if("null".equals(file_path))continue;
                params.put("FILE_PATH",file_path );
                params.put("FILE_NAME",file_name );
                params.put("REAL_FILE_NAME",uploadFile.get(j).getOriginalFilename().toString() );
                params.put("BOARD_SEQ", params.get("BOARD_SEQ"));

                boardService.insertBoardFile(params);
            }
        }

        //게시판에서 선택한 직급 구분 codeStr 배열화
        String [] code_arr = request.getParameter("codeStr").split("/");
        HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
        for (int i = 0; i < code_arr.length; i++) {
            boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
            boardTestClassMap.put("BOARD_SEQ", params.get("BOARD_SEQ"));
            boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
            boardService.insertBoardCatInfo(boardTestClassMap);
        }

        model.addAttribute("params", params);

        return "redirect:/intgboard/list.do?BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

       *//**
       * @Method Name : boardView
       * @작성일 : 2015. 06.
       * @Method 설명 : 게시판글 보기 화면
       * @param request
       * @return
       *//*
    @RequestMapping(value="/view.do")
    public String boardView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));

        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
        params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
        params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

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
        model.addAttribute("params",params);
        model.addAttribute("detailView",detailView);
        model.addAttribute("boardAttachFile",boardAttachFile);
        model.addAttribute("boardAttachFile_Img",boardAttachFile_Img);

        return "/boardAll/intg/view";
    }

        *//**
        * @Method Name : boardModifiy
        * @작성일 : 2015. 06.
        * @Method 설명 : 게시판글 수정 화면
        * @param request
        * @return
        *//*
    @RequestMapping(value="/modify.do")
    public String boardModifiy(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEW_SEQ",request.getParameter("BOARDVIEW_SEQ"));
        params.put("BOARDVIEWPARENT_SEQ",request.getParameter("BOARDVIEWPARENT_SEQ"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

        if(params.get("BOARDVIEWPARENT_SEQ").toString().equals("0")){ //원글일때
            //수정 체크박스를 위한 코드리스트
            List<HashMap<String, String>> list = boardService.getRankCode(params);

            //해당글의 코드데이터
            List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
            // 등록된 글 data
            HashMap<String, Object> detailView = boardService.getBoardDetail(params);
            List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
            params.put("board_gubun", "ORIGIN");
            model.addAttribute("params",params);
            model.addAttribute("detailView",detailView);
            model.addAttribute("gbList",list);
            model.addAttribute("boardCodeList",boardCodeList);
            model.addAttribute("boardAttachFile",boardAttachFile);
        }else{ //답변일때
            // 등록된 글 data
            HashMap<String, Object> detailView = boardService.getBoardDetail(params);
            List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
            params.put("board_gubun", "REPLY");
            model.addAttribute("params",params);
            model.addAttribute("detailView",detailView);
            model.addAttribute("boardAttachFile",boardAttachFile);
        }

        return "/boardAll/intg/modify";
    }

         *//**
         * 게시판 수정 첨부 파일 삭제 프로세스
         * @param model
         * @param request
         * @return
         * @throws Exception
         *//*
    @RequestMapping(value="/boardDeleteFile.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
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

          *//**
          * @Method Name : boardUpdateProcess
          * @작성일 : 2015. 06.
          * @Method 설명 : 게시판글 수정프로세스
          * @param request
          * @return
          *//*
    @RequestMapping(value="/boardUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        //게시글이 원글인지 답글인지 구분자
        params.put("board_gubun",CommonUtil.isNull(request.getParameter("board_gubun"), ""));

        params.put("BOARD_MNG_SEQ",request.getParameter("BOARD_MNG_SEQ"));
        params.put("BOARD_MNG_TYPE",request.getParameter("BOARD_MNG_TYPE"));
        params.put("BOARD_MNG_NAME",request.getParameter("BOARD_MNG_NAME"));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(String.valueOf(request.getParameter("BOARD_MNG_NAME")), ""));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //params.put("codeStr",request.getParameter("codeStr"));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("CREATENAME",request.getParameter("CREATENAME"));
        params.put("REG_ID",request.getParameter("REG_ID"));
        params.put("NOTICE_TOP_YN",request.getParameter("NOTICE_TOP_YN"));
        params.put("OPEN_YN",request.getParameter("OPEN_YN"));
        params.put("ISSUE",CommonUtil.isNull(request.getParameter("ISSUE"), ""));
        params.put("RECOMMEND",CommonUtil.isNull(request.getParameter("RECOMMEND"), ""));
        params.put("SUBJECT",request.getParameter("SUBJECT"));
        params.put("CONTENT",request.getParameter("CONTENT"));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        //params.put("ISATTACHFILEID",CommonUtil.isNull(request.getParameter("ISATTACHFILEID"), ""));

        params.put("BOARDVIEW_SEQ",request.getParameter("BOARDVIEW_SEQ"));
        params.put("BOARDVIEW_SEQ",request.getParameter("BOARDVIEW_SEQ"));
        params.put("BOARDVIEWPARENT_SEQ",request.getParameter("BOARDVIEWPARENT_SEQ"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

        String rootPath = fsResource.getPath();
        String subPath = "board/";

        MultipartFile uploadFileThumbNail = multiRequest.getFile("uploadFileThumbNail");
        if(uploadFileThumbNail != null && uploadFileThumbNail.isEmpty() == false) {
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFileThumbNail, rootPath, subPath);
            String file_path =  String.valueOf(fileMap.get("fileFullPath"));
            String file_name =  String.valueOf(fileMap.get("fileName"));

            params.put("THUMBNAIL_FILE_PATH",file_path );
            params.put("THUMBNAIL_FILE_NAME",file_name );
            params.put("THUMBNAIL_FILE_REAL_NAME",uploadFileThumbNail.getOriginalFilename().toString() );
        }else{
            params.put("THUMBNAIL_FILE_PATH","" );
            params.put("THUMBNAIL_FILE_NAME","" );
            params.put("THUMBNAIL_FILE_REAL_NAME","");
        }

        Object bContentId = boardService.boardUpdateProcess(params);

        List<MultipartFile> uploadFile = multiRequest.getFiles("ATTACH_FILE");

        if(uploadFile != null && uploadFile.isEmpty() == false) {
            for(int j =0; j < uploadFile.size(); j++){

                HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subPath);
                String file_path =  String.valueOf(fileMap.get("fileFullPath"));
                String file_name =  String.valueOf(fileMap.get("fileName"));

                if("null".equals(file_path))continue;
                params.put("FILE_PATH",file_path );
                params.put("FILE_NAME",file_name );
                params.put("REAL_FILE_NAME",uploadFile.get(j).getOriginalFilename().toString() );
                params.put("BOARD_SEQ", params.get("BOARDVIEW_SEQ"));
                if(params.get("board_gubun").toString().equals("ORIGIN")){
                    boardService.insertBoardFile(params);
                }else{
                    boardService.insertBoardReplyFile(params);
                }
            }
        }

        //게시글이 원본글일때만
        if(params.get("board_gubun").toString().equals("ORIGIN")){
            //기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
            boardService.deleteTboardTestClass(params);
            logger.info("--------"+ params);
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
            logger.info("--------"+ params);
            if(replayData.size()  > 0 ){
                for (int k = 0; k < replayData.size(); k++) {

                    HashMap<String, Object> updateReplyParam = new HashMap<String, Object>();
                    updateReplyParam.put("BOARD_MNG_SEQ", String.valueOf(params.get("BOARD_MNG_SEQ")));
                    updateReplyParam.put("BOARDVIEW_SEQ", replayData.get(k).get("BOARD_SEQ"));
                    //기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
                    boardService.deleteTboardTestClass(updateReplyParam);

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
        model.addAttribute("params", params);

        return "redirect:/intgboard/list.do?BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ")+"&SEARCHCATEGORY="+params.get("SEARCHCATEGORY");
    }

           *//**
           * @Method Name : boardDelete
           * @작성일 : 2015. 06.
           * @Method 설명 :  삭제프로세스
           * @param request
           * @return
           *//*
    @RequestMapping(value="/delete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));
        params.put("FILE_PATH",CommonUtil.isNull(String.valueOf(request.getParameter("FILE_PATH")), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEW_SEQ")), ""));
        params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEWPARENT_SEQ")), ""));
        params.put("BOARDVIEWCODENAME",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEWCODENAME")), ""));

        //원본글
        boardService.deleteBoardData(params);
        boardService.deleteBoardFileData(params);
        boardService.deleteTboardTestClass(params);

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

        return "redirect:/intgboard/list.do?BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

            *//**
            * @Method Name : boardReplyWrite
            * @작성일 : 2015. 06.
            * @Method 설명 : 게시판글 답변 등록화면
            * @param request
            * @return
            *//*
    @RequestMapping(value="/reply_add.do")
    public String boardReplyWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));

        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("REPLY_YN",CommonUtil.isNull(String.valueOf(request.getParameter("REPLY_YN")), ""));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
        params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
        params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

        // 등록된 원본글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);

        model.addAttribute("params",params);
        model.addAttribute("detailView",detailView);

        return "/boardAll/intg/reply_add";
    }

             *//**
             * @Method Name : boardReplyInsertProcess
             * @작성일 : 2015. 06.
             * @Method 설명 : 게시판 답변글 등록프로세스
             * @param request
             * @return
             *//*
    @RequestMapping(value="/boardReplyInsertProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardReplyInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("BOARD_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARD_MNG_SEQ"), ""));
        params.put("BOARD_MNG_TYPE",CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        params.put("BOARD_MNG_NAME",CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));
        params.put("REPLY_YN",CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));

        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("CREATENAME",CommonUtil.isNull(request.getParameter("CREATENAME"), ""));
        params.put("REG_ID",CommonUtil.isNull(request.getParameter("REG_ID"), ""));
        params.put("NOTICE_TOP_YN",CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
        params.put("OPEN_YN",CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
        params.put("SUBJECT",CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("CONTENT",CommonUtil.isNull(request.getParameter("CONTENT"), ""));

        params.put("currentPage",CommonUtil.isNull(request.getParameter("CONTENT"), "1"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
        params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
        params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

        String rootPath = fsResource.getPath();
        String subPath = "board/";

        HashMap<String, String> params2 = new  HashMap<String, String>();
        params2.put("BOARDVIEW_SEQ", params.get("BOARDVIEW_SEQ").toString());
        int isReply = boardService.getIsReply(params2);

        if(isReply > 0){
            model.addAttribute("params", params);
            model.addAttribute("MESSAGE", "답글이 이미 등록되었습니다.");
            return "/boardAll/message";
        }

        MultipartFile uploadFile = multiRequest.getFile("uploadFile");
        if(uploadFile != null && uploadFile.isEmpty() == false) {
            HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
            String file_path =  String.valueOf(fileMap.get("fileFullPath"));
            String file_name =  String.valueOf(fileMap.get("fileName"));

            params.put("FILE_PATH",file_path );
            params.put("FILE_NAME",file_name );
            params.put("REAL_FILE_NAME",uploadFile.getOriginalFilename().toString() );
        }else{
            params.put("FILE_PATH","" );
            params.put("FILE_NAME","" );
            params.put("REAL_FILE_NAME","");
        }

        Object bContentId = boardService.boardReplyInsertProcess(params);

        //원본글의 게시판 코드 리스트 가져오기
        HashMap<String, String> getCodeParam  = new HashMap<String, String>();
        getCodeParam.put("BOARD_MNG_SEQ", String.valueOf(params.get("BOARD_MNG_SEQ")));
        getCodeParam.put("BOARDVIEW_SEQ", String.valueOf(params.get("BOARDVIEW_SEQ")));
        List<HashMap<String, String>> codeList = boardService.getboardCodeList(getCodeParam);

        //게시판에서 선택한 직급 구분 codeStr 배열화
        //String [] code_arr = request.getParameter("codeStr").split("/");
        HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
        for (int i = 0; i < codeList.size(); i++) {
            boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARD_MNG_SEQ"));
            boardTestClassMap.put("BOARD_SEQ", params.get("bContentId"));
            boardTestClassMap.put("CATEGORY_CODE", codeList.get(i).get("CATEGORY_CODE"));

            boardService.insertBoardCatInfo(boardTestClassMap);
        }
        model.addAttribute("params", params);

        return "forward:/intgboard/list.do";
    }

              *//**
              * @Method Name : boardReplyModify
              * @작성일 : 2015. 06.
              * @Method 설명 : 답변글 수정 화면
              * @param request
              * @return
              *//*
    @RequestMapping(value="/reply_modify.do")
    public String boardReplyModify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("BOARDENVID",CommonUtil.isNull(request.getParameter("BOARDENVID"), "1"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEWID",request.getParameter("BOARDVIEWID"));
        params.put("BOARDVIEWPARENTID",request.getParameter("BOARDVIEWPARENTID"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USERID");
        params.put("USERNAME", userName);
        params.put("USERID", userId);

        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);

        model.addAttribute("params",params);
        model.addAttribute("detailView",detailView);

        return "/boardAll/intg/reply_modify";
    }

               *//**
               * @Method Name : boardReplyUpdateProcess
               * @작성일 : 2015. 06.
               * @Method 설명 : 답글  수정 프로세스
               * @param request
               * @return
               *//*
    @RequestMapping(value="/boardReplyUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardReplyUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("BOARDENVID",request.getParameter("BOARDENVID"));
        params.put("BOARDTYPE",request.getParameter("BOARDTYPE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //params.put("codeStr",request.getParameter("codeStr"));
        params.put("writer",request.getParameter("writer"));
        params.put("writerId",request.getParameter("writerId"));
        params.put("ISTOP",request.getParameter("ISTOP"));
        params.put("ISOPEN",request.getParameter("ISOPEN"));
        params.put("title",request.getParameter("title"));
        params.put("content",request.getParameter("content"));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("ISATTACHFILEID",CommonUtil.isNull(request.getParameter("ISATTACHFILEID"), ""));

        params.put("BOARDVIEWID",request.getParameter("BOARDVIEWID"));
        params.put("BOARDVIEWPARENTID",request.getParameter("BOARDVIEWPARENTID"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

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
        Object bContentId = boardService.boardReplyUpdateProcess(params);

        model.addAttribute("params", params);

        return "forward:/intgboard/list.do";
    }

                *//**
                * @Method Name : boardReplyDelete
                * @작성일 : 2015. 06.
                * @Method 설명 : 리플글 삭제프로세스
                * @param request
                * @return
                *//*
    @RequestMapping(value="/reply_delete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardReplyDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("BOARDENVID",request.getParameter("BOARDENVID"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        //params.put("codeStr",request.getParameter("codeStr"));
        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("writer",request.getParameter("writer"));
        params.put("writerId",request.getParameter("writerId"));
        params.put("ISTOP",request.getParameter("ISTOP"));
        params.put("ISOPEN",request.getParameter("ISOPEN"));
        params.put("title",request.getParameter("title"));
        params.put("content",request.getParameter("content"));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEWID",request.getParameter("BOARDVIEWID"));
        params.put("BOARDVIEWPARENTID",request.getParameter("BOARDVIEWPARENTID"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));
        params.put("ATTACHFILEID",request.getParameter("ATTACHFILEID"));

        //게시글 데이터 지우고 게시글코드 지우고 파일 지우고 파일DB 지우고
        if(String.valueOf(params.get("ATTACHFILEID")) != null  && String.valueOf(params.get("ATTACHFILEID")) != ""){
            //파일 삭제
            String filePath = String.valueOf(boardService.getFileInfo(params).get("FILEPATH"));
            if(filePath != null && filePath==""){
                File file = new File(filePath);
                file.delete();
            }
        }

        boardService.deleteBoardData(params);
        boardService.deleteTboardTestClass(params);
        boardService.deleteBoardFile(params);

        model.addAttribute("params", params);

        return "forward:/intgboard/list.do";
    }*/

    /**
     * 게시판 수정 파일 삭제 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    /*    @RequestMapping(value="/getReplyData.do")
    @ResponseBody
    public HashMap<String, String> getReplyData(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        HashMap<String, String> result = new HashMap<String, String>();
        params.put("BOARD_MNG_SEQ", request.getParameter("BOARD_MNG_SEQ"));
        params.put("BOARDVIEW_SEQ", request.getParameter("BOARDVIEW_SEQ"));
        List<HashMap<String, String>> replayData = boardService.getReplyData(params);
        if(replayData.size()  > 0){
            result.put("message","N");
        }else{
            result.put("message","Y");
        }
        return result;
    }
     */
    /**
     * @Method Name : boardReplyView
     * @작성일 : 2015. 06.
     * @Method 설명 : 게시판글 답변 보기 화면
     * @param request
     * @return
     */
    /*    @RequestMapping(value="/reply_view.do")
    public String boardReplyView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
        params.put("BOARDENVID",CommonUtil.isNull(request.getParameter("BOARDENVID"), "1"));
        params.put("BOARDTYPE",CommonUtil.isNull(request.getParameter("BOARDTYPE"), "TESTNOTICE"));
        params.put("BOARDTITLE",request.getParameter("BOARDTITLE"));
        params.put("HASREPLY",CommonUtil.isNull(String.valueOf(request.getParameter("HASREPLY")), "0"));
        params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("BOARDVIEWID",request.getParameter("BOARDVIEWID"));
        params.put("BOARDVIEWPARENTID",request.getParameter("BOARDVIEWPARENTID"));
        params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = "";
        if (sessionMap.get("USER_ROLE").equals("ADMIN")){
            userName = sessionMap.get("USER_NICKNM");
        }else{
            userName = sessionMap.get("USER_NM");
        }
        String userId = sessionMap.get("USERID");
        params.put("USERNAME", userName);
        params.put("USERID", userId);

        //조회수 증가
        //boardService.updateBoardHits(params);
        // 등록된 글 data
        HashMap<String, Object> detailView = boardService.getBoardDetail(params);

        //해당글이 리플을 가지고있는지 여부
        int replyCount = boardService.getIsReply(params);
        params.put("ISBOARDREPLY", String.valueOf(replyCount));


        model.addAttribute("params",params);
        model.addAttribute("detailView",detailView);

        return "/boardAll/intg/reply_view";
    }
     */
    /**
     * @Method Name : setParam
     * @작성일 : 2013. 12.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParam(HashMap<String, Object> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHBANNERNO", CommonUtil.isNull(request.getParameter("SEARCHBANNERNO"), ""));
        params.put("SEARCHISUSE", CommonUtil.isNull(request.getParameter("SEARCHISUSE"), ""));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
    }

}
