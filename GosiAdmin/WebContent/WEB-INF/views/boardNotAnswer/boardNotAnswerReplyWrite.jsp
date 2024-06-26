<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

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
	 editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();  
});

/* function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"upFile\" class=\"multipartFile\" onchange=\"fileAdd()\" >");
} */

/* function deleteAttachFile(id){
	$.ajax({
		type: "GET", 
		url: '<c:url value="/board/deleteFile/"/>'+id,
		dataType: "json",		
		async : false,
		success: function(json) {
			$("#file_"+id).remove();
		}
	});
} */

function paramCheck() {
	//처리상태 값: CS,운영 현재 게시판 처리상태만 변경 답변완료이면 답변까지 추가됨
	var boardReply 	= $(':radio[name="BOARD_REPLY"]:checked').val();
	//처리상태 답변완료 체크시에만 등록값을 체크
	if(boardReply=='Y'){
		var contentStr = editor.outputBodyHTML();
		
		if($("#S_TYPE").val() == "P") {
	    	alert("등록중입니다. 잠시만 기다려주세요...");
	    } else{
			$("#S_TYPE").val("P");
	    }
		
		if($("#SUBJECT").val() == "") {
			alert("제목을 등록해 주세요.");
			$("#title").focus();
			return;
		} else if(contentStr == ""){
			alert("내용을 등록해 주세요.");
			$("#CONTENT").focus();
			return;
		}
	}
	//먼저 처리상태 변경 후 리턴값으로 체크
	var params = "BOARDVIEW_SEQ="+$("#BOARDVIEW_SEQ").val()+'&BOARDVIEW_MNG_SEQ='+$("#BOARDVIEW_MNG_SEQ").val()+'&BOARD_REPLY='+boardReply;
	$.ajax({
		type: "POST", 
		url: '<c:url value="/boardNotAnswer/updateBoardReply.do"/>?'+params,
		dataType: "json",		
		async : false,
		success: function(result) {
			var result = result.resultmsg;
			if(result=="Y"){
				$("#frm").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerReplyInsertProcess.do' />");
				$("#frm").submit();
			}else{
				var msg = "";
				if(result=="C") msg="CS";
				else if(result=="A") msg="운영";
				else msg = result;
				alert("처라상태가 "+msg+"로 처리되었습니다.");
				fn_boardList();
			}
		}
	});
}
function fn_boardList(){
	$("#frm").attr("action", "<c:url value='/boardNotAnswer/boardNotAnswerList.do' />");
	$("#frm").submit();
}

</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="codeStr" name="codeStr"  value=""/>

<input type="hidden" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" name="REG_ID" value="${params.USERID}"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="NOTICE_TOP_YN" name="NOTICE_TOP_YN" value="N"/>

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="BOARDVIEW_MNG_SEQ" name="BOARDVIEW_MNG_SEQ" value="${params.BOARDVIEW_MNG_SEQ}">
<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="S_TYPE" name="S_TYPE" value="" />
	
	<h2>● 게시판관리  > <strong>미답변 게시판 답변 등록</strong></h2>
    	<table class="table01">
    	<caption></caption>
    	<colgroup>
    		<col width="15%">
    		<col width="">
    	</colgroup>
   		<tr>
    		<th scope="col">공개 여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio" ><label for="a2">공개</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio"  checked><label for="a3">비공개</label>
				</div>
   			</td>
   		</tr>	
   		<tr>
    		<th scope="col">질문구분</th>
   			<td scope="col" style="text-align:left;">
				<label for="BOARD_TYPE"></label>
					<select  id="BOARD_TYPE" name="BOARD_TYPE">
					<option value="">-분류선택-</option>
					<option value="A">강좌개강일</option>
					<option value="B">시작일변경</option>
					<option value="C">결제</option>
					<option value="D">환불문의</option>
					<option value="E">재수강,연장</option>
					<option value="F">PMP</option>
					<option value="G">동영상오류</option>
					<option value="H">일시중지</option>
					<option value="I">불만/건의</option>
					<option value="J">교재 및 자료</option>
					<option value="K">배송문의</option>
					<option value="L">회원정보</option>
					<option value="Z">기타</option>
				</select>
   			</td>
   		</tr>	
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="RE:${detailView.SUBJECT}" style="width:96%;" />&nbsp;&nbsp;
   					<!-- <input name="isOpenCk" id="a2" class="i_check" value="1" type="checkbox"><label for="a2">비공개</label> -->
   				</div>
   			</td>
   		</tr>
   		<tr>
			<th height="150">내용</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="CONTENT" name="CONTENT" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
						<p></p>
						------------  원본 내용 ------------------<br>
						<c:out value="${detailView.CONTENT}" escapeXml="false"/>
					</textarea>
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
				<%-- <c:if test="${detailView.ATTACHFILEID ne null }">
					<span style="cursor:pointer" id="file_${detailView.ATTACHFILEID }" onclick="deleteAttachFile('${detailView.ATTACHFILEID}')">${detailView.FILENAME} - 삭제</span><br/>
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
				</c:if>
				<c:if test="${detailView.ATTACHFILEID eq null }">
					<input title="레이블 텍스트" type="file" name="uploadFile"  />
				</c:if> --%>
				<input title="레이블 텍스트" type="file" name="uploadFile"  />
			</div>
			</td>
		</tr>
<%-- 	 	<c:if test="${detailView.PARENT_BOARD_SEQ > 0}"> --%>
			<tr>
	    		<th scope="col">처리여부</th>
	   			<td scope="col" style="text-align:left;">
	   				<div class="item">
		   				<input name="BOARD_REPLY"  class="i_check" value="C" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'C'}">checked</c:if>><label for="a2">CS</label>&nbsp;&nbsp;
		   				<input name="BOARD_REPLY"  class="i_check" value="A" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'A'}">checked</c:if>><label for="a3">운영</label>&nbsp;&nbsp;
		   				<input name="BOARD_REPLY"  class="i_check" value="Y" type="radio" <c:if test="${detailView.BOARD_REPLY eq 'Y' || empty detailView.BOARD_REPLY || detailView.BOARD_REPLY eq ''}">checked</c:if>><label for="a3">답변완료</label>
					</div>
	   			</td>
	   		</tr>	
<%--    		</c:if> --%>
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
<c:import url="/board/board_reply.do" />