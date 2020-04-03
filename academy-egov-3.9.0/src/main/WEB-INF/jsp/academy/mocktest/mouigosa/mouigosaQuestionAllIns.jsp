<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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

window.onload = function () {
}

//등록
function checkParam() {
    if(!formValidate("popFrm")) return;

    opener.name="list";
    popFrm.target = opener.name;
    $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaQuestionAllUpdate.do"/>').submit();
    self.close();
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

<input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="${searchMap.SUBJECT_CD}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />

<div id="content_pop" style="width:770px;position:relative;">
    <h2>● <strong>문제지정</strong></h2>

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
                    ${searchMap.EXAMYEAR}년 ${searchMap.EXAMROUND}회차 ${searchMap.SUBJECT_NM} (${searchMap.PROF_NM}) / 문항갯수 : ${searchMap.QUESTIONNUM} / 옵션 : ${searchMap.QUESTIONREGISTRATIONOPTION}
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
        <col width="">
        <col width="">
        <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">문제</th>
            <th scope="col">년도</th>
            <th scope="col">회차</th>
            <th scope="col">문제번호</th>
        </tr>
        </thead>
        <tbody>
      <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
          <td>${list.QUESTIONNUMBER}<input type="hidden" id="QUESTIONNUMBER" name="QUESTIONNUMBER" value="${list.QUESTIONNUMBER}" /></td>
          <td>
            <select id="EXAMYEAR" name="EXAMYEAR" class="must" title="년차을 선택해 주십시오.">
            <c:forEach items="${list2}"  var="list2">
                <option value="${list2.EXAMYEAR }">${list2.EXAMYEAR }</option>
            </c:forEach>
            </select>
          </td>
         <td>
            <select id="EXAMROUND" name="EXAMROUND" class="must" title="회차 선택해 주십시오.">
            <c:forEach items="${list3}"  var="list3">
                <option value="${list3.EXAMROUND }">${list3.EXAMROUND }</option>
            </c:forEach>
            </select>
          </td>
          <td>
            <select id="QUESTIONNUMBER2" name="QUESTIONNUMBER2" class="must" title="문제번호를 선택해 주십시오.">
            <c:forEach items="${list4}"  var="list4">
                <option value="${list4.QUESTIONNUMBER }">${list4.QUESTIONNUMBER }</option>
            </c:forEach>
            </select>
          </td>
        </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="4">데이터가 존재하지 않습니다.</td>
        </tr>
      </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">등록</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
    <!-- <a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->
</div>

</form>
<!--//팝업-->
