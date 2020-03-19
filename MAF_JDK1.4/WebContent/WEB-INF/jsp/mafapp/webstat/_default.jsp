<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 
<c:choose>
	<c:when test="${param.cmd == 'firsthit'}">
		<c:set var="cur" value="${param.cmd}"/>
	</c:when>
	<c:when test="${param.cmd == 'login'}">
		<c:set var="cur" value="${param.cmd}"/>
	</c:when>
	<c:otherwise>
		<c:set var="cur" value="default"/>
	</c:otherwise>
</c:choose>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="left" style="padding: 20px 10px 10px 15px;"><!-- 네비게이션 -->
            <c:import url="/web_src/intranet/include/title.jsp">
                <c:param name="mid" value="${content.mid}"/>
             </c:import>
        </td>
    </tr> 
</table> 
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td><br><mf:form action="${controlaction}" method="get" name="search">
        <select name="yyyy">
        	<c:forTokens var="t" items="2006,2007,2008,2009,2010,2011,2012,2013,2014,2015" delims=",">
        		<mf:option value="${t}" curValue="${yyyy}" text="${t}"/>
        	</c:forTokens>
        </select>년 
        <select name="mm">
        <c:forTokens var="t" items="01,02,03,04,05,06,07,08,09,10,11,12" delims=",">
        		<mf:option value="${t}" curValue="${mm}"><c:out value="${t}"/></mf:option>
        	</c:forTokens>
        </select>월
        
        <mf:submit bundle="button" key="search" />
        </mf:form></td>
    <tr>
    <tr>
        <td align="center"><mh:import url="${MAF_INFO.file}" /> </td>
    </tr>

</table>    

</body>
</html>
<jsp:include  page="/layout/common/commonTailInfo.jsp" flush="true"/> 