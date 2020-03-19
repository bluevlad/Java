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
			frm.code.value = code; 
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
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<tr>
					<td><mf:label name="code"/></td>
					<td><mf:input name="code" type="text" value="${LISTOP.ht.code}" /></td>					
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
		      	<th><mf:header name="code" sort="true"/> </th>			
		      	<th><mf:header name="allnames" sort="true"/> </th>			
		      	<th><mf:header name="active_yn"/> </th>			
		      	<th><mf:header name="local_name"/> </th>
		      	<th><mf:header name="reg_nm"/> </th>
		    </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
			<tr   >
				<td class='center' ><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
				<td class='center' ><a href='javascript:doView("<c:out value="${item.code}"/>");'><mh:out value="${item.code}" td="true"/></a></td>	
				<td ><mh:out value="${item.allnames}" td="true"/></td>	
				<td class='center' ><mh:out value="${item.active_yn}" td="true"/></td>	
				<td ><mh:out value="${item.local_name}" td="true"/></td>
				<td ><mh:out value="${item.reg_nm}" td="true"/></td>
			</tr>
	    </c:forEach>
	    </tbody>
	    </table></div>

	    </td>
    </tr>
	<tr>
    	<td align="center">
	    	<mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/><br>
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>


		
		
		