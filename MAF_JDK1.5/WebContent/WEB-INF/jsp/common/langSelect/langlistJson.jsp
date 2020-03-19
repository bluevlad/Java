<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.web.mvc.view.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ page import="modules.etest.stat.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    BaseHttpSession sessionBean = MySession.getSessionBean(request, response);

    final String sql = " SELECT code,allnames, active_yn, NVL(local_name, CODE) local_name"   +
        "   FROM maf_lang_iso639_1"   +
        "  WHERE active_yn = 'Y'"   +
        "  order by code"  ;
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
        {'code':'<c:out value="${item.code}"/>','name':'<c:out value="${item.local_name}"/>'}<c:if test="${!status.last}">,</c:if>
    </c:forEach>
]

