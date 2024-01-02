<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
var listSize = "${listSize}";

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

window.onload = function () {	
	//alert("listSize:"+listSize);
	
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
			
	
}

function lecview_pop(leccode, bridge_leccode, orderno, orderid) {
	window.open('<c:url value="/lecture/dataViewList.pop"/>?LECCODE=' + leccode + '&BRIDGE_LECCODE=' + bridge_leccode + '&ORDERNO=' + orderno + '&ORDERID=' + orderid, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1100,height=670');
}

// 20130102 단과할인글자 표시 깜빡이게 하는부분		
function fn_bright() {
	 
	 var color1 = "#000000"; // 기본   색상코드 RGB
	 var color2 = "#FF99CC"; // 변경될 색상코드 RGB
	 var interval = 250;    // millisecond 1000[1초], 2000[2초]
	 
	 var obj = document.getElementById('BRIGHT');
	 
	 if(obj.style.color == color1) {
	  obj.style.color = color2;
	 } else {
	  obj.style.color = color1;
	 }
	 
	 timeID = setTimeout("fn_bright();", interval);
}

function mylecture_start_date_onkeyup(ii, no)
{	
	if (parseInt(listSize) > 0) {
			if (document.popFrm.mylecture_start_date.value != document.popFrm.mylecture_start_date_old.value)
			{
				document.popFrm.lecture_no2.value = no;
				return;
			}
			document.popFrm.lecture_no2.value = "";
	}
}

function mylecture_period_onkeyup(ii, no)
{
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_period.value != document.popFrm.mylecture_period_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}
}

function mylecture_paused_date1_onkeyup(ii, no)
{	
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_date1.value != document.popFrm.mylecture_paused_date1_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}	
}

function mylecture_paused_period1_onkeyup(ii, no)
{
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_period1.value != document.popFrm.mylecture_paused_period1_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}
}

function mylecture_paused_date2_onkeyup(ii, no)
{
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_date2.value != document.popFrm.mylecture_paused_date2_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}
}

function mylecture_paused_period2_onkeyup(ii, no)
{
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_period2.value != document.popFrm.mylecture_paused_period2_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	
	}
}

function mylecture_paused_date3_onkeyup(ii, no)
{
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_date3.value != document.popFrm.mylecture_paused_date3_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}
}

function mylecture_paused_period3_onkeyup(ii, no)
{	
	if (parseInt(listSize) > 0) {
				if (document.popFrm.mylecture_paused_period3.value != document.popFrm.mylecture_paused_period3_old.value)
				{
					document.popFrm.lecture_no2.value = no;
					document.popFrm.Flag2.value = "S";
					return;
				}
				document.popFrm.lecture_no2.value = "";
	}
}

//수정하기 버튼
function form_submit()
{
	if (parseInt(listSize) > 0) {
		if(document.popFrm.lecture_no2.value == "") {
			alert("수정된 내역이 존재하지 않습니다.");
			return;
		}
		
		if (confirm("수정 하시겠습니까?"))
		{
			
			$('#popFrm').attr('action','<c:url value="/productOrder/play_gostop_in_update.do"/>').submit();
		}
	}
}


$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("mylecture_start_date");
	initDatePicker1("mylecture_paused_date1");
	initDatePicker1("mylecture_paused_date1_2");
	initDatePicker1("mylecture_paused_date2");
	initDatePicker1("mylecture_paused_date2_2");
	initDatePicker1("mylecture_paused_date3");
	initDatePicker1("mylecture_paused_date3_2");
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

function onChan1() {
	if (!$("#mylecture_paused_date1").val() == "" && !$("#mylecture_paused_date1_2").val() == "") {
		if(parseInt($("#mylecture_paused_date1_2").val().replace(/-/g,'')) < parseInt($("#mylecture_paused_date1").val().replace(/-/g,''))){
			alert('1차중지 종료일은 시작일보다 큰 날짜를 선택하세요.');
			return;
		}
		
		var dateVal1 = $("#mylecture_paused_date1").val();
		var dateVal2 = $("#mylecture_paused_date1_2").val();
		var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
		var date2 = new Date(dateVal2.substring(0,4)+"/"+dateVal2.substring(4,6)+"/"+dateVal2.substring(6,8));
		var interval = date2 - date1;
		var day   =  1000*60*60*24;
		var period = parseInt(interval/day) + 1;
		$("#mylecture_paused_period1").val(period);
	}

}

