<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
<meta charset="utf-8">
<title>WILLBES</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/common.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/video.css'/>">
<script src="<c:url value='/assets/libs/jquery/jquery-1.11.2.min.js'/>"></script>
<script src="<c:url value='/assets/js/lib/jquery.bxslider.min.js'/>"></script>
<script src="<c:url value='/assets/js/common.js'/>"></script>
<script src="<c:url value='/assets/js/sub.js'/>"></script>
</head>
<body>
<script>
$(document).ready(function(){
});
</script>
<!-- WIDTH:800px * 597px-->
<div class="popupWrap">
	<!-- <div class="popupHead">
		<h1><img src="/resources/img/common/pop_tit1.gif" alt="교재소개"></h1>
		<button type="button" class="pClose" onclick="javascript:self.close()">팝업닫기</button>
	</div> -->
	<div class="popupContainer">
		<div class="bookDtWrap mgB3">
			<div class="bookPhoto">
	      <div class="frame">
	      	<c:choose><c:when test="${!empty view[0].ATTACH_IMG_L}">
                  <img src="<c:url value="/imgFileView.do?path=${view[0].ATTACH_IMG_L}" />" alt="" onError="javascript:this.src='/assets/img/common/teacher_6_2.jpg'"/>
                  </c:when><c:otherwise>
                  <img src="<c:url value='/resources/img/common/teacher_6_2.jpg'/>" alt="">
                  </c:otherwise></c:choose>
	      </div>
	    </div>
	    <div class="bookDetail mgB2">      
	      <table class="bookDtTb mgB1">
	        <caption>교재 상세정보</caption>
	        <tbody>
        		<tr>
	            <th>교재명</th>
	            <td><strong>${view[0].BOOK_NM}</strong>
		            <c:choose>
						<c:when test="${view[0].COVER_TYPE == 'O'}"><span class="red">${view[0].COVER_TYPENM}</span></c:when>
						<c:when test="${view[0].COVER_TYPE == 'S'}"><span class="red">${view[0].COVER_TYPENM}</span></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
	          </tr>
	          <tr>
	            <th>분야</th>
	            <td>${view[0].CATEGORY_NM}</td>
	          </tr>
	          <tr>
	            <th>저자</th>
	            <td>${view[0].BOOK_AUTHOR}</td>
	          </tr>
	          <tr>
	            <th>출판사</th>
	            <td>${view[0].BOOK_PUBLISHERS}</td>
	          </tr>
	          <tr>
	            <th>판형/쪽수</th>
	            <td>${view[0].BOOK_FORMAT} / ${view[0].BOOK_PAGE}</td>
	          </tr>
	          <tr>
	            <th>출판일</th>
	            <td>
            		<c:if test="${!empty view[0].BOOK_DATE}">
              				${fn:substring(view[0].BOOK_DATE,0, 4)}-${fn:substring(view[0].BOOK_DATE,4, 6)}-${fn:substring(view[0].BOOK_DATE,6, 8)}
					</c:if>
				</td>
	          </tr>         
	          <tr>
	            <th>가격</th>
	            <td class="price"><del><fmt:formatNumber value="${view[0].PRICE}" pattern="##,###.##" />원</del> →  <strong><fmt:formatNumber value="${view[0].DISCOUNT_PRICE}" pattern="##,###.##" />원 </strong><em>(${view[0].DISCOUNT}%할인)</em></td>
	          </tr>
	          <tr>
	            <th>적립금</th>
	            <td class="price"><strong><fmt:formatNumber value="${view[0].POINT}" pattern="##,###.##" />원 </strong>(5%적립)</td>
	          </tr>         
	        </tbody>
	      </table>
	    </div>    
    </div>
<%--     <div class="lect-infoClass mgB3">
      <h2 class="mgB1"><img src="/assets/img/video/lect/h4_lect12.gif" alt="교재소개"></h2>
      <div class="infoClassBx">       
        <!-- <ol> -->
        	<c:if test="${!empty view[0].BOOK_INFO}">
				<li>${fn:replace(view[0].BOOK_INFO, newLineChar, '</li><li>')}</li>
			</c:if>
        <!-- </ol> -->
      </div>
    </div> --%>
<%--     <div class="lect-infoClass mgB3">
    	 <h2 class="mgB1"><img src="/assets/img/video/lect/h4_lect13.gif" alt="목차"></h2>
    <div class="infoClassBx">
    	<ul class="toc">
          <c:if test="${!empty view[0].BOOK_CONTENTS}">
			<li>
				${fn:replace(view[0].BOOK_CONTENTS, newLineChar, '</li><li>')}
			</li>
			</c:if>
        </ul>              
    </div>
    </div> --%>
    <!-- // 교재정보-->
	</div>
</div>
</body>
</html>