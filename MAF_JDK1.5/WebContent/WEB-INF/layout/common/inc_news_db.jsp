<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib uri="/WEB-INF/tld/mi-board.tld" prefix="mb" %>
<%@ taglib uri="/WEB-INF/tld/mi-fmt.tld" prefix="mfmt" %>
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
<c:set var="items" value="${mb:getRecentBoardDatabyBID(param.bid, mhtml:nvl(param.cnt,5) )}"/>
<c:set var="title_byte" value="${mfmt:int(mhtml:nvl(param.title_byte, 35))}"/>
<c:forEach items="${items}" var="data"  varStatus="status">
	<c:url var="view" value="/board/view.do">
		<c:param name="bid" value="${data.bid}"/>
		<c:param name="c_index" value="${data.c_index}"/>
	</c:url>
	<tr>
		<td nowrap class="bbs_notice_title"><a href="${view}" title="${fn:escapeXml(data.c_subject)}">${fn:escapeXml(mhtml:getByteCut(data.c_subject,title_byte))}</a> 
		<c:if test="${mdate:betweenHour(data.c_date)<24}">
				&nbsp;<img src="${CPATH}/mboard/images/icon/notice_new.gif" alt="" border="0" align="absmiddle">
				</c:if></td>
		
		<td nowrap align="right"><span class="date_small">${mdate:format(data.c_date,'MM.dd')}</span></td>
	</tr>
</c:forEach>
<c:if test="${empty(items)}">
	<tr>
		<td>등록된 게시물이 없습니다.</td>
	</tr>
</c:if>
</table>