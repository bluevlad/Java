<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.bpopup.min2.js" />"></script>
<script type="text/javascript">
function fn_makeResult(){
	if(confirm("등록 답안지를 일괄 채점하시겠습니까?")){
		$('#rd-popup').bPopup();					 

		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
				type: "POST", 
			    url : '<c:url value="/gosi/GosiMakeResult.do"/>',
			    data: dataString,
			    dataType: "text",  
	            cache : false,
			    success: function(RES) {
				    if($.trim(RES)=="Y"){
						alert("답안지 채점이 완료되었습니다.");
						$('#rd-popup').bPopup().close();
						url  = '<c:url value="/gosi/GosiStatView.do"/>'+"?MENU_ID=${params.MENU_ID}&TOP_MENU_ID=${params.TOP_MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=${params.L_MENU_NM}";
						location.href = url;
					}
				},error: function(){
			    	alert("수정 실패");
			      	return;
				}
		});
	}
}

function fn_makeAdjust(){
	if(confirm("조정점수를 새로 작성하시겠습니까?")){
		$('#rd-popup').bPopup();					 

		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
				type: "POST", 
			    url : '<c:url value="/gosi/GosiMakeAdjust.do"/>',
			    data: dataString,
			    dataType: "text",  
	            cache : false,
			    success: function(RES) {
				    if($.trim(RES)=="Y"){
						alert("조정점수 반영이 완료되었습니다.");
						$('#rd-popup').bPopup().close();
						url  = '<c:url value="/gosi/GosiStatView.do"/>'+"?MENU_ID=${params.MENU_ID}&TOP_MENU_ID=${params.TOP_MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=${params.L_MENU_NM}";
						location.href = url;
					}
				},error: function(){
			    	alert("수정 실패");
			      	return;
				}
		});
	}
}

function fn_makeStat(){
	if(confirm("직렬[지역]별 상세 시험통계결과를 새로 작성하시겠습니까?")){
		$('#rd-popup').bPopup();					 

		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
				type: "POST", 
			    url : '<c:url value="/gosi/GosiMakeStat.do"/>',
			    data: dataString,
			    dataType: "text",  
	            cache : false,
			    success: function(RES) {
				    if($.trim(RES)=="Y"){
						alert("직렬[지역]별 상세 시험통계결과 처리가 완료되었습니다.");
						$('#rd-popup').bPopup().close();
						url  = '<c:url value="/gosi/GosiStatView.do"/>'+"?MENU_ID=${params.MENU_ID}&TOP_MENU_ID=${params.TOP_MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=${params.L_MENU_NM}";
						location.href = url;
					}
				},error: function(){
			    	alert("수정 실패");
			      	return;
				}
		});
	}
}
</script>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="GOSI_CD" name="GOSI_CD" value="${params.GOSI_CD}"/>

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    
	<div align="left">
		<select id="GOSI_CD" name="GOSI_CD" onchange="fn_List()">
		<c:forEach items="${gosi_list}" var="item" varStatus="loop">
			<option value="${item.GOSI_CD}" <c:if test="${params.GOSI_CD eq item.GOSI_CD}">selected="selected"</c:if>>${item.GOSI_NM}</option>
		</c:forEach>
		</select>
	</div>

    <!--버튼-->
	<ul class="boardBtns">
    	<li><a href="javascript:fn_makeAdjust();">조정점수산출</a></li>
    	<li><a href="javascript:fn_makeStat();">시험통계산출</a></li>
    	<li><a href="javascript:fn_makeResult();">[답안지일괄채점]</a></li>
    </ul>
    <!--//버튼-->

   	<table class="table01">
		<tr>
  			<th>응시구분</th>
  			<th width="7%">과목</th>
  			<th width="7%">과목구분</th>
   			<th width="7%">직렬별응시인원</th>
   			<th width="7%">표준편차</th>
   			<th width="7%">평균점수</th>
   			<th width="7%">조정평균점수</th>
   			<th width="7%">5%평균</th>
  			<th width="7%">10%평균</th>
  			<th width="7%">[과목별응시인원]</th>
  			<th width="7%">[과목별평균점수]</th>
  		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
   		<tr>
  			<td>${item.GOSI_AREA_FULL_NM}</td>
  			<td>${item.SUBJECT_NM}</td>
  			<td>${item.SBJ_TYPE}</td>
  			<td>${item.GOSI_USER_NUM}</td>
  			<td>${item.SDV}</td>
  			<td>${item.GOSI_AVR_POINT}</td>
  			<td>${item.GOSI_ADJ_AVR_POINT}</td>
  			<td>${item.GOSI_3_POINT}</td>
  			<td>${item.GOSI_10_POINT}</td>
  			<td>${item.REQ_USR_NUM}</td>
  			<td>${item.AVR_POINT}</td>
  		</tr>
  		</c:forEach>
	</table>
</form>
</div>
<!--//content --> 
		<div id="rd-popup" class="rd-Pstyle"  style="visibility:hidden;">
			<span class="b-close"></span>
		</div>