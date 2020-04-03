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
	 editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
	 editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
	 editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	 editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();  
});
//첨부파일
function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"uploadFile\"   >");
}
//섬네일 첨부파일
function fileAdd2() {
	$("#fileControlThumb").append("<br/><input type=\"file\" name=\"uploadFileThumbNail\"   >");
}

function deleteAttachFile(file_path ,board_seq , file_type, file_no){

	if(file_type == 'default')	{
		$.ajax({
			type: "GET", 
			url: '<c:url value="boardDeleteFile.do/?FILE_PATH="/>'+file_path+'&BOARD_SEQ='+board_seq+'&FILETYPE='+file_type,
			dataType: "json",		
			async : false,
			success: function(json) {
				alert('삭제성공');
				$("#file_"+file_no).remove();
				//fileAdd();
			}
		});
	}
	if(file_type == 'thumb')	{
		$.ajax({
			type: "GET", 
			url: '<c:url value="boardDeleteFile.do/?FILE_PATH="/>'+file_path+'&BOARD_SEQ='+board_seq+'&FILETYPE='+file_type,
			dataType: "json",		
			async : false,
			success: function(json) {
				alert('삭제성공');
				$("#fileThumbNail_"+board_seq).remove();
				fileAdd2();
			}
		});
	}
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

	if($("input:checkbox[id='CHECK_ISSUE']").is(":checked")){
		$("#ISSUE").val("Y");
	}else{
		$("#ISSUE").val("N");
	}
	
	if($("input:checkbox[id='CHECK_RECOMMEND']").is(":checked")){
		$("#RECOMMEND").val("Y");
	}else{
		$("#RECOMMEND").val("N");
	}
	
//	var board_gubun = $("#board_gubun").val();
//	if(board_gubun=="ORIGIN"){
//		if(codeStr==""){
//			alert("구분을 1개 이상 체크해 주세요.");
//		}
//	}	
	if(contentStr == "") {
		alert("제목을 등록해 주세요.");
		$("#title").focus();
	} else if($("#CONTENT").val() == ""){
		alert("내용을 등록해 주세요.");
		$("#CONTENT").focus();
	} else {
		$bb  = $("#codeStr").val(codeStr);
		if(confirm("등록하시겠습니까?")){	
			$("#frm").attr("action", "<c:url value='/teacher/boardUpdateProcess.do' />");
			$("#frm").submit();
		} 
	}
}
function fn_boardList(){
	<c:if test='${params.ANSW == "N"}'>
	$("#frm").attr("action", "<c:url value='/teacher/boardNotList.do' />");
	</c:if>
	<c:if test='${params.ANSW != "N"}'>
	$("#frm").attr("action", "<c:url value='/teacher/boardList.do' />");
	</c:if>
	$("#frm").submit();
}

function addAttchFile(){
	var divLength = $("#fileControl div").length;
	var lastItemNo;
	var nNum;
	//alert("divLength > " + divLength);
	if(divLength > 0){
		lastItemNo = $("#fileControl div:last-child").attr('id').replace("row",""); // $("#fileControl").replace("ATTACH_FILE", "");
	    nNum = parseInt(lastItemNo) + 1;
	} else {
		nNum = 1;
	}
	//alert(lastItemNo + " : " + nNum);	
    var newitem = '<div id="row' + nNum + '"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_' + nNum + '" size="50">';
    newitem += ' <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow(\'' + nNum + '\');"></div>';
    $("#fileControl").append(newitem);
	/*
    if(nNum > 10){
        alert("최대 10개까지 가능합니다.");
        return;
    }*/
}

function delRow(id, param){
	if(param == 'text') $("#rowText" + id).remove();
	else $("#row" + id).remove();
}

</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

<input type="hidden" id="codeStr" name="codeStr"  value=""/>
<input type="hidden" name="board_gubun" id="board_gubun" value="${params.board_gubun}"/>
<input type="hidden" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" name="REG_ID" value="${params.REG_ID}"/>

<input type="hidden" id="ANSW" name="ANSW" value="${params.ANSW}"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="ISSUE" name="ISSUE" value=""/>
<input type="hidden" id="RECOMMEND" name="RECOMMEND" value=""/>

<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}수정</strong></h2>
    	<table class="table01" >
    	<c:if test="${params.board_gubun == 'ORIGIN'}">
		<input type="hidden" name="NOTICE_TOP_YN"  value="${detailView.NOTICE_TOP_YN}"/>
   		</c:if>
   		<tr>
    		<th scope="col">공개 여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio" <c:if test="${detailView.OPEN_YN == 'Y' }">checked="checked"</c:if>><label for="a2">공개</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio"  <c:if test="${detailView.OPEN_YN == 'N' }">checked="checked"</c:if>><label for="a3">비공개</label>
				</div>
   			</td>
   		</tr>		
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="<c:out value="${detailView.SUBJECT}"/>" style="width:68%;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
		<tr>
			<th height="150">내용</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="CONTENT" name="CONTENT" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
						<c:out value="${detailView.CONTENT}"/>
					</textarea>
				</div>
			</td>
		</tr>
		<c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1}">
		<tr>
			<th>섬네일 첨부파일</th>
			<td style="text-align:left;">
			<div class="item" id="fileControlThumb">
				<c:if test="${detailView.THUMBNAIL_FILE_NAME ne null }">
					<span style="cursor:pointer" id="fileThumbNail_${detailView.BOARD_SEQ }" onclick="deleteAttachFile('${detailView.THUMBNAIL_FILE_PATH}','${detailView.BOARD_SEQ }','thumb', '${detailView.BOARD_SEQ }')">${detailView.THUMBNAIL_FILE_NAME} - 삭제</span><br/>
				</c:if>
				<c:if test="${detailView.THUMBNAIL_FILE_NAME eq null }">
					<input title="레이블 텍스트" type="file" name="uploadFileThumbNail"  />
				</c:if>
			</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<th>첨부파일</th>
			<td style="text-align:left;">
			<%-- <c:forEach items="${boardContent.attachFiles }" var="attachFile">
				<span style="cursor:pointer" id="file_${attachFile.attachFileId }" onclick="deleteAttachFile('${attachFile.attachFileId}')">${attachFile.fileName } - 삭제</span><br/>
			</c:forEach> <br/> --%>
			<div class="item" id="fileControl">
				<c:if test="${boardAttachFile.size() > 0 }">
					<c:forEach items="${boardAttachFile}" var="data" varStatus="status">
						<span style="cursor:pointer" id="file_${data.FILE_NO }" onclick="deleteAttachFile('${data.FILE_PATH}','${data.BOARD_SEQ }' , 'default', '${data.FILE_NO }')">${data.FILE_NAME} - 삭제</span><br/>
					</c:forEach>
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
				</c:if>
				
				<input type="button" id="addButton" onclick="javascript:addAttchFile();" value="파일추가">
				<div id="row1"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_1" size="50">
				<input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow('1');"></div>
				
			</div>
			</td>
		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">등록</a></li>
	      <li><a href="javascript:fn_boardList();">목록</a></li>
	  </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
