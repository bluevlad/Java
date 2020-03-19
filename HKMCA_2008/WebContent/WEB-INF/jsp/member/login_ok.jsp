<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=utf-8" />
	<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1"> 
	<title>Untitled</title>
</head>

<body>
<c:url var="next" value='${next}'>
	<c:forEach var='aKey' items='${Xparam}'>
		<c:param name='${aKey.key}' value='${aKey.value}' />
	</c:forEach>
	<c:if test="${!empty(course)}">
		<c:param name='extleccode' value='${course.extleccode}' />
	</c:if>
</c:url>
<script>
<c:if test="${type == 'popup'}" >
	opener.document.location = '<c:out value="${next}" escapeXml="false" />';
	self.close();
</c:if>
<c:if test="${type != 'popup'}" >
	document.location = '<c:out value="${next}" escapeXml="false" />';
</c:if>
</script>
    MAF_INFO.file = <c:out value="${MAF_INFO.file}"/>; <br>
</body>
</html>
