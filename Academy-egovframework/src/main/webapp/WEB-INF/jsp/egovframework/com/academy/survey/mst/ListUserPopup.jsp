<%--
  Class Name : EgovQustnrManageListPopup.jsp
  Description : 설문관리 목록 팝업 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
	 2017.07.17          김예영          표준프레임워크 v3.7 개선
	 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="survey.manage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css'/>">
</head>
<body>

<div class="popup">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

	<!-- 목록영역 -->
	<table class="popwTable" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 25%;">
		<col style="width: 30%;">
		<col style="width: 15%;">
	</colgroup>
	<thead>
	<tr>             
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="survey.regId" /></th><!-- 설문기간 -->
		<th><spring:message code="survey.regDt" /></th><!-- 등록자 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${status.count}</td>
		<!-- 설문기간 -->
		<td class="lt_text3">${resultInfo.userId}</td>
	  	<!-- 등록일자  -->
	  	<td class="lt_text3">${fn:substring(resultInfo.regDt, 0, 10)}</td>
	  </tr>	  
	</c:forEach>	
	</tbody>
	</table>
</body>
</html>
