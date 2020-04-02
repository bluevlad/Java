package web.boardManagement;

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

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.boardManagement.service.BoardManagementService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   : BoardManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/boardManagement")
@Controller
public class BoardManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private BoardManagementService boardManagementService;

    /**
     * @Method Name  : commonCodeList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	코드관리  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/boardMngList.do")
    public String boardMngList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
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

        // 공통코드 리스트
        list = boardManagementService.getBoardMngList(searchMap);

        // 총 건수 - 공통코드
        int listCount = boardManagementService.getBoardMngListCount(searchMap);

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

        return "boardManagement/boardMngList";
    }


    /**
     * @Method Name  : commonCodeInsert
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 코드관리 등록 화면
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardMngInsert.do")
    public String boardMngInsert(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = sessionMap.get("USER_NM");
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("USERID", userId);
        model.addAttribute("params",params);

        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param

        List<HashMap<String, String>> boardTypeList = boardManagementService.getBoardTypeList();
        model.addAttribute("boardTypeList", boardTypeList);

        return "/boardManagement/boardMngInsert";
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
    @RequestMapping(value="/boardMngInsertProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardMngInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("BOARD_MNG_NAME", CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));
        params.put("BOARD_MNG_TYPE", CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        //params.put("ATTACH_FILE_YN", CommonUtil.isNull(request.getParameter("ATTACH_FILE_YN"), ""));
        params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
        params.put("REPLY_YN", CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = sessionMap.get("USER_NM");
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);

        //코드 등록
        boardManagementService.boardMngInsertProcess(params);
        model.addAttribute("params",params);
        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param

        return "forward:/boardManagement/boardMngList.do";
    }

    /**
     * @Method Name  : commonCodeDetail
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 코드관리 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardMngDetail.do")
    public String boardMngDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("DETAIL_BOARD_MNG_SEQ",request.getParameter("DETAIL_BOARD_MNG_SEQ"));


        //코드 select
        HashMap<String, Object> detail  =	boardManagementService.boardMngDetail(params);

        model.addAttribute("params",params);
        model.addAttribute("detail",detail);
        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param
        return "/boardManagement/boardMngDetail";
    }


    /**
     * @Method Name  : commonCodeUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 공통코드 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardMngUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardMngUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("DETAIL_BOARD_MNG_SEQ",request.getParameter("DETAIL_BOARD_MNG_SEQ"));
        params.put("BOARD_MNG_NAME", CommonUtil.isNull(request.getParameter("BOARD_MNG_NAME"), ""));
        params.put("BOARD_MNG_TYPE", CommonUtil.isNull(request.getParameter("BOARD_MNG_TYPE"), ""));
        //params.put("ATTACH_FILE_YN", CommonUtil.isNull(request.getParameter("ATTACH_FILE_YN"), ""));
        params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
        params.put("REPLY_YN", CommonUtil.isNull(request.getParameter("REPLY_YN"), ""));
        params.put("ISUSE",CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
        String userName = sessionMap.get("USER_NM");
        String userId = sessionMap.get("USER_ID");
        params.put("USERNAME", userName);
        params.put("UPD_ID", userId);
        //코드 수정 등록
        boardManagementService.boardMngUpdateProcess(params);

        model.addAttribute("params",params);

        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param
        return "forward:/boardManagement/boardMngList.do";
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
    @RequestMapping(value="/boardMngDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardMngDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("DETAIL_BOARD_MNG_SEQ",request.getParameter("DETAIL_BOARD_MNG_SEQ"));
        // 삭제
        boardManagementService.boardMngDelete(params);

        model.addAttribute("params",params);
        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param
        return "redirect:/boardManagement/boardMngList.do";
    }


    /**
     * @Method Name  : commonCodeCheckDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 코드관리 리스트 체크박스  일괄 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/boardMngCheckDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String boardMngCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SEARCHTYPE", "");
        params.put("SEARCHKEYWORD" , "");

        params.put("currentPage",request.getParameter("currentPage"));
        params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("deleteIds",request.getParameter("deleteIds").replaceAll("&apos;", "\'"));
        // 일괄 삭제
        boardManagementService.boardMngCheckDelete(params);

        model.addAttribute("params",params);
        //메뉴 param
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        //메뉴 param
        return "redirect:/boardManagement/boardMngList.do";
    }
}
