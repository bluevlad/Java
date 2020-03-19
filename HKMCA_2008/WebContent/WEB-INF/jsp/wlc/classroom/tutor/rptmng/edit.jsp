<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function doUpdate() {
	var frm = getObject("myform");
	if(frm) {
		if (validate(frm)) {
			frm.cmd.value="update";
			frm.submit();
		}
	} else {
		alert ("form[" + frmName + "] is invalid");
	}
}

function doDelete() {
	var frm = getObject("myform");
	frm.cmd.value="delete";
	frm.submit();
}

function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
    	<c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="rptcode" value="${item.rptcode}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="rpttitle" /></th>
        <td colspan="3"><mf:input type="text" name="rpttitle" size="100" value="${item.rpttitle}"/></td>
    </tr>
    <tr>
        <th><mf:label name="rptdesc" /></th>
        <td colspan="3"><mf:textarea name="rptdesc" cols="110" rows="3" value="${item.rptdesc}"/></td>
    </tr>
    <tr>
        <th><mf:label name="rpt_sdat" /></th>
        <td><mf:input type="date" name="rpt_sdat" size="12" value="${item.rpt_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
        <th><mf:label name="rpt_edat" /></th>
        <td><mf:input type="date" name="rpt_edat" size="12" value="${item.rpt_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
    </tr>
    <tr>
        <th><mf:label name="rptrate" /></th>
        <td><mf:input name="rptrate" value="${item.rptrate}" size="4"/>%</td>
        <th><mf:label name="rptopen" /></th>
        <td><mf:select name="rptopen" curValue="${item.rptopen}" codeGroup="ACTIVE_YN"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save" />
            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete" />
        	<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
<br>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <col width="10%" />
    <col width="*" />
    <thead>
    <tr>
        <th nowrap>#</th>
        <th nowrap><mf:header name="quetitle" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td class="center"><mf:input type="checkbox" name="rpt_chk" value="${list.quecode}" curValue="${list.chk}"/></td>
        <td class="center"><mh:out value="${list.quetitle}" td="true" /></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</mf:form>
</div>