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
        var urlname = CPATH + "/xadmin.survey/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id;
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
<mf:input type="hidden" name="surveyid" value="${item.surveyid}" />
<mf:input type="hidden" name="surveyowner" value="${item.surveyowner}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="surveytitle" /></th>
        <td colspan="3"><mf:input type="text" name="surveytitle" cssStyle="width:98%" value="${item.surveytitle}" /></td>
    </tr>
    <tr>
        <th><mf:label name="surveydesc" /></th>
        <td colspan="3"><mf:textarea name="surveydesc" cssStyle="width:98%" rows="3" value="${item.surveydesc}"/></td>
    </tr>
    <tr>
        <th><mf:label name="survey_sdat" /></th>
        <td><mf:input type="date" name="survey_sdat" cssStyle="width:90px" value="${item.survey_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/></td>
        <th><mf:label name="survey_edat" /></th>
        <td><mf:input type="date" name="survey_edat" cssStyle="width:90px" value="${item.survey_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/></td>
    </tr>
    <tr>
        <th><mf:label name="set"/></th>
        <td><mf:input type="text" name="setid" cssStyle="width:55px" value="${item.setid}" readonly="true" required="true" />&nbsp;<mf:button bundle="button" key="select" onclick="javascript:setChoose('myform','setid')" /></td>
        <th><mf:label name="active_yn" /></th>
        <td><mf:select name="active_yn" curValue="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th><mf:label name="survey_target"/></th>
        <td colspan="3">
            <c:forEach var="target" items="${target}">
                <mf:input type="checkbox" name="vtarget" value="${target.role_id}" curValue="${target.chk}"/><mh:out value="${target.role_name}" />&nbsp;&nbsp;
            </c:forEach>
        </td>
    </tr>
    <tr>
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