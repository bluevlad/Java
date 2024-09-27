<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
</head>
  <!--content -->
<div id="content">
<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

<!--테이블-->    
<form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="POLL_ID" name="POLL_ID" value="" />
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

<table class="table01">
	<tr>
    	<th width="10%">검색</th>
        <td width="50%">            
			<label for="SEARCHKIND"></label>
				제목&nbsp;&nbsp;
			<label for="SEARCHTEXT"></label>
			<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_checkEnter()">
			<input type="button"   onclick="goList(1)"  value="검색" />
		</td>
		<th width="15%">화면출력건수</th>
		<td width="30%">               
        	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
	        <input type="button"   onclick="goList(1)"  value="검색" />
		</td>
	</tr>
</table>
<br>
<div align="right">
<!--//테이블-->
<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
<!--테이블-->
          
<table class="table02">
	<tr>
		<th width="10%">NO</th>
        <th>제목</th>
        <th width="10%">과목명</th>
        <th width="15%">기간</th>
        <th width="10%">문항수</th>
        <th width="10%">참여인원</th>
	</tr>
    <tbody>
    <c:if test="${not empty list}">
	<c:forEach items="${list}" var="list" varStatus="status">
	<tr>
		<td>${totalCount-((params.currentPage-1)*params.pageRow)-status.index}</td>
		<td><a href="javascript:view('${list.POLL_ID}');">${list.TITLE}</a></td>
		<td>${list.SUBJECT_NM}</td>
		<td>${list.START_DT} ~ ${list.END_DT}</td>
		<td><a href="javascript:item_view('${list.POLL_ID}');">${list.ITEM_CNT}</a></td>
		<td><a href="javascript:user_view('${list.POLL_ID}');">${list.USER_CNT}</a></td>
	</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty list}">
	<tr bgColor=#ffffff align=center> 
		<td colspan="9">데이터가 존재하지 않습니다.</td>
	</tr>
	</c:if>	 
    </tbody>
</table>
          
<!--//테이블--> 
<!-- paginate-->
<c:if test="${not empty list}">
	<c:out value="${pagingHtml}" escapeXml="false" />
</c:if>
<!--//paginate-->

<ul class="boardBtns">
	<li><a href="javascript:fn_Reg();">등록</a></li>
</ul>
</form> 
</div>
<!--//content --> 

<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {          
			$('#pageRow').val("");
			$('#pageRow').focus();
			return;       
		} else {          
			if (val < 1) {             
				$('#pageRow').val("");          
				$('#pageRow').focus();            
				return;          
			}       
		}    
	}
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
	
	$('#pageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		goList(1);
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/poll/list.do"/>').submit();
}

//등록
function fn_Reg() {
	$('#searchFrm').attr('action','<c:url value="/poll/write.do"/>').submit();
}

//상세
function view(no) {
	$("#POLL_ID").val(no);
	$('#searchFrm').attr('action','<c:url value="/poll/modify.do"/>').submit();
}

//아이템 항목 보기
function item_view(no) {
	window.open('<c:url value="/poll/item_list.pop"/>?POLL_ID=' + no, '_poll', 'location=no,resizable=no,width=600,height=430,top=0,left=0,location=no,scrollbars=yes');
}

//참여인원 리스트 보기
function user_view(no) {
	window.open('<c:url value="/poll/user_list.pop"/>?POLL_ID=' + no, '_poll', 'location=no,resizable=no,width=600,height=530,top=0,left=0,location=no,scrollbars=yes');
}
</script>  