<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />	
<script type="text/javascript" src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script language="JavaScript">
function add(form) {
    var retVal=0;
    if(form.NM.value=='') {
        alert("☞ 분류명을 입력해주세요");
        form.NM.focus();
    }
    else {
		$.ajax({
			type : 'POST' , 
			url : 'pub_insertPubCategory.json',
			data : $("#myform").serialize(),
			async : false,
			success : function(result){
				if(result == 1) {
					alert("등록 되었습니다.");
					$(parent.document).find("#frmTree").attr("src" , '<c:url value="/pub/pub_tree.frm"/>?TOP_MENU_ID=<c:out value="${params.TOP_MENU_ID}"/>&MENU_ID=<c:out value="${params.MENU_ID}"/>&MENUTYPE=<c:out value="${params.MENUTYPE}"/>&L_MENU_NM=<c:out value="${params.L_MENU_NM}"/>');
				} else {
					alert("등록중에 오류가 발생했습니다.");
				}
			}
		});
    }
}
</script>
</head>

<body style="min-width: 700px;">

	<form name=myform id="myform" method="post">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
	<input type="hidden" name="P_IDX" id="P_IDX" value="${detail.IDX}">
	<input type="hidden" name="LVL" id="LVL" value="${detail.LVL+1}">

	<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
		<tr>
        	<th>IDX</th>
            <td></td>
        	<th>부모메뉴ID</th>
            <td><c:out value='${detail.IDX}'/></td>
        </tr>
        <tr>
            <th>분류명</th>
            <td><input type="text" name="NM" style="width:250px;background:#FFECEC;" value=""></td>
            <th>순번</th>
            <td><input type="text" name="SEQ" style="width:30px;background:#FFECEC;" value=""></td>
        </tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
    	<tr>
			<td height="40" align="right">
				<input type="button" onclick="add(this.form);" class="btn04" value="분류등록">
            </td>
		</tr>
	</table>
	</form>
</body> 
</html>