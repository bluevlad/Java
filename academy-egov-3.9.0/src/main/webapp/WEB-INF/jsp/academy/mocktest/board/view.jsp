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
	editor = CKEDITOR.replace('c_content', {
	 	language : 'ko',
	 	width : '530px',
	 	height : '255px',
	 	filebrowserUploadUrl : '<c:url value="/file/editorImgUpload"/>',
	 	
	 });
});

function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"upFile\" size=\"30\" class=\"in\" onchange=\"fileAdd()\" >");
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
	if($("#title").val() == "") {
		alert("제목을 등록해 주세요.");
		$("#title").focus();
	} else if(editor.getData() == ""){
		alert("내용을 등록해 주세요.");
		$("#c_content").focus();
	} else {
		document.frm.submit();
	}
}
</script>
</head>


<div id="content">
	<h3>
		<c:if test="${path == 'qna' }"><img src="<c:url value="/resources/img/common/h3_0203.gif"/>" alt="교육문의" /></c:if>
		<c:if test="${path == 'bbs' }"><img src="<c:url value="/resources/img/common/h3_0204.gif"/>" alt="자유게시판" /></c:if>
	</h3>
	<p id="location">Home &gt; 교육지원센터 &gt; <strong>교육문의</strong></p>
	<!-- 본문 시작 -->
	<p class="t20">
		<c:if test="${path == 'qna' }"><img src="<c:url value="/resources/img/support/txt_qna.gif"/>" alt="글을 작성하기 전 분야별 FAQ를 이용하시면 빠르고 쉽게 문제를 해결할 수 있습니다." /></c:if>
		<c:if test="${path == 'bbs' }"><img src="<c:url value="/resources/img/support/txt_free.gif"/>" alt="글을 작성하기 전 분야별 FAQ를 이용하시면 빠르고 쉽게 문제를 해결할 수 있습니다." /></c:if>
	</p>
	
	
	<form name="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="<c:url value="/support/${path}"/>">
	<input type="hidden" name="_method" value="${(edit == true) ? 'put' : 'post'}" />
	<input type="hidden" name="accountId" value="${userAccount.accountId }"/>
	<input type="hidden" name="boardId" value="${boardId}"/>
	<input type="hidden" name="boardContentId" value="${boardContent.boardContentId}"/>
	<table summary="교육문의 글쓰기 폼" cellspacing="0" cellpadding="0" border="0" class="viewType02">
		<caption>교육문의 글쓰기 폼</caption>
		<colgroup>
			<col width="20%" />
			<col width="80%" />
		</colgroup>
		<tbody>
			<tr>
				<th><img src="<c:url value="/resources/img/support/l_tit.gif"/>" alt="제  목" /></th>
				<td><input id="title" name="title" type="text" class="in" size="50" value="${boardContent.title }" /> <input  type="checkbox" name="isOpen" id="c1" value="0" <c:if test="${boardContent.isOpen == '0' }"> checked="checked"</c:if> /> <label for="c1" class="fs90">비공개</label></td>
			</tr>
			<tr>
				<th><img src="<c:url value="/resources/img/support/l_cont.gif"/>" alt="내   용" /></th>
				<td><textarea id="c_content" name="content" class="in" style="width:530px;height:255px;">${boardContent.content }</textarea></td>
			</tr>
			<tr>
				<th><img src="<c:url value="/resources/img/support/l_file.gif"/>" alt="첨부파일" /></th>
				<td>
					<c:forEach items="${boardContent.attachFiles }" var="attachFile">
						<span style="cursor:pointer" id="file_${attachFile.attachFileId }" onclick="deleteAttachFile('${attachFile.attachFileId}')">${attachFile.fileName } - 삭제</span><br/>
					</c:forEach> <br/>
					<div id="fileControl">
						<input type="file" size="30" class="in" name="upFile" onchange="fileAdd();" />
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div align="center" class="t10">
		<a href="#"><img src="<c:url value="/resources/img/support/btn_ok.gif"/>" alt="확인" onclick="javascript:paramCheck()" /></a>
		<c:if test="${empty page }"><a href="<c:url value="/support/${path }?page=1"/>"><img src="<c:url value="/resources/img/support/btn_cancel.gif"/>" alt="취소"  /></a></c:if>
		<c:if test="${not empty page }"><a href="<c:url value="/support/${path }?page=${page }"/>"><img src="<c:url value="/resources/img/support/btn_cancel.gif"/>" alt="취소"  /></a></c:if>
	</div>
	
	</form>
</div>

