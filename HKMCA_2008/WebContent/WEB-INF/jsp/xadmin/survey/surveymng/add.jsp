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
    
    function setChoose(frm_id, elm_id) {
        var urlname = CPATH + "/xadmin.survey/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id;
        var oWin = window.open(urlname,
            "setChooseWin",
            "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
        windows_focus(oWin);
    }
//-->
</script>
<jf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<jf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<jf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><jf:label name="surveytitle" /></th>
        <td colspan="3"><jf:input type="text" name="surveytitle" size="100" maxlength="50" value="" /></td>
    </tr>
    <tr>
        <th><jf:label name="surveydesc" /></th>
        <td colspan="3"><jf:textarea name="surveydesc" cols="120" rows="3" value=""/></td>
    </tr>
    <tr>
        <th><jf:label name="survey_sdat" /></th>
        <td><jf:input type="date" name="survey_sdat" size="10" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
        <th><jf:label name="survey_edat" /></th>
        <td><jf:input type="date" name="survey_edat" size="10" value="" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
    </tr>
    <tr>
        <th><jf:label name="set"/></th>
        <td colspan="3"><jf:input type="text" name="setid" size="20" maxlength="50" value="" /><jf:button bundle="button" key="select" onclick="javascript:setChoose('myform','setid')" /></td>
    </tr>
    <tr>
        <th><jf:label name="survey_target"/></th>
        <td colspan="3">
            <c:forEach var="target" items="${target}">
                <jf:input type="checkbox" name="vtarget" value="${target.role_id}"/><mh:out value="${target.role_name}" />&nbsp;&nbsp;
            </c:forEach>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
         <jf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_add" />
         <jf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</jf:form>