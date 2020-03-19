<%@ page contentType = "text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<html>
	<META HTTP-EQUIV='Content-type' CONTENT='text/html; charset=utf-8'>
	<head><title><fmt:message key="page.title"/></title>
	<link href='<c:url value="/css/dbadmin.css"/>' rel='stylesheet' type='text/css'></link>
	<style type="text/css">
	.TOP_LINK {
		font-size: 10px;
		font-family: Fixedsys;
		color: #ffffff;
	}
</style>
<script language='javascript' src='<c:url value="/js/sub.common.js"/>' ></script>
<script language='javascript' >
	function frmSubmit(cmd) {
		var frm = getObject("frmColumn");
		
		if(frm) {
			frm.cmd.value = cmd;
			frm.submit();
		} else {
			alert(" frmColumn not exists");
		}
		
	}
</script>
</head>
<body  >

<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <table>
                <tr>
					<td><a href="javascript:frmSubmit('sql')">Columns(SQL)</a></td>
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('beans')">Java Bean</a></td>
	                <td>&nbsp;|&nbsp;</td>
	                <td><a href="javascript:frmSubmit('ExcelUpload')">Excel Upload</a></td>
                </tr>
            </table>
        </td>
	</tr>
	<tr>
        <td>
            <table>
				<tr>
					<td><a href="javascript:frmSubmit('JavaActionConfig');">Action Config(XML)</a></td>
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JavaResourceConfig');">Resource Config(XML)</a></td>
	                <td>&nbsp;|&nbsp;</td>
	                <td><a href="javascript:frmSubmit('JavaDB');">Java(DB)</a></td>
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JavaAction');">Java(Action)</a></td>
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JspList');">JSP(List)</a></td>
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JspView');">JSP(View)</a></td>                
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JspEdit');">JSP(Edit)</a></td>                
					<td>&nbsp;|&nbsp;</td>
					<td><a href="javascript:frmSubmit('JspAdd');">JSP(Add)</a></td>
                </tr>
            </table>
		</td>
	</tr>
	<tr>
		<td><c:import url="column_info.jsp" /></td>
	</tr>
	<tr>
		<c:url var="url" value="${controlaction}">
			<c:param name="type" value="down"/>
		</c:url>
		<td>
			<br>
			<c:if test="${source=='t'}"><textarea cols="90" rows="20" wrap="soft"></c:if>
			<c:catch var="e"><c:import url="${MAF_INFO.file}"/></c:catch>
			<c:if test="${!empty e }"><c:out value="${e}"/></c:if>
			<c:if test="${source=='t'}"></textarea></c:if>
		</td>
	</tr>
</table>
</body>
</html>