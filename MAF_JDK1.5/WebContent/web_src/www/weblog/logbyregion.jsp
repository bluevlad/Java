<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%
    if (request.getParameter("nation")!=null ) {
%>
    <jsp:include page="logbyregion_nation.jsp"/>     
<%
    } else {
%>
    <jsp:include page="logbyregion_region.jsp"/>              
<%
    }
%>
