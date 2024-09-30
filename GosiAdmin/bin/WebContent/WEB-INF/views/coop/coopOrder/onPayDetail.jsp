<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

$(function() {
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("searchStartDate");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("searchEndDate");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function goList() {
    $("#form").attr("action", "<c:url value='/coopOrder/onPayDetail.do' />");
    $("#form").submit();
}

function fn_excel_down() {
    $("#form").attr("action", "<c:url value='/coopOrder/excel.do' />");
    $("#form").submit();
}

function onOnlyNumber(obj) {
     for (var i = 0; i < obj.value.length ; i++){
      chr = obj.value.substr(i,1);  
      chr = escape(chr);
      key_eg = chr.charAt(1);
      if (key_eg == "u"){
       key_num = chr.substr(i,(chr.length-1));   
       if((key_num < "AC00") || (key_num > "D7A3")) { 
        event.returnValue = false;
       }    
      }
     }
     if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
     } else {
      event.returnValue = false;
     }
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

    <h2>● 상품주문관리 > <strong>남서울대 매출 관리</strong></h2>

    <table class="table01">
        <tr>
          <td>
              &nbsp;&nbsp;
              주문일
              <input type="text" id="searchStartDate" name="searchStartDate" maxlength="10" class="i_text" value="${searchStartDate}" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              ~
              <input type="text" id="searchEndDate" name="searchEndDate" maxlength="10" class="i_text" value="${searchEndDate}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
              &nbsp;&nbsp;&nbsp;
              <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"  onclick="goList()">검색</button></span>
        </td>
        </tr>
    </table>
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    <!--//버튼-->
    <table class="table02">
        <tr>
            <th rowspan="2">ID</th>
            <th rowspan="2">수강자</th>
            <th rowspan="2">강의명</th>
            <th rowspan="2">신청일</th>
            <th rowspan="2">수강시작</th>
            <th rowspan="2">수강종료</th>
            <th rowspan="2">금액</th>
            <th colspan="3">입금구분</th>
        </tr>
        <tr>
            <th>결제</th>
            <th>수수료율</th>
            <th>수수료</th>
        </tr>
        <tbody>
            <c:set var="pay110" value="0"/>
            <c:set var="pay120" value="0"/>
            <c:set var="pay130" value="0"/>
            <c:set var="pay100" value="0"/>
            <c:set var="pay110_su" value="0"/>
            <c:set var="pay120_su" value="0"/>
            <c:set var="pay130_su" value="0"/>
            <c:set var="price" value="0"/>
            <c:set var="etc1" value="0"/>
            <c:set var="etc2" value="0"/>
            
            <c:forEach items="${list}" var="item" varStatus="loop">
            <c:set var="pay110" value="${pay110 + item.PAY110_PRICE}" />
            <c:set var="pay120" value="${pay120 + item.PAY120_PRICE}" />
            <c:set var="pay130" value="${pay130 + item.PAY130_PRICE}" />
            <c:set var="pay100" value="${pay100 + item.PAY100_PRICE}" />
            
            <c:set var="pay110_su" value="${pay110_su + item.PAY110_SUSU}"/>
            <c:set var="pay120_su" value="${pay120_su + item.PAY120_SUSU}"/>
            <c:set var="pay130_su" value="${pay130_su + item.PAY130_SUSU}"/>

            <c:set var="price" value="${price + item.TEACHER_PAY}"/>
            <c:set var="etc1" value="${etc1 + item.TEACHER_PAY_TEMP1}"/>
            <c:set var="etc2" value="${etc2 + item.TEACHER_PAY_TEMP2}"/>
            
            <tr>
                <td>${item.USERID}</td>
                <td>${item.USER_NM}</td>
                <td>${item.SUBJECT_TITLE}</td>
                <td>${item.REG_DT}</td>
                <td>${item.START_DATE}</td>
                <td>${item.END_DATE}</td>
                <td><fmt:formatNumber value="${item.TOTAL_PAY}" groupingUsed="true"/></td>
                <td>${item.PAYCODE}</td>
                <td>${item.SUSU}</td>
                <td>${item.SUSU_PAY}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table01">
            <tr>
                <th>신용카드 입금</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay110}" groupingUsed="true"/></b></font></td>
                <th>가상계좌  입금</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay120}" groupingUsed="true"/></b></font></td>
                <th>계좌이체  입금</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay130}" groupingUsed="true"/></b></font></td>
            </tr>
            <tr>
                <th>신용카드 수수료</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay110_su}" groupingUsed="true"/></b></font></td>
                <th>가상계좌 수수료</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay120_su}" groupingUsed="true"/></b></font></td>
                <th>계좌이체 수수료</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay130_su}" groupingUsed="true"/></b></font></td>
            </tr>
            <tr>
                <th>공급가액</th>
                <td><font color="green"><b><fmt:formatNumber value="${pay110 + pay120 + pay130 + pay100}" groupingUsed="true"/></b></font></td>
                <th>정산합계</th>
                <td><font color="green"><b><fmt:formatNumber value="${(pay110 + pay120 + pay130 + pay100) - (pay110_su + pay120_su + pay130_su)}" groupingUsed="true"/></b></font></td>
                <th>지급금액</th>
                <td><font color="green"><b><fmt:formatNumber value="${ ((pay110 + pay120 + pay130 + pay100) - (pay110_su + pay120_su + pay130_su))*0.1 }" groupingUsed="true"/></b></font></td>
            </tr>
        </tbody>
    </table>

</form>
</div>    
