<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
function fn_search() {
	$("#form").attr("action", "<c:url value='/dday/list.do' />");
	$("#form").submit();
}
function fn_view(idx) {
	$("#DDAY_IDX").val(idx);
	$("#form").attr("action", "<c:url value='/dday/view.do' />");
	$("#form").submit();
}
//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#form').attr('action','<c:url value="/dday/list.do"/>').submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="DDAY_IDX" name="DDAY_IDX"/>
<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">

	<h2>● 사이트관리 > <strong>D-day관리</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="10%">직종</th>
            <td>
            	<select id="searchCategory" name="searchCategory">
            		<option value="">전체</option>
            		<c:forEach items="${categoryList}" var="item" varStatus="loop">
            		<option value="${item.CODE}" <c:if test="${item.CODE eq searchCategory}">selected</c:if>>${item.NAME}</option>
            		</c:forEach>
            	</select>
            </td>
            <th width="10%">D-day 설명</th>
            <td>
            	<input type="text" id="searchDdayName" name="searchDdayName" value="${searchDdayName}"/> 
            </td>
            <th width="10%">화면출력건수</th>
		    <td>               
	             <input type="text" id="pageRow" name="pageRow" value="${pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
				<input type="button" onclick="fn_search()" value="검색" />
            </td>
            
		</tr>
	</table>
    <!-- //검색 -->
     
    <!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_view('-1');">등록</a></li>
	</ul>
    <!--//버튼--> 
        
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
     
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th>직종</th>
	        <th>D-day 설명</th>
	        <th>D-day 날짜</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
			<tr>
				<td>${item.DDAY_CATEGORY}</td>
				<td><a href="javascript:fn_view('${item.DDAY_IDX}');">${item.DDAY_NAME}</a></td>
				<td>${item.DDAY_DATE}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	 
	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
	 
</form>
</div>    

</html>