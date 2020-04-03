<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<!--content -->
<div id="content">

	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHKIND" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
	<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
	<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
	<input type="hidden" id="CCODE" name="CCODE" value="${view.CCODE}"/>
    
	<table class="table01">
		<col width="15%"/>
		<col width="35%"/>
		<col width="15%"/>
		<col width="35%"/>
		<tr>
			<th>쿠폰코드</th>
        	<td colspan="3">${view.CCODE}</td>
		</tr>
		<tr>
			<th>쿠폰명</th>
			<td colspan="3"><input type="text" id="CNAME" name="CNAME" value="${view.CNAME}" style="width:90%;background:#FFECEC;"/></td>
		</tr>
		<tr>
        	<th>쿠폰설명</th>
        	<td colspan="3"><textarea id="CCONTENT" name="CCONTENT" rows="3" style="width:90%;">${view.CCONTENT}</textarea></td>
		</tr>
		<tr>
        	<th>할인구분</th>
        	<td>
        		<input type="radio"  id="REGTYPE" name="REGTYPE" value="C" <c:if test="${view.REGTYPE == 'C'}">checked</c:if>/>할인율&nbsp;
        		<input type="radio"  id="REGTYPE" name="REGTYPE" value="P" <c:if test="${view.REGTYPE == 'P'}">checked</c:if>/>할인금액
        	</td>
        	<th>할인율(할인금액)</th>
        	<td>
        		<input type="text" id="REGPRICE" name="REGPRICE" value="${view.REGPRICE}" style="width:100px;background:#FFECEC;"/>
        		<c:if test="${view.REGTYPE == 'C'}">%</c:if>
        		<c:if test="${view.REGTYPE == 'P'}">원</c:if>
        	</td>
		</tr>
		<tr>
			<th>사용기간</th>
        	<td><input type="text" id="EXPDAY" name="EXPDAY" value="${view.EXPDAY}" style="width:50px;background:#FFECEC;"/>일</td>
			<th>유효기간</th>
			<td>
				<input type="text" id="EXPDATES" name="EXPDATES" value="${view.EXPDATES}" style="width:70px;background:#FFECEC;IME-MODE:disabled;"/> 
					&nbsp;~&nbsp;
				<input type="text" id="EXPDATEE" name="EXPDATEE" value="${view.EXPDATEE}" style="width:70px;background:#FFECEC;IME-MODE:disabled;"/>
          	</td>
		</tr>
		<tr>
			<th>사용구분</th>
        	<td colspan="3">
        		<input type="radio"  id="ADD_FLAG" name="ADD_FLAG" value="O" <c:if test="${view.ADD_FLAG == 'O'}">checked</c:if>/>동영상&nbsp;
        		<input type="radio"  id="ADD_FLAG" name="ADD_FLAG" value="M" <c:if test="${view.ADD_FLAG == 'M'}">checked</c:if>/>모의고사&nbsp;
        		<input type="radio"  id="ADD_FLAG" name="ADD_FLAG" value="L" <c:if test="${view.ADD_FLAG == 'L'}">checked</c:if>/>교재&nbsp;
        		<input type="radio"  id="ADD_FLAG" name="ADD_FLAG" value="F" <c:if test="${view.ADD_FLAG == 'F'}">checked</c:if>/>학원&nbsp;
        		<input type="radio"  id="ADD_FLAG" name="ADD_FLAG" value="B" <c:if test="${view.ADD_FLAG == 'B'}">checked</c:if>/>배송
        	</td>
		</tr>
        	<input type="hidden" id="TCLASS" name="TCLASS" value="${view.TCLASS}" /></td>
        	<input type="hidden" id="TCLASSCAT" name="TCLASSCAT" value="${view.TCLASSCAT}" /></td>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">수정</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
	</form>
</div>
<!--//content --> 
<script type="text/javascript">

$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("EXPDATES");	
	initDateFicker2("EXPDATEE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/Coupon/list.do' />");
	$("#frm").submit();
}

// 숫자만입력
function fn_OnlyNumber(obj) {
	for (var i = 0; i < obj.value.length ; i++){
		chr = obj.value.substr(i,1);  
	  	chr = escape(chr);
	  	key_eg = chr.charAt(1);
		if (key_eg == "u"){
	   		key_num = chr.substr(i,(chr.length-1));   
		   	if((key_num < "AC00") || (key_num > "D7A3")) { 
		    	event.returnValue = false;
		   	}    
	  	}
	}
	if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
	}else{
		event.returnValue = false;
	}
}

// 수정
function fn_Submit(){

	if($.trim($("#CNAME").val()) == ""){
 		alert("쿠폰명을 입력하세요");
 		$("#CNAME").focus();
        return;
 	}
 	if($.trim($("#REGPRICE").val()) == ""){
 		alert("금액/할인율을 입력하세요");
 		$("#REGPRICE").focus();
        return;
 	}
 	if($.trim($("#EXPDAY").val()) == ""){
 		alert("사용기간을 입력하세요");
 		$("#EXPDAY").focus();
        return;
 	} 	
 	if($.trim($("#EXPDATES").val()) == ""){
 		alert("유효기간 시작일을 입력하세요");
 		$("#EXPDATES").focus();
        return;
 	}
 	if($.trim($("#EXPDATEE").val()) == ""){
 		alert("유효기간 종료일을 입력하세요");
 		$("#EXPDATEE").focus();
        return;
 	}

	$("#frm").attr("action","<c:url value='/Coupon/update.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>