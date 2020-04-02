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
<!--
//중복확인 여부 
var isMenuIDCheck=0;

function checkNumber(input) {
    if(!isNumber(input)) {
        alert("☞ 숫자[0~9]만 사용하여 입력하십시요");
        setEmpty();
    }
}
function checkData(input) {
    if(!isAlphaNumDash(input)) {
        alert("☞ 영문 대문자[A~Z]만 사용하여 입력하십시요");
        setEmpty();
    }
}

function add(form) {
    var retVal=0;
    if(form.menu_nm.value=='') {
        alert("☞ 메뉴명을 입력해주세요");
        form.menu_nm.focus();
    } else if(form.menu_url.value=='') {
        alert("☞ 메뉴URL을 입력해주세요");
        form.menu_url.focus();
    } else {
        var dataString = $("#form").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/adminManagementMenu/passmenuInsertProcess.json"/>',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isInsert == 1) {
                    alert("등록 되었습니다.");
                    window.parent.location.href = '<c:url value="/adminManagementMenu/passMenuMain.do"/>?MENU_ID='+result.VWMENU_ID
                                                + '&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}';
                } else {
                    alert("등록이 안되었습니다.");
                }
            }
        });
        //$("#form").attr("action", "<c:url value='/adminManagement/menuInsertProcess.frm' />");
        //$("#form").submit();
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
-->
</script>
</head>

<body style="min-width: 700px;">
<h2 style="padding: 0; font-size: 16px; width: 55%">● 운영자 관리 > <strong>메뉴 등록</strong></h2>
<table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
        <form name=myform id="form" method=post  style="margin:0">
        <input type=hidden name=ONOFF_DIV value="${params.ONOFF_DIV}">
        <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
        <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
            <tr>
                <th >메뉴ID</th>
                <td colspan=3>
                ${params.MENU_ID}
                    <input type="hidden" name="menu_id"  class="in_just" value="${params.MENU_ID}">
                    <!-- <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)"> -->
                    <!-- <DIV ID='duplcheck' style='display: ; position: absolute;'>
                    <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)">
                    </div> -->
                </td>
            </tr>
            <tr>
                <th >부모메뉴ID</th>
                <td colspan=3>
                 ${params.P_MENUID}<input type="hidden" name="p_menuid" size="60"   value="${params.P_MENUID}">
                </td>
            </tr>
            <tr>
                <th>메뉴명</th>
                <td colspan=3 width=30%><input type="text" name="menu_nm" size="60" class="in_just"></td>
            </tr>
            <tr>
                <th width=20% height="25" >순서</th>
                <td width=30%>${params.MENU_SEQ}<input type="hidden" name="menu_seq" size="30"   value="${params.MENU_SEQ}"></td>
            </tr>
            <tr>
                <th height="25" >메뉴URL</th>
                <td colspan=3 ><input type="text" name="menu_url" size="60" ></td>
            </tr>
            <input type="hidden" name="target" size="15" >
            <tr>
                <th height="25" >상태</th>
                <td colspan=3 >
                    <input type=radio name="isuse" value="Y" checked >활성
                    <input type=radio name="isuse" value="N">비활성
                </td>
            </tr>
            <tr>
                <th height="25" >TARGET설정</th>
                <td colspan=3 >
                    <input type=radio name="TARGET" value="_blank"  >새창
                    <input type=radio name="TARGET" value=""  checked>현재창
                </td>
            </tr>
            <tr>
                <th>직렬코드</th>
                <td colspan=3 ><input type="text" name="CODE" size="10"  value=""></td>
            </tr>
            <tr>
                <th height="25" >상단메뉴이미지URL</th>
                <td colspan=3 ><input type="text" name="TOP_IMG_URL" size="60" maxlength="200"></td>
            </tr>
            <tr>
                <th height="25" >왼쪽메뉴이미지URL</th>
                <td colspan=3 ><input type="text" name="LEFT_IMG_URL" size="60" maxlength="200"></td>
            </tr>
            <tr>
                <th height="25" >타이틀이미지URL</th>
                <td colspan=3 ><input type="text" name="TITL_IMG_URL" size="60" maxlength="200"></td>
            </tr>
            <tr>
                <th height="25" >부타이틀이미지URL</th>
                <td colspan=3 ><input type="text" name="SUBTITL_IMG_URL" size="60" maxlength="200"></td>
            </tr>
            <tr>
                <th height="25" >메뉴설명</th>
               <td colspan=3 >
               <textarea rows="10" cols="70" name="menu_info" ></textarea>
                <br>(<span class="coun">※ 간략한 설명으로 영문 200자이내 입력</span>)
                </td>
            </tr>
            <tr>
           <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
                <tr>
                <td height="40" align="right">
                  <input type="reset" class="btn04">
                  <input type="button" onclick="add(this.form);" class="btn04" value="메뉴등록">
                </td>
              </tr>
            </table>
            </tr>
        </form>
</table>
</body> 
</html>