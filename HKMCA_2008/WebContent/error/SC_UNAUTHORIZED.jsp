<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<jsp:include  page="/error/inc_start.jsp" flush="true"/> 
<table width="584" height="35" border="0" align="center" cellspacing="3" cellpadding="0" bgcolor="#EBEBEB">
	<TR>
		<TD bgColor=#ffffff>
		<table width="578" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<td width="157"><IMG src='<c:url value="/error/images/img_logout.gif"/>' border=0></td>
				<td width="20"></td>
				<td width="401">
				<table width="401" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD class=b colSpan=2 height=21><B><mfmt:message bundle="common" key="login.unauthorized" /></B></TD>
					</TR>
				</TABLE>
				<!-- // --></td>
			</TR>
		</TABLE>

		</TD>
	</TR>
</table>
<br>
<jsp:include  page="/error/inc_finish.jsp" flush="true"/> 
<jsp:include  page="/WEB-INF/layout/common/commonTailInfo.jsp" flush="true"/> 