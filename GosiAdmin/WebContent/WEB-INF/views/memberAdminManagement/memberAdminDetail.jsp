<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>

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
 if(!isUpperCase(input))	setLowerCase();
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
		alert("아이디를 입력해주세요");
		$("#USER_ID").focus();
		return;
	}	
	if($.trim($("#USER_ID").val()).length < 4){
		alert("아이디는 4자 이상 13자 이하로 구성해주세요");
		$("#USER_ID").focus();
		return;
	}	
	$.ajax({
		type: "GET", 
		url : '<c:url value="/memberAdminManagement/idAdminCheck.do"/>?USER_ID=' + $.trim($("#USER_ID").val()),
		dataType: "text",		
		async : false,
		success: function(RES) {
			if($.trim(RES)=="Y"){
				alert("사용가능한 아이디 입니다");
				$("#CHECKID").val($.trim($("#USER_ID").val()));
				return;
			}else{
				alert("이미 등록된 아이디 입니다");
				$("#CHECKID").val("");
				return;
			}
		},error: function(){
			alert("중복체크 실패");
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
	if($.trim($("#USER_NM").val())==""){
		alert("성명을 입력해주세요");
		$("#USER_NM").focus();
		return;
	}
	/*
	if($.trim($("#USER_POSITION").val())==""){
		alert("직책을 입력해주세요");
		$("#USER_POSITION").focus();
		return;
	}
	if($.trim($("#USER_NICKNM").val())==""){
		alert("별명을 입력해주세요");
		$("#USER_NICKNM").focus();
		return;
	}
	if($.trim($("#USER_PWD").val())==""){
		alert("비밀번호를 입력해주세요");
		$("#USER_PWD").focus();
		return;
	}
	*/
	if($.trim($("#PHONE_NO").val())==""){
		alert("전화번호를 입력해주세요");
		$("#PHONE_NO").focus();
		return;
	}
	if($("#EMAIL").isEmail() == false){
		alert("메일형식이 아닙니다.");
		$("#EMAIL").focus();
		return;
	} 

	if(confirm("운영자를 수정하시겠습니까?")){
//		$("#USER_PWD").val(Base64.encode($("#USER_PWD").val()));
		$("#frm").attr("action","<c:url value='/memberAdminManagement/memberAdminUpdateProcess.do' />");
		$("#frm").submit();
	}
}
function fn_List(){
	
	if($("#GUBUN").val() == "1"){
		$("#frm").attr("action", "<c:url value='/memberAdminManagement/AdminLoginList.do' />");
	}else{						
		$("#frm").attr("action", "<c:url value='/memberAdminManagement/memberAdminList.do' />");
	}	
	
	$("#frm").submit();
}
function fn_Delete(){
	$("#frm").attr("action", "<c:url value='/memberAdminManagement/memberAdminDelete.do' />");
	$("#frm").submit();
}
$(document).ready(function(){
	$("#USER_PWD").val(new String(Base64.decode($("#USER_PWD").val())));
});
</script>
</head>
<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="USER_ID" name="USER_ID" value="${detail.USER_ID}">
<input type="hidden" id="GUBUN" name="GUBUN" value="${GUBUN}">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" /> 

	<c:if test="${GUBUN != '1'}"><h2>● 회원관리 > 관리자조회 > <strong>관리자 상세</strong></h2></c:if>
	<c:if test="${GUBUN == '1'}"><h2>● 회원관리 > 관리자 로그인 정보 > <strong>관리자 상세</strong></h2></c:if>
		
    	<table class="table01">
   		<tr>
   			<th width="20%">회원ID</th>
  			<td>
				${detail.USER_ID}	
  			</td>
  		</tr>  		
   		<tr>
   			<th>성명</th>
  			<td>
	   			<input type="text" id="USER_NM" name="USER_NM" value="${detail.USER_NM}" size="20"  maxlength="25" title="성명" style="width:28%;background:#FFECEC;"/>
  			</td>
  		</tr>  		
   		<tr>
   			<th>별명</th>
  			<td>
	   			<input type="text" id="USER_NICKNM" name="USER_NICKNM" value="${detail.USER_NICKNM}" size="20"  maxlength="25" title="별명" style="width:28%;background:#FFECEC;"/>
  			</td>
  		</tr>  		
		
   		<tr>
   			<th>비밀번호</th>
  			<td>
	   			<input type="password" id="USER_PWD" name="USER_PWD" value="" size="20"  maxlength="20" title="비밀번호" style="width:18%;background:#FFECEC;"/>
  			</td>
  		</tr>
   		<tr>
   			<th>전화번호</th>
  			<td>
	   			<input type="text" id="PHONE_NO" name="PHONE_NO" value="${detail.PHONE_NO}" size="20"  maxlength="80" title="전화번호" style="width:28%; background:#FFECEC;"/>
  			</td>
  		</tr>
   		<tr>
   			<th>이메일</th>
  			<td>
	   			<input type="text" id="EMAIL" name="EMAIL" value="${detail.EMAIL}" size="20"  maxlength="80" title="이메일" style="width:28%; background:#FFECEC;"/>
  			</td>
  		</tr>
		<tr>
   			<th>회원권한</th>
  			<td>
  				<select id="ADMIN_ROLE" name="ADMIN_ROLE">
					<c:forEach items="${categoryList}" var="item" varStatus="loop">
	   					<c:set var="CHECKEDSET" value="" />
   						<c:if test="${item.SITE_ID eq detail.ADMIN_ROLE}" ><c:set var="CHECKEDSET" value="selected" /></c:if> 
	   					<option value="${item.SITE_ID}" ${CHECKEDSET} >[${item.SITE_ID}]${item.SITE_NM}</option>
	   				</c:forEach>
   				</select>
  			</td>
  		</tr>		
		<tr>
   			<th>관리사이트</th>
  			<td>
  				<select id="USER_POSITION" name="USER_POSITION">
   					<option value="PUB" <c:if test="${detail.USER_POSITION eq 'PUB'}"> selected</c:if>>공무원</option>
   					<option value="COP" <c:if test="${detail.USER_POSITION eq 'COP'}"> selected</c:if>>경찰</option>
   					<option value="GWJ" <c:if test="${detail.USER_POSITION eq 'GWJ'}"> selected</c:if>>광주</option>
   					<option value="ALL" <c:if test="${detail.USER_POSITION eq 'ALL'}"> selected</c:if>>통합</option>
   				</select>
  			</td>
  		</tr>		
  		<tr>
   			<th>상태</th>
   			<td>
   					<label for="ISUSE"></label>
   					<select  id="ISUSE" name="ISUSE">
						<option value="Y"  <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
						<option value="N"  <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
					</select>
   			</td>
   		</tr>
  		<tr>
   			<th>성별</th>
   			<td>
   				<input type="radio" name="SEX" value="M"  <c:if test="${detail.SEX == 'M'}">checked</c:if> />남자 &nbsp;
   				<input type="radio" name="SEX" value="F"   <c:if test="${detail.SEX == 'F'}">checked</c:if>/>여자 &nbsp;
   			</td>
   		</tr>
   		<tr>
   			<th>메모</th>
  			<td>
	   			<textarea id="MEMO" name="MEMO" ROWS="3" maxlength="4000" style="width:98%;">${detail.MEMO}</textarea>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_Submit();">수정</a></li>
   	  <li><a href="javascript:fn_Delete();">삭제</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
