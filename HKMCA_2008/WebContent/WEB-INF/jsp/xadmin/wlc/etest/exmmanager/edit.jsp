<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
	function frmSubmit(frmName) {
		var frm = getObject(frmName);
		if(frm) {
			if (validate(frm)) {
				// 사용자 조건 추가.
				frm.cmd.value="update";
				frm.submit();
			}
		} else {
			alert ("form[" + frmName + "] is invalid");
		}
	}

    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}

</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<input type="hidden" name="cmd" value="">
<mf:input type="hidden" name="exmid" value="${item.exmid}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th nowrap><mf:label name="exmtitle" /></th>
        <td colspan="3"><mf:input type="text" name="exmtitle" size="100" maxlength="100" value="${item.exmtitle}" required="true"/></td>
    </tr>
    <tr>
        <th nowrap><mf:label name="exmdesc" /></th>
        <td colspan="3"><mf:textarea name="exmdesc" cols="120" rows="3" value="${item.exmdesc}"/></td>
    </tr>
    <tr>
        <th nowrap><mf:label name="exm_sdat" /></th>
        <td><mf:input type="date" name="exm_sdat" size="12" value="${item.exm_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
        <th nowrap><mf:label name="exm_edat" /></th>
        <td><mf:input type="date" name="exm_edat" size="12" value="${item.exm_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
    </tr>
    <tr>
        <th nowrap><mf:label name="exmtime" /></th>
        <td><mf:input type="text" name="exmtime" size="20" maxlength="50" value="${item.exmtime}" required="true" option="number"/></td>
        
    </tr>
    <tr>
        <th nowrap><mf:label name="exmview" /></th>
        <td><mf:select name="exmview" curValue="${item.exmview}" codeGroup="YESORNO"/></td>
        <th nowrap><mf:label name="exmopen" /></th>
        <td><mf:select name="exmopen"  curValue="${item.exmopen}" codeGroup="YESORNO"/></td>
    </tr>
    <tr>
        <th nowrap><mf:label name="exmpage" /></th>
        <td><mf:input type="text" name="exmpage" size="20" maxlength="50" value="${item.exmpage}" required="true" option="number" /></td>
        <th nowrap><mf:label name="active_yn" /></th>
        <td><mf:select name="active_yn" curValue="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
        	
        	<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" />
        	<mf:button bundle="button" key="goList" onclick="goList()" />
        </td>
    </tr>
</table>
</mf:form>
