<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
&lt;%@ page contentType="text/html; charset=utf-8"%>     
&lt;%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %&gt;    

&lt;script language="javascript" src="\${CPATH}/js/lib.validate.js">&lt;/script>
&lt;script language="javascript" >
		function frmSubmit(frm) {
			if (validate(frm)) {

				frm.cmd.value="insert";
				return true;
			} else {
				return false;
			}
		}
		function goList() {
	        &lt;c:url var="urlList" value="${'${'}control_action}">
            &lt;c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            &lt;c:param name="cmd" value="default"/>
	        &lt;/c:url>
	        document.location = "${'${'}urlList}";
	    }

&lt;/script>

&lt;div class="viewContainer">
&lt;mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    &lt;mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
    &lt;mf:input type="hidden" name="cmd" value=""/>
    <c:forEach var="item" items="${columns}" varStatus="status"><c:if test="${item.pk > 0}">
    &lt;mf:input type="hidden" name="<mh:out value="${item.name}" case="lower"/>" value="<mh:out value="${item.name}" case="lower"/>"/></c:if></c:forEach>
&lt;table width="100%" border="0" cellpadding="2" cellspacing="0" class="view"> 
    <col width="25%"/>
    <col width="75%"/>
    <c:forEach var="item" items="${columns}" varStatus="status"><mh:set var="columnname" value="${item.name}" case="lower"/>
    &lt;tr>
        &lt;th>&lt;mf:label name="<c:out value="${columnname}"/>"/></th> 
        <td >&lt;mf:input type="text" name="<c:out value="${columnname}"/>" size="20" maxlength="50" value="" /></td>
    &lt;/tr>
    </c:forEach>
&lt;/table>
&lt;table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">      
    &lt;tr>
        &lt;td colspan="2" align="center">
        &lt;mf:button bundle="button" key="save" onclick="frmSubmit('myform')"/&gt;     
        &lt;mf:button bundle = "button"  key="goList" onclick="goList()"/&gt;</td>
    &lt;/tr>
&lt;/table>
&lt;/mf:form>
&lt;/div>