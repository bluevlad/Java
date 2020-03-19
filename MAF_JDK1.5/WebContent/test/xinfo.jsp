<%@page language="java" contentType="text/html; charset=utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<%@page import="java.util.*" %>

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


CPATH = [<mh:CPATH/>]<br>
IPATH = [<mh:IPATH/>]<br>
Current JSP Spec = [<c:out value="JSP 1.2"/>, ${'JSP 2.0'} ] 
<hr>
chkvalue01 : [<mh:out value="${chkvalue01}" />]<br>
chkvalue02 : [<mh:out value="${chkvalue02}" td="true"/>]<br>
function Check : <br>
<c:set var="str" value="012345"/>
<mh:length var="t" value="${str}"/>
length of String(EL)  : <c:out value="${str}"/> [<mh:out value="${t}"/>]<br/>
length of direct String(012345) : [<mh:length  value="012345"/>]<br/>
<mh:length var="t" value="${map}"/>
Map : [<mh:out value="${t}"/>]<mh:out value="${map}"/><br>
<mh:length var="t" value="${list}"/>
list : [<mh:out value="${t}"/>]<mh:out value="${list}"/>
<hr>
<table width="800" border="1" cellpadding="2" cellspacing="0">
	<tr>
		<th>param</th>
		<th>values</th>
	</tr>
	<tr>
		<td>pageContext.request.contextPath</td>
		<td><c:out value="${pageContext.request.contextPath}"/></td>
	</tr>
	<tr>
		<td>CPATH</td>
		<td><c:out value="${CPATH}"/></td>
	</tr>    

	<tr>
		<td>pageContext.request.getServerName</td>
		<td><c:out value="${pageContext.request.serverName}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.getServerPort</td>
		<td><c:out value="${pageContext.request.serverPort}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.getRequestURI</td>
		<td><c:out value="${pageContext.request.requestURI}"/></td>
	</tr>    
	<tr>
		<td>pageContext.request.getServletPath</td>
		<td><c:out value="${pageContext.request.servletPath}"/></td>
	</tr>    
	<tr>
		<td>pageContext.request.method</td>
		<td><c:out value="${pageContext.request.method}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.requestURL</td>
		<td><c:out value="${pageContext.request.requestURL}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.queryString</td>
		<td><c:out value="${pageContext.request.queryString}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.remoteAddr</td>
		<td><c:out value="${pageContext.request.remoteAddr}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.requestedSessionId</td>
		<td><c:out value="${pageContext.request.cookies[0].name}"/></td>
	</tr>

	<tr>
		<td colspan="2">
		<h3>Parameter info:</h3>
		</td>
	</tr>

	<c:forEach var="aKey" items="${paramValues}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td><c:forEach var="val" items="${aKey.value}">
					[<c:out value="${val}" />]<br />
			</c:forEach></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="2">
		<h3>Header info:</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${headerValues}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td><c:forEach var="val" items="${aKey.value}">
		     [<c:out value="${val}" />]<br />
			</c:forEach></td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>cookies:</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${pageContext.request.cookies}">
		<tr>
			<td><c:out value="${aKey.name}" /></td>
			<td><c:forEach var="val" items="${aKey.value}">
	     [<c:out value="${val}" />]<br />
			</c:forEach></td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>sessionScope:</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${sessionScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]
			<c:if test="${aKey.key == 'msession'}">
				<br/>
				userid = <c:out value="${aKey.value.userid}"/> , nm = <c:out value="${aKey.value.nm}"/><br/>

			</c:if></td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>Application Scope:</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${applicationScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]</td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>requestScope</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${requestScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]</td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>initParam</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${initParam}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]</td>
		</tr>
	</c:forEach>

	<tr>
		<td colspan="2">
		<h3>pageScope</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${pageScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="2">
		<h3>CONFIGUREAIONS</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${CONFIGUREAIONS}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]<br/>
				<c:forEach var="cmd" items="${aKey.value.commandMapping}">
					<a href="<c:url value='${aKey.key}${cmd.key}'/>" target="show"><c:out value="${cmd.key}" /></a> | <c:out value="${cmd.value.type}" /><br/>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>