<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
<script type="text/javascript">
// 등록
function fn_Submit(){
	if(confirm("내용을 변경하시겠습니까?")){
		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
			type: "POST",
			url : '<c:url value="/poll/ItemUpdate.json"/>',
			data: dataString,
		    dataType: "text",  
		    async : false,
		    success: function(RES) {
				if($.trim(RES)=="Y"){
		        	alert("온라인폴 정보가 변경되었습니다.");
					location.reload();
		        	return;
				}
			},error: function(){
				alert("수정 실패");
		    	return;
			}
		});
	}
}

function fn_Insert(){
	if(confirm("문항을 추가하시겠습니까?")){
		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
			type: "POST",
			url : '<c:url value="/poll/ItemInsert.json"/>',
			data: dataString,
		    dataType: "text",  
		    async : false,
		    success: function(RES) {
				if($.trim(RES)=="Y"){
		        	alert("문항이 추가되었습니다.");
					location.reload();
		        	return;
				}
			},error: function(){
				alert("수정 실패");
		    	return;
			}
		});
	}
}

function fn_Delete(no){
	if(confirm("문항을 삭제하시겠습니까?")){
		$("#POLL_ITEM_ID").val(no);
		var dataString = $("#frm").serialize(); // frm은 form 이름
		$.ajax({
			type: "POST",
			url : '<c:url value="/poll/ItemDelete.json"/>',
			data: dataString,
		    dataType: "text",  
		    async : false,
		    success: function(RES) {
				if($.trim(RES)=="Y"){
		        	alert("문항이 삭제되었습니다.");
					location.reload();
		        	return;
				}
			},error: function(){
				alert("수정 실패");
		    	return;
			}
		});
	}
}
</script>
</head>
<!--content -->
<h2>● 강의관리 > <strong>온라인설문조사</strong></h2>
    
<!--테이블-->
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="POLL_ID" name="POLL_ID" value="${params.POLL_ID}"/>
<input type="hidden" id="POLL_ITEM_ID" name="POLL_ITEM_ID" value=""/>
<table class="table04">
	<tr>
		<th width="5%">NO</th>
        <th>문항명</th>
        <th width="10%">응답인원</th>
        <th width="10%">#</th>
	</tr>
    <tbody>
    <c:if test="${not empty list}">
	<c:forEach items="${list}" var="list" varStatus="status">
	<input type="hidden" id="v_POLL_ITEM_ID" name="v_POLL_ITEM_ID" value="${list.POLL_ITEM_ID}"/>
	<tr>
		<td><input type="text" id="SEQ_${list.POLL_ITEM_ID}" name="SEQ_${list.POLL_ITEM_ID}" value="${list.SEQ}" style="width:95%;background:#FFECEC;"/></td>
		<td><input type="text" id="VIW_${list.POLL_ITEM_ID}" name="VIW_${list.POLL_ITEM_ID}" value="${list.VIW}" style="width:95%;background:#FFECEC;"/></td>
		<td>${list.CNT}</td>
		<td><input type="button" onclick="fn_Delete('${list.POLL_ITEM_ID}')"  value="삭제" /></td>
	</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty list}">
	<tr bgColor=#ffffff align=center> 
		<td colspan="5">데이터가 존재하지 않습니다.</td>
	</tr>
	</c:if>	 
	<tr>
		<td>#</td>
		<td><input type="text" id="VIW" name="VIW" value="" style="width:95%;background:#FFECEC;"/></td>
		<td>#</td>
		<td><input type="button" onclick="fn_Insert()"  value="추가" /></td>
	</tr>
    </tbody>
</table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">저장</a></li>
    </ul>
    <!--//버튼-->
</form>
<!--//테이블--> 
</html>