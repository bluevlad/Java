<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page import="hddms.main.*" %>    
<%@page import="java.net.*" %>    
<%@page import="maf.web.util.*" %>   
<%
    String userid = request.getParameter("userid");
    if(userid == null) {
     userid="2822131";   
    }
    String encode = EnDeCrypt.encode(userid);
    String renc = null;
   renc = URLEncoder.encode(encode);
   // renc = encode;
%>
<%
    response.sendRedirect("/hkmcb/rlogin.do?userid="+ renc);
%>