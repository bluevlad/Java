<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head></head>

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
<input type="hidden" id="ORDR" name="ORDR" value="1">
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${params.TYPE_CHOICE}">

    <h2>● 강의 관리 > <strong>직렬관리</strong></h2>

    <ul class="lecWritheTab">
        <li><a href="javascript:fn_go_list('S');" <c:if test="${params.TYPE_CHOICE eq 'S'}">class="active"</c:if>>직렬-직급등록</a></li>
        <li><a href="javascript:fn_go_list('C');" <c:if test="${params.TYPE_CHOICE eq 'C'}">class="active"</c:if>>직급-직렬연결</a></li>
    </ul>       

    <table class="table01">
        <tr>
            <th>코드</th>
            <td>
                <input type="text" id="SRS_CD" name="SRS_CD" value="" size="20"  maxlength="10" title="코드" style="width:28%;background:#FFECEC;IME-MODE:disabled;"/>
            </td>
        </tr>
        <tr>
            <th>직종명</th>
            <td>
                <input type="text" id="SRS_NM" name="SRS_NM" value="" size="20"  maxlength="25" title="직렬명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>관련 직종</th>
            <td>
                <c:forEach items="${cat_list}" var="item" varStatus="status" >
                    <input type="checkbox" id="CAT_CD" name="CAT_CD" value="${item.CODE}" ><label for="a1">${item.NAME }</label><c:if test="${(status.count mod 6) eq 0}"><br/></c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td>
                <input type="radio" id="ISUSE" name="ISUSE" value="Y" title="활성" checked="checked" /><label for="ISUSE">활성</label>
                <input type="radio" id="ISUSE" name="ISUSE" value="N" title="비활성"  /><label for="ISUSE2">비활성</label>
            </td>
        </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_submit();">등록</a></li>
        <li><a href="javascript:fn_list();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
$(document).ready(function(){
    $("#SRS_CD").focus();
});

// 목록으로
function fn_list(){
    $("#frm").attr("action","<c:url value='/series/list.do' />");
    $("#frm").submit();
}

function fn_go_list(val){
    $("#TYPE_CHOICE").val(val);
    if(val=="S"){
        $("#frm").attr("action", "<c:url value='/series/list.do' />");
    }else{
        $("#frm").attr("action", "<c:url value='/series/cat/main.do' />");
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
function fn_submit(){
    if($.trim($("#SRS_CD").val())==""){
        alert("코드를 입력해주세요");
        $("#SRS_CD").focus();
        return;
    }
    if($.trim($("#SRS_NM").val())==""){
        alert("직렬명을 입력해주세요");
        $("#SRS_NM").focus();
        return;
    }

    var checkparam = "SRS_CD=" + $.trim($("#SRS_CD").val());
    $.ajax({
        type: "GET",
        url : '<c:url value="/series/codeCheck.do"/>?' + checkparam,
        dataType: "text",       
        async : false,
        success: function(RES) {
            if($.trim(RES)=="Y"){
                if(confirm("정보를 등록하시겠습니까?")){
                    $("#frm").attr("action","<c:url value='/series/save.do' />");
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
