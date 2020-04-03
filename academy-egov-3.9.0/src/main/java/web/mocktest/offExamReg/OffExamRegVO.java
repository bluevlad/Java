package com.willbes.web.mocktest.offExamReg;

public class OffExamRegVO {

    private String DIV_CD;
    private String SUBJECT_NM;
    private String USER_NM;
    private String IDENTITY_ID;
    private String MARKINGS;

    private String ERR_YN;
    private String ERR_DESC;

    private String[] HEADER_NM = new String[5];
    private String[] ERRS_YN = new String[5];
    private String[] ERRS_DESC = new String[5];

    public String getDIV_CD() {
        return DIV_CD;
    }
    public void setDIV_CD(String div_cd) {
        DIV_CD = div_cd;
    }
    public String getSUBJECT_NM() {
        return SUBJECT_NM;
    }
    public void setSUBJECT_NM(String subject_nm) {
        SUBJECT_NM = subject_nm;
    }
    public String getUSER_NM() {
        return USER_NM;
    }
    public void setUSER_NM(String user_nm) {
        USER_NM = user_nm;
    }
    public String getIDENTITY_ID() {
        return IDENTITY_ID;
    }
    public void setIDENTITY_ID(String identity_id) {
        IDENTITY_ID = identity_id;
    }
    public String getMARKINGS() {
        return MARKINGS;
    }
    public void setMARKINGS(String markings) {
        MARKINGS = markings;
    }
    public String getERR_YN() {
        return ERR_YN;
    }
    public void setERR_YN(String err_yn) {
        ERR_YN = err_yn;
    }
    public String getERR_DESC() {
        return ERR_DESC;
    }
    public void setERR_DESC(String err_desc) {
        ERR_DESC = err_desc;
    }
    public String[] getHEADER_NM() {
        return HEADER_NM;
    }
    public void setHEADER_NM(String[] header_nm) {
        HEADER_NM = header_nm;
    }
    public String[] getERRS_YN() {
        return ERRS_YN;
    }
    public void setERRS_YN(String[] errs_yn) {
        ERRS_YN = errs_yn;
    }
    public String[] getERRS_DESC() {
        return ERRS_DESC;
    }
    public void setERRS_DESC(String[] errs_desc) {
        ERRS_DESC = errs_desc;
    }

}
