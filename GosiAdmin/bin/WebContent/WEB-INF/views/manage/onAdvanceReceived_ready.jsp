<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.bpopup.min2.js' />"></script>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	go_popup();
	$("#form").attr("action", "<c:url value='/manage/onAdvanceReceived.do' />");
	$("#form").submit();
}

function fn_excel() {
	$("#form").attr("action", "<c:url value='/manage/onReceivedExcel.do' />");
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
<h2>● 경영관리 > <strong>동영상 선수금관리</strong></h2>
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
				<!--
				<input type="button" onclick="fn_search()" value="검색" /> 
				<input type="button" onclick="fn_excel()" value="Excel" />
				-->
            </td>
		</tr>
	</table>
     <ul class="boardBtns">
    	<li><a href="javascript:fn_excel();">엑셀다운로드</a></li>
    </ul>
     
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
        	<td colspan="27"></td>
			<tr>
				<td colspan="16">&nbsp;</td>
				<td></td>
				<td colspan="7">&nbsp;</td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
    <!-- //검색 -->
</form>
</div>
<div id="rd-popup" class="rd-Pstyle"  style="visibility:hidden;"><span class="b-close"></span></div>
</html>