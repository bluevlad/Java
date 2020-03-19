<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="jh" uri="http://jmf.jstl.com/jsp/tld/jmf-html.tld"%>
<%@ taglib prefix="jf" uri="http://jmf.jstl.com/jsp/tld/jmf-form.tld"%>
<%@ taglib prefix="jfmt" uri="http://jmf.jstl.com/jsp/tld/jmf-fmt.tld"%>
<%@page import="java.util.*" %>
<%
	Map map = new HashMap();
	map.put("k1","k1");
	List list = new ArrayList();
	list.add("l1");list.add("l2");list.add("l2");list.add("l2");list.add("l3");list.add("l2");list.add("l4");list.add("l2");list.add("l2");list.add("l2");list.add("l2");
	request.setAttribute("map",map);
	request.setAttribute("list",list);
	request.setAttribute("str","ko\"rea<td></td>한글도");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>System Status</TITLE>
<style type="text/css"> 
		body, td {
			font-family:  "Verdana", "Arial", "sans-serif";
			font-size: 12px;
		}
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
</head>
<body>
<mfmt:message bundle="common.login" key="btn.findid"/><br>
CPATH = [<mh:CPATH/>]<br>
IPATH = [<mh:IPATH/>]<br>
Current JSP Spec = [<c:out value="JSP 1.2"/>, ${'JSP 2.0'} ] <br/>

<mh:length var="t" value="${str}"/>
length of String(EL)  : <c:out value="${str}"/> [<mh:out value="${t}"/>]<br/>
length of direct String(012345) : [<mh:length  value="012345"/>]<br/>
<mh:length var="t" value="${map}"/>
Map : [length = <mh:out value="${t}"/>]<mh:out value="${map}"/><br>
<mh:length var="t" value="${list}"/>
list : [length = <mh:out value="${t}"/>]<mh:out value="${list}"/><br/>
Replace Test(mh:replace value="korea" before="ko" after="KO")  = <mh:replace value="korea" before="ko" after="KO"/><br/>


<hr>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this)">
	<mf:input name="n1" type="text" value="${str}"/>
	<mf:textarea name="n2"  value="${str}zxc" rows="10"/>
</mf:form>
</body>
</html>