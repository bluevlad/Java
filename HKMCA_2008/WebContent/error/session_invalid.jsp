<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:import url="/error/inc_start.jsp"/>
<c:url var="nextUrl" value="${controlaction}" scope="request">
	<c:forEach var="aKey" items="${paramValues}">
			<c:if test="${aKey.key !='lang'}">
			<c:forEach var="val" items="${aKey.value}">
					<c:param name='${aKey.key}' value='${val}'/>
			</c:forEach>
			</c:if>
	</c:forEach>
</c:url>
<table width="584" height="35" border="0" cellspacing="3" cellpadding="0" align="center" bgcolor="#EBEBEB">
	<TR>
		<TD bgColor=#ffffff>
		<table width="578" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<td width="157"><IMG src='<c:url value="/error/images/img_logout.gif"/>' border=0></td>
				<td width="20"></td>
				<td width="401">
				<table width="401" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<td height="21" colspan="2" ><B><mfmt:message bundle="common.message" key="message.error.auth" /></B></td>
					</TR>

				</TABLE>
				<!-- // --></td>
			</TR>
		</TABLE>

		</TD>
	</TR>
</table>
<c:import url="/error/inc_finish.jsp"/>