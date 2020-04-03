<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<head></head>

<div id="content">
    <h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
    <form name="frm" id="frm" class="form form-horizontal" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
    <span id="CHOICEITEMAREA">
    <c:forEach items="${subjectview}" var="item" varStatus="index">
        <c:if test="${item.MOCKCODE eq view[0].EXAMCODE && item.ORDERNO eq view[0].ORDERNO && item.SUBJECTTYPEDIVISION ne '1'}">
            <input name="CHOICEITEMARR" type="hidden" value="${item.ITEMID}" />
        </c:if>
    </c:forEach>
    </span>
    <input type="hidden" id="IDENTYID" name="IDENTYID" value="${view[0].IDENTYID}"/>
    <input type="hidden" id="ORDERNO" name="ORDERNO" value="${view[0].ORDERNO}"/>
    <input type="hidden" id="EXAMCODE" name="EXAMCODE" value="${view[0].EXAMCODE}"/>
    <input type="hidden" id="CLASSCODE" name="CLASSCODE" value="${view[0].CLASSCODE}"/>
    <input type="hidden" id="CLASSSERIESCODE" name="CLASSSERIESCODE" value="${view[0].CLASSSERIESCODE}"/>
    <input type="hidden" id="EXAMYEAR" name="EXAMYEAR" value=""/>
    <input type="hidden" id="EXAMROUND" name="EXAMROUND" value=""/>
    <input type="hidden" id="PAYMENTSTATE" name="PAYMENTSTATE" value="${view[0].PAYMENTSTATE}"/>
    <input type="hidden" id="PAYMENTTARGETAMOUNT" name="PAYMENTTARGETAMOUNT" value="${view[0].PAYMENTTARGETAMOUNT}"/>
    <input type="hidden" id="ADDDISCOUNTRATIO" name="ADDDISCOUNTRATIO" value="${view[0].ADDDISCOUNTRATIO}"/>
    <input type="hidden" id="ADDDISCOUNTAMOUNT" name="ADDDISCOUNTAMOUNT" value="${view[0].ADDDISCOUNTAMOUNT}"/>
    <input type="hidden" id="PAYMENTAMOUNT" name="PAYMENTAMOUNT" value="${view[0].PAYMENTAMOUNT}"/>
    <input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
    <input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
    <input type="hidden" id="SEARCHRECEIPTTYPE" name="SEARCHRECEIPTTYPE" value="${params.SEARCHRECEIPTTYPE}"/>
    <input type="hidden" id="SEARCHPAYMENT" name="SEARCHPAYMENT" value="${params.SEARCHPAYMENT}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <tbody>
        <tr>
            <th scope="col">성명</th>
            <td scope="col" style="text-align:left;">
                <div class="item">${view[0].USER_NM}</div>
            </td>
        <tr>
            <th scope="col">수험번호</th>
            <td scope="col" style="text-align:left;">
                <div class="item">${fn:substring(view[0].IDENTYID, 8, 13)}(${view[0].IDENTYID})</div>
            </td>
        </tr>
        <tr>
            <th scope="col">연락처</th>
            <td scope="col" style="text-align:left;">
                <div class="item">${view[0].PHONE_NO}</div>
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
                    <c:if test="${view[0].PAYMENTSTATE eq '0'}">
                        <input type="radio" name="DISRDO" value="R" <c:if test="${view[0].ADDDISCOUNTAMOUNT eq 0}">checked=checked</c:if>/>&nbsp;비율(%)&nbsp;
                        <input type="radio" name="DISRDO" value="W" <c:if test="${view[0].ADDDISCOUNTAMOUNT gt 0}">checked=checked</c:if>/>&nbsp;정액(원)
                        <c:set var="DISCOUNTVAL" value="" />
                        <c:if test="${view[0].ADDDISCOUNTRATIO gt 0}"><c:set var="DISCOUNTVAL" value="${view[0].ADDDISCOUNTRATIO}" /></c:if>
                        <c:if test="${view[0].ADDDISCOUNTAMOUNT gt 0}"><c:set var="DISCOUNTVAL" value="${view[0].ADDDISCOUNTAMOUNT}" /></c:if>
                        <input type="text" id="DISCOUNTVAL" name="DISCOUNTVAL" class="i_text" title="레이블 텍스트" value="<fmt:parseNumber integerOnly="true" value="${DISCOUNTVAL}"/>" style="80px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        *&nbsp;할인사유
                        <input type="text" id="ADDDISCOUNTREASON" name="ADDDISCOUNTREASON" class="i_text" title="레이블 텍스트" value="${view[0].ADDDISCOUNTREASON}" maxlength="50" style="width:200px;" />&nbsp;&nbsp;
                        <span class="btn_pack medium" id="Dis1"><button type="button" onclick="javascript:fn_Dis('1')">적용</button></span>
                        <span class="btn_pack medium" id="Dis2"><button type="button" onclick="javascript:fn_Dis('2')">RESET</button></span>
                    </c:if>
                    <c:if test="${view[0].PAYMENTSTATE eq '1'}">
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
            <th scope="col">시험구분</th>
            <td scope="col" style="text-align:left;">
				<input type="radio" id="EXAMTYPE" name="EXAMTYPE"  value="0" <c:if test="${view[0].EXAMTYPE eq 0}">checked="checked"</c:if>>온라인&nbsp;
				<input type="radio" id="EXAMTYPE" name="EXAMTYPE"  value="1" <c:if test="${view[0].EXAMTYPE eq 1}">checked="checked"</c:if>>오프라인&nbsp;
            </td>
        </tr>
        
        <tr>
            <th scope="col">신청구분</th>
            <td scope="col" style="text-align:left;">
				<input type="radio" id="RECEIPTTYPE" name="RECEIPTTYPE"  value="0" <c:if test="${view[0].RECEIPTTYPE eq 0}">checked="checked"</c:if>>온라인접수&nbsp;
				<input type="radio" id="RECEIPTTYPE" name="RECEIPTTYPE"  value="1" <c:if test="${view[0].RECEIPTTYPE eq 1}">checked="checked"</c:if>>방문접수&nbsp;
				<input type="radio" id="RECEIPTTYPE" name="RECEIPTTYPE"  value="2" <c:if test="${view[0].RECEIPTTYPE eq 2}">checked="checked"</c:if>>데스크접수
            </td>
        </tr>
        <!-- 
        <tr>
            <th scope="col">결제</th>
            <td scope="col" style="text-align:left;">
				<c:if test="${view[0].PAYMENTSTATE eq '0' || view[0].PAYMENTSTATE eq '1'}">
                <input type="radio" id="PAYMENTTYPE" name="PAYMENTTYPE" value="0" <c:if test="${view[0].PAYMENTTYPE eq 0}">checked=checked</c:if>/>카드&nbsp;
                <input type="radio" id="PAYMENTTYPE" name="PAYMENTTYPE" value="1" <c:if test="${view[0].PAYMENTTYPE eq 1}">checked=checked</c:if>/>현금&nbsp;
                <input type="radio" id="PAYMENTTYPE" name="PAYMENTTYPE" value="2" <c:if test="${view[0].PAYMENTTYPE eq 2}">checked=checked</c:if>/>예금&nbsp;
                </c:if>
            </td>
        </tr>
		 -->
		 <tr>
            <th scope="col">결제</th>
            <td scope="col" style="text-align:left;">
				<c:if test="${view[0].PAYMENTSTATE eq '0' || view[0].PAYMENTSTATE eq '1'}">
				       <table class="tdTable">
                            <tr>
                                <th style="width:70px;">카드</th>
                                <td>
                                    <input type="radio" name="PAYMENTTYPE" value="0" <c:if test="${view[0].PAYMENTTYPE eq 0}">checked=checked</c:if>/>
                                </td>
                                <th style="width:120px;">*&nbsp;카드종류</th>
                                <td><input type="text" id="CARDKIND" name="CARDKIND" class="i_text" title="레이블 텍스트" value="${view[0].CARDKIND}" maxlength="10" style="width:180px;" /></td>
                            </tr>
                            <tr>
                                <th style="text-align:center;">현금</th>
                                <td style="text-align:center;">
                                    <input type="radio" name="PAYMENTTYPE" value="1" <c:if test="${view[0].PAYMENTTYPE eq 1}">checked=checked</c:if>/>
                                </td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <th style="text-align:center;">예금</th>
                                <td style="text-align:center;">
                                    <input type="radio" name="PAYMENTTYPE" value="2" <c:if test="${view[0].PAYMENTTYPE eq 2}">checked=checked</c:if>/>
                                </td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                </c:if>
            </td>
        </tr>
        <tr>
            <th scope="col">비고</th>
            <td scope="col" style="text-align:left;">
                <div class="item"><textarea id="NOTE" name="NOTE" ROWS="3" maxlength="250" class="i_text" style="width:68%;">${view[0].NOTE}</textarea></div>
            </td>
        </tr>

        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit();">수정</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
        <li><a href="javascript:fn_Print();">응시표출력</a></li>
        <li><a href="javascript:fn_PrintDelete();">응시표복원</a></li>
        <li><a href="javascript:fn_Delete('${view[0].IDENTYID}');"><font color="red">주문삭제</font></a></li>
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
        if($("input[name='DISRDO']:checked").length < 1){
            alert("할인수단을 선택해주세요");
            return;
        }
        if($.trim($("#DISCOUNTVAL").val()) == ""){
            alert("할인비율 또는 금액을 입력해주세요");
            return;
        }
        if($.trim($("#ADDDISCOUNTREASON").val()) == ""){
            alert("할인사유를 입력해주세요");
            return;
        }
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
        $("#ADDDISCOUNTREASON").val("");
        $("#PAYMENTAMOUNT").val($("#PAYMENTTARGETAMOUNT").val());
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

