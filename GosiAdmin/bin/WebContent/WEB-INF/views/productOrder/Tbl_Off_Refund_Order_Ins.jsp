<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
var USER_ID = "${searchMap.USER_ID}";
var ORDERNO = "${searchMap.ORDERNO}";
var SET_DATE = "${searchMap.SET_DATE}";
var STATUSCODE = "${searchMap.STATUSCODE}";
var MGNTNO = "${searchMap.MGNTNO}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	
    var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	
    getSubCODE2('CLASSSERIESCODE', '<c:url value="/productOrder/subCodeUp.do"/>', ORDERNO, MGNTNO);
    
    if(SET_DATE == "") {
    	$('#SET_DATE').val(year+mon+day );
	}

}

//장바구니 뿌리기
function getSubCODE2(target, url, ORDERNO, MGNTNO) {
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = url + '?ORDERNO=' + ORDERNO+'&MGNTNO='+MGNTNO;
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("subCodes.length:"+json.subCodes.length);
			if(json && json.length > 0) {
				$(json).each(function(index, lec){			
					leccodes = "'"+lec.LECCODE+"'";
					if (lec.LIST_YN == 'Y') {
						$("#"+target).append('<tr><td style=text-align:left;>' + lec.SUBJECT_TITLE + '</td><td style=text-align:center;>' + lec.SUBJECT_REAL_PRICE + '</td><td style=text-align:center;>' + lec.PRICE + '</td><td style=text-align:center;>' + lec.LEC_SCHEDULE + '</td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + lec.SUBJECT_REAL_PRICE + '"><input type="hidden" name="SUBJECT_REAL_PRICE2" id="SUBJECT_REAL_PRICE2" value="' + lec.PRICE + '"><input type="hidden" name="LECCODE" id="LECCODE" value="' + lec.LECCODE + '"></tr>');
					}else{
						$("#"+target).append('<tr><td style=text-align:left;>' + lec.SUBJECT_TITLE + '</td><td style=text-align:center;></td><td style=text-align:center;></td><td style=text-align:center;>' + lec.LEC_SCHEDULE + '</td></tr>');
					}
				});	
			}
			
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
	
	var tmp =""; 
	$("input[name=SUBJECT_REAL_PRICE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = parseInt(tmp) + parseInt($(this).val());
		}
	});
	
	if(tmp == ""){
		document.getElementById("TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("TOTAL_PRICE").innerHTML = tmp;
	}
	
	var tmp2 =""; 
	$("input[name=SUBJECT_REAL_PRICE2]").each(function (index){
		if(tmp2 == null || tmp2 == ""){
			tmp2 = $(this).val();
		}else{
			tmp2 = parseInt(tmp2) + parseInt($(this).val());
		}
	});
	
	if(tmp2 == ""){
		document.getElementById("REAL_TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("REAL_TOTAL_PRICE").innerHTML = tmp2;
	}
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "REFUND_CARD") {
				$('#REFUND_CARD').val("");      
				$('#REFUND_CARD').focus();         
				return;
			}
			
			if(target == "REFUND_CASH") {
				$('#REFUND_CASH').val("");      
				$('#REFUND_CASH').focus();         
				return;
			}
		}       
	}
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("SET_DATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
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

//목록
function goList() {
	if(sdate == ""){
		$('#searchFrm').attr('action','<c:url value="/productOrder/off_refund_list.do"/>').submit();
	}else{
		$('#searchFrm').attr('action','<c:url value="/productOrder/off_list.do"/>').submit();
	}
	
}

//확인
function checkParam() {
	if ($('#REFUND_CARD').val() == "" && $('#REFUND_CASH').val() == "") {
		alert('환불금액을 입력해주세요.');
		$('#REFUND_CARD').focus();
		return;
	}
	
	var tmp =""; 
	$("input[name=LECCODE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = tmp + "," + $(this).val();
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("환불 할 품목이 없습니다.");
		return;
	}				
	
	if(STATUSCODE == "DLV230") {
		if(confirm("환불정보 수정 하시겠습니까?")) {
			$('#searchFrm').attr('action','<c:url value="/productOrder/refundCrud.do"/>').submit();
		}
	}else{
		if(confirm("환불등록 처리 하시겠습니까?")) {
			$('#searchFrm').attr('action','<c:url value="/productOrder/refundCrud.do"/>').submit();
		}
	}
}

//환불후 재입력
function checkParamRe() {
	if ($('#REFUND_CARD').val() == "" && $('#REFUND_CASH').val() == "") {
		alert('환불금액을 입력해주세요.');
		$('#REFUND_CARD').focus();
		return;
	}
	
	var tmp =""; 
	$("input[name=LECCODE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = tmp + "," + $(this).val();
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("환불 할 품목이 없습니다.");
		return;
	}				
	
	if(confirm("환불후 재입력 하시겠습니까?")) {
		$('#searchFrm').attr('action','<c:url value="/productOrder/refundNewReCrud.do"/>').submit();
	}
}

//환불취소
function cancelRefund() {

	var tmp =""; 
	$("input[name=LECCODE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = tmp + "," + $(this).val();
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("환불 할 품목이 없습니다.");
		return;
	}

	if(confirm("환불취소 하시겠습니까?")) {
		$('#searchFrm').attr('action','<c:url value="/productOrder/refundDelete.do"/>').submit();
	}
}
</script>
</head>


<!--content -->
<div id="content">
<form name="searchFrm" id="searchFrm" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<input type="hidden" id="ORDERNO" name="ORDERNO" value="${searchMap.ORDERNO}" />
<input type="hidden" id="STATUSCODE" name="STATUSCODE" value="${searchMap.STATUSCODE}" />

	<h2>● 수강신청관리 > <strong>환불관리</strong></h2>	
    	<table class="table01">
    	
    	<tr>
    		<th >주문번호</th>
   			<td>${searchMap.ORDERNO}</td>
   		</tr>
   		
    	<tr>
    		<th >ID</th>
   			<td>
   				<div class="item" >
   					${searchMap.USER_ID}
	   				<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}" />
				</div>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >성명</th>
   			<td >${searchMap.USER_NM}</td>
   		</tr>
   		
   		<tr>
    		<th >연락처</th>
   			<td >${searchMap.PHONE_NO}</td>
   		</tr>
   		   		
   		<tr>
    		<th >E-Mail</th>
   			<td >${searchMap.EMAIL}</td>
   		</tr>
   		
   		<tr>
    		<th >수강신청일자</th>
   			<td >${searchMap.REG_DT}</td>
   		</tr>
   		
   		<tr>
    		<th >결제내역</th>
   			<td style="float:left; width:100%;">
		     	<!--테이블-->
			         
		           <div class="form_table" style="margin-top:10px; float:left; width:100%;">
			           <table class="tbl_type" style="margin-top:10px; float:left; width:100%;" border="1" cellspacing="0" summary="받은쪽지">
			             <caption>
			             </caption>
			             <colgroup>
			             <col width="10%">
			             <col width="8%">
			             <col width="8%">
			             <col width="8%">
			             <col width="8%">
			             <col width="8%">
			             <col width="10%">
			             <col width="8%">
			             <col width="30%">			             
			             </colgroup>
			             <thead>
			               <tr>
			               	 <th scope="col">결제일자</th>
			                 <th scope="col">결제대상액</th>
			                 <th scope="col">카드</th>
			                 <th scope="col">현금</th>
			                 <th scope="col">예금</th>
			                 <th scope="col">미수금</th>
			                 <th scope="col">결제완료<br>예정일자</th>
			                 <th scope="col">카드종류</th>
			                 <th scope="col">비고</th>
			               </tr>
			             </thead>
			             
			             <tbody>
			             <tr> 
							<td style="text-align:center;">${fn:substring(searchMap.ISCONFIRM, 0, 4)}-${fn:substring(searchMap.ISCONFIRM, 4, 6)}-${fn:substring(searchMap.ISCONFIRM, 6, 8)}</td>
							<td style="text-align:center;"><fmt:formatNumber value="${searchMap.PRICE}" type="currency" /></td>
							<td style="text-align:center;"><fmt:formatNumber value="${searchMap.PRICE_CARD}" type="currency" /></td>
							<td style="text-align:center;"><fmt:formatNumber value="${searchMap.PRICE_CASH}" type="currency" /></td>
							<td style="text-align:center;"><fmt:formatNumber value="${searchMap.PRICE_BANK}" type="currency" /></td>
							<td style="text-align:center;"><fmt:formatNumber value="${searchMap.PRICE_UNPAID}" type="currency" /></td>
							<td style="text-align:center;">${fn:substring(searchMap.DUE_DT, 0, 4)}-${fn:substring(searchMap.DUE_DT, 4, 6)}-${fn:substring(searchMap.DUE_DT, 6, 8)}</td>
							<td style="text-align:center;">${searchMap.CARD_NAME}</td>
							<td style="text-align:left;">${searchMap.MEMO}</td>
						 </tr>		             
						 </tbody>
						 
			           </table>
		         </div>
		         <!--//테이블-->
		     </td>
   		</tr>
   		
   		<tr>
    		<th >품목별 환불</th>
   			<td style="float:left; width:100%;">
		     	<!--테이블-->
			         
		           <div class="form_table" style="margin-top:10px; float:left; width:85%;">
			           <table class="tbl_type" style="margin-top:10px; float:left; width:85%;" border="1" cellspacing="0" summary="품목리스트">
			             <caption>
			             </caption>
			             <colgroup>
			             <col width="15%">
			             <col width="5%">
			             <col width="5%">
			             <col width="5%">
			             </colgroup>
			             <thead>
			               <tr>
			               	 <th scope="col">품목</th>
			                 <th scope="col">원수강료</th>
			                 <th scope="col">결제금액</th>
			                 <th scope="col">전체회차</th>
			               </tr>
			             </thead>
			             
			             <tbody id="CLASSSERIESCODE">
			             <tr bgColor=#ffffff align=center> 
							<td colspan="4" style="text-align:center;">등록된 신청 내역이 없습니다.</td>
						 </tr>			             
						 </tbody>
			              
			              <tr> 
							<td style="text-align:right;">합계</td>
							<td style="text-align:center;"><span id="TOTAL_PRICE">0</span></td>
							<td style="text-align:center;"><span id="REAL_TOTAL_PRICE">0</span></td>
							<td></td>
						 </tr>
						 
			           </table>
		         </div>
		         <!--//테이블-->
		     </td>
   		</tr>
   		
   		<c:choose>
			<c:when test="${searchMap.REFUND_PAY eq null}">
				<c:set var="REFUND_CARD" value="${searchMap.PRICE_CARD}"/>
				<c:set var="REFUND_CASH" value="${searchMap.PRICE_CASH + searchMap.PRICE_BANK}"/>
			</c:when>
			
			<c:otherwise>
				<c:set var="REFUND_CARD" value="${searchMap.REFUND_CARD}"/>
				<c:set var="REFUND_CASH" value="${searchMap.REFUND_CASH}"/>
			</c:otherwise>
		</c:choose>
									
   		<tr>
    		<th >환불금액</th>
   			<td >
   				카드취소<input type="text" id="REFUND_CARD" name="REFUND_CARD"  title="레이블 텍스트" value="${REFUND_CARD}" maxlength="20" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'REFUND_CARD');" onblur="chk(this,'REFUND_CARD');"/>
  				<br>
  				현금환불총액<input type="text" id="REFUND_CASH" name="REFUND_CASH"  title="레이블 텍스트" value="${REFUND_CASH}" maxlength="20" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'REFUND_CASH');" onblur="chk(this,'REFUND_CASH');"/>
   						
   			</td>
   		</tr>
   		
   		<tr>
    		<th >환불은행</th>
   			<td >
				은행&nbsp;<input type="text" id="ACC_BANK_NAME" name="ACC_BANK_NAME"  title="레이블 텍스트" value="${searchMap.ACC_BANK_NAME}" style="width:15%; background:#FFECEC;" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				계좌번호&nbsp;<input type="text" id="ACC_BANK_NUM" name="ACC_BANK_NUM"  title="레이블 텍스트" value="${searchMap.ACC_BANK_NUM}" style="width:30%; background:#FFECEC;" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				예금주&nbsp;<input type="text" id="ACC_BANK_DEPOSITOR" name="ACC_BANK_DEPOSITOR"  title="레이블 텍스트" value="${searchMap.ACC_BANK_DEPOSITOR}" style="width:15%; background:#FFECEC;" />
   			</td>
   		</tr>
   		
   		<tr>
    		<th >환불비고</th>
   			<td >
   				<textarea rows="4" style="width:60%;" name="REFUND_MEMO" id="REFUND_MEMO">${searchMap.REFUND_MEMO}</textarea>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >환불일자</th>
   			<td >
   				<input type="text" id="SET_DATE" name="SET_DATE" maxlength="10" class="i_text" value="${searchMap.SET_DATE}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
   			</td>
   		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:checkParam();">확인</a></li>
   	  
   	  <c:if test="${searchMap.STATUSCODE ne  'DLV230'}">
      	<li><a href="javascript:checkParamRe();">환불후 재입력</a></li>
      </c:if>
	      
      <li><a href="javascript:goList();">목록</a></li>
      
      <c:if test="${searchMap.STATUSCODE eq  'DLV230'}">
      	<li><a href="javascript:cancelRefund();">환불취소</a></li>
      </c:if>
      
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
