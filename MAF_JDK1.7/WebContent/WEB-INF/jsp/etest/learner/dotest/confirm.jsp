<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>


<table>

    <tr>
        <td><mfmt:message key="warning" bundle="etest.common" remark="%주의%"/><br>
1. <mfmt:message key="warning.01" bundle="etest.common" remark="%주의%"/><br>
2. <mfmt:message key="warning.02" bundle="etest.common" remark="%주의%"/><br>

        </td>
    </tr>
</table>
<br><br>
Choose Language : <br>
<c:forEach var="i" items="${setlangs}" varStatus="status" >
    <c:url var="url" value="${controlAction}">
        <c:param name="cmd" value="test"/>
        <c:param name="start" value="T"/>
        <c:param name="exmid" value="${param.exmid}"/>
        <c:param name="lang" value="${i.lang}"/>
    </c:url>
    &nbsp;&nbsp;&nbsp;<a href='<c:out value="${url}" escapeXml="false"/>'><c:out value="${i.allnames}" /><br></a>
</c:forEach>