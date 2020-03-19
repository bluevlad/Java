<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import="java.util.Map"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<fmt:setBundle basename="resources.common" scope="request"/>
<html>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=utf-8">
	<head><title><mfmt:message key="page.title"/></title>
	<link href="<c:url value="/css/dbadmin.css"/>" rel="stylesheet" type="text/css"></link>
	<style type="text/css">
	.TOP_LINK {
		font-size: 10px;
		font-family: Fixedsys;
		color: #ffffff;
	}
</style>
<script language="javascript" src="<c:url value="/js/sub.common.js"/>"></script>
</head>
<body  >
<c:import url="${MAF_INFO.file}"/>
</body>
</html>