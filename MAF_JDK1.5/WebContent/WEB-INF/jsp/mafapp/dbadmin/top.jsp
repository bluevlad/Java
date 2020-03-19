<%@ page contentType = "text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="<c:url value="/dbadmin/default.do"/>" name="frmUserSel" id="frmUserSel" target="_top">
USER(<c:out value="${param.owner}"/> : <select name="owner">
<c:forEach items="${users}" var="item">
	<option value="<c:out value="${item.username}"/>" <c:if test="${item.username == param.owner}">SELECTED</c:if> ><c:out value="${item.username}"/></option>
</c:forEach>
</select> <input type="submit" name="btnSubmit" value="View">
</form>
