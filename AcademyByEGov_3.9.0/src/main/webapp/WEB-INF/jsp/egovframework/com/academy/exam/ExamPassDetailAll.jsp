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
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 시험답안지 등록 -->
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
    varFrom.action = "<c:url value='/exam/pass/updateAll.do'/>";
    var AnsArr = varFrom.AnsArr1.value + varFrom.AnsArr2.value + varFrom.AnsArr3.value + varFrom.AnsArr4.value;
	varFrom.AnsArr.value = AnsArr;
    if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.submit();
    }
}
</script>
</head>

<body>
<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/exam/pass/updateAll.do' />" onSubmit="fnUpdate(); return false;">
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
	
		<!-- 검색조건 유지 -->
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
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
	<!-- 목록영역 -->
	<br>
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
			<th><spring:message code="exam.item15" /></th><!-- 시험코드 -->
			<th><spring:message code="exam.item610" /></th><!-- 시험코드 -->
			<th><spring:message code="exam.item1115" /></th><!-- 시험코드 -->
			<th><spring:message code="exam.item1620" /></th><!-- 시험코드 -->
		</tr>
		</thead>
		<c:set var="Arr1" value=""/>
		<c:set var="Arr2" value=""/>
		<c:set var="Arr3" value=""/>
		<c:set var="Arr4" value=""/>
		<c:forEach var="passList" items="${passList}">
			<c:if test="${passList.itemNo <= 5 }"><c:set var="Arr1" value="${Arr1}${passList.passAns}"/></c:if>
			<c:if test="${passList.itemNo > 5 && passList.itemNo <= 10 }"><c:set var="Arr2" value="${Arr2}${passList.passAns}"/></c:if>
			<c:if test="${passList.itemNo > 10 && passList.itemNo <= 15 }"><c:set var="Arr3" value="${Arr3}${passList.passAns}"/></c:if>
			<c:if test="${passList.itemNo > 15 && passList.itemNo <= 20 }"><c:set var="Arr4" value="${Arr4}${passList.passAns}"/></c:if>
		</c:forEach>
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
		<span class="btn_s"><a href="<c:url value='/exam/pass/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
