<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>

<body>
<table border="1" cellspacing="0" cellpadding="2">

<c:forEach items="${tables}" var="item" varStatus="status">
	<tr>
		<td colspan="2">${status.count}.${item.key}</td>
		<td>${mcount[item.key]}</td>
	</tr>
		<c:forEach items="${item.value}" var="citem" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${citem.column_name}</td>
				<td>${citem.column_type}(${citem.length})</td>
			</tr>
		</c:forEach>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</c:forEach>
</table>

</body>
</html>
