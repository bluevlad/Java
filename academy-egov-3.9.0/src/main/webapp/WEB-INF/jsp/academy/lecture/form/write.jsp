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
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<h2>● 강의 관리 > <strong>학습형태관리</strong></h2>
   	<table class="table01">
		<tr>
   			<th>코드</th>
  			<td>
	   			<input type="text" id="CODE" name="CODE" value="" size="20"  maxlength="10" title="코드" style="width:28%;background:#FFECEC;IME-MODE:disabled;"/>
  			</td>
  		</tr>
  		<!--
		<tr>
  			<th>ON/OFF</th>
  			<td>
				<select id="ONOFF_DIV" name="ONOFF_DIV">
					<option value="0">온라인</option>
					<option value="1">오프라인</option>
				</select>
  			</td>
  		</tr>
  		-->
		<tr>
  			<th>분류</th>
  			<td>
				<select id="LEC_DIV" name="LEC_DIV">
				<c:forEach items="${codelist}" var="item" varStatus="loop">
					<option value="${item.CODE_CD}">${item.CODE_NM}</option>
				</c:forEach>
				</select>
  			</td>
  		</tr>
   		<tr>
   			<th>학습형태명</th>
  			<td>
	   			<input type="text" id="NAME" name="NAME" value="" size="20"  maxlength="25" title="카테고리명" style="width:28%;background:#FFECEC;"/>
  			</td>
  		</tr>
		<tr>
  			<th>상태</th>
  			<td>
				<select id="ISUSE" name="ISUSE">
  					<option value="Y">활성</option>
  					<option value="N">비활성</option>
				</select>
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
	$("#CODE").focus();
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/form/list.do' />");
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
	if($.trim($("#LEC_DIV option:selected").val())==""){
		alert("분류를 선택해주세요");
		$("#LEC_DIV").focus();
		return;
	}
	if($.trim($("#NAME").val())==""){
		alert("코드명을 입력해주세요");
		$("#NAME").focus();
		return;
	}

	var checkparam = "CODE=" + $.trim($("#CODE").val());
	$.ajax({
		type: "GET",
		url : '<c:url value="/form/codeCheck.do"/>?' + checkparam,
		dataType: "text",
		async : false,
		success: function(RES) {
			if($.trim(RES)=="Y"){
				if(confirm("학습형태를 등록하시겠습니까?")){
					$("#frm").attr("action","<c:url value='/form/save.do' />");
					$("#frm").submit();
				}
			}else{
				alert("학습형태 코드중 동일한 코드가 이미 등록되어 있습니다");
				return;
			}
		},error: function(){
			alert("코드중복체크 실패");
			return;
		}
	});
}
</script>
</body>
</html>