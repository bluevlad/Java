<%@ page language="java" contentType="text/html; charset=utf-8" %>
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
	
	function doView(code){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.org_cd.value = code; 
			frm.submit();
		}
	}
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "list";
			frm.miv_page.value = 1;
			return true;
		}     
    }
    Event.observe(window, 'load', function(){OrgSetter.sync('<c:out value="${LISTOP.ht.s_org_cd}"/>')});
    
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
        	<h1><mfmt:message bundle="common" key="searchtitle"/></h1>
        	<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
        	<input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
			<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="org_cd" value=""/>
            <mf:input type="hidden" name="s_org_cd" value=""/>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<tr>
					<td><mh:import url="/WEB-INF/layout/common/org_selector.jsp">
                        <mh:param name="target_code" value="s_org_cd"/> 
                        <mh:param name="target_name" value="s_org_name"/> 
                    </mh:import></td>		
					<td><mf:label name="org_type"/></td>
					<td><mf:select name="org_type"  curValue="${LISTOP.ht.org_type}" codeGroup="ORG_TYPE"><option value="">-</option></mf:select></td>				
				</tr>
                <tr>
                    <td colspan="10"></td>
                </tr>
	        </table>
	        
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
	        	<tr>
	        		<td  >
	                <mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" />

	                </td>
	        	</tr>
	        </table>
	        </mf:form> 
	        </div>    
        </td>
    </tr>

	<tr>
	    <td><div class="listContainer">
		<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " 
			enableAlternateRows="true" rowAlternateClass="alternateRow">
		<thead>
		    <tr>
		    	<th># </th>
		      	<th><mf:header name="org_cd" sort="true"/> </th>			
		      	<th><mf:header name="org_type" sort="true"/> </th>			
		      	<th><mf:header name="region" sort="true"/> </th>			
		      	<th><mf:header name="org_name" sort="true"/> </th>
		      	<th><mf:header name="nation" sort="true"/> </th>
		      	<th><mf:header name="active_flag"/> </th>
		    </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
			<tr   >
				<td class='center' ><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
				<td class='center' ><a href='javascript:doView("<c:out value="${item.org_cd}"/>");'><mh:out value="${item.org_cd}" td="true"/></a></td>	
				<td ><mh:out value="${item.org_type}" td="true" codeGroup="ORG_TYPE"/></td>	
				<td class='center' ><mh:out value="${item.region}" td="true" codeGroup="REGION"/></td>	
				<td ><a href='javascript:doView("<c:out value="${item.org_cd}"/>");'><mh:out value="${item.org_name}" td="true"/></a></td>
				<td ><mh:out value="${item.nation}" td="true" codeGroup="NATION_CODE"/></td>
				<td align="center"><mh:out value="${item.active_flag}" td="true"  codeGroup="ACTIVE_YN"/></td>
			</tr>
	    </c:forEach>
	    </tbody>
	    </table></div>

	    </td>
    </tr>
	<tr>
    	<td align="center"><jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>


		
		
		


