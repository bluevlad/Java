<%
/**
 * @Class Name : DEtail.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2023.05.00	rainend		시험문제 상세정보
 *  @author rainend
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
<c:set var="pageTitle"><spring:message code="exam.examBankManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 부서관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="ExamForm" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function fn_List() {
	location.href = "<c:url value='/academy/exam/bank/List.do'/>";
}

function fn_Update() {
	document.ExamForm.action = "<c:url value='/academy/exam/bank/Update.do'/>";
	document.ExamForm.submit();
}
</script>
</head>

<body>
<form:form name="ExamForm" id="ExamForm" method="post" action="" onSubmit="fn_Update(); return false;">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.detail" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
	
		<!-- 시험문제코드 -->
	    <input type="hidden" name="queId" id="queId" value="${ExamVO.queId}" />
	    
		<!-- 시험문제명 -->
		<c:set var="title"><spring:message code="exam.queTitle" /></c:set>
		<tr>
			<th><label for="queTitle">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="queTitle" value="${ExamVO.queTitle}" style="width:90%;" /></td>
		</tr>
		<!-- 정답 -->
		<c:set var="title"><spring:message code="exam.passAns" /></c:set>
		<tr>
			<th><label for="passAns">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="passAns" value="${ExamVO.passAns}" style="width:90%;" /></td>
		</tr>
		<!-- 문제유형 -->
		<c:set var="title"><spring:message code="exam.queType" /></c:set>
		<tr>
			<th><label for="queType">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select name="queType" style="width:120px;">
                    <option value="S" <c:if test="${ExamVO.queType == 'S'}">selected</c:if>><spring:message code="exam.input.S" /></option>
                    <option value="M" <c:if test="${ExamVO.queType == 'M'}">selected</c:if>><spring:message code="exam.input.M" /></option>
                    <option value="T" <c:if test="${ExamVO.queType == 'T'}">selected</c:if>><spring:message code="exam.input.T" /></option>
                    <option value="D" <c:if test="${ExamVO.queType == 'D'}">selected</c:if>><spring:message code="exam.input.D" /></option>
                </select>
			</td>
		</tr>
		<!-- 지문1 -->
		<c:set var="title"><spring:message code="exam.ansView1" /></c:set>
		<tr>
			<th><label for="ansView1">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansView1" value="${ExamVO.ansView1}" style="width:90%;" /></td>
		</tr>
		<!-- 지문2 -->
		<c:set var="title"><spring:message code="exam.ansView2" /></c:set>
		<tr>
			<th><label for="ansView2">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansView2" value="${ExamVO.ansView2}" style="width:90%;" /></td>
		</tr>
		<!-- 지문3 -->
		<c:set var="title"><spring:message code="exam.ansView3" /></c:set>
		<tr>
			<th><label for="ansView3">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansView3" value="${ExamVO.ansView3}" style="width:90%;" /></td>
		</tr>
		<!-- 지문4 -->
		<c:set var="title"><spring:message code="exam.ansView4" /></c:set>
		<tr>
			<th><label for="ansView4">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansView4" value="${ExamVO.ansView4}" style="width:90%;" /></td>
		</tr>
		<!-- 지문5 -->
		<c:set var="title"><spring:message code="exam.ansView5" /></c:set>
		<tr>
			<th><label for="ansView5">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansView5" value="${ExamVO.ansView5}" style="width:90%;" /></td>
		</tr>
		<!-- 답안해설 -->
		<c:set var="title"><spring:message code="exam.ansDesc" /></c:set>
		<tr>
			<th><label for="ansDesc">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="ansDesc" value="${ExamVO.ansDesc}" style="width:90%;" /></td>
		</tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="exam.isUse" /></c:set>
		<tr>
			<th><label for="isUse">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select name="isUse" cssClass="txt" style="width:80px;">
	        		<option value="Y" <c:if test="${ExamVO.isUse == 'Y'}">selected</c:if>>사용</option>
	        		<option value="N" <c:if test="${ExamVO.isUse == 'N'}">selected</c:if>>미사용</option>
                </select>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/academy/exam/bank/List.do'/>"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
