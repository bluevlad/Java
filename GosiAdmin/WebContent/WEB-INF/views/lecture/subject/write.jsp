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
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<h2>● 강의 관리 > <strong>과목관리</strong></h2>	
   	<table class="table01">
   		<tr>
   			<th>과목명</th>
  			<td>
	   			<input type="text" id="SUBJECT_NM" name="SUBJECT_NM" value="" size="20"  maxlength="150" title="과목명" style="width:28%;background:#FFECEC;"/>
  			</td>
  		</tr>
		<tr>
   			<th>직급</th>
  			<td>
  				<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
   					<input type="checkbox" name="CATEGORY_CODE" VALUE="${item.CODE}"/>${item.NAME} &nbsp;
   				</c:forEach>
  			</td>
  		</tr>
		<tr>
  			<th>상태</th>
  			<td>
				<select id="ISUSE" name="ISUSE">
  					<option value="Y">활성</option>
  					<option value="N">비활성</option>
				</select>
				<c:choose>
					<c:when test="${params.MENUTYPE eq 'OM_ROOT'}">
						<c:set var="onoff_page" value="온라인"/>
					</c:when>
					<c:when test="${params.MENUTYPE eq 'FM_ROOT'}">
						<c:set var="onoff_page" value="오프라인"/>
					</c:when>
				</c:choose>
				<span style="color:red">※비활성으로 하면 온라인/오프라인 전체가 미노출 됩니다.상단 직급으로 노출/비노출 할수 있습니다.현재는 ${onoff_page} 페이지입니다. </span>
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
	$("#SUBJECT_NM").focus();
});

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/subject/list.do' />");
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
	if($.trim($("#SUBJECT_NM").val())==""){
		alert("과목명을 입력해주세요");
		$("#SUBJECT_NM").focus();
		return;
	}
	
	var checkparam = "SUBJECT_NM=" + escape(encodeURIComponent($.trim($("#SUBJECT_NM").val())));
	$.ajax({
		type: "GET", 
		url : '<c:url value="/subject/codeCheck.do"/>?' + checkparam,
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="Y"){
				if(confirm("과목을 등록하시겠습니까?")){
					$("#frm").attr("action","<c:url value='/subject/save.do' />");
					$("#frm").submit();
				}				
			}else{
				alert("입력하신 과목은 이미 등록된 과목입니다");
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