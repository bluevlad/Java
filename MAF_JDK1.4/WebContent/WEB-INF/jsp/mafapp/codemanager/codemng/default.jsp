<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<table width="100%" height='550' border="0" cellspacing="0" cellpadding="0">
	<tr height='100%'>
		<td width='250' valign="top">
		   <iframe src="<mh:ACTION/>?cmd=list" name="frmList" id="frmList" width="250" height="550" marginwidth="0" marginheight="0" scrolling="auto"></iframe>
		</td>
		<td class='td' style='padding-left:5px;' valign="top">
		   <iframe src="<mh:ACTION/>?cmd=gadd" name="frmEdit" id="frmEdit" width="650" height="550" marginwidth="0" marginheight="0"></iframe>
		</td>
	</tr>
</table>

