package com.willbes.cmm.interceptor;

import com.willbes.cmm.LoginVO;
import com.willbes.cmm.util.EgovUserDetailsHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  2014.09.19  kckim          URL 경로 변경 (redirect:/login/egovLoginUsr.do -> redirect:/login/loginUsr.do)
 *  </pre>
 */


public class AuthenticInterceptor extends HandlerInterceptorAdapter {

    /**
     * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
     * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isPermittedURL = false;

        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if(loginVO != null){
            return true;
        } else if(!isPermittedURL){
            ModelAndView modelAndView = new ModelAndView("redirect:/login/loginUsr.do");
            throw new ModelAndViewDefiningException(modelAndView);
        }else{
            return true;
        }
    }

}
