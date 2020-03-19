<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<fmt:setBundle var = "m" basename = "resources.board"/>
<script language="JavaScript" src="${MBOARD.CPATH}/js/list.js" type="text/javascript"></script>
<link href="${MBOARD.CPATH}/css/board_01.css" rel="stylesheet" type="text/css"></link>
<table width="720" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td><table width="100%" cellspacing="1" cellpadding="1" class="bbs_table" border="0">
			<tr>
				<td align="right"><form action="${pageContext.request.requestURL}" name="frmSrch" id="frmSrch" onSubmit="Search();return false;">
					<table border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td><select name="v_category" onchange="javascript:doSelcat(this);">
								<option value=""><fmt:message bundle="${m}" key="title.board.burrusearch"/></option>
							<c:forTokens var="cat" items="${MBOARD.board.category}" delims="," >
								<option value='${cat}' ${v_srch == cat?'selected':''}>${cat}</option>
							</c:forTokens>
							</select>
							<select name="v_key" id="v_key" onchange="javascript:doSelcatKey(this);">">
								<option value="X" ${v_key == 'X' ? 'selected' :''}>All</option>
								<option value="SUBJECT" ${v_key == 'SUBJECT' ? 'selected' :''}><fmt:message bundle="${m}" key="label.title"/></option>
								<option value="WNAME" ${v_key == 'WNAME' ? 'selected' :''}><fmt:message bundle="${m}" key="label.writer"/></option>
								<option value="CATEGORY" ${v_key == 'CATEGORY' ? 'selected' :''}><fmt:message bundle="${m}" key="title.board.burru"/></option>
							</select></td>
						<td><input type="text" name="v_srch" id="v_srch" value="${v_srch}" size="15" maxlength="40"></td>
						<td><img src="${MBOARD.CPATH}/images/button/search.gif" alt="" border="0" onClick="javascript:Search();"></td>
					</tr>
				</table></form></td>
			</tr>
		</table></td>
	</tr>
	<tr>
	<td align="center"><table width=100% cellspacing="0" cellpadding="0" class="bbs_table" border="0">
		<tr height="25">
			<th bgcolor="#666666" width="50" >#</th>
			<th class="bbs_th" width="550"><fmt:message bundle="${m}" key="label.title"/></th>
			<th class="bbs_th" width="120"><fmt:message bundle="${m}" key="label.writer"/></th>
			<th class="bbs_th" width="45"><fmt:message bundle="${m}" key="label.view"/></th>
			<th class="bbs_th" width="100"><fmt:message bundle="${m}" key="label.regdt"/></th>
		</tr>
<c:choose>	
	<c:when test="${fn:length(list) > 0 }">
		<c:forEach items="${list}" var="data">
			<c:url var="url" value="view.do">
				<c:param name="c_index" value="${data.c_index}" />
				<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
				<c:if test="${!empty(course)}">
					<c:param name="cor_cd" value="${course.cor_cd}" />
				</c:if>
			</c:url>
			<tr class="bbs_td" height="20">
				<td class="bbs_td_id"  align="center" >${data.c_index}</td>
				<td class="bbs_td">
				${mhtml:strRepeat("&nbsp;", data.c_level*2)}
				<c:if test="${data.c_level > 1}">
					<img src="${MBOARD.CPATH}/images/re.gif">&nbsp;
				</c:if>
				<a href='${url}'>${mhtml:capsule('[', data.c_category,'] ')} ${mhtml:getByteCut(data.c_subject, 60-data.c_level)}</a>
				<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
					(${data.c_ccnt})
				</c:if>
				<c:if test="${mdate:betweenHour(data.c_date)<24}">
				&nbsp;<img src="${MBOARD.CPATH}/images/icon/notice_new.gif" alt="" width="25" height="13" border="0" align="middle">
				</c:if></td>
				<td class="bbs_td" align="center" >${data.wname}</td>
				<td class="bbs_td_cmt" align="center" >${data.c_visit}</td>
				<td class="bbs_td_cmt" align="center"><fmt:formatDate value="${data.c_date}" type="date" dateStyle="medium" /></td>
			</tr>
		</c:forEach>
			<tr>
				<td align=center colspan="15"><br><c:import url="${MBOARD.PATH}/inc/navigator.jsp"/></td>
			</tr>	
	</c:when>
	<c:otherwise>
			<tr>
				<td class="bbs_td" align=center colspan="15"><br><fmt:message bundle="${m}" key="title.board.searchlist" /><br>&nbsp;</td>
			</tr>	
	</c:otherwise>
</c:choose>		
<c:if test="${fn:indexOf(MBOARD.BTN_AUTH, 'W') >= 0 }">
		<tr>
			<td><br><a href="javascript:Write();"><img src="${MBOARD.CPATH}/images/button/regist.gif" alt=""  border="0"></a> </td>
		</tr>
</c:if>
	</table></td>
	</tr>
</table>
<c:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />

