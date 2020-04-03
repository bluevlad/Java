<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
<div id="content">
	<form id="frm" name="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
	<input type="hidden" id="LECCODE" name="LECCODE" value="">
	<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${params.EVENT_NO}">

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg('${params.EVENT_NO}');">강의등록</a></li>
    	<li><a href="javascript:fn_Delete();">강의삭제</a></li>
    	<li><a href="javascript:fn_List();">이벤트목록</a></li>
    </ul>
    
	<table class="table01">
	      <tr>
	        <th>직렬</th>
	        <td class="tdLeft">${ view.CATEGORY_NAME }</td>
	      </tr>
	      <tr>
	        <th>제목</th>
	        <td class="tdLeft">${ view.TITLE }</td>
	      </tr>
	</table>
    <br>
	<table class="table02">
		<tr>
	        <th width="110"><input type="checkbox" name="allCheck" id="allCheck" value="" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" /> No</th>
	        <th width="80">직종</th>
	        <th width="75">강의코드</th>
	        <th>강의명</th>
	        <th width="60">개설여부</th>	        
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
		        <td class="tdCenter">
					<input type="checkbox" name="DEL_ARR" value="${item.LECCODE}" />
		        	${loop.index+1}
		        </td>
		        <td>${item.CATEGORY_NM}</td>
		        <td>${item.LECCODE}</td>
		        <td style="text-align:left;">${item.SUBJECT_TITLE}</td>
		        <td class="txt03"><c:if test="${item.SUBJECT_ISUSE eq 'Y' }">활성</c:if><c:if test="${item.SUBJECT_ISUSE ne 'Y'}">비활성</c:if></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center>
				<td colspan="10">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg('${params.EVENT_NO}');">강의등록</a></li>
    	<li><a href="javascript:fn_Delete();">강의삭제</a></li>
    	<li><a href="javascript:fn_List();">이벤트목록</a></li>
    </ul>
    
</form>
</div>

<script type="text/javascript">
//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//삭제
function fn_Delete(){
	if($("input[name='DEL_ARR']:checked").length > 0){
		if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/LecEventMng/eventLecturedeleteProcess.do' />");
			$("#frm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

// 등록폼
function fn_Reg(event_no){
	window.open('<c:url value="/LecEventMng/lectureWrite.pop"/>?EVENT_NO=' + event_no, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=800');
}

//이벤트 목록
function fn_List(){
	$("#frm").attr("action", "<c:url value='/LecEventMng/eventMgtList.do'/>");
	$("#frm").submit();
}
</script>
</body>
</html>