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

<jf:form action="${control_action}" method="post" name="myform" id="myform" >
<input type="hidden" name="cmd" value="list">
<jf:input type='hidden' name='exmid' value="${item.exmid}"/>
<jf:input type='hidden' name='LISTOP' value="${LISTOP.serializeUrl}"/>
</jf:form>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><jf:header name="exmtitle" /></th>
        <td colspan="3"><jh:out value="${item.exmtitle}" /></td>
    </tr>
    <tr>
        <th><jf:header name="exmtime" /></th>
        <td><jh:out value="${item.exmtime}" td="true"/><jfmt:message bundle="common" key="table.time.min"/></td>
        <th><jf:header name="questions" /></th>
        <td><jh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true"/></td>
    </tr>
    <tr>
        <th><jf:header name="exm_sdat" /></th>
        <td><jh:out value="${item.exm_sdat}"/></td>
        <th><jf:header name="exm_edat" /></th>
        <td><jh:out value="${item.exm_edat}"/></td>
    </tr>
     <tr>
        <th><jf:header name="lang" /></th>
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
                <jf:button bundle="etest.button" key="start" onclick="eTestGo('${item.exmid}');" />
            </c:when>
            <c:when test="${item.rst_status == 'F'}">
                <jf:button bundle="etest.button" key="viewResult" onclick="viewResult('${item.exmid}');" />
            </c:when>
            </c:choose>
            <jf:button bundle="button" key="goList" onclick="goList();" />
        </td>
    </tr>
</table>
</div>