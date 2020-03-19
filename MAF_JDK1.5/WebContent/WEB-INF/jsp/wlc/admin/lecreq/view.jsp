<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doComplete() {
        var frm = getObject("myform");
        frm.cmd.value= "complete";
        frm.req_stat.value = "LE";
        frm.submit();
    }
    function doApprove() {
        var frm = getObject("myform");
        frm.cmd.value= "complete";
        frm.req_stat.value = "LP";
        frm.submit();
    }
    function doReq() {
        var frm = getObject("myform");
        frm.cmd.value= "complete";
        frm.req_stat.value = "LR";
        frm.submit();
    }
    function doCancel() {
        var frm = getObject("myform");
        frm.cmd.value= "complete";
        frm.req_stat.value = "CA";
        frm.submit();
    }
	function doExcel(){
		var frm = getObject("myform");
			frm.cmd.value = "excel";
			frm.submit();
	}
	function doUpload(){
		var frm = getObject("myform");
			frm.cmd.value = "upload";
			frm.submit();
	}
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}
</script>
<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform">
	<mf:input type="hidden" name="cmd" value=""/>
	<mf:input type="hidden" name="leccode" value="${item.leccode}"/>
	<mf:input type="hidden" name="req_stat" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="leccode"/></th> 
	    <td colspan="3"><mh:out value="${item.leccode}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_year"/></th> 
	    <td><mh:out value="${item.lec_year}"/></td>
	    <th><mf:label name="lecnumb"/></th> 
	    <td><mh:out value="${item.lecnumb}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lecname"/></th> 
	    <td colspan="3"><mh:out value="${item.lecname}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lecstat"/></th> 
	    <td><mh:out value="${item.lecstat}" codeGroup="LECSTAT"/></td>
	    <th><mf:label name="ltype"/></th> 
	    <td><mh:out value="${item.ltype}" codeGroup="LTYPE"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_sdate"/></th> 
	    <td><mh:out value="${item.lec_sdate}" format="fulldate" td="true"/></td>
	    <th><mf:label name="lec_edate"/></th> 
	    <td><mh:out value="${item.lec_edate}" format="fulldate" td="true"/></td>
 	</tr>
</table>
</div>
<br>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	<thead>
	<tr>
		<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_userid')"/></th>
		<th><mf:header name="lecnumb"/></th>
		<th><mf:header name="userid"/></th>
		<th><mf:header name="nm"/></th>
		<th><mf:header name="region"/></th>
		<th><mf:header name="dist"/></th>
		<th><mf:header name="deal"/></th>
		<th><mf:header name="tot_score"/></th>
		<th><mf:header name="is_grad"/></th>
		<th><mf:header name="req_stat"/></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="list" items="${list}" varStatus="status">
	<tr>
		<td class="center"><mf:input type="checkbox" name="v_userid" value="${list.userid}##${list.lecnumb}"/></td>
		<td class="center"><mh:out value="${list.lecnumb}" td="true"/></td>
		<td class="center"><mh:out value="${list.userid}" td="true"/></td>
		<td class="center"><mh:out value="${list.nm}" td="true"/></td>
		<td class="center"><mh:out value="${list.region_name}" td="true"/></td>
		<td class="center"><mh:out value="${list.dist_name}" td="true"/></td>
		<td class="center"><mh:out value="${list.deal_name}" td="true"/></td>
		<td class="center"><mh:out value="${list.tot_score}" td="true"/></td>
		<td class="center"><mh:out value="${list.is_grad}" codeGroup="IS_GRADE" td="true"/></td>
		<td class="center"><mh:out value="${list.req_stat}" codeGroup="REQ_ST"  td="true"/></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<table border="0" cellpadding="2" cellspacing="0" width="100%">	
	<tr>
		<td align="right">
        <mf:button bundle="button" key="button.lecture.approve" onclick="doApprove()"/>
		<mf:button bundle="button" key="button.lecture.complete" onclick="doComplete()"/>
		<mf:button bundle="button" key="button.lec.request" onclick="doReq()"/>
		<mf:button bundle="button" key="button.req.cancel.ed" onclick="doCancel()"/>
    	<mf:button bundle="button" key="exceldown" onclick="doExcel()"/>
        <mf:button bundle="button" key="button.excel.up" onclick="doUpload()"/>
		<mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
	</tr>
</table>
</mf:form>
