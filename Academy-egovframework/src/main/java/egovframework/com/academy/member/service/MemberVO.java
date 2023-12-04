package egovframework.com.academy.member.service;

import java.io.Serializable;

/**
*
* 강의정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2023.08.00			rainend			강의정보 등록
* </pre>
*/

public class MemberVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색Keyword */
    private String searchKeyword = "";
    /** 검색직렬 */
    private String searchCategory = "";
    /** 검색사용여부 */
    private String searchUseYn = "";
    /** 온오프구분여부 */
    private String OnOffDiv = "";
    /** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 10;
    /** 페이지사이즈 */
    private int pageSize = 10;
    /** firstIndex */
    private int firstIndex = 1;
    /** lastIndex */
    private int lastIndex = 1;
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    /** 등록일시 */
    private String regDt;
    /** 등록자ID */
    private String regId;
    /** 수정일시 */
    private String updDt;
    /** 수정자ID */
    private String updId;
    /** 사용 여부 */
    private String isUse;

	/** 회원 id */
	private String userId;
	/** 성명 */
	private String userNm;
    /**  비밀번호 */
    private String userPwd;
    /** 직책(관리자에 사용) */
    private String userPosition;
    /** 성별 */
    private String sex;
    /** 사용자 권한(사용자, 관리자, 강사) */
    private String userRole;
    /**  관리자 */
    private String adminRole;
    /**  생일(생년월일) */
    private String birthDay;
    /**  이메일 */
    private String email;
    /**  핸드폰 */
    private String phoneNo;
    /**  자택전화번호 */
    private String telNo;
    /**  우편번호 */
    private String zipCode;
    /**  자택주소 */
    private String address1;
    /**  자택주소 상세 */
    private String address2;
    /**  관심분야(직종코드 - 회원에 사용) */
    private String categoryCd;
    /**  관심분야명(직종코드 - 회원에 사용) */
    private String categoryNm;
    /**  과목코드 */
    private String subjectCd;
    /**  과목명 */
    private String subjectNm;
    /**  포인트 */
    private int userPoint;
    /**  특이사항 */
    private String remark;
    /**  메모(관리자에 사용) */
    private String memo;
    /**  강사료 지급률(강사에 사용) */
    private int payment;
    /**  사진(소)(강사에 사용) */
    private String pic;
    /**  약력(강사에 사용) */
    private String profile;
    /**  입금계좌(강사에 사용) */
    private String account;
    /**  아이핀 di(회원에 사용) */
    private String ipindi;
    /**  문자수신여부 */
    private String isokSms;
    /**  이메일수신여부 */
    private String isokEmail;
 
	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * searchCondition attribute 값을 설정한다.
	 * @param searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * searchKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @param searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchCategory attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchCategory() {
		return searchCategory;
	}
	/**
	 * searchCategory attribute 값을 설정한다.
	 * @param searchCategory String
	 */
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	/**
	 * searchUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @param searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * OnOffDiv attribute 를 리턴한다.
	 * @return String
	 */
	public String getOnOffDiv() {
		return OnOffDiv;
	}
	/**
	 * OnOffDiv attribute 값을 설정한다.
	 * @param OnOffDiv String
	 */
	public void setOnOffDiv(String OnOffDiv) {
		this.OnOffDiv = OnOffDiv;
	}

	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * pageIndex attribute 값을 설정한다.
	 * @param pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * pageUnit attribute 값을 설정한다.
	 * @param pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * pageSize attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * pageSize attribute 값을 설정한다.
	 * @param pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * firstIndex attribute 값을 설정한다.
	 * @param firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * lastIndex attribute 값을 설정한다.
	 * @param lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @param recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	/**
	 * regDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * regDt attribute 값을 설정한다.
	 * @param regDt String
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * regId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * regId attribute 값을 설정한다.
	 * @param regId String
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * uptDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * uptDt attribute 값을 설정한다.
	 * @param uptDt String
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	/**
	 * uptId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * uptId attribute 값을 설정한다.
	 * @param uptId String
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	
	/**
	 * isUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsUse() {
		return isUse;
	}
	/**
	 * isUSe attribute 값을 설정한다.
	 * @param isUse String
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	
	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 *  attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * userNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param userNm String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	
	/**
	 * userPwd attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * userPwd attribute 값을 설정한다.
	 * @param userPwd String
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	/**
	 * userPosition attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserPosition() {
		return userPosition;
	}
	/**
	 * userPosition attribute 값을 설정한다.
	 * @param userPosition String
	 */
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	
	/**
	 * sex attribute 를 리턴한다.
	 * @return String
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * sex attribute 값을 설정한다.
	 * @param sex String
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * userRole attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * userRole attribute 값을 설정한다.
	 * @param userRole String
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	/**
	 * adminRole attribute 를 리턴한다.
	 * @return String
	 */
	public String getAdminRole() {
		return adminRole;
	}
	/**
	 * adminRole attribute 값을 설정한다.
	 * @param adminRole String
	 */
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	
	/**
	 * birthDay attribute 를 리턴한다.
	 * @return String
	 */
	public String getBirthDay() {
		return birthDay;
	}
	/**
	 * birthDay attribute 값을 설정한다.
	 * @param birthDay String
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	/**
	 * email attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email attribute 값을 설정한다.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * phoneNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * phoneNo attribute 값을 설정한다.
	 * @param phoneNo String
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	/**
	 * telNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * telNo attribute 값을 설정한다.
	 * @param telNo String
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	/**
	 * zipCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * zipCode attribute 값을 설정한다.
	 * @param zipCode String
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * address1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * address1 attribute 값을 설정한다.
	 * @param address1 String
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	/**
	 * address2 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * address2 attribute 값을 설정한다.
	 * @param address2 String
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	/**
	 * remark attribute 를 리턴한다.
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * remark attribute 값을 설정한다.
	 * @param remark String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * userPoint attribute 를 리턴한다.
	 * @return int
	 */
	public int getUserPoint() {
		return userPoint;
	}
	/**
	 * userPoint attribute 값을 설정한다.
	 * @param userPoint int
	 */
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	/**
	 * categoryCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	/**
	 * categoryCd attribute 값을 설정한다.
	 * @param categoryCd String
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	/**
	 * categoryNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCategoryNm() {
		return categoryNm;
	}
	/**
	 * categoryNm attribute 값을 설정한다.
	 * @param categoryNm String
	 */
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	/**
	 * subjectCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectCd() {
		return subjectCd;
	}
	/**
	 * subjectCd attribute 값을 설정한다.
	 * @param subjectCd String
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	/**
	 * subjectNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectNm() {
		return subjectNm;
	}
	/**
	 * subjectNm attribute 값을 설정한다.
	 * @param subjectNm String
	 */
	public void setSubjectNm(String subjectNm) {
		this.subjectNm = subjectNm;
	}
	/**
	 * memo attribute 를 리턴한다.
	 * @return String
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * memo attribute 값을 설정한다.
	 * @param memo String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * payment attribute 를 리턴한다.
	 * @return int
	 */
	public int getPayment() {
		return payment;
	}
	/**
	 * payment attribute 값을 설정한다.
	 * @param payment int
	 */
	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	/**
	 * pic attribute 를 리턴한다.
	 * @return String
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * pic attribute 값을 설정한다.
	 * @param pic String
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * profile attribute 를 리턴한다.
	 * @return String
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * profile attribute 값을 설정한다.
	 * @param profile String
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	/**
	 * account attribute 를 리턴한다.
	 * @return String
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * account attribute 값을 설정한다.
	 * @param account String
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * ipindi attribute 를 리턴한다.
	 * @return String
	 */
	public String getIpindi() {
		return ipindi;
	}
	/**
	 * ipindi attribute 값을 설정한다.
	 * @param ipindi String
	 */
	public void setIpindi(String ipindi) {
		this.ipindi = ipindi;
	}
	/**
	 * isokSms attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsokSms() {
		return isokSms;
	}
	/**
	 * isokSms attribute 값을 설정한다.
	 * @param isokSms String
	 */
	public void setIsokSms(String isokSms) {
		this.isokSms = isokSms;
	}
	
	/**
	 * isokEmail attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsokEmail() {
		return isokEmail;
	}
	/**
	 * isokEmail attribute 값을 설정한다.
	 * @param isokEmail String
	 */
	public void seIisokEmail(String isokEmail) {
		this.isokEmail = isokEmail;
	}
	
}
