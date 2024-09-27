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
<c:set var="pageTitle"><spring:message code="exam.examManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 시험 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fnList() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/academy/exam/bank/List.do'/>";
    varFrom.submit();
}

function fnInsert() {
	var varFrom = document.getElementById("ExamVO");
	varFrom.action = "<c:url value='/academy/exam/bank/Insert.do'/>";
	if(confirm("<spring:message code="common.save.msg" />")){	
		varFrom.submit();
	}
}
</script>
</head>

<body>

<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/academy/exam/bank/Insert.do' />" onSubmit="fnInsert(); return false;">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		<!-- 시험명 -->
		<c:set var="title"><spring:message code="exam.ExamNm" /></c:set>
		<tr>
			<th><label for="examNm">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="examNm" title="${title} ${inputTxt}" style="width:90%" />
				<div><form:errors path="examNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="exam.isUse"/></c:set>
		<tr>
			<th><label for="isUse">${title}</label></th>
			<td class="left">
				<form:select path="isUse" id="isUse" title="${title} ${inputTxt}">
                	<!--<option value="" label="${inputSelect}"/>-->
                    <form:option value="Y" label=" ${inputYes}"/>
                    <form:option value="N" label="${inputNo}"/>
                </form:select>
                <div><form:errors path="isUse" cssClass="error"/></div>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/academy/exam/bank/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div><div style="clear:both;"></div>
	
</div>
</form:form>

</body>
</html>
