<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head></head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="RSC_ID" name="RSC_ID" value="${params.RSC_ID}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>

	<h2>● 교재 관리 > <strong>교재관리</strong></h2>
 
     <!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_Excel();">엑셀다운로드</a></li>
	</ul>
    <!--//버튼--> 
 
     <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="85">No</th>
	        <th>주문번호</th>
	        <th>주문자</th>
	        <th>결제수단</th>
	        <th>등록일</th>
	        <th>상품명</th>
	        <th>구입금액</th>
	        <th>상태</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			    	<td>${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
					<td>${item.ORDERNO}</td>
					<td>${item.ORDERS_USERNAME}<br/>(${item.ORDERS_USERID})</td>
					<td>${item.PAY_NM}</td>
					<td><fmt:formatDate value="${item.ORDERS_REGDATE}" pattern="yyyy-MM-dd"/></td>
					<td>${item.BOOK_NM}</td>
					<td>${item.PRICE}</td>
					<td>${item.STATUSNAME }</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="8">데이터가 존재하지 않습니다.</td>
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
	$("#frm").attr("action", "<c:url value='/book/sellList.do' />");
	$("#frm").submit();
}

// 엑셀다운로드
function fn_Excel() {
	$("#frm").attr("action", "<c:url value='/book/sellListExcel.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>