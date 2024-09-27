<%--
  Class Name : Detail.jsp
  Description : 설문문항 수정 페이지
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
<c:set var="pageTitle"><spring:message code="survey.que.manage.Title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="SurveyBankVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){

	var varFrom = document.getElementById("SurveyVO");
	varFrom.action = "<c:url value='/academy/survey/bank/List.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(){
	var varFrom = document.getElementById("SurveyVO");

	if(confirm("<spring:message code='common.save.msg' />")){
		varFrom.action =  "<c:url value='/academy/survey/bank/Regist.do' />";
		varFrom.submit();
	}
}

function type_reload(){
    var sm = document.getElementById("viewSelect");
    if(document.getElementById("queType").value == "S"){  //single선택형
    	document.getElementById("queCount").selectedIndex == document.getElementById("tmp").value;
        view_reload("S");
        sm.style.display = "";
        document.getElementById("queCount").disabled == false;
    }else if(document.getElementById("queType").value == "M"){  //multi선택형
    	document.getElementById("queCount").selectedIndex == document.getElementById("tmp").value;
        view_reload("M");
        sm.style.display = "";
        document.getElementById("queCount").disabled == false;
    }else if(document.getElementById("queType").value == "T"){    //단답형
    	document.getElementById("queCount").selectedIndex == 0;
        view_reload("T");
        sm.style.display = "none";
    }else if(document.getElementById("queType").value == "D"){    //서술형
    	document.getElementById("queCount").selectedIndex == 0;
        view_reload("D");
        sm.style.display = "none";
    }
}

function view_reload(type){
    var cnt = parseInt(document.getElementById("tmp").value);
    for(i=1; i<=10; i++){
        var sm = document.getElementById("viewTEXT"+i);
        if(i<=cnt && (type=='S' || type=='M')){
 			if(sm) {
            	sm.style.display="";
	        }
        }else{
            sm.style.display = "none";
        }
    }
}

function changeQueuCount() {
	document.getElementById("tmp").value = document.getElementById("queCount").value;
	view_reload(document.getElementById("queType").value);
}
</script>
</head>
<body onLoad="type_reload();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="wTableFrm">
<!-- 상단타이틀 -->
<form:form commandName="SurveyVO" name="SurveyVO" action="" method="post" enctype="multipart/form-data" onSubmit="fn_save(document.forms[0]); return false;">
	<input name="queId" id="queId" type="hidden" value="${resultList.queId}">
	<input name="cmd" type="hidden" value="${cmd}">
	
	<c:choose>
	    <c:when test="${resultList.queType == 'S' || resultList.queType == 'M'}">
	        <input type="hidden" name="tmp" id="tmp" value="${resultList.queCount}"/>
	    </c:when>
	    <c:otherwise>
	        <input type="hidden" name="tmp" id="tmp" value="4">
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
		<!-- 설문지정보(제목) -->
		<c:set var="title"><spring:message code="survey.que.title"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="queTitle" type="text" value="${resultList.queTitle}"></td>
		</tr>
		<!-- 질문유형 -->	
		<c:set var="title"><spring:message code="survey.queType"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3">
  				<select id="queType" name="queType" title="<spring:message code='survey.queType'/><spring:message code='input.cSelect'/>" onchange="type_reload();"><!-- title="질문유형 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option><!-- 선택 -->
				<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList.queType}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="survey.que.desc"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3">
				<textarea name="queDesc" id="queDesc" class="textarea"  cols="75" rows="10" title="<spring:message code='survey.que.desc'/><spring:message code='input.input'/>" style="width:99%;">${resultList.queDesc}</textarea><!-- title="질문내용 입력" -->
    			<div><form:errors path="queDesc" cssClass="error"  /></div>
    		</td>
		</tr>
		<!-- 최대선택건수 -->
		<c:set var="title"><spring:message code="survey.queCount"/></c:set>
		<tr id="viewSelect">
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3">
  				<select id="queCount" name="queCount" title="<spring:message code='survey.queCount'/><spring:message code='input.cSelect'/>" onchange="changeQueuCount();"><!-- title="최대선택건수 선택" -->
			        <c:forEach var="i" begin="1" end="10">
			       	<option value="${i}" <c:if test="${resultList.queCount == i}">selected</c:if>>${i}</option>
			        </c:forEach>
		      	</select>
			</td>
		</tr>
	    <c:forEach var="v" begin="1" end="10" varStatus="status">
        <c:set var="Sview" value= "none"/>
       	<c:if test="${resultList.queCount >= status.count}"><c:set var="Sview" value= ""/></c:if>
        <c:choose>
            <c:when test="${status.count == 1}">
                <c:set var="viw" value = "${resultList.queViw1}"/>
            </c:when>
            <c:when test="${status.count == 2}">
                <c:set var="viw" value= "${resultList.queViw2}"/>
            </c:when>
            <c:when test="${status.count == 3}">
                <c:set var="viw" value= "${resultList.queViw3}"/>
            </c:when>
            <c:when test="${status.count == 4}">
                <c:set var="viw" value= "${resultList.queViw4}"/>
            </c:when>
            <c:when test="${status.count == 5}">
                <c:set var="viw" value= "${resultList.queViw5}"/>
            </c:when>
            <c:when test="${status.count == 6}">
                <c:set var="viw" value= "${resultList.queViw6}"/>
            </c:when>
            <c:when test="${status.count == 7}">
                <c:set var="viw" value= "${resultList.queViw7}"/>
            </c:when>
            <c:when test="${status.count == 8}">
                <c:set var="viw" value= "${resultList.queViw8}"/>
            </c:when>
            <c:when test="${status.count == 9}">
                <c:set var="viw" value= "${resultList.queViw9}"/>
            </c:when>
            <c:when test="${status.count == 10}">
                <c:set var="viw" value= "${resultList.queViw10}"/>
            </c:when>
            <c:otherwise>
                <c:set var="viw" value= ""/>
            </c:otherwise>
        </c:choose>
	    <!-- single select -->
	    <tr style='<c:out value="display:${Sview}"/>' id='<c:out value="viewTEXT${status.count}"/>'>
	        <th>보기<c:out value="${status.count}"/></th>
	        <td><textarea name="queViw${status.count}" id="queViw${status.count}" style="width:98%" rows="2">${viw}</textarea></td>
	    </tr>
	    </c:forEach>
		
	</tbody>
	</table>

<!-- 하단 버튼 -->
<div class="btn">
	<!-- 저장버튼 -->
	<input type="submit" class="s_submit" value="<spring:message code='button.save' />" title="<spring:message code='button.save' /> <spring:message code='input.button' />" onclick="fn_save(this.form); return false;"/>
	<!-- 목록버튼 -->	
	<span class="btn_s"><a href="<c:url value='/academy/survey/bank/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>