// 모의고사 품목선택 팝업
function fn_AddPopup() {
    //window.open('<c:url value="/offerer/offererModifyMoui.pop"/>', '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=650,height=580');
    window.open('<c:url value="/offerer/offererModifyMoui.pop"/>?IDENTYID=${view[0].IDENTYID}&MOCKCODE=${view[0].EXAMCODE}', '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1200,height=670');
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


// 등록
function fn_Submit(){
	$("#frm").attr("action","<c:url value='/offerer/offererModifyUpdate.do'/>");
    var curprice = "";
    if($.trim($("#DISCOUNTVAL").val())!=""){
        if(fn_onlyNum('DISCOUNTVAL')==true){
            alert("숫자만 입력하십시오.");
            $("#DISCOUNTVAL").focus();
            return;
        }

        if($("input[name='DISRDO']:checked").val() == "R"){     // 비율
            curprice = $("#PAYMENTTARGETAMOUNT").val() - (($("#PAYMENTTARGETAMOUNT").val()*(1/100)) * $("#DISCOUNTVAL").val());
        }else{                                                  // 정액
            curprice = $("#PAYMENTTARGETAMOUNT").val() - $("#DISCOUNTVAL").val();
        }
        if($("#PAYMENTAMOUNT").val()!=curprice){
            alert("추가할인내용을 적용하지 않으셨습니다\n적용하시려면 먼저 적용버튼을 눌러주세요");
        }

    }
    if($("input[name='PAYMENTTYPE']:checked").length < 1){
//        alert("결제수단을 선택해주세요");
//        return;
    }
    if(confirm("수정하시겠습니까?")){
        <c:if test="${view[0].PAYMENTSTATE eq '0'}">
            if($("#PAYMENTAMOUNT").val()==""){
                $("#PAYMENTAMOUNT").val($("#PAYMENTTARGETAMOUNT").val());
            }
            if($("#PAYMENTAMOUNT").val()==$("#PAYMENTTARGETAMOUNT").val()){
                $("#ADDDISCOUNTRATIO").val("");
                $("#ADDDISCOUNTAMOUNT").val("");
                $("#ADDDISCOUNTREASON").val("");
            }
        </c:if>
        $("#frm").submit();
    }
}

//주문삭제
function fn_Delete(idt){
	if(confirm("정말 주문을 삭제하시겠습니까?\n관련 데이타가 전부 삭제됩니다")){
	  $("#frm").attr("action","<c:url value='/offerer/offererDelete.do' />");
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