<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doView(crs_cd){
	var frm = getObject("myform");
	if(frm) {
		frm.cmd.value = "edit";
		frm.crs_cd.value = crs_cd; 
		frm.submit();
	}
}

function doWrite(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "add";
        frm.submit();
    }
}
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch();return false;">
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="crs_cd" value=""/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td>
            <div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th>#</th>
		            <th><mf:header name="crs_cd" sort="true"/></th>
		            <th><mf:header name="crs_nm" sort="true"/></th>
		            <th><mfmt:message bundle="common" key="active_yn" /></th>
		            
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mh:out value="${status.count}"/></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.crs_cd}"/>");'><mh:out value="${item.crs_cd}" td="true"/></a></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.crs_cd}"/>");'><mh:out value="${item.crs_nm}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
			    </tr>
			</table>        
		    </div>
	    </td>
    </tr>
</table>		
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>