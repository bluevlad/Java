<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SDATE" name="SDATE" value="${params.SDATE}"/>
<input type="hidden" id="EDATE" name="EDATE" value="${params.EDATE}"/>
<input type="hidden" id="CHECKID" name="CHECKID" value="">

    <h2>● 회원관리 > <strong>회원조회</strong></h2> 
        <table class="table01">
        <tr>
            <th width="15%">회원아이디</th>
            <td colspan="3">
                <input type="text" id="USER_ID" name="USER_ID" value="" size="20"  maxlength="13" title="회원아이디" style="width:18%;background:#FFECEC;ime-mode:disabled;" onKeyUp="checkData(this);"/>
                <input name="input" type="button" onClick="fn_IdCheck();" value="중복체크"> ☞ ID는 영문소문자[a~z], 숫자[0~9], 특수문자[ '_', '-' ] 포함하여 4자이상 13자 이하로 구성하세요
            </td>
        </tr>       
        <tr>
            <th width="15%">비밀번호</th>
            <td>
                <input type="password" id="USER_PWD" name="USER_PWD" value="" size="20"  maxlength="12" title="비밀번호" style="width:50%;background:#FFECEC;ime-mode:disabled;"/>
            </td>
            <th width="15%">비밀번호확인</th>
            <td>
                <input type="password" id="USER_PWD2" name="USER_PWD2" value="" size="20"  maxlength="12" title="비밀번호확인" style="width:50%;background:#FFECEC;ime-mode:disabled;"/>
            </td>
        </tr>
        <tr>
            <th width="15%">성명</th>
            <td colspan="3">
                <input type="text" id="USER_NM" name="USER_NM" value="" size="20"  maxlength="25" title="성명" style="width:28%;background:#FFECEC;ime-mode:active;"/>
            </td>
        </tr>       
        <tr>
            <th>생년월일</th>
            <td>
                <input type="text" id="BIRTH_DAY" name="BIRTH_DAY" value="" size="20"  maxlength="8" title="생년월일" style="width:50%; background:#FFECEC;"/>
            </td>
            <th>이메일</th>
            <td>
                <input type="text" id="EMAIL" name="EMAIL" value="" size="20"  maxlength="80" title="이메일" style="width:50%; background:#FFECEC;ime-mode:disabled;"/>
            </td>
        </tr>
        <tr>
            <th>SMS/EMAIL 수신동의</th>
            <td colspan="3">
                <label for="ISOK_SMS">SMS수신</label>
                <select  id="ISOK_SMS" name="ISOK_SMS">
                    <option value="Y">수신</option>
                    <option value="N">거부</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="ISOK_EMAIL">EMAIL수신</label>
                <select  id="ISOK_EMAIL" name="ISOK_EMAIL">
                    <option value="Y">수신</option>
                    <option value="N">거부</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>자택주소</th>
            <td colspan=3>
                <input type="text" id="ZIP_CODE" name="ZIP_CODE" value="" size="7"  maxlength="7" readonly="readonly" title="우편번호" style="width:18%;ime-mode:disabled;"/>
                <input type="button" name="input" onClick="ZipSearch()" value="우편번호찾기">
                <div id="UI_ZIPCODE" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                </div>
                <input type="text" id="ADDRESS1" name="ADDRESS1" value="" size="60"  readonly="readonly"  title="자택주소" style="width:80%;ime-mode:disabled;"/>
                <input type="text" id="ADDRESS2" name="ADDRESS2" value="" size="60"  title="자택주소 상세" style="width:80%;ime-mode:disabled;"/>
            </td>
        </tr>
        <tr>
            <th>포인트</th>
            <td>
                <input type="text" id="USER_POINT" name="USER_POINT" value="" size="20"  maxlength="20" title="포인트" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
            <th>상태</th>
            <td>
                <label for="ISUSE"></label>
                <select  id="ISUSE" name="ISUSE">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>특이사항</th>
            <td colspan="3">
                <textarea id="REMARK" name="REMARK" ROWS="3" maxlength="4000" style="width:68%;"></textarea>
            </td>
        </tr>
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
<!--//content --> 
<script type="text/javascript">
// 대문자 체크
$.prototype.isCapital = function(){
    var chk = /[A-Z_]/;
    return chk.test(this.val());
};
$.prototype.isEmail = function(){
    var chk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    return chk.test(this.val());
};

function foldDaumPostcode() {
    var element = document.getElementById('UI_ZIPCODE');
    // iframe을 넣은 element를 안보이게 한다.
    element.style.display = 'none';
}

function ZipSearch(){
    // 우편번호 찾기 iframe을 넣을 element
    var element = document.getElementById('UI_ZIPCODE');
    // 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
            document.getElementById('ZIP_CODE').value = data.zonecode;
            document.getElementById('ADDRESS1').value = fullAddr;
            document.getElementById('ADDRESS2').focus();

            // iframe을 넣은 element를 안보이게 한다.
            element.style.display = 'none';
            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분.
        // iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element.style.height = size.height + "px";
        },
        width : '100%',
        height : '100%'
    }).embed(element);
    // iframe을 넣은 element를 보이게 한다.
    element.style.display = 'block';
}

