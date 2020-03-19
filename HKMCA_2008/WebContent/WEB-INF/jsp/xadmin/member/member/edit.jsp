<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
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
</script>
<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="usn" value="${item.usn}"/>  
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="usn"/></th> 
        <td><mh:out value="${item.usn}"/></td>
        <th><mf:label name="userid"/></th> 
        <td><mf:input type="text" name="userid" cssStyle="width:98%" value="${item.userid}"/></td>
    </tr>
    <tr>
        <th><mf:label name="passwd"/></th> 
        <td><mf:input type="text" name="passwd" cssStyle="width:98%" value="${item.passwd}"/></td>
        <th><mf:label name="nm"/></th> 
        <td><mf:input type="text" name="nm" cssStyle="width:98%" value="${item.nm}"/></td>
    </tr>
    <tr>
        <th><mf:label name="pos_nm"/></th> 
        <td><mf:input type="text" name="pos_nm" cssStyle="width:98%" value="${item.pos_nm}"/></td>
        <th><mf:label name="hp"/></th> 
        <td><mf:input type="text" name="hp" cssStyle="width:98%" value="${item.hp}"/></td>
    </tr>
    <tr>
        <th><mf:label name="tel"/></th> 
        <td><mf:input type="text" name="tel" cssStyle="width:98%" value="${item.tel}"/></td>
        <th><mf:label name="fax"/></th> 
        <td><mf:input type="text" name="fax" cssStyle="width:98%" value="${item.fax}"/></td>
    </tr>
    <tr>
        <th><mf:label name="email"/></th> 
        <td colspan="3"><mf:input type="text" name="email" cssStyle="width:98%" value="${item.email}"/></td>
    </tr>
    <tr>
        <th><mf:label name="add"/></th> 
        <td colspan="3">
            <mf:input type="text" name="zip" cssStyle="width:70px" readonly="true" value="${item.zip}" onclick="javascript:ZipCodeSeach('myform','post=zip&addr1=addr1&addr2=addr2');"/>
            <mf:input type="text" name="addr1" cssStyle="width:250px" value="${item.addr1}"/>
            <mf:input type="text" name="addr2" cssStyle="width:250px" value="${item.addr2}"/>
        </td>
    </tr>
    <tr>
        <th><mf:label name="org_cd"/></th> 
        <td><mf:input type="text" name="org_cd" cssStyle="width:98%" value="${item.org_cd}"/></td>
        <th><mf:label name="active_yn"/></th> 
        <td><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">     
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/>       
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
</div>