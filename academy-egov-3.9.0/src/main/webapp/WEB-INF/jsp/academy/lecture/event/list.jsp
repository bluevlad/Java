<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="EVENT_ID" name="EVENT_ID" value="">

    <h2>● 강의관리 > <strong>강의구매할인이벤트</strong></h2>
    
    <table class="table01">
        <tr>
			<th width="15%">이벤트명</th>
			<td>
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()"/>
          </td>
          <th width="15%">화면출력건수</th>
          <td width="30%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>
    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
    
<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    
    
	<table class="table02">
		<tr>
	        <th width="50">No </th>
	        <th>이벤트명</th>
	        <th width="80">강의수</th>
	        <th width="80">이벤트방법</th>
	        <th width="80">할인비율(금액)</th>
	        <th width="80">시작일</th>
	        <th width="80">종료일</th>	        
	        <th width="80">진행여부</th>	        
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
		        <td class="tdCenter">${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
		        <td><a href="javascript:fn_Modify('${item.EVENT_ID}');">${item.EVENT_NM}</a></td>
		        <td>${item.CNT}</td>
		        <td>${item.EVENT_TYPE}</td>
		        <td>${item.EVENT_AMOUNT}</td>
		        <td>${item.ST_DATE}</td>
		        <td>${item.ED_DATE}</td>
		        <td class="txt03"><c:if test="${item.EVENT_YN eq 'Y' }">진행</c:if><c:if test="${item.EVENT_YN ne 'Y' }">종료</c:if></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center>
				<td colspan="10">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
    
</form>
</div>
<script type="text/javascript">
// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	$("#frm").submit();
}

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
			$("#frm").attr("action", "<c:url value='/lecture/listDelete.do' />");
			$("#frm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/productevent/list.do' />");
	$("#frm").submit();
}

// 등록폼
function fn_Reg(){
	$("#frm").attr("action", "<c:url value='/productevent/write.do'/>");
	$("#frm").submit();
}

// 수정폼
function fn_Modify(val){
	$("#EVENT_ID").val(val);
	$("#frm").attr("action", "<c:url value='/productevent/modify.do' />");
	$("#frm").submit();
}

// 엔터키 검색
function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_Search();
	});
}

// RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
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


// 리로드
function fn_reload(){
	$("#frm").attr("action", "<c:url value='/product_event/list.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>