package com.willbes.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.11   이삼섭          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class ComDefaultCodeVO implements Serializable {

    String SYS_CD = "";
    String SYS_NM = "";
    String CODE_CD = "";
    String CODE_NM = "";
    String CODE_VAL = "";

    /** 특정테이블명 */
    private String tableNm = "";	//특정테이블에서 코드정보를추출시 사용

    /** 상세 조건 여부 */
    private String haveDetailCondition = "N";

    /** 상세 조건 */
    private String detailCondition = "";

    public String getSYS_CD() {
        return SYS_CD;
    }

    public void setSYS_CD(String sYS_CD) {
        SYS_CD = sYS_CD;
    }

    public String getSYS_NM() {
        return SYS_NM;
    }

    public void setSYS_NM(String sYS_NM) {
        SYS_NM = sYS_NM;
    }

    public String getCODE_NM() {
        return CODE_NM;
    }

    public void setCODE_NM(String cODE_NM) {
        CODE_NM = cODE_NM;
    }

    public String getCODE_VAL() {
        return CODE_VAL;
    }

    public void setCODE_VAL(String cODE_VAL) {
        CODE_VAL = cODE_VAL;
    }

    public String getCODE_CD() {
        return CODE_CD;
    }

    public void setCODE_CD(String cODE_CD) {
        CODE_CD = cODE_CD;
    }


    /**
     * tableNm attribute를 리턴한다.
     *
     * @return the tableNm
     */
    public String getTableNm() {
	return tableNm;
    }

    /**
     * tableNm attribute 값을 설정한다.
     *
     * @param tableNm
     *            the tableNm to set
     */
    public void setTableNm(String tableNm) {
	this.tableNm = tableNm;
    }

    /**
     * haveDetailCondition attribute를 리턴한다.
     *
     * @return the haveDetailCondition
     */
    public String getHaveDetailCondition() {
	return haveDetailCondition;
    }

    /**
     * haveDetailCondition attribute 값을 설정한다.
     *
     * @param haveDetailCondition
     *            the haveDetailCondition to set
     */
    public void setHaveDetailCondition(String haveDetailCondition) {
	this.haveDetailCondition = haveDetailCondition;
    }

    /**
     * detailCondition attribute를 리턴한다.
     *
     * @return the detailCondition
     */
    public String getDetailCondition() {
	return detailCondition;
    }

    /**
     * detailCondition attribute 값을 설정한다.
     *
     * @param detailCondition
     *            the detailCondition to set
     */
    public void setDetailCondition(String detailCondition) {
	this.detailCondition = detailCondition;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
