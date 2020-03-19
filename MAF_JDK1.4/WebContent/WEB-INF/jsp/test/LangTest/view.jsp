<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script>

    
    function doEdit() {
        var frm = getObject("myform");
        
			frm.code.value = '<c:out value="${item.code}"/>';
		 
        frm.submit();
    }
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	            <c:param name="cmd" value="list"/>
	        </c:url>
	        document.location = '<mh:out value="${urlList}"/>';
	    }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
	<input type="hidden" name="cmd" value="edit">
	<input type='hidden' name='code' value="">  
</mf:form>
<div class="viewContainer">
<h1>정보</h1>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<tr>
	    <th nowrap><mf:header name="code"/></th> 
	    <td ><mh:out value="${item.code}" /></td>
 	</tr>
	
	<tr>
	    <th nowrap><mf:header name="allnames"/></th> 
	    <td ><mh:out value="${item.allnames}" /></td>
 	</tr>
	
	<tr>
	    <th nowrap><mf:header name="active_yn"/></th> 
	    <td ><mh:out value="${item.active_yn}" /></td>
 	</tr>
	
	<tr>
	    <th nowrap><mf:header name="local_name"/></th> 
	    <td ><mh:out value="${item.local_name}" /></td>
 	</tr>
	
	<tr>
		<td colspan="2" align="center"><mf:button bundle="button" key="edit" onclick="doEdit();"/> <mf:button bundle = "button"  key="goList" onclick="goList();"/></td>
	</tr>
</table>
</div>
		
		
		