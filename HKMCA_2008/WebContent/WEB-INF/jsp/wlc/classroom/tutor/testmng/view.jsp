<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script language="javascript">
function goList() {
	var frm = getObject("myform");
	if(frm) {
	    frm.cmd.value = "list";
	    frm.submit();
	}
 }

function doEdit(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "edit";
        frm.submit();
    }
}

function goPrint(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "print";
        frm.submit();
    }
}
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="usn" value=""/>
<mf:input type="hidden" name="lec_num" value=""/>
<mf:input type="hidden" name="exmid" value="${item.exmid}"/>
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
</mf:form>
<div class="viewContainer">
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:header name="exmtitle" /></th>
        <td colspan="3"><mh:out value="${item.exmtitle}" /></td>
    </tr>
    <tr>
        <th><mf:header name="exm_sdat" /></th>
        <td><mh:out value="${item.exm_sdat}" /></td>
        <th><mf:header name="exm_edat" /></th>
        <td><mh:out value="${item.exm_edat}" /></td>
    </tr>
    <tr>
        <th><mf:header name="questions" /></th>
        <td colspan="3">
            <mf:label name="exmcnt1" /> : <b><mh:out value="${item.exmcnt1}"/></b>&nbsp;&nbsp;
            <mf:label name="exmcnt2" /> : <b><mh:out value="${item.exmcnt2}"/></b>&nbsp;&nbsp;
            <mf:label name="exmcnt3" /> : <b><mh:out value="${item.exmcnt3}"/></b>&nbsp;&nbsp;
            <mf:label name="exmcnt4" /> : <b><mh:out value="${item.exmcnt4}"/></b>&nbsp;&nbsp;
            <mf:label name="exmcnt5" /> : <b><mh:out value="${item.exmcnt5}"/></b>
        </td>
    </tr>
</table>
</div>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="edit" onclick="doEdit();" icon="icon_edit" /> 
            <mf:button bundle="button" key="list" onclick="goList();" icon="icon_list" />
            <mf:button bundle="button" key="print" onclick="goPrint();" icon="icon_edit" />
        </td>
    </tr>
</table>
<br><br>
<table border="0" cellpadding="2" cellspacing="0"  width="100%">
    <tr>
        <td width="30%" valign="top">
	        <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	            <col width="50%" />
	            <col width="50%" />
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstavg" /></th>
	                <td align="center"><mh:out value="${exmStat.rstavg}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstavg10" /></th>
	                <td align="center"><mh:out value="${exmStat.rstavg10}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.passavg"/></th>
	                <td align="center"><fmt:formatNumber value="${exmStat.passavg}" pattern="###.0#" />&nbsp;</td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstscoremax" /></th>
	                <td align="center"><mh:out value="${exmStat.rstscoremax}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstscoremin" /></th>
	                <td align="center"><mh:out value="${exmStat.rstscoremin}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.countoffinish"/> /
	                           <mfmt:message bundle="etest.common" key="result.countoftarget"/></th>
	                <td align="center"><mh:out value="${item.cnt_s} / ${item.cnt_reg}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.countofprogress" /></th>
	                <td align="center"><mh:out value="${item.cnt_i}" td="true"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.passcnt" /></th>
	                <td align="center"><mh:out value="${exmStat.cnt_pass}( ${exmStat.passrate} %) " td="true"/></td>
	            </tr>
	        </table>
        </td>
        <td width="70%">
            <table border="0" cellpadding="2" cellspacing="0"  width="100%">
                <tr>
                    <td align="center">
                        <div id="chartdiv" align="center">FusionCharts.</div>
                        <c:url var="dataUrl" value="${control_action}">
                            <c:param name="cmd" value="userHistgram" />
                            <c:param name="exmid" value="${item.exmid}" />
                            <c:param name="rstscore100" value="${item.rstscore100}" />
                        </c:url> 
                        <script type="text/javascript">
                        var chart = new FusionCharts("Column3D.swf", "ChartId", "500", "300", "0", "0");
                        chart.setDataURL(escape('<c:out value="${dataUrl}" escapeXml="false"/>'));         
                        chart.render("chartdiv");
                       </script>
                   </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</div>