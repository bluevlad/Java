<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<table  border="1" cellspacing="0" cellpadding="2">
<%
   Runtime rt = Runtime.getRuntime();
   long free = rt.freeMemory();
   long total = rt.totalMemory();
   long usedRatio = (total - free) * 100 / total;
   long unusedRatio = free * 100 / total;
   request.setAttribute("total", total+"");
   request.setAttribute("free", free+"");

%>

<table width=500 bgcolor="lightgrey" border=1 cellpadding=6 cellspacing=0>
	<tr>
		<td align="center" colspan="2">Total JVM Memory (<b><fmt:formatNumber value="${total/1024}"/> KB</b>)</td>
	</tr>
	<tr bgcolor=#E3E3E3>
		<td align="center">Used memory (<b><mh:out value="${(total - free)/1024}" format="currency"/> KB</b>)</td>
		<td align="center">Free memory (<b><mh:out value="${free/1024}" format="currency"/> KB</b>)</td>
	</tr>
	<tr bgcolor=#E8EEEC>
		<td><hr color="#CC3366" align=left size=10 width="<%= usedRatio %>%" noshade>
		(<%= usedRatio %> %)</td>
		<td><hr color="#0066FF" align=left size=10 width="<%= unusedRatio %>%" noshade>
		(<%= unusedRatio %> %)</td>
	</tr>
</table>
<table width=500 bgcolor="lightgrey" border=1 cellpadding=6 cellspacing=0>
	<tr>
		<th>availableProcessors=<%=rt.availableProcessors() %></th>
	</tr>
	<tr>
		<td align="center" >
			<a href='<c:url value="${control_action}"><c:param name="gc" value=""/></c:url>'>[run GC]</a>
			&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			<a href="${control_action}">[refresh]</a><br>
			<mf:form method="post" action="${control_action}" enctype="multipart/form-data" name="rrdform">
			<font face="Arial, Helvetica, sans-serif" size="-1">refresh: 5 min<input type="text" value="" name="counter" size="5"></font>
			</mf:form>
		</td>
	</tr>
</table>