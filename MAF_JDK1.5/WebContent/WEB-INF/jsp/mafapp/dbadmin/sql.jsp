<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
Table Name : <c:out value="${tabsname}"/><br/>
<mh:length var="ColumnCnt" value="${columns}"/>
cnt:[<c:out value="${ColumnCnt}"/>]
