<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
<script type="text/javascript">
    if("back"=="${returnData}"){
        alert("성적데이타가 등록되지 않아 확인이 불가능합니다");
        history.back();
    }
    
    // 목록으로
	function fn_List(){
    	$("#form").attr("action","<c:url value='/stats/statsPersonList.do' />");
        $("#form").submit();
	}
</script>
</head>

<div id="content">
    <h2>● 경영관리 > <strong>개인별 모의고사통계</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonTotalList.do' />">
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
<input type="hidden" id="IDENTYID" name="IDENTYID" value="${params.IDENTYID}" />
<input type="hidden" id="MOCKCODE" name="MOCKCODE" value="${params.MOCKCODE}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${totalinfolist[0].USER_ID}" />

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href='javascript:fn_List();'>목록</a></li>
    </ul>
    <!--//버튼-->
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">모의고사명</th>
            <td scope="col" colspan="2" style="text-align:left;">
                <div class="item">
                    ${totalinfolist[0].MOCKNAME}
                </div>
            </td>
        </thead>
        <tbody>
        <tr>
            <th scope="col">이름</th>
            <td scope="col" colspan="2" style="text-align:left;">
                <div class="item">
                    ${totalinfolist[0].USER_NM}
                </div>
            </td>
        </tr>
        <tr>
            <th scope="col">응시번호</th>
            <td scope="col" colspan="2" style="text-align:left;">
                <div class="item">
                    ${totalinfolist[0].IDENTYID}
                </div>
            </td>
        </tr>
        <tr>
            <th scope="col">직급 / 직렬</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    ${totalinfolist[0].CLASSCODENM} / ${totalinfolist[0].CLASSSERIESCODENM}
                </div>
            </td>
            <td scope="col" style="text-align:right;">
                <div class="item">
                    <span class="btn_pack medium"><button type="button" onClick="javascript:fn_Detail();">상세성적보기</button></span>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="form_table_s" style="margin-top:30px; float:left; width:100%;">
        <ul class="allAssay01">
            <li>
                <table class="table01 mgT20 tbl_type" border="0" cellspacing="0" cellpadding="0">
                <caption><h2>과목별 득점분석</h2></caption>
                    <tr>
                        <th rowspan="2">구분</th>
                        <c:forEach items="${totalinfo2_TblH}" var="item" varStatus="index">
                            <c:if test="${item.SUBJECTTYPEDIVISION eq '1' }"><th rowspan="2">${item.SUBJECT_NM}</th></c:if>
                            <c:if test="${item.SUBJECTTYPEDIVISION ne '1' }"><th colspan="2">${item.SUBJECT_NM}</th></c:if>
                        </c:forEach>
                            <th rowspan="2">평균/석차<br/>(조정기준)</th>
                        </tr>
                    <tr>
                    <c:forEach items="${totalinfo2_TblH}" var="item" varStatus="index">
                        <c:if test="${item.SUBJECTTYPEDIVISION ne '1' }">
                            <th width="12%">원점수</th>
                            <th width="12%">조정점수</th>
                        </c:if>
                    </c:forEach>
                    </tr>
                    <c:forEach items="${TblArr}" var="steps" varStatus="loop">
                    <tr>
                        <td class="tdCenter">
                            <c:if test="${loop.index eq '0' }">본인</c:if>
                            <c:if test="${loop.index eq '1' }">전체평균</c:if>
                            <c:if test="${loop.index eq '2' }">최고</c:if>
                            <c:if test="${loop.index eq '3' }">과목석차</c:if>
                        </td>
                        <c:forEach items="${steps}" var="item" varStatus="index">
                            <td class="tdCenter">${item}</td>
                        </c:forEach>
                    </tr>
                    </c:forEach>
                </table>
            </li>
        </ul>
    </div>
</form>
</div>
<!--//content -->

<script type="text/javascript">
function fn_Detail(){
    $("#form").attr("action", "<c:url value='/stats/statsPersonTotalList.do'/>");
    $("#form").submit();
}
</script>
