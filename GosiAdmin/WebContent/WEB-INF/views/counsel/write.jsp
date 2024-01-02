<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head></head>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}">
<input type="hidden" id="TIME_COUNT" name="TIME_COUNT" value="${time_table.size()}"/>
<input type="hidden" id="DATE_COUNT" name="DATE_COUNT" value=""/>
    <h2>● 수강신청관리 > <strong>상담예약관리</strong></h2> 
    <table class="table01">
        <tr>
            <th>상담직종</th>
            <td colspan="3">
                <c:forEach items="${rankList}"  var="data" varStatus="status" >
                    <input type="checkbox" id="CAT_CD" name="CAT_CD" class="i_check" value="${data.CODE}"><label for="a1">${data.NAME}</label>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th>상담일정</th>
            <td colspan="3">
                <input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${searchMap.sdate }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);" readonly="readonly"/>
               ~
               <input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${searchMap.edate }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
            </td>
        </tr>
        <tr>
            <th>상담인원</th>
            <td>
                <input type="text" id="REQ_CNT" name="REQ_CNT" value="" size="5"  maxlength="3" title="상담인원" style="width:10%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>&nbsp;명
            </td>
            <th>상담신청대상</th>
            <td>
                <select id="REQ_TYPE" name="REQ_TYPE">
                    <option value="A">회원</option>
                    <option value="S">수강생</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td colspan="3">
                <select id="ISUSE" name="ISUSE">
                    <option value="Y">활성</option>
                    <option value="N">비활성</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>시간표</th>
            <td colspan="3">
                <c:forEach items="${time_table}" var="item" varStatus="loop">
                    <c:if test="${item.IDX eq '3'}">
                        ${item.TIME_SET}&nbsp;&nbsp;&nbsp;점 심 시 간<br>
                    </c:if>
                    <c:if test="${item.IDX ne '3'}">
                        ${item.TIME_SET}<br>
                    </c:if>                     
					<input type="hidden" name="arr_idx" value="${item.IDX}"/>
                </c:forEach>
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
$(function() {
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDateFicker2("sdate", "edate");
});

function onOnlyNumber(obj) {
    for (var i = 0; i < obj.value.length ; i++){
        chr = obj.value.substr(i,1);  
        chr = escape(chr);
        key_eg = chr.charAt(1);
        if (key_eg == "u"){
            key_num = chr.substr(i,(chr.length-1));   
            if((key_num < "AC00") || (key_num > "D7A3")) { 
               // event.returnValue = false; 표준 브라우저(IE9, 파이어폭스, 오페라, 사파리, 크롬)
               event.preventDefault(); // IE
            }    
        }
    }
     
    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
    } else {
        // event.returnValue = false; 표준 브라우저(IE9, 파이어폭스, 오페라, 사파리, 크롬)
        event.preventDefault(); // IE
    }
}

function fn_Submit(){
    var codeStr = "";
    $("input[name=CAT_CD]:checked").each(function() {
        checkboxValue = $(this).val();
        codeStr += checkboxValue+",";
    });
    if(codeStr == ""){
        alert("직종을 선택해주세요");
        $("#CAT_CD").focus();
        return;
    }
    $("#CAT_CD").val(codeStr);
    
    if($.trim($("#sdate").val())==""){
        alert("상담일정 시작일을 입력해주세요");
        $("#sdate").focus();
        return;
    }
    
    if($.trim($("#edate").val())==""){
        alert("상담일정 종료일을 입력해주세요");
        $("#edate").focus();
        return;
    }
    
    if($("#edate").val() < $("#sdate").val()){
        alert("상담일정을 다시 입력해주세요.");
        $("#sdate").focus();
        return;
    }
    
    if($.trim($("#REQ_CNT").val())==""){
        alert("상담인원을 입력해주세요");
        $("#REQ_CNT").focus();
        return;
    }

    var a = new Date($("#sdate").val().substring(0,4), $("#sdate").val().substring(4,6), $("#sdate").val().substring(6,8));
    var b = new Date($("#edate").val().substring(0,4), $("#edate").val().substring(4,6), $("#edate").val().substring(6,8));
    var c = b-a;
    c = (c / (24*3600*1000))+1;

    $('#DATE_COUNT').val(c);

    var checkparam = "SDATE=" + $('#sdate').val() + "&EDATE=" + $('#edate').val() 
                   + "&REQ_CNT=" + $('#REQ_CNT').val() 
                   + "&ISUSE=" + $('#ISUSE').val() 
                   + "&REQ_TYPE=" + $('#REQ_TYPE').val() 
                   + "&DATE_COUNT=" + $('#DATE_COUNT').val() + "&TIME_COUNT=" + $('#TIME_COUNT').val()
                   + "&CAT_CDs=" + $('#CAT_CD').val();
    $.ajax({
        type: "GET", 
        url : '<c:url value="/counsel/Schedule_Insert.do"/>?' + checkparam,
        dataType: "text",       
        async : false,
        success: function(RES) {
            if($.trim(RES)=="schedule_over"){
                alert("상담일정이 잡혀있습니다.");
                return;     
            }else{
                alert("상담일정이 등록되었습니다.");
                $("#frm").attr("action","<c:url value='/counsel/list.do' />");
                $("#frm").submit();
                return;
            }
        },error: function(){
            alert("상담일정 등록 실패");
            return;
        }
    });
}

// 목록으로
function fn_List(){
    $("#frm").attr("action","<c:url value='/counsel/list.do' />");
    $("#frm").submit();
}
</script>