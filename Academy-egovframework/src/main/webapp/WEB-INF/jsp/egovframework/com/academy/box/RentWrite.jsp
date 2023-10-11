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
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="BoxVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 아이디 중복체크
 ******************************************************** */
function fn_IdCheck(){
	if($.trim($("#USER_ID").val())==""){
		alert("사용자 아이디를 입력해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	if($.trim($("#USER_ID").val()).length < 4){
		alert("아이디는 4자 이상 13자 이하로 구성해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	$.ajax({
		type: "GET", 
		url : '<c:url value="/memberManagement/idCheck.do"/>?USER_ID=' + $.trim($("#USER_ID").val()),
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="N"){
				alert("등록된 사용자 아이디입니다. 사물함 대여 신청할 수 있습니다.");
				$("#CHECKID").val($.trim($("#USER_ID").val()));
				return;
			}else{
				alert("등록되지 않은 아이디 입니다. 사물함 대여 신청 불가합니다.");
				$("#CHECKID").val("");
				return;
			}
		},error: function(){
			alert("아이디 등록 체크 실패");
			return;
		}
	});		
}

/* ********************************************************
 * 등록처리화면
 ******************************************************** */
function fn_Submit() {
		if($.trim($("#USER_ID").val())==""){
			alert("아이디를 입력해주세요");
			$("#USER_ID").focus();
			return;
		}
		if($.trim($("#USER_ID").val()).length < 4){
			alert("아이디는 4자 이상 13자 이하로 구성해주세요");
			$("#USER_ID").focus();
			return;
		}	
		if($.trim($("#CHECKID").val())!=$.trim($("#USER_ID").val())){
			alert("아이디 등록체크를 해주세요");
			return;
		}
		if($.trim($("#RENT_START").val())==""){
			alert("대여 시작일을 입력해주세요");
			$("#RENT_START").focus();
			return;
		}
		if($.trim($("#RENT_END").val())==""){
			alert("대여 종료일을 입력해주세요");
			$("#RENT_END").focus();
			return;
		}
		if ($.trim($("#PRICE_GET_TOTAL").val()) == "") {
			alert("결재금액을 입력해 주세요.");
			$("#PRICE_GET_TOTAL").focus();
			return;
		}
		if ($.trim($("#DEPOSIT").val()) == "") {
			alert("예치금을 입력해 주세요.");
			$("#DEPOSIT").focus();
			return;
		}

		if (confirm("사물함 신청정보를 저장하겠습니까?")) {
			$("#frm").attr("action", "<c:url value='/box/boxRentOrderProcess.do' />");
			$("#frm").submit();
		}
	}
	
/* ********************************************************
 * 목록화면
 ******************************************************** */
function fn_List() {
	$("#frm").attr("action",
			"<c:url value='/box/boxView.do' />");
	$("#frm").submit();
}
	
/* ********************************************************
 * 사물함 정보 삭제 화면
 ******************************************************** */
function fn_Delete(){
	if(confirm("사물함 정보를 삭제하겠습니까?")){
		$("#frm").attr("action","<c:url value='/box/boxDeleteProcess.do' />");
		$("#frm").submit();
	}		
}
	
	$(function() {
		setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
		initDateFicker1("RENT_START");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
		initDateFicker1("RENT_END");
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
		// 아이디를 변경했을 때는 아이디 등록체크 여부를 확인하도록 세팅함.
		$('#USER_ID').keyup(function () { $("#CHECKID").val("");});
	});
	
	
/* ********************************************************
 * 사물함 상태 변경 처리화면
 ******************************************************** */
function status_update() {
	$("#frm").attr("action","<c:url value='/box/updateBoxFlagAsync.do' />");
	$("#frm").submit();
}

/* ********************************************************
 * 사물함 변경 처리화면
 ******************************************************** */
function change_box(box_cd, box_num, rent_seq) {
   	window.open('<c:url value="/box/boxChange.pop"/>?USED_BOX_CD='+box_cd+'&BOX_CD='+box_cd+'&USED_BOX_NUM='+box_num+'&USED_RENT_SEQ='+rent_seq+'&TOP_MENU_ID=<c:out value="${params.TOP_MENU_ID}"/>&MENU_ID=<c:out value="${params.MENU_ID}"/>', '_boxChangeFrm', 'location=no,resizable=yes,width=800,height=500,top=0,left=0,scrollbars=yes,location=no');
}
	
/* ********************************************************
 * 사물함 연장 처리화면
 ******************************************************** */
