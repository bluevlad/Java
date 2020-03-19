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
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="crs_cd"/></th> 
	    <td><mf:input type="text" name="crs_cd" required="true" value="${item.crs_cd}" cssStyle="width:95%"/></td>
        <th><mfmt:message bundle="common" key="active_yn" /></th> 
        <td><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}" /></td>
 	</tr>
    <tr>
        <th><mf:label name="crs_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="crs_nm" value="${item.crs_nm}" cssStyle="width:98%"/></td>
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