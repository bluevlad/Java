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
	
	$("#form").attr("action", "<c:url value='/manage/MovieSales.do' />");
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

	$("#form").attr("action", "<c:url value='/manage/MovieSalesExcel.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}"/>
<input type="hidden" id="S_TYPE" name="S_TYPE" value="" />

	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
				<input type="radio" id="SEARCHTYPE" name="SEARCHTYPE" VALUE="D" <c:if test='${params.SEARCHTYPE == "D"}'>checked="true"</c:if>/> 단과
				<input type="radio" id="SEARCHTYPE" name="SEARCHTYPE" VALUE="P" <c:if test='${params.SEARCHTYPE == "P"}'>checked="true"</c:if>/> 종합반(패키지)
				<input type="radio" id="SEARCHTYPE" name="SEARCHTYPE" VALUE="Y" <c:if test='${params.SEARCHTYPE == "Y"}'>checked="true"</c:if>/> 프리패스
				<input type="radio" id="SEARCHTYPE" name="SEARCHTYPE" VALUE="L" <c:if test='${params.SEARCHTYPE == "L"}'>checked="true"</c:if>/> 도서
				&nbsp;&nbsp;
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${params.searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${params.searchEndDate}" maxlength="8" readonly="readonly"/>
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
     
    <!-- 테이블 -->
    <!-- 
    <table class="table05">
		<tr>
	        <th>일련번호</th>
	        <th>주문번호</th>
	        <th>고객명</th>
	        <th>아이디</th>
	        <th>연락처</th>
	        <th>주문방법</th>
	        <th>결제수단</th>
	        <th>상품구분</th>
	        <th>상품코드</th>
	        <th>상품명</th>
	        <th>강의코드</th>
	        <th>강의명</th>
	        <th>교수명</th>
	        <th>안분율</th>
	        <th>배분율</th>
	        <th>결제일</th>
	        <th>환불일</th>
	        <th>시작일</th>
	        <th>종료일</th>
	        <th>기준일</th>
	        <th>전체</th>
	        <th>잔여</th>
	        <th>기준금액</th>
	        <th>결제금액</th>
	        <th>환불금액</th>
	        <th>전체금액</th>
	        <th>안분금액</th>
	        <th>잔여금액</th>
	        <th>사용금액</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="15">데이터가 존재하지 않습니다.</td>
        	</c:if>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${loop.index + 1}</td>
				<td>${item.ORDERNO}</td>
				<td>${item.USER_NM}</td>
				<td>${item.USER_ID}</td>
				<td>${item.PHONE_NO}</td>
				<td>${item.OTYPE}</td>
				<td>${item.PAYNAME}</td>
				<td>${item.PTYPE}</td>
				<td>${item.MGNTNO}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td>${item.LECTURE_NO}</td>
				<td>${item.LEC_TITLE}</td>
				<td>${item.SUBJECT_TEACHER}</td>
				<td>${item.SUB_AVR}</td>
				<td>${item.PAYMENT}</td>
				<td>${item.ORDER_DATE}</td>
				<td><c:if test="${item.CANCEL_PRICE != 0}"><font color="red">${item.CANCEL_DATE}</font></c:if></td>
				<td>${item.START_DATE}</td>
				<td>${item.END_DATE}</td>
				<td>${item.SEARCHENDDATE}</td>
				<td>${item.PERIOD}</td>
				<td>${item.REST_PERIOD}</td>
				<td><fmt:formatNumber value="${item.SUBJECT_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.PRICE}"/></td>
				<td><c:if test="${item.CANCEL_PRICE != 0}"><font color="red"><fmt:formatNumber value="${item.CANCEL_PRICE}"/></font></c:if></td>
				<td><fmt:formatNumber value="${item.DIV_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.SUB_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.REST_PRICE}"/></td>
				<td><fmt:formatNumber value="${item.USE_PRICE}"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="22">합계</td>
				<td><fmt:formatNumber value="${totalPrice}"/></td>
				<td></td>
				<td><font color="red"><fmt:formatNumber value="${totalRefund}"/></font></td>
				<td colspan="4"></td>
			</tr>
		</tbody>
	</table>
	 -->    
    <!-- //테이블--> 
</form>
</div>    

</html>