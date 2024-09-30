<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
<script type="text/javascript">
$(function(){
    $.datepicker.regional['ko'] = {
        closeText: '닫기',
        prevText: '이전',
        nextText: '다음',
        currentText: '오늘',
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        dateFormat: 'yy-mm-dd',
        firstDay: 0,
        showMonthAfterYear: true,
        yearSuffix: '년 ',
        autoSize: false};
    $.datepicker.setDefaults($.datepicker.regional['ko']);
});


//아이디 중복체크
function fn_IdCheck(){
	if($.trim($("#USER_ID").val())==""){
		alert("사용자 아이디를 입력해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	if($.trim($("#USER_ID").val()).length < 4){
		alert("아이디는 4자 이상 13자 이하로 구성해 주세요");
		$("#USER_ID").focus();
		return;
	}	
	$.ajax({
		type: "GET", 
		url : '<c:url value="/memberManagement/idCheck.do"/>?USER_ID=' + $.trim($("#USER_ID").val()),
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="N"){
				alert("정상적인 사용자 아이디입니다. 모의고사를 신청등록해주세요.");
				return;
			}else{
				alert("등록되지 않은 아이디 입니다. 새로 회원가입을 해주세요.");
				$("#USER_ID").val("");
				return;
			}
		},error: function(){
			alert("아이디 등록 체크 실패");
			return;
		}
	});		
}

</script>
</head>

<div id="content">
    <h2>● 신청자관리 > <strong>신청자등록</strong></h2>
    <form name="frm" id="frm" class="form form-horizontal" method="post" action="<c:url value="/offerer/offererSave.do"/>">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
    <input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
    <input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
    <input type="hidden" id="SEARCHRECEIPTTYPE" name="SEARCHRECEIPTTYPE" value="${params.SEARCHRECEIPTTYPE}"/>
    <input type="hidden" id="SEARCHPAYMENT" name="SEARCHPAYMENT" value="${params.SEARCHPAYMENT}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
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
    <input type="hidden" id="OFFCLOSEPERSONNUMBER" name="OFFCLOSEPERSONNUMBER" value=""/>

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">아이디</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" id="USER_ID" name="USER_ID" class="i_text" title="사용자아이디" value="" style="width:154px;" />
                    <input name="input" type="button" onClick="fn_IdCheck();" value="아이디체크">
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="col">성명</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" id="USER_NM" name="USER_NM" maxlength="6" class="i_text" title="레이블 텍스트" value="윌비스" style="width:154px;" />
                </div>
            </td>
        </tr>
        <tr>
            <th scope="col">연락처</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="text" id="PHONE_NO" name="PHONE_NO" maxlength="11" class="i_text" title="레이블 텍스트" value="" style="width:154px;" />
                </div>
            </td>
        </tr>
        <tr>
            <th scope="col">품목<br/>
                <span class="btn_pack medium"><button type="button" onClick="javascript:fn_AddPopup();">품목선택</button></span>
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
                            <td id="mouiArea1">&nbsp;</td>
                            <td id="mouiArea2">&nbsp;</td>
                            <td id="mouiArea3">&nbsp;</td>
                        </tr>                           
                    </table>
                </div>
            </td>
        </tr>

        <tr>
            <th scope="col">관리자추가할인</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <input type="radio" name="DISRDO" value="R" checked="checked"/>&nbsp;비율(%)&nbsp; 
                    <input type="radio" name="DISRDO" value="W"/>&nbsp;정액(원)
                    <input type="text" id="DISCOUNTVAL" name="DISCOUNTVAL" class="i_text" title="레이블 텍스트" value="" maxlength="10" style="80px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);" />
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    *&nbsp;할인사유
                    <input type="text" id="ADDDISCOUNTREASON" name="ADDDISCOUNTREASON" class="i_text" title="레이블 텍스트" value="" maxlength="50" style="width:200px;" />&nbsp;&nbsp;
                    <span class="btn_pack medium" id="Dis1"><button type="button" onclick="javascript:fn_Dis('1')">적용</button></span>
                    <span class="btn_pack medium" id="Dis2"><button type="button" onclick="javascript:fn_Dis('2')">RESET</button></span>
                </div>
            </td>
        </tr>

        <tr>
            <th scope="col">결제대상금액</th>
            <td scope="col" style="text-align:left;">
                <div id="PAYAREA" class="item"></div>
            </td>
        </tr>           
        
        <tr>
            <th scope="col">결제</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                    <table class="tdTable" style="width:400px;">
                        <tr>
                            <th style="width:70px;">카드</th>
                            <td><input type="radio" name="PAYMENTTYPE" value="0" /></td>
                            <th style="width:120px;">*&nbsp;카드종류</th>
                            <td><input type="text" id="CARDKIND" name="CARDKIND" class="i_text" title="레이블 텍스트" value="" maxlength="10" style="width:80px;" /></td>                             
                        </tr>
                        <tr>
                            <th style="text-align:center;">현금</th>
                            <td style="text-align:center;"><input type="radio" name="PAYMENTTYPE" value="1"  checked="checked" /></td>
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
                <div class="item">
                    <textarea id="NOTE" name="NOTE" rows="3" class="i_text" style="width:68%;"></textarea>
                </div>
            </td>
        </tr>           
        
        </tbody>
    </table>
    <!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit();">등록</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
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
    if($.trim($("#EXAMCODE").val())==""){
        alert("품목을 먼저 선택해주세요");
        return;
    }               
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
        }else{                                                  // 정액
            $("#ADDDISCOUNTAMOUNT").val($("#DISCOUNTVAL").val());
            $("#ADDDISCOUNTRATIO").val(0);
            curprice = $("#PAYMENTTARGETAMOUNT").val() - $("#DISCOUNTVAL").val();
            $("#PAYMENTAMOUNT").val(curprice);
            $("#PAYAREA").html(curprice + "원");
        }
    }else{          // 리셋
        $("#DISCOUNTVAL").val("");
        $("#ADDDISCOUNTREASON").val("");
        $("#PAYMENTAMOUNT").val($("#PAYMENTTARGETAMOUNT").val());
        $("#PAYAREA").html($("#PAYMENTTARGETAMOUNT").val() + "원");
    }
}

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/offerer/offererList.do' />");
    $("#frm").submit();
}

