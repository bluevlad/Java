<%@ page contentType="text/html; charset=euc-kr"%>         	
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" src="${CPATH}/js/calendar.js"></script>
<script language="javascript" >
	function frmSubmit(frm) {
		if (validate(frm)) {
			// 사용자 조건 추가.
			frm.${mrt:mvcCmd()}.value = "${(param.cmd == 'edit') ? 'update' : 'insert'}";
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
	function doDelete() {
		var frm = getObject("myform");	
		frm.${mrt:mvcCmd()}.value = "delete";
		frm.submit();
	}
	function doWrite(){
	    var frm = getObject("myform");
	    frm.${mrt:mvcCmd()}.value = "add";
	    frm.submit();
	}
</script>
<table border="0" cellpadding="2" cellspacing="0" class="view">	
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	<input type="hidden" name="${mrt:mvcCmd()}" value="">
	<input type="hidden" name="ymd" value="${item.ymd}">  
	<input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
	<tr>
	    <th class="view" nowrap>일자</th>
	    <td class="view">
			<c:if test="${param.cmd == 'edit'}">
		        ${item.ymd2}
			</c:if>
			<c:if test="${param.cmd != 'edit'}">
		        <input type="text" name="ymd2" size="10" readonly required OnClick="popUpCalendar(this, this, 'yyyy-mm-dd');" hname="일자">
			</c:if>
	    </td>
 	</tr>
	<tr>
	    <th class="view" nowrap>금융계휴일여부</th> 
	    <td class="view">
			<select name="bank_work">
				<option value="1" ${item.bank_work == '1' ? 'selected':''}>근무일</option>
				<option value="0" ${item.bank_work == '0' ? 'selected':''}>휴일</option>
			</select>
	    </td>
 	</tr>
	<tr>
	    <th class="view" nowrap>휴일여부</th> 
	    <td class="view">
			<select name="work_day">
				<option value="1" ${item.work_day == '1' ? 'selected':''}>근무일</option>
				<option value="0" ${item.work_day == '0' ? 'selected':''}>휴일</option>
			</select>
	    </td>
 	</tr>
	<tr>
	    <th class="view" nowrap>휴일정보</th> 
	    <td class="view"><input type="text" name="bigo" size="20" maxlength="50" value="${item.bigo}" /></td>
 	</tr>
	<tr>
		<td colspan="2" align="center">
			<c:if test="${param.cmd == 'edit'}">
				<a href="javascript:doWrite();"><mfmt:message bundle="button" key="register"/></a>			
			</c:if>
			<mform:submit bundle="button" key="button.save"/>			
			<c:if test="${param.cmd == 'edit'}">
				<!-- a href="javascript:doDelete();"><mfmt:message bundle="button" key="delete"/></a -->
			</c:if>
			<a href="javascript:goList();"><mfmt:message bundle="button" key="list"/></a>
		</td>
	</tr>
</form>
</table>

		