<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>

<html>
<head>
	<meta http-equiv="Cache-Control" content="no-cache"/> 
	<meta http-equiv="Expires" content="0"/> 
	<meta http-equiv="Pragma" content="no-cache"/> 
	<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8">
	<title>Error</title>
	<SCRIPT>
		alert('<c:out value="${control_action}"/>' + ':\n <mh:out value="${message}" escapeJS="true" />');
		history.back(-1);
	</SCRIPT>
</head>
<body>
</body>
</html>