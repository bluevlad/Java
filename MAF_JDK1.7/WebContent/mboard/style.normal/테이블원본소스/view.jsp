<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<fmt:setBundle var = "m" basename = "resources.board"/>
<script src="${CPATH}/js/lib.validate.js"></script>
<script language="JavaScript" src="${MBOARD.CPATH}/js/view.js" type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
	var urlname = "/ecampus/board/viewimage.do?bid=" + bid + 
				"&c_index=" + c_index +
				"&seq="+seq <c:if test="${!empty(course)}">+ "&cor_cd=${course.cor_cd}"</c:if>;
	browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
	browsing_window.focus();
}
</script>
<link href="${MBOARD.CPATH}/css/board_01.css" rel="stylesheet" type="text/css"></link>
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="bbs_table">
	<tr>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="bbs_table">
				<tr >
					<td class="bbs_view_top_th" width="80" height="25"><fmt:message bundle="${m}" key="label.title"/></td>
					<td class="bbs_view_top_td" colspan="5"> 
					${mhtml:capsule('[', data.c_category,'] ')} 
					${data.c_subject}</td>
				</tr>
				<tr>
					<td class="bbs_view_th" width="40"><fmt:message bundle="${m}" key="label.writer"/></td>
					<td class="bbs_view_td" width="276">${data.wname}</td>
					<td class="bbs_view_th" ><fmt:message bundle="${m}" key="label.view"/> : <span class="bbs_view_top_text">${data.c_visit}&nbsp;</span></td>
					<td class="bbs_view_th" >&nbsp;&nbsp;<fmt:message bundle="${m}" key="label.regdt"/> : 
						<span class="bbs_view_top_text"><fmt:formatDate value="${data.c_date}" type="both" dateStyle="medium" /></span></td>
				</tr>
				<tr>
					<td class="bbs_view_content" colspan="6">
						<div style="width: 565px; overflow: auto;">
							<style>
								p {margin-top:0px;margin-bottom:0px;};
							</style>
							${data.c_content}
						</div>
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
<!-- 첨부파일 -->	
<c:if test="${!empty(attItems)}">
	<tr><td><c:import url="${MBOARD.PATH}/inc/attach.jsp"/></td></tr>
</c:if>
<!--  버튼 -->	
	<tr>
		<td align="right" style="padding-right:20px; padding-top:10px">
			<c:import url="${MBOARD.PATH}/inc/viewBtn.jsp"/>
			<a href="javascript:go_list();"><img src="${MBOARD.CPATH}/images/button/list.gif" alt="" border="0" align="absmiddle"></a></td>
	</tr>
<c:if test="${MBOARD.board.fl_comment == 'T'}">
	<tr><td><c:import url="${MBOARD.PATH}/inc/cmt.jsp"/></td></tr>
</c:if>
	<tr height=30><td></td></tr>
	<tr>
		<td>
			<img src="${MBOARD.CPATH}/images/bullet/view_dot_01.gif" alt="" border="0" align="absmiddle">&nbsp;<strong><fmt:message bundle="${m}" key="title.board.prenext" /> </strong>
			<TABLE cellspacing="0" cellpadding="2" border=0 class="bbs_table" width="100%">
				<tr >
					<td class="bbs_view_top_th" align="center" width="60">#</td>
					<td class="bbs_view_top_th" align="center" width="350"><fmt:message bundle="${m}" key="label.title"/></td>
					<td class="bbs_view_top_th" align="center" ><fmt:message bundle="${m}" key="label.writer"/></td>
					<td class="bbs_view_top_th" align="center" ><fmt:message bundle="${m}" key="label.regdt"/></td>
				</tr>
				<c:forEach items="${refList}" var="data">
				<c:url var="url" value="view.do">
					<c:param name="c_index" value="${data.c_index}" />
					<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
					<c:if test="${!empty(course)}">
						<c:param name="cor_cd" value="${course.cor_cd}" />
					</c:if>
				</c:url>
				<c:set var="st" value="${data.prevnext == 'C'?'bbs_td_cur':'bbs_td_cmt'}" />
				<tr >
					<td class="${st}" align="center">${data.c_index}</td>
					<td class="${st}" >
						<c:forEach var="i" begin="1" end="${data.c_level}">&nbsp;&nbsp;</c:forEach>
						<a href="${url}">
						${mhtml:capsule('[', data.c_category,'] ')} ${data.c_subject}
						<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
							(${data.c_ccnt})
						</c:if></a></td>
					<td class="${st}" align="center">${data.wname}</td>
					<td class="${st}" align="center"><fmt:formatDate value="${data.c_date}" type="both" dateStyle="short" /></td>
				</tr>
				</c:forEach>
		</table></td>
	</tr>
</table>
<c:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />



