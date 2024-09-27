<%--
  Class Name : Detail.jsp
  Description : 설문관리 수정 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
    2008.03.09    장동한          최초 생성
    2017.07.19    김예영          표준프레임워크 v3.7 개선
	2023.01.01   KYK        설문문항 관리 등록
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="survey.manage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="surveyVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_init(){

	$("#surveySdat").datepicker(  
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


	$("#surveyEdat").datepicker( 
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
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){
	location.href = "<c:url value='/academy/survey/mst/List.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(form){

	form.action = "<c:url value='/academy/survey/mst/Modify.do'/>";
	var sStartDay = form.surveySdat.value.replaceAll("-","");
	var sEndDay = form.surveyEdat.value.replaceAll("-","");

	var iStartDay = parseInt(sStartDay);
	var iEndDay = parseInt(sEndDay);

	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateSurveyVO(form)){
			return;
		}else{
			if(iStartDay > iEndDay || iEndDay < iStartDay){
				alert("<spring:message code='survey.surveySdat'/>"+"\n\n"+"<spring:message code='survey.surveyEdat'/>"); //설문기간  시작일은 종료일  보다 클수 없고 \n\n설문기간 종료일은 시작일 보다 작을수 없습니다!
				return;
			}
			form.submit();
		}
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
<form:form commandName="surveyVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
 
<!-- 타이틀 -->
<h2>${pageTitle} <spring:message code="title.update" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.detail" /></caption>
	<colgroup>
		<col style="width:20%;">
		<col style="width: ;">		
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문제목 -->
		<c:set var="title"><spring:message code="survey.title"/></c:set>
		<tr>
			<th><label for="surveyTitle">${title}</label><span class="pilsu">*</span></th>
			<td class="left">
  				<form:input path="surveyTitle" cssClass="txaIpt" style="width:98%;" title="<spring:message code='survey.title'/><spring:message code='input.input'/>" /> <!-- title="설문제목 입력" -->
      			<form:errors path="surveyTitle"/>
			</td>
		</tr>
		<!-- 설문작성안내 내용 -->	
		<c:set var="title"><spring:message code="survey.Desc"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="surveyDesc" rows="3" style="width:98%;" cssClass="txaClass" title="<spring:message code='survey.Desc'/><spring:message code='input.input'/>"/><!-- title="설문작성안내내용 입력" -->
    			<form:errors path="surveyDesc"/>
			</td>
		</tr>
		<!-- 설문대상 -->
		<c:set var="title"><spring:message code="survey.Target"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="surveyTarget" title="<spring:message code='survey.Target'/><spring:message code='input.cSelect'/>"><!-- title="설문대상 선택" -->
					<option value=""><spring:message code="input.cSelect"/></option>
			    <form:options items="${comCode034}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
			<div><form:errors path="surveyTarget"/></div>
			</td>
		</tr>
		<!-- 설문기간 -->
		<c:set var="title"><spring:message code="survey.date"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="nopd">
  				<form:input path="surveySdat" readonly="true" style="width:70px;" />
				<form:errors path="surveySdat" cssClass="error"/>
				 ~ <form:input path="surveyEdat" readonly="true" style="width:70px;" />
				<form:errors path="surveyEdat" cssClass="error"/>
			</td>
		</tr>
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="survey.set.setId"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<table border="0" cellspacing="0" cellpadding="0" align="center">
					<c:forEach items="${resultList}" var="resultList" varStatus="status">
					<tr>
						<td height="10"><input type="radio" name="setId" value="${resultList.setId}" <c:if test="${surveyVO.setId == resultList.setId}">checked</c:if>>${resultList.setTitle}</td>
					</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	
	</tbody>
	</table>
<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/survey/mst/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>

<input name="surveyId" type="hidden" value="${SurveyVO.surveyId}">
</form:form>

</body>
</html>