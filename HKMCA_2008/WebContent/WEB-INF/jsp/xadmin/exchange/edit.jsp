<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
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
    function doDel(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "delete";
		    frm.submit();
		}
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
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="xch_cd" value="${item.xch_cd}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
        <th><mf:label name="xch_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="xch_nm" cssStyle="width:98%" required="true" value="${item.xch_nm}"/></td>
 	</tr>
    <tr>
        <th><mf:label name="xch_amount"/></th> 
        <td><mf:input type="text" name="xch_amount" cssStyle="width:100px" value="${item.xch_amount}"/></td>
        <th><mf:label name="xch_unit" /></th> 
        <td><mf:select name="xch_unit" codeGroup="XCH.UNIT" curValue="${item.xch_unit}" /></td>
    </tr>
    <tr>
        <th><mf:label name="xch_usual" /></th>
        <td colspan="3"><mf:input type="text" name="xch_usual" cssStyle="width:98%" required="true" value="${item.xch_usual}"/></td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
	<tr>
		<td align="right">
			<mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save"/>
			<mf:button bundle="button" key="delete" onclick="doDel()" icon="icon_delete"/>
			<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
		</td>
	</tr>
</table>
</mf:form>
</div>