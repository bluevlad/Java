<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<html>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=<fmt:message key='page.charset'/>">
	<head><title><fmt:message key="page.title"/></title>
	<link href="${CPATH}/css/dbadmin.css" rel="stylesheet" type="text/css"></link>
	<style type="text/css">
	.TOP_LINK {
		font-size: 10px;
		font-family: Fixedsys;
		color: #ffffff;
	}
</style>
<script language="javascript" src="${CPATH}/js/sub.common.js"></script>
<script language="javascript" >
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
<form action="${controlaction}" method="post" name="frmColumn" id="frmColumn">
<input type="hidden" name="cmd" value="${param.cmd}">
<pre>
* 작성 방법 : field Name | 한글명 | Option (L : List, S : 검색조건 , P : Primary Key)
ex)
name|이름|LS,userid|사용자ID|LSP,address|주소
</pre>
<table>
	<tr>
		<td>Package name : <input type="text" name="packageName" value="${packageName}"/></td>
		<td>class name : <input type="text" name="className" value="${className}"/></td>
	</tr>
	<tr>
		<td colspan="4"><textarea cols="80" rows="15" name="colinfo">${param.colinfo}</textarea></td>
	</tr>
</table>
</form>


<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><table>
			<tr>
				<td><a href="javascript:frmSubmit('sql')">Columns(SQL)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('index')">Indexes</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('sql')">Scripts</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('beans')">Java Bean</a></td>
</tr>

		</table></td>
	</tr>
	<tr>
		<td><table>
			<tr>
				<td><a href="javascript:frmSubmit('JavaAction');">Java(Action)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JavaDB');">Java(DB)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JavaActionConfig');">Action Config(XML)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JavaResourceConfig');">Resource Config(XML)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JspList');">JSP(List)</a></td>
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JspView');">JSP(View)</a></td>                
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JspEdit');">JSP(Edit)</a></td>                
				<td>&nbsp;|&nbsp;</td>
				<td><a href="javascript:frmSubmit('JspAdd');">JSP(Add)</a></td>
	     </tr>
		</table></td>
	</tr>
	<tr>
		<td><tiles:insert page="column_info.jsp" flush="true"/></td>
	</tr>
	<tr>
		<td>tpl : ${MAF_INFO.file} <a href="${controlaction}?type=down&${pageContext.request.queryString}">[저장]</a> <br>
		<c:if test="${source=='t'}"><textarea cols="120" rows="20" wrap="soft"></c:if>
		<tiles:insert page="${MAF_INFO.file}" flush="true"/>
		<c:if test="${source=='t'}"></textarea></c:if>
		</td>
	</tr>
</table>
${selColumns}
</body>
</html>
