<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<jsp:include page="_view.jsp"/>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="false"
    rowAlternateClass="alternateRow">
   <col width="20" />
   <col width="80" />
   <col width="80" />
   <col width="100" />
   <col width="150" />
    <col width="*" />
    <col width="50" />
    <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Type</th>
            <th>region</th>
            <th>&nbsp;</th>
            
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                
                <td class="center"><mh:out value="${status.count}" /></td>
                <td class="center"><a href='<c:url value="${controlaction}">
                        <c:param name="cmd" value="view"/>
                        <c:param name="org_cd" value="${item.org_cd}"/>
                        </c:url>'><mh:out value="${item.org_name}" td="true"/></a></td>
                <td class="center"><mh:out value="${item.org_type}" td="true"/></td>
                <td class="center"><mh:out value="${item.region}" td="true"/></td>
                
                <td>&nbsp;</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
