package com.willbes.cmm;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @author
 * @since 2014.10.01
 * @version 1.0
 * @Modification Information
 * @
 * @  수정일         수정자         수정내용
 * @ ------------    -----------    ---------------------------
 * @ 2014.10.01   miraenet         최초 생성
 *
 *  @see
 *
 */
public class ComDefaultVO implements Serializable {

    private static final long serialVersionUID = 7847763369320402609L;

    /** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 20;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 20;

    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";

    /** 검색KeywordTo */
    private String searchKeywordTo = "";

    /** 사이트 아이디 */
    private String SITE_ID = "";

    /** 메뉴 아이디 */
    private String MENU_ID = "";

    /** Top 메뉴 아이디 */
    private String TOP_MENU_ID = "";

    /** 메뉴 유형 */
    private String MENUTYPE = "";

    /** 왼쪽 메뉴 명 */
    private String L_MENU_NM = "";


    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    //currentPage
    public int getCurrentPage() {
        return pageIndex;
    }

    //currentPage
    public void setCurrentPage(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    //pageRow
    public int getPageRow() {
        return pageUnit;
    }

    //pageRow
    public void setPageRow(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * searchKeywordFrom attribute를 리턴한다.
     * @return String
     */
    public String getSearchKeywordFrom() {
        return searchKeywordFrom;
    }

    /**
     * searchKeywordFrom attribute 값을 설정한다.
     * @param searchKeywordFrom String
     */
    public void setSearchKeywordFrom(String searchKeywordFrom) {
        this.searchKeywordFrom = searchKeywordFrom;
    }

    /**
     * searchKeywordTo attribute를 리턴한다.
     * @return String
     */
    public String getSearchKeywordTo() {
        return searchKeywordTo;
    }

    /**
     * searchKeywordTo attribute 값을 설정한다.
     * @param searchKeywordTo String
     */
    public void setSearchKeywordTo(String searchKeywordTo) {
        this.searchKeywordTo = searchKeywordTo;
    }

    public String getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(String site_id) {
        SITE_ID = site_id;
    }

    public String getMENU_ID() {
        return this.MENU_ID;
    }

    public void setMENU_ID(String menu_id) {
        this.MENU_ID = menu_id;
    }

    public String getTOP_MENU_ID() {
        return this.TOP_MENU_ID;
    }

    public void setTOP_MENU_ID(String top_menu_id) {
        this.TOP_MENU_ID = top_menu_id;
    }

    public String getMENUTYPE() {
        return this.MENUTYPE;
    }

    public void setMENUTYPE(String menutype) {
        this.MENUTYPE = menutype;
    }

    public String getL_MENU_NM() {
        return this.L_MENU_NM;
    }

    public void setL_MENU_NM(String l_menu_nm) {
        this.L_MENU_NM = l_menu_nm;
    }
}
