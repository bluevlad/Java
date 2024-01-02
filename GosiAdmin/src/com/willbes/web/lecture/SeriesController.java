package com.willbes.web.lecture;

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

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.lecture.service.KindService;
import com.willbes.web.lecture.service.SeriesService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/series")
@Controller
public class SeriesController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private KindService kindservice;

    /**
     * @Method Name : list
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage").toString());
        int pageRow = Integer.parseInt(params.get("pageRow").toString());
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = seriesService.seriesList(params);
        int listCount = seriesService.seriesListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

        model.addAttribute("params", params);

        return "/lecture/series/list";
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2015. 05.
     * @Method 설명 : 목록에서 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/listDelete.do")
    public String listDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        params.put("SRS_CDs", DEL_ARR);

        seriesService.seriesDelete(params);

        return "forward:/series/list.do";
    }

    /**
     * @Method Name : write
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 등록폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/write.do")
    public String write(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        List<HashMap<String, String>> list = kindservice.selectKindCode();
        model.addAttribute("cat_list", list);

        model.addAttribute("params", params);

        return "/lecture/series/write";
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2015. 05.
     * @Method 설명 : 코드중복체크
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/codeCheck.do")
    @ResponseBody
    public String codeCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        int listCount = seriesService.seriesCheck(params);
        String returnStr = "Y";
        if(listCount > 0)
            returnStr = "N";
        return returnStr;
    }

    /**
     * @Method Name : save
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/save.do")
    public String save(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        params.put("CAT_CDs", CAT_CDs);

        seriesService.seriesInsert(params);

        return "redirect:/series/list.do";
    }

    /**
     * @Method Name : modify
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 수정폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/modify.do")
    public String modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        List<HashMap<String, String>> list = seriesService.seriesView(params);
        model.addAttribute("list", list);

        List<HashMap<String, String>> cat_list = kindservice.selectKindCode();
        model.addAttribute("cat_list", cat_list);

        model.addAttribute("params", params);

        return "/lecture/series/modify";
    }

    /**
     * @Method Name : update
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/update.do")
    public String update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        params.put("CAT_CDs", CAT_CDs);

        seriesService.seriesUpdate(params);

        return "forward:/series/list.do";
    }

    /**
     * @Method Name : delete
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/delete.do")
    public String delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        String[] DEL_ARR = {params.get("SRS_CD").toString()};
        params.put("SRS_CDs", DEL_ARR);

        seriesService.seriesDelete(params);

        return "forward:/series/list.do";
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 05.
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

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        params.put("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "S"));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHCODE", CommonUtil.isNull(request.getParameter("SRS_CD"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));

        params.put("SRS_CD", CommonUtil.isNull(request.getParameter("SRS_CD"), ""));
        params.put("SRS_NM", CommonUtil.isNull(request.getParameter("SRS_NM"), ""));
        params.put("ORDR", CommonUtil.isNull(request.getParameter("ORDR"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
    }

}
