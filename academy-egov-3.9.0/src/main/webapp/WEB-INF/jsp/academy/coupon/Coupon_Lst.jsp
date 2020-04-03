<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var message = "${searchMap.message}";
var search_date_type = "${searchMap.search_date_type}";
var payall = "${searchMap.payall}";

//쿠폰코드 클릭
function view(ccode) {
	$("#CCODE").val(ccode);
	$('#searchFrm').attr('action','<c:url value="/Coupon/modify.do"/>').submit();
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
		$('#searchFrm').attr('action','<c:url value="/Coupon/list.do"/>').submit();
}

//등록폼
function fn_Reg(){
	$("#searchFrm").attr("action", "<c:url value='/Coupon/write.do'/>");
	$("#searchFrm").submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchkey').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

function user_list(coupon_cd){
    
   	window.open('<c:url value="/Coupon/user_list.pop"/>?CCODE=' + coupon_cd, '_blank', 'location=no,resizable=no,width=1080,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
}
</script>
</head>

<!--content -->
  <div id="content">
	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
    
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
	<input type="hidden" id="page" name="page" value="${params.page}" />

	<input type="hidden" id="CCODE" name="CCODE" value="" />
       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
								
	<table class="table02" style="width:100%;">
		<tr>
			<th style="width:7%;">쿠폰코드</th>
          	<th style="width:*;">쿠폰명</th>
          	<th style="width:10%;">발급인원(명)</th>
          	<th style="width:7%;">쿠폰타입</th>
          	<th style="width:7%;">금액/할인율</th>
          	<th style="width:10%;">사용구분</th>
          	<th style="width:7%;">사용기간</th>
          	<th style="width:10%;">사용만료일</th>
          	<th style="width:10%;">쿠폰등록일</th>
        </tr>
	    <tbody>
        <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
	    <tr<c:if test="${list.EXPD eq 'Y'}">class="vitality"</c:if>>
	    	<td>${list.CCODE}</td>
			<td><a href="javascript:view('${list.CCODE}')">${list.CNAME}</a></td>
			<td><a href="javascript:user_list('${list.CCODE}')"><span style="color:red;">${list.USE_CNT}</span> / ${list.CNT}</a></td>
			<td>${list.REGTYPE_NM}</td>
			<td>${list.REGPRICE_NM}</td>
			<td>
				<c:if test="${list.ADD_FLAG == 'O'}">동영상</c:if>
	        	<c:if test="${list.ADD_FLAG == 'M'}">모의고사</c:if>
	        	<c:if test="${list.ADD_FLAG == 'L'}">교재</c:if>
	        	<c:if test="${list.ADD_FLAG == 'F'}">학원</c:if>
			</td>
			<td>${list.EXPDAY}일</td>
			<td>${list.EXPDATEE}</td>
			<td>${list.REGDATE}</td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
        <tr bgColor=#ffffff align=center> 
        	<td colspan="11">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
       </tbody>
    </table>
    <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->

    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
</form>
</div> 
</html>