function fn_Extend() {
	if(confirm('현재의 사물함을 연장하시겠습니까?')) {
		$("#frm").attr("action", "<c:url value='/box/boxExtendOrderProcess.do' />");
		$("#frm").submit();
	}
}

/* ********************************************************
 * 사물함 주문정보 조회화면
 ******************************************************** */
function fn_view(orderId){
	$("#ORDERNO").val(orderId);
	$("#MENU_ID").val("FM007_002");
	$("#MENU_NM").val("사물함신청내역");
	$("#frm").attr("action","<c:url value='/box/boxOrderView.do' />");
	$("#frm").submit();
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
</script>
</head>


<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_submit(document.forms[0]); return false;">
<input type="hidden" name="WMODE" value="${WMODE}">
<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="BOXRENTWRITE">
<input type="hidden" id="CHECKID" name="CHECKID" value="${BoxVO.userId}">
<input type="hidden" id="boxCd" name="boxCd" value="${BoxVO.boxCd}">
<input type="hidden" id="boxNum" name="boxNum" value="${BoxVO.boxNum}">
<input type="hidden" id="boxNm" name="boxNm" value="${BoxVO.boxNm}">
<input type="hidden" id="rentSeq" name="rentSeq" value="${BoxVO.rentSeq}">
<input type="hidden" id="orderno" name="orderno" value="${BoxVO.orderno}">
<input type="hidden" id="rowNum" name="rowNum" value="${BoxVO.rowNum}">
<c:choose>
<c:when test="${WMODE == 'EDT'}">
	<c:set var="PRICE_GET_TOTAL" value="${boxNumRentOrderDetail.price}"/>
	<c:set var="DEPOSIT" value="${boxNumRentDetail.deposit}"/>
</c:when>
<c:otherwise>
	<c:set var="PRICE_GET_TOTAL" value="${boxDetail.boxPrice}"/>
	<c:set var="DEPOSIT" value="${boxDetail.deposit}"/>
</c:otherwise>
</c:choose>
	
	
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
	   			<a class="btn02" onclick="change_box('${BoxVO.boxCd}','${BoxVO.boxNum}','${boxNumRentDetail.rentSeq}'); return false;">사물함 변경</a>
		</tr>
		
		<!-- 사용자정보 -->
		<c:set var="title"><spring:message code="box.userId"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<input type="text" id="userId" name="userId" value="${boxNumRentDetail.userId}" style="width:90px;background:#FFECEC;"/> ${boxnumrentorderdetail.userNm}
	   			<a class="btn02"  onclick="fn_IdCheck()">아이디 등록체크</a>
			</td>
		</tr>
		
		<!-- 사물함상태 -->
		<c:set var="title"><spring:message code="box.boxFlag"/></c:set>
		<c:if test="${empty boxNumRentDetail}"> <c:set var="boxFlag" value="N"> </c:set></c:if>
		<c:if test="${!empty boxNumRentDetail}"> <c:set var="boxFlag" value="${boxNumRentDetail.boxFlag}"> </c:set></c:if>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="boxFlag" VALUE="Y" <c:if test="${boxFlag eq 'Y'}" > checked='checked' </c:if>/><spring:message code="box.code.flag2"/> &nbsp;
	  			<input type="radio" name="boxFlag" VALUE="N" <c:if test="${boxFlag eq 'N'}" > checked='checked' </c:if>/><spring:message code="box.code.flag1"/> &nbsp;
	  			<input type="radio" name="boxFlag" VALUE="D" <c:if test="${boxFlag eq 'D'}" > checked='checked' </c:if>/><spring:message code="box.code.flag3"/> &nbsp;
	  			<input type="radio" name="boxFlag" VALUE="H" <c:if test="${boxFlag eq 'H'}" > checked='checked' </c:if>/><spring:message code="box.code.flag4"/> &nbsp;
	  			<input type="radio" name="boxFlag" VALUE="X" <c:if test="${boxFlag eq 'X'}" > checked='checked' </c:if>/><spring:message code="box.code.flag5"/> &nbsp;
	   			<a class="btn02" onclick="status_update()">상태값 변경</a>
			</td>
		</tr>
		
		<!-- 대여 기간 -->
		<c:set var="title"><spring:message code="box.rentDay"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
              <input type="text" id="rentStart" name="rentStart" value="${boxNumRentDetail.rentStart}" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="rentEnd" name="rentEnd" value="${boxNumRentDetail.rentEnd}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
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
		
		<!-- 결제금액 -->
		<c:set var="title"><spring:message code="box.pay.Total"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="payTotal" type="text" value="${BoxVO.payTotal}" style="width:90%;">원</td>
		</tr>
		
		<!-- 할인금액 -->
		<c:set var="title"><spring:message code="box.pay.priceDiscount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="priceDiscount" type="text" value="${boxNumRentOrderDetail.priceDiscount}" style="width:90%;">원</td>
		</tr>
		
		<!-- 카드지불액 -->
		<c:set var="title"><spring:message code="box.pay.priceCard"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="priceCard" type="text" value="${boxNumRentOrderDetail.priceCard}" style="width:90%;;background:#FFECEC;"></td>
		</tr>
		<!-- 현금지불액 -->
		<c:set var="title"><spring:message code="box.pay.priceCash"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="priceCash" type="text" value="${boxNumRentOrderDetail.priceCash}" style="width:90%;;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"></td>
		</tr>
		
		<!-- 할인내역 -->
		<c:set var="title"><spring:message code="box.endNum"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><textarea id="priceDiscountReason" name="priceDiscountReason" ROWS="3" style="width:75%;">${boxNumRentOrderDetail.priceDiscountReason}</textarea></td>
		</tr>
		
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="deposit" type="text" value="${BoxVO.deposit}" style="width:90%;;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"></td>
		</tr>
		
		<!-- 예치금 반환 여부 -->
		<c:set var="title"><spring:message code="box.depositYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="depositYn" VALUE="Y" <c:if test="${boxNumRentDetail.depositYn eq 'Y'}" > checked='checked' </c:if>/>반환 &nbsp;
	  			<input type="radio" name="depositYn" VALUE="N" <c:if test="${boxNumRentDetail.depositYn eq 'N'}" > checked='checked' </c:if>/>미반환 &nbsp;
			</td>
		</tr>
		
		<!-- 결제방법 -->
		<c:set var="title"><spring:message code="box.payGubun"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="payGubun" VALUE="PAY110" <c:if test="${boxNumRentOrderDetail.payGubun eq 'PAY110'}" > checked='checked' </c:if>/>카드 &nbsp;
	  			<input type="radio" name="payGubun" VALUE="PAY140" <c:if test="${boxNumRentOrderDetail.payGubun eq 'PAY140'}" > checked='checked' </c:if>/>현금 &nbsp;
			</td>
		</tr>

		<!-- 신청구분 -->
		<c:set var="title"><spring:message code="box.rentType"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="rentType" VALUE="ON" <c:if test="${boxNumRentDetail.rentType eq 'ON'}" > checked='checked' </c:if>/>온라인 &nbsp;
	  			<input type="radio" name="rentType" VALUE="OFF" <c:if test="${boxNumRentDetail.rentType eq 'OFF'}" > checked='checked' </c:if>/>오프라인 &nbsp;
			</td>
		</tr>

		<!-- 키반납여부 -->
		<c:set var="title"><spring:message code="box.keyYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="keyYn" VALUE="R" <c:if test="${boxNumRentDetail.keyYn eq 'R'}" > checked='checked' </c:if>/>대여 &nbsp;
	  			<input type="radio" name="keyYn" VALUE="Y" <c:if test="${boxNumRentDetail.keyYn eq 'Y'}" > checked='checked' </c:if>/>반납 &nbsp;
	  			<input type="radio" name="keyYn" VALUE="N" <c:if test="${boxNumRentDetail.keyYn eq 'N'}" > checked='checked' </c:if>/>미반납 &nbsp;
			</td>
		</tr>

		<!-- 연장여부 -->
		<c:set var="title"><spring:message code="box.extendYn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
	  			<input type="radio" name="extendYn" VALUE="Y" <c:if test="${boxNumRentDetail.extendYn eq 'Y'}" > checked='checked' </c:if>/>Yes &nbsp;
	  			<input type="radio" name="extendYn" VALUE="N" <c:if test="${boxNumRentDetail.extendYn eq 'N'}" > checked='checked' </c:if>/>No &nbsp;
			</td>
		</tr>
		
		<!-- 대여정보 -->
		<c:set var="title"><spring:message code="box.rentMemo"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><textarea id="rentMemo" name="rentMemo" ROWS="3" style="width:75%;">${boxNumRentOrderDetail.rentMemo}</textarea></td>
		</tr>

	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/box/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
    <c:if test="${!empty boxNumRentOrderDetail.orderno}">
	<span class="btn_s"><a href="javascript:fn_Extend();" title="<spring:message code='button.list' />  <spring:message code='input.button' />">연장<spring:message code="button.list" /></a></span>
    </c:if>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>