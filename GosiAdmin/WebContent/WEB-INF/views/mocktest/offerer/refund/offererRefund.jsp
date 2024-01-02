<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<head></head>

<div id="content">
    <h2>● 신청자관리 > <strong>환불처리</strong></h2>
<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="IDENTYID" name="IDENTYID" value="${view[0].IDENTYID}"/>
<span id="CHOICEITEMAREA"></span>
<input type="hidden" id="EXAMTYPE" name="EXAMTYPE" value="1"/>
<input type="hidden" id="EXAMCODE" name="EXAMCODE" value=""/>
<input type="hidden" id="CLASSCODE" name="CLASSCODE" value=""/>
<input type="hidden" id="CLASSSERIESCODE" name="CLASSSERIESCODE" value=""/>
<input type="hidden" id="EXAMYEAR" name="EXAMYEAR" value=""/>
<input type="hidden" id="EXAMROUND" name="EXAMROUND" value=""/>
<input type="hidden" id="RECEIPTTYPE" name="RECEIPTTYPE" value="2"/>
<input type="hidden" id="PAYMENTSTATE" name="PAYMENTSTATE" value="0"/>
<input type="hidden" id="PAYMENTTARGETAMOUNT" name="PAYMENTTARGETAMOUNT" value="0"/>
<input type="hidden" id="ADDDISCOUNTRATIO" name="ADDDISCOUNTRATIO" value="0"/>
<input type="hidden" id="ADDDISCOUNTAMOUNT" name="ADDDISCOUNTAMOUNT" value="0"/>
<input type="hidden" id="PAYMENTAMOUNT" name="PAYMENTAMOUNT" value="0"/>
<input type="hidden" id="ADDPOINT1" name="ADDPOINT1" value=""/>
<input type="hidden" id="ADDPOINT2" name="ADDPOINT2" value=""/>
<input type="hidden" id="ADDPOINT3" name="ADDPOINT3" value=""/>
<c:if test="${!empty view[0].PAYMENTAMOUNT}"><input type="hidden" id="REFUNDAMOUNT" name="REFUNDAMOUNT" value="${view[0].PAYMENTAMOUNT}"/></c:if>
<c:if test="${empty view[0].PAYMENTAMOUNT}"><input type="hidden" id="REFUNDAMOUNT" name="REFUNDAMOUNT" value="${view[0].PAYMENTTARGETAMOUNT}"/></c:if>
<input type="hidden" id="TID" name="TID" value="${view[0].TID}"/>
<input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
<input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
<input type="hidden" id="SEARCHRECEIPTTYPE" name="SEARCHRECEIPTTYPE" value="${params.SEARCHRECEIPTTYPE}"/>
<input type="hidden" id="SEARCHPAYMENT" name="SEARCHPAYMENT" value="${params.SEARCHPAYMENT}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="PAYMENTTYPE" name="PAYMENTTYPE" value="${view[0].PAYMENTTYPE}"/>
<input type="hidden" id="CARDKIND" name="CARDKIND" value="${view[0].CARDKIND}"/>

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">성명</th>
            <td scope="col" style="text-align:left;">
                <div class="item">${view[0].USER_NM}</div>
            </td>
        </thead>
        <tbody>
        <tr>
            <th scope="col">연락처</th>
            <td scope="col" style="text-align:left;">
                <div class="item">${view[0].PHONE}</div>
            </td>
        </tr>

        <tr>
            <th scope="col">품목<br/>
            </th>
            <td scope="col" style="text-align:left;">
                <div id="itemArea" class="item">
                    <table class="tdTable">
                        <tr>
                            <th>품목</th>
                            <th>응시료</th>
                            <th>시험일</th>
                        </tr>
                        <tr>
                            <td id="mouiArea1">
                                ${view[0].MOCKNAME} (${view[0].CLASSSERIESCODENM})<br/>
                                <div style="padding-left:140px;text-align:left;">
                                    <div>
                                    <c:set var="COMMA" value="" />
                                    필수 :
                                    <c:forEach items="${subjectview}" var="item" varStatus="index">
                                        <c:if test="${item.MOCKCODE eq view[0].EXAMCODE && item.ORDERNO eq view[0].ORDERNO && item.SUBJECTTYPEDIVISION eq '1'}">
                                            ${COMMA}${item.SUBJECT_NM}<c:set var="COMMA" value="&nbsp;,&nbsp;" />
                                        </c:if>
                                    </c:forEach>
                                    </div>
                                    <c:set var="COMMA" value="" />
                                    <div id="mouiArea1_1">
                                    <c:forEach items="${subjectview}" var="item" varStatus="index">
                                        <c:if test="${item.MOCKCODE eq view[0].EXAMCODE && item.ORDERNO eq view[0].ORDERNO && item.SUBJECTTYPEDIVISION ne '1'}">
                                            <c:if test="${empty COMMA}">선택 : </c:if>
                                                ${COMMA}${item.SUBJECT_NM}<c:set var="COMMA" value="&nbsp;,&nbsp;" />
                                                ${COMMA}${item.SUBJECT_NM}<c:set var="COMMA" value="&nbsp;,&nbsp;" />
                                        </c:if>
                                    </c:forEach>
                                    </div>
                                </div>
                            </td>
                            <td id="mouiArea2">${view[0].PAYMENTTARGETAMOUNT}</td>
                            <td id="mouiArea3">
                                ${fn:substring(view[0].EXAMSTARTTIME,4, 6)}월
                                ${fn:substring(view[0].EXAMSTARTTIME,6, 8)}일
                                ${fn:substring(view[0].EXAMSTARTTIME,8, 10)}시
                                 ~
                                ${fn:substring(view[0].EXAMENDTIME,8, 10)}시
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>

        <tr>
            <th scope="col">관리자추가할인</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <c:if test="${!empty view[0].ADDDISCOUNTRATIO && view[0].ADDDISCOUNTRATIO ne 0}">
                        할인율 : <fmt:parseNumber integerOnly="true" value="${view[0].ADDDISCOUNTRATIO}"/>%
                    </c:if>
                    <c:if test="${!empty view[0].ADDDISCOUNTAMOUNT && view[0].ADDDISCOUNTAMOUNT ne 0}">
                        할인금액 : <fmt:parseNumber integerOnly="true" value="${view[0].ADDDISCOUNTAMOUNT}"/>%(원)
                    </c:if>
                    <c:if test="${!empty view[0].ADDDISCOUNTRATIO && view[0].ADDDISCOUNTRATIO ne 0 && !empty view[0].ADDDISCOUNTAMOUNT && view[0].ADDDISCOUNTAMOUNT ne 0}">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    *&nbsp;할인사유 :
                    ${view[0].ADDDISCOUNTREASON}
                    </c:if>
                </div>
            </td>
        </tr>

        <tr>
            <th scope="col">실결제금액<br/>(환불금액)</th>
            <td scope="col" style="text-align:left;">
                <div id="PAYAREA" class="item">
                    <c:if test="${!empty view[0].PAYMENTAMOUNT}">${view[0].PAYMENTAMOUNT}</c:if>
                    <c:if test="${empty view[0].PAYMENTAMOUNT}">${view[0].PAYMENTTARGETAMOUNT}</c:if>
                </div>
            </td>
        </tr>

        <c:if test="${view[0].PAYMENTSTATE==1}">
            <c:if test="${view[0].PAYMENTTYPE ne '0'}">
                <tr>
                    <th scope="col">환불입금계좌</th>
                    <td scope="col" style="text-align:left;">
                        <div class="item">
                            <div>
                                은행 : <input type="text" id="DEPOSITBANK" name="DEPOSITBANK" maxlength="25" class="i_text" title="레이블 텍스트" value="" style="width:154px;" />
                                &nbsp;&nbsp;&nbsp;
                                계좌번호 : <input type="text" id="ACCOUNTNUMBER" name="ACCOUNTNUMBER" maxlength="30" class="i_text" title="레이블 텍스트" value="" style="width:184px;" />
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="col">비고</th>
                    <td scope="col" style="text-align:left;">
                        <div class="item"><textarea id="REFUNDREASON" name="REFUNDREASON" ROWS="3" maxlength="250" class="i_text" style="width:68%;"></textarea></div>
                    </td>
                </tr>
            </c:if>
        </c:if>
        <c:if test="${view[0].PAYMENTSTATE==2}">
                <tr>
                    <th scope="col">환불입금계좌</th>
                    <td scope="col" style="text-align:left;">
                        <div class="item">
                            <div>
                                은행 : ${view[0].DEPOSITBANK}
                                &nbsp;&nbsp;&nbsp;
                                계좌번호 : ${view[0].ACCOUNTNUMBER}
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="col">비고</th>
                    <td scope="col" style="text-align:left;">
                        <div class="item">
                            ${fn:replace(view[0].REFUNDREASON, newLineChar, '<br/>')}
                        </div>
                    </td>
                </tr>
        </c:if>

        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
    <c:if test="${view[0].PAYMENTSTATE == 1}">
      <c:if test="${view[0].PAYMENTTYPE == 0}">
        <li><a href="javascript:fn_CardRefundSave();">카드취소</a></li>
      </c:if>
      <c:if test="${view[0].PAYMENTTYPE ne '0'}">
        <li><a href="javascript:fn_RefundSave();">환불처리</a></li>
      </c:if>
    </c:if>
        <li><a href="javascript:fn_List();">목록</a></li>
    <c:if test="${view[0].PAYMENTSTATE == 2}">
        <li><a href="javascript:fn_RefundCancel();">환불취소</a></li>
    </c:if>
      </ul>
    <!--//버튼-->

