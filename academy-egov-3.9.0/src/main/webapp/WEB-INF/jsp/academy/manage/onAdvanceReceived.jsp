<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
	<h2>● 경영관리 > <strong>온라인선수금관리</strong></h2>

<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchDate" name="searchDate" value="${searchDate}" maxlength="8" readonly="readonly"/> 
				<input type="button" onclick="fn_search()" value="검색" />
				<input type="button" onclick="fn_excel()" value="Excel" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table01">
		<tr>
	        <th>순번</th>
	        <th>아이디</th>
	        <th>이름</th>
	        <th>결제수단</th>
	        <th>상품구분</th>
	        <th>주문번호</th>
	        <th>상품코드</th>
	        <th>상품명</th>
	        <th>강의코드</th>
	        <th>강의명</th>
	        <th>교수명</th>
	        <th>결제일</th>
	        <th>결제금액</th>
	        <th>수수료율</th>
	        <th>수수료공제금액</th>
	        <th>안분율</th>
	        <th>안분금액</th>
	        <th>배분율</th>
	        <th>배분금액</th>
	        <th>수강시작일</th>
	        <th>수강종료일</th>
	        <th>수강일수</th>
	        <th>잔여수강일수</th>
	        <th>사용일수</th>
	        <th>잔여금액</th>
	        <th>사용금액</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="30">데이터가 존재하지 않습니다.</td>
        	</c:if>
			<c:set var="rows" value="0" />
        	<c:forEach items="${list}" var="item" varStatus="loop">
			<c:set var="rows" value="${rows+1}" />
			<tr>
				<td>${rows}</td>
				<td>${item.USER_NM}</td>
				<td>${item.USER_ID}</td>
				<td>${item.PAYNAME}</td>
				<td>${item.LEC_TYPE_CHOICE}</td>
				<td>${item.ORDERNO}</td>
				<td>${item.MGNTNO}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td>${item.LECTURE_NO}</td>
				<td>${item.LEC_TITLE}</td>
				<td>${item.SUBJECT_TEACHER}</td>
				<td>${item.ORDER_DATE}</td>
				<td><fmt:formatNumber value="${item.PRICE}" groupingUsed="true"/></td>
				<td>${item.SUSU_RATE}</td>
				<td><fmt:formatNumber value="${item.SUSU_PRICE}" groupingUsed="true"/></td>
				<td>${item.SUB_AVR}</td>
				<td><fmt:formatNumber value="${item.SUB_PRICE}" groupingUsed="true"/></td>
				<td>${item.PAYMENT}</td>
				<td><fmt:formatNumber value="${item.TEACHER_PRICE}" groupingUsed="true"/></td>
				<td>${item.START_DATE}</td>
				<td>${item.END_DATE}</td>
				<td>${item.PERIOD}</td>
				<td>${item.REST_PERIOD}</td>
				<td>${item.USE_PERIOD}</td>
				<td><fmt:formatNumber value="${item.REST_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.USE_PRICE}" groupingUsed="true"/></td>
			</tr>
			</c:forEach>
        	<c:if test="${fn:length(list) > 0 }">
			<tr>
				<td colspan="10"><font color="red">합계</font></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><fmt:formatNumber value="${sub_price}" groupingUsed="true"/></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><fmt:formatNumber value="${rest_price}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${use_price}" groupingUsed="true"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	     
    <!-- //테이블--> 
</form>
</div>    

</html>