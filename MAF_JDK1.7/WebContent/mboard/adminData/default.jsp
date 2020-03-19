<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<html>
<head>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=ks_c_5601-1987">
  <meta http-equiv="Cache-Control" content="No-Cache">
  <meta http-equiv="Pragma" content="No-Cache">
	<title>게시물관리</title>
</head>
<frameset rows="20,*" frameborder="1">
    <frame name="head" src="${control_action}?cmd=head" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0">
    <frameset cols="200,*" frameborder="0">
        <frame name="tree" src="${control_action}?cmd=tree" marginwidth="0" marginheight="0" scrolling="no" frameborder="0">
        <frame name="content" src="${control_action}?cmd=blank" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0">
    </frameset>
</frameset>
</html>
