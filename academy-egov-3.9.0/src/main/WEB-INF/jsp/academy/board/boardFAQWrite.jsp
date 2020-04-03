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
	/*  editor = CKEDITOR.replace('c_content', {
	 	language : 'ko',
	 	width : '96%',
	 	height : 400,
	 	filebrowserUploadUrl : '<c:url value="/file/editorImgUpload"/>'
	 });  */
	 
	 editor = new cheditor();              // 에디터 개체를 생성합니다.
	 editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
	 editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
	 editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	 editor.inputForm = 'ANSWER';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();  
});

function doSubmit (theform)
{
    // ---------------------------------------------------------------------------------
    // myeditor라는 이름은 현재 데모에서 만들어진 에디터 개체 이름입니다.
    //
    // myeditor.outputBodyHTML() 메서드를 호출하면 에디터에서 작성한 글 내용이
    // myeditor.inputForm 설정 옵션에 지정한 'fm_post' 폼 값에 자동으로 입력됩니다.
    //
    // outputBodyHTML:  BODY 태그 안쪽 내용을 가져옵니다.
    // outputHTML:      HTML 문서 모두를 가져옵니다.
    // outputBodyText:  BODY 태그 안쪽의 HTML 태그를 제외한 텍스트만을 가져옵니다.
    // inputLength:     입력한 텍스트 문자 수를 리턴합니다.
    // contentsLength:  BODY 태그 안쪽의 HTML 태그를 포함한 모든 문자 수를 리턴합니다.
    // contentsLengthAll: HTML 문서의 모든 문자 수를 리턴합니다.

    alert(editor.outputBodyHTML());
    return false;
}

function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"upFile\" class=\"multipartFile\" onchange=\"fileAdd()\" >");
}

function deleteAttachFile(id){
	$.ajax({
		type: "GET", 
		url: '<c:url value="/board/deleteFile/"/>'+id,
		dataType: "json",		
		async : false,
		success: function(json) {
			$("#file_"+id).remove();
		}
	});
}

function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

function paramCheck() {
	
	var contentStr = editor.outputBodyHTML();	
	var codeStr = "";
	$("input[name=CATEGORY_CODE]:checked").each(function() {
		checkboxValue = $(this).val();
		codeStr+= checkboxValue+"/";
	});
	
	if(codeStr==""){
		alert("구분을 1개 이상 체크해 주세요.");
	}
	else if($("#SUBJECT").val() == "") {
		alert("제목을 등록해 주세요.");
		$("#SUBJECT").focus();
	} else if(contentStr == ""){
		alert("내용을 등록해 주세요.");
		$("#ANSWER").focus();
	} else {
    	var temp = $("#SUBJECT").val();
    	$("#SUBJECT").val(temp.replace(/・/gi, "."));
		$bb  = $("#codeStr").val(codeStr);
		$("#frm").attr("action", "<c:url value='/board/boardFAQInsertProcess.pop' />");
		$("#frm").submit();
	}
}
function fn_boardList(){
	$("#frm").attr("action", "<c:url value='/board/boardFAQList.pop' />");
	$("#frm").submit();
}

</script>
</head>


<!--content -->
<div id="content_pop">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="codeStr" name="codeStr"  value=""/>
<input type="hidden" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" name="REG_ID" value="${params.REG_ID}"/>
<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>

<!-- <input type="hidden" id="isOpen" name="isOpen" value=""/> -->

<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<%-- 	<div id="stitle">
		<ul id="stitle_l"><img src="<c:url value="/resources/img/common/icon_stitle01_02.png"/>" align="absmiddle" />
			${params.BOARDTITLE}
		</ul>
		<ul id="stitle_r">* Home > 게시판관리 > ${params.BOARDTITLE}</ul>
    </div> --%>
    	<h2>● 생성게시판  > <strong>${params.BOARD_MNG_NAME} 등록</strong></h2>
    	<table class="table01">
    	<tr>
    		<th scope="col">구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
   					<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
	   				<c:forEach items="${gbList}"  var="data" varStatus="status" >
	   					<input name="CATEGORY_CODE"  class="i_check" type="checkbox" value="${data.CODE}" ><label for="a1">${data.NAME }</label>
					</c:forEach>
				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">상단노출 구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="Y" type="radio" ><label for="a2">TOP</label>&nbsp;&nbsp;
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="N" type="radio"  checked><label for="a3">일반</label>
				</div>
   			</td>
   		</tr>	
   		<tr>
    		<th scope="col">공개 여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio" checked><label for="a2">공개</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio"  ><label for="a3">비공개</label>
				</div>
   			</td>
   		</tr>	
   		<tr>
   			<th scope="col">질문</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="" style="width:90%;" />&nbsp;&nbsp;
   					<!-- <input name="isOpen" id="isOpenCk" class="i_check" value="1" type="checkbox" ><label for="a4">비공개</label> -->
   				</div>
   			</td>
   		</tr>
		<tr>
			<th height="150">답변</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="ANSWER" name="ANSWER" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td style="text-align:left;">
			<%-- <c:forEach items="${boardContent.attachFiles }" var="attachFile">
				<span style="cursor:pointer" id="file_${attachFile.attachFileId }" onclick="deleteAttachFile('${attachFile.attachFileId}')">${attachFile.fileName } - 삭제</span><br/>
			</c:forEach> <br/> --%>
			<div class="item" id="fileControl">
				<input title="레이블 텍스트" type="file" name="uploadFile" />
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
			</div>
			</td>
		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <!-- <div style="float:left; width:100%; text-align:center; margin-top:16px;">
    	<span class="btn_pack medium"><button type="button" onclick="javascript:paramCheck()">등록</button></span>
    	<span class="btn_pack medium"><button type="button" onclick="javascript:fn_boardList()">목록</button></span>
    </div> -->
     <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">등록</a></li>
	      <li><a href="javascript:fn_boardList();">목록</a></li>
	  </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
