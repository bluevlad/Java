<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
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
    
    function setChoose(frm_id, elm_id, lec_cd) {
        var urlname = CPATH + "/wlc.admin/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id+"&lec_cd="+lec_cd;
        var oWin = window.open(urlname,
            "setChooseWin",
            "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
        windows_focus(oWin);
    }
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
        <th><mf:label name="exmtitle" /></th>
        <td colspan="3"><mf:input type="text" name="exmtitle" size="100" maxlength="50" value="" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmdesc" /></th>
        <td colspan="3"><mf:textarea name="exmdesc" cols="120" rows="3" value=""/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="etest.common" key="msg.exm_set"/></th>
        <td colspan="3">
            <mf:input type="text" name="setid" size="20" maxlength="50" value="" required="true"/> 
            <mf:button bundle="button" key="select" onclick="setChoose('myform','setid','${lec_cd}')"/>
        </td>
    </tr>
    <tr>
        <th><mf:label name="exm_sdat" /></th>
        <td><mf:input type="date" name="exm_sdat" size="12" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/></td>
        <th><mf:label name="exm_edat" /></th>
        <td><mf:input type="date" name="exm_edat" size="12" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmview" /></th>
        <td><mf:select name="exmview" codeGroup="ACTIVE_YN"/></td>
        <th><mf:label name="exmopen" /></th>
        <td><mf:select name="exmopen" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmpage" /></th>
        <td colspan="3"><mf:input type="text" name="exmpage" size="20" maxlength="50" value="1" required="true" option="number"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
			<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save" />
			<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>
</div>