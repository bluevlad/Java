<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="m" value="table.bas_org" />
<c:choose>
	<c:when test="${param.cmd == 'edit'}">
		<c:set var="acttype" value="update"/>
	</c:when>
	<c:otherwise>
		<c:set var="acttype" value="insert"/>
	</c:otherwise>
</c:choose>
<mf:form action='${control_action}' method="post" name="frmEdit" id="frmEdit">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="view">
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
		<th><mfmt:message bundle="${m}" key="org_cd" /></th>
		<td><mf:input type="text" name="org_cd" value='${item.org_cd}' required="true" hname="org_Cd" option="alphanum" readonly='${item.org_cd == "UBQ"}'/></td>
        <th><mfmt:message bundle="${m}" key="p_org_cd" /></th>
        <td><mf:input type="text" name="p_org_cd" value="${item.p_org_cd}" readonly='${item.org_cd == "UBQ"}' /></td>
	</tr>
	<tr>
		<th><mfmt:message bundle="${m}" key="org_name" /></th>
		<td colspan="3"><mf:input type="text" name="org_name" size="40" value="${item.org_name}" required="true" hname="ORG_NAME" /></td>
	</tr>
	<tr>
		<th><mfmt:message bundle="${m}" key="address" /></th>
		<td colspan="3"><mf:input type="text" name="address" size="50" value="${item.address}"  hname="address" /></td>
	</tr>
	<tr>
        <th><mfmt:message bundle="${m}" key="nation" /></th>
        <td><mf:input type="text" name="nation" value="${item.nation}"/></td>
	    <th><mfmt:message bundle="${m}" key="active_flag" /></th>
	    <td>
			<mf:input type="radio" name="active_flag" value="Y" curValue="${item.active_flag}"  /> : <mfmt:message bundle="common" key="table.yes"/>
			<mf:input type="radio" name="active_flag" value="N" curValue="${item.active_flag}"  /> : <mfmt:message bundle="common" key="table.no"/>		
	    </td>
	</tr>		
	<mf:input type="hidden" name="cmd" value="${acttype}"/>
	<mf:input type="hidden" name="current_org_cd" value="${org_cd}"/>
</table>
</mf:form>

<table width="100%" border="0" cellspacing="1" cellpadding="2">
<tr>
    <td align="center" class="view">
		<c:if test="${acttype == 'update'}">
	        <mf:button onclick="javascript:m_add()" bundle="button" key="add" />
		</c:if>
	        <mf:button onclick="javascript:m_update()" bundle="button" key="save" />
		<c:if test="${acttype == 'update'}">
            <mf:button onclick="javascript:m_delete()" bundle="button" key="delete" />
		</c:if>
    </td>
</tr>
</table>

<script language='javascript'>
<!--
var vfrm = document.frmEdit;
function m_delete(){
	if (confirm("삭제하시겠습니까?")) {

		vfrm.cmd.value="delete";
		vfrm.submit();
	}
}
function m_update(){
	if (validate(vfrm)) {      
		if (confirm("저장하시겠습니까?")) {

			vfrm.submit();
		}
	}	
}
function m_add(){
	<c:url var="t" value="${control_action}">
		<c:param name="cmd" value="add"/>
	</c:url>
    if (confirm("하위 메뉴를 작성하시겠습니까?")) {
        document.location.href = '<c:out value="${t}" escapeXml="false"/>&p_org_cd=' + vfrm.org_cd.value;
    }
}
<c:if test="${param.reload =='T'}">
	parent.frmTree.location.reload();
</c:if>
//-->
</script>