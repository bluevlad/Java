<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />	
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<script type="text/javascript"  src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script type="text/javascript">
//등록
function add(subject_cd) {
    $("#SUBJECT_CD").val(subject_cd);
	$(parent.document).find("#frmEdit").attr("src" , '<c:url value="/mouigosa/AreaView.frm"/>?SUBJECT_CD='+subject_cd+'&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}');
}
</script>
</head>

<body>
<form id="frm" name="frm" method="get">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="SUBJECT_CD" name="SUBJECT_CD" value="" />
<table class="table04" style="font-size: 9pt; width:350px">
<col width="15%">
<col width="35%">
<col width="15%">
	<tr>
		<th>No</th>
        <th>과목명</th>
        <th>영역수</th>
	</tr>
    <c:if test="${not empty list}">
    <c:forEach items="${list}" var="list" varStatus="status">
    <tr>
    	<td>${status.index+1}</td>
        <td><a href="javascript:add('${list.SUBJECT_CD}');">${list.SUBJECT_NM}</a></td>
        <td><a href="javascript:add('${list.SUBJECT_CD}');">${list.CNT}</a></td>
	</tr>
    </c:forEach>
    </c:if>
    <c:if test="${empty list}">
    <tr bgColor=#ffffff align=center>
    	<td colspan="6">데이터가 존재하지 않습니다.</td>
	</tr>
    </c:if>
</table>
</form>

</body>
</html>