function onChan1_1() {
	if ($('#mylecture_paused_date1').val() == "") {
		alert('1차중지 시작일을 입력해 주세요.');
		$('#mylecture_paused_date1').focus();
		return;
	}
	
	var dateVal1 = $("#mylecture_paused_date1").val();
	var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
 	var plusDays = parseInt($("#mylecture_paused_period1").val());
 	var returnVal = new Date(Date.parse(date1) + plusDays * 1000 * 60 * 60 * 24 - 1);

 	var yyyy = returnVal.getFullYear().toString();                                    
    var mm = (returnVal.getMonth()+1).toString(); // getMonth() is zero-based         
    var dd  = returnVal.getDate().toString();             
 	var eDate = yyyy + '' + (mm.length=='2'?mm:"0"+mm) + '' + (dd.length=='2'?dd:"0"+dd);
 	if(isNaN(plusDays) || plusDays == 0) $("#mylecture_paused_date1_2").val('');
 	else $("#mylecture_paused_date1_2").val(eDate);

}

function onChan2() {
	if (!$("#mylecture_paused_date2").val() == "" && !$("#mylecture_paused_date2_2").val() == "") {
		if(parseInt($("#mylecture_paused_date1_2").val().replace(/-/g,'')) < parseInt($("#mylecture_paused_date1").val().replace(/-/g,''))){
			alert('2차중지 종료일은 시작일보다 큰 날짜를 선택하세요.');
			return;
		}
		
		var dateVal1 = $("#mylecture_paused_date2").val();
		var dateVal2 = $("#mylecture_paused_date2_2").val();
		var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
		var date2 = new Date(dateVal2.substring(0,4)+"/"+dateVal2.substring(4,6)+"/"+dateVal2.substring(6,8));
		var interval = date2 - date1;
		var day   =  1000*60*60*24;
		var period = parseInt(interval/day) + 1;
		$("#mylecture_paused_period2").val(period);
	}

}

function onChan2_1() {
	if ($('#mylecture_paused_date2').val() == "") {
		alert('2차중지 시작일을 입력해 주세요.');
		$('#mylecture_paused_date2').focus();
		return;
	}
	
	var dateVal1 = $("#mylecture_paused_date2").val();
	var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
 	var plusDays = parseInt($("#mylecture_paused_period2").val());
 	var returnVal = new Date(Date.parse(date1) + plusDays * 1000 * 60 * 60 * 24 - 1);

 	var yyyy = returnVal.getFullYear().toString();                                    
    var mm = (returnVal.getMonth()+1).toString(); // getMonth() is zero-based         
    var dd  = returnVal.getDate().toString();             
 	var eDate = yyyy + '' + (mm.length=='2'?mm:"0"+mm) + '' + (dd.length=='2'?dd:"0"+dd);
 	
 	if(isNaN(plusDays) || plusDays == 0) $("#mylecture_paused_date2_2").val('');
 	else $("#mylecture_paused_date2_2").val(eDate);
}

function onChan3() {
	if (!$("#mylecture_paused_date3").val() == "" && !$("#mylecture_paused_date3_2").val() == "") {
		if(parseInt($("#mylecture_paused_date3_2").val().replace(/-/g,'')) < parseInt($("#mylecture_paused_date3").val().replace(/-/g,''))){
			alert('3차중지 종료일은 시작일보다 큰 날짜를 선택하세요.');
			return;
		}
		var dateVal1 = $("#mylecture_paused_date3").val();
		var dateVal2 = $("#mylecture_paused_date3_2").val();
		var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
		var date2 = new Date(dateVal2.substring(0,4)+"/"+dateVal2.substring(4,6)+"/"+dateVal2.substring(6,8));
		var interval = date2 - date1;
		var day   =  1000*60*60*24;
		var period = parseInt(interval/day) + 1;
		$("#mylecture_paused_period3").val(period);
	}

}

