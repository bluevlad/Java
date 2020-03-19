<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<html>
<head>
<title>아이디중복확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<link href="${CPATH}/css/common.css"	rel="stylesheet" type="text/css"></link>
	<script language="javascript"	src="${CPATH}/js/sub.common.js"></script>
	<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript">
<!--
	function setUserID() {
		opener.document.myform.userid.value = "${param.userid}";
		self.close();
	}
	
	function check() {
		var frm = document.getElementById("myform");
		if(frm) {
			if(validate(frm)) {
				frm.submit();
			}
		}
	}
	
	function setf() {
		
		var frm = document.getElementById("myform");
		if(frm) {
			frm.userid.focus();
		}
	}
	window.resizeTo(460, 400);
//-->
</script>

</head>

<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" bgcolor="#FFFFFF" onload="javascript:setf();">
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return validate(this)">
<table border="0" cellpadding="0" cellspacing="0" width="450">
<tr>
	<td><img src="${CPATH}/images/www/member/bit_pop01.jpg" width="15" height="50"></td>
	<td background="${CPATH}/images/www/member/bit_pop02.jpg" width="420">
		<img src="${CPATH}/images/www/member/ts_pop_checkid.gif" width="100" height="20" alt="아이디(ID)확인"><br>
	</td>
	<td><img src="${CPATH}/images/www/member/bit_pop03.jpg" width="15" height="50"></td>
</tr>
<tr>
	<td valign="top"><img src="${CPATH}/images/www/member/bit_pop04.jpg" width="15" height="162"></td>
	<td valign="top" style="padding:5px" height="250">
		<c:if test="${isOverlap=='OK'}">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td width="120" nowrap valign="top">
				<img src="${CPATH}/images/www/member/img_apply.gif" width="115" height="88"><br>
			</td><td style="line-height:140%" height="134" valign="top">
				입력하신 <font color="#eb7022">'${param.userid}'</font>아이디(ID)는 사용하실 수 있습니다.<br>
				신청하시려면 '신청하기'버튼을 누르시기 바랍니다.<br>
				<a href="javascript:setUserID();"><img src="${CPATH}/images/www/member/bt_apply02.gif" width="71" height="21" border="0" alt="신청하기" vspace="6"></a><br>
			</td></tr>
		</table>
		</c:if>
		<c:if test="${isOverlap == 'DUP'}">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td width="120" nowrap valign="top">
				<img src="${CPATH}/images/www/member/img_double.gif" width="115" height="88"><br>
			</td><td valign="top" nowrap style="padding-right:3px">
				<img src="${CPATH}/images/www/member/bl_square_arrow.gif" width="12" height="11" align="absmiddle" vspace="2"><br>
			</td><td style="line-height:140%" height="134" valign="top">
				<font color="#2f7eae"><b>사용할 수 없는 아이디(ID)입니다.</b></font><br>
				입력하신 아이디(ID) <font color="#eb7022">'${param.userid}'</font>는 사용하실 수 없는
				아이디(ID)입니다.<br>
				다른 아이디(ID)를 확인하고자 하시면 아래에<br>
				다른 아이디(ID)를 입력하시고 중복여부를<br>
				확인하시기 바랍니다.
			</td></tr>
		</table>
		</c:if>
		<c:if test="${isOverlap == 'NONE'}">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td nowrap valign="top">사용하려는 아이디(ID)를 입력하시고 중복여부를 확인하시기 바랍니다. </td></tr>
		</table>
		</c:if>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
		<tr>
			<td width="127" bgcolor="#75bbd4" height="2"></td>
			<td bgcolor="#a5c9d9"></td>
		</tr>
		<tr>
			<td width="127" nowrap style="padding-left:15px" class="blue3e7" bgcolor="#eef0f4" height="55">
				아이디(ID)입력
			</td>
			<td width="100%" bgcolor="#fcfcfc" style="padding-left:10px">
				<font size="3" face="굴림체"><input type="text" name="userid" maxlength="15" style="width:122px" required option="userid" hname="아이디(ID)"></font>
				<a href="javascript:check();"><img src="${CPATH}/images/www/member/btn_confirm_overlap.gif" width="50" height="18" align="absmiddle" border="0" alt="중복확인"></a>
			</td>
		</form></tr>
		<tr><td colspan="2" height="1" bgcolor="#dcdcdc"></td></tr>
		</table><br>
	</td>
	<td valign="top"><img src="${CPATH}/images/www/member/bit_pop05.jpg" width="15" height="80"></td>
</tr>
<tr><td colspan="3" background="${CPATH}/images/member/bg_popfoot.gif" height="35" align="center">
	<a href="javascript:window.close()"><img src="${CPATH}/images/www/member/bt_close.gif" width="43" height="21" border="0" alt="닫기"></a><br>
</td></tr>
</table>

</body>
</html>
