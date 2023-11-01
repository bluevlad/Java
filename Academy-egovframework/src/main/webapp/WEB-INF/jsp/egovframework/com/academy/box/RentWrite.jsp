<%--
  Class Name : RentWrite.jsp
  Description : 사물함 대여 정보 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2023.08.00    rainend          최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="box.title.boxInfo"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="BoxVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_init(){

	$("#rentStart").datepicker(  
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});


	$("#rentEnd").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
	
}
	
/* ********************************************************
 * 목록화면
 ******************************************************** */
function fn_List() {
	var varFrom = document.getElementById("BoxVO");
	varFrom.action = "<c:url value='/academy/box/Detail.do' />";
	varFrom.submit();
}

/* ********************************************************
 * 아이디 중복체크
 ******************************************************** */
function fn_IdCheck(){
	var varFrom = document.getElementById("BoxVO");

	if(varFrom.userId.value==""){
		alert("사용자 아이디를 입력해 주세요");
		varFrom.userId.focus();
		return;
	}	
	if(varFrom.userId.value.length < 4){
		alert("아이디는 4자 이상 13자 이하로 구성해 주세요");
		varFrom.userId.focus();
		return;
	}	
	$.ajax({
		type: "GET", 
		url : '<c:url value="/api/member/idCheck"/>?userId=' + varFrom.userId.value,
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="Y"){
				alert("등록된 사용자 아이디입니다. 사물함 대여 신청할 수 있습니다.");
				varFrom.CHECKID.val(varFrom.userId.value);
				return;
			}else{
				alert("등록되지 않은 아이디 입니다. 사물함 대여 신청 불가합니다.");
				varFrom.CHECKID.val("");
				return;
			}
		},error: function(){
			alert("아이디 등록 체크 실패");
			return;
		}
	});		
	
}
	
	
/* ********************************************************
 * 사물함 상태 변경 처리화면
 ******************************************************** */
function status_update() {
	var varFrom = document.getElementById("BoxVO");

	var param =  "&boxFlag="+varFrom.boxFlag.value+"&boxCd="+varFrom.boxCd.value+"&boxNum="+varFrom.boxNum.value;

	$.ajax({
		type: "POST", 
		url : '<c:url value="/academy/box/api/UpdateBoxFlag"/>?userId=' + varFrom.userId.value,
		data : param,		
		success: function(RES) {
			if($.trim(RES)=="Y"){
				alert("사물함 상태가 변경되었습니다.");
				return;
			}
		},error: function(){
			alert("사물함 상태 변경 실패!");
			return;
		}
	});		

}
	
/* ********************************************************
 * 사물함 연장 처리화면
 ******************************************************** */
function fn_Extend() {
	if(confirm('현재의 사물함을 연장하시겠습니까?')) {
		var varFrom = document.getElementById("BoxVO");
		varFrom.action = "<c:url value='/academy/box/ExtendOrder.do' />";
		varFrom.submit();
	}
}

/* ********************************************************
 * 등록처리화면
 ******************************************************** */
function fn_save(form) {
	form.action = "<c:url value='/academy/box/RentOrder.do'/>";

	if(confirm("<spring:message code="common.save.msg" />")){	
		if (!validateBoxVO(form)){
			return false;
		} else{
			form.submit();
		}
	}
}
	
/* ********************************************************
 * 사물함 변경 처리화면
 ******************************************************** */
function change_box(box_cd, box_num, rent_seq) {
   	window.open('<c:url value="/academy/box/Change.pop"/>?usedBoxCd='+box_cd+'&boxCd='+box_cd+'&usedBoxNum='+box_num+'&usedRentSeq='+rent_seq, '_boxChangeFrm', 'location=no,resizable=yes,width=800,height=500,top=0,left=0,scrollbars=yes,location=no');
}

/* ********************************************************
 * 사물함 환불 처리화면
 ******************************************************** */
