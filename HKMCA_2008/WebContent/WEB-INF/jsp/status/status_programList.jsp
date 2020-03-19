<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld"%>

<table border="1" cellspacing="0" cellpadding="2">

<c:forEach var="aKey" items="${CONFIGUREAIONS}">
	<tr>
		<td colspan="3"><h3><c:out value="${aKey.key}"/></h3></td>
	</tr>
	<tr>
		<td>Action : Class</td>
		<td>Command : Method</td>
		<td>View : JSP File</td>
	</tr>	
		
		<c:forEach var="cmd" items="${aKey.value.commandMapping}" varStatus="status">
		<tr>
			<td valign="top" ><c:out value="${aKey.key}/${cmd.key} ${ cmd.value.desc}"/><br> 
				&raquo; <mh:out value="${cmd.value.type}"  td="TRUE"/>&nbsp;</td>

			<td valign="top" >
			<c:if test="${!empty cmd.value.cmdMap}">
   				<c:forEach var="cv" items="${cmd.value.cmdMap}">
   						<mh:out value="${cv.key} : ${cv.value.method}"  td="TRUE"/> <br>
				</c:forEach>
			</c:if>&nbsp;
			</td>
			<td valign="top" >
				<c:if test="${!empty cmd.value.viewMap}">
					<c:forEach var="cv" items="${cmd.value.viewMap}">
    						<mh:out value="${cv.key} : ${cv.value.uri},${cv.value.templateName}" td="TRUE"/> <br>
					</c:forEach>
				</c:if>&nbsp;
			</td>
		</tr>
		</c:forEach>
		
</c:forEach>
</table>