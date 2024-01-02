package com.willbes.cmm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.willbes.cmm.LoginVO;

import egovframework.rte.fdl.string.EgovObjectUtil;

//import com.miraenet.cmm.service.EgovUserDetailsService;

/**
 * EgovUserDetails Helper 클래스
 *
 * @author sjyoon
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2009.03.10  sjyoon         최초 생성
 *   2011.07.01	 서준식          interface 생성후 상세 로직의 분리
 * </pre>
 */

public class EgovUserDetailsHelper {

    /*    static EgovUserDetailsService egovUserDetailsService;

    public EgovUserDetailsService getEgovUserDetailsService() {
        return egovUserDetailsService;
    }

    public void setEgovUserDetailsService(EgovUserDetailsService egovUserDetailsService) {
        EgovUserDetailsHelper.egovUserDetailsService = egovUserDetailsService;
    }

     *//**
     * 인증된 사용자객체를 VO형식으로 가져온다.
     * @return Object - 사용자 ValueObject
     *//*
    public static Object getAuthenticatedUser() {
        return egovUserDetailsService.getAuthenticatedUser();
    }

      *//**
      * 인증된 사용자의 권한 정보를 가져온다.
      * @return List - 사용자 권한정보 목록
      *//*
    public static List<String> getAuthorities() {
        return egovUserDetailsService.getAuthorities();
    }

       *//**
       * 인증된 사용자 여부를 체크한다.
       * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
       *//*
    public static Boolean isAuthenticated() {
        return egovUserDetailsService.isAuthenticated();
    }
        */

    /**
     * 인증된 사용자객체를 VO형식으로 가져온다.
     * @return Object - 사용자 ValueObject
     */
    public static Object getAuthenticatedUser() {
        return (LoginVO)RequestContextHolder.getRequestAttributes().getAttribute("LoginVO", RequestAttributes.SCOPE_SESSION)==null ?
                new LoginVO() : (LoginVO) RequestContextHolder.getRequestAttributes().getAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);

    }

    /**
     * 인증된 사용자의 권한 정보를 가져온다.
     * 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B, ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED, IS_AUTHENTICATED_ANONYMOUSLY]
     * @return List - 사용자 권한정보 목록
     */
    public static List<String> getAuthorities() {
        List<String> listAuth = new ArrayList<String>();

        if (EgovObjectUtil.isNull((LoginVO) RequestContextHolder.getRequestAttributes().getAttribute("LoginVO", RequestAttributes.SCOPE_SESSION))) {
            return null;
        }

        return listAuth;
    }

    /**
     * 인증된 사용자 여부를 체크한다.
     * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
     */
    public static Boolean isAuthenticated() {

        if (EgovObjectUtil.isNull((LoginVO) RequestContextHolder.getRequestAttributes().getAttribute("LoginVO", RequestAttributes.SCOPE_SESSION))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
