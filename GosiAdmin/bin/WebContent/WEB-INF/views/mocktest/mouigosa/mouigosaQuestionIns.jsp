<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
var sts = "${sts}"; //등록:I, 수정:U
var STSCNT = "${searchMap.STSCNT}"; //실제 등록된 문제 건수

var ITEMID = "${searchMap.ITEMID}"; //문제은행구분번호
var QUESTIONNUMBER = "${searchMap.QUESTIONNUMBER}"; //문제은행구분번호
var EXAMROUND = "${searchMap.EXAMROUND}"; //회차
var SUBJECT_CD = "${searchMap.SUBJECT_CD}"; //과목코드
var ENTRYNUM = "${searchMap.ENTRYNUM}"; //지문수
var QUESTIONREGISTRATIONOPTION = "${searchMap.QUESTIONREGISTRATIONOPTION}"; //문제등록옵션
var QUESTIONNUM = "${searchMap.QUESTIONNUM}"; //문제수
var QUESTIONFILEPATH = "${searchMap.QUESTIONFILEPATH}";
var ANSWEREXPLAINFILEPATH = "${searchMap.ANSWEREXPLAINFILEPATH}";

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var S_currentPage = "${searchMap.S_currentPage}";
var S_pageRow = "${searchMap.S_pageRow}";

window.onload = function () {

    $("form input[name='QUESTIONPATTERN']").change(function() {       
        $("input:radio[name='ANSWERNUMBER']").each(function() {
            this.checked = false;
        });
        $("input:checkbox[name='ANSWERNUMBER']").each(function() {
            this.checked = false;
        });
    });
}

//등록
function checkParam() {
    if(!formValidate("popFrm")) return;

    /* if (sts == "I") {
        if (STSCNT == QUESTIONNUM) {
            alert("해당 과목의 문제수를 초과하여 더이상 등록할 수 없습니다.");
            return;
        }
    } */
    /* alert("imgFile:"+$("#imgFile").val());
    alert("imgFile2:"+$("#imgFile2").val()); */

    if (!$("#imgFile").val() == "") {
        $("#imgFile_sts").val("Y");
    }
    if (!$("#imgFile2").val() == "") {
        $("#imgFile2_sts").val("Y");
    }
    if (!$("#imgFile3").val() == "") {
        $("#imgFile3_sts").val("Y");
    }

    var obj;
    var obj2 = "jpg";
    var obj3 = "png";
    if ($('#QUESTIONREGISTRATIONOPTION').val() == "1") {
        /* if('${searchMap.QUESTIONFILENAME}' == '' && $("#imgFile").val().length < 1) {
            alert("문제 이미지를 업로드 해 주세요.");
            return;
        } */
        obj = document.getElementById('imgFile');
        if(fileCheck(obj,obj2,obj3) == false){
//            alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
//            return;
        }
        /* if('${searchMap.ANSWEREXPLAINFILENAME}' == '' && $("#imgFile2").val().length < 1) {
            alert("해설 이미지를 업로드 해 주세요.");
            return;
        } */
        obj = document.getElementById('imgFile2');
        if(fileCheck(obj,obj2,obj3) == false){
            alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
            return;
        }
    } else if ($('#QUESTIONREGISTRATIONOPTION').val() == "2" ) {
        if('${searchMap.QUESTIONFILENAME}' == '' && $("#imgFile").val().length < 1) {
            alert("문제 이미지를 업로드 해 주세요.");
            return;
        }
        obj = document.getElementById('imgFile');
        if(fileCheck(obj,obj2,obj3) == false){
            alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
            return;
        }
        if('${searchMap.ANSWEREXPLAINFILENAME}' == '' && $("#imgFile2").val().length < 1) {
            alert("해설 이미지를 업로드 해 주세요.");
            return;
        }
        obj = document.getElementById('imgFile2');
        if(fileCheck(obj,obj2,obj3) == false){
            alert('확장자가 jpg, png 파일만 첨부가능 합니다.');
            return;
        }
    } else if ($('#QUESTIONREGISTRATIONOPTION').val() == "3") {
        if ($('#QUESTION3').val() == "") {
            alert('문제를 입력해 주세요.');
            $('#QUESTION3').focus();
            return;
        }
        if ($('#ANSWEREXPLAIN3').val() == "") {
            alert('해설을 입력해 주세요.');
            $('#ANSWEREXPLAIN3').focus();
            return;
        }
        /* $('#QUESTION3').val().replace(/\n/g, "<br>");
        $('#ANSWEREXPLAIN3').val().replace(/\n/g, "<br>"); */
    } else if ($('#QUESTIONREGISTRATIONOPTION').val() == "4") {
    <c:if test="${searchMap.QUESTION eq null }">
        if(!$("#imgFile3").val()){
            alert("엑셀 파일을 첨부해 주시기 바랍니다.");
            return;
        }
        obj = document.getElementById('imgFile3');
        obj2 = "xls";
        obj3 = "xls";
        if(fileCheck(obj,obj2,obj3) == false){
            alert('확장자가 xls 파일만 첨부가능 합니다.');
            return;
        }
    </c:if>
    <c:if test="${searchMap.QUESTION ne null }">
        if ($('#QUESTION4').val() == "") {
            alert('문제를 입력해 주세요.');
            $('#QUESTION4').focus();
            return;
        }
        if ($('#ANSWEREXPLAIN4').val() == "") {
            alert('해설을 입력해 주세요.');
            $('#ANSWEREXPLAIN4').focus();
            return;
        }
    </c:if>
    }

    opener.name="list";
    popFrm.target = opener.name;
    $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaQuestionCrud.do"/>').submit();
    self.close();
}

