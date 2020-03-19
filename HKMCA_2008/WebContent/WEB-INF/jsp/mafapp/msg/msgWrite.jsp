<%@ page contentType="text/html; charset=utf-8"%>         	
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" >
		function frmSubmit(frm) {
			if (validate(frm)) {
				// ����� v�� �߰� 
				frm.cmd.value="insert";
				return true;
			} else {
				return false;
			}
		}
		function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
	            <c:param name="${mrt:mvcCmd()}" value="list"/>
	        </c:url>
	        document.location = "${urlList}";
	    }

</script>
<table border="0" cellpadding="2" cellspacing="0" class="view">	
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="cmd" value="">
	<input type="hidden" name="usn_rcv" value="">  
	<tr>
	    <th class="view" nowrap><mform:label name="usn_rcv"/></th> 
	    <td class="view"><mform:input name="usn_rcv" size="20" maxlength="50" value="${item.usn_rcv}"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="nm_rcv"/></th> 
	    <td class="view"><mform:input name="nm_rcv" size="20" maxlength="50" value="${item.nm_rcv}"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="title"/></th> 
	    <td class="view"><mform:input name="title" size="20" maxlength="50" value="${item.title}"/></td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="contents"/></th> 
	    <td class="view"><mform:input name="contents" size="20" maxlength="50" value="${item.contents}"/></td>
 	</tr>
	<tr>
		<td colspan="2" align="center"><mform:submit bundle="button" key="button.save"/> <a href="javascript:goList()"/> <mfmt:message bundle="button"  key="goList"/></a></td>
	</tr>
</form>
</table>

		