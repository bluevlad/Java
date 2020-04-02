<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="SEQ" name="SEQ" value="">
<input type="hidden" id="V_USER_ID" name="V_USER_ID" value="">

	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
    
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>            
				<label for="SEARCHTYPE"></label>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>아이디</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>이름</option>
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

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="10%">이름</th>
	        <th width="10%">구분</th>
	        <th width="*">
				<table width="100%">
					<tr>
						<th width="*">기기정보</th>
						<th width="20%">등록일</th>
						<th width="20%">삭제일</th>
						<th width="10%">삭제자</th>
						<th width="20%">MacAddress</th>
						<th width="10%">초기화</th>
					</tr>
				</table>
	        </th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
	                <td rowspan="2">
		                ${item.USER_NM}</br>(${item.USER_ID})</br></br>
		                <input type="button" onclick="javascript:fn_Device_Reset_ALL('${item.USER_ID}');" value="일괄초기화" />
	                </td>
	                <td>PC</td>
	                <td>
					    <table width="100%">
				        <c:forEach items="${list_all}" var="al">
				        <c:if test="${item.USER_ID eq al.USER_ID && al.DEVICE_GUBUN eq 'PC'}">
							<tr>
				                <td width="*">${al.DEVICE_ID}</td>
				                <td width="20%">${al.PC_REG_DT}</td>
				                <td width="20%">${al.PC_CANCEL_DT}</td>
				                <td width="10%">${al.ADMIN_ID}</td>
				                <td width="20%">${al.MAC_ADDR}</td>
				                <td width="10%"><c:if test="${al.PC_USEYN eq 'Y'}"><input type="button" onclick="javascript:fn_Device_Reset('${al.SEQ}');" value="초기화" /></c:if></td>
				        	</tr>
						</c:if>
				        </c:forEach>
						</table>
	                </td>
				</tr>
				<tr>
					<td>모바일</td>
					<td>
					    <table width="100%">
				        <c:forEach items="${list_all}" var="al">
				        <c:if test="${item.USER_ID eq al.USER_ID && al.DEVICE_GUBUN eq 'MO'}">
							<tr>
				                <td width="*">${al.DEVICE_ID}</td>
				                <td width="20%">${al.MOBILE_REG_DT}</td>
				                <td width="20%">${al.MOBILE_CANCEL_DT}</td>
				                <td width="10%">${al.ADMIN_ID}</td>
				                <td width="20%">${al.MAC_ADDR}</td>
				                <td width="10%"><c:if test="${al.MOBILE_USEYN eq 'Y'}"><input type="button" onclick="javascript:fn_Device_Reset1('${al.SEQ}');" value="초기화" /></c:if></td>
				        	</tr>
						</c:if>
				        </c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="6">데이터가 존재하지 않습니다.</td>
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
	$("#frm").attr("action", "<c:url value='/macaddressmanager/list_all.do' />");
	$("#frm").submit();
}

//검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/macaddressmanager/list_all.do' />");
	$("#frm").submit();
}

function fn_Device_Reset(seq) {
	if(confirm("선택한 항목을 초기화 하시겠습니까?")){
		$("#currentPage").val(1);
		$("#SEQ").val(seq);
		$("#frm").attr("action", "<c:url value='/macaddressmanager/macaddressUpdate.do' />");
		$("#frm").submit();
	}
}

function fn_Device_Reset1(seq) {
	if(confirm("선택한 항목을 정말 초기화 하시겠습니까?")){
		$("#currentPage").val(1);
		$("#SEQ").val(seq);
		$("#frm").attr("action", "<c:url value='/macaddressmanager/macaddressUpdate1.do' />");
		$("#frm").submit();
	}
}

function fn_Device_Reset_ALL(user_id) {
	if(confirm("회원 디바이스정보를 일괄 초기화 하시겠습니까?")){
		$("#currentPage").val(1);
		$("#V_USER_ID").val(user_id);
		$("#frm").attr("action", "<c:url value='/macaddressmanager/MacAdrUserUpdate.do' />");
		$("#frm").submit();
	}
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