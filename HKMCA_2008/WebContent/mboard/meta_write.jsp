<%@ page contentType="text/html; charset=utf-8"%>     
<%@ page import="modules.community.mboard.beans.*"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="m" value="board" />
<%
	MbbsMetaBean board = (MbbsMetaBean) request.getAttribute("board");
%>
<c:choose>
	<c:when test="${empty(board.bid)}">
		<c:set var="mcmd" value="insert"/>
	</c:when>
	<c:otherwise>
		<c:set var="mcmd" value="update"/>
	</c:otherwise>
</c:choose>

<SCRIPT LANGUAGE="JavaScript">
<!--
function goWrite(){
    var frm = getObject("mForm");
	if(validate(frm)) {
		frm.cmd.value="<c:out value='${mcmd}'/>";
		frm.submit();
	}
}
function goBBSDelete() {
	if(confirm('<mfmt:message bundle="${m}" key="title.board.script" />')) {
		document.mForm.cmd.value="delete";
		document.mForm.LISTOP.value = "<c:out value='${dellistOpStr}'/>";
		document.mForm.submit();
	}
}
function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}
//--> 
</script>
<mf:form name="mForm" method="post" action="${control_action}" onSubmit="return validate(this)">
<mf:input type="hidden" name="LISTOP" value="${listOp.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value="update_act"/>
<table width="100%" cellspacing="0" border="0" cellpadding="2" class="view">
	<tr>
		<th width="20%" align="center" class="view"><mfmt:message bundle="${m}" key="title.board.title" /></th>
		<td class="view">
		<c:choose>
			<c:when test="${empty(board.bid)}">
				<mf:input type="text" name="bid" size="32" maxlength="32" hname="Board ID"  value='${board.bid}' required="true"/>
			</c:when>
			<c:otherwise>
				<mf:input type="text" name="bid" size="32" maxlength="32" hname="Board ID"  value='${board.bid}' readonly="true"/>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.titlename" /></td>
		<td class="view"><mf:input type="text" name="subject" size="100" hname="Board Name" required="true" value='${board.subject}'/></td>
	</tr>
	<tr >
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.group" /></th >
		<td class="view">
			<select name="grp" id="sjt_cd">
        	<c:forEach var="grp" items="${groups}" >
        		<mf:option value="${grp.grp}" text="${grp.grp_desc}" />
        	</c:forEach>
        	</select>
			<!-- mf:select name="grp" items="${groups}" keyValue="grp" keyText="grp_desc" curValue="${board.grp}"/-->
		</td>
	</tr>
	<tr>
		<th class="view" align=center>OPTION</th>
		<td class="view"><table cellspacing=0 cellpadding=2 border=0>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.usetype" /></th>
				<td class="view"><mf:select name="is_use" codeGroup="ISUSE" curValue="${board.is_use}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.heusgotype" /></th>
				<td class="view"><mf:select name="fl_waste" codeGroup="ISUSE" curValue="${board.fl_waste}"/></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.user" /></th>
				<td class="view"><mf:select name="fl_reply" codeGroup="ISUSE" curValue="${board.fl_reply}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.userment" /></th>
				<td class="view"><mf:select name="fl_comment" codeGroup="ISUSE" curValue="${board.fl_comment}"/></td>
			</tr>		
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.htmluse" /></th>
				<td class="view"><mf:select name="fl_html" codeGroup="ISUSE" curValue="${board.fl_html}"/></td>
				<th align="center" class="view">MyPage</th>
				<td class="view"><mf:select name="ext_usersel" codeGroup="ISUSE" curValue="${board.ext_usersel}"/></td>
			</tr>		
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.cate" /></th>
				<td class="view"><mf:select name="fl_category" codeGroup="ISUSE" curValue="${board.fl_category}"/></td>
				<th align="center" class="view">&nbsp;</th>
				<td class="view">&nbsp;</td>
			</tr>				
			</table></td>
	</tr>
<tr>
		<th class="view" align=center>Auth</th>
		<td class="view"><table cellspacing=0 cellpadding=2 border=0>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_list" /></th>
				<td class="view"><mf:select name="grant_list" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_list}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_view" /></th>
				<td class="view"><mf:select name="grant_view" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_view}"/></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_write" /></th>
				<td class="view"><mf:select name="grant_write" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_write}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_comment" /></th>
				<td class="view"><mf:select name="grant_comment" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_comment}"/></td>
			</tr>			
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_reply" /></th>
				<td class="view"><mf:select name="grant_reply" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_reply}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_delete" /></th>
				<td class="view"><mf:select name="grant_delete" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_delete}"/></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_html" /></th>
				<td class="view"><mf:select name="grant_html" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_html}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_notice" /></th>
				<td class="view"><mf:select name="grant_notice" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_notice}"/></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_secret" /></th>
				<td class="view"><mf:select name="grant_secret" codeGroup="BBS.BRD_LEVEL" curValue="${board.grant_secret}"/></td>
			</tr>
			</table></td>
	</tr>	
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.boardtype" /></th>
		<td class="view"><mf:select name="fl_board_type" codeGroup="BBS.BRD_TYPE" curValue="${board.fl_board_type}"/></td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.category" /></th>
		<td class="view">
			<mf:input type="text" name="category" size="50" maxlength="200" hname="Category" value='${board.category}'/><br>
			<mfmt:message bundle="${m}" key="title.board.exm" />
		</td>
	</tr>	
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.filecount" /></th>
		<td class="view"><mf:input type="text" name="number_attach" size="10" maxlength="10" hname="file count" required="true" option="number" value='${board.number_attach}'/></td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.space" /></th>
		<td class="view"><mf:input type="text" name="max_attach_size" size="10" maxlength="10" hname="Max Attach Size" required="true" option="number" value='${board.max_attach_size}'/>Mega Byte</td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.top" /></th>
		<td class="view"><mf:input type="text" name="max_attach_limit" size="10" maxlength="10" hname="Max Attach Size" required="true" option="number" value='${board.max_attach_limit}'/>Mega Byte /
			<mfmt:message bundle="${m}" key="title.board.now" /> : <mh:out value="${board.total_attach_size}"/> KB</td>
	</tr>	
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.pagelist" /></th>
		<td class="view"><mf:input type="text" name="nrows" size="10" maxlength="10" hname="message in page" required="true" option="number" value='${board.nrows}'/>
		<mfmt:message bundle="${m}" key="title.board.pagelist.cmt" /> </td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.pgid" /></th>
		<td class="view"><mf:input type="text" name="pgid" size="20" maxlength="50" hname="Board ID" value='${board.pgid}'/></td>
	</tr>
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.usn" /></th>
		<td class="view"><mf:input type="text" name="admin_usn" size="20" maxlength="20" hname="Admin User" option="alphanum" value='${board.admin_usn}'/></td>
	</tr>
	<tr>
		<th align="center" class="view">Header file</th>
		<td class="view"><mf:input type="text" name="file_header" size="50" maxlength="100" hname="Header file"  value='${board.file_header}'/></td>
	</tr>
	<tr>
		<th align="center" class="view">Footer file</th>
		<td class="view"><mf:input type="text" name="file_footer" size="50" maxlength="100" hname="Footer file" value='${board.file_footer}'/></td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
	<tr>
		<td align="right">
            <mf:button onclick="javascript:goWrite()" bundle="button" key="save" icon="icon_save" />
            <mf:button onclick="javascript:goBBSDelete()" bundle="button" key="delete" icon="icon_delete" />
            <mf:button onclick="javascript:goList()" bundle="button" key="list" icon="icon_list" />
		</td>
	</tr>
</table>
</mf:form>
