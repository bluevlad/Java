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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">

<input type="hidden" id="COOP_CD" name="COOP_CD" value="${params.COOP_CD}" />
<input type="hidden" id="LECCODE" name="LECCODE" value="${params.LECCODE}" />

    <h2>● 쿠폰 발급 목록</h2>
    
    <table class="table04">
    	<col width="15%">
    	<col width="35%">
    	<col width="15%">
    	<col width="35%">
        <tr>
			<th>검색</th>
			<td>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>쿠폰번호</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>인증아이디</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="20" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th>화면출력건수</th>
          <td><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>

<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    
    
	<table class="table05">
    	<col width="50">
    	<col width="*">
    	<col width="100">
    	<col width="200">
		<tr>
	        <th>No</th>
	        <th>쿠폰번호</th>
	        <th>인증아이디</th>
	        <th>인증일시</th>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
		        <td class="tdCenter">	${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
		        <td>${item.C_CD}</td>
		        <td><a href="javascript:view('${item.USER_ID}')">${item.USER_ID}</a></td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd hh:mm"/></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="4">데이터가 존재하지 않습니다.</td>
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
	$("#frm").attr("action", "<c:url value='/Coupon/coop_coupon_all.pop' />");
	$("#frm").submit();
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/Coupon/coop_coupon_all.pop' />");
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

//주문자 클릭시
function view(userid){
 
	if(userid=="" || userid ==null){
    	alert("비회원입니다.");
     	return;
	}else{
 		window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}
}
</script>
</body>
</html>