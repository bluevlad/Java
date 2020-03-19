<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
&lt;%@ page contentType="text/html; charset=utf-8"%>     
&lt;%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>      	
&lt;script language="javascript" src='&lt;c:url value="/js/lib.validate.js"/>'>&lt;/script>
&lt;script>
    function doEdit() {
        var frm = getObject("myform");
        <c:forEach var="item" items="${pkColumns}" varStatus="status"><mh:set var="columnname" value="${item.name}" case="lower"/>
			<c:out value="frm.${columnname}.value = '"/>&lt;c:out value="${item.<c:out value="${columnname}"/>}"/&gt;';
		</c:forEach> 		
        frm.submit();
    }
    function goList() {
	        &lt;c:url var="urlList" value="${control_action}">
	            &lt;c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	            &lt;c:param name="cmd" value="list"/>
	        &lt;/c:url>
	        document.location = '&lt;mh:out value="${urlList}"/>';
	    }
&lt;/script>

&lt;mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doEdit(); ">
	&lt;input type="hidden" name="cmd" value="edit">
	<c:forEach var="item" items="${selColumns}" varStatus="status"><c:if test="${!empty item.pk }">
	&lt;input type='hidden' name='<mh:out value="${item.name}" case="lower"/>' value=""></c:if></c:forEach>  
&lt;/mf:form>
<div class="viewContainer">
&lt;table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="25%"/>
	<col width="75%"/>
	<c:forEach var="item" items="${selColumns}" varStatus="status"><mh:set var="columnname" value="${item.name}" case="lower"/>
	&lt;tr>
	    &lt;th nowrap>&lt;mf:header name="<c:out value="${columnname}"/>"/></th> 
	    &lt;td >&lt;mh:out value="${item.<c:out value="${columnname}"/>}" /&gt;</td>
 	&lt;/tr>
	</c:forEach>

&lt;/table>

&lt;table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">	
	&lt;tr>
		&lt;td colspan="2" align="center">&lt;mf:button bundle="button" key="edit" onclick="doEdit();"/&gt; &lt;mf:button bundle = "button"  key="goList" onclick="goList();"/&gt;</td>
	&lt;/tr>
&lt;/table>
</div>