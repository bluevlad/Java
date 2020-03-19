<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>

<body>
<c:out value="${control_action}, syncstatus : ${syncstatus}"/>
<a href='<c:url value="${control_action}"><c:param name="cmd" value="start"/></c:url>'>[Start]</a> | 
<a href='<c:url value="${control_action}"><c:param name="cmd" value="stop"/></c:url>'>[Stop]</a> | 
<a href='<c:url value="${control_action}"/>'>[Reload]</a>
<hr>
<c:forEach var="item" items="${info.tables}">
	Key = <c:out value="${item.key}"/><br>
	<c:forEach var="item2" items="${item.value.columns}">
		- Column : <c:out value="${item2.key} , ${item2.value.s_nm} ${item2.value.s_value} -&gt; ${item2.value.t_nm}, ${item2.value.t_value} , ${item2.value.pkey}"/><br>
	</c:forEach>
	<br>selectSql : <br>
    <textarea cols="80" rows="5"><c:out value="${item.value.selectSql}"/></textarea>
    <br>updateSql : <br>
    <textarea cols="80" rows="5"><c:out value="${item.value.updateSql}"/></textarea>
    <br>insertSql : <br>
    <textarea cols="80" rows="5"><c:out value="${item.value.insertSql}"/></textarea>
    <br>deleteSql : <br>
    <textarea cols="80" rows="5"><c:out value="${item.value.deleteSql}"/></textarea>
	<hr>
</c:forEach><br>
</body>
</html>
