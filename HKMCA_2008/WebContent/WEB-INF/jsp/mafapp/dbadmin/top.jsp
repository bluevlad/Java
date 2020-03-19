<%@ page contentType = "text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<form action="<c:url value="/dbadmin/default.do"/>" name="frmUserSel" id="frmUserSel" target="_top">
USER(<c:out value="${param.owner}"/> : <select name="owner">
<c:forEach items="${users}" var="item">
	<option value="<c:out value="${item.username}"/>" <c:if test="${item.username == param.owner}">SELECTED</c:if> ><c:out value="${item.username}"/></option>
</c:forEach>
</select> <input type="submit" name="btnSubmit" value="View">
</form>
