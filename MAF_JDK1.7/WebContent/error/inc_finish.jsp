<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<BR>
<c:url var="nextUrl" value="${controlaction}">
	<c:forEach var="aKey" items="${paramValues}">
			<c:if test="${aKey.key !='lang'}">
			<c:forEach var="val" items="${aKey.value}">
					<c:param name='${aKey.key}' value='${val}'/>
			</c:forEach>
			</c:if>
	</c:forEach>
</c:url>
<script>
	<c:url var="url_login_pop" value="/login.do">
		<c:param name="cmd" value="loginForm"/>
		<c:param name="next" value="${nextUrl}"/>
		<c:param name="type" value="popup"/>
	</c:url>
    <c:url var="url_login" value="/login.do">
        <c:param name="cmd" value="loginForm"/>
        <c:param name="next" value="${nextUrl}"/>
    </c:url>
	function openLoginWindow() {
		var oWin = window.open('<c:out value="${url_login_pop}" escapeXml="false"/>', "user", "width=300, height=200, scrollbars=yes, resizable=yes");
		if(oWin) {
			if (oWin.opener == null) {
				oWin.opener = window;
			}
			oWin.focus();
		}
	}
    function goLogin() {
        document.location = '<c:out value="${url_login}" escapeXml="false"/>';
        
    }
</script>
<table width="584" height="35" border="0" cellspacing="3" cellpadding="0" align="center" bgcolor="#EBEBEB">
	<TR>
		<TD bgColor="#ffffff" align="center">
		<br>
		<a href='<c:url value="/"/>' target="_top">Home</a>
		<c:if test="${empty(sessionScope.msession)}">
			&nbsp;|&nbsp;<a href="javascript:goLogin()">go Login!!!</a>
		</c:if>
        &nbsp;|&nbsp;<a href='<c:url value="/logout.do"/>' target="_top">Logout</a> 
		&nbsp;|&nbsp;<a href="javascript:history.back(-1)">Back</a>
		<br>&nbsp;
		</TD>
	</TR>
</table>
</body>
</html>
