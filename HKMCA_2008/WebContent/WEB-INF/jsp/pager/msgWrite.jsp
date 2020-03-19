<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
function goWrite() {
    var frm = getObject("myform");
	frm.cmd.value="sendMsg";
    frm.submit();
}
</script>
<mf:form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="msg_no_p" value="${item.msg_no}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<col width="150"/>
	<col width="100%"/>
    <tr>
	    <th colspan="2"><mfmt:message bundle="pager" key="write" /></th> 
 	</tr>
	<tr>
	    <th><mf:label name="msg_rcv_usn"/></th> 
	    <td class="view"><mf:input cssStyle="width:95%" name="msg_rcv_userid" size="40" value="${item.msg_rcv_usn}" /></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mf:label name="msg_title"/></th> 
	    <td class="view"><mf:input cssStyle="width:95%" name="msg_title" size="40" value=""/></td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mf:label name="msg_info"/></th> 
	    <td class="view"><mf:textarea cssStyle="width:95%" rows="10" name="msg_info" value=""/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mf:label name="file_name"/></th> 
	    <td class="view"><mf:input name="file_name" size="20" maxlength="50" value=""/></td>
 	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="1" width="100%" class="viewBtn">     
    <tr>
        <td align="right"><mf:button bundle="button" key="pager.send" onclick="goWrite()"/></td>
    </tr>
</table>
</mf:form>