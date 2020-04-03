<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>

<script type="text/javascript">
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

		if (confirm("사물함 정보를 등록하시겠습니까?")) {
			$("#frm").attr("action",
							"<c:url value='/box/boxInsertProcess.do' />");
			$("#frm").submit();
		}
	}
	
	// 목록으로
	function fn_List() {
		$("#frm").attr("action",
				"<c:url value='/box/boxList.do' />");
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
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

	<h2>● 사물함 관리 &lt; <strong>사물함 등록</strong>
		</h2>	
    	<table class="table01">
   		<tr>
   			<th>호</th>
  			<td>
	   			<input type="text" id="BOX_CD" name="BOX_CD" value="" size="10"
					maxlength="10" title="호" style="width:18%;background:#FFECEC;" /> 호
  			</td>
  		</tr>  		
   		<tr>
   			<th>사물함명</th>
  			<td>
	   			<input type="text" id="BOX_NM" name="BOX_NM" value=""
					size="30" maxlength="50" title="사물함명"
					style="width:18%;background:#FFECEC;" />
  			</td>
  		</tr>
   		<tr>
   			<th>층수</th>
  			<td>
	   			<input type="text" id="ROW_COUNT" name="ROW_COUNT" value=""
					size="30" maxlength="50" title="층수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 층 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>사물함 갯수</th>
  			<td>
	   			<input type="text" id="BOX_COUNT" name="BOX_COUNT" value=""
					size="30" maxlength="50" title="사물함 갯수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 개 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>가로갯수</th>
  			<td>
	   			<input type="text" id="ROW_NUM" name="ROW_NUM" value=""
					size="30" maxlength="50" title="가로 갯수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 개 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>예치금</th>
  			<td>
	   			<input type="text" id="DEPOSIT" name="DEPOSIT" value=""
					size="30" maxlength="50" title="예치금"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 원 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>사물함 시작번호</th>
  			<td>
	   			<input type="text" id="START_NUM" name="START_NUM" value=""
					size="30" maxlength="50" title="사물함 시작번호"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>사물함 종료번호</th>
  			<td>
	   			<input type="text" id="END_NUM" name="END_NUM" value=""
					size="30" maxlength="50" title="사물함 종료번호"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> (※ 숫자만 입력)
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
</body>

</html>