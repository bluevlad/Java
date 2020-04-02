package web.index;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.util.CommonUtil;
import web.index.service.IndexService;
import web.login.service.LoginService;

/**
 * @FileName   : IndexController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 10. 29.
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 :	메뉴 구성
 */
@Controller
@RequestMapping(value="/main")
public class IndexController {

    @Autowired
    private IndexService indexservice;
    @Autowired
    private LoginService loginservice;

    /**
     *	 LEFT 메뉴의 기본 MENU_NM
     */
    private String defaultLeftName;
    /**
     *	 LEFT 메뉴의 기본 MENU_ID
     */
    private String defaultTopMenuId;

    /**
     * @Method Name  : index
     * @Date         : 2013. 10. 29.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : blank 페이지
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="index.do")
    public String index(ModelMap model, HttpServletRequest request) throws Exception {
    	
		String ST = CommonUtil.isNull(request.getParameter("ST"), "");
        
        HashMap<String, String> params = new  HashMap<String, String>();

        HashMap<String, String> sessionMap = (HashMap<String, String>)request.getSession().getAttribute("AdmUserInfo");
        String userId = sessionMap.get("USER_ID");

        params.put("USERID", userId);
        params.put("LOG_CNT", "11");

		List<HashMap<String, String>> log_ip = loginservice.login_ip(params);

        model.addAttribute("log_ip" , log_ip);
        model.addAttribute("ST", ST);
        model.addAttribute("USER_IP" , request.getRemoteAddr());

        return "main/index";
    }

    @RequestMapping(value="index_v2.do")
    public String index_v2(ModelMap model, HttpServletRequest request) throws Exception {
        return "main/index_v2";
    }

    /**
     * @Method Name  : top
     * @Date         : 2013. 10. 29.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : TOP 메뉴 구성
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="top.do")
    public String top(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        HashMap<String, Object> params2 = new  HashMap<String, Object>();
        HashMap<String, Object> params3 = new  HashMap<String, Object>();

        params.put("MENUTYPE","OM_ROOT");
        params2.put("MENUTYPE","FM_ROOT");
        params3.put("MENUTYPE","TM_ROOT");

        HashMap<String, String> sessionMap = (HashMap<String, String>)request.getSession().getAttribute("AdmUserInfo");
        String userName = sessionMap.get("USER_NM");
        String userId = sessionMap.get("USER_ID");
        String adminRore = sessionMap.get("ADMIN_ROLE");
        String onoff_div = sessionMap.get("ONOFF_DIV");

        params.put("USERNAME", userName);
        params.put("USERID", userId);
        params.put("ADMIN_ROLE", adminRore);

        params2.put("USERNAME", userName);
        params2.put("USERID", userId);
        params2.put("ADMIN_ROLE", adminRore);

        params3.put("USERNAME", userName);
        params3.put("USERID", userId);
        params3.put("ADMIN_ROLE", adminRore);

        List<HashMap<String, Object>> topMenuList =  null;
        List<HashMap<String, Object>> topOffMenuList =  null;
        List<HashMap<String, Object>> topTstMenuList =  null;

        topMenuList = indexservice.getTopMenuList(params);
        topOffMenuList = indexservice.getTopMenuList(params2);
        topTstMenuList = indexservice.getTopMenuList(params3);

        String menuType = CommonUtil.isNull(request.getParameter("MENUTYPE"), (String)request.getAttribute("MENUTYPE"));

        String t  = "";
        String t_name  = "";
        String t_off  = "";
        String t_off_name  = "";
        String t_test  = "";
        String t_test_name  = "";

        if(topMenuList.size() > 0 && topMenuList != null){
            t  = String.valueOf(topMenuList.get(0).get("MENU_ID"));
            t_name  = String.valueOf(topMenuList.get(0).get("MENU_NM"));
        }
        if(topOffMenuList.size() > 0 && topOffMenuList != null){
            t_off  = String.valueOf(topOffMenuList.get(0).get("MENU_ID"));
            t_off_name  = String.valueOf(topOffMenuList.get(0).get("MENU_NM"));
        }
        if(topTstMenuList.size() > 0 && topTstMenuList != null){
            t_test  = String.valueOf(topTstMenuList.get(0).get("MENU_ID"));
            t_test_name  = String.valueOf(topTstMenuList.get(0).get("MENU_NM"));
        }

        if(menuType == null) {
            if( onoff_div.equals("A") || onoff_div.equals("O") ) {
                if(topMenuList.size() > 0 && topMenuList != null){
                    model.addAttribute("topMenuList", topMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params.get("MENUTYPE") );
                    defaultTopMenuId = t;
                    defaultLeftName = t_name;
                }
            } else if(onoff_div.equals("F") ) {
                if(topOffMenuList.size() > 0 && topOffMenuList != null){
                    model.addAttribute("topMenuList", topOffMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params2.get("MENUTYPE") );
                    defaultTopMenuId = t_off;
                    defaultLeftName = t_off_name;
                }
            } else if(onoff_div.equals("T") ) {
                if(topTstMenuList.size() > 0 && topTstMenuList != null){
                    model.addAttribute("topMenuList", topTstMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params3.get("MENUTYPE") );
                    defaultTopMenuId = t_test;
                    defaultLeftName = t_test_name;
                }
            }
        } else {
            if(menuType.equals("OM_ROOT")) {
                if(topMenuList.size() > 0 && topMenuList != null){
                    model.addAttribute("topMenuList", topMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params.get("MENUTYPE") );
                    defaultTopMenuId = t;
                    defaultLeftName = t_name;
                }
            } else if(menuType.equals("FM_ROOT")) {
                if(topOffMenuList.size() > 0 && topOffMenuList != null){
                    model.addAttribute("topMenuList", topOffMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params2.get("MENUTYPE") );
                    defaultTopMenuId = t_off;
                    defaultLeftName = t_off_name;
                }
            } else if(menuType.equals("TM_ROOT")) {
                if(topTstMenuList.size() > 0 && topTstMenuList != null){
                    model.addAttribute("topMenuList", topTstMenuList);
                    model.addAttribute("TOP_MENU_ID",t );
                    model.addAttribute("TOP_MENU_ID_OFF",t_off );
                    model.addAttribute("TOP_MENU_ID_TST",t_test );
                    model.addAttribute("MENUTYPE",params3.get("MENUTYPE") );
                    defaultTopMenuId = t_test;
                    defaultLeftName = t_test_name;
                }
            }
        }

        model.addAttribute("ONOFF_DIV", onoff_div);
        return "main/top";
    }

    /**
     * @Method Name  : left
     * @Date         : 2015. 04. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	LEFT 메뉴 구성/ LEFT 메뉴 트리 구성으로 변경
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="left.do")
    public String left(ModelMap model, HttpServletRequest request) throws Exception {

        String topMenuId = CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), (String)request.getAttribute("TOP_MENU_ID"));
        String topMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"), (String)request.getAttribute("MENUTYPE"));
        String topMenuName = CommonUtil.isNull(request.getParameter("L_MENU_NM"), (String)request.getAttribute("L_MENU_NM"));
        String MENU_ID = CommonUtil.isNull(request.getParameter("MENU_ID"), (String)request.getAttribute("MENU_ID"));

        HashMap<String, String> sessionMap = (HashMap<String, String>)request.getSession().getAttribute("AdmUserInfo");
        String userName = sessionMap.get("USER_NM");
        String userId = sessionMap.get("USER_ID");
        String adminRole = sessionMap.get("ADMIN_ROLE");
        String onoff_div = sessionMap.get("ONOFF_DIV");

        HashMap<String, Object> params = new  HashMap<String, Object>();
        params.put("TOP_MENU_ID", CommonUtil.isNull(topMenuId, defaultTopMenuId));

        if( onoff_div.equals("A") || onoff_div.equals("O")){
            params.put("MENUTYPE", CommonUtil.isNull(topMenuType, "OM_ROOT"));
        } else if(onoff_div.equals("F")){
            params.put("MENUTYPE", CommonUtil.isNull(topMenuType, "FM_ROOT"));
        } else if(onoff_div.equals("T")){
            params.put("MENUTYPE", CommonUtil.isNull(topMenuType, "TM_ROOT"));
        }

        params.put("USERNAME", userName);
        params.put("USERID", userId);
        params.put("ADMIN_ROLE", adminRole);

        List<HashMap<String, Object>> leftMenuList =  null;
        //leftMenuList = indexservice.getLeftMenuList(params);
        leftMenuList = indexservice.selectLeftMenuTree(params);
        if(leftMenuList.size()  > 0 && leftMenuList != null){
            params.put("L_MENU_NM", CommonUtil.isNull(topMenuName, defaultLeftName));
            model.addAttribute("leftMenuList", leftMenuList);
            model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
            model.addAttribute("MENU_ID", MENU_ID);
            model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
            model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        }

        return "main/left";
    }

    @RequestMapping(value="footer.do")
    public String footer(ModelMap model, HttpServletRequest request) throws Exception {
        return "main/footer";
    }
}
