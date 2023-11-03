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
// 사물함 변경
function change_boxnum(boxnum) {
	var varFrom = document.getElementById("BoxVO");

	var usedBoxCd = varFrom.usedBoxCd.value;
	var usedBoxNum = varFrom.usedBoxNum.value;
	var usedRentSeq = varFrom.usedRentSeq.value;
	var boxCd = varFrom.boxCd.value;
	var boxNum = boxnum;
	
	if (confirm(<spring:message code="box.change.num" /> "사물함 번호 [" + boxnum + "]를 선택했습니다. 이 번호로 변경하겠습니까?")) {
	
		$.ajax({
			url : "<c:url value='/academy/box/ChangePop' />",
			type: "POST", 
        	data : {"usedBoxNum":usedBoxNum, "usedBoxCd":usedBoxCd, "usedRentSeq":usedRentSeq, "boxCd":boxCd, "boxNum":boxNum},
	        dataType: 'json',
			success: function(data) {
				alert("정상적으로 자리가 변경되었습니다.");
				window.opener.document.location.href="<c:url value='/academy/box/Detail.do' />?boxCd="+varFrom.boxCd.value;
				window.close();
			},error: function(){
				alert("자리이동실패");
			}
		});		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	
	}
}

// 다른 사물함 층을 선택
function fn_Change_boxCD() {
	var varFrom = document.getElementById("BoxVO");
	varFrom.boxCd.value = varFrom.box.value;
	varFrom.action = "<c:url value='/academy/box/Change.pop' />";
	varFrom.submit();
}

</script>
</head>
       		
<body>
<!--content -->
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<!-- 상단타이틀 -->

<form:form commandName="BoxVO" name="BoxVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
<input type="hidden" id="usedBoxCd" name="usedBoxCd" value="${usedBoxCd}">
<input type="hidden" id="usedBoxNum" name="usedBoxNum" value="${usedBoxNum}">
<input type="hidden" id="usedRentSeq" name="usedRentSeq" value="${usedRentSeq}">
<input type="hidden" id="boxCd" name="boxCd" value="${boxCd}">
<input type="hidden" id="boxNum" name="boxNum" value="0">

<div id="contentBoxNum">

   	<!-- 사물함 loop -->
   	<table class="wTable" cellpadding="5">
    	<tr>
			<td>
			<select id="box" name="box" onchange="fn_Change_boxCD();" style="width:120;">
				<c:forEach items="${boxlist}" var="item" varStatus="loop">
				<option value="${item.boxCd}" <c:if test="${boxCd == item.boxCd}">selected</c:if>>${item.boxNm}</option>
				</c:forEach>
		 	</select>             
			</td>
		</tr>
	</table>
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
    
   	<!-- 사물함 loop -->
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
    	<tr>
		<c:forEach items="${boxnumList}" var="item" varStatus="loop">
			<c:choose>
				<c:when test="${item.boxFlag eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
				<c:when test="${item.boxFlag eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
				<c:when test="${item.boxFlag eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
				<c:when test="${item.boxFlag eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
				<c:when test="${item.boxFlag eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
				<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
			</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<c:choose>
				<c:when test="${item.boxFlag eq 'N'}">
					<p align="center"><a href="javascript:change_boxnum('${item.boxNum}');">${item.boxNum}</a></p>
				</c:when>
				<c:otherwise>
					<p align="center">${item.boxNum}</p>
				</c:otherwise>
				</c:choose>
			</td>
		<c:if test="${((loop.index+1) mod 10) eq 0 }">
		</tr>
		<tr>
		</c:if>								
		</c:forEach>
    	</tr>
	</tbody>
	</table>
</div>
</form:form>
<!--//content --> 
</body>
</html>