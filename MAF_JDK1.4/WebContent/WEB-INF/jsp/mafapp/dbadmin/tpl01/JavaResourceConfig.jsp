<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>


&lt;?xml version="1.0" encoding="utf-8" ?>
&lt;!DOCTYPE maf-command PUBLIC
         "-//Miranet//DTD maf-command config XML V1.0//EN"
         "resourceBundle.dtd">
&lt;bundle defaultLocale="ko">
<c:forEach var="item" items="${columns}" varStatus="status">
	&lt;resource key="<mh:out value="${item.name}" case="lower"/>" >
		&lt;message locale="ko" value="<mh:out value="${item.comments}" case="lower"/>" />
	&lt;/resource>
</c:forEach>	
&lt;/bundle>	
