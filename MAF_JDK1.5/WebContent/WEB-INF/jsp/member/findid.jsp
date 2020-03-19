<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=<fmt:message key='page.charset'/>">
<TITLE>SMIS 교육센터</TITLE>
<link href="/smis/css/common.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
    window.resizeTo("200","150");
</script>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function win_close(){
	    window.close();
	}
//-->
</SCRIPT>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/lib.validate.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/sub.common.js"></script>

<BODY style="background-repeat: repeat-x; background-image: url(${CPATH}/images/mall/bg.jpg);" bgcolor="CED2DB" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<!-- 상단부분 -->
<!-- 상단부분끝 -->
<table width="100%"  border="0" cellpadding="2" cellspacing="2" bordercolor="#8E96AB" bgcolor="#FFFFFF">
  <tr>
    <td>
<!-- 내용부분 시작 -->
		<form action="${control_action}" method="post" name="frmLogin" id="frmLogin" onSubmit="return validate(this)">
		<input type="hidden" name="cmd" value="login">
		<input type="hidden" name="next" value="${(empty next)?'/':mhtml:JSaddSlashes(next)}">
        <table width="100%" border="0" cellpadding="4" cellspacing="0">
			<tr>
				<th align="right">이&nbsp;&nbsp;름 : </th>
				<td><input type="text" name="nm" size="20" maxlength="15" required hname="이름"></td>
			</tr>
			<tr>
				<th align="right">주민등록번호 : </th>
				<td>
					<input type="text" name="pin_1"   size="6" maxlength="6"  required hname="주민등록번호" >-
					<input type="text" name="pin_2" size="7" maxlength="7"  required hname="주민등록번호" >
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><br><br><input type="submit" value="찾기"></td>
			</tr>
        </table>
        </form>
<!-- 내용부분 끝 -->
    </td>
  </tr>
</table>
<!-- 하단부분 -->
<TABLE width="100%" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
          <TD background="${CPATH}/images/mall/bottom.jpg" width="100%" height="10"></TD>
	</TR>

</TABLE>
<!-- 하단부분끝 -->

</BODY>
</HTML>