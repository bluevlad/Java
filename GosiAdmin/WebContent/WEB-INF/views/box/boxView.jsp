<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>

<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${ msgStr ne '' && msgStr ne null}">alert('${msgStr}')</c:if>
});

	//등록
	function fn_Submit() {
		if ($.trim($("#BOX_CD").val()) == "") {
			alert("호를 입력해 주세요.");
			$("#BOX_CD").focus();
			return;
		}
		if ($.trim($("#BOX_NM").val()) == "") {
			alert("사물함 이름을 입력해 주세요.");
			$("#BOX_NM").focus();
			return;
		}
		if ($.trim($("#BOX_COUNT").val()) == "") {
			alert("사물함 총 갯수를 입력해 주세요.");
			$("#BOX_COUNT").focus();
			return;
		}
		if ($.trim($("#ROW_COUNT").val()) == "") {
			alert("층 수를 입력해 주세요.");
			$("#ROW_COUNT").focus();
			return;
		}
		if ($.trim($("#ROW_NUM").val()) == "") {
			alert("가로 갯수를 입력해 주세요.");
			$("#ROW_NUM").focus();
			return;
		}
		if ($.trim($("#BOX_PRICE").val()) == "") {
			alert("이용요금을 입력해 주세요.");
			$("#BOX_PRICE").focus();
			return;
		}
		if ($.trim($("#DEPOSIT").val()) == "") {
			alert("예치금을 입력해 주세요.");
			$("#DEPOSIT").focus();
			return;
		}
		if ($.trim($("#START_NUM").val()) == "") {
			alert("시작번호를 입력해 주세요.");
			$("#START_NUM").focus();
			return;
		}
		if ($.trim($("#END_NUM").val()) == "") {
			alert("종료번호를 입력해 주세요.");
			$("#END_NUM").focus();
			return;
		}
		
		if (($("#END_NUM").val()-$("#START_NUM").val()+1) != $("#BOX_COUNT").val()) {
			alert("사물함 총 갯수가 (종료번호-시작번호+1)와 틀립니다. 다시 입력해 주세요.")
			$("#BOX_COUNT").focus();
			return;
		}

		if (confirm("수정된 사물함 정보를 저장하시겠습니까?")) {
			$("#frm").attr("action", "<c:url value='/box/boxUpdateProcess.do' />");
			$("#frm").submit();
		}
	}
	
	// 목록으로
	function fn_List() {
		$("#frm").attr("action",
				"<c:url value='/box/boxList.do' />");
		$("#frm").submit();
	}
	
	// 사물함 정보 삭제 
	function fn_Delete(){
		if(confirm("사물함 정보를 삭제하겠습니까?")){
			$("#frm").attr("action","<c:url value='/box/boxDeleteProcess.do' />");
			$("#frm").submit();
		}		
	}
	
	// 사물함 정보 삭제 
	function fn_Update(){
		if(confirm("사물함 정보를 삭제하겠습니까?")){
			$("#frm").attr("action","<c:url value='/box/updateBoxFlag.do' />");
			$("#frm").submit();
		}		
	}
	
	// 사물함 상세 정보 보기 
	function fn_view(box_num, rent_seq){
		$("#BOX_NUM").val(box_num);
		$("#RENT_SEQ").val(rent_seq); 
		$("#frm").attr("action","<c:url value='/box/boxRentWrite.do' />");
		$("#frm").submit();
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

<body>
<!--content -->
	<div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="BOX_CD" name="BOX_CD" value="${boxdetail.BOX_CD}">
	<input type="hidden" id="BOX_NUM" name="BOX_NUM" value="">
	<input type="hidden" id="RENT_SEQ" name="RENT_SEQ" value="">
    	<table class="table01">
   		<tr>
   			<th>호</th>
  			<td colspan="3">
	   			${boxdetail.BOX_CD} 호
  			</td>
  		</tr>  		
   		<tr>
   			<th>사물함명</th>
  			<td>
	   			<input type="text" id="BOX_NM" name="BOX_NM" value="${boxdetail.BOX_NM}"
					maxlength="50" title="사물함명" style="width:200px;background:#FFECEC;" />
  			</td>
   			<th>층수</th>
  			<td>
	   			<input type="text" id="ROW_COUNT" name="ROW_COUNT" value="${boxdetail.ROW_COUNT}"
					size="30" maxlength="50" title="층수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 층 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>사물함 갯수</th>
  			<td>
	   			<input type="text" id="BOX_COUNT" name="BOX_COUNT" value="${boxdetail.BOX_COUNT}"
					size="30" maxlength="50" title="사물함 갯수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 개 (※ 숫자만 입력)
  			</td>
   			<th>가로갯수</th>
  			<td>
	   			<input type="text" id="ROW_NUM" name="ROW_NUM" value="${boxdetail.ROW_NUM}"
					size="30" maxlength="50" title="가로 갯수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 개 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>이용요금</th>
  			<td>
	   			<input type="text" id="BOX_PRICE" name="BOX_PRICE" value="${boxdetail.BOX_PRICE}"
					size="30" maxlength="50" title="이용요금"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원 (※ 숫자만 입력)
  			</td>
   			<th>예치금</th>
  			<td>
	   			<input type="text" id="DEPOSIT" name="DEPOSIT" value="${boxdetail.DEPOSIT}"
					size="30" maxlength="50" title="예치금"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>사물함 시작번호</th>
  			<td>
	   			<input type="text" id="START_NUM" name="START_NUM" value="${boxdetail.START_NUM}"
					size="30" maxlength="50" title="사물함 시작번호"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> (※ 숫자만 입력)
  			</td>
   			<th>사물함 종료번호</th>
  			<td>
	   			<input type="text" id="END_NUM" name="END_NUM" value="${boxdetail.END_NUM}"
					size="30" maxlength="50" title="사물함 종료번호"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> (※ 숫자만 입력)
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_Submit();">저장</a></li>
      <li><a href="javascript:fn_List();">목록</a></li>
      <li><a href="javascript:fn_Delete();">삭제</a></li>
      <li><a href="javascript:fn_Update();">사물함정보리셋</a></li>
    </ul>
    <!--//버튼--> 
</form>

<div id="contentBoxNum">

	<table border="0" cellpadding="5" width="741">
	    <tr>
	        <td width="100">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50" bgcolor="#CCCCCC">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용중</p>
	        </td>
	        <td width="50" bgcolor="#f8dcd4">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용가능</p>
	        </td>
	        <td width="50" bgcolor="#f3fc65">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>대기중</p>
	        </td>
	        <td width="50" bgcolor="#79f670">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>홀드</p>
	        </td>
	        <td width="50" bgcolor="#f36262">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>고장</p>
	        </td>
	    </tr>
	</table>
    
   	<!-- 사물함 loop -->
   	<table width="100%" border="0" cellpadding="0" cellspacing="3">
    	<tr>
		<c:forEach items="${boxnumList}" var="item" varStatus="loop">
			<c:choose>
				<c:when test="${item.BOX_FLAG eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
				<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
			</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<a href="javascript:fn_view('${item.BOX_NUM}','${item.RENT_SEQ}');"><p align="center">${item.BOX_NUM}<br><c:if test="${item.BOX_FLAG == 'Y'}">${item.USER_NM}</c:if></p></a>
			</td>
		<c:if test="${((loop.index+1) mod 10) eq 0 }">
			</tr>
			<tr>
		</c:if>								
		</c:forEach>
    	</tr>
	</table>
</div>
</div>

<!--//content --> 
</body>
</html>