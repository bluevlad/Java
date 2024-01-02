package com.willbes.platform.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ( request.getSession().getAttribute("AdmUserInfo") == null) {
            logger.debug("CHECK ADMIN SESSION - FAILURE");
            response.sendRedirect(request.getContextPath() + "/login/login.adm");  //구 시스템 수정
            //response.sendRedirect(request.getContextPath() + "/login/loginUsr.do");  //신규 시스템
            return false;
        }

        logger.debug("CHECK ADMIN SESSION - TRUE");

        return true;
	}
}