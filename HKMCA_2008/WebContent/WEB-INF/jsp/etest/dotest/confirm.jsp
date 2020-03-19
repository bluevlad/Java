<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
    <tr>
        <td align="center">
            <br><br>
			<mfmt:message key="script.exm.start" bundle="etest.common"/><br>
			<br>
            <c:url var="url" value="${controlAction}">
                <c:param name="cmd" value="test"/>
			    <c:param name="start" value="T"/>
			    <c:param name="exmid" value="${param.exmid}"/>
			</c:url>
			<a href='<c:out value="${url}" escapeXml="false"/>'><b>[<mfmt:message key="etest.getque" bundle="button"/>]</b></a>
        </td>
    </tr>
</table>