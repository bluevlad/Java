<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<html>
<head>
<jsp:include page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0"  >
<div id="div_r1">
    <jsp:include page="/WEB-INF/layout/common/menu_top.jsp" flush="true"/> 
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td valign="top">
            <div id="div_contents_container">
                <div id="div_contents">
                    <c:catch var="error">
                        <c:import url="${MAF_INFO.file}" />
                    </c:catch>
                    <c:if test="${!empty error}">
                        <hr/>
                        Error:<mh:out value="${error}" nl2br="true"/>
                        <hr/>
                    </c:if>
                </div>
            </div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/common/commonTail.jsp" flush="true"/> 
</body>
</html>