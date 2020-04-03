<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.bpopup.min2.js' />"></script>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchStartDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("searchEndDate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	var date1 = parseInt($("#searchStartDate").val());
	var date2 = parseInt($("#searchEndDate").val());
	if(date1 > date2) {
		alert("검색일 설정이 잘못되었습니다.\n검색시작일은 검색종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	go_popup();
	$("#form").attr("action", "<c:url value='/manage/offTeacherPay.do' />");
	$("#form").submit();
}

function fn_detail(id, name) {
	$("#searchTeacher").val(id);
	$("#searchTeacherName").val(name);
	$("#form").attr("action", "<c:url value='/manage/offTeacherPayDetail.do' />");
	$("#form").submit();
}

function fn_excel(id, name) {
	$("#searchTeacher").val(id);
	$("#searchTeacherName").val(name);
	$("#form").attr("action", "<c:url value='/manage/offTeacherPayExcel.do' />");
	$("#form").submit();
}

function go_popup() {	
	$('#rd-popup').bPopup();
}

function go_popup_close() {
	$('#rd-popup').bPopup().close();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="searchTeacher" name="searchTeacher" value=""/>

	<h2>● 경영관리 > <strong>강사료관리</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" maxlength="8" readonly="readonly"/>
            </td>
            <th width="15%">강사명</th>
            <td>
            	<input type="text" id="searchTeacherName" name="searchTeacherName" value="${searchTeacherName}" /> 
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="80">순번</th>
	        <th width="120">강사명</th>
	        <th>수강료 합계</th>
	        <th>강사 지급액</th>
	        <th width="120">수강인원</th>
	        <th width="80">엑셀</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="6">데이터가 존재하지 않습니다.</td>
        	</c:if>
        	<c:if test="${fn:length(list) > 0 }">
			<tr>
				<td colspan="2"><font color="red">합계</font></td>
				<td><font color="red"><fmt:formatNumber value="${totalPrice}" groupingUsed="true"/></font></td>
				<td><font color="red"><fmt:formatNumber value="${totalTeacherPrice}" groupingUsed="true"/></font></td>
				<td><font color="red"><fmt:formatNumber value="${totalCount}" groupingUsed="true"/></font></td>
				<td></td>
			</tr>
			</c:if>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${loop.index + 1}</td>
				<td><a href="javascript:fn_detail('${item.TEACHER_ID}','${item.TEACHER_NAME}');">${item.TEACHER_NAME}</a></td>
				<td><fmt:formatNumber value="${item.TOTAL_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.REAL_MONEY}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.USER_COUNT}" groupingUsed="true"/></td>
				<td><input type="button" value="Excel" onclick="fn_excel('${item.TEACHER_ID}','${item.TEACHER_NAME}');""></td>
			</tr>
			</c:forEach>
        	<c:if test="${fn:length(list) > 0 }">
			<tr>
				<td colspan="2"><font color="red">합계</font></td>
				<td><font color="red"><fmt:formatNumber value="${totalPrice}" groupingUsed="true"/></font></td>
				<td><font color="red"><fmt:formatNumber value="${totalTeacherPrice}" groupingUsed="true"/></font></td>
				<td><font color="red"><fmt:formatNumber value="${totalCount}" groupingUsed="true"/></font></td>
				<td></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	     
    <!-- //테이블--> 
</form>
</div>    
<div id="rd-popup" class="rd-Pstyle"  style="visibility:hidden;"><span class="b-close"></span></div>
</html>