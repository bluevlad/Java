<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page session="false"%>
<% 
	String menu_on = request.getParameter("menu_on");

%>

<table width="450" height="43" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td><img src="../images/msg/icon_03.gif" width="82" height="43"></td>
		<td><a href="#" onMouseOut="MM_swapImgRestore()"
			onMouseOver="MM_swapImage('msg_m01','','../images/msg/menu_on_01.gif',1)"><img
			src="../images/msg/menu_off_01.gif" name="msg_m01" width="91"
			height="43" border="0"></a></td>
		<td><a href="#" onMouseOut="MM_swapImgRestore()"
			onMouseOver="MM_swapImage('msg_m02','','../images/msg/menu_on_02.gif',1)"><img
			src="../images/msg/menu_off_02.gif" name="msg_m02" width="91"
			height="43" border="0"></a></td>
		<td><img src="../images/msg/menu_on_03.gif" width="91" height="43"></td>
		<td><a href="#" onMouseOut="MM_swapImgRestore()"
			onMouseOver="MM_swapImage('msg_m04','','../images/msg/menu_on_04.gif',1)"><img
			src="../images/msg/menu_off_04.gif" name="msg_m04" width="95"
			height="43" border="0"></a></td>
	</tr>
</table>
