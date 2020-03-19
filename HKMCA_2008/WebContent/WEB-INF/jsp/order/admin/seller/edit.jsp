<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
<!--
function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
        if (validate(frm)) {
    <c:choose>
    <c:when test="${param.cmd == 'add'}">
        frm.cmd.value="insert";
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
        frm.cmd.value="update";
    </c:when>
    </c:choose>
        frm.submit();
        }
    
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="20%"/>
    <col width="30%"/>    
    <col width="20%"/>
    <col width="30%"/>    
    <tr>
        <th><mf:label name="sell_cd"/></th> 
        <td><mf:input type="text" name="sell_cd" readonly="true" cssStyle="width:95%" value="${item.sell_cd}"/></td>
        <th><mf:label name="sell_nm"/></th> 
        <td><mf:input type="text" name="sell_nm" cssStyle="width:95%" value="${item.sell_nm}"/></td>
    </tr>    
    <tr>
        <th><mf:label name="sell_phone"/></th> 
        <td><mf:input type="text" name="sell_phone" cssStyle="width:150px" option="phone" value="${item.sell_phone}"/></td>
        <th><mf:label name="sell_fax"/></th> 
        <td><mf:input type="text" name="sell_fax" cssStyle="width:150px" option="phone" value="${item.sell_fax}"/></td>
    </tr>    
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">     
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/>   
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>