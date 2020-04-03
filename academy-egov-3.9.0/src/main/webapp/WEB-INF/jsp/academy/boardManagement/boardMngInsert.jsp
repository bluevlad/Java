<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
function paramCheck() {

    var boardDiv = $("select[name=ONOFF_DIV]").val();
    if(boardDiv == 'T'){
        if($("select[name=BOARD_MNG_TYPE]").val() == 'FAQ') {
            alert("강사용 게시판에는 FAQ유형의 게시판을 생성할 수 없습니다.");
            $("#ONOFF_DIV").val('');
            return;
        }
    }

    if($("#BOARD_MNG_NAME").val() == "") {
        alert("게시판명 입력해 주세요.");
        $("#BOARD_MNG_NAME").focus();
        return;
    } else {
        if(confirm("등록 하시겠습니까?")) {
            $("#frm").attr("action", "<c:url value='/boardManagement/boardMngInsertProcess.do' />");
            $("#frm").submit();
        }
    }
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/boardManagement/boardMngList.do' />");
    $("#frm").submit();
}

function fn_change(target){
    var boardType = $(target).val();

    if(boardType == 'NOTICE'){
    //  $("#display_1").hide();
        $("#display_2").hide();
    //  $("#display_3").show();
    }
    if(boardType == 'BOARD'){
    //  $("#display_1").show();
        $("#display_2").show();
    //  $("#display_3").show();
    }
    if(boardType == 'FAQ'){
    //  $("#display_1").hide();
        $("#display_2").hide();
    //  $("#display_3").show();
    }
    if(boardType == 'TCC'){
    //  $("#display_1").show();
        $("#display_2").show();
    //  $("#display_3").show();
    }
}

function fn_change_div(target){
    var boardDiv = $(target).val();
    if(boardDiv == 'T'){
        if($("select[name=BOARD_MNG_TYPE]").val() == 'FAQ') {
            alert("강사용 게시판에는 FAQ 유형의 게시판을 생성할 수 없습니다.");
            $("#ONOFF_DIV").val('');
            return;
        }
    }
}

$(document).ready(function(){
    //var selectVal = $("BOARD_TYPE").val();
    //$("#display_1").hide();
    $("#display_2").hide();
});
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>

    <h2>● 게시판 관리 > <strong>게시판 관리</strong></h2>
    <table class="table01">
        <tr>
            <th >게시판명</th>
            <td>
                <div class="item" >
                    <input type="text" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME"  title="레이블 텍스트" value="" size="20"  maxlength="20" style="width:28% ; background:#FFECEC;" />
                </div>
            </td>
        </tr>
        <tr>
            <th>게시판 타입</th>
            <td>
                <select  id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" onchange="fn_change(this)"> &nbsp;
                <c:forEach items="${boardTypeList}" var="item" varStatus="loop">
                    <option value="${item.CODE_CD}">${item.CODE_NM}</option>
                </c:forEach>
                </select>
                 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            </td>
        </tr>
        <tr>
            <th>온라인,오프라인,강사용</th>
            <td>
                <label for="ONOFF_DIV"></label>
                <select  id="ONOFF_DIV" name="ONOFF_DIV" onchange="fn_change_div(this)">
                    <option value="O">온라인</option>
                    <option value="F">오프라인</option>
                    <option value="T">강사용</option>
                </select>
                <span>(☞ 게시물 등록시 온라인, 오프라인, 강사용 위치에 대한 설정입니다.)</span>
            </td>
        </tr>
        <tr id="display_1">
            <th>로그인,비로그인 기능</th>
            <td>
                <label for="OPEN_YN"></label>
                <select  id="OPEN_YN" name="OPEN_YN">
                    <option value="Y">로그인</option>
                    <option value="N">비로그인</option>
                </select>
                <span>(☞ 게시물 상세보기 [로그인,비로그인] 활성 여부에 대한 설정입니다.)</span>
            </td>
        </tr>
        <tr id="display_2">
            <th>답변여부</th>
            <td>
                <label for="REPLY_YN"></label>
                <select  id="REPLY_YN" name="REPLY_YN">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
                <span>(☞ 게시글에 대해 답변기능을 부여할지에 대한 설정입니다) </span>
            </td>
        </tr>
        <!-- <tr id="display_3">
            <th>첨부파일여부</th>
            <td>
                <label for="ATTACH_FILE_YN"></label>
                <select  id="ATTACH_FILE_YN" name="ATTACH_FILE_YN">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
                <span>(☞ 게시글에 파일 첨부기능을 부여할지에 대한 설정입니다.) </span>
            </td>
        </tr> -->
        <tr>
            <th>게시판 사용유무</th>
            <td>
                <label for="ISUSE"></label>
                <select  id="ISUSE" name="ISUSE">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
                <span>(☞ 게시판 사용유무에 대한 설정입니다)  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </span>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:paramCheck();">등록</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
</form>
</div>
<!--//content -->
