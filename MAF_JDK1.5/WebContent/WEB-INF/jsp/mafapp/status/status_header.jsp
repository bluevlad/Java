<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>

<table width="100%" border="1" cellpadding="2" cellspacing="0">
	<tr>
		<th colspan="2"></th>
		<h3>Header </h3>
	</tr>
	<tr>
		<th>param</th>
		<th>values</th>
	</tr>
	<tr>
		<td>pageContext.request.contextPath</td>
		<td><c:out value="${CPATH}"/></td>
	</tr>
	<tr>
		<td>pageContext.request.servletPath</td>
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
		<td>pageContext.request.requestURI</td>
		<td><c:out value="${pageContext.request.requestURI}"/></td>
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
		<h3>sessionScope:</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${sessionScope}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]
			<c:if test="${aKey.key == 'msession'}">
				<br/>
				userid = <c:out value="${aKey.value.userid}"/><br/>
				usn = <c:out value="${aKey.value.usn}"/><br/>
				nm = <c:out value="${aKey.value.nm}"/><br/>
			</c:if></td>
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
			<td>[<c:if test="${aKey.key != 'RESOURCE' && aKey.key != 'CONFIGUREAIONS' }">
                    <c:out value="${aKey.value}" />
                 </c:if>]</td>
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
			<td>[<c:if test="${aKey.key != 'RESOURCE' && aKey.key != 'CONFIGUREAIONS' }">
                    <c:out value="${aKey.value}" />
                 </c:if>]</td>
		</tr>
	</c:forEach>
	
</table>