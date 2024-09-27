<%--
  Class Name : EgovQustnrTmplatManageDetail.jsp
  Description : 설문템플릿 상세보기
  Modification Information

    author   : rainend
    since    : 2023.04.12

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="exam.examManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){
	location.href = "<c:url value='/academy/exam/set/List.do' />";
}
/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_insert(){
	document.ExamForm.action = "<c:url value='/academy/exam/set/Insert.do'/>";
	document.ExamForm.submit();
}
</script>
</head>
<body>
<form name="ExamForm" id="ExamForm" method="post" action="" onSubmit="fn_insert(); return false;">
	<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 상세조회 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:25%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="exam.set.setTitle"/></c:set>
		<tr>
			<th><label for="itemTitle">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="setTitle" value="" style="width:98%;" /></td>
		</tr>
		<!-- 템플릿 설명 -->	
		<c:set var="title"><spring:message code="exam.set.setDesc" /></c:set>
		<tr>
			<th><label for="answer">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><textarea name="setDesc" style="width:98%;height:100px;"></textarea></td>
		</tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="exam.isUse" /></c:set>
		<tr>
			<th><label for="isUse">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select name="isUse" cssClass="txt" style="width:80px;">
	        		<option value="Y" selected>사용</option>
	        		<option value="N">미사용</option>
                </select>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 저장버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
		<!-- 목록버튼 -->	
		<span class="btn_s"><a href="<c:url value='/academy/exam/set/List.do' />" title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>

	</div>
</form>

</body>
</html>
