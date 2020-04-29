<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="title.main"/></title>
</head>

<frameset frameborder="0" framespacing="0" rows="75, *, 45">
	<frame name="_top" src="${pageContext.request.contextPath}/Top.do" scrolling="no" title="헤더">
		<frameset frameborder="0" framespacing="0" cols="20%, 80%">
			<frame name="_left" src="${pageContext.request.contextPath}/Left.do" scrolling="yes" title="메뉴페이지">
			<frame name="_content" src="${pageContext.request.contextPath}/Content.do" title="메인페이지">
		</frameset>
	<frame name="_bottom" src="${pageContext.request.contextPath}/Bottom.do" scrolling="no" title="푸터">
</frameset>
