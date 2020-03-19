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
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="lec_cd" value="${item.lec_cd}"/>
<mf:input type="hidden" name="req_stat" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="lec_cd"/></th> 
	    <td colspan="3"><mh:out value="${item.lec_cd}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_year"/></th> 
	    <td><mh:out value="${item.lec_year}"/></td>
	    <th><mf:label name="lec_num"/></th> 
	    <td><mh:out value="${item.lec_num}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_nm"/></th> 
	    <td colspan="3"><mh:out value="${item.lec_nm}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_stat"/></th> 
	    <td><mh:out value="${item.lec_stat}" codeGroup="LEC.LECSTAT"/></td>
	    <th><mf:label name="ltype"/></th> 
	    <td><mh:out value="${item.ltype}" codeGroup="LEC.LTYPE"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="sch_sdt"/></th> 
	    <td><mh:out value="${item.sch_sdt}" td="true"/></td>
	    <th><mf:label name="sch_edt"/></th> 
	    <td><mh:out value="${item.sch_edt}" td="true"/></td>
 	</tr>
</table>
</div>
<br>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	<thead>
	<tr>
		<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_userid')"/></th>
		<th><mf:header name="lec_num"/></th>
		<th><mf:header name="userid"/></th>
		<th><mf:header name="nm"/></th>
		<th><mf:header name="tot_score"/></th>
		<th><mf:header name="is_grad"/></th>
		<th><mf:header name="req_stat"/></th>
	</tr>
	</thead>
	<tbody>
    <c:forEach var="list" items="${navigator.list}" varStatus="status">
	<tr>
		<td class="center"><mf:input type="checkbox" name="v_usn" value="${list.usn}##${list.lec_num}"/></td>
		<td class="center"><mh:out value="${list.lec_num}" td="true"/></td>
		<td class="center"><mh:out value="${list.userid}" td="true"/></td>
		<td class="center"><mh:out value="${list.nm}" td="true"/></td>
		<td class="center"><mh:out value="${list.tot_score}" td="true"/></td>
		<td class="center"><mh:out value="${list.is_grad}" codeGroup="WLC.IS_GRADE" td="true"/></td>
		<td class="center"><mh:out value="${list.req_stat}" codeGroup="WLC.REQ_STAT"  td="true"/></td>
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
			<mf:button bundle="button" key="goList" onclick="javascript:goList()"/>
		</td>
	</tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>