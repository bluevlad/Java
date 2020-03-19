<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import ="java.net.*,java.io.*,java.util.*,java.text.*" %>
<html><head><title>JSP Test page.</title></head><body><h4>
<%		
		out.println("<h3>- System Informations -</h3>");
        out.println("<table border=\"0\" cellpadding=\"5\">");

		String getStr="";
		InetAddress Address =InetAddress.getLocalHost();

		getStr=Address.getHostAddress();
		if(getStr.length()>0)
		{
			out.println("<tr><td bgcolor=\"#B0E2FF\">IP Address</td><td>");
			out.println(getStr + "</td></tr>");
		}
		getStr=Address.getHostName();
		if(getStr.length()>0)
		{
			out.println("<tr><td bgcolor=\"#B0E2FF\">Host name</td><td>");
			out.println(getStr + "</td></tr>");
		}
		URL url = application.getResource("/test.jsp");
		if(url!=null)
		{
			getStr = url.getPath();
			if(getStr.length()>0)
			{
				out.println("<tr><td bgcolor=\"#B0E2FF\">Test JSP Path</td><td>");
			    out.println(getStr + "</td></tr>");
			}
		}

		out.println("<tr><td bgcolor=\"#B0E2FF\">Server Info</td><td>");
		out.println(application.getServerInfo()+ "</td></tr>");
		out.println("<tr><td bgcolor=\"#B0E2FF\">Servlet/JSP version</td><td>");
		out.println(application.getMajorVersion()+"."+application.getMinorVersion()+ "</td></tr>");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = fmt.format(new java.util.Date());
		out.println("<tr><td bgcolor=\"#B0E2FF\">Current system time</td><td>");
		out.println(time+ "</td></tr>");
		out.println("</table>");

		out.println("<h3>- Session Informations -</h3>");
        out.println("<table border=\"0\" cellpadding=\"5\">");
 
	    out.println("<tr><td bgcolor=\"#B0E2FF\">Session ID</td><td>");
        out.println(session.getId() + "</td></tr>");
        out.println("<tr><td bgcolor=\"#B0E2FF\">Creation Time</td><td>");
        out.println(new Date(session.getCreationTime()) + "</td></tr>");
        out.println("<tr><td bgcolor=\"#B0E2FF\">Last Access Time</td><td>");
        out.println(new Date(session.getLastAccessedTime()) + "</td></tr>");
        out.println("<tr><td bgcolor=\"#B0E2FF\">is New</td><td>");
        out.println(session.isNew() + "</td></tr>");
        out.println("<tr><td bgcolor=\"#B0E2FF\">Max Inactive Interval(Time out seconds)</td><td>");
        out.println(session.getMaxInactiveInterval() + "</td></tr>");
        String inactiveTime = request.getParameter("inactive");
		try {
			int interval = Integer.parseInt(inactiveTime);
			if (interval != session.getMaxInactiveInterval()) {
					session.setMaxInactiveInterval(interval);
					out.println("<tr><td bgcolor=\"#B0E2FF\">New Max Inactive Interval</td><td>");
					out.println(session.getMaxInactiveInterval() + "</td></tr>");
			}
		} catch (NumberFormatException nfe) {
		}

        out.println("</table>");

%>
</h4></body></html>
