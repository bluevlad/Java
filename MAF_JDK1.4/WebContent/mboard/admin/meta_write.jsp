<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="miraenet.app.mboard.beans.*" %>
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
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function goWrite(){
	if(validate(document.frm)) {
		document.frm.cmd.value="<c:out value='${mcmd}'/>";
		document.frm.submit();
	}
}
function goBBSDelete() {
	if(confirm('<mfmt:message bundle="${m}" key="title.board.script" />')) {
		document.frm.cmd.value="delete";
		document.frm.LISTOP.value = "<c:out value='${dellistOpStr}'/>";
		document.frm.submit();
	}
}
//--> 
</script>
<table width="100%" cellspacing="0" border="0" cellpadding="2" class="view">
	<form name="frm" method="post" action='<mh:out value="${control_action}"/>' onSubmit="return validate(this)">
		<mf:input type="hidden" name="LISTOP" value="${listOp.serializeUrl}"/>
		<mf:input type="hidden" name="cmd" value="update_act"/>
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
				<td class="view"><mf:select name="is_use" codeGroup="TF" curValue="${board.is_use}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.heusgotype" /></th>
				<td class="view"><mf:select name="fl_waste" codeGroup="TF" curValue="${board.fl_waste}"/></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.user" /></th>
				<td class="view"><mf:select name="fl_reply" codeGroup="TF" curValue="${board.fl_reply}"/></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.userment" /></th>
				<td class="view"><mf:select name="fl_comment" codeGroup="TF" curValue="${board.fl_comment}"/></td>
			</tr>		
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.htmluse" /></th>
				<td class="view"><mf:select name="fl_html" codeGroup="TF" curValue="${board.fl_html}"/></td>
				<th align="center" class="view">MyPage</th>
				<td class="view"><mf:select name="ext_usersel" codeGroup="TF" curValue="${board.ext_usersel}"/></td>
			</tr>		
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.cate" /></th>
				<td class="view"><mf:select name="fl_category" codeGroup="TF" curValue="${board.fl_category}"/></td>
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
				<td class="view"><%=printSelect("grant_list",board.getGrant_list())%></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_view" /></th>
				<td class="view"><%=printSelect("grant_view",board.getGrant_view())%></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_write" /></th>
				<td class="view"><%=printSelect("grant_write",board.getGrant_write())%></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_comment" /></th>
				<td class="view"><%=printSelect("grant_comment",board.getGrant_comment())%></td>
			</tr>			
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_reply" /></th>
				<td class="view"><%=printSelect("grant_reply",board.getGrant_reply())%></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_delete" /></th>
				<td class="view"><%=printSelect("grant_delete",board.getGrant_delete())%></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_html" /></th>
				<td class="view"><%=printSelect("grant_html",board.getGrant_html())%></td>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_notice" /></th>
				<td class="view"><%=printSelect("grant_notice",board.getGrant_notice())%></td>
			</tr>
			<tr>
				<th align="center" class="view"><mfmt:message bundle="${m}" key="title.grant_secret" /></th>
				<td class="view"><%=printSelect("grant_secret",board.getGrant_secret())%></td>
			</tr>
			</table></td>
	</tr>	
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.boardtype" /></th>
		<td class="view">
			<select name="fl_board_type">
					<OPTION value="normal" ${(board.fl_board_type == 'normal')?'selected':''}><mfmt:message bundle="${m}" key="title.board.board" /></OPTION>
					<OPTION value="data" ${(board.fl_board_type == 'data')?'selected':''}><mfmt:message bundle="${m}" key="title.board.data" /></OPTION>
					<OPTION value="photo" ${(board.fl_board_type == 'photo')?'selected':''}><mfmt:message bundle="${m}" key="title.board.picboard" /></OPTION>
					<OPTION value="photonews" ${(board.fl_board_type == 'photonews')?'selected':''}><mfmt:message bundle="${m}" key="title.board.picnewboard" /></OPTION>
			</select>
			[<c:out value="${board.fl_board_type}"/>]
		</td>
	</tr>
 
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.datejijun" /></th>
		<td class="view">
			<INPUT type="radio" value="S" name="fl_modify_date"><mfmt:message bundle="${m}" key="title.board.sysdate" />&nbsp;&nbsp;
			<INPUT type="radio" value="U" name="fl_modify_date"><mfmt:message bundle="${m}" key="title.board.syadatesy" />		
		</td>
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
			<mfmt:message bundle="${m}" key="title.board.now" /> : ${board.total_attach_size} KB</td>
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
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.club_id" /></th>
		<td class="view"><mf:input type="text" name="club_id" size="20" maxlength="50" hname="Club ID" value='${board.club_id}'/></td>
	</tr>	
	<tr>
		<th align="center" class="view"><mfmt:message bundle="${m}" key="title.board.leccode" /></th>
		<td class="view"><mf:input type="text" name="leccode" size="20" maxlength="50" hname="Lecture Code" value='${board.leccode}'/></td>
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
	</form>
</table>

<table width="100%" border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td align="right">
			<a href="javascript:goBBSDelete();">Delete</a> 
			<a href="javascript:goWrite();">Save</a> 
            <a href="<c:out value='${control_action}'/>?LISTOP=<c:out value='${listOp.serializeUrl}'/>">fo List</a> 
		</td>
	</tr>
</table>
<%!
private String printSelect(String nm, int curval) {
	
	StringBuffer sRv = new StringBuffer(20);
	sRv.append("<select name='");
	sRv.append(nm);
	sRv.append("'>");
	for(int i = 1; i <= 10; i++) {
		sRv.append("<option value='");
		sRv.append(i + "' ");
		if(curval == i) {
			sRv.append(" SELECTED ");
		}
		sRv.append(">");
		sRv.append(i + "");
		sRv.append("</option>");
	}
	sRv.append("</select>");
	return sRv.toString();
}
%>