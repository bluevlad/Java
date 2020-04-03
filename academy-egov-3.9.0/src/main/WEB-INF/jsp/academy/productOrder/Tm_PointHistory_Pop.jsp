<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
</head>
    <!--content -->
<div>
<table style="width:100%;">
	<tr>
		<td width="2%"></td>
		<td>
			<table>
				<tr>
					<td align="left" bgcolor="#FFFFFF"><h2>▣ 포인트(POINT) 내역</h2></td>
				</tr>
			</table>
			<!--테이블--> 
	    	<table class="table05">
	        	<tr>
	          		<th width="20%">그룹</th>
	          		<td>${meminfo[0].USER_ROLE} (${meminfo[0].USER_POSITION})</td>
	        	</tr>
	        	<tr> 
	    	  	<th>아이디 / 이름</th>
	          		<td>${meminfo[0].USERID}  /  ${meminfo[0].USER_NM}</td>
	        	</tr>
	        	<tr>  
	          	<th>전화번호 / 휴대폰 번호</th>
	          		<td>${meminfo[0].TELEP_NO}  /  ${meminfo[0].CELP_NO}</td>
	        	</tr>
	        	<tr>  
	          		<th>이메일 주소</th>
	          		<td>${meminfo[0].EMAIL}</td>
	        	</tr>
	        	<tr>  
	          		<th>가입일</th>
	          		<td>${meminfo[0].REGDATE}</td>
	        	</tr>
			</table>
			<div style="height:10px;"> </div>
	    	<!--테이블-->
	     	<table class="table05">
	        	<tr>
		          	<th>NO</th>
					<th>사이트구분</th>
					<th>주문번호</th>
					<th>포인트</th>
					<th>사용일자</th>
					<th>내용</th>
				</tr>
	         	<c:if test="${not empty list}">
	          	<c:forEach items="${list}" var="list" varStatus="status">
	            <tr>
					<td>${status.index + 1}</td>
					<td>${list.SITE}</td>
					<td>${list.ORDERNO}</td>
					<td style="text-align:right;">${list.POINT}</td>
					<td>${list.REGDATE}</td>
					<td>${list.COMMENT1}</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list}">
			    <tr bgColor=#ffffff align=center> 
					<td colspan="7">표시할 포인트 내역이 없습니다.</td>
				</tr>
				</c:if>	 
			</table>
	     	<!--//테이블-->
			<!--버튼-->    
	    	<ul class="boardBtns">
	        	<li><a href="javascript:self.close();">닫기</a></li>
	    	</ul>
	    	<!--//버튼-->
	
		</td>
		<td width="4%"></td>
	</tr>
</table>	  

</div>
<!--//content --> 
</html>