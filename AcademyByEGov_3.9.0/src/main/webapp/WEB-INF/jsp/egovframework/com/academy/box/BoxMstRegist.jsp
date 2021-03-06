<%
/**
 * @Class Name : BoxMstRegist.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2020.05.25	rainend		사물함 등록
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
<c:set var="pageTitle"><spring:message code="box.boxManage.title"/></c:set>
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
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/ox/mst/List.do'/>";
    varFrom.submit();
}

function fnInsert() {
	var varFrom = document.getElementById("BoxVO");
	varFrom.action = "<c:url value='/box/mst/insert.do'/>";
	if(confirm("<spring:message code="common.save.msg" />")){	
		varFrom.submit();
	}
}
</script>
</head>

<body>

<form:form commandName="BoxVO" method="post" action="${pageContext.request.contextPath}/box/mst/insert.do' />" onSubmit="fnInsert(); return false;">
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
		<tr>
		<!-- 사물함명 -->
		<c:set var="title"><spring:message code="box.boxNm" /></c:set>
			<th><label for="boxNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="boxCd" title="${title} ${inputTxt}" style="width:70px;" />
				<form:hidden path="boxNum" title="${title} ${inputTxt}" />
				<form:hidden path="rentSeq" title="${title} ${inputTxt}" />
				<form:input path="boxNm" title="${title} ${inputTxt}" style="width:80%;" />
				<div><form:errors path="boxNm" cssClass="error" /></div> 
			</td>
		<!-- 사물함갯수 -->
		<c:set var="title"><spring:message code="box.boxCount" /></c:set>
			<th><label for="boxCount">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="boxCount" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="boxCount" cssClass="error" /></div>       
			</td>
		</tr>
		<tr>
		<!-- 층수 -->
		<c:set var="title"><spring:message code="box.highNum" /></c:set>
			<th><label for="highNum">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="highNum" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="highNum" cssClass="error" /></div>       
			</td>
		<!-- 가로갯수 -->
		<c:set var="title"><spring:message code="box.rowNum" /></c:set>
			<th><label for="rowNum">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="rowNum" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="rowNum" cssClass="error" /></div>       
			</td>
		</tr>
		<tr>
		<!-- 이용요금 -->
		<c:set var="title"><spring:message code="box.boxPrice" /></c:set>
			<th><label for="boxPrice">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="boxPrice" title="${title} ${inputTxt}" style="width:100px;" />
				<div><form:errors path="boxPrice" cssClass="error" /></div>       
			</td>
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit" /></c:set>
			<th><label for="deposit">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="deposit" title="${title} ${inputTxt}" style="width:100px;" />
				<div><form:errors path="deposit" cssClass="error" /></div>       
			</td>
		</tr>
		<tr>
		<!-- 사물함 시작번호 -->
		<c:set var="title"><spring:message code="box.startNum" /></c:set>
			<th><label for="startNum">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="startNum" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="startNum" cssClass="error" /></div>       
			</td>
		<!-- 사물함 종료번호 -->
		<c:set var="title"><spring:message code="box.endNum" /></c:set>
			<th><label for="endNum">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="endNum" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="endNum" cssClass="error" /></div>       
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/box/mst/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div><div style="clear:both;"></div>
	
</div>
</form:form>

</body>
</html>
