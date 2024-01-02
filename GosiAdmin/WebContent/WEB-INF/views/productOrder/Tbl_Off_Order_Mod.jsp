<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
var sts = "${sts}";
var USER_ID = "${searchMap.USER_ID}";
var ORDERNO = "${searchMap.ORDERNO}";
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
var TYPE = "N";

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("ISCONFIRM");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "PHONE_NO") {
				$('#PHONE_NO').val("");      
				$('#PHONE_NO').focus();         
				return;
			}
			
			if(target == "PRICE_DISCOUNT") {
				$('#PRICE_DISCOUNT').val("");      
				$('#PRICE_DISCOUNT').focus();
				return;
			}
			
			if(target == "PRICE_CARD") {
				$('#PRICE_CARD').val("");      
				$('#PRICE_CARD').focus();         
				return;
			}
			
			if(target == "PRICE_CASH") {
				$('#PRICE_CASH').val("");      
				$('#PRICE_CASH').focus();         
				return;
			}
			
			if(target == "PRICE_BANK") {
				$('#PRICE_BANK').val("");      
				$('#PRICE_BANK').focus();         
				return;
			}
		}       
	}
}

//품목변경
function product_change(num) {
	window.open('<c:url value="/productOrder/pop_subject_mod.pop"/>?Num=' + num
			 + '&searchkey=' + escape(encodeURIComponent(searchkey)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=yes,width=1200,height=550,top=0,left=0,location=no,scrollbars=yes');
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
	if(sts == "U") {
		$('#USER_ID').val("");
	}
	$('#searchFrm').attr('action','<c:url value="/productOrder/off_list.do"/>').submit();
}

//저장
function checkParam() {
	if ($('#USER_ID').val() == "") {
		alert('ID를 입력해주세요.');
		$('#USER_ID').focus();
		return;
	}
	
	if ($('#USER_NM').val() == "") {
		alert('성명을 입력해주세요.');
		$('#USER_NM').focus();
		return;
	}
	
	var PRICE = $('#PRICE').val();
	var PRICE_CARD = $('#PRICE_CARD').val();
	var PRICE_CASH = $('#PRICE_CASH').val();
	var PRICE_BANK = $('#PRICE_BANK').val();
	if (parseInt(PRICE) != (parseInt(PRICE_CARD)+parseInt(PRICE_CASH)+parseInt(PRICE_BANK))) {
		alert("결제금액을 확인해주세요.")
		return;
	}
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/orderUpdate.do"/>').submit();
}


//삭제
function goDel() {	
	if(confirm("삭제하시겠습니까?")) {
		$('#searchFrm').attr('action','<c:url value="/productOrder/registrationDelete.do"/>').submit();
	}
}

//총 합계금액 자동계산
function make_sum(){
	var TOT_PRICE = 0;
	var cnt = 0;
	var DIS_PRICE = 0;
	var PRICE = "";

	cnt = parseInt($('#sub_size').val());
	
	 for (var i = 1; i < cnt+1; i++){
		 TOT_PRICE = TOT_PRICE +parseInt($('#REALPRICE_'+i+"").val());
	 }
		$('#PRICE').val(TOT_PRICE);
		document.getElementById("REAL_TOTAL_PRICE").innerHTML = TOT_PRICE;
}

//금액 onchange 자동계산
function self_dis(){
	var code = document.getElementsByName("PRICE_DISCOUNT_TYPE");
	if (code[0].checked == true) {
		alert("비율할인 사용입니다.");
		self_dis1();
	}else	if (code[1].checked == true) {
		alert("금액할인 사용입니다.");
		self_dis2();
	} else {
		alert("할인이 적용되지 않았습니다.");
		self_dis2();
	}
}

//금액 비율 입력시 자동계산
function self_dis1(){
	var DIS = "";
	var PRICE = "";
	var TOT_PRICE = 0;
	var cnt = 0;

	if ($('#PRICE_DISCOUNT').val() > 100 || $('#PRICE_DISCOUNT').val() < 0) {
		alert("할인 비율의 범위를 조정해주세요.");
		return;
	}

	cnt = parseInt($('#sub_size').val());
	DIS = parseInt($('#PRICE_DISCOUNT').val())/100;
	
	 for (var i = 1; i < cnt+1; i++){
		PRICE = $('#ORG_PRICE_'+i+"").val() - ($('#ORG_PRICE_'+i+"").val() * DIS);
//		PRICE = PRICE / 100;
		PRICE = Math.floor(PRICE);
		$('#REALPRICE_'+i+"").val(PRICE);

		TOT_PRICE = TOT_PRICE +parseInt($('#REALPRICE_'+i+"").val());
	 }
		$('#PRICE').val(TOT_PRICE);
		document.getElementById("REAL_TOTAL_PRICE").innerHTML = TOT_PRICE;
}

//금액을 직접 입력시 자동계산
function self_dis2(){
	var TOT_PRICE = 0;
	var cnt = 0;
	var DIS_PRICE = 0;
	var PRICE = "";

	if (parseInt($('#PRICE_DISCOUNT').val()) > parseInt($('#PRICE').val())) {
		alert("할인 금액이 결재대상금액보다 크게 설정되었습니다.");
		return;
	}
	
	cnt = parseInt($('#sub_size').val());
	DIS_PRICE = $('#PRICE_DISCOUNT').val()  / cnt;
	
	 for (var i = 1; i < cnt+1; i++){
		 PRICE = $('#ORG_PRICE_'+i+"").val() - DIS_PRICE;
  		 PRICE = Math.floor(PRICE);
		 $('#REALPRICE_'+i+"").val(PRICE);

		 TOT_PRICE = TOT_PRICE +parseInt($('#REALPRICE_'+i+"").val());
	 }
		$('#PRICE').val(TOT_PRICE);
		document.getElementById("REAL_TOTAL_PRICE").innerHTML = TOT_PRICE;
}

//전액버튼 onchange 자동계산
function self_dis3(cnt){
	var PRICE = $('#PRICE').val();
	
	if (cnt == "1") {
		$("#PRICE_CARD").val(PRICE);
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val("0");
	}else if (cnt == "2") {
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val(PRICE);
		$("#PRICE_BANK").val("0");
	}else if (cnt == "3") {
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val(PRICE);
	}else{
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val("0");
	}
}

//단위올림버튼 onchange 자동계산
function self_dis4(){
	var PRICE = $('#PRICE').val();
	
	if ($('#PRICE').val() == "") {
		alert('품목을 추가해 주세요.');
		return;
	}
	var PRICE2 = "";
	
	if (parseInt(PRICE.substring(PRICE.length - 2, PRICE.length - 1)) > 0) {
		PRICE2 = PRICE.substring(0, PRICE.length - 2);
		PRICE2 = parseInt(PRICE2) + 1;
		PRICE = PRICE2 + "00";
	}else{
		PRICE2 = PRICE.substring(0, PRICE.length - 2);
		PRICE = PRICE2 + "00";
	}
	
	$('#PRICE').val(PRICE);
}

//엔터키 검색
function fn_checkEnter(){
	$('#PRICE_DISCOUNT').keyup(function(e)  {
		if(e.keyCode == 13) {
			self_dis();
		}
	});
}

//수강증출력
function fn_Print(){
	window.open('<c:url value="/productOrder/offererPrint.pop"/>?PRINT_STS=2&ORDERNO=' + $('#ORDERNO').val() + '&MGNTNO=' + MGNTNO
			 + '&searchkey=' + escape(encodeURIComponent(searchkey)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,toolbar=no,resizable=yes,width=620,height=330,top=0,left=0,location=no,scrollbars=no');
	
}

//수강증복원
function printRe() {	
	
	var _url = '<c:url value="/productOrder/updateRePrint.do"/>?ORDERNO=' + $('#ORDERNO').val();
	
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("duplication.length:"+json.duplication.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){
					
				});	
			}
			
			alert("수강증발급이 초기화되었습니다.");
			//document.location.href = document.URL;
			$('#searchFrm').attr('action','<c:url value="/productOrder/updatePage.do"/>').submit();
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
}
</script>
</head>

<!--content -->
<div id="content">
<form name="searchFrm" id="searchFrm" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<input type="hidden" id="ORDERNO" name="ORDERNO" value="${searchMap.ORDERNO}" />
<input type="hidden" id="TYPE" name="TYPE" value="" />
<input type="hidden" id="sub_size" name="sub_size" value="${sub_size}" />

	<h2>● 수강신청관리 > <strong>수강신청</strong></h2>	
    	<table class="table01">
    	
    	<c:if test="${sts eq  'U'}">
	    	<tr>
	    		<th >주문번호</th>
	   			<td>
	   				${searchMap.ORDERNO}
	   			</td>
	   		</tr>
   		</c:if>
   		
    	<tr>
    		<th >ID</th>
   			<td>
   				<div class="item" >
   					<c:if test="${sts eq  'I'}">
		   				<input type="text" id="USER_ID" name="USER_ID"  title="레이블 텍스트" value="${searchMap.USER_ID}" maxlength="" style="width:20% ; background:#FFECEC;" />
		   				<input type="button" onclick='javascript:id_chk();' value="ID회원체크" />
	   				</c:if>
	   				
	   				<c:if test="${sts eq  'U'}">
	   					${searchMap.USER_ID}
	   					<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}" />
	   				</c:if>
				</div>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >성명</th>
   			<td >
   					<input type="text" id="USER_NM" name="USER_NM"  title="레이블 텍스트" value="${searchMap.USER_NM}" maxlength="20" style="width:10%; background:#FFECEC;" />
   			</td>
   		</tr>
   		
   		<tr>
    		<th >연락처</th>
   			<td>
   					<input type="text" id="PHONE_NO" name="PHONE_NO"  title="레이블 텍스트" value="${searchMap.PHONE_NO}" maxlength="20" style="width:15%; background:#FFECEC;" onkeyup="chk(this,'PHONE_NO')" onblur="chk(this,'PHONE_NO')"/>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >E-Mail</th>
   			<td>
   					<input type="text" id="EMAIL" name="EMAIL"  title="레이블 텍스트" value="${searchMap.EMAIL}" maxlength="" style="width:20%; background:#FFECEC;" />
   			</td>
   		</tr>
   		
   		
   		<tr>
    		<th >품목<br></th>
   			<td>
		     	<!--테이블-->
		           <div class="form_table" style="margin-top:10px; float:left; width:95%;">
			           <table class="tbl_type" style="margin-top:10px; float:left; width:95%;" border="1">
			             <colgroup>
			             <col width="5%">
			             <col width="*">
			             <col width="15%">
			             <col width="15%">
			             <col width="10%">
			             </colgroup>
			             <thead>
			               <tr>
			               	 <th scope="col">No.</th>
			               	 <th scope="col">품목</th>
			                 <th scope="col">원수강료</th>
			                 <th scope="col">결제대상금액</th>
			                 <th scope="col">선택</th>
			               </tr>
			             </thead>
			             <tbody>
				         <c:if test="${not empty sub_list}">
							<c:forEach items="${sub_list}" var="list" varStatus="status">
				            <c:if test="${list.O_TYPE == 'M'}"><c:set var="price" value="${list.PRICE}"/><c:set var="colors" value="#EFEFEF"/></c:if>
				            <c:if test="${list.O_TYPE == 'S'}"><c:set var="price" value="${list.REALPRICE}"/><c:set var="colors" value="#FFFFFF"/></c:if>
							<input type="hidden" id="PRD" name="PRD" value="${list.NUM}"/>
							<input type="hidden" id="ORG_PACKGE_NO_${list.NUM}" name="ORG_PACKGE_NO_${list.NUM}" value="${list.MGNTNO}"/>
							<input type="hidden" id="ORG_LECTURE_NO_${list.NUM}" name="ORG_LECTURE_NO_${list.NUM}" value="${list.LECTURE_NO}"/>
							<input type="hidden" id="O_TYPE_${list.NUM}" name="O_TYPE_${list.NUM}" value="${list.O_TYPE}"/>
							<input type="hidden" id="L_TYPE_${list.NUM}" name="L_TYPE_${list.NUM}" value="${list.LEC_TYPE_CHOICE}"/>
			             <tr style="background-color:${colors}">
							<td>${list.NUM}</td>
							<td><span id="SUBJECT_NM_${list.NUM}">${list.SUBJECT_TITLE}</span></td>
				            <td><span id="SUBJECT_REAL_PRICE_${list.NUM}"><fmt:formatNumber value="${list.SUBJECT_REAL_PRICE}" type="currency" /></span></td>
							<td>
								<input type="hidden" id="MGNTNO_${list.NUM}" name="MGNTNO_${list.NUM}" value="${list.MGNTNO}"/>
								<c:if test="${list.LEC_TYPE_CHOICE == 'D'}">
								<input type="hidden" id="ORG_PRICE_${list.NUM}" name="ORG_PRICE_${list.NUM}" value="${list.REALPRICE}"/>
								<input type="text" id="REALPRICE_${list.NUM}" name="REALPRICE_${list.NUM}"  onBlur="make_sum()" style="width:80px; background:#FFECEC;" value="${list.REALPRICE}"/>
								<c:set var="sum" value="${sum + list.SUBJECT_REAL_PRICE}"/>
								</c:if>
								<c:if test="${list.LEC_TYPE_CHOICE != 'D'}">
								<input type="hidden" id="ORG_PRICE_${list.NUM}" name="ORG_PRICE_${list.NUM}" value="0"/>
								<input type="hidden" id="REALPRICE_${list.NUM}" name="REALPRICE_N_${list.NUM}" value="0"/>
								</c:if>
							</td>
				            <td>
								<c:if test="${list.LEC_TYPE_CHOICE == 'D' && list.O_TYPE == 'M'}">
					            <input type="button" onclick="javascript:product_change('${list.NUM}');" value="품목변경" />
					            </c:if>
				            </td>
				            <c:if test="${list.O_TYPE == 'M'}"></c:if>
				        </tr>
				      		</c:forEach>
						</c:if>
						<c:if test="${empty sub_list}">
			             <tr bgColor=#ffffff align=center>
							<td colspan="3" style="text-align:center;">등록된 신청 내역이 없습니다.</td>
						 </tr>
						 </c:if>
						 </tbody>
			              
			              <tr> 
							<td style="text-align:right;"></td>
							<td style="text-align:right;">합계</td>
							<td style="text-align:right;"><fmt:formatNumber value="${sum}" type="currency" /></td>
							<td style="text-align:center;"><span id="REAL_TOTAL_PRICE">${searchMap.PRICE}</span></td>
							<td style="text-align:right;"></td>
						 </tr>
						 
			           </table>
		         </div>
		         <!--//테이블-->
		     </td>
   		</tr>
   		
   		<tr>
    		<th >관리자 추가할인</th>
   			<td >
				<input name="PRICE_DISCOUNT_TYPE" id="PRICE_DISCOUNT_TYPE" value="R" type="radio" <c:if test="${searchMap.PRICE_DISCOUNT_TYPE == 'R'}">checked</c:if> />비율(%)
				&nbsp;
				<input name="PRICE_DISCOUNT_TYPE" id="PRICE_DISCOUNT_TYPE" value="C" type="radio" <c:if test="${searchMap.PRICE_DISCOUNT_TYPE == 'C'}">checked</c:if>/>금액(원) 
				&nbsp;
   				<input type="text" id="PRICE_DISCOUNT" name="PRICE_DISCOUNT"  title="레이블 텍스트" value="${searchMap.PRICE_DISCOUNT}" style="width:10%; background:#FFECEC;"/>
   				<input type="button" onclick='javascript:self_dis();' value="금액계산" />
   				&nbsp;&nbsp;
   				*할인사유&nbsp;<input type="text" id="PRICE_DISCOUNT_REASON" name="PRICE_DISCOUNT_REASON"  title="레이블 텍스트" value="${searchMap.PRICE_DISCOUNT_REASON}" style="width:30%; background:#FFECEC;" />
   			</td>
   		</tr>
   		
   		<tr>
    		<th >결제대상금액</th>
   			<td >
   					<input type="text" id="PRICE" name="PRICE"  title="레이블 텍스트" value="${searchMap.PRICE}" style="width:15%; background:#FFECEC;"/>원
   					&nbsp;&nbsp;&nbsp;&nbsp;
   					<input type="button" onclick='javascript:self_dis4();' value="백단위올림" />
   			</td>
   		</tr>
   		
   		<tr>
    		<th >결제</th>
   			<td >
   				카드승인<input type="text" id="PRICE_CARD" name="PRICE_CARD"  title="레이블 텍스트" value="${searchMap.PRICE_CARD}" maxlength="20" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'PRICE_CARD');self_dis2();" onblur="chk(this,'PRICE_CARD');self_dis2();"/> &nbsp;
   						<input type="button" onclick='javascript:self_dis3(1);' value="전액" />&nbsp;&nbsp;&nbsp;&nbsp;
  				*카드종류<input type="text" id="CARD_NAME" name="CARD_NAME"  title="레이블 텍스트" value="${searchMap.CARD_NAME}" maxlength="20" style="width:10%; background:#FFECEC;" />
  				<br>
  				현금<input type="text" id="PRICE_CASH" name="PRICE_CASH"  title="레이블 텍스트" value="${searchMap.PRICE_CASH}" maxlength="20" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'PRICE_CASH');self_dis2();" onblur="chk(this,'PRICE_CASH');self_dis2();"/> &nbsp;
   				<input type="button" onclick='javascript:self_dis3(2);' value="전액" />
   				<br>
  				예금<input type="text" id="PRICE_BANK" name="PRICE_BANK"  title="레이블 텍스트" value="${searchMap.PRICE_BANK}" maxlength="20" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'PRICE_BANK');self_dis2();" onblur="chk(this,'PRICE_BANK');self_dis2();"/> &nbsp;
   				<input type="button" onclick='javascript:self_dis3(3);' value="전액" />
   			</td>
   		</tr>
   		
   		<tr>
    		<th >비고</th>
   			<td >
   				<textarea rows="4" style="width:60%;" name="MEMO" id="MEMO">${searchMap.MEMO}</textarea>
   			</td>
   		</tr>
   		
   		<tr>
    		<th>승인일자</th>
   			<td>
   				<input type="text" id="ISCONFIRM" name="ISCONFIRM" maxlength="10" class="i_text" value="${searchMap.ISCONFIRM}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
   			</td>
   		</tr>
   		<tr>
    		<th>등록일자</th>
   			<td>${searchMap.REG_DT}</td>
   		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:checkParam();">저장</a></li>
      <li><a href="javascript:goList();">목록</a></li>
      
      <c:if test="${sts eq  'U'}">
      	  <c:if test="${searchMap.PRICE ne null}">
		      <c:if test="${searchMap.TICKET_PRINT_DT eq  null}">
		      	<li><a href="javascript:fn_Print();">수강증출력</a></li>
		      </c:if>
		      <c:if test="${searchMap.TICKET_PRINT_DT ne  null}">
		      	<li><a href="javascript:printRe();">수강증복원</a></li>
		      </c:if>
      	  </c:if>
	      
	      <li><a href="javascript:goDel();">삭제</a></li>
      </c:if>
      
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
