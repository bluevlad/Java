<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<html>
<head>
<meta name="decorator" content="index">
</head>
<script LANGUAGE="JavaScript" src="<c:url value='/resources/FusionCharts/JSClass/FusionCharts.js'/>"></script>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 경영관리 > <strong>매출통계관리</strong></h2>
    <table class="table05">
    	<tr>
            <td>
				<div class="left"><img src="<c:url value='/imgFileView.do?path=${view.PIC1}'/>"  alt=""></div>
				<div class="r_professor">
					<h3>${view.USER_NM }</h3>
				</div>
				<!-- 교수 프로필 -->
				<div class="teach_profile">
					<span class="img1_teacher"></span> 
				</div>
            </td>
            <td>
				<!-- 교수 프로필 -->
				<div class="teach_profile">
					<div class="inbox">
						<div class="history">
							<h3>- 교수약력 -</h3>
							<p>
								${fn:replace(view.PROFILE_SUMMARY, newLineChar, '<br/>')}
							</p>
						</div>
						<div class="books">
							<h3>- 주요저서 -</h3>
							<p>
								${fn:replace(view.BOOK_LOG_SUMMARY, newLineChar, '<br/>')}
							</p>
						</div>
					</div>
				</div>
            </td>
		</tr>
	</table>
     
    <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th>순번</th>
	        <th>기간</th>
	        <th>온라인/매출건수</th>
	        <th>온라인/매출액</th>
	        <th>온라인/환불건수</th>
	        <th>온라인/환불액</th>
	        <th>오프/매출건수</th>
	        <th>오프/매출액</th>
	        <th>오프/환불건수</th>
	        <th>오프/환불액</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="10">데이터가 존재하지 않습니다.</td>
        	</c:if>
			<SCRIPT LANGUAGE="JavaScript">
			<!--
				var iData, cData, oData, fData, oCnt, fCnt;
			//-->
			</SCRIPT>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${loop.index + 1}</td>
				<td>${item.YM}</td>
				<td><fmt:formatNumber value="${item.ON_SALE_CNT}"/></td>
				<td><fmt:formatNumber value="${item.ON_SALE_SUM}"/></td>
				<td><fmt:formatNumber value="${item.ON_REFUND_CNT}"/></td>
				<td><fmt:formatNumber value="${item.ON_REFUND_SUM}"/></td>
				<td><fmt:formatNumber value="${item.OF_SALE_CNT}"/></td>
				<td><fmt:formatNumber value="${item.OF_SALE_SUM}"/></td>
				<td><fmt:formatNumber value="${item.OF_REFUND_CNT}"/></td>
				<td><fmt:formatNumber value="${item.OF_REFUND_SUM}"/></td>
			</tr>
				<script language="JavaScript">		
				cData = cData + "<category label='${item.YM}'/>";
	        	oData = oData + "<set value='${item.ON_SALE_SUM}'/>";
	        	fData = fData + "<set value='${item.OF_SALE_SUM}'/>";
	        	oCnt = oCnt + "<set value='${item.ON_SALE_CNT}'/>";
	        	fCnt = fCnt + "<set value='${item.OF_SALE_CNT}'/>";
	            </script>
			<c:set var="OSC" value="${OSC+item.ON_SALE_CNT}"/>	            
			<c:set var="OSS" value="${OSS+item.ON_SALE_SUM}"/>	            
			<c:set var="ORC" value="${ORC+item.ON_REFUND_CNT}"/>	            
			<c:set var="ORS" value="${ORS+item.ON_REFUND_SUM}"/>	            
			<c:set var="FSC" value="${FSC+item.OF_SALE_CNT}"/>	            
			<c:set var="FSS" value="${FSS+item.OF_SALE_SUM}"/>	            
			<c:set var="FRC" value="${FRC+item.OF_REFUND_CNT}"/>	            
			<c:set var="FRS" value="${FRS+item.OF_REFUND_SUM}"/>	            
			</c:forEach>
			<tr>
				<td colspan="2">합계</td>
				<td><fmt:formatNumber value="${OSC}"/></td>
				<td><fmt:formatNumber value="${OSS}"/></td>
				<td><fmt:formatNumber value="${ORC}"/></td>
				<td><fmt:formatNumber value="${ORS}"/></td>
				<td><fmt:formatNumber value="${FSC}"/></td>
				<td><fmt:formatNumber value="${FSS}"/></td>
				<td><fmt:formatNumber value="${FRC}"/></td>
				<td><fmt:formatNumber value="${FRS}"/></td>
			</tr>
		</tbody>
	</table>
    <!-- //테이블--> 
	<CENTER>
		<div id="chart1div">
			FusionCharts
		</div>
		
		<script language="JavaScript">		
//			var chart1 = new FusionCharts("<c:url value='/resources/FusionCharts/Charts/MSColumn3DLineDY.swf'/>", "chart1Id", "800", "300", "0", "1");
			var chart1 = new FusionCharts("MSColumn3DLineDY.swf", "chart1Id", "800", "300", "0", "1");
			iData = "<chart caption='매출내역' xAxisName='기간' yAxisName='금액' showValues='1' decimals='0' formatNumberScale='0'>";
			iData = iData + "<categories>";
			iData = iData + cData;
			iData = iData + "</categories>";
			iData = iData + "<dataset seriesName='온라인 매출액'>";
			iData = iData + oData;
			iData = iData + "</dataset>";
			iData = iData + "<dataset seriesName='학원 매출액'>";
			iData = iData + fData;
			iData = iData + "</dataset>";
			iData = iData + "</chart>";
			chart1.setDataXML(iData);
			chart1.render("chart1div");
		</script>
	</CENTER>
</form>
</div>    
</html>