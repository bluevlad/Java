<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %><head>
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
	
	contntEvent();
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
	if($.trim($("#TITLE").val())==""){
		alert("이벤트명을 입력해주세요");
		$("#TITLE").focus();
		return;
	}
	var contentStr = editor.outputBodyHTML();
	//alert("contentStr : "+ contentStr);
	//alert($("#CONTENTS_TEXT").val());
	
	if(confirm("이벤트를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/LecEventMng/eventUpdateProcess.do' />");
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

function deleteImage(param){
	if(param == 'contentsImg'){
		//alert($("#bannerImg").html());
		$("#contentsImg").html('');
		$("#CONTENTS_IMG_DEL").val('Y');
	}else if(param == 'listThumImg'){
		$("#listThumImg").html('');
		$("#LIST_THUMBNAIL_DEL").val('Y');		
	}
	alert('수정버튼을 눌러야 적용이 됩니다.');
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

<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${ params.EVENT_NO }"/>

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
        
    	<table class="table01">
		      <tr>
		        <th>직렬</th>
		        <td class="tdLeft">${ view.CATEGORY_NAME }</td>
		      </tr>
		      <tr>
		        <th>적용여부</th>
		        <td class="tdLeft">
					<input type="radio" id="OPEN_YN_N" name="OPEN_YN" value="N"
						<c:if test="${ view.OPEN_YN eq 'N' }">checked="checked"</c:if>/>미적용
					<input type="radio" id="OPEN_YN_Y" name="OPEN_YN" value="Y"
						<c:if test="${ view.OPEN_YN eq 'Y' }">checked="checked"</c:if>/>적용
		        </td>
		      </tr>
		      <tr>
		        <th>시작일</th>
		        <td class="tdLeft"><input type="text" id="START_DATE" name="START_DATE" size="10" readonly="readonly" value="${ view.START_DATE }"></td>
		      </tr>
		      <tr>
		        <th>종료일</th>
		        <td class="tdLeft"><input type="text" id="END_DATE" name="END_DATE" size="10" readonly="readonly" value="${ view.END_DATE }"></td>
		      </tr>
		      <tr>
		        <th>제목</th>
		        <td class="tdLeft">
					<input type="text" id="TITLE" name="TITLE" value="${ view.TITLE }" style="width: 70%">
		        </td>
		      </tr>
	      </table>
	      <br/>
	      <table class="table01">
		      <tr>
		        <td class="tdLeft">
					<div><textarea id="CONTENTS_TEXT" name="CONTENTS_TEXT" title="레이블 텍스트" style="width:96%;">
						${ view.CONTENTS_TEXT }
					</textarea></div>
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
		            <input type="hidden" name="LIST_THUMBNAIL_BEFORE" id="LIST_THUMBNAIL_BEFORE" value="${view.LIST_THUMBNAIL}">
		            <input type="hidden" name="LIST_THUMBNAIL_DEL" id="LIST_THUMBNAIL_DEL" value="">
		            <ul class="bannerFile" id="listThumImg">
						<c:if test="${!empty view.LIST_THUMBNAIL}" >
							<li>
								${fn:substring(view.LIST_THUMBNAIL, fn:indexOf(view.LIST_THUMBNAIL, '/')+1, fn:length(view.LIST_THUMBNAIL))}
								<a href="javascript:deleteImage('listThumImg');"> X </a>
							</li>
							<li><img src="<c:url value="/imgFileView.do?path=${view.LIST_THUMBNAIL}"/>" width="150" height="50"></li>
						</c:if> 
		            </ul>		        	
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
