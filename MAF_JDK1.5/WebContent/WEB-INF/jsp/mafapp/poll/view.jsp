<%@ page contentType="text/html; charset=euc-kr"%>         	
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script>

    
    function doEdit() {
        var frm = getObject("myform");
        
			frm.poll_id.value = "${item.poll_id}";
		 
        frm.submit();
    }
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
	            <c:param name="${mrt:mvcCmd()}" value="list"/>
	        </c:url>
	        document.location = "${urlList}";
	    }
</script>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%">	
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="edit">
	
	<input type="hidden" name="poll_id" value="${item.poll_id}">  
	
	<tr>
	    <th class="view" nowrap><mform:label name="poll_id"/></th> 
	    <td class="view">${mhtml:null2nbsp(item.poll_id)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="start_dt"/></th> 
	    <td class="view">${mfmt:shortDate(item.start_dt)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="end_dt"/></th> 
	    <td class="view">${mfmt:shortDate(item.end_dt)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="is_use"/></th> 
	    <td class="view">${mhtml:null2nbsp(item.is_use)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="title"/></th> 
	    <td class="view">${mhtml:null2nbsp(item.title)}</td>
 	</tr>
	
	<tr>
	    <th class="view" nowrap><mform:label name="is_show"/></th> 
	    <td class="view">${mhtml:null2nbsp(item.is_show)}</td>
 	</tr>
	
	<tr>
		<td colspan="2" align="center"><a href="javascript:doEdit()"><mfmt:button bundle="button" key="edit"/></a> <a href="javascript:goList()"><mfmt:button bundle="button"  key="goList"/></a></td>
	</tr>
</form>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
		<col width="50"/>
		<col width="*"/>
		<col width="100"/>
		<col width="100"/>
	    <tr>

	      	<th class="list BL" ><mform:header name="seq"/></th>	
	      	<th class="list" ><mform:header name="question"/></th>	
			<th class="list " ><mform:header name="hit"/></th>				
	      	<th class="list BR" ><mform:header name="img"/></th>	
	    </tr>
	    <c:forEach var="item" items="${pollitems}" varStatus="status">
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list center">${mhtml:null2nbsp(item.seq)}</td>	
			<td class="list">${mhtml:null2nbsp(item.question)}</td>	
			<td class="list left">${mhtml:null2nbsp(item.hit)}</td>	
			<td class="list">${mhtml:null2nbsp(item.img)}</td>	
		</tr>
	    </c:forEach>
</table>

		