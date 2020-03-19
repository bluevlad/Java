<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<fmt:setBundle  var="m" basename="resources.msg"/>
<fmt:setBundle var="a" basename="resources.message"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=<fmt:message key='page.charset'/>">
	<link rel="stylesheet" href="${CPATH}/css/st02_common.css" type="text/css">
	<title><fmt:message bundle = "${m}" key="msg.title"/></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style3 {
	font-family: "µ¸¿ò";
	font-weight: bold;
	font-size: 11px;
}
-->
</style>

<script language="javascript" type="text/JavaScript" src="${CPATH}/js/sub.common.js"></script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" >
<tiles:insert page="${content.file}" flush="true"/>
</body>
</html>