<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
&lt;%@ page language="java" contentType="text/html; charset=utf-8" %>
&lt;%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "add";
		    frm.submit();
		}
	}
	
	function doView(<c:forEach var="item" items="${pkColumns}" varStatus="status"><mh:out value="${item.name}" case="lower"/><c:if test="${!status.last}">, </c:if></c:forEach>){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			<c:forEach var="item" items="${pkColumns}" varStatus="status">
			frm.<mh:out value="${item.name}" case="lower"/>.value=<mh:out value="${item.name}" case="lower"/>;
			</c:forEach>
			
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
        	<h1>&lt;mfmt:message bundle="common" key="searchtitle"/></h1>
        	&lt;mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
			&lt;fm:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
			<input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
			<input type="hidden" name="miv_page" value="1"/>
			<input type="hidden" name="cmd" value="list"/>
		<c:forEach var="item" items="${pkColumns}" varStatus="status">
			<input type='hidden' name='<mh:out value="${item.name}" case="lower"/>' value=''></c:forEach> 
			
				<table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<c:forEach var="item" items="${searchColumnsList}" varStatus="status">			
	            <tr>
	                <th >&lt;mf:label name="<mh:out value="${item.name}" case="lower"/>" for="s_<mh:out value="${item.name}" case="lower"/>"/></th>
	                <td >&lt;mf:input type="text" name="s_<mh:out value="${item.name}" case="lower"/>" value="${LISTOP.ht.s_<mh:out value="${item.name}" case="lower"/>}"/></td>
	             </tr>
				</c:forEach>
	           </table>
	        
	        	<table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
	        		<tr><td>
	                &lt;mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search"/>
                    &lt;mf:button onclick="frmReset('myform');" bundle="button" key="reset" />
	                </td></tr>
		        </table>
	        &lt;/mf:form></div>   
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
		<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
		<thead>
	    <tr>
	    	<th># </th>
	      <c:forEach var="item" items="${listColumnsList}" varStatus="status"><mh:set var="columnname" value="${item.name}" case="lower"/>
			<th>&lt;mf:header name="<mh:out value="${columnname}" case="lower"/>" sort="true"/></th>
	      </c:forEach>				
	    </tr>
	    </thead>
	    <tbody>
	    &lt;c:forEach var="item" items="${navigator.list}" varStatus="status">
		<tr >
			<td class="center" >&lt;mh:listseq navigator="${navigator}" count="${status.count}"/></td>
			<c:forEach var="item" items="${listColumnsList}" varStatus="status">
				<mh:set var="columnname" value="${item.name}" case="lower"/>
				<c:if  test="${status.first}">
					<c:set var="td">&lt;a href="javaScript:doView(<c:forEach var="item2" items="${pkColumns}" varStatus="istatus">
						'&lt;c:out value="${item.<c:out value="${columnname}"/>}"/>'<c:if test="${!istatus.last}">, </c:if></c:forEach>)"/>
						&lt;mh:out value="${item.<c:out value="${columnname}"/>}" td="true" /></a></c:set></c:if>
				<c:if  test="${!status.first}">
					<c:set var="td">&lt;mh:out value="${item.<c:out value="${columnname}"/>}" td="true" /></c:set>
				</c:if>
				<td ><c:out value="${td}" escapeXml="false"/></td>
			</c:forEach>	
		</tr>
	    &lt;/c:forEach>
	    </tbody>
	    </table></div>

	    </td>
    </tr>
	<tr>
    	<td align="center">
	    	&lt;mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/><br>
	    	&lt;jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>
