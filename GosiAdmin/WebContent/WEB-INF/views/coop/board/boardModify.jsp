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
	 editor.config.editorHeight = '200px';     // 에디터 세로폭입니다.
	 editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
	 editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	 editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
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

    //alert(editor.outputBodyHTML());
    return false;
}

function paramCheck() {
	
	var contentStr = editor.outputBodyHTML();	

	if($("input:checkbox[id='RECOMMEND']").is(":checked")){
		$("#RECOMMEND").val("Y");
	}else{
		$("#RECOMMEND").val("N");
	}
	
	if($("#COOP_AREA").val() == ""){
		alert("지역을 선택해주세요.");
		$("#COOP_AREA").focus();
		return;
	}else if($("#COOP_CATE").val() == ""){
		alert("의료분과를 선택해주세요.");
		$("#COOP_CATE").focus();
		return;
	}else if($("#CREATENAME").val() == "") {
		alert("병원명을 등록해 주세요.");
		$("#CREATENAME").focus();
		return;
	}else if($("#SUBJECT").val() == "") {
		alert("제목을 등록해 주세요.");
		$("#SUBJECT").focus();
		return;
	} else if(contentStr == ""){
		alert("내용을 등록해 주세요.");
		$("#CONTENT").focus();
		return;
	} else if($("#THUMBNAIL_FILE_NAME").val() == ""){
		alert("첨부파일 섬네일을 등록해 주세요.");
		return;
	} else if($("#FILE_NAME").val() == ""){
		alert("첨부파일을 등록해 주세요.");
		return;
	} else {
		if(confirm("등록하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/CoopBoardMng/updateCoopboard.do' />");
			$("#frm").submit();			
		}
	}
}

function fn_boardList(){
	$("#listform").attr("action", "<c:url value='/CoopBoardMng/boardList.do' />");
	$("#listform").submit();
}

function deleteAttachFile(file_path ,board_seq , file_type){

    if(file_type == 'default')  {
        $.ajax({
            type: "GET",
            url: '<c:url value="CoopboardDeleteFile.do/?FILE_PATH="/>'+file_path+'&COOP_BOARD_SEQ='+board_seq+'&FILETYPE='+file_type,
            dataType: "json",
            async : false,
            success: function(json) {
                //alert('삭제성공');
                $("#file_1").remove();
                fileAdd();
            }
        });
    }
    if(file_type == 'thumb')    {
        $.ajax({
            type: "GET",
            url: '<c:url value="CoopboardDeleteFile.do/?FILE_PATH="/>'+file_path+'&COOP_BOARD_SEQ='+board_seq+'&FILETYPE='+file_type,
            dataType: "json",
            async : false,
            success: function(json) {
                //alert('삭제성공');
                $("#fileThumbNail_1").remove();
                fileAdd2();
            }
        });
    }
}

//첨부파일
function fileAdd() {
    $("#fileControl").append("<br/><input type=\"file\" name=\"FILE_NAME\" id=\"FILE_NAME\"  size=\"50\"   >&nbsp;&nbsp;(가로 980)");
}
//섬네일 첨부파일
function fileAdd2() {
    $("#fileControlThumb").append("<br/><input type=\"file\" name=\"THUMBNAIL_FILE_NAME\" id=\"THUMBNAIL_FILE_NAME\"  size=\"50\"   >&nbsp;&nbsp;(190*190)");
}
//섬네일 첨부파일
function fn_Del() {
	if(confirm("삭제하시겠습니까?")){
		$("#frm").attr("action", "<c:url value='/CoopBoardMng/deleteCoopboard.do' />");
		$("#frm").submit();
	}
}

