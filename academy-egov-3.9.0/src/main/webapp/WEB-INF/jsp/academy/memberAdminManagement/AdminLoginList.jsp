<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDATE"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
    initDatePicker1("EDATE");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

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
        fn_Search();
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

    $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/AdminLoginList.do"/>').submit();
}

// 회원 상세
function view(USER_ID) {
    $("#USER_ID").val(USER_ID);
    $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminDetail.do"/>').submit();
}

// 엑셀 다운로드
function fn_excel_down() {
    $("#searchFrm").attr("action", "<c:url value='/memberAdminManagement/excel.do' />");
    $("#searchFrm").submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 ><strong> 관리자 로그인 정보</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="MESSAGEID" name="MESSAGEID" value="">
    <input type="hidden" id="MESSAGENM" name="MESSAGENM" value="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="USER_ID" name="USER_ID" /> 

    <table class="table01">
        <tr>
            <th width="10%">접속일</th>
            <td>
                <input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" maxlength="8" readonly="readonly" style="width:80px;"/>
                ~
                <input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" maxlength="8" readonly="readonly" style="width:80px;"/>
            </td>    
            <th width="10%">검색조건</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:80px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" style="width:100px;" value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="10%">화면출력건수</th>
            <td>
                <input size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>

    <!--//테이블-->
    <!--버튼-->
    <ul class="boardBtns">
        <!-- <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li> -->
    </ul>
    <table class="table02">
        <tr>
            <th width="85">No</th>
            <th>회원ID</th>
            <th>이름</th>
            <th>회원권한</th>
            <th>접속일시</th>
            <th>접속아이피</th>
        </tr>
        <tbody>
    <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
            <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
            <td><a href="javascript:view('${list.USER_ID}')">${list.USER_ID}</a></td>
            <td>${list.USER_NM}</td>
            <td>${list.SITE_NM}</td>
            <td>${list.ACCESS_DT}</td>
            <td>${list.USER_IP}</td>
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
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <!--//테이블-->

    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
   <!--//paginate-->
    </form>
</div>
<!--//content -->