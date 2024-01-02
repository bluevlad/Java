<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
var course_type_code = "${searchMap.course_type_code}";
var mode = "${searchMap.mode}";
var point = "${searchMap.point}";
var cnt1 = "${count}";

var price = "${searchMap2.price}";

var maxRefund = "${price_Sum}";
var message = "${message}";
var refund_point = "${searchMap.refund_point}";

window.onload = function () {
	
	/* alert("course_type_code:"+course_type_code +"\n"+
			"mode:"+mode +"\n"+
			"cnt1:"+cnt1 +"\n"+
			"price:"+price +"\n"+
			"message:"+message +"\n"+
			"refund_point:"+refund_point +"\n"+
			"maxRefund:"+maxRefund); */
	
	if(message == "등록완료") {
		opener.document.location.href = opener.document.URL;
		self.close();
	}
	
	if(course_type_code == "") {
		course_type_code = "L";
	}
	
	/* if(mode == "" || mode == null) {
		mode = "insert";
	} */
	
	if(!course_type_code == "D" && !course_type_code == "L") {
		alert("종합반, 패키지, 내마음대로는 부분환불을 하실 수 없습니다");
		self.close();
	}
	
	 if(parseInt(cnt1) > 0) {
		//mode="update";
		//$("#mode").val(mode);
		alert('해당주문건에 대하여 이미 환불한 내역이 존재합니다. 환불금액 수정모드로 처리합니다.');
	}//else if(parseInt(cnt1) > 0 || mode == "update"){
		//mode="update";
		//$("#mode").val(mode);
	//} 
	 
	 /* if(parseInt(maxRefund) <= 0) {		
		alert('해당주문건에 대한 환불가능금액은 없습니다.\n확인을 누르시면 창을 닫습니다.');
		self.close();
	} */
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "price") {
				$('#price').val("");      
				$('#price').focus();         
				return;
			}
			
			if(target == "point") {
				$('#point').val("");      
				$('#point').focus();         
				return;
			}
		}       
		else {          
			if (val < 1) {             
				/* if(target == "OFFCLOSEPERSONNUMBER") {
					$('#OFFCLOSEPERSONNUMBER').val("");      
					$('#OFFCLOSEPERSONNUMBER').focus();         
					return;
				}  */         
			}       
		}    
	}
}

//수정버튼
function frmsubmit1(){
	if ($('#price').val() == "" && $('#point').val() == "") {
		alert("금액 또는 포인트에 적절한 값을 넣어주세요");
		$('#price').focus();
		return;
	}
	
	//완전환불이 가능하게 변경하므로써 미만에서 이하로 바꿈
	if($('#price').val() > parseInt(maxRefund)){
		alert("부분환불 가능 금액은 "+maxRefund+"원 이하여야 합니다.\n적절한 금액을 재입력해주세요");
		$('#price').focus();
		return;
	}
	
	if($('#point').val() > parseInt(point)){
		alert("부분환불 가능 포인트는 "+point+"포인트 이하여야 합니다.\n적절한 포인트를 재입력해주세요");
		$('#point').focus();
		return;
	}

	//'모드가 update일때만 기존금액과 상이한지 검사하는 스크립트를 추가한다
	//'금액 이외에 메모내용만 바꿀경우가 있을것으로 사료하여 주석처리함
	//'if mode="update" Then
	
	if(popFrm.playyn.checked == true){
		$('#playyn').val("N");
	}else{
		$('#playyn').val("Y");
	}
	
	if ($('#price').val() == "") {
		$("#price").val("0");
	}
	
	if ($('#point').val() == "") {
		$("#point").val("0");
	}
	var point_gap = parseInt($('#point').val()) - parseInt(refund_point);
	$("#point_gap").val(point_gap);
	
	$('#popFrm').attr('action','<c:url value="/productOrder/pop_refund_insert.do"/>').submit();
}

//삭제
function frmsubmit_del(){
	
	if (confirm("해당 환불내역을 삭제하시겠습니까?"))
	{
		if(popFrm.playyn.checked == true){
			$('#playyn').val("N");
		}else{
			$('#playyn').val("Y");
		}
		
		$("#mode").val("delete");
		$('#popFrm').attr('action','<c:url value="/productOrder/pop_refund_insert.do"/>').submit();
	}
}
</script>
</head>


<!--팝업-->
<form id="popFrm" name="popFrm" method="post">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
<input type="hidden" id="MANAGER_ID" name="MANAGER_ID" value="${menuParams.MANAGER_ID}" />								  
<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<input type="hidden" id="mode" name="mode" value="${searchMap.mode}" />
<input type="hidden" id="orderno" name="orderno" value="${searchMap.orderno}" />
<input type="hidden" id="mgntno" name="mgntno" value="${searchMap.mgntno}" />
<input type="hidden" id="orders_userid" name="orders_userid" value="${searchMap.orders_userid}" />
<input type="hidden" id="refund_point" name="refund_point" value="${searchMap.refund_point}" />
<input type="hidden" id="point_gap" name="point_gap" />

