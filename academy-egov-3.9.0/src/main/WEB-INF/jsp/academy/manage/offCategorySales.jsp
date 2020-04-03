<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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
	$("#form").attr("action", "<c:url value='/manage/offCategorySales.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 경영관리 > <strong>직종별매출</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" style="width:60;" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" style="width:60;" maxlength="8" readonly="readonly"/>
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th>직종</th>
	        <th> 단과반 건수</th>
	        <th> 단과반 매출</th>
	        <th>종합반 건수</th>
	        <th>종합반 매출</th>
	        <th>(단과+종합) 총 건수</th>
	        <th>(단과+종합) 총 매출</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${item.CATEGORY_NAME}</td>
				  <td><fmt:formatNumber value="${item.COUNT}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${item.PRICE}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${item.COUNT1}" groupingUsed="true"/></td>
				  <td><fmt:formatNumber value="${item.PRICE1}" groupingUsed="true"/></td>
				  <td><fmt:formatNumber value="${item.COUNT+item.COUNT1}" groupingUsed="true"/></td>
				  <td><fmt:formatNumber value="${item.PRICE+item.PRICE1}" groupingUsed="true"/></td>		
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br /><br />
    <table class="table02">
		<tr>
	        <th>총계</th>
	        <th>매출현황</th>
		</tr>
		<c:forEach items="${listSum}" var="item" varStatus="loop">
			<c:choose>
				<c:when test="${item.CATEGORY_NAME == NULL}">
				<tr>
					<th><font color="red">TOTAL</font></th>
					<td><font color="red"><fmt:formatNumber value="${item.PRICE}" groupingUsed="true"/></font></td>
				</tr>
				</c:when>
				<c:otherwise>
				<tr>
					<th>${item.CATEGORY_NAME}</th>
					<td><fmt:formatNumber value="${item.PRICE}" groupingUsed="true"/></td>
				</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>      
    <!-- //테이블--> 
</form>
</div>    

</html>