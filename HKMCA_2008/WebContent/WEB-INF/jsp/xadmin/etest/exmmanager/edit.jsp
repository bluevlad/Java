<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
	function frmSubmit(frmName) {
		var frm = getObject(frmName);
		if(frm) {
			if (validate(frm)) {
                <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "update";
                </c:when>
                <c:when test="${param.cmd == 'add'}">
                    frm.cmd.value = "insert";
                </c:when>
                <c:otherwise>
                    frm.cmd.value = "insert";
                </c:otherwise>
                </c:choose>
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
    
    function setChoose(frm_id, elm_id) {
        var urlname = CPATH + "/xadmin.etest/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id;
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
<mf:input type="hidden" name="exmid" value="${item.exmid}" />
<mf:input type="hidden" name="exmowner" value="${item.exmowner}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="exmtitle" /></th>
        <td colspan="3"><mf:input type="text" name="exmtitle" cssStyle="width:98%" value="${item.exmtitle}" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmdesc" /></th>
        <td colspan="3"><mf:textarea name="exmdesc" cssStyle="width:98%" rows="3" value="${item.exmdesc}"/></td>
    </tr>
    <tr>
        <th>Set</th>
        <td colspan="3">
            <mf:input type="text" name="setid" cssStyle="width:50px" value="${item.setid}" required="true" readonly="true"/> 
            <mf:button bundle="button" key="select" onclick="setChoose('myform','setid')"/>
        </td>
    </tr>
    <tr>
        <th><mf:label name="exm_sdat" /></th>
        <td><mf:input type="date" name="exm_sdat" cssStyle="width:100px" value="${item.exm_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
        <th><mf:label name="exm_edat" /></th>
        <td><mf:input type="date" name="exm_edat" cssStyle="width:100px" value="${item.exm_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown" /></td>
    </tr>
    <tr>
        <th><mf:label name="exmtime" /></th>
        <td><mf:input type="text" name="exmtime" cssStyle="width:50px" value="${item.exmtime}" required="true" option="number"/></td>
        <th><mf:label name="exmpage" /></th>
        <td><mf:input type="text" name="exmpage" cssStyle="width:25px" value="${item.exmpage}" required="true" option="number" /></td>
    </tr>
    <tr>
        <th><mf:label name="exmview" /></th>
        <td><mf:select name="exmview" curValue="${item.exmview}" codeGroup="ACTIVE_YN"/></td>
        <th><mf:label name="exmopen" /></th>
        <td><mf:select name="exmopen" curValue="${item.exmopen}" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th><mf:label name="active_yn" /></th>
        <td colspan="3"><mf:select name="active_yn" curValue="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
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