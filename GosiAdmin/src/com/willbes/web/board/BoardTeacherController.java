package com.willbes.web.board;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.board.service.BoardService;
import com.willbes.web.boardNotAnswer.service.BoardNotAnswerService;
import com.willbes.web.lecture.service.SubjectService;
import com.willbes.web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/board/teacher")
@Controller
public class BoardTeacherController extends BoardController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private TeacherService teacherservice;
    @Autowired
    private SubjectService subjectservice;

    @Resource(name="boardTeacherServiceImpl")
    private BoardService boardService;
	@Autowired
	private BoardNotAnswerService boardNService;	

    /**
     * @Method Name : boardList
     * @작성일 : 2015. 6
     * @Method 설명 :  게시판 리스트
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

        params.put("SEARCHSBJ",CommonUtil.isNull(request.getParameter("SEARCHSBJ"), "")); //과목이 선택된 경우

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

        if(null != params.get("SEARCHONOFFDIV") && "T".equals(params.get("SEARCHONOFFDIV").toString())) {
            params.put("SEARCHGUBN", "T");
            List<HashMap<String, String>> subject_list = subjectservice.subjectList(params);
            List<HashMap<String, String>> teacher_list = teacherservice.teacherList(params);
            model.addAttribute("SEARCHPRFIDs", teacher_list);
            model.addAttribute("subject_list", subject_list);
        }

        return "board/teacher/boardList";
    }

	/**
	 * @Method Name  : boardNotList
	 * @Date         : 2015. 10. 09. 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	강사게시판 미답변 리스트
	 * @param model
	 * @param req
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/boardNotList.do")
	public String boardNotList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);
		
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), "15"));
		
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		
		params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), "SEARCHSUBJECT"));
		params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		
		params.put("BOARD_MNG_SEQ", "BOARD_012");
		params.put("PRF", "Y");
		params.put("ANSW", "N");
		//해당 게시판 글 리스트
		List<HashMap<String, String>> list = boardNService.boardList(params);
		int listCount = boardNService.listCount(params);
		
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
		
		model.addAttribute("list", list);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		
		return "board/teacher/boardNotList";
	}
    
    @RequestMapping(value="/Is_RE_Update.pop")
    public String Is_RE_Update(ModelMap model, HttpServletRequest request) throws Exception {

        super.Is_RE_Update(model, request);

        return "redirect:/board/teacher/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+model.get("BOARD_MNG_SEQ");
    }

    /**
     * @Method Name : offExamView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardWrite.pop")
    public String boardWrite(ModelMap model, HttpServletRequest request) throws Exception {

        super.boardWrite(model, request);

        return "/board/teacher/boardWrite";
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

        super.boardInsertProcess(model, request, multiRequest);

        return "redirect:/board/teacher/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+model.get("BOARD_MNG_SEQ");
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

        super.boardView(model, request);

        return "/board/teacher/boardView";
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

        super.boardModifiy(model, request);

        return "/board/teacher/boardModify";
    }

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
        return super.boardDeleteFile(model, request);
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

        super.boardUpdateProcess(model, request, multiRequest);

        return "redirect:/board/teacher/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+model.get("BOARD_MNG_SEQ")+"&SEARCHCATEGORY="+model.get("SEARCHCATEGORY");
    }

    /**
     * @Method Name : offExamView
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판글 답변 등록화면
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyWrite.pop")
    public String boardReplyWrite(ModelMap model, HttpServletRequest request) throws Exception {

        super.boardReplyWrite(model, request);

        return "/board/teacher/boardReplyWrite";
    }

    /**
     * @Method Name : boardReplyInsertProcess
     * @작성일 : 2015. 6.
     * @Method 설명 : 게시판 답변글 등록프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardReplyInsertProcess.pop")
    public String boardReplyInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {

        super.boardReplyInsertProcess(model, request, multiRequest);

        return "forward:/board/teacher/boardList.pop";
    }

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

        return super.getReplyData(model, request);
    }

    /**
     * @Method Name : boardReplyDelete
     * @작성일 : 2015. 6.
     * @Method 설명 : 리플글 삭제프로세스
     * @param request
     * @return
     */
    @RequestMapping(value="/boardDelete.pop")
    public String boardDelete(ModelMap model, HttpServletRequest request) throws Exception {

        super.boardDelete(model, request);

        return "redirect:/board/teacher/boardList.pop?SEARCHONOFFDIV="+model.get("SEARCHONOFFDIV")+"&BOARD_MNG_SEQ="+model.get("BOARD_MNG_SEQ");
    }
    
    /**
     * @Method Name : boardTeacherSearchPop
     * @작성일 : 2017 2.
     * @Method 설명 : 강사게시판 검색팝업
     * @param request
     * @return
     */
    @RequestMapping(value="/boardTeacherSearchPop.pop")
    public String boardTeacherSearchPop(ModelMap model, HttpServletRequest request) throws Exception {
    	HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
	    
		int currentPage = Integer.parseInt(params.get("currentPage"));
	    int pageRow = Integer.parseInt(params.get("pageRow"));
	    int startNo = (currentPage - 1) * pageRow;
	    int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        
        params.put("FLAG", "O");
        params.put("SEARCH_TEACHER", "Y");
        List<HashMap<String, String>> teacher_list = teacherservice.teacherAllList(params);
        int listCount = teacherservice.teacherAllListCount(params);
        model.addAttribute("teacher_list", teacher_list);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);
        
        return "/board/teacher/tearcher_search_pop";
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
        params.put("USERID", loginInfo.get("USER_ID"));

        String userName = "";
        if (loginInfo.get("USER_ROLE").equals("ADMIN")){
            userName = loginInfo.get("USER_NICKNM");
        }else{
            userName = loginInfo.get("USER_NM");
        }
        params.put("USER_NM", userName);
        params.put("USERNAME", userName);

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
    }
}
