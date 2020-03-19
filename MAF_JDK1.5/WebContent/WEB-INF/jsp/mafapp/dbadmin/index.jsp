<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>db Admin[<c:out value="${paramValues['owner'][0]}"/>]</title>
</head>

<frameset rows="60,*" frameborder="NO" border="0" framespacing="0">
  	<frame src="<c:url value="top.do">
  			<c:param name="owner" value="${param.owner}"/>
  		</c:url>" name="topFrame" scrolling="NO" noresize>
	<frameset cols="300,*" framespacing="1" frameborder="1">
  		<frame src="<c:url value="tableList.do">
  			<c:param name="owner" value="${param.owner}"/>
  			</c:url>" name="listFrame" id="listFrame" scrolling="Auto" bordercolor="#000000">
  		<frame src="<c:url value="/blank.html"/>" name="mainFrame">
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
