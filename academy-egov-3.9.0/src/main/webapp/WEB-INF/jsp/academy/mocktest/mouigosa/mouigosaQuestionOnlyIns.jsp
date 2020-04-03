<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var S_currentPage = "${searchMap.S_currentPage}";
var S_pageRow = "${searchMap.S_pageRow}";

var QUESTIONREGISTRATIONOPTION = "${searchMap.QUESTIONREGISTRATIONOPTION}";

window.onload = function () {
}

//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#popFrm').attr('action','<c:url value="/mouigosa/updateQuestionOnlyMouigosa.pop"/>').submit();
}

//추가
function checkParam(ITEMID,QUESTIONNUMBER) {
    $("#ITEMID2").val(ITEMID);
    $("#QUESTIONNUMBER2").val(QUESTIONNUMBER);

    opener.name="list";
    popFrm.target = opener.name;
    $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaQuestionOnlyUpdate.do"/>').submit();
    self.close();
}

//프린트 팝업
function PrintPopup(ITEMID,QUESTIONNUMBER,sts) {
    var go_url = '<c:url value="/mouigosa/printMouigosa.pop"/>?'
                + 'ITEMID=' + ITEMID
                + '&QUESTIONREGISTRATIONOPTION=' + QUESTIONREGISTRATIONOPTION 
                + '&QUESTIONNUMBER=' + QUESTIONNUMBER 
                + '&sts=' + sts;

    window.open(go_url, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=600,height=700');
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
<input type="hidden" id="S_EXAMYEAR" name="S_EXAMYEAR" value="${searchMap.S_EXAMYEAR}">
<input type="hidden" id="S_EXAMROUND" name="S_EXAMROUND" value="${searchMap.S_EXAMROUND}">
<input type="hidden" id="S_searchFlag" name="S_searchFlag" value="${searchMap.S_searchFlag}">
<input type="hidden" id="S_searchKeyWord" name="S_searchKeyWord" value="${searchMap.S_searchKeyWord}">

<input type="hidden" id="currentPage" name="currentPage">
<input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
<input type="hidden" id="QUESTIONNUMBER" name="QUESTIONNUMBER" value="${searchMap.QUESTIONNUMBER}" />
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="${searchMap.SUBJECT_CD}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />

<input type="hidden" id="ITEMID2" name="ITEMID2" />
<input type="hidden" id="QUESTIONNUMBER2" name="QUESTIONNUMBER2" />

    <div id="content_pop" style="width:770px;position:relative;">
    <h2>● <strong>다른 문제 불러오기</strong></h2>

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
                    문제 : ${searchMap.QUESTIONNUMBER}번 / ${searchMap.EXAMYEAR}년 ${searchMap.EXAMROUND}회차 ${searchMap.SUBJECT_NM} (${searchMap.PROF_NM}) / 문항갯수 : ${searchMap.QUESTIONNUM} / 옵션 : ${searchMap.QUESTIONREGISTRATIONOPTION}
                </div>
            </td>
        </tr>
        <tr>
            <th>검색</th>
            <td>
                <input type="hidden" name="_method" value="get">
                <div class="item" style="margin-left:0px;">
                    <select style="width:55px;"   id="S_EXAMYEAR2" name="S_EXAMYEAR2">
                        <option value="">년도</option>
                        <option value="2013"  <c:if test="${searchMap.S_EXAMYEAR2 == '2013'}">selected</c:if>>2013</option>
                        <option value="2014"  <c:if test="${searchMap.S_EXAMYEAR2 == '2014'}">selected</c:if>>2014</option>
                        <option value="2015"  <c:if test="${searchMap.S_EXAMYEAR2 == '2015'}">selected</c:if>>2015</option>
                        <option value="2016"  <c:if test="${searchMap.S_EXAMYEAR2 == '2016'}">selected</c:if>>2016</option>
                        <option value="2017"  <c:if test="${searchMap.S_EXAMYEAR2 == '2017'}">selected</c:if>>2017</option>
                        <option value="2018"  <c:if test="${searchMap.S_EXAMYEAR2 == '2018'}">selected</c:if>>2018</option>
                        <option value="2019"  <c:if test="${searchMap.S_EXAMYEAR2 == '2019'}">selected</c:if>>2019</option>
                    </select>
                    &nbsp;
                    <select style="width:50px;"   id="S_EXAMROUND2" name="S_EXAMROUND2">
                        <option value="">회차</option>
                        <option value="1"  <c:if test="${searchMap.S_EXAMROUND2 == '1'}">selected</c:if>>1</option>
                        <option value="2"  <c:if test="${searchMap.S_EXAMROUND2 == '2'}">selected</c:if>>2</option>
                        <option value="3"  <c:if test="${searchMap.S_EXAMROUND2 == '3'}">selected</c:if>>3</option>
                        <option value="4"  <c:if test="${searchMap.S_EXAMROUND2 == '4'}">selected</c:if>>4</option>
                        <option value="5"  <c:if test="${searchMap.S_EXAMROUND2 == '5'}">selected</c:if>>5</option>
                        <option value="6"  <c:if test="${searchMap.S_EXAMROUND2 == '6'}">selected</c:if>>6</option>
                        <option value="7"  <c:if test="${searchMap.S_EXAMROUND2 == '7'}">selected</c:if>>7</option>
                        <option value="8"  <c:if test="${searchMap.S_EXAMROUND2 == '8'}">selected</c:if>>8</option>
                        <option value="9"  <c:if test="${searchMap.S_EXAMROUND2 == '9'}">selected</c:if>>9</option>
                        <option value="10"  <c:if test="${searchMap.S_EXAMROUND2 == '10'}">selected</c:if>>10</option>
                        <option value="11"  <c:if test="${searchMap.S_EXAMROUND2 == '11'}">selected</c:if>>11</option>
                        <option value="12"  <c:if test="${searchMap.S_EXAMROUND2 == '12'}">selected</c:if>>12</option>
                        <option value="13"  <c:if test="${searchMap.S_EXAMROUND2 == '13'}">selected</c:if>>13</option>
                        <option value="14"  <c:if test="${searchMap.S_EXAMROUND2 == '14'}">selected</c:if>>14</option>
                        <option value="15"  <c:if test="${searchMap.S_EXAMROUND2 == '15'}">selected</c:if>>15</option>
                        <option value="16"  <c:if test="${searchMap.S_EXAMROUND2 == '16'}">selected</c:if>>16</option>
                        <option value="17"  <c:if test="${searchMap.S_EXAMROUND2 == '17'}">selected</c:if>>17</option>
                        <option value="18"  <c:if test="${searchMap.S_EXAMROUND2 == '18'}">selected</c:if>>18</option>
                        <option value="19"  <c:if test="${searchMap.S_EXAMROUND2 == '19'}">selected</c:if>>19</option>
                        <option value="20"  <c:if test="${searchMap.S_EXAMROUND2 == '20'}">selected</c:if>>20</option>
                    </select>
                    &nbsp;
                    <select style="width:90px;"   id="S_QUESTIONRANGE2" name="S_QUESTIONRANGE2">
                        <option value="">영역</option>
                        <c:forEach items="${subject_area_list}"  var="subject_area_list">
                            <option value="${subject_area_list.ID }" <c:if test="${searchMap.S_QUESTIONRANGE2 == subject_area_list.ID}">selected</c:if>>${subject_area_list.QUESTIONRANGENAME }</option>
                        </c:forEach>
                    </select>
                    &nbsp;&nbsp;
                    <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
                </div>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--테이블-->
    <table class="table02">
        <caption>
        </caption>
        <colgroup>
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="7%">
        <col width="*">
        <col width="8%">
        <col width="10%">
        <col width="8%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">문제번호</th>
            <th scope="col">문제</th>
            <th scope="col">해설</th>
            <th scope="col">정답</th>
            <th scope="col">영역</th>
            <th scope="col">난이도</th>
            <th scope="col">활용여부</th>
            <th scope="col">선택</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
            <td>${list.QUESTIONNUMBER}</td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','1')">문제</a></td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','2')">해설</a></td>
            <td>${list.ANSWERNUMBER}</td>
            <td>${list.QUESTIONRANGENAME}</td>
            <td>${list.LEVELDIFFICULTYNAME}</td>
            <td>${list.USEFLAGNAME}</td>
            <td><span class="btn_pack small"><button type="button" onclick="checkParam('${list.ITEMID}','${list.QUESTIONNUMBER}')">추가</button></span></td>
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

    <!-- <a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->
    </div>
</form>
<!--//팝업-->