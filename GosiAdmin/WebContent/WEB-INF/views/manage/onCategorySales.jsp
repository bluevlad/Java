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

function fn_GoTeacherDashBoard(teacherid, usernm){
	if(teacherid=="" || teacherid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	
    	//alert("teacherid="+teacherid);
    //	alert("menutype="+menutype);
        window.open('<c:url value="/manage/teacherdashBoardList.pop"/>?userid=' + teacherid+"&Teachernm="+usernm+"&startdate=${searchStartDate}&enddate=${searchEndDate}", 'usernm', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');

    }
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${SEARCHTYPE}"/>

	<h2>● 경영관리 > <strong>직종별매출</strong></h2>
	<ul class="lecWritheTab">
	
    	<li><a href="javascript:fn_search('C');" <c:if test="${SEARCHTYPE eq 'C'}">class="active"</c:if>>직종별통계</a></li>
        <li><a href="javascript:fn_search('S');" <c:if test="${SEARCHTYPE eq 'S'}">class="active"</c:if>>과목별통계</a></li>
        <li><a href="javascript:fn_search('L');" <c:if test="${SEARCHTYPE eq 'L'}">class="active"</c:if>>유형별통계</a></li>
        <li><a href="javascript:fn_search('T');" <c:if test="${SEARCHTYPE eq 'T'}">class="active"</c:if>>교수별통계</a></li>
    </ul>  
    <!-- 검색 -->    
	
    <ul class="lecArrary">
      <li><span>프리패스 제외 금액입니다.</span> </li>
    </ul>

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
		    <c:choose>
			    <c:when test="${SEARCHTYPE eq 'C'}">
		    	 	<th>직종</th>
			    </c:when>
			    <c:when test="${SEARCHTYPE eq 'S'}">
			    	 <th>과목</th>
			    </c:when>
			    <c:when test="${SEARCHTYPE eq 'L'}">
			    	 <th>유형</th>
			    </c:when>
			    <c:when test="${SEARCHTYPE eq 'T'}">
			    	 <th>교수명</th>
			    </c:when>
			    <c:otherwise>
			    	 <th>직종</th>
			    </c:otherwise>
		    </c:choose>
	        <th>매출건수</th>
	        <th>매출현황</th>
	        <th>환불건수</th>
	        <th>환불현황</th>
		</tr>
        <tbody>
			<c:set var="USER_COUNT" value="0" />
			<c:set var="TOTAL_PRICE" value="0" />
			<c:set var="REFUND_USER_COUNT" value="0" />
			<c:set var="REFUND_TOTAL_PRICE" value="0" />
			<c:set var="POINT_USER_COUNT" value="0" />
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<c:set var="USER_COUNT" value="${USER_COUNT+item.USER_COUNT}" />
			<c:set var="TOTAL_PRICE" value="${TOTAL_PRICE+item.TOTAL_PRICE}" />
			<c:set var="REFUND_USER_COUNT" value="${REFUND_USER_COUNT+item.REFUND_USER_COUNT}" />
			<c:set var="REFUND_TOTAL_PRICE" value="${REFUND_TOTAL_PRICE+item.REFUND_TOTAL_PRICE}" />
			<tr>
	    	 	<td>
				    <c:if test="${SEARCHTYPE eq 'T'}">
				    	 <a href="#" onclick="javascript:fn_GoTeacherDashBoard('${item.SRHTYPE}', '${item.SRHNAME}')">
				    </c:if>${item.SRHNAME}
					</a>
				</td>				    
				<td><fmt:formatNumber value="${item.USER_COUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.TOTAL_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.REFUND_USER_COUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.REFUND_TOTAL_PRICE}" groupingUsed="true"/></td>
			</tr>
			</c:forEach>
			<tr>
	    	 	<td>합계	</td>				    
				<td><fmt:formatNumber value="${USER_COUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${TOTAL_PRICE}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${REFUND_USER_COUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${REFUND_TOTAL_PRICE}" groupingUsed="true"/></td>
			</tr>
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
					<th><font color="red">이러닝</font></th>
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