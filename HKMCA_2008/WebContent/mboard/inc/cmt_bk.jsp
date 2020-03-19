<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<fmt:setBundle var ="m" basename = "resources.board"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<!-- 코멘트 입력하기 시작 -->
	<form action="${MBOARD.PATH}/act.do" method="post" name="frmCmt" id="frmCmt" onSubmit="return validate(this)">
	<input type="hidden" name="cmd" value="cmt_add">
	<input type="hidden" name="c_id" value="">
	<input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}>">
	<tr>
		<td>
			<img src="${MBOARD.CPATH}/images/bullet/view_dot_01.gif" alt="" border="0" align="absmiddle">&nbsp;<strong><mfmt:message bundle="${m}" key="title.board.queswrite" /></strong>
			<c:choose>	
				<c:when test="${!empty(sessionScope.msession)}">
					<c:import url="${MBOARD.PATH}/inc/cmt_Auth.jsp"/>
				</c:when>
				<c:otherwise>
					<c:import url="${MBOARD.PATH}/inc/cmt_noAuth.jsp"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</FORM>
	<c:if test="${fn:length(refList) > 0}">
		<tr>
			<td>
			    <TABLE cellspacing="0" cellpadding="2" border=0 class="table" width="100%">
				<tr>
					<td class="bbs_view_top_th" width="100"><mfmt:message bundle="${m}" key="label.writer"/></td>
					<td class="bbs_view_top_th" width="356"><mfmt:message bundle="${m}" key="label.title"/></td>
					<td class="bbs_view_top_th" width="120"><mfmt:message bundle="${m}" key="label.regdt"/></td>
				</tr>
				<c:forEach items="${refList}" var="data">
						<tr >
							<td class="bbs_view_th" width="100"><img src="${MBOARD.CPATH}/images/icon/${data.c_opnion}.gif" align="absmiddle" border="0">${data.wname}</td>
							<td class="bbs_view_td" >${mhu:nl2br(data.c_memo)}
							<c:if test="${!empty(sessionScope.msession)}">
								<c:if test="${ (data.usn == sessionScope.msession.usn) ||
											(fn:indexOf(sessionScope.msession.utype, 'A') >= 0)  ||
											(MBOARD.board.admin_usn == sessionScope.msession.usn) }">
								<img src="${MBOARD.CPATH}/images/button/cmt_delete.gif" onClick="cmtDelete('${data.c_id}')" align="absmiddle" border=0 style="cursor:pointer;">
								</c:if>
							</c:if>
							</td>
							<td class="bbs_view_td" align="center" width="120"><fmt:formatDate value="${data.c_date}" type="both" dateStyle="medium" /></td>
						</tr>
				</c:forEach>
				</table>
				</td>
			</tr>			
	</c:if>
</table>