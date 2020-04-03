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
     editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
     editor.run();  
     
     if($("input[name=CATEGORY_CODE]:checked").length == parseInt('${fn:length(gbList)}')){
    	 $("#allCheck").attr("checked", true);
     }
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
    fn_gowrite();
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
    
    if(codeStr==""){
        alert("구분을 1개 이상 체크해 주세요.");
    }
    else if($("#SUBJECT").val() == "") {
        alert("제목을 등록해 주세요.");
        $("#SUBJECT").focus();
    } else if(contentStr == ""){
        alert("내용을 등록해 주세요.");
        $("#CONTENT").focus();
    } else {
        $bb  = $("#codeStr").val(codeStr);
        if(confirm("등록하시겠습니까?")){
        	var temp = $("#SUBJECT").val();
        	$("#SUBJECT").val(temp.replace(/・/gi, "."));

            $("#frm").attr("action", "<c:url value='/board/boardInsertProcess.pop' />");
            $("#frm").submit();         
        }
    }
}
function fn_boardList(){
    $("#frm").attr("action", "<c:url value='/board/boardList.pop' />");
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

function fn_gowrite(){
	<c:if test="${params.BOARD_MNG_SEQ == 'NOTICE_024'}">
		var cat = "";
		$("input[name=CATEGORY_CODE]:checked").each(function() {
			if(cat == ""){
				cat += $(this).val();
			}else{
				cat += ","+$(this).val();
			}
		});
		if(cat == ""){
			cat = '${params.SEARCHCATEGORY}';
		}
		$("#ARR_CATEGORY").val(cat);
		$("#frm").attr("action", "<c:url value='/board/boardWrite.pop' />");
		$("#frm").submit();
	</c:if>
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

<input type="hidden" id="ISSUE" name="ISSUE" value=""/>
<input type="hidden" id="RECOMMEND" name="RECOMMEND" value=""/>

<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="ARR_CATEGORY" name="ARR_CATEGORY" value="">
<%--    <div id="stitle">
        <ul id="stitle_l"><img src="<c:url value="/resources/img/common/icon_stitle01_02.png"/>" align="absmiddle" />
            ${params.BOARDTITLE}
        </ul>
        <ul id="stitle_r">* Home > 게시판관리 > ${params.BOARDTITLE}</ul>
    </div> --%>
        <h2>● 생성게시판  > <strong>${params.BOARD_MNG_NAME}</strong></h2>
        <table class="table01">
        <tr>
            <th scope="col">구분</th>
            <td scope="col" style="text-align:left;">
                <div class="item" id="codeList">
                    <input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
                    <c:forEach items="${gbList}"  var="data" varStatus="status" >
                        <input name="CATEGORY_CODE"  onclick="fn_gowrite()" class="i_check" type="checkbox" <c:if test="${params.BOARD_MNG_SEQ == 'NOTICE_024' && fn:indexOf(params.ARR_CATEGORY, data.CODE) != -1}">checked="checked"</c:if> value="${data.CODE}" ><label for="a1">${data.NAME }</label>
                    </c:forEach>
                </div>
	            	<c:if test="${params.BOARD_MNG_SEQ == 'NOTICE_024'}">
						<select style="width:100px;" name="SUBJECT_CD" id="SUBJECT_CD">
								<option value="" >과목</option>
							<c:forEach items="${catSubject}"  var="data" varStatus="status" >
								<option value="${data.SUBJECT_CD}" >${data.SUBJECT_NM }(${data.CATEGORY_NM})</option>
							</c:forEach>
						</select>
						<select style="width:100px;" name="PRF_ID" id="PRF_ID">
								<option value="" >강사</option>
							<c:forEach items="${teacherList}"  var="data" varStatus="status" >
								<option value="${data.USER_ID}" >${data.USER_NM }(${data.USER_ID})</option>
							</c:forEach>
						</select>
						<select style="width:80px;" name="LEARNING_CD" id="LEARNING_CD">
							<option value="" >단계</option>
							<option value="M0101" >이론</option>
							<option value="M0102" >문제풀이</option>
							<option value="M0103" >특강</option>
						</select>
	            	</c:if>
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
        <c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1 && params.BOARD_MNG_SEQ != 'NOTICE_024'}">
        <tr>
            <th scope="col">추천/이슈</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input id="CHECK_ISSUE"  class="i_check" value="Y" type="checkbox" ><label for="a2">이슈</label>&nbsp;&nbsp;
                    <input id="CHECK_RECOMMEND"  class="i_check" value="Y" type="checkbox"><label for="a3">추천</label>
                </div>
            </td>
        </tr>   
        </c:if>
        <c:if test="${(params.BOARD_MNG_SEQ == 'NOTICE_009') or (params.BOARD_MNG_SEQ == 'NOTICE_010') or (params.BOARD_MNG_SEQ == 'NOTICE_013')}">
        <tr>
            <th scope="col">PC/모바일 노출</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input name="DIVICE_TYPE"  class="i_check" value="1" type="radio" ><label for="a1">PC</label>&nbsp;&nbsp;
                    <input name="DIVICE_TYPE"  class="i_check" value="2" type="radio" ><label for="a2">모바일</label>&nbsp;&nbsp;
                    <input name="DIVICE_TYPE"  class="i_check" value="3" type="radio"  checked><label for="a3">PC+모바일</label>
                </div>
            </td>
        </tr>   
        </c:if>
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
            <th scope="col">제목</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="" style="width:68%;" />&nbsp;&nbsp;
                    <!-- <input name="isOpen" id="isOpenCk" class="i_check" value="1" type="checkbox" ><label for="a4">비공개</label> -->
                </div>
            </td>
        </tr>
        <tr>
            <th height="150">내용</th>
            <td style="text-align:left;">
                <div class="item">
                    <textarea id="CONTENT" name="CONTENT" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;"></textarea>
                </div>
            </td>
        </tr>
        <c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1}">
        <tr>
            <th>첨부파일 섬네일</th>
            <td style="text-align:left;">
                <input title="레이블 텍스트" type="file" name="uploadFileThumbNail" size="50"/>
                &nbsp;썸네일크기 : <input type="text" id="THUMB_X" name="THUMB_X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X 
                <input type="text" id="THUMB_Y" name="THUMB_Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/>                
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
            
                    <input type="button" id="addButton" onclick="javascript:addAttchFile();" value="파일추가">
                    <div id="row1"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_1" size="50">
                    <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow('1');"></div>
                <!-- <input title="레이블 텍스트" type="file" name="uploadFile" /> 기존 첨부파일-->
                                
                <!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
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
