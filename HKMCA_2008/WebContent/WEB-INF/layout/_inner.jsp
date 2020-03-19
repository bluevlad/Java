<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body  topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0">
<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="left" style="padding: 20px 10px 10px 15px;"><!-- 네비게이션 -->
            <c:import url="/WEB-INF/layout/lib/title.jsp">
                <c:param name="mid" value="${content.mid}"/>
             </c:import>
        </td>
    </tr>     
    <tr>
        <td style="padding: 10px 10px 10px 15px;" ><c:import url="${JMF_INFO.file}" /></td>
    </tr>
</table>
</body>
</html>
<jsp:include  page="/WEB-INF/layout/common/commonTailInfo.jsp" flush="true"/> 