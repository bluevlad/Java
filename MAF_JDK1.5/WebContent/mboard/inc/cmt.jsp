<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>

<script language="JavaScript" src="${MBOARD.CPATH}/js/cmt.js" type="text/javascript"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<!-- 코멘트 입력하기 시작 -->
	<form action="${MBOARD.PATH}/act.do" method="post" name="frmCmt" id="frmCmt" onSubmit="return validate(this)">
	<input type="hidden" name="cmd" value="cmt_add">
	<input type="hidden" name="c_id" value="">
	<c:if test="${!empty(course)}">
		<input type="hidden" name="extleccode" value="${course.extleccode}">
	</c:if>	
	<input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}>">
	<tr>
		<td>
			<img src="${MBOARD.CPATH}/images/bullet/view_dot_01.gif" alt="" border="0" align="absmiddle">
			&nbsp;<strong><mfmt:message bundle="board" key="title.board.queswrite" /> </strong>
			<c:choose>	
				<c:when test="${!empty(sessionScope.msession)}">
					<c:import url="${MBOARD.PATH}/inc/cmt_Auth.jsp"/>
				</c:when>
				<c:otherwise>
					<mfmt:message bundle="board" key="title.board.boardlogin" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</FORM>
	<c:if test="${fn:length(cmtLists) > 0}">
		<tr>
			<td><TABLE cellspacing="0" cellpadding="2" border=0 class="bbs_table" width="100%">
				<tr>
					<td class="bbs_view_top_th" width="100"><mfmt:message bundle="board" key="label.writer"/></td>
					<td class="bbs_view_top_th" width="450"><mfmt:message bundle="board" key="label.title"/></td>
					<td class="bbs_view_top_th" width="120"><mfmt:message bundle="board" key="label.regdt"/></td>
				</tr>
			<c:forEach items="${cmtLists}" var="data">
				<tr >
					<td class="bbs_view_th" width="100"><img src="${MBOARD.CPATH}/images/icon/${data.c_opnion}.gif" align="absmiddle" border="0">${data.wname}</td>
					<td class="bbs_view_td" >${mhtml:nl2br(data.c_memo)}
					<c:if test="${!empty(sessionScope.msession)}">
						<c:if test="${ ( data.usn == sessionScope.msession.usn) ||
									(fn:indexOf(sessionScope.msession.utype, 'A') >= 0)  ||
									(MBOARD.board.admin_usn == sessionScope.msession.usn) }">
						<img src="${MBOARD.CPATH}/images/button/cmt_delete.gif" onClick="cmtDelete('${data.c_id}')" align="absmiddle" border=0 style="cursor:pointer;">
						</c:if>
					</c:if>
					</td>
					<td class="bbs_view_td" align="center" width="120"><fmt:formatDate value="${data.c_date}" type="both" dateStyle="medium" /></td>
				</tr>
			</c:forEach>
				</table></td>
			</tr>			
	</c:if>
	</table>
