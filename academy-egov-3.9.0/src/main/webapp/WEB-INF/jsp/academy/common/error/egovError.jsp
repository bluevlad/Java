<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/common.css'/>">
<title>공무원, 가장 빠른 합격전략 윌비스</title>
<script language="javascript">
function fncGoAfterErrorPage(){
	//alert('메인 화면으로 이동합니다.');
    document.location.href = "/main/index.html";
}
</script>
</head>
<body>
<a href="#LINK" onClick="fncGoAfterErrorPage();">홈페이지 바로가기</a>
</body>
</html>
