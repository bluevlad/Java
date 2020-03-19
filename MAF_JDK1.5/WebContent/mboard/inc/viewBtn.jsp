<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<fmt:setBundle var="m" basename="resources.board" />
    <mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="E"/>
    <c:if test="${idx >= 0 }">
        <mf:button onclick="go_edit()" bundle="button" key="edit" />
    </c:if>
    
    <mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="D"/>
    <c:if test="${!empty(sessionScope.msession) && idx > -1 }">
        <mf:button onclick="go_delete()" bundle="button" key="delete" />
    </c:if>
    
    <c:if test="${empty(sessionScope.msession) && idx > -1 }">
        <mf:button onclick="showfrmPasswd(this)" bundle="button" key="delete" />
    </c:if>
    
    <mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="R"/>
    <c:if test="${!empty(sessionScope.msession) && idx > -1 && data.is_notice != 'T'}">
        <mf:button onclick="go_reply()" bundle="button" key="reply" />
    </c:if>
    <div id="divDelete" style="display: none; width: 150; height: 50; position: absolute; ">
    <form action="" name="frmPasswd" id="frmPasswd" onSubmit="false">
    <table width="150" border="0" cellspacing="0" cellpadding="0" bgcolor="#F2D675">
    <tr>
    	<td colspan="3" align="center" style="font-size: 11px; font-family: inherit;"><fmt:message bundle="${m}" key="title.board.passexm" /></td>
    </tr>
    <tr>
    	<td>&nbsp;<input type="password" name="passwd" size="10" maxlength="10" required HNAME="암호"></td>
    	<td><img src='<c:out value="${MBOARD.CPATH}"/>/images/button/delete.gif' alt="" border="0" align="absmiddle" style="cursor:pointer;" onClick="goChkDel()"></td>
    	<td><img src='<c:out value="${MBOARD.CPATH}"/>/images/button/m_delete.gif' alt="" border="0" align="absmiddle" style="cursor:pointer;" onClick="hidefrmPasswd()">&nbsp;</td>
    </tr>
    </table></form>
    </div>

