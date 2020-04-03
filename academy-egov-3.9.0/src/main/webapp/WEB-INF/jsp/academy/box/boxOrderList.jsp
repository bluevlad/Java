<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
var message = "${params.message}";
//alert("message:"+message);
var search_date_type = "${params.search_date_type}";

window.onload = function () {
	 if(message != "") {
		alert(message);
	}
}


//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	if($("#SDATE").val()!='' || $("#EDATE").val()!=''){
		if($("#SDATE").val()!='' && $("#EDATE").val()!=''){
			if(parseInt($("#EDATE").val().replace(/-/g,'')) < parseInt($("#SDATE").val().replace(/-/g,''))){
				alert('검색일자 종료일은 시작일보다 큰 날짜를 선택하세요.');
				return;
			}			
		}		
	}
	$('#searchFrm').attr('action','<c:url value="/box/boxOrderList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가 	
	$('#searchFrm').attr('action','<c:url value="/box/boxOrderList_excel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHKEY').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

function fn_view(orderNo, status_cd){
	$("#ORDERNO").val($.trim(orderNo));
	$("#STATUSCODE").val($.trim(status_cd));
	$("#searchFrm").attr("action","<c:url value='/box/boxOrderView.do' />");
	$("#searchFrm").submit();
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("SDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("EDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

</script>

</head>

<body>
<!--content -->
  <div id="content">
	<!-- <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2> -->
	<h2>● 사물함관리 > <strong>사물함신청내역</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
		
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="ORDERLIST">
	
	<table class="table01">
        <tr>
          <th width="15%">검색</th>
          <td>
          
          <input type="text" id="SDATE" name="SDATE" maxlength="10" class="i_text" value="${params.SDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="EDATE" name="EDATE" maxlength="10" class="i_text" value="${params.EDATE }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           
           &nbsp;
           
          <select name="RENTTYPE" id="RENTTYPE" onchange="goList(1)" style="width:120;">
			<option value="">--신청구분--</option>
			<option value="OFF" <c:if test="${params.RENTTYPE == 'OFF'}">selected</c:if>>학원방문</option>
			<option value="ON" <c:if test="${params.RENTTYPE == 'ON'}">selected</c:if>>온라인신청</option>
			<option value="DESK" <c:if test="${params.RENTTYPE == 'DESK'}">selected</c:if>>데스크접수</option>
		  </select>
		  
		  <select id="ORDERSTATUSCODE" name="ORDERSTATUSCODE" onchange="goList(1)" style="width:120;">
			<option value="">--결제상태--</option>
			<option value="DLV105" <c:if test="${params.ORDERSTATUSCODE == 'DLV105'}">selected</c:if>>결제완료</option>
			<option value="DLV230" <c:if test="${params.ORDERSTATUSCODE == 'DLV230'}">selected</c:if>>환불완료</option>
		  </select>             
              
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           
           <select name="SEARCHTYPE" id="SEARCHTYPE">
			<option value="ALL" <c:if test="${params.SEARCHTYPE == 'ALL'}">selected</c:if>>전체검색</option>
			<option value="TOO.ORDERNO" <c:if test="${params.SEARCHTYPE == 'TOO.ORDERNO'}">selected</c:if>>주문번호</option>
			<option value="TOO.USER_NM" <c:if test="${params.SEARCHTYPE == 'TOO.USER_NM'}">selected</c:if>>성명</option>
		  </select>
		
		<input type="text" class="i_text"  title="검색" id="SEARCHKEY" name="SEARCHKEY"  type="text" style="width:80px;"  value="${params.SEARCHKEY}" onkeypress="fn_checkEnter()">
        <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
        </td>
        </tr>
    </table>
      
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:excel_onclick1();">Excel</a></li>
    </ul>
    <!--//버튼-->
	</form>
	       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
          	<th>주문번호</th>
			<th>신청구분</th>
			<th>신청일시</th>
			<th>성명(핸드폰)</th>
			<th>품목(사물함 번호)</th>
			<th>총 결제금액</th>
			<th>결제상태</th>
			<th>결제종류</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td>${list.ORDERNO}</td>
				<td>${list.RENT_TYPE_NM}</td>
				<td>${list.REG_DT}</td>
				<td><a href="javascript:fn_view('${list.ORDERNO}')">${list.USER_NM}(${list.PHONE_NO})</a></td>
				<td style="text-align:left;"><a href="javascript:fn_view('${list.ORDERNO}','${list.STATUSCODE}')">${list.BOX_NM} ${list.BOX_NUM}번 사물함 주문</a></td>				
				<td>${list.PRICE}</td>
				<td>${list.STATUSCODE_NM}</td>
				<td>${list.PAYCODE_NM}</td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="9">표시할 데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
     <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</div>
</body> 
</html>