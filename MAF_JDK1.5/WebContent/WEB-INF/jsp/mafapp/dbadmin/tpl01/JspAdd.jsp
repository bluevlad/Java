<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<c:set var="ColumnCnt" value="${fn:length(columns)}"/>

&lt;%@ page contentType="text/html; charset=euc-kr"%>         	
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
	            &lt;c:param name="${'${'}mrt:mvcListOp()}" value="${'${'}LISTOP.serializeUrl}"/>
	            &lt;c:param name="${'${'}mrt:mvcCmd()}" value="list"/>
	        &lt;/c:url>
	        document.location = "${'${'}urlList}";
	    }

&lt;/script>
&lt;table border="0" cellpadding="2" cellspacing="0" class="view">	
&lt;form action="${'${'}control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
	&lt;input type="hidden" name="${'${'}mrt:mvcCmd()}" value="">
	<c:forEach var="item" items="${columns}" varStatus="status"><c:if test="${item.pk > 0}">
	&lt;input type="hidden" name="${fn:toLowerCase(item.name)}" value=""></c:if></c:forEach>  
	<c:forEach var="item" items="${columns}" varStatus="status"><c:set var="columnname" value="${fn:toLowerCase(item.name)}"/><c:set var="fnname" value="${fn:endsWith(fn:toLowerCase(item.name),'_dt')?'mfmt:shortDate':'mhtml:null2nbsp'}"/>
	&lt;tr>
	    <th class="view" nowrap>&lt;mform:label name="${columnname}"/></th> 
	    <td class="view">&lt;mform:input name="${columnname}" size="20" maxlength="50" value="${'${'}item.${columnname}}"/></td>
 	&lt;/tr>
	</c:forEach>
	&lt;tr>
		&lt;td colspan="2" align="center">&lt;mform:submit bundle="button" key="save"/&gt; &lt;a href="javascript:goList()"/&gt; &lt;mfmt:message bundle="button"  key="goList"/></a></td>
	&lt;/tr>
&lt;/form>
&lt;/table>
