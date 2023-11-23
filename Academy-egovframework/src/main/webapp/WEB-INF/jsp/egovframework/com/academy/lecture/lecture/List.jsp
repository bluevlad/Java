<%--
  Class Name : List.jsp
  Description : 강의 목록 페이지
  Modification Information

       수정일               수정자            수정내용
    ----------   --------   ---------------------------
    2023.11.01   KYK        강의관리 리스트 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="lec.Lecture.title"/></c:set>
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
	document.listForm.action = "<c:url value='/academy/leture/lecture/List.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_regist(){
	var vFrom = document.listForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/academy/leture/lecture/Regist.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search(){
	var vFrom = document.listForm;
	vFrom.action = "<c:url value='/academy/leture/lecture/List.do' />";
	vFrom.submit();
}

// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	$("#frm").submit();
}

function fn_goLecType(val){
	$("#LEC_TYPE_CHOICE").val(val);
	if(val=="D"){
		$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	} else if(val=="Y"){
		$("#frm").attr("action", "<c:url value='/lecture/yearlist.do' />");
	} else if(val=="F"){
		$("#frm").attr("action", "<c:url value='/lecture/freelist.do' />");
	}else{
		$("#frm").attr("action", "<c:url value='/lecture/jongList.do' />");
	}
	$("#frm").submit();	
}

//VOD,PMP,동영상 유/무 팝업
function fn_PayListPop(gubn, type, leccode, bridge_leccode){
	window.open('<c:url value="/lecture/payList.pop"/>?SEARCHPAYYN=' + gubn + '&SEARCHOPENPAGE=L&SEARCHPAYTYPE=' + type + '&LECCODE=' + leccode + '&BRIDGE_LECCODE=' + bridge_leccode , '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// VOD,PMP,동영상 클릭시 팝업
function fn_MovieDataViewPop(val1, val2, val3){
	window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
}
</script>
</head>
<!-- 자바스크립트 경고 태그  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="" method="post" onSubmit="fn_search(); return false;">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code='common.searchCondition.msg' />">
		<ul>
			<!-- 검색키워드 및 조회버튼 -->
	          	<label for="SEARCHKIND"></label>
	            <select name="SEARCHKIND" id="SEARCHKIND">
					<option value="">--직종검색--</option>
					<c:forEach items="${kindlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	            </select>
	            <label for="SEARCHFORM"></label>
	           	<select name="SEARCHFORM" id="SEARCHFORM">
					<option value="">--학습형태검색--</option>
					<c:forEach items="${formlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	          </select>
			<li>
				<!-- 조회버튼 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code='title.search' /> <spring:message code='input.input' />" value="<c:out value='${searchKeyword}'/>"  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code='button.inquire' />" title="<spring:message code='title.inquire' /> <spring:message code='input.button' />" onclick="fn_search(); return false;" />
				<!-- 등록버튼 -->
				<span class="btn_b"> <a href="<c:url value='/academy/leture/subject/Regist.do'/>" title="<spring:message code='button.create' /> <spring:message code='input.button' />"><spring:message code="button.create" /></a></span> 
			</li>
		</ul>
	</div>

<input type="hidden" id="lecTypeChoice" name="lecTypeChoice" value="${lecTypeChoice}">
<input type="hidden" id="bridgeLeccode" name="bridgeLeccode" value="">
<input type="hidden" id="leccode" name="leccode" value="">
</form>

    <ul class="lecWritheTab">
    	<li><a href="javascript:fn_goLecType('D');" <c:if test="${lecTypeChoice eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${lecTypeChoice eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${lecTypeChoice eq 'P'}">class="active"</c:if>>패키지</a></li>
        <li><a href="javascript:fn_goLecType('Y');" <c:if test="${lecTypeChoice eq 'Y'}">class="active"</c:if>>연회원패키지</a></li>
    </ul>       
    
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 7%;">
		<col style="width: 7%;">
		<col style="width: 7%;">
		<col style="width: 7%;">
		<col style="width: ;">
		<col style="width: 7%;">
		<col style="width: 10%;">
		<col style="width: 5%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="lec.categoryInfo" /></th><!-- 직종 -->
		<th><spring:message code="lec.subjectNm" /></th><!-- 과목명 -->
		<th><spring:message code="lec.lecture.leccode" /></th><!--강의코드 -->
		<th><spring:message code="lec.teacher.name" /></th><!--강사명 -->
		<th class="board_th_link"><spring:message code="lec.lecture.subjectTitle" /></th><!-- 강의명 -->
		<th><spring:message code="lec.form.name" /></th><!--강의형태 -->
		<th><spring:message code="lec.regDt" /></th><!--등록일 -->
		<th><spring:message code="lec.isUse" /></th><!--사용여부 -->
	</tr>
	</thead>
	<tbody class="ov">	
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>																																																																																																																																				
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	  <tr>
		<td class="lt_text3">${(LectureVO.pageIndex-1) * LectureVO.pageSize + status.count}</td>
		<td class="lt_text3"><c:out value="${resultInfo.categoryNm}"/></td>
		<td class="lt_text3"><c:out value="${resultInfo.subjectNm}"/></td>
		<td class="lt_text3"><c:out value="${resultInfo.leccode}"/></td>
		<td class="lt_text3"><c:out value="${resultInfo.subjectTeacherNm}"/></td>
		<td class="lt_text3L"><a href="<c:url value='/academy/leture/lecture/Detail.do'/>?leccode=${resultInfo.leccode}"><c:out value='${resultInfo.subjectTitle}'/></a></td>
		<td class="lt_text3"><c:out value="${resultInfo.formName}"/></td>
		<td class="lt_text3"><c:out value="${resultInfo.regDt}"/></td>
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