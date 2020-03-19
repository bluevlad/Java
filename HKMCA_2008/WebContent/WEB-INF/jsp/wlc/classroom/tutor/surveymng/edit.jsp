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

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="surveyid" value="${item.surveyid}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="surveytitle" /></th>
        <td colspan="3"><mf:input type="text" name="surveytitle" size="100" maxlength="100" value="${item.surveytitle}" /></td>
    </tr>
    <tr>
        <th><mf:label name="surveydesc" /></th>
        <td colspan="3"><mf:textarea name="surveydesc" cols="110" rows="3" value="${item.surveydesc}"/></td>
    </tr>
    <tr>
        <th><mf:label name="survey_sdat" /></th>
        <td><mf:input type="date" name="survey_sdat" size="12" value="${item.survey_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
        <th><mf:label name="survey_edat" /></th>
        <td><mf:input type="date" name="survey_edat" size="12" value="${item.survey_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
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
<br>
<table border="0" cellpadding="2" cellspacing="0" width="100%">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="5%">
                <col width="10%">
                <col width="*">
                <col width="15%">
                <col width="15%">
                <tr>
                    <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_queid')"/></th>
                    <th>seq</th>
                    <th><mf:header name="surveytitle"/></th>
                    <th><mfmt:message bundle="survey" key="quetype"/></th>
                    <th><mfmt:message bundle="survey" key="questions"/></th>
                </tr>
                <c:forEach var="item" items="${bnk}" varStatus="status">
                <tr>
                    <td class="center"><mf:input type="checkbox" name="v_queid" value="${item.queid}" curValue="${item.chk}"/></td>
                    <td class="center"><mf:input type="text" name="seq_${item.queid}" size="2" value="${item.seq}" /></td>
                    <td><mh:out value="${item.quetitle}" td="true"/></td>
                    <td class="center"><mh:out value="${item.quetype}" codeGroup="ETEST.QTYPE"/></td>
                    <td class="center"><mh:out value="${item.quecount}" td="true"/></td>
                </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</mf:form>
</div>