package egovframework.com.academy.code.service;

import java.io.Serializable;

/**
* 공통코드 모델 클래스
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
 *  2020.03.00	rainend		myProject 적용
* </pre>
*/

public class CodeMst implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 코드ID */
	private String codeId = "";
	/** 코드ID명 */
	private String codeIdNm = "";
	/** 코드ID설명 */
	private String codeIdDc = "";
    /** 상세코드 */
	private String code = "";
	/** 상세코드명 */
    private String codeNm = "";
    /** 상세코드설명 */
    private String codeDc = "";
	/** 사용여부 */
    private String useAt = "";
    /** 최초등록자ID */
    private String frstRegisterId = "";
    /** 최종수정자ID */
    private String lastUpdusrId   = "";
    
	/**
	 * codeId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * codeId attribute 값을 설정한다.
	 * @param codeId String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	/**
	 * codeIdNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeIdNm() {
		return codeIdNm;
	}
	/**
	 * codeIdNm attribute 값을 설정한다.
	 * @param codeIdNm String
	 */
	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	/**
	 * codeIdDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeIdDc() {
		return codeIdDc;
	}
	/**
	 * codeIdDc attribute 값을 설정한다.
	 * @param codeIdDc String
	 */
	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
	}
	/**
	 * code attribute 를 리턴한다.
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * codeId attribute 값을 설정한다.
	 * @param codeId String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * codeIdNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeNm() {
		return codeNm;
	}
	/**
	 * codeIdNm attribute 값을 설정한다.
	 * @param codeIdNm String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeIdDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeDc() {
		return codeDc;
	}
	/**
	 * codeIdDc attribute 값을 설정한다.
	 * @param codeIdDc String
	 */
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * @param useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

}
