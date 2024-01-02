<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<title>독서실 대여금 환불 등록</title>
<script type="text/javascript">
//등록
function fn_Submit() {
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

		if (confirm("독서실 대여 환불 정보를 저장하겠습니까?")) {
			$("#frm").attr("action", "<c:url value='/room/roomRefundProcess.do' />");
			$("#frm").submit();
			window.close();
		}
	}
	
	// 목록으로
	function fn_Close() {
		window.close();
	}

	$(function() {
		setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
		initDateFicker1("REFUND_DATE");
	});
	
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
       		
<body>
<!--content -->
<div id="content_pop">
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="ROOM_CD" name="ROOM_CD" value="${ROOM_CD}">
	<input type="hidden" id="ROOM_NUM" name="ROOM_NUM" value="${ROOM_NUM}">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="${RENT_SEQ}">
	<input type="hidden" id="ORDER_ID" name="ORDER_ID" value="${roomnumrentorderdetail.ORDERNO}" />

	<h2>● 독서실 관리 &gt; <strong>독서실 환불 등록</strong></h2> 
    	<table class="table01">
   		<tr>
   			<th style="width:20%;" colspan="2">주문 번호</th>
  			<td>${roomnumrentorderdetail.ORDERNO}
			</td>
  		</tr>  		
   		<tr>
   			<th colspan="2">성  명</th>
  			<td>${memberdetail.USER_NM}
  			</td>
  		</tr>
   		<tr>
   			<th colspan="2">연락처</th>
  			<td>${memberdetail.PHONE_NO}
  			</td>
  		</tr>
   		<tr>
   			<th colspan="2">이메일</th>
  			<td>${memberdetail.EMAIL}
  			</td>
  		</tr>
   		<tr>
   			<th colspan="2">독서실 대여일</th>
  			<td>${roomnumrentorderdetail.REG_DT}
  			</td>
  		</tr>
   		<tr>
   			<th colspan="2">독서실 대여기간</th>
  			<td>${roomnumrentdetail.RENT_START} ~ ${roomnumrentdetail.RENT_END} 
  			</td>
  		</tr>
   		<tr>
   			<th rowspan="5">결재내역</th>
   			<th>결제일</th>
  			<td> ${roomnumrentorderdetail.REG_DT}
  			</td>
  		</tr>
   		<tr>
   			<th>결제 금액</th>
  			<td> ${roomnumrentorderdetail.PRICE}
  			</td>
  		</tr>
   		<tr>
   			<th>카드 사용</th>
  			<td> ${roomnumrentorderdetail.PRICE_CARD}
  			</td>
  		</tr>
   		<tr>
   			<th>현금 사용</th>
  			<td> ${roomnumrentorderdetail.PRICE_CASH}
  			</td>
  		</tr>
   		<tr>
   			<th>비고</th>
  			<td> ${roomnumrentdetail.RENT_MEMO}
  			</td>
  		</tr>
   		<tr>
   			<th colspan="2">환불 금액/일자</th>
  			<td><input type="text" id="REFUND_PRICE" name="REFUND_PRICE" value="" size="30" maxlength="50" title="환불금액"
					style="width:18%;background:#FFECEC;" /> 원  
				<input type="text" id="REFUND_DATE" name="REFUND_DATE" maxlength="10" size="10" class="i_text" value="" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_Submit();">저장</a></li>
      <li><a href="javascript:fn_Close();">닫기</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>

<!--//content --> 
</body>
</html>