function onChan3_1() {
	if ($('#mylecture_paused_date3').val() == "") {
		alert('3차중지 시작일을 입력해 주세요.');
		$('#mylecture_paused_date3').focus();
		return;
	}
	var dateVal1 = $("#mylecture_paused_date3").val();
	var date1 = new Date(dateVal1.substring(0,4)+"/"+dateVal1.substring(4,6)+"/"+dateVal1.substring(6,8));
 	var plusDays = parseInt($("#mylecture_paused_period3").val());
 	var returnVal = new Date(Date.parse(date1) + plusDays * 1000 * 60 * 60 * 24 - 1);

 	var yyyy = returnVal.getFullYear().toString();                                    
    var mm = (returnVal.getMonth()+1).toString(); // getMonth() is zero-based         
    var dd  = returnVal.getDate().toString();             
 	var eDate = yyyy + '' + (mm.length=='2'?mm:"0"+mm) + '' + (dd.length=='2'?dd:"0"+dd);
 	
 	if(isNaN(plusDays) || plusDays == 0) $("#mylecture_paused_date3_2").val("");
 	else $("#mylecture_paused_date3_2").val(eDate);
}

function fn_refresh() {
	$('#popFrm').attr('action','<c:url value="/productOrder/plyer_gostop.pop"/>').submit();
}

//누락된 강의 등록하기
function insertMylecture()
{
	if (confirm("[${searchMap.package_no}] 강의를 등록 하시겠습니까?")) {
		$('#popFrm').attr('action','<c:url value="/productOrder/insertMylecture.do"/>').submit();
	}
}
</script>
</head>

	
<!--팝업-->
<form id="popFrm" name="popFrm" method="post">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
								  
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

<input type="hidden" id="popup" name="popup" value="yes" />

