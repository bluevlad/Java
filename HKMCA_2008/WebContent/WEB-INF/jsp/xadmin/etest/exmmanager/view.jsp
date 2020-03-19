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
 
 function showDetailScore(usn) {
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "showDetailScore";
        frm.usn.value =  usn;
        frm.submit();
    }
 }
 
 function goUserSel() {
    var frm = getObject("myform");
    
    if(frm) {
        frm.cmd.value = "userSel";
        frm.submit();
    }
 }
 
 function doExcel(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "excel";
        frm.submit();
    }
}
function doUploadExcel(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "uploadExcel";
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="usn" value=""/>
<mf:input type="hidden" name="exmid" value="${item.exmid}"/>
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
</mf:form>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
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
        <th><mfmt:message bundle="etest.common" key="msg.exm_set" /></th>
        <td colspan="3">[<mh:out value="${item.setid}" />]&nbsp;&nbsp;<mh:out value="${item.settitle}"/></td>
    </tr>
    <tr>            
        <th><mfmt:message bundle="table.exm_set" key="passing_score" /></th>
        <td><mh:out value="${item.passing_score}" /></td>
        <th><mfmt:message bundle="table.exm_mst" key="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" /></td>
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
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="edit" onclick="doEdit();" icon="icon_edit" /> 
            <mf:button bundle="button" key="list" onclick="goList();" icon="icon_list" />
            <mf:button bundle="button" key="etest.goPrint" onclick="goPrint();" />
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
	                <td align="center"><mh:out value="${exmStat.rstavg}" default="0"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstavg10" /></th>
	                <td align="center"><mh:out value="${exmStat.rstavg10}" default="0"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.passavg"/></th>
	                <mh:iif var="t" test="${empty exmStat.passavg}" trueValue="0" falseValue="${exmStat.passavg}"/>
	                <td align="center"><fmt:formatNumber value="${t}" pattern="##0.0#"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstscoremax" /></th>
	                <td align="center"><mh:out value="${exmStat.rstscoremax}" default="0"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.rstscoremin" /></th>
	                <td align="center"><mh:out value="${exmStat.rstscoremin}" default="0"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.countoffinish"/> /
	                    <mfmt:message bundle="etest.common" key="result.countoftarget"/></th>
	                <td align="center"><mh:out value="${item.cnt_s} / ${item.cnt_reg}" /></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.countofprogress" /></th>
	                <td align="center"><mh:out value="${item.cnt_i}"  default="0"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="etest.common" key="result.passcnt" /></th>
	                <td align="center"><mh:out value="${exmStat.cnt_pass}( ${exmStat.passrate} %) " default="0" /></td>
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
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" enableAlternateRows="false" rowAlternateClass="alternateRow">
    <col width="20" />
    <col width="80" />
    <col width="120" />
    <col width="120" />
    <col width="80" />
    <col width="50" />
    <col width="50" />
    <thead>
        <tr>
            <th>#</th>
            <th><mf:header name="nm" /></th>
            <th><mf:header name="exm_sdat" /></th>
            <th><mf:header name="exm_edat" /></th>
            <th><mf:header name="rst_status" /></th>
            <th><mf:header name="rstscore100" /></th>
            <th><mf:header name="passing_yn" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="ri" items="${navigator.list}" varStatus="status">
            <tr>
                <td class="center"><mh:out value="${status.count}" /></td>
                <td class="center"><a href='javascript:showDetailScore(<c:out value="'${ri.usn}'"/>)'><mh:out value="${ri.nm}(${ri.userid})" td="true" /></a></td>
                <td class="center"><mh:out value="${ri.rst_sdt}" td="true" /></td>
                <td class="center"><mh:out value="${ri.rst_edt}" td="true" /></td>
                <td class="center"><mh:out value="${ri.rst_status}" td="true" codeGroup="ETEST.RST_STATUS" /></td>
                <td class="center"><mh:out value="${ri.rstscore100}" td="true" /></td>
                <td class="center"><mh:out value="${ri.passing_yn}" td="true" codeGroup="ETEST.PASSING_YN"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right"><mf:button bundle="button" key="list" onclick="goList();" icon="icon_save" /></td>
    </tr>
</table>
</div>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>