<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript">
    function doUpdate() {
        var frm = getObject("myform");
        if(frm != null && validate(frm)) {
            frm.cmd.value="insert";
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
	    <th><mf:label name="field"/></th> 
	    <td><mf:select name="field" codeGroup="FIELD" /></td>
	    <th><mf:label name="section"/></th> 
	    <td><mf:select name="section"  codeGroup="SECTION"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="cert_lvl"/></th> 
	    <td><mf:select name="cert_lvl" codeGroup="CERT_LVL"><option value=""/></mf:select></td>
	    <th><mf:label name="isuse"/></th> 
	    <td><mf:select name="isuse" codeGroup="ISUSE" /></td>
 	</tr>

	<tr>

	    <th><mf:label name="ctype"/></th> 
	    <td><mf:select name="ctype" codeGroup="CTYPE"/></td>

	    <th><mf:label name="is_autocerti"/></th> 
	    <td><mf:select name="is_autocerti" codeGroup="YESORNO"/></td>
 	</tr>
    <tr>
        <th><mf:label name="crs_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="crs_nm" size="120" value="" /></td>
    </tr>    
    <tr>
        <th><mf:label name="certi_id"/></th> 
        <td colspan="3">
        <mf:select name="certi_id">
            <option value="">--</option>
            <c:forEach var="i" items="${CertiList}">
                <mf:option value="${i.certi_id}" text="${i.certi_name }" curValue="${item.certi_id}"/>
                
            </c:forEach>
        </mf:select>
        </td>
    </tr>

	<tr>	    
        <th><mf:label name="vc_type"/></th> 
        <td><mf:select  name="vc_type" curValue="${item.vc_type}" codeGroup="VC_TYPE">
            <option value="">-</option>
        </mf:select></td>
	    <th><mf:label name="passcondition"/></th> 
	    <td colspan="3"><mf:input type="text" name="passcondition" value="" option="number"/></td>
        
 	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td align="right">
		<mf:button bundle="button" key="save" onclick="javascript:doUpdate()"/>
		<mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
	</tr>
</table>
</mf:form>
</div>

