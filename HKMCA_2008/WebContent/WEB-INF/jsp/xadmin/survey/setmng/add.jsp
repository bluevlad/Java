<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
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

</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
    <input type="hidden" name="cmd" value="">
    <mf:input type="hidden" name="setid" value="${setid}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="85%" />
        <tr>
            <th nowrap><mf:label name="settitle" /></th>
            <td><mf:input type="text" name="settitle" cssStyle="width:98%" value="" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="setdesc" /></th>
            <td><mf:input type="text" name="setdesc" cssStyle="width:98%" value="" /></td>
        </tr>
    </table>
<br>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td align="right">
	            <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save" />
	            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
            </td>
        </tr>
    </table>
</mf:form>