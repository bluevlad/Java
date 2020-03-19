<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body leftmargin="10" topmargin="10" rightmargin="10" bottommargin="10" marginwidth="0">   
<jsp:include page="${MAF_INFO.file}" flush="true"/>
<br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="right"><a href="javascript:window.close();"><mfmt:message bundle="button" key="close"/></a></td>
    </tr>
</table>
</body>
</html> 