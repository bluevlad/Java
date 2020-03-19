<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=<fmt:message key='page.charset'/>" />
	<title>KAIS 회원정보관리</title>
	<link href="${CPATH}/css/common.css"	rel="stylesheet" type="text/css"></link>
	<script language="javascript"	src="${CPATH}/js/sub.common.js"></script>
	<script language="javascript">
		function frmSubmit(frm) {
				re = new RegExp("\.jpg$","i");
				if (frm.photo.value) {
					if (!frm.photo.value.match(re)) {
						alert('jpg 파일만 가능합니다.');return false;
					}
					var tImg = new Image();
					tImg.src = frm.photo.value;
					alert( " Width = " + tImg.width );
					return true;
				}
			return false;
		}
		window.resizeTo(400,270);
	</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<table align=center border=0 cellpadding=1 cellspacing=0 width=100% bgcolor=white>
		<tr bgcolor="#21A7D5" > 
			<td> <img src="${CPATH}/images/member/popup_photo_up.gif" alt="" border="0"> </td>
		</tr>
		<tr> 
			<td bgcolor=#0488B3 height=1></td>
		</tr>
		<tr> 
			<td bgcolor=#E0E0E0 height=3></td>
		</tr>
	</table>
<table align=center border=0 cellspacing=0 width=95%>
    	<tr>

 
              
        	<td>
          	<table align=center border=0 cellpadding=2 cellspacing=0 width=100% bgcolor=white>
          	<form name="Form1" method="post" action="${control_action}" enctype="multipart/form-data" onSubmit="return frmSubmit(this)">
						<input type="hidden" name="cmd" value="modPhoto_Act">
            		<tr> 
              			<td height=10></td>
            		</tr>
            		<tr> 
              			<td>사진의 규격은<b> 103*132 </b>Pixel 이 적절합니다 .</td>              
            		</tr>
            		<tr> 
              			<td>File형식은 <b>JPEG,JPG</b>만 가능합니다.</font></td>
            		</tr>            		
            		<tr> 
              			<td height=8></td>
            		</tr>
            		<tr> 
						<td height="3" background="${CPATH}/images/member/line_3_dot.gif"></td>
					</tr>
					<tr> 
						<td height=5 bgcolor=#efefef></td>
					</tr>
					<tr> 
						<td align=center height=25 bgcolor=#efefef><input type="file"  name="photo"> </td>
					</tr>
					<tr> 
						<td height=5 bgcolor=#efefef></td>
					</tr>
					<tr> 
						<td height="3" background="${CPATH}/images/member/line_3_dot.gif"></td>
					</tr>
          	</table></td>
    	</tr>
</table>

<table align=center border=0 cellspacing=0 width=300>
	<tr>
		<td height=10></td>
	</tr>
	<tr>
		<td align=center><input type="image" name="image" value="사진 업로드" src="${CPATH}/images/member/btn_photo_up.gif" border="0" img=""></td>
	</tr>
</table>
</form>

</body>
</html>			


</body>

</html> 
