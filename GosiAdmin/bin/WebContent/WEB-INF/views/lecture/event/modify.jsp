<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="EVENT_ID" name="EVENT_ID" value="${item.EVENT_ID}"/>

	<h2>● 강의관리 > <strong>강의구매이벤트 등록</strong></h2>
    
	<table class="table01">
		<col width="20%"/>
		<col width="30%"/>
		<col width="20%"/>
		<col width="30%"/>
		<tr>
			<th width="160">이벤트명</th>
        	<td colspan="3"><input type="text" id="EVENT_NM" name="EVENT_NM" value="${item.EVENT_NM }" size="120" maxlength="333" style="background:#FFECEC;"/></td>
		</tr>
		<tr>
			<th>이벤트방법</th>
			<td>
				<input type="radio" name="EVENT_TYPE" value="D" <c:if test="${item.EVENT_TYPE eq 'D' }">checked="checked"</c:if> /><span class="txt03">할인율</span>			
				<input type="radio" name="EVENT_TYPE" value="P" <c:if test="${item.EVENT_TYPE eq 'P' }">checked="checked"</c:if> /><span class="txt03">금액</span>			
			</td>
			<th>할인율(금액)</th>
			<td><input type="text" id="EVENT_AMOUNT" name="EVENT_AMOUNT" value="${item.EVENT_AMOUNT }" style="background:#FFECEC;"/></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
					시작일 <input type="text" id="ST_DATE" name="ST_DATE" value="${item.ST_DATE }" style="background:#FFECEC;width:90px;" readonly="readonly"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					종료일 <input type="text" id="ED_DATE" name="ED_DATE" value="${item.ED_DATE }" style="background:#FFECEC;width:90px;" readonly="readonly"/>
          	</td>
			<th>사용여부</th>
			<td>
				<input type="radio" name="EVENT_YN" value="Y" <c:if test="${item.EVENT_YN eq 'Y' }">checked="checked"</c:if> /><span class="txt03">진행</span>			
				<input type="radio" name="EVENT_YN" value="N" <c:if test="${item.EVENT_YN eq 'N' }">checked="checked"</c:if> /><span class="txt03">종료</span>			
			</td>
		</tr>
    </table>
	
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">저장</a></li>
    </ul>
    <!--//버튼-->

	<table class="table02">
		<tr>
        	<th width="30">#</th>
        	<th width="100">과목코드</th>
			<th>과목명</th>
		</tr>
		<c:forEach items="${list_prd}" var="item" varStatus="loop">
        <tr>
			<td><input name="i_leccode" type="checkbox" value="${item.LECCODE}"/></td>
			<td>${item.LECCODE}</td>
			<td>${item.SUBJECT_TITLE}</td>
        </tr>
		</c:forEach>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_LecPop();">강의등록</a></li>
   		<li><a href="javascript:fn_LecDel();">강의삭제</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("ST_DATE");	
	initDateFicker2("ED_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 강의검색팝업
function fn_LecPop(type){
	window.open('<c:url value="/productevent/pop_subject_add.pop"/>?EVENT_ID='+${item.EVENT_ID}, '_lec', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/productevent/list.do' />");
	$("#frm").submit();
}

//목록으로
function fn_LecDel(){
	$("#frm").attr("action","<c:url value='/productevent/lec_delete.do' />");
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

// 등록
function fn_Submit(){
	if($.trim($("#EVENT_NM").val()) == ""){
		alert("이벤트명을 입력하세요");
		$("#EVENT_NM").focus();
		return;
	}
	if(1 != $("input[name='EVENT_TYPE']:checked").size()){
		alert("이벤트 방법을 선택하세요");
		$("input[name='EVENT_TYPE']")[0].focus();
		return;
	}
 	if($.trim($("#ST_DATE").val()) == ""){
 		alert("이벤트 시작일을 입력하세요");
 		$("#ST_DATE").focus();
        return;
 	}	
 	if($.trim($("#ED_DATE").val()) == ""){
 		alert("이벤트 종료일을 입력하세요");
 		$("#ED_DATE").focus();
        return;
 	} 	
 	if($.trim($("#EVENT_AMOUNT").val()) == ""){
 		alert("할인율(금액)을 입력하세요");
 		$("#EVENT_AMOUNT").focus();
        return;
 	} 	

	if(confirm("강의할인 이벤트 내용 변경하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/productevent/update.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>