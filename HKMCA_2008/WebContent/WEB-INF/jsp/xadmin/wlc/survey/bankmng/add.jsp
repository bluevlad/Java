<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

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

        frm.cmd.value='insert';
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

addEvent(window, 'load', function(){type_reload('myform', 'auto')})
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doWrite();return false;" enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>

<jsp:include page="_edit.jsp"/>
<table border="0" cellpadding="2" cellspacing="1" width="100%">     
    <tr>
        <td align="center">
	        <mf:button bundle="button" key="button.save" onclick="doWrite()"/>
	        <mf:button bundle="button" key="button.list" onclick="goList()"/>
        </td>
    </tr>
</table>
</mf:form>