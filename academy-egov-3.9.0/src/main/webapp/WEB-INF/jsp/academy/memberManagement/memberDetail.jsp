<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
<input type="hidden" id="USER_ID" name="USER_ID" value="${detail.USER_ID}">
<input type="hidden" id="SBJ_REQ" name="SBJ_REQ" value="${detail.SBJ_REQ }" />
<input type="hidden" id="F_CAT_CD" name="F_CAT_CD" value="${detail.F_CAT_CD }" />
<input type="hidden" id="F_AREA" name="F_AREA" value="${detail.F_AREA }" />
<input type="hidden" id="S_CAT_CD" name="S_CAT_CD" value="${detail.S_CAT_CD }" />
<input type="hidden" id="S_AREA" name="S_AREA" value="${detail.S_AREA }" />
<input type="hidden" id="EVENT_REQ" name="EVENT_REQ" value="${detail.EVENT_REQ }" />
<input type="hidden" id="SBJ_REQ_COUNT" name="SBJ_REQ_COUNT" value="${fn:length(fn:replace(detail.SBJ_REQ,',','')) }" />

    <h2>● 회원관리 > <strong>회원조회</strong></h2> 
        <table class="table01">
        <tr>
            <th width="15%">회원아이디</th>
            <td colspan="3">
                ${detail.USER_ID}
            </td>
        </tr>       
        <tr>
            <th width="15%">비밀번호</th>
            <td>
                <input type="password" id="USER_PWD" name="USER_PWD" value="" size="20"  maxlength="20" title="비밀번호" style="width:50%;background:#FFECEC;"/>
            </td>
            <th>비밀번호확인</th>
            <td>
                <input type="password" id="USER_PWD2" name="USER_PWD2" value="" size="20"  maxlength="20" title="비밀번호확인" style="width:50%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th width="15%">성명</th>
            <td colspan="3">
                <input type="text" id="USER_NM" name="USER_NM" value="${detail.USER_NM}" size="20"  maxlength="25" title="성명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>       
        <tr>
            <th>생년월일</th>
            <td>
                <input type="text" id="BIRTH_DAY" name="BIRTH_DAY" value="${detail.BIRTH_DAY}" size="20"  maxlength="8" title="생년월일" style="width:50%;background:#FFECEC;"/>
            </td>
            <th>이메일</th>
            <td>
                <input type="text" id="EMAIL" name="EMAIL" value="${detail.EMAIL}" size="20"  maxlength="80" title="이메일" style="width:50%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>핸드폰</th>
            <td>
                <input type="text" id="PHONE_NO" name="PHONE_NO" value="${detail.PHONE_NO}" size="20"  maxlength="20" title="핸드폰" style="width:50%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
            <th>자택전화번호</th>
            <td>
                <input type="text" id="TEL_NO" name="TEL_NO" value="${detail.TEL_NO}" size="20"  maxlength="20" title="전화번호" style="width:50%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
        </tr>
        <tr>
            <th>SMS/EMAIL 수신동의</th>
            <td colspan=3>
                <label for="ISOK_SMS">SMS수신</label>
                <select  id="ISOK_SMS" name="ISOK_SMS">
                    <option value="Y" <c:if test="${detail.ISOK_SMS eq 'Y'}">selected='selected'</c:if>>수신</option>
                    <option value="N" <c:if test="${detail.ISOK_SMS eq 'N'}">selected='selected'</c:if>>거부</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="ISOK_EMAIL">EMAIL수신</label>
                <select  id="ISOK_EMAIL" name="ISOK_EMAIL">
                    <option value="Y" <c:if test="${detail.ISOK_EMAIL eq 'Y'}">selected='selected'</c:if>>수신</option>
                    <option value="N" <c:if test="${detail.ISOK_EMAIL eq 'N'}">selected='selected'</c:if>>거부</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>자택주소</th>
            <td colspan=3>
                <input type="text" id="ZIP_CODE" name="ZIP_CODE" value="${detail.ZIP_CODE}"  readonly="readonly" size="7"  maxlength="7" title="우편번호" style="width:18%;ime-mode:disabled;"/>
                <input type="button" name="input" onClick="ZipSearch()" value="우편번호찾기">
                <div id="UI_ZIPCODE" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                </div>
                <input type="text" id="ADDRESS1" name="ADDRESS1" value="${detail.ADDRESS1}"  readonly="readonly"  size="60"   title="자택주소" style="width:80%;ime-mode:disabled;"/>
                <input type="text" id="ADDRESS2" name="ADDRESS2" value="${detail.ADDRESS2}" size="60"   title="자택주소 상세" style="width:80%;ime-mode:disabled;"/>
            </td>
        </tr>
        <tr>
            <th>포인트</th>
            <td>
                <input type="text" id="USER_POINT" name="USER_POINT" value="${detail.USER_POINT}" size="20"  maxlength="20" title="포인트" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
                <input type="hidden" id="BEFORE_USER_POINT" name="BEFORE_USER_POINT" value="${detail.USER_POINT}" size="20"  maxlength="20" title="포인트" style="width:18%;ime-mode:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
            <th>상태</th>
            <td>
                <label for="ISUSE"></label>
                <select  id="ISUSE" name="ISUSE">
                    <option value="Y"  <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
                    <option value="N" <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>가입경로</th>
            <td>
                <select id="JOIN_CHANNEL" name="JOIN_CHANNEL">
                    <option value="">선택하세요</option>
                <c:forEach items="${JOIN_CHANNELs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.JOIN_CHANNEL}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>직업</th>
            <td>
                <select id="JOB" name="JOB">
                    <option value="">선택하세요</option>
                <c:forEach items="${JOBs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.JOB}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>관심직렬</th>
            <td>
                <select id="CATEGORY_CODE" name="CATEGORY_CODE">
                    <option value="">선택하세요</option>
                <c:forEach items="${categoryList}" var="data" varStatus="status" >
                    <option value="${data.CODE}" <c:if test="${data.CODE eq detail.USER_CATEGORY_CODE}">selected='selected'</c:if>><c:out value='${data.NAME}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>수험기간</th>
            <td>
                <select id="EXAM_REQ" name="EXAM_REQ">
                    <option value="">선택하세요</option>
                <c:forEach items="${EXAM_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.EXAM_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>선호하는 선택과목</th>
            <td colspan=3>
                <select id="sbj_req_1" name="sbj_req_1" onchange="javascript:fn_sbj_req('1');">
                    <option value="">선택하세요</option>
                <c:forEach items="${SBJ_REQs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:if test="${data.CODE_CD eq fn:substring(fn:replace(detail.SBJ_REQ,',',''),0,1)}"><c:set var="CHECKEDSET" value="selected='selected'" /></c:if>
                    <option value="${data.CODE_CD}" ${CHECKEDSET}><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;
                <select id="sbj_req_2" name="sbj_req_2" onchange="javascript:fn_sbj_req('2');">
                    <option value="">선택하세요</option>
                <c:forEach items="${SBJ_REQs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:if test="${data.CODE_CD eq fn:substring(fn:replace(detail.SBJ_REQ,',',''),1,2)}"><c:set var="CHECKEDSET" value="selected='selected'" /></c:if>
                    <option value="${data.CODE_CD}" ${CHECKEDSET}><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>1차 관심직종 및 지역</th>
            <td colspan=3>
                <select id="f_cat_cd" name="f_cat_cd" onchange="javascript:fn_f_cat_cd(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${S_CAT_CDs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.F_CAT_CD}" >selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <p class="stxp">※ 1차 관심 직종의 선택가능한 지역을 모두 선택 해 주세요.</p>
                <ul class="iptList">
                <c:forEach items="${AREAs}" var="data" varStatus="status" >
                    <c:set var="CHECKEDSET" value="" />
                    <c:set var="x" value="0" />
                    <c:set var="y" value="2" />
                    <c:forEach var="i" begin="1" end="${fn:length(fn:replace(detail.F_AREA,',',''))/2}">
                        <c:if test="${data.CODE_VAL eq fn:substring(fn:replace(detail.F_AREA,',',''),x,y)}"><c:set var="CHECKEDSET" value="checked='checked'" /></c:if>
                        <c:set var="x" value="${x+2}" /><c:set var="y" value="${y+2}" />
                    </c:forEach>
                    <input type="checkbox" id="f_area_${data.CODE_CD}" name="f_area_${data.CODE_CD}" value="${data.CODE_CD}" onchange="javascript:fn_f_area(this)" ${CHECKEDSET}><label for="f_area_${data.CODE_CD}"><c:out value='${data.CODE_NM}'/></label>&nbsp;
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th>2차 관심직종 및 지역</th>
            <td colspan=3>
                <select id="s_cat_cd" name="s_cat_cd" onchange="javascript:fn_s_cat_cd(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${S_CAT_CDs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.S_CAT_CD}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <p class="stxp">※ 2차 관심 직종의 선택가능한 지역을 모두 선택 해 주세요.</p>
                <ul class="iptList">
                <c:forEach items="${AREAs}" var="data" varStatus="status" >
                    <c:set var="x" value="0" />
                    <c:set var="y" value="2" />
                    <c:set var="CHECKEDSET" value="" />
                    <c:forEach var="i" begin="1" end="${fn:length(fn:replace(detail.S_AREA,',',''))/2}">
                        <c:if test="${data.CODE_VAL eq fn:substring(fn:replace(detail.S_AREA,',',''),x,y)}"><c:set var="CHECKEDSET" value="checked='checked'" /></c:if>
                        <c:set var="x" value="${x+2}" /><c:set var="y" value="${y+2}" />
                    </c:forEach>
                    <input type="checkbox" id="s_area_${data.CODE_CD}" name="s_area_${data.CODE_CD}" value="${data.CODE_CD}" onchange="javascript:fn_s_area(this)" ${CHECKEDSET}><label for="s_area_${data.CODE_CD}"><c:out value='${data.CODE_NM}'/></label>&nbsp;
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th>관심정보</th>
            <td>
                <select id="INFO_REQ" name="INFO_REQ">
                    <option value="">선택하세요</option>
                <c:forEach items="${INFO_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.INFO_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
            </td>
            <th>선호이벤트</th>
            <td>
                <select id="event_req" name="event_req" onchange="javascript:fn_event_req(this);">
                    <option value="">선택하세요</option>
                <c:forEach items="${EVENT_REQs}" var="data" varStatus="status" >
                    <option value="${data.CODE_CD}" <c:if test="${data.CODE_CD eq detail.EVENT_REQ}">selected='selected'</c:if>><c:out value='${data.CODE_NM}'/></option>
                </c:forEach>
                </select>
                <c:if test="${detail.EVENT_REQ eq 'F' }">
                <div class="elts" id="event_req_etc" style="display: block"><label for="elts">기타 :</label><input type="text" id="event_req_etc_txt" name="event_req_etc_txt" value="${myInfoMap.EVENT_REQ_ETC }" class="ipt"></div>
                </c:if>
                <c:if test="${detail.EVENT_REQ ne 'F' }">
                <div class="elts" id="event_req_etc" style="display: none"><label for="elts">기타 :</label><input type="text" id="event_req_etc_txt" name="event_req_etc_txt" value="${myInfoMap.EVENT_REQ_ETC }" class="ipt"></div>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>특이사항</th>
            <td colspan=3>
                <textarea id="REMARK" name="REMARK" ROWS="3" maxlength="4000" style="width:68%;">${detail.REMARK}</textarea>
            </td>
        </tr>
    </table>
    <!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_Submit();">수정</a></li>
      <!-- <li><a href="javascript:fn_Delete();">삭제</a></li> -->
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


