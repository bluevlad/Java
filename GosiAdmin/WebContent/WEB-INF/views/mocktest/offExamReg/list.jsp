<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta name="decorator" content="index">
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
    $('#searchKeyWord').keyup(function(e)  {
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

function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/offExamReg/offExamList.do' />");
    $("#form").submit();
}
function goOffExamView(mockcode){
    //var searchText = $("#searchText").val();
    //var searchType = $("#searchType  option:selected").val();
    //var pageRow = $("#pageRow").val();
    //if(typeof(searchText) == "undefined") $("#searchText").val("");

    //location.href='<c:url value="/offExamReg/offExamView.do"/>' + '?mockCode='+mockcode+'&currentPage='+${currentPage}+'&pageRow='+pageRow+'&searchText='+searchText+'&searchType='+searchType;
    $("#mockCode").val(mockcode);
    $("#form").attr("action", "<c:url value='/offExamReg/offExamView.do' />");
    $("#form").submit();
}
</script>
</head>

<div id="content">
    <h2>● 신청자관리 > <strong>OFF응시자등록</strong></h2>

    <!--테이블-->
    <form name="searchFrm" id="form" action="<c:url value="/offExamReg/offExamList.do"/>" method="post">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>

    <input type="hidden" id="mockCode" name="mockCode" value="">

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="*">
            <col width="15%">
            <col width="30%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <input type="hidden" name="_method" value="get">
                <select style="width:100px;" name="searchType" id="searchType">
                    <option value="1" <c:if test="${searchType == '1' }">selected="selected"</c:if>>코드번호</option>
                    <option value="2" <c:if test="${searchType == '2' }">selected="selected"</c:if>>모의고사명</option>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="i_text" title="레이블 텍스트" type="text" name="searchText" id="searchText" style="width:160px;" value="${searchText }">
                <!-- <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span> -->
            </td>
            <th>화면출력건수</th>
            <td>
                <input class="i_text"  title="검색" type="text" id="pageRow" name="pageRow" type="text" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}"  onkeypress="fn_checkEnter()">
                &nbsp;&nbsp;
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>
            </td>
        </tr>
    </table>
    </form>
     <!--//테이블-->

     <!--버튼-->
     <!--//버튼-->

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="8%">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">코드번호</th>
            <th scope="col">모의고사명</th>
            <th scope="col">시험직급</th>
            <th scope="col">시작일</th>
            <th scope="col">종료일</th>
            <th scope="col">시험시간</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
        <form id="form" name="form" method="post">
            <input type="hidden" id="currentPage" name="currentPage">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td><c:out value="${data.MOCKCODE}"/></td>
                <td style="text-align:left;">
                    <a href='javascript:goOffExamView("${data.MOCKCODE}");'>
                        <c:out value="${data.MOCKNAME}"/>
                    </a>
                </td>
                <td><c:out value="${data.CLASSCODE}"/></td>
                <td>${data.EXAMSTARTTIME}</td>
                <td>${data.EXAMENDTIME}</td>
                <td><c:out value="${data.EXAMTIME}"/>(분)</td>
            </tr>
            </c:forEach>
        </form>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="7">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!--paging  -->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->

</div>
<!--//content -->
