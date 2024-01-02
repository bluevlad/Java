<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta name="decorator" content="index">
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

	if($("#S_TYPE").val() == "P") {
    	alert("검색중입니다. 잠시만 기다려주세요...");
    	return;
    } else{
		$("#S_TYPE").val("P");
    }
	
	$("#form").attr("action", "<c:url value='/manage/offLecSalesJong.do' />");
	$("#form").submit();
}

//엑셀
function fn_excel() {
	var date1 = parseInt($("#searchStartDate").val());
	var date2 = parseInt($("#searchEndDate").val());

	if(date1 > date2) {
		alert("검색일 설정이 잘못되었습니다.\n검색시작일은 검색종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	
	$("#form").attr("action", "<c:url value='/manage/offLecSalesJongExcel.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="S_TYPE" name="S_TYPE" value="" />

	<h2>● 경영관리 > <strong>종합반 매출 관리</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" maxlength="8" readonly="readonly"/>
             	&nbsp;
             	<!-- <input type="button" onclick="fn_search()" value="검색" /> -->
            </td>
            <td>
			    <!--버튼-->    
			    <ul class="boardBtns">
			        <li><a href="javascript:fn_excel();">엑셀다운로드</a></li>
			    </ul>
			    <!--//버튼-->
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th>순번</th>
	        <th>주문번호</th>
	        <th>주문자명</th>
	        <th>전화번호</th>
	        <th>주문방법</th>
	        <th>강의코드</th>
	        <th>강의명</th>
	        <th>원수강료</th>
	        <th>결재금액</th>
	        <th>강의시작일</th>
	        <th>강의종료일</th>
	        <th>강의일수</th>
	        <th>잔여일수</th>
	        <th>사용금액</th>
	        <th>잔여액</th>
		</tr>
		<!-- 
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="17">데이터가 존재하지 않습니다.</td>
        	</c:if>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${loop.index + 1}</td>
				<td>${item.ORDERNO}</td>
				<td>${item.USER_NM}</td>
				<td>${item.PHONE_NO}</td>
				<td>${item.ORDER_TYPE}</td>
				<td>${item.LECCODE}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td><fmt:formatNumber value="${item.SUBJECT_REAL_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.PRICE}"/></td>
				<td><fmt:formatDate value="${item.MIN_DATE}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${item.MAX_DATE}" pattern="yyyy-MM-dd"/></td>
				<td>${item.LEC_SCHEDULE}</td>
				<td>${item.REST}</td>
				<td><fmt:formatNumber value="${item.USE_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.REST_PRICE}"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="7">합계</td>
				<td><fmt:formatNumber value="${totalPrice}"/></td>
				<td><fmt:formatNumber value="${totalPay}"/></td>
				<td colspan="4"></td>
				<td><fmt:formatNumber value="${totalUsePay}"/></td>
				<td><fmt:formatNumber value="${totalRestPay}"/></td>
			</tr>
		</tbody>
 		-->
 	</table>
	     
    <!-- //테이블--> 
</form>
</div>    

</html>