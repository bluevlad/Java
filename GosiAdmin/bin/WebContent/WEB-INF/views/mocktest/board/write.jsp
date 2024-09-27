<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>

<head>
<meta name="decorator" content="index">

<script type="text/javascript" src="<c:url value="/resources/libs/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
	/*  editor = CKEDITOR.replace('c_content', {
	 	language : 'ko',
	 	width : '96%',
	 	height : 400,
	 	filebrowserUploadUrl : '<c:url value="/file/editorImgUpload"/>'
	 });  */
});

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

function paramCheck() {
	var codeStr = "";
	$("#codeList input[type=checkbox]:checked").each(function() {
		checkboxValue = $(this).attr('name');
		codeStr+= checkboxValue+"/";
	});

	var chk = $("#isOpen").is(":checked");
	if(chk) $("#isOpen").val("0"); //비공개
	else    $("#isOpen").val("1"); //공개



	if(codeStr==""){
		alert("구분을 1개 이상 체크해 주세요.");
	}
	else if($("#title").val() == "") {
		alert("제목을 등록해 주세요.");
		$("#title").focus();
	} else if(editor.getData() == ""){
		alert("내용을 등록해 주세요.");
		$("#c_content").focus();
	} else {
		alert(chk);
		$("#frm").attr("action", "<c:url value='/offExamReg/offExamList.do' />");
		$("#frm").submit();
	}
}
</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="<c:url value="/board/${boardId}"/>">
<input type="hidden" name="codeStr" id="codeStr" value=""/>
<input type="hidden" name="writer" value="${params.USERNAME}"/>
<input type="hidden" name="boardType" value="${params.boardType}"/>
	<div id="stitle">
		<ul id="stitle_l"><img src="<c:url value="/resources/img/common/icon_stitle01_02.png"/>" align="absmiddle" />
			게시판관리
		</ul>
		<ul id="stitle_r">* Home > 게시판관리 > 모의고사 공고 등록</ul>
    </div>
	<c:if test="${boardId == 46 }">
		<p>※ 본 화면은 가로 718px에 최적화 되어 있습니다..</p>
	</c:if>
    <div class="form_table" style="margin-top:30px; float:left; width:100%; margin-left:0px;">
    	<table class="tbl_type" border="1" cellspacing="0" summary="받은쪽지" style="min-width:800px;">
    	<caption></caption>
    	<colgroup>
    		<col width="15%">
    		<col width="">
    	</colgroup>
    	<thead>
    	<tr>
    		<th scope="col">구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
	   				<c:forEach items="${gbList}"  var="data" varStatus="status" >
	   					<input name="${data.CODE}"  class="i_check" value="0" type="checkbox" value="${data.CODE}" ><label for="a1">${data.NAME }</label>
					</c:forEach>
				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">공지 구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="isNotice"  class="i_check" value="1" type="radio" ><label for="a2">TOP</label>&nbsp;&nbsp;
	   				<input name="isNotice"  class="i_check" value="0" type="radio"  checked><label for="a3">일반</label>
				</div>
   			</td>
   		</tr>
   		</thead>
   		<tbody>
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="title" name="title" class="i_text" title="레이블 텍스트" value="" style="width:68%;" />&nbsp;&nbsp;
   					<input name="isOpen" id="isOpen" class="i_check" value="1" type="checkbox" ><label for="a4">비공개</label>
   				</div>
   			</td>
   		</tr>
		<tr>
			<th height="150">내용</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="c_content" name="content" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;"></textarea>
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
				<input title="레이블 텍스트" type="file" name="upFile" />
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
			</div>
			</td>
		</tr>
		</tbody>
		</table>
	</div>
	<!--//테이블-->

    <!--버튼-->
    <div style="float:left; width:100%; text-align:center; margin-top:16px;">
    	<span class="btn_pack medium"><button type="button" onclick="javascript:paramCheck()">등록</button></span>
    	<a href="<c:url value="/support/board/${boardId}?page=${page }"/>"><span class="btn_pack medium"><button type="button">목록</button></span></a>
    	<%-- <c:choose>
    		<c:when test="${boardId == 46 }">
    			<a href="<c:url value="/homepage/html"/>"><span class="btn_pack medium"><button type="button">목록</button></span></a>
    		</c:when>
    		<c:otherwise>
    			<a href="<c:url value="/support/board/${boardId}?page=${page }"/>"><span class="btn_pack medium"><button type="button">목록</button></span></a>
    		</c:otherwise>
    	</c:choose> --%>
    </div>
    <!--//버튼-->

    <!--빈공간-->
    <div style="float:left; width:100%; height:30px;"></div>
    <!--//빈공간-->

</form>
</div>
<!--//content -->
