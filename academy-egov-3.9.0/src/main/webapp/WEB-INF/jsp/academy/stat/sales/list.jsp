<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta name="decorator" content="index">
<script LANGUAGE="JavaScript" src="<c:url value='/resources/FusionCharts/JSClass/FusionCharts.js'/>"></script>
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
	$("#form").attr("action", "<c:url value='/stat/sales/SalesList.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">회원가입일자</th>
            <td>
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${params.searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${params.searchEndDate}" maxlength="8" readonly="readonly"/>
             	&nbsp;
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
    <!--버튼-->
			<ul class="boardBtns">
			</ul>
    <!--//버튼-->
</form>
     
    <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th>가입회원수</th>
			<td><fmt:formatNumber value="${item.USER_CNT}"/></td>
	        <th></th>
			<td></td>
		</tr>
		<tr>
	        <th>동영상 구매회원수</th>
			<td><fmt:formatNumber value="${item.ON_ORD_CNT}"/></td>
	        <th>학원강의 구매회원수</th>
			<td><fmt:formatNumber value="${item.OF_ORD_CNT}"/></td>
		</tr>
		<tr>
	        <th>동영상구매수강생 평균건수</th>
			<td><fmt:formatNumber value="${item.ON_ORD_AVR}"/></td>
	        <th>학원구매수강생 평균건수</th>
			<td><fmt:formatNumber value="${item.OF_ORD_AVR}"/></td>
		</tr>
		<tr>
	        <th>동영상 총매출</th>
			<td><fmt:formatNumber value="${item.ON_ORD_SUM}"/></td>
	        <th>학원강의 총매출</th>
			<td><fmt:formatNumber value="${item.OF_ORD_SUM}"/></td>
		</tr>
		<tr>
	        <th>동영상 구매자 평균구매금액</th>
			<td><fmt:formatNumber value="${item.ON_ORD_PRICE}"/></td>
	        <th>학원강의 구매자 평균구매금액</th>
			<td><fmt:formatNumber value="${item.OF_ORD_PRICE}"/></td>
		</tr>
		<tr>
	        <th>회원별 동영상 평균구매금액</th>
			<td><fmt:formatNumber value="${item.ON_ORD_ALL_PRICE}"/></td>
	        <th>회원별 학원강의 평균구매금액</th>
			<td><fmt:formatNumber value="${item.OF_ORD_ALL_PRICE}"/></td>
		</tr>
		<tr>
	        <th>회원가입후 평균 동영상 첫 구매일수</th>
			<td><fmt:formatNumber value="${item.FST_ON_ORD_DAY}"/></td>
	        <th>회원가입후 평균 학원강의 첫 구매일수</th>
			<td><fmt:formatNumber value="${item.FST_OF_ORD_DAY}"/></td>
		</tr>
		<tr>
	        <th>회원가입후 평균 동영상 두번째 구매일수</th>
			<td><fmt:formatNumber value="${item.SND_ON_ORD_DAY}"/></td>
	        <th>회원가입후 평균 학원강의 두번째 구매일수</th>
			<td><fmt:formatNumber value="${item.SND_OF_ORD_DAY}"/></td>
		</tr>
	</table>
<script language="JavaScript">
<!--
	var aData, bData, cData, dData, eData, fData;
	aData = aData + "<set label='동영상 구매회원수' value='${item.ON_ORD_CNT}'/>";
	aData = aData + "<set label='학원강의 구매회원수' value='${item.OF_ORD_CNT}'/>";
	aData = aData + "<set label='비구매회원수' value='${item.USER_CNT - (item.ON_ORD_CNT+item.OF_ORD_CNT)}'/>";
	bData = bData + "<set label='동영상 구매금액' value='${item.ON_ORD_SUM}'/>";
	bData = bData + "<set label='학원강의 구매금액' value='${item.OF_ORD_SUM}'/>";
	cData = cData + "<set value='${item.ON_ORD_PRICE}'/>";
	cData = cData + "<set value='${item.ON_ORD_ALL_PRICE}'/>";
	dData = dData + "<set value='${item.OF_ORD_PRICE}'/>";
	dData = dData + "<set value='${item.OF_ORD_ALL_PRICE}'/>";
	eData = eData + "<set value='${item.FST_ON_ORD_DAY}'/>";
	eData = eData + "<set value='${item.SND_ON_ORD_DAY}'/>";
	fData = fData + "<set value='${item.FST_OF_ORD_DAY}'/>";
	fData = fData + "<set value='${item.SND_OF_ORD_DAY}'/>";
//-->
</script>
    <!-- //테이블--> 
    <table>
	    <tr>
		    <td>
				<CENTER>
					<div id="chart1div">
						FusionCharts
					</div>
					<script language="JavaScript">		
						var chart = new FusionCharts("Pie3D.swf", "chart1Id", "600", "300", "0", "1");
						iData = "<chart caption='구매회원수비교' showPercentageValues='1' baseFontSize='12' showBorder='1'>";
						iData = iData + aData;
						iData = iData + "</chart>";
						chart.setDataXML(iData);
						chart.render("chart1div");
					</script>
				</CENTER>
			</td>
			<td>
				<CENTER>
					<div id="chart2div">
						FusionCharts
					</div>
					<script language="JavaScript">		
						var chart = new FusionCharts("Pie3D.swf", "chart2Id", "600", "300", "0", "1");
						iData = "<chart caption='구매회원금액비교' showPercentageValues='1' baseFontSize='12' showBorder='1'>";
						iData = iData + bData;
						iData = iData + "</chart>";
						chart.setDataXML(iData);
						chart.render("chart2div");
					</script>
				</CENTER>
			</td>
		</tr>
	</table>
    <table>
	    <tr>
		    <td>
				<CENTER>
					<div id="chart3div">
						FusionCharts
					</div>
					<script language="JavaScript">		
						var chart = new FusionCharts("MSColumn3D.swf", "chart3Id", "600", "300", "0", "1");
						iData = "<chart caption='평균구매금액비교' numberScaleValue='10' showValues='1' baseFontSize='12' showBorder='1'>";
						iData = iData + "<categories>";
						iData = iData + "<category label='구매자1인당 평균구매금액'/>";
						iData = iData + "<category label='회원1인당 평균구매금액'/>";
						iData = iData + "</categories>";
						iData = iData + "<dataset seriesName='동영상'>";
						iData = iData + cData;
						iData = iData + "</dataset>";
						iData = iData + "<dataset seriesName='학원'>";
						iData = iData + dData;
						iData = iData + "</dataset>";
						iData = iData + "</chart>";
						chart.setDataXML(iData);
						chart.render("chart3div");
					</script>
				</CENTER>
			</td>
		    <td>
				<CENTER>
					<div id="chart4div">
						FusionCharts
					</div>
					<script language="JavaScript">		
						var chart = new FusionCharts("MSColumn3D.swf", "chart4Id", "600", "300", "0", "1");
						iData = "<chart caption='평균구매일수비교' numberScaleValue='10' showValues='1' baseFontSize='12' showBorder='1'>";
						iData = iData + "<categories>";
						iData = iData + "<category label='회원가입후 평균 첫 구매일수'/>";
						iData = iData + "<category label='회원가입후 평균 두번째 구매일수'/>";
						iData = iData + "</categories>";
						iData = iData + "<dataset seriesName='동영상'>";
						iData = iData + eData;
						iData = iData + "</dataset>";
						iData = iData + "<dataset seriesName='학원'>";
						iData = iData + fData;
						iData = iData + "</dataset>";
						iData = iData + "</chart>";
						chart.setDataXML(iData);
						chart.render("chart4div");
					</script>
				</CENTER>
			</td>
		</tr>
	</table>
</div>    
</html>