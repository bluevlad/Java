<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>

<script type="text/javascript">
//var message = "${searchMap.message}";
//alert("message:"+message);
//var search_date_type = "${searchMap.search_date_type}";

//var MOCKCODE = "${searchMap.MOCKCODE}";
//alert("MOCKCODE:"+MOCKCODE);
var sts = "${sts}";
var USER_ID = "${searchMap.USER_ID}";
var ORDERNO = "${searchMap.ORDERNO}";

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
	 /* if(message != "") {
		alert(message);
	} */
	
	/* alert("orderstatuscode:"+orderstatuscode +"\n"+
	"searchkey:"+searchkey +"\n"+
	"searchtype:"+searchtype +"\n"+
	"paycode:"+paycode +"\n"+		
	"sdate:"+sdate +"\n"+
	"edate:"+edate +"\n"+
	"currentPage:"+currentPage +"\n"+
	"pageRow:"+pageRow +"\n"+
	"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
	"MENUTYPE:"+MENUTYPE +"\n"+
	"L_MENU_NM:"+L_MENU_NM); */
	
    var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	
    if(sts == "U") {
    	getSubCODE2('CLASSSERIESCODE', '<c:url value="/productOrder/subCodeUp.do"/>', ORDERNO);
    }else{
    	$('#DUE_DT').val(year+mon+day );
        $('#REG_DT').val(year+mon+day );
    }


}

//장바구니 뿌리기
function getSubCODE2(target, url, ORDERNO) {
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = url + '?ORDERNO=' + ORDERNO;	
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("subCodes.length:"+json.subCodes.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){			
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#"+target).append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td style=text-align:right;>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td style=text-align:right;><span id="REAL_TOTAL_PRICE' + index + '">' + mouigosa.PRICE + '</span><input type="button" onclick="goup('+ leccodes +')" value="변경" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="SUBJECT_REAL_PRICE2" id="SUBJECT_REAL_PRICE2" value="' + mouigosa.PRICE + '"><input type="hidden" name="LECCODE" id="LECCODE" value="' + mouigosa.LECCODE + '"></tr>');					
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
	
	/* alert("TOTAL_PRICE:"+tmp +"\n"+
			 "USER_ID:"+tmp2); */
}

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
			
			if(target == "PRICE_UNPAID") {
				$('#PRICE_UNPAID').val("");      
				$('#PRICE_UNPAID').focus();         
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

//id체크
function id_chk(){
	
	if ($('#USER_ID').val() == "") {
		alert('ID를 입력해주세요.');
		$('#USER_ID').focus();
		return;
	}
	
	var USER_ID = $('#USER_ID').val();
		
	var _url = '<c:url value="/productOrder/idChk.do"/>?USER_ID=' + USER_ID;
	
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("registrationMax.length:"+json.registrationMax.length);
			if(json && json.length > 0) {
				$(json).each(function(index, key){
					
					$('#USER_NM').val(key.USER_NM);
					$('#PHONE_NO').val(key.PHONE_NO);
					$('#EMAIL').val(key.EMAIL);
					
					 /* alert("mouigosa.MOCKCODE:"+mouigosa.MOCKCODE +"\n"+
							"MOCKCODE:"+$('#MOCKCODE').val()); */
				});	
			}else{
				alert('해당 ID는 회원가입된 ID가 아닙니다.');
				return;
			}
		}
	});
}

