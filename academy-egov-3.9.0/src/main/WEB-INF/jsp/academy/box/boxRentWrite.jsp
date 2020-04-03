<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">

//아이디 중복체크
function fn_IdCheck(){
	if($.trim($("#USER_ID").val())==""){
		alert("사용자 아이디를 입력해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	if($.trim($("#USER_ID").val()).length < 4){
		alert("아이디는 4자 이상 13자 이하로 구성해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	$.ajax({
		type: "GET", 
		url : '<c:url value="/memberManagement/idCheck.do"/>?USER_ID=' + $.trim($("#USER_ID").val()),
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="N"){
				alert("등록된 사용자 아이디입니다. 사물함 대여 신청할 수 있습니다.");
				$("#CHECKID").val($.trim($("#USER_ID").val()));
				return;
			}else{
				alert("등록되지 않은 아이디 입니다. 사물함 대여 신청 불가합니다.");
				$("#CHECKID").val("");
				return;
			}
		},error: function(){
			alert("아이디 등록 체크 실패");
			return;
		}
	});		
}


//등록
function fn_Submit() {
		if($.trim($("#USER_ID").val())==""){
			alert("아이디를 입력해주세요");
			$("#USER_ID").focus();
			return;
		}
		if($.trim($("#USER_ID").val()).length < 4){
			alert("아이디는 4자 이상 13자 이하로 구성해주세요");
			$("#USER_ID").focus();
			return;
		}	
		if($.trim($("#CHECKID").val())!=$.trim($("#USER_ID").val())){
			alert("아이디 등록체크를 해주세요");
			return;
		}
		if($.trim($("#RENT_START").val())==""){
			alert("대여 시작일을 입력해주세요");
			$("#RENT_START").focus();
			return;
		}
		if($.trim($("#RENT_END").val())==""){
			alert("대여 종료일을 입력해주세요");
			$("#RENT_END").focus();
			return;
		}
		if ($.trim($("#PRICE_GET_TOTAL").val()) == "") {
			alert("결재금액을 입력해 주세요.");
			$("#PRICE_GET_TOTAL").focus();
			return;
		}
		if ($.trim($("#DEPOSIT").val()) == "") {
			alert("예치금을 입력해 주세요.");
			$("#DEPOSIT").focus();
			return;
		}

		if (confirm("사물함 신청정보를 저장하겠습니까?")) {
			$("#frm").attr("action", "<c:url value='/box/boxRentOrderProcess.do' />");
			$("#frm").submit();
		}
	}
	
	// 목록으로
	function fn_List() {
		$("#frm").attr("action",
				"<c:url value='/box/boxView.do' />");
		$("#frm").submit();
	}
	
	// 사물함 정보 삭제 
	function fn_Delete(){
		if(confirm("사물함 정보를 삭제하겠습니까?")){
			$("#frm").attr("action","<c:url value='/box/boxDeleteProcess.do' />");
			$("#frm").submit();
		}		
	}
	
	$(function() {
		setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
		initDateFicker1("RENT_START");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
		initDateFicker1("RENT_END");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		// 아이디를 변경했을 때는 아이디 등록체크 여부를 확인하도록 세팅함.
		$('#USER_ID').keyup(function () { $("#CHECKID").val("");});
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
	
	function status_update() {
		$("#frm").attr("action","<c:url value='/box/updateBoxFlagAsync.do' />");
		$("#frm").submit();
	}

	// 사물함 변경 
    function change_box(box_cd, box_num, rent_seq) {
       	window.open('<c:url value="/box/boxChange.pop"/>?USED_BOX_CD='+box_cd+'&BOX_CD='+box_cd+'&USED_BOX_NUM='+box_num+'&USED_RENT_SEQ='+rent_seq+'&TOP_MENU_ID=<c:out value="${params.TOP_MENU_ID}"/>&MENU_ID=<c:out value="${params.MENU_ID}"/>', '_boxChangeFrm', 'location=no,resizable=yes,width=800,height=500,top=0,left=0,scrollbars=yes,location=no');
    }
	
	function fn_Extend() {
		if(confirm('현재의 사물함을 연장하시겠습니까?')) {
			$("#frm").attr("action", "<c:url value='/box/boxExtendOrderProcess.do' />");
			$("#frm").submit();
		}
	}

	// 사물함 주문정보 조회
	function fn_view(orderId){
		$("#ORDERNO").val(orderId);
		$("#frm").attr("action","<c:url value='/box/boxOrderView.do' />");
		$("#frm").submit();
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
	<!-- <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong> -->
	<h2>● 사물함관리 > <strong>사물함관리</strong>

	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="WMODE" name="WMODE" value="${WMODE}">
	<input type="hidden" id="BOX_CD" name="BOX_CD" value="${params.BOX_CD}">
	<input type="hidden" id="BOX_NUM" name="BOX_NUM" value="${params.BOX_NUM}">
	<input type="hidden" id="BOX_NM" name="BOX_NM" value="${boxNumRentDetail.BOX_NM}">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="${params.RENT_SEQ}">
	<input type="hidden" id="ORDER_ID" name="ORDER_ID" value="${boxNumRentDetail.ORDER_ID}">
	<input type="hidden" id="ORDERNO" name="ORDERNO" value="${boxNumRentDetail.ORDER_ID}">
	<input type="hidden" id="LOW_NUM" name="LOW_NUM" value="${boxNumRentDetail.LOW_NUM}">
	<input type="hidden" id="CHECKID" name="CHECKID" value="${boxNumRentDetail.USERID}">
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="BOXRENTWRITE">
	</h2>	

		<c:choose>
		<c:when test="${WMODE == 'EDT'}">
			<c:set var="PRICE_GET_TOTAL" value="${boxNumRentOrderDetail.PRICE}"/>
			<c:set var="DEPOSIT" value="${boxNumRentDetail.DEPOSIT}"/>
		</c:when>
		<c:otherwise>
			<c:set var="PRICE_GET_TOTAL" value="${boxDetail.BOX_PRICE}"/>
			<c:set var="DEPOSIT" value="${boxDetail.DEPOSIT}"/>
		</c:otherwise>
		</c:choose>
	
    	<table class="table01">
   		<tr>
   			<th style="width:15%;">사물함명(번호)</th>
  			<td colspan="3">
	   			${params.BOX_NM} (${params.BOX_NUM}번) &nbsp;
	   			<a href="javascript:change_box('${params.BOX_CD}','${params.BOX_NUM}','${boxNumRentDetail.RENT_SEQ}');"><b>[사물함 변경]</b></a>
  			</td>
  		</tr>
   		<tr>
   			<th>사용자 아이디</th>
  			<td colspan="3">
  				<input type="text" id="USER_ID" name="USER_ID" value="${boxNumRentDetail.USERID}" size="20"  maxlength="13" title="회원아이디" style="width:18%;background:#FFECEC;"/> ${boxnumrentorderdetail.USER_NM}
	   			<input name="input" type="button" onClick="fn_IdCheck();" value="아이디 등록체크">
  			</td>
  		</tr>
   		<tr>
   			<th>사용 여부</th>
   			<c:if test="${empty boxNumRentDetail}"> <c:set var="boxFlag" value="N"> </c:set></c:if>
   			<c:if test="${!empty boxNumRentDetail}"> <c:set var="boxFlag" value="${boxNumRentDetail.BOX_FLAG}"> </c:set></c:if>
  			<td colspan="3">
	  			<input type="radio" name="BOX_FLAG" VALUE="Y" <c:if test="${boxFlag eq 'Y'}" > checked='checked' </c:if>/>사용 &nbsp;
	  			<input type="radio" name="BOX_FLAG" VALUE="N" <c:if test="${boxFlag eq 'N'}" > checked='checked' </c:if>/>미사용 &nbsp;
	  			<input type="radio" name="BOX_FLAG" VALUE="D" <c:if test="${boxFlag eq 'D'}" > checked='checked' </c:if>/>대기 &nbsp;
	  			<input type="radio" name="BOX_FLAG" VALUE="H" <c:if test="${boxFlag eq 'H'}" > checked='checked' </c:if>/>홀드 &nbsp;
	  			<input type="radio" name="BOX_FLAG" VALUE="X" <c:if test="${boxFlag eq 'X'}" > checked='checked' </c:if>/>고장 &nbsp;
	  			<a href="javascript:status_update();"><b>[상태값 변경]</b></a>
  			</td>
  		</tr>
   		<tr>
   			<th>대여 기간</th>
  			<td colspan="3">
              <input type="text" id="RENT_START" name="RENT_START" maxlength="10" size="10" class="i_text" value="${boxNumRentDetail.RENT_START }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="RENT_END" name="RENT_END" maxlength="10" size="10" class="i_text" value="${boxNumRentDetail.RENT_END }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
   		<tr>
   			<th>주문 정보</th>
  			<td colspan="3">
	   			주문번호 : ${boxNumRentOrderDetail.ORDERNO} &nbsp;&nbsp; || &nbsp;&nbsp;주문일시 : ${boxNumRentOrderDetail.REG_DT} ||
          &nbsp;&nbsp;주문방법 : <c:if test="${boxNumRentOrderDetail.RENT_TYPE eq 'OFF'}">오프라인</c:if><c:if test="${boxNumRentOrderDetail.RENT_TYPE eq 'ON'}">온라인</c:if>
  			</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">결제 금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_TOTAL" name="PRICE_GET_TOTAL" value="${PRICE_GET_TOTAL}"
					size="30" maxlength="50" title="결제 금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
   			<th style="width:15%;">할인 금액</th>
  			<td>
	   			<input type="text" id="PRICE_DISCOUNT_VALUE" name="PRICE_DISCOUNT_VALUE" value="${boxNumRentOrderDetail.PRICE_DISCOUNT}"
					size="30" maxlength="50" title="할인 금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
  		</tr>
   		<tr>
   			<th>카드 지불금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_CARD" name="PRICE_GET_CARD" value="${boxNumRentOrderDetail.PRICE_CARD}"
					size="30" maxlength="50" title="카드 지불금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
   			<th>현금 지불금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_CASH" name="PRICE_GET_CASH" value="${boxNumRentOrderDetail.PRICE_CASH}"
					size="30" maxlength="50" title="현금 지불금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
  		</tr>
   		<tr>
   			<th>할인 내역</th>
  			<td colspan="3">
	   			<textarea id="PRICE_DISCOUNT_REASON" name="PRICE_DISCOUNT_REASON" ROWS="3" maxlength="4000" style="width:75%;">${boxNumRentOrderDetail.PRICE_DISCOUNT_REASON}</textarea>
  			</td>
  		</tr>
   		<tr>
   			<th>예치금</th>
  			<td>
	   			<input type="text" id="DEPOSIT" name="DEPOSIT" value="${DEPOSIT}"
					size="30" maxlength="50" title="예치금"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
   			<th>예치금 반환 여부</th>
  			<td>
	  			<input type="radio" name="DEPOSIT_YN" VALUE="Y" <c:if test="${boxNumRentDetail.DEPOSIT_YN eq 'Y'}" > checked='checked' </c:if>/>반환 &nbsp;
	  			<input type="radio" name="DEPOSIT_YN" VALUE="N" <c:if test="${boxNumRentDetail.DEPOSIT_YN eq 'N'}" > checked='checked' </c:if>/>미반환 &nbsp;
  			</td>
  		</tr>
   		<tr>
   			<th>결제방법</th>
  			<td>
	  			<input type="radio" name="APPROVAL_KIND" VALUE="PAY110" <c:if test="${boxNumRentOrderDetail.PAYCODE eq 'PAY110'}" > checked='checked' </c:if>/>카드 &nbsp;
	  			<input type="radio" name="APPROVAL_KIND" VALUE="PAY140" <c:if test="${boxNumRentOrderDetail.PAYCODE eq 'PAY140'}" > checked='checked' </c:if>/>현금 &nbsp;
  			</td>
   			<th>신청구분</th>
  			<td>
	  			<input type="radio" name="RENT_TYPE" VALUE="ON" <c:if test="${boxNumRentDetail.RENT_TYPE eq 'ON'}" > checked='checked' </c:if>/>온라인 &nbsp;
	  			<input type="radio" name="RENT_TYPE" VALUE="OFF" <c:if test="${boxNumRentDetail.RENT_TYPE eq 'OFF'}" > checked='checked' </c:if>/>오프라인 &nbsp;
  			</td>
  		</tr>
   		<tr>
   			<th>키반납여부</th>
  			<td>
	  			<input type="radio" name="KEY_YN" VALUE="R" <c:if test="${boxNumRentDetail.KEY_YN eq 'R'}" > checked='checked' </c:if>/>대여 &nbsp;
	  			<input type="radio" name="KEY_YN" VALUE="Y" <c:if test="${boxNumRentDetail.KEY_YN eq 'Y'}" > checked='checked' </c:if>/>반납 &nbsp;
	  			<input type="radio" name="KEY_YN" VALUE="N" <c:if test="${boxNumRentDetail.KEY_YN eq 'N'}" > checked='checked' </c:if>/>미반납 &nbsp;
  			</td>
   			<th>연장여부</th>
  			<td>
	  			<input type="radio" name="EXTEND_YN" VALUE="Y" <c:if test="${boxNumRentDetail.EXTEND_YN eq 'Y'}" > checked='checked' </c:if>/>Yes &nbsp;
	  			<input type="radio" name="EXTEND_YN" VALUE="N" <c:if test="${boxNumRentDetail.EXTEND_YN eq 'N'}" > checked='checked' </c:if>/>No &nbsp;
  			</td>
  		</tr>
   		<tr>
   			<th>대여 정보</th>
  			<td colspan="3">
	   			<textarea id="RENT_MEMO" name="RENT_MEMO" ROWS="3" maxlength="4000" style="width:75%;">${boxNumRentDetail.RENT_MEMO}</textarea>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_Submit();">저장</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
      <c:if test="${!empty boxNumRentOrderDetail.ORDERNO}">
      <li><a href="javascript:fn_Extend();">연장</a></li>
      </c:if>
    </ul>
    <!--//버튼--> 
</form>

	<div id="contentBoxNum">
   	<!-- 사물함 loop -->
   	<table class="table02">
		<tr>
			<th width="85">아이디</th>
			<th>사용자명</th>
			<th>대여 시작일</th>
			<th>대여 종료일</th>
			<th>예치금반환여부</th>
			<th>키반납여부</th>
			<th>연장여부</th>
			<th>신청구분</th>
			<th>결제구분</th>
		</tr>
		<tbody>
			<c:if test="${not empty boxNumRentOrderList}">
				<c:forEach items="${boxNumRentOrderList}" var="list" varStatus="status">
					<tr>
						<td>${list.USERID}</td>
						<td><a href="javascript:fn_view('${list.ORDERNO}')">${list.USER_NM}[${list.PHONE_NO}]</a></td>
						<td>${list.RENT_START}</td>
						<td>${list.RENT_END}</td>
						<td>${list.DEPOSIT_YN}</td>
						<td>${list.KEY_YN}</td>
						<td>${list.EXTEND_YN}</td>
						<td>${list.RENT_TYPE}</td>
						<td>${list.PAY_GUBUN}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty boxNumRentOrderList}">
				<tr bgColor=#ffffff align=center>
					<td colspan="9">사물함 대여 신청 내역이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
	</table>
	</div>
</div>
<!--//content --> 
</body>
</html>