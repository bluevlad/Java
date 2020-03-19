<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <jsp:include  page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<script language="javascript">
<!--
function win_close(){
    if(confirm('<mfmt:message bundle="common.message.lecture" key="classroom.lecture.end"/>')) destroy();
    window.close();
}
//-->
</script>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
    <c:catch var="error">
        <c:import url="${MAF_INFO.file}" />
    </c:catch>
    <c:if test="${!empty error}">
        <mh:out value="${error}" td="true"/>
    </c:if>
<!-- CLOSE BAR START -->
<!--  table width="100%"  border="0" cellspacing="0" cellpadding="0" style="margin-top:30">
	<tr>
    	<td height="1" bgcolor="C7D9E8"></td>
	</tr>
	<tr>
	   <td height="40" align="center"><jf:button bundle="button" key="close" onclick="javascript:win_close();" icon="icon_delete" /></td>
	</tr>
</table-->
<!-- CLOSE BAR END -->
</body>
</html>