function fn_sbj_req(obj){
    var x = $("#sbj_req_"+obj).val();
    if ($("select[name=sbj_req_"+obj+"]").val() != "") {
        if(parseInt($("#SBJ_REQ_COUNT").val()) < 2) {
            $("#SBJ_REQ_COUNT").val(parseInt($("#SBJ_REQ_COUNT").val())+1);
        }
    }else{
        $("#SBJ_REQ_COUNT").val(parseInt($("#SBJ_REQ_COUNT").val())-1);
    }

    if(parseInt($("#SBJ_REQ_COUNT").val()) == 2) {
        $("#SBJ_REQ").val($("#sbj_req_1").val()+","+$("#sbj_req_2").val());
    }
}

function fn_f_cat_cd(obj){
    $("#F_CAT_CD").val(obj.value);
}

function fn_f_area(obj){
    var x = document.getElementById("f_area_"+obj.value).value
    if(document.getElementById("f_area_"+obj.value).checked){
        if($("#F_AREA").val() == ""){
            $("#F_AREA").val($("#F_AREA").val()+x);
        }else{
            $("#F_AREA").val($("#F_AREA").val()+","+x);
        }
    }else{
        var y;
        if(parseInt($("#F_AREA").val().length) == 2){
            y = $("#F_AREA").val().replace(document.getElementById("f_area_"+obj.value).value,"");
        }else{
            if(parseInt($("#F_AREA").val().indexOf(document.getElementById("f_area_"+obj.value).value)) == 0){
                y = $("#F_AREA").val().replace(document.getElementById("f_area_"+obj.value).value+",","");
            }else{
                y = $("#F_AREA").val().replace(','+document.getElementById("f_area_"+obj.value).value,"");
            }
        }
        $("#F_AREA").val(y);
    }
}

