<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<script type="text/javascript">
    if("back"=="${returnData}"){
        alert("성적데이타가 등록되지 않아 확인이 불가능합니다");
        history.back();
    }
</script>
</head>

<div id="content">
    <h2>● 경영관리 > <strong>전체모의고사통계</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonList.do' />">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}" />
<input type="hidden" id="SEARCHROUND" name="SEARCHROUND" value="${params.SEARCHROUND}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}" />
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" />
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}" />
    <table class="table01">
      <tr>
        <th>모의고사명</th>
        <th></th>
      </tr>
      <tr>
        <td class="tdCenter">${totalViewInfo[0].MOCKNAME}</td>
        <td class="tdLeft">
            <div class="item">
                <span class="btn_pack medium"><button type="button" onClick="javascript:fn_Print();">인쇄</button></span>
            </div>
        </td>
      </tr>
    </table>

    <c:forEach items="${resultList}" var="item1" varStatus="index1">
        <div class="form_table" style="margin-top:30px; float:left; width:100%; margin-left:0px;">
            <ul class="allAssay">
               <li>
                    <c:if test="${empty item1.TblH}">
                        <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
                            <tr>
                                <th style="width:80px;">${item1.TITLE}</th>
                                <th>[${item1.TITLE}] 시험에 응시한 인원이 없습니다</th>
                            </tr>
                        </table>
                    </c:if>

                    <c:if test="${!empty item1.TblH}">
                        <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
                              <tr>
                                <th rowspan="2" style="width:80px;">${item1.TITLE}</th>
                                <c:forEach items="${item1.TblH}" var="item2" varStatus="index2">
                                    <c:if test="${item2.SUBJECTTYPEDIVISION eq '1' }"><th rowspan="2">${item2.SUBJECT_NM}</th></c:if>
                                    <c:if test="${item2.SUBJECTTYPEDIVISION ne '1' }"><th colspan="2">${item2.SUBJECT_NM}</th></c:if>
                                </c:forEach>
                                <th rowspan="2" style="width:80px;">평균</th>
                              </tr>
                              <tr>
                                <c:forEach items="${item1.TblH}" var="item2" varStatus="index2">
                                    <c:if test="${item2.SUBJECTTYPEDIVISION ne '1' }">
                                        <th width="6%">원점수</th>
                                        <th width="7%">조정점수</th>
                                    </c:if>
                                </c:forEach>
                              </tr>
                            <c:forEach items="${item1.TblArr}" var="steps" varStatus="loop">
                                <tr>
                                    <td class="tdCenter">
                                        <c:if test="${loop.index eq '0' }">전체평균</c:if>
                                        <c:if test="${loop.index eq '1' }">최고</c:if>
                                        <c:if test="${loop.index eq '2' }">응시인원</c:if>
                                    </td>
                                        <c:forEach items="${steps}" var="item3" varStatus="index3">
                                            <td class="tdCenter">
                                                <c:if test="${empty item3 or item3 < 1}">-</c:if>
                                                <c:if test="${!empty item3 and item3 > 0}">${item3}</c:if>
                                            </td>
                                        </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </li>
            </ul>
        </div>
    </c:forEach>

    <br/>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_List()">목록</a></li>
    </ul>
    <!--//버튼-->

</form>
</div>

<script type="text/javascript">
// 목록으로
function fn_List(){
    $("#form").attr("action","<c:url value='/stats/statsTotalList.do' />");
    $("#form").submit();
}
// 인쇄
function fn_Print(){
    print();
}
</script>
