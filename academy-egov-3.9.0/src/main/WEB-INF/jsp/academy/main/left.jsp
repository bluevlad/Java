<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<dl id="lnb">
    <c:if test="${not empty leftMenuList}">
    <c:forEach items="${leftMenuList}" var="list" end="0">
    <dt>${list.MENU_NM}</dt> 
    </c:forEach>
    <dd>
		<ul>
		<c:set var="menu_level" value="0" />
        <c:set var="menu_level2" value="0" />
        <c:set var="menu_length" value="${fn:length(leftMenuList)}" />
        <c:forEach items="${leftMenuList}" var="list" varStatus="status" begin="1">
        <li>
        <c:set var="menu_level" value="${list.LEVEL}" />
        <c:set var="menu_level2" value="0" />
        <c:if test="${(status.index+1) < (menu_length)}"><c:set var="menu_level2" value="${leftMenuList[status.index+1].LEVEL}" /></c:if>
        <c:set var="goURL" value="${list.MENU_URL}" />
        <c:if test="${not fn:contains(list.MENU_URL, '?')}"><c:set var="goURL" value="${list.MENU_URL}?" /></c:if>
        <c:choose>
        	<c:when test="${menu_level2 > menu_level}">
            <a href="<c:url value="${goURL}&MENU_ID=${list.MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${list.MENU_NM}"/>" class="<c:if test="${list.MENU_ID eq MENU_ID or fn:startsWith(MENU_ID,list.MENU_ID)}">active</c:if>"><c:out  value="${list.MENU_NM}"/></a>
            </c:when>
            <c:when test="${menu_level2 == menu_level}">
            	<c:choose>
                	<c:when test="${menu_level == 2}">
            <a href="<c:url value="${goURL}&MENU_ID=${list.MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${list.MENU_NM}"/>" class="<c:if test="${list.MENU_ID eq MENU_ID or fn:startsWith(MENU_ID,list.MENU_ID)}">active</c:if>"><c:out  value="${list.MENU_NM}"/></a>
            		</c:when>
                    <c:when test="${menu_level == 3}">
            <p><a href="<c:url value="${goURL}&MENU_ID=${list.MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${list.MENU_NM}"/>" class="<c:if test="${list.MENU_ID eq MENU_ID}">on</c:if>"><c:out  value="${list.MENU_NM}"/></a></p>
            		</c:when>
				</c:choose>
			</c:when>
            <c:when test="${menu_level2 < menu_level}">
            	<c:choose>
                	<c:when test="${menu_level2 == 0 and menu_level == 2}">
			<a href="<c:url value="${goURL}&MENU_ID=${list.MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${list.MENU_NM}"/>" class="<c:if test="${list.MENU_ID eq MENU_ID or fn:startsWith(MENU_ID,list.MENU_ID)}">active</c:if>"><c:out  value="${list.MENU_NM}"/></a>
            		</c:when>
                    <c:otherwise>
			<p><a href="<c:url value="${goURL}&MENU_ID=${list.MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${list.MENU_NM}"/>" class="<c:if test="${list.MENU_ID eq MENU_ID}">on</c:if>"><c:out  value="${list.MENU_NM}"/></a></p>
            		</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
        </li>
        </c:forEach>
        </ul>
	</dd>
    </c:if> 
    </dl>