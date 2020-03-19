<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:if test="${navigator != null}">
	<c:url var="listUrl" value="${controlaction}">
		<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
		<c:if test="${!empty(course)}">
			<c:param name="extleccode" value="${course.extleccode}" />
		</c:if>
	</c:url>
	<table border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<c:if test="${navigator.startPage > navigator.screenSize}">
				<c:url var="tUrl" value="${listUrl}">
					<c:param name="v_page" value="${navigator.startPage - navigator.screenSize}"/>
				</c:url>
				<td><a	href="${tUrl}"><img
					src="${MBOARD.CPATH}/images/pre.gif" valign="absmiddle" border="0"></a>
				&nbsp;</td>
			</c:if>
			<td><c:forEach var="i" begin="${navigator.startPage}"
				end="${navigator.endPage}">
				<c:choose>
					<c:when test="${i == navigator.currentPage}">
						<strong><c:out value="[${i}]"/></strong>
					</c:when>
					<c:otherwise>
						<c:url var="tUrl" value="${listUrl}">
							<c:param name="v_page" value="${i}"/>
						</c:url>
						<a href='<c:out value="${tUrl}"/>'><c:out value="[${i}]"/></a>
					</c:otherwise>
				</c:choose>
			</c:forEach></td>
			<c:if test="${navigator.endPage < navigator.pageCount}">
				<c:url var="tUrl" value="${listUrl}">
					<c:param name="v_page" value="${navigator.startPage + navigator.screenSize}"/>
				</c:url>
				<td>&nbsp; <a href="${tUrl}"><img	src="${MBOARD.CPATH}/images/next.gif" valign="absmiddle" border="0"></a>
				</td>
			</c:if>
		</tr>
	</table>
</c:if>
