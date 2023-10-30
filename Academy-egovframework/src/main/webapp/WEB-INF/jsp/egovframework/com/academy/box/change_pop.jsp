<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<title>사물함 변경</title>
<script type="text/javascript">
	// 사물함 변경
	function change_boxnum(boxnum) {
		if (confirm("사물함 번호 [" + boxnum + "]를 선택했습니다. 이 번호로 변경하겠습니까?")) {
			$("#BOX_NUM").val($.trim(boxnum));
			$("#frm").attr("action", "<c:url value='/box/boxChangePopProcess.do' />");
			$("#frm").submit();
			window.opener.document.location.href="<c:url value='/box/boxView.do' />?BOX_CD="+$("#BOX_CD").val()+"&TOP_MENU_ID=<c:out value='${params.TOP_MENU_ID}'/>&MENU_ID=<c:out value='${params.MENU_ID}'/>";
			window.close();
		}
	}

	// 다른 사물함 층을 선택
	function fn_Change_boxCD() {
		$("#BOX_CD").val($("#box").val());
		$("#frm").attr("action", "<c:url value='/box/boxChange.pop' />");
		$("#frm").submit();
	}

</script>
</head>
       		
<body>
<!--content -->
<div id="content_pop">
	<h2>● 사물함 관리 &gt; <strong>사물함 변경</strong></h2> 
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="USED_BOX_CD" name="USED_BOX_CD" value="${USED_BOX_CD}">
	<input type="hidden" id="USED_BOX_NUM" name="USED_BOX_NUM" value="${USED_BOX_NUM}">
	<input type="hidden" id="USED_RENT_SEQ" name="USED_RENT_SEQ" value="${USED_RENT_SEQ}">
	<input type="hidden" id="BOX_CD" name="BOX_CD" value="${BOX_CD}">
	<input type="hidden" id="BOX_NUM" name="BOX_NUM" value="">
</form>
</div>
<div id="contentBoxNum">

   	<!-- 사물함 loop -->
   	<table style="width:100%;background:#FFECEC;border:0;cellpadding:2;cellspacing:2;">
    	<tr>
			<td>
			<select id="box" name="box" onchange="fn_Change_boxCD();" style="width:120;">
				<c:forEach items="${boxlist}" var="item" varStatus="loop">
				<option value="${item.BOX_CD}" <c:if test="${BOX_CD == item.BOX_CD}">selected</c:if>>${item.BOX_NM}</option>
				</c:forEach>
		 	</select>             
			</td>
		</tr>
	</table>
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
    
   	<!-- 사물함 loop -->
   	<table style="width:100%;background:#FFECEC;border:0;cellpadding:0;cellspacing:0;">
    	<tr>
		<c:forEach items="${boxnumList}" var="item" varStatus="loop">
			<c:choose>
				<c:when test="${item.BOX_FLAG eq 'N'}"><c:set var="bgcolor1" value="#f8dcd4" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'Y'}"><c:set var="bgcolor1" value="#CCCCCC" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'D'}"><c:set var="bgcolor1" value="#f3fc65" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'H'}"><c:set var="bgcolor1" value="#79f670" /></c:when>
				<c:when test="${item.BOX_FLAG eq 'X'}"><c:set var="bgcolor1" value="#f36262" /></c:when>
				<c:otherwise><c:set var="bgcolor1" value="#FFFFFF" /></c:otherwise>
			</c:choose>
			<td width="70" height="30" bgcolor="${bgcolor1}">
				<c:choose>
				<c:when test="${item.BOX_FLAG eq 'N'}">
					<p align="center"><a href="javascript:change_boxnum('${item.BOX_NUM}');">${item.BOX_NUM}</a></p>
				</c:when>
				<c:otherwise>
					<p align="center">${item.BOX_NUM}</p>
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