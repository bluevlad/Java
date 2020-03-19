<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<table border="1" cellspacing="0" cellpadding="2">
	<tr>
		<td colspan="10">
		<h3>Resource</h3>
		</td>
	</tr>
	<tr>
		<th width="20%">bundle</th>
		<th width="40%">messages</th>
		<th width="40%">result</th>
	</tr>
	<c:forEach var="item" items="${RESOURCE}">
		<tr>
			<td valign="top"><c:out value="${item.key}"/><br>
				설명:[<c:out value="${item.value.desc}"/>]<br>
				defaultLocale : [<c:out value="${item.value.defaultLocale}"/>]<br>
				imagePath:[<c:out value="${item.value.imagePath}"/>]</td>
			<td valign="top">
				<c:forEach var="ritem" items="${item.value.resourceMap}">
					<c:out value="${ritem.key}[${ritem.value.type}]=${ritem.value.value}, ${ritem.value.filename}"/><br>

					<c:if test="${!empty  ritem.value.localeValueMap}">
					<c:forEach var="xitem" items="${ritem.value.localeValueMap}">
						&nbsp;&nbsp;&nbsp; +-- <c:out value="${xitem.key} = ${xitem.value}, ${xitem.value.filename}"/><br>
					</c:forEach>
					</c:if>
				</c:forEach>&nbsp;
			</td>
			<td valign="top">
				<c:forEach var="ritem" items="${item.value.resourceMap}">
					<mfmt:message bundle="${item.key}" key="${ritem.key}"  /><br>
					<c:if test="${!empty  ritem.value.localeValueMap}">
					<c:forEach var="xitem" items="${ritem.value.localeValueMap}">
						&nbsp;&nbsp;&nbsp; +-- <mfmt:message bundle="${item.key}" key="${ritem.key}" locale="${xitem.key}" /><br>
					</c:forEach>
					</c:if>
				</c:forEach>&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
