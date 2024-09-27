<%--
  Class Name : Statistics.jsp
  Description : 설문문항 통계보기
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.20    김예영          표준프레임워크 v3.7 개선
    2023.01.01   KYK        설문문항 관리 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="survey.que.manage.Title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uss/olp/opp/online_poll.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){
	var vFrom = document.QustnrQestnManageForm;
	vFrom.action = "<c:url value='/academy/survey/bank/List.do' />";
	vFrom.submit();
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="survey.statistics" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="survey.statistics" /></caption>
	<colgroup>
		<col style="width:30%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문지정보(제목) -->
		<c:set var="title"><spring:message code="survey.que.title"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultDetail.queTitle}"/>
			</td>
		</tr>
		<!-- 질문순번 -->
		<c:set var="title"><spring:message code="survey.queCount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${resultDetail.queCount}"/>
			</td>
		</tr>
		<!-- 질문유형 -->
		<c:set var="title"><spring:message code="survey.queType"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:if test="${resultDetail.queType == 'S'}"><spring:message code="survey.que.QuestS" /></c:if><!-- 단일선택 -->
				<c:if test="${resultDetail.queType == 'M'}"><spring:message code="survey.que.QuestM" /></c:if><!-- 다중선택 -->
	    		<c:if test="${resultDetail.queType == 'T'}"><spring:message code="survey.que.QuestT" /></c:if><!-- 단답 -->
	    		<c:if test="${resultDetail.queType == 'D'}"><spring:message code="survey.que.QuestD" /></c:if><!-- 서술 -->
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="survey.que.desc"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<c:out value="${fn:replace(resultDetail.queDesc , crlf , '<br/>')}" escapeXml="false" />
			</td>
		</tr>
		
		<c:set var="title"><spring:message code="survey.statistics.que"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
		<!-- 설문항목 결과 -->
		<c:forEach items="${statisticsList}" var="resultInfo" varStatus="status1">
		<c:forEach var="v" begin="1" end="${resultDetail.queCount}">
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
				            <c:otherwise>
				            </c:otherwise>
				        </c:choose>
				</dl>
			</c:if>
		</c:forEach>
	    </c:forEach>
			</td>
		</tr>
		<!-- 응답자답변내용 결과 -->		
		<c:set var="title"><spring:message code="comUssOlpQqm.result.respondAnswerCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<table border="0" cellpadding="0" cellspacing="0">
			    <c:forEach items="${answerList}" var="answerList" varStatus="status2">
			    <c:if test="${answerList.userAnsw ne '' && (answerList.queType == 'T' || answerList.queType == 'D') }">
					<tr>
					<td style="border-bottom:1px solid white;">
					<c:out value="${fn:replace(answerList.userAnsw , crlf , '<br/>')}" escapeXml="false" />
					</td>
					</tr>
				</c:if>
			    </c:forEach>
			    </table>
			</td>
		</tr>
	</tbody>
	</table>
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 목록 버튼 -->
		<form name="QustnrQestnManageForm" action="<c:url value='/academy/survey/bank/List.do'/>" method="post" onsubmit="fn_list(); return false;" style="float:left;">
		<input type="submit" class="s_submit" value="<spring:message code='button.list' />" onclick="fn_list(); return false;">
		</form>
		
	</div><div style="clear:both;"></div>
<%-- 설문지정보 상태유지 --%>
</div>

</body>
</html>