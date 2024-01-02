package com.willbes.cmm.service;

import java.io.Serializable;

//import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭
 *    2014. 10. 04.   kckim       DB TABLE 변경에 따라 전체 내용 변경
 *
 *
 */
@SuppressWarnings("serial")
public class FileVO implements Serializable {

    public String ATCH_FILE_ID; // 파일번호
    public long FILE_SN; //파일 시리얼 번호
    public String FILE_NM; //파일이름
    public String FILE_ORGN_NM;  //파일 원본 이름
    public String FILE_PATH; //파일경로
    public String FILE_FULL_PATH; //파일 전체 경로
    public String FILE_EXTSN;  // 파일확장자
    public long FILE_SIZE;  //파일크기
    public String FILE_CONTENTS; //파일내용
    public String THUMBNAIL_FILE_NM; //썸네일 파일명
    public String THUMBNAIL_FILE_PATH;  //썸네일 경로
    public String THUMBNAIL_FILE_REAL_NM; //썸네일 파일 실제 명
    public String REG_ID; //등록자 아이디
    public String REG_DT; //등록일
    public String UPD_ID; //수정자 아이디
    public String UPD_DT; //수정일
    public String USE_YN; //사용여부
    public String DEL_YN; //삭제여부

    public String getATCH_FILE_ID() {
        return ATCH_FILE_ID;
    }
    public void setATCH_FILE_ID(String atch_file_id) {
        ATCH_FILE_ID = atch_file_id;
    }
    public String getFILE_NM() {
        return FILE_NM;
    }
    public void setFILE_NM(String file_nm) {
        FILE_NM = file_nm;
    }
    public String getFILE_ORGN_NM() {
        return FILE_ORGN_NM;
    }
    public void setFILE_ORGN_NM(String file_orgn_nm) {
        FILE_ORGN_NM = file_orgn_nm;
    }
    public String getFILE_PATH() {
        return FILE_PATH;
    }
    public void setFILE_PATH(String file_path) {
        FILE_PATH = file_path;
    }
    public String getFILE_FULL_PATH() {
        return FILE_FULL_PATH;
    }
    public void setFILE_FULL_PATH(String file_full_path) {
        FILE_FULL_PATH = file_full_path;
    }
    public String getFILE_EXTSN() {
        return FILE_EXTSN;
    }
    public void setFILE_EXTSN(String file_extsn) {
        FILE_EXTSN = file_extsn;
    }
    public long getFILE_SIZE() {
        return FILE_SIZE;
    }
    public void setFILE_SIZE(long file_size) {
        FILE_SIZE = file_size;
    }
    public String getFILE_CONTENTS() {
        return FILE_CONTENTS;
    }
    public void setFILE_CONTENTS(String file_contents) {
        FILE_CONTENTS = file_contents;
    }
    public String getTHUMBNAIL_FILE_NM() {
        return THUMBNAIL_FILE_NM;
    }
    public void setTHUMBNAIL_FILE_NM(String thumbnail_file_nm) {
        THUMBNAIL_FILE_NM = thumbnail_file_nm;
    }
    public String getTHUMBNAIL_FILE_PATH() {
        return THUMBNAIL_FILE_PATH;
    }
    public void setTHUMBNAIL_FILE_PATH(String thumbnail_file_path) {
        THUMBNAIL_FILE_PATH = thumbnail_file_path;
    }
    public String getTHUMBNAIL_FILE_REAL_NM() {
        return THUMBNAIL_FILE_REAL_NM;
    }
    public void setTHUMBNAIL_FILE_REAL_NM(String thumbnail_file_real_nm) {
        THUMBNAIL_FILE_REAL_NM = thumbnail_file_real_nm;
    }
    public long getFILE_SN() {
        return FILE_SN;
    }
    public void setFILE_SN(long file_sn) {
        FILE_SN = file_sn;
    }
    public String getREG_ID() {
        return REG_ID;
    }
    public void setREG_ID(String reg_id) {
        REG_ID = reg_id;
    }
    public String getREG_DT() {
        return REG_DT;
    }
    public void setREG_DT(String reg_dt) {
        REG_DT = reg_dt;
    }
    public String getUPD_ID() {
        return UPD_ID;
    }
    public void setUPD_ID(String upd_id) {
        UPD_ID = upd_id;
    }
    public String getUPD_DT() {
        return UPD_DT;
    }
    public void setUPD_DT(String upd_dt) {
        UPD_DT = upd_dt;
    }
    public String getUSE_YN() {
        return USE_YN;
    }
    public void setUSE_YN(String use_yn) {
        USE_YN = use_yn;
    }
    public String getDEL_YN() {
        return DEL_YN;
    }
    public void setDEL_YN(String del_yn) {
        DEL_YN = del_yn;
    }


/*
    *//**
     * 첨부파일 아이디
     *//*
    public String atchFileId = "";
    *//**
     * 생성일자
     *//*
    public String creatDt = "";
    *//**
     * 파일내용
     *//*
    public String fileCn = "";
    *//**
     * 파일확장자
     *//*
    public String fileExtsn = "";
    *//**
     * 파일크기
     *//*
    public String fileMg = "";
    *//**
     * 파일연번
     *//*
    public String fileSn = "";
    *//**
     * 파일저장경로
     *//*
    public String fileStreCours = "";
    *//**
     * 원파일명
     *//*
    public String orignlFileNm = "";
    *//**
     * 저장파일명
     *//*
    public String streFileNm = "";

    *//**
     * atchFileId attribute를 리턴한다.
     *
     * @return the atchFileId
     *//*
    public String getAtchFileId() {
        return atchFileId;
    }

    *//**
     * atchFileId attribute 값을 설정한다.
     *
     * @param atchFileId
     *            the atchFileId to set
     *//*
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    *//**
     * creatDt attribute를 리턴한다.
     *
     * @return the creatDt
     *//*
    public String getCreatDt() {
        return creatDt;
    }

    *//**
     * creatDt attribute 값을 설정한다.
     *
     * @param creatDt
     *            the creatDt to set
     *//*
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

    *//**
     * fileCn attribute를 리턴한다.
     *
     * @return the fileCn
     *//*
    public String getFileCn() {
        return fileCn;
    }

    *//**
     * fileCn attribute 값을 설정한다.
     *
     * @param fileCn
     *            the fileCn to set
     *//*
    public void setFileCn(String fileCn) {
        this.fileCn = fileCn;
    }

    *//**
     * fileExtsn attribute를 리턴한다.
     *
     * @return the fileExtsn
     *//*
    public String getFileExtsn() {
        return fileExtsn;
    }

    *//**
     * fileExtsn attribute 값을 설정한다.
     *
     * @param fileExtsn
     *            the fileExtsn to set
     *//*
    public void setFileExtsn(String fileExtsn) {
        this.fileExtsn = fileExtsn;
    }

    *//**
     * fileMg attribute를 리턴한다.
     *
     * @return the fileMg
     *//*
    public String getFileMg() {
        return fileMg;
    }

    *//**
     * fileMg attribute 값을 설정한다.
     *
     * @param fileMg
     *            the fileMg to set
     *//*
    public void setFileMg(String fileMg) {
        this.fileMg = fileMg;
    }

    *//**
     * fileSn attribute를 리턴한다.
     *
     * @return the fileSn
     *//*
    public String getFileSn() {
        return fileSn;
    }

    *//**
     * fileSn attribute 값을 설정한다.
     *
     * @param fileSn
     *            the fileSn to set
     *//*
    public void setFileSn(String fileSn) {
        this.fileSn = fileSn;
    }

    *//**
     * fileStreCours attribute를 리턴한다.
     *
     * @return the fileStreCours
     *//*
    public String getFileStreCours() {
        return fileStreCours;
    }

    *//**
     * fileStreCours attribute 값을 설정한다.
     *
     * @param fileStreCours
     *            the fileStreCours to set
     *//*
    public void setFileStreCours(String fileStreCours) {
        this.fileStreCours = fileStreCours;
    }

    *//**
     * orignlFileNm attribute를 리턴한다.
     *
     * @return the orignlFileNm
     *//*
    public String getOrignlFileNm() {
        return orignlFileNm;
    }

    *//**
     * orignlFileNm attribute 값을 설정한다.
     *
     * @param orignlFileNm
     *            the orignlFileNm to set
     *//*
    public void setOrignlFileNm(String orignlFileNm) {
        this.orignlFileNm = orignlFileNm;
    }

    *//**
     * streFileNm attribute를 리턴한다.
     *
     * @return the streFileNm
     *//*
    public String getStreFileNm() {
        return streFileNm;
    }

    *//**
     * streFileNm attribute 값을 설정한다.
     *
     * @param streFileNm
     *            the streFileNm to set
     *//*
    public void setStreFileNm(String streFileNm) {
        this.streFileNm = streFileNm;
    }

    *//**
     * toString 메소드를 대치한다.
     *//*
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
