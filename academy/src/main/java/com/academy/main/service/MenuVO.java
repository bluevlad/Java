package com.academy.main.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 게시판 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.10			rainend			 게시판 등록
* </pre>
*/

public class MenuVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

    private int menuNo; // 메뉴 일련번호 (PK)
    private String menuId; // 메뉴 아이디
    private String menuTitle; // 메뉴명 (필수)
    private String menuEn; // 메뉴 영문명
    private String menuType; // 메뉴 타입
    private String menuIcon; // 메뉴 아이콘
    private String menuUrl; // 메뉴 URL
    private String menuExact; // 메뉴 연결 여부 (Y/N)
    private String menuTarget; // 메뉴 새창 여부 (Y/N)
    private String menuBreadcrumbs; // 메뉴 브레드크럼 사용 여부 (Y/N)
    private String menuExternal; // 외부 링크 여부 (Y/N)
    private String menuLeft; // 좌측 메뉴 여부 (Y/N)
    private String menuPath; // 메뉴 연결명
    private String menuElement; // 메뉴 링크 (HTML 요소 등)
    private String menuLayout; // 화면 레이아웃 정보
    private int menuUpperId; // 상위 메뉴 코드 (부모 메뉴)
    private int menuDepth; // 메뉴 단계 (Depth)
    private String isUse; // 사용 여부 (Y/N)

    // Getter & Setter
    public int getMenuNo() {
        return menuNo;
    }
    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuEn() {
        return menuEn;
    }
    public void setMenuEn(String menuEn) {
        this.menuEn = menuEn;
    }

    public String getMenuType() {
        return menuType;
    }
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuExact() {
        return menuExact;
    }
    public void setMenuExact(String menuExact) {
        this.menuExact = menuExact;
    }

    public String getMenuTarget() {
        return menuTarget;
    }
    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public String getMenuBreadcrumbs() {
        return menuBreadcrumbs;
    }
    public void setMenuBreadcrumbs(String menuBreadcrumbs) {
        this.menuBreadcrumbs = menuBreadcrumbs;
    }

    public String getMenuExternal() {
        return menuExternal;
    }
    public void setMenuExternal(String menuExternal) {
        this.menuExternal = menuExternal;
    }

    public String getMenuLeft() {
        return menuLeft;
    }
    public void setMenuLeft(String menuLeft) {
        this.menuLeft = menuLeft;
    }

    public String getMenuPath() {
        return menuPath;
    }
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuElement() {
        return menuElement;
    }
    public void setMenuElement(String menuElement) {
        this.menuElement = menuElement;
    }

    public String getMenuLayout() {
        return menuLayout;
    }
    public void setMenuLayout(String menuLayout) {
        this.menuLayout = menuLayout;
    }

    public int getMenuUpperId() {
        return menuUpperId;
    }
    public void setMenuUpperId(int menuUpperId) {
        this.menuUpperId = menuUpperId;
    }

    public int getMenuDepth() {
        return menuDepth;
    }
    public void setMenuDepth(int menuDepth) {
        this.menuDepth = menuDepth;
    }

    public String getIsUse() {
        return isUse;
    }
    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "menuNo=" + menuNo +
                ", menuId='" + menuId + '\'' +
                ", menuTitle='" + menuTitle + '\'' +
                ", menuEn='" + menuEn + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuExact='" + menuExact + '\'' +
                ", menuTarget='" + menuTarget + '\'' +
                ", menuBreadcrumbs='" + menuBreadcrumbs + '\'' +
                ", menuExternal='" + menuExternal + '\'' +
                ", menuLeft='" + menuLeft + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuElement='" + menuElement + '\'' +
                ", menuLayout='" + menuLayout + '\'' +
                ", menuUpperId=" + menuUpperId +
                ", menuDepth=" + menuDepth +
                ", isUse='" + isUse + '\'' +
                '}';
    }
}
