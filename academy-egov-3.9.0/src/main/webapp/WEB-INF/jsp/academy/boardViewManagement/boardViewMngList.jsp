<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
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
    $('#SEARCHKEYWORD').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });

    $('#pageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        goList(1);
        return;
    }
    if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}
//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#searchFrm').attr('target' ,'_self');
    $('#searchFrm').attr('action','<c:url value="/boardViewManagement/boardViewMngList.do"/>');
    $('#searchFrm').submit();
}

// 코드 상세
function view(BOARD_MNG_SEQ) {
    $("#BOARD_MNG_SEQ").val(BOARD_MNG_SEQ);
    $("#currentPage").val("");

    if(BOARD_MNG_SEQ.indexOf("FAQ") != -1){
        window.open('', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=880 ');
        $('#searchFrm').attr('target' ,'viewBoard');
  <c:choose>
    <c:when test="${searchMap.SEARCHONOFFDIV == 'T'}">
        $('#searchFrm').attr('action','<c:url value="/board/teacher/boardFAQList.pop"/>');
    </c:when>
    <c:otherwise>
        $('#searchFrm').attr('action','<c:url value="/board/boardFAQList.pop"/>');
    </c:otherwise>
  </c:choose>
        $('#searchFrm').submit();
    } else if(BOARD_MNG_SEQ.indexOf("NOTICE") != -1 || BOARD_MNG_SEQ.indexOf("BOARD") != -1 || BOARD_MNG_SEQ.indexOf("TCC") != -1){
        window.open('', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=880 ');
        $('#searchFrm').attr('target' ,'viewBoard');
  <c:choose>
    <c:when test="${searchMap.SEARCHONOFFDIV == 'T'}">
        $('#searchFrm').attr('action','<c:url value="/board/teacher/boardList.pop"/>');
    </c:when>
    <c:otherwise>
        $('#searchFrm').attr('action','<c:url value="/board/boardList.pop"/>');
    </c:otherwise>
  </c:choose>
        $('#searchFrm').submit();
    }
}
</script>
</head>

  <!--content -->
  <div id="content">
  <c:choose>
    <c:when test="${searchMap.SEARCHONOFFDIV == 'T'}"><h2>● 게시판 관리 > <strong>강사게시판 관리</strong></h2></c:when>
    <c:otherwise><h2>● 게시판 관리 > <strong>생성 게시판</strong></h2></c:otherwise>
  </c:choose>
    
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="USERID" name="USERID" value="${AdmUserInfo.USERID}" />
    <input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="" />
    <input type="hidden" id="SEARCHONOFFDIV" name="SEARCHONOFFDIV" value="${searchMap.SEARCHONOFFDIV}" />

    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="BOARD_MNG_SEQ"  <c:if test="${searchMap.SEARCHTYPE == 'BOARD_MNG_SEQ'}">selected</c:if>>게시판ID</option>
                    <option value="BOARD_MNG_NAME"  <c:if test="${searchMap.SEARCHTYPE == 'BOARD_MNG_NAME'}">selected</c:if>>게시판명</option>
                    <%-- <option value="BOARD_MNG_TYPE"  <c:if test="${searchMap.SEARCHTYPE == 'BOARD_MNG_TYPE'}">selected</c:if>>게시판유형</option> --%>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name=SEARCHKEYWORD  type="text" size="40"  value="${searchMap.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>
    </form>
    <!--//테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <!--테이블-->
    <table class="table02">
        <tr>
          <th width="85">No</th>
          <c:if test="${searchMap.SEARCHONOFFDIV != 'T'}"><th>온라인,오프라인</th></c:if>
          <th>게시판ID</th>
          <th>게시판명</th>
          <th>게시판유형</th>
          <th>공개,비공개</th>
          <th>게시글수</th>
          <th>TODAY</th>
          <th>상태</th>
          <th>등록일</th>
        </tr>
    <tbody>
    <c:if test="${not empty list}">
      <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
            <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
            <c:if test="${searchMap.SEARCHONOFFDIV != 'T'}"><td>${list.ONOFF_DIV_NM}</td></c:if>
            <td>${list.BOARD_MNG_SEQ}</td>
            <td><a href="javascript:view('${list.BOARD_MNG_SEQ}')">${list.BOARD_MNG_NAME}</a></td>
            <td>${list.BOARD_MNG_TYPE_NM}</td>
            <td>${list.OPEN_YN_NM}</td>
            <td>${list.CNT}</td>
            <td>${list.TODAY}</td>
            <td>${list.ISUSE_NM}</td>
            <td>${list.REG_DT}</td>
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
    <!--//테이블-->

    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
    <!--//paginate-->
</div>
<!--//content -->