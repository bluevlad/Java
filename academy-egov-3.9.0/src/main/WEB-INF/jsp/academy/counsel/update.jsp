<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}">
<input type="hidden" id="TIME_COUNT" name="TIME_COUNT" value="${sch_table.size()}"/>
<input type="hidden" id="DATE_COUNT" name="DATE_COUNT" value=""/>
<input type="hidden" id="UPDATE_DATE" name="UPDATE_DATE" value="${params.UPDATE_DATE }"/>
<input type="hidden" id="CAT_CD" name="CAT_CD" value="${params.CAT_CD }"/>
    <h2>● 수강신청관리 > <strong>상담예약관리</strong></h2> 
    <table class="table01">
        <tr>
            <th>상담직종</th>
            <td>${params.CAT_NM}</td>
        </tr>
        <tr>
            <th>상담일자</th>
            <td>
                <strong>${params.UPDATE_DATE}</strong>
            </td>
        </tr>
        <tr>
            <th>시간표</th>
            <td>
                <table>
                    <tr>
                        <th>시간</th>
                        <th>상담인원</th>
                        <th>상담신청대상</th>
                        <th>상태</th>
                    </tr> 
                    <c:forEach items="${sch_table}" var="item" varStatus="loop">
                    <tr>                
                        <td>${item.TIME_SET }</td>
                    <c:if test="${item.TS_IDX eq '3' }">
                        <td colspan="3" align="center" id="REQ_CNT_${item.TS_IDX }" name="REQ_CNT_${item.TS_IDX }">점 심 시 간</td>
                    </c:if>
                    <c:if test="${item.TS_IDX ne '3' }">
                        <td>
                            <input type="text" id="REQ_CNT" name="REQ_CNT" value="${item.MAX_USR }" size="5"  maxlength="3" title="상담인원" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>&nbsp;명
                        </td>
                        <td>
                            <select id="REQ_TYPE" name="REQ_TYPE">
                                <option value="A"  <c:if test="${item.REQ_TYPE eq 'A' }">selected</c:if>>회원</option>
                                <option value="S"  <c:if test="${item.REQ_TYPE eq 'S' }">selected</c:if>>수강생</option>
                            </select>
                        </td>
                        <td>
                            <select id="ISUSE" name="ISUSE">
                                <option value="Y" <c:if test="${item.ISUSE eq 'Y' }">selected</c:if>>활성</option>
                                <option value="N" <c:if test="${item.ISUSE eq 'N' }">selected</c:if>>비활성</option>
                            </select>
                        </td>
                    </c:if>
                    </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Update();">수정</a></li>
        <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
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

function fn_Update(){
    var dataString = $("#frm").serialize(); 
    $.ajax({
        type: "POST", 
        url : '<c:url value="/counsel/Schedule_Modify.do"/>?',
        data: dataString,
        dataType: "text",       
        async : false,
        success: function(RES) {
            if($.trim(RES)=="Y"){                                           
                alert("상담일정이 수정되었습니다.");
                $("#frm").attr("action","<c:url value='/counsel/Schedule_update.do' />");
                $("#frm").submit();
                return;
            }
        },error: function(){
            alert("상담일정 수정 실패");
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