function fileCheck(obj,obj2,obj3){
     // 파일 확장자 체크하기. 엑셀 파일만 업로드 가능.
    //var obj = document.getElementById('uploadFile');
    // 파일 경로를 obj로 받아온다.
    var lastIndex = -1;
    var filePaht = "";
    filePath = obj.value;
    lastIndex = filePath.lastIndexOf('.');
    extension = filePath.substring( lastIndex+1, filePath.len );
    if(!((extension.toLowerCase() == obj2)) && !((extension.toLowerCase() == obj3)) && extension != "") {
        return false;
    }

    return true;
}

// 파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}
</script>
</head>

<!--팝업-->
<form id="popFrm" name="popFrm" enctype="multipart/form-data" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="S_currentPage" name="S_currentPage" value="${searchMap.S_currentPage}">
<input type="hidden" id="S_pageRow" name="S_pageRow" value="${searchMap.S_pageRow}">
<input type="hidden" id="S_searchFlag" name="S_searchFlag" value="${searchMap.S_searchFlag}">
<input type="hidden" id="S_searchKeyWord" name="S_searchKeyWord" value="${searchMap.S_searchKeyWord}">
<input type="hidden" id="S_EXAMYEAR" name="S_EXAMYEAR" value="${searchMap.S_EXAMYEAR}">
<input type="hidden" id="S_EXAMROUND" name="S_EXAMROUND" value="${searchMap.S_EXAMROUND}">

<input type="hidden" id="QUESTIONNUMBER" name="QUESTIONNUMBER" value="${searchMap.QUESTIONNUMBER}" />
<input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
<input type="hidden" id="QUESTIONREGISTRATIONOPTION" name="QUESTIONREGISTRATIONOPTION" value="${searchMap.QUESTIONREGISTRATIONOPTION}" />
<input type="hidden" id="QUESTIONNUM" name="QUESTIONNUM" value="${searchMap.QUESTIONNUM}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />
<input type="hidden" id="sts" name="sts" value="${sts}" />

<input type="hidden" id="QUESTIONFILEID" name="QUESTIONFILEID" value="${searchMap.QUESTIONFILEID}" />
<input type="hidden" id="ANSWEREXPLAINFILEID" name="ANSWEREXPLAINFILEID" value="${searchMap.ANSWEREXPLAINFILEID}" />
<input type="hidden" id="SMAQUESTIONFILEID" name="SMAQUESTIONFILEID" value="${searchMap.SMAQUESTIONFILEID}" />

