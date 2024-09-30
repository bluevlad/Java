<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	$("#form").attr("action", "<c:url value='/manage/onAdvanceReceived.do' />");
	$("#form").submit();
}

function fn_excel() {
	$("#form").attr("action", "<c:url value='/manage/onAdvanceReceivedExcel.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 경영관리 > <strong>선수금관리</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchDate" name="searchDate" value="${searchDate}" maxlength="8" readonly="readonly"/> 
				<!-- <input type="button" onclick="fn_search()" value="검색" /> -->
				<input type="button" onclick="fn_excel()" value="Excel" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th>순번</th>
	        <th>강의명</th>
	        <th>강사명</th>
	        <th>분류</th>
	        <th>일자</th>
	        <th>번호</th>
	        <th>상대처</th>
	        <th>아이디</th>
	        <th>현금</th>
	        <th>카드</th>
	        <th>가상계좌</th>
	        <th>계좌이체</th>
	        <th>계</th>
	        <th>잔여</th>
	        <th>총수</th>
	        <th>잔여분</th>
	        <th>요율</th>
	        <th>수수료</th>
	        <th>대상금액</th>
	        <th>요율</th>
	        <th>강사료</th>
	        <th>잔여분</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="22">데이터가 존재하지 않습니다.</td>
        	</c:if>
        	<c:if test="${fn:length(list) > 0 }">
			<tr>
				<td colspan="8"><font color="red">합계</font></td>
				<td><fmt:formatNumber value="${pay100_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay110_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay120_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay130_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_period}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${period}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_price}" groupingUsed="true"/></td>
				<td></td>
				<td><fmt:formatNumber value="${susu_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${real_price}" groupingUsed="true"/></td>
				<td></td>
				<td><fmt:formatNumber value="${teacher_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_price2}" groupingUsed="true"/></td>
			</tr>
			</c:if>

			<c:set var="rows" value="0" />
        	<c:forEach items="${list}" var="item" varStatus="loop">
			<c:set var="rows" value="${rows+1}" />
			<tr>
				<td>${rows}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td>${item.SUBJECT_TEACHER}</td>
				<td>${item.LEC_TYPE_CHOICE}</td>
				<td>${item.ORDER_DATE}</td>
				<td>${item.ORDERNO}</td>
				<td>${item.USER_NM}</td>
				<td>${item.USER_ID}</td>
				<td><fmt:formatNumber value="${item.PAY100_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.PAY110_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.PAY120_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.PAY130_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.AFTER_PERIOD}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.PERIOD}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.AFTER_PRICE}" groupingUsed="true"/></td>
				<td>${item.SUSU_RATE}</td>
				<td><fmt:formatNumber value="${item.SUSU_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.REAL_PRICE}" groupingUsed="true"/></td>
				<td>${item.PAYMENT}%</td>
				<td><fmt:formatNumber value="${item.TEACHER_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.AFTER_PRICE2}" groupingUsed="true"/></td>
			</tr>
			</c:forEach>
        	<c:if test="${fn:length(list) > 0 }">
			<tr>
				<td colspan="8"><font color="red">합계</font></td>
				<td><fmt:formatNumber value="${pay100_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay110_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay120_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${pay130_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_period}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${period}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_price}" groupingUsed="true"/></td>
				<td></td>
				<td><fmt:formatNumber value="${susu_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${real_price}" groupingUsed="true"/></td>
				<td></td>
				<td><fmt:formatNumber value="${teacher_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${after_price2}" groupingUsed="true"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	     
    <!-- //테이블--> 
</form>
</div>    

</html>