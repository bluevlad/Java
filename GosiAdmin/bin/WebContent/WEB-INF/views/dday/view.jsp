<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("DDAY_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_list(){
	$("#frm").attr("action","<c:url value='/dday/list.do' />");
	$("#frm").submit();
}

// 등록
function fn_insert(type){
 	if($.trim($("#DDAY_NAME").val()) == ""){
 		alert("D-day 설명을 입력하세요");
 		$("#DDAY_NAME").focus();
        return;
 	}
 	if($.trim($("#DDAY_DATE").val()) == ""){
 		alert("D-day 날짜를 입력하세요");
 		$("#DDAY_DATE").focus();
        return;
 	}
	
 	if(type == "I") {
		if(confirm("등록 하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/dday/insertProcess.do' />");
			$("#frm").submit();
		}
 	} else {
		if(confirm("수정 하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/dday/updateProcess.do' />");
			$("#frm").submit();
		}
 	}
}

// 삭제
function fn_Delete(){
	if(confirm("정말 삭제하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/dday/deleteProcess.do' />");
		$("#frm").submit();
	}
}

</script>
</head>
<body>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>

<input type="hidden" id="searchDdayName" name="searchDdayName" value="${searchDdayName}"/>
<input type="hidden" id="searchCategory" name="searchCategory" value="${searchCategory}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${pageRow}"/>

<input type="hidden" id="DDAY_IDX" name="DDAY_IDX" value="${DDAY_IDX}"/>

	<h2>● 사이트관리 > <strong>D-Day관리</strong></h2>
	
    <!-- 테이블-->
   	<table class="table01">
   		<tr>
   			<th>직종</th>
  			<td>
            	<select id="DDAY_CATEGORY" name="DDAY_CATEGORY">
            		<c:forEach items="${categoryList}" var="item" varStatus="loop">
            		<option value="${item.CODE}" <c:if test="${item.CODE eq view.DDAY_CATEGORY}">selected</c:if>>${item.NAME}</option>
            		</c:forEach>
            	</select>
  			</td>
  		</tr>
   		<tr>
   			<th>D-day 설명</th>
  			<td>
	   			<input type="text" id="DDAY_NAME" name="DDAY_NAME" value="${view.DDAY_NAME}" style="width:80%;background:#FFECEC;"/>
  			</td>
  		</tr>
   		<tr>
   			<th>D-day 날짜</th>
  			<td>
	   			<input type="text" id="DDAY_DATE" name="DDAY_DATE" value="${view.DDAY_DATE}" style="width:60;background:#FFECEC;" maxlength="8" readonly="readonly"/>
  			</td>
  		</tr>		
		<tr>
   			<th>D-day Link</th>
  			<td><input type="text" id="DDAY_LINK" name="DDAY_LINK" value="${view.DDAY_LINK}" style="width:80%;"/></td>
  		</tr>
   		<tr>
   			<th>D-day Type</th>
  			<td>
	   			<select id="DDAY_TYPE" name="DDAY_TYPE">
	   				<option value="P">관리자가 입력해놓은 D-day</option>
	   			</select>
  			</td>
  		</tr>
		<tr>
   			<th>개인 사용자 활성 여부</th>
  			<td>
	   			<select id="DDAY_ACTIVE" name="DDAY_ACTIVE">
	   				<option value="F">비활성</option>
	   			</select>
  			</td>
  		</tr>
	</table>
    
    <!--버튼-->
	<ul class="boardBtns">
		<c:if test='${DDAY_IDX eq "-1" }'>
   		<li><a href="javascript:fn_insert('I');">등록</a></li>
		<li><a href="javascript:fn_list();">목록</a></li>
		</c:if>
		<c:if test='${DDAY_IDX ne "-1" }'>
   		<li><a href="javascript:fn_insert('U');">수정</a></li>
		<li><a href="javascript:fn_Delete();">삭제</a></li>
		<li><a href="javascript:fn_list();">목록</a></li>
		</c:if>
    </ul>    
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 


</body>
</html>