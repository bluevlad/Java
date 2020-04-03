<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<meta name="decorator" content="index">

<!--content -->
<div id="content">
    <h2>● 게시판관리 > <strong>${params.BOARD_MNG_NAME}</strong></h2>
    <form id="form" name="form" method="post">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

    <input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
    <input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
    <input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}" />
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
    <input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="${params.BOARDVIEW_SEQ}" />
    <input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="${params.BOARDVIEWPARENT_SEQ}" />
    <input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="${params.BOARDVIEWCODENAME}" />

    <input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
    <input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
    <input type="hidden" id="FILE_PATH" name="FILE_PATH" value="${detailView.FILE_PATH}"/>

    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        </thead>
        <tbody>
        <tr>
            <th scope="col" width="15%">모의고사명</th>
            <td scope="col" style="text-align:left;">${detailView.MOCKNAME}</td>
            <th scope="col">공지구분</th>
            <td scope="col" style="text-align:left;">
                <c:if test='${detailView.NOTICE_TOP_YN=="Y"}'>TOP</c:if>
                <c:if test='${detailView.NOTICE_TOP_YN=="N"}'>일반</c:if>
            </td>
        </tr>
        <tr>
            <th scope="col">작성자</th>
            <td scope="col" style="text-align:left;"><c:out value="${detailView.CREATENAME}"/></td>
            <th scope="col">작성일</th>
            <td scope="col" style="text-align:left;">${detailView.REG_DT}</td>
        </tr>
        <tr>
            <th scope="col">첨부파일</th>
            <td scope="col" style="text-align:left;">
                <c:if test="${boardAttachFile.size()>0}">
                    <!-- TB_BOARD_FILE 테이블에서 가지고 오기 위한 수정 (다수의 첨부파일을 올릴수 있게 하기 위함)-->
                        <c:forEach items="${boardAttachFile}" var="data" varStatus="status">
                            <a href="javascript:goFileDownload('<c:out value="${data.FILE_PATH}" />')">
                                ${data.FILE_NAME}&nbsp;<img src="/resources/img/common/icon_disk01.png" alt="파일첨부" />
                            </a><br/>
                        </c:forEach>
                </c:if>
            </td>
            <th scope="col">조회수</th>
            <td scope="col" style="text-align:left;"><fmt:formatNumber value="${detailView.HITS}" type="number"/></td>
        </tr>
        <tr>
            <th scope="col" colspan>제목</th>
            <td scope="col" colspan="3" style="text-align:left;"><c:out value="${detailView.SUBJECT}"/></td>
        </tr>
        <tr>
            <th scope="col" height="150">내용</th>
            <td scope="col" colspan="3" style="text-align:left;">
                <c:if test="${boardAttachFile_Img.size() > 0 }">
                    <c:forEach items="${boardAttachFile_Img}" var="data" varStatus="status">
                            <div>
                                <img src="<c:url value="/imgFileView.do?path=${data.FILE_PATH}" />" width="600"/>
                            </div>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(detailView_Origin ) > 0}">
                    -원본글-
                    <br>
                    <br>
                    <c:out value="${detailView_Origin.CONTENT}" escapeXml="false" />
                    <br>
                    ----------------------------------------------------------------------------------------------------------------------------------------------------
                    <br><br>
                </c:if>
                <c:out value="${detailView.CONTENT}" escapeXml="false" />
            </td>
        </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
    <c:if test="${params.REPLY_YN == 'Y' }">
        <c:if test="${detailView.PARENT_BOARD_SEQ == 0 and params.ISBOARDREPLY == 0}">
        <li><a href='javascript:goBoardReply("${detailView.BOARD_SEQ}")'>답변</a></li>
        </c:if>
    </c:if>
        <li><a href="javascript:goBoardModify()">수정</a></li>
        <li><a href='javascript:goBoardDelete("${detailView.BOARD_SEQ}","${params.BOARD_MNG_SEQ}")'>삭제</a></li>
        <li><a href="javascript:goBoardList()">목록</a></li>
    </ul>
    <!--//버튼-->
    </form>

</div>
<!--//content -->

<script type="text/javascript">
function goBoardReply(id){
    $("#form").attr("action", "<c:url value='/mtboard/boardReplyWrite.do' />");
    $("#form").submit();
}

function goBoardList(){
    $("#form").attr("action", "<c:url value='/mtboard/boardList.do' />");
    $("#form").submit();
}

function goFileDownload(filePath) {
    document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

function goBoardModify(){
    $("#form").attr("action", "<c:url value='/mtboard/boardModify.do' />");
    $("#form").submit();
}

function goBoardDelete(board_seq ,board_mng_seq ) {
    $.ajax({
        type: "GET",
        url: '<c:url value="getReplyData.do/?BOARDVIEW_SEQ="/>'+board_seq+'&BOARD_MNG_SEQ='+board_mng_seq,
        dataType: "json",
        async : false,
        success: function(result) {
            if(result.message=="N"){
                alert("답변이 존재하여 삭제 할수 없습니다.");
                return;
            }else{
                if(confirm("삭제하시겠습니까?")) {
                    $("#form").attr("action", "<c:url value='/mtboard/boardDelete.pop' />");
                    $("#form").submit();
                } else {
                    return false;
                }
            }
        }
    });
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