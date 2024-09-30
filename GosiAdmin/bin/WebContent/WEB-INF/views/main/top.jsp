<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<head>
<script type="text/javascript">
    function getOnOffMenu(onoff){
        //location.href='<c:url value="/main/top.do"/>' + '?MENUTYPE='+onoff;
    }
</script>
</head>
    <!--상단메뉴 S-->
    <div class="firstheader">
        <h1><a href="<c:url value="/"/>">학원관리</a></h1>
        <ul class="util">
            <li><a href="<c:url value="/"/>">HOME</a> | </li>
            <c:if test="${ONOFF_DIV =='A'}">
              <c:if test="${MENUTYPE eq 'OM_ROOT'}">
                <c:set var="COLOR_ON" value="style='color: red;'" />
                <c:set var="COLOR_OFF" value="" />
                <c:set var="COLOR_TST" value="" />
              </c:if>
              <c:if test="${MENUTYPE eq 'FM_ROOT'}">
                <c:set var="COLOR_ON" value="" />
                <c:set var="COLOR_OFF" value="style='color: red;'" />
                <c:set var="COLOR_TST" value="" />
              </c:if>
              <c:if test="${MENUTYPE eq 'TM_ROOT'}">
                <c:set var="COLOR_ON" value="" />
                <c:set var="COLOR_OFF" value="" />
                <c:set var="COLOR_TST" value="style='color: red;'" />
              </c:if>
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=OM_ROOT"/>"><span ${COLOR_ON}>온라인</span></a> | </li>
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID_OFF}&MENUTYPE=FM_ROOT"/>"><span ${COLOR_OFF}>오프라인</span></a> | </li>
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID_TST}&MENUTYPE=TM_ROOT"/>"><span ${COLOR_TST}>모의고사</span></a> | </li>
            </c:if>
            <c:if test="${ONOFF_DIV =='O'}">
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=OM_ROOT"/>">온라인</a> | </li>
            </c:if>
            <c:if test="${ONOFF_DIV =='F'}">
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID_OFF}&MENUTYPE=FM_ROOT"/>">오프라인</a> | </li>
            </c:if>
            <c:if test="${ONOFF_DIV =='T'}">
                <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${TOP_MENU_ID_TST}&MENUTYPE=TM_ROOT"/>">모의고사</a> | </li>
            </c:if>
            <li><a href="<c:url value="/login/logout.do"/>">로그아웃</a> | </li>
            <li><span class="txt01">"${AdmUserInfo.USER_NM}"</span>님 운영실에 입장하셨습니다.</li>
        </ul>        
        <ul class="gnb">
        <c:if test="${not empty topMenuList}">
          <c:forEach items="${topMenuList}" var="list" varStatus="status">
            <li><a href="<c:url value="/main/index.do?TOP_MENU_ID=${list.MENU_ID}&MENUTYPE=${list.P_MENUID}&L_MENU_NM=${list.MENU_NM}"/>"><c:out  value="${list.MENU_NM}"/></a> | </li>
          </c:forEach>
        </c:if>
          <li style="float:right;"><a href="javascript:fn_faqPop();" style="text-decoration:underline;">[기술응대FAQ]</a></li>
        </ul>
        <script type="text/javascript">
		    function fn_faqPop(){
		    	window.open('http://lms.willbes.net/06.Crm/03.CS/01.ActionData/dataList_popup.asp'
		    			, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1000,height=800');
		    }
		</script>
    </div>
