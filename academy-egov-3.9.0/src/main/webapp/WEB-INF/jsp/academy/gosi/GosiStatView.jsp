<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${ msg ne '' && msg ne null}">alert('${msg}')</c:if>
	
	$(document).ready(function(){
		setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
		initDatePicker1("START_DATE");	
		$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	});
});

function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/GosiStatView.do' />");
	$("#frm").submit();
}

function fn_gosiUpdate(){
	if($("#GOSI_COUNT").val()<0 || $("#GOSI_COUNT").val()==""){
		alert("정확한 수를 입력하세요");
		return;
	}
	if(confirm("오픈시작일과 총 참여자수를 조정하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiUpdate.do' />");
		$("#frm").submit();
	}
}
function fn_makeResult(){
	if(confirm("시험결과를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiMakeResult.do' />");
		$("#frm").submit();
	}
}

function fn_makeAdjust(){
	if(confirm("조정점수를 새로 작성하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiMakeAdjust.do' />");
		$("#frm").submit();
	}
}

function fn_makeStat(){
	if(confirm("시험통계결과를 새로 작성하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/GosiMakeStat.do' />");
		$("#frm").submit();
	}
}
function fn_makeRank(){
	if(confirm("응시자 랭킹을 새로 작성하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/gosi/makeGosiRank.do' />");
		$("#frm").submit();
	}
}
</script>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

	<h2>● 강의 관리 > <strong>시험통계관리</strong></h2>	
    <select name="GOSI_CD" id="GOSI_CD" onchange="fn_List()">
		<option value="">--분류 선택--</option>
		<c:forEach items="${gosi_list}" var="item" varStatus="loop">
			<option value="${item.GOSI_CD}" <c:if test="${params.GOSI_CD == item.GOSI_CD }">selected="selected"</c:if>>${item.GOSI_NM}</option>
		</c:forEach>
	</select>
    <!--버튼-->
    <c:set var="upd_cnt" value="${gosi_info.GOSI_COUNT+gosi_info.USER_CNT+gosi_info.DET_CNT+gosi_info.POLL_CNT}"/>
    <c:set var="real_cnt" value="${gosi_info.USER_CNT+gosi_info.DET_CNT+gosi_info.POLL_CNT}"/>
    <div class="boardBtns">
    <span style="color:red;">수정 참여현황=${upd_cnt}명 |  
    	실제 참여현황=${real_cnt}명[${gosi_info.USER_CNT}명(사전예약)+${gosi_info.DET_CNT}명(채점)+${gosi_info.POLL_CNT}명(설문조사)]
   	</span>
   	</div>
    <c:if test="${gosi_info.IS_USE eq 'Y'}">
	<ul class="boardBtns">
		<c:set var="start_day" value=""/>
		<c:set var="start_time" value=""/>
		<c:set var="start_min" value=""/>
		<c:if test="${not empty gosi_info.START_DATE and gosi_info.START_DATE!=''}">
			<c:set var="start_day" value="${fn:substring(gosi_info.START_DATE, 0, 8)}"/>
			<c:set var="start_time" value="${fn:substring(gosi_info.START_DATE, 8, 10)}"/>
			<c:set var="start_min" value="${fn:substring(gosi_info.START_DATE, 10, 12)}"/>
		</c:if>
		오픈시작일:
		<input type="text" id="START_DATE" name="START_DATE" size="10" readonly="readonly" value="${start_day}">
		<select id="START_TIME" name="START_TIME">
		<c:forEach begin="1" end="24" step="1" var="item">
				<c:if test="${ item < 10}">
					<c:set var="itemCmpr" value="0${ item }"/>
					<option value="${ itemCmpr }"
						<c:if test="${ start_time eq itemCmpr }">selected="selected"</c:if>>0${item}</option></c:if>
				<c:if test="${ item >= 10}"><option value="${ item }"
					<c:if test="${start_time eq item }">selected="selected"</c:if>>${item}</c:if>
			</c:forEach>
		</select>
		시
		<select id="START_MIN" name="START_MIN">
		<c:forEach begin="0" end="60" step="1" var="item">
				<c:if test="${ item < 10}">
					<c:set var="itemMin" value="0${ item }"/>
					<option value="${ itemMin }"
						<c:if test="${ start_min eq itemMin }">selected="selected"</c:if>>0${item}</option></c:if>
				<c:if test="${ item >= 10}"><option value="${ item }"
					<c:if test="${ start_min eq item }">selected="selected"</c:if>>${item}</c:if>
			</c:forEach>
		</select>
		분
		|
		<li>총 참여자수 표기 값:<input type="text" style="width:100px;text-align: right;" id="GOSI_COUNT" name="GOSI_COUNT" value="${gosi_info.GOSI_COUNT}"/>&nbsp;&nbsp;<a href="javascript:fn_gosiUpdate();">수정</a></li>
    	<li><a href="javascript:fn_makeResult();">성적결과처리</a></li>
    	<li><a href="javascript:fn_makeAdjust();">조정점수산출</a></li>
    	<li><a href="javascript:fn_makeStat();">시험통계산출</a></li>
    	<li><a href="javascript:fn_makeRank();">응시자랭킹산출</a></li>
    </ul>
    </c:if>
    <!--//버튼-->

   	<table class="table01">
		<tr>
  			<th>응시구분</th>
  			<th>과목</th>
   			<th>응시인원</th>
   			<th>평균점수</th>
   			<th>5%평균</th>
<!--   			<th>10%평균</th> -->
  		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
   		<tr>
  			<td>${item.GOSI_AREA_FULL_NM}(${item.GOSI_AREA_NM})</td>
  			<td>${item.GOSI_SUBJECT_NM}</td>
  			<td>${item.GOSI_USER_NUM}</td>
  			<td>${item.GOSI_AVR_POINT}</td>
  			<td>${item.GOSI_3_POINT}</td>
<%--   			<td>${item.GOSI_10_POINT}</td> --%>
  		</tr>
  		</c:forEach>
	</table>
     
</form>
</div>
<!--//content --> 
</body>
</html>