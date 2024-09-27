<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript" src="<c:url value="/resources/libs/cheditor/cheditor.js" />"></script>
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
     editor.inputForm = 'c_content';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
     editor.run();  
});


function fileAdd() {
    $("#fileControl").append("<br/><input type=\"file\" name=\"upFile\" class=\"multipartFile\" onchange=\"fileAdd()\" >");
}

function deleteAttachFile(id){
    $.ajax({
        type: "GET", 
        url: '<c:url value="boardDeleteFile.do/?ATTACHFILEID="/>'+id,
        dataType: "json",       
        async : false,
        success: function(json) {
            alert('삭제성공');
            $("#file_"+id).remove();
            fileAdd();
        }
    });
}

function paramCheck() {
    var contentStr = editor.outputBodyHTML();
    
    if($("#title").val() == "") {
        alert("제목을 등록해 주세요.");
        $("#title").focus();
        return;
    } else if(contentStr == ""){
        alert("내용을 등록해 주세요.");
        $("#c_content").focus();
        return;
    } else {
        $("#frm").attr("action", "<c:url value='/mtboard/boardReplyUpdateProcess.do' />");
        $("#frm").submit();
    }
}
function fn_boardList(){
    $("#frm").attr("action", "<c:url value='/mtboard/boardList.do' />");
    $("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
    <h2>● 게시판관리 > <strong>${params.BOARDTITLE}</strong></h2>
    <form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
    <input type="hidden" id="BOARDENVID" name="BOARDENVID" value="${params.BOARDENVID}"/>
    <input type="hidden" id="BOARDTYPE" name="BOARDTYPE" value="${params.BOARDTYPE}"/>
    <input type="hidden" id="BOARDTITLE" name="BOARDTITLE" value="${params.BOARDTITLE}"/>
    <input type="hidden" id="HASREPLY" name="HASREPLY" value="${params.HASREPLY}"/>
    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
    <input type="hidden" id="codeStr" name="codeStr"  value=""/>
    <input type="hidden" name="writer" value="${params.USERNAME}"/>
    <input type="hidden" name="writerId" value="${params.USERID}"/>
    <input type="hidden" id="ISTOP" name="ISTOP" value="0"/>
    <input type="hidden" id="ISOPEN" name="ISOPEN" value="1"/>

    <input type="hidden" id="BOARDVIEWID" name="BOARDVIEWID" value="${params.BOARDVIEWID}">
    <input type="hidden" id="BOARDVIEWPARENTID" name="BOARDVIEWPARENTID" value="${params.BOARDVIEWPARENTID}">
    <input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">제목</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" name="title" class="i_text" title="레이블 텍스트" value="${detailView.TITLE}" style="width:68%;" />&nbsp;&nbsp;
                    <!--<input name="isOpen" id="isOpenCk" class="i_check" value="1" type="checkbox" <c:if test="${detailView.ISOPEN == '0' }">checked="checked"</c:if>><label for="a4">비공개</label>
                     <input name="isOpenCk" id="a2" class="i_check" value="1" type="checkbox" ><label for="a2">비공개</label> -->
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th height="150">내용</th>
            <td style="text-align:left;">
                <div class="item">
                    <textarea id="c_content" name="content" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">
                        <c:out value="${detailView.CONTENT}" escapeXml="false"/>
                        <%-- <c:out value="${detailView.ORIGIN}" escapeXml="false"/> --%>
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
                    <c:if test="${detailView.ATTACHFILEID ne null }">
                        <span style="cursor:pointer" id="file_${detailView.ATTACHFILEID }" onclick="deleteAttachFile('${detailView.ATTACHFILEID}')">${detailView.FILENAME} - 삭제</span><br/>
                    <!-- <input title="레이블 텍스트" type="file" name="upFile" onchange="fileAdd();" /> -->
                    </c:if>
                    <c:if test="${detailView.ATTACHFILEID eq null }">
                        <input title="레이블 텍스트" type="file" name="uploadFile"  />
                    </c:if>
                </div>
            </td>
        </tr>
        </tbody>
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
