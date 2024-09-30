<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

$(function() {
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("SDATE", "EDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//검색
function goList() {
		$('#searchFrm').attr('action','<c:url value="/Coupon/CouponOrderList.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchkey').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList();
		}
	});
}

function onOnlyNumber(obj) {
	 for (var i = 0; i < obj.value.length ; i++){
	  chr = obj.value.substr(i,1);
	  chr = escape(chr);
	  key_eg = chr.charAt(1);
	  if (key_eg == "u"){
	   key_num = chr.substr(i,(chr.length-1));
	   if((key_num < "AC00") || (key_num > "D7A3")) {
	    event.returnValue = false;
	   }
	  }
	 }
	 if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
	 } else {
	  event.returnValue = false;
	 }
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
	<input type="hidden" id="CCODE" name="CCODE" value="" />
     <!--테이블-->

	<table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td>&nbsp;
			<label for="PTYPE"></label>
			<select style="width:80px;" id="PTYPE" name="PTYPE">
				<option value=""  >-강의구분-</option>
				<option value="D"  <c:if test="${params.PTYPE == 'D'}">selected</c:if>>단과</option>
				<option value="N"  <c:if test="${params.PTYPE == 'N'}">selected</c:if>>종합반</option>
			</select>
			&nbsp;
			<label for="REGTYPE"></label>
			<select style="width:80px;" id="REGTYPE" name="REGTYPE">
				<option value=""  >-할인구분-</option>
				<option value="C"  <c:if test="${params.REGTYPE == 'C'}">selected</c:if>>비율할인</option>
				<option value="P"  <c:if test="${params.REGTYPE == 'P'}">selected</c:if>>포인트할인</option>
			</select>
			<th>세부항목</th>
			<td>
				<input type="checkbox" name="RTYPE" id="RTYPE" value="Y" <c:if test="${params.RTYPE == 'Y' }">checked="checked"</c:if>>할인구분별&nbsp;&nbsp;
				<input type="checkbox" name="CTYPE" id="CTYPE" value="Y" <c:if test="${params.CTYPE == 'Y' }">checked="checked"</c:if>>쿠폰별
			</td>
			<th>사용기간</th>
			<td>
				<input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/> 
					&nbsp;~&nbsp;
				<input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
          	</td>
			<td>
            <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList()"  >
            </td>
          </td>          
        </tr>
    </table>
	<p class="pInto01">&nbsp;<span></span></p>               
								
	<table class="table02" style="width:100%;">
		<tr>
			<th style="width:10%;">쿠폰구분</th>
			<th style="*">쿠폰</th>
			<th style="width:10%;">할인금액(율)</th>
			<th style="width:10%;">쿠폰사용건수</th>
          	<th style="width:15%;">강의금액</th>
          	<th style="width:15%;">판매금액</th>
          	<th style="width:15%;">할인금액</th>
        </tr>
	    <tbody>
        <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
	    <tr>
			<td>${list.REGTYPE}</td>
			<td>${list.CNAME}</td>
			<td><fmt:formatNumber value="${list.REGPRICE}" type="number"/></td>
			<td><span style="color:red;"><fmt:formatNumber value="${list.USE_CNT}" type="number"/></span>/<fmt:formatNumber value="${list.CNT}" type="number"/></td>
			<td><fmt:formatNumber value="${list.ORG_AMOUNT}" type="currency"/></td>
			<td><fmt:formatNumber value="${list.SALE_AMOUNT}" type="currency"/></td>
			<td><span style="color:red;"><fmt:formatNumber value="${list.ORG_AMOUNT - list.SALE_AMOUNT}" type="currency"/></span></td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
        <tr bgColor=#ffffff align=center> 
        	<td colspan="10">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
       </tbody>
    </table>
    <!--//테이블-->
</form>
</div> 
</html>