<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="<c:url value='/js/egovframework/com/main.js' />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/main_portal.css' />">
<link href="<c:url value='/css/egovframework/com/cmm/main.css' />" rel="stylesheet" type="text/css">
<style type="text/css">
link { color: #666666; text-decoration: none; }
link:hover { color: #000000; text-decoration: none; }
</style>
<div id="lnb">
	<ul class="lnb_title">
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:choose>
			<c:when test="${result.menuOrdr == '1'}">
			<li>
				<strong class="left_title_strong"><strong class="top_title_strong"><c:out value="${result.menuNm}"/></strong></strong><!-- 포털(예제) 메인화면 -->
			</li>
			</c:when>
			<c:otherwise>
		<ul class="2depth">
			<li><a href="${pageContext.request.contextPath}<c:out value="${result.menuPath}"/>" target="_content" class="link"> <c:out value="${result.menuNm}"/></a></li>
		</ul>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</ul>
			<h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.exchangeRateInformation"/></h3><!-- 환율정보 -->
			<iframe src="http://fx.kebhana.com/fxportal/jsp/RS/DEPLOY_EXRATE/fxrate_all.html" width="100%" height="181" border="0" frameborder="no" scrolling="no" marginwidth="0" hspace="0" vspace="0"></iframe>
</div>