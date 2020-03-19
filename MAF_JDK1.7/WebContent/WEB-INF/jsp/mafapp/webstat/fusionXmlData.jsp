<%@ page contentType="text/html; charset=UTF-8"%>
<graph  
	caption='Login / day' 
	xAxisName='day' 
	yAxisName='count' 
	showNames='1'
	formatNumber="1"
	decimalPrecision="0"
	formatNumberScale="1">
<c:forEach var="item" items="${list}" varStatus="status">
<set name='${fn:substring(item.category,8,10)}' value='${item.cnt}' color='0099FF' />
</c:forEach>
</graph>