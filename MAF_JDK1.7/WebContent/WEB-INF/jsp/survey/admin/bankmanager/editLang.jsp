<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript"   src='<c:url value="/js/lib.validate.js"/>'></script>
<jsp:include page="_javascript.jsp"/>
<script language="javascript"   >

function goList()   {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<c:out value="${urlList}"/>';
}                

function frmSubmit(frm) {
    if(validate(frm)) {

        frm.cmd.value='update';
        return true;
    }   else {
        return false;
    }
}

function doWrite(){
    var frm = getObject("myform");
    if(frm)   {
        gogogo(frm, 1)
    } else {
        return;
    }
}

function doDelete(){
    var frm = getObject("myform");
     if(frm) {
        frm.cmd.value='delete';
        frm.submit();
    
    }
}	


//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doWrite();return false;" enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>

<jsp:include page="_editLang.jsp"/>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%">     
    <tr>
        <td colspan="3" align="center">
            <mf:button bundle="button" key="button.save" onclick="doWrite()"/>
	        <mf:button bundle="button" key="button.list" onclick="goList()"/>&nbsp;&nbsp;
            <mf:button bundle="button" key="button.delete" onclick="doDelete()"/>
        </td>
    </tr>
</table>
</mf:form>

