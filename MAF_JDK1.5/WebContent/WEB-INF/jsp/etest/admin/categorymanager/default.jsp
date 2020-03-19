<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<table width="100%" height='550' border="1" cellspacing="0" cellpadding="0">
<tr >
	<td width='250' class='td' height="550">
	   <iframe src='<c:url value="${control_action}">
	   				<c:param name="cmd" value="tree"/></c:url>' name="frmTree" id="frmTree" width="250" height="100%" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</td>
	<td class='td' style='padding-left:5px;' height="550">
	   <iframe src='<c:url value="${control_action}" >
	   				<c:param name="cmd" value="edit"/></c:url>' name="frmMenuEdit" id="frmMenuEdit" width="550" height="100%" marginwidth="0" marginheight="0"></iframe>
	</td>
</tr>
</table>

<script language='javascript'>

