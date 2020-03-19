<%@ page contentType="text/html; charset=euc-kr %>
<%@ page language="java" %> 
<%@ page import="java.util.List"%>
<%@ page errorPage="/etc/error.jsp"%>  

<%
	String grpflag = (String)request.getAttribute("grpflag");  
	String catcode = request.getParameter("catcode");
	String cmtcode = request.getParameter("cmtcode");
	response.sendRedirect("/univ/servlets/M_CmtSesServlet?cmd=main&grpflag="+grpflag+"&catcode="+catcode+"&cmtcode="+cmtcode);	
%>

