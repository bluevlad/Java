<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>

<script type="text/javascript">

// 목록으로
function fn_List() {
	if($.trim($("#CALLPOSITION").val())=="ROOMRENTWRITE"){
		$("#frm").attr("action","<c:url value='/room/roomRentWrite.do' />");
	} else {
		$("#frm").attr("action","<c:url value='/room/roomOrderList.do' />");
	}
	$("#frm").submit();
}

// 독서실 정보 삭제 
function fn_DeleteOrder(){
	if(confirm("독서실 주문정보를 삭제하겠습니까?")){
		$("#frm").attr("action","<c:url value='/room/roomDeleteOrderProcess.do' />");
		$("#frm").submit();
	}		
}
	
</script>
</head>


<body>
<!--content -->
<div id="content">
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="ROOM_CD" name="ROOM_CD" value="${ROOM_CD}">
	<input type="hidden" id="ROOM_NUM" name="ROOM_NUM" value="${ROOM_NUM}">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="${RENT_SEQ}">
	<input type="hidden" id="ORDERNO" name="ORDERNO" value="${ORDERNO}">
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="${CALLPOSITION}">

	<h2>● 독서실 관리 &gt; <strong>독서실 결제 정보</strong></h2>	
    	<table class="table01">
   		<tr>
   			<th style="width:15%;">주문 번호</th>
  			<td>${roomOrderDetail.ORDERNO}</td>
   			<th style="width:15%;">성명(아이디)</th>
  			<td>${roomOrderDetail.USER_NM}(${roomOrderDetail.USER_ID})</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">연락처</th>
  			<td>${roomOrderDetail.PHONE_NO}</td>
   			<th style="width:15%;">이메일</th>
  			<td>${roomOrderDetail.EMAIL}</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">독서실명(번호)</th>
  			<td>${roomOrderDetail.ROOM_NM} (${roomOrderDetail.ROOM_NUM}번)</td>
   			<th style="width:15%;">대여기간</th>
  			<td>${roomOrderDetail.RENT_START} ~ ${roomOrderDetail.RENT_END}</td>
  		</tr>  		
   		<tr>
   			<th style="width:15%;">독서실 신청일</th>
  			<td>${roomOrderDetail.REG_DT}</td>
   			<th style="width:15%;">신청구분</th>
  			<td><c:if test="${roomOrderDetail.RENT_TYPE eq 'OFF'}">오프라인</c:if><c:if test="${roomOrderDetail.RENT_TYPE eq 'ON'}">온라인</c:if></td>
  		</tr>  		
   		<tr>
   			<th style="width:15%;">대여 금액</th>
  			<td>100,000 원</td>
   			<th style="width:15%;">결제 금액</th>
  			<td>${roomOrderDetail.PRICE} 원</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">카드 지불금액</th>
  			<td>${roomOrderDetail.PRICE_CARD} 원</td>
   			<th style="width:15%;">현금 지불금액</th>
  			<td>${roomOrderDetail.PRICE_CASH} 원</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">할인 금액</th>
  			<td>${roomOrderDetail.PRICE_DISCOUNT} 원</td>
   			<th style="width:15%;">할인 사유</th>
  			<td>${roomOrderDetail.PRICE_DISCOUNT_REASON}</td>
  		</tr>
   		
   		<tr>
   			<th>메 모</th>
  			<td colspan="3">
	   			<textarea id="RENT_MEMO" name="RENT_MEMO" ROWS="3" maxlength="4000" style="width:75%;">${roomOrderDetail.RENT_MEMO}</textarea>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_List();">목록</a></li>
   	  <li><a href="javascript:fn_DeleteOrder();">주문삭제</a></li>
    </ul>
    <!--//버튼--> 
</form>

</div>

<!--//content --> 
</body>

</html>