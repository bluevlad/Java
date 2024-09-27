<%--
* /lecture/series/cat/add.jsp
* 등록
* @author (Copyright Miraenet Co.,Ltd.)
* @email  @miraenet.com
* @version 1.0,2015/05/20
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
    $("#form").attr("action", "<c:url value='/category/add.frm' />");
    $("#form").submit();
}

function del(form) {
    var retVal=0;
    if(confirm("☞ 선택한 항목을 삭제하겠습니까?") )  {
        var dataString = $("#form").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/category/delete_process.json" />',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isDelete == 1) {
                    alert("삭제 되었습니다.");
                    parent.location.href = '<c:url value="/category/main.do" />?CODE=CLASSCODE'
                                         + '&TYPE_CHOICE=${TYPE_CHOICE}&TOP_MENU_ID=${TOP_MENU_ID}'
                                         + '&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}' ;
                } else {
                    alert("삭제가 안되었습니다.");
                }
            }
        });
    }
}
function modify(form) {
  <c:if test="${detail.P_CODE eq 'CLASSCODE'}" >
    var retVal=0;
    if(form.NAME.value=='') {
        alert("☞ 직종명을 입력해 주세요");
        form.myform.focus();
        return;
    } else {
        //$("#form").attr("action", "<c:url value='/category/update_process.frm' />");
        //$("#form").submit();
        var dataString = $("#form").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/category/update_process.json" />',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isUpdate == 1) {
                    alert("수정 되었습니다.");
                    window.parent.location.href = '<c:url value="/category/main.do" />?CODE='+result.VIEWCODE
                                                + '&TYPE_CHOICE=${TYPE_CHOICE}&TOP_MENU_ID=${TOP_MENU_ID}'
                                                + '&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}';
                } else {
                    alert("수정이 안되었습니다.");
                }
            }
        });        
    }
  </c:if>
  <c:if test="${detail.P_CODE ne 'CLASSCODE'}" >
    var retVal=0;
    if(form.NAME.value=='') {
        alert("☞ 직렬명을 입력해 주세요");
        form.myform.focus();
        return;
    } else {
        //$("#form").attr("action", "<c:url value='/category/update_process.frm' />");
        //$("#form").submit();
        var dataString = $("#form").serialize();
        $.ajax({
            type : 'POST' , 
            url : '<c:url value="/category/update_process.json" />',
            data : dataString ,
            async : false,
            success : function(result){
                if(result.isUpdate == 1) {
                    alert("수정 되었습니다.");
                    window.parent.location.href = '<c:url value="/category/main.do" />?CODE='+result.VIEWCODE
                                                + '&TYPE_CHOICE=${TYPE_CHOICE}&TOP_MENU_ID=${TOP_MENU_ID}'
                                                + '&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}';
                } else {
                    alert("수정이 안되었습니다.");
                }
            }
        });        
    }
  </c:if>    
}

