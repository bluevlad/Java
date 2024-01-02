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

function fn_search(searchtype) {
	if(searchtype!=null && searchtype != ''){
		$("#SEARCHTYPE").val(searchtype);
	}
	var date1 = parseInt($("#searchStartDate").val());
	var date2 = parseInt($("#searchEndDate").val());
	if(date1 > date2) {
		alert("검색일 설정이 잘못되었습니다.\n검색시작일은 검색종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	$("#form").attr("action", "<c:url value='/manage/onCategorySales.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${SEARCHTYPE}"/>

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" style="width:60;" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" style="width:60;" maxlength="8" readonly="readonly"/>
				<input type="button" onclick="fn_search('')" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <!-- 테이블-->
    <table class="table02">
		<tr>
    	 	<th>년월</th>
	    	 <th>패키지수</th>
	    	 <th>구매건수</th>
	    	 <th>환불건수</th>
	    	 <th>적립금</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
	    	 	<td>${item.YYYYMM}</td>				    
				<td><fmt:formatNumber value="${item.CNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.ORDER_CNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.REFUND_CNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.ADD_POINT}" groupingUsed="true"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
    <!-- //테이블--> 
</form>
</div>    

</html>