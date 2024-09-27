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
function fn_modify(){
	document.ExamForm.action = "<c:url value='/academy/exam/set/Update.do'/>";
	document.ExamForm.submit();
}
/* ********************************************************
 * 문항삭제
 ******************************************************** */
function fn_delQue(queid){
	document.ExamForm.queId.value = queid;
	document.ExamForm.action = "<c:url value='/academy/exam/set/queDelete.do'/>";
	document.ExamForm.submit();
}
/* ********************************************************
 * 설문지문항정보 팝업창열기
 ******************************************************** */
function fn_addQue(set_id){
  	window.showModalDialog("<c:url value='/academy/exam/set/QueListPopup.do' />?setId=" + set_id, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
}
</script>
</head>
<body>
<form name="ExamForm" id="ExamForm" method="post" action="" onSubmit="fn_modify(); return false;">
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
		<!-- 입력 -->
		<input type="hidden" name="setId" id="setId" value="${ExamVO.setId}" />
		<input type="hidden" name="queId" id="queId" value="0" />
		
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="exam.set.setTitle"/></c:set>
		<tr>
			<th><label for="itemTitle">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><input type="text" name="setTitle" value="${ExamVO.setTitle}" style="width:98%;" /></td>
		</tr>
		<!-- 템플릿 설명 -->	
		<c:set var="title"><spring:message code="exam.set.setDesc" /></c:set>
		<tr>
			<th><label for="answer">${title}</label> <span class="pilsu">*</span></th>
			<td class="left"><textarea name="setDesc" style="width:98%;height:100px;">${ExamVO.setDesc}</textarea></td>
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
	

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
		<caption>${pageTitle} <spring:message code="title.list" /></caption>
		<colgroup>
			<col style="width: 10%;">
			<col style="width: *;">
			<col style="width: 10%;">
			<col style="width: 20%;">
			<col style="width: 10%;">
			<col style="width: 10%;">
		</colgroup>
		<thead>
		<tr>
			<th><spring:message code="table.num" /></th><!-- 번호 -->
			<th class="board_th_link"><spring:message code="exam.set.setTitle" /></th>
			<th><spring:message code="exam.set.queCount" /></th>
			<th><spring:message code="exam.queType" /></th>
			<th><spring:message code="exam.set.queSeq" /></th>
			<th>*</th>
		</tr>
		</thead>
		<tbody class="ov">	
		<c:forEach items="${ExamSetItem}" var="resultInfo" varStatus="status">
		<tr>
		  	<!-- 번호 -->
		  	<input type="hidden" name="v_queId" value="${resultInfo.queId}"/>
			<td class="lt_text3">${resultInfo.queId}</td>
			<!-- 템플릿 이름 -->
			<td class="lt_text3">${resultInfo.queTitle}</td>
			<!-- 사용여부 -->
			<td class="lt_text3">${resultInfo.queCount}</td>
			<!-- 수정일자 -->
			<td class="lt_text3">
				<c:if test="${resultInfo.queType == 'S'}"><spring:message code="exam.input.S" /></c:if><!-- 단일선택 -->
				<c:if test="${resultInfo.queType == 'M'}"><spring:message code="exam.input.M" /></c:if><!-- 다중선택 -->
	    		<c:if test="${resultInfo.queType == 'T'}"><spring:message code="exam.input.T" /></c:if><!-- 단답 -->
	    		<c:if test="${resultInfo.queType == 'D'}"><spring:message code="exam.input.D" /></c:if><!-- 서술 -->
			</td>
		  	<!-- 번호 -->
			<td class="lt_text3"><input type="text" name="v_queSeq_${resultInfo.queId}" value="${resultInfo.queSeq}" style="width:30px;"/></td>
			<td class="lt_text3"><div class="btn"><input type="button" class="s_submit" name="delQue" value="삭제" onclick="javascript:fn_delQue('${resultInfo.queId}');"></div></td>
		</tr>	  
		</c:forEach>	
        <div class="item" id="queAdd">
		<tr>
			<td class="lt_text3"></td>
			<td class="lt_text3"></td>
			<td class="lt_text3"></td>
			<td class="lt_text3"></td>
			<td class="lt_text3"></td>
			<td class="lt_text3"><div class="btn"><input type="button" class="s_submit" name="addQue" value="추가" onclick="javascript:fn_addQue('${ExamVO.setId}');"></div></td>
		</tr>
		</div>  
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
