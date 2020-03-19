<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage="true" %>
<%
    Exception e = (Exception) request.getAttribute("error");
    String error_msg = (e != null) ? maf.logger.Trace.getStackTrace(e) : "No Error !";
	String exceptionMode = "debug";
%>
<html>
<head>
    <META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=euc-kr">
    <title>Error Page</title>
</head>
<body bgcolor="white">

<% if ("debug".equals(exceptionMode)) { %>
<font size=2>
==============================================================================<br>
             #  에러 메세지 수집 #<br>
==============================================================================<br>
</font>
<pre>
<%= error_msg %>
</pre>
<font size=2>
==============================================================================<br>
            #  에러 메세지 끝 #<br>
==============================================================================<br>
</font>

<%} else {%>
<br>
<br>
<table  align=center width="451" height="216"  border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td width="162">&nbsp;</td>
		<td style='padding:14'>
			<table width="256"  border="0" cellpadding="0" cellspacing="0">
            	<tr>
            		<td> 
            				<span class="bbstop"><fmt:message key="title.error.title"/></span></td>
           		</tr>
            	<tr>
            		<td height="6"></td>
           		</tr>
            	<tr>
            		<td height="2" bgcolor="DFD5FF"></td>
           		</tr>
            	<tr>
            		<td height="120" align="center" class="bbsbottom">
						<%
					 	out.print(e.toString());
						%>
					
					</td>
           		</tr>
            	<tr>
            		<td height="1" bgcolor="EEEEEE"></td>
           		</tr>
            	<tr>
            		<td height="28" align="right" valign="bottom">
            			<a href="javascript:history.go(-1);">
            			</td>
           		</tr>
           	</table>
		</td>
	</tr>
</table>


	


<% } %>

</body>
</html>