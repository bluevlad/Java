<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=<fmt:message key='page.charset'/>" />
	<title>회원가입</title>
	<link href="${CPATH}/css/common.css"	rel="stylesheet" type="text/css"></link>
	<script language="javascript"	src="${CPATH}/js/sub.common.js"></script>
</head>
<body background="${CPATH}/images/member/bg.gif" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x;">
<table width="880" border="0" cellspacing="0" cellpadding="0" align="center">
	<col width="10">
	<col width="860">
	<col width="10">
	<tr>
		<td height="60" colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><a href="/smis/"><img src="${CPATH}/images/member/top_logo.gif" alt=""  border="0"></a></td>
				<td align="right" valign="top"><a href="${CPATH}"><img src="${CPATH}/images/member/go_home.gif" alt="" border="0"></a></td>
			</tr></table></td>
	</tr>
	<tr>
		<td colspan="3"><img src="${CPATH}/images/member/border_top.gif" alt="" ></td>
	</tr>
	<tr>
		<td background="${CPATH}/images/member/border_left" valign="top" style="background-repeat: repeat-y;"><img src="${CPATH}/images/pixel/transparency.gif" alt="" width="10" height="19" border="0"></td>
		<td valign="top" bgcolor="#ffffff" ><table border="0" cellspacing="0" cellpadding="0">
				<col width="170">
				<col width="10">
				<col width="680">
				<tr>
					<td valign="top"><img src="${CPATH}/images/member/lm_register.gif" alt="" border="0"></td>
					<td valign="top"><img src="${CPATH}/images/pixel/transparency.gif" alt="" width="10" height="19" border="0"></td>
					<td align="center"><img src="${CPATH}/images/member/t2_${step}.gif" alt="" width="680" height="50" border="0"></td>
				</tr>
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				<tr>
					<td valign="top" background="${CPATH}/images/member/bg_left.gif" style="background-repeat: no-repeat;"><img src="${CPATH}/images/member/lm2_register.gif" alt="" width="170" height="108" border="0"></td>
					<td valign="top"></td>
					<td align="center"><jsp:include page="${MAF_INFO.file}" flush="true"/></td>
				</tr>
			</table>
		<td background="${CPATH}/images/member/border_right.gif" valign="top" style="background-repeat: repeat-y;"><img src="${CPATH}/images/pixel/transparency.gif" alt="" width="10" height="19" border="0"></td>
	</tr>
	<tr>
		<td colspan="3"><img src="${CPATH}/images/member/border_bottom.gif" alt="" ></td>
	</tr>
</table>
<jsp:include  page="/layout/common/st02_bottom.jsp" flush="true"/>
</body>
</html>
