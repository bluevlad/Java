<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eGovFrame 공통 컴포넌트</title>
<link href="<c:url value='/css/egovframework/com/cmm/main.css' />" rel="stylesheet" type="text/css">
<style type="text/css">
link { color: #666666; text-decoration: none; }
link:hover { color: #000000; text-decoration: none; }
</style>
</head>
<body>
<div id="lnb">
<ul class="lnb_title">
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:if test="${result.progrmFileNm == 'dir' && result.cntntsUseAt == 'Y' }">
		<c:set var="pMenu" value="${result.menuNo}"/>
			<li>
				<strong class="left_title_strong"><strong class="top_title_strong"><c:out value="${result.menuNo}"/>.<c:out value="${result.menuNm}"/></strong></strong><!-- 포털(예제) 메인화면 -->
			</li>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<c:if test="${result.progrmFileNm != 'dir' && pMenu == result.upperMenuId && result.cntntsUseAt == 'Y'}">
				<ul class="2depth">
				<li><a href="${pageContext.request.contextPath}<c:out value="${result.chkURL}"/>" target="_content" class="link"> <c:out value="${result.menuNo}"/>.<c:out value="${result.menuNm}"/></a></li>
				</ul>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
</ul>

</body>
</html>
