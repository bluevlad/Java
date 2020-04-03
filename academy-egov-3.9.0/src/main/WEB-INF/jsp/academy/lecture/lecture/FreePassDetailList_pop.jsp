<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">

</script>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="ADDBOOKAREA" name="ADDBOOKAREA" value="${params.ADDBOOKAREA}"/>

	<input type="hidden" id="MESSAGEID" name="MESSAGEID">
	<input type="hidden" id="MESSAGENM" name="MESSAGENM">
	<h2>● 강의제작 > 강의관리 > <strong>프리패스 상세</strong></h2>
      
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th>학습유형</th>
	        <th>과목명</th>
	        <th>교수명</th>
	        <th>강의명</th>

		</tr>
        <tbody>
	        <c:forEach items="${viewleccodelist}" var="item" varStatus="loop">
				<tr>
			    	<td>${item.LEARNING_NM }</td>
					<td>${item.SUBJECT_NM }</td>
					<td>${item.SUBJECT_TEACHER_NM }</td>
					<td>${item.SUBJECT_TITLE }</td>
				</tr>
			</c:forEach>
			<c:if test="${empty viewleccodelist}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="4">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
        </tbody>
	</table>
	<br><br>
	<strong>* 수강중인 강의</strong>
    <table class="table02">
		<tr>
	        <th>학습유형</th>
	        <th>과목명</th>
	        <th>교수명</th>
	        <th>강의명</th>
	        <th>진도율</th>

		</tr>
        <tbody>
	        <c:forEach items="${list}" var="data" varStatus="loop">
				<tr>
			    	<td>${data.LEARNING_NM }</td>
					<td>${data.SUBJECT_NM }</td>
					<td>${data.SUBJECT_TEACHER_NM }</td>
					<td>${data.SUBJECT_TITLE }</td>
					<td>${data.STUDY_PERCENT }%</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="5">데이터가 존재하지 않습니다.</td>
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
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">

</script>
</body>
</html>