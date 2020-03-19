<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=UTF-8" />
	<title>Login</title>
	<link href='<c:url value="/css/common.css"/>'	rel="stylesheet" type="text/css"></link>
	<script language="javascript" src='<c:url value="/js/sub.common.js"/>'></script>
</head>
<body leftmargin="0" topmargin="0" 
	rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x;" >
<table  height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td  valign="middle" align="center" ><c:import url="${MAF_INFO.file}" /></td>
	</tr>
</table>
<jsp:include  page="/WEB-INF/layout/common/commonTailInfo.jsp" flush="true"/> 
</body>
</html>
