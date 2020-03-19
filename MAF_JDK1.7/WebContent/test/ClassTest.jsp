<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="test.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=utf-8" />
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>Dojo TreeTest</title>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<%
    int line = 1;
    Test x = new Test();
    x.hit();
    x.setName("gg");
    out.print(line + ":" + x.getCount() + "<br>");
    out.print(line + ":" + x.getCount() + "<br>");
    out.print("new <br>");
    Test y = new Test();
    y.hit();
    out.print(line + ":" + y.getCount() + "<br>");
    out.print(line + ":" + y.getCount() + "<br>");
    out.print("name : [" + y.getName() + "]<br>");

    out.print("<hr>Abstract Class <br>");
    line = 1;
    TestAbstract xa = new TestAbstract();
    xa.setName("TestAbstract");
    xa.hit();
    out.print(line + ":" + xa.getCount() + "<br>");
    out.print(line + ":" + xa.getCount() + "<br>");
    out.print("new <br>");
    TestAbstract ya = new TestAbstract();
    ya.hit();
    out.print(line + ":" + ya.getCount() + "<br>");
    out.print(line + ":" + ya.getCount() + "<br>");
    out.print("name : [" + ya.getName() + "]<br>");
%>
</body>
</body>
</html>