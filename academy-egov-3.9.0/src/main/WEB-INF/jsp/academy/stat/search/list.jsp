<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("sdate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("edate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	var date1 = parseInt($("#sdate").val());
	var date2 = parseInt($("#edate").val());
	if(date1 > date2) {
		alert("검색일 설정이 잘못되었습니다.\n검색시작일은 검색종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	$("#form").attr("action", "<c:url value='/stat/teacher/list.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색일자</th>
            <td>
            	<input type="text" id="sdate" name="sdate" value="${params.sdate}" style="width:90px;IME-MODE:disabled;" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="edate" name="edate" value="${params.edate}" style="width:90px;IME-MODE:disabled;" maxlength="8" readonly="readonly"/>
             	&nbsp;
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
    
<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    

    <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th>순번</th>
	        <th>검색어</th>
	        <th>최초검색일</th>
	        <th>최근검색일</th>
	        <th>검색건수</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<th>${loop.index + 1}</th>
				<td>${item.SEARCH_KEYWORD}</td>
				<td>${item.REG_DT}</td>
				<td>${item.UPD_DT}</td>
				<td>${item.SEARCH_CNT}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
	     
    <!-- //테이블--> 
</form>
</div>    

</html>