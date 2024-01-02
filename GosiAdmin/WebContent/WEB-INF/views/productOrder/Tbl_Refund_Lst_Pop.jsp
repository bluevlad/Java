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

var orderstatuscode = "${searchMap.orderstatuscode}";
var search_date_type = "${searchMap.search_date_type}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var payall = "${searchMap.payall}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

var playyn = "${searchMap.playyn}";
var MYLECTURE_PAUSED_COUNT = "${searchMap.MYLECTURE_PAUSED_COUNT}";
var STUDY_PERCENT = "${searchMap.STUDY_PERCENT}";
var DAY_PRICE = "${searchMap.DAY_PRICE}";
var START_DATE = "${searchMap.START_DATE}";


var REG_DT = "${searchMap2.REG_DT}";
var TO_DT = "${searchMap2.TO_DT}";
var approvals_payCodeName = "${searchMap2.approvals_payCodeName}";

window.onload = function () {
	
	/* alert("orderstatuscode:"+orderstatuscode +"\n"+
			"search_date_type:"+search_date_type +"\n"+
			"searchkey:"+searchkey +"\n"+
			"searchtype:"+searchtype +"\n"+
			"paycode:"+paycode +"\n"+			
			"payall:"+payall +"\n"+
			"sdate:"+sdate +"\n"+
			"edate:"+edate +"\n"+
			"currentPage:"+currentPage +"\n"+
			"pageRow:"+pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */
	
			/* alert("playyn:"+playyn +"\n"+
					"MYLECTURE_PAUSED_COUNT:"+MYLECTURE_PAUSED_COUNT +"\n"+
					"STUDY_PERCENT:"+STUDY_PERCENT +"\n"+
					"REG_DT:"+REG_DT +"\n"+
					"TO_DT:"+TO_DT +"\n"+
					"price:"+price +"\n"+
					"DAY_PRICE:"+DAY_PRICE +"\n"+
					"START_DATE:"+START_DATE +"\n"+
					"approvals_payCodeName:"+approvals_payCodeName);
			 */
	/* alert("course_type_code:"+course_type_code +"\n"+
			"mode:"+mode +"\n"+
			"cnt1:"+cnt1 +"\n"+
			"price:"+price +"\n"+
			"message:"+message +"\n"+
			"maxRefund:"+maxRefund); */
	
	if(message == "등록완료") {
		//opener.document.location.href = opener.document.URL;
		
		 window.opener.searchFrm.orderstatuscode.value=orderstatuscode;
		 window.opener.searchFrm.search_date_type.value=search_date_type;
		 window.opener.searchFrm.searchkey.value=searchkey;
		 window.opener.searchFrm.searchtype.value=searchtype;
		 window.opener.searchFrm.paycode.value=paycode;
		 window.opener.searchFrm.payall.value=payall;
		 window.opener.searchFrm.sdate.value=sdate;
		 window.opener.searchFrm.edate.value=edate;		 
		 window.opener.searchFrm.currentPage.value=currentPage;
		 window.opener.searchFrm.pageRow.value=pageRow;
		 
		 window.opener.searchFrm.TOP_MENU_ID.value=TOP_MENU_ID;
		 window.opener.searchFrm.MENUTYPE.value=MENUTYPE;
		 window.opener.searchFrm.L_MENU_NM.value=L_MENU_NM;
		 
		 window.opener.goList(currentPage);
		 
		self.close();
	}
	
	if(course_type_code == "") {
		course_type_code = "L";
	}
	
	if(point == "") {
		point = "0";
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
	
	
	//환불금액 자동계산	
	var toDate = new Date(TO_DT.substring(0,4),TO_DT.substring(4,6),TO_DT.substring(6,8));
	var regDate = new Date(REG_DT.substring(0,4),REG_DT.substring(4,6),REG_DT.substring(6,8));
	var DT = Math.floor((toDate.getTime() - regDate.getTime())/1000/60/60/24);

	var m_price = price;
	
	if(parseInt(DT) >= 7) {
		m_price = price - (price * 10/100);
		
		//alert("m_price1:"+m_price);
	}
	
	/* alert("m_price21:"+m_price +"\n"+
			"approvals_payCodeName:"+approvals_payCodeName); */
			
	// 수수료를 공제한 금액
	if(approvals_payCodeName == "PAY110") { //카드결제
		m_price = m_price - (parseFloat(m_price * 0.03157));
	}else if(approvals_payCodeName == "PAY120") { //가상계좌
		m_price = m_price - 280;
	}else if(approvals_payCodeName == "PAY130") { //계좌이체
		m_price = m_price - (parseFloat(m_price * 0.0187));
	}	
	// 수강료를 공제한 금액
	m_price = m_price - point; //공제한  수강료
	// 수강시작일부터 오늘까지의 수강일 수 계산
	var stDate = new Date(START_DATE.substring(0,4),START_DATE.substring(4,6),START_DATE.substring(6,8));
	var days = Math.floor((toDate.getTime() - stDate.getTime())/1000/60/60/24);

	// 수강한 기간의 금액 계산
	var TOTAL_PRICE = DAY_PRICE * days;  /* TO_DT - START_DATE */
	
	// 수수료와 포인트를 공제한 금액에서 이미 수강한 일수의 금액을 제외한 금액
	
	if(days < 0){
		TOTAL_PRICE = m_price;
	}else{
		TOTAL_PRICE = m_price - TOTAL_PRICE;
	}
	
	$('#price').val(parseInt(TOTAL_PRICE));
}

//금액 onchange 자동계산
function self_price(){
	//환불금액 자동계산	
	var toDate = new Date(TO_DT.substring(0,4),TO_DT.substring(4,6),TO_DT.substring(6,8));
	var regDate = new Date(REG_DT.substring(0,4),REG_DT.substring(4,6),REG_DT.substring(6,8));
	var DT = Math.floor((toDate.getTime() - regDate.getTime())/1000/60/60/24);
	
	/* alert("TO_DT:"+TO_DT +"\n"+
			"REG_DT:"+REG_DT +"\n"+
			"DT:"+DT); */
	
	var m_price = $('#price').val();
	
	if(parseInt(DT) >= 7) {
		m_price = $('#price').val() - ($('#price').val() * 10/100);
		
		//alert("m_price1:"+m_price);
	}
	
	/* alert("m_price21:"+m_price +"\n"+
			"approvals_payCodeName:"+approvals_payCodeName); */
	
	if(approvals_payCodeName == "PAY110") { //카드결제
		m_price = m_price - (parseFloat(m_price * 0.03157));
	}else if(approvals_payCodeName == "PAY120") { //가상계좌
		m_price = m_price - 280;
	}else if(approvals_payCodeName == "PAY130") { //계좌이체
		m_price = m_price - (parseFloat(m_price * 0.0187));
	}
	/* alert("m_price22:"+m_price);
	alert("point:"+point); */
	m_price = m_price - $('#point').val(); //공제 수강료
	//alert("m_price23:"+m_price);
	
	var stDate = new Date(START_DATE.substring(0,4),START_DATE.substring(4,6),START_DATE.substring(6,8));
	alert(stDate);
	var days = Math.floor((toDate.getTime() - stDate.getTime())/1000/60/60/24);
	var TOTAL_PRICE = DAY_PRICE * days;
	//alert("TOTAL_PRICE1:"+TOTAL_PRICE);
	
	TOTAL_PRICE = m_price - TOTAL_PRICE;
	//alert("TOTAL_PRICE2:"+TOTAL_PRICE);
	
	$('#price').val(parseInt(TOTAL_PRICE));
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

//등록버튼
function frmsubmit1(){
	
	if(parseInt(STUDY_PERCENT) >= 30) {
		alert("진도율 "+STUDY_PERCENT+"%입니다. 30%이상은 환불 불가합니다.");
//		return;
	}
	
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
	
	if(popFrm.playyn.checked == true){
		$('#playyn').val("N");
	}else{
		$('#playyn').val("Y");
	}
	
	//'모드가 update일때만 기존금액과 상이한지 검사하는 스크립트를 추가한다
	//'금액 이외에 메모내용만 바꿀경우가 있을것으로 사료하여 주석처리함
	//'if mode="update" Then
	
	if ($('#price').val() == "") {
		$("#price").val("0");
	}
	
	if ($('#point').val() == "") {
		$("#point").val("0");
	}
	
	$('#popFrm').attr('action','<c:url value="/productOrder/pop_refund_insert.do"/>').submit();
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
          
	          
    <table class="table05">
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
		    <p align="left">-<span>환불할 금액</span>을 입력하여 주세요<BR>
		      - 환불가능한 <span style="color: blue;">금액</span>은   <span style="color: blue;"><fmt:formatNumber value="${price_Sum}" groupingUsed="true"/>원</span> 미만으로 설정하셔야 합니다.<BR>
		      - 환불가능한 <span style="color: red;">포인트</span>는   <span style="color: red;">${searchMap.point}포인트</span> 미만으로 설정하셔야 합니다. <c:if test="${fn:substring(searchMap.mgntno, 0, 1) eq  'L'}">[적립된 도서 포인트는  <span style="color: red;">${PlusPoint }포인트</span> 입니다]</c:if><BR>
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
              <td><input type="text" name="price" id="price" value="" onkeyup="chk(this)" onblur="chk(this)" size="9" maxlength="9" style="width:80%;">원</td>
             <%--  <td><input type="text" name="point" id="point" value="${searchMap.point}" onkeyup="chk(this)" onblur="chk(this)" size="6" maxlength="6" style="width:80%;"></td> --%>
              <td><input type="text" name="point" id="point" value="" onkeyup="chk(this)" onblur="chk(this)" size="6" maxlength="6" style="width:80%;"></td>
              <td><input type="text" name="confirmdate" id="confirmdate" value="${searchMap.ch}" size="9" maxlength="9" style="width:90%;"></td>
                <jsp:scriptlet>
				pageContext.setAttribute("newline", "\n");
				</jsp:scriptlet>
		
              <c:set var="F_MEMO" value="일지중지 ${searchMap.MYLECTURE_PAUSED_COUNT}회 사용중입니다. ${newline}"/>
                <c:choose>
					<c:when test="${searchMap.STUDY_PERCENT >= 30 }">
						<td><textarea rows="2" cols="30" name="memo" id="memo">${F_MEMO}진도율 ${searchMap.STUDY_PERCENT}%입니다. 30% 이상은 환불 불가합니다.</textarea></td>
					</c:when>
					
					<c:otherwise>
						<td><textarea rows="2" cols="30" name="memo" id="memo">${F_MEMO}</textarea></td>
					</c:otherwise>
				</c:choose>
              
              <td>
	              <c:if test="${fn:substring(searchMap.mgntno, 0, 1) ne  'L'}">
	              	<input type="checkbox" name="playyn" id="playyn" value="N" checked="checked">
	              </c:if>
	          </td>              
            </tr>
            
        </tbody>
    </table>    
          
    <!--//테이블-->
	
	
	<!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:frmsubmit1();">환불등록</a></li>
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