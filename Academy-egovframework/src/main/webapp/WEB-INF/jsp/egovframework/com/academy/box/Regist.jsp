<%--
  Class Name : Regist.jsp
  Description : 사물함 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2023.08.00    rainend          최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="box.title.boxInfo"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="BoxVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){

	var varFrom = document.getElementById("BoxVO");
	varFrom.action = "<c:url value='/academy/box/List.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(form){
	form.action = "<c:url value='/academy/box/Insert.do'/>";

	if(confirm("<spring:message code="common.save.msg" />")){	
		if (!validateBoxVO(form)){
			return false;
		} else{
			form.submit();
		}
	}
}
</script>
</head>

<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
<div class="wTableFrm">
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<!-- 사물함코드 -->
		<c:set var="title"><spring:message code="box.boxCd"/></c:set>
		<tr>
			<th><label for="boxCd">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxCd" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="boxCd" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함명칭 -->
		<c:set var="title"><spring:message code="box.boxNm"/></c:set>
		<tr>
			<th><label for="boxNm">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxNm" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="boxNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함갯수 -->
		<c:set var="title"><spring:message code="box.boxCount"/></c:set>
		<tr>
			<th><label for="boxCount">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxCount" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="boxCount" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 층수 -->
		<c:set var="title"><spring:message code="box.rowCount"/></c:set>
		<tr>
			<th><label for="rowCount">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="rowCount" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="rowCount" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 가로갯수 -->
		<c:set var="title"><spring:message code="box.rowNum"/></c:set>
		<tr>
			<th><label for="rowNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="rowNum" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="rowNum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함대여비용 -->
		<c:set var="title"><spring:message code="box.boxPrice"/></c:set>
		<tr>
			<th><label for="boxPrice">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxPrice" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="boxPrice" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit"/></c:set>
		<tr>
			<th><label for="deposit">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="deposit" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="deposit" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 시작번호 -->
		<c:set var="title"><spring:message code="box.startNum"/></c:set>
		<tr>
			<th><label for="startNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="startNum" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="startNum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 종료번호 -->
		<c:set var="title"><spring:message code="box.endNum"/></c:set>
		<tr>
			<th><label for="endNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="endNum" title="${title} ${inputTxt}" style="width:98%;" />
    			<div><form:errors path="endNum" cssClass="error" /></div>
			</td>
		</tr>
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/box/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>