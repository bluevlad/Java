<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<jsp:include page="_view.jsp"/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list ">
    <col width="200" />
    <col width="*" />
    <col width="100" />
    <col width="100" />
    <thead>
        <tr>
            <th>Photo</th>
            <th><mf:header name="photo_comment" />&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                <td class='center'><img src='<c:url value="/pds/org/${item.file_name}"/>' height="50"
                    onclick="javascript:viewPhoto('<mh:out value="${item.file_name}"/>');"
                    onError="this.src='<mh:out value="${CPATH}"/>/images/global/no_photo.gif'"></td>
                <td class='center'><mh:out value="${item.photo_comment}" td="true" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
