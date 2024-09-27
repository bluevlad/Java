<%--
  Class Name : List.jsp
  Description : 설문문항 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2008.03.09   장동한            최초 생성
    2017.07.17   김예영            표준프레임워크 v3.7 개선
    2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
    2023.01.01   KYK        설문문항 관리 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="org.json.*" %>
<c:set var="pageTitle"><spring:message code="survey.que.manage.Title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<%

%>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/academy/survey/bank/List.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_QustnrQestnManage(){
	var vFrom = document.listForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/academy/survey/bank/Regist.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 문항통계
 ******************************************************** */
function fn_statistics(queId){
	var vFrom = document.listForm;
	vFrom.queId.value = queId;
	vFrom.action = "<c:url value='/academy/survey/bank/Statistics.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search(){
	var vFrom = document.listForm;
	vFrom.action = "<c:url value='/academy/survey/bank/List.do' />";
	vFrom.submit();
}
</script>
</head>
<body>

<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="" method="post" onSubmit="fn_search(); return false;">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<li>
				<select name="searchCondition" title="<spring:message code='title.searchCondition' /> <spring:message code='input.cSelect' />">
					<option selected value=''>--<spring:message code="input.select" />--</option><!-- 선택하세요 -->
					<option value="QESTN_CN"  <c:if test="${SurveyVO.searchCondition == 'QESTN_CN'}">selected="selected"</c:if> ><spring:message code="comUssOlpQqm.regist.qestnCn" /></option><!-- 질문내용 -->
					<option value="MXMM_CHOISE_CO"  <c:if test="${SurveyVO.searchCondition == 'MXMM_CHOISE_CO'}">selected="selected"</c:if> ><spring:message code="comUssOlpQqm.regist.mxmmChoiseCo" /></option><!-- 최대선택건수 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_search(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"> <a href="<c:url value='/academy/survey/bank/Regist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

<input name="pageIndex" type="hidden" value="<c:out value='${SurveyVO.pageIndex}'/>">
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
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
		<th><spring:message code="table.num" /></th><!-- 순번 -->
		<th class="board_th_link"><spring:message code="survey.que.title" /></th><!-- 질문내용 -->
		<th><spring:message code="survey.queType" /></th><!-- 질문유형 -->
		<th><spring:message code="survey.queCount" /></th><!-- 질문개수 -->
		<th><spring:message code="survey.statistics" /></th><!-- 통계 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>																																																																																																																																				
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(SurveyVO.pageIndex-1) * SurveyVO.pageSize + status.count}</td>
		<!-- 질문내용  -->
		<td class="lt_text3L">
			<a href="<c:url value='/academy/survey/bank/Detail.do'/>?queId=${resultInfo.queId}"><c:out value='${resultInfo.queTitle}'/></a>
    	</td>
		<!-- 질문유형 -->
		<td class="lt_text3">
			<c:if test="${resultInfo.queType == 'S'}"><spring:message code="survey.que.QuestS" /></c:if><!-- 단일선택 -->
			<c:if test="${resultInfo.queType == 'M'}"><spring:message code="survey.que.QuestM" /></c:if><!-- 다중선택 -->
    		<c:if test="${resultInfo.queType == 'T'}"><spring:message code="survey.que.QuestT" /></c:if><!-- 단답 -->
    		<c:if test="${resultInfo.queType == 'D'}"><spring:message code="survey.que.QuestD" /></c:if><!-- 서술 -->
		</td>
		<!-- onLoad="if(this.width>65){this.width=65}" -->
		<!-- 질문항목 -->
		<td class="lt_text3"><c:out value="${resultInfo.queCount}"/></td>
		<!-- 통계  -->
	  	<td class="lt_text3">
			<form name="subForm" method="post" action="<c:url value='/academy/survey/bank/Statistics.do'/>">
			<input name="queId" type="hidden" value="${resultInfo.queId}">
			<input name="queType" type="hidden" value="${resultInfo.queType}">
			<span class="btn_b"><input type="submit" class="btn_submit" style="width:40px;border:solid 0px black;text-align:left;" value="<spring:message code='comUssOlpQqm.value.view'/>" onclick="fn_statistics('${resultInfo.queId}'); return false;"></span><!-- value="보기" -->
			</form>
		</td>
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
