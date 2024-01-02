<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

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
				alert("등록된 사용자 아이디입니다. 독서실 대여 신청할 수 있습니다.");
				$("#CHECKID").val($.trim($("#USER_ID").val()));
				return;
			}else{
				alert("등록되지 않은 아이디 입니다. 독서실 대여 신청 불가합니다.");
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
		if (confirm("독서실 신청정보를 저장하겠습니까?")) {
			$("#frm").attr("action", "<c:url value='/room/roomRentOrderProcess.do' />");
			$("#frm").submit();
		}
	}
	
	// 목록으로
	function fn_List() {
		$("#frm").attr("action",
				"<c:url value='/room/roomView.do' />");
		$("#frm").submit();
	}
	
	// 독서실 정보 삭제 
	function fn_Delete(){
		if(confirm("독서실 정보를 삭제하겠습니까?")){
			$("#frm").attr("action","<c:url value='/room/roomDeleteProcess.do' />");
			$("#frm").submit();
		}		
	}

	// 독서실 주문정보 조회
	function fn_view(orderId){
		$("#ORDERNO").val($.trim(orderId));
		$("#frm").attr("action","<c:url value='/room/roomOrderView.do' />");
		$("#frm").submit();
	}
	
	
	$(function() {
		setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
		initDateFicker1("RENT_START");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
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
		var sRoomFlag = '';
		for (i=0; i<frm.ROOM_FLAG.length; i++){
			if (frm.ROOM_FLAG[i].checked){
				sRoomFlag = frm.ROOM_FLAG[i].value;
			}
		}
		$.ajax({
			type: "GET", 
			url : '<c:url value="/room/updateRoomFlagAsync.do"/>?ROOM_CD=' + $("#ROOM_CD").val()+'&ROOM_NUM=' + $("#ROOM_NUM").val()+'&ROOM_FLAG=' + sRoomFlag ,
			dataType: "text",
			async : false,
			success: function(RES) {
				if($.trim(RES)=="Y"){
					alert("상태값이 수정되었습니다.");
					return;
				}
			},error: function(){
				alert("상태값 수정 실패!");
				return;
			}
		});	  
	}

	// 독서실 변경 
    function change_room(room_cd, room_num, rent_seq) {
	    var url = '<c:url value="/room/roomChangePop.pop"/>?USED_ROOM_CD='+room_cd;  // 기존독서실 코드
	    url += "&ROOM_CD=" + room_cd;   // 독서실 코드
	    url += "&USED_ROOM_NUM=" + room_num;   // 독서실 번호
	    url += "&USED_RENT_SEQ=" + rent_seq; /// 독서실 구매 번호
	    url += "&TOP_MENU_ID=" + $("#TOP_MENU_ID").val() + "&MENUTYPE=" + $("#MENUTYPE").val() + "&L_MENU_NM=" + $("#L_MENU_NM").val();  // 메뉴 정보
	    window.open(url,'roomChangeFrm','scrollbars=no,toolbar=no,resizable=yes,width=750,height=600');
    }
	
	function fn_Extend() {
		if(confirm('현재의 독서실을 연장하시겠습니까?')) {
			$("#frm").attr("action", "<c:url value='/room/roomExtendOrderProcess.do' />");
			$("#frm").submit();
		}
	}

	function fn_Refund() {
		window.open('<c:url value="/room/roomRefundPop.pop"/>?ORDER_ID=' + $("#ORDER_ID").val() + '&ROOM_CD=' + $("#ROOM_CD").val()+'&ROOM_NUM=' + $("#ROOM_NUM").val()+'&RENT_SEQ=' + 
				$("#RENT_SEQ").val()+'&TOP_MENU_ID=' + $("#TOP_MENU_ID").val()+'&MENUTYPE=' + $("#MENUTYPE").val()+'&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 
				'scrollbars=no,toolbar=no,resizable=yes,width=750,height=600');
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
	<input type="hidden" id="WMODE" name="WMODE" value="${WMODE}">
	<input type="hidden" id="ROOM_CD" name="ROOM_CD" value="${ROOM_CD}">
	<input type="hidden" id="ROOM_NUM" name="ROOM_NUM" value="${ROOM_NUM}">
	<input type="hidden" id="ROOM_NM" name="ROOM_NM" value="${roomnumrentdetail.ROOM_NM}">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="${RENT_SEQ}">
	<input type="hidden" id="ORDER_ID" name="ORDER_ID" value="${roomnumrentdetail.ORDER_ID}">
	<input type="hidden" id="ORDERNO" name="ORDERNO" value="${roomnumrentdetail.ORDER_ID}">
	<input type="hidden" id="CHECKID" name="CHECKID" value="${roomnumrentdetail.USERID}">
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="ROOMRENTWRITE">

	<h2>● 독서실 관리 &gt; 
		<c:choose>
		<c:when test="${empty roomnumrentdetail.USERID}">
			<strong>독서실 신청 신규 등록</strong>
		</c:when>
		<c:otherwise>
			<strong>독서실 신청 정보 변경</strong>
		</c:otherwise>
		</c:choose>
		</h2>	
    	<table class="table01">
   		<tr>
   			<th style="width:15%;">독서실명(번호)</th>
  			<td colspan="3">
	   			${ROOM_NM} (${ROOM_NUM}번) &nbsp;
	   			<a href="javascript:change_room('${ROOM_CD}','${ROOM_NUM}','${RENT_SEQ}');"><b>[독서실 변경]</b></a>
  			</td>
  		</tr>  		
   		<tr>
   			<th>사용자 아이디</th>
  			<td colspan="3">
  				<input type="text" id="USER_ID" name="USER_ID" value="${roomnumrentdetail.USERID}" size="20"  maxlength="13" title="회원아이디" style="width:18%;background:#FFECEC;"/> ${roomnumrentorderdetail.USER_NM}
	   			<input name="input" type="button" onClick="fn_IdCheck();" value="아이디 등록체크">
  			</td>
  		</tr>
   		<tr>
   			<th>사용 여부</th>
   			<c:if test="${empty roomnumrentdetail}"> <c:set var="roomFlag" value="N"> </c:set></c:if>
   			<c:if test="${!empty roomnumrentdetail}"> <c:set var="roomFlag" value="${roomnumrentdetail.ROOM_FLAG}"> </c:set></c:if>
  			<td colspan="3">
	  			<input type="radio" name="ROOM_FLAG" VALUE="Y" <c:if test="${roomFlag eq 'Y'}" > checked='checked' </c:if>/>사용 &nbsp;
	  			<input type="radio" name="ROOM_FLAG" VALUE="N" <c:if test="${roomFlag eq 'N'}" > checked='checked' </c:if>/>미사용 &nbsp;
	  			<input type="radio" name="ROOM_FLAG" VALUE="D" <c:if test="${roomFlag eq 'D'}" > checked='checked' </c:if>/>대기 &nbsp;
	  			<input type="radio" name="ROOM_FLAG" VALUE="H" <c:if test="${roomFlag eq 'H'}" > checked='checked' </c:if>/>홀드 &nbsp;
	  			<input type="radio" name="ROOM_FLAG" VALUE="X" <c:if test="${roomFlag eq 'X'}" > checked='checked' </c:if>/>고장 &nbsp;
	  			<a href="javascript:status_update();"><b>[상태값 변경]</b></a>
  			</td>
  		</tr>
   		<tr>
   			<th>대여 기간</th>
  			<td colspan="3">
              <input type="text" id="RENT_START" name="RENT_START" maxlength="10" size="10" class="i_text" value="${roomnumrentdetail.RENT_START }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="RENT_END" name="RENT_END" maxlength="10" size="10" class="i_text" value="${roomnumrentdetail.RENT_END }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
   		<tr>
   			<th>주문 정보</th>
  			<td colspan="3">
	   			주문번호 : ${roomnumrentorderdetail.ORDERNO} &nbsp;&nbsp; || &nbsp;&nbsp;주문일시 : ${roomnumrentorderdetail.REG_DT} ||
          &nbsp;&nbsp;주문방법 : <c:if test="${roomnumrentdetail.RENT_TYPE eq 'OFF'}">오프라인</c:if><c:if test="${roomnumrentdetail.RENT_TYPE eq 'ON'}">온라인</c:if>
  			</td>
  		</tr>
   		<tr>
   			<th style="width:15%;">결제 금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_TOTAL" name="PRICE_GET_TOTAL" value="${roomnumrentorderdetail.PRICE}"
					size="30" maxlength="50" title="결제 금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
   			<th style="width:15%;">할인 금액</th>
  			<td>
	   			<input type="text" id="PRICE_DISCOUNT_VALUE" name="PRICE_DISCOUNT_VALUE" value="${roomnumrentorderdetail.PRICE_DISCOUNT}"
					size="30" maxlength="50" title="할인 금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
  		</tr>
   		<tr>
   			<th>결제방법</th>
  			<td>
	  			<input type="radio" name="APPROVAL_KIND" VALUE="PAY110" <c:if test="${roomnumrentorderdetail.PAYCODE eq 'PAY110'}" > checked='checked' </c:if>/>카드 &nbsp;
	  			<input type="radio" name="APPROVAL_KIND" VALUE="PAY140" <c:if test="${roomnumrentorderdetail.PAYCODE eq 'PAY140'}" > checked='checked' </c:if>/>현금 &nbsp;
  			</td>
   			<th>신청구분</th>
  			<td>
	  			<input type="radio" name="RENT_TYPE" VALUE="ON" <c:if test="${roomnumrentdetail.RENT_TYPE eq 'ON'}" > checked='checked' </c:if>/>온라인 &nbsp;
	  			<input type="radio" name="RENT_TYPE" VALUE="OFF" <c:if test="${roomnumrentdetail.RENT_TYPE eq 'OFF'}" > checked='checked' </c:if>/>오프라인 &nbsp;
  			</td>
  		</tr>
   		<tr>
   			<th>카드 지불금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_CARD" name="PRICE_GET_CARD" value="${roomnumrentorderdetail.PRICE_CARD}"
					size="30" maxlength="50" title="카드 지불금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
   			<th>현금 지불금액</th>
  			<td>
	   			<input type="text" id="PRICE_GET_CASH" name="PRICE_GET_CASH" value="${roomnumrentorderdetail.PRICE_CASH}"
					size="30" maxlength="50" title="현금 지불금액"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원
  			</td>
  		</tr>
   		<tr>
   			<th colspan="3">할인 내역</th>
  			<td>
  				<input type="text" id="PRICE_DISCOUNT_REASON" name="PRICE_DISCOUNT_REASON" value="${roomnumrentorderdetail.PRICE_DISCOUNT_REASON}"
					size="50" maxlength="200" title="할인 내역"
					style="width:90%;background:#FFECEC;"/>
  			</td>
  		</tr>
   		<tr>
   			<th>대여 메모</th>
  			<td colspan="3">
	   			<textarea id="RENT_MEMO" name="RENT_MEMO" ROWS="3" maxlength="4000" style="width:75%;">${roomnumrentdetail.RENT_MEMO}</textarea>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_Submit();">저장</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
      <c:if test="${!empty roomnumrentorderdetail.ORDERNO}">
      <li><a href="javascript:fn_Extend();">연장</a></li>
      <li><a href="javascript:fn_Refund();">환불</a></li>
      </c:if>
    </ul>
    <!--//버튼--> 
</form>

<div id="contentRoomNum">
   	<!-- 독서실 loop -->
   	<table class="table02">
		<tr>
			<th width="85">아이디</th>
			<th>사용자명</th>
			<th>대여 시작일</th>
			<th>대여 종료일</th>
			<th>신청구분</th>
			<th>결제구분</th>
		</tr>
		<tbody>
			<c:if test="${not empty roomNumRentOrderList}">
				<c:forEach items="${roomNumRentOrderList}" var="list" varStatus="status">
					<tr>
						<td>${list.USERID}</td>
						<td><a href="javascript:fn_view('${list.ORDER_ID}')">${list.USER_NM}[${list.PHONE_NO}]</a></td>
						<td>${list.RENT_START}</td>
						<td>${list.RENT_END}</td>
						<td>${list.RENT_TYPE}</td>
						<td>${list.PAY_GUBUN}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty roomNumRentOrderList}">
				<tr bgColor=#ffffff align=center>
					<td colspan="6">독서실 대여 신청 내역이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
	</table>
</div>
</div>



<!--//content --> 
</body>

</html>