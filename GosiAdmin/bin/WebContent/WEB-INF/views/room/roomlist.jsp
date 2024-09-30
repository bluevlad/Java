<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
	//등록
	function addRoom() {
		$('#frm').attr('action', '<c:url value="/room/roomInsert.do"/>').submit();
	}

	//회원 상세
	function view(ROOM_CD) {
		$("#ROOM_CD").val(ROOM_CD);
		$('#frm').attr('action',
				'<c:url value="/room/roomView.do"/>').submit();
	}

</script>
</head>
<body>
	<!--content -->
	<div id="content">
		<h2>
			● 독서실관리 > <strong>독서실관리</strong>
		</h2>

		<form id="frm" name="frm" method="post" action="">
			<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
			<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
			<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

			<input type="hidden" id="ROOM_CD" name="ROOM_CD" value="" />

			<!--버튼-->
			<ul class="boardBtns">
				<li><a href="javascript:addRoom();">등록</a></li>
			</ul>
			<!-- 테이블 -->
			<table class="table02">
				<tr>
					<th width="85">번호</th>
					<th>독서실명</th>
					<th>총 좌석수</th>
					<th>사용 좌석수</th>
					<th>남은 좌석수</th>
				</tr>
				<tbody>
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list" varStatus="status">
							<tr>
								<td>${(status.index+1)}</td>
								<td><a href="javascript:view('${list.ROOM_CD}')">${list.ROOM_NM}[${list.ROOM_CD}]</a></td>
								<td>${list.ROOM_COUNT}</td>
								<td>${list.Y_CNT}</td>
								<td>${list.N_CNT}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}">
						<tr bgColor=#ffffff align=center>
							<td colspan="5">등록된 독서실이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</form>
	</div>
	<!--//content -->
</body>
</html>