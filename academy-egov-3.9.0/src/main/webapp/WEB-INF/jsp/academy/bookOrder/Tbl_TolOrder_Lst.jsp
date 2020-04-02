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
//var search_date_type = "${searchMap.search_date_type}";
//var payall = "${searchMap.payall}";
//alert("payall:"+payall);

//var MOCKCODE = "${searchMap.MOCKCODE}";
//alert("MOCKCODE:"+MOCKCODE);

var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var searchtype = "${searchMap.searchtype}";
var searchkey = "${searchMap.searchkey}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	 if(message != "") {
		alert(message);
	}
	 
	 /* alert(	"sdate:"+sdate +"\n"+
			 "edate:"+edate +"\n"+
			 "searchtype:"+searchtype +"\n"+
			 "searchkey:"+searchkey +"\n"+
				"currentPage:"+currentPage +"\n"+
				"pageRow:"+pageRow +"\n"+
				"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
				"MENUTYPE:"+MENUTYPE +"\n"+
				"L_MENU_NM:"+L_MENU_NM); */
	 
	/* alert("S_EXAMYEAR:"+S_EXAMYEAR +"\n"+
	"S_EXAMROUND:"+S_EXAMROUND +"\n"+
	"S_searchFlag:"+S_searchFlag +"\n"+
	"S_searchKeyWord:"+S_searchKeyWord +"\n"+
	"S_currentPage:"+S_currentPage +"\n"+
	"S_pageRow:"+S_pageRow); */
}

//주문번호 클릭
function go_view(orderno) {
	//alert("orderno:"+orderno);
	
	$("#orderno").val(orderno);
	$("#sts").val("tol");
	
	$('#searchFrm').attr('action','<c:url value="/bookOrder/update.do"/>').submit();
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
	
	$('#searchFrm').attr('action','<c:url value="/bookOrder/tol_list.do"/>').submit();
}

