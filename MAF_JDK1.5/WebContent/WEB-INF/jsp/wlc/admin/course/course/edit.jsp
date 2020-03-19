<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
    function doUpdate() {
        var frm = getObject("myform");
        if(frm != null && validate(frm)) {
            frm.cmd.value="update";
            frm.submit();
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
<mf:input type="hidden" name="crs_cd" value="${item.crs_cd}"/>

<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="crs_cd"/></th> 
	    <td colspan="3"><mh:out value="${item.crs_cd}"/></td>
 	</tr>
	<tr>
        <th><mf:label name="field"/></th> 
        <td><mf:select name="field" codeGroup="FIELD" curValue="${item.field}" /></td>

	    <th><mf:label name="section"/></th> 
	    <td><mf:select name="section" codeGroup="SECTION" curValue="${item.section}"/></td>
 	</tr>
	<tr>
        <th><mf:label name="cert_lvl"/></th> 
        <td><mf:select name="cert_lvl" codeGroup="CERT_LVL" curValue="${item.cert_lvl}" ><option value=""/></mf:select></td>
	    <th><mf:label name="isuse"/></th> 
	    <td><mf:select name="isuse" codeGroup="ISUSE" curValue="${item.isuse}" /></td>
 	</tr>

	<tr>
	    <th><mf:label name="ctype"/></th> 
	    <td><mf:select name="ctype" codeGroup="CTYPE" curValue="${item.ctype}" /></td>
	    <th><mf:label name="is_autocerti"/></th> 
	    <td><mf:select name="is_autocerti" codeGroup="YESORNO" curValue="${item.is_autocerti}" /></td>
 	</tr>
    <tr>
        <th><mf:label name="crs_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="crs_nm" value="${item.crs_nm}" size="120"/></td>
    </tr>
     <tr>
        <th><mf:label name="certi_id"/></th> 
        <td colspan="3">
        <mf:select name="certi_id">
            <option value="">-</option>
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
        <td><mf:input type="text" name="passcondition" value="${item.passcondition}" option="number"/></td>
 	</tr>
   
    <tr>
        <th><mf:label name="precondition"/></th> 
        <td colspan="3"><table cellpadding="2" cellspacing="0" border="0" width="100%">
                <col width="*"/>
                <col width="60"/>
                <col width="50"/>
                <thead>
                <tr>
                    <th><mf:label name="precondition"/></th>
                    <th><mf:label name="isuse"/></th>
                    <th>Check</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sitem" items="${preq}" varStatus="count">
                    
                    <tr>
                        <td><mh:out value="${sitem.crs_nm}" td="true"/></td>
                        <td align="center"><mh:out codeGroup="ISUSE" value="${sitem.isuse}" /></td>
                        <td align="Center"><mf:input type="checkbox" value="${sitem.crs_cd}" name="p_crs_cds" curValue="${sitem.p_crs_cd}" /></td>
                    </tr>
            </c:forEach>
                </tbody>
            </table></td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td colspan="4" align="right">
		<mf:button bundle="button" key="save" onclick="doUpdate()"/>
		<mf:button bundle="button" key="delete" onclick="doDel()"/>
		<mf:button bundle="button" key="goList" onclick="goList()"/></td>
	</tr>
</table>
</mf:form>
</div>

