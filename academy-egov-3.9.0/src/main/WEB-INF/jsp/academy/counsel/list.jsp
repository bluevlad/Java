<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDate");   
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    initDatePicker1("EDate");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//상담내역 상세
function view(sch_dat, cat_cd) {
    $("#SCH_DAY").val(sch_dat);
    $("#CAT_CD").val(cat_cd);
    $('#frm').attr('action', '<c:url value="/counsel/view.do"/>').submit();
}

//상담일정 조회
function listUser() {
    $('#frm').attr('action', '<c:url value="/counsel/userlist.do"/>').submit();
}

//상담일정 등록
function insertSchedule() {
    $('#frm').attr('action', '<c:url value="/counsel/Schedule_add.do"/>').submit();
}

//상담일정 수정
function update(sch_day, cat_cd, cat_nm) {
    $("#UPDATE_DATE").val(sch_day);
    $("#CAT_CD").val(cat_cd);
    $("#CAT_NM").val(cat_nm);
    $('#frm').attr('action', '<c:url value="/counsel/Schedule_update.do"/>').submit();
}

//상담일정 삭제
function ScheduleDel() {
    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택하신 상담일정을 삭제하시겠습니까?")){
            var dataString = $("#frm").serialize(); 
            $.ajax({
                type: "POST", 
                url : '<c:url value="/counsel/Schedule_delete.do"/>?',
                data: dataString,
                dataType: "text",       
                async : false,
                success: function(RES) {
                    if($.trim(RES)=="Y"){                                           
                        alert("상담일정이 삭제되었습니다.");
                        $("#frm").attr("action","<c:url value='/counsel/list.do' />");
                        $("#frm").submit();
                        return;
                    }
                },error: function(){
                    alert("상담일정 삭제 실패");
                    return;
                }
            });
        }else{
            return;
        }           
    }else{
        alert("삭제하실 상담일정을 선택해주세요");
    }
}

//검색
function goList(page) {
    var codeStr = "";
    $("#codeList input[type=checkbox]:checked").each(function() {
        checkboxValue = $(this).val();
        codeStr+= checkboxValue+",";
    });
    var searchCategory = codeStr;
    $("#SEARCHCATEGORY").val(searchCategory);

    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    
    $('#frm').attr('action','<c:url value="/counsel/list.do"/>').submit();
}
</script>
</head>

<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" /> 
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" /> 
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="SCH_DAY" name="SCH_DAY" value="" />
<input type="hidden" id="CAT_CD" name="CAT_CD" value="" />
<input type="hidden" id="CAT_NM" name="CAT_NM" value="" />
<input type="hidden" id="UPDATE_DATE" name="UPDATE_DATE" value="" />
<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value=""/>
    <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    <table class="table01">
        <tr>
            <th width="10%">직종구분</th>
            <td colspan="5">
                <div class="item" id="codeList">
                <c:forEach items="${rankList}"  var="data" varStatus="status" >
                    <c:set var="vChecked">
                       <c:choose>
                        <c:when test="${fn:contains(params.SEARCHCATEGORY, data.CODE)}">checked="checked"</c:when>
                         <c:otherwise></c:otherwise>
                       </c:choose>
                     </c:set>
                    <input type="checkbox" name="searchCategory" class="i_check" value="${data.CODE}" <c:out value='${vChecked}'/> ><label for="a1">${data.NAME }</label>
                </c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <th width="15%">검색</th>
            <td>            
                <input type="text" id="SDate" name="SDate"  class="i_text"  value="${params.SDate}" maxlength="10" readonly="readonly"/> 
                ~
                <input type="text" id="EDate" name="EDate"  class="i_text"  value="${params.EDate}" maxlength="10" readonly="readonly"/>
                &nbsp;
            </td>
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                <input size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:insertSchedule();">상담일정등록</a></li>
        <li><a href="javascript:listUser();">신청자조회</a></li>
        <li><a href="javascript:ScheduleDel();">상담일정삭제</a></li>
    </ul>

    <!-- 테이블 -->
    <table class="table02">
        <tr>
            <th>No</th>
            <th>직종</th>
            <th>일자(요일)</th>
            <th>신청현황</th>
            <th>수정</th>
        </tr>
        <tbody>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="data" varStatus="loop">
            <tr>
                <td><input type="checkbox" id="DEL_ARR" name="DEL_ARR" value="${data.SCH_DAY}#${data.CAT_CD}"></td>
                <td>(${data.CAT_NM})</td>
                <td><a href="javascript:view('${data.SCH_DAY}','${data.CAT_CD}');">${data.SCH_DAY} (${data.WEEK})</a></td>
                <td>
                    <c:if test="${(data.MAX_USR - data.REQ_CNT) <= 0}"><font color="red"></c:if>
                    <a href="javascript:view('${data.SCH_DAY}','${data.CAT_CD}');">${data.REQ_CNT} / ${data.MAX_USR}</a>
                </td>
                <td><a href="javascript:update('${data.SCH_DAY}','${data.CAT_CD}','${data.CAT_NM}');">[수정]</a></td>
            </tr>
        </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="5">예정된 상담이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</form>
</div>
<!--//content -->
