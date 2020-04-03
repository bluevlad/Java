<%--
* /manager/sysinfo/Sg_Menu_Mst_Add.jsp
* 메뉴관리 등록
* @author (Copyright Miraenet Co.,Ltd.)
* @email  @miraenet.com
* @version 1.0,2008/04/11
--%>
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
<!--
function checkNumber(input) {
    if(!isNumber(input)) {
        alert("☞ 숫자[0~9]만 사용하여 입력하십시요");
        setEmpty();
    }
}
function add(form) {
	$("#form").attr("action", "<c:url value='/adminManagementMenu/menuAdd.frm' />");
	$("#form").submit();
}

function del(form) {
    var retVal=0;
	if(confirm("☞ 선택한 항목을 삭제하겠습니까?") )	{
		var dataString = $("#form").serialize();
		$.ajax({
			type : 'POST' , 
			url : '<c:url value="/adminManagementMenu/menuDeleteProcess.json" />',
			data : dataString ,
			async : false,
			success : function(result){
				if(result.isDelete == 1) {
					alert("삭제 되었습니다.");
					parent.location.href = '<c:url value="/adminManagementMenu/adminMenuMain.do" />?' 
					                     + 'TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}';
				} else {
					alert("삭제가 안되었습니다.");
				}
			}
		});
		//$("#form").attr("action", "<c:url value='/adminManagement/menuDeleteProcess.frm' />");
		//$("#form").submit();
    }
}
function modify(form) {
    var retVal=0;
   /*  if(form.menu_nm.value=='') {
        alert("☞ 메뉴명을 입력해주세요");
        form.menu_nm.focus();
    } else if(form.menu_url.value=='') {
        alert("☞ 메뉴URL을 입력해주세요");
        form.menu_url.focus();
    } else if(form.p_menuid.value=='') {
        alert("☞ 메뉴순서를 입력해주세요");
        form.p_menuid.focus();
    } else if(form.menu_seq.value=='') {
        alert("☞ 부모메뉴ID를 입력해주");
        form.menu_seq.focus();
    } else {
    	$("#form").attr("action", "<c:url value='/adminManagement/menuUpdateProcess.frm' />");
		$("#form").submit();
    } */
    //$("#form").attr("action", "<c:url value='/adminManagementMenu/menuUpdateProcess.frm' />");
	//$("#form").submit();
    var dataString = $("#form").serialize();
    $.ajax({
        type : 'POST' , 
        url : '<c:url value="/adminManagementMenu/menuUpdateProcess.json" />',
        data : dataString ,
        async : false,
        success : function(result){
            if(result.isUpdate == 1) {
                alert("수정 되었습니다.");
                window.parent.location.href = '<c:url value="/adminManagementMenu/adminMenuMain.do" />?VWMENU_ID='+result.VWMENU_ID
				                            + '&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}';
            } else {
                alert("수정이 안되었습니다.");
            }
        }
    });        
}

$(document).ready(function(){
	//parent.document.getElementById('frmTree').contentWindow.location.reload();
});
-->
</script>
</head>
<body style="min-width: 700px;">
<h2 style="padding: 0; font-size: 16px; width: 55%">● 운영자 관리 > <strong>메뉴 수정</strong></h2>
<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
	<form name=myform id="form" method=post >
	<input type=hidden name=ONOFF_DIV value="${detail.ONOFF_DIV}">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
	<!-- form -->
		<tr>
			<th >메뉴ID</th>
			<td colspan=3 ><c:out value='${detail.MENU_ID}'/></td>
		</tr>
		<tr>
			<th >부모메뉴ID</th>
			<td colspan=3 ><c:out value='${detail.P_MENUID}'/><input type="hidden" name="p_menuid" size="60"   value="${detail.P_MENUID}"></td>
		</tr>
		<tr>
			<th>메뉴명</th>
			<td colspan=3 ><input type="text" name="menu_nm" size="60"  value="<c:out value='${detail.MENU_NM}'/>"></td>
		</tr>
		<tr>
			<th >순서</th>
			<td colspan=3  ><input type="text" name="menu_seq" size="10"  value="<c:out value='${detail.MENU_SEQ}'/>"></td>
		</tr>
		<tr>
			<th>메뉴URL</th>
			<td colspan=3 ><input type="text" name="menu_url" size="60"  value="<c:out value='${detail.MENU_URL}'/>"></td>
		</tr>
		<input type="hidden" name="target" size="15"  value="<c:out value='${detail.TARGET}'/>">
		<%-- <tr>
			<th>TARGET</th>
			<td colspan=3 ><input type="text" name="target" size="15"  value="<c:out value='${detail.TARGET}'/>">
			<br><span class="coun">※ 현재선택한 메뉴의 표시 창(프레임)<br>(_self : 현재창 , _blank : 새창, 특정이름 : 해당명의 창, window:popup)</span>
			</td>
		</tr> --%>
		<tr>
			<th>상태</th>
			<td colspan=3 >
				<input type=radio name="isuse" value="Y" <c:if test="${detail.ISUSE == 'Y'}">checked</c:if> >활성
				<input type=radio name="isuse" value="N" <c:if test="${detail.ISUSE == 'N'}">checked</c:if>>비활성
			</td>
		</tr>
		<tr>
			<th>메뉴설명</th>
			<td colspan=3 >
				<textarea rows="10" cols="70" name="menu_info"><c:out value='${detail.MENU_INFO}'/></textarea>
			<br>(<span class="coun">※ 간략한 설명으로 영문 200자이내 입력</span>)
			</td>
		</tr>
		<input type="hidden" name="MENU_ID" value="${detail.MENU_ID}">
		<input type="hidden" name="MENU_SEQ" value="${detail.MENU_SEQ}">
	   
		<table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
			<tr>
			<td height="40" align="right" >
			  <input type="reset" class="btn04" value="원래대로">
			  <input type="button" onclick="modify(this.form);" class="btn04" value="메뉴수정">
			  <input type="button" onclick="add(this.form);" class="btn04" value="메뉴등록">
			  <input type="button" onclick="del(this.form);" class="btn04" value="메뉴삭제">
			</td>
		  </tr>
		</table>
		
    </form>
</table>
</body>
</html>