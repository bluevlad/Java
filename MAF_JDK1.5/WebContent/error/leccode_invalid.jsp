<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="/error/inc_start.jsp"/>
<c:url var="nextUrl" value="${controlaction}" scope="request">
    <c:forEach var="aKey" items="${paramValues}">
            <c:if test="${aKey.key !='lang'}">
            <c:forEach var="val" items="${aKey.value}">
                    <c:param name='${aKey.key}' value='${val}'/>
            </c:forEach>
            </c:if>
    </c:forEach>
</c:url>
<table width="584" height="35" border="0" cellspacing="3" cellpadding="0" align="center" bgcolor="#EBEBEB">
    <TR>
        <TD bgColor=#ffffff>
        <table width="578" border="0" cellspacing="0" cellpadding="0">
            <TR>
                <td width="157"><IMG src='<c:url value="/error/images/img_logout.gif"/>' border=0></td>
                <td width="20"></td>
                <td width="401">
                <table width="401" border="0" cellspacing="0" cellpadding="0">
                    <TR>
                        <td height="21" colspan="2" ><B>Unauthorized Access</B></td>
                    </TR>
                   

                </TABLE>
                <!-- // --></td>
            </TR>
        </TABLE>

        </TD>
    </TR>
</table>
<a href='<c:url value="/wlc.learner/main.do"/>'>go My Desk </a>
<c:import url="/error/inc_finish.jsp"/>