<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<fmt:setBundle basename="resources.common" scope="request"/>
<html>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=utf-8">
	<head><title><fmt:message key="page.title"/></title>
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