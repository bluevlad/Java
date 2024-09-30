<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("START_DATE", "END_DATE");
});

//등록
function fn_Submit(){
	if($.trim($("#TITLE").val())==""){
		alert("이벤트명을 입력해주세요");
		$("#TITLE").focus();
		return;
	}

	if(confirm("이벤트를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/LecEventMng/eventInsertProcess.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action", "<c:url value='/LecEventMng/eventMgtList.do' />");
	$("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
	<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
	<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
        
	<table class="table01">
		<tr>
			<th>시작일</th>
		    <td class="tdLeft"><input type="text" id="START_DATE" name="START_DATE" size="10"></td>
		</tr>
		<tr>
			<th>종료일</th>
		    <td class="tdLeft"><input type="text" id="END_DATE" name="END_DATE" size="10"></td>
		</tr>
		<tr>
			<th>제목</th>
		    <td class="tdLeft"><input type="text" id="TITLE" name="TITLE" style="width:95%;"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea id="CONTENTS_TEXT" name="CONTENTS_TEXT" title="레이블 텍스트" style="width:95%;height:100px"></textarea></td>
		</tr>
	</table>
	
	<!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_Submit();">확인</a></li>
	    <li><a href="javascript:fn_List();">목록</a></li>
	</ul>
	<!--//버튼--> 
	</form>
	
</div>
<!--//content --> 
