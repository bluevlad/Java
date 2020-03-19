<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
	function frmSubmit() {
		var frm = getObject("myform");	
		if (validate(frm)) {
			<c:choose>
				<c:when test="${param.cmd == 'edit'}">
					frm.cmd.value = "update";
				</c:when>
				<c:when test="${param.cmd == 'add'}">
					frm.cmd.value = "ginsert";
				</c:when>
			</c:choose>
			frm.submit();
		
		}
	}
	function doDelete() {
		var frm = getObject("myform");
        if(confirm('<mfmt:message bundle="common.scripts" key="alert.info2"/>')){
			frm.cmd.value = "delete";
    		frm.submit();
        }
	}
    function frmAdd() {
        var frm = getObject("myform");  
        frm.cmd.value = "insert";
        frm.submit();
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
//-->		
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="30%"/>
    <tr>
        <th><mfmt:message bundle="common" key="group_cd" /></th>
        <td><mf:input type="text" name="group_cd" cssStyle="width:95%" value="${item.group_cd}" required="true"/></td>
        <th><mfmt:message bundle="common" key="group_nm" /></th>
        <td><mf:input type="text" name="group_name" cssStyle="width:95%" value="${item.group_name}" required="true"/></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="1" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save"/>
            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete"/>
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
<br>
<table border="0" cellpadding="2" cellspacing="1" class="list"width="100%">
	<col width="10%"/>
	<col width="20%"/>
    <col width="*"/>
    <col width="10%"/>
    <thead>
    <tr>
        <th>#</th>  
        <th><mfmt:message bundle="common" key="code" /></th>
        <th><mfmt:message bundle="common" key="code_nm" /></th>
        <th><mfmt:message bundle="common" key="no"/></th>    
        <th><mfmt:message bundle="button" key="delete" /></th>      
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td align="center"><mh:out value="${status.count}"/></td>
        <td align="center"><mf:input name="code_no" cssStyle="width:90%" value="${list.code_no}"/></td>
        <td align="center"><mf:input name="name_${list.code_no}" cssStyle="width:90%" value="${list.code_name}"/></td>
        <td align="center"><mf:input name="seq_${list.code_no}" cssStyle="width:90%" value="${list.seq}"/></td>
        <td align="center"><mf:input type="radio" name="del_${list.code_no}" value="Y"/></td> 
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="list">
    <col width="20%"/>
    <col width="*"/>
    <col width="10%"/>
    <col width="10%"/>
    <thead>
    <tr>
        <th><mfmt:message bundle="common" key="code" /></th>
        <th><mfmt:message bundle="common" key="code_nm" /></th>
        <th><mfmt:message bundle="common" key="no"/></th>    
        <th>#</th>  
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="center"><mf:input name="new_code_no" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="code_name" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="seq" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:button bundle="button" key="add" onclick="frmAdd()" icon="icon_save"/></td>
    </tr>
    </tbody>
</table>
</mf:form>