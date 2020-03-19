<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:forEach items="${list}" var="data" varStatus="iter">
		<c:url var="url" value="/board/view.do">
		<c:param name="c_index" value="${data.c_index}" />
		<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
		<c:if test="${!empty(course)}">
			<c:param name="extleccode" value="${course.extleccode}" />
		</c:if>
		</c:url>
		<tr  style="background-color:#ffffff;" 
			 >
			<td  align="center" ><mh:out value="${data.c_index}" td="true"/></td>
			<td  align="left">
				<mh:out value=" " repeate="${(data.c_level-1)*2}"/>
				<c:if test="${data.c_level > 1}">
					<img src='<c:out value="${MBOARD.CPATH}"/>/images/re.gif' border="0">&nbsp;
				</c:if>
				<c:if test="${! empty data.c_category}">
					<a href="javascript:ShowOnly('category','<c:out value="${data.c_category}"/>')"><mh:capsule value="${data.c_category}" left="[" right="]"/></a>
				</c:if>
					<a href='<c:out value="${url}"/>'><mh:out value="${data.c_subject}" bytes="${60-data.c_level}"/></a>
				<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
					(${data.c_ccnt})
				</c:if>
				<mh:betweenHour  var="t" value="${data.c_date}"></mh:betweenHour>
				<c:if test="${t<24}">
				&nbsp;<img src='<c:out value="${MBOARD.CPATH}"/>/images/icon/notice_new.gif' alt="" width="25" height="13" border="0" valign="absmiddle">
				</c:if></td>
			<td  align="center" ><mh:out value="${data.wname}" td="true"/></td>
			<td  align="center" ><mh:out value="${data.c_visit}" td="true"/></td>
			<td  align="center"><mh:out value="${data.c_date}" format="fulldate" /></td>
			</tr>
</c:forEach>
