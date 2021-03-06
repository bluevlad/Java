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
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 시험 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fnList() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/exam/pass/List.do'/>";
    varFrom.submit();
}

function fnInsert() {
	var varFrom = document.getElementById("ExamVO");
	varFrom.action = "<c:url value='/exam/pass/insertAll.do'/>";
    var AnsArr = varFrom.AnsArr1.value + varFrom.AnsArr2.value + varFrom.AnsArr3.value + varFrom.AnsArr4.value;
	varFrom.AnsArr.value = AnsArr;
	if(confirm("<spring:message code="common.save.msg" />")){	
		varFrom.submit();
	}
}
</script>
</head>

<body>

<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/exam/pass/insertAll.do' />" onSubmit="fnInsert(); return false;">
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
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 시험코드 -->
		<c:set var="title"><spring:message code="exam.ExamNm"/></c:set>
		<tr>
			<th><label for="examCd">${title}</label></th>
			<td class="left">
                  <form:select path="examCd" id="examCd" title="${title} ${inputSelect}">
                       <form:option value="" label="${inputSelect}"/>
                       <form:options items="${examList}" itemValue="examCd" itemLabel="examNm"/>
                    </form:select>
                    <div><form:errors path="examCd" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 과목코드 -->
		<c:set var="title"><spring:message code="exam.SubjectNm"/></c:set>
		<tr>
			<th><label for="sbjCd">${title}</label></th>
			<td class="left">
                <form:select path="sbjCd" id="examCd" title="${title} ${inputSelect}">
                	<form:option value="" label="${inputSelect}"/>
                    <form:options items="${subjectList}" itemValue="sbjCd" itemLabel="sbjNm"/>
				</form:select>
                    <div><form:errors path="sbjCd" cssClass="error"/></div>
			</td>
		</tr>
	</tbody>
	</table>
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
		<tr>
			<td><c:out value="${i}"/></td>
			<td><input type="text" name="AnsArr1" id="AnsArr1" value="${Arr1}" title="${title} ${inputTxt}"/></td>
			<td><input type="text" name="AnsArr2" id="AnsArr2" value="${Arr2}" title="${title} ${inputTxt}"/></td>
			<td><input type="text" name="AnsArr3" id="AnsArr3" value="${Arr3}" title="${title} ${inputTxt}"/></td>
			<td><input type="text" name="AnsArr4" id="AnsArr4" value="${Arr4}" title="${title} ${inputTxt}"/></td>
		</tr>
		</tbody>
	</table>
	<input type="hidden" name="AnsArr" id="AnsArr" value=""/>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/exam/pass/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div><div style="clear:both;"></div>
	
</div>
</form:form>

</body>
</html>