function fn_s_cat_cd(obj){
    $("#S_CAT_CD").val(obj.value);
}

function fn_s_area(obj){
    var x = document.getElementById("s_area_"+obj.value).value
    if(document.getElementById("s_area_"+obj.value).checked){
        if($("#S_AREA").val() == ""){
            $("#S_AREA").val($("#S_AREA").val()+x);
        }else{
            $("#S_AREA").val($("#S_AREA").val()+","+x);
        }
    }else{
        var y;
        if(parseInt($("#S_AREA").val().length) == 2){
            y = $("#S_AREA").val().replace(document.getElementById("s_area_"+obj.value).value,"");
        }else{
            if(parseInt($("#S_AREA").val().indexOf(document.getElementById("s_area_"+obj.value).value)) == 0){
                y = $("#S_AREA").val().replace(document.getElementById("s_area_"+obj.value).value+",","");
            }else{
                y = $("#S_AREA").val().replace(','+document.getElementById("s_area_"+obj.value).value,"");
            }
        }
        $("#S_AREA").val(y);
    }
}

function fn_event_req(obj){
    $("#EVENT_REQ").val(obj.value);

    if(obj.value == 'F'){
        $("#event_req_etc").css("display","block");
    }else{
        $("#event_req_etc").css("display","none");
        $("#event_req_etc_txt").val("");
    }
}

