package com.willbes.cmm;

/**
 * @Class Name : ComDefaultExtVO.java
 * @Description : ComDefaultExtVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2014.09.22    kckim         최초 생성
 *
 *  @author kckim
 *  @since 2014.00.22
 *  @version 1.0
 *  @see
 *
 */
public class ComDefaultExtVO extends ComDefaultVO {

    private static final long serialVersionUID = 7847763369320402609L;

    /** 검색조건 */
    private String SEARCHKIND = "";
    /** 검색조건 */
    private String SEARCHTYPE = "";

    /** 검색Keyword */
    private String SEARCHTEXT = "";
    /** 검색Keyword */
    private String SEARCHKEYWORD = "";

    /** 검색 시작일*/
    private String SDATE = "";
    /** 검색 시작일*/
    private String EDATE = "";

    /** 노출 여부*/
    private String SEARCHOPEN_YN = "";

    /** 정렬조건 */
    private String ORDERFIELD = "";
    /** 정렬값 */
    private String ORDERVALUE = "";

    public String getSEARCHKIND() {
        return this.SEARCHKIND;
    }

    public void setSEARCHKIND(String searchkind) {
        this.SEARCHKIND = searchkind;
    }

    public String getSEARCHTEXT() {
        return this.SEARCHTEXT;
    }

    public void setSEARCHTEXT(String searchtext) {
        this.SEARCHTEXT = searchtext;
    }
    public String getSEARCHTYPE() {
        return SEARCHTYPE;
    }

    public void setSEARCHTYPE(String searchtype) {
        SEARCHTYPE = searchtype;
    }

    public String getSEARCHKEYWORD() {
        return SEARCHKEYWORD;
    }

    public void setSEARCHKEYWORD(String searchkeyword) {
        SEARCHKEYWORD = searchkeyword;
    }

    public String getORDERFIELD() {
        return ORDERFIELD;
    }

    public void setORDERFIELD(String orderfield) {
        ORDERFIELD = orderfield;
    }

    public String getORDERVALUE() {
        return ORDERVALUE;
    }

    public void setORDERVALUE(String ordervalue) {
        ORDERVALUE = ordervalue;
    }

    public String getSDATE() {
        return this.SDATE;
    }

    public void setSDATE(String sdate) {
        this.SDATE = sdate;
    }

    public String getEDATE() {
        return this.EDATE;
    }

    public void setEDATE(String edate) {
        this.EDATE = edate;
    }

    public String getSEARCHOPEN_YN() {
        return this.SEARCHOPEN_YN;
    }

    public void setSEARCHOPEN_YN(String searchopen_yn) {
        this.SEARCHOPEN_YN = searchopen_yn;
    }
}
