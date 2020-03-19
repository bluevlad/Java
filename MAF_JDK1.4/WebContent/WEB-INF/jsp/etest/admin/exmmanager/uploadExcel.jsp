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
	    	<c:param name="cmd" value="view"/>
            <c:param name="exmid" value="${exmid}"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}
</script>
<div class="viewContainer">
<mf:form action="${control_action}"  method="post" enctype="multipart/form-data" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="exmid" value="${exmid}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mh:out value="Download"/></th> 
	    <td><a href='javascript:doSample();'><mh:out value="Sample.xls" td="true"/></a></td>
	    <th><mh:out value="Upload File"/></th> 
	    <td><input type="file" name="certifile"></td>
 	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%">	
	<tr>
		<td align="right">
		<mf:button bundle="button" key="save" onclick="javascript:doUploadOk()"/>
		<mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
	</tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/jsp/common/excelupload/uploadfileinfo.jsp"/>
</div>