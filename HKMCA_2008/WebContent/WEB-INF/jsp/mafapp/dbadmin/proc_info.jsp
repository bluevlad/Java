<%@page contentType = "text/html; charset=euc-kr"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<textarea cols="90" rows="35" name="bean" id="frmBean">
<c:forEach items="${source}" var="item" varStatus="status"><mh:out value="${item.text}"/></c:forEach>
</textarea>