</script>
</head>

  <!--content -->
  <div id="content">
    	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

	<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
	<input type="hidden" id="COOP_BOARD_SEQ" name="COOP_BOARD_SEQ" value="${detail.COOP_BOARD_SEQ}" />
	<input type="hidden" id="SEARCH_COOP_AREA" name="SEARCH_COOP_AREA" value="${params.SEARCH_COOP_AREA}" />
	<input type="hidden" id="SEARCH_COOP_CATE" name="SEARCH_COOP_CATE" value="${params.SEARCH_COOP_CATE}" />
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}" />
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" />

    	<table class="table01">
    	<tr>
    		<th scope="col">지역</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
					<select id="COOP_AREA" name="COOP_AREA" style="width:120px;">
					<option value="">지역선택</option>
					<c:forEach items="${codeAreaList}"  var="area_list">
					<option value="${area_list.CODE_CD}" <c:if test="${detail.COOP_AREA == area_list.CODE_CD}">selected</c:if>>${area_list.CODE_NM }</option>
					</c:forEach>
		  			</select>
				</div>
   			</td>
   		</tr>
    	<tr>
    		<th scope="col">의료분과</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
					<select id="COOP_CATE" name="COOP_CATE" style="width:120px;">
					<option value="">의료분과</option>
					<c:forEach items="${codeHsptList}"  var="hspt_list">
					<option value="${hspt_list.CODE_CD}" <c:if test="${detail.COOP_CATE == hspt_list.CODE_CD}">selected</c:if>>${hspt_list.CODE_NM }</option>
					</c:forEach>
		  			</select>
				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">상단노출 구분</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="Y" type="radio" <c:if test="${detail.NOTICE_TOP_YN == 'Y' }">checked="checked"</c:if>><label for="a2">TOP</label>&nbsp;&nbsp;
	   				<input name="NOTICE_TOP_YN"  class="i_check" value="N" type="radio"  <c:if test="${detail.NOTICE_TOP_YN == 'N' }">checked="checked"</c:if>><label for="a3">일반</label>
				</div>
   			</td>
   		</tr>	
    	<tr>
    		<th scope="col">추천여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item" id="codeList">
	   				<input type="checkbox" id="RECOMMEND" name="RECOMMEND" VALUE="Y" <c:if test="${detail.RECOMMEND == 'Y' }">checked="checked"</c:if>/>
				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">공개 여부</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio" <c:if test="${detail.OPEN_YN == 'Y' }">checked="checked"</c:if>><label for="a2">공개</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio" <c:if test="${detail.OPEN_YN == 'N' }">checked="checked"</c:if>><label for="a3">비공개</label>
				</div>
   			</td>
   		</tr>	
   		<tr>
   			<th scope="col">병원명</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="CREATENAME" name="CREATENAME" class="i_text" title="레이블 텍스트" value="${detail.CREATENAME}" style="width:200px;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="${detail.SUBJECT}" style="width:68%;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
		<tr>
			<th height="50">내용</th>
			<td style="text-align:left;">
				<div class="item">
					<textarea id="CONTENT" name="CONTENT" cols="50" rows="10" class="i_text" title="레이블 텍스트" style="width:96%;">
					<c:out value="${detail.CONTENT}"/>
					</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<th>첨부파일 섬네일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControlThumb">
					<c:if test='${empty detail.THUMBNAIL_FILE_PATH}'>
						<input type="file" id="THUMBNAIL_FILE_NAME" name="THUMBNAIL_FILE_NAME"  size="50"/>&nbsp;&nbsp;(190*190)
					</c:if>
					<%-- <br>${detail.THUMBNAIL_FILE_NAME} --%>
					<c:if test='${!empty detail.THUMBNAIL_FILE_PATH}'>
						<span style="cursor:pointer" id="fileThumbNail_1" onclick="deleteAttachFile('${detail.THUMBNAIL_FILE_PATH}','${detail.COOP_BOARD_SEQ }' , 'thumb')">${detail.THUMBNAIL_FILE_NAME} - 삭제</span>
					</c:if>	
				</div>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td style="text-align:left;">
				<div class="item" id="fileControl">
					<c:if test='${empty detail.FILE_PATH}'>
					<input type="file" name="FILE_NAME" id="FILE_NAME"   size="50">&nbsp;&nbsp;(가로 980)

					</c:if>
					<%-- <br>${detail.FILE_NAME} --%>
					<c:if test='${!empty detail.FILE_PATH}'>
						<span style="cursor:pointer" id="file_1" onclick="deleteAttachFile('${detail.FILE_PATH}','${detail.COOP_BOARD_SEQ }' , 'default')">${detail.FILE_NAME} - 삭제</span>		
					</c:if>
				</div>						
			</td>
		</tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
     <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">저장</a></li>
	   	  <li><a href="javascript:fn_Del();">삭제</a></li>
	      <li><a href="javascript:fn_boardList();">목록</a></li>
	  </ul>
    <!--//버튼--> 
</form>
<form name="listform" id="listform" class="form form-horizontal"  method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
	<input type="hidden" id="COOP_BOARD_SEQ" name="COOP_BOARD_SEQ" value="${detail.COOP_BOARD_SEQ}" />
	<input type="hidden" id="SEARCH_COOP_AREA" name="SEARCH_COOP_AREA" value="${params.SEARCH_COOP_AREA}" />
	<input type="hidden" id="SEARCH_COOP_CATE" name="SEARCH_COOP_CATE" value="${params.SEARCH_COOP_CATE}" />
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}" />
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" />
</form>
</div>
<!--//content --> 
