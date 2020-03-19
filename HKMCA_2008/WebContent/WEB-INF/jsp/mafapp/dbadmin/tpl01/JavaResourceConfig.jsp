<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

&lt;?xml version="1.0" encoding="utf-8" ?>
&lt;!DOCTYPE maf-command PUBLIC
         "-//MAF//DTD maf-command config XML V1.0//EN"
         "resourceBundle.dtd">
&lt;bundle defaultLocale="ko">
<c:forEach var="item" items="${columns}" varStatus="status">
	&lt;resource key="<mh:out value="${item.name}" case="lower"/>" >
		&lt;message locale="ko" value="<mh:out value="${item.comments}" case="lower"/>" />
	&lt;/resource>
</c:forEach>	
&lt;/bundle>