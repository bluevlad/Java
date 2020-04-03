<%--
* /series/cat/add.jsp
*  등록
* @author (Copyright Miraenet Co.,Ltd.)
* @email  @miraenet.com
* @version 1.0,2015/04/11
--%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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
    if(form.CODE.value=='') {
        alert("☞ 직종 코드를 입력해 주세요");
        form.CODE.focus();
        return;
    }
    // else if(isMenuIDCheck == 0) {
    //    alert("☞ 메뉴ID 중복확인 해주세요");
    // }
    if(form.NAME.value=='') {
        alert("☞ 직종명을 입력해 주세요");
        form.NAME.focus();
        return;
    } 
    //if(form.ORDR.value=='') {
    //    alert("☞ 정렬 순서를 입력해 주세요");
    //    form.ORDR.focus();
    //    return;
    //}
    else {
        var dataString = $("#form").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/category/insert_process.json" />',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isInsert == 1) {
                    alert("등록 되었습니다.");
                    window.parent.location.href = '<c:url value="/category/main.do" />?CODE='+result.CODEVIEW
                                                + '&TYPE_CHOICE=${TYPE_CHOICE}&TOP_MENU_ID=${TOP_MENU_ID}'
                                                + '&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}';
                    //$("#form").attr("action", "<c:url value='/category/main.do' />");
                    //$("#form").target = window.parent;
                    //$("#form").submit();
                } else {
                    alert("등록이 안됬습니다.");
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
        window.open("<c:url value='/category/IdCheck.pop?CODE="+form.menu_id.value+" ' />","specialWin", "resizable=yes,width=400,height=300,top=0,left=0,scrollbars=no,location=no");
        isMenuIDCheck=1;
    }
}
-->
</script>
</head>

<body style="min-width: 700px;">
  <c:if test="${params.P_CODE eq null or params.P_CODE eq '' or params.P_CODE eq 'CLASSCODE'}" >
    <table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
    <form name=myform id="form" method=post  style="margin:0">
        <input type="hidden" id="MENU_ID" name="MENU_ID" value="<c:out value='${MENU_ID}'/>" />
        <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
        <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
        <input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
        <input type="hidden" name="P_CODE" value="${params.P_CODE}">
        <input type="hidden" name="ORDR" value="${params.ORDR}">
        <tr>
            <th width="20%">상위 직종 코드</th>
            <td colspan=3 width="80%">${params.P_CODE}</td>
        </tr>
        <tr>
            <th >상위 직종명</th>
            <td colspan=3>${params.P_NAME}</td>
        </tr>
        <tr>
            <th >직급 코드</th>
            <td colspan=3><input type="text" name="CODE"  class="in_just" value="">
                <!-- <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)"> -->
                <!-- <DIV ID='duplcheck' style='display: ; position: absolute;'>
                <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)">
                </div> -->
            </td>
        </tr>
        <tr>
            <th>직종명</th>
            <td colspan=3 width=30%><input type="text" name="NAME" size="60" class="in_just"></td>
        </tr>
        <tr>
            <th>ON 사용 여부</th>
            <td colspan=3 >
                <input type=radio name="USE_ON" value="Y" checked >활성
                <input type=radio name="USE_ON" value="N" >비활성
            </td>
        </tr>   
        <tr>
            <th>OFF 사용 여부</th>
            <td colspan=3 >
                <input type=radio name="USE_OFF" value="Y" checked >활성
                <input type=radio name="USE_OFF" value="N" >비활성
            </td>
        </tr>   
        <tr>
            <th height="25" >상태</th>
            <td colspan=3 >
                <input type=radio name="ISUSE" value="Y" checked >활성
                <input type=radio name="ISUSE" value="N" >비활성
            </td>
        </tr>
        <tr>
        <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
            <tr>
            <td height="40" align="right">
              <input type="reset" class="btn04">
              <input type="button" onclick="add(this.form);" class="btn04" value="직종등록">
            </td>
          </tr>
        </table>
        </tr>
    </form>
    </table>
  </c:if>
  <c:if test="${params.P_CODE ne null and params.P_CODE ne '' and params.P_CODE ne 'CLASSCODE'}" >
    <table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
    <form name=myform id="form" method=post  style="margin:0">
        <input type="hidden" id="MENU_ID" name="MENU_ID" value="<c:out value='${MENU_ID}'/>" />
        <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
        <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
        <input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
        <input type="hidden" name="P_CODE" size="60" value="${params.P_CODE}">
        <tr>
            <th width="20%">직종코드/상위 직렬코드</th>
            <td colspan=3 width="80%">${params.P_CODE}</td>
        </tr>
        <tr>
            <th >직종명/상위 직렬명</th>
            <td colspan=3>${params.P_NAME}</td>
        </tr>
        <tr>
            <th >직렬 코드</th>
            <td colspan=3><input type="text" name="CODE"  class="in_just" value="">
                <!-- <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)"> -->
                <!-- <DIV ID='duplcheck' style='display: ; position: absolute;'>
                <input type=button class=btn04 value="중복확인" onClick="duplcheck(this.form)">
                </div> -->
            </td>
        </tr>
        <tr>
            <th>직렬명</th>
            <td colspan=3 width=30%><input type="text" name="NAME" size="60" class="in_just"></td>
        </tr>
        <tr>
            <th >순서</th>
            <td ><input type="text" name="ORDR" size="5" onkeyup="checkNumber(this)" value="${params.ORDR}"></td>
        </tr>
        <tr>
            <th height="25" >상태</th>
            <td colspan=3 >
                <input type=radio name="ISUSE" value="Y" checked >활성
                <input type=radio name="ISUSE" value="N" >비활성
            </td>
        </tr>
        <tr>
        <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
            <tr>
            <td height="40" align="right">
              <input type="reset" class="btn04">
              <input type="button" onclick="add(this.form);" class="btn04" value="직렬등록">
            </td>
          </tr>
        </table>
        </tr>
    </form>
    </table>
  </c:if>    
</body> 
</html>