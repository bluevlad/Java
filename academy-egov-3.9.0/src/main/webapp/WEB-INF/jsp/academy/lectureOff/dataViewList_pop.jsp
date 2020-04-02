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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="ADDAREA" name="ADDAREA" value="${params.ADDAREA}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">

	<h2>● 강의제작 > <strong>자료</strong></h2>
 
    <p class="pInto01">&nbsp;</p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="85">회차</th>
	        <th>학습내용</th>
	        <th>강의자료</th>
	        <!--  
	        <th>샘플학습</th>
	        <th>시간</th>
	        <th>PMP</th>
	        -->
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			         <td>${item.MOVIE_ORDER1}회 ${item.MOVIE_ORDER2}강</td>
			         <td>${item.MOVIE_NAME}</td>
			         <td>${item.MOVIE_DATA_FILENAME}</td>
			         <!--  
			         <td></td>
			         <td></td>
			         <td></td>
			         -->			         			
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
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">



</script>
</body>
</html>