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
<input type="hidden" id="ORDERNO" name="ORDERNO" value=""/>
<input type="hidden" id="PACKAGE_NO" name="PACKAGE_NO" value=""/>
<input type="hidden" id="LECTURE_NO" name="LECTURE_NO" value="">
<input type="hidden" id="MOVIE_NO" name="MOVIE_NO" value="">

<h2>● 수강신청관리 > <strong>수강생 학습 현황</strong></h2>
 
<!-- 테이블-->
<table class="table02">
	<tr>
		<th width="70">회차</th>
		<th width="*">학습내용</th>
		<th width="90">강의자료</th>
		<th width="90">학습횟수</th>
		<th width="110">강의시간</th>
		<th width="100">학습시간</th>
		<th width="80">최초수강</th>
		<th width="80">최종수강</th>
	</tr>
	<tbody>
	<c:forEach items="${list}" var="item" varStatus="loop">
	<tr>
		<td>${item.MOVIE_ORDER1}회 ${item.MOVIE_ORDER2}강</td>
		<td>${item.MOVIE_NAME}</td>
		<td>
			<c:choose>
				<c:when test="${item.MOVIE_DATA_FILE_YN == 'Y'}">
				<a href="javascript:fn_FileDownload('${item.MOVIE_DATA_FILENAME}');">${item.MOVIE_DATA_FILE_YN}</a>
				</c:when>
				<c:otherwise>
				${item.MOVIE_DATA_FILE_YN}
				</c:otherwise>
			</c:choose>
		</td>
		<td><a href="javascript:fn_userLecMovieLog('${item.ORDERNO}','${item.PACKAGE_NO}','${item.LECTURE_NO}','${item.MOVIE_NO}');">${item.LOG_CNT}</a></td>
		<td>
			<c:set var="LIST_MOVIE_TIME" value="${item.MOVIE_TIME}" />
            <c:set var="LIST_MOVIE_TIME_S" value="${LIST_MOVIE_TIME % 60}" />
            <c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_S) / 60}" />
			<c:set var="LIST_MOVIE_TIME_M" value="${LIST_MOVIE_TIME % 60}" />
			<c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_M) / 60}" />
			<c:set var="LIST_MOVIE_TIME_H" value="${LIST_MOVIE_TIME % 60}" />
			<fmt:formatNumber value="${LIST_MOVIE_TIME_H}" type="number"/>시간
			<fmt:formatNumber value="${LIST_MOVIE_TIME_M}" type="number"/>분
			<fmt:formatNumber value="${LIST_MOVIE_TIME_S}" type="number"/>초
		</td>
		<td><font color="red">${item.CURR_TIME}</font>/${item.STUDY_TIME}(초)</td>
		<td>${item.STUDY_DT}</td>
		<td>${item.UPD_DT}</td>
	</tr>
	</c:forEach>
	<c:if test="${empty list}">
	<tr bgColor=#ffffff align=center> 
		<td colspan="7">데이터가 존재하지 않습니다.</td>
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
//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}

function fn_userLecMovieLog(orderno, package_no, lecture_no, movie_no){
	window.open('<c:url value="/productOrder/userLecMovieLog.pop"/>?ORDERNO=' + orderno + '&PACKAGE_NO=' + package_no + '&LECTURE_NO=' + lecture_no + '&MOVIE_NO=' + movie_no, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=600,height=400');
}
</script>
</body>
</html>