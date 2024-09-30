<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
</head>

  <!--content -->
	<h2>● 온라인설문 > <strong>참여자정보</strong></h2>

    <!--테이블-->    
    <table class="table04">
    	<tr>
        	<th width="7%">NO</th>
            <th>아이디</th>
            <th width="10%">이름</th>
        </tr>
        <tbody>
        <c:if test="${not empty aList}">
	    <c:forEach items="${aList}" var="list" varStatus="status">
		<tr>
			<td>${status.index}</td>
		    <td>${list.USER_ID}</td>
		    <td>${list.USER_NM}</td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty aList}">
		<tr bgColor=#ffffff align=center> 
			<td colspan="5">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
        </tbody>
	</table>
  <!--//content -->
  </html>