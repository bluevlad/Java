<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
function fn_update() {
    if($("#BOARD_MNG_NAME").val() == ""){
        alert("게시판명을 입력해 주세요.");
        $("#BOARD_MNG_NAME").focus();
        return;
    } else {
        //$("#DETAIL_CODE_NO").val(CODE_NO);        
        if(confirm("수정 하시겠습니까?")) {
            $("#frm").attr("action", "<c:url value='/boardManagement/boardMngUpdateProcess.do' />");
            $("#frm").submit();
        }
    }
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/boardManagement/boardMngList.do' />");
    $("#frm").submit();
}

function fn_delete(){
    if(confirm("삭제하시겠습니까?")) {
        $("#frm").attr("action", "<c:url value='/boardManagement/boardMngDelete.do' />");
        $("#frm").submit();
    } else {
        return false;
    }
}
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

<input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${detail.BOARD_MNG_TYPE}" />  
<input type="hidden" id="DETAIL_BOARD_MNG_SEQ" name="DETAIL_BOARD_MNG_SEQ" value="${detail.BOARD_MNG_SEQ}" />   

<h2>● 게시판관리 > <strong>게시판생성관리 상세</strong></h2>
<!--//테이블--> 
    <table class="table01">
        <tr>
            <th >게시판ID</th>
            <td>
                ${detail.BOARD_MNG_SEQ}
            </td>
        </tr>
        <tr>
            <th >게시판명</th>
            <td>
                <div class="item" >
                    <input type="text" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME"  title="레이블 텍스트" value="${detail.BOARD_MNG_NAME}" size="20"  maxlength="20" style="width:28% ; background:#FFECEC;" />
                </div>
            </td>
        </tr>
        <tr>
            <th>게시판 타입</th>
            <td>
                ${detail.BOARD_MNG_TYPE_NM}
            </td>
        </tr>
        <tr>
            <th>온라인,오프라인,강사용</th>
            <td>
                ${detail.ONOFF_DIV_NM}
            </td>
        </tr>
    <c:if test="${detail.BOARD_MNG_TYPE =='BOARD' or detail.BOARD_MNG_TYPE =='TCC'}">
        <tr id="display_1">
            <th>공개,비공개 기능</th>
            <td>
                <label for="OPEN_YN"></label>
                <select  id="OPEN_YN" name="OPEN_YN">
                    <option value="Y" <c:if test="${detail.OPEN_YN == 'Y'}">selected</c:if>>로그인</option>
                    <option value="N" <c:if test="${detail.OPEN_YN == 'N'}">selected</c:if>>비로그인</option>
                </select>
                <span>(☞ 게시물 상세보기 [로그인,비로그인] 활성 여부에 대한 설정입니다.)</span>  
            </td>
        </tr>
        <tr id="display_2">
            <th>답변여부</th>
            <td>
                <label for="REPLY_YN"></label>
                <select  id="REPLY_YN" name="REPLY_YN">
                    <option value="Y" <c:if test="${detail.REPLY_YN == 'Y'}">selected</c:if> >활성</option>
                    <option value="N" <c:if test="${detail.REPLY_YN == 'N'}">selected</c:if>>비활성</option>
                </select>
                <span>(☞ 게시글에 대해 답변기능을 부여할지에 대한 설정입니다) </span>
            </td>
        </tr>
        <%-- <tr id="display_3">
            <th>첨부파일여부</th>
            <td>
                <label for="ATTACH_FILE_YN"></label>
                <select  id="ATTACH_FILE_YN" name="ATTACH_FILE_YN">
                    <option value="Y" <c:if test="${detail.ATTACH_FILE_YN == 'Y'}">selected</c:if>>활성</option>
                    <option value="N" <c:if test="${detail.ATTACH_FILE_YN == 'N'}">selected</c:if>>비활성</option>
                </select>
                <span>(☞ 게시글에 파일 첨부기능을 부여할지에 대한 설정입니다.) </span>
            </td>
        </tr>  --%>
    </c:if>     
    <!-- 게시판 타입이 NOTICE 일때 -->
    <c:if test="${detail.BOARD_MNG_TYPE =='NOTICE' or detail.BOARD_MNG_TYPE =='FAQ'}">
        <tr id="display_1">
            <th>로그인,비로그인 기능</th>
            <td>
                <label for="OPEN_YN"></label>
                <select  id="OPEN_YN" name="OPEN_YN">
                    <option value="Y" <c:if test="${detail.OPEN_YN == 'Y'}">selected</c:if>>로그인</option>
                    <option value="N" <c:if test="${detail.OPEN_YN == 'N'}">selected</c:if>>비로그인</option>
                </select>
                <span>(☞ 게시물 상세보기 [로그인,비로그인] 활성 여부에 대한 설정입니다.)</span> 
            </td>
        </tr>
    </c:if>
        <tr>
            <th>게시판 사용유무</th>
            <td>
                <label for="ISUSE"></label>
                <select  id="ISUSE" name="ISUSE">
                    <option value="Y"  <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
                    <option value="N"  <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
                </select>
                <span>(☞ 게시판 사용유무에 대한 설정입니다) </span>
            </td>
        </tr>
    </table>
    <!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_update();">수정</a></li>
      <li><a href="javascript:fn_delete();">삭제</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
