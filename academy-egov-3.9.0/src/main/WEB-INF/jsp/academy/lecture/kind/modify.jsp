<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
<input type="hidden" id="CODE" name="CODE" value="${params.CODE}"/>
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
<input type="hidden" name="P_CODE" value="${detail.P_CODE}">
<input type="hidden" name="ORDR" value="${detail.ORDR}">
 
    <h2>● 강의 관리 > <strong>직종관리</strong></h2>
    <table class="table01">
        <tr>
            <th>코드</th>
            <td>
                ${list[0].CODE}
            </td>
        </tr>
        <tr>
            <th>직종명</th>
            <td>
                <input type="text" id="NAME" name="NAME" value="${list[0].NAME}" size="20"  maxlength="25" title="직종명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>ON 사용여부</th>
            <td>
                <select id="USE_ON" name="USE_ON">
                    <option value="Y" <c:if test="${list[0].USE_ON eq 'Y' }">selected="selected"</c:if>>사용</option>
                    <option value="N" <c:if test="${list[0].USE_ON eq 'N' }">selected="selected"</c:if>>미사용</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>OFF 사용여부</th>
            <td>
                <select id="USE_OFF" name="USE_OFF">
                    <option value="Y" <c:if test="${list[0].USE_OFF eq 'Y' }">selected="selected"</c:if>>사용</option>
                    <option value="N" <c:if test="${list[0].USE_OFF eq 'N' }">selected="selected"</c:if>>미사용</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td>
                <select id="ISUSE" name="ISUSE">
                    <option value="Y" <c:if test="${list[0].ISUSE eq 'Y' }">selected="selected"</c:if>>활성</option>
                    <option value="N" <c:if test="${list[0].ISUSE eq 'N' }">selected="selected"</c:if>>비활성</option>
                </select>
            </td>
        </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Submit();">수정</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
        <li><a href="javascript:fn_Delete();">비활성</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
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

// 수정
function fn_Submit(){
    if($.trim($("#NAME").val())==""){
        alert("직종명을 입력해주세요");
        $("#NAME").focus();
        return;
    }
    if(confirm("수정하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/kind/update.do' />");
        $("#frm").submit();
    }
}

// 삭제
function fn_Delete(){
    if(confirm("정말 비활성 하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/kind/delete.do' />");
        $("#frm").submit();
    }
}
</script>
</body>
</html>