// 모의고사 품목선택 팝업
function fn_AddPopup() {
    window.open('<c:url value="/offerer/offererWriteMoui.pop"/>', '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=670');
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

// 등록
function fn_Submit(){
    if($.trim($("#USER_NM").val())==""){
        alert("성명을 입력해주세요");
        $("#USER_NM").focus();
        return;
    }
    if($.trim($("#PHONE_NO").val())==""){
        alert("연락처를 정확히 입력해주세요");
        $("#PHONE_NO").focus();
        return;
    }
    if(fn_onlyNum('PHONE_NO')==true){
        alert("연락처는 숫자만 입력하십시오.");
        $("#PHONE_NO").focus();
        return;     
    }
    if($.trim($("#EXAMCODE").val())==""){
        alert("품목을 정확히 선택해주세요");
        return;
    }
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
    if($.trim($("#DISCOUNTVAL").val())!=""){
        if($.trim($("#ADDDISCOUNTREASON").val())==""){
            alert("할인사유를 입력해주세요");
            $("#ADDDISCOUNTREASON").focus();
            return;         
        }
    }
    if($("input[name='PAYMENTTYPE']:checked").val() == "0"){
        if($.trim($("#CARDKIND").val())==""){
            alert("카드종류를 입력해주세요(예:신한,국민 등...)");
            $("#CARDKIND").focus();
            return;         
        }
    }
    if(confirm("신청자를 등록하시겠습니까?")){
        if($("#PAYMENTAMOUNT").val()==""){
            $("#PAYMENTAMOUNT").val($("#PAYMENTTARGETAMOUNT").val());
        }
        if($("#PAYMENTAMOUNT").val()==$("#PAYMENTTARGETAMOUNT").val()){
            $("#ADDDISCOUNTRATIO").val("");
            $("#ADDDISCOUNTAMOUNT").val("");
            $("#ADDDISCOUNTREASON").val("");
        }       
        if($("input[name='PAYMENTTYPE']:checked").val() != "0"){
            $("#ADDDISCOUNTREASON").val("");
        }
        $("#frm").submit();
    }
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