<input type="hidden" id="imgFile_sts" name="imgFile_sts" />
<input type="hidden" id="imgFile2_sts" name="imgFile2_sts" />
<input type="hidden" id="imgFile3_sts" name="imgFile3_sts" />
    <div id="content_pop" style="width:770px;position:relative;">
    <h2>
    <c:choose>
        <c:when test="${sts == 'I'}">● <strong>문제 등록</strong></c:when>
        <c:when test="${sts == 'U'}">● <strong>문제 수정</strong></c:when>
    </c:choose>
    </h2>

    <!--테이블-->
    <table class="table01">
        <caption>
        </caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tr>
            <th>과목정보</th>
            <td>
                <input type="hidden" name="_method" value="get">
                <div class="item" style="margin-left:0px;">
                    ${searchMap.EXAMYEAR}년 ${searchMap.EXAMROUND}회차 ${searchMap.SUBJECT_NM} (${searchMap.PROF_NM}) / 문항갯수:${searchMap.QUESTIONNUM} / 옵션:${searchMap.QUESTIONREGISTRATIONOPTION}
                </div>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--테이블-->
    <table class="table02">
        <colgroup>
            <col width="25%">
            <col width="75%">
        </colgroup>
        <tbody>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제번호</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    ${searchMap.QUESTIONNUMBER }
                </div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">출제영역</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="QUESTIONRANGE" name="QUESTIONRANGE" class="must" title="출제영역을 선택해 주십시오.">
                    <c:forEach items="${subject_area_list}"  var="subject_area_list">
                        <option value="${subject_area_list.ID }" <c:if test="${searchMap.QUESTIONRANGE == subject_area_list.ID}">selected</c:if>>${subject_area_list.QUESTIONRANGENAME }</option>
                    </c:forEach>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">난이도</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="LEVELDIFFICULTY" name="LEVELDIFFICULTY" class="must">
                        <option value="1"  <c:if test="${searchMap.LEVELDIFFICULTY == '1'}">selected</c:if>>상</option>
                        <option value="2"  <c:if test="${searchMap.LEVELDIFFICULTY == '2'}">selected</c:if>>중</option>
                        <option value="3"  <c:if test="${searchMap.LEVELDIFFICULTY == '3'}">selected</c:if>>하</option>
                    </select>
                </div>
            </td>
        </tr>
    <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '1' || searchMap.QUESTIONREGISTRATIONOPTION == '2' }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile" name="imgFile" title="레이블 텍스트" type="file" />${searchMap.QUESTIONFILENAME }</div></td>
        </tr>
      <c:if test="${searchMap.QUESTIONFILEID ne null }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제 이미지</th>
            <td style="text-align:left;"><div class="item">
                <c:choose>
                    <c:when test="${searchMap.QUESTIONFILEID != ''}"><img src="<c:url value="/imgFileView.do?path=${searchMap.QUESTIONFILEPATH }" />" /></c:when>
                    <c:otherwise><img src="<c:url value="/imgFileView.do?path=${searchMap.QUESTIONFILEPATH }" />" /></c:otherwise>
                </c:choose>
            </div></td>
        </tr>
      </c:if>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">해설</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile2" name="imgFile2" title="레이블 텍스트" type="file" />${searchMap.ANSWEREXPLAINFILENAME }</div></td>
        </tr>
      <c:if test="${searchMap.ANSWEREXPLAINFILEID ne null }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">해설 이미지</th>
            <td style="text-align:left;"><div class="item">
                <c:choose>
                    <c:when test="${searchMap.ANSWEREXPLAINFILEID != ''}"><img src="<c:url value="/imgFileView.do?path=${searchMap.ANSWEREXPLAINFILEPATH }" />" /></c:when>
                    <c:otherwise><img src="<c:url value="/imgFileView.do?path=${searchMap.ANSWEREXPLAINFILEPATH }" />" /></c:otherwise>
                </c:choose>
            </div></td>
        </tr>
      </c:if>
    </c:if>
        <jsp:scriptlet>
        pageContext.setAttribute("newline", "\n");
        pageContext.setAttribute("newline2", "\u0020");
        </jsp:scriptlet>
        <c:set var="QUESTION" value="${fn:replace(searchMap.QUESTION,'<br>',newline)}"/>
        <c:set var="ANSWEREXPLAIN" value="${fn:replace(searchMap.ANSWEREXPLAIN,'<br>',newline)}"/>
    <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION eq 3 }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item"><textarea id="QUESTION3" name="QUESTION3" cols="50" rows="8" title="레이블 텍스트" style="width:90%;">${fn:replace(QUESTION,'&nbsp;',newline2)}</textarea></div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">해설</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item"><textarea id="ANSWEREXPLAIN3" name="ANSWEREXPLAIN3" cols="50" rows="8" title="레이블 텍스트" style="width:90%;">${fn:replace(ANSWEREXPLAIN,'&nbsp;',newline2)}</textarea></div>
            </td>
        </tr>
    </c:if>
    <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION eq 4 }">
      <c:if test="${searchMap.QUESTION eq null }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제/해설 엑셀 업로드</th>
            <td style="text-align:left;"><div class="item"><input id="imgFile3" name="imgFile3" title="레이블 텍스트" type="file" /></div></td>
        </tr>
      </c:if>
      <c:if test="${searchMap.QUESTION ne null }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">

                <div class="item"><textarea id="QUESTION4" name="QUESTION4" cols="50" rows="8" title="레이블 텍스트" style="width:90%;">${fn:replace(QUESTION,'&nbsp;',newline2)}</textarea></div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">해설</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item"><textarea id="ANSWEREXPLAIN4" name="ANSWEREXPLAIN4" cols="50" rows="8" title="레이블 텍스트" style="width:90%;">${fn:replace(ANSWEREXPLAIN,'&nbsp;',newline2)}</textarea></div>
            </td>
        </tr>
      </c:if>
    </c:if>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제 유형</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio must" value="A" title="문제 유형을 선택해 주세요." type="radio" <c:if test="${empty searchMap.QUESTIONPATTERN or searchMap.QUESTIONPATTERN == 'A' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN1">단일선택형</label>
                    <!--<input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="B" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'B' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN2">다지선택형</label>
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="C" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'C' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN3">단답형</label>
                    <input id="QUESTIONPATTERN" name="QUESTIONPATTERN" class="i_radio" value="D" type="radio" <c:if test="${searchMap.QUESTIONPATTERN == 'D' }">checked="checked"</c:if> /><label for="QUESTIONPATTERN4">서술형</label>-->
                </div>
            </td>
        </tr>
        <%--<tr id="one-select" <c:if test="${not empty searchMap.QUESTIONPATTERN and searchMap.QUESTIONPATTERN == 'B' }">style="display:none"</c:if>>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">정답</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <input id="ANSWERNUMBER1" name="ANSWERNUMBER" class="i_radio must" value="1" title="정답을 선택해 주세요." type="radio" <c:if test="${empty searchMap.ANSWERNUMBER or searchMap.ANSWERNUMBER == '1' }">checked="checked"</c:if> /><label for="ANSWERNUMBER1">1</label>
                    <input id="ANSWERNUMBER2" name="ANSWERNUMBER" class="i_radio" value="2" type="radio" <c:if test="${searchMap.ANSWERNUMBER == '2' }">checked="checked"</c:if> /><label for="ANSWERNUMBER2">2</label>
                    <input id="ANSWERNUMBER3" name="ANSWERNUMBER" class="i_radio" value="3" type="radio" <c:if test="${searchMap.ANSWERNUMBER == '3' }">checked="checked"</c:if> /><label for="ANSWERNUMBER3">3</label>
                    <input id="ANSWERNUMBER4" name="ANSWERNUMBER" class="i_radio" value="4" type="radio" <c:if test="${searchMap.ANSWERNUMBER == '4' }">checked="checked"</c:if> /><label for="ANSWERNUMBER4">4</label>
                    <c:if test="${searchMap.ENTRYNUM eq 5 }">
                        <input id="ANSWERNUMBER5" name="ANSWERNUMBER" class="i_radio" value="5" type="radio" <c:if test="${searchMap.ANSWERNUMBER == '5' }">checked="checked"</c:if> /><label for="ANSWERNUMBER5">5</label>
                    </c:if>
                </div>
            </td>
        </tr>--%>
        <tr id="multi-select">
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">정답</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <input id="ANSWERNUMBER1" name="ANSWERNUMBER" value="1" title="정답을 선택해 주세요." type="checkbox" <c:if test="${empty searchMap.ANSWERNUMBER or fn:contains(searchMap.ANSWERNUMBER, '1') }">checked="checked"</c:if> /><label for="ANSWERNUMBER1">1</label>
                    <input id="ANSWERNUMBER2" name="ANSWERNUMBER" value="2" type="checkbox" <c:if test="${fn:contains(searchMap.ANSWERNUMBER, '2')}">checked="checked"</c:if> /><label for="ANSWERNUMBER2">2</label>
                    <input id="ANSWERNUMBER3" name="ANSWERNUMBER" value="3" type="checkbox" <c:if test="${fn:contains(searchMap.ANSWERNUMBER, '3')}">checked="checked"</c:if> /><label for="ANSWERNUMBER3">3</label>
                    <input id="ANSWERNUMBER4" name="ANSWERNUMBER" value="4" type="checkbox" <c:if test="${fn:contains(searchMap.ANSWERNUMBER, '4')}">checked="checked"</c:if> /><label for="ANSWERNUMBER4">4</label>
                    <c:if test="${searchMap.ENTRYNUM eq 5 }">
                        <input id="ANSWERNUMBER5" name="ANSWERNUMBER" value="5" type="checkbox" <c:if test="${fn:contains(searchMap.ANSWERNUMBER, '5')}">checked="checked"</c:if> /><label for="ANSWERNUMBER5">5</label>
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">유사문제</th>
            <td style="text-align:left;"><div class="item"><input id="smaFile" name="smaFile" title="유사문제파일" type="file" />${searchMap.SMAQUESTIONFILENAME }</div></td>
        </tr>
      <c:if test="${searchMap.SMAQUESTIONFILEID ne null }">
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">유사문제파일</th>
            <td style="text-align:left;">
            	<div class="item"><a href="javascript:fn_FileDownload('${searchMap.SMAQUESTIONFILEPATH }');">${searchMap.SMAQUESTIONFILEPATH }</a></div>
            </td>
        </tr>
      </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">저장</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
    <!--<a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->

    </div>
</form>
<!--//팝업-->
