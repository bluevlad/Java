<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
function doUpdate() {
    var frm = getObject("myform");
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
function doDel(){
    var frm = getObject("myform");
	if(frm) {
	    frm.cmd.value = "delete";
	    frm.submit();
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
<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="crs_nm"/></th> 
	    <td>
            <select name="crs_cd" required hname='<mf:header name="crs_nm"/>'>
            <c:forEach var="sitem" items="${courselist}" varStatus="status">
                <mf:option text="${sitem.crs_cd}:${sitem.crs_nm}" value="${sitem.crs_cd}" curValue="${item.crs_cd}"/>
            </c:forEach>
            </select>
        </td>
	    <th><mf:label name="sjt_cd"/></th> 
	    <td><mf:input type="text" name="sjt_cd" required="true" value="${item.sjt_cd}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="sjt_nm"/></th> 
	    <td colspan="3"><mf:input type="text" name="sjt_nm" required="true" size="120" value="${item.sjt_nm}"/></td>
 	</tr>
    <tr>
        <th><mf:label name="contents"/></th>
        <td colspan="3"><mf:textarea cssStyle="width:100%" rows="3" name="contents" value="${item.contents}"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="common" key="active_yn" /></th> 
        <td colspan="3"><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}" /></td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
	<tr>
		<td align="right">
			<mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save"/>
			<mf:button bundle="button" key="delete" onclick="doDel()" icon="icon_delete"/>
			<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
	</tr>
</table>
</mf:form>
</div>