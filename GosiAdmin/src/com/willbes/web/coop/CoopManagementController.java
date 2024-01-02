package com.willbes.web.coop;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.coop.service.CoopManagementService;

/**
 * @FileName   : CoopManagementController.java
 * @Date       : 2017. 12.
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 제휴사관리
 */
@RequestMapping(value="/CoopManagement")
@Controller
public class CoopManagementController {

    @Autowired
    private CoopManagementService  CoopManagementService;

    /**
     * @Method Name  : CoopList
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	제휴사 조회
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/CoopList.do")
    public String CoopList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        List list = CoopManagementService.CoopList(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/coop/CoopList";
    }

    /**
     * @Method Name  : CoopInsertProcess
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴사 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/CoopInsertProcess.do")
    @Transactional( readOnly=false,  rollbackFor=Exception.class)
    public String CoopInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD"), ""));
        params.put("COOP_NM", CommonUtil.isNull(request.getParameter("COOP_NM"), ""));
        params.put("DISCOUNT_PER", CommonUtil.isNull(request.getParameter("DISCOUNT_PER"), ""));
        params.put("COOP_DESC", CommonUtil.isNull(request.getParameter("COOP_DESC"), ""));

        //제휴사 등록
        CoopManagementService.CoopInsertProcess(params);
        
        model.addAttribute("params", params);

        return "redirect:/CoopManagement/CoopList.do";
    }

    /**
     * @Method Name  : CoopUpdateProcess
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴사 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/CoopUpdateProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String CoopUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        String[] V_COOP_CD = request.getParameterValues("V_COOP_CD");
        
		if(V_COOP_CD != null){
			for(int i=0; i<V_COOP_CD.length; i++){																	// 주교재 루프
				params.put("COOP_CD", V_COOP_CD[i]);
		        params.put("COOP_NM", request.getParameter("COOP_NM_"+V_COOP_CD[i]));
				params.put("DISCOUNT_PER", request.getParameter("DISCOUNT_PER_"+V_COOP_CD[i]));
				params.put("COOP_DESC", request.getParameter("COOP_DESC_"+V_COOP_CD[i]));
				//제휴사 정보 수정
				CoopManagementService.CoopUpdateProcess(params);
			}
		}

        model.addAttribute("params",params);

        return "forward:/CoopManagement/CoopList.do";
    }

    /**
     * @Method Name  : CoopIpList
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	제휴사 조회
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/CoopIpList.pop")
    public String CoopIpList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD"), ""));
        List list = CoopManagementService.CoopIpList(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/coop/CoopIpList_pop";
    }

    /**
     * @Method Name  : CoopIpInsertProcess
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴사 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/CoopIpInsertProcess.do")
    @Transactional( readOnly=false,  rollbackFor=Exception.class)
    public String CoopIpInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD"), ""));
        params.put("USER_IP", CommonUtil.isNull(request.getParameter("USER_IP"), ""));

        //제휴사 등록
        CoopManagementService.CoopIpInsertProcess(params);
        
        model.addAttribute("params", params);

        return "redirect:/CoopManagement/CoopIpList.pop";
    }

    /**
     * @Method Name  : CoopIpDeleteProcess
     * @Date         : 2017. 12.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 제휴사 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/CoopIpDeleteProcess.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String CoopIpDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD"), ""));
        params.put("O_USER_IP", CommonUtil.isNull(request.getParameter("O_USER_IP"), ""));
        
		CoopManagementService.CoopIpDeleteProcess(params);

        model.addAttribute("params",params);

        return "forward:/CoopManagement/CoopIpList.pop";
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
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("USER_ID", loginInfo.get("USER_ID"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));

    }
}
