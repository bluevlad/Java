package web.mocktest.board;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import web.util.CommonUtil;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.board.BoardController;
import web.board.service.BoardService;
import web.mocktest.offExamReg.service.OffExamRegService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/mtboard")
@Controller
public class MTBoardController extends BoardController {

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="MTBoardServiceImpl")
    private BoardService boardService;

    @Autowired
    private OffExamRegService offExamRegservice;

    /**
     * @Method Name : boardList
     * @작성일 : 2015. 6
     * @Method 설명 : 게시판 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/boardList.do")
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

        model.addAttribute("rankList", rankList);
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "mocktest/board/boardList";
    }

    /**
     * @Method Name : boardWrite
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardWrite.do")
    public String boardWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        model.addAttribute("gbList",boardService.getRankCode(params));
        params.put("startNo", String.valueOf("0"));
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");
        params.put("SEARCHUSEFLAG", "Y");
        model.addAttribute("exmList",offExamRegservice.offExamList(params));
        model.addAttribute("params",params);

        return "mocktest/board/boardWrite";
    }

    /**
     * @Method Name : boardInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardInsertProcess.do")
    public String boardInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        super.boardInsertProcess(model, request, multiRequest);
        model.addAttribute("params", params);

        return "redirect:/mtboard/boardList.do?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : boardView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 보기 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardView.do")
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

        return "mocktest/board/boardView";
    }

    /**
     * @Method Name : boardModify
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정 화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardModify.do")
    public String boardModifiy(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        if(params.get("BOARDVIEWPARENT_SEQ").toString().equals("0")){ //원글일때
            //해당글의 코드데이터
            List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
            model.addAttribute("boardCodeList",boardCodeList);
            // 등록된 글 data
            params.put("board_gubun", "ORIGIN");
            model.addAttribute("detailView",boardService.getBoardDetail(params));
            model.addAttribute("boardAttachFile",boardService.boardAttachFile(params));

            //수정 체크박스를 위한 코드리스트
            model.addAttribute("gbList",boardService.getRankCode(params));
            params.put("startNo", String.valueOf("0"));
            params.put("endNo", propertiesService.getInt("maxUnitSize")+"");
            params.put("SEARCHUSEFLAG", "Y");
            model.addAttribute("exmList",offExamRegservice.offExamList(params));

        }else{ //답변일때
            // 등록된 글 data
            params.put("board_gubun", "REPLY");
            model.addAttribute("detailView",boardService.getBoardDetail(params));
            model.addAttribute("boardAttachFile",boardService.boardAttachFile(params));
        }
        model.addAttribute("params",params);

        return "mocktest/board/boardModify";
    }

    /**
     * @Method Name : boardUpdateProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 수정프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardUpdateProcess.do")
    public String boardUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        super.boardUpdateProcess(model, request, multiRequest);
        model.addAttribute("params", params);

        return "redirect:/mtboard/boardList.do?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+params.get("BOARD_MNG_SEQ")+"&SEARCHCATEGORY="+params.get("SEARCHCATEGORY");
    }

    /**
     * @Method Name : boardReplyWrite
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 답변 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyWrite.do")
    public String boardReplyWrite(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        // 등록된 원본글 data
        model.addAttribute("detailView",boardService.getBoardDetail(params));
        model.addAttribute("params",params);

        return "mocktest/board/boardReplyWrite";
    }

    /**
     * @Method Name : boardReplyInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판 답변글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyInsertProcess.do")
    public String boardReplyInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        super.boardReplyInsertProcess(model, request, multiRequest);
        model.addAttribute("params", params);

        return "forward:/mtboard/boardList.do";
    }

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
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USERNAME", loginInfo.get("USER_NM"));
        params.put("USERID", loginInfo.get("USER_ID"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

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

    }
}
