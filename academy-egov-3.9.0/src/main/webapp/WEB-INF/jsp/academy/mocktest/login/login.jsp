<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
	<div style="width:690px; margin:0 auto; margin-top:100px;"></div>
	<div style="width:390px; margin:0 auto; border:2px solid #e1e1e1;">
		<form name="frm" action="<c:url value="/login/loginProc.adm" />"  method="post" class="pull-right" style="margin:0px;">
		<table border="0" cellspacing="0" cellpadding="0">
		<tr><td colspan="3">&nbsp;</td></tr>
		<tr>
			<!-- <td><img src="<c:url value="/resources/img/common/img_login01.gif"/>" /></td> -->
			<td>&nbsp;</td>
			<td>
				<table border="0" cellspacing="0" cellpadding="0">
				<tr><td colspan="4"><img src="<c:url value="/resources/img/common/img_login02.gif"/>" width="385" height="37" /></td></tr>
				<tr><td height="26" colspan="4">&nbsp;</td></tr>
				<tr>
					<td width="26">&nbsp;</td>
					<td width="66" align="left" valign="middle"><span style="FONT-SIZE: 13px; LETTER-SPACING: 0px; line-height:18px; color:#666666"><strong>아이디</strong></span></td>
					<td width="164" align="left"><input type="text" id="USERID" name="USERID" value="wcaman1" class="i_text" style=" width:140px; height:14px; position:relative;margin:2px;padding:3px 4px;border:1px solid #b7b7b7;border-right-color:#e1e1e1;border-bottom-color:#e1e1e1;background:transparent" title="아이디" /></td>
					<td width="147" rowspan="3"><a><img src="<c:url value="/resources/img/common/bt_login01.gif"/>" alt="로그인" onclick="frm.submit();" style="cursor:pointer" /></a></td>
				</tr>
				<tr><td height="5" colspan="3"></td></tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><span style="FONT-SIZE: 13px; LETTER-SPACING: 0px; line-height:18px; color:#666666"><strong>패스워드</strong></span></td>
					<td align="left"><input type="password" id="PSWD" name="PSWD" value="wcapass" class="i_text" style=" width:140px; height:14px; position:relative;margin:2px;padding:3px 4px;border:1px solid #b7b7b7;border-right-color:#e1e1e1;border-bottom-color:#e1e1e1;background:transparent" title="패스워드" /></td>
				</tr>
				</table>
			</td>
		</tr>
	    <tr><td colspan="3">&nbsp;</td></tr>
	  	</table>
	  	<input type="submit" value=" " style="border:0;background:0;" />
	  	</form>
	</div>
</body>
</html>
