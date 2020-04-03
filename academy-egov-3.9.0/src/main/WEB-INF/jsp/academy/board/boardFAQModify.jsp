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
	 editor.inputForm = 'ANSWER';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();  
});

function fileAdd() {
	$("#fileControl").append("<br/><input type=\"file\" name=\"uploadFile\"   >");
}

function deleteAttachFile(file_path ,board_seq){
	$.ajax({
		type: "GET", 
		url: '<c:url value="boardDeleteFile.do"/>?FILE_PATH='+file_path+'&BOARD_SEQ='+board_seq+'&FILETYPE=default',
		dataType: "json",		
		async : false,
		success: function(json) {
			alert('파일이 삭제되었습니다.');
			$("#file_"+board_seq).remove();
			fileAdd();
		},
		error: function(){
			alert('파일이 삭제되지 않았습니다.');
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
	else if(contentStr == "") {
		alert("제목을 등록해 주세요.");
		$("#title").focus();
	} else if($("#CONTENT").val() == ""){
		alert("내용을 등록해 주세요.");
		$("#CONTENT").focus();
	} else {
		$bb  = $("#codeStr").val(codeStr);
		$("#frm").attr("action", "<c:url value='/board/boardFAQUpdateProcess.pop' />");
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
<input type="hidden" name="board_gubun" id="board_gubun" value="${params.board_gubun}"/>
<input type="hidden" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" name="REG_ID" value="${params.REG_ID}"/>

<!-- <input type="hidden" id="ISTOP" name="ISTOP" value=""/>
<input type="hidden" id="ISOPEN" name="ISOPEN" value=""/> -->
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

<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

	<h2>● 생성게시판  > <strong>${params.BOARD_MNG_NAME} 수정</strong></h2>
    	<table class="table01" >
    	<tr>
    		<th scope="col">구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
					<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
					<c:forEach items="${gbList}"  var="data" varStatus="status" >
						 <c:set var="vChecked">
							  <c:forEach var="vList2" items="${boardCodeList}" varStatus="vStatus2">
								   <c:choose>
								    <c:when test="${data.CODE == vList2.CATEGORY_CODE}">checked="checked"</c:when>
								    <c:otherwise></c:otherwise>
								   </c:choose>
							  </c:forEach>
						 </c:set>
						 <input name="CATEGORY_CODE"  class="i_check" type="checkbox" value="${data.CODE}"  <c:out value='${vChecked}'/> ><label for="a1">${data.NAME }</label>
					</c:forEach>
					
				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">상단노출 구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="Y" type="radio"  <c:if test="${detailView.NOTICE_TOP_YN == 'Y' }">checked="checked"</c:if>><label for="a2">TOP</label>&nbsp;&nbsp;
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="N" type="radio"  <c:if test="${detailView.NOTICE_TOP_YN == 'N' }">checked="checked"</c:if>><label for="a3">일반</label>
				</div>
   			</td>
   		</tr>	
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
   			<th scope="col">질문</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="<c:out value="${detailView.SUBJECT}"/>" style="width:68%;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
		<tr>
			<th height="150">답변</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="ANSWER" name="ANSWER" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
						<c:out value="${detailView.ANSWER}"/>
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
				<c:if test="${detailView.FILE_NAME ne null }">
					<span style="cursor:pointer" id="file_${detailView.BOARD_SEQ }" onclick="deleteAttachFile('${detailView.FILE_PATH}','${detailView.BOARD_SEQ }')">${detailView.FILE_NAME} - 삭제</span><br/>
				<!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
				</c:if>
				<c:if test="${detailView.FILE_NAME eq null }">
					<input title="레이블 텍스트" type="file" name="uploadFile"  />
				</c:if>
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