</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl('<c:url value="/resources/img/common/icon_calendar01.png"/>');
    initDateFicker2("PAYMENTDUEDATE");
});

// 관리자 할인 적용 & 리셋
function fn_Dis(mode){
    var curprice = "0";
    if(mode=="1"){  // 할인적용
        if($("input[name='DISRDO']:checked").val() == "R"){     // 비율
            $("#ADDDISCOUNTRATIO").val($("#DISCOUNTVAL").val());
            $("#ADDDISCOUNTAMOUNT").val(0);
            curprice = $("#PAYMENTTARGETAMOUNT").val() - (($("#PAYMENTTARGETAMOUNT").val()*(1/100)) * $("#DISCOUNTVAL").val());
            $("#PAYMENTAMOUNT").val(curprice);
            $("#PAYAREA").html(curprice + "원");
        }else{ // 정액
            $("#ADDDISCOUNTAMOUNT").val($("#DISCOUNTVAL").val());
            $("#ADDDISCOUNTRATIO").val(0);
            curprice = $("#PAYMENTTARGETAMOUNT").val() - $("#DISCOUNTVAL").val();
            $("#PAYMENTAMOUNT").val(curprice);
            $("#PAYAREA").html(curprice + "원");
        }
    }else{ // 리셋
        $("#DISCOUNTVAL").val("");
        $("#PAYAREA").html($("#PAYMENTTARGETAMOUNT").val() + "원");
    }
}

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/offerer/refund/list.do' />");
    $("#frm").submit();
}

