package web.adminManagement;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.util.CommonUtil;
import web.adminManagement.service.AdminManagementMenuService;

/**
 * @FileName   : AdminManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/adminManagementMenu")
@Controller
public class AdminManagementMenuController {

    @Autowired
    private AdminManagementMenuService adminManagementMenuService;

    /*
     * =============================================================================================
     * 메뉴 등록 트리
     * */
    /**
     * @Method Name  : adminMenuMain
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 메뉴 관리 iframe page
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/adminMenuMain.do")
    public String adminMenuMain(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();
        setParam(params, request, model);

        model.addAttribute("menuParams", params);
        return "/adminManagementMenu/adminMenuMain";
    }

    /**
     * @Method Name  : adminMenuMain
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 사용자 메뉴 관리 iframe page
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passMenuMain.do")
    public String passMenuMain(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();
        setParam(params, request, model);

        model.addAttribute("menuParams", params);
        return "/adminManagementMenu/passMenuMain";
    }

    /**
     * @Method Name  : menuTree
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 메뉴관리  -  메뉴 트리
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuTree.frm")
    public String menuTree(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();
        setParam(params, request, model);

        model.addAttribute("menuList",adminManagementMenuService.getMenuTree());
        return "/adminManagementMenu/Sg_Menu_Mst_Tree";
    }

    /**
     * @Method Name  : menuTree
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 사용자메뉴관리  -  사용자메뉴 트리
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passmenuTree.frm")
    public String passmenuTree(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();
        setParam(params, request, model);

        model.addAttribute("menuList",adminManagementMenuService.getpassMenuTree());
        return "/adminManagementMenu/Sg_Pass_Menu_Mst_Tree";
    }

    /**
     * @Method Name  : menuAdd
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :  메뉴관리 -  메뉴 수정
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuAdd.frm")
    public String menuAdd(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        HashMap<String, Object> resultMap  = new HashMap<String, Object>();
        String MENU_ID = request.getParameter("MENU_ID");
        String MENU_SEQ = request.getParameter("MENU_SEQ");
        String ONOFF_DIV = request.getParameter("ONOFF_DIV");
        if(MENU_ID!=null && MENU_ID!=""){
            params.put("MENU_ID", MENU_ID);
            params.put("MENU_SEQ", MENU_SEQ);
            resultMap.put("P_MENUID", MENU_ID);
            resultMap.put("ONOFF_DIV", ONOFF_DIV);

            HashMap<String, Object> maxMenuMap  = new HashMap<String, Object>();
            maxMenuMap =   adminManagementMenuService.getMaxMenuId(params);
            if(maxMenuMap == null){
                if(MENU_SEQ.equals("000")){ //Root
                    String temp = MENU_ID.split("_")[0];
                    String t_menu_id = temp+"_"+"001";
                    String t_seq = "001";
                    resultMap.put("MENU_ID", t_menu_id);
                    resultMap.put("MENU_SEQ", t_seq);
                }else{//
                    String temp = MENU_ID;
                    String t_menu_id = temp+"_"+"001";
                    String t_seq = MENU_SEQ+"_001";
                    resultMap.put("MENU_ID", t_menu_id);
                    resultMap.put("MENU_SEQ", t_seq);
                }
            } else {
                resultMap.put("MENU_ID", maxMenuMap.get("MENU_ID"));
                resultMap.put("MENU_SEQ", maxMenuMap.get("MENU_SEQ"));
            }
        }
        model.addAttribute("params", resultMap);
        return "/adminManagementMenu/Sg_Menu_Mst_Add";
    }

    /**
     * @Method Name  : menuAdd
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :  사용자메뉴관리 -  사용자메뉴 수정
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/pass_menuAdd.frm")
    public String pass_menuAdd(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        HashMap<String, Object> resultMap  = new HashMap<String, Object>();
        String MENU_ID = request.getParameter("MENU_ID");
        String MENU_SEQ = request.getParameter("MENU_SEQ");
        String ONOFF_DIV = request.getParameter("ONOFF_DIV");
        if(MENU_ID!=null && MENU_ID!=""){
            params.put("MENU_ID", MENU_ID);
            params.put("MENU_SEQ", MENU_SEQ);
            resultMap.put("P_MENUID", MENU_ID);
            resultMap.put("ONOFF_DIV", ONOFF_DIV);

            HashMap<String, Object> maxMenuMap  = new HashMap<String, Object>();
            maxMenuMap =   adminManagementMenuService.getpassMaxMenuId(params);
            if(maxMenuMap == null){
                if(MENU_SEQ.equals("000")){ //Root
                    String temp = MENU_ID.split("_")[0];
                    String t_menu_id = temp+"_"+"001";
                    String t_seq = "001";
                    resultMap.put("MENU_ID", t_menu_id);
                    resultMap.put("MENU_SEQ", t_seq);
                }else{//
                    String temp = MENU_ID;
                    String t_menu_id = temp+"_"+"001";
                    String t_seq = MENU_SEQ+"_001";
                    resultMap.put("MENU_ID", t_menu_id);
                    resultMap.put("MENU_SEQ", t_seq);
                }
            } else {
                resultMap.put("MENU_ID", maxMenuMap.get("MENU_ID"));
                resultMap.put("MENU_SEQ", maxMenuMap.get("MENU_SEQ"));
            }
        }
        model.addAttribute("params", resultMap);
        return "/adminManagementMenu/Sg_Pass_Menu_Mst_Add";
    }

    /**
     * @Method Name  : menuDetai
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	메뉴 관리 - 메뉴 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuDetail.frm")
    public String menuDetai(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        params.put("MENU_ID", request.getParameter("MENU_ID"));

        model.addAttribute("detail",adminManagementMenuService.getDetailMenu(params));

        model.addAttribute("params", params);
        return "/adminManagementMenu/Sg_Menu_Mst_detail";
    }

    /**
     * @Method Name  : menuDetai
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	사용자메뉴 관리 - 사용자메뉴 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passmenuDetail.frm")
    public String passmenuDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);
        params.put("MENU_ID", request.getParameter("MENU_ID"));
        model.addAttribute("detail",adminManagementMenuService.getpassDetailMenu(params));

        model.addAttribute("params", params);
        return "/adminManagementMenu/Sg_Pass_Menu_Mst_detail";
    }

    /**
     * @Method Name  : menuIdCheck
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	메뉴 중복확인
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuIdCheck.pop")
    public String menuIdCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        params.put("MENU_ID",request.getParameter("MENU_ID"));
        //메뉴 업데이트
        int idCount = adminManagementMenuService.menuIdCheck(params);

        HashMap<String, Object> result = new  HashMap<String, Object>();
        String script = "javascript:self.close();";
        String message = "";
        if(idCount  > 0){
            message = "☞ 중복된 메뉴ID입니다. 다른 메뉴ID를 사용하여 등록해주십시요";
        }else{
            message = "☞ 등록가능한 메뉴ID입니다";
        }
        result.put("script", script);
        result.put("message", message);
        model.addAttribute("result", result);

        return "/adminManagementMenu/checkIdPop";
    }

    /**
     * @Method Name  : menuInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 메뉴관리 - 메뉴등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuInsertProcess.json")
    @ResponseBody
    public HashMap<String, Object> menuInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV"));
        params.put("MENU_ID",request.getParameter("menu_id"));
        params.put("MENU_NM",request.getParameter("menu_nm"));
        params.put("MENU_SEQ",request.getParameter("menu_seq"));
        params.put("P_MENUID",request.getParameter("p_menuid"));
        params.put("MENU_URL",request.getParameter("menu_url"));
        params.put("TARGET",request.getParameter("target"));
        params.put("ISUSE",request.getParameter("isuse"));
        params.put("MENU_INFO",request.getParameter("menu_info"));

        int isInsert = adminManagementMenuService.menuInsertProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isInsert",isInsert );

        return result;
    }

    /**
     * @Method Name  : menuInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 사용자메뉴관리 - 사용자메뉴등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passmenuInsertProcess.json")
    @ResponseBody
    public HashMap<String, Object> passmenuInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV"));
        params.put("MENU_ID",request.getParameter("menu_id"));
        params.put("MENU_NM",request.getParameter("menu_nm"));
        params.put("MENU_SEQ",request.getParameter("menu_seq"));
        params.put("P_MENUID",request.getParameter("p_menuid"));
        params.put("MENU_URL",request.getParameter("menu_url"));
        params.put("TARGET",request.getParameter("TARGET"));
        params.put("CODE",request.getParameter("CODE"));
        params.put("ISUSE",request.getParameter("isuse"));
        params.put("MENU_INFO",request.getParameter("menu_info"));
        params.put("TOP_IMG_URL",request.getParameter("TOP_IMG_URL"));
        params.put("LEFT_IMG_URL",request.getParameter("LEFT_IMG_URL"));
        params.put("TITL_IMG_URL",request.getParameter("TITL_IMG_URL"));
        params.put("SUBTITL_IMG_URL",request.getParameter("SUBTITL_IMG_URL"));

        int isInsert = adminManagementMenuService.passmenuInsertProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isInsert",isInsert );
        return result;
    }

    /**
     * @Method Name  : menuUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :    메뉴 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuUpdateProcess.json")
    @ResponseBody
    public HashMap<String, Object> menuUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("MENU_ID",request.getParameter("MENU_ID"));
        params.put("MENU_NM",request.getParameter("menu_nm"));
        params.put("MENU_SEQ",request.getParameter("menu_seq"));
        params.put("P_MENUID",request.getParameter("p_menuid"));
        params.put("MENU_URL",request.getParameter("menu_url"));
        params.put("TARGET",request.getParameter("target"));
        params.put("ISUSE",request.getParameter("isuse"));
        params.put("MENU_INFO",request.getParameter("menu_info"));

        adminManagementMenuService.menuUpdateProcess(params);
        model.addAttribute("params",params);

        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isUpdate", "1");
        result.put("VWMENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));

        return result;
    }

    /**
     * @Method Name  : menuUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :    메뉴 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passmenuUpdateProcess.json")
    @ResponseBody
    public HashMap<String, Object> PassmenuUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("MENU_ID",request.getParameter("MENU_ID"));
        params.put("MENU_NM",request.getParameter("menu_nm"));
        params.put("MENU_SEQ",request.getParameter("menu_seq"));
        params.put("P_MENUID",request.getParameter("p_menuid"));
        params.put("MENU_URL",request.getParameter("menu_url"));
        params.put("TARGET",request.getParameter("TARGET"));
        params.put("CODE",request.getParameter("CODE"));
        params.put("ISUSE",request.getParameter("isuse"));
        params.put("MENU_INFO",request.getParameter("menu_info"));
        params.put("TOP_IMG_URL",request.getParameter("TOP_IMG_URL"));
        params.put("LEFT_IMG_URL",request.getParameter("LEFT_IMG_URL"));
        params.put("TITL_IMG_URL",request.getParameter("TITL_IMG_URL"));
        params.put("SUBTITL_IMG_URL",request.getParameter("SUBTITL_IMG_URL"));

        //메뉴 업데이트
        adminManagementMenuService.PassmenuUpdateProcess(params);
        model.addAttribute("params",params);

        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isUpdate", "1");
        result.put("VWMENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));

        return result;
    }

    /**
     * @Method Name  : menuDeleteProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :    메뉴 삭제 process- Ajax
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/menuDeleteProcess.json")
    @ResponseBody
    public HashMap<String, Object> menuDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);
        params.put("MENU_ID",request.getParameter("MENU_ID"));

        int isDelete = adminManagementMenuService.menuDeleteProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isDelete",isDelete );
        return result;
    }

    /**
     * @Method Name  : menuDeleteProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :    메뉴 삭제 process- Ajax
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/passmenuDeleteProcess.json")
    @ResponseBody
    public HashMap<String, Object> passmenuDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);
        params.put("MENU_ID",request.getParameter("MENU_ID"));

        int isDelete = adminManagementMenuService.passmenuDeleteProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isDelete",isDelete );
        return result;
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
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("USERNAME", loginInfo.get("USER_NM"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        model.addAttribute("VWMENU_ID", CommonUtil.isNull(request.getParameter("VWMENU_ID"), ""));
    }

}
