<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">

	<h2>● 강의 관리 > <strong>과목관리</strong></h2>
    
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>            
				<label for="SEARCHTYPE"></label>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>코드</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>과목명</option>
				</select>
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
			</td>
		    <th width="15%">화면출력건수</th>
		    <td width="30%">               
	             <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="검색어" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
		         <input type="button" onclick="fn_Search()" value="검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
    
    <!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_Reg();">등록</a></li>
	    <li><a href="javascript:fn_Delete();">삭제</a></li>
	</ul>
    <!--//버튼--> 
        
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="100"><input type="checkbox" name="allCheck" id="allCheck" class="i_check" value="" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />No</th>
	        <th width="100">코드</th>
	        <th width="200">과목명</th>
	        <th width="100">상태</th>
	        <th width="100">등록일</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr <c:if test="${item.ISUSE == 'Y'}">class="vitality"</c:if>>
		    	<td><input type="checkbox" name="DEL_ARR" class="i_check" value="${item.SUBJECT_CD}" /> ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
                <td>${item.SUBJECT_CD}</td>
                <td><a href="javascript:fn_Modify('${item.SUBJECT_CD}');">${item.SUBJECT_NM}</a></td>
                <td>${item.ISUSENM}</td>
                <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
	    </tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr bgColor=#ffffff align=center> 
			<td colspan="10">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블--> 
   
	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">
// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/subject/list.do' />");
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

// 삭제
function fn_Delete(){
	if($("input[name='DEL_ARR']:checked").length > 0){
		if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/subject/listDelete.do' />");
			$("#frm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

//검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/subject/list.do' />");
	$("#frm").submit();
}

//검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/subject/list.do' />");
	$("#frm").submit();
}

// 등록폼
function fn_Reg(){
	$("#frm").attr("action", "<c:url value='/subject/write.do'/>");
	$("#frm").submit();
}

// 수정폼
function fn_Modify(val){
	$("#SUBJECT_CD").val(val);
	$("#frm").attr("action", "<c:url value='/subject/modify.do' />");
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
</script>
</body>
</html>