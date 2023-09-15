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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="exam.passManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 부서관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fnList() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/exam/pass/List.do'/>";
    varFrom.submit();
}

function fnUpdate() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/exam/pass/update.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.submit();
    }
}
</script>
</head>

<body>
<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/exam/pass/update.do' />" onSubmit="fnUpdate(); return false;">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption>${pageTitle} <spring:message code="title.update" /></caption>
		<colgroup>
			<col style="width: 16%;"><col style="width: ;">
		</colgroup>
		<tbody>
	
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		
		<!-- 시험코드 -->
	    <form:hidden path="examCd" title="${title} ${inputTxt}" style="width:100px;" readonly="true" />
	    <form:hidden path="sbjCd" title="${title} ${inputTxt}" style="width:100px;" readonly="true" />
		<!-- 시험명 -->
		<c:set var="title"><spring:message code="exam.ExamNm" /></c:set>
		<tr>
			<th><label for="examNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">${ExamVO.examNm}	</td>
		</tr>
		<!-- 과목명 -->
		<c:set var="title"><spring:message code="exam.SubjectNm" /></c:set>
		<tr>
			<th><label for="sbjNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">${ExamVO.sbjNm}	</td>
		</tr>
	</tbody>
	</table>
	<br>
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 20%;">
		<col style="width: 20%">
		<col style="width: 20%;">
		<col style="width: 20%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="exam.item1" /></th><!-- 시험코드 -->
		<th><spring:message code="exam.item2" /></th><!-- 시험코드 -->
		<th><spring:message code="exam.item3" /></th><!-- 시험코드 -->
		<th><spring:message code="exam.item4" /></th><!-- 시험코드 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:forEach var="passList" items="${passList}" varStatus="status">
	<tr>
		<td><c:out value="${passList.itemNo}"/></td>
		<td><input type="radio" name="itemNo${passList.itemNo}" id="itemNo${passList.itemNo}" value="1" title="${title} ${inputSelect}" <c:if test="${passList.passAns == 1}">checked</c:if>/></td>
		<td><input type="radio" name="itemNo${passList.itemNo}" id="itemNo${passList.itemNo}" value="2" title="${title} ${inputSelect}" <c:if test="${passList.passAns == 2}">checked</c:if>/></td>
		<td><input type="radio" name="itemNo${passList.itemNo}" id="itemNo${passList.itemNo}" value="3" title="${title} ${inputSelect}" <c:if test="${passList.passAns == 3}">checked</c:if>/></td>
		<td><input type="radio" name="itemNo${passList.itemNo}" id="itemNo${passList.itemNo}" value="4" title="${title} ${inputSelect}" <c:if test="${passList.passAns == 4}">checked</c:if>/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/exam/pass/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
