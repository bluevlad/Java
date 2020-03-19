<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<%@page import="java.lang.*" %>
<%
	int x = 10;	
	String t = "!23";
	String rv = null;
	if(x instanceof String) {
		rv = "true";
	} else {
		rv = "false";
	}
%>
 뭘까 ? <%= rv %>
