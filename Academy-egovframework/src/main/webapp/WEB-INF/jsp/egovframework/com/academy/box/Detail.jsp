<%--
  Class Name : Detail.jsp
  Description : 사물함 수정 페이지
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
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){

	var varFrom = document.getElementById("BoxVO");
	varFrom.action = "<c:url value='/academy/box/List.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(form){
	form.action = "<c:url value='/academy/box/Update.do'/>";

	if(confirm("<spring:message code="common.save.msg" />")){	
		if (!validateBoxVO(form)){
			return false;
		} else{
			form.submit();
		}
	}
}
/* ********************************************************
 * 대여화면
 ******************************************************** */
function fn_view(box_num, rent_seq){

	var varFrom = document.getElementById("BoxVO");
	varFrom.boxNum.value = box_num;
	varFrom.rentSeq.value = rent_seq;

	varFrom.action =  "<c:url value='/academy/box/RentWrite.do' />";
	varFrom.submit();
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
<input type="hidden" name="boxNum" value="0">
<input type="hidden" name="rentSeq" value="0">
	
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
			<th><label for="boxCd">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxCd" title="${title} ${inputTxt}" value="${BoxVO.boxCd}" style="width:98%;" readonly="true" />
    			<div><form:errors path="boxCd" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함명칭 -->
		<c:set var="title"><spring:message code="box.boxNm"/></c:set>
		<tr>
			<th><label for="boxNm">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxNm" title="${title} ${inputTxt}" value="${BoxVO.boxNm}" style="width:98%;" />
    			<div><form:errors path="boxNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함갯수 -->
		<c:set var="title"><spring:message code="box.boxCount"/></c:set>
		<tr>
			<th><label for="boxCount">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxCount" title="${title} ${inputTxt}" value="${BoxVO.boxCount}" style="width:98%;" readonly="true" />
    			<div><form:errors path="boxCount" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 층수 -->
		<c:set var="title"><spring:message code="box.rowCount"/></c:set>
		<tr>
			<th><label for="rowCount">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="rowCount" title="${title} ${inputTxt}" value="${BoxVO.rowCount}" style="width:98%;" readonly="true" />
    			<div><form:errors path="rowCount" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 가로갯수 -->
		<c:set var="title"><spring:message code="box.rowNum"/></c:set>
		<tr>
			<th><label for="rowNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="rowNum" title="${title} ${inputTxt}" value="${BoxVO.rowNum}" style="width:98%;" readonly="true" />
    			<div><form:errors path="rowNum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사물함대여비용 -->
		<c:set var="title"><spring:message code="box.boxPrice"/></c:set>
		<tr>
			<th><label for="boxPrice">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="boxPrice" title="${title} ${inputTxt}" value="${BoxVO.boxPrice}" style="width:98%;" />
    			<div><form:errors path="boxPrice" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 예치금 -->
		<c:set var="title"><spring:message code="box.deposit"/></c:set>
		<tr>
			<th><label for="deposit">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="deposit" title="${title} ${inputTxt}" value="${BoxVO.deposit}" style="width:98%;" />
    			<div><form:errors path="deposit" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 시작번호 -->
		<c:set var="title"><spring:message code="box.startNum"/></c:set>
		<tr>
			<th><label for="startNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="startNum" title="${title} ${inputTxt}" value="${BoxVO.startNum}" style="width:98%;" readonly="true" />
    			<div><form:errors path="startNum" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 종료번호 -->
		<c:set var="title"><spring:message code="box.endNum"/></c:set>
		<tr>
			<th><label for="endNum">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="endNum" title="${title} ${inputTxt}" value="${BoxVO.endNum}" style="width:98%;" readonly="true" />
    			<div><form:errors path="endNum" cssClass="error" /></div>
			</td>
		</tr>
	</tbody>
	</table>
<br>
	<table class="wTable" cellpadding="5">
	<tbody>
	        <td width="50" bgcolor="#CCCCCC">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용중</p>
	        </td>
	        <td width="50" bgcolor="#f8dcd4">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용가능</p>
	        </td>
	        <td width="50" bgcolor="#f3fc65">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>대기중</p>
	        </td>
	        <td width="50" bgcolor="#79f670">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>홀드</p>
	        </td>
	        <td width="50" bgcolor="#f36262">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>고장</p>
	        </td>
	</tbody>
	</table>
	
	<table class="board_list">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 10%;">
		<col style="width: ;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
	</colgroup>
	<tbody class="ov">	
	<%-- 데이터를 화면에 출력해준다 --%>
		<c:forEach items="${boxnumList}" var="resultInfo" varStatus="status">
		<c:choose>
			<c:when test="${resultInfo.boxFlag eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
			<c:when test="${resultInfo.boxFlag eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
			<c:when test="${resultInfo.boxFlag eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
			<c:when test="${resultInfo.boxFlag eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
			<c:when test="${resultInfo.boxFlag eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
			<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
		</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<a href="javascript:fn_view('${resultInfo.boxNum}','${resultInfo.rentSeq}');"><p align="center">${resultInfo.boxNum}<br><c:if test="${resultInfo.boxFlag == 'Y'}">${resultInfo.userNm}</c:if></p></a>
			</td>
		<c:if test="${((status.index+1) mod 10) eq 0 }">
			</tr>
			<tr>
		</c:if>
		</c:forEach>	
    	</tr>
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/box/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>