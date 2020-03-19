<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>

<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td><br><mf:form action="${controlaction}" method="get" name="search">
            <mf:input type="text" name="sch_sdt" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${param.sch_sdt}" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${param.sch_edt}" cssClass="dropdown"/> 

        
        <mf:submit bundle="button" key="search" />
        </mf:form><br><br></td>
    <tr>
    <tr>
        <td align="center">


<table width="100%" border="0" cellspacing="0" cellpadding="0" >
 
	<tr>
		<td>
            <div id="chartdiv" align="center">FusionCharts. </div>
        
            <c:url var="dataUrl" value="${control_action}">
            <c:param name="cmd" value="byOrgTypeXml" />
            <c:param name="sch_sdt" value="${param.sch_sdt}" />
            <c:param name="sch_edt" value="${param.sch_edt}" />
            </c:url> 
            <script type="text/javascript">
            var chart = new FusionCharts("Column3D.swf", "ChartId", "600", "400", "0", "0");
            chart.setDataURL(escape('<c:out value="${dataUrl}" escapeXml="false"/>'));   
            chart.render("chartdiv");
            </script></td>
	</tr>
</table>
<table width="640" border="0" cellspacing="0" cellpadding="2" class="list" align="left">
	<col width="50%"/>
	<col width="50%"/>
	<tr>
		<th class="list">Day</th>
		<th class="list">Login</th>
	</tr>
	<c:forEach var="item" items="${list}" varStatus="status">
		<tr>
			<td class="list center"><mh:out value="${item.category}"/></td>
			<td class="list center"><mh:out value="${item.cnt}"/> </td>
			
		</tr>
	</c:forEach>
</table></td>
    </tr>

</table>    
