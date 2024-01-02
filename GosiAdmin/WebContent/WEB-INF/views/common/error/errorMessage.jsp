<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/common.css'/>">
<title>공무원, 가장 빠른 합격전략 윌비스</title>
<script type='text/javascript'>
	alert('${message}');
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