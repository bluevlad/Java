<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.web.mvc.view.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    BaseHttpSession sessionBean = MySession.getSessionBean(request, response);

    final String sql = " SELECT role_id, role_name"   +
        "   FROM maf_role"   +
        "  order by role_name asc"  ;
    MdbDriver oDb = null;
    try {
        oDb = Mdb.getMdbDriver();
        request.setAttribute("list", oDb.selector(Map.class, sql));
    } finally {
        try {oDb.close();} catch (Exception ex) {}
        oDb = null;
    }
    response.setContentType(JsonViewer.CONTENTS_TYPE);
%>
[
    <%@page import="maf.web.mvc.view.JsonViewer"%>
<c:forEach var="item" items="${list}" varStatus="status">
        {'role':'<c:out value="${item.role_id}"/>','name':'<c:out value="${item.role_name}"/>'}<c:if test="${!status.last}">,</c:if>
    </c:forEach>
]