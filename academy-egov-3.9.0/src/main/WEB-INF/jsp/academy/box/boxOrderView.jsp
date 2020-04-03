<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>

<script type="text/javascript">
$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("REFUND_DATE");
});

// 목록으로
function fn_List() {
	if($.trim($("#CALLPOSITION").val())=="BOXRENTWRITE"){
		$("#frm").attr("action","<c:url value='/box/boxRentWrite.do' />");
	} else {
		$("#frm").attr("action","<c:url value='/box/boxOrderList.do' />");
	}
	$("#frm").submit();
}

// 사물함 정보 삭제 
function fn_DeleteOrder(){
	if(confirm("사물함 주문정보를 삭제하겠습니까?")){
		$("#frm").attr("action","<c:url value='/box/boxDeleteOrderProcess.do' />");
		$("#frm").submit();
	}		
}

function fn_Refund() {
	if($.trim($("#REFUND_PRICE").val())==""){
		alert("환불 금액을 입력해 주세요");
		$("#REFUND_PRICE").focus();
		return;
	}
	if($.trim($("#REFUND_DATE").val())==""){
		alert("환불 일자를 입력해 주세요");
		$("#REFUND_DATE").focus();
		return;
	}

	if (confirm("사물함 대여 환불 정보를 저장하겠습니까?")) {
		$("#frm").attr("action", "<c:url value='/box/boxRefundProcess.do' />");
		$("#frm").submit();
	}
}
</script>
</head>

<body>
<!--content -->
<div id="content">
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="BOX_CD" name="BOX_CD" value="${boxOrderDetail.BOX_CD}">
	<input type="hidden" id="BOX_NUM" name="BOX_NUM" value="${boxOrderDetail.BOX_NUM}">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="${boxOrderDetail.RENT_SEQ}">
	<input type="hidden" id="ORDER_ID" name="ORDER_ID" value="${boxOrderDetail.ORDERNO}">
	<input type="hidden" id="ORDERNO" name="ORDERNO" value="${boxOrderDetail.ORDERNO}">
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="${CALLPOSITION}">

	<!-- <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>	 -->
	<h2>● 사물함관리 > <strong>사물함신청관리</strong></h2>	
    	<table class="table01">
   		<tr>
   			<th style="width:15%;">주문 번호</th>
  			<td>${boxOrderDetail.ORDERNO}</td>
   			<th style="width:15%;">성명(아이디)</th>
  			<td>${boxOrderDetail.USER_NM}(${boxOrderDetail.USER_ID})</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">연락처</th>
  			<td>${boxOrderDetail.PHONE_NO}</td>
   			<th style="width:15%;">이메일</th>
  			<td>${boxOrderDetail.EMAIL}</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">사물함명(번호)</th>
  			<td>${boxOrderDetail.BOX_NM} (${boxOrderDetail.BOX_NUM}번)</td>
   			<th style="width:15%;">대여기간</th>
  			<td>${boxOrderDetail.RENT_START} ~ ${boxOrderDetail.RENT_END}</td>
  		</tr>  		
   		<tr>
   			<th style="width:15%;">사물함 신청일</th>
  			<td>${boxOrderDetail.REG_DT}</td>
   			<th style="width:15%;">신청구분</th>
  			<td><c:if test="${boxOrderDetail.RENT_TYPE eq 'OFF'}">오프라인</c:if><c:if test="${boxOrderDetail.RENT_TYPE eq 'ON'}">온라인</c:if></td>
  		</tr>  		
   		<tr>
   			<th style="width:15%;">대여 금액</th>
  			<td>${boxOrderDetail.PRICE} 원</td>
   			<th style="width:15%;">결제 금액</th>
  			<td>${boxOrderDetail.PRICE} 원</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">카드 지불금액</th>
  			<td>${boxOrderDetail.PRICE_CARD} 원</td>
   			<th style="width:15%;">현금 지불금액</th>
  			<td>${boxOrderDetail.PRICE_CASH} 원</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">할인 금액</th>
  			<td>${boxOrderDetail.PRICE_DISCOUNT} 원</td>
   			<th style="width:15%;">할인 사유</th>
  			<td>${boxOrderDetail.PRICE_DISCOUNT_REASON}</td>
  		</tr>
   		
   		<tr>
   			<th>메 모</th>
  			<td colspan="3">
	   			<textarea id="RENT_MEMO" name="RENT_MEMO" ROWS="3" maxlength="4000" style="width:75%;">${boxOrderDetail.RENT_MEMO}</textarea>
  			</td>
  		</tr>
   		<tr>
   			<th>환불 금액/일자</th>
  			<td colspan="3"><input type="text" id="REFUND_PRICE" name="REFUND_PRICE" value="" size="30" maxlength="50" title="환불금액"
					style="width:100px;background:#FFECEC;" /> 원  
				<input type="text" id="REFUND_DATE" name="REFUND_DATE" maxlength="10" size="10" class="i_text" value="" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_List();">목록</a></li>
      <c:if test="${boxOrderDetail.STATUSCODE eq 'DLV105'}">
      <li><a href="javascript:fn_Refund();">환불</a></li>
      </c:if>
   	  <li><a href="javascript:fn_DeleteOrder();">주문삭제</a></li>
    </ul>
    <!--//버튼--> 
</form>

</div>

<!--//content --> 
</body>

</html>