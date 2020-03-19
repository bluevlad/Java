<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <jsp:include  page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<!-- CLOSE BAR START -->
<table width="350"  border="0" cellspacing="0" cellpadding="0" class="popup">
    <tr>
        <td>
            <c:catch var="error">
                <c:import url="${MAF_INFO.file}" />
		    </c:catch>
		    <c:if test="${!empty error}">
		        <mh:out value="${error}" td="true"/>
		    </c:if>
       </td>
    </tr>
    <tr>
        <td>
            <!-- CLOSE BAR START -->
			<table width="340"  border="0" cellspacing="0" cellpadding="0" style="margin-top:20px" class="no_border">
			    <tr>
			        <td align="center" height="30"><img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/close.gif" onclick="window.close();" style="cursor: pointer;"  alt="닫기"></td>
			    </tr>
			</table>
            <!-- CLOSE BAR END -->
       </td>
    </tr>
</table>
</body>
</html>