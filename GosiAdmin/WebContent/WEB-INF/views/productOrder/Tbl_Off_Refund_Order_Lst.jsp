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
var message = "${searchMap.message}";

window.onload = function () {
	 if(message != "") {
		alert(message);
	}
}

//성명,강의명 클릭
function view(ORDERNO, USER_ID, STATUSCODE, MGNTNO) {
	//alert("orderno:"+orderno);
	
	$("#ORDERNO").val(ORDERNO);
	$("#USER_ID").val(USER_ID);
	$("#STATUSCODE").val(STATUSCODE);
	$("#MGNTNO").val(MGNTNO);
	$('#searchFrm').attr('action','<c:url value="/productOrder/updateRefundPage.do"/>').submit();
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/off_refund_list.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchkey').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//주문자 클릭시
function user_view(userid){
	if(userid=="" || userid ==null){
	     alert("비회원입니다.");
	     return;
	}else{
	 	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("sdate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("edate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

</script>
</head>

<!--content -->
  <div id="content">
	<h2>● 수강신청관리 > <strong>환불관리</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
		
	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />
	<input type="hidden" id="MGNTNO" name="MGNTNO" />
	
	<table class="table01">
        <tr>
          <th width="15%">검색</th>
          <td>
          
          <input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${searchMap.sdate }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${searchMap.edate }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>

          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

          <select name="searchtype" id="searchtype">
			<option value="all" <c:if test="${searchMap.searchtype == 'all'}">selected</c:if>>전체검색</option>
			<option value="A.ORDERNO" <c:if test="${searchMap.searchtype == 'A.ORDERNO'}">selected</c:if>>주문번호</option>
			<option value="A.USER_NM" <c:if test="${searchMap.searchtype == 'A.USER_NM'}">selected</c:if>>성명</option>
			<option value="A.PHONE_NO" <c:if test="${searchMap.searchtype == 'A.PHONE_NO'}">selected</c:if>>핸드폰번호</option>
		  </select>
		
		<input type="text" class="i_text"  title="검색" id="searchkey" name="searchkey" type="text" style="width:80px;"  value="${searchMap.searchkey}" onkeypress="fn_checkEnter()">
		
              <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
        </td>
        </tr>
    </table>
             
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
          	<th>주문번호</th>
			<th>신청구분</th>
			<th>승인일시</th>
			<th>성명(전화)</th>
			<th>강의명</th>
			<th>강의수</th>
			<th>총 결제금액</th>
			<th>결제상태</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td><a href="javascript:view('${list.ORDERNO}','${list.USER_ID}','${list.STATUSCODE}')">${list.ORDERNO}</a></td>
				<td>${list.ORDER_TYPE_NM}</td>
				<td>${list.ISCONFIRM}</td>
				<td><a href="javascript:user_view('${list.USER_ID}')">${list.USER_NM}(${list.PHONE_NO})</a></td>
				<td style="text-align:left;"><a href="javascript:view('${list.ORDERNO}','${list.USER_ID}','${list.STATUSCODE}','${list.MGNTNO}')">${list.SUBJECT_TITLE}</a></td>				
				<td>${list.SUBJECT_CNT}</td>				
				<td><fmt:formatNumber value="${list.PRICE}" type="currency" /></td>
				<td>${list.STATUSCODE_NM}</td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="9">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
     <!--//테이블-->
		                  
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</form>
</div> 
</html>