<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="miraenet.AppConfig" %>
<c:set var="aicc_url" value="<%=AppConfig.AICC_URL%>"/>
<c:set var="apicodebase_url" value="<%=AppConfig.APICODEBASE_URL%>"/>
<c:set var="lms_url" value="<%=AppConfig.LMS_URL%>"/>
<c:set var="item_id" value="${citem.itm_id}"/>
<c:set var="openPage" value="${citem.launch_data}"/>
<c:choose>
  <c:when test="${empty userid}">
<script language="JavaScript">
<!--
    alert("<bean:message key="login.message.relogin"/>");
    parent.opener.top.location.href="${CPATH}";
    self.close();
-->
</script>
  </c:when>
  <c:otherwise>
<HTML>
<title><mfmt:message bundle="common" key="classroom.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script language="javascript" src="${CPATH}/script/common.js"></script>
<style type="text/css">
<!--
.style1 {
	font-family: "굴림";
	font-weight: bold;
	color: #000000;
}
-->
</style>

<object name="API" classid="clsid:A04EB8B6-3462-48BD-A595-EA010E6B2980" width="0" height="0" 
CODEBASE="${CPATH}/lmsobject/SpeedAdapter.cab#version=1.0.0.1">
<PARAM NAME="ServerURL" value="${CPATH}/lms.learner/lmscmi.do">
<PARAM NAME="LecCode" value="${leccode}">
<PARAM NAME="Item_Id" value="${item_id}">
<PARAM NAME="StdCode" value="${userid}">
</object>
<script language ="JavaScript" >
<!--
function check(){
    parent.opener.location.href= "${CPATH}/wlc.learner/chapters.do";
}
window.onbeforeunload = check;
//-->
</script>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="1%" height="80"><img src="${CPATH}/lmsobject/scorm_viewer/bg-01.gif" width="10" height="80"></td>
        <td width="98%" background="${CPATH}/lmsobject/scorm_viewer/bg-02.gif">
            <table width="100%" height="80"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align=left><img src="${CPATH}/lmsobject/scorm_viewer/dout.gif" width="9" height="9"><span class="style1">${citem.itm_title}</span></td>
                    <td></td>
                    <td width="5%" align="right" valign="bottom"><a href="javascript:self.close();"><img src="${CPATH}/lmsobject/scorm_viewer/close.gif" width="54" height="22" border=0></a></td>
                </tr>
            </table>
        </td>
        <td width="1%"><img src="${CPATH}/lmsobject/scorm_viewer/bg-03.gif" width="10" height="80"></td>
    </tr>
</table>
<iframe src="${CPATH}${openPage}" width="100%" height="90%"/>
</body>
</html>
  </c:otherwise>
</c:choose>  