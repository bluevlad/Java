<%@ page session="false" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="maf.web.requestMon.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ServiceTrace trace =ServiceTrace.getInstance();
	Map list = trace.getActiveStat();
	request.setAttribute("list" , list );

%>      

<h4>Maf Request Monitor version 1.0 </h4>


<table border="1" cellspacing="0" cellpadding="2" >
    <col width="300"/>
    <col width="100"/>
    <col width="100"/>
    <col width="100"/>
    <col width="100"/>
	<thead>
	<tr>
		<th>URI</th>
		<th>Processing time</th>
		<th>Request count</th>
		<th>avg</th>
		<th>max</th>
	
	</tr>
	</thead>
	<tbody>
    <c:forEach var="item" items="${list}" varStatus="status">
	<tr>
		<td><c:out value="${item.value.requestURI}"/></td>
		<td align="right"><fmt:formatNumber value="${item.value.elaspedTime}" /></td>
		<td align="right"><fmt:formatNumber value="${item.value.executeCount}"/></td>
		<td align="right"><fmt:formatNumber value="${item.value.elaspedTime/item.value.executeCount}" /></td>
		<td align="right"><fmt:formatNumber value="${item.value.maxTime}"/></td>

	</tr>
</c:forEach>
