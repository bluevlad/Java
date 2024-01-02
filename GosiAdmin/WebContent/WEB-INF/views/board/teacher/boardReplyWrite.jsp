<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<meta name="decorator" content="index">
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
    $aa = null;
    var contentStr = editor.outputBodyHTML();
    if($("#SUBJECT").val() == "") {
        alert("제목을 입력해 주세요.");
        $("#SUBJECT").focus();
        return;
    } else if(contentStr == ""){
        alert("내용을 입력해 주세요.");
        $("#CONTENT").focus();
        return;
    } else {
        $("#frm").attr("action", "<c:url value='/board/teacher/boardReplyInsertProcess.pop' />");
        $("#frm").submit();
    }
}

function fn_boardList(){
    $("#frm").attr("action", "<c:url value='/board/teacher/boardList.pop' />");
    $("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content_pop">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
<input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
<input type="hidden" id="SEARCHONOFFDIV" name="SEARCHONOFFDIV" value="${params.SEARCHONOFFDIV}">

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHPRFID" name="SEARCHPRFID" value="${params.SEARCHPRFID}"/>

<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

<input type="hidden" id="REG_ID" name="REG_ID" value="${params.USERID}"/>
<input type="hidden" id="CREATENAME" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" id="NOTICE_TOP_YN" name="NOTICE_TOP_YN" value="N"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
<input type="hidden" id="PROF_ID" name="PROF_ID" value="${detailView.PROF_ID}"/>

<input type="hidden" id="codeStr" name="codeStr"  value=""/>

    <h2>● 강사게시판관리 > <strong>${params.BOARD_MNG_NAME} 답변 등록</strong></h2>
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
        <tr>
            <th scope="col">제목</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" id="SUBJECT" name="SUBJECT" class="i_text" title="레이블 텍스트" value="RE:${detailView.SUBJECT}" style="width:96%;" />&nbsp;&nbsp;
                </div>
            </td>
        </tr>
            <th height="150">내용</th>
            <td style="text-align:left;">
                <div class="item">
                    <textarea id="CONTENT" name="CONTENT" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
                        
                        <p>
                        ------------  원본 내용 ------------------<br>
                        <c:out value="${detailView.CONTENT}" escapeXml="false"/>
                    </textarea>
                </div>
            </td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td style="text-align:left;">
                <div class="item" id="fileControl">
                <input title="레이블 텍스트" type="file" name="uploadFile"  />
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
