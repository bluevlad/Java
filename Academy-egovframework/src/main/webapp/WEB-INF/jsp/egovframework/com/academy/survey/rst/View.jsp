<%--
  Class Name : Detail.jsp
  Description : 설문관리 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
    2008.03.09    장동한          최초 생성
    2017.07.19    김예영          표준프레임워크 v3.7 개선
	2023.01.01   KYK        설문문항 관리 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="survey.manage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="surveyVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){
	location.href = "<c:url value='/academy/survey/mst/List.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(form){

	form.action = "<c:url value='/academy/survey/rst/Insert.do'/>";
	form.submit();
}

function fn_chk(chk_vak)  {
	const query = 'input[name='+chk_vak+']:checked';
  	const selectedEls = 
    document.querySelectorAll(query);
  
	// 선택된 목록에서 value 찾기
  	let result = '';
  	selectedEls.forEach((el) => {
    	result += el.value + ';';
  	});
	chk_val.value = result; 
}
</script>
</head>

<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form name="listForm" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
 
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody>
		<!-- 설문제목 -->
		<c:set var="title"><spring:message code="survey.title"/></c:set>
		<tr>
			<th><spring:message code="survey.title"/></th>
			<td class="left">${resultDetail.surveyTitle}</td>
		</tr>
		<!-- 설문작성안내 내용 -->	
		<tr>
			<th><spring:message code="survey.Desc"/></th>
			<td class="left">${resultDetail.surveyDesc}</td>
		</tr>
		<!-- 설문대상 -->
		<tr>
			<th><spring:message code="survey.Target"/></th>
			<td class="left">
				<c:forEach items="${comCode034}" var="comCode" varStatus="status">
					<c:if test="${comCode.code == resultDetail.surveyTarget}">${comCode.codeNm}</c:if>
				</c:forEach>
			</td>
		</tr>
		<!-- 설문기간 -->
		<tr>
			<th><spring:message code="survey.date"/></th>
			<td class="nopd">${resultDetail.surveySdat} ~ ${resultDetail.surveyEdat}</td>
		</tr>
		<!-- 템플릿 유형 -->
		<tr>
			<th><spring:message code="survey.set.setId"/></th>
			<td class="left">
  				<table border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${SurveySetItem}" var="SurveySetItem" varStatus="status">
					<input type="hidden" name="v_queId" value="${SurveySetItem.queId}"/>
					<tr>
						<th class="center">${SurveySetItem.queTitle}</th>
					</tr>
				    <tr>
				    	<td>
				    	<c:if test="${SurveySetItem.queType == 'S' || SurveySetItem.queType == 'M'}">
					    <c:forEach var="v" begin="1" end="${SurveySetItem.queCount}" varStatus="status">
				    	<c:if test="${SurveySetItem.queType == 'S'}"><input type="radio" name="userAnsw_${SurveySetItem.queId}" value="${v}"></c:if>
				    	<c:if test="${SurveySetItem.queType == 'M'}"><input type="checkbox" name="userAnsw_${SurveySetItem.queId}" value="${v}" onclick="fn_chk('userAnsw_${SurveySetItem.queId}');"></c:if>
				        <c:choose>
				            <c:when test="${status.count == 1}">${SurveySetItem.queViw1}</c:when>
				            <c:when test="${status.count == 2}">${SurveySetItem.queViw2}</c:when>
				            <c:when test="${status.count == 3}">${SurveySetItem.queViw3}</c:when>
				            <c:when test="${status.count == 4}">${SurveySetItem.queViw4}</c:when>
				            <c:when test="${status.count == 5}">${SurveySetItem.queViw5}</c:when>
				            <c:when test="${status.count == 6}">${SurveySetItem.queViw6}</c:when>
				            <c:when test="${status.count == 7}">${SurveySetItem.queViw7}</c:when>
				            <c:when test="${status.count == 8}">${SurveySetItem.queViw8}</c:when>
				            <c:when test="${status.count == 9}">${SurveySetItem.queViw9}</c:when>
				            <c:when test="${status.count == 10}">${SurveySetItem.queViw10}</c:when>
				        </c:choose>
		    			</c:forEach>
		    			</c:if>
				    	<c:if test="${SurveySetItem.queType == 'T'}"><input type="text" name="userAnsw_${SurveySetItem.queId}" value="" style="width:98%;" /></c:if>
				    	<c:if test="${SurveySetItem.queType == 'D'}"><textarea name="userAnsw_${SurveySetItem.queId}" style="width:98%;height:100px;"></textarea></c:if>
				        </td>
				    </tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</tbody>
	</table>
<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/survey/mst/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="surveyId" type="hidden" value="${resultDetail.surveyId}">
</form>

</body>
</html>