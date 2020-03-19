<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
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
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<input type="hidden" name="cmd" value="list">
<mf:input type='hidden' name='exmid' value="${item.exmid}"/>
<mf:input type='hidden' name='LISTOP' value="${LISTOP.serializeUrl}"/>
</mf:form>
<div class="viewContainer">
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
        <th><mf:header name="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" td="true"/><mfmt:message bundle="common" key="time.min"/></td>
        <th><mf:header name="questions" /></th>
        <td><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:header name="exm_sdat" /></th>
        <td><mh:out value="${item.exm_sdat}"/></td>
        <th><mf:header name="exm_edat" /></th>
        <td><mh:out value="${item.exm_edat}"/></td>
    </tr>
     <tr>
        <th><mf:header name="lang" /></th>
        <td colspan="3">
            <c:forEach var="i" items="${setlangs}" varStatus="status">
            <c:out value="${i.allnames}" />
            <c:if test="${!status.last}">,</c:if>
            </c:forEach>&nbsp;
        </td>
   </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
            <c:choose>
            <c:when test="${item.rst_status == 'R'}">
                <mf:button bundle="button" key="etest.start" onclick="eTestGo('${item.exmid}');" />
            </c:when>
            <c:when test="${item.rst_status == 'F'}">
                <mf:button bundle="button" key="etest.viewResult" onclick="viewResult('${item.exmid}');" />
            </c:when>
            </c:choose>
            <mf:button bundle="button" key="list" onclick="goList();" />
        </td>
    </tr>
</table>
</div>