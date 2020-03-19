<%@ page contentType = "text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>db Admin[<c:out value="${paramValues['owner'][0]}"/>]</title>
</head>

<frameset rows="60,*" frameborder="NO" border="0" framespacing="0">
  	<frame src="<c:url value="top.do">
  			<c:param name="owner" value="${param.owner}"/>
  		</c:url>" name="topFrame" scrolling="NO" noresize>
	<frameset cols="350,*" framespacing="1" frameborder="1">
  		<frame src="<c:url value="tableList.do">
  			<c:param name="owner" value="${param.owner}"/>
  			</c:url>" name="listFrame" id="listFrame" scrolling="NO" bordercolor="#000000">
  		<frame src="<c:url value="/blank.html"/>" name="mainFrame">
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
