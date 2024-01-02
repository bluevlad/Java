package com.willbes.cmm.service;

import java.io.Serializable;

/**
 * 공통 코드 모델 클래스(TB_BA_CONFIG_CD)
 * @author kckim
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.09.23  kckim          최초 생성
 *
 * </pre>
 */
public class CmmnDetailCode implements Serializable {

    private static final long serialVersionUID = 1L;

    String SYS_CD = "";
    String SYS_NM = "";
    int CODE_NO;
    String CODE_NM = "";
    String CODE_VAL = "";
    String CODE_INFO = "";
    String ISUSE = "";
    String REG_ID = "";
    String REG_DT = "";
    String UPD_ID = "";
    String UPD_DT = "";

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
    public int getCODE_NO() {
        return CODE_NO;
    }
    public void setCODE_NO(int cODE_NO) {
        CODE_NO = cODE_NO;
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
    public String getCODE_INFO() {
        return CODE_INFO;
    }
    public void setCODE_INFO(String cODE_INFO) {
        CODE_INFO = cODE_INFO;
    }
    public String getISUSE() {
        return ISUSE;
    }
    public void setISUSE(String iSUSE) {
        ISUSE = iSUSE;
    }
    public String getREG_ID() {
        return REG_ID;
    }
    public void setREG_ID(String rEG_ID) {
        REG_ID = rEG_ID;
    }
    public String getREG_DT() {
        return REG_DT;
    }
    public void setREG_DT(String rEG_DT) {
        REG_DT = rEG_DT;
    }
    public String getUPD_ID() {
        return UPD_ID;
    }
    public void setUPD_ID(String uPD_ID) {
        UPD_ID = uPD_ID;
    }
    public String getUPD_DT() {
        return UPD_DT;
    }
    public void setUPD_DT(String uPD_DT) {
        UPD_DT = uPD_DT;
    }

}
