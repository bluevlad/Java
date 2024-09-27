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

    if(file_type == 'default')  {
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
    if(file_type == 'thumb')    {
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

    var board_gubun = $("#board_gubun").val();
    if(board_gubun=="ORIGIN"){
        if(codeStr==""){
            alert("구분을 1개 이상 체크해 주세요.");
        }
    }
    if($("#SUBJECT").val() == "") {
        alert("제목을 등록해 주세요.");
        $("#SUBJECT").focus();
    } else if(contentStr == ""){
        alert("내용을 등록해 주세요.");
        $("#CONTENT").focus();
    } else {
        $bb  = $("#codeStr").val(codeStr);
        if(confirm("등록하시겠습니까?")){
            $("#frm").attr("action", "<c:url value='/board/boardUpdateProcess.pop' />");
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

<input type="hidden" id="ISSUE" name="ISSUE" value=""/>
<input type="hidden" id="RECOMMEND" name="RECOMMEND" value=""/>

<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}">
<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}">
<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

    <h2>● 생성게시판  > <strong>${params.BOARD_MNG_NAME} 수정</strong></h2>
        <table class="table01" >
        <c:if test="${params.board_gubun == 'ORIGIN'}">
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
        <c:if test="${params.BOARD_MNG_SEQ == 'NOTICE_008'}">
	        <tr>
	            <th scope="col">공고유형</th>
	            <td scope="col" style="text-align:left;">
	                <div class="item" id="typeList">
	                    <c:forEach items="${pub_gubun}"  var="data" varStatus="status" >
	                        <input name="EXAM_TYPE"  <c:if test="${data.CODE_VAL == detailView.EXAM_TYPE}">checked="checked"</c:if> class="i_check" type="radio" value="${data.CODE_VAL}" ><label for="a1">${data.CODE_NM }</label>
	                    </c:forEach>
	                </div>
	            </td>
	        </tr>
			<tr>
				<th>지역구분</th>
				<td class="tdLeft">
				<label for="PUB_CAT"></label>
					<select name="EXAM_AREA" id="EXAM_AREA">
					<option value="">-구분-</option>
					<c:forEach items="${pub_area}" var="item" varStatus="loop">
						<option value="${item.CODE_VAL}" <c:if test="${item.CODE_VAL == detailView.EXAM_AREA}">selected="selected"</c:if>>${item.CODE_NM}</option>					
					</c:forEach>
					</select>
				</td>
			</tr>
		</c:if>
		<c:if test="${params.BOARD_MNG_SEQ == 'NOTICE_000'}">
		<tr>
            <th scope="col">메인노출 구분</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input name="UPDATE_MAIN"  class="i_check" value="Y" type="radio" <c:if test="${detailView.UPDATE_MAIN == 'Y' }">checked="checked"</c:if>><label for="a2">노출</label>&nbsp;&nbsp;
                    <input name="UPDATE_MAIN"  class="i_check" value="N" type="radio"  <c:if test="${detailView.UPDATE_MAIN == 'N' }">checked="checked"</c:if>><label for="a3">비노출</label>
                </div>
            </td>
        </tr> 
        </c:if>
        <tr>
            <th scope="col">상단노출 구분</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input name="NOTICE_TOP_YN"  class="i_check" value="Y" type="radio"  <c:if test="${detailView.NOTICE_TOP_YN == 'Y' }">checked="checked"</c:if>><label for="a2">TOP</label>&nbsp;&nbsp;
                    <input name="NOTICE_TOP_YN"  class="i_check" value="N" type="radio"  <c:if test="${detailView.NOTICE_TOP_YN == 'N' }">checked="checked"</c:if>><label for="a3">일반</label>
                </div>
            </td>
        </tr>
        <c:if test="${fn:indexOf(params.BOARD_MNG_SEQ, 'NOTICE') != -1}">
        <tr>
            <th scope="col">추천/이슈</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input id="CHECK_ISSUE"  class="i_check" value="Y" type="checkbox"  <c:if test="${detailView.ISSUE == 'Y' }">checked="checked"</c:if>><label for="a2">이슈</label>&nbsp;&nbsp;
                    <input id="CHECK_RECOMMEND"  class="i_check" value="Y" type="checkbox" <c:if test="${detailView.RECOMMEND == 'Y' }">checked="checked"</c:if>><label for="a3">추천</label>
                </div>
            </td>
        </tr>
        </c:if>
        <c:if test="${(params.BOARD_MNG_SEQ == 'NOTICE_009') or (params.BOARD_MNG_SEQ == 'NOTICE_010') or (params.BOARD_MNG_SEQ == 'NOTICE_013')}">
        <tr>
            <th scope="col">PC/모바일 노출</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input name="DIVICE_TYPE"  class="i_check" value="1" type="radio" <c:if test="${detailView.DIVICE_TYPE == '1' }">checked="checked"</c:if>><label for="a1">PC</label>&nbsp;&nbsp;
                    <input name="DIVICE_TYPE"  class="i_check" value="2" type="radio" <c:if test="${detailView.DIVICE_TYPE == '2' }">checked="checked"</c:if>><label for="a2">모바일</label>&nbsp;&nbsp;
                    <input name="DIVICE_TYPE"  class="i_check" value="3" type="radio" <c:if test="${detailView.DIVICE_TYPE == '3' }">checked="checked"</c:if>><label for="a3">PC+모바일</label>
                </div>
            </td>
        </tr>
        </c:if>
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
                    <input title="레이블 텍스트" type="file" name="uploadFileThumbNail" size="50"/>
                    &nbsp;썸네일크기 : <input type="text" id="THUMB_X" name="THUMB_X" value="" title="넓이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/> X
                    <input type="text" id="THUMB_Y" name="THUMB_Y" value="" title="높이" size="2" maxlength="4" style="ime-mode:disabled;" onKeyDown="return fn_OnlyNumber(event);" onkeyup="removeChar(event);"/>
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
