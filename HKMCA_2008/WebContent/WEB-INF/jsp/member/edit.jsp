<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
function frmSubmit() {
    var frm = getObject("myform");
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "update";
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
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="usn" value="${item.usn}"/>  
<mf:input type="hidden" name="active_yn" value="Y"/>  
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="userid"/></th> 
        <td><mf:input type="text" name="userid" required="true" cssStyle="width:95%" value="${item.userid}" onclick="javascript:UserInfo('iduniq', 'myform');"/></td>
        <th><mf:label name="passwd"/></th> 
        <td><mf:input type="text" name="passwd" required="true" cssStyle="width:95%" value="${item.passwd}"/></td>
    </tr>
    <tr>
        <th><mf:label name="nm"/></th> 
        <td><mf:input type="text" name="nm" required="true" cssStyle="width:95%" value="${item.nm}"/></td>
        <th><mf:label name="nm_eng"/></th> 
        <td><mf:input type="text" name="nm_eng" cssStyle="width:95%" value="${item.nm_eng}"/></td>
    </tr>
    <tr>
        <th><mf:label name="pos_nm"/></th> 
        <td><mf:input type="text" name="pos_nm" cssStyle="width:95%" value="${item.pos_nm}"/></td>
        <th><mf:label name="hp"/></th> 
        <td><mf:input type="text" name="hp" cssStyle="width:95%" option="phone" value="${item.hp}"/></td>
    </tr>
    <tr>
        <th><mf:label name="tel"/></th> 
        <td><mf:input type="text" name="tel" cssStyle="width:95%" option="phone" value="${item.tel}"/></td>
        <th><mf:label name="fax"/></th> 
        <td><mf:input type="text" name="fax" cssStyle="width:95%" option="phone" value="${item.fax}"/></td>
    </tr>
    <tr>
        <th><mf:label name="email"/></th> 
        <td colspan="3"><mf:input type="text" name="email" cssStyle="width:95%" option="email" value="${item.email}"/></td>
    </tr>
    <tr>
        <th><mf:label name="add"/></th> 
        <td colspan="3">
            <mf:input type="text" name="zip" cssStyle="width:70px" readonly="true" value="${item.zip}" onclick="javascript:ZipCodeSeach('myform','post=zip&addr1=addr1&addr2=addr2');"/>
            <mf:input type="text" name="addr1" cssStyle="width:250px" value="${item.addr1}"/>
            <mf:input type="text" name="addr2" cssStyle="width:250px" value="${item.addr2}"/>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">     
    <tr>
        <td align="right"><mf:button bundle="button" key="save" icon="icon_save" onclick="frmSubmit('myform')"/></td>
    </tr>
</table>
</mf:form>