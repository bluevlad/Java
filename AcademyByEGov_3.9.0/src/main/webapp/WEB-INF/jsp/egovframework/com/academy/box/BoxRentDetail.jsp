<%
/**
 * @Class Name : ExamList.java
 * @Modification Information
 * @
 * @  수정일     수정자         수정내용
 * @ ---------     --------    ---------------------------
 *  2020.05.25	rainend		사물함대여 정보
 *  @author rainend
 *  @version 1.0
 *  @see
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="box.boxManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 사물함 관리 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<validator:javascript formName="BoxVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">
function init_BoxManage(){

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

function fnList() {
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/box/mst/Detail.do'/>";
    varFrom.submit();
}

function fnUpdate() {
    var varFrom = document.getElementById("BoxVO");
    varFrom.action = "<c:url value='/box/rent/update.do'/>";
    if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.submit();
    }
}
</script>
</head>

<body onLoad="init_BoxManage()">
<form:form commandName="BoxVO" method="post" action="" onSubmit="fnUpdate(); return false;">
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 15%;">
		<col style="width: 35%;">
		<col style="width: 15%;">
		<col style="width: 35%;">
	</colgroup>
	<tbody>
	
		<!-- 검색조건 유지 -->
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
		<c:set var="inputNo"><spring:message code="input.no" /></c:set>
		
		<tr>
		<!-- 사물함명 -->
		<c:set var="title"><spring:message code="box.title.boxInfo" /></c:set>
			<th><label for="boxNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<form:input path="boxCd" title="${title} ${inputTxt}" style="width:70px;" readonly="true" />
				<form:input path="boxNm" title="${title} ${inputTxt}" style="width:80%;" />
				<div><form:errors path="boxNm" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
		<!-- 사용자아이디 -->
		<c:set var="title"><spring:message code="box.title.userInfo" /></c:set>
			<th><label for="userId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<form:input path="userId" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="userId" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="box.title.rentInfo" /></c:set>
			<th><label for="boxFlag">${title}</label> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<div style="float:left;"><form:radiobutton path="boxFlag" value="1"/><spring:message code="box.code.flag1"/> </div> <!-- 사용 -->
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="boxFlag" value="2"/><spring:message code="box.code.flag2"/> </div><!-- 미사용 --> 
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="boxFlag" value="3"/><spring:message code="box.code.flag3"/> </div><!-- 대기 -->
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="boxFlag" value="4"/><spring:message code="box.code.flag4"/> </div><!-- 홀드 -->
				<div style="float:left; margin:0 0 0 10px"><form:radiobutton path="boxFlag" value="5"/><spring:message code="box.code.flag5"/> </div><!-- 고장 -->
				<div style="clear:both;"><form:errors path="boxFlag" cssClass="error"/></div>
				
			</td>
		</tr>
		<tr>
		<!-- 대여기간 -->
		<c:set var="title"><spring:message code="box.title.rentDay" /></c:set>
			<th><label for="rentStart">${title}</label> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<form:input path="rentStart" readonly="true" maxlength="10" style="width:70px;"/>
				&nbsp;&nbsp;~&nbsp;&nbsp;
				<form:input path="rentEnd" readonly="true" maxlength="10" style="width:70px;"/>
				&nbsp;
			</td>
		</tr>
		<tr>
		<!-- 주문정보 -->
		<c:set var="title"><spring:message code="box.title.ordInfo" /></c:set>
			<th><label for="orderId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<form:input path="orderId" title="${title} ${inputTxt}" style="width:100px;" />
				<div><form:errors path="orderId" cssClass="error" /></div>
			</td>
		</tr>
		<tr>
		<!-- 결제금액 -->
		<c:set var="title"><spring:message code="box.boxPrice" /></c:set>
			<th><label for="boxPrice">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="boxPrice" title="${title} ${inputTxt}" style="width:100px;" />
				<div><form:errors path="boxPrice" cssClass="error" /></div>       
			</td>
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit" /></c:set>
			<th><label for="deposit">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="deposit" title="${title} ${inputTxt}" style="width:100px;" />
				<div><form:errors path="deposit" cssClass="error" /></div>       
			</td>
		</tr>
		<tr>
		<!-- 결제방법 -->
		<c:set var="title"><spring:message code="box.payGubun" /></c:set>
			<th><label for="payGubun">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="payGubun" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="payGubun" cssClass="error" /></div>       
			</td>
		<!-- 신청구분 -->
		<c:set var="title"><spring:message code="box.rentType" /></c:set>
			<th><label for="rentType">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="rentType" title="${title} ${inputTxt}" style="width:70px;" />
				<div><form:errors path="rentType" cssClass="error" /></div>       
			</td>
		</tr>
	</tbody>
	</table>
	<br>
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 10%;">
		<col style="width: 10%">
		<col style="width: 10%">
		<col style="width: 10%">
		<col style="width: 10%">
		<col style="width: 10%">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="box.userNm" /></th><!-- 사용자 -->
		<th><spring:message code="box.rentStart" /></th><!-- 대여시작일 -->
		<th><spring:message code="box.rentEnd" /></th><!-- 대여종료일 -->
		<th><spring:message code="box.depositYn" /></th><!-- 예치금반환여부 -->
		<th><spring:message code="box.keyYn" /></th><!-- 키반납여부 -->
		<th><spring:message code="box.extendYn" /></th><!-- 연장구분 -->
		<th><spring:message code="box.rentType" /></th><!-- 신청구분 -->
		<th><spring:message code="box.payGubun" /></th><!-- 결제구분 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(RentList) == 0}">
	<tr>
		<td colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="RentList" items="${RentList}" varStatus="status">
	<tr>
		<td><c:out value="${status.count+1}"/></td>
		<td><c:out value="${RentList.userId}"/>(<c:out value="${RentList.userNm}"/>)</td>
		<td><c:out value="${RentList.rentStart}"/></td>
		<td><c:out value="${RentList.rentEnd}"/></td>
		<td><c:out value="${RentList.depositYn}"/></td>
		<td><c:out value="${RentList.keyYn}"/></td>
		<td><c:out value="${RentList.extendYn}"/></td>
		<td><c:out value="${RentList.rentType}"/></td>
		<td><c:out value="${RentList.payGubun}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<span class="btn_s"><a href="javascript:fnList()" title="<spring:message code="button.list" /><spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div>
	<div style="clear:both;"></div>

</div>
</form:form>

</body>
</html>
