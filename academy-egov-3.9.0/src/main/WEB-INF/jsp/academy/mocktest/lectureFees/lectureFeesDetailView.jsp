<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
<meta name="decorator" content="index">
<script type="text/javascript">
function goLectureList() {
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesList.do' />");
    $("#form").submit();
}
function fn_excelDownload(){
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesExcelDownLoad.do' />");
    $("#form").submit();
}
</script>
</head>

<div id="content">
    <h2>● 모의고사 > 강사료정산 > <strong>강사료정산</strong></h2>
<form id="form" name="form" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
<input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="PROFCODE" name="PROFCODE" value="${params.PROFCODE}"/>
<input type="hidden" id="TMEMBERNAME" name="TMEMBERNAME" value="${params.TMEMBERNAME}"/>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_excelDownload()">Excel</a></li>
    </ul>
    <!--//버튼-->

    <div><strong>${params.TMEMBERNAME} 강사님의 수강료지급 현황입니다.</strong></div>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="3%">
            <col width="12%">
            <col width="7%">
            <col width="">
            <col width="8%">
            <col width="5%">
            <col width="8%">
            <col width="7%">
            <col width="6%">
            <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">NO</th>
            <th scope="col">이름/ID</th>
            <th scope="col">응시형태</th>
            <th scope="col">모의고사명</th>
            <th scope="col">신청일</th>
            <th scope="col">결제금액</th>
            <th scope="col">강사료지급률%</th>
            <th scope="col">입금구분</th>
            <th scope="col">수수료</th>
            <th scope="col">강사지급액</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}"><!--String[] keys = {"USRNAMEID","EXAMTYPE","MOCKNAME","REG_DT","MONEY","PROPERCENT","PAYMENTPARAM","COMMITION","ALLOWANCE"};  -->
            <input type="hidden" id="currentPage" name="currentPage">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td><c:out value="${status.index+1}"/></td>
                <td><c:out value="${data.USRNAMEID}"/></td>
                <td><c:out value="${data.EXAMTYPE}"/></td>
                <td><c:out value="${data.MOCKNAME}"/></td>
                <td><c:out value="${data.REG_DT}"/></td>
                <td><fmt:formatNumber value='${data.MONEY}' pattern="#,###" type="number"/></td>
                <td><c:out value="${data.PROPERCENT}"/></td>
                <td><c:out value="${data.PAYMENTPARAM}"/></td>
                <td><c:out value="${data.COMMITION}"/></td>
                <td><c:out value="${data.ALLOWANCE}"/></td>
            </tr>
            </c:forEach>
            <tr ><td colspan="10" style="text-align:right;">신용카드 입금 : <c:out value="${lfMap.cardTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">계좌이체 : <c:out value="${lfMap.realTimeTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">현금 : <c:out value="${lfMap.cashTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">가상계좌 입금 : <c:out value="${lfMap.vaconTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">공급가액 : <c:out value="${lfMap.supplyMoneyTotal}"/></td></tr>
            <tr ><td colspan="10"></td></tr>
            <tr ><td colspan="10" style="text-align:right;">신용카드 수수료 : <c:out value="${lfMap.cardChargeTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">계좌이체 수수료 : <c:out value="${lfMap.realTimeChargeTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">가상계좌 수수료 : <c:out value="${lfMap.vaconChargeTotal}"/></td></tr>
            <tr ><td colspan="10"></td></tr>
            <tr ><td colspan="10" style="text-align:right;">정산합계 : <c:out value="${lfMap.moneyTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">강사료 : <c:out value="${lfMap.TechTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">원천세 : <c:out value="${lfMap.onechenTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">주민세 : <c:out value="${lfMap.juminTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">실지급액 : <c:out value="${lfMap.realMoneyTotal}"/></td></tr>
            <tr ><td colspan="10"></td></tr>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="10">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
      </tbody>
    </table>

    <div><strong>${params.TMEMBERNAME} 강사님의 환불 현황입니다.</strong></div>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="3%">
            <col width="12%">
            <col width="7%">
            <col width="">
            <col width="8%">
            <col width="5%">
            <col width="8%">
            <col width="7%">
            <col width="6%">
            <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">NO</th>
            <th scope="col">이름/ID</th>
            <th scope="col">응시형태</th>
            <th scope="col">모의고사명</th>
            <th scope="col">신청일</th>
            <th scope="col">결제금액</th>
            <th scope="col">입금구분</th>
            <th scope="col">수수료</th>
            <th scope="col">강사료지급률%</th>
            <th scope="col">강사지급액</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty refundlist}"><!--String[] keys = {"USRNAMEID","EXAMTYPE","MOCKNAME","REG_DT","MONEY","PROPERCENT","PAYMENTPARAM","COMMITION","ALLOWANCE"};  -->
            <input type="hidden" id="currentPage" name="currentPage">
            <c:forEach items="${refundlist}" var="data" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td><c:out value="${data.USRNAMEID}"/></td>
                <td><c:out value="${data.EXAMTYPE}"/></td>
                <td><c:out value="${data.MOCKNAME}"/></td>
                <td><c:out value="${data.REG_DT}"/></td>
                <td><fmt:formatNumber value='${data.MONEY}' pattern="#,###" type="number"/></td>
                <td><c:out value="${data.PAYMENTPARAM}"/></td>
                <td><c:out value="${data.PROPERCENT}"/></td>
                <td><c:out value="${data.COMMITION}"/></td>
                <td><c:out value="${data.ALLOWANCE}"/></td>
            </tr>
            </c:forEach>
            <tr ><td colspan="10" style="text-align:right;">신용카드 입금 : <c:out value="${lfRefundMap.recardTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">가상계좌 입금 : <c:out value="${lfRefundMap.revaconTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">실시간 입금 : <c:out value="${lfRefundMap.rerealTimeTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">현금 : <c:out value="${lfRefundMap.recashTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">환불 총금액 : <c:out value="${lfRefundMap.remoneyTotal}"/></td></tr>
            <tr ><td colspan="10"></td></tr>
            <tr ><td colspan="10" style="text-align:right;">강사료 : <c:out value="${lfRefundMap.reTechTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">원천세 : <c:out value="${lfRefundMap.reonechenTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">주민세 : <c:out value="${lfRefundMap.rejuminTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">실환불액 : <c:out value="${lfRefundMap.rerealMoneyTotal}"/></td></tr>
            <tr ><td colspan="10" style="text-align:right;">실 지급액 : <c:out value="${lfRefundMap.silMoenyTotal}"/></td></tr>
            <tr ><td colspan="10"></td></tr>
        </c:if>
        <c:if test="${empty refundlist}">
            <tr bgColor=#ffffff align=center>
                <td colspan="10">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
      </tbody>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:goLectureList()">목록</a></li>
    </ul>
    <!--//버튼-->
   </form>

</div>
<!--//content -->
