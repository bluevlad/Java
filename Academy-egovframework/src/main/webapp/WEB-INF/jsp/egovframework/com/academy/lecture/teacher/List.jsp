<%--
  Class Name : List.jsp
  Description : 과목 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2023.08.01   KYK        과목관리 리스트 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="member.teacher.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/academy/leture/teacher/List.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_regist(){
	var vFrom = document.listForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/academy/leture/teacher/Regist.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search(){
	var vFrom = document.listForm;
	vFrom.action = "<c:url value='/academy/leture/teacher/List.do' />";
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
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_search(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"> <a href="<c:url value='/academy/leture/teacher/Regist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

<input name="pageIndex" type="hidden" value="<c:out value='${MemberVO.pageIndex}'/>">
</form>

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: ;">
		<col style="width: 15%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="lec.categoryInfo" /></th><!-- 직종 -->
		<th><spring:message code="lec.subjectNm" /></th><!-- 과목명 -->
		<th><spring:message code="lec.teacher.code" /></th><!--강사아이디 -->
		<th class="board_th_link"><spring:message code="lec.teacher.name" /></th><!-- 강사명 -->
		<th><spring:message code="member.isUse" /></th><!--사용여부 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>																																																																																																																																				
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
	  	<!-- 번호 -->
		<td class="lt_text3">${(MemberVO.pageIndex-1) * MemberVO.pageSize + status.count}</td>
		<!-- 사용자구분-->
		<td class="lt_text3"><c:out value="${resultInfo.categoryNm}"/></td>
		<!-- 이메일 -->
		<td class="lt_text3"><c:out value="${resultInfo.subjectNm}"/></td>
		<!-- 사용자아이디 -->
		<td class="lt_text3"><c:out value="${resultInfo.userId}"/></td>
		<!-- 사용자명  -->
		<td class="lt_text3L">
			<a href="<c:url value='/academy/leture/teacher/Detail.do'/>?userId=${resultInfo.userId}"><c:out value='${resultInfo.userNm}'/></a>
    	</td>
		<!-- 사용여부 -->
		<td class="lt_text3"><c:out value="${resultInfo.isUse}"/></td>
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
