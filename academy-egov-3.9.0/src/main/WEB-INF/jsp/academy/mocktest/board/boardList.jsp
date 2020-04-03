<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<meta name="decorator" content="index">

<div id="content">
    <h2>● 게시판관리 > <strong>${params.BOARD_MNG_NAME}</strong></h2>

<form id="form" name="form" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
<%--    <input type="hidden" id="pageRow" name="pageRow" value="${pageRow}">
 --%>
    <input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="${params.BOARD_MNG_SEQ}"/>
    <input type="hidden" id="BOARD_MNG_TYPE" name="BOARD_MNG_TYPE" value="${params.BOARD_MNG_TYPE}"/>
    <input type="hidden" id="BOARD_MNG_NAME" name="BOARD_MNG_NAME" value="${params.BOARD_MNG_NAME}"/>
    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value=""/>
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value=""/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value=""/>
    <input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="">
    <input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="">
    <input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="">

    <input type="hidden" id="OPEN_YN" name="OPEN_YN" value="${params.OPEN_YN}"/>
    <input type="hidden" id="REPLY_YN" name="REPLY_YN" value="${params.REPLY_YN}"/>
    <input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
    <input type="hidden" id="ATTACH_FILE_YN" name="ATTACH_FILE_YN" value="${params.ATTACH_FILE_YN}"/>

    <!--테이블-->
    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="85%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <select style="width:100px;" name="searchCategory" id="searchCategory">
                <c:forEach items="${rankList}"  var="data" varStatus="status" >
                    <option value="${data.CODE}" <c:if test="${data.CODE == params.SEARCHCATEGORY}">selected="selected"</c:if> >${data.NAME }</option>
                </c:forEach>
                </select>
                <select style="width:100px;" name="searchKind" id="searchKind">
                    <option value="SEARCHSUBJECT" <c:if test="${params.SEARCHKIND == 'SEARCHSUBJECT' }">selected="selected"</c:if>>제목</option>
                    <option value="SEARCHNAME" <c:if test="${params.SEARCHKIND == 'SEARCHNAME' }">selected="selected"</c:if>>이름</option>
                    <option value="SEARCHCONTENT" <c:if test="${params.SEARCHKIND == 'SEARCHCONTENT' }">selected="selected"</c:if>>내용</option>
                    <option value="SEARCHMOCKNM" <c:if test="${params.SEARCHKIND == 'SEARCHMOCKNM' }">selected="selected"</c:if>>모의고사명</option>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="i_text" title="레이블 텍스트" type="text" name="searchText" id="searchText" style="width:160px;" value="${params.SEARCHTEXT}">
                <button type="button" onclick="goList(1)"  onkeypress="fn_checkEnter()">검색</button>
            </td>
        </tr>
    </table>

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="7%">
            <col width="15%">
            <col width="50%">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">모의고사명</th>
                <th scope="col">제목</th>
                <th scope="col">첨부</th>
                <th scope="col">작성자</th>
                <th scope="col">공개여부</th>
                <th scope="col">작성일</th>
                <th scope="col">조회수</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${data.MOCKNAME}</td>
                <td style="text-align:left; padding-left:${data.LISTLEVEL*10}px;" >
                    <a href='javascript:goBoardView("${data.BOARD_SEQ}" ,"${data.PARENT_BOARD_SEQ}" , "${data.CODENAME}" );'>
                        <c:if test="${data.PARENT_BOARD_SEQ ne '0'}"><img src="<c:url value="/resources/images//re.gif"/>" align="absmiddle" /><c:out value="${data.SUBJECT}"/></c:if>
                        <c:if test="${data.PARENT_BOARD_SEQ eq '0'}">
                            <c:if test="${data.NOTICE_TOP_YN == 'Y'}">
                                <strong style="color:black;"><c:out value="${data.SUBJECT}"/></strong>
                            </c:if>
                            <c:if test="${data.NOTICE_TOP_YN == 'N'}">
                               <c:out value="${data.SUBJECT}"/>             
                            </c:if>
                        </c:if>
                    </a>
                </td>
                <td><c:if test="${data.FILE_NAME ne null}"><img src="<c:url value="/resources/img/common/icon_disk01.png"/>" width="16" height="16" /></c:if></td>
                <td><c:out value="${data.CREATENAME}"/></td>
                <td>
                    <input type="checkbox" value="${data.BOARD_SEQ}" name="UPDATE_OPEN_ARR" <c:if test="${data.OPEN_YN eq 'Y'}"> checked="checked"</c:if>>
                </td>
                <td><c:out value="${fn:substring(data.REG_DT, 0, 10)}"/></td>
                <td><fmt:formatNumber value="${data.HITS}" type="number"/></td>
            </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="8">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
          </tbody>
    </table>
</form>

    <!--버튼-->
         <ul class="boardBtns">
          <li><a href="javascript:fn_Write();">등록</a></li>
        </ul>
    <!--//버튼-->

    <!--paging  -->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->

</div>
<!--//content -->
<script type="text/javascript">

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            $('#pageRow').val("");
            $('#pageRow').focus();
            return;
        } else {
            if (val < 1) {
                $('#pageRow').val("");
                $('#pageRow').focus();
                return;
            }
        }
    }
}

//엔터키 검색
function fn_checkEnter(){
     $('#searchText').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}

function goList(page) {
    var searchCategory = $("#searchCategory").val();
    var searchKind = $("#searchKind").val();
    var searchText = $("#searchText").val();
    $("#SEARCHCATEGORY").val(searchCategory);
    $("#SEARCHKIND").val(searchKind);
    $("#SEARCHTEXT").val(searchText);

    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/mtboard/boardList.do' />");
    $("#form").submit();
}

function goBoardView(boardSeq , boardParentSeq , codeName){
    var searchCategory = $("#searchCategory").val();
    var searchKind = $("#searchKind").val();
    var searchText = $("#searchText").val();
    $("#SEARCHCATEGORY").val(searchCategory);
    $("#SEARCHKIND").val(searchKind);
    $("#SEARCHTEXT").val(searchText);
    $("#BOARDVIEW_SEQ").val(boardSeq);
    $("#BOARDVIEWPARENT_SEQ").val(boardParentSeq);
    $("#BOARDVIEWCODENAME").val(codeName);
    //원본글인지
    if(boardParentSeq == 0){
        $("#REPLY_YN").val("Y");
        $("#form").attr("action", "<c:url value='/mtboard/boardView.do' />");
        $("#form").submit();
    } else {
        $("#REPLY_YN").val("N");
        $("#form").attr("action", "<c:url value='/mtboard/boardView.do' />");
        $("#form").submit();
    }
}

function fn_Write(){
    var searchCategory = $("#searchCategory").val();
    var searchKind = $("#searchKind").val();
    var searchText = $("#searchText").val();
    $("#SEARCHCATEGORY").val(searchCategory);
    $("#SEARCHKIND").val(searchKind);
    $("#SEARCHTEXT").val(searchText);
    $("#form").attr("action", "<c:url value='/mtboard/boardWrite.do' />");
    $("#form").submit();
}
</script>