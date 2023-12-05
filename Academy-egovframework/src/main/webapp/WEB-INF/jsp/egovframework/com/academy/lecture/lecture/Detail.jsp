<%--
  Class Name : Detail.jsp
  Description : 강의 수정 페이지
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
<c:set var="pageTitle"><spring:message code="lec.Lecture.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="LectureVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list(){

	var varFrom = document.getElementById("LectureVO");
	varFrom.action = "<c:url value='/academy/leture/ㅣlecture/List.do' />";
	varFrom.submit();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save(){
	var varFrom = document.getElementById("LectureVO");

	if(confirm("<spring:message code='common.save.msg' />")){
		varFrom.action =  "<c:url value='/academy/leture/lecture/Update.do' />";
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
<form:form commandName="LectureVO" name="LectureVO" action="" method="post" onSubmit="fn_save(document.forms[0]); return false;">
	
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
		
		<!-- 강의코드 -->
		<c:set var="title"><spring:message code="lec.lecture.lecCd"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left"><input name="lecCd" type="text" value="${LectureVO.lecCd}" style="width:90%;" readonly="true"></td>
		</tr>
		<!-- 강의명 -->
		<c:set var="title"><spring:message code="lec.lecture.lecTitle"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecTitle" type="text" value="${LectureVO.lecTitle}" style="width:90%;"></td>
		</tr>
		<!-- 강의예정회차 -->
		<c:set var="title"><spring:message code="lec.lecture.lecSchedule"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecSchedule" type="text" value="${LectureVO.lecSchedule}" style="width:90%;"></td>
		</tr>
		<!-- 강의수 -->
		<c:set var="title"><spring:message code="lec.lecture.lecCount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecCount" type="text" value="${LectureVO.lecCount}" style="width:90%;"></td>
		</tr>
		<!-- 강의개요 -->	
		<c:set var="title"><spring:message code="lec.lecture.lecDesc"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="lecDesc" rows="3" style="width:98%;" cssClass="txaClass" title="<spring:message code='lec.lecture.lecDesc'/><spring:message code='input.input'/>"/>
    			<form:errors path="lecDesc"/>
			</td>
		</tr>
		<!-- 키워드 -->	
		<c:set var="title"><spring:message code="lec.lecture.lecKeyword"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="lecKeyword" rows="3" style="width:98%;" cssClass="txaClass" title="<spring:message code='lec.lecture.lecKeyword'/><spring:message code='input.input'/>"/>
    			<form:errors path="lecKeyword"/>
			</td>
		</tr>
		<!-- 강의일수 -->
		<c:set var="title"><spring:message code="lec.lecture.lecPeriod"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecPeriod" type="text" value="${LectureVO.lecPeriod}" style="width:90%;"></td>
		</tr>
		<!-- 강의원가 -->
		<c:set var="title"><spring:message code="lec.lecture.lecPrice"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecPrice" type="text" value="${LectureVO.lecPrice}" style="width:90%;"></td>
		</tr>
		<!-- 할인율 -->
		<c:set var="title"><spring:message code="lec.lecture.lecDiscount"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecDiscount" type="text" value="${LectureVO.lecDiscount}" style="width:90%;"></td>
		</tr>
		<!-- 수강료 -->
		<c:set var="title"><spring:message code="lec.lecture.lecMovie"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecMovie" type="text" value="${LectureVO.lecMovie}" style="width:90%;"></td>
		</tr>
		<!-- 포인트 -->
		<c:set var="title"><spring:message code="lec.lecture.lecPoint"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecPoint" type="text" value="${LectureVO.lecPoint}" style="width:90%;"></td>
		</tr>
		<!-- 주교재 -->
		<c:set var="title"><spring:message code="lec.lecture.lecVodDefaultPath"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3">
                <table class="tdTable" id="bookJuArea">
                    <tr>
                        <th width="15%">직급</th>
                        <th width="15%">학습형태</th>
                        <th>교재명</th>
                        <th width="10%">삭제</th>
                    </tr>
                    <c:set var="CHECKBOOKSET" value="N" />
                    <c:forEach items="${viewbooklist}" var="item" varStatus="loop">
                        <c:if test="${item.bookFlag eq 'J'}">
                        <c:set var="CHECKBOOKSET" value="Y" />
                            <tr>
                                <td>${item.categoryNm}</td>
                                <td>${item.formName}</td>
                                <td><a href="javascript:fn_summary('${item.bookCd}')">${item.bookNm}</a></td>
                                <td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="bookCd" value="${item.bookCd}" /></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${CHECKBOOKSET eq 'N'}">
                        <tr class="basic_space">
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </c:if>
                </table>
			</td>
		</tr>
		<!-- 동영상 기본경로 -->
		<c:set var="title"><spring:message code="lec.lecture.lecVodDefaultPath"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecVodDefaultPath" type="text" value="${LectureVO.lecVodDefaultPath}" style="width:90%;"></td>
		</tr>
		<!-- 보강비밀번호 -->
		<c:set var="title"><spring:message code="lec.lecture.lecPass"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left" colspan="3"><input name="lecPass" type="text" value="${LectureVO.lecPass}" style="width:90%;"></td>
		</tr>
		<!-- 사용여부 -->
		<c:set var="title"><spring:message code="lec.isUse"/></c:set>
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
	<span class="btn_s"><a href="<c:url value='/academy/leture/lecture/List.do' />"   title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>