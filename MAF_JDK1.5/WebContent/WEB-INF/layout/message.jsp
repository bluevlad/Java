<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${target != null}">
		<c:set var="document" value="${target}.location" />
	</c:when>
	<c:otherwise>
		<c:set var="document" value="document.location" />
	</c:otherwise>
</c:choose>
<c:url var="next" value='${next}'>
	<c:forEach var='aKey' items='${Xparam}'>
		<c:param name='${aKey.key}' value='${aKey.value}' />
	</c:forEach>
	<c:if test="${!empty(course)}">
		<c:param name='extleccode' value='${course.extleccode}' />
	</c:if>
</c:url>
<html>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=UTF-8">
	<title>Message</title>
<head>
</head>
<body>
	<Script>
		<c:if test="${!empty(message)}" >
			alert('<mh:out value="${message}" />');
		</c:if>
		 //alert("<c:out value="${next}" escapeXml="false"/>");
		<c:out value="${document}"/>='<c:out value="${next}" escapeXml="false"/>';
	</Script>
</body>
</html>