$(document).ready(function(){
    //parent.document.getElementById('frmTree').contentWindow.location.reload();
});
-->
</script>
</head>
<body style="min-width: 700px;">
  <c:if test="${detail.P_CODE eq null or detail.P_CODE eq '' or detail.P_CODE eq 'CLASSCODE'}" >
    <table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
    <form name=myform id="form" method=post >
        <input type="hidden" id="MENU_ID" name="MENU_ID" value="<c:out value='${MENU_ID}'/>" />
        <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
        <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
        <input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
        <input type="hidden" name="CODE" value="${detail.CODE}">
        <input type="hidden" name="P_CODE" value="${detail.P_CODE}">
        <input type="hidden" name="ORDR" value="${detail.ORDR}">
        <tr>
            <th width="20%">직종 코드</th>
            <td colspan=3 width="80%"><c:out value='${detail.CODE}'/></td>
        </tr>
        <tr>
            <th>직종 명</th>
            <td colspan=3 ><input type="text" name="NAME" size="60"  value="<c:out value='${detail.NAME}'/>"></td>
        </tr>
        <tr>
            <th>ON 사용 여부</th>
            <td colspan=3 >
                <input type=radio name="USE_ON" value="Y" <c:if test="${detail.USE_ON == 'Y'}">checked</c:if> >활성
                <input type=radio name="USE_ON" value="N" <c:if test="${detail.USE_ON == 'N'}">checked</c:if>>비활성
            </td>
        </tr>   
        <tr>
            <th>OFF 사용 여부</th>
            <td colspan=3 >
                <input type=radio name="USE_OFF" value="Y" <c:if test="${detail.USE_OFF == 'Y'}">checked</c:if> >활성
                <input type=radio name="USE_OFF" value="N" <c:if test="${detail.USE_OFF == 'N'}">checked</c:if>>비활성
            </td>
        </tr>   
        <tr>
            <th>상태</th>
            <td colspan=3 >
                <input type=radio name="ISUSE" value="Y" <c:if test="${detail.ISUSE == 'Y'}">checked</c:if> >활성
                <input type=radio name="ISUSE" value="N" <c:if test="${detail.ISUSE == 'N'}">checked</c:if>>비활성
            </td>
        </tr>   
        <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
            <tr>
            <td height="40" align="right" >
              <input type="reset" class="btn04" value="원래대로">
              <input type="button" onclick="modify(this.form);" class="btn04" value="직종수정">
            <c:choose><c:when test="${detail.P_CODE eq null or detail.P_CODE eq ''}" >
              <input type="button" onclick="add(this.form);" class="btn04" value="직종등록">
            </c:when>
            <c:when test="${detail.P_CODE eq 'CLASSCODE'}" >
              <input type="button" onclick="add(this.form);" class="btn04" value="직렬등록">
            </c:when></c:choose>
              <input type="button" onclick="del(this.form);" class="btn04" value="직종삭제">
            </td>
          </tr>
        </table>    
    </form>
    </table>
  </c:if>
  <c:if test="${detail.P_CODE ne null and detail.P_CODE ne '' and detail.P_CODE ne 'CLASSCODE'}" >
    <table class="table01" style="font-size: 9pt; min-width: 700px; width: 55%" >
    <form name=myform id="form" method=post >
        <input type="hidden" id="MENU_ID" name="MENU_ID" value="<c:out value='${MENU_ID}'/>" />
        <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out value='${TOP_MENU_ID}'/>" />
        <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out value='${MENUTYPE}'/>" />
        <input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
        <input type="hidden" name="CODE" value="${detail.CODE}">
        <input type="hidden" name="P_CODE" value="${detail.P_CODE}">
        <tr>
            <th width="20%">직렬 코드/상위 코드</th>
            <td colspan=3 width="80%"><c:out value='${detail.P_CODE}'/></td>
        </tr>
        <tr>
            <th>직렬 명/상위 코드명</th>
            <td colspan=3 ><c:out value='${detail.P_NAME}'/></td>
        </tr>
        <tr>
            <th>직렬 코드</th>
            <td colspan=3 ><c:out value='${detail.CODE}'/></td>
        </tr>
        <tr>
            <th>직렬 명</th>
            <td colspan=3 ><input type="text" name="NAME" size="60"  value="<c:out value='${detail.NAME}'/>"></td>
        </tr>
        <tr>
            <th >순서</th>
            <td ><input type="text" name="ORDR" size="5" onkeyup="checkNumber(this)" value="${detail.ORDR}"></td>
        </tr>
        <tr>
            <th>상태</th>
            <td colspan=3 >
                <input type=radio name="ISUSE" value="Y" <c:if test="${detail.ISUSE == 'Y'}">checked</c:if> >활성
                <input type=radio name="ISUSE" value="N" <c:if test="${detail.ISUSE == 'N'}">checked</c:if>>비활성
            </td>
        </tr>   
        <table border="0" cellspacing="0" cellpadding="0" style="min-width: 700px;">
            <tr>
            <td height="40" align="right" >
              <input type="reset" class="btn04" value="원래대로">
              <input type="button" onclick="modify(this.form);" class="btn04" value="직렬수정">
              <input type="button" onclick="add(this.form);" class="btn04" value="직렬등록">
              <input type="button" onclick="del(this.form);" class="btn04" value="직렬삭제">
            </td>
          </tr>
        </table>    
    </form>
    </table>
  </c:if>
</body>
</html>