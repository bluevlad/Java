<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<jsp:include page="_view.jsp"/>

<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="false">
   <col width="20" />
   <col width="*" />
   <col width="80" />
   <col width="100" />
   <col width="150" />
    <col width="150" />
    <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Position</th>
            <th>Tel</th>
            <th>E-mail</th>
            <th>Section</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                <td class="center"><mh:out value="${status.count}" /></td>
                <td class="center" nowrap><mh:out value="${item.nm}" td="true" default="&nbsp;"/></td>
                <td class="center"><mh:out value="${item.pos_cd}" td="true" default="&nbsp;"/></td>
                <td class="center"><mh:out value="${item.tel}" td="true" default="&nbsp;"/></td>
                <td class="center"><mh:out value="${item.email}" td="true" default="&nbsp;"/></td>
                <td class="center"><mh:out value="${item.mst_section}" codeGroup="SECTION" td="true"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