//품목변경
function product_change() {
	//alert("USER_ID:"+$("#USER_ID").val());

	window.open('<c:url value="/productOrder/pop_subject_add.pop"/>?USER_ID=' + $("#USER_ID").val()
			 + '&searchkey=' + escape(encodeURIComponent(searchkey)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=yes,width=1200,height=550,top=0,left=0,location=no,scrollbars=yes');
}

//부모페이지 
function getSubCODE(target, url, USER_ID) {
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = url + '?USER_ID=' + USER_ID;	
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("subCodes.length:"+json.subCodes.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){			
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#"+target).append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td style=text-align:right;>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td style=text-align:right;><span id="REAL_TOTAL_PRICE' + index + '">' + mouigosa.SUBJECT_REAL_PRICE + '</span><input type="button" onclick="goup('+ leccodes +')" value="변경" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="SUBJECT_REAL_PRICE2" id="SUBJECT_REAL_PRICE2" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="LECCODE" id="LECCODE" value="' + mouigosa.LECCODE + '"></tr>');					
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
	
	/* alert("TOTAL_PRICE:"+tmp +"\n"+
			 "USER_ID:"+tmp2); */
	
	 $("#PRICE").val(tmp2);
	 
	 $("#PRICE_DISCOUNT").val("");
	 
	 $("#PRICE_CARD").val("0");
	 $("#PRICE_CASH").val("0");
	 $("#PRICE_BANK").val("0");
	 $("#PRICE_UNPAID").val(tmp2);
}

//변경
function goup(LECCODE) {
	//alert("LECCODE:"+LECCODE);

	window.open('<c:url value="/productOrder/pop_price_change.pop"/>?LECCODE=' + LECCODE
			 + '&searchkey=' + escape(encodeURIComponent(searchkey)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=yes,width=600,height=200,top=0,left=0,location=no,scrollbars=no');
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("REG_DT");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("DUE_DT");
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
	$('#searchFrm').attr('action','<c:url value="/productOrder/off_refund_list.do"/>').submit();
	
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
	
	if ($('#PRICE').val() == "") {
		alert('품목변경을 해서 결제대상금액을 입력해주세요.');
		$('#PRICE').focus();
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
		alert("품목변경을 해서 품목을 선택해 주세요.");
		return;
	}				
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/registrationRefundInsert.do"/>').submit();
}


//삭제
function goDel() {	
	if(confirm("삭제하시겠습니까?")) {
		$('#searchFrm').attr('action','<c:url value="/productOrder/registrationDelete.do"/>').submit();
	}
}

//적용 부모페이지
function getSubPrice(LECCODE,PRICE){
	
	/* alert("LECCODE:"+LECCODE +"\n"+
			 "PRICE:"+PRICE); */
		
	$("input[name=LECCODE]").each(function (index){
		
		if(LECCODE == $(this).val()){	
			document.getElementById("REAL_TOTAL_PRICE"+index).innerHTML = PRICE;
			searchFrm.SUBJECT_REAL_PRICE2[index].value = PRICE;
		}
		
	});
	
	
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
	
	/* alert("TOTAL_PRICE:"+tmp +"\n"+
			 "USER_ID:"+tmp2); */
	
	 $("#PRICE").val(tmp2);
	 
	 $("#PRICE_DISCOUNT").val("");
	 
	 $("#PRICE_CARD").val("0");
	 $("#PRICE_CASH").val("0");
	 $("#PRICE_BANK").val("0");
	 $("#PRICE_UNPAID").val(tmp2);
}

// 라디오버튼 onchange
function radio_chk() {	
	getSubCODE('CLASSSERIESCODE', '<c:url value="/productOrder/subCode.do"/>', $('#USER_ID').val());
}

//금액 onchange 자동계산
function self_dis(){
	var PRICE = "";
	var REAL_TOTAL_PRICE = document.getElementById("REAL_TOTAL_PRICE").innerHTML;
	
	
	if (searchFrm.PRICE_DISCOUNT_TYPE1.checked == true) {
		PRICE = parseInt(REAL_TOTAL_PRICE) - (parseInt(REAL_TOTAL_PRICE) * parseInt($('#PRICE_DISCOUNT').val())/100);
	}else{
		PRICE = parseInt(REAL_TOTAL_PRICE) - parseInt($('#PRICE_DISCOUNT').val());
	}
	
	 /* alert("PRICE_DISCOUNT_TYPE:"+$('#PRICE_DISCOUNT_TYPE').val() +"\n"+
			 "REAL_TOTAL_PRICE:"+REAL_TOTAL_PRICE +"\n"+
			"PRICE_DISCOUNT:"+$('#PRICE_DISCOUNT').val() +"\n"+
			"PRICE:"+PRICE); */
	 
	$('#PRICE').val(PRICE);
	 		
			
	  var tmp ="";
	  var tmp2 ="";
	$("input[name=SUBJECT_REAL_PRICE2]").each(function (index){
		
		if (searchFrm.PRICE_DISCOUNT_TYPE1.checked == true) {
			tmp2 = parseInt($(this).val()) - (parseInt($(this).val()) * parseInt($('#PRICE_DISCOUNT').val())/100);
			
			document.getElementById("REAL_TOTAL_PRICE"+index).innerHTML = tmp2;
			searchFrm.SUBJECT_REAL_PRICE2[index].value = tmp2;
			
			if(tmp == null || tmp == ""){
				tmp = tmp2;
			}else{
				tmp = parseInt(tmp) + parseInt(tmp2);
			}
		}else{
			tmp2 = parseInt($(this).val()) - (parseInt(parseInt($(this).val()) / parseInt(document.getElementById("TOTAL_PRICE").innerHTML) * parseInt($('#PRICE_DISCOUNT').val())));
			
			document.getElementById("REAL_TOTAL_PRICE"+index).innerHTML = tmp2;
			searchFrm.SUBJECT_REAL_PRICE2[index].value = tmp2;
			
			if(tmp == null || tmp == ""){
				tmp = tmp2;
			}else{
				tmp = parseInt(tmp) + parseInt(tmp2);
			}
		}
		
	});
	
	if(parseInt(tmp) > parseInt(PRICE)){
		document.getElementById("REAL_TOTAL_PRICE0").innerHTML = parseInt(document.getElementById("REAL_TOTAL_PRICE0").innerHTML) - parseInt((parseInt(tmp) - parseInt(PRICE)));
		searchFrm.SUBJECT_REAL_PRICE2[0].value = parseInt(searchFrm.SUBJECT_REAL_PRICE2[0].value) - parseInt((parseInt(tmp) - parseInt(PRICE)));
	}else if(parseInt(tmp) < parseInt(PRICE)){
		document.getElementById("REAL_TOTAL_PRICE0").innerHTML = parseInt(document.getElementById("REAL_TOTAL_PRICE0").innerHTML) + parseInt((parseInt(PRICE) - parseInt(tmp)));
		searchFrm.SUBJECT_REAL_PRICE2[0].value = parseInt(searchFrm.SUBJECT_REAL_PRICE2[0].value) + parseInt((parseInt(PRICE) - parseInt(tmp)));
	}
	
	/* alert("REAL_TOTAL_PRICE0:"+document.getElementById("REAL_TOTAL_PRICE0").innerHTML +"\n"+
			 "SUBJECT_REAL_PRICE2:"+searchFrm.SUBJECT_REAL_PRICE2[0].value +"\n"+
			"SUBJECT_REAL_PRICE2:"+searchFrm.SUBJECT_REAL_PRICE2[1].value); */
	
	document.getElementById("REAL_TOTAL_PRICE").innerHTML = PRICE;
	
	 	 
	 $("#PRICE_CARD").val("0");
	 $("#PRICE_CASH").val("0");
	 $("#PRICE_BANK").val("0");
	 $("#PRICE_UNPAID").val(PRICE);
}

//금액 onchange 자동계산
function self_dis2(){
	var PRICE = $('#PRICE').val();
	
	var PRICE_CARD = $('#PRICE_CARD').val();
	var PRICE_CASH = $('#PRICE_CASH').val();
	var PRICE_BANK = $('#PRICE_BANK').val();
	var PRICE_UNPAID = $('#PRICE_UNPAID').val();
	
	PRICE_UNPAID = parseInt(PRICE) - (parseInt(PRICE_CARD) + parseInt(PRICE_CASH) + parseInt(PRICE_BANK));
	
	$('#PRICE_UNPAID').val(PRICE_UNPAID);
}

//전액버튼 onchange 자동계산
function self_dis3(cnt){
	var PRICE = $('#PRICE').val();
	
	if (cnt == "1") {
		$("#PRICE_CARD").val(PRICE);
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val("0");
		$("#PRICE_UNPAID").val("0");
	}else if (cnt == "2") {
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val(PRICE);
		$("#PRICE_BANK").val("0");
		$("#PRICE_UNPAID").val("0");
	}else if (cnt == "3") {
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val(PRICE);
		$("#PRICE_UNPAID").val("0");
	}else{
		$("#PRICE_CARD").val("0");
		$("#PRICE_CASH").val("0");
		$("#PRICE_BANK").val("0");
		$("#PRICE_UNPAID").val(PRICE);
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
	window.open('<c:url value="/productOrder/offererPrint.pop"/>?PRINT_STS=2&ORDERNO=' + $('#ORDERNO').val()
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

function view(ORDERNO,USER_ID) {
	//alert("orderno:"+orderno);
	
	$("#ORDERNO").val(ORDERNO);
	$("#USER_ID").val(USER_ID);
	$('#searchFrm').attr('action','<c:url value="/productOrder/updatePage.do"/>').submit();
}
</script>
</head>


<!--content -->
<div id="content">
<form name="searchFrm" id="searchFrm" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

<input type="hidden" id="orderstatuscode" name="orderstatuscode" />
<input type="hidden" id="paycode" name="paycode" />
<input type="hidden" id="sdate" name="sdate" />
<input type="hidden" id="edate" name="edate" />

<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<input type="hidden" id="ORDERNO" name="ORDERNO" value="${searchMap.ORDERNO}" />

	<h2>● 수강신청관리 > <strong>수강신청</strong></h2>	
    	<table class="table01">
    	
    	<%-- <c:if test="${sts eq  'U'}">
	    	<tr>
	    		<th >주문번호</th>
	   			<td>
	   				${searchMap.ORDERNO}
	   			</td>
	   		</tr>
   		</c:if> --%>
   		
    	<tr>
    		<th >ID</th>
   			<td>
   				<div class="item" >
   						<input type="text" id="USER_ID" name="USER_ID"  title="레이블 텍스트" value="${searchMap.USER_ID}" maxlength="" style="width:20% ; background:#FFECEC;" />
		   				<input type="button" onclick='javascript:id_chk();' value="ID회원체크" />
   					<%-- <c:if test="${sts eq  'I'}">
		   				<input type="text" id="USER_ID" name="USER_ID"  title="레이블 텍스트" value="${searchMap.USER_ID}" maxlength="" style="width:20% ; background:#FFECEC;" />
		   				<input type="button" onclick='javascript:id_chk();' value="ID회원체크" />
	   				</c:if>
	   				
	   				<c:if test="${sts eq  'U'}">
	   					${searchMap.USER_ID}
	   					<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}" />
	   				</c:if> --%>
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
   			<td >
   					<input type="text" id="PHONE_NO" name="PHONE_NO"  title="레이블 텍스트" value="${searchMap.PHONE_NO}" maxlength="20" style="width:15%; background:#FFECEC;" onkeyup="chk(this,'PHONE_NO')" onblur="chk(this,'PHONE_NO')"/>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >E-Mail</th>
   			<td >
   					<input type="text" id="EMAIL" name="EMAIL"  title="레이블 텍스트" value="${searchMap.EMAIL}" maxlength="" style="width:20%; background:#FFECEC;" />
   			</td>
   		</tr>
   		
   		
   		<tr>
    		<th >품목</th>
   			<td style="float:left; width:100%;">
		     	<!--테이블-->
			         
		           <div class="form_table" style="margin-top:10px; float:left; width:85%;">
			           <table class="tbl_type" style="margin-top:10px; float:left; width:85%;" border="1" cellspacing="0" summary="받은쪽지">
			             <caption>
			             </caption>
			             <colgroup>
			             <col width="15%">
			             <col width="5%">
			             <col width="5%">
			             </colgroup>
			             <thead>
			               <tr>
			               	 <th scope="col">품목</th>
			                 <th scope="col">원수강료</th>
			                 <th scope="col">결제대상금액</th>
			               </tr>
			             </thead>
			             
			             <tbody id="CLASSSERIESCODE" style="background-color:#efefef">
			             <tr bgColor=#ffffff align=center> 
							<td colspan="3" style="text-align:center;">등록된 신청 내역이 없습니다.</td>
						 </tr>			             
						 </tbody>
			             
			              <tr> 
							<td style="text-align:right;">합계</td>
							<td style="text-align:right;"><span id="TOTAL_PRICE">0</span></td>
							<td style="text-align:center;"><span id="REAL_TOTAL_PRICE">0</span></td>
						 </tr>
						 
			           </table>
		         </div>
		         <!--//테이블-->
		     </td>
   		</tr>
   		
   		<tr>
    		<th >관리자 추가할인</th>
   			<td >
				<input name="PRICE_DISCOUNT_TYPE" id="PRICE_DISCOUNT_TYPE1" value="R" type="radio" checked="checked" onchange="radio_chk();" <c:if test="${searchMap.PRICE_DISCOUNT_TYPE == 'R' }">checked="checked"</c:if>/>비율(%)
				&nbsp;
				<input name="PRICE_DISCOUNT_TYPE" id="PRICE_DISCOUNT_TYPE2" value="W" type="radio" onchange="radio_chk();" <c:if test="${searchMap.PRICE_DISCOUNT_TYPE == 'W' }">checked="checked"</c:if>/>정액(원)
				&nbsp;
   				<input type="text" id="PRICE_DISCOUNT" name="PRICE_DISCOUNT"  title="레이블 텍스트" value="${searchMap.PRICE_DISCOUNT}" style="width:10%; background:#FFECEC;" onkeyup="chk(this,'PRICE_DISCOUNT');" onblur="chk(this,'PRICE_DISCOUNT');"/>
   				<input type="button" onclick='javascript:self_dis();' value="할인적용" />
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
   				<!-- <br>
  				미수금<input type="text" id="PRICE_UNPAID" name="PRICE_UNPAID"  title="레이블 텍스트" value="${searchMap.PRICE_UNPAID}" maxlength="20" style="width:10%; background:#FFECEC;"  readonly="readonly"/> &nbsp;
   				<input type="button" onclick='javascript:self_dis3(4);' value="전액" />&nbsp;&nbsp;&nbsp;&nbsp;
  				*결제완료예정일자<input type="text" id="DUE_DT" name=DUE_DT maxlength="10" class="i_text" value="${searchMap.DUE_DT}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
   				-->		
   			</td>
   		</tr>
   		
   		<tr>
    		<th >비고</th>
   			<td >
   				<textarea rows="4" style="width:60%;" name="MEMO" id="MEMO">${searchMap.MEMO}</textarea>
   			</td>
   		</tr>
   		
   		<tr>
    		<th >등록일자</th>
   			<td >
   				<input type="text" id="REG_DT" name="REG_DT" maxlength="10" class="i_text" value="${searchMap.REG_DT}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
   			</td>
   		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:checkParam();">저장</a></li>
      <li><a href="javascript:goList();">목록</a></li>
      
      <%-- <c:if test="${sts eq  'U'}">
	      <c:if test="${searchMap.TICKET_PRINT_DT eq  null}">
	      	<li><a href="javascript:fn_Print();">수강증출력</a></li>
	      </c:if>
	      
	      <c:if test="${searchMap.TICKET_PRINT_DT ne  null}">
	      	<li><a href="javascript:printRe();">수강증복원</a></li>
	      </c:if>
	      
	      <li><a href="javascript:goDel();">삭제</a></li>
      </c:if> --%>
      
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
