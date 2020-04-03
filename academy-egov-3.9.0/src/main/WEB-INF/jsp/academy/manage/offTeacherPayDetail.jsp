<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
function fn_list() {
	$("#searchTeacher").val("");
	$("#form").attr("action", "<c:url value='/manage/onTeacherPay.do' />");
	$("#form").submit();
}

function fn_excel() {
	$("#form").attr("action", "<c:url value='/manage/onTeacherPayExcel.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="searchStartDate" name="searchStartDate" value="${searchStartDate}"/>
<input type="hidden" id="searchEndDate" name="searchEndDate" value="${searchEndDate}"/>
<input type="hidden" id="searchTeacher" name="searchTeacher" value="${searchTeacher}"/>

	<h2>● 경영관리 > <strong>강사료관리</strong></h2>

	<table class="table01">
    	<tr>
            <th>${searchTeacherName} 강사님의 수강료지급 현황입니다.(총:${fn:length(list)})</th>
            <td width="5%"><input type="button" value="excel" onclick="fn_excel()"></td>
            <td width="5%"><input type="button" value="목록" onclick="fn_list()"></td>
		</tr>
	</table>
    <br/>
    <table class="table02">
		<tr>
	        <th rowspan="2">ID</th>
	        <th rowspan="2">수강자</th>
	        <th rowspan="2">구분</th>
	        <th rowspan="2">강의명</th>
	        <th rowspan="2">신청일</th>
	        <th rowspan="2">수강시작</th>
	        <th rowspan="2">수강종료</th>
	        <th rowspan="2">금액</th>
	        <th colspan="3">입금구분</th>
	        <th rowspan="2">강사지급액</th>
	        <th rowspan="2">강사지급률</th>
	        <th rowspan="2">장바구니할인</th>
		</tr>
		<tr>
			<th>결제</th>
			<th>수수료율</th>
			<th>수수료</th>
		</tr>
        <tbody>
        	<c:set var="pay110" value="0"/>
        	<c:set var="pay120" value="0"/>
        	<c:set var="pay130" value="0"/>
        	<c:set var="pay100" value="0"/>
        	<c:set var="pay110_su" value="0"/>
        	<c:set var="pay120_su" value="0"/>
        	<c:set var="pay130_su" value="0"/>
        	<c:set var="price" value="0"/>
        	<c:set var="etc1" value="0"/>
        	<c:set var="etc2" value="0"/>
        	
	        <c:forEach items="${list}" var="item" varStatus="loop">
	        <c:set var="pay110" value="${pay110 + item.PAY110_PRICE}" />
	        <c:set var="pay120" value="${pay120 + item.PAY120_PRICE}" />
	        <c:set var="pay130" value="${pay130 + item.PAY130_PRICE}" />
	        <c:set var="pay100" value="${pay100 + item.PAY100_PRICE}" />
	        
        	<c:set var="pay110_su" value="${pay110_su + item.PAY110_SUSU}"/>
        	<c:set var="pay120_su" value="${pay120_su + item.PAY120_SUSU}"/>
        	<c:set var="pay130_su" value="${pay130_su + item.PAY130_SUSU}"/>

        	<c:set var="price" value="${price + item.TEACHER_PAY}"/>
        	<c:set var="etc1" value="${etc1 + item.TEACHER_PAY_TEMP1}"/>
        	<c:set var="etc2" value="${etc2 + item.TEACHER_PAY_TEMP2}"/>
	        
			<tr>
				<td>${item.USERID}</td>
				<td>${item.USER_NM}</td>
				<td>${item.LEC_TYPE_CHOICE}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td>${item.REG_DT}</td>
				<td>${item.START_DATE}</td>
				<td>${item.END_DATE}</td>
				<td><fmt:formatNumber value="${item.TOTAL_PAY}" groupingUsed="true"/></td>
				<td>${item.PAYCODE}</td>
				<td>${item.SUSU}</td>
				<td>${item.SUSU_PAY}</td>
				<td><fmt:formatNumber value="${item.TEACHER_PAY}" groupingUsed="true"/></td>
				<td>${item.PAYMENT}%</td>
				<td>${item.DISCOUNTPER}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="14"></td>
			</tr>
			<tr>
				<th colspan="3">신용카드 입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay110}" groupingUsed="true"/></b></font></td>
				<th colspan="4">신용카드 수수료</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay110_su}" groupingUsed="true"/></b></font></td>
				<th colspan="3">정산합계</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${(pay110 + pay120 + pay130 + pay100) - (pay110_su + pay120_su + pay130_su)}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">가상계좌  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay120}" groupingUsed="true"/></b></font></td>
				<th colspan="4">가상계좌 수수료</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay120_su}" groupingUsed="true"/></b></font></td>
				<th colspan="3">강사료</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${price}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">계좌이체  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay130}" groupingUsed="true"/></b></font></td>
				<th colspan="4">계좌이체 수수료</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay130_su}" groupingUsed="true"/></b></font></td>
				<th colspan="3">원천세</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${etc1}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">무통장  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay100}" groupingUsed="true"/></b></font></td>
				<th colspan="4"></th>
				<td></td>
				<th colspan="3">주민세</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${etc2}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">공급가액</th>
				<td><font color="green"><b><fmt:formatNumber value="${pay110 + pay120 + pay130 + pay100}" groupingUsed="true"/></b></font></td>
				<th colspan="4"></th>
				<td></td>
				<th colspan="3">실지급액</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${price - (etc1 + etc2)}" groupingUsed="true"/></b></font></td>
			</tr>

		</tbody>
	</table>

	<br/><br/>
	
	<table class="table01">
    	<tr>
            <th>${searchTeacherName} 강사님의 환불 현황입니다.</th>
		</tr>
	</table>
    <br/>
    <table class="table02">
		<tr>
	        <th rowspan="2">ID</th>
	        <th rowspan="2">수강자</th>
	        <th rowspan="2">구분</th>
	        <th rowspan="2">강의명</th>
	        <th rowspan="2">신청일</th>
	        <th rowspan="2">수강시작</th>
	        <th rowspan="2">수강종료</th>
	        <th rowspan="2">금액</th>
	        <th colspan="3">입금구분</th>
	        <th rowspan="2">강사지급액</th>
	        <th rowspan="2">강사지급률</th>
	        <th rowspan="2">장바구니할인</th>
		</tr>
		<tr>
			<th>결제</th>
			<th>수수료율</th>
			<th>수수료</th>
		</tr>
        <tbody>
        	<c:set var="r_pay110" value="0"/>
        	<c:set var="r_pay120" value="0"/>
        	<c:set var="r_pay130" value="0"/>
        	<c:set var="r_pay100" value="0"/>
        	<c:set var="r_price" value="0"/>
        	<c:set var="r_etc1" value="0"/>
        	<c:set var="r_etc2" value="0"/>
        	
	        <c:forEach items="${list2}" var="item" varStatus="loop">
	        <c:set var="r_pay110" value="${r_pay110 + item.PAY110_PRICE}" />
	        <c:set var="r_pay120" value="${r_pay120 + item.PAY120_PRICE}" />
	        <c:set var="r_pay130" value="${r_pay130 + item.PAY130_PRICE}" />
	        <c:set var="r_pay100" value="${r_pay100 + item.PAY100_PRICE}" />
	        
        	<c:set var="r_price" value="${r_price + item.TEACHER_PAY}"/>
        	<c:set var="r_etc1" value="${r_etc1 + item.TEACHER_PAY_TEMP1}"/>
        	<c:set var="r_etc2" value="${r_etc2 + item.TEACHER_PAY_TEMP2}"/>
			<tr>
				<td>${item.USERID}</td>
				<td>${item.USER_NM}</td>
				<td>${item.LEC_TYPE_CHOICE}</td>
				<td>${item.SUBJECT_TITLE}</td>
				<td>${item.REG_DT}</td>
				<td>${item.START_DATE}</td>
				<td>${item.END_DATE}</td>
				<td><fmt:formatNumber value="${item.TOTAL_PAY}" groupingUsed="true"/></td>
				<td>${item.PAYCODE}</td>
				<td>${item.SUSU}</td>
				<td>${item.SUSU_PAY}</td>
				<td><fmt:formatNumber value="${item.TEACHER_PAY}" groupingUsed="true"/></td>
				<td>${item.PAYMENT}%</td>
				<td>${item.DISCOUNTPER}</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(list2) == 0 }">
        		<td colspan="14">데이터가 존재하지 않습니다.</td>
        	</c:if>			
			<tr>
				<td colspan="14"></td>
			</tr>
			<tr>
				<th colspan="3">신용카드 입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${r_pay110}" groupingUsed="true"/></b></font></td>
				<td colspan="5"></td>
				<th colspan="3">강사료</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${r_price}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">가상계좌  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${r_pay120}" groupingUsed="true"/></b></font></td>
				<td colspan="5"></td>
				<th colspan="3">원천세</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${r_etc1}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">계좌이체  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${r_pay130}" groupingUsed="true"/></b></font></td>
				<td colspan="5"></td>
				<th colspan="3">주민세</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${r_etc2}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">무통장  입금</th>
				<td><font color="green"><b><fmt:formatNumber value="${r_pay100}" groupingUsed="true"/></b></font></td>
				<td colspan="5"></td>
				<th colspan="3">실환불액</th>
				<td colspan="2"><font color="green"><b><fmt:formatNumber value="${r_price - (r_etc1 + r_etc2)}" groupingUsed="true"/></b></font></td>
			</tr>
			<tr>
				<th colspan="3">환불 총금액</th>
				<td><font color="green"><b><fmt:formatNumber value="${r_pay110 + r_pay120 + r_pay130 + r_pay100}" groupingUsed="true"/></b></font></td>
				<td colspan="5"></td>
				<th colspan="3">총실지급액</th>
				<td colspan="2"><font color="red"><b><fmt:formatNumber value="${price - (etc1 + etc2) + (r_price - (r_etc1 + r_etc2))}" groupingUsed="true"/></b></font></td>
			</tr>

		</tbody>
	</table>
</form>
</div>    

</html>