function fn_CardRefundSave() {
    if(confirm("정말 환불처리하시겠습니까?\n카드결제 내역도 모두 삭제됩니다")){
        $("#frm").attr("action","<c:url value='/offerer/refund/offererCardPaymentDelete.do' />");
        $("#frm").submit();
    }
}

// 환불처리
function fn_RefundSave() {
    /*if($.trim($("#DEPOSITBANK").val())==""){
        alert("환불하실 은행을 입력해주세요");
        $("#DEPOSITBANK").focus();
        return;
    }
    if($.trim($("#ACCOUNTNUMBER").val())==""){
        alert("환불하실 은행계좌번호를 입력해주세요");
        $("#ACCOUNTNUMBER").focus();
        return;
    }
    if(fn_onlyNum('ACCOUNTNUMBER')==true){
        alert("계좌번호는 숫자만 입력하십시오.");
        $("#ACCOUNTNUMBER").focus();
        return;
    }*/
    if(confirm("정말 환불처리하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/offerer/refund/offererRefundSave.do' />");
        $("#frm").submit();
    }
}

// 환불취소
function fn_RefundCancel() {
    if(confirm("정말 환불취소하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/offerer/refund/offererRefundCancel.do' />");
        $("#frm").submit();
    }
}

// 숫자만 입력
function fn_onlyNum(obj){
    var inputVal = $.trim($("#"+obj).val());
    var inputStr = inputVal.toString();
    for (var i = 0; i < inputStr.length; i++) {
        var oneChar = inputStr.charAt(i);
        if (oneChar < "0" || oneChar > "9") {
            return true;
        }
    }
    return false;
}
</script>