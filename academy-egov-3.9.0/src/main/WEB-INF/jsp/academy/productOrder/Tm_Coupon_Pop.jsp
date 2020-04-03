<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

var orderstatuscode = "${searchMap.orderstatuscode}";
var search_date_type = "${searchMap.search_date_type}";
var payall = "${searchMap.payall}";
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

var msg = "${msg}";

window.onload = function () {
	 if(msg != "") {
		alert(msg);
	} 
	
	/* alert("LECCODE:"+LECCODE +"\n"+
			"orderstatuscode:"+orderstatuscode +"\n"+
			"search_date_type:"+search_date_type +"\n"+
			"payall:"+payall +"\n"+
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
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
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

//선택한 쿠폰 사용자에게 발행 버튼
function submitMycouponInsFrm()
{
    var code = document.getElementsByName("ccode");
    var Expday = document.getElementsByName("eday");
    var code1 = "";
    var Expday1 = "";
    for(var i=0; i<code.length; i++){
   	 if(code[i].checked == true){
   		code1= code[i].value;
   		Expday1= Expday[i].value;
   	 }
    }
    
    if(code1 == ""){
   	 	alert("쿠폰을 선택해 주시기 바랍니다.");
        return; 
    
    }else{    	
    	$("#code1").val(code1);
    	$("#Expday1").val(Expday1);
    	$('#STS').val("Y");
    	$('#searchFrm').attr('action','<c:url value="/productOrder/TmCoupon.pop"/>').submit();
    }
}
//선택한 쿠폰 사용자에게 발행 버튼
function submitMycouponDelFrm()
{
    var code = document.getElementsByName("ccode");
    var Expday = document.getElementsByName("eday");
    var code1 = "";
    var Expday1 = "";
    for(var i=0; i<code.length; i++){
   	 if(code[i].checked == true){
   		code1= code[i].value;
   		Expday1= Expday[i].value;
   	 }
    }
    
    if(code1 == ""){
   	 	alert("쿠폰을 선택해 주시기 바랍니다.");
        return; 
    
    }else{    	
    	$("#code1").val(code1);
    	$("#Expday1").val(Expday1);
    	$('#STS').val("Y");
    	$('#searchFrm').attr('action','<c:url value="/productOrder/TmCoupon.pop"/>').submit();
    }
}

//신규쿠폰등록 버튼
function newMycoupon()
{
	$('#searchFrm').attr('action','<c:url value="/productOrder/TmCouponInsertAdd.pop"/>').submit();
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("EXPDATES");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("EXPDATEE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

</script>
</head>
    <!--content -->
    <div>
    
    <form id="searchFrm" name="searchFrm" method="post">
  	
  	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
  	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
  	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
  				
  	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
  	<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
  	<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
  	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
  	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
  	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
  	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
  	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
  	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
  	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
    <input type="hidden" name="userid" id="userid" value="${searchMap.userid}" >
    
    <input type="hidden" name="code1" id="code1" >
    <input type="hidden" name="Expday1" id="Expday1" >
    <input type="hidden" name="STS" id="STS" value="N" >
    
<table style="width:100%;">
<tr>
	<td width="2%"></td>
	<td>
	
	<table>
      <tr>
        <td align="left" bgcolor="#FFFFFF"><h2>▣ 전체 쿠폰 리스트</h2></td>
      </tr>
    </table>
    
    <p class="pInto01">&nbsp;<span>총 &nbsp; ${listCount}건</span></p>
        
    <!--테이블-->
     <table class="table05">
        <tr>
          	<th></th>
			<th>쿠폰</th>
			<th>쿠폰이름</th>
			<th>할인액</th>
			<th>쿠폰유효기간</th>
			<th>적용기간</th>
			<th>대상</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
			 
			 <c:choose>
				<c:when test="${list.ISALLOC eq '0' }">
					<c:set var="trcolor" value="black"/>
				</c:when>
				
				<c:otherwise>
					<c:set var="trcolor" value="red"/>
				</c:otherwise>
			</c:choose>
				
             <tr  style="color:${trcolor};">
				<td><c:if test="${(list.EXPDATEE > list.THISTIME) }"> <input type="radio" name="ccode" id="ccode" value="${list.CCODE}"></c:if></td>
				<td>${list.CCODE}</td>
				<td style="text-align:left;">${list.CNAME}</td>
				<td>
	            	${list.REGPRICE}
	            
	            <c:choose>
					<c:when test="${list.REGTYPE eq 'C' }">
						(%)
					</c:when>
					
					<c:when test="${list.REGTYPE eq 'P' }">
						(point)
					</c:when>
					
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
	            
	            </td>
				<td>${fn:substring(list.EXPDATES, 0, 10)} ~ ${fn:substring(list.EXPDATEE, 0, 10)}</td>
				<td>${list.EXPDAY}일
					<c:if test="${(list.EXPDATEE > list.THISTIME) }"> <input type="hidden" name="eday" id="eday" value="${list.EXPDAY}"> </c:if>
				</td>
				<td>${list.TCLASS}</td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="7">표시할 쿠폰이 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
			<!-- 
             <tr>
				<td></td>
				<td><input id="new_code" name="new_code" type="text" style="width:70px;" title="" value=""/></td>
				<td><input id="cname" name="cname" type="text" style="width:220px;" title="" value=""/></td>
				<td>
					<select id="REGTYPE" name="REGTYPE" style="width:70px;">
					<option value="C">할인율</option>
					<option value="P">포인트</option>
					</select><br>
					<input id="regprice" name="regprice" type="text" style="width:50px;" title="" value=""/>
				</td>
				<td>
		              <input type="text" id="EXPDATES" name="EXPDATES" maxlength="10" class="in_basic" value="" readonly="readonly"  style="width:70px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
		              ~<br>
		              <input type="text" id="EXPDATEE" name="EXPDATEE" maxlength="10" class="in_basic" value="" readonly="readonly" style="width:70px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
				</td>
				<td><input id="EXPDAY" name="EXPDAY" type="text" style="width:20px;" title="" value=""/>일</td>
				<td>1</td>
	        </tr>
	         -->
     </table>
     <!--//테이블-->
     
    <!--버튼-->    
    <ul class="boardBtns">
    	<li><a href="javascript:submitMycouponInsFrm();">선택한 쿠폰을 사용자에게 발행</a></li>
    	<li><a href="javascript:newMycoupon();">신규쿠폰등록</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->

	</td>
	<td width="2%"></td>
</tr>
</table>	  
    
</form>
</div>
<!--//content --> 

</html>