//숫자만 입력
function fn_OnlyNumber(obj) {
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
    }else{
        event.returnValue = false;
    }
}

//아아디 체크
function checkData(input) {
 if(!isAlphaNumDash(input)) {
     alert("ID는 영문소문자[a~z], 숫자[0~9], 특수문자[ '_', '-' ] 포함하여 \n4자이상 13자 이하로 구성하세요");
     $("#USER_ID").val("");
 }
 if(!isUpperCase(input))    setLowerCase();
}

//입력값이 알파벳,숫자, _  - 로 되어있는지 체크
function isAlphaNumDash(input) {
 var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
 return containsCharsOnly(input,chars);
}

//입력값이 알파벳 대문자인지 체크
function isUpperCase(input) {
 var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 return containsCharsOnly(input,chars);
}

//입력값을 모두 소문자로 치환한다
function setLowerCase() {
 var objEv = event.srcElement;
 objEv.value=objEv.value.toLowerCase();
}

//입력값이 특정 문자(chars)만으로 되어있는지 체크
function containsCharsOnly(input,chars) {
 for (var inx = 0; inx < input.value.length; inx++) {
    if (chars.indexOf(input.value.charAt(inx)) == -1)
        return false;
 }
 return true;
}

//아이디 중복체크
function fn_IdCheck(){
    if($.trim($("#USER_ID").val())==""){
        alert("아이디를 입력해 주세요.");
        $("#USER_ID").focus();
        return;
    }   
    if($.trim($("#USER_ID").val()).length < 4){
        alert("아이디는 4자 이상 13자 이하로 구성해 주세요.");
        $("#USER_ID").focus();
        return;
    }   
    $.ajax({
        type: "GET", 
        url : '<c:url value="/memberManagement/idCheck.do"/>?USER_ID=' + $.trim($("#USER_ID").val()),
        dataType: "text",       
        async : false,
        success: function(RES) {
            if($.trim(RES)=="Y"){
                alert("사용가능한 아이디 입니다.");
                $("#CHECKID").val($.trim($("#USER_ID").val()));
                return;
            }else{
                alert("이미 등록된 아이디 입니다.");
                $("#CHECKID").val("");
                return;
            }
        },error: function(){
            alert("코드중복체크 실패");
            return;
        }
    });     
}

//입력값의 바이트 길이를 리턴
function getByteLength(input) {
 var byteLength = 0;
 for (var inx = 0; inx < input.value.length; inx++) {
     var oneChar = escape(input.value.charAt(inx));
     if ( oneChar.length == 1 ) {
         byteLength ++;
     } else if (oneChar.indexOf("%u") != -1) {
         byteLength += 3;
     } else if (oneChar.indexOf("%") != -1) {
         byteLength += oneChar.length/3;
         byteLength += oneChar.length/3;
     }
 }
 return byteLength;
}

//입력값의 바이트 길이가 주어진 길이보다 크면 false를 리턴
function checkLenth(input, len) {
 if(getByteLength(input) > len) {
     return false;
 } else true;
 
}

//등록
function fn_Submit(){
    if($.trim($("#USER_ID").val())==""){
        alert("아이디를 입력해주세요");
        $("#USER_ID").focus();
        return;
    }
    if($.trim($("#USER_ID").val()).length < 4){
        alert("아이디는 4자 이상 13자 이하로 구성해주세요");
        $("#USER_ID").focus();
        return;
    }   
    if($.trim($("#CHECKID").val())!=$.trim($("#USER_ID").val())){
        alert("아이디 중복체크를 해주세요");
        return;
    }
    if($.trim($("#USER_PWD").val())==""){
        alert("비밀번호를 입력해주세요");
        $("#USER_PWD").focus();
        return;
    }
    if($.trim($("#USER_PWD2").val())==""){
        alert("비밀번호 확인을 입력해주세요");
        $("#USER_PWD2").focus();
        return;
    }
    if($.trim($("#USER_PWD").val())!=$.trim($("#USER_PWD2").val())){
        alert("비밀번호와 비밀번호확인 입력값이 틀립니다. 다시 확인해주세요");
        $("#USER_PWD").focus();
        return;
    }
    if($.trim($("#USER_NM").val())==""){
        alert("성명을 입력해주세요");
        $("#USER_NM").focus();
        return;
    }
    if($("#EMAIL").isEmail() == false){
        alert("메일형식이 아닙니다.");
        $("#EMAIL").focus();
        return;
    } 

    if(confirm("회원을 등록하시겠습니까?")){
        $("#USER_PWD").val(Base64.encode($("#USER_PWD").val()));
        $("#frm").attr("action","<c:url value='/memberManagement/memberInsertProcess.do' />");
        $("#frm").submit();
    }
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/memberManagement/memberList.do' />");
    $("#frm").submit();
}

</script>