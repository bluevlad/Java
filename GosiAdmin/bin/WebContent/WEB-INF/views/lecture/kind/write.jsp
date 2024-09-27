<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
<input type="hidden" name="P_CODE" value="CLASSCODE">
<input type="hidden" name="ORDR" value="">

    <h2>● 강의 관리 > <strong>직종관리</strong></h2>

    <ul class="lecWritheTab">
        <li><a href="javascript:fn_go_list('C');" <c:if test="${TYPE_CHOICE eq 'C'}">class="active"</c:if>>직종관리</a></li>
        <li><a href="javascript:fn_go_list('S');" <c:if test="${TYPE_CHOICE eq 'S'}">class="active"</c:if>>직종-직렬관리</a></li>
    </ul>       
    <table class="table01">
        <tr>
            <th>코드</th>
            <td>
                <input type="text" id="CODE" name="CODE" value="" size="20"  maxlength="10" title="코드" style="width:28%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
            </td>
        </tr>
        <tr>
            <th>직종명</th>
            <td>
                <input type="text" id="NAME" name="NAME" value="" size="20"  maxlength="25" title="직종명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>ON 사용여부</th>
            <td>
                <select id="USE_ON" name="USE_ON">
                    <option value="Y">사용</option>
                    <option value="N">미사용</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>OFF 사용여부</th>
            <td>
                <select id="USE_OFF" name="USE_OFF">
                    <option value="Y">사용</option>
                    <option value="N">미사용</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td>
                <select id="ISUSE" name="ISUSE">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
            </td>
        </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit();">등록</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
$(document).ready(function(){
    $("#CODE").focus();
});

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/kind/list.do' />");
    $("#frm").submit();
}

function fn_go_list(val){
    $("#TYPE_CHOICE").val(val);
    if(val=="C"){
        $("#frm").attr("action", "<c:url value='/kind/list.do' />");
    }else{
        $("#frm").attr("action", "<c:url value='/category/main.do' />");
    }
    $("#frm").submit(); 
}

// 숫자만입력
function fn_OnlyNumber(obj) {
    for (var i = 0; i < obj.value.length ; i++){
        chr = obj.value.substr(i,1);  
        chr = escape(chr);
        key_eg = chr.charAt(1);
        if (key_eg == "u"){
            key_num = chr.substr(i,(chr.length-1));   
            if((key_num < "AC00") || (key_num > "D7A3")) { 
                event.returnValue = false;
            }    
        }
    }
    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
    }else{
        event.returnValue = false;
    }
}

// 등록
function fn_Submit(){
    if($.trim($("#CODE").val())==""){
        alert("코드를 입력해주세요");
        $("#CODE").focus();
        return;
    }
    if($.trim($("#NAME").val())==""){
        alert("직종명을 입력해주세요");
        $("#NAME").focus();
        return;
    }
    
    var checkparam = "CODE=" + $.trim($("#CODE").val());
    $.ajax({
        type: "GET",
        url : '<c:url value="/kind/codeCheck.do"/>?' + checkparam,
        dataType: "text",       
        async : false,
        success: function(RES) {
            if($.trim(RES)=="Y"){
                if(confirm("코드를 등록하시겠습니까?")){
                    $("#frm").attr("action","<c:url value='/kind/save.do' />");
                    $("#frm").submit();
                }               
            }else{
                alert("입력하신 코드는 이미 등록된 코드입니다");
                return;
            }
        },error: function(){
            alert("코드중복체크 실패");
            return;
        }
    }); 
}
</script>
</body>
</html>