
<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>

<script language="javascript" >
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
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
	<input type="hidden" name="cmd" value="">
	
	<mf:input type="hidden" name="org_cd" value="${org_cd}"/>  
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	

	<col width="25%"/>
	<col width="75%"/>
	<tr>
		<th nowrap><mf:label name="org_cd"/></th> 
	    <td ><mf:input type="text" name="org_cd" size="20" maxlength="50" value="${item.org_cd}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="p_org_cd"/></th> 
	    <td ><mf:input type="text" name="p_org_cd" size="20" maxlength="50" value="${item.p_org_cd}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="org_type"/></th> 
	    <td ><mf:input type="text" name="org_type" size="20" maxlength="50" value="${item.org_type}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="region"/></th> 
	    <td ><mf:input type="text" name="region" size="20" maxlength="50" value="${item.region}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="org_name"/></th> 
	    <td ><mf:input type="text" name="org_name" size="20" maxlength="50" value="${item.org_name}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="nation"/></th> 
	    <td ><mf:input type="text" name="nation" size="20" maxlength="50" value="${item.nation}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="active_flag"/></th> 
	    <td ><mf:input type="text" name="active_flag" size="20" maxlength="50" value="${item.active_flag}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="dist_no"/></th> 
	    <td ><mf:input type="text" name="dist_no" size="20" maxlength="50" value="${item.dist_no}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="deal_no"/></th> 
	    <td ><mf:input type="text" name="deal_no" size="20" maxlength="50" value="${item.deal_no}"/></td>
 	</tr>
	
	<tr>
		<th nowrap><mf:label name="deal_region"/></th> 
	    <td ><mf:input type="text" name="deal_region" size="20" maxlength="50" value="${item.deal_region}"/></td>
 	</tr>
	
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">		
	<tr>
		<td colspan="2" align="center">
		<mf:button bundle="button" key="save" onclick="frmSubmit()"/>		
		<mf:button bundle = "button"  key="goList" onclick="goList()"/></td>
	</tr>
</table>
</mf:form>

		
		
		