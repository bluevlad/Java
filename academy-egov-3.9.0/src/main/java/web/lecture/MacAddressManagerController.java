package web.lecture;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.lecture.service.MacAddressManagerService;

import egovframework.rte.fdl.property.EgovPropertyService;
	
@RequestMapping(value="/macaddressmanager")
@Controller
public class MacAddressManagerController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")	
    protected EgovPropertyService propertiesService;

    @Autowired
    private MacAddressManagerService macaddressmanagerservice;

    /**		
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list_be.do")
    public String list_be(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        return "/lecture/macaddressmanager/list_be";
    }

    /**		
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list_all.do")
    public String list_all(ModelMap model, HttpServletRequest request) throws Exception {
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

        List<HashMap<String, String>> list = macaddressmanagerservice.MacAdrUserList(params);
        int listCount = macaddressmanagerservice.MacAdrUserCount(params);

        List<HashMap<String, String>> list_all = macaddressmanagerservice.MacAdrUserAllList(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("list_all", list_all);
        return "/lecture/macaddressmanager/list_all";
    }

    /**		
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
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

        List<HashMap<String, String>> list = macaddressmanagerservice.macaddressmanagerList(params);
        int listCount = macaddressmanagerservice.macaddressmanagerListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/lecture/macaddressmanager/list";
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/macaddressUpdate.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String macaddressUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        macaddressmanagerservice.macaddressmanagerUpdate(params);
		model.addAttribute("params", params); 

        return "redirect:/macaddressmanager/list_all.do?SEARCHTEXT="+params.get("SEARCHTEXT");
    }
    /**
     * 모바일 초기화
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/macaddressUpdate1.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String macaddressUpdate1(ModelMap model, HttpServletRequest request) throws Exception {
    	HashMap<String, String> params = new  HashMap<String, String>();
    	setParam(params, request, model);
    	macaddressmanagerservice.macaddressmanagerUpdate1(params);
    	model.addAttribute("params", params);
    	
    	return "redirect:/macaddressmanager/list_all.do?SEARCHTEXT="+params.get("SEARCHTEXT");
    }
    @RequestMapping(value="/Pop_macaddressUpdate.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String pop_macaddressUpdate(ModelMap model, HttpServletRequest request) throws Exception {
    	HashMap<String, String> params = new  HashMap<String, String>();
    	setParam(params, request, model);
    	macaddressmanagerservice.macaddressmanagerUpdate(params);
    	
    	
    	
    	return "redirect:/macaddressmanager/devicelist.pop";
    }
    
    /**
     * 팝업 모바일 초기화
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/Pop_macaddressUpdate1.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String pop_macaddressUpdate1(ModelMap model, HttpServletRequest request) throws Exception {
    	HashMap<String, String> params = new  HashMap<String, String>();
    	setParam(params, request, model);
    	macaddressmanagerservice.macaddressmanagerUpdate1(params);
    	
    	
    	
    	return "redirect:/macaddressmanager/devicelist.pop";
    }
    
 // 사용자 팝업
 	@RequestMapping(value="/devicelist.pop")
 	@Transactional(readOnly=false, rollbackFor=Exception.class)
 	public String devicelist(ModelMap model, HttpServletRequest request) throws Exception {
 		HashMap<String, String> params = new HashMap<String, String>();
 		
 		setParam(params, request, model);
 		List<HashMap<String, String>> list = macaddressmanagerservice.devicelist(params);
 		List<HashMap<String, String>> view = macaddressmanagerservice.macaddressView(params);
 		
 		model.addAttribute("list", list);
 		model.addAttribute("view", view);
 		
 		return "/lecture/macaddressmanager/devicelist";
 	}

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/MacAdrUserUpdate.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String MacAdrUserUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        
        macaddressmanagerservice.MacAdrUserUpdate(params);
		model.addAttribute("params", params); 

        return "redirect:/macaddressmanager/list_all.do?SEARCHTEXT="+params.get("SEARCHTEXT");
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
    public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
        params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("V_USER_ID", CommonUtil.isNull(request.getParameter("V_USER_ID"), ""));
        params.put("SEARCHGUBN", "T");
        model.addAttribute("SEQ", params.get("SEQ"));
        
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

}
