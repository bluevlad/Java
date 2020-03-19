<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<HTML>
<head>
<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=<fmt:message key='page.charset'/>" />
<title><fmt:message key='page.title'/></title>
<link href="${CPATH}/css/common.css" rel="stylesheet" type="text/css"></link>
<script language="javascript"
	src="${CPATH}/js/sub.common.js"></script>
<script>
		function init() {
			var frm = getObject("frmLogin");
			if(frm) {
				var l = frm.userid;
				if (l) {
					l.focus();
					l.select();
				}
			}
		}
	</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" onLoad="init()">
<table width="740" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#FF0000">

	<tr>
		<td><tiles:insert page="${MAF_INFO.file}" flush="true"/></td>
	</tr>

</table>
</body>
</html>
