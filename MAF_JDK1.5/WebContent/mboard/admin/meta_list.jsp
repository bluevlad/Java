<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<c:set var="m" value="board" />
<script>
	function go_search() {
		var frm = getObject("frmSrch");
		if(frm) {
			frm.submit();
		}
	}
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
        <mf:form action="${control_action}" name="frmSrch" id="frmSrch" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="2">
			<tr>
				<td><select name="v_grp">
						<option value="ALL"><mfmt:message bundle="${m}" key="title.board.allview" /></option>
						<c:forEach var="group" items="${groups}">
							<mf:option value="${group.grp}" curValue="${param.v_grp}" ><c:out value="${group.grp_desc}"/></mf:option>
						</c:forEach>
					</select>
					&nbsp;<mf:input type="text" name="v_srch" value="${param.v_srch}" size="15" maxlength="40"/>
					&nbsp;&nbsp;<mf:button onclick="javascript:go_search()" bundle="button" key="search" />
                </td>
				<td align="right" ><a href='<c:url value="${control_action}">
								<c:param name="cmd" value="add"/>
								<c:param name="LISTOP" value="${listOpStr}"/>
								</c:url>'>[Add]</a></td>
			</tr>
		</table></mf:form></div>
	</td>
</tr>
<tr>
    <td><div class="listContainer">
		<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " 
			enableAlternateRows="true" rowAlternateClass="alternateRow">
            <col width="50"  />
            <col width="100" />
            <col width="300" />
            <col width="30"/>
            <col width="20"/>
            <col width="200"/>
            <col width="150"/>
            <col width="150" "/>
            <col width="100"/>
            <thead>
            <tr >
                <th align='center'>GRP</th>
                <th align='center'>ID (<mfmt:message bundle="${m}" key="title.board.modify" />)</th>
                <th align='center'><mfmt:message bundle="${m}" key="title.board.titlename" /><br>(<mfmt:message bundle="${m}" key="title.board.listview" />)</th>
                <th align='center'><mfmt:message bundle="${m}" key="title.board.boardutype" /></th>
                <th align='center'><mfmt:message bundle="${m}" key="board.use" /></th>
                <th align='center'><mfmt:message bundle="${m}" key="title.board.gunhan" /><br>
[<mfmt:message bundle="${m}" key="title.board.writeboar" />|<mfmt:message bundle="${m}" key="title.board.reply" />|<mfmt:message bundle="${m}" key="title.board.injung" />|<mfmt:message bundle="${m}" key="title.board.coment" />]</th>	
                <th align='center'><mfmt:message bundle="${m}" key="title.board.countlist" /><br>(<mfmt:message bundle="${m}" key="title.board.heuso" />/<mfmt:message bundle="${m}" key="title.board.deletelist" />)</th>
                <th align='center'><mfmt:message bundle="${m}" key="title.board.allfilecoutn" /><br><mfmt:message bundle="${m}" key="title.board.size" /></th>
                <th align='center'>PGID</th>
            </tr>
            </thead>
			<c:forEach var="board" items="${boards}">
			<tr >
				<td align="center"><mh:out value="${board.grp}" td="true"/></td>
                <c:url var="link" value="${control_action}">
                    <c:param name="cmd" value="edit"/>
                    <c:param name="bid" value="${board.bid}"/>
                    <c:param name="LISTOP" value="${listOpStr}"/>
                </c:url>
				<td align="center"><a href='<c:out value="${link}"/>'><c:if test="${empty board.cor_nm}"><mh:out value="${board.bid}"/></c:if>
														   <c:if test="${!(empty board.cor_nm)}"><mh:out value="[${board.dpt_nm}]-[board.cor_nm}-${board.part_no}]"/></c:if></a></td>
				<td align="center"><mh:out value="${board.subject}" td="true"/></td>
				<td align="center"><mh:out value="${board.fl_board_type}" td="true"/></td>
				<td align="center"><mh:out value="${board.is_use}" td="true"/></td>
				<td align="center"><mh:out value="[${board.grant_list} | ${board.grant_write} |  ${board.fl_comment}]" td="true"/></td>
				<td align="right"><mh:out value="${board.cnt_t} / 	${board.cnt_w} /	${board.cnt_d}" td="true"/></td>
				<td align="right"><mh:out value="${board.total_attach_size}" td="true"/></td>					
				<td align="center"><mh:out value="${board.pgid}" td="true"/></td>
			</tr>
			</c:forEach>
	</table></td></tr>
<tr>
	<td align="center" bgcolor=white ></td>
</tr>
</table>
<form  name="frm">
	<input type="hidden" name="v_page" value="${v_page}">
	<input type="hidden" name="LISTOP" value="${listOpStr}">
</form>

<SCRIPT LANGUAGE="JavaScript">
<!--
function goPage(nPage, dummy) {
	document.frm.v_page.value = nPage;
	document.frm.submit();
}
//-->
</SCRIPT>