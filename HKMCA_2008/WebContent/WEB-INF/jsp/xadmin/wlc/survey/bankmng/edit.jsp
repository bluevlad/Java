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

addEvent(window, 'load', function(){type_reload('myform', 'auto')})
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doWrite();return false;" enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>

<jsp:include page="_edit.jsp"/>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="viewBtn">     
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doWrite()" icon="icon_save"/>
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete"/>
        </td>
    </tr>
</table>
</mf:form>