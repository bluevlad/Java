<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head> 
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />   
<script type="text/javascript"  src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script language="JavaScript">
function add(form) {
    $("#myform").attr("action", "<c:url value='/adminManagementCode/pass_codeAdd.frm' />");
    $("#myform").submit();
}

function del(form) {
	
    var retVal=0;
    if(confirm("☞ 선택한 항목을 삭제하겠습니까?") )  {
        var dataString = $("#myform").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/adminManagementCode/passcodeDeleteProcess.json" />',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isDelete == 1) {
                    alert("삭제 되었습니다.");
                    window.parent.location.href = '<c:url value="/adminManagementCode/passCodeMain.do" />?MENU_ID=OM_001_001&TOP_MENU_ID=OM_001&MENUTYPE=OM_ROOT&L_MENU_NM=운영자관리&MENU_NM=코드관리';
                } else {
                    alert("삭제가 안되었습니다.");
                }
            }
        });
    }
}

function modify(form) {
    var retVal=0;
    var dataString = $("#myform").serialize();
    $.ajax({
        type : 'POST' , 
        url : '<c:url value="/adminManagementCode/passcodeUpdateProcess.json" />',
        data : dataString ,
        async : false,
        success : function(result){
            if(result.isUpdate == 1) {
                alert("수정 되었습니다.");
                window.parent.location.href = '<c:url value="/adminManagementCode/passCodeMain.do" />?MENU_ID=OM_001_001&TOP_MENU_ID=OM_001&MENUTYPE=OM_ROOT&L_MENU_NM=운영자관리&MENU_NM=코드관리';
            } else {
                alert("수정이 안되었습니다.");
            }
        }
    });        
}
</script>
</head>
<body style="min-width: 700px;">
<h2 style="padding: 0; font-size: 16px; width: 55%">● 운영자 관리 > <strong>코드 수정</strong></h2>
<form name="myform" id="myform" method="post">
<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
	<tr>
		<th>공통코드</th>
		<td><input type="text" id="SYS_CD" name="SYS_CD" style="width:90%;" value="<c:out value='${detail.SYS_CD}'/>"></td>
	</tr>
	<tr>
		<th>설정코드</th>
        <td><input type="text" id="CODE_VAL" name="CODE_VAL" style="width:90%;" value="<c:out value='${detail.CODE_VAL}'/>"></td>
	</tr>    
	<tr>
		<th>설정코드명</th>
		<td><input type="text" id="CODE_NM" name="CODE_NM" style="width:90%;" value="<c:out value='${detail.CODE_NM}'/>"></td>
	</tr>       
	<tr>
		<th>순서</th>
		<td><input type="text" id="CODE_SEQ" name="CODE_SEQ" style="width:90%;" value="<c:out value='${detail.CODE_SEQ}'/>"></td>
	</tr>
	<tr>
    	<th>상태</th>
        <td>
        	<input type=radio id="ISUSE" name="ISUSE" value="Y" <c:if test="${detail.ISUSE == 'Y'}">checked</c:if> >활성
            <input type=radio id="ISUSE" name="ISUSE" value="N" <c:if test="${detail.ISUSE == 'N'}">checked</c:if>>비활성
        </td>
	</tr>
	<tr>
		<th>코드설명</th>
		<td>
			<textarea rows="10" id="CODE_INFO" name="CODE_INFO" style="width:90%;"><c:out value='${detail.CODE_INFO}'/></textarea>
            <br>(<span class="coun">※ 간략한 설명으로 20자이내 입력</span>)
		</td>
	</tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
	<tr>
		<td height="40" align="right" >
              <input type="button" onclick="modify(this.form);" class="btn04" value="코드수정">
              <input type="button" onclick="add(this.form);" class="btn04" value="코드등록">
              <input type="button" onclick="del(this.form);" class="btn04" value="코드삭제">
		</td>
    </tr>
</table>
</form>
</body>
</html>