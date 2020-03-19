<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <jsp:include  page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
    <c:catch var="error">
        <c:import url="${MAF_INFO.file}" />
    </c:catch>
    <c:if test="${!empty error}">
        <mh:out value="${error}" td="true"/>
    </c:if>
</body>
</html>