<%--
  Class Name : EgovQustnrManageListPopup.jsp
  Description : 설문관리 목록 팝업 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
	 2017.07.17          김예영          표준프레임워크 v3.7 개선
	 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQmc.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css'/>">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/academy/survey/set/QueListPopup.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_QustnrManage(){
	var vFrom = document.listForm;
	vFrom.action = "<c:url value='/academy/survey/set/QueListPopup.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_add_Que(qestnrId){

	var vFrom = document.subForm;
	vFrom.action = "<c:url value='/academy/survey/set/queInsert.do' />";
	vFrom.queId.value = qestnrId;
	vFrom.submit();
}

function reload(){
	window.opener.location.reload();
}
</script>
</head>
<body onload="reload();">

<div class="popup">

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="ListForm" action="" method="post" onSubmit="fn_egov_search_QustnrManage(); return false;">

	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QUSTNR_SJ"  <c:if test="${searchVO.searchCondition == 'QUSTNR_SJ'}">selected="selected"</c:if> ><spring:message code="comUssOlpQmc.searchCondition.QUSTNR_SJ" /></option><!-- 설문제목 -->
					<option value="FRST_REGISTER_ID"  <c:if test="${searchVO.searchCondition == 'FRST_REGISTER_ID'}">selected="selected"</c:if> ><spring:message code="comUssOlpQmc.searchCondition.FRST_REGISTER_ID" /></option><!-- 등록자 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchVO.searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="btn_style3" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_egov_search_QustnrManage(); return false;" />
				<!-- 등록버튼 -->
			</li>
		</ul>
	</div>

</form>

	<form name="subForm" method="post" action="">
	<input name="setId" id="setId" type="hidden" value="${setId}">
	<input name="queId" id="queId" type="hidden" value="">
	<!-- 목록영역 -->
	<table class="popwTable" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: *;">
		<col style="width: 15%;">
		<col style="width: 10%;">
		<col style="width: 20%;">	
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="survey.que.title" /></th><!-- 설문문항 -->
		<th><spring:message code="survey.queType" /></th><!-- 질문유형 -->
		<th><spring:message code="survey.queCount" /></th><!-- 문항수 -->
		<th>*</th><!-- 선택  -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
		<!-- 질문내용  -->
		<td class="lt_text3L"><c:out value='${resultInfo.queTitle}'/></td>
		<!-- 질문유형 -->
		<td class="lt_text3">
			<c:if test="${resultInfo.queType == 'S'}"><spring:message code="survey.que.QuestS" /></c:if><!-- 단일선택 -->
			<c:if test="${resultInfo.queType == 'M'}"><spring:message code="survey.que.QuestM" /></c:if><!-- 다중선택 -->
    		<c:if test="${resultInfo.queType == 'T'}"><spring:message code="survey.que.QuestT" /></c:if><!-- 단답 -->
    		<c:if test="${resultInfo.queType == 'D'}"><spring:message code="survey.que.QuestD" /></c:if><!-- 서술 -->
		</td>
		<td class="lt_text3"><c:out value="${resultInfo.queCount}"/></td>
		<!-- 통계  -->
	  	<td class="lt_text3">
			<span class="btn_b"><input type="submit" class="btn_submit" style="width:70px;border:solid 0px black;text-align:center;" value="<spring:message code='survey.que.add'/>" onclick="fn_add_Que('${resultInfo.queId}');"></span>
		</td>
      </tr>	  
	</c:forEach>	
	</tbody>
	</table>
	</form>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</div><!-- end div board -->

</body>
</html>
