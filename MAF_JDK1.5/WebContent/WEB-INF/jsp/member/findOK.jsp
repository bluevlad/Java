<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=<fmt:message key='page.charset'/>">
<TITLE>SMIS 교육센터</TITLE>
<link href="/smis/css/common.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
    window.resizeTo("250","200");
</script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/lib.validate.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/sub.common.js"></script>

<BODY style="background-repeat: repeat-x; background-image: url(${CPATH}/images/mall/bg.jpg);" bgcolor="CED2DB" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<table width="100%"  border="0" cellpadding="2" cellspacing="2" bordercolor="#8E96AB" bgcolor="#FFFFFF">
  <tr>
    <td>
		<!-- 내용부분 시작 -->
        <form name=myform method=post action="${control_servlet}">
        <input type="hidden" name="cmd">
        <table width="100%" border="0" cellpadding="4" cellspacing="0">
            <tr>
                <td align="center" class="td">
                <c:choose>
                	<c:when test="${send_stat=='OK'}">
						변경된 비밀번호를 등록하신 메일(<b>${email}</b>)로 보내드렸습니다.<br>
						로그인 후 개인정보 관리에서 비밀번호를 다시 변경하시면 됩니다.<br>
					</c:when>
					<c:otherwise>
						<font color="red">ERROR !!!</font>변경된 비밀번호를  등록하신 메일(<b>${email}</b>)로 <br>
						보내는 도중 오류가 발생하였습니다.<br>
						전화 02-3471-8588 로 문의 하시면 본인확인 후 <br>
						새로운 비밀번호를 지정하여 드립니다.
					</c:otherwise>
				</c:choose>
                </td>
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