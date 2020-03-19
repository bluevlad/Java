<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld"%>

<table border="1" cellspacing="0" cellpadding="2">
	<tr>
		<td colspan="2">
		<h3>Action CONFIGUREAIONS</h3>
		</td>
	</tr>
	<c:forEach var="aKey" items="${CONFIGUREAIONS}">
		<tr>
			<td><c:out value="${aKey.key}<br>${aKey.value.desc}" escapeXml="false"/></td>
			<td>## Template : <br />
			<c:forEach var="cmd" items="${aKey.value.templateMap}">
					- <c:out value="${cmd.key}"/> =  <mh:out value="${cmd.value}" nl2br="true"/><br />
			</c:forEach>
			<hr>
			<c:forEach var="cmd" items="${aKey.value.commandMapping}">
				<a href="<c:url value='${aKey.key}/${cmd.key}'/>" target="show"><mh:out value="${cmd.key}"/></a> <mh:out value="(${cmd.value.desc}) , ${cmd.value.type}"/>
					<br />
				<c:if test="${!empty cmd.value.cmdMap}">
    					&nbsp;&nbsp;# CmdMap<br />
					<c:forEach var="cv" items="${cmd.value.cmdMap}">
    						&nbsp;&nbsp;&nbsp;&nbsp;+-- <mh:out value="${cv.key} : ${cv.value.method}, formName:${cv.value.formName}"/> <br>
					</c:forEach>
					<br>
					
				</c:if>
				<c:if test="${!empty cmd.value.viewMap}">
    					&nbsp;&nbsp;# ViewMap<br />
					<c:forEach var="cv" items="${cmd.value.viewMap}">
    						&nbsp;&nbsp;&nbsp;&nbsp;+-- <mh:out value="${cv.key} : ${cv.value.uri},${cv.value.templateName}"/> <br>
					</c:forEach>
					<br>
				</c:if>
				<c:if test="${!empty cmd.value.formMap}">
    					&nbsp;&nbsp;# formMap<br />
					<c:forEach var="cv" items="${cmd.value.formMap}">
    						&nbsp;&nbsp;&nbsp;&nbsp;+-- <mh:out value="${cv.key}" nl2br="false"/> <br>
					</c:forEach>
					<br>
				</c:if>
			</c:forEach>
			<hr />

			Global View : <br />
			<c:forEach var="gv" items="${aKey.value.globalViewMap}">
				&nbsp;&nbsp;+--  <mh:out value="${gv.key} :${cv.value.uri},${cv.value.templateName}"/> <br>
			</c:forEach></td>
		</tr>
	</c:forEach>
</table>