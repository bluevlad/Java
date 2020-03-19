<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
    function doUpdate() {
        var frm = getObject("myform");
		if(frm) {
            if (validate(frm)) {
		        frm.cmd.value="insert";
		        frm.submit();
	        }
        }
    }
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}
            function exmSetChoose(frm_id, elm_id) {
        var urlname = CPATH + "/etest.admin/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id;
        var oWin = window.open(urlname,
            "setChooseWin",
            "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
        windows_focus(oWin);
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
	    <th><mf:label name="crs_nm"/></th> 
	    <td colspan="3">
	    	<select name="crs_cd" required hname='<mf:header name="crs_nm"/>'>
            <option value=""></option>
			<c:forEach var="item" items="${courselist}" varStatus="status">
		    	<mf:option text="${item.crs_cd}:${item.crs_nm}" value="${item.crs_cd}"/>
			</c:forEach>
            </select>
	    </td>
 	</tr>
	<tr>
	    <th><mf:label name="subject_nm"/></th> 
	    <td colspan="3"><mf:input type="text" name="subject_nm" size="120" value="" required="true" /></td>
 	</tr>

	<tr>
	    <th><mf:label name="isuse"/></th> 
	    <td><mf:select name="isuse" codeGroup="ISUSE" curValue="N"/></td>
	    <th><mf:label name="stype"/></th> 
	    <td><mf:select name="stype" codeGroup="STYPE" /></td>
 	</tr>
	
	<tr>
	    <th><mf:label name="sbj_type"/></th> 
	    <td><mf:select name="sbj_type" codeGroup="SBJ_TYPE" ><option value="">-</option></mf:select></td>
	   <th><mf:label name="year"/></th> 
        <td><mf:select name="year" codeGroup="CAL_YY" curValue="${year}"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="term"/></th> 
	    <td colspan="3"><mf:input type="text" name="term" value="" option="number" /><mfmt:message bundle="common" key="table.day"/></td>
         
 	</tr>
    <tr>
    <th><mf:label name="test_type"/></th> 
        <td colspan="3"><mf:input type="text" name="exm_setid" value=""/>
             <mf:button bundle="button" key="button.select" onclick="exmSetChoose('myform','exm_setid')"/> </td>
    </tr>
	<tr>
	    <th><mf:label name="outline"/></th> 
	    <td colspan="3"><mf:textarea name="outline" cols="130" rows="3" value=""/></td>
 	</tr>

	<tr>
	    <th><mf:label name="target"/></th> 
	    <td colspan="3"><mf:textarea name="target" cols="130" rows="3" value=""/></td>
 	</tr>
	<tr>
	    <th><mf:label name="precondition"/></th> 
	    <td colspan="3"><mf:textarea name="precondition" cols="130" rows="3" value=""/></td>
 	</tr>
	<tr>
	    <th><mf:label name="contents"/></th> 
	    <td colspan="3"><mf:textarea name="contents" cols="130" rows="3" value=""/></td>
 	</tr>
    <tr>
        <th><mf:label name="target"/></th> 
        <td colspan="3" valign="top"><table cellpadding="2" cellspacing="0" border="1" width="100%">
                <thead>
                    <tr>
                        <th><mfmt:message bundle="table.maf_user" key="sub_section"/></th>
                        <th><mfmt:message bundle="table.bas_subject_cd" key="mandatory"/></th>
                        <th><mfmt:message bundle="table.bas_subject_cd" key="optional"/></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="sitem" items="${subsection}" varStatus="count">
                    <tr>
                        <td><mf:input type="hidden" name="sub_sections" value="sub_section_${sitem.code_no}"/><mh:out value="${sitem.code_no}" codeGroup="SUB_SECTION"/></td>
                        <td align="Center"><mf:input type="checkbox" value="R" name='sub_section_${sitem.code_no}' curValue="${sitem.required_type}"  onclick="checkboxToggle(this)"/></td>
                        <td align="Center"><mf:input type="checkbox" value="O" name='sub_section_${sitem.code_no}' curValue="${sitem.required_type}" onclick="checkboxToggle(this)"/></td>
                    </tr>
            </c:forEach>
                </tbody>
            </table></td>
    </tr>    
	<tr>
	    <th><mf:label name="ad_new"/></th> 
	    <td><mf:select name="ad_new" codeGroup="YESORNO" curValue="N"/></td>
	    <th><mf:label name="ad_recommend"/></th> 
	    <td><mf:select  name="ad_recommend" codeGroup="YESORNO" curValue="N"/></td>
 	</tr>
     <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"> <jsp:include page="/WEB-INF/jsp/common/fileAttach/fileAttach.jsp" flush="true">
           <jsp:param name="FILEID" value="attachfile" />     
         </jsp:include> </td>
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