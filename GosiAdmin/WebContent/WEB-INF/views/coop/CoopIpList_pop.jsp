<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//등록
function addCoopIp() {
    $('#Frm').attr('action','<c:url value="/CoopManagement/CoopIpInsertProcess.do"/>').submit();
}

function fn_DeleteIp(uip) {
	$("#O_USER_IP").val(uip);
    $('#Frm').attr('action','<c:url value="/CoopManagement/CoopIpDeleteProcess.do"/>').submit();
}
</script>
</head>
<body>

	<h2>● 등록 아이피 목록</h2>

    <!--테이블-->
    <form id="Frm" name="Frm" method="post">
	<input type="hidden" id="COOP_CD" name="COOP_CD" value="${params.COOP_CD}" />
	<input type="hidden" id="O_USER_IP" name="O_USER_IP" value="" />

    <table class="table04">
		<tr>
			<th>사용IP</th>
			<th>등록일</th>
			<th>#</th>
        </tr>
		<tbody>
		<c:forEach items="${list}" var="list" varStatus="status">
		<tr>
			<td>${list.USER_IP}</td>
		    <td><fmt:formatDate value="${list.REG_DT}" pattern="yyyy-MM-dd"/></td>
		    <td><input type="button" name="input" onClick="fn_DeleteIp('${list.USER_IP}')" value="삭제"></td>
		</tr>
        </c:forEach>
		<tr>
			<th><input type="text" id="USER_IP" name="USER_IP" value="" style="width:200px;"></td>
			<th>#</td>
		    <th><input type="button" name="input" onClick="addCoopIp()" value="신규등록"></td>
		</tr>
		</tbody>
    </table>
    </form>
</body>
</html>