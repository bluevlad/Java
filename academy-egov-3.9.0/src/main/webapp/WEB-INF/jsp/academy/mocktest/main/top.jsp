<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div id="gnb">
				<ul id="gnb_l">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</ul>
	 			<ul class="gnb_c">
	 				<li><a href="<c:url value="/board/boardList.do?BOARDENVID=1"/>">게시판관리</a></li> 
	 				<li><a href="<c:url value="/offerer/offererList.do"/>">신청자관리</a></li>
	 				<li><a href="<c:url value="/mouigosa/mouigosaList.do"/>">모의고사등록</a></li> 
	 				<li><a href="<c:url value="/offExamReg/offExamList.do"/>">OFF응시자목록</a></li>
	 				<li><a href="<c:url value="/stats//statsPersonList.do"/>">모의고사통계</a></li>
	 				<li><a href="/lectureFees/lectureFeesList.do">강사료정산</a></li>
	 			</ul> 
		   	</div>
		</div>
	</div>
</body>
</html>