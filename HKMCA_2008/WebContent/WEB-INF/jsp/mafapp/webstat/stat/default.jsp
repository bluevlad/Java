<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>

<table width="100%" border="0" cellspacing="2" cellpadding="2" align="center">
    <tr>
        <td>
            <br>Year : <mf:form action="${controlaction}" method="get" name="search">
            <select name="yyyy">
            <c:forTokens var="t" items="2006,2007,2008,2009,2010,2011,2012,2013,2014,2015" delims=",">
            <mf:option value="${t}" curValue="${yyyy}" text="${t}"/>
            </c:forTokens>
            </select>
            Month : 
            <select name="mm">
            <c:forTokens var="t" items="01,02,03,04,05,06,07,08,09,10,11,12" delims=",">
            <mf:option value="${t}" curValue="${mm}"><c:out value="${t}"/></mf:option>
            </c:forTokens>
            </select>
            <mf:submit bundle="button" key="search" />
            </mf:form>
        </td>
    </tr>
    <tr>
        <td align="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td>
			            <div id="chartdiv" align="center">FusionCharts. </div>
			            <c:url var="dataUrl" value="${control_action}">
			            <c:param name="cmd" value="user_log_day" />
			            <c:param name="yyyy" value="${param.yyyy}" />
			            <c:param name="mm" value="${param.mm}" />
			            </c:url> 
			            <script type="text/javascript">
			            var chart = new FusionCharts("Column2D.swf", "ChartId", "100%", "400", "0", "0");
			            chart.setDataURL(escape('<c:out value="${dataUrl}" escapeXml="false"/>'));   
			            chart.render("chartdiv");
			            </script>
			        </td>
				</tr>
			</table>
        </td>
    </tr>
</table>