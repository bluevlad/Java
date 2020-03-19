<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<title>logout</title>
</head>

<body>
<c:if test="${empty next}"><c:set var="next" value="${CPATH}"/></c:if>
<c:url var="next" value='${next}'/>
<script>

	document.location = '<c:out value="${next}" escapeXml="false"/>';

</script>
</body>
</html>
