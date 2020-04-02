<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript">

function paramCheck() {
	
	if($("#CONT").val() == "") {
		alert("쪽지내용을 등록해 주세요.");
		$("#CONT").focus();
	} 
	else {
		if(confirm("수정하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/note/update.do' />");
			$("#frm").submit();			
		}
	}
}
function fn_List(){
	$("#frm").attr("action", "<c:url value='/note/list.do' />");
	$("#frm").submit();
}

//글자수 제한 체크 
function len_chk(){  
  var frm = document.frm.CONT; 
    
  if(frm.value.length > 4000){  
       alert("글자수는 영문4000, 한글2000자로 제한됩니다.!");  
       frm.value = frm.value.substring(0,4000);  
       frm.focus();  
  } 
} 
function fn_noteDelete(){
	if(confirm("삭제하시겠습니까?")){
		$("#frm").attr("action", "<c:url value='/note/delete.do' />");
		$("#frm").submit();	
	}
}
</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm"  method="post" action="">
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="NOTEID" name="NOTEID" value="${view.NOTEID}">

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    	<h2>● 쪽지 관리  > <strong>쪽지 관리</strong></h2>
    	<table class="table01">
   		<tr>
			<th width="20%">수신인</th>
			<td>${view.FROM_USERNM }</td>
		</tr>
   		<tr>
			<th>전송인</th>
			<td>${view.SENDNM }</td>
		</tr>
		<tr>
   			<th scope="col">전송시간</th>
   			<td scope="col" style="text-align:left;" colspan="3">
   			${view.SEND_DT }	
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">쪽지 내용</th>
   			<td scope="col" colspan="3" style="text-align:left;">
   				<textarea name="CONT" id="CONT" rows="10" style="width:68%; background:#FFECEC;" maxlength="1333">${view.CONT}</textarea>
   			</td>
   		</tr>	
   		<tr>
    		<th scope="col">수신여부</th>
   			<td scope="col" colspan="3" style="text-align:left;">
   				<div class="item">
	   				<input name="READ_YN"  class="i_check" value="Y" type="radio"  <c:if test="${view.READ_YN == 'Y' }">checked="checked"</c:if>><label for="a2">수신</label>&nbsp;&nbsp;
	   				<input name="READ_YN"  class="i_check" value="N" type="radio"  <c:if test="${view.READ_YN == 'N' }">checked="checked"</c:if>><label for="a3">미수신</label>
				</div>
   			</td>
   		</tr>	
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
     <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">수정</a></li>
	   	  <li><a href="javascript:fn_noteDelete();">삭제</a></li>
	      <li><a href="javascript:fn_List();">목록</a></li>
	  </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