function foldDaumPostcode() {
    var element = document.getElementById('UI_ZIPCODE');
    // iframe을 넣은 element를 안보이게 한다.
    element.style.display = 'none';
}

function ZipSearch() {
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
//  if($.trim($("#USER_PWD").val())==""){
//      alert("비밀번호를 입력해 주세요.");
//      $("#USER_PWD").focus();
//      return;
//  }
//  if($.trim($("#USER_PWD2").val())==""){
//      alert("비밀번호 확인을 입력해 주세요.");
//      $("#USER_PWD2").focus();
//      return;
//  }
    if($.trim($("#USER_PWD").val())!=$.trim($("#USER_PWD2").val())){
        alert("비밀번호와 비밀번호확인 입력값이 틀립니다. 다시 확인해 주세요.");
        $("#USER_PWD").focus();
        return;
    }
    if($.trim($("#USER_NM").val())==""){
        alert("성명을 입력해 주세요.");
        $("#USER_NM").focus();
        return;
    }
    /*
    if($.trim($("#PROFILE").val())!=""){
        if(checkLenth(document.getElementById("PROFILE"),4000)==false){
            alert("☞ 영문 4000자,한글 1333자까지 입력이 가능합니다\n글자수가 너무 많습니다. 줄여주세요");
            return;
        }       
    }
    */
    if(confirm("회원을 수정하시겠습니까?")){
//      $("#USER_PWD").val(Base64.encode($("#USER_PWD").val()));
        $("#frm").attr("action","<c:url value='/memberManagement/memberUpdateProcess.do' />");
        $("#frm").submit();
    }
}
function fn_List(){
    $("#frm").attr("action", "<c:url value='/memberManagement/memberList.do' />");
    $("#frm").submit();
}

function fn_Delete(){
    $("#frm").attr("action", "<c:url value='/memberManagement/memberList.do' />");
    $("#frm").submit();
}
</script>