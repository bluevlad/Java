<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">

<script type="text/javascript">
var message = "${searchMap.message}";
//alert("message:"+message);
var search_date_type = "${searchMap.search_date_type}";
var payall = "${searchMap.payall}";
//alert("payall:"+payall);

//var MOCKCODE = "${searchMap.MOCKCODE}";
//alert("MOCKCODE:"+MOCKCODE);

window.onload = function () {
	 if(message != "") {
		alert(message);
	}
	/* alert("S_EXAMYEAR:"+S_EXAMYEAR +"\n"+
	"S_EXAMROUND:"+S_EXAMROUND +"\n"+
	"S_searchFlag:"+S_searchFlag +"\n"+
	"S_searchKeyWord:"+S_searchKeyWord +"\n"+
	"S_currentPage:"+S_currentPage +"\n"+
	"S_pageRow:"+S_pageRow); */
}

function set_date(ii, i, total_count, row_count){
	if(total_count == 1 && row_count == 1) {
		if(i != 1 || i != 2){
			var statuscode_old = document.searchFrm.statuscode_old.value;
			var statuscode = i;

			if(statuscode != "" && statuscode_old != ""){
				if(statuscode != statuscode_old){
					document.searchFrm.mgntno_change.value=document.searchFrm.mgntno.value;
				}
			}

		}
		if(i == 1 || i == 2){
			var confirmdate = document.searchFrm.confirmdate.value;
			var isconfirm = document.searchFrm.isconfirm.value;

			var confirmdate_old = document.searchFrm.confirmdate_old.value;
			var isconfirm_old = document.searchFrm.isconfirm_old.value;
		}

		if(i == 1){
			//입금일
			if(confirmdate != "" && confirmdate_old != ""){
				if(confirmdate != confirmdate_old){
					document.searchFrm.mgntno_change.value=document.searchFrm.mgntno.value;
				}
			}
		}

		if(i == 2){
		//입금확인일
			if(isconfirm != "" && isconfirm_old != ""){
				if(isconfirm != isconfirm_old){
					document.searchFrm.mgntno_change.value=document.searchFrm.mgntno.value;
				}
			}
		}
	} else {
		if(i != 1 || i != 2){
			var statuscode_old = document.searchFrm.statuscode_old[ii].value;
			var statuscode = i;

			if(statuscode != "" && statuscode_old != ""){
				if(statuscode != statuscode_old){
					document.searchFrm.mgntno_change[ii].value=document.searchFrm.mgntno[ii].value;
				}
			}

		}
		if(i == 1 || i == 2){
			var confirmdate = document.searchFrm.confirmdate[ii].value;
			var isconfirm = document.searchFrm.isconfirm[ii].value;

			var confirmdate_old = document.searchFrm.confirmdate_old[ii].value;
			var isconfirm_old = document.searchFrm.isconfirm_old[ii].value;
		}

		if(i == 1){
			//입금일
			if(confirmdate != "" && confirmdate_old != ""){
				if(confirmdate != confirmdate_old){
					document.searchFrm.mgntno_change[ii].value=document.searchFrm.mgntno[ii].value;
				}
			}
		}

		if(i == 2){
		//입금확인일
			if(isconfirm != "" && isconfirm_old != ""){
				if(isconfirm != isconfirm_old){
					document.searchFrm.mgntno_change[ii].value=document.searchFrm.mgntno[ii].value;
				}
			}
		}
	}
}

//주문번호 클릭
function go_view(orderno) {
	//alert("orderno:"+orderno);

	$("#orderno").val(orderno);
	$('#searchFrm').attr('action','<c:url value="/productOrder/update.do"/>').submit();
}

