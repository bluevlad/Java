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

</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="setid" value="${setid}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="settitle" /></th>
        <td colspan="3"><mf:input type="text" name="settitle" size="120" maxlength="200" value="${item.settitle}" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="setdesc" /></th>
        <td colspan="3"><mf:textarea name="setdesc" cols="120" rows="3" value=""/></td>
    </tr>
    <tr>
        <th><mf:label name="sjt_cd"/></th>
        <td colspan="3"><mf:select name="sjt_cd" items="${subject}" keyValue="sjt_cd" keyText="sjt_nm" curValue="${item.sjt_cd}"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmtime" /></th>
        <td><mf:input type="text" name="exmtime" size="5" maxlength="4" value="${item.exmtime}" option="number" required="true"/></td>
        <th><mf:label name="passing_score" /></th>
        <td><mf:input type="text" name="passing_score" size="5" maxlength="4" value="${item.passing_score}" option="number" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmtype"/></th> 
        <td><mf:select name="exmtype" codeGroup="ETEST.EXMTYPE" curValue="${item.exmtype}"></mf:select>
        <th><mf:label name="viewrand"/></th> 
        <td><mf:select name="viewrand" codeGroup="ACTIVE_YN" curValue="${item.viewrand}"></mf:select>
    </tr>        
</table>
<table border="0" cellpadding="2" cellspacing="2" class="viewBtn" width="100%">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save" />
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>