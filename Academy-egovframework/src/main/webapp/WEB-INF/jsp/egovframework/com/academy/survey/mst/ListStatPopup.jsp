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
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/olp/opp/online_poll.css' />">
</head>
<body>

<div class="popup">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

	<!-- 목록영역 -->
	<table class="popwTable" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 100%;">
	</colgroup>
	<thead>
	<tr>
		<th>*</th><!-- 번호 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<tr>
		<td>
		<!-- 설문항목 결과 -->
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status1">
			<table>
				<tr>
					<td>${resultInfo.queTitle}</td>
				</tr>
				<tr>
					<td>
						<c:forEach var="v" begin="1" end="${resultInfo.queCount}">
							<dl class="poll_chart">
							<!-- 객관식 중 선택된 항목이 있을 때 -->
							<c:if test="${resultInfo.queType == 'S' || resultInfo.queType == 'M'}">
				        <c:choose>
				            <c:when test="${v == 1}">
				                <c:out value="${resultInfo.queViw1}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans1/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans1}(<fmt:formatNumber type="percent" value="${(resultInfo.ans1/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 2}">
				                <c:out value="${resultInfo.queViw2}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans2/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans2}(<fmt:formatNumber type="percent" value="${(resultInfo.ans2/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 3}">
				                <c:out value="${resultInfo.queViw3}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans3/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans3}(<fmt:formatNumber type="percent" value="${(resultInfo.ans3/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 4}">
				                <c:out value="${resultInfo.queViw4}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans4/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans4}(<fmt:formatNumber type="percent" value="${(resultInfo.ans4/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 5}">
				                <c:out value="${resultInfo.queViw5}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans5/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans5}(<fmt:formatNumber type="percent" value="${(resultInfo.ans5/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 6}">
				                <c:out value="${resultInfo.queViw6}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans6/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans6}(<fmt:formatNumber type="percent" value="${(resultInfo.ans6/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 7}">
				                <c:out value="${resultInfo.queViw7}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans7/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans7}(<fmt:formatNumber type="percent" value="${(resultInfo.ans7/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 8}">
				                <c:out value="${resultInfo.queViw8}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans8/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans8}(<fmt:formatNumber type="percent" value="${(resultInfo.ans8/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 9}">
				                <c:out value="${resultInfo.queViw9}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans9/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans9}(<fmt:formatNumber type="percent" value="${(resultInfo.ans9/resultInfo.ansSum)}"/>)
				            </c:when>
				            <c:when test="${v == 10}">
				                <c:out value="${resultInfo.queViw10}" escapeXml="false" />
								<ul>
									<li>
									<span class="g_bar2">
										<span class="g_org" style="width:${(resultInfo.ans10/resultInfo.ansSum)*100}%;"></span>
									</span>
									</li>
								</ul>
								${resultInfo.ans10}(<fmt:formatNumber type="percent" value="${(resultInfo.ans10/resultInfo.ansSum)}"/>)
				            </c:when>
				        </c:choose>
				</dl>
			</c:if>
					</c:forEach>
	    			</td>
	    		</tr>
	    	</table>
	    </c:forEach>
		</td>
	</tr>
	</tbody>
	</table>
</body>
</html>