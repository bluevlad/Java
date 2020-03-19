<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<html>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=utf-8">
	<head><title><fmt:message key="page.title"/></title>
	<link href="${CPATH}/jsp/dbadmin/style.css" rel="stylesheet" type="text/css"></link>
	<style type="text/css">
	.TOP_LINK {
		font-size: 10px;
		font-family: Fixedsys;
		color: #ffffff;
	}
</style>
<script language="javascript" src="${CPATH}/js/sub.common.js"></script>
</head>
<body  >
<br>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
	<td><tiles:insert page="${MAF_INFO.content.file}" flush="true"/></td>
</tr>
</table>
</body>
</html>