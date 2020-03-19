<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=<fmt:message key='page.charset'/>">
	<title>KAIS</title>
	<link href="${CPATH}/css/common.css" rel="stylesheet" type="text/css"></link>
	<script language="javascript"	src="${CPATH}/js/sub.common.js"></script>
	<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
</head>

<c:set var="bgcolor" value="background-color=transparent;"/>
<c:choose>
	<c:when test="${titleKey != null}">
		<fmt:message var="TITLE" key="${titleKey}"/>
	</c:when>
	<c:when test="${title != null}">
		<c:set var="TITLE" value="${title}"/>
	</c:when>
	<c:otherwise>
		<c:set var="TITLE" value="${MVC_INFO.TITLE}"/>
	</c:otherwise>
</c:choose>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" style="${bgcolor}">
<c:if test="${!empty(TITLE)}">
<table border=0 cellpadding=0 cellspacing=0 width=95% align=center>
	<tr>
		<td height=30># <b>${TITLE}</b></td>
		<td align=right></td>
	</tr>
</table>
</c:if>
<tiles:insert page="${content.file}" flush="true"/>
</body>
</html>

	