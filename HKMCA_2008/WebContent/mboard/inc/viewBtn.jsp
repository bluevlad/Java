<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="E"/>
    <c:if test="${idx >= 0 }">
        <mf:button onclick="go_edit()" bundle="button" key="edit" icon="icon_edit" />
    </c:if>
${MBOARD.BTN_AUTH}:<mh:out value="${MBOARD.BTN_AUTH}"/>
<mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="D"/>
    <c:if test="${!empty(sessionScope.msession) && idx > -1 }">
        <mf:button onclick="go_delete()" bundle="button" key="delete" icon="icon_delete" />
    </c:if>
    <c:if test="${empty(sessionScope.msession)}">
        <mf:button onclick="showfrmPasswd(this)" bundle="button" key="delete" icon="icon_delete" />
    </c:if>

<mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="R"/>
    <c:if test="${!empty(sessionScope.msession) && idx > -1 && data.is_notice != 'T'}">
        <mf:button onclick="go_reply()" bundle="button" key="reply" />
    </c:if>

<!-- div id="divDelete" style="display: none; position: absolute;">
<form action="" name="frmPasswd" id="frmPasswd" onSubmit="false">
<table width="250" border="0" cellspacing="2" cellpadding="2">
    <tr>
 	  <td align="center"><mfmt:message bundle="board" key="title.board.passexm" /></td>
    </tr>
    <tr>
        <td><input type="password" name="passwd" size="10" maxlength="10" required>&nbsp;
	    <mf:button onclick="goChkDel()" bundle="button" key="delete" />
        <mf:button bundle="button" key="cancel" onclick="hidefrmPasswd()" /></td>
    </tr>
</table>
</form>
</div-->