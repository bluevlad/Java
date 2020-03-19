<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
   function doReply() {
        var frm = getObject("myform");
        frm.cmd.value = "msgWrite";
        frm.submit();
    }
    function doDelete(){
        var frm = getObject("myform");
        if(frm) {
            if (confirm('<mfmt:message bundle="common.scripts" key="pager.delete"/>')){
                frm.cmd.value = "deleteSnd"; 
                frm.submit();
            }
        }
    }       
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="listRcv"/>
        </c:url>
        document.location = "${urlList}";
    }
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="msg_no_p" value="${item.msg_no}"/>  
<mf:input type="hidden" name="msg_no" value="${item.msg_no}"/>  
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mfmt:message bundle="pager" key="msg_rcv_usn"/></th> 
        <td><mh:out value="${item.msg_rcv_nm}" td="true"/>(<mh:out value="${item.msg_rcv_id}" td="true"/>)</td>
        <th><mfmt:message bundle="pager" key="msg_dt"/></th> 
        <td><mh:out value="${item.msg_dt}" format="fulldate"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="pager" key="msg_title"/></th> 
        <td colspan="3"><mh:out value="${item.msg_title}" nl2br="true"/></td>
    </tr>
    
    <tr>
        <th><mfmt:message bundle="pager" key="msg_info"/></th> 
        <td colspan="3"><mh:out value="${item.msg_info}" nl2br="true"/></td>
    </tr>
    
    <tr>
        <th><mfmt:message bundle="pager" key="file_name"/></th> 
        <td colspan="3"><mh:out value="${item.file_name}" td="true"/></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="1" width="100%" class="viewBtn">     
    <tr>
        <td align="right"><mf:button bundle="button" key="pager.delete" onclick="doDelete()"/></td>
    </tr>
</table>
</mf:form>