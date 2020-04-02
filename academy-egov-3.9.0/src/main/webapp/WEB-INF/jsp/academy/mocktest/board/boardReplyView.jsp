<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
function goBoardList(){
    $("#form").attr("action", "<c:url value='/mtboard/boardList.do' />");
    $("#form").submit();
}

function goFileDownload(filePath) {
    document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

function goBoardReplyModify(){
    $("#form").attr("action", "<c:url value='/mtboard/boardReplyModify.do' />");
    $("#form").submit();
}

function goBoardReplyDelete() {
    if(confirm("삭제하시겠습니까?")) {
        //location.href='<c:url value="/mtboard/${board.boardId}/delete/${boardContent.boardContentId}"/>';
        $("#form").attr("action", "<c:url value='/mtboard/boardReplyDelete.do' />");
        $("#form").submit();
    } else {
        return false;
    }
}

function checkComment() {
    if($("#commentContent").val().length < 1) {
        alert("내용을 등록해주세요.");
        return false;
    } else {
        document.commentWriteFrm.submit();
    }
}

function checkModifyComment(id) {
    document.commentModityFrm.content.value=$("#m_contnet_"+id).val();
    document.commentModityFrm.boardCommentId.value=id;

    if($("#m_contnet_"+id).val().length < 1) {
        alert("덧글내용을 등록해주세요.");
        return false;
    } else {
        document.commentModityFrm.submit();
    }
}

function modifyComment(id) {
    $("#m_showFrm_"+id).hide();
    $("#m_hide_"+id).show();
}

function modifyCommentCancal(id) {
    $("#m_showFrm_"+id).show();
    $("#m_hide_"+id).hide();
}

var initBody;
function beforePrint(){
    initBody = document.body.innerHTML;
    document.body.innerHTML = idPrint.innerHTML;
}

function afterPrint(){
    document.body.innerHTML = initBody;
}

//프린트하기
function print_info() {
    window.print();
}
window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
</script>
</head>

<!--content -->
<div id="content">
    <h2>● 게시판관리 > <strong>${params.BOARDTITLE}</strong></h2>
    <form id="form" name="form" method="post">
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
    <input type="hidden" id="PARENTBCONTENTID" name="PARENTBCONTENTID" value="${params.PARENTBCONTENTID}"/>
    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}" />
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>

    <input type="hidden" id="BOARDVIEWID" name="BOARDVIEWID" value="${params.BOARDVIEWID}">
    <input type="hidden" id="BOARDVIEWPARENTID" name="BOARDVIEWPARENTID" value="${params.BOARDVIEWPARENTID}">
    <input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}">

    <input type="hidden" id="ATTACHFILEID" name="ATTACHFILEID" value="${detailView.ATTACHFILEID}">

    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        </thead>
        <tbody>
        <tr>
            <th scope="col">작성자</th>
            <td scope="col" style="text-align:left;">${detailView.CREATENAME}</td>
            <th scope="col">작성일</th>
            <td scope="col" style="text-align:left;"><fmt:formatDate value="${detailView.REG_DT}" type="DATE" pattern="yyyy-MM-dd  KK:mm" /></td>
        </tr>
        <tr>
            <th scope="col">첨부</th>
            <td scope="col" style="text-align:left;">
                <c:if test="${detailView.FILENAME ne null}">
                    <a href="javascript:goFileDownload('<c:out value="${detailView.FILEPATH}" />')">${detailView.FILENAME}</a>
                </c:if>
            </td>
            <th scope="col">조회수</th>
            <td scope="col" style="text-align:left;"><fmt:formatNumber value="${detailView.HITS}" type="number"/></td>
        </tr>
        <tr>
            <th scope="col" colspan>제목</th>
            <td scope="col" colspan="3" style="text-align:left;"><c:out value="${detailView.TITLE}"/></td>
        </tr>
        <tr>
            <th scope="col" height="150">원본내용</th>
            <td scope="col" colspan="3" style="text-align:left;">
                <c:out value="${detailView.ORIGIN}" escapeXml="false" />
            </td>
        </tr>
        <tr>
            <th scope="col" height="150">답변</th>
            <td scope="col" colspan="3" style="text-align:left;">
                <c:out value="${detailView.CONTENT}" escapeXml="false" />
            </td>
        </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <c:if test="${params.USERID eq detailView.REG_ID}">
            <li><a href='javascript:goBoardReplyModify()'>수정</a></li>
            <li><a href='javascript:goBoardReplyDelete()'>삭제</a></li>
        </c:if>
        <li><a href="javascript:goBoardList()">목록</a></li>
        <li><a href="javascript:print_info()">PRINT</a></li>
    </ul>
    <!--//버튼-->
    </form>

</div>
<!--//content -->
