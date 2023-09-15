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
<c:set var="pageTitle"><spring:message code="exam.examRequest.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 시험신청정보 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fnList() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/academy/exam/request/List.do'/>";
    varFrom.submit();
}

function fnUpdate() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/academy/exam/request/Update.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.submit();
    }
}
</script>
</head>

<body>
<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/academy/exam/request/Update.do' />" onSubmit="fnUpdate(); return false;">
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
		<c:set var="title"><spring:message code="exam.ExamId" /></c:set>
		<tr>
			<th><label for="examId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="examId" title="${title} ${inputTxt}" style="width:90%;" onclick="fn_addExam();" />
			</td>
		</tr>
		<!-- 사용자아이디 -->
		<c:set var="title"><spring:message code="exam.userId" /></c:set>
		<tr>
			<th><label for="userId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="userId" title="${title} ${inputTxt}" style="width:90%;" />
			</td>
		</tr>
		<!-- 사용자이름 -->
		<c:set var="title"><spring:message code="exam.userNm" /></c:set>
		<tr>
			<th><label for="userNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="userNm" title="${title} ${inputTxt}" style="width:90%;" />
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/academy/exam/request/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
