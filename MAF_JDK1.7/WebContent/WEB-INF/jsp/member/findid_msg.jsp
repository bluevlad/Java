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
<SCRIPT LANGUAGE="JavaScript">
<!--
	function set_passwd(){
	    var frm = getObject("myform");
	    frm.action = "findid.do";
	    frm.submit();
//	    window.close();
	}
//-->
</SCRIPT>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/lib.validate.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/sub.common.js"></script>

<BODY style="background-repeat: repeat-x; background-image: url(${CPATH}/images/mall/bg.jpg);" bgcolor="CED2DB" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<table width="100%"  border="0" cellpadding="2" cellspacing="2" bordercolor="#8E96AB" bgcolor="#FFFFFF">
  <tr>
    <td>
		<!-- 내용부분 시작 -->
        <form name=myform method=post action="${control_servlet}">
        <input type="hidden" name="cmd">
        <input type="hidden" name="reqid" value="${userid}">
        <table width="100%" border="0" cellpadding="4" cellspacing="0">
            <tr>
                <td align="center" class="td">
					<c:choose>
						<c:when test="${empty userid}">등록된 ID가 없습니다. <a href="${control_action}">[재검색]</a></c:when>
						<c:otherwise>
							등록된 ID는 [<strong>${userid}</strong>]입니다.<br><br>
							비밀번호를 분실하셨을 경우  <br>
							아래 버튼을 클릭 하시면 새로운 비밀번호를 지정하여 <br>
							e-mail로 발송해 드립니다.
						</c:otherwise>
					</c:choose>
                </td>
            </tr>
            <tr>
                <td align="center" class="td"><a href="javascript:set_passwd();">[비밀번호 메일로 받기]</a></td>
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