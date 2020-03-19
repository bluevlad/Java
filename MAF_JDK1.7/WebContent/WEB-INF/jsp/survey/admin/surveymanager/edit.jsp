<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/calendar.js"></script>
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
    <mf:input type="hidden" name="surveyid" value="${item.surveyid}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:label name="surveytitle" /></th>
            <td colspan="3"><mf:input type="text" name="surveytitle" size="100" maxlength="100" value="${item.surveytitle}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="surveydesc" /></th>
            <td colspan="3"><mf:input type="text" name="surveydesc" size="100" maxlength="1000" value="${item.surveydesc}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="survey_sdat_t1" /></th>
            <td><mf:input type="date" name="survey_sdat_t1" size="10" value="${item.survey_sdat_t1}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
            <th nowrap><mf:label name="survey_edat_t1" /></th>
            <td><mf:input type="date" name="survey_edat_t1" size="10" value="${item.survey_edat_t1}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="active_yn" /></th>
            <td colspan="3"><mf:select name="active_yn" curValue="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
        </tr>
    </table>

    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <c:forEach var="target" items="${target}">
        <tr>
            <td><mf:input type="checkbox" name="vtarget" value="${target.code_no}" curValue="${target.is_chk}"/><mh:out value="${target.code_name}" /></td>
        </tr>
        </c:forEach>
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