function fn_Refund() {
	if($.trim($("#REFUND_PRICE").val())==""){
		alert("환불 금액을 입력해 주세요");
		$("#REFUND_PRICE").focus();
		return;
	}
	if($.trim($("#REFUND_DATE").val())==""){
		alert("환불 일자를 입력해 주세요");
		$("#REFUND_DATE").focus();
		return;
	}

	if (confirm("사물함 대여 환불 정보를 저장하겠습니까?")) {
		$("#frm").attr("action", "<c:url value='/box/boxRefundProcess.do' />");
		$("#frm").submit();
	}
}

/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
</head>

<body onLoad="fn_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<!-- 상단타이틀 -->

<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
<input type="hidden" name="WMODE" value="${WMODE}">
<input type="hidden" id="boxCd" name="boxCd" value="${BoxVO.boxCd}">
<input type="hidden" id="boxNum" name="boxNum" value="${BoxVO.boxNum}">
<input type="hidden" id="boxNm" name="boxNm" value="${BoxVO.boxNm}">
<input type="hidden" id="rentSeq" name="rentSeq" value="${boxNumRentDetail.rentSeq}">
<input type="hidden" id="orderno" name="orderno" value="${boxNumRentDetail.orderno}">
	
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<!-- 사물함코드 -->
		<c:set var="title"><spring:message code="box.boxCd"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	   			${BoxVO.boxNm} (${BoxVO.boxNum}번) &nbsp;
	   			<a class="btn02" onclick="change_box('${BoxVO.boxCd}','${BoxVO.boxNum}','${boxNumRentDetail.rentSeq}'); return false;"><spring:message code="box.button.exchange" /></a>
		</tr>
		
		<!-- 사용자정보 -->
		<c:set var="title"><spring:message code="box.userId"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="userId" title="${title} ${inputTxt}" style="width:150px;" value="${boxNumRentDetail.userId}" />&nbsp;<a class="btn02" onclick="fn_IdCheck();"><spring:message code="member.button.checkId" /></a>
    			<div><form:errors path="userId" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 사물함상태 -->
		<c:set var="title"><spring:message code="box.boxFlag"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.boxFlag eq 'Y'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.boxFlag eq 'N'}" >checked</c:if></c:set>
				<c:set var="chk3"><c:if test="${boxNumRentDetail.boxFlag eq 'D'}" >checked</c:if></c:set>
				<c:set var="chk4"><c:if test="${boxNumRentDetail.boxFlag eq 'H'}" >checked</c:if></c:set>
				<c:set var="chk5"><c:if test="${boxNumRentDetail.boxFlag eq 'X'}" >checked</c:if></c:set>
	  			<form:radiobutton path="boxFlag" title="${title} ${inputTxt}" value="Y" checked="${chk1}"/><spring:message code="box.code.flag2"/> &nbsp;
	  			<form:radiobutton path="boxFlag" title="${title} ${inputTxt}" value="N" checked="${chk2}"/><spring:message code="box.code.flag1"/> &nbsp;
	  			<form:radiobutton path="boxFlag" title="${title} ${inputTxt}" value="D" checked="${chk3}"/><spring:message code="box.code.flag3"/> &nbsp;
	  			<form:radiobutton path="boxFlag" title="${title} ${inputTxt}" value="H" checked="${chk4}"/><spring:message code="box.code.flag4"/> &nbsp;
	  			<form:radiobutton path="boxFlag" title="${title} ${inputTxt}" value="X" checked="${chk5}"/><spring:message code="box.code.flag5"/> &nbsp;
	   			<a class="btn02" onclick="status_update()"><spring:message code="box.button.status"/></a>
    			<div><form:errors path="boxFlag"/></div>
			</td>
		</tr>
		
		<!-- 대여 기간 -->
		<c:set var="title"><spring:message code="box.rentDay"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="rentStart" readonly="true" style="width:70px;" value="${boxNumRentDetail.rentStart}" />
				<form:errors path="rentStart" cssClass="error"/>
				 ~ <form:input path="rentEnd" readonly="true" style="width:70px;" value="${boxNumRentDetail.rentEnd}" />
				<form:errors path="rentEnd" cssClass="error"/>
			</td>
		</tr>
		
		<!-- 사물함주문정보 -->
		<c:set var="title"><spring:message code="box.title.ordInfo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	   			주문번호 : ${boxNumRentOrderDetail.orderno} &nbsp;&nbsp; || &nbsp;&nbsp;주문일시 : ${boxNumRentOrderDetail.regDt} ||
	          &nbsp;&nbsp;주문방법 : <c:if test="${boxNumRentOrderDetail.rentType eq 'OFF'}">오프라인</c:if><c:if test="${boxNumRentOrderDetail.rentType eq 'ON'}">온라인</c:if>
			</td>
		</tr>
		
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="deposit" title="${title} ${inputTxt}" style="width:120px;" value="${BoxVO.deposit}" />원
    			<div><form:errors path="deposit" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 결제금액 -->
		<c:set var="title"><spring:message code="box.pay.Total"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="payTotal" title="${title} ${inputTxt}" style="width:120px;" value="${boxNumRentOrderDetail.price}" />원
    			<div><form:errors path="payTotal" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 할인금액 -->
		<c:set var="title"><spring:message code="box.pay.priceDiscount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="priceDiscount" title="${title} ${inputTxt}" style="width:120px;" value="${boxNumRentOrderDetail.priceDiscount}" />원
    			<div><form:errors path="priceDiscount" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 카드지불액 -->
		<c:set var="title"><spring:message code="box.pay.priceCard"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="priceCard" title="${title} ${inputTxt}" style="width:120px;" value="${boxNumRentOrderDetail.priceCard}" />원
    			<div><form:errors path="priceCard" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 현금지불액 -->
		<c:set var="title"><spring:message code="box.pay.priceCash"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="priceCash" title="${title} ${inputTxt}" style="width:120px;" value="${boxNumRentOrderDetail.priceCash}" />원
    			<div><form:errors path="priceCash" cssClass="error" /></div>
			</td>
		</tr>
		
		<!-- 할인내역 -->
		<c:set var="title"><spring:message code="box.priceDiscountReason"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="priceDiscountReason" title="${title} ${inputTxt}" rows="3" style="width:95%;"/>
    			<form:errors path="priceDiscountReason"/>
			</td>
		</tr>
		
		<!-- 예치금 반환 여부 -->
		<c:set var="title"><spring:message code="box.depositYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.depositYn eq 'Y'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.depositYn eq 'N'}" >checked</c:if></c:set>
			    <form:radiobutton path="depositYn" title="${title} ${inputTxt}" value="Y" checked="${chk1}"/><spring:message code="box.code.depositY"/>&nbsp;
			    <form:radiobutton path="depositYn" title="${title} ${inputTxt}" value="N" checked="${chk2}" /><spring:message code="box.code.depositN"/>&nbsp;
				<div><form:errors path="depositYn"/></div>
			</td>
		</tr>
		
		<!-- 결제방법 -->
		<c:set var="title"><spring:message code="box.payGubun"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.payGubun eq 'PAY110'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.payGubun eq 'PAY140'}" >checked</c:if></c:set>
			    <form:radiobutton path="payGubun" title="${title} ${inputTxt}" value="PAY110" checked="${chk1}" /><spring:message code="box.code.pay110"/>&nbsp;
			    <form:radiobutton path="payGubun" title="${title} ${inputTxt}" value="PAY110" checked="${chk2}" /><spring:message code="box.code.pay140"/>&nbsp;
				<div><form:errors path="payGubun"/></div>
			</td>
		</tr>

		<!-- 신청구분 -->
		<c:set var="title"><spring:message code="box.rentType"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.rentType eq 'ON'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.rentType eq 'OF'}" >checked</c:if></c:set>
			    <form:radiobutton path="rentType" title="${title} ${inputTxt}" value="ON" checked="${chk1}" /><spring:message code="box.code.rentType.On"/>&nbsp;
			    <form:radiobutton path="rentType" title="${title} ${inputTxt}" value="OF" checked="${chk2}" /><spring:message code="box.code.rentType.Off"/>&nbsp;
				<div><form:errors path="rentType"/></div>
			</td>
		</tr>

		<!-- 키반납여부 -->
		<c:set var="title"><spring:message code="box.keyYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.keyYn eq 'R'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.keyYn eq 'Y'}" >checked</c:if></c:set>
				<c:set var="chk3"><c:if test="${boxNumRentDetail.keyYn eq 'N'}" >checked</c:if></c:set>
			    <form:radiobutton path="keyYn" title="${title} ${inputTxt}" value="R" checked="${chk1}" /><spring:message code="box.code.keyR"/>&nbsp;
			    <form:radiobutton path="keyYn" title="${title} ${inputTxt}" value="Y" checked="${chk2}" /><spring:message code="box.code.keyY"/>&nbsp;
			    <form:radiobutton path="keyYn" title="${title} ${inputTxt}" value="N" checked="${chk3}" /><spring:message code="box.code.keyN"/>&nbsp;
				<div><form:errors path="keyYn"/></div>
			</td>
		</tr>

		<!-- 연장여부 -->
		<c:set var="title"><spring:message code="box.extendYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<c:set var="chk1"><c:if test="${boxNumRentDetail.extendYn eq 'Y'}" >checked</c:if></c:set>
				<c:set var="chk2"><c:if test="${boxNumRentDetail.extendYn eq 'N'}" >checked</c:if></c:set>
			    <form:radiobutton path="extendYn" title="${title} ${inputTxt}" value="Y" checked="${chk1}" /><spring:message code="box.code.extendY"/>&nbsp;
			    <form:radiobutton path="extendYn" title="${title} ${inputTxt}" value="N" checked="${chk2}" /><spring:message code="box.code.extendN"/>&nbsp;
				<div><form:errors path="extendYn"/></div>
			</td>
		</tr>
		
		<!-- 대여정보 -->
		<c:set var="title"><spring:message code="box.rentMemo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="rentMemo" title="${title} ${inputTxt}" rows="3" style="width:98%;" value="${boxNumRentOrderDetail.rentMemo}" />
    			<form:errors path="rentMemo"/>
			</td>
		</tr>

	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="javascript:fn_List();"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
    <c:if test="${!empty boxNumRentOrderDetail.orderno}">
	<span class="btn_s"><a href="javascript:fn_Extend();" title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="box.button.extend" /></a></span>
    </c:if>
