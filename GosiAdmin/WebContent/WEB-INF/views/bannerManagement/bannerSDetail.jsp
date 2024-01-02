<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %><head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
    initDatePicker1("BANNER_SDT");  
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    initDatePicker1("BANNER_EDT");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');

<c:if test="${view[0].BANNER_TYP == 'H'}">
     editor = new cheditor();              // 에디터 개체를 생성합니다.
     editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
     editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
     editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
     editor.inputForm = 'BANNER_NOTE';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
     editor.run();  
</c:if>
});
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
}
//등록
function fn_Submit(){
<c:if test="${view[0].BANNER_TYP == 'H'}">
    var contentStr = editor.outputBodyHTML();
</c:if>
    if($.trim($("#BANNER_SUBTITLE").val())==""){
        alert("배너명을 입력해 주세요.");
        $("#BANNER_SUBTITLE").focus();
        return;
    }
    if(confirm("배너를 수정하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/bannerUpdateProcess.do' />");
        $("#frm").submit();
    }
}

function fn_Delete(){
    if(confirm("배너를 삭제하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/bannerDelete.do' />");
        $("#frm").submit();
    }
}

function fn_List(){
    $("#frm").attr("action","<c:url value='/bannerManagement/bannerSList.do' />");
    $("#frm").submit();
}
// 단과팝업
function fn_search_lecture_pop(){
<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
    window.open('/lecture/searchLecList.pop?LEC_TYPE_CHOICE=D&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewLec', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=780');
</c:when><c:otherwise>
    window.open('/lectureOff/searchLecList.pop?LEC_TYPE_CHOICE=D&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewLec', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=780');
</c:otherwise></c:choose>
}
// 게시판팝업
function fn_select_board_pop(){
    window.open('/board/teacher/select/boardList.pop?BOARD_MNG_SEQ=TCC_000&SEARCHONOFFDIV=T&currentPage=1&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=800');
}
// 교수팝업
function fn_search_teacher_pop(){
<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
    window.open('/teacher/list_pop.pop?ONOFFDIV=O&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewProf', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=880');
</c:when><c:otherwise>
    window.open('/teacher/list_pop.pop?ONOFFDIV=F&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewProf', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=880');
</c:otherwise></c:choose>
}
// 모의고사팝업
function fn_search_exam_pop(){
<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
    window.open('/mouigosa/reg/searchExamList.pop?S_ISEXAMTYPEON=1&S_ISEXAMTYPEOFF=&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewExm', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=780');
</c:when><c:otherwise>
    window.open('/mouigosa/reg/searchExamList.pop?S_ISEXAMTYPEON=&S_ISEXAMTYPEOFF=1&SRCHCODE=BANNER_LINK&SRCHTXT=BANNER_LINK_TXT', 'viewExm', 'scrollbars=no,toolbar=no,resizable=yes,width=1080,height=780');
</c:otherwise></c:choose>
}
function deleteImage(param){
    if(param == 'bannerImg'){
        $("#bannerImg").html('');
        $("#BANNER_IMAGE_DEL").val('Y');
    }else if(param == 'bannerThumbnailImg'){
        $("#bannerThumbnailImg").html('');
        $("#BANNER_THUMBNAIL_IMAGE_DEL").val('Y');      
    }
    alert('수정버튼을 눌러야 적용이 됩니다.');
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="subCurrentPage" name="subCurrentPage" value="${params.subCurrentPage}">
<input type="hidden" id="subPageRow" name="subPageRow" value="${params.subPageRow}">

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}"/>

<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="SEARCHROLIDX" name="SEARCHROLIDX" value="${params.SEARCHROLIDX}"/>

<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${view[0].ONOFF_DIV}" />
<input type="hidden" id="SEQ" name="SEQ" value="${ params.SEQ }"/>
<input type="hidden" id="P_SEQ" name="P_SEQ" value="${params.P_SEQ}" />
<input type="hidden" id="BANNER_NO" name="BANNER_NO" value="${view[0].BANNER_NO}" />
<input type="hidden" id="SCREEN_GUBUN" name="SCREEN_GUBUN" value="${view[0].SCREEN_GUBUN}" />
<input type="hidden" id="BANNER_TYP" name="BANNER_TYP" value="${view[0].BANNER_TYP}" />
<input type="hidden" id="G_SEQ" name="G_SEQ" value="${detail[0].G_SEQ}" />
        <h2>● 사이트관리 > <strong>배너관리</strong></h2>        
        <h2>${view[0].CATEGORY_NM} | <strong>${view[0].BANNER_TITLE}</strong></h2>
        <table class="table01">
            <tr>
                <th>적용여부</th>
                <td class="tdLeft tdValign"><span class="tdLeft">
                  <input type="radio" name="ISUSE" id="ISUSE" value="Y" <c:if test="${ detail[0].ISUSE eq 'Y' }">checked</c:if>><span class="txt03">적용</span>
                  <input type="radio" name="ISUSE" id="ISUSE" value="N" <c:if test="${ detail[0].ISUSE eq 'N' }">checked</c:if>><span class="txt04">비적용</span></span>
                </td>
            </tr>
            <tr>
                <th>적용날짜</th>
                <td class="tdLeft tdValign">
                    시작일 <input type="text" id="BANNER_SDT" name="BANNER_SDT" value="${ detail[0].BANNER_SDT }" size="10">
                 ~ 종료일 <input type="text" id="BANNER_EDT" name="BANNER_EDT" value="${ detail[0].BANNER_EDT }" size="10">
                </td>
            </tr>
            <tr>
                <th>배너순서</th>
                <td>
                <select id="ROL_IDX" name="ROL_IDX" >
                    <option value="1" <c:if test="${detail[0].ROL_IDX eq '1' }">selected="selected"</c:if>>1</option>
                    <option value="2" <c:if test="${detail[0].ROL_IDX eq '2' }">selected="selected"</c:if>>2</option>
                    <option value="3" <c:if test="${detail[0].ROL_IDX eq '3' }">selected="selected"</c:if>>3</option>
                    <option value="4" <c:if test="${detail[0].ROL_IDX eq '4' }">selected="selected"</c:if>>4</option>
                    <option value="5" <c:if test="${detail[0].ROL_IDX eq '5' }">selected="selected"</c:if>>5</option>
                    <option value="6" <c:if test="${detail[0].ROL_IDX eq '6' }">selected="selected"</c:if>>6</option>
                    <option value="7" <c:if test="${detail[0].ROL_IDX eq '7' }">selected="selected"</c:if>>7</option>
                    <option value="8" <c:if test="${detail[0].ROL_IDX eq '8' }">selected="selected"</c:if>>8</option>
                    <option value="9" <c:if test="${detail[0].ROL_IDX eq '9' }">selected="selected"</c:if>>9</option>
                    <option value="10" <c:if test="${detail[0].ROL_IDX eq '10' }">selected="selected"</c:if>>10</option>
                    <option value="11" <c:if test="${detail[0].ROL_IDX eq '11' }">selected="selected"</c:if>>11</option>
                    <option value="12" <c:if test="${detail[0].ROL_IDX eq '12' }">selected="selected"</c:if>>12</option>
                    <option value="13" <c:if test="${detail[0].ROL_IDX eq '13' }">selected="selected"</c:if>>13</option>
                    <option value="14" <c:if test="${detail[0].ROL_IDX eq '14' }">selected="selected"</c:if>>14</option>
                    <option value="15" <c:if test="${detail[0].ROL_IDX eq '15' }">selected="selected"</c:if>>15</option>
                    <option value="16" <c:if test="${detail[0].ROL_IDX eq '16' }">selected="selected"</c:if>>16</option>
                    <option value="17" <c:if test="${detail[0].ROL_IDX eq '17' }">selected="selected"</c:if>>17</option>
                    <option value="18" <c:if test="${detail[0].ROL_IDX eq '18' }">selected="selected"</c:if>>18</option>
                    <option value="19" <c:if test="${detail[0].ROL_IDX eq '19' }">selected="selected"</c:if>>19</option>
                    <option value="20" <c:if test="${detail[0].ROL_IDX eq '20' }">selected="selected"</c:if>>20</option>
                </select>
                </td>
            </tr>
            <tr>
                <th>배너명</th>
                <td><input type="text" id="BANNER_SUBTITLE" name="BANNER_SUBTITLE" value="${ detail[0].BANNER_SUBTITLE }" size="100%"></td>
            </tr>
        <c:choose>
          <c:when test="${view[0].BANNER_TYP == 'L'}">
            <tr>
                <th>강좌선택</th>
                <td><input type="text" name="BANNER_LINK_TXT" id="BANNER_LINK_TXT" value="${ detail[0].BANNER_LINK_TXT }" size="100%">
                <input type="button" name="lecbtn" value="찾아보기" onClick="fn_search_lecture_pop();"></td>
            </tr>
            <tr>
                <th>Target</th>
                <td>
                  <ul class="pgT5"><span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <input type="hidden" name="BANNER_IMAGE" id="BANNER_IMAGE" value="${ detail[0].BANNER_IMAGE }">
            <input type="hidden" name="BANNER_LINK" id="BANNER_LINK" value="${ detail[0].BANNER_LINK }">
          </c:when>
          <c:when test="${view[0].BANNER_TYP == 'B'}">
            <tr>
                <th>연결선택</th>
                <td><input type="text" name="BANNER_LINK_TXT" id="BANNER_LINK_TXT"  value="${ detail[0].BANNER_LINK_TXT }" size="100%">
                <input type="button" name="boardbtn" value="찾아보기" onClick="fn_select_board_pop();"></td>
            </tr>
            <tr>
                <th>Target</th>
                <td>
                  <ul class="pgT5"><span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <input type="hidden" name="BANNER_IMAGE" id="BANNER_IMAGE" value="${ detail[0].BANNER_IMAGE }">
            <input type="hidden" name="BANNER_LINK" id="BANNER_LINK" value="${ detail[0].BANNER_LINK }">
          </c:when>
          <c:when test="${view[0].BANNER_TYP == 'P'}">
            <tr>
                <th>교수선택</th>
                <td><input type="text" name="BANNER_LINK_TXT" id="BANNER_LINK_TXT" value="${ detail[0].BANNER_SUBTITLE }" size="100%">
                <input type="button" name="lecbtn" value="찾아보기" onClick="fn_search_teacher_pop();"></td>
            </tr>
            <tr>
                <th>Target</th>
                <td>
                  <ul class="pgT5"><span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <input type="hidden" name="BANNER_IMAGE" id="BANNER_IMAGE" value="${ detail[0].BANNER_IMAGE }">
            <input type="hidden" name="BANNER_LINK" id="BANNER_LINK" value="${ detail[0].BANNER_LINK }">
          </c:when>
          <c:when test="${view[0].BANNER_TYP == 'T'}">
            <tr>
                <th>모의고사선택</th>
                <td><input type="text" name="BANNER_LINK_TXT" id="BANNER_LINK_TXT" value="${ detail[0].BANNER_SUBTITLE }" size="100%">
                <input type="button" name="lecbtn" value="찾아보기" onClick="fn_search_exam_pop();"></td>
            </tr>
            <tr>
                <th>Target</th>
                <td>
                  <ul class="pgT5"><span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <tr>
                <th>이미지</th>
                <td>
                    <input type="file" name="BANNER_IMAGE" id="BANNER_IMAGE" size="50">
                    <input type="hidden" name="BANNER_IMAGE_BEFORE" id="BANNER_IMAGE_BEFORE" value="${detail[0].BANNER_IMAGE}">
                    <input type="hidden" name="BANNER_IMAGE_DEL" id="BANNER_IMAGE_DEL" value="">
                    <ul class="bannerFile" id="bannerImg">
                        <c:if test="${!empty detail[0].BANNER_IMAGE}" >
                            <li>
                                ${fn:substring(detail[0].BANNER_IMAGE, fn:indexOf(detail[0].BANNER_IMAGE, '/')+1, fn:length(detail[0].BANNER_IMAGE))}
                                <a href="javascript:deleteImage('bannerImg');"> X </a>
                            </li>
                            <li><img src="<c:url value="/imgFileView.do?path=${detail[0].BANNER_IMAGE}"/>"></li>
                        </c:if>
                    </ul>
                </td>
            </tr>
            <input type="hidden" name="BANNER_LINK" id="BANNER_LINK" value="${ detail[0].BANNER_LINK }">
          </c:when>
          <c:when test="${view[0].BANNER_TYP == 'H'}">
            <tr>
                <th>내용</th>
                <td>
                <textarea id="BANNER_NOTE" name="BANNER_NOTE" cols="50" rows="20" class="i_text" title="레이블 텍스트" style="width:96%;">${detail[0].BANNER_NOTE}</textarea>
                </td>
            </tr>
            <tr>
                <th>Target</th>
                <td>
                  <ul class="pgT5"><span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <input type="hidden" name="BANNER_IMAGE" id="BANNER_IMAGE" value="${ detail[0].BANNER_IMAGE }">
            <input type="hidden" name="BANNER_LINK" id="BANNER_LINK" value="${ detail[0].BANNER_LINK }">
          </c:when>
          <c:otherwise>
            <tr>
                <th>이미지</th>
                <td>
                    <input type="file" name="BANNER_IMAGE" id="BANNER_IMAGE" size="50">
                    <input type="hidden" name="BANNER_IMAGE_BEFORE" id="BANNER_IMAGE_BEFORE" value="${detail[0].BANNER_IMAGE}">
                    <input type="hidden" name="BANNER_IMAGE_DEL" id="BANNER_IMAGE_DEL" value="">
                    <ul class="bannerFile" id="bannerImg">
                        <c:if test="${!empty detail[0].BANNER_IMAGE}" >
                            <li>
                                ${fn:substring(detail[0].BANNER_IMAGE, fn:indexOf(detail[0].BANNER_IMAGE, '/')+1, fn:length(detail[0].BANNER_IMAGE))}
                                <a href="javascript:deleteImage('bannerImg');"> X </a>
                            </li>
                            <li><img src="<c:url value="/imgFileView.do?path=${detail[0].BANNER_IMAGE}"/>"></li>
                        </c:if>
                    </ul>
                </td>
            </tr>
            <tr>
                <th>링크</th>
                <td><input type="text" name="BANNER_LINK" id="BANNER_LINK"  value="${ detail[0].BANNER_LINK }" size="100%">
                  <ul class="pgT5">Target : <span class="tdLeft">
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="1" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '1' }">checked</c:if>>현재창으로 연결 
                  <input type="radio" name="BANNER_LINK_TARGET" id="BANNER_LINK_TARGET" value="2" <c:if test="${ detail[0].BANNER_LINK_TARGET eq '2' }">checked</c:if>>새창으로 연결</span>
                  </ul>
                </td>
            </tr>
            <%--<c:if test="${ MENUTYPE eq 'FM_ROOT' }">
            <tr>
                <th>썸네일</th>
                <td>
                    <input type="file" name="BANNER_THUMBNAIL_IMAGE" id="BANNER_THUMBNAIL_IMAGE" size="50">
                    <input type="hidden" name="BANNER_THUMBNAIL_IMAGE_BEFORE" id="BANNER_THUMBNAIL_IMAGE_BEFORE" value="${detail[0].BANNER_THUMBNAIL_IMAGE}">
                    <input type="hidden" name="BANNER_THUMBNAIL_IMAGE_DEL" id="BANNER_THUMBNAIL_IMAGE_DEL" value="">                        
                    <ul class="bannerFile" id="bannerThumbnailImg">
                        <li><span class="txt08">(1번의 썸네일 사이즈 : 125px × 128px)</span></li>
                        <c:if test="${!empty detail[0].BANNER_THUMBNAIL_IMAGE}" >
                            <li>
                                ${fn:substring(detail[0].BANNER_THUMBNAIL_IMAGE, fn:indexOf(detail[0].BANNER_THUMBNAIL_IMAGE, '/')+1, fn:length(detail[0].BANNER_THUMBNAIL_IMAGE))}
                                <a href="javascript:deleteImage('bannerThumbnailImg');"> X </a>
                            </li>
                            <li><img src="<c:url value="/imgFileView.do?path=${detail[0].BANNER_THUMBNAIL_IMAGE}"/>" width="125"></li>
                        </c:if>                         
                    </ul>
                </td>
              </tr>
            </c:if>--%>
          </c:otherwise>
        </c:choose>    
        </table>
        <!--//테이블--> 
        
        <!--버튼-->
        <ul class="boardBtns">
          <li><a href="javascript:fn_Submit();">수정</a></li>
          <li><a href="javascript:fn_Delete();">삭제</a></li>
          <li><a href="javascript:fn_List();">목록</a></li>
        </ul>
        <!--//버튼-->
</form>
</div>
<!--//content --> 
