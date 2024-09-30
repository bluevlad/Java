<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<title>강사검색</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/colorbox/colorbox.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/libs/colorbox/jquery.colorbox.js" />"></script>
<script type="text/javascript">
function goList(page){
	if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/board/teacher/boardTeacherSearchPop.pop' />");
    $("#form").submit();
}
function resetPage(){
	$("#SEARCHTEXT").val('');
	goList(1);
}
function fn_search(){
	goList(1);
}
function fn_getTeacher(id){
	window.opener.$("#PROF_ID").val(id).attr("selected", "selected");
// 	window.opener.$("#PROF_NM_TEXT").val(name);
	window.close();
}
//엔터키 검색
function fn_checkEnter(){
    $('#searchText').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
    
    $('#pageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}
</script>
</head>

<!--content -->
<div id="content_pop">
	<form id="form" name="form" method="post">
	    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
		
		<table class="table04">
	    <tr>
	        <th width="15%">강사명</th>
	        <td>
	            <input class="i_text" title="레이블 텍스트" type="text" name="SEARCHTEXT" id="SEARCHTEXT" size="10" value="${params.SEARCHTEXT}" onKeyPress="fn_checkEnter()" />
	            <input value="강사검색" type="button" id="SEARCH_BTN" onClick="fn_search()" />
	            <input value="RESET" type="button" id="RESET_BTN" onClick="resetPage()" />
	        </td>
	    </tr>
	    </table>
    </form>
    <table class="table04">
        <tr>
            <th scope="col" style="width:100px;">No</th>
            <th scope="col" style="width:150px;">ID</th>
            <th scope="col" style="width:150px;">강사명</th>
            <th scope="col" style="width:200px;">과목명</th>
        </tr>
    <c:if test="${not empty teacher_list}">
        <c:forEach items="${teacher_list}" var="data" varStatus="status">
        <tr>
            <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
            <td>${data.USER_ID}</td>
            <td><a href="javascript:fn_getTeacher('${data.USER_ID}')">${data.USER_NM}</a></td>
            <td>${data.SUBJECT_NM}</td>
        </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty teacher_list}">
        <tr bgColor=#ffffff align=center> 
            <td colspan="4">데이터가 존재하지 않습니다.</td>
        </tr>
    </c:if> 
    </table>
     <!--paging  -->
    <c:if test="${not empty teacher_list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->
</div>
<!--//content -->