//P버튼
function print1(printval, printType, printnocode)
{
	
	window.open('<c:url value="/bookOrder/pop_print.pop"/>?orderno=' + printval + '&printtype=' + printType + '&printnocode=' + printnocode
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val()
			 + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', "location=no,resizable=no,width=820,height=600,top=0,left=0,scrollbars=yes");
			
}

//체크항목 프린트
function chkvalPrint()
{	
	var tmp = "";	
	
	var str = "";
	var arr = "";
	$("input[name=smsname]:checked").each(function (index){
		if(tmp == null || tmp == ""){
			str = $(this).val();
			arr = str.split(",");
			tmp = arr[1];
		}else{
			str = $(this).val();
			arr = str.split(",");
			tmp = tmp + "/" + arr[1];
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("프린터할 항목을 선택해주세요.");
		return;
	}
	
	//alert("tmp:"+tmp);
	
	window.open('<c:url value="/bookOrder/pop_all_print.pop"/>?orderno=' + tmp + '&printtype=deliver'
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val()
			 + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=820,height=600,top=0,left=0,scrollbars=yes');
	
}

//엑셀
function excel_onclick1() {
	$('#searchFrm').attr('action','<c:url value="/bookOrder/tolorder_list_excel.do"/>').submit();
}

// 주문자 클릭시
function view(userid){
	if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=820,height=630,top=0,left=0,scrollbars=yes');
    }
}
			
//SMS보내기 
function smssend_pop() {
	
	var tmp = "";	
	var userphone = "";
	
	var str = "";
	var arr = "";
	$("input[name=smsname]:checked").each(function (index){
		if(tmp == null || tmp == ""){
			str = $(this).val();
			arr = str.split(",");
			tmp = arr[0];
			userphone = $("#userphone"+index).val();
		}else{
			str = $(this).val();
			arr = str.split(",");
			tmp = tmp + "/" + arr[0];
			userphone = userphone + "/" + $("#userphone"+index).val();
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	 /* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone);  */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=' + escape(encodeURIComponent(tmp))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val()
			 + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=820,height=400,top=0,left=0,scrollbars=no');
}

//쪽지
function fn_message()
{    
	var tmp = "";	
	var tmp_nm="";
	
	var str = "";
	var arr = "";
	$("input[name=smsname]:checked").each(function (index){
		if(tmp == null || tmp == ""){
			str = $(this).val();
			arr = str.split(",");
			tmp = arr[2];
			tmp_nm = arr[3];
		}else{
			str = $(this).val();
			arr = str.split(",");
			tmp = tmp + "," + arr[2];
			tmp_nm = tmp_nm + "," + arr[3];
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	
	/* alert("tmp:"+tmp +"\n"+
			"tmp_nm:"+tmp_nm); */
	
	 if(confirm("쪽지를 보내시겠습니까?")) {		
		$("#MESSAGEID").val(tmp);
		$("#MESSAGENM").val(tmp_nm);
		window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=820,height=210 ');
		$('#searchFrm').attr('target' ,'message');
		$('#searchFrm').attr('action','<c:url value="/memberManagement/memberCheckMessage.pop"/>').submit();
	}
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
	initDateFicker1("sdate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("edate");
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
</script>
</head>

<!--content -->
  <div id="content">
	<h2>● 교재배송관리 > <strong>교재매출내역</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" name="orderno" id="orderno">
	
	<input type="hidden" name="sts" id="sts">
	
	<input type="hidden" id="MESSAGEID" name="MESSAGEID">
	<input type="hidden" id="MESSAGENM" name="MESSAGENM">
	
	
	<table class="table01">
        <tr>
          <th width="15%">검색</th>
          <td>
              <input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${searchMap.sdate }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${searchMap.edate }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>&nbsp;
              
              <!-- <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">일자검색</button></span> -->
              
              <select name="searchtype" id="searchtype">
				<option value="all" <c:if test="${searchMap.searchtype == 'all'}">selected</c:if>>전체검색</option>
				<option value="b.orderNo" <c:if test="${searchMap.searchtype == 'b.orderNo'}">selected</c:if>>주문번호</option>
				<option value="c.USER_ID" <c:if test="${searchMap.searchtype == 'c.USER_ID'}">selected</c:if>>주문자아이디</option>
				<option value="c.USER_NM" <c:if test="${searchMap.searchtype == 'c.USER_NM'}">selected</c:if>>주문자이름</option>
				<option value="b.username" <c:if test="${searchMap.searchtype == 'b.username'}">selected</c:if>>수령인이름</option>
				<option value="d.payName" <c:if test="${searchMap.searchtype == 'd.payName'}">selected</c:if>>입금자이름</option>				
				<option value="tb.book_nm" <c:if test="${searchMap.searchtype == 'tb.book_nm'}">selected</c:if>>상품명</option>
				<option value="tb.rsc_id" <c:if test="${searchMap.searchtype == 'tb.rsc_id'}">selected</c:if>>상품코드</option>												  
			  </select>
		
			  <input type="text" class="i_text"  title="검색" id="searchkey" name="searchkey"  type="text" style="width:80px;"  value="${searchMap.searchkey}" onkeypress="fn_checkEnter()">
		
              <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>
              <B>*전체 검색시 상품명, 상품코드는 제외됨</B>
        </td>
        </tr>
    </table>
      
    <!--버튼-->    
    <ul class="boardBtns">
    	<li><a href="javascript:excel_onclick1();">엑셀다운로드</a></li>
    	<li><a href="javascript:fn_message();">쪽지발송</a></li>
   	    <li><a href="javascript:smssend_pop();">SMS보내기</a></li>
        <li><a href="javascript:chkvalPrint();">체크항목 프린트</a></li>
    </ul>
    <!--//버튼-->
       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
								
    <table class="table02" style="width:100%;">
        <tr>
          <th style="width:5%;"><input id="allCheck" value="" type="checkbox" onclick="checkAll('allCheck', 'smsname')" />No</th>
          <th style="width:10%;">주문번호</th>
          <th style="width:5%;">주문자</th>
          <th style="width:31%;">상품명</th>
          <th style="width:5%;">저자</th>          
          <th style="width:15%;">출판사</th>
          <th style="width:8%;">주문일</th>
          <th style="width:8%;">정가</th>
          <th style="width:8%;">판매가</th>
          <th style="width:5%;">수량</th>
        </tr>
        
	    <tbody>
	      	  <c:set var="total_count" value="0"/>
		  
              <c:if test="${not empty list}">
         	  <c:forEach items="${list}" var="list" varStatus="status">
	          <tr>
	             <td style="width:5%;">
	             	<c:set var="CHECK_VAL">${list.ORDERS_USERNAME},${list.ORDERNO},${list.USERID},${list.USERNAME}</c:set>
	               <input type="checkbox" name="smsname" id="smsname" value="${CHECK_VAL}">${totalCount - (( currentPage - 1) * pageRow) - status.index}
				 </td>
				 
				 <td style="width:10%;">
					<p>
						<a href="javascript:go_view('${list.ORDERNO}')">
							${list.ORDERNO}						
						</a>
						
						<input onclick="print1('${list.ORDERNO}','deliver','${status.index}')" type="button" value="P">
					</p>
				 </td>
				 
				 <td style="width:5%;">
				   <p>
				   		${list.USERNAME}<br/>
					       (${list.USERID})
			            <input type="hidden" name="userphone${status.index}" id="userphone${status.index}" value="${fn:replace(list.ORDERS_CELLNO,'-', '')}">
				   </p>
				 </td>
				
				<td style="width:31%;">
				<!-- 루프도는곳 -->
				
				<c:set var="list_second_idx" value="list_second${status.index}"/>
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			${list_second.NAME}(${list_second.CNT})<br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<td style="width:5%;">
				<!-- 루프도는곳 -->
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			${list_second.AUTHOR}<br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<td style="width:15%;">
				<!-- 루프도는곳 -->
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			${list_second.BOOK_PUBLISHERS}<br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<td style="width:8%;">
					${list.SENDDATE}
				</td>
				
				<td style="width:8%;">
				<!-- 루프도는곳 -->
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			<fmt:formatNumber value="${list_second.PRICE}" groupingUsed="true" /><br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<td style="width:8%;">
				<!-- 루프도는곳 -->
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			<fmt:formatNumber value="${list_second.DISCOUNT_PRICE}" groupingUsed="true" /><br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<td style="width:5%;">
				<!-- 루프도는곳 -->
				
				<c:if test="${not empty list_second0}">
  				<c:forEach items="${requestScope[list_second_idx]}" var="list_second" varStatus="status2">
         			${list_second.CNT}<br>
         		</c:forEach>
				</c:if>
				
				<!-- 루프도는곳 -->
				</td>
				
				<input type="hidden" id="dleorder" name="dleorder" value="">
				<input type="hidden" id="orderno2" name="orderno2" value="${list.ORDERNO}">
			</tr>
			  
			</c:forEach>
			</c:if>
			
			<c:if test="${empty list}">
		        <tr bgColor=#ffffff align=center> 
					<td colspan="10">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
       </tbody>
    </table>
    <!--//테이블-->
		                  
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</form>
</div> 
</html>