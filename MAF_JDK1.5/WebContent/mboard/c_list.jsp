<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<link href='<c:out value="${MBOARD.CPATH}"/>/css/board_m00.css'
	rel="stylesheet" type="text/css"></link>
<script src='<c:url value="/js/lib.validate.js"/>' ></script>
<script language="JavaScript" src='<c:out value="${MBOARD.CPATH}"/>/js/list.js'	type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
	var urlname = '<c:url value="/board/viewimage.do"/>?bid=' + bid + 
				'&c_index=' + c_index +
				'&seq='+seq ;
	browsing_window = window.open(urlname, "imagewin",
			"top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
	browsing_window.focus();
}
</script>
<c:choose>
	<c:when test="${MBOARD.board.fl_board_type=='photonews'}">
		<c:set var="jsp" value="${MBOARD.PATH}/style.photonews/list.jsp" />
	</c:when>
	<c:when test="${MBOARD.board.fl_board_type=='photo'}">
		<c:set var="jsp" value="${MBOARD.PATH}/style.photo/list.jsp" />
	</c:when>
	<c:otherwise>
		<c:set var="jsp" value="${MBOARD.PATH}/style.normal/list.jsp" />
	</c:otherwise>
</c:choose>
<c:if test="${!empty(MBOARD.board.file_header)}">
	<BASE HREF="<mh:CPATH/>/user/board/">
	<c:import url="/user/board/${MBOARD.board.file_header}" />
</c:if>


<table width="95%" cellspacing="0" cellpadding="0" border="0">
	<!--  검색부  -->
	<tr>
		<td>
		<table width="100%" cellspacing="1" cellpadding="1" border="0">
			<tr>
				<td align="right">
				<mf:form method="post" action="${controlaction}"
					name="frmSrch" id="frmSrch" onSubmit="MBoard_Search();return false;">
				<table border="0" cellspacing="0" cellpadding="2">
					<tr>
						<td><select name="v_key" id="v_key"
							onchange="javascript:doSelcatKey(this);">
							<mf:option value="X" curValue="${v_key}" >-</mf:option>
							<mf:option value="SUBJECT" curValue="${v_key}" ><mfmt:message
								bundle="board" key="label.title" /></mf:option>
							<mf:option value="WNAME" curValue="${v_key}" ><mfmt:message
								bundle="board" key="label.writer" /></mf:option>
							<mf:option value="CATEGORY" curValue="${v_key}" ><mfmt:message
								bundle="board" key="title.board.burru" /></mf:option>
						</select></td>
						<td><mf:input type="text" name="v_srch" 
							value="${v_srch}" size="15" maxlength="40"/></td>
						<td><mf:button onclick="MBoard_Search()" bundle="button" key="search" /></td>
					</tr>
				</table>
				</mf:form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<!--  검색부  -->
	<!-- List -->
<c:choose>
	<c:when test="${MBOARD.board.fl_board_type=='photo'}">
	<tr>
		<td align="center">
		<table width=100% cellspacing="5" cellpadding="5" border="0">
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
			<col width="25%" />
			<tbody>
				<mh:import url="${jsp}" />
			</tbody>
		</table>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
		<td align="center">
		<table width=100% cellspacing="0" cellpadding="2" class="list"
			border="0">
			<col width="20" />
			<col width="500" />
			<col width="120" />
			<col width="80" />
			<col width="100" />
			<thead>
			<tr>
				<th class="list bl">&nbsp;#&nbsp;</th>
				<th class="list "><mfmt:message bundle="board" key="label.title" /></th>
				<th class="list "><mfmt:message bundle="board"
					key="label.writer" /></th>
				<th class="list"><mfmt:message bundle="board" key="label.view" /></th>
				<th class="list br"><mfmt:message bundle="board"
					key="label.regdt" /></th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${!empty(list)  || !empty(noticeList) }">
					<!-- Notice  -->
					<c:forEach items="${noticeList}" var="data" varStatus="iter">
						<c:url var="url" value="/board/view.do">
							<c:param name="c_index" value="${data.c_index}" />
							<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
							<c:if test="${!empty(course)}">
								<c:param name="extleccode" value="${course.extleccode}" />
							</c:if>
						</c:url>
						<!-- Notice -->
						
						<tr style="background-color:#f5f5f5;"
							onMouseover="this.style.backgroundColor='#EFF8F9'"
							onMouseout="this.style.backgroundColor='#f5f5f5'">
							<td class="list BL" align="center" nowrap><img
								src='<c:url value="/mboard/images/notice.gif"/>' alt="" border="0"></td>
							<td class="list" align="left"><mh:out value=" " repeate="${(data.c_level-1)*2}"/><c:if test="${data.c_level > 1}">
								<img src="<c:out value="${MBOARD.CPATH}"/>/images/re.gif"
									border="0">&nbsp;
				</c:if> <c:if test="${! empty data.c_category}">
								<a href="javascript:ShowOnly('category','${data.c_category}')">
                                <mh:capsule value="${data.c_category}" left="[" right="]"/></a>
							</c:if> <a href='<c:out value="${url}"/>'><mh:out value="${data.c_subject}" bytes="${60-data.c_level}"/></a> <c:if
								test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
					(${data.c_ccnt})
				</c:if><mh:betweenHour var="t" value="${data.c_date}"/><c:if test="${t<24}">
				&nbsp;<img
									src='<c:out value="${MBOARD.CPATH}"/>/images/icon/notice_new.gif'
									alt="" border="0" align="absmiddle">
							</c:if></td>
							<td class="list" align="center"><mh:out
								value="${data.wname}" td="true" /></td>
							<td class="list " align="center"><mh:out
								value="${data.c_visit}" td="true" /></td>
							<td class="list BR" align="center"><mh:out 
								value="${data.c_date}" format="fulldate" /></td>
						</tr>
					</c:forEach>
					<mh:import url="${jsp}" />

				</c:when>
				<c:otherwise>
					<tr>
						<td class="bbs_td" align=center colspan="15" width="100%"><br>
						<mfmt:message bundle="board" key="title.board.searchlist" /><br>
						&nbsp;</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>

		</table>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
	<!-- List -->
	<tr>
		<td align=center colspan="15"><table>
            <tr>
                <td><mh:import url="${MBOARD.PATH}/inc/navigator.jsp" /> <mh:indexOf var="t" value="${MBOARD.BTN_AUTH}" needle="W" /></td>
            	<c:if test="${t >= 0 }">
            		<td width="100" align="right"><mf:button onclick="MBoard_Write()" bundle="button" key="add" /></td>
            	</c:if>
            </tr></table>
		</td>
	</tr>
	
</table>
<mh:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />
<c:if test="${!empty(MBOARD.board.file_footer)}">
	<BASE HREF="${CPATH}/user/board/">
	<mh:import url="/user/board/${MBOARD.board.file_footer}" />
</c:if>

