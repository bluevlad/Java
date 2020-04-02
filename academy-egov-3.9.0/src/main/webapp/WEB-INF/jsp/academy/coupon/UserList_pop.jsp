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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="CCODE" name="CCODE" value="${params.CCODE}">

    <h2>● 쿠폰 사용자 목록</h2>
    
    <table class="table01">
        <tr>
			<th width="15%">검색</th>
			<td width="35%">
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="0">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>사용자아이디</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>사용자명</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="20" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="35%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>

<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    
    
	<table class="table02">
		<tr>
	        <th width="40">No</th>
	        <th width="80">사용자(아이디)</th>
	        <th width="60">발급일</th>
	        <th width="60">주문번호</th>
	        <th width="60">주문일자</th>
	        <th width="40">주문수량</th>
	        <th width="60">주문금액</th>
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
		        <td class="tdCenter">	${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
		        <td>${item.USER_NM}(${item.USERID})</td>
		        <td><fmt:formatDate value="${item.REGDATE}" pattern="yyyy-MM-dd"/></td>
		        <td>${item.ORDERNO}</td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <td>${item.CNT}</td>
		        <td><fmt:formatNumber value="${item.PRICE}" type="currency"/></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="7">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
</form>

<script type="text/javascript">
// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/Coupon/user_list.pop' />");
	$("#frm").submit();
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/Coupon/user_list.pop' />");
	$("#frm").submit();
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