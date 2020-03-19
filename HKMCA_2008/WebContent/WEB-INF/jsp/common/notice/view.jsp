
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script>
    function doEdit() {
        var frm = getObject("myform");
		frm.id.value = '<c:out value="${item.id}"/>';
        frm.cmd.value="edit";		 		
        frm.submit();
    }
    function doDelete() {
        var frm = getObject("myform");
        frm.id.value = '<c:out value="${item.id}"/>';
        frm.cmd.value="delete";
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doEdit(); ">
    <mf:input type="hidden" name="cmd" value="edit"/>
    <mf:input type='hidden' name='id' value="${item.id}"/>
</mf:form>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th nowrap><mf:header name="title" /></th>
        <td colspan="3"><mh:capsule value="${item.category}" left="[" right="]"/><mh:out value="${item.title}" td="true" /> </td>
    </tr>
    <tr>
        <th nowrap><mf:header name="update_dt" /></th>
        <td colspan="3">
            <mh:out value="${item.update_dt}" td="true" format="fulldate"/>, 
                            <mh:out value="${item.update_id}" td="true" />/<mh:out value="${item.owner}" td="true" /></td>
    </tr>

    <tr>
        <th nowrap><mf:header name="contetnts" /></th>
        <td colspan="3"><div style="width: 780px; overflow: auto; text-align:left">
                            <style>
                                p {margin-top:0px;margin-bottom:0px;};
                            </style>
                            <mh:out value="${item.contetnts}" escapeXml="false"/><br>
                        </div></td>
    </tr>

    <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"> 
            <c:forEach var="fi" items="${files}">
                <a href='<c:url value="/wdownload">
                    <c:param name="type" value="notice_file"/>
                    <c:param name="filename" value="${fi.filename}" /></c:url>' target="_blank" ><mh:out value="${fi.org_filename }"/> (<mh:out value="${fi.file_size}"/> KB)</a><br>
           </c:forEach>&nbsp;
           </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
            <c:if test="${MAF_AAM.auth_d && IS_OWN}"><mf:button bundle="button" key="delete" onclick="doDelete();" /></c:if>
            <c:if test="${MAF_AAM.auth_u && IS_OWN}"><mf:button bundle="button" key="edit" onclick="doEdit();" /> </c:if>
            <mf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>
</div>
