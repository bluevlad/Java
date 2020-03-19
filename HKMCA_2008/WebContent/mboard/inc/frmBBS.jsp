<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<mf:form method="post" action="${controlaction}" name="frmBBS" id="frmBBS">
	<mf:input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}"/>
	<mf:input type="hidden" name="v_key" value="${v_key}"/>
	<mf:input type="hidden" name="v_srch" value="${v_srch}"/>
	<mf:input type="hidden" name="v_status" value="${v_status}"/>
	<mf:input type="hidden" name="cmd" value=""/>
	<mf:input type="hidden" name="v_page" value=""/>
	<mf:input type="hidden" name="bid" value="${MBOARD.bid}"/>
    <mf:input type="hidden" name="passwd" value=""/>
</mf:form>