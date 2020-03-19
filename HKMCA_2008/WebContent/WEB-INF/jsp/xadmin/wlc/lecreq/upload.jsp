<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doUploadOk() {
        var frm = getObject("myform");
		if(frm) {
	        frm.cmd.value="upload_ok";
	        frm.submit();
        }
    }
    function doSample() {
        var frm = getObject("myform");
		if(frm) {
	        frm.cmd.value="sample";
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
<mf:form action="${control_action}"  method="post" enctype="multipart/form-data" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mh:out value="Download"/></th> 
	    <td><a href='javascript:doSample();'><mh:out value="sample.xls" td="true"/></a></td>
	    <th><mh:out value="Upload File"/></th> 
	    <td><input type="file" name="lecfile"></td>
 	</tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="searchBtn">	
	<tr>
		<td align="right">
			<mf:button bundle="button" key="save" onclick="javascript:doUploadOk()" icon="icon_add"/>
			<mf:button bundle="button" key="list" onclick="javascript:goList()" icon="icon_list"/>
        </td>
	</tr>
</table>
</mf:form>
</div>