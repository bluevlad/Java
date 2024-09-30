<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	<div class="loginInfo">
		<c:if test="${ST == 'Y'}">
		<p>담당자 외 접속은 IP 추적을 통해 법적 조치하겠습니다.</p>
        <h2><strong>${AdmUserInfo.USER_NM}님 접속 정보</strong></h2>
		<table class="table04" cellspacing="0">
			<colgroup>
				<col width="">
			</colgroup>

			<tr>
				<th class="tL">현재 접속 IP</th>
			</tr>
			<tr>
				<td>${USER_IP}</td>
			</tr>
			<tr>
				<th class="tL">오늘 로그인 기록</th>
			</tr>
			<tr>
				<td>
			        <c:forEach items="${log_ip}" var="item" varStatus="index">
                    <p>[${item.ACCESS_DT}] [${item.USER_IP}] [로그인<c:if test="${USER_IP==item.USER_IP}">성공</c:if><c:if test="${USER_IP!=item.USER_IP}">실패</c:if>] </p>
                    </c:forEach>
				</td>
			</tr>
		</table> 
		</c:if>
	</div>
