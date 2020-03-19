<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.naming.directory.*"%>
<%!   
    public String getX(short x ) {
     
        int m1 = x/26;
        int m2 = x%26;
        String s = "";
        if(m1 > 0) {
            s = (char) (m1+65-1) + "";
        }
        s = s + (char) (m2+65);
        return s;
    }
%>
<html>
<head>
</head>
<body>
<%
for(int i = 0; i < 255; i++) {
    if (i % 26 == 0 ) out.print("<br>");
%>
<%=i%>:<%=getX((short)i)%>, 
<%
}%>
</body>