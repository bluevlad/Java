<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
	function frmSubmit(frmName) {
		var frm = getObject(frmName);
		if(frm) {
			if (validate(frm)) {
				// 사용자 조건 추가.
				frm.cmd.value="insert";
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
//-->
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="surveytitle" /></th>
        <td colspan="3"><mf:input type="text" name="surveytitle" size="100" maxlength="50" value="" /></td>
    </tr>
    <tr>
        <th><mf:label name="surveydesc" /></th>
        <td colspan="3"><mf:textarea name="surveydesc" cols="110" rows="3" value=""/></td>
    </tr>
    <tr>
        <th><mf:label name="survey_sdat" /></th>
        <td><mf:input type="date" name="survey_sdat" size="10" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
        <th><mf:label name="survey_edat" /></th>
        <td><mf:input type="date" name="survey_edat" size="10" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
    </tr>
</table>

<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
	         <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save" />
	         <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>
</div>