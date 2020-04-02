<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<title>▶ 주소 및 우편번호 찾기</title>
<script type="text/javascript">
// 윈도우환경고정
function fitFormSize() {
  self.resizeTo(450,424);
  document.formsub.bunji.focus();
}

// 엔터 체크
function enterCheck(type) {
	if( event.keyCode == 13) {
		if(type=="select") {
			member_home_admin_click();
			return;
		}
		if(type=="search") {
			areacheck();
			return;
		}
    }
}

//입력값 체크
function areacheck()
{
    var form = document.zipform;
    if(form.SEARCHAREA.value=="" || form.SEARCHAREA.length < 2) {
        alert("☞ 읍/면/동, 대량배달처명, 리단위 이름을 2자 이상 입력하세요.");
        form.area.focus();
    } else {
    	
    	 $("#zipform").attr("action" , "<c:url value='/memberManagement/searchZipCode.pop' />");
   		 $("#zipform").submit();
    }
}
function member_home_admin_click(){
	var zipcode = $("#areaname").val();
	var adress2 = $("#address2").val();
	 if(opener.setZipcode) {
	    opener.setZipcode(zipcode ,adress2 );
	    window.close();
	  }
};
//주소설정
function setZipcode(_postNo, _addr) {
  if(opener.setZipcode) {
    opener.setZipcode(_postNo, _addr);
    window.close();
  }
}

</script>
</head>
<div id="content_pop">
	<h2>
		<strong>:::우편번호 검색:::</strong>
	</h2>
	<form name="zipform" id="zipform" method="post" action="" style="margin: 0">
		<input type=hidden name="toPost" value="member_home_admin"> 
		<input type=hidden name="cmd" value="zipcd"> 
		<input type=hidden	name="flg" value="rslt">
		<table class='tdTable'>
			<tr>
				<td height="50" align="center" class="cou_tit">☞ 서울 송파구 신천동에
					거주하신다면 신천동만 입력하시고, <br>강원도 양양군 양양읍에 사시는 분은 양양읍만 입력하세요. <br>지역의
					&quot; 읍/면/동, 또는 대량배달처/리&quot; 단위만 입력해 주세요.
				</td>
			</tr>
			<tr>
				<td height="35" align="center" class="ta_white">
					<input	name="SEARCHAREA" type="text" class="box" size="20" style="ime-mode:active;" value="${params.SEARCHAREA}" onkeypress="enterCheck('search');"> 
					<input type="button" value="검색" onClick="areacheck();" class="btn02">
				</td>
			</tr>
	</form>
			<c:if test="${not empty ZIPLIST}">
				<form name="formsub" style="margin: 0">
					<tr>
						<td height="28" align="center" class="cou_tit">☞ 검색한 주소입니다. 주소를	선택하시고 상세 주소를 입력해 주세요</td>
					</tr>
					<tr>
						<td height="30" align="center" class="ta_white">
							<select name="areaname" id="areaname" class="box" style="width: 70%">
								<c:forEach items="${ZIPLIST}" var="data" varStatus="status">
									<option value="${data.JUSO_VALUE}">${data.JUSO_NM}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td height="28" align="center" class="cou_tit">☞ 번지와 호수 등의 나머지 주소를 입력해 주세요</td>
					</tr>
					<tr>
						<td height="30" align="center" class="ta_white">
							<input	type="text" name="address2" id="address2" size="40" onKeyPress="enterCheck('select');"> <input type="button" value="선택" onClick="member_home_admin_click();" class="btn02">
						</td>
					</tr>
					<tr>
						<td height="20">&nbsp;</td>
					</tr>
				</form>
			</c:if>
	</table>
</div>
