<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<title>독서실 변경</title>
<script type="text/javascript">
	// 독서실 변경
	function change_roomnum(roomnum) {
		if (confirm("독서실 번호 [" + roomnum + "]를 선택했습니다. 이 번호로 변경하겠습니까?")) {
			$("#ROOM_NUM").val($.trim(roomnum));
			$("#frm").attr("action", "<c:url value='/room/roomChangePopProcess.do' />");
			$("#frm").submit();
			window.opener.document.location.href="<c:url value='/room/roomView.do' />?ROOM_CD="+$("#ROOM_CD").val()+"&TOP_MENU_ID=<c:out value='${params.TOP_MENU_ID}'/>&MENU_ID=<c:out value='${params.MENU_ID}'/>";
			window.close();
		}
	}

	// 다른 독서실 층을 선택
	function fn_Change_roomCD(roomcd) {
		$("#ROOM_CD").val($.trim(roomcd));
		$("#frm").attr("action", "<c:url value='/room/roomChangePop.pop' />");
		$("#frm").submit();
	}

</script>
</head>
       		
<body>
<!--content -->
<div id="content_pop">
	<h2>● 독서실 관리 &gt; <strong>독서실 변경</strong></h2> 
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="USED_ROOM_CD" name="USED_ROOM_CD" value="${USED_ROOM_CD}">
	<input type="hidden" id="USED_ROOM_NUM" name="USED_ROOM_NUM" value="${USED_ROOM_NUM}">
	<input type="hidden" id="USED_RENT_SEQ" name="USED_RENT_SEQ" value="${USED_RENT_SEQ}">
	<input type="hidden" id="ROOM_CD" name="ROOM_CD" value="${ROOM_CD}">
	<input type="hidden" id="ROOM_NUM" name="ROOM_NUM" value="">
	
	<div>
		<table style="border-padding:1px; border-collapse:collapse; border:#d2d2d2 1px solid; border-top:2px solid #000; width:100%; height:40px; text-align:center; background-color:#FFF;">
			<colgroup>
				<col width="33.3%"/>
				<col width="33.3%"/>
				<col width="33.4%"/>
			</colgroup>
			<tbody>
				<tr>
					<td><a href="javascript:fn_Change_roomCD('101');"><c:if test="${ROOM_CD eq '101'}"><font color="red"><b></c:if>독서실 A동<c:if test="${ROOM_CD eq '101'}"></b></font></c:if></a></td>
					<td><a href="javascript:fn_Change_roomCD('201');"><c:if test="${ROOM_CD eq '201'}"><font color="red"><b></c:if>독서실 B동<c:if test="${ROOM_CD eq '201'}"></b></font></c:if></a></td>
					<td><a href="javascript:fn_Change_roomCD('301');"><c:if test="${ROOM_CD eq '301'}"><font color="red"><b></c:if>독서실 C동<c:if test="${ROOM_CD eq '301'}"></b></font></c:if></a></td>
				</tr>
			</tbody>
		</table>
	</div>

</form>
</div>
<div id="contentRoomNum">

	<table border="0" cellpadding="5" width="741">
	    <tr>
	        <td width="100">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50" bgcolor="#CCCCCC">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용중</p>
	        </td>
	        <td width="50" bgcolor="#f8dcd4">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>사용가능</p>
	        </td>
	        <td width="50" bgcolor="#f3fc65">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>대기중</p>
	        </td>
	        <td width="50" bgcolor="#79f670">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>홀드</p>
	        </td>
	        <td width="50" bgcolor="#f36262">
	            <p>&nbsp;</p>
	        </td>
	        <td width="50">
	            <p>고장</p>
	        </td>
	    </tr>
	</table>
    
   	<!-- 독서실 loop -->
   	<table style="width:100%;background:#FFECEC;border:0;cellpadding:0;cellspacing:0;">
    	<tr>
		<c:forEach items="${roomnumList}" var="item" varStatus="loop">
			<c:choose>
				<c:when test="${item.ROOM_FLAG eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
				<c:when test="${item.ROOM_FLAG eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
				<c:when test="${item.ROOM_FLAG eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
				<c:when test="${item.ROOM_FLAG eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
				<c:when test="${item.ROOM_FLAG eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
				<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
			</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<c:choose>
				<c:when test="${item.ROOM_FLAG eq 'N'}">
					<p align="center"><a href="javascript:change_roomnum('${item.ROOM_NUM}');">${item.ROOM_NUM}</a></p>
				</c:when>
				<c:otherwise>
					<p align="center">${item.ROOM_NUM}</p>
				</c:otherwise>
				</c:choose>
			</td>
		<c:if test="${((loop.index+1) mod 10) eq 0 }">
		</tr>
		<tr>
		</c:if>								
		</c:forEach>
    	</tr>
	</table>
</div>

<!--//content --> 
</body>
</html>