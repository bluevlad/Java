<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><head>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mt_upg.css' />" >

<script type="text/javascript">
    if("back"=="${returnData}"){
        alert("성적데이타가 등록되지 않아 확인이 불가능합니다");
        history.back();
    }
</script>
</head>


<div id="content">
    <h2>● 경영관리 > <strong>개인별 모의고사통계</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonList.do' />">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}" />
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}" />
<input type="hidden" id="SEARCHROUND" name="SEARCHROUND" value="${params.SEARCHROUND}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}" />
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" />
<input type="hidden" id="IDENTYID" name="IDENTYID" value="${params.IDENTYID}" />
<input type="hidden" id="MOCKCODE" name="MOCKCODE" value="${params.MOCKCODE}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${params.USER_ID}" />

        <ul class="tabA">
            <li><a href="<c:url value='/stats/statsPersonTotalList.do'/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&IDENTYID=${params.IDENTYID}&MOCKCODE=${params.MOCKCODE}&USER_ID=${params.USER_ID}">전체 성적 분석</a></li>
            <li><a href="<c:url value='/stats/statsPersonSubjectList.do'/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&IDENTYID=${params.IDENTYID}&MOCKCODE=${params.MOCKCODE}&USER_ID=${params.USER_ID}" class="active">과목별 문항 분석</a></li>
        </ul>

        <c:set var="TITEMID" value="" />
        <c:set var="ATITEMID" value="" />
        <c:forEach items="${subjectlist}" var="item" varStatus="index">
        <c:set var="TITEMID" value="${item.ITEMID}" />
        <ul class="allAssay01">
            <li>
            <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
                <caption>${item.SUBJECT_NM} 문항별 분석 <span>■ 색은 틀린부분 표시</span></caption>
                <tr>
                    <th>구분</th>
                    <c:forEach var="i" begin="1" end="${item.QUESTIONNUM}" step="1">
                        <th>${i}</th>
                    </c:forEach>
                </tr>
                <c:set var="OPPERERYN" value="" />
                <c:forEach items="${subjectanswerlist}" var="item1" varStatus="index1">
                    <c:if test="${item1.ITEMID eq TITEMID}">
                        <c:set var="OPPERERYN" value="Y" />
                    </c:if>
                </c:forEach>
                <c:if test="${OPPERERYN eq 'Y'}">
                    <tr>
                        <td class="tdCenter">정답</td>
                        <c:forEach items="${subjectanswerlist}" var="item1" varStatus="index1">
                            <c:if test="${item1.ITEMID eq TITEMID}">
                                <td class="tdCenter">${item1.QANSWERNUMBER}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="tdCenter">마킹</td>
                        <c:forEach items="${subjectanswerlist}" var="item1" varStatus="index1">
                            <c:if test="${item1.ITEMID eq TITEMID}">
                                <c:if test="${item1.CORRECTYN eq 'Y'}"><td class="tdCenter"></c:if>
                                <c:if test="${item1.CORRECTYN ne 'Y'}"><td class="tdOdab"></c:if>
                                    ${item1.UANSWERNUMBER}
                                </td>
                            </c:if>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="tdCenter">정답률</td>
                        <c:forEach items="${subjectanswerlist}" var="item1" varStatus="index1">
                            <c:if test="${item1.ITEMID eq TITEMID}">
                                <td class="tdCenter">${item1.ARATIO}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="tdCenter">난이도</td>
                        <c:forEach items="${subjectanswerlist}" var="item1" varStatus="index1">
                            <c:if test="${item1.ITEMID eq TITEMID}">
                                <td class="tdCenter">${item1.LEVELDIFFICULTYNAME}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:if>
                <c:if test="${OPPERERYN ne 'Y'}">
                    <tr>
                        <td colspan="${item.QUESTIONNUM+1}" class="tdCenter"><span>답안정보가 없습니다</span></td>
                    </tr>
                </c:if>
            </table>
            </li>
            <c:if test="${index.index < subjectlist.size() }">
            <li></li>
            <li></li>
            </c:if>
        </ul>
        </c:forEach>
        <c:forEach items="${subjectlist}" var="item" varStatus="index">
        <c:set var="TITEMID" value="${item.ITEMID}" />
        <ul class="allAssay01">
            <li>
                <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
                  <caption>
                  ${item.SUBJECT_NM} 영역 및 학습요소
                  </caption>
                  <tr>
                    <th width="20%">구분</th>
                    <th width="10%">개수</th>
                    <th width="10%">평균</th>
                    <th width="30%">관련문항</th>
                    <th>오답문항</th>
                  </tr>
                <c:forEach items="${subjectinfolist}" var="item1" varStatus="index1">
                    <c:if test="${item1.ITEMID eq TITEMID}">
                      <tr>
                        <td class="tdCenter">${item1.SUBJECTAREA}</td>
                        <td class="tdCenter">
                            <c:set var="OANSWER" value="0" />
                            <c:set var="TANSWER" value="0" />
                            <c:set var="ONUMBER" value="" />
                            <c:set var="TNUMBER" value="" />
                            <c:forEach items="${subjectanswerlist}" var="item2" varStatus="index2">
                                <c:if test="${item1.ID eq item2.QUESTIONRANGE}">
                                    <c:set var="TANSWER" value="${TANSWER+1}" />
                                    <c:if test="${!empty TNUMBER}"><c:set var="TNUMBER" value="${TNUMBER} , (${item2.QUESTIONNUMBER})" /></c:if>
                                    <c:if test="${empty TNUMBER}"><c:set var="TNUMBER" value="(${item2.QUESTIONNUMBER})" /></c:if>
                                    <c:if test="${'Y' eq item2.CORRECTYN}">
                                        <c:set var="OANSWER" value="${OANSWER+1}" />
                                    </c:if>
                                    <c:if test="${'Y' ne item2.CORRECTYN}">
                                        <c:if test="${!empty ONUMBER}"><c:set var="ONUMBER" value="${ONUMBER} , (${item2.QUESTIONNUMBER})" /></c:if>
                                        <c:if test="${empty ONUMBER}"><c:set var="ONUMBER" value="(${item2.QUESTIONNUMBER})" /></c:if>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            ${OANSWER} / ${TANSWER}
                        </td>
                        <td class="tdCenter"><fmt:formatNumber value="${item1.QPERCENT}" pattern="0.00"/></td>
                        <td class="tdCenter">${TNUMBER}</td>
                        <td class="tdCenter">${ONUMBER}</td>
                      </tr>
                    </c:if>
                </c:forEach>
                </table>
            </li>
            <c:if test="${index.index < subjectlist.size() }">
            <li></li>
            <li></li>
            </c:if>
        </ul>
        </c:forEach>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href='javascript:fn_List();'>목록</a></li>
    </ul>
    <!--//버튼-->

</form>
</div>
<!--//content -->

<script type="text/javascript">
//목록으로
function fn_List(){
    $("#form").attr("action","<c:url value='/stats/statsPersonList.do' />");
    $("#form").submit();
}
</script>
