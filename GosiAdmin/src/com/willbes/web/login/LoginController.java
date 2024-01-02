package com.willbes.web.login;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.web.login.service.LoginService;

@RequestMapping(value="/login")
@Controller
public class LoginController {
    @Autowired
    private LoginService loginservice;

    /**
     * @Method Name : login
     * @작성일 : 2013. 9.
     * @Method 설명 : 로그인폼
     * @param request
     * @return
     */
    @RequestMapping(value="/login.adm")
    public String login(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        params.put("login_chk","Y");
        return "/login/login";
    }

    /**
     * @Method Name : loginIP
     */
    @RequestMapping(value="/loginIP")
    @ResponseBody
    public String loginIP(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();

        params.put("USERID", CommonUtil.isNull(request.getParameter("USERID"), ""));
        params.put("USER_IP", request.getRemoteAddr());
        
        List<HashMap<String, String>> isLogin = loginservice.login(params);
        if (isLogin.size() == 0) {
            return "E"; //회원정보가 없을경우
        }else{
            HashMap<String, String> login = isLogin.get(0);
            params.put("LOG_CNT", "2");
            params.put("isAdmin", "Y");
            List<HashMap<String, String>> result = loginservice.login_ip(params);
            if (result.size() > 0) { //기존아이피로 로그인되었을경우
                return "Y";
            }else{ //새로운 아이피로 로그인되었을경우
                return "N";
            }
        }

    }

    /**
     * @Method Name : loginSMS
     * @작성일 : 2013. 9.
     * @Method 설명 : 로그인
     * @param request
     * @return
     */
    @RequestMapping(value="/loginSMS")
    public String loginSMS(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();

        params.put("USERID", CommonUtil.isNull(request.getParameter("USERID"), ""));
        params.put("PSWD", CommonUtil.isNull(request.getParameter("PSWD"), ""));

        List<HashMap<String, String>> result = loginservice.login(params);
        if (result.size() > 0) {
            HashMap<String, String> login = result.get(0);

            Random rnd = new Random(System.currentTimeMillis());
            String code  = "";
            for(int i=0 ; i < 5 ; i++){
                int a = rnd.nextInt(9)+1;
                code += String.valueOf(a);
            }

            String message = "관리자 인증센터입니다. 인증번호[" +code+"]를 정확히 입력해 주십시오.";
            params.put("MESSAGE", message);
            params.put("PHONE_NO", login.get("PHONE_NO"));
            params.put("COMPANY_NO", "");

            HttpSession session = request.getSession();
            session.setAttribute("SMSCODE", code);
            session.setAttribute("I_USERID", params.get("USERID"));
            session.setAttribute("i_PSWD", params.get("PSWD"));

            loginservice.insertSMS(params);

            model.addAttribute("params" , params);
            //TEST 용, SMS 연동 시 제거
            model.addAttribute("SMSCODE" , code);
        }

        return "/login/loginSMS_pop";
    }

    @RequestMapping(value="/loginPhoneCheck")
    @ResponseBody
    public String loginPhoneCheck(ModelMap model, HttpServletRequest request) throws Exception {
        String code = CommonUtil.isNull(request.getParameter("smsCode"), "");
        HttpSession session = request.getSession();
        String SMSCODE = (String) session.getAttribute("SMSCODE");

        if(code.equals(SMSCODE)){
            return "Y";
        }else{
            return "N";
        }
    }

    /**
     * @Method Name : loginProc
     * @작성일 : 2013. 9.
     * @Method 설명 : 로그인
     * @param request
     * @return
     */
    @RequestMapping(value="/loginProc.adm")
    public String loginProc(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        HttpSession session = request.getSession();

        params.put("USERID", CommonUtil.isNull(request.getParameter("USERID"), (String) session.getAttribute("I_USERID")));
        params.put("PSWD", CommonUtil.isNull(request.getParameter("PSWD"), (String) session.getAttribute("i_PSWD")));
        params.put("USER_IP", request.getRemoteAddr());

        List<HashMap<String, String>> result = loginservice.login(params);
        String returnUrl = "";

        if(result.size() > 0){	// 로그인 성공
            session.setAttribute("AdmUserInfo", result.get(0));
            session.setAttribute("LoginYn", "Y");

            //관리자 로그인 기록
            loginservice.mbAccessInsert(params);

            returnUrl = "/main/index.do?ST=Y";	// blank 페이지로 이동
        }else{					// 로그인 실패
            session.setAttribute("LoginYn", "N");
            returnUrl = "/login/login.adm";
        }
        return "redirect:" + returnUrl;
    }

    /**
     * @Method Name : Logout
     * @작성일 : 2013. 9.
     * @Method 설명 : 로그아웃
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate();
        return "redirect:/login/login.adm";
    }
}
