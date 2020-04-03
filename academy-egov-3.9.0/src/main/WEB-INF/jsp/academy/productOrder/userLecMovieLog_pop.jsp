<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<h2>● ${list[0].MOVIE_NAME} > <strong>${list[0].MOVIE_ORDER1}회 ${list[0].MOVIE_ORDER2}강</strong></h2>
 
<!-- 테이블-->
<table class="table05">
<c:set var="Type" value=""/>
	<tr>
		<th width="85">No</th>
		<th>학습시작시간(다운로드시간)</th>
		<th>학습종료시간</th>
		<th>구분</th>
	</tr>
	<tbody>
	<c:forEach items="${list}" var="item" varStatus="loop">
	<tr>
		<td>${item.IDX}</td>
		<td>${item.REG_DT}</td>
		<td>${item.END_DT}</td>
		<td>${item.M_TYPE}</td>
	</tr>
	</c:forEach>
	<c:if test="${empty list}">
	<tr bgColor=#ffffff align=center> 
		<td colspan="3">데이터가 존재하지 않습니다.</td>
	</tr>
	</c:if>	 
    </tbody>
</table>      
<!-- //테이블--> 
	
<!--버튼-->
<ul class="boardBtns">
	<li><a href="javascript:self.close();">닫기</a></li>
</ul>
<!--//버튼--> 	
</div>
<!--//content --> 
</body>
</html>