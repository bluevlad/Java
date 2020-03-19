<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="m" value="board" />

<script language="javascript">
<!--
function doUpdate() {
    var frm = getObject("myform");
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "update";
                </c:when>
                <c:when test="${param.cmd == 'add'}">
                    frm.cmd.value = "insert";
                </c:when>
                <c:otherwise>
                    frm.cmd.value = "insert";
                </c:otherwise>
            </c:choose>
            frm.submit();
        }
    } else {
        alert ("form[" + frmName + "] is invalid");
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

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
    <col width="20%" />
    <col width="30%" />
    <col width="20%" />
    <col width="30%" />
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.title" /></th>
        <td colspan="3">
	        <c:choose>
	            <c:when test="${empty(item.bid)}">
	                <mf:input type="text" name="bid" cssStyle="width:150px" hname="Board ID"  value='${item.bid}' required="true"/>
	            </c:when>
	            <c:otherwise>
	                <mf:input type="text" name="bid" cssStyle="width:150px" hname="Board ID"  value='${item.bid}' readonly="true"/>
	            </c:otherwise>
	        </c:choose>
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.titlename" /></th>
        <td colspan="3"><mf:input type="text" name="subject" cssStyle="width:98%" hname="Board Name" required="true" value='${item.subject}'/></td>
    </tr>
    <tr>
        <th>OPTION</th>
        <td colspan="3">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
			    <col width="20%" />
			    <col width="30%" />
                <col width="20%" />
                <col width="30%" />
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.board.usetype" /></th>
	                <td><mf:select name="is_use" codeGroup="ISUSE" curValue="${item.is_use}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.board.heusgotype" /></th>
	                <td><mf:select name="fl_waste" codeGroup="ISUSE" curValue="${item.fl_waste}"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.board.user" /></th>
	                <td><mf:select name="fl_reply" codeGroup="ISUSE" curValue="${item.fl_reply}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.board.userment" /></th>
	                <td><mf:select name="fl_comment" codeGroup="ISUSE" curValue="${item.fl_comment}"/></td>
	            </tr>       
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.board.htmluse" /></th>
	                <td><mf:select name="fl_html" codeGroup="ISUSE" curValue="${item.fl_html}"/></td>
	                <th>MyPage</th>
	                <td><mf:select name="ext_usersel" codeGroup="ISUSE" curValue="${item.ext_usersel}"/></td>
	            </tr>       
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.board.cate" /></th>
	                <td><mf:select name="fl_category" codeGroup="ISUSE" curValue="${item.fl_category}"/></td>
	                <th>&nbsp;</th>
	                <td>&nbsp;</td>
	            </tr>               
            </table>
        </td>
    </tr>
    <tr>
        <th>Auth</th>
        <td colspan="3">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
                <col width="20%" />
                <col width="30%" />
                <col width="20%" />
                <col width="30%" />
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.grant_list" /></th>
	                <td><mf:select name="grant_list" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_list}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.grant_view" /></th>
	                <td><mf:select name="grant_view" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_view}"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.grant_write" /></th>
	                <td><mf:select name="grant_write" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_write}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.grant_comment" /></th>
	                <td><mf:select name="grant_comment" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_comment}"/></td>
	            </tr>           
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.grant_reply" /></th>
	                <td><mf:select name="grant_reply" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_reply}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.grant_delete" /></th>
	                <td><mf:select name="grant_delete" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_delete}"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.grant_html" /></th>
	                <td><mf:select name="grant_html" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_html}"/></td>
	                <th><mfmt:message bundle="${m}" key="title.grant_notice" /></th>
	                <td><mf:select name="grant_notice" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_notice}"/></td>
	            </tr>
	            <tr>
	                <th><mfmt:message bundle="${m}" key="title.grant_secret" /></th>
	                <td><mf:select name="grant_secret" codeGroup="BBS.BRD_LEVEL" curValue="${item.grant_secret}"/></td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>
	            </tr>
            </table>
        </td>
    </tr>   
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.boardtype" /></th>
        <td><mf:select name="fl_board_type" codeGroup="BBS.BRD_TYPE" curValue="${item.fl_board_type}"/></td>
        <th><mfmt:message bundle="${m}" key="title.board.group" /></th>
        <td>
            <mf:select name="grp" items="${groups}" keyValue="grp" keyText="grp_desc" curValue="${item.grp}">
            </mf:select>
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.category" /></th>
        <td colspan="3">
            <mf:input type="text" name="category" cssStyle="width:150px" hname="Category" value='${item.category}'/>&nbsp;
            <mfmt:message bundle="${m}" key="title.board.exm" />
        </td>
    </tr>   
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.filecount" /></th>
        <td colspan="3"><mf:input type="text" name="number_attach" cssStyle="width:70px" hname="file count" required="true" option="number" value='${item.number_attach}'/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.space" /></th>
        <td colspan="3"><mf:input type="text" name="max_attach_size" cssStyle="width:70px" hname="Max Attach Size" required="true" option="number" value='${item.max_attach_size}'/>Mega Byte</td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.top" /></th>
        <td colspan="3">
            <mf:input type="text" name="max_attach_limit" cssStyle="width:70px" hname="Max Attach Size" required="true" option="number" value='${item.max_attach_limit}'/>Mega Byte /
            <mfmt:message bundle="${m}" key="title.board.now" /> : <jh:out value="${item.total_attach_size}"/> KB
        </td>
    </tr>   
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.pagelist" /></th>
        <td colspan="3">
	        <mf:input type="text" name="nrows" cssStyle="width:70px" hname="message in page" required="true" option="number" value='${item.nrows}'/>
	        <mfmt:message bundle="${m}" key="title.board.pagelist.cmt" />
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.pgid" /></th>
        <td colspan="3"><mf:input type="text" name="pgid" cssStyle="width:100px" hname="Board ID" value='${item.pgid}'/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.usn" /></th>
        <td colspan="3"><mf:input type="text" name="admin_usn" cssStyle="width:100px" hname="Admin User" option="alphanum" value='${item.admin_usn}'/></td>
    </tr>
    <tr>
        <th>Header file</th>
        <td colspan="3"><mf:input type="text" name="file_header" cssStyle="width:100px" hname="Header file"  value='${item.file_header}'/></td>
    </tr>
    <tr>
        <th>Footer file</th>
        <td colspan="3"><mf:input type="text" name="file_footer" cssStyle="width:100px" hname="Footer file" value='${item.file_footer}'/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save" />
        	<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>
</div>