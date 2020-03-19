<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "add";
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
	function doView(crs_cd){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "edit";
			frm.crs_cd.value = crs_cd; 
			frm.submit();
		}
	}
    function doSearch() {
		var frm = getObject("myform");
		if(frm) {
   			frm.cmd.value = "list";
			frm.miv_page.value = 1;
			frm.submit();
		}     
    }
	function doExcel(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "excel";
		    frm.submit();
		}
	}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
	        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch();return false;">
	        <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
			<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="crs_cd" value=""/> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
					<th><mf:label name="section"/></th>
					<td>
						<mf:select name="s_section" curValue="${LISTOP.ht.s_section}" codeGroup="SECTION" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="ctype"/></th>
					<td>
						<mf:select name="s_ctype" curValue="${LISTOP.ht.s_ctype}" codeGroup="CTYPE" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="field"/></th>
					<td>
						<mf:select name="s_field" curValue="${LISTOP.ht.s_field}" codeGroup="FIELD" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="vc_type"/></th>
					<td>
						<mf:select name="s_vc_type" curValue="${LISTOP.ht.s_vc_type}" codeGroup="VC_TYPE" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="crs_nm"/></th>
					<td colspan="3"><mf:input name="s_crs_name" value="${LISTOP.ht.s_crs_name}" cssStyle="width:300px"/></td>					
				</tr>
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td aligh="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
	        </div>    
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_crs_cd')"/></th>
			    	<th>#</th>
                    <th><mf:header name="field" sort="true"/></th>
		            <th><mf:header name="section" sort="true"/></th>
                    <th><mf:header name="cert_lvl" sort="true"/></th>
		            <th><mf:header name="ctype" sort="true"/></th>
		            <th><mf:header name="crs_cd" sort="true"/></th>
		            <th><mf:header name="crs_nm" sort="true"/></th>
		            <th><mf:header name="isuse" sort="true"/></th>
		            
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="checkbox" name="v_crs_cd" value="${item.crs_cd}"/></td>
					<td class="center"><mh:out value="${status.count}"/></td>
                    <td class="center"><mh:out value="${item.field}" codeGroup="FIELD" td="true"/></td>
					<td class="center"><mh:out value="${item.section}" codeGroup="SECTION" td="true"/></td>
                    <td class="center"><mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL" td="true"/></td>
					<td class="center"><mh:out value="${item.ctype}" codeGroup="CTYPE"/></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.crs_cd}"/>");'><mh:out value="${item.crs_cd}" td="true"/></a></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.crs_cd}"/>");'><mh:out value="${item.crs_nm}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.isuse}" codeGroup="ISUSE"/></td>
					
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
		    </mf:form>
		    </div>
	    </td>
    </tr>
</table>		
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td align="right">
	    	<mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
	    	<mf:button bundle="button" key="delete" onclick="doDel()"/>
	    	<mf:button bundle="button" key="exceldown" onclick="doExcel()"/><br/>
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>		