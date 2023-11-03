<%--
  Class Name : OrderView.jsp
  Description : 사물함 주문 정보 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2023.11.00    rainend          최초 생성
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
$(function() {
	$("#refundDt").datepicker(  
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
});

/* ********************************************************
 * 목록화면
 ******************************************************** */
function fn_List() {
	var varFrom = document.getElementById("BoxVO");
	varFrom.action = "<c:url value='/academy/box/RentWrite.do' />";
	varFrom.submit();
}

// 사물함 정보 삭제 
function fn_DeleteOrder(){
	if(confirm("사물함 주문정보를 삭제하겠습니까?")){
		$("#frm").attr("action","<c:url value='/box/boxDeleteOrderProcess.do' />");
		$("#frm").submit();
	}		
}

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
</script>
</head>

<body>


<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<!-- 상단타이틀 -->

<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
<input type="hidden" id="boxCd" name="boxCd" value="${boxOrderDetail.boxCd}">
<input type="hidden" id="boxNum" name="boxNum" value="${boxOrderDetail.boxNum}">
<input type="hidden" id="rentSeq" name="rentSeq" value="${boxOrderDetail.rentSeq}">
<input type="hidden" id="orderno" name="orderno" value="${boxOrderDetail.orderno}">

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
		<c:set var="title"><spring:message code="box.orderno"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.orderno}</td>
		</tr>
		<c:set var="title"><spring:message code="box.userId"/>(<spring:message code="box.userNm"/>)</c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.userId}(${boxOrderDetail.userNm})</td>
		</tr>
		<c:set var="title"><spring:message code="member.email"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.email}</td>
		</tr>
		<c:set var="title"><spring:message code="box.title.rentInfo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.boxNm}(${boxOrderDetail.boxNum})</td>
		</tr>
		<c:set var="title"><spring:message code="box.title.rentDay"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.rentStart} ~ ${boxOrderDetail.rentEnd}</td>
		</tr>
		<c:set var="title"><spring:message code="box.regDt"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.rentStart} ~ ${boxOrderDetail.rentEnd}</td>
		</tr>
		<c:set var="title"><spring:message code="box.rentType"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><c:if test="${boxOrderDetail.rentType eq 'OF'}"><spring:message code="box.code.rentType.Off"/></c:if>
				<c:if test="${boxOrderDetail.rentType eq 'ON'}"><spring:message code="box.code.rentType.On"/></c:if></td>
		</tr>
		<c:set var="title"><spring:message code="box.boxPrice"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.boxPrice}</td>
		</tr>
		<c:set var="title"><spring:message code="box.pay.Total"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.payTotal}</td>
		</tr>
		<c:set var="title"><spring:message code="box.pay.priceCard"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.priceCard}</td>
		</tr>
		<c:set var="title"><spring:message code="box.pay.priceCash"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.priceCash}</td>
		</tr>
		<c:set var="title"><spring:message code="box.pay.priceDiscount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.priceDiscount}</td>
		</tr>
		<c:set var="title"><spring:message code="box.priceDiscountReason"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">${boxOrderDetail.priceDiscountReason}</td>
		</tr>
		<c:set var="title"><spring:message code="box.rentMemo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="rentMemo" title="${title} ${inputTxt}" rows="3" style="width:98%;" value="${boxOrderDetail.rentMemo}" />
    			<form:errors path="rentMemo"/>
			</td>
		</tr>
		<c:set var="title"><spring:message code="box.title.refundInfo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="priceCard" title="${title} ${inputTxt}" style="width:120px;" value="${boxOrderDetail.refundPrice}" />원
    			<div><form:errors path="refundPrice" cssClass="error" /></div>
  				<form:input path="refundDt" readonly="true" style="width:70px;" value="${boxOrderDetail.refundDt}" />
				<form:errors path="refundDt" cssClass="error"/>
			</td>
		</tr>
	</table>
	<!--//테이블--> 

	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 목록버튼 -->	
		<span class="btn_s"><a href="javascript:fn_List();"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	    <c:if test="${boxOrderDetail.statuscode eq 'DLV105'}">
		<span class="btn_s"><a href="javascript:fn_Refund();" title="<spring:message code='box.button.refund' />  <spring:message code='input.button' />"><spring:message code="box.button.refund" /></a></span>
	    </c:if>
		<span class="btn_s"><a href="javascript:fn_DeleteOrder();"   title="<spring:message code='box.button.delete' />  <spring:message code='input.button' />"><spring:message code="box.button.delete" /></a></span>
	</div><div style="clear:both;"></div>
    
</form:form>

</div>
<!--//content --> 
</body>
</html>