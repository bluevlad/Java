<%@ page contentType="text/html; charset=utf-8"%>         	
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" >

    
    function doEdit() {
        var frm = getObject("myform");
			frm.msgid.value = "${item.msgid}";
		 
        frm.submit();
    }
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
	            <c:param name="${mrt:mvcCmd()}" value="rcvList"/>
	        </c:url>
	        document.location = "${urlList}";
	    }
</script>
<table border="0" cellpadding="2" cellspacing="0" class="view">	
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="cmd" value="edit">
	<input type="hidden" name="msgid" value="${item.msgid}">
	<tr>
	    <th class="view" nowrap><mform:label name="msgid"/></th> 
	    <td class="view">${mhu:null2nbsp(item.msgid)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="usn_snd"/></th> 
	    <td class="view">${mhu:null2nbsp(item.usn_snd)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="usn_rcv"/></th> 
	    <td class="view">${mhu:null2nbsp(item.usn_rcv)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="nm_snd"/></th> 
	    <td class="view">${mhu:null2nbsp(item.nm_snd)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="nm_rcv"/></th> 
	    <td class="view">${mhu:null2nbsp(item.nm_rcv)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="title"/></th> 
	    <td class="view">${mhu:null2nbsp(item.title)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="contents"/></th> 
	    <td class="view">${mhu:null2nbsp(item.contents)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="deleted"/></th> 
	    <td class="view">${mhu:null2nbsp(item.deleted)}</td>
 	</tr>
	
	<tr>
		<td colspan="2" align="center"><a href="javascript:doReply()"><mfmt:message bundle="msg" key="button.reply"/></a> <a href="javascript:goList()"><mfmt:message bundle="button"  key="goList"/></a></td>
	</tr>
</form>
</table>

		