</div><div style="clear:both;"></div>
</form:form>
</div>

   	<!-- 사물함 loop -->
	<table class="board_list" summary="<spring:message code='common.summary.list' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
		<col style="width: 10%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="member.userId" /></th>
			<th><spring:message code="member.userNm" /></th>
			<th><spring:message code="box.rentStart" /></th>
			<th><spring:message code="box.rentEnd" /></th>
			<th><spring:message code="box.depositYn" /></th>
			<th><spring:message code="box.keyYn" /></th>
			<th><spring:message code="box.extendYn" /></th>
			<th><spring:message code="box.rentType" /></th>
			<th><spring:message code="box.payGubun" /></th>
		</tr>
		<tbody>
		<c:if test="${fn:length(boxNumRentOrderList) == 0}">
		<tr>
			<td colspan="9"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach items="${boxNumRentOrderList}" var="list" varStatus="status">
		<tr>
			<td>${list.userId}</td>
			<td><a href="javascript:fn_view('${list.orderno}')">${list.userNm}</a></td>
			<td>${list.rentStart}</td>
			<td>${list.rentEnd}</td>
			<td>${list.depositYn}</td>
			<td>${list.keyYn}</td>
			<td>${list.extendYn}</td>
			<td>${list.rentType}</td>
			<td>${list.payGubun}</td>
		</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>