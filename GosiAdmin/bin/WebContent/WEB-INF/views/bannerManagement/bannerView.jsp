<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %><head>
<script type="text/javascript">
//수정
function fn_Submit(){
    if(confirm("배너를 수정하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/update.do' />");
        $("#frm").submit();
    }
}

function fn_Delete(){
    if(confirm("등록된 배너가 모두 삭제됩니다. 배너를 삭제하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/delete.do' />");
        $("#frm").submit();
    }
}

function fn_List(){
    $("#frm").attr("action","<c:url value='/bannerManagement/bannerMgtList.do' />");
    $("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}"/>

<input type="hidden" id="SEQ" name="SEQ" value="${params.SEQ}"/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${view[0].ONOFF_DIV}"/>
<input type="hidden" id="SCREEN_GUBUN" name="SCREEN_GUBUN" value="${view[0].SCREEN_GUBUN}"/>
<input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${view[0].CATEGORY_CD}"/>
        <h2>● 사이트관리 > <strong>배너관리</strong></h2>
        <table class="table01">
            <tr>
                <th>직렬</th>
                <td class="tdLeft">
                    <c:forEach items="${catekindlist}" var="item" varStatus="loop">
                        <c:if test="${ view[0].CATEGORY_CD eq item.CODE }">${item.NAME}</c:if>
                    </c:forEach>
                    <c:forEach items="${menukindlist}" var="item" varStatus="loop">
                        <c:if test="${ view[0].CATEGORY_CD eq item.CODE }">${item.NAME}</c:if>
                    </c:forEach>
                </td>
            </tr>
            <!--직렬선택 /-->
            <tr>
                <th width="10%">번호</th>
                <td class="tdLeft tdValign"><input type="text" id="BANNER_NO" name="BANNER_NO" size="10" value="${view[0].BANNER_NO}" readonly="true"></td>
            </tr>
            <tr>
                <th>배너명</th>
                <td><input type="text" id="BANNER_TITLE" name="BANNER_TITLE" value="${view[0].BANNER_TITLE}" size="100%"></td>
            </tr>
            <tr>
                <th>배너유형</th>
                <td class="tdLeft tdValign"><span class="tdLeft">
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="I" <c:if test="${ view[0].BANNER_TYP eq 'I' }">checked</c:if>><span class="txt03">이미지등록</span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="L" <c:if test="${ view[0].BANNER_TYP eq 'L' }">checked</c:if>><span class="txt04">강좌선택</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="B" <c:if test="${ view[0].BANNER_TYP eq 'B' }">checked</c:if>><span class="txt05">게시판연결</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="H" <c:if test="${ view[0].BANNER_TYP eq 'H' }">checked</c:if>><span class="txt06">HTML</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="P" <c:if test="${ view[0].BANNER_TYP eq 'P' }">checked</c:if>><span class="txt07">교수</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="T" <c:if test="${ view[0].BANNER_TYP eq 'T' }">checked</c:if>><span class="txt08">모의고사</span></span>
                </td>
            </tr>
            <%--  <tr>
                <th>적용날짜</th>
                <td class="tdLeft tdValign">
                    시작일 <input type="text" id="OPEN_STARTDATE" name="OPEN_STARTDATE" value="${ view[0].OPEN_STARTDATE }" size="10">
                 ~ 종료일 <input type="text" id="OPEN_ENDDATE" name="OPEN_ENDDATE" value="${ view[0].OPEN_ENDDATE }" size="10">
                  </td>
            </tr> --%>
            <tr>
                <th>적용여부</th>
                <td class="tdLeft tdValign"><span class="tdLeft">
                  <input type="radio" name="ISUSE" id="ISUSE" value="Y" <c:if test="${ view[0].ISUSE eq 'Y' }">checked</c:if>><span class="txt03">적용</span>
                  <input type="radio" name="ISUSE" id="ISUSE" value="N" <c:if test="${ view[0].ISUSE eq 'N' }">checked</c:if>><span class="txt04">비적용</span></span>
                </td>
            </tr>
        </table>
        <!--//테이블--> 
    
        <!--버튼-->
        <ul class="boardBtns">
          <li><a href="javascript:fn_Submit();">수정</a></li>
          <li><a href="javascript:fn_Delete();">삭제</a></li>
          <li><a href="javascript:fn_List();">목록</a></li>
        </ul>
        <!--//버튼--> 
</form>
</div>
<!--//content --> 
