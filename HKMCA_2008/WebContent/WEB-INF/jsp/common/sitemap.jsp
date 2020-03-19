<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<div id="sitemap">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
    <!-- 메뉴정보 -->
        <c:forEach var="item" items="${item}">
        <!-- 메뉴가 상단메뉴일 경우 -->
        <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td><h1><mh:out value="${item.title}"/></h1></td>
                </tr>
                <tr>
                    <td>
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <c:forEach var="sub" items="${sub}">
                            <c:if test="${sub.pnodeid == item.pgid}">
                            <tr style="cursor:pointer" >
                                <td valign="middle" class="menu_l3">
                                <a href="<c:if test="${sub.isservlet == 'T'}"></c:if><mh:out value="${sub.page}"/>"><mh:out value="${sub.title}"/></a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                        <c:forEach var="last" items="${last}">
                                        <c:if test="${last.pnodeid == sub.pgid}">
                                        <tr style="cursor:pointer">
                                            <td valign="middle" class="menu_l4"><mh:out value="${last.title}"/></td>
                                        </tr>
                                        </c:if>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            </c:if>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
        </c:forEach>
    </tr>
</table>
</div>