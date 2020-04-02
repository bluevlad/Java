<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />	
<script type="text/javascript"  src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script type="text/javascript">
//수정
function add(){
	$("#frm").attr("action","<c:url value='/pub/pub_write.frm' />");
	$("#frm").submit();
}

function update(){
	$("#frm").attr("action","<c:url value='/pub/pub_updatePubCategory.json' />");
	$("#frm").submit();
}

function del() {
	$.ajax({
		type: "POST",
		url: 'pub_deletePubCategory.json',
		data: $("#frm").serialize(),
		async : false,
		success: function (result) {
			if(result == 1) {
				alert("삭제 되었습니다.");
				$(parent.document).find("#frmTree").attr("src" , '<c:url value="/pub/pub_tree.frm"/>?TOP_MENU_ID=<c:out value="${params.TOP_MENU_ID}"/>&MENU_ID=<c:out value="${params.MENU_ID}"/>&MENUTYPE=<c:out value="${params.MENUTYPE}"/>&L_MENU_NM=<c:out value="${params.L_MENU_NM}"/>');
			}
		}
	});
}
</script>
</head>
<body style="min-width: 700px;">
    <form name=frm id="frm" method=post >
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
    <input type="hidden" name="IDX" value="<c:out value='${detail.IDX}'/>">
    <input type="hidden" name="P_IDX" value="<c:out value='${detail.P_IDX}'/>">
	<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
		<tr>
        	<th>IDX</th>
            <td><c:out value='${detail.IDX}'/></td>
        	<th>부모메뉴ID</th>
            <td><c:out value='${detail.P_IDX}'/><input type="hidden" name="p_menuid" value="${detail.P_IDX}"></td>
        </tr>
        <tr>
            <th>분류명</th>
            <td><input type="text" name="NM" style="width:250px;background:#FFECEC;" value="<c:out value='${detail.NM}'/>"></td>
            <th>순번</th>
            <td><input type="text" name="SEQ" style="width:30px;background:#FFECEC;" value="<c:out value='${detail.SEQ}'/>"></td>
        </tr>
	</table>
    <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
    	<tr>
        	<td height="40" align="right" >
                <input type="button" onclick="update();" class="btn04" value="수정">
                <input type="button" onclick="del();" class="btn04" value="삭제">
                <input type="button" onclick="add();" class="btn04" value="하위분류등록">
            </td>
         </tr>
	</table>
	</form>
</body>
</html>