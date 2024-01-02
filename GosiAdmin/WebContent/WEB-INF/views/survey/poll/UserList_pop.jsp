<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
</head>
    <!--content -->
    <div>
    <h2>● 강의관리 > <strong>온라인설문조사</strong></h2>
    
<div align="right">
<!--테이블-->
<table class="table04">
	<tr>
		<th width="5%">NO</th>
        <th>USERID</th>
        <th width="10%">선택</th>
        <th width="25%">응답일자</th>
	</tr>
    <tbody>
    <c:if test="${not empty list}">
	<c:forEach items="${list}" var="list" varStatus="status">
	<tr>
		<td>${status.count}</td>
		<td>${list.USER_ID}</td>
		<td>${list.ANSW}</td>
		<td>${list.REG_DT}</td>
	</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty list}">
	<tr bgColor=#ffffff align=center> 
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
	</c:if>	 
    </tbody>
</table>
<!--//테이블--> 
</div>
<!--//content --> 
</html>