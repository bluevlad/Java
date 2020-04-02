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
<title></title> 
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />   
<script type="text/javascript" src="<c:url value="/resources/js/eduhome/common.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script language="JavaScript">
function add(form) {
    var retVal=0;
    if(form.CODE_VAL.value=='') {
        alert("☞ 설정코드를 입력해주세요");
        form.CODE_VAL.focus();
    } else if(form.CODE_NM.value=='') {
        alert("☞ 설정코드명을 입력해주세요");
        form.CODE_NM.focus();
    } else {
    	
	    var dataString = $("#form").serialize();
	        
	    $.ajax({
	        type : 'POST' , 
	        url : '<c:url value="/adminManagementCode/passcodeInsertProcess.json"/>',
	        data : dataString ,
	        async : false,
	        success : function(result){
	            if(result.isInsert == 1) {
	                alert("등록 되었습니다.");
	                window.parent.location.href = '<c:url value="/adminManagementCode/passCodeMain.do"/>?MENU_ID=OM_001_001&TOP_MENU_ID=OM_001&MENUTYPE=OM_ROOT&L_MENU_NM=운영자관리&MENU_NM=코드관리';
	            } else {
	                alert("등록이 안되었습니다.");
	            }
	        }
	    });
    }
}

// 중복 체크
function duplcheck(form) {
    if(form.menu_id.value < 3){
        alert("☞ 고유 메뉴ID를 입력해주세요");
    }else{
        window.open("<c:url value='/adminManagementMenu/menuIdCheck.pop?MENU_ID="+form.menu_id.value+" ' />","specialWin", "resizable=yes,width=400,height=300,top=0,left=0,scrollbars=no,location=no");
        isMenuIDCheck=1;
    }
}

</script>
</head>

<body style="min-width: 700px;">
<h2 style="padding: 0; font-size: 16px; width: 55%">● 운영자 관리 > <strong>코드 등록</strong></h2>
<form name=myform id="form" method=post  style="margin:0">
<input type=hidden name=ONOFF_DIV value="${params.ONOFF_DIV}">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
<input type="hidden" name="SYS_CD" value="${params.CODE_VAL}">
<input type="hidden" name="CODE_NO" value="${resultMap.CODE_NO}">
<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
	<tr>
    	<th width=30% >부모코드ID</th>
		<td>${params.CODE_VAL}</td>
   	</tr>
   	<tr>
		<th>순서</th>
       	<td>${resultMap.CODE_NO}</td>
   	</tr>
	<tr>
		<th>상태</th>
       	<td>
			<input type=radio name="ISUSE" value="Y" checked >활성
			<input type=radio name="ISUSE" value="N">비활성
		</td>
	</tr>
	<tr>
    	<th>설정코드</th>
		<td><input type="text" name="CODE_VAL" size="60"  value=""></td>
	</tr>       
	<tr>
		<th>설정코드명</th>
    	<td><input type="text" name="CODE_NM" size="60"  value=""></td>
	</tr>               
	<tr>
		<th>코드설명</th>
      	<td>
			<textarea rows="5" cols="70" name="CODE_INFO" ></textarea>
			<br>(<span class="coun">※ 간략한 설명으로 20자이내 입력</span>)
       </td>
   </tr>
   <tr>
  <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
       <tr>
       <td height="40" align="right">
         <input type="button" onclick="add(this.form);" class="btn04" value="메뉴등록">
        </td>
      </tr>
    </table>
    </tr>
</table>
</form>
</body> 
</html>