<input type="hidden" id="userid" name="userid" value="${searchMap.userid}" />
<input type="hidden" id="orderno" name="orderno" value="${searchMap.orderno}" />
<input type="hidden" id="package_no" name="package_no" value="${searchMap.package_no}" />
<input type="hidden" id="lecture_no" name="lecture_no" value="" />
<input type="hidden" id="search_type" name="search_type" value="" />
<input type="hidden" id="search_text" name="search_text" value="" />
<input type="hidden" id="search_date1" name="search_date1" value="" />
<input type="hidden" id="search_date2" name="search_date2" value="" />
<input type="hidden" id="search_date3" name="search_date3" value="" />
<input type="hidden" id="cmd" name="cmd" />

	<table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF" colspan="2"><p>▣ 주문번호 : ${searchMap.orderno}, 수강자 : ${searchMap.USER_NM} (${searchMap.USER_ID})  &#13;</p></td>
        <td><p>▣ 강의선택</p></td>
        <td>
        	<c:set var="searchLeccodeCheck" value="${searchMap.searchLeccode}"/> 
        	<select id="searchLeccode" name="searchLeccode" onchange="fn_refresh();">
        		<c:forEach items="${list}" var="list" varStatus="status">
        			<option value="${list.LECTURE_NO}" <c:if test="${searchMap.searchLeccode eq list.LECTURE_NO}"> selected</c:if>>${list.PACKAGE_NAME }</option>
        			<c:if test='${searchMap.searchLeccode eq "" and status.index eq 0}'>
		        		<c:set var="searchLeccodeCheck" value="${list.LECTURE_NO}"/>
		        	</c:if> 
        		</c:forEach>
        	</select>
			<c:if test="${empty list}">
				<p><a href="javascript:insertMylecture();"><font style='color:red;'>[ 누락 강의 등록 ]</font></a></p>
			</c:if>
        </td>
    </table>
    
	<!--테이블-->
          
    <table class="table02">
        <tr>          
          <th>강의명</th>
          <th>기간</th>
          <th>1차 중지</th>
          <th>2차 중지</th>
          <th>3차 중지</th>
        </tr>
        <tbody>
        
        <c:if test="${not empty list}">
         <c:forEach items="${list}" var="list" varStatus="status">
         	<c:if test="${searchLeccodeCheck eq list.LECTURE_NO}">
	         <input type="hidden" name="mylecture_start_date_old" id="mylecture_start_date_old" value="${list.MYLECTURE_START_DATE}">
	         <input type="hidden" name="mylecture_period_old" id="mylecture_period_old" value="${list.MYLECTURE_PERIOD}">
	         
	         <input type="hidden" name="mylecture_paused_date1_old" id="mylecture_paused_date1_old" value="${list.MYLECTURE_PAUSED_DATE1}">
	         <input type="hidden" name="mylecture_paused_period1_old" id="mylecture_paused_period1_old" value="${list.MYLECTURE_PAUSED_PERIOD1}">
	         <input type="hidden" name="mylecture_paused_date2_old" id="mylecture_paused_date2_old" value="${list.MYLECTURE_PAUSED_DATE2}">
	         <input type="hidden" name="mylecture_paused_period2_old" id="mylecture_paused_period2_old" value="${list.MYLECTURE_PAUSED_PERIOD2}">
	         <input type="hidden" name="mylecture_paused_date3_old" id="mylecture_paused_date3_old" value="${list.MYLECTURE_PAUSED_DATE3}">
	         <input type="hidden" name="mylecture_paused_period3_old" id="mylecture_paused_period3_old" value="${list.MYLECTURE_PAUSED_PERIOD3}">
              
            <tr>
              <td style="text-align:left;">
           		<c:if test="${list.SPO eq 'spo_spo' }">
           			<b>30%할인(검찰청)</b>, &nbsp;
           		</c:if>
           		<a href="javascript:lecview_pop('${list.PACKAGE_NO}', '${list.BRIDGE_LECCODE }', '${searchMap.orderno}', '${searchMap.USER_ID}')">${list.PACKAGE_NAME}</a>
              </td>
              
              <td>
           		<input type="text" id="mylecture_start_date" name="mylecture_start_date" maxlength="10" class="i_text" value="${list.MYLECTURE_START_DATE }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="mylecture_start_date_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		~
           		${list.MYLECTURE_END_DATE}
           		<br>&nbsp;
           		<c:if test="${list.MYLECTURE_PAUSED_COUNT ne 0 }">
           			<font color="red">
           		</c:if>
           		(<input type="text" name="mylecture_period" id="mylecture_period" value="${list.MYLECTURE_PERIOD}" style="width:30%;" onkeyup="mylecture_period_onkeyup('${status.index}', '${list.LECTURE_NO}');"><!-- 20130102 15%할인 받을경우 10일추가 20%할인 받은경우 20일 추가부분 -->
           		
           		<c:if test="${list.MYLECTURE_PAUSED_COUNT ne 0 }">
           			+ ${list.MYLECTURE_PAUSED_PERIOD}
           		</c:if>
           		일)
           		<c:if test="${list.MYLECTURE_PAUSED_COUNT ne 0 }">
           			+ ${list.MYLECTURE_PAUSED_COUNT}회
           		</c:if>
              </td>
              
              <td>
              	<input type="hidden" id="lecture_no2" name="lecture_no2" value="" />
              	<!-- 
              		1. 강의 일시중지 페이지에서 강의 시작 날짜만 변경해도 TB_MYLECTURE_PAUSE 테이블에 카운트가 올라가거나 Insert되는 현상 발생. 
              		2. 기간만 변경시 TB_MYLECTURE_PAUSE 테이블에 데이터를 Insert 하거나 Update 안되게 해달라고 요청(김상구 실장님)
              		3. 일시중지 자료 입력시에 Flag2에 S값을 넣어 보낸뒤 Java소스(ProductOrderController) 쪽에서 Flag2값 확인후 S값인 경우에만 TB_MYLECTURE_PAUSE 테이블에 Insert 혹은 Update하게 하는 방향으로 수정
              	-->	
              	<input type="hidden" id="Flag2" name="Flag2" value="" /> 
           		<input type="text" id="mylecture_paused_date1" name="mylecture_paused_date1" maxlength="10" class="i_text" value="${list.MYLECTURE_PAUSED_DATE1 }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan1();mylecture_paused_date1_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		~
           		<input type="text" id="mylecture_paused_date1_2" name="mylecture_paused_date1_2" maxlength="10" class="i_text" value="" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan1();mylecture_paused_date1_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		<br>
           		<input type="text" name="mylecture_paused_period1" id="mylecture_paused_period1" value="${list.MYLECTURE_PAUSED_PERIOD1}" style="width:20%;" maxlength="3" onkeyup="onChan1_1();mylecture_paused_period1_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);">
           		일간
              </td>
              
              <td>
           		<input type="text" id="mylecture_paused_date2" name="mylecture_paused_date2" maxlength="10" class="i_text" value="${list.MYLECTURE_PAUSED_DATE2 }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan2();mylecture_paused_date2_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		~
           		<input type="text" id="mylecture_paused_date2_2" name="mylecture_paused_date2_2" maxlength="10" class="i_text" value="" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan2();mylecture_paused_date2_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		<br>
           		<input type="text" name="mylecture_paused_period2" id="mylecture_paused_period2" value="${list.MYLECTURE_PAUSED_PERIOD2}" style="width:20%;" maxlength="3" onkeyup="onChan2_1();mylecture_paused_period2_onkeyup('${status.index}', '${list.LECTURE_NO}');" onkeyup="mylecture_paused_period2_onkeyup('${status.index}', '${list.LECTURE_NO}');">
           		일간
              </td>
              
              <td>
           		<input type="text" id="mylecture_paused_date3" name="mylecture_paused_date3" maxlength="10" class="i_text" value="${list.MYLECTURE_PAUSED_DATE3 }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan3();mylecture_paused_date3_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		~
           		<input type="text" id="mylecture_paused_date3_2" name="mylecture_paused_date3_2" maxlength="10" class="i_text" value="" readonly="readonly" style="width:90px;IME-MODE:disabled;" onChange="onChan3();mylecture_paused_date3_onkeyup('${status.index}', '${list.LECTURE_NO}');" onKeyDown="onOnlyNumber(this);"/>
           		<br>
           		<input type="text" name="mylecture_paused_period3" id="mylecture_paused_period3" value="${list.MYLECTURE_PAUSED_PERIOD3}" style="width:20%;" maxlength="3" onkeyup="onChan3_1();mylecture_paused_period3_onkeyup('${status.index}', '${list.LECTURE_NO}');" onkeyup="mylecture_paused_period3_onkeyup('${status.index}', '${list.LECTURE_NO}');">
           		일간
              </td>			              
            </tr>
            </c:if>  
     	</c:forEach>
		</c:if>
		
		<c:if test="${empty list}">
		          <tr bgColor=#ffffff align=center> 
				<td colspan="5">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 
        </tbody>
    </table>    
          
    <!--//테이블-->
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:form_submit();">수정하기</a></li>
   	  <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
    
    
    <!--테이블-->
    <c:if test="${not empty pmp_list}">
    <c:forEach items="${pmp_list}" var="pmp_list" varStatus="status">
    <c:if test="${status.index eq 0}">
	    <script language="javascript">
			alert('이 회원은 PMP 다운 받은 내역이 있으므로 \n\n           ★ 환불 불가 ★');
		</script>
	</c:if>
    <table class="table02">
        <tr>          
          <th>PmP회차</th>
          <th>강의명</th>
          <th>PmP_Download 날짜</th>
        </tr>
        <tbody>              
            <tr>
              <td>
           		${pmp_list.MOVIE_ORDER1}회 ${pmp_list.MOVIE_ORDER2}강
              </td>
              
              <td>
           		${pmp_list.MOVIE_NAME}
              </td>
              
              <td>
           		${pmp_list.PMP_REGDATE}
              </td>			              
            </tr>
        </tbody>
    </table>        
    </c:forEach>
	</c:if>      
    <!--//테이블-->
    <!--테이블-->
    
    <Br>
    <Br>
    <c:if test="${not empty mobile_list}">
    <c:forEach items="${mobile_list}" var="mobile_list" varStatus="status">
    
    <c:if test="${status.index eq 0}">
	    <script language="javascript">
			alert('이 회원은 Mobile 다운 받은 내역이 있으므로 \n\n           ★ 환불 불가 ★');
		</script>
	</c:if>		      
    <table class="table02">
        <tr>          
          <th>Mobile회차</th>
          <th>강의명</th>
          <th>Mobile_Download 날짜</th>
        </tr>
        <tbody>              
            <tr>
              <td>
           		${mobile_list.MOVIE_ORDER1}회 ${mobile_list.MOVIE_ORDER2}강
              </td>
              
              <td>
           		${mobile_list.MOVIE_NAME}
              </td>
              
              <td>
           		${mobile_list.MOBILE_REGDATE}
              </td>			              
            </tr>
        </tbody>
    </table>        
    </c:forEach>
	</c:if>      
    <!--//테이블-->
    
</form>
</html>


