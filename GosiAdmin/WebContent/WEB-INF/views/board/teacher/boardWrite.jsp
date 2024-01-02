<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js"></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
     editor = new cheditor();              // 에디터 개체를 생성합니다.
     editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
     editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
     editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
     editor.inputForm = 'CONTENT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
     editor.run();  
     
//      $("#PROF_ID").click(function(){
//     	 $("#PROF_NM_TEXT").val('');
//      });
//      $("#PROF_ID").change(function(){
//    		 var name = $("#PROF_ID option:selected").text();
//    		 $("#PROF_NM_TEXT").val(name);
//      });
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
        url: '<c:url value="/board/teacher/deleteFile/"/>'+id,
        dataType: "json",       
        async : false,
        success: function(json) {
            $("#file_"+id).remove();
        }
    });
}

function paramCheck() {
    
    var contentStr = editor.outputBodyHTML();   
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
    
    if($("#PROF_ID").val() == ""){
        alert("강사를 선택해 주세요.");
        $("#PROF_ID").focus();
        return;
    } else if($("#SUBJECT").val() == "") {
        alert("제목을 입력해 주세요.");
        $("#SUBJECT").focus();
        return;
    } else if(contentStr == ""){
        alert("내용을 입력해 주세요.");
        $("#CONTENT").focus();
        return;
    } else {
        if(confirm("등록하시겠습니까?")){
            $("#frm").attr("action", "<c:url value='/board/teacher/boardInsertProcess.pop' />");
            $("#frm").submit();         
        }
    }
}

function fn_boardList(){
    $("#frm").attr("action", "<c:url value='/board/teacher/boardList.pop' />");
    $("#frm").submit();
}

function addAttchFile(){
    var divLength = $("#fileControl div").length;
    var lastItemNo;
    var nNum;
    if(divLength > 0){
        lastItemNo = $("#fileControl div:last-child").attr('id').replace("row",""); // $("#fileControl").replace("ATTACH_FILE", "");
        nNum = parseInt(lastItemNo) + 1;
    } else {
        nNum = 1;
    }
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

function searchPop(){
	$("#PROF_ID").val("").attr("selected", "selected");
	var offset = $("#search_teacher").offset();
	var xPos = offset.left;
	var yPos = offset.top+100;

	window.open('<c:url value="/board/teacher/boardTeacherSearchPop.pop"/>', 'teacher_pop', 'location=no,resizable=no,width=500,height=700,top='+yPos+',left='+xPos+',scrollbars=no,location=no,scrollbars=yes');
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

<input type="hidden" id="REG_ID" name="REG_ID" value="${params.REG_ID}"/>
<input type="hidden" id="CREATENAME" name="CREATENAME" value="${params.USERNAME}"/>
<input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="ISSUE" name="ISSUE" value=""/>
<input type="hidden" id="RECOMMEND" name="RECOMMEND" value=""/>
<input type="hidden" id="CATEGORY_CODE" name="CATEGORY_CODE" value=""/>
<input type="hidden" id="codeStr" name="codeStr"  value=""/>
    <h2>● 강사게시판관리 > <strong>${params.BOARD_MNG_NAME}</strong></h2>
    <table class="table01">
        <tr>
            <th scope="col">강사선택</th>
            <td scope="col" style="text-align:left;">
            <div class="item" id="codeList">
            <select style="width:100px;" name="PROF_ID" id="PROF_ID">
                <option value="" >강사선택</option>
            <c:forEach items="${SEARCHPRFIDs}"  var="data" varStatus="status" >
                <option value="${data.USER_ID}" >${data.USER_NM }</option>
            </c:forEach>
            </select>
            <input type="button" id="search_teacher" value="강사검색" onclick="javascript:searchPop();">
<!--             <input size="10" title="강사검색" type="text" id="PROF_NM_TEXT"> -->
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
        <%--<c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1}">
        <tr>
            <th scope="col">추천/이슈</th>
            <td scope="col" style="text-align:left;">
            <div class="item">
                <input id="CHECK_ISSUE"  class="i_check" value="Y" type="checkbox" ><label for="a2">이슈</label>&nbsp;&nbsp;
                <input id="CHECK_RECOMMEND"  class="i_check" value="Y" type="checkbox"><label for="a3">추천</label>
            </div>
            </td>
        </tr>   
        </c:if>--%>
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
        <c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1 or fn:indexOf(params.BOARD_MNG_SEQ, 'TCC') != -1}">
        <tr>
            <th>첨부파일 섬네일</th>
            <td style="text-align:left;">
                <input title="레이블 텍스트" type="file" name="uploadFileThumbNail" size="50"/>           
            </td>
        </tr>
        </c:if>
        <c:choose><c:when test="${fn:indexOf(params.BOARD_MNG_SEQ, 'TCC') != -1}">
        <tr>
            <th>영상 경로</th>
            <td style="text-align:left;">
            <div class="item">
                <input type="text" id="FILE_PATH" name="FILE_PATH" class="i_text" title="UCC 동영상 경로" value="" style="width:68%;" />&nbsp;&nbsp;
            </div>
            </td>
        </tr>
        </c:when><c:otherwise>
        <tr>
            <th>첨부파일</th>
            <td style="text-align:left;">
            <div class="item" id="fileControl">
                <input type="button" id="addButton" onclick="javascript:addAttchFile();" value="파일추가">
                <div id="row1"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_1" size="50">
                <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow('1');"></div>
            </div>
            </td>
        </tr>
        </c:otherwise></c:choose>
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
