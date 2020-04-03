<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<html>
<head>
<meta name="decorator" content="index">
</head>

<!--content -->
<div>
<form id="searchFrm" name="searchFrm" method="post">                
<table style="width:100%;">
<tr>
    <td width="2%"></td>
    <td>
    <table style="width:100%; border:0; cellspacing:0; cellpadding:12px;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><h2>▣ 예약현황</h2></td>
      </tr>
    </table>

    <table class="table05">
        <tr>
          <th width="15%">이름(아이디)</th>
          <td width="35%">${user_counsel[0].USER_NM} (${user_counsel[0].USER_ID})</td>
          <th width="15%">생년월일</th>
          <td width="35%" >${user_counsel[0].USER_BIRTHDAY}</td>
        </tr>
        <tr>  
          <th>연락처</th>
          <td>${user_counsel[0].USER_PHONE}</td>
          <th>이메일</th>
          <td>${user_counsel[0].USER_EMAIL}</td>
        </tr>
        <tr>  
          <th>응시직급</th>
          <td>${user_counsel[0].USER_CATEGORY_NM}</td>
          <th>상담예약일시</th>
          <td>${user_counsel[0].SCH_DAY} ${user_counsel[0].TIME_SET}</td>
        </tr>
        <tr>  
          <th>응시직렬</th>
          <td>${user_counsel[0].USER_CODE1_NM}<c:if test="${data.USER_CODE2_NM ne null and data.USER_CODE2_NM ne ''}">,${user_counsel[0].USER_CODE2_NM}</c:if></td>
          <th>수험기간</th>
          <td>
            <c:if test="${user_counsel[0].USER_PERIOD eq 'A'}">6개월 미만</c:if>
            <c:if test="${user_counsel[0].USER_PERIOD eq 'B'}">1년 미만</c:if>
            <c:if test="${user_counsel[0].USER_PERIOD eq 'C'}">1년 이상</c:if>         
          </td>
        </tr>
        <tr>  
          <th>취약과목</th>
          <td>${user_counsel[0].USER_SUBJECT}</td>
          <th>수강여부</th>
          <td>${user_counsel[0].USER_LEC_NM}</td>
        </tr>
        <tr>  
          <th>메모</th>
          <td colspan="3">${fn:replace(user_counsel[0].USER_COMMENTS, newLineChar, '<br/>')}</td>
        </tr>
    </table>
    
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->

    </td>
    <td width="2%"></td>
</tr>
</table> 
</form>
</div>
<!--//content --> 

</html>