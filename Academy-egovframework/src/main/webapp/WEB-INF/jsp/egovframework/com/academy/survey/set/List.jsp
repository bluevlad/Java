<%--
	Class Name : EgovQustnrTmplatManageList.jsp
  	Description : 설문템플릿 목록 페이지
  	Modification Information

	author   : rainend
    since    : 2023.04.12
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="survey.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/academy/survey/set/List.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_regist(){
	location.href = "<c:url value='/academy/survey/set/Regist.do' />";
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/academy/survey/set/List.do' />";
	vFrom.submit();

}
</script>
</head>
<body>

<div class="board">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/academy/survey/set/List.do'/>" method="post" onSubmit="fn_search(); return false;">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

	<!-- 설문템플릿관리 목록 -->
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QUSTNR_TMPLAT_DC"  <c:if test="${searchVO.searchCondition == 'QUSTNR_TMPLAT_DC'}">selected="selected"</c:if> ><spring:message code="survey.searchCondition.QUSTNR_TMPLAT_DC" /></option><!-- 템플릿 설명 -->
					<option value="QUSTNR_TMPLAT_TY"  <c:if test="${searchVO.searchCondition == 'QUSTNR_TMPLAT_TY'}">selected="selected"</c:if> ><spring:message code="survey.searchCondition.QUSTNR_TMPLAT_TY" /></option><!-- 템플릿 유형 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrTmplatManage(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"><a href="<c:url value='/academy/survey/set/Regist.do' />"  title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: *;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="survey.set.setId" /></th><!-- 설문세트 코드 -->
		<th><spring:message code="survey.set.setTitle" /></th><!-- 설문세트명 -->
		<th><spring:message code="survey.set.queCount" /></th><!-- 사용여부 -->
		<th><spring:message code="survey.isUse" /></th><!-- 사용여부 -->
		<th><spring:message code="survey.updDt" /></th><!-- 최종수정일시 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 템플릿 번호 -->
		<td class="lt_text3L"><div class="divDotText" style="width:100px;">${resultInfo.setId}</div></td>
		<!-- 템플릿 이름 -->
		<td class="lt_text3"><a href="<c:url value='/academy/survey/set/Detail.do'/>?setId=${resultInfo.setId}">${resultInfo.setTitle}</a></td>
		<!-- 설문개수 -->
		<td class="lt_text3">${resultInfo.queCount}</td>
		<!-- 사용여부 -->
		<td class="lt_text3">${resultInfo.isUse}</td>
		<!-- 수정일자 -->
		<td class="lt_text3">${fn:substring(resultInfo.updDt, 0, 10)}</td>
	  </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	
</div><!-- end div board -->

</body>
</html>
