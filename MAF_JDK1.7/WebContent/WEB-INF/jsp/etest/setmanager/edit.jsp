
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
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
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
    <input type="hidden" name="cmd" value="">
    <mf:input type="hidden" name="setid" value="${setid}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="25%" />
        <col width="75%" />
        <tr>
            <th nowrap><mf:label name="setid" /></th>
            <td><mf:input type="text" name="setid" size="20" maxlength="50" value="${item.setid}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="setowner" /></th>
            <td><mf:input type="text" name="setowner" size="20" maxlength="50" value="${item.setowner}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="settitle" /></th>
            <td><mf:input type="text" name="settitle" size="20" maxlength="50" value="${item.settitle}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="setdesc" /></th>
            <td><mf:input type="text" name="setdesc" size="20" maxlength="50" value="${item.setdesc}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="exmtime" /></th>
            <td><mf:input type="text" name="exmtime" size="20" maxlength="50" value="${item.exmtime}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="reg_usn" /></th>
            <td><mf:input type="text" name="reg_usn" size="20" maxlength="50" value="${item.reg_usn}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="reg_dt" /></th>
            <td><mf:input type="text" name="reg_dt" size="20" maxlength="50" value="${item.reg_dt}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="update_dt" /></th>
            <td><mf:input type="text" name="update_dt" size="20" maxlength="50" value="${item.update_dt}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="update_usn" /></th>
            <td><mf:input type="text" name="update_usn" size="20" maxlength="50" value="${item.update_usn}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="active_yn" /></th>
            <td><mf:input type="text" name="active_yn" size="20" maxlength="50" value="${item.active_yn}" /></td>
        </tr>
        
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center"><mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> <mf:button
                bundle="button" key="goList" onclick="goList()" /></td>
        </tr>
    </table>
</mf:form>
