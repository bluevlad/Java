<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld"%>
<%@ page import="java.net.InetAddress" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
<head> 
<META HTTP-EQUIV="Content-type"	CONTENT="text/html;charset=utf-8" />
<title> <%= InetAddress.getLocalHost().getHostName() %> JVM Status </title>
	<link href='<c:url value="/css/maf_common.css"/>' rel="stylesheet" type="text/css"></link>
	<script type="text/javascript" src='<c:url value="/js/sub.common.js"/>' ></script>
<style type="text/css">

	.tdblue {
		background-color: #0000ff;
		color: #ffffff;
		text-align: center;
	}
	.tdred {
		background-color: #ff0000;
		color: #ffffff;
		text-align: center;
	}
</style>
<script type="text/javascript">//<![CDATA[
var min = "00"
var sec = "0"

function timer(){

	if ((min < 10) && (min != "00")){
		dismin = "0" + min
	}
	else{
		dismin = min
	}

	dissec = (sec < 10) ? sec = "0" + sec : sec
	document.rrdform.counter.value = dismin + ":" + dissec

	if (sec < 59){
		sec++
	}
	else{
		sec = "0"
		min++
		if (min >= 5){
			document.rrdform.submit()
		}
	}

	window.setTimeout("timer()",1000) 
}


//]]></script>
</head>
<body onload="timer()">
CharacterEncoding : <%=request.getCharacterEncoding()%> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="header"/></c:url>'>HEADER</a> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="action"/></c:url>'>Action Configuration</a> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="resource"/></c:url>'>Resources</a> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="code"/></c:url>'>Code</a> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="requestmon"/></c:url>'>request Monitor</a> | 
<a href='<c:url value="${controlaction}"><c:param name="xcmd" value="programList"/></c:url>'>Program List</a><hr>
<table>
	<tr>
		<td valign="top">
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<th>MAF/J Pool Status</th>
				</tr>
				<tr>
					<td valign="top">
						<table  border="1" cellspacing="0" cellpadding="2">
							<tr>
								<th>parameter</th>
								<th>value</th>
							</tr>
						<c:forEach var="aKey" items="${mvcPoolStatus}">
							<tr>
								<td><c:out value="${aKey.key}" /></td>
								<td><c:forEach var="val" items="${aKey.value}">
								     <c:out value="${val}" /><br />
									</c:forEach>
								</td>
							</tr>
						</c:forEach>
						</table>
					</td>
			
				</tr>
			</table>
		</td>
		<td  valign="top"><jsp:include page="status_jvm.jsp" flush="true"/></td>
	</tr>
</table>
<c:choose>
	<c:when test="${param.xcmd == 'header'}">
		<jsp:include page="status_header.jsp" flush="true"/>
	</c:when>
	<c:when test="${param.xcmd == 'resource'}">
		<jsp:include page="status_resource.jsp" flush="true"/>
	</c:when>	
	<c:when test="${param.xcmd == 'code'}">
		<jsp:include page="status_code.jsp" flush="true"/>
	</c:when>
	<c:when test="${param.xcmd == 'requestmon'}">
		<jsp:include page="status_requestmon.jsp" flush="true"/>
	</c:when>		
	<c:when test="${param.xcmd == 'programList'}">
		<jsp:include page="status_programList.jsp" flush="true"/>
	</c:when>	
	<c:otherwise>
		<jsp:include page="status_action.jsp" flush="true"/>
	</c:otherwise>
</c:choose>

<hr>
<table border="1">
	<c:forEach var="aKey" items="${SESSIONS}">
		<tr>
			<td><c:out value="${aKey.key}" /></td>
			<td>[<c:out value="${aKey.value}" />]
			<c:if test="${aKey.key == 'msession'}">
				<br/>
				userid = <c:out value="${aKey.value.userid}"/><br/>
				usn = <c:out value="${aKey.value.usn}"/><br/>
				nm = <c:out value="${aKey.value.nm}"/><br/>
				nm_eng = <c:out value="${aKey.value.nm_eng}"/><br/>
				dpt_cd = <c:out value="${aKey.value.dpt_cd}"/><br/>
				dpt_cd = <c:out value="${aKey.value.loginIP}"/><br/>
			</c:if></td>
		</tr>
	</c:forEach>
</table>	
	
</body>
</html>