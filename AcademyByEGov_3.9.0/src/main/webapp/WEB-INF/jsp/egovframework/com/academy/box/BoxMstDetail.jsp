<%
/**
 * @Class Name : BoxMstList.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2020.05.21	rainend		사물함 정보
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
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 사물함 관리 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="BoxVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fnList() {
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/box/mst/List.do'/>";
    varFrom.submit();
}

function fnUpdate() {
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/box/mst/update.do'/>";
    if(confirm("<spring:message code="common.update.msg" />")){
		varFrom.submit();
    }
}

function fnDelete() {
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/box/mst/delete.do'/>";
    if(confirm("<spring:message code="common.delete.msg" />")){
		varFrom.submit();
    }
}

function fnRent(boxnum, rentseq) {
    var varFrom = document.getElementById("BoxVO");
    varFrom.boxNum.value = boxnum;
    varFrom.rentSeq.value = rentseq;
    varFrom.action = "<c:url value='/box/rent/Detail.do'/>";
	varFrom.submit();
}
</script>
</head>

<body>
<form:form commandName="BoxVO" method="post" action="${pageContext.request.contextPath}/box/mst/update.do' />" onSubmit="fnUpdate(); return false;">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 15%;">
		<col style="width: 35%;">
		<col style="width: 15%;">
		<col style="width: 35%;">
	</colgroup>
	<tbody>
	
		<!-- 검색조건 유지 -->
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<tr>
		<!-- 사물함명 -->
		<c:set var="title"><spring:message code="box.boxNm" /></c:set>
			<th><label for="boxNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="boxCd" title="${title} ${inputTxt}" style="width:70px;" readonly="true" />
				<form:hidden path="boxNum" title="${title} ${inputTxt}" />
				<form:hidden path="rentSeq" title="${title} ${inputTxt}" />
				<form:input path="boxNm" title="${title} ${inputTxt}" style="width:150px;" readonly="true" />
				<div><form:errors path="boxCd" cssClass="error" /></div> 
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
	<br>
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption>${pageTitle} <spring:message code="title.update" /></caption>
		<tbody>
		<tr>
	        <td width="50" bgcolor="#CCCCCC">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용중</p>
	        </td>
	        <td width="50" bgcolor="#f8dcd4">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>미사용</p>
	        </td>
	        <td width="50" bgcolor="#f3fc65">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>대기중</p>
	        </td>
	        <td width="50" bgcolor="#79f670">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>홀드</p>
	        </td>
	        <td width="50" bgcolor="#f36262">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>고장</p>
	        </td>
		</tr>	        
	</tbody>
	</table>

	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<caption>${pageTitle} <spring:message code="title.update" /></caption>
		<tbody>
		<tr>
		<c:forEach var="boxList" items="${boxList}" varStatus="status">
			<c:choose>
				<c:when test="${boxList.boxFlag eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
				<c:when test="${boxList.boxFlag eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
				<c:when test="${boxList.boxFlag eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
				<c:when test="${boxList.boxFlag eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
				<c:when test="${boxList.boxFlag eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
				<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
			</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<a href="javascript:fnRent('${boxList.boxNum}','${boxList.rentSeq}');"><p align="center">${boxList.boxNum}<br><c:if test="${boxList.boxFlag == 'Y'}">${boxList.userNm}</c:if></p></a>
			</td>
		<c:if test="${((status.index+1) mod 10) eq 0 }">
		</tr>
		<tr>
		</c:if>				
		</c:forEach>
		</tr>
		</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="javascript:fnList();" title="<spring:message code="button.list" /><spring:message code="input.button" />"><spring:message code="button.list" /></a><!-- 목록 --></span>
		<span class="btn_s"><a href="javascript:fnUpdate();" title="<spring:message code="button.save" /><spring:message code="input.button" />" ><spring:message code="button.save" /></a><!-- 저장 -->	</span>
		<span class="btn_s"><a href="javascript:fnDelete();" title="<spring:message code="button.delete" /><spring:message code="input.button" />" ><spring:message code="button.delete" /></a><!-- 삭제 --></span>
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
