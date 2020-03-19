<%@ page contentType="text/html; charset=euc-kr"%>         	
<c:set var="bundle" value="mafpager"/>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script>
   function doReply() {
        var frm = getObject("myform");
		frm.${mrt:mvcCmd()}.value = "msgWrite";
        frm.submit();
    }
	function doDelete(){
	    var frm = getObject("myform");
	    if(frm) {
			if (confirm('쪽지를 삭제하시겟습니까?')){
			    frm.${mrt:mvcCmd()}.value = "deleteSnd"; 
			    frm.submit();
			}
		}
	}		
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
	            <c:param name="${mrt:mvcCmd()}" value="listRcv"/>
	        </c:url>
	        document.location = "${urlList}";
	    }
</script>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="100"/>
	<col width="30*"/>
	<col width="100"/>
	<col width="30*"/>
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="">
	<input type="hidden" name="msg_no" value="${item.msg_no}">  
	<input type="hidden" name="msg_no_p" value="${item.msg_no}">

	<tr>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_rcv_nm"/></th> 
	    <td class="view" nowrap>${mhtml:null2nbsp(item.msg_rcv_nm)} &lt;${item.msg_rcv_userid}&gt;</td>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_dt"/></th> 
	    <td class="view" nowrap>${mfmt:fullDateTime(item.msg_dt)}</td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_snd_nm"/></th> 
	    <td class="view" nowrap>${mhtml:null2nbsp(item.msg_snd_nm)} &lt;${item.msg_snd_userid}&gt;</td>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_read_dt"/></th> 
	    <td class="view" nowrap>${mfmt:fullDateTime(item.msg_read_dt)}</td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_title"/></th> 
	    <td class="view" colspan="3">${mhtml:null2nbsp(item.msg_title)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.msg_info"/></th> 
	    <td class="view"  colspan="3">${mhtml:null2nbsp(item.msg_info)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mfmt:message bundle="${bundle}" key="form.file_name"/></th> 
	    <td class="view"  colspan="3">${mhtml:null2nbsp(item.file_name)}</td>
 	</tr>

	
	<tr>
		<td colspan="4" align="center">
			<a href="javascript:doReply()"><mfmt:button bundle="mafpager" key="btn.reply"/></a> 
			<a href="javascript:doDelete()"><mfmt:button bundle="mafpager" key="btn.delete"/></a>
			<a href="javascript:goList()"><mfmt:button bundle = "button"  key="goList"/></a></td>
	</tr>
</form>
</table>

		