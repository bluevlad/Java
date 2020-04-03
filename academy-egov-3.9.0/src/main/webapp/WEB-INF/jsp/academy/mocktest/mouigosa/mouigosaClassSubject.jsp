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
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
<body>
<table class="table01" style="font-size:9pt;">
	<form name=myform id="form" method=post >
	<input type=hidden name=ONOFF_DIV value="${detail.ONOFF_DIV}">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
	<!-- form -->
        <colgroup>
        <col width="15%">
        <col width="15%">
        <col width="*">
        </colgroup>
        <c:if test="${not empty SubjectList}">
        <c:forEach items="${SubjectList}" var="list" varStatus="status">
            <tr>
                <td>${list.SUBJECT_CD}</td>
                <td>${list.SUBJECT_NM}</td>
                <td><input type="button" onclick="del(this.form);" class="btn04" value="과목삭제"></td>
            </tr>
        </c:forEach>
            <tr>
                <td><input type="text" id="SUBJECT_CD" name="SUBJECT_CD" value="" style="width:50px;" /></td>
                <td><input type="text" id="SUBJECT_NM" name="SUBJECT_NM" value="" style="width:50px;" /></td>
                <td><input type="button" onclick="add(this.form);" class="btn04" value="과목등록"></td>
            </tr>
        </c:if>
    </form>
</table>
</body>
</html>