//상품명 클릭 팝업
function plyer_gostop(userid,orderno, package_no){
	window.open('<c:url value="/productOrder/plyer_gostop.pop"/>?userid=' + userid + '&orderno=' + orderno + '&package_no=' + package_no
			 + '&orderstatuscode=' + $("#orderstatuscode").val() + '&search_date_type=' + escape(encodeURIComponent(search_date_type))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val() + '&paycode=' + $("#paycode").val()
			 + '&payall=' + $("#payall").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=yes,width=1000,height=510,top=0,left=0,scrollbars=yes');
}

//구입금액 클릭 팝업
function pop_refund(orderno, mgntno, orders_userid, price){
	//alert("orderno:"+orderno);alert("mgntno:"+mgntno);
	if(parseInt(price) <= 0) {
		alert('해당 주문건에 대한 환불가능금액은 없습니다.');
		return;
	}

	window.open('<c:url value="/productOrder/pop_refund.pop"/>?orderno=' + orderno + '&mgntno=' + mgntno  + '&orders_userid=' + orders_userid
			 + '&orderstatuscode=' + $("#orderstatuscode").val() + '&search_date_type=' + escape(encodeURIComponent(search_date_type))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val() + '&paycode=' + $("#paycode").val()
			 + '&payall=' + $("#payall").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val() + '&MANAGER_ID=' +$("#manager_id").val(), '_blank', 'location=no,resizable=no,width=800,height=500,top=0,left=0,scrollbars=yes');
}

//택배비
function deliver_refund(orderno){
	window.open('<c:url value="/productOrder/deliver_refund.pop"/>?orderno=' + orderno
			 + '&orderstatuscode=' + $("#orderstatuscode").val() + '&search_date_type=' + escape(encodeURIComponent(search_date_type))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val() + '&paycode=' + $("#paycode").val()
			 + '&payall=' + $("#payall").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=800,height=250,top=0,left=0,scrollbars=yes');
}

//입금상태저장
function saveDlvStatus() {
	$('#searchFrm').attr('action','<c:url value="/productOrder/staus_update.do"/>').submit();
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);

	if($("#sdate").val()!='' || $("#edate").val()!=''){
		if($("#sdate").val()!='' && $("#edate").val()!=''){
			if(parseInt($("#edate").val().replace(/-/g,'')) < parseInt($("#sdate").val().replace(/-/g,''))){
				alert('검색일자 종료일은 시작일보다 큰 날짜를 선택하세요.');
				return;
			}
		}
	}

	if(payall == '3'){
		$('#searchFrm').attr('action','<c:url value="/productOrder/list_0.do"/>').submit();
	}else{
		if(searchFrm.payall.checked == true){
			$('#payall').val("1");
		}else{
			$('#payall').val("0");
		}


		$('#searchFrm').attr('action','<c:url value="/productOrder/list.do"/>').submit();
	}
}

//카드변경
function card_modify(orderno_val)
{
	if (confirm("카드로 변경 하시겠습니까?"))
	{
		$("#orderno_val").val(orderno_val);
		$('#searchFrm').attr('action','<c:url value="/productOrder/pay_kind_update.do"/>').submit();
	}
}

//엑셀
function excel_onclick1() { //ck 추가
	if(payall != '3'){
		if(searchFrm.payall.checked == true){
			$('#payall').val("1");
		}else{
			$('#payall').val("0");
		}
	}

	//alert($('#payall').val());
	$('#searchFrm').attr('action','<c:url value="/productOrder/tbl_order_list_excel.do"/>').submit();
}

// CJ현대택배 (대한통운) //
function fn_deliveryTrace(invoicenum)
{
    var vLoc = "http://www.doortodoor.co.kr/jsp/cmn/Tracking.jsp?QueryType=3&pTdNo="+invoicenum;
    mywin = window.open(vLoc, "TRACE","toolbar=no,location=no,scrollbars=yes,resizeable=no,width=780,height=500");
}

// 한진택배 //
function KeOrderTrace(invoicenum)
{
     vLoc = " http://www.hanjinexpress.hanjin.net/customer/plsql/hddcw07.result?wbl_num="+invoicenum;
     mywin = window.open(vLoc, "TRACE","toolbar=no,location=no,scrollbars=yes,resizeable=no,width=520,height=530");
}

// 한진택배 //
function KeOrderTrace1(invoicenum)
{
	vLoc = "http://www.hanjinexpress.hanjin.net/customer/plsql/hddcw07.result?wbl_num="+invoicenum;
	mywin = window.open(vLoc, "TRACE","toolbar=no,location=no,scrollbars=yes,resizeable=no,width=520,height=530");
}

// 택배정보 없을시
function delivery_onclick()
{
	alert("올바른 운송번호가 아닙니다.");
	return;
}

// 주문자 클릭시
function view(userid){

    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}

//SMS보내기
function smssend_pop() {

	var tmp = "";
	var userphone = "";
	$("input[name=smsname]").each(function(index){
		if ($(this).is(':checked')) {
			if(tmp == null || tmp == ""){
				tmp = $(this).val();
				userphone = $("#userphone"+index).val();
			}else{
				tmp = tmp + "/" + $(this).val();
				userphone = userphone + "/" + $("#userphone"+index).val();
			}
		}
	});

	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone);
	/* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone); */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=' + escape(encodeURIComponent(tmp)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&search_date_type=' + escape(encodeURIComponent(search_date_type))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val() + '&paycode=' + $("#paycode").val()
			 + '&payall=' + $("#payall").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchkey').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("sdate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("edate");
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

function coop_lec_cancel(orderid, orderno){
	
	var checkparam = "ORDERNO=" + orderno + "&ORDERID=" + orderid;

	$.ajax({
	  type: "GET", 
	  url : '<c:url value="/productOrder/CoopLecture_Cancel.do"/>?' + checkparam,
	  dataType: "text",  
	  async : false,
	  success: function(RES) {
	   if($.trim(RES)=="error:3"){  
			alert("이미 환불된 주문번호 입니다.");
			return;
	   }else if($.trim(RES)=="error:5"){ 
		   alert("등록된 주문번호가 없습니다.");
		   return;
	   }else if($.trim(RES)=="error:6"){	   
	    	alert("환불 정보가 부족 합니다.");
	    	return;
	   }else if($.trim(RES)=="error:8"){
		   alert("프로세스 구분 오류 입니다.");
	       return;
	   }else if($.trim(RES)=="error:9"){
		   alert("업체 구분 오류 입니다.");
	       return;
	   }else{
		   alert("정상적으로 환불처리 되었습니다.");
		   return;
	   } 
	  },error: function(){
	   alert("환불 실패");
	   return;
	  }
	 });

}

//누락주문 등록
function missorder_pop(){
 	window.open('<c:url value="/productOrder/MissOrder.pop"/>', '_order', 'location=no,resizable=no,width=600,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
}
</script>
</head>

<!--content -->
  <div id="content">
	<h2>● 상품주문관리 > <strong>전체상품주문<c:if test="${searchMap.payall eq '3' }">(0원)</c:if></strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post">
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

	<input type="hidden" id="sortfield" name="sortfield" value="${searchMap.sortfield}" />
	<input type="hidden" id="sort_op" name="sort_op" value="${searchMap.sort_op}" />

	<input type="hidden" id="page" name="page" value="${searchMap.page}" />
	<input type="hidden" id="position" name="position" value="${searchMap.position}" />
	<input type="hidden" id="orderno_val" name="orderno_val" />

	<table class="table01">
        <tr>
          <th width="5%">검색</th>
          <td>

          <select id="orderstatuscode" name="orderstatuscode" onchange="goList(1)" style="width:120;">
				<option value="">상태코드전체</option>
			<c:forEach items="${order_list}"  var="order_list">
				<option value="${order_list.CODE_VAL }" <c:if test="${searchMap.orderstatuscode == order_list.CODE_VAL}">selected</c:if>>${order_list.CODE_NM }</option>
			</c:forEach>
		  </select>

		  <select id="paycode" name="paycode" onchange="goList(1)" style="width:120;">
				<option value="">결제방법전체</option>
			<c:forEach items="${payment_list}"  var="payment_list">
				<option value="${payment_list.CODE_VAL }" <c:if test="${searchMap.paycode == payment_list.CODE_VAL}">selected</c:if>>${payment_list.CODE_NM }</option>
			</c:forEach>
		  </select>

        &nbsp;
              <input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${searchMap.sdate }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${searchMap.edate }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>

              <input type="radio" name="search_date_type" id="search_date_type1" value="등록일"  <c:if test="${empty searchMap.search_date_type or searchMap.search_date_type == '등록일' }">checked="checked"</c:if>>등록일
              <input type="radio" name="search_date_type" id="search_date_type2" value="배송일" <c:if test="${searchMap.search_date_type == '배송일' }">checked="checked"</c:if>>배송일

              <c:if test="${searchMap.payall ne '3' }">
              <input type="checkbox" name="payall" id="payall" value="${searchMap.payall}" <c:if test="${searchMap.payall == '1' }">checked="checked"</c:if>>(0원포함)
              </c:if>

              <c:if test="${searchMap.payall eq '3' }">
              <input type="hidden" id="payall" name="payall" value="${searchMap.payall}">
              </c:if>
              &nbsp;&nbsp;
              <input type="checkbox" name="ucancel" id="ucancel" value="Y" <c:if test="${searchMap.ucancel == 'Y' }">checked="checked"</c:if> onchange="goList(1)">(사용자당일취소)
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <!-- <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">일자검색</button></span> -->

              <select name="searchtype" id="searchtype">
				<option value="all" <c:if test="${searchMap.searchtype == 'all'}">selected</c:if>>전체검색</option>
				<option value="a.orderNo" <c:if test="${searchMap.searchtype == 'a.orderNo'}">selected</c:if>>주문번호</option>
				<option value="a.USER_ID" <c:if test="${searchMap.searchtype == 'a.USER_ID'}">selected</c:if>>주문자아이디</option>
				<option value="a.USER_NM" <c:if test="${searchMap.searchtype == 'a.USER_NM'}">selected</c:if>>주문자이름</option>
				<!-- <option value="b.username" <c:if test="${searchMap.searchtype == 'b.username'}">selected</c:if>>수령인이름</option> -->
				<option value="c.payName" <c:if test="${searchMap.searchtype == 'c.payName'}">selected</c:if>>입금자이름</option>
				<option value="e.subject_title" <c:if test="${searchMap.searchtype == 'e.subject_title'}">selected</c:if>>강의명</option>
				<option value="f.book_nm" <c:if test="${searchMap.searchtype == 'f.book_nm'}">selected</c:if>>도서명</option>
			</select>

		<input type="text" class="i_text"  title="검색" id="searchkey" name="searchkey"  type="text" style="width:80px;"  value="${searchMap.searchkey}" onkeypress="fn_checkEnter()">

              <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
        </td>
        </tr>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
   	    <li><a href="javascript:missorder_pop();">주문등록</a></li>
   	    <li><a href="javascript:smssend_pop();">SMS보내기</a></li>
        <li><a href="javascript:saveDlvStatus();">입금상태저장</a></li>
        <li><a href="javascript:excel_onclick1();">엑셀다운로드</a></li>
    </ul>
    <!--//버튼-->

	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>

     <!--테이블-->

    <table class="table02" style="width:100%;">
        <tr>
          <th style="width:8%;">주문번호</th>
          <th style="width:6%;">주문자</th>
          <th style="width:5%;">입금자</th>
          <th style="width:7%;">결제방법</th>
          <th style="width:6%;">등록일</th>

          <th colspan="6" style="width:68%;">
          	<table class="table05" style="border-style:hidden;width:100%;">
				<tr>
					<th >상품명</th>
					<th style="width:94px;">구입금액</th>
					<th style="width:105px;">상태</th>
					<th style="width:40px;">선택</th>
					<th style="width:84px;">입금일</th>
					<th style="width:86px;">입금확인</th>
				</tr>
			</table>
		  </th>
        </tr>

	    <tbody>
	      	  <c:set var="total_count" value="0"/>
		      <c:set var="orderno" value=""/>

              <c:if test="${not empty list}">
         	  <c:forEach items="${list}" var="list" varStatus="status">
	          <tr>
	            <td style="width:8%;">
	               <input type="checkbox" name="smsname" id="smsname" value="${list.ORDERS_USERNAME}">${totalCount - (( currentPage - 1) * pageRow) - status.index}<br>
					<p>
						<a href="javascript:go_view('${list.ORDERNO}')">
                            <font color="blue">${list.ORDERNO}</font>
						</a>
                            <br>${list.BANNER_NAME}&nbsp;[&nbsp;${fn:substring(list.TID,0,1)}&nbsp;]
					</p>
					<c:if test="${list.OPEN_ADMIN_ID != '' && list.OPEN_ADMIN_ID != null}">
					<p>
						<b>${list.OPEN_ADMIN_NM}</b>(${list.OPEN_ADMIN_ID})
					</p>
					</c:if>
				 </td>

				 <td style="width:6%;">
				   <p>
				       <a href="#" onclick="javascript:view('${list.ORDERS_USERID}')">
				           ${list.ORDERS_USERNAME}<br>(${list.ORDERS_USERID})
				            <input type="hidden" name="userphone${status.index}" id="userphone${status.index}" value="${fn:replace(list.ORDERS_CELLNO,'-', '')}">
				       </a>
				   </p>
				 </td>

				<td style="width:5%;">
					<p>

					<c:choose>
						<c:when test="${empty list.DELIVERS_SENDNO or list.DELIVERS_SENDNO == '9999999999' }">
							<a href="javascript:delivery_onclick();">${list.APPROVALS_PAYNAME}</a>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty list.SENDDATE}">
								<c:set var="senddate1" value="${fn:replace(list.SENDDATE,' ', '')}"/>
								<c:set var="senddate2" value="${fn:replace(senddate1,'-', '')}"/>
								<c:set var="senddate3" value="${fn:substring(senddate2, 2, 10)}"/>
                                <c:choose>
                                    <c:when test="${senddate3 < '15070100' }">
                                    <a href="javascript:fn_deliveryTrace('${list.DELIVERS_SENDNO}');">${list.APPROVALS_PAYNAME}</a>
                                    </c:when>
                                    <c:when test="${'15070100' <= senddate3 and senddate3 < '16120100' }">
                                    <a href="javascript:KeOrderTrace('${list.DELIVERS_SENDNO}');">${list.APPROVALS_PAYNAME}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:fn_deliveryTrace('${list.DELIVERS_SENDNO}');">${list.APPROVALS_PAYNAME}</a>
                                    </c:otherwise>
                                </c:choose>
							</c:if>
						</c:otherwise>
					</c:choose>

					</p>
				</td>

				<td style="width:7%;"><p>

				<c:choose>
					<c:when test="${list.APPROVALS_PAYCODENAME eq 'PAY100' }">
						무통장입금
					</c:when>

					<c:when test="${list.APPROVALS_PAYCODENAME eq 'PAY110' }">
						카드결제
					</c:when>

					<c:when test="${list.APPROVALS_PAYCODENAME eq 'PAY120' }">
						가상계좌
					</c:when>

					<c:when test="${list.APPROVALS_PAYCODENAME eq 'PAY130' }">
						계좌이체
					</c:when>

					<c:otherwise>
						없음
					</c:otherwise>
				</c:choose>

				<!-- 
				<br>
				<a href="javascript:card_modify('${list.ORDERNO}');">카드변경</a>
				<br>
				 -->
				<c:if test="${list.APPROVALS_PAYCODE eq 'PAY100' }">
					<%--<a href="javascript:openCenter('bank_modify.asp?orderNo=<%=bean.getOrderno()%>&payCode=<%=bean.getApprovals_paycode()%>&AcctNoCode=<%=bean.getApprovals_acctnoname()%>', '입금은행변경', 'scrollbars=yes', '860', '180');">[은행변경]</a>--%>
				</c:if>

				</p>
				</td>

				<td style="width:6%;">${list.ORDERS_REGDATE}</td>

				<td colspan="6" style="width:68%;">

				<!-- 루프도는곳 -->

				<c:choose>
					<c:when test="${list.APPROVALS_REPRICE eq null }">
						<c:set var="rePriceNullChk" value="null"/>
						<c:set var="rePrice" value="0"/>
					</c:when>

					<c:otherwise>
						<c:set var="rePriceNullChk" value=""/>
						<c:set var="rePrice" value="${list.APPROVALS_REPRICE }"/>
					</c:otherwise>
				</c:choose>

				<c:set var="tot_sum" value="0"/>
				<c:set var="ori_price" value="0"/>
				<c:set var="orders_mgntNo_count" value="0"/>

				<c:set var="list_second_idx" value="list_second${status.index}"/>

				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">

  					<c:set var="ori_price" value="${list_second.PRICE}"/>
<%-- 2014.03.04
  					<c:if test="${list_second.STATUSCODE eq  'DLV150' or list_second.STATUSCODE eq  'DLV230'}">
  						<c:set var="ori_price" value="0"/>
  					</c:if>
 --%>
  					<c:set var="tot_sum" value="${tot_sum + ori_price}"/>

         			<c:choose>
						<c:when test="${list_second.PRICE ne list_second.REALPRICE and list_second.PRICE ne 0}">
							<c:set var="colorx" value="blue"/>
						</c:when>

						<c:when test="${list_second.WMV_PMP eq 'PMP'}">
							<c:set var="colorx" value="#FF44FB"/>
						</c:when>

						<c:when test="${list_second.WMV_PMP eq 'VOD'}">
							<c:set var="colorx" value="#black"/>
						</c:when>

						<c:when test="${list_second.WMV_PMP eq 'VOD+PMP'}">
							<c:set var="colorx" value="#993300"/>
						</c:when>
					</c:choose>

  					<c:if test="${list_second.ISCANCEL eq  '2'}">
  						<c:set var="colorx" value="red"/>
  					</c:if>


					<table style="width:100%">
						<tr>
							<td style="text-align:left;">
								<c:if test="${list_second.GIFT_NAME eq '사은품'}">
							   		(${list_second.GIFT_NAME})
							   </c:if>
								<c:if test="${fn:substring(list_second.MGNTNO, 0, 1) ne  'L' && list_second.ISCANCEL ne '2'}"> <!-- 교제가 아닌것만 링크 걸리게 수정  -->
							    	<a href="javascript:plyer_gostop('${list.ORDERS_USERID}','${list.ORDERNO}','${list_second.MGNTNO}');" style="color:${colorx}; font-weight:bold; text-decoration:none;">
								    	${status2.index + 1}. ${list_second.NAME}
								    </a>
								    <c:if test="${!empty list_second.COOP_CD }">
								    	&nbsp;
								    	<a href="javascript:coop_lec_cancel('${list.ORDERS_USERID}','${list.ORDERNO}');" style="color:red; font-weight:bold; text-decoration:none;">
								    		[제휴강의취소]
								    	</a>
								    </c:if>
								    
							   </c:if>
								<c:if test="${fn:substring(list_second.MGNTNO, 0, 1) eq  'L' || list_second.ISCANCEL eq  '2'}"> <!-- 교제이면 교제이름만 표시한다.  -->
								    <span style="color:${colorx};">${status2.index + 1}. ${list_second.NAME}</span>
							   </c:if>
							   
							 </td>

							<td style="width:94px;">
								<c:choose>
									<c:when test="${list_second.STATUSCODE eq 'DLV100' }">
									<fmt:formatNumber value="${list_second.PRICE}" groupingUsed="true" />
									</c:when>
									<c:when test="${list_second.STATUSCODE eq 'DLV150' }">
									<fmt:formatNumber value="${list_second.PRICE}" groupingUsed="true" />
									</c:when>
									<c:when test="${list_second.STATUSCODE eq 'DLV230' }">
									<fmt:formatNumber value="${list_second.PRICE}" groupingUsed="true" />
									</c:when>
									<c:otherwise>
									<a href="javascript:pop_refund('${list.ORDERNO}','${list_second.MGNTNO}', '${list.ORDERS_USERID}', '${list_second.PRICE}');" style="text-decoration:none;">
										<fmt:formatNumber value="${list_second.PRICE}" groupingUsed="true" />
									</a>
									</c:otherwise>
								</c:choose>
							</td>

							<td style="width:104px;">

							<input type="hidden" name="mgntno" id="mgntno" value="${list_second.MGNTNO}">
							<input type="hidden" name="mgntno_change" id="mgntno_change" value="">
							<input type="hidden" name="orderno" id="orderno" value="${list.ORDERNO}">
							<input type="hidden" name="point" id="point" value="${list.APPROVALS_POINT}">
							<input type="hidden" name="delivers_cellno" id="delivers_cellno" value="${list.DELIVERS_CELLNO}">
							<input type="hidden" name="orders_mgntNo_count" id="orders_mgntNo_count" value="${orders_mgntNo_count}">
							<input type="hidden" name="userid" id="userid" value="${list.ORDERS_USERID}">
							<input type="hidden" name="statuscode_old" id="statuscode_old" value="${list_second.STATUSCODE}">
							<input type="hidden" name="manager_id" id="manager_id" value="${menuParams.MANAGER_ID}">

								<p>
									<input type="hidden" name="orderNoa" id="orderNoa" value="${list.ORDERNO}">

									<c:choose>
									<c:when test="${list_second.ISCANCEL ne '2'}">
										<select id="statuscode" name="statuscode" class="in_basic" onchange="set_date('${total_count}', this.options[this.selectedIndex].value,'${totalCount}','${fn:length(requestScope[list_second_idx])}')">
											<c:forEach items="${order_list}"  var="order_list">
												<option value="${order_list.CODE_VAL }" <c:if test="${list_second.STATUSCODE == order_list.CODE_VAL}">selected</c:if>>${order_list.CODE_NM }</option>
											</c:forEach>
										  </select>
									</c:when>

									<c:otherwise>
										<input type="hidden" name="statuscode" id="statuscode" >
										<b><font color="red">[환불완료]</font></b>
									</c:otherwise>
								</c:choose>
								</p>

							</td>

							<%--><td width="6%" align="center"  bgcolor=#ffffff class="list">-</td>--%>

							<td style="width:38px;"><input type="checkbox" name="orderck" id="checkbox" value="${list.ORDERNO}#${list_second.MGNTNO}#${list_second.CONFIRMDATE}#${total_count + 1}#${status2.index}"></td>

							<c:set var="confirmdate_old" value="${fn:replace(list_second.CONFIRMDATE,'-', '')}"/>
							<c:set var="confirmdate_old" value="${fn:substring(confirmdate_old, 0, 8)}"/>

							<c:set var="isconfirm_old" value="${fn:replace(list_second.ISCONFIRM,'-', '')}"/>

							<input type="hidden" name="confirmdate_old" id="confirmdate_old" value="${confirmdate_old}">
							<input type="hidden" name="isconfirm_old" id="isconfirm_old" value="${isconfirm_old}">

							<td style="width:84px;">
								<input type="text" name="confirmdate" id="confirmdate" style="width:100%;" class="in_basic" value="${confirmdate_old}" maxlength="8" size="9" onkeyup="set_date('${total_count}', 1,'${totalCount}','${fn:length(requestScope[list_second_idx])}')">
								<input type="hidden" name="confirmdatelast" id="confirmdatelast" style="width:100%;" class="in_basic" value="${fn:replace(fn:substring(list_second.CONFIRMDATE2, 11, 19),':','')}" >
							</td>

							<td style="width:84px;">
								<input type="text" name="isconfirm" id="isconfirm" style="width:100%;" class="in_basic" value="${isconfirm_old}" onkeyup="set_date('${total_count}', 2,'${totalCount}','${fn:length(requestScope[list_second_idx])}')" maxlength="8" size="9">
							</td>
						</tr>
					</table>

					<c:set var="total_count" value="${total_count + 1 }"/>
					<c:set var="orders_mgntNo_count" value="${orders_mgntNo_count + 1}"/>
					<c:set var="orderno" value="${list.ORDERNO}"/>
				</c:forEach>
				</c:if>

				<table style="width:100%">
					<tr>
						<td style="width:100%;text-align:center;colspan:6;">

						<fmt:formatNumber value="${tot_sum - list.APPROVALS_POINT}" groupingUsed="true" />&nbsp; + (택배비 : <a href="javascript:deliver_refund('${orderno}');"><fmt:formatNumber value="${list.APPROVALS_ADDPRICE}" groupingUsed="true" /></a>
						<c:if test="${list.BOOK_SUM > 30000}">, <font style='color:green;font-weight: bold;'>3만원이상 무료배송</font></c:if><c:if test="${list.USE_DEL_COUPON_CNT > 0}">, <font style='color:green;font-weight: bold;'>쿠폰사용</font></c:if>)

						<c:set var="chuga_add" value="0"/>
						<c:if test="${rePriceNullChk ne null}">
							<c:choose>
								<c:when test="${list.DELIVERS_ZIPCD ne null}">

								<c:set var="first_zipcd" value="${fn:substring(list.DELIVERS_ZIPCD, 0, 2)}"/>

									<c:choose>
										<c:when test="${first_zipcd eq '69' and first_zipcd ne null}">
											<font style='color:red;'>, 환불택배비 : ${rePrice}</font> (추가 배송료 : 2,500원)
											<c:set var="chuga_add" value="2500"/>
										</c:when>

										<c:otherwise>
											<font style='color:red;'>, 환불택배비 : ${rePrice}</font>
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:otherwise>
									<font style='color:red;'>, 환불택배비 : ${rePrice}</font>
								</c:otherwise>
							</c:choose>
						</c:if>

						<c:if test="${list.APPROVALS_POINT ne 0}">
							+ POINT : ${list.APPROVALS_POINT}
						</c:if>

						= <b><fmt:formatNumber value="${tot_sum + list.APPROVALS_ADDPRICE + rePrice - list.APPROVALS_POINT + chuga_add}" groupingUsed="true" /></b>
						<c:if test="${list.STATUSCODE == 'DLV150' and list.USER_CANCEL == 'Y'}"><font style="color:red;">(당일취소)</font></c:if>
						</td>
					</tr>
				</table>
				<!-- 루프 도는 곳 -->

			</td>
			</tr>

			</c:forEach>
			</c:if>

			<c:if test="${empty list}">
		        <tr bgColor=#ffffff align=center>
					<td colspan="11">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
       </tbody>
    </table>
    <!--//테이블-->

	<!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:saveDlvStatus();">입금상태저장</a></li>
    </ul>
    <!--//버튼-->

    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</form>
</div>
</html>