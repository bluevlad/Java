<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
	editor = new cheditor();              // 에디터 개체를 생성합니다.
	editor.config.editorHeight = '250px';     // 에디터 세로폭입니다.
	editor.config.editorWidth = '100%';        // 에디터 가로폭입니다.
	editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	editor.inputForm = 'CONTENTS_TEXT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	editor.run(); 
	
	setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
	initDatePicker1("START_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("END_DATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//숫자만 입력
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

//등록
function fn_Submit(){
	if($("input[name='CATEGORY_CODE']:checked").size()  < 1){
		alert("직렬을 하나이상 입력해주세요");
		$("input[name='CATEGORY_CODE']")[0].focus();
		return;
	}else if($.trim($("#TITLE").val())==""){
		alert("이벤트명을 입력해주세요");
		$("#TITLE").focus();
		return;
	}
	var contentStr = editor.outputBodyHTML();
	//alert("contentStr : "+ contentStr);
	//alert($("#CONTENTS_TEXT").val());
	
	if(confirm("이벤트를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/LecEventMng/eventInsertProcess.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action", "<c:url value='/LecEventMng/eventMgtList.do' />");
	$("#frm").submit();
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
        
    	<table class="table01">
		      <tr>
		        <th width="150">직렬</th>
		        <td class="tdLeft">
					<c:forEach items="${kindlist}" var="item" varStatus="loop">
						<input type="checkbox" name="CATEGORY_CODE" value="${item.CODE}"/>
						${item.NAME} &nbsp;&nbsp;
					</c:forEach>
					<input id="allCheck" value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')" />전체
		        </td>
		      </tr>
		      <tr>
		        <th>적용여부</th>
		        <td class="tdLeft">
					<input type="radio" id="OPEN_YN_N" name="OPEN_YN" value="N" checked/>미적용
					<input type="radio" id="OPEN_YN_Y" name="OPEN_YN" value="Y"/>적용
		        </td>
		      </tr>
		      <tr>
		        <th>시작일</th>
		        <td class="tdLeft"><input type="text" id="START_DATE" name="START_DATE" size="10"></td>
		      </tr>
		      <tr>
		        <th>종료일</th>
		        <td class="tdLeft"><input type="text" id="END_DATE" name="END_DATE" size="10"></td>
		      </tr>
		      <tr>
		        <th>제목</th>
		        <td class="tdLeft"><input type="text" id="TITLE" name="TITLE" style="width:95%;"></td>
		      </tr>
	      </table>
	      <br/>
	      <table class="table01">
		      <tr>
		        <td class="tdLeft">
					<div><textarea id="CONTENTS_TEXT" name="CONTENTS_TEXT" title="레이블 텍스트" style="width:95%;"></textarea></div>		        
				</td>
		      </tr>
		  </table>
		  <br/>
	      ● 리스트 썸네일 이미지 등록
	      <table class="table01">
		      <tr>
		        <th width="10%">리스트 썸네일</th>
		        <td class="tdLeft">
		        	<input type="file" name="LIST_THUMBNAIL" id="LIST_THUMBNAIL" size="50">
				</td>
		      </tr>
		  </table>
	    <!--버튼-->
	    <ul class="boardBtns">
	   	  <li><a href="javascript:fn_Submit();">확인</a></li>
	      <li><a href="javascript:fn_List();">목록</a></li>
	    </ul>
	    <!--//버튼--> 
</form>
</div>
<!--//content --> 
