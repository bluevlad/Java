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
<mf:input type="hidden" name="prd_cd" value="${item.prd_cd}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
        <th><mf:label name="prd_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="prd_nm" cssStyle="width:98%" required="true" value="${item.prd_nm}"/></td>
 	</tr>
    <tr>
        <th><mf:label name="org_price"/></th> 
        <td><mf:input type="text" name="org_price" cssStyle="width:100px" value="${item.org_price}"/></td>
        <th><mf:label name="prd_type" /></th> 
        <td><mf:select name="prd_type" codeGroup="PRD.PRD_TYPE" curValue="${item.prd_type}" /></td>
    </tr>
    <tr>
        <th><mf:label name="prd_desc" /></th>
        <td colspan="3"><mf:textarea name="surveydesc" cssStyle="width:98%" rows="20" value="${item.prd_desc}"/></td>
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