<%@tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="id" required="true"%>
<c:url var="sortA" value="/maf_images/navigator/sort-a.gif"/>
<c:url var="sortD" value="/maf_images/navigator/sort-d.gif"/>
<c:if test="${navigator.order.key==id }">
	<c:if test="${navigator.order.order=='A'}">
		<c:url var="sortA" value="/maf_images/navigator/sort-a_c.gif"/>
	</c:if>
	<c:if test="${navigator.order.order!='A'}">
		<c:url var="sortD" value="/maf_images/navigator/sort-d_c.gif"/>
	</c:if>
</c:if>
<a href="javascript:mnavi_Sort('A','${id}')"><img src="${sortA}" border="0"></a>
<a href="javascript:mnavi_Sort('D','${id}')"><img src="${sortD}" border="0"></a>