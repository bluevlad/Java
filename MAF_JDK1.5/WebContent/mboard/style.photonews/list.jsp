<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<fmt:setBundle var = "m" basename = "resources.board"/>
<c:forEach items="${list}" var="data" varStatus="iter">
		<c:url var="url" value="${CPATH}/board/view.do">
		<c:param name="c_index" value="${data.c_index}" />
		<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
		<c:if test="${!empty(course)}">
			<c:param name="extleccode" value="${course.extleccode}" />
		</c:if>
		</c:url>
		<tr class="bbs_td" style="background-color:#ffffff;" 
			height="20" onMouseover="this.style.backgroundColor='#e0e0e0'" 
			onMouseout="this.style.backgroundColor='#ffffff'">
			<td class="bbs_td_id"  align="center" >
					<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/thumb/${mhtml:rawURLEncode(data.c_image)}"/>
		        <img src="${imgUrl}" border="0" 
						style="cursor: pointer;" hspace="5" vspace="5" border="0" 
						onClick="mboard_popup('${MBOARD.bid}', '${data.c_index}', 0)"
						onError="this.src='${MBOARD.CPATH}/images/no_image.gif'"></td>
				<td class="bbs_td" >
				${mhtml:strRepeat("&nbsp;", (data.c_level-1)*2)}
				<c:if test="${data.c_level > 1}">
					<img src="${MBOARD.CPATH}/images/re.gif">&nbsp;
				</c:if>
				<c:if test="${! empty data.c_category}">
					<a href="javascript:ShowOnly('category','${data.c_category}')">${mhtml:capsule('[', data.c_category,']')}</a>
				</c:if>
					<a href='${url}'>${mhtml:getByteCut(data.c_subject, 60-data.c_level)}</a>
				<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
					(${data.c_ccnt})
				</c:if>
				<c:if test="${mdate:betweenHour(data.c_date)<24}">
				&nbsp;<img src="${MBOARD.CPATH}/images/icon/notice_new.gif" alt="" width="25" height="13" border="0" align="middle">
				</c:if></td>
				<td class="bbs_td" align="center" ><a href="#" title="${data.userid}">${data.wname}</a></td>
				<td class="bbs_td_cmt" align="center" >${data.c_visit}</td>
				<td class="bbs_td_cmt" align="center"><fmt:formatDate value="${data.c_date}" type="date" dateStyle="medium" /></td>
			</tr>
</c:forEach>
