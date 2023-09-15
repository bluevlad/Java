<%--
  Class Name : Detail.jsp
  Description : 과목 수정 페이지
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
<c:set var="pageTitle"><spring:message code="member.teacher.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="MemberVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){

	var varFrom = document.getElementById("MemberVO");
	varFrom.action = "<c:url value='/academy/leture/teacher/List.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(){
	var varFrom = document.getElementById("MemberVO");

	if(confirm("<spring:message code='common.save.msg' />")){
		varFrom.action =  "<c:url value='/academy/leture/teacher/Update.do' />";
		varFrom.submit();
	}
}
</script>
</head>
<body>

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="MemberVO" name="MemberVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
	
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
		
		<!-- 사용자아이디 -->
		<c:set var="title"><spring:message code="member.userId"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="userId" type="text" value="${MemberVO.userId}" style="width:90%;" readonly="true"></td>
		</tr>
		<!-- 사용자명 -->
		<c:set var="title"><spring:message code="member.userNm"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="userNm" type="text" value="${MemberVO.userNm}" style="width:90%;"></td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="member.userPwd"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="userPwd" type="text" value="${MemberVO.userPwd}" style="width:90%;"></td>
		</tr>
		<!-- 직책 -->
		<c:set var="title"><spring:message code="member.userRole"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3">
  				<select id="userRole" name="userRole" title="<spring:message code='member.userRole'/><spring:message code='input.cSelect'/>" style="width:200px;"><!-- title="질문유형 선택" -->
				<c:forEach items="${cmmCodeUSRGRP}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq MemberVO.userRole}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 직책 -->
		<c:set var="title"><spring:message code="member.sex"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="sex" type="text" value="${MemberVO.sex}" style="width:90%;"></td>
		</tr>
		<c:set var="title"><spring:message code="member.isokSms"/></c:set>
		<tr>
			<th><label for="isokSms">${title}</label></th>
			<td class="left">
				<form:select path="isokSms" id="isokSms" title="${title} ${inputTxt}">
                    <form:option value="Y" label="${inputYes}"/>
                    <form:option value="N" label="${inputNo}"/>
                </form:select>
                <div><form:errors path="isUse" cssClass="error"/></div>
			</td>
		</tr>

		<c:set var="title"><spring:message code="member.isokEmail"/></c:set>
		<tr>
			<th><label for="isokEmail">${title}</label></th>
			<td class="left">
				<form:select path="isokEmail" id="isokEmail" title="${title} ${inputTxt}">
                    <form:option value="Y" label="${inputYes}"/>
                    <form:option value="N" label="${inputNo}"/>
                </form:select>
                <div><form:errors path="isUse" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="member.isUse"/></c:set>
		<tr>
			<th><label for="isUse">${title}</label></th>
			<td class="left">
				<form:select path="isUse" id="isUse" title="${title} ${inputTxt}">
                    <form:option value="Y" label=" ${inputYes}"/>
                    <form:option value="N" label="${inputNo}"/>
                </form:select>
                <div><form:errors path="isUse" cssClass="error"/></div>
			</td>
		</tr>
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/leture/teacher/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>