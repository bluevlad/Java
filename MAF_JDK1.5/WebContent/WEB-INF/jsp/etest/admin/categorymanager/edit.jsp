<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>' ></script>
<c:choose>
    <c:when test="${param.cmd == 'edit'}">
        <c:set var="acttype" value="update"/>
    </c:when>
    <c:otherwise>
        <c:set var="acttype" value="insert"/>
    </c:otherwise>
</c:choose>	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>

<mf:form action="${control_action}" method="post" name="frmEdit" id="frmEdit" >
	<mf:input type="hidden" name="cmd" value="${acttype}"/>
    <mf:input type="hidden" name="old_cat_id" value="${item.cat_id}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="25%"/>
	<col width="75%"/>
    <c:if test="${!empty item.cat_id}">
    <tr>
        <th nowrap><mf:label name="cat_id"/></th> 
        <td ><c:out value="${item.cat_id}" /></td>
    </tr>
    </c:if>
	
	<tr>
		<th nowrap><mf:label name="cat_name"/></th> 
	    <td ><mf:input type="text" name="cat_name" size="20" maxlength="50" value="${item.cat_name}" required="true"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="cat_description"/></th> 
	    <td ><mf:input type="text" name="cat_description" size="20" maxlength="50" value="${item.cat_description}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="p_cat_id"/></th> 
	    <td ><mf:input type="text" name="p_cat_id" size="20" maxlength="50" value="${item.p_cat_id}" required="true"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="active_yn"/></th> 
	    <td ><mf:select name="active_yn" curValue="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="seq"/></th> 
	    <td ><mf:input type="text" name="seq" size="20" maxlength="50" value="${item.seq}"/></td>
 	</tr>
	
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">		
	<tr>
		<td colspan="2" align="center">
        <c:if test="${acttype == 'update'}">
            <mf:button bundle="button" key="add" onclick="m_add();"/>       
        </c:if>
		<mf:button bundle="button" key="save" onclick="m_update();"/>		
		<c:if test="${acttype == 'update'}">
            <mf:button bundle="button" key="delete" onclick="m_delete();"/>   
        </c:if></td>
	</tr>
</table>
</mf:form>

<script>

function m_delete(){
    var vfrm = getObject("frmEdit");
	if (confirm("Do you wish to delete?")) {
		vfrm.cmd.value="delete";
		vfrm.submit();
	}
}
function m_update(){
    var vfrm = getObject("frmEdit");
	if (validate(vfrm)) {      
		if (confirm("Do you wish to save?")) {

			vfrm.submit();
		}
	}	
}
function m_add(){
	<c:url var="t" value="${control_action}">
		<c:param name="cmd" value="add"/>
	</c:url>
    var vfrm = getObject("frmEdit");
    if (confirm("Do you wish to add sub category?")) {
        document.location.href = '<c:out value="${t}" escapeXml="false"/>&p_cat_id=' + vfrm.old_cat_id.value;
    }
}
<c:if test="${param.reload =='T'}">
	parent.frmTree.location.reload();
</c:if>
</script>
