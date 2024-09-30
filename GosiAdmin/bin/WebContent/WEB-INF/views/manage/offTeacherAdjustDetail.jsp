<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<style type="text/css">
 .noborder, .noborder tr, .noborder th, .noborder td
 {
 	border: none;
 }
</style>

<script type="text/javascript">
//var message = "${searchMap.message}";
//alert("message:"+message);
var isNew = 'Y';
<c:if test="${item.SETTLE_DT ne null}">
	isNew = 'N';
</c:if>
var detaili = 0; 

$(document).ready(function(){

	$('#WITHHOLDRATIO').keyup(function() {
		// 실지급액 계산
		calcurateAdjustAmaunt();
	});
	
	$('#TEACHERPAY').change(function() {
		// 실지급액 계산
		calcurateAdjustAmaunt();
	});
	
	addDeductItem();
	addDeductEtcItem();
	
	 calcurateTeacherAmount();
	 onChangeCalcucriteria();
	
});

// 강사료산출 버튼 클릭
function confirmAdjustAmaunt() {
	
	calcurateAdjustAmaunt();
	$("#hIsConfirmed").val("Y");
}
// 추가사항 P/M 변경, 금액 변경시 호출
function onChangeDeduction(idx){
//	alert($('#DeductItem_'+idx+'').children('#ADDTYPE').val());
//	alert($('#DeductItem_'+idx+'').children('#ADDMONEY').val());
	var tvalue = 0;
	if ($('#DeductItem_'+idx+'').children('#ADDTYPE').val() == 'P') {
		tvalue = Number($('#DeductItem_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItem_'+idx+'').children('#hADDMONEY').val(Math.abs(tvalue));
	} else {
		tvalue = Number($('#DeductItem_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItem_'+idx+'').children('#hADDMONEY').val(Math.abs(tvalue)*-1);
	}
//	alert($('#DeductItem_'+idx+'').children('#hADDMONEY').val());
	calcurateTeacherAmount();
}

// 대상금액을 자동 계산한다.
function calcurateTeacherAmount() {

	var iSum = 0;
	$("input[name=hADDMONEY]").each(function (index){
		iSum += Number($(this).val());
	});
	
	var tSum = Number(iSum) + Number($("#hAMOUNT").val());
	$("#TEACHERAMOUNT").val(tSum);
	$("#hTEACHERAMOUNT").val(tSum);

}

// 신규 추가사항 P/M 변경, 금액 변경시 호출
function onChangeDeductionn(idx){
//	alert($('#DeductItemn_'+idx+'').children('#ADDTYPE').val());
//	alert($('#DeductItemn_'+idx+'').children('#ADDMONEY').val());

	var tvalue = 0;
	if ($('#DeductItemn_'+idx+'').children('#ADDTYPE').val() == 'P') {
		tvalue = Number($('#DeductItemn_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItemn_'+idx+'').children('#hADDMONEY').val(Math.abs(tvalue));
	} else {
		tvalue = Number($('#DeductItemn_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItemn_'+idx+'').children('#hADDMONEY').val(Math.abs(tvalue)*-1);
	}
//	alert($('#DeductItemn_'+idx+'').children('#hADDMONEY').val());
	calcurateTeacherAmount();
}


// 기타 추가사항 P/M 변경, 금액 변경시 호출
function onChangeDeductione(idx){
//	alert($('#DeductItem_'+idx+'').children('#ADDTYPE').val());
//	alert($('#DeductItem_'+idx+'').children('#ADDMONEY').val());

	var tvalue = 0;
	if ($('#DeductItem_'+idx+'').children('#ADDTYPE').val() == 'P') {
		tvalue = Number($('#DeductItem_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItem_'+idx+'').children('#hADDMONEYE').val(Math.abs(tvalue));
	} else {
		tvalue = Number($('#DeductItem_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItem_'+idx+'').children('#hADDMONEYE').val(Math.abs(tvalue)*-1);
	}
	calcurateAdjustAmaunt();
}

//  실지급액을 자동 계산한다.
function calcurateAdjustAmaunt() {

	// 원천징수 계산 
	var iTemp = Number($("#TEACHERPAY").val()) * Number($("#WITHHOLDRATIO").val()) / 100; 
	$("#WITHHOLDTAX").val(Math.floor(iTemp));
		
	// 기타 추가사항
	var iSum = 0;
	$("input[name=hADDMONEYE]").each(function (index){
		iSum += Number($(this).val());
	});
	
	// 실지금액 = 정산기준 계산금액 - 원천징수 - 기타 추가사항 금액
	var tSum = Number($("#TEACHERPAY").val()) - Number($("#WITHHOLDTAX").val()) + Number(iSum);
	$("#ADJUSTAMOUNT").val(tSum);
}

//신규 추가사항 P/M 변경, 금액 변경시 호출
function onChangeDeductionne(idx){
	var tvalue = 0;
	if ($('#DeductItemn_'+idx+'').children('#ADDTYPE').val() == 'P') {
		tvalue = Number($('#DeductItemn_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItemn_'+idx+'').children('#hADDMONEYE').val(Math.abs(tvalue));
	} else {
		tvalue = Number($('#DeductItemn_'+idx+'').children('#ADDMONEY').val());
		$('#DeductItemn_'+idx+'').children('#hADDMONEYE').val(Math.abs(tvalue)*-1);
	}
	calcurateAdjustAmaunt();
}


// 추가사항 추가버튼 클릭시 
function addDeductItem() { 
	++detaili; 
	msg = "<div id=DeductItemn_"+detaili+">";
	msg += "<input type='hidden' id='PSA_NO' name='PSA_NO' value='0' /> ";
	msg += "<select id='ADDTYPE' name='ADDTYPE' onChange=onChangeDeductionn("+detaili+");>";
	msg += "	<option value='M'>-</option>";
	msg += "	<option value='P'>+</option>";
	msg += "</select>&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='text' id='ADDMEMO' name='ADDMEMO'  value='' style='width:200px;' />&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='text' id='ADDMONEY' name='ADDMONEY' value='0' onKeyup=onChangeDeductionn("+detaili+"); style='width:70px;' />원&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='hidden' id='hADDMONEY' name='hADDMONEY' value='0' /> ";
	msg += "<input type='hidden' id='ETCYN' name='ETCYN'  value='N'/>&nbsp;&nbsp;&nbsp;";
	msg += "<input type='button' onclick='javascript:deleteDeductionn("+detaili+");' value='삭제' />";
	msg += "</div>";
	
	//  기존 추가사항 목록에 추가한다.
	DeductListDiv.innerHTML += msg; 
}
// 기타추가사항 추가버튼 클릭시
function addDeductEtcItem() { 
	++detaili; 
	msg = "<div id=DeductItemn_"+detaili+">";
	msg += "<input type='hidden' id='PSA_NO' name='PSA_NO' value='0' /> ";
	msg += "<select id='ADDTYPE' name='ADDTYPE' onChange=onChangeDeductionne("+detaili+");>";
	msg += "	<option value='M'>-</option>";
	msg += "	<option value='P'>+</option>";
	msg += "</select>&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='text' id='ADDMEMO' name='ADDMEMO'  value='' style='width:200px;' />&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='text' id='ADDMONEY' name='ADDMONEY'  value='0' onKeyup=onChangeDeductionne("+detaili+"); style='width:70px;' />원&nbsp;&nbsp;&nbsp;&nbsp;";
	msg += "<input type='hidden' id='hADDMONEYE' name='hADDMONEYE' value='0' /> ";
	msg += "<input type='hidden' id='ETCYN' name='ETCYN'  value='Y'/>&nbsp;&nbsp;&nbsp;";
	msg += "<input type='button' onclick='javascript:deleteDeductionn("+detaili+");' value='삭제' />";
	msg += "</div>";
	
	//  기존 기타추가사항 목록에 추가한다.
	DeductEtcListDiv.innerHTML += msg; 
}

//(기타)추가사항 항목을 삭제하기
function deleteDeduction(idx) {
	 $('#DeductItem_'+idx+'').remove();
	 calcurateTeacherAmount();
	 calcurateAdjustAmaunt();
}
//(기타)추가사항 항목을 삭제하기
function deleteDeductionn(idx) {
	 $('#DeductItemn_'+idx+'').remove();
	 calcurateTeacherAmount();
	 calcurateAdjustAmaunt();
}

// 정산기준 단과반/ 종합반 변경시
function onChangeCalcucriteria(){
	var tDAmt = Number($('#hDAMOUNT').val());
	var tJAmt = Number($('#hJAMOUNT').val());
	var tDvalue = Number($('#CALCUCRITERIA_DVALUE').val());
	var tJvalue = Number($('#CALCUCRITERIA_JVALUE').val());

	var result = 0;
	if ($('#CALCUCRITERIA_DTYPE').val() == '1') { // 단과반 정산 비율(%)	
		result = Math.floor(tDAmt * tDvalue / 100);
	} else if ($('#CALCUCRITERIA_DTYPE').val() == '2') { // 시급(원)		
		result = Math.floor(tDAmt - tDvalue);
	} else if ($('#CALCUCRITERIA_DTYPE').val() == '3') { // 월정액(원)		
		result = Math.floor(tDAmt - tDvalue);
	}
	if ($('#CALCUCRITERIA_JTYPE').val() == '1') { // 종합반 정산 비율(%)	
		result += Math.floor(tJAmt * tJvalue / 100); 
	} else if ($('#CALCUCRITERIA_JTYPE').val() == '2') { // 시급(원)		
		result += Math.floor(tJAmt - tJvalue);
	} else if ($('#CALCUCRITERIA_JTYPE').val() == '3') { // 월정액(원)		
		result += Math.floor(tJAmt - tJvalue);
	}
	
	$('#TEACHERPAY').val(result);
	
	calcurateAdjustAmaunt();

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

//목록
function goList() {	
	$('#searchFrm').attr('action','<c:url value="/offmng/teacher/offTeacherAdjustList.do"/>').submit();
	
}

//저장
function goSave() {
	if ($('#hIsConfirmed').val() == "N") {
		alert('강사료 산출 버튼을 클릭해 주세요.');
		return;
	}

	$('#searchFrm').attr('action','<c:url value="/offmng/teacher/offTeacherAdjustInsert.do"/>').submit();
}

// 수강내역 엑셀 저장
function saveExcelOrders() {
	$('#oFrm').attr('action','<c:url value="/offmng/teacher/offTeacherOrdersExcel.do"/>').submit();
}

// 강사 정산내역 엑셀 저장
function TeacherAdjustExcelDownload() {
	$('#oFrm').attr('action','<c:url value="/offmng/teacher/offTeacherAdjustExcelDownload.do"/>').submit();
}

</script>
</head>

<!--content -->
<div id="content">
<form name="oFrm" id="oFrm" method="post">
<input type="hidden" id="searchTeacher" name="searchTeacher" value="${params.searchTeacher}"/>
<input type="hidden" id="searchLeccode" name="searchLeccode" value="${params.searchLeccode}"/>
<input type="hidden" id="teachername" name="teachername" value="${list[0].TEACHER_NM}"/>
<input type="hidden" id="startdate" name="startdate" value="${list[0].STARTDATE}"/>
<input type="hidden" id="enddate" name="enddate" value="${list[0].ENDDATE}"/>
<input type="hidden" id="subjecttitle" name="subjecttitle" value="${list[0].SUBJECT_TITLE}"/>
</form>

<form name="searchFrm" id="searchFrm" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

<input type="hidden" id="searchType" name="searchType" value="${params.searchType}" />
<input type="hidden" id="searchStartDate" name="searchStartDate" value="${params.searchStartDate}" />
<input type="hidden" id="searchEndDate" name="searchEndDate" value="${params.searchEndDate}" />
<input type="hidden" id="searchTeacherName" name="searchTeacherName" value="${params.searchTeacherName}"/>
<input type="hidden" id="searchTeacher" name="searchTeacher" value="${params.searchTeacher}"/>
<input type="hidden" id="searchLeccode" name="searchLeccode" value="${params.searchLeccode}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEQ" name="SEQ"  value="${list[0].SEQ }"/>

<h2>● 경영관리 &gt; <strong>강사료 정산관리</strong></h2>	
    <table class="table01">
	<c:set var="studentCnt" value="0" />  
    <c:if test="${fn:length(orderlist) > 0 }">
    	<c:forEach items="${orderlist}" var="item1" varStatus="loop">
    		<c:if test="${item1.REFUND gt -1}">
    			<c:set var="studentCnt" value="${studentCnt + 1}" />
    		</c:if>
    	</c:forEach>
    </c:if>
	<c:set var="sumDCharge" value="0" />
	<c:set var="sumJCharge" value="0" />
	<c:set var="sumDTotal" value="0" />
	<c:set var="sumJTotal" value="0" />
    
	<c:forEach items="${list}" var="item" varStatus="loop">    	
    	<tr>
    		<th >강사명</th>
   			<td style="margin-left:5px;">
   				${item.TEACHER_NM }
   				<input type="hidden" id="TEACHER_NM" name="TEACHER_NM"  value="${item.TEACHER_NM }"/>
   			</td>
   		</tr>
    	<tr>
    		<th >강의기간</th>
   			<td style="margin-left:5px;">
   				${item.STARTDATE } ~ ${item.ENDDATE } 
   			</td>
   		</tr>
    	<tr>
    		<th >강의명</th>
   			<td style="margin-left:5px;">
   				${item.SUBJECT_TITLE }
   			</td>
   		</tr>
   		<tr>
    		<th >수강생 수</th>
   			<td style="margin-left:5px;">
   				<c:out value="${studentCnt }"/> 명
   			</td>
   		</tr>
    	<tr>
    		<th >수강내역</th>
   			<td style="float:left; width:99%; margin-left:5px;">
		     	<!--테이블-->
		           <div class="form_table" style="margin-top:2px; float:left; width:98%; OVERFLOW-Y:auto; height:200px;">
			           <table class="tbl_type" style="margin-top:1px; float:left; width:100%;" border="1" summary="수강내역">
			             <caption>
			             </caption>
			             <colgroup>
			             <col width="3%">
			             <col width="5%">
			             <col width="5%">
			             <col width="3%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%" style="text-align: right;">
			             <col width="5%">
			             <col width="4%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             </colgroup>
			             <thead>
							<tr>
						        <th rowspan="2">번호</th>
						        <th rowspan="2">주문번호</th>
						        <th rowspan="2">수강신청일</th>
						        <th rowspan="2">성명</th>
						        <th rowspan="2">연락처</th>
						        <th rowspan="2">이메일</th>
						        <th colspan="8">수강료</th>
						        <th rowspan="2">수강구분</th>
						        <th rowspan="2">비고</th>
						        <th rowspan="2">추가할인</th>
						        <th rowspan="2">선택</th>
					   		</tr>
					   		<tr>
						        <th>현금</th>
						        <th>카드</th>
						        <th>예금</th>
						        <th>가상계좌</th>
						        <th>계좌이체</th>
						        <th>카드수수료</th>
						        <th>환불</th>
						        <th>합계</th>
							</tr>			               
			             </thead>
			             
			             <tbody id="CLASSSERIESCODE" style="background-color:#efefef">
					    <c:if test="${fn:length(orderlist) lt 1 }">
				             <tr bgColor=#ffffff align=center> 
								<td colspan="16" style="text-align:center;">수강신청 내역이 없습니다.</td>
							 </tr>			             
					    </c:if>
					    <c:set var="orderCnt" value="${fn:length(orderlist)}"></c:set>
				    	<c:forEach items="${orderlist}" var="item1" varStatus="loop1">
				    		<c:if test="${item1.REFUND gt -1}">
				    			<!-- 정상 수강내역일 때(환불 아님) -->
				    			<tr style="background-color:#ffffff;">
				    				<td style="text-align: center;">${orderCnt - loop1.index}</td>
				    				<td>${item1.ORDERNO}</td>
				    				<td>${item1.REG_DT}</td>
				    				<td style="text-align: center;">${item1.USER_NM}</td>
				    				<td>${item1.PHONE_NO}</td>
				    				<td>${item1.EMAIL}</td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_CASH}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_CARD}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_BANK}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_VACCT}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_DBANK}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.CHARGE}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.REFUND}" groupingUsed="true"/></td>
				    				<c:set var="Totals" value="${item1.PRICE_CASH + item1.PRICE_CARD + item1.PRICE_BANK + item1.PRICE_VACCT + item1.PRICE_DBANK}" />
				    				<td style="text-align: right;"><fmt:formatNumber value="${Totals - item1.CHARGE}" groupingUsed="true"/></td>
				    				<td style="text-align: center;">${item1.PTYPE}</td>
				    				<td>${item1.PRICE_DISCOUNT_REASON}</td>
				    				<td>${item1.DISCOUNTPLUS}</td>
				    				<td></td>
				    				<c:if test="${item1.PTYPE eq '단과반' }">
				    					<c:set var="sumDTotal" value="${sumDTotal + Totals}" />
				    					<c:set var="sumDCharge" value="${sumDCharge + item1.CHARGE}" />
				    				</c:if>
				    				<c:if test="${item1.PTYPE eq '종합반' }">
				    					<c:set var="sumJTotal" value="${sumJTotal + Totals}" />
				    					<c:set var="sumJCharge" value="${sumJCharge + item1.CHARGE}" />
				    				</c:if>
				    			</tr>
				    		</c:if>
				    		<c:if test="${item1.REFUND lt 0}">
				    			<!-- 환불일 때 표시 -->
				    			<tr style="background-color:#ffffff; color:red;">
				    				<td style="text-align: center;">${orderCnt - loop1.index}</td>
				    				<td>${item1.ORDERNO}</td>
				    				<td>${item1.REG_DT}</td>
				    				<td  style="text-align: center;">${item1.USER_NM}</td>
				    				<td>${item1.PHONE_NO}</td>
				    				<td>${item1.EMAIL}</td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_CASH}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_CARD}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_BANK}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_VACCT}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.PRICE_DBANK}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.CHARGE}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="${item1.REFUND}" groupingUsed="true"/></td>
				    				<td style="text-align: right;"><fmt:formatNumber value="0" groupingUsed="true"/></td>
				    				<td style="text-align: center;">${item1.PTYPE}</td>
				    				<td>${item1.PRICE_DISCOUNT_REASON}</td>
				    				<td>${item1.DISCOUNTPLUS}</td>
				    				<td></td>
				    			</tr>
				    		</c:if>
				    	</c:forEach>
						 </tbody>
			             <tr> 
							<td colspan="20" style="text-align:right;">
							    <ul class="boardBtns">
							   	  <li><a href="javascript:saveExcelOrders();">엑셀저장</a></li>
							      <!-- <li><a href="javascript:sendSMS();">SMS전송</a></li> -->
							    </ul>
							</td>
						 </tr>
						 
			           </table>
		         </div>
		         <!--//테이블-->
		     </td>
   		</tr>
    	<tr>
    		<th>수수료공제전금액</th>
   			<td style="margin-left:5px;">
   				<input type="text" id="PREAMOUNT" name="PREAMOUNT"  value="<fmt:formatNumber value="${sumDTotal + sumJTotal}" groupingUsed="true"/>" style="width:100px; background:#FFECEC;" readonly="readonly" />원
   				<input type="hidden" id="hPREAMOUNT" name="hPREAMOUNT"  value="${sumDTotal + sumJTotal}"/>
   				<c:if test="${item.SETTLE_DT ne null}">
   					( ※ 저장된 금액 : <fmt:formatNumber value="${item.PREAMOUNT }" groupingUsed="true"/>원,  차액: <fmt:formatNumber value="${sumDTotal + sumJTotal - item.PREAMOUNT }" groupingUsed="true"/>원 )
   				</c:if>
   			</td>
   		</tr>
    	<tr>
    		<th>총합계 금액</th>
   			<td style="margin-left:5px;">
   				<input type="text" id="AMOUNT" name="AMOUNT"  value="<fmt:formatNumber value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge}" groupingUsed="true"/>" style="width:100px; background:#FFECEC;" readonly="readonly" />원
   				 = 단과반(<fmt:formatNumber value="${sumDTotal - sumDCharge}" groupingUsed="true"/>원) + 종합반(<fmt:formatNumber value="${sumJTotal - sumJCharge}" groupingUsed="true"/>원)
   				 <input type="hidden" id="hAMOUNT" name="hAMOUNT"  value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge}"/>
   				 <input type="hidden" id="hDAMOUNT" name="hDAMOUNT"  value="${sumDTotal - sumDCharge}"/>
   				 <input type="hidden" id="hJAMOUNT" name="hJAMOUNT"  value="${sumJTotal - sumJCharge}"/>
   				<c:if test="${item.SETTLE_DT ne null}">
   					<br /> ( ※ 저장된 금액 : <fmt:formatNumber value="${item.AMOUNT }" groupingUsed="true"/>원,  차액: <fmt:formatNumber value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge - item.AMOUNT }" groupingUsed="true"/>원 )
   				</c:if>   				 
   			</td>
   		</tr>
    	<tr>
    		<th>추가사항</th>
   			<td style="float:left; width:99%; margin-left:5px;">
		     	<!--테이블-->
		           <div class="form_table" style="margin-top:2px; float:left; width:95%;">
		           <table class="noborder">
		           <tr>
		           		<td><input type="button" onclick='javascript:addDeductItem();' value="+추가" /></td>
		           		<td>
		           			<div id="DeductListDiv">
		           			<c:forEach items="${deductlist}" var="item2" varStatus="loop2">
		           				<div id="DeductItem_${item2.PSA_NO}">
		           					<input type="hidden" id="PSA_NO" name="PSA_NO" value="${item2.PSA_NO }" />
				           			<select id="ADDTYPE" name="ADDTYPE" onChange="onChangeDeduction('${item2.PSA_NO}');">
						   				<option value="M" <c:if test="${item2.ADDTYPE eq 'M' }">selected="selected"</c:if>>-</option>
						   				<option value="P" <c:if test="${item2.ADDTYPE eq 'P' }">selected="selected"</c:if>>+</option>
						   			</select>&nbsp;&nbsp;&nbsp;
						   			<input type="text" id="ADDMEMO" name="ADDMEMO"  value="${item2.ADDMEMO }" style="width:200px;" />&nbsp;&nbsp;&nbsp;
						   			<input type="text" id="ADDMONEY" name="ADDMONEY"  value="${item2.ADDMONEY }" onKeyup="onChangeDeduction('${item2.PSA_NO}');" style="width:70px;" />원&nbsp;&nbsp;
						   			<input type="hidden" id="hADDMONEY" name="hADDMONEY"
						   			<c:if test="${item2.ADDTYPE eq 'M'}"> value="${item2.ADDMONEY * -1}" </c:if>  
						   			<c:if test="${item2.ADDTYPE eq 'P'}"> value="${item2.ADDMONEY }" </c:if>
						   			/>&nbsp;&nbsp;
						   			<input type="hidden" id="ETCYN" name="ETCYN"  value="N"/>&nbsp;
						   			<input type="button" onclick="javascript:deleteDeduction('${item2.PSA_NO}');" value="삭제" />
					   			</div>
		           			</c:forEach>
		           			</div> 
		           		</td>
		           </tr>
		           </table>
		           </div>
		     	<!--테이블-->
   			</td>
   		</tr>
    	<tr>
    		<th>대상금액</th>
   			<td style="margin-left:5px;">
   				<input type="text" id="TEACHERAMOUNT" name="TEACHERAMOUNT"  value="<fmt:formatNumber value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge}" groupingUsed="true"/>" style="width:100px; background:#FFECEC;" readonly="readonly" />원
   				<input type="hidden" id="hTEACHERAMOUNT" name="hTEACHERAMOUNT"  value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge}" />
   				<c:if test="${item.SETTLE_DT ne null}">
   					( ※ 저장된 금액 : <fmt:formatNumber value="${item.TEACHERAMOUNT }" groupingUsed="true"/>원 )
   				</c:if>
   			</td>
   		</tr>
    	<tr>
    		<th>정산기준</th>
   			<td style="float:left; width:99%; margin-left:5px;">
		     	<!--테이블-->
		        <div class="form_table" style="margin-top:2px; float:left; width:95%;">
		           <table class="noborder">
		           <tr>
		           		<td>
		           			단과반 : 
		           		</td>
		           		<td>
				   			<select id="CALCUCRITERIA_DTYPE" name="CALCUCRITERIA_DTYPE" onChange="onChangeCalcucriteria();">
				   				<option value="1" <c:if test="${item.CALCUCRITERIA_DTYPE eq '1' }">selected="selected"</c:if>>비율(%)</option>
				   				<option value="2" <c:if test="${item.CALCUCRITERIA_DTYPE eq '2' }">selected="selected"</c:if>>시급(월)</option>
				   				<option value="3" <c:if test="${item.CALCUCRITERIA_DTYPE eq '3' }">selected="selected"</c:if>>월정액(월)</option>
				   			</select>
		           			<input type="text" id="CALCUCRITERIA_DVALUE" name="CALCUCRITERIA_DVALUE"  value="${item.CALCUCRITERIA_DVALUE }" onKeyup="onChangeCalcucriteria();" style="width:100px;" />
				   		</td>
		           </tr>
		           <tr>
		           		<td>
		           			종합반 : 
		           		</td>
		           		<td>
				   			<select id="CALCUCRITERIA_JTYPE" name="CALCUCRITERIA_JTYPE" onChange="onChangeCalcucriteria();">
				   				<option value="1" <c:if test="${item.CALCUCRITERIA_JTYPE eq '1' }">selected="selected"</c:if>>비율(%)</option>
				   				<option value="2" <c:if test="${item.CALCUCRITERIA_JTYPE eq '2' }">selected="selected"</c:if>>시급(월)</option>
				   				<option value="3" <c:if test="${item.CALCUCRITERIA_JTYPE eq '3' }">selected="selected"</c:if>>월정액(월)</option>
				   			</select>
		           			<input type="text" id="CALCUCRITERIA_JVALUE" name="CALCUCRITERIA_JVALUE"  value="${item.CALCUCRITERIA_JVALUE }" onKeyup="onChangeCalcucriteria();" style="width:100px;" />
				   		</td>
		           </tr>
		           </table>
		        </div>
		     	<!--테이블-->
   			</td>
   		</tr>
    	<tr>
    		<th>정산기준 계산금액</th>
   			<td style="margin-left:5px;">
   				<input type="text" id="TEACHERPAY" name="TEACHERPAY"  value="${sumDTotal + sumJTotal - sumDCharge - sumJCharge}" style="width:100px;" />원
   				<c:if test="${item.SETTLE_DT ne null}">
   					( ※ 저장된 금액 : <fmt:formatNumber value="${item.TEACHERPAY }" groupingUsed="true"/>원 )
   				</c:if>
   			</td>
   		</tr>
    	<tr>
    		<th>원천징수</th>
   			<td style="margin-left:5px;">
   			<input type="text" id="WITHHOLDRATIO" name="WITHHOLDRATIO"  value="${item.WITHHOLDRATIO}" style="width:30px;" />%
   				<input type="text" id="WITHHOLDTAX" name="WITHHOLDTAX"  value="0" style="width:100px; background:#FFECEC;" readonly="readonly" />원
   				<c:if test="${item.SETTLE_DT ne null}">
   					( ※ 저장된 금액 : <fmt:formatNumber value="${item.WITHHOLDTAX }" groupingUsed="true"/>원 )
   				</c:if>
   			</td>
   		</tr>
   		<tr>
    		<th>기타 추가사항</th>
   			<td style="float:left; width:99%; margin-left:5px;">
		     	<!--테이블-->
		           <div class="form_table" style="margin-top:2px; float:left; width:95%;">
		           <table class="noborder">
		           <tr>
		           		<td><input type="button" onclick='javascript:addDeductEtcItem();' value="+추가" /></td>
		           		<td>
		           			<div id="DeductEtcListDiv">
		           			<c:forEach items="${deductetclist}" var="item2" varStatus="loop2">
		           				<div id="DeductItem_${item2.PSA_NO}">
		           					<input type="hidden" id="PSA_NO" name="PSA_NO" value="${item2.PSA_NO }" />
		           					<select id="ADDTYPE" name="ADDTYPE" onChange="onChangeDeductione('${item2.PSA_NO}');">
						   				<option value="M" <c:if test="${item2.ADDTYPE eq 'M' }">selected="selected"</c:if>>-</option>
						   				<option value="P" <c:if test="${item2.ADDTYPE eq 'P' }">selected="selected"</c:if>>+</option>
						   			</select>&nbsp;&nbsp;&nbsp;
						   			<input type="text" id="ADDMEMO" name="ADDMEMO"  value="${item2.ADDMEMO }" style="width:200px;" />&nbsp;&nbsp;&nbsp;
						   			<input type="text" id="ADDMONEY" name="ADDMONEY"  value="${item2.ADDMONEY }" onKeyup="onChangeDeductione('${item2.PSA_NO}');" style="width:70px;" />원&nbsp;&nbsp;
						   			<input type="hidden" id="hADDMONEYE" name="hADDMONEYE"
						   			<c:if test="${item2.ADDTYPE eq 'M'}"> value="${item2.ADDMONEY * -1}" </c:if>  
						   			<c:if test="${item2.ADDTYPE eq 'P'}"> value="${item2.ADDMONEY }" </c:if>
						   			/>&nbsp;&nbsp;
						   			<input type="hidden" id="ETCYN" name="ETCYN"  value="Y"/>&nbsp;
						   			<input type="button" onclick="javascript:deleteDeduction('${item2.PSA_NO}');" value="삭제" />
					   			</div>
		           			</c:forEach>
		           			</div> 
		           		</td>
		           </tr>
		           </table>
		           </div>
		     	<!--테이블-->
   			</td>
   		</tr>
   		<tr>
    		<th >실 지급액</th>
   			<td >
				<input type="text" id="ADJUSTAMOUNT" name="ADJUSTAMOUNT"  title="실지급액" value="${item.ADJUSTAMOUNT}" style="width:100px; background:#FFECEC;"/>원
					&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick='javascript:confirmAdjustAmaunt();' value="강사료 산출" />
				<input type="hidden" id="hIsConfirmed" name="hIsConfirmed" value="N" />
   				<c:if test="${item.SETTLE_DT ne null}">
   					( ※ 저장된 실 지급액 : <fmt:formatNumber value="${item.ADJUSTAMOUNT }" groupingUsed="true"/>원 )
   				</c:if>
   			</td>
   		</tr>
   		<c:if test="${item.SETTLE_DT ne null}">
   		<tr>
    		<th >최근정산일자</th>
   			<td >
   				${item.SETTLE_DT }  <input type="button" onclick='javascript:TeacherAdjustExcelDownload();' value="엑셀저장" />
   			</td>
   		</tr>
   		</c:if>
	</c:forEach>   		
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <c:if test="${list[0].SETTLE_DT ne null}">
      	<li><a href="javascript:goSave();">다시 정산</a></li>
      </c:if>
   	  <li><a href="javascript:goSave();">확인</a></li>
      <li><a href="javascript:goList();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
