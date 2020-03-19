<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript">
	function frmSubmit(frm) {
			if(validate(frm)) {
				return true;
			} else {
				return false;
			}
	}
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#F8F8F7" style="BORDER-RIGHT: #e9e9e9 1px solid; BORDER-TOP: #e9e9e9 1px solid; BORDER-LEFT: #e9e9e9 1px solid; BORDER-BOTTOM: #e9e9e9 1px solid">
		<tr>
			<td style="padding-left: 20px; padding-right: 20px; padding-top: 20px; padding-bottom: 20px;">
			<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
			<input type="hidden" name="cmd" value="step02">
			<input type="hidden" name="${TOKEN.key}" value="${TOKEN.value}">
			<table align="center" width="80%">
			<tr>
				<td align="center">외부로부터 <strong>${sessionScope.msession.userid}</strong>님의 정보를 안전하게 보호하기 위해<br> 비밀번호를 확인하셔야 합니다.</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" cellspacing="0" cellpadding="2" class="table_comment">
								<tr>
									<th align="right">아이디(ID) : </th>
									<td><input type="text" name="userid" value="${sessionScope.msession.userid}" size="20" maxlength="15" tabindex="10" HNAME="아이디(ID)" readonly></td>
								</tr>
								<tr>
									<th align="right">암호 : </th>
									<td><input type="password" name="passwd"   size="20" maxlength="32"  required tabindex="1" hname="암호" ></td>
								</tr>

						</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<!--<td align="center"><input type="image" src="${CPATH}/images/member/btn_agreement01.gif" align="absmiddle" border="0">&nbsp;
				<a href="${CPATH}"><img src="${CPATH}/images/member/btn_cancel.gif" alt="" border="0" align="middle"></a></td>-->
				<td align="center"><input type="image" src="${CPATH}/images/www/member/btn_agreement01.gif" align="absmiddle" border="0">&nbsp;
				<a href="${CPATH}"><img src="${CPATH}/images/www/member/btn_cancel.gif" alt="" border="0" align="middle"></a></td>
			</tr>
			</table>
			</form></td>
		</tr>
		</table></td>
	</tr>
</table>
