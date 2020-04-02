package web.bookCmmt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.book.service.BookService;
import web.bookCmmt.service.BookCmmtService;
import web.lecture.service.KindService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   : BookCmmtController.java
 * @Project    :
 * @Date       :
 * @Author     :
 * @변경이력    :
 * @프로그램 설명 : 교재구매후기 게시판
 */
@RequestMapping(value="/book/comment")
@Controller
public class BookCmmtController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Autowired
    private BookCmmtService bookCmmtService;
    @Autowired
    private KindService kindservice;
    @Autowired
    private BookService bookservice;

    /**
     * @Method Name  : list
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :  리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/list.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(String.valueOf(params.get("currentPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("pageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        //리스트
        List<HashMap<String, Object>> list = bookCmmtService.bookCmmtList(params);
        // 총 건수
        int listCount = bookCmmtService.bookCmmtListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        params.put("SEARCHGUBN", "T");
        model.addAttribute("kindlist", kindservice.getKindList(params));
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "bookCmmnt/list";
    }

    /**
     * @Method Name  : view
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 후기 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/view.do")
    public String view(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        int currentPageReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPageReply"), "1"));
        int pageRowReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRowReply"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPageReply - 1) * pageRowReply;
        int endNo = startNo + pageRowReply;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        params.put("currentPageReply", String.valueOf(currentPageReply));
        params.put("pageRowReply", String.valueOf(pageRowReply));

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("RSC_ID", CommonUtil.isNull(request.getParameter("RSC_ID"), ""));
        params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), ""));

        List<HashMap<String, Object>>  replyList  =	bookCmmtService.bookCmmtDetailList(params);
        // 총 건수
        int replyListCount = bookCmmtService.bookCmmtDetailListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPageReply, replyListCount, pageRowReply).getPagingHtml().toString();

        model.addAttribute("view",bookservice.bookView(params));
        model.addAttribute("viewlist",bookservice.bookViewList(params));
        model.addAttribute("cmmntList",replyList);
        model.addAttribute("totalCount", replyListCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("currentPageReply", currentPageReply);
        model.addAttribute("pageRowReply", pageRowReply);
        model.addAttribute("totalPage", (int) Math.ceil((double) replyListCount / pageRowReply));
        model.addAttribute("params",params);

        return "/bookCmmnt/view";
    }

    /**
     * @Method Name  : delete
     * @Date         : .
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 후기 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/delete.do")
    public String delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("DELETE_RSC_ID",request.getParameter("DELETE_RSC_ID"));
        params.put("DELETE_SEQ",request.getParameter("DELETE_SEQ"));
        //삭제
        bookCmmtService.cmmtDelete(params);

        model.addAttribute("params",params);

        return "forward:/book/comment/view.do";
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

        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", URLDecoder.decode(CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""),"UTF-8"));
    }

}
