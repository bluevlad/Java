<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%@ page import="java.util.*" %>
<%@ page import="maf.*"%>
		<!-- 일반글 -->
<c:set var="mList" value="${getMatrix(list,4)}"/>		
<c:forEach items="${mList}" var="mData" varStatus="iter">
	<tr class="bbs_td" style="background-color:#ffffff;" height="10" ><td colspan="15"></td></tr>
	<tr style="background-color:#ffffff;" colspan="15">
		<td colspan="15"><table width="100%" border="0" cellspacing="2" cellpadding="0">
			<tr>
		<c:forEach items="${mData}" var="data" varStatus="iter">
				<c:if test="${empty(data)}">
					<td width="25%" align="center">&nbsp;</td>
				</c:if>
				<c:if test="${!empty(data)}">
					<td  width="25%" valign="top" align="center"><table border=0 cellspacing=0 cellpadding=2 width="124" >
						<tr height="104">
							<c:url var="url" value="${CPATH}/board/view.do">
								<c:param name="c_index" value="${data.c_index}" />
								<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
								<c:if test="${!empty(course)}">
									<c:param name="extleccode" value="${course.extleccode}" />
								</c:if>
							</c:url>
							<td  align="center"  background="${MBOARD.CPATH}/images/photo_bg.gif"  >
								<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/thumb/${mhtml:rawURLEncode(data.c_image)}"/>
					        <img src="${imgUrl}" border="0" 
									style="cursor: pointer;" hspace="5" vspace="5" border="0" 
									onClick="mboard_popup('${MBOARD.bid}', '${data.c_index}', 0)"
									onError="this.src='${MBOARD.CPATH}/images/no_image.gif'"/>
							</td>
					</tr>
					<tr>
						<td style="LETTER-SPACING: -1px" align="center">
							<c:if test="${! empty data.c_category}">
								<a href="javascript:ShowOnly('category','${data.c_category}')">${mhtml:capsule('[', data.c_category,']')}</a>
							</c:if>
								<a href='${url}'>${mhtml:getByteCut(data.c_subject, 40-data.c_level)}</a>
							<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
								(${data.c_ccnt})
							</c:if>
							<c:if test="${mdate:betweenHour(data.c_date)<24}">
							&nbsp;<img src="${MBOARD.CPATH}/images/icon/notice_new.gif" alt="" width="25" height="13" border="0" align="middle">
							</c:if>
						</td>
					</tr>
				</table></td>
			</c:if>
		</c:forEach>
		</tr>
		</table></td>
	</tr>
</c:forEach>		