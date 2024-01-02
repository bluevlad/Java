package com.willbes.web.adminManagement;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.adminManagement.service.AdminManagementCodeService;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @FileName   : AdminManagementCodeController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2016. 08. 07.
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/adminManagementCode")
@Controller
public class AdminManagementCodeController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private AdminManagementCodeService adminManagementCodeService;

	
	@RequestMapping(value="/passCodeMain.do")
    public String passMenuMain(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();
		
		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		
		model.addAttribute("VWMENU_ID", CommonUtil.isNull(request.getParameter("VWMENU_ID"), ""));
        model.addAttribute("menuParams", params);
        return "/adminManagementCode/passCodeMain";
    }
    
	@RequestMapping(value="/passcodeTree.frm") 
    public String passmenuTree(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String,  Object>();

        model.addAttribute("codeList",adminManagementCodeService.getpassCodeTree());
        return "/adminManagementCode/Sg_Pass_Code_Mst_Tree";
    }
    
    @RequestMapping(value="/passcodeDetail.frm")
    public String passmenuDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("SYS_CD", request.getParameter("SYS_CD"));
        params.put("CODE_VAL", request.getParameter("CODE_VAL"));

        model.addAttribute("detail",adminManagementCodeService.getpassDetailCode(params));

        model.addAttribute("params", params);
        return "/adminManagementCode/Sg_Pass_Code_Mst_detail";
    }
    
    @RequestMapping(value="/pass_codeAdd.frm")
    public String pass_menuAdd(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        HashMap<String, Object> resultMap  = new HashMap<String, Object>();
        params.put("CODE_VAL", request.getParameter("CODE_VAL"));
        
        resultMap = adminManagementCodeService.getpassMaxCodeId(params);

        model.addAttribute("params", params);
        model.addAttribute("resultMap", resultMap);
        return "/adminManagementCode/Sg_Pass_Code_Mst_Add";
    }
    
    @RequestMapping(value="/passcodeUpdateProcess.json")
    @ResponseBody
    public HashMap<String, Object> PassmenuUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		
		HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("SYS_CD",request.getParameter("SYS_CD"));				// 공통코드 
        params.put("CODE_SEQ",request.getParameter("CODE_SEQ"));
        params.put("CODE_VAL",request.getParameter("CODE_VAL"));	// 설정코드
        params.put("CODE_NM",request.getParameter("CODE_NM"));		// 설정코드명
        params.put("CODE_INFO",request.getParameter("CODE_INFO"));	// 코드설명
        params.put("ISUSE",request.getParameter("ISUSE"));					// 상태
        
        //코드 업데이트
        adminManagementCodeService.PasscodeUpdateProcess(params);
        model.addAttribute("params",params);

        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isUpdate", "1");

        return result;
    }

    @RequestMapping(value="/passcodeInsertProcess.json")
    @ResponseBody
    public HashMap<String, Object> passmenuInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        
        params.put("SYS_CD", request.getParameter("SYS_CD"));
        params.put("CODE_NO", request.getParameter("CODE_NO"));
        params.put("CODE_VAL", request.getParameter("CODE_VAL"));
        params.put("CODE_NM", request.getParameter("CODE_NM"));
        params.put("CODE_INFO", request.getParameter("CODE_INFO"));
        params.put("ISUSE", request.getParameter("ISUSE"));
        params.put("REG_ID", loginInfo.get("USER_ID"));
        
        int isInsert = adminManagementCodeService.passcodeInsertProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isInsert",isInsert );
        return result;
    }

    @RequestMapping(value="/passcodeDeleteProcess.json")
    @ResponseBody
    public HashMap<String, Object> passmenuDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();

        params.put("SYS_CD",request.getParameter("SYS_CD"));				// 공통코드 
        params.put("CODE_VAL",request.getParameter("CODE_VAL"));	// 설정코드

        int isDelete = adminManagementCodeService.passcodeDeleteProcess(params);
        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isDelete",isDelete );
        return result;
    }

}