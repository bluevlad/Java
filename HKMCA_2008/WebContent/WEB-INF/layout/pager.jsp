<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include  page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function resize() {
    var w = 700;
    var h = 500;

    with(document.body)
    {
       window.resizeTo(w, h);
    }

}
function goMenu(cmd) {
    var frm = getObject("mymenu");
    frm.cmd.value = cmd;
    frm.submit();
}
//-->
</SCRIPT>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" onload="resize()">
<mf:form action="${control_action}" method="post" name="mymenu" id="mymenu">
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <col width="25%">
    <col width="25%">
    <col width="25%">
    <col width="25%">
    <tr height="50">
        <th><a href="javascript:goMenu('listRcv')"><mfmt:message bundle="pager" key="tab.rcvbox"/></a></th>
        <th><a href="javascript:goMenu('listSnd')"><mfmt:message bundle="pager" key="tab.sndbox"/></a></th>
        <th><a href="javascript:goMenu('msgWrite')"><mfmt:message bundle="pager" key="tab.writemsg"/></a></th>
        <th><a href="javascript:goMenu('addressBook')"><mfmt:message bundle="pager" key="tab.contacts"/></a></th>
    </tr>
</table>
</mf:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>
		    <c:catch var="error">
		        <c:import url="${MAF_INFO.file}" />
		    </c:catch>
		    <c:if test="${!empty error}">
		        <mh:out value="${error}" td="true"/>
		    </c:if>
		 </td>
	</tr>
</table>
<!-- CLOSE BAR START -->
<table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-top:30">
    <tr>
        <td height="1" bgcolor="C7D9E8"></td>
    </tr>
    <tr>
       <td height="40" align="center"><mf:button bundle="button" key="close" onclick="javascript:window.close();" icon="icon_delete" /></td>
    </tr>
</table>
<!-- CLOSE BAR END -->
</body>
</html>