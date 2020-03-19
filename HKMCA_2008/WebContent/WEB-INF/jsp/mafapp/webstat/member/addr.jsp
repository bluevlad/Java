<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var data; //Cahrt Data
    var tdata; //Cahrt Data
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
	    <td>
            <jsp:include page="_search.jsp" flush="true"/>
        </td>
    </tr>
    <tr>
        <td>
            <div class="listContainer">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <col width="30%">
                <col width="70%">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
						<thead>
						    <tr>
                                <th width="25%"><mfmt:message bundle="common.webstat" key="no" /></th>
					            <th width="50%"><mfmt:message bundle="common.webstat" key="type" /></th>
					            <th width="25%"><mfmt:message bundle="common.webstat" key="count" /></th>
						    </tr>
					    </thead>
					    <tbody>
					    	<c:set var="cnt" value="1"/>
                            <c:set var="tot" value="0"/>
							<c:forEach var="item" items="${list}">
							<tr>
                                <td class="center"><mh:out value="${cnt}" td="true"/></td>
								<td class="center"><mh:out value="${item.sido}" td="true"/></td>
								<td class="center"><mh:out value="${item.cnt}" td="true"/></td>
							</tr>
                            <script type="text/javascript">
                            tdata = tdata + "<set label='<c:out value="${item.sido}"/>' value='<c:out value="${item.cnt}"/>'/>";
                            </script>
						    	<c:set var="cnt" value="${cnt+1}"/>
                                <c:set var="tot" value="${tot+item.cnt}"/>
						    </c:forEach>
                            <tr>
                                <th><mfmt:message bundle="common.webstat" key="total.accumulate" /></th>
                                <td align="center"><mh:out value="${cnt-1}" /></td>
                                <td align="center"><mh:out value="${tot}" /></td>
                            </tr>
					    </tbody>
					    </table>
					</td>
                </tr>
                <tr>
					<td> 
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th><div id="chartdiv" align="center"></div></th>
                            </tr>
                        </thead>
                        <tbody>
						    <tr>
						        <td align="center">
                                    <c:set var="size" value="${cnt*50}"/>
                                    <script type="text/javascript">
                                        //function(swf, id, w, h, debugMode, registerWithJS, c, scaleMode, lang)
                                        var chart = new FusionCharts("Bar2D.swf", "ChartId", "100%", "<mh:out value="${size}"/>", "0", "0");
                                        data = "<chart caption='지역별' xAxisName='' yAxisName='' showValues='1' decimals='0' formatNumberScale='0'>";
                                        data = data + tdata;
                                        data = data + "</chart>";
                                        chart.setDataXML(data);
                                        chart.render("chartdiv");
                                    </script>
						        </td>
						    </tr>
                        </tbody>
						</table>
					</td>
				</tr>
			</table>
		    </div>
	    </td>
    </tr>
</table>