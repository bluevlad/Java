<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta name="decorator" content="index">
</head>
<script LANGUAGE="JavaScript" src="<c:url value='/resources/FusionCharts/JSClass/FusionCharts.js'/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchStartDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
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
	$("#form").attr("action", "<c:url value='/memberManagement/memberStat.do' />");
	$("#form").submit();
}
</script>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 회원관리 > <strong>회원통계관리</strong></h2>
     
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">조회일자</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" maxlength="8" readonly="readonly" style="width:100px;"/><br>
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" maxlength="8" readonly="readonly" style="width:100px;"/>
             	&nbsp;
            	<input type="radio" id="selType" name="selType" value="YY" <c:if test="${selType == 'YY'}">checked</c:if>/>년도별&nbsp;
            	<input type="radio" id="selType" name="selType" value="MM" <c:if test="${selType == 'MM'}">checked</c:if>/>월별&nbsp; 
            	<input type="radio" id="selType" name="selType" value="WW" <c:if test="${selType == 'WW'}">checked</c:if>/>주별&nbsp; &nbsp; 
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
	<br>
    <table>
	    <tr>
		    <td width="50%">
			    <!-- 테이블-->
			    <table class="table05">
					<tr>
				        <th>순번</th>
				        <th>기간</th>
				        <th>가입회원수</th>
				        <th>탈퇴회원수</th>
					</tr>
			        <tbody>
			        	<c:if test="${fn:length(list) == 0 }">
			        	<td colspan="4">데이터가 존재하지 않습니다.</td>
			        	</c:if>
						<SCRIPT LANGUAGE="JavaScript">
						<!--
							var iData ="";
							var tData;
						//-->
						</SCRIPT>
						<c:set var="h" value="${100}"/>	            
				        <c:forEach items="${list}" var="item" varStatus="loop">
						<tr>
							<td>${loop.index + 1}</td>
							<td>${item.DT}</td>
							<td><fmt:formatNumber value="${item.CNT}"/></td>
							<td><fmt:formatNumber value="${item.WD_CNT}"/></td>
						</tr>
						<script language="JavaScript">
							iData = iData + "<set value='${item.CNT}' label='${item.DT}'/>";
			            </script>
						<c:set var="SUM" value="${SUM+item.CNT}"/>	
						<c:set var="WD_SUM" value="${WD_SUM+item.WD_CNT}"/>	
						<c:set var="h" value="${h+20}"/>	            
						</c:forEach>
						<tr>
							<td colspan="2">합계</td>
							<td><fmt:formatNumber value="${SUM}"/></td>
							<td><fmt:formatNumber value="${WD_SUM}"/></td>
						</tr>
					</tbody>
				</table>
		    </td>
		    <td>
			    <!-- //테이블--> 
					<div id="chart1div">
						FusionCharts
					</div>
					
					<script language="JavaScript">		
						var chart1 = new FusionCharts("Bar2D.swf", "chart1Id", "500", "${h}", "0", "1");
						tData = "<chart chartRightMargin='30' caption='회원가입현황' xAxisName='기간' yAxisName='회원수(명)' showValues='1' decimals='0' formatNumberScale='0'>";
						tData = tData + iData;
						tData = tData + "</chart>";
						chart1.setDataXML(tData);
						chart1.render("chart1div");
					</script>
		    </td>
	    </tr>
    </table>
</form>
</div>    
</html>