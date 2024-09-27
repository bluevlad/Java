<%
/**
 * @Class Name : ExamList.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2020.04.00	rainend		시험리스트
 *  @author rainend
 *  @version 1.0
 *  @see
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="exam.examRequest.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 시험신청 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javaScript" defer="defer">
function fnSelectList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/academy/exam/request/List.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/academy/exam/request/List.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fnSelectList('1');
    }
}
</script>
</head>

<body>

<form name="listForm" method="post"> 
<div class="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li><div style="line-height:4px;">&nbsp;</div><div><spring:message code="exam.exam.searchKeywordText" /> : </div></li><!-- 시험명 -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${ExamVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/academy/exam/request/Regist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 20%">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="exam.orderNo" /></th><!-- 주문번호 -->
		<th class="board_th_link"><spring:message code="exam.reqId" /></th><!-- 응시번호 -->
		<th><spring:message code="exam.userId" /></th><!-- 응시자아이디 -->
		<th><spring:message code="exam.userNm" /></th><!-- 응시자이름 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(examRequestList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="examRequestList" items="${examRequestList}" varStatus="status">
	<tr>
		<td><c:out value="${(ExamVO.pageIndex-1) * ExamVO.pageSize + status.count}"/></td>
		<td><a href="<c:url value='/academy/exam/request/Detail.do'/>?pageIndex=${ExamVO.pageIndex}&searchKeyword=${ExamVO.searchKeyword}&orderNo=${examRequestList.ordNo}"><c:out value="${examRequestList.ordNo}"/></a></td>
		<td><a href="<c:url value='/academy/exam/request/Detail.do'/>?pageIndex=${ExamVO.pageIndex}&searchKeyword=${ExamVO.searchKeyword}&orderNo=${examRequestList.ordNo}"><c:out value="${examRequestList.reqId}"/></a></td>
		<td><c:out value="${examRequestList.userId}"/></td>
		<td><c:out value="${examRequestList.userNm}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<c:if test="${!empty ExamVO.pageIndex }">
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	</c:if>
	
</div><!-- end div board -->

<input name="pageIndex" type="hidden" value="<c:out value='${ExamVO.pageIndex}'/>">
</form>

</body>
</html>
