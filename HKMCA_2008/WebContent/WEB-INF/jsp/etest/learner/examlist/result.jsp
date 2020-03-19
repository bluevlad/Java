<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script language="javascript">
function goList() {
     var frm = getObject("myform");
     frm.cmd.value="list";
     frm.submit();
}

function viewResult() {
     var frm = getObject("myform");
     frm.cmd.value="result";
     frm.submit();
}
</script>
<jf:form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" name="cmd" value="list">
    <input type='hidden' name='exmid' value="">
    <jf:input type='hidden' name='LISTOP' value="${LISTOP.serializeUrl}"/>
</jf:form>
<div class="viewContainer" style="border:1px solid #e0e0e0; padding:10px;">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <col width="10%" />
    <tr>
        <th><jfmt:message bundle="etest.common" key="result.qcnt" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstscore" /> / <jfmt:message bundle="etest.common" key="result.qsum" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstscore100" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstrank" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstcnt" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstrank100" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstavg" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstavg10" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstscoremax" /></th>
        <th><jfmt:message bundle="etest.common" key="result.rstscoremin" /></th>
    </tr>
    <tr>
        <td align="center"><jh:out value="${item.qcnt}" /></td>
        <td align="center"><jh:out value="${item.rstscore}" /> / <jh:out value="${item.qsum}" /></td>
        <td align="center"><jh:out value="${item.rstscore100}" /></td>
        <td align="center"><jh:out value="${item.rstrank}" /></td>
        <td align="center"><jh:out value="${item.rstcnt}" /></td>
        <td align="center"><jh:out value="${item.rstrank100}" /></td>
        <td align="center"><jh:out value="${item.rstavg}" /></td>
        <td align="center"><jh:out value="${item.rstavg10}" /></td>
        <td align="center"><jh:out value="${item.rstscoremax}" /></td>
        <td align="center"><jh:out value="${item.rstscoremin}" /></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <tr>
        <td align="center"><div id="chartdiv" align="center"> 
            FusionCharts. </div>
            <c:url var="dataUrl" value="${control_action}">
            <c:param name="cmd" value="userHistgram"/>
            <c:param name="exmid" value="${item.exmid}"/>
            <c:param name="rstscore100" value="${item.rstscore100}"/>
            </c:url>
            <script type="text/javascript">
            var chart = new FusionCharts("Column3D.swf", "ChartId", "600", "400", "0", "0");
            chart.setDataURL(escape('<c:out value="${dataUrl}" escapeXml="false"/>'));         
            chart.render("chartdiv");
            </script>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right"><jf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>
</div>