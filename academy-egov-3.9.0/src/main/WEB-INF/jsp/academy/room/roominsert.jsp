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
		if ($.trim($("#ROOM_CD").val()) == "") {
			alert("호를 입력해 주세요.");
			$("#ROOM_CD").focus();
			return;
		}
		if ($.trim($("#ROOM_NM").val()) == "") {
			alert("독서실 이름을 입력해 주세요.");
			$("#ROOM_NM").focus();
			return;
		}
		if ($.trim($("#ROOM_COUNT").val()) == "") {
			alert("독서실 총 좌석수를 입력해 주세요.");
			$("#ROOM_COUNT").focus();
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
		
		if (($("#END_NUM").val()-$("#START_NUM").val()+1) != $("#ROOM_COUNT").val()) {
			alert("독서실 총 갯수가 (종료번호-시작번호+1)와 틀립니다. 다시 입력해 주세요.")
			$("#ROOM_COUNT").focus();
			return;
		}

		if (confirm("독서실 정보를 등록하시겠습니까?")) {
			$("#frm").attr("action",
							"<c:url value='/room/roomInsertProcess.do' />");
			$("#frm").submit();
		}
	}
	
	// 목록으로
	function fn_List() {
		$("#frm").attr("action",
				"<c:url value='/room/roomList.do' />");
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

	<h2>● 독서실 관리 &lt; <strong>독서실 등록</strong>
		</h2>	
    	<table class="table01">
   		<tr>
   			<th>호</th>
  			<td>
	   			<input type="text" id="ROOM_CD" name="ROOM_CD" value="" size="10"
					maxlength="10" title="호" style="width:18%;background:#FFECEC;" /> 호
  			</td>
  		</tr>  		
   		<tr>
   			<th>독서실명</th>
  			<td>
	   			<input type="text" id="ROOM_NM" name="ROOM_NM" value=""
					size="30" maxlength="50" title="독서실명"
					style="width:18%;background:#FFECEC;" />
  			</td>
  		</tr>
   		<tr>
   			<th>독서실 좌석수</th>
  			<td>
	   			<input type="text" id="ROOM_COUNT" name="ROOM_COUNT" value=""
					size="30" maxlength="50" title="독서실 좌석수"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> 개 (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>독서실 시작번호</th>
  			<td>
	   			<input type="text" id="START_NUM" name="START_NUM" value=""
					size="30" maxlength="50" title="독서실 시작번호"
					style="width:18%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);" /> (※ 숫자만 입력)
  			</td>
  		</tr>
   		<tr>
   			<th>독서실 종료번호</th>
  			<td>
	   			<input type="text" id="END_NUM" name="END_NUM" value=""
					size="30" maxlength="50" title="독서실 종료번호"
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