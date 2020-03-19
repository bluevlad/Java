<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    var data; //Cahrt Data
    var tdata; //Cahrt Data
//-->
</SCRIPT>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0"  width="100%">
    <tr>
        <td>
	        <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	            <col width="15%" />
	            <col width="35%" />
                <col width="15%" />
                <col width="35%" />
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstavg" /></th>
	                <td align="center"><mh:out value="${lecStat.rstavg}" td="true"/></td>
	                <th><mfmt:message bundle="etest.common" key="result.rstavg10" /></th>
	                <td align="center"><mh:out value="${lecStat.rstavg10}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstscoremax" /></th>
	                <td align="center"><mh:out value="${lecStat.rstscoremax}" td="true"/></td>
                    <th><mfmt:message bundle="etest.common" key="result.rstscoremin" /></th>
                    <td align="center"><mh:out value="${lecStat.rstscoremin}" td="true"/></td>
	            </tr>
	            <tr>
                    <th><mfmt:message bundle="etest.common" key="result.passavg"/></th>
                    <td align="center"><fmt:formatNumber value="${lecStat.passavg}" pattern="###.0#" />&nbsp;</td>
	                <th><mfmt:message bundle="etest.common" key="result.passcnt" /></th>
	                <td align="center"><mh:out value="${lecStat.cnt_pass}/${lecStat.cnt_usn}( ${lecStat.passrate} %) " td="true"/></td>
	            </tr>
	        </table>
        </td>
    </tr>
    <tr>
        <td>
            <table border="0" cellpadding="2" cellspacing="0"  width="100%">
                <tr>
                    <td align="center">
                        <div id="chartdiv" align="center">FusionCharts.</div>
                        <script type="text/javascript">
                        <c:forEach var="item" items="${list}">
                            tdata = tdata + "<set label='~<c:out value="${item.sc_range}"/>' value='<c:out value="${item.cnt}"/>'/>";
                        </c:forEach>
                            //function(swf, id, w, h, debugMode, registerWithJS, c, scaleMode, lang)
                            var chart = new FusionCharts("Column3D.swf", "ChartId", "700", "350", "0", "0");
                            data = "<chart caption='Histogram' xAxisName='Score' yAxisName='peoples' showValues='1' decimals='0' formatNumberScale='0'>";
                            data = data + tdata;
                            data = data + "</chart>";
                            chart.setDataXML(data);
                            chart.render("chartdiv");
                        </script>
                   </td>
                </tr>
            </table>
        </td>
    </tr>
</table>