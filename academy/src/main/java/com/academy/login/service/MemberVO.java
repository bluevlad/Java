package com.academy.login.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
 * @Class Name : MemberVO.java
 * @Description : MemberVO VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2025.02.20   rainend         최초 생성
 *
 *  @version 1.0
 *  @see
 *  
 */
public class MemberVO extends CommonVO implements Serializable{
	
	private static final long serialVersionUID = 638950577710720796L;

	/** 인증용 토큰 */
	private String token;
	/** 아이디 */
	private String userId;
	/** 이름 */
	private String userNm;
	/** 비밀번호 */
	private String userPwd;
	/** 성별 */
	private String sex;
	/** 사용자권한 */
	private String userRole;
	/** 관리자권한 */
	private String adminRole;
	/** 생년월일 */
	private String birthDay;
	/** 이메일 */
	private String email;
	/** 우편번호 */
	private String zipCode;
	/** 주소 */
	private String address1;
	/** 상세주소 */
	private String address2;
	/** 사용자포인트 */
	private int userPoint;
	/** 사용자설명 */
	private String memo;
	/** 사용자사진 */
	private String pic;
	/** 문자수신여부 */
	private String isokSms;
	/** 이메일수신여부 */
	private String isokEmail;

	public String getToken() {
		return token; 
	}
	public void setToken(String token) {
		this.token = token; 
	}

    // getter & setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAdminRole() {
        return adminRole;
    }
    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getUserPoint() {
        return userPoint;
    }
    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIsokSms() {
        return isokSms;
    }
    public void setIsokSms(String isokSms) {
        this.isokSms = isokSms;
    }

    public String getIsokEmail() {
        return isokEmail;
    }
    public void setIsokEmail(String isokEmail) {
        this.isokEmail = isokEmail;
    }
	
}
