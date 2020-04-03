<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/text.css">
<script type='text/javascript'>
	alert('${alertMessage}');
	if ('${returnUrl}' != null && '${returnUrl}' != ""){
		location.href="${returnUrl}";
	} else {
		history.back();
	}
</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
</body>
</html>