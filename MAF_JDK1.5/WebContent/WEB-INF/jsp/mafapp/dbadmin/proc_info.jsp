<%@page contentType = "text/html; charset=euc-kr"%>
<textarea cols="90" rows="35" name="bean" id="frmBean">
<c:forEach items="${source}" var="item" varStatus="status">${item.line} : ${item.text}</c:forEach>
</textarea>
<hr>
<c:forEach items="${procs}" var="item" varStatus="status">
	${item}<br>
</c:forEach>
<hr>
${s}