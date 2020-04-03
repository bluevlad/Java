<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<head></head>

<div id="content">
    <h2>● 신청자관리 > <strong>신청자 주문내역 상세보기</strong></h2>
    <form name="frm" id="frm" class="form form-horizontal" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
    <input type="hidden" id="IDENTYID" name="IDENTYID" value="${view[0].IDENTYID}"/>
    <input type="hidden" id="TID" name="TID" value="${view[0].TID}"/>
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

    <input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
    <input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
    <input type="hidden" id="SEARCHRECEIPTTYPE" name="SEARCHRECEIPTTYPE" value="${params.SEARCHRECEIPTTYPE}"/>
    <input type="hidden" id="SEARCHPAYMENT" name="SEARCHPAYMENT" value="${params.SEARCHPAYMENT}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>

    <table class="table02">
      <caption></caption>
      <colgroup>
        <col width="15%">
        <col width="">
      </colgroup>
      <thead>
      <tr>
        <th scope="col">성명</th>
        <td scope="col" style="text-align:left;">
          <div class="item">${view[0].USER_NM}
          </div>
        </td>
      </thead>
      <tbody>
      <tr>
        <th scope="col">연락처</th>
        <td scope="col" style="text-align:left;">
          <div class="item">${view[0].PHONE_NO}
          </div>
        </td>
      </tr>

      <tr>
        <th scope="col">품목</th>
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
                  ${view[0].MOCKNAME} <br>
                  직렬 : ${view[0].CLASSSERIESCODENM} || 지역 : ${view[0].AREANM}
                  <div style="padding-left:140px;text-align:left;">
                    <div>
                    <c:set var="COMMA" value="" />
                  <c:forEach items="${subjectview}" var="item" varStatus="index">
                    <c:if test="${item.MOCKCODE eq view[0].EXAMCODE && item.ORDERNO eq view[0].ORDERNO && item.SUBJECTTYPEDIVISION eq '1'}">
                      <c:if test="${empty COMMA}">필수 : </c:if>
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
                        ${fn:substring(view[0].EXAMENDTIME,4, 6)}월
                        ${fn:substring(view[0].EXAMENDTIME,6, 8)}일
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
        <th scope="col">결제대상금액</th>
        <td scope="col" style="text-align:left;">
          <div id="PAYAREA" class="item">
            <c:if test="${!empty view[0].PAYMENTAMOUNT}">${view[0].PAYMENTAMOUNT}</c:if>
            <c:if test="${empty view[0].PAYMENTAMOUNT}">${view[0].PAYMENTTARGETAMOUNT}</c:if>
          </div>
        </td>
      </tr>

      <tr>
        <th scope="col">결제</th>
        <td scope="col" style="text-align:left;">
          <div class="item">
            <table class="tdTable" style="width:400px;">
              <tr>
                <th style="width:70px;">카드</th>
                <td>
                  <c:if test="${view[0].PAYMENTTYPE eq 0}">●</c:if>
                </td>
                <td style="width:120px;">*&nbsp;카드종류</td>
                <td>${view[0].CARDKIND}</td>
              </tr>
              <tr>
                <th style="text-align:center;">현금</th>
                <td style="text-align:center;">
                  <c:if test="${view[0].PAYMENTTYPE eq 1}">●</c:if>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th style="text-align:center;">예금</th>
                <td style="text-align:center;">
                  <c:if test="${view[0].PAYMENTTYPE eq 2}">●</c:if>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </div>
        </td>
      </tr>

      <tr>
        <th scope="col">비고</th>
        <td scope="col" style="text-align:left;">
          <div class="item">${fn:replace(view[0].NOTE, newLineChar, '<br/>')}</div>
        </td>
      </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_List();">목록</a></li>
        <li><a href="javascript:fn_Delete();">주문삭제</a></li>
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
    if($("input[name='DISRDO']:checked").val() == "R"){   // 비율
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
  <c:choose>  
    <c:when test="${MENU_ID eq 'TM_004_001'}" >
    $("#frm").attr("action","<c:url value='/offerer/offererList.do' />");
    </c:when>
    <c:when test="${MENU_ID eq 'TM_004_002'}" >
    $("#frm").attr("action","<c:url value='/offerer/refund/list.do' />");
    </c:when>
    <c:otherwise >
    $("#frm").attr("action","<c:url value='/offerer/offererList.do' />");
    </c:otherwise>
  </c:choose>  
    $("#frm").submit();
}

//주문삭제
function fn_Delete(){
  if(confirm("정말 주문을 삭제하시겠습니까?\n관련 데이타가 전부 삭제됩니다")){
  <c:choose>  
    <c:when test="${MENU_ID eq 'TM_004_001'}" >
    $("#frm").attr("action","<c:url value='/offerer/offererDelete.do' />");
    </c:when>
    <c:when test="${MENU_ID eq 'TM_004_002'}" >
    $("#frm").attr("action","<c:url value='/offerer/refund/offererDelete.do' />");
    </c:when>
    <c:otherwise >
    $("#frm").attr("action","<c:url value='/offerer/offererDelete.do' />");
    </c:otherwise>
  </c:choose>  
    $("#frm").submit();
  }
}

//응시표복원
function fn_PrintDelete(){
  if(confirm("정말 복원하시겠습니까?")){
    $("#frm").attr("action","<c:url value='/offerer/offererPrintDelete.do' />");
    $("#frm").submit();
  }
}

//응시표복원
function fn_Print(){
  window.open('<c:url value="/offerer/offererPrint.pop"/>?IDENTYID=${view[0].IDENTYID}&MOCKCODE=${view[0].EXAMCODE}', '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=620,height=330');
}
</script>
