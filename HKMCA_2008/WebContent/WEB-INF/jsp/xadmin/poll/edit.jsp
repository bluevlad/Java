<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
function doUpdate() {
    var frm = getObject("myform");
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "update";
                </c:when>
                <c:when test="${param.cmd == 'add'}">
                    frm.cmd.value = "insert";
                </c:when>
                <c:otherwise>
                    frm.cmd.value = "insert";
                </c:otherwise>
            </c:choose>
            frm.submit();
        }
    } else {
        alert ("form[" + frmName + "] is invalid");
    }
}

function doItem() {
    var frm = getObject("myform");
    frm.cmd.value = "addItem";
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
<mf:input type="hidden" name="cmd" value="update"/>
<mf:input type="hidden" name="poll_id" value="${item.poll_id}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
    <col width="15%">
    <col width="35%">
    <col width="15%">
    <col width="35%">
	<tr>
	    <th><mf:label name="start_dt"/></th> 
	    <td><mf:input type="date" name="start_dt" readonly="true" size="12" value="${item.start_dt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
	    <th><mf:label name="end_dt"/></th> 
	    <td><mf:input type="date" name="end_dt" readonly="true" size="12" value="${item.end_dt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
 	</tr>
    <tr>
        <th><mf:label name="title"/></th> 
        <td colspan="3"><mf:input name="title" size="120" maxlength="100" value="${item.title}"/></td>
    </tr>
	<tr>
	    <th><mf:label name="is_use"/></th> 
	    <td><mf:select name="is_use" curValue="${item.is_use}" codeGroup="ISUSE"/></td>
	    <th><mf:label name="is_show"/></th> 
	    <td><mf:select name="is_show" curValue="${item.is_show}" codeGroup="ISUSE"/></td>
 	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save" />
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
	<col width="50"/>
	<col width="50"/>
	<col width="*"/>
	<col width="100"/>
	<col width="100"/>
    <col width="100"/>
    <tr>
		<th>#</th>	
      	<th><mf:header name="seq"/></th>	
      	<th><mf:header name="question"/></th>	
		<th><mf:header name="hit"/></th>				
        <th>#</th>                
		<th><mfmt:message bundle="button" key="delete" /></th>		
    </tr>
	<c:set var="maxid" value="0"/>
    <c:forEach var="pitem" items="${pollitems}" varStatus="status">
        <mf:input type="hidden" name="item_ids" value="${pitem.item_id}"/>
		<mf:input type="hidden" name="item_nids" value="${pitem.item_id}"/>
		<c:set var="id" value="${pitem.item_id}"/>
		<c:if test="${empty pitem.item_id}"></c:if>
	<tr>
		<td align="center"><mh:out value="${status.count}"/></td>
		<td align="center"><mf:input name="seq_${id}" type="text" size="4" value="${pitem.seq}" option="number"/></td>	
		<td><mf:input name="question_${id}" type="text" size="100" value="${pitem.question}"/></td>	
        <td align="center"><mh:out value="${pitem.hit}" td="true"/></td>
		<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${status.count}"/>.gif" width="<c:out value="${(pitem.hit/item.sum)*50}"/>" height="25"/></td>
		<td align="center"><mf:input type="radio" name="del_${id}" value="Y"/></td>	
	</tr>
    </c:forEach>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
	<col width="50"/>
	<col width="*"/>
	<col width="100"/>
    <tr>
      	<th><mf:header name="seq"/></th>	
      	<th><mf:header name="question"/></th>	
    </tr>
	<tr>
		<td align="center"><mf:input type="text" name="seq" size="4" value="0" option="number"/></td>	
		<td><input name="question" type="text" size="50" value=""></td>	
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="add" onclick="doItem()" icon="icon_add" />
        </td>
    </tr>
</table>
</mf:form>
</div>