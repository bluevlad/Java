<%@ page contentType="text/html; charset=euc-kr"%>         	
<script language="javascript" src="${CPATH}/js/lib.validate_200401007.js"></script>
<script language="javascript" src="${CPATH}/js/lib.calendar.js"></script>
<script language="javascript" >
		function frmSubmit(frm) {
			if (validate(frm)) {
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
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="update">
	<input type="hidden" name="poll_id" value="${item.poll_id}">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%">	

	<tr>
	    <th class="view" nowrap><mform:label name="site"/></th> 
	    <td class="view"><mform:input name="site" size="20" maxlength="50" value="${item.site}"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="start_dt"/></th> 
	    <td class="view"><mform:input name="start_dt" size="20" maxlength="50" value="${item.start_dt}" OnClick="popUpCalendar(this, this, 'yyyymmdd');"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="end_dt"/></th> 
	    <td class="view"><mform:input name="end_dt" size="20" maxlength="50" value="${item.end_dt}" OnClick="popUpCalendar(this, this, 'yyyymmdd');"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="is_use"/></th> 
	    <td class="view"><mform:input name="is_use" size="20" maxlength="50" value="${item.is_use}"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="title"/></th> 
	    <td class="view"><mform:input name="title" size="50" maxlength="50" value="${item.title}"/></td>
 	</tr>
	<tr>
	    <th class="view" nowrap><mform:label name="is_show"/></th> 
	    <td class="view"><mform:input name="is_show" size="20" maxlength="50" value="${item.is_show}"/></td>
 	</tr>
	<tr>
		<td colspan="2" align="center"><mform:submit bundle="button" key="button.save"/> <a href="javascript:goList()"/> <mfmt:button bundle="button"  key="goList"/></a></td>
	</tr>

</table>


<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
		<col width="50"/>
		<col width="50"/>
		<col width="*"/>
		<col width="100"/>
		<col width="200"/>
	    <tr>
			<th class="list BL" >#</th>	
	      	<th class="list" ><mform:header name="seq"/></th>	
	      	<th class="list" ><mform:header name="question"/></th>	
			<th class="list " ><mform:header name="hit"/></th>				
			<th class="list BR" >삭제여부</th>		
	    </tr>
		<c:set var="maxid" value="0"/>
	    <c:forEach var="item" items="${pollitems}" varStatus="status">
			<input type="hidden" name="item_ids" value="${item.item_id}">
			<input type="hidden" name="item_nids" value="${item.item_id}">
			<c:set var="id" value="${item.item_id}"/>
			<c:if test="${empty item.item_id}">
			</c:if>
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list center">${status.count}</td>
			<td class="list center"><input name="seq_${id}" type="text" size="4" value="${item.seq}" option="number" hname="순번"></td>	
			<td class="list"><input name="question_${id}" type="text" size="50" value="${mhtml:null2nbsp(item.question)}"></td>	
			<td class="list right">${mhtml:null2nbsp(item.hit)}</td>
			<td class="list center"><input type="radio" name="del_${id}" value="Y">:[삭제]</td>	
		</tr>
	    </c:forEach>

</table>
</form>
<form action="${control_action}" method="post" name="frmItemAdd" id="frmItemAdd" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="addItem">
	<input type="hidden" name="poll_id" value="${item.poll_id}">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
		<col width="50"/>
		<col width="*"/>
		<col width="100"/>
	    <tr>
	      	<th class="list" ><mform:header name="seq"/></th>	
	      	<th class="list" ><mform:header name="question"/></th>	
		
	    </tr>
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list center"><input name="seq" type="text" size="4" value="0" option="number" hname="순번"></td>	
			<td class="list"><input name="question" type="text" size="50" value="" hname="<mform:header name="question"/>"></td>	
		</tr>
		<tr>
			<td colspan="2" align="center"><mform:submit bundle="button" key="button.add"/></td>
		</tr>
</table>
</form>