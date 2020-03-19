<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="jh" uri="http://jmf.jstl.com/jsp/tld/jmf-html.tld"%>
<%@ taglib prefix="jf" uri="http://jmf.jstl.com/jsp/tld/jmf-form.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>System Status</TITLE>
<style type="text/css"> 
		body, td {
			font-family:  "Verdana", "Arial", "sans-serif";
			font-size: 12px;
		}
		.tdblue {
			background-color: #0000ff;
			color: #ffffff;
			text-align: center;
		}
		.tdred {
			background-color: #ff0000;
			color: #ffffff;
			text-align: center;
		}
</style>
</head>
<body>
<table width="100%" border="1" cellpadding="2" cellspacing="0">
	<tr>
		<th>code</th>
		<th>name</th>
		<th>local name</th>
	</tr>
<sql:setDataSource  dataSource="jdbc:jeus:pool:hkmca"/>
<!-- sql:update>
	update JMF_LANG_ISO639 set LOCAL_NAME='中文(简体)' where code ='ZH'
</sql:update-->
<sql:query var="entries" >
	select * from V_JMF_LANG
</sql:query>
	<c:forEach var="item" items="${entries.rows}">
		<tr>
			<td><c:out value="${item.code }"/></td>
			<td><c:out value="${item.allnames }"/></td>
			<td><c:out value="${item.local_name }"/></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>