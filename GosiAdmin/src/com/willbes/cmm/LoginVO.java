package com.willbes.cmm;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2014.09.18    kckim          최초 생성
 *
 *  @author kckim
 *  @since 2014.09.18
 *  @version 1.0
 *  @see
 *
 */
public class LoginVO implements Serializable {

    private static final long serialVersionUID = -8274004534207618049L;

    /** 아이디 */
    private String USER_ID;
    /** 이름 */
    private String USER_NM;
    /** 이메일주소 */
    private String EMAIL;
    /** 비밀번호 */
    private String USER_PWD;
    /** 사용자구분 */
    private String USER_SE;
    /** 고유아이디 */
    private String UNIQ_ID;
    /** 사용자 역할 */
    private String USER_ROLE;
    /** 운영자 역할 */
    private String ADMIN_ROLE;
    /** 온오프구분 */
    private String ONOFF_DIV;
    /** 별명 */
    private String NICKNM;
    /** 역할명 */
    private String ROLE_NM;
    /** 인덱스 메뉴 아이디*/
    private String INDX_MENU_ID;
    /** 상위(Root) 메뉴 아이디: OM_ROOT 또는 FM_ROOT*/
    private String P_MENUID;
    /** 인텍스 메뉴 URL*/
    private String INDX_MENU_URL;
    private String USER_IP;

    /** 로그인 후 이동할 페이지 */
    private String url;
    /** 사용자 IP정보 */
    private String ip;
    /** GPKI인증 DN */
    private String dn;
    /** 조직(부서)ID */
    private String orgnztId;
    /** 조직(부서)명 */
    private String orgnztNm;
    /** 비밀번호 힌트 */
    private String passwordHint;
    /** 비밀번호 정답 */
    private String passwordCnsr;

    /**
     * id attribute 를 리턴한다.
     * @return String
     */
    public String getUSER_ID() {
        return USER_ID;
    }
    /**
     * id attribute 값을 설정한다.
     * @param id String
     */
    public void setUSER_ID(String user_id) {
        this.USER_ID = user_id;
    }
    /**
     * name attribute 를 리턴한다.
     * @return String
     */
    public String getUSER_NM() {
        return USER_NM;
    }
    /**
     * name attribute 값을 설정한다.
     * @param name String
     */
    public void setUSER_NM(String name) {
        this.USER_NM = name;
    }
    /**
     * email attribute 를 리턴한다.
     * @return String
     */
    public String getEMAIL() {
        return EMAIL;
    }
    /**
     * email attribute 값을 설정한다.
     * @param email String
     */
    public void setEMAIL(String email) {
        this.EMAIL = email;
    }
    /**
     * password attribute 를 리턴한다.
     * @return String
     */
    public String getUSER_PWD() {
        return USER_PWD;
    }
    /**
     * password attribute 값을 설정한다.
     * @param password String
     */
    public void setUSER_PWD(String password) {
        this.USER_PWD = password;
    }
    /**
     * userSe attribute 를 리턴한다.
     * @return String
     */
    public String getUSER_SE() {
        return USER_SE;
    }
    /**
     * userSe attribute 값을 설정한다.
     * @param userSe String
     */
    public void setUSER_SE(String userSe) {
        this.USER_SE = userSe;
    }
    /**
     * uniqId attribute 를 리턴한다.
     * @return String
     */
    public String getUNIQ_ID() {
        return UNIQ_ID;
    }
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    public void setUNIQ_ID(String uniqId) {
        this.UNIQ_ID = uniqId;
    }

    public String getUSER_ROLE() {
        return USER_ROLE;
    }
    public void setUSER_ROLE(String userRole) {
        this.USER_ROLE = userRole;
    }
    public String getADMIN_ROLE() {
        return ADMIN_ROLE;
    }
    public void setADMIN_ROLE(String adminRole) {
        this.ADMIN_ROLE = adminRole;
    }
    public String getROLE_NM() {
        return ROLE_NM;
    }
    public void setROLE_NM(String roleName) {
        this.ROLE_NM = roleName;
    }
    public String getONOFF_DIV() {
        return ONOFF_DIV;
    }
    public void setONOFF_DIV(String onOffDiv) {
        this.ONOFF_DIV = onOffDiv;
    }
    public String getNICKNM() {
        return NICKNM;
    }
    public void setNICKNM(String nickName) {
        this.NICKNM = nickName;
    }
    public String getINDX_MENU_ID() {
        return INDX_MENU_ID;
    }
    public void setINDX_MENU_ID(String indxMenuId) {
        this.INDX_MENU_ID = indxMenuId;
    }
    public String getP_MENUID() {
        return P_MENUID;
    }
    public void setP_MENUID(String pMenuId) {
        this.P_MENUID = pMenuId;
    }
    public String getINDX_MENU_URL() {
        return INDX_MENU_URL;
    }
    public void setINDX_MENU_URL(String indxMenuUrl) {
        this.INDX_MENU_URL = indxMenuUrl;
    }
    public String getUSER_IP() {
        return USER_IP;
    }
    public void setUSER_IP(String uSER_IP) {
        USER_IP = uSER_IP;
    }

    /**
     * url attribute 를 리턴한다.
     * @return String
     */
    public String getUrl() {
        return url;
    }
    /**
     * url attribute 값을 설정한다.
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * ip attribute 를 리턴한다.
     * @return String
     */
    public String getIp() {
        return ip;
    }
    /**
     * ip attribute 값을 설정한다.
     * @param ip String
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * orgnztId attribute 를 리턴한다.
     * @return String
     */
    public String getOrgnztId() {
        return orgnztId;
    }
    /**
     * orgnztId attribute 값을 설정한다.
     * @param orgnztId String
     */
    public void setOrgnztId(String orgnztId) {
        this.orgnztId = orgnztId;
    }
    /**
     * @return the orgnztNm
     */
    public String getOrgnztNm() {
        return orgnztNm;
    }
    /**
     * @param orgnztNm the orgnztNm to set
     */
    public void setOrgnztNm(String orgnztNm) {
        this.orgnztNm = orgnztNm;
    }
    /**
     * passwordHint attribute 를 리턴한다.
     * @return String
     */
    public String getPasswordHint() {
        return passwordHint;
    }
    /**
     * passwordHint attribute 값을 설정한다.
     * @param passwordHint String
     */
    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }
    /**
     * passwordCnsr attribute 를 리턴한다.
     * @return String
     */
    public String getPasswordCnsr() {
        return passwordCnsr;
    }
    /**
     * passwordCnsr attribute 값을 설정한다.
     * @param passwordCnsr String
     */
    public void setPasswordCnsr(String passwordCnsr) {
        this.passwordCnsr = passwordCnsr;
    }
    /**
     * dn attribute 를 리턴한다.
     * @return String
     */
    public String getDn() {
        return dn;
    }
    /**
     * dn attribute 값을 설정한다.
     * @param dn String
     */
    public void setDn(String dn) {
        this.dn = dn;
    }
}
