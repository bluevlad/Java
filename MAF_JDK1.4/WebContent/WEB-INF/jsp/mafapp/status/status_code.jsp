<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<table border="1" cellspacing="0" cellpadding="2">
	<tr>
		
		<h3>Resource</h3>

	</tr>
	<tr>
		<th width="20%">code group</th>
		<th width="40%">code</th>

	</tr>
	<c:forEach var="item" items="${CODES}">
		<tr>
			<td valign="top"><c:out value="${item.key}"/><br>
				설명:[<c:out value="${item.value}"/>]
				</td>
			<td valign="top">
				<c:forEach var="ritem" items="${item.value}">
					<c:out value="${ritem.key}=${ritem.value}"/><br>
				</c:forEach>
			</td>
			
		</tr>
	</c:forEach>
</table>