<table style="width:100%;">
	<tr>
	<td width="3%">
	</td>
	<td width="94%">
	<table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>[환불처리화면]</p></td>
      </tr>
    </table>
    
	<!--테이블-->
          
    <table class="table05" width="100%">
        <tr>          
          <th>주문번호</th>
          <th>주문자</th>
          <th>입금자</th>
          <th>상태</th>
          <th>상품</th>
          <th>구입금액</th>
          <th>상태</th>
        </tr>
        <tbody>
              
            <tr>
              <td>${searchMap2.orderNo}</td>
              
              <td>${searchMap2.USER_NM}<BR>${searchMap2.USER_ID}</td>
              
              <td>${searchMap2.payName}</td>
              
              <td>${searchMap2.approvals_payCodeName}</td>
              
              <td style="text-align:left;">${searchMap2.name}</td>
              
              <td><fmt:formatNumber value="${searchMap2.price}" type="currency" />원</td>
                            
              <td>
           		<c:choose>
					<c:when test="${searchMap2.statusName eq 'DLV100'}">
						<c:set var="status_code" value="무통장입금"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV105'}">
						<c:set var="status_code" value="입금완료"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV110'}">
						<c:set var="status_code" value="배송준비중"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV120'}">
						<c:set var="status_code" value="배송중"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV130'}">
						<c:set var="status_code" value="배송완료"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV140'}">
						<c:set var="status_code" value="취소요청"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV150'}">
						<c:set var="status_code" value="취소완료"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV160'}">
						<c:set var="status_code" value="교환요청"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV170'}">
						<c:set var="status_code" value="교환배송중"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV180'}">
						<c:set var="status_code" value="교환완료"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV220'}">
						<c:set var="status_code" value="환불요청"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV230'}">
						<c:set var="status_code" value="환불완료"/>
					</c:when>
					
					<c:when test="${searchMap2.statusName eq 'DLV240'}">
						<c:set var="status_code" value="단과수강취소"/>
					</c:when>
				</c:choose>
				
				${status_code}
              </td>	              
            </tr>
        </tbody>
    </table>    
          
    <!--//테이블-->
    
    <c:if test="${price_Sum <= 0}">
		<c:set var="price_Sum" value="0"/>
	</c:if>
	
	<c:if test="${price_Sum > searchMap2.price}">
		<c:set var="price_Sum" value="${searchMap2.price}"/>
	</c:if>
	
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td height="50" align="center" bgcolor="#FFFFFF"><p>&nbsp;</p>
		    <p align="left">- <span>환불할 금액</span>을 입력하여 주세요<BR>
		      - 환불가능한 <span style="color: blue;">금액</span>은   <span style="color: blue;"><fmt:formatNumber value="${price_Sum}" groupingUsed="true"/>원</span> 미만으로 설정하셔야 합니다.<BR>
		      - 환불가능한 <span style="color: red;">포인트</span>는   <span style="color: red;">${searchMap.point}포인트</span> 미만으로 설정하셔야 합니다.<BR>
		      - 만약<span> 전액환불인 경우 주문관리의 상태코드변경을   이용</span>해주세요.<BR>
	          - <span>환불일자</span>가 <span>오늘</span>인 경우 환불일자는 비워두셔도 됩니다. </p></td>
		</tr>
	 </table>

	 <!--테이블-->
          
    <table class="table05">
        <tr>          
          <th width="17%">환불 금액</th>
          <th width="15%">포인트</th>
          <th width="15%">환불일자</th>
          <th width="40%">메모</th>
          <th width="13%">강좌중지여부</th>       
        </tr>
        <tbody>
            <tr>
              <td><input type="text" name="price" id="price" value="${searchMap3.oldRefund}" onkeyup="chk(this)" onblur="chk(this)" size="9" maxlength="9" style="width:70%;">원</td>
              <td><input type="text" name="point" id="point" value="" onkeyup="chk(this)" onblur="chk(this)" size="9" maxlength="9" style="width:70%;"><br>* 환불된 포인트 : ${searchMap.refund_point}</td>
              <td><input type="text" name="confirmdate" id="confirmdate" value="${searchMap3.confirmDate}" size="9" maxlength="9" style="width:90%;"></td>
              <td><textarea rows="2" cols="30" name="memo" id="memo">${searchMap3.memo}</textarea></td>
              <td>
	              <c:if test="${fn:substring(searchMap.mgntno, 0, 1) ne  'L'}">
	              	<input type="checkbox" name="playyn" id="playyn" value="N" <c:if test="${searchMap.playyn == 'N' }">checked="checked"</c:if>>
	              </c:if>
	          </td>
            </tr>
        </tbody>
    </table>
    
    <!--//테이블-->
	
	<!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:frmsubmit1();">수정</a></li>
   	  <li><a href="javascript:frmsubmit_del();">삭제</a></li>
   	  <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->

	</td>
	<td width="3%">
	</td>
	</tr>
</table>
</form>
</html>