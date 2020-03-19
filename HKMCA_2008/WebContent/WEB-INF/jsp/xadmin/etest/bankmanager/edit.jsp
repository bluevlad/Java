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
	if(frm) {
	    if (validate(frm)) {
	        <c:choose>
	            <c:when test="${param.cmd == 'edit'}">
	                frm.cmd.value = "update";
	            </c:when>
	            <c:when test="${param.cmd == 'add'}">
	                frm.cmd.value = "insert";
	            </c:when>
	            <c:otherwise>
	                frm.cmd.value = "insert";
	            </c:otherwise>
	        </c:choose>
	        frm.submit();
	    }
	} else {
	    alert ("form[" + frmName + "] is invalid");
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

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doWrite();return false;" enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>

<jsp:include page="_edit.jsp"/>
<br>
<table border="0" cellpadding="2" cellspacing="1" width="100%" class="viewBtn">     
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doWrite()" icon="icon_save"/>
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete"/>
        </td>
    </tr>
</table>
</mf:form>
</div>