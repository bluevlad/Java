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
	$("#form").attr("action", "<c:url value='/stat/teacher/list.do' />");
	$("#form").submit();
}

function fn_detail(id) {
	$("#PRF_ID").val(id);
	$("#form").attr("action", "<c:url value='/stat/teacher/SalesStat.do' />");
	$("#form").submit();
}

function doMake(type) {
	$("#S_TYPE").val(type);
	$("#form").attr("action", "<c:url value='/stat/teacher/SalesMake.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="PRF_ID" name="PRF_ID" value=""/>
<input type="hidden" id="S_TYPE" name="S_TYPE" value=""/>

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" maxlength="8" readonly="readonly"/>
             	&nbsp;
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
    <!--버튼-->
			<ul class="boardBtns">
            	<input type="text" id="S_YAER" name="S_YAER" value="" maxlength="4" style="width:50px;"/>년
				<li><a href="javascript:doMake('ON');">동영상매출산정</a></li>
		        <li><a href="javascript:doMake('OF');">학원매출산정</a></li>
			</ul>
    <!--//버튼-->
     
    <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th>순번</th>
	        <th>강사명</th>
	        <th>등록일</th>
	        <th>과목</th>
	        <th>삭제여부</th>
	        <th>순번</th>
	        <th>강사명</th>
	        <th>등록일</th>
	        <th>과목</th>
	        <th>삭제여부</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="10">데이터가 존재하지 않습니다.</td>
        	</c:if>
			<tr>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<th>${loop.index + 1}</th>
				<td><a href="javascript:fn_detail('${item.USER_ID}');">${item.USER_NM}</a></td>
				<td>${item.REG_DT}</td>
				<td>
			        <c:forEach items="${tlist}" var="tech" varStatus="status">
					<c:if test="${item.USER_ID == tech.SUBJECT_TEACHER}">${tech.SUBJECT_NM} / </c:if>
					</c:forEach>
				</td>
				<td>${item.ISUSE}</td>
		    <c:if test="${loop.index%2 == 1}">
		    </tr>
		    <tr>
		    </c:if>
			</c:forEach>
			</tr>
		</tbody>
	</table>
	     
    <!-- //테이블--> 
</form>
</div>    

</html>