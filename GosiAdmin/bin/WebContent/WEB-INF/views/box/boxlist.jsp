<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${ msgStr ne '' && msgStr ne null}">alert('${msgStr}')</c:if>
});

//등록
	function addBox() {
		$('#frm').attr('action', '<c:url value="/box/boxInsert.do"/>').submit();
	}

	//회원 상세
	function view(BOX_CD) {
		$("#BOX_CD").val(BOX_CD);
		$('#frm').attr('action', '<c:url value="/box/boxView.do"/>').submit();
	}
</script>
</head>
<body>
	<!--content -->
	<div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

		<form id="frm" name="frm" method="post" action="">
	    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
		<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

		<input type="hidden" id="BOX_CD" name="BOX_CD" value="" />

			<!--버튼-->
			<ul class="boardBtns">
				<li><a href="javascript:addBox();">등록</a></li>
			</ul>
			<!-- 테이블 -->
			<table class="table02">
				<tr>
					<th width="85">번호</th>
					<th>사물함명</th>
					<th>총 갯수</th>
					<th>사용 갯수</th>
					<th>예약가능 갯수</th>
					<th>사물함대여금액</th>
					<th>예치금</th>
				</tr>
				<tbody>
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list" varStatus="status">
							<tr>
								<td>${(status.index+1)}</td>
								<td><a href="javascript:view('${list.BOX_CD}')">${list.BOX_NM}[${list.BOX_CD}]</a></td>
								<td>${list.BOX_COUNT}</td>
								<td>${list.Y_CNT}</td>
								<td>${list.N_CNT}</td>
								<td>${list.BOX_PRICE}</td>
								<td>${list.DEPOSIT}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}">
						<tr bgColor=#ffffff align=center>
							<td colspan="7">등록된 사물함이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</form>
	</div>
	<!--//content -->
</body>
</html>