<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=<fmt:message key='page.charset'/>">
<TITLE>SMIS ��������</TITLE>
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
		<!-- ����κ� ���� -->
        <form name=myform method=post action="${control_servlet}">
        <input type="hidden" name="cmd">
        <input type="hidden" name="reqid" value="${userid}">
        <table width="100%" border="0" cellpadding="4" cellspacing="0">
            <tr>
                <td align="center" class="td">
					<c:choose>
						<c:when test="${empty userid}">��ϵ� ID�� �����ϴ�. <a href="${control_action}">[��˻�]</a></c:when>
						<c:otherwise>
							��ϵ� ID�� [<strong>${userid}</strong>]�Դϴ�.<br><br>
							��й�ȣ�� �н��ϼ��� ���  <br>
							�Ʒ� ��ư�� Ŭ�� �Ͻø� ���ο� ��й�ȣ�� �����Ͽ� <br>
							e-mail�� �߼��� �帳�ϴ�.
						</c:otherwise>
					</c:choose>
                </td>
            </tr>
            <tr>
                <td align="center" class="td"><a href="javascript:set_passwd();">[��й�ȣ ���Ϸ� �ޱ�]</a></td>
            </tr>
        </table>
        </form>
		<!-- ����κ� �� -->
    </td>
  </tr>
</table>
<!-- �ϴܺκ� -->
<TABLE width="100%" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
          <TD background="${CPATH}/images/mall/bottom.jpg" width="100%" height="10"></TD>
	</TR>

</TABLE>
<!-- �ϴܺκг� -->

</BODY>
</HTML>