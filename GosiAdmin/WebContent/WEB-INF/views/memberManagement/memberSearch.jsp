<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDATE"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
    initDatePicker1("EDATE");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            $('#pageRow').val("");
            $('#pageRow').focus();
            return;
        } else {
            if (val < 1) {
                $('#pageRow').val("");
                $('#pageRow').focus();
                return;
            }
        }
    }
}

//엔터키 검색
function fn_checkEnter(){
    $('#SEARCHKEYWORD').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });

    $('#pageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
}

//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        fn_Search();
        return;
    }
    if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#searchFrm').attr('action','<c:url value="/memberManagement/memberList.do"/>').submit();
}

//등록
function addMember() {
    $('#searchFrm').attr('action','<c:url value="/memberManagement/memberInsert.do"/>').submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 > <strong>회원조회</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td colspan="3">
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                    <option value="PHONE_NO"  <c:if test="${params.SEARCHTYPE == 'PHONE_NO'}">selected</c:if>>휴대폰번호</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            </td>
        </tr>
        <tr>
            <th width="15%">등록일</th>
            <td>
                <input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
                ~
                <input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
            </td>    
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:addMember();">등록</a></li>
    </ul>
    <ul class="boardBtns">
            <b>*조회가 안되는 2013년 이전 가입회원은 [이관회원관리]에서 검색해주세요.</b><br>
            <b>*가입사이트 : 공무원 / 경찰 / 광주캠퍼스</b>
    </ul>
    </form>
</div>
<!--//content -->