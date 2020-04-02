<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
</head>
<body>
	<!--snb -->
	<div class="snb">
	
		<div class="gnb_l">
			<ul>
				<li class="info"><div align="center"><img src="<c:url value="/resources/img/common/icon_main01_07.png"/>" alt="icon_main01_07" align="texttop" /><strong>${AdmUserInfo.USERNAME}</strong> 님</div><div align="center">환영합니다</div></li>
				<li class="logout"><a href="<c:url value="/login/logout.do"/>" title="로그아웃"><img src="<c:url value="/resources/img/common/icon_main01_10_2.png"/>" alt="icon_main01_10" align="absmiddle" />로그아웃</a></li>
			</ul>		
	 	</div>
	 	
	    <!--네비게이션-->
	    <div class="blue bluedemo-container">
	      <ul class="accordion"  id="accordion-3">
  	      
			<c:if test="${fn:substring(menu,0,2) eq 'm1' }">
		        <li><a href="#"><strong>게시판관리</strong><img src="<c:url value="/resources/img/navi/arrow01.gif"/>" style="margin-left:36px; margin-bottom:3px;" /></a>
		          <ul>
		          	<li><a href="<c:url value="/board/boardList.do?BOARDENVID=1"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />모의고사 공고</a></li>
		          	<li><a href="/board/boardList.do?BOARDENVID=2"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />모의고사 이의신청</a></li>
		          	<li><a href="/board/boardList.do?BOARDENVID=3"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />성적표+정오표</a></li>
		          	<li><a href="/board/boardList.do?BOARDENVID=4"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />모의고사 상담실</a></li>	            
		          </ul>
		        </li>
			</c:if>

			<c:if test="${fn:substring(menu,0,2) eq 'm2' }">
	        	<li><a href="<c:url value="/offerer/offererList.do"/>"><strong>신청자관리</strong></a>
		          <ul>
		          	<li><a href="<c:url value="/offerer/offererList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />신청자관리</a></li>	            
		          </ul>	        		        	
	        	</li>
			</c:if>
			
			<c:if test="${fn:substring(menu,0,2) eq 'm3' }">
		        <li><a href="#"><strong>모의고사등록</strong><img src="<c:url value="/resources/img/navi/arrow01.gif"/>" style="margin-left:36px; margin-bottom:3px;" /></a>
		          <ul>
			          	<li><a href="<c:url value="/mouigosa/mouigosaList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />과목별 문제등록</a></li>
			          	<li><a href="<c:url value="/mouigosa/mouigosaRegistrationList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />모의고사등록</a></li>	            
		          </ul>
		        </li>
			</c:if>
			
			<c:if test="${fn:substring(menu,0,2) eq 'm4' }">
				<li><a href="<c:url value="/offExamReg/offExamList.do"/>"><strong>OFF응시자등록</strong></a></li>
			</c:if>
			
			<c:if test="${fn:substring(menu,0,2) eq 'm5' }">
		        <li><a href="<c:url value="/stats//statsPersonList.do"/>"><strong>모의고사통계</strong><img src="<c:url value="/resources/img/navi/arrow01.gif"/>" style="margin-left:36px; margin-bottom:3px;" /></a>
		          <ul>
		          	<li><a href="<c:url value="/stats/statsPersonList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />개인별 모의고사 통계</a></li>
		          	<li><a href="<c:url value="/stats/statsTotalList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />전체 모의고사 통계</a></li>
		          	<li><a href="<c:url value="/stats/statsOffererList.do"/>"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />ON/OFF 접수자 명단</a></li>	            
		          </ul>
		        </li>
			</c:if>
			
			<c:if test="${fn:substring(menu,0,2) eq 'm6' }">
		        <li><a href="#"><strong>강사료정산</strong><img src="<c:url value="/resources/img/navi/arrow01.gif"/>" style="margin-left:36px; margin-bottom:3px;" /></a>
		          <ul>
		          	<li><a href="#"><img src="<c:url value="/resources/img/navi/bullet01_2.gif"/>" alt="." align="absmiddle" style="margin-right:8px;" />강사료정산</a></li>	            
		          </ul>
		        </li>
		    </c:if>
	        
	      </ul>
	    </div>
	    <!--//네비게이션--> 
	  </div>
	  
	  <!--//snb --> 
</body>
</html>