<%@ page contentType="text/html; charset=euc-kr"%>         	
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" >
		function frmSubmit(frm) {

			if (validate(frm)) {

				frm.cmd.value="sendMsg";
				return true;
			} else {

				return false;
			}
		}
		function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
	            <c:param name="${mrt:mvcCmd()}" value="listSnd"/>
	        </c:url>
	        document.location = "${urlList}";
	    }

</script>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="150"/>
	<col width="100%"/>
<form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="">
	<input type="hidden" name="msg_no_p" value="${item.msg_no}">
	<tr>
	    <th class="view center" colspan="2"><mfmt:message bundle="mafpager" key="notice.writeaddr" /></th> 

 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="msg_rcv_userid"/></th> 
	    <td class="view"><mform:input name="msg_rcv_userid" size="40" value="${item.msg_snd_userid};" /></td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="msg_rcv_nm"/></th> 
	    <td class="view"><mform:input name="msg_rcv_nm" size="40" value="${item.msg_snd_nm}"/></td>
 	</tr>

	
	<tr>
	    <th class="view" nowrap><mform:label name="msg_title"/></th> 
	    <td class="view"><mform:input name="msg_title" size="40" value="${item.msg_title}"/></td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="msg_info"/></th> 
	    <td class="view"><mform:input name="msg_info" size="20" cols="40" rows="10" value="${item.msg_info}"/></td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="file_name"/></th> 
	    <td class="view"><mform:input name="file_name" size="20" maxlength="50" value="${item.file_name}"/></td>
 	</tr>

	
	<tr>
		<td colspan="2" align="center"><mform:submit bundle="button" key="save"/> <a href="javascript:goList()"/> <mfmt:message bundle="button"  key="goList"/></a></td>
	</tr>
</form>
</table>

		