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
    
	<table class="table01">
		<col width="15%"/>
		<col width="35%"/>
		<col width="15%"/>
		<col width="35%"/>
		<tr>
			<th>제휴사코드</th>
        	<td>
				<select name="COOP_CD" id="COOP_CD" style="width:100px;background:#FFECEC;">
					<option value="">--제휴사선택--</option>
					<c:forEach items="${coop_list}" var="item" varStatus="loop">
					<option value="${item.COOP_CD}">${item.COOP_NM}</option>
					</c:forEach>
				</select> 
        	</td>
        	<th>강의코드</th>
        	<td><input type="text" id="LECCODE" name="LECCODE" style="width:100px;background:#FFECEC;" onclick="pop_subject_add();"/></td>
		</tr>
		<tr>
			<th>쿠폰/수강권명</th>
			<td colspan="3"><input type="text" id="COUPON_NM" name="COUPON_NM" value="" style="width:90%;background:#FFECEC;"/></td>
		</tr>
		<tr>
        	<th>쿠폰발행수</th>
        	<td><input type="text" id="COUPON_CNT" name="COUPON_CNT" style="width:40px;background:#FFECEC;"/></td>
			<th>유효기간</th>
			<td>
				<input type="text" id="ST_DT" name="ST_DT" value="" style="width:70px;background:#FFECEC;IME-MODE:disabled;"/>
					&nbsp;~&nbsp;
				<input type="text" id="ED_DT" name="ED_DT" value="" style="width:70px;background:#FFECEC;IME-MODE:disabled;"/>
          	</td>
		</tr>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">등록</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
	</form>
</div>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("ST_DT");	
	initDateFicker2("ED_DT");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/Coupon/coop_coupon_list.do' />");
	$("#frm").submit();
}

// 강의선택
function pop_subject_add() {
	window.open('<c:url value="/Coupon/pop_subject_add.pop"/>', '_blank', 'location=no,resizable=no,width=800,height=550,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
}

// 등록
function fn_Submit(){

	if($.trim($("#COOP_CD").val()) == ""){
 		alert("제휴사를 선택하세요");
 		$("#COOP_CD").focus();
        return;
 	}
 	if($.trim($("#LECCODE").val()) == ""){
 		alert("강의를 선택하세요");
 		$("#LECCODE").focus();
        return;
 	}
 	if($.trim($("#COUPON_NM").val()) == ""){
 		alert("쿠폰/수강권명을 입력하세요");
 		$("#COUPON_NM").focus();
        return;
 	} 	
 	if($.trim($("#COUPON_CNT").val()) == ""){
 		alert("쿠폰발행수를 입력하세요");
 		$("#COUPON_CNT").focus();
        return;
 	}
 	if($.trim($("#ST_DT").val()) == ""){
 		alert("유효기간 시작일을 입력하세요");
 		$("#ST_DT").focus();
        return;
 	}
 	if($.trim($("#ED_DT").val()) == ""){
 		alert("유효기간 종료일을 입력하세요");
 		$("#ED_DT").focus();
        return;
 	}

	$("#frm").attr("action","<c:url value='/Coupon/CoopCouponInsert.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>