<%
/**
 * @Class Name : ExamList.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2020.04.00	rainend		시험리스트
 *  2023.06.00	rainend		시험리스트
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
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ExamVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fn_List() {
    var varFrom = document.getElementById("ExamVO");
    varFrom.action = "<c:url value='/academy/exam/mst/List.do'/>";
    varFrom.submit();
}

function fn_Update(form) {

	if (!validateExamVO(form)){
		return false;
	}
	
    form.action = "<c:url value='/academy/exam/mst/Update.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){
		form.submit();
    }
}
/* ********************************************************
 * 설문지문항정보 팝업창열기
 ******************************************************** */
function fn_addSet(examId){
  	window.showModalDialog("<c:url value='/academy/exam/mst/SetListPopup.do' />?examId="+examId, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
}
</script>
</head>

<body>
<form:form commandName="ExamVO" method="post" action="${pageContext.request.contextPath}/academy/exam/mst/Update.do' />" onSubmit="fn_Update(document.forms[0]); return false;">
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
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		<c:set var="inputF"><spring:message code="exam.flag.F" /></c:set>
		<c:set var="inputT"><spring:message code="exam.flag.T" /></c:set>
		<c:set var="inputE"><spring:message code="exam.flag.E" /></c:set>
		<c:set var="inputU"><spring:message code="exam.flag.U" /></c:set>
	
		<!-- 시험코드 -->
	    <input type="hidden" name="examId" id="examId" value="${ExamVO.examId}" />
		<!-- 시험명 -->
		<c:set var="title"><spring:message code="exam.ExamNm" /></c:set>
		<tr>
			<th><label for="examNm">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="examNm" title="${title} ${inputTxt}" style="width:90%" />
				<div><form:errors path="examNm" cssClass="error" /></div> 
			</td>
		</tr>
		<c:set var="title"><spring:message code="exam.examYear" /></c:set>
		<tr>
			<th><label for="examYear">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="examYear" title="${title} ${inputTxt}" style="width:100px" />
				<div><form:errors path="examYear" cssClass="error" /></div> 
			</td>
		</tr>
		<c:set var="title"><spring:message code="exam.examRound" /></c:set>
		<tr>
			<th><label for="examRound">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="examRound" title="${title} ${inputTxt}" style="width:40px" />
				<div><form:errors path="examRound" cssClass="error" /></div> 
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
		<!-- 개설여부 -->
		<c:set var="title"><spring:message code="exam.useFlag"/></c:set>
		<tr>
			<th><label for="useFlag">${title}</label></th>
			<td class="left">
				<form:select path="useFlag" id="isUse" title="${title} ${inputTxt}">
                	<!--<option value="" label="${inputSelect}"/>-->
                    <form:option value="F" label=" ${inputF}"/>
                    <form:option value="T" label="${inputT}"/>
                    <form:option value="E" label=" ${inputE}"/>
                    <form:option value="U" label="${inputU}"/>
                </form:select>
                <div><form:errors path="useFlag" cssClass="error"/></div>
			</td>
		</tr>
	</tbody>
	</table>
	
	<table class="wTable">
		<colgroup>
			<col style="width:25%;">
			<col style="width: ;">		
			<col style="width:25%;">
			<col style="width: ;">		
		</colgroup>
		<tbody >
		<tr>
			<c:set var="title"><spring:message code="exam.set.setId"/></c:set>
			<th><label for="itemTitle">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="setId" id="setId" value="${ExamSet.setId}" style="width:98%;" readonly="true" onclick="fn_addSet('${ExamVO.examId}');" /></td>
			<c:set var="title"><spring:message code="exam.set.setTitle"/></c:set>
			<th><label for="itemTitle">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="setTitle" id="setTitle" value="${ExamSet.setTitle}" style="width:98%;" /></td>
		</tr>
		</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/academy/exam/mst/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
