<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
		<!-- 일반글 -->
<c:if test="${!empty(list)}">
<c:set value="4" var="cols"/>
<c:set value="1" var="cnt"/>
	<tr>
   	<c:forEach items="${list}" var="item" varStatus="status">
   	<c:url var="url" value="/board/view.do">
	<c:param name="c_index" value="${item.c_index}" />
	<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
	</c:url>
		<td width="25%" align="center">
		<!-- 사진1 start -->
			<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/thumb/${item.c_image}"/>
           	<a href='<c:out value="${url}"/>'><img src='<c:out value="${imgUrl}"/>' border="0" width="80"></a>
           	<br><mh:out value="${item.c_subject }" bytes="20"/>
		<!-- 사진1 end -->
		</td>
   	<c:if test="${cnt%cols == 0}">
	</tr>
	<tr>
	</c:if>
	<c:set value="${cnt+1}" var="cnt"/>
	</c:forEach>
	</tr>
</c:if>
<!-- 리스트 첫번째 줄 end -->