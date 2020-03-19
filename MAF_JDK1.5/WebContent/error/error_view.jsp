<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page isErrorPage="true"%>
<c:import url="/error/inc_start.jspf"/>
<%
try {
	boolean chk = true;
	if ("127.0.0.1".equals(request.getRemoteAddr())) {
		chk = true;
	}
	if ( chk ) {
		Throwable e = exception;
		String errorMsg = null;
		String errMsg = null;
		String url = null;
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter writer = new java.io.PrintWriter(bos);
		e.printStackTrace(writer);
		writer.flush();
		
		errMsg = bos.toString();
		if (e != null) {
			errorMsg = exception.toString();
			url = errorMsg.substring(errorMsg.indexOf(":") + 1);
		}
%>
<hr>
<div align="left"><pre>
<%=errMsg%>
</pre></div>
<hr>
<%	} else { %>
<table width="584" height="35" border="0" cellspacing="3"
	cellpadding="0" bgcolor="#EBEBEB">
	<TR>
		<TD bgColor=#ffffff>
		<table width="578" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<td width="157"><IMG src="${CPATH}/error/images/img_logout.gif" border=0></td>
				<td width="20"></td>
				<td width="401">
				<table width="401" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD class=b colSpan=2 height=21><B>System Error!!!</B></TD>
					</TR>
				</TABLE>
				<!-- // --></td>
			</TR>
		</TABLE>

		</TD>
	</TR>
</table>
<%
	}
 } catch (Exception e) {
 }
%>
<c:import url="/error/inc_finish.jspf"/>