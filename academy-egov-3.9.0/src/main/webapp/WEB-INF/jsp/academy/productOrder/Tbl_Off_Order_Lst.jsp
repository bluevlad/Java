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

window.onload = function () {
	 if(message != "") {
		alert(message);
	}
}

//성명,강의명 클릭
function view(ORDERNO,USER_ID,STATUSCODE,MGNTNO, ORDER_TYPE) {
	
	$("#ORDERNO").val(ORDERNO);
	$("#USER_ID").val(USER_ID);
	$("#STATUSCODE").val(STATUSCODE);
	$("#MGNTNO").val(MGNTNO);
	$("#ORDER_TYPE").val(ORDER_TYPE);
	if(STATUSCODE == "DLV230") {
		$('#searchFrm').attr('action','<c:url value="/productOrder/updateRefundPage2.do"/>').submit();
	}else{
		$('#searchFrm').attr('action','<c:url value="/productOrder/updatePage.do"/>').submit();
	}
	
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
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/off_list.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가 	
	$('#searchFrm').attr('action','<c:url value="/productOrder/tbl_off_order_list_excel.do"/>').submit();
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

//목록
function goList2() {
	$("#sdate").val("");
	$("#edate").val("");
	$("#orderstatuscode").val("");
	$("#searchkey").val("");
	$("#paycode").val("");
	$("#searchtype").val("");
	$("#currentPage").val("");
	$("#pageRow").val("");
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/off_list.do"/>').submit();
}

//수강증출력
function fn_Print(ORDERNO, MGNTNO){
	window.open('<c:url value="/productOrder/offererPrint.pop"/>?PRINT_STS=1&ORDERNO=' + ORDERNO + '&MGNTNO=' + MGNTNO
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,toolbar=no,resizable=yes,width=620,height=330,top=0,left=0,location=no,scrollbars=no');
}

//수강증출력
function fn_Print_New(ORDERNO, MGNTNO){
	window.open('<c:url value="/productOrder/offererPrint.pop"/>?PRINT_STS=3&ORDERNO=' + ORDERNO + '&MGNTNO=' + MGNTNO
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&searchtype=' + $("#searchtype").val()
			 + '&paycode=' + $("#paycode").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,toolbar=no,resizable=yes,width=620,height=330,top=0,left=0,location=no,scrollbars=no');
}

//등록
function add() {
	$('#searchFrm').attr('action','<c:url value="/productOrder/createPage.do"/>').submit();
}

//주문자 클릭시
function user_view(userid){
	if(userid=="" || userid ==null){
	     alert("비회원입니다.");
	     return;
	}else{
	 	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}
}
</script>
</head>

<!--content -->
  <div id="content">
	<h2>● 수강신청관리 > <strong>수강신청</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
		
	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />
	<input type="hidden" id="MGNTNO" name="MGNTNO" />
	<input type="hidden" id="ORDER_TYPE" name="ORDER_TYPE" />
	
	<table class="table01">
        <tr>
          <th width="15%">검색</th>
          <td>
          
          <input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${searchMap.sdate }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${searchMap.edate }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           
           &nbsp;
           
          <select name="ordertype" id="ordertype" onchange="goList(1)" style="width:120;">
			<option value="">--수강신청구분--</option>
			<option value="OFF" <c:if test="${searchMap.ordertype == 'OFF'}">selected</c:if>>학원방문</option>
			<option value="ON" <c:if test="${searchMap.ordertype == 'ON'}">selected</c:if>>온라인신청</option>
			<option value="DESK" <c:if test="${searchMap.ordertype == 'DESK'}">selected</c:if>>데스크접수</option>
		  </select>
		  
		  <select id="orderstatuscode" name="orderstatuscode" onchange="goList(1)" style="width:120;">
			<option value="">--결제상태--</option>
			<option value="DLV105" <c:if test="${searchMap.orderstatuscode == 'DLV105'}">selected</c:if>>결제완료</option>
			<option value="DLV230" <c:if test="${searchMap.orderstatuscode == 'DLV230'}">selected</c:if>>환불완료</option>
			<option value="DLV250" <c:if test="${searchMap.orderstatuscode == 'DLV250'}">selected</c:if>>대기중</option>
		  </select>             
 		  
		  <select id="orderpaycode" name="orderpaycode" onchange="goList(1)" style="width:120;">
			<option value="">--결제종류--</option>
			<option value="PAY110" <c:if test="${searchMap.orderpaycode == 'PAY110'}">selected</c:if>>카드</option>
			<option value="PAY120" <c:if test="${searchMap.orderpaycode == 'PAY120'}">selected</c:if>>가상계좌</option>
			<option value="PAY130" <c:if test="${searchMap.orderpaycode == 'PAY130'}">selected</c:if>>실시간계좌이체</option>
			<option value="PAY140" <c:if test="${searchMap.orderpaycode == 'PAY140'}">selected</c:if>>현금</option>
			<option value="PAY160" <c:if test="${searchMap.orderpaycode == 'PAY160'}">selected</c:if>>전액할인</option>
		  </select>             
              
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
              <select name="searchtype" id="searchtype">
				<option value="all" <c:if test="${searchMap.searchtype == 'all'}">selected</c:if>>전체검색</option>
				<option value="A.ORDERNO" <c:if test="${searchMap.searchtype == 'A.ORDERNO'}">selected</c:if>>주문번호</option>
				<option value="A.USER_NM" <c:if test="${searchMap.searchtype == 'A.USER_NM'}">selected</c:if>>성명</option>
				<option value="A.USER_ID" <c:if test="${searchMap.searchtype == 'A.USER_ID'}">selected</c:if>>아이디</option>
				<option value="A.PHONE_NO" <c:if test="${searchMap.searchtype == 'A.PHONE_NO'}">selected</c:if>>핸드폰번호</option>
				<option value="D.SUBJECT_TITLE" <c:if test="${searchMap.searchtype == 'D.SUBJECT_TITLE'}">selected</c:if>>강의명</option>
			  </select>
		
		<input type="text" class="i_text"  title="검색" id="searchkey" name="searchkey"  type="text" style="width:80px;"  value="${searchMap.searchkey}" onkeypress="fn_checkEnter()">
		
              <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
        </td>
        </tr>
    </table>
      
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:add();">등록</a></li>
        <li><a href="javascript:excel_onclick1();">Excel</a></li>
    </ul>
    <!--//버튼-->
       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
          	<th>주문번호</th>
			<th>신청구분</th>
			<th>신청일시</th>
			<th>승인일시</th>
			<th>성명(전화)</th>
			<th>강의명</th>
			<th>강의수</th>
			<th>총 결제금액</th>
			<th>결제상태</th>
			<th>결제종류</th>
			<th>수강증출력</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td><a href="javascript:view('${list.ORDERNO}','${list.USER_ID}','${list.STATUSCODE}','${list.MGNTNO}','${ORDER_TYPE}')">${list.ORDERNO}</a></td>
				<td>${list.ORDER_TYPE_NM}</td>
				<td>${list.REG_DT}</td>
				<td>${list.ISCONFIRM}</td>
				<td><a href="javascript:user_view('${list.USER_ID}')">${list.USER_NM}(${list.PHONE_NO})</a></td>
				<td style="text-align:left;"><a href="javascript:view('${list.ORDERNO}','${list.USER_ID}','${list.STATUSCODE}','${list.MGNTNO}','${ORDER_TYPE}')">${list.SUBJECT_TITLE}</a></td>				
				<td>${list.SUBJECT_CNT}</td>				
				<td><fmt:formatNumber value="${list.PRICE }" groupingUsed="true"/></td>
				<td>${list.STATUSCODE_NM}</td>
				<td>${list.PRICE_STS}</td>
				
				<c:choose>
					<c:when test="${list.TICKET_PRINT_DT ne null}">
						<c:set var="colorx" value="red"/>
					</c:when>
					
					<c:otherwise>
						<c:set var="colorx" value="#black"/>
					</c:otherwise>
				</c:choose>
				<td>
					<c:if test="${list.STATUSCODE eq 'DLV105'}">
						<c:choose>
							<c:when test="${list.MGNTNO eq 'N'}">
								<input type="button" onclick='javascript:fn_Print_New("${list.ORDERNO}","${list.LECCODE}");' value="종합반-출력" style="color:${colorx};" />
							</c:when>
							<c:otherwise>
								<c:if test="${list.SUBJECT_CNT == 1}">
								<input type="button" onclick='javascript:fn_Print_New("${list.ORDERNO}","${list.LECCODE}");' value="단과반-출력" style="color:${colorx};" />
								</c:if>
								<c:if test="${list.SUBJECT_CNT > 1}">
								<input type="button" onclick="javascript:view('${list.ORDERNO}','${list.USER_ID}','${list.STATUSCODE}','${list.MGNTNO}','${ORDER_TYPE}');" value="상세내역확인" style="color:${colorx};" />
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:if> 
				</td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		<tr bgColor=#ffffff align=center> 
			<td colspan="11">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
     <!--//테이블-->
	
	<!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:add();">등록</a></li>
        <li><a href="javascript:goList2();">목록</a></li>
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