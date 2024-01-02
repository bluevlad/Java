<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %><head>
<script type="text/javascript">
$(document).ready(function(){
    $('#CATEGORY_CD').on('change', function(){
        if(($("select[name=CATEGORY_CD]").val()).indexOf('OM_001') > -1
           || ($("select[name=CATEGORY_CD]").val()).indexOf('FM_001') > -1 ) {
            $("#SCREEN_GUBUN").val('M');
        } else if(($("select[name=CATEGORY_CD]").val()).indexOf('MM_000') > -1) {
            $("#SCREEN_GUBUN").val('H');
        } else {
            $("#SCREEN_GUBUN").val('S');
        }
        fn_getBannerCode();
    });

    $('#BANNER_SEL').on('change', function(){
        if($("select[name=BANNER_SEL]").val() != '') {
            $("#BANNER_NO").val($("select[name=BANNER_SEL]").val());
            $("#BANNER_TITLE").val($("select[name=BANNER_SEL] option:selected").text());
        } else {
            alert('배너 영역을 선택해 주세요.');
            return;
        }
    });
});

function fn_getBannerCode(){
    var params = '?ONOFF_DIV=' + $("#ONOFF_DIV").val()
               + '&SCREEN_GUBUN=' + $("#SCREEN_GUBUN").val()
               + '&CATEGORY_CD=' + $("select[name=CATEGORY_CD]").val();
    $.ajax({
        type: "GET",
        url : '<c:url value="/bannerManagement/getBannerCode.json"/>'+params,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        async : false,
        success: function(RES) {
            if(RES.length  > 0){
                $('#BANNER_SEL').find('option').remove();
                $('#BANNER_SEL').find('option').end().append("<option value=''>선택</option>").val("");
                $.each(RES,function(idx, data){
                    var list = "";
                    list +="<option value='" + data.CODE_CD +"'>" + data.CODE_NM + "</option>";
                    $('#BANNER_SEL')
                        .find('option')
                        .end()
                        .append(list)
                        .val(data.CODE_CD);
                });
                $('#BANNER_SEL').prop('selectedIndex',0);
            }else {
                alert("검색결과가 없습니다.")
                return;
            }
        },error: function(){
            alert("검색 실패");
            return;
        }
    });
}

function fn_List(){
    $("#frm").attr("action", "<c:url value='/bannerManagement/bannerMgtList.do' />");
    $("#frm").submit();
}
//등록
function fn_Submit(){
    if($("select[name=CATEGORY_CD]").val() == '') {
        alert("메인홈 또는 직렬을 선택해 주세요.");
        $("#CATEGORY_CD").focus();
        return;
    }
    if($("select[name=BANNER_SEL]").val() == '') {
        alert("배너 영역을 선택해 주세요.");
        $("#BANNER_SEL").focus();
        return;
    }
    if($.trim($("#BANNER_TITLE").val())==""){
        alert("배너 영역을 선택해 주세요.");
        $("#BANNER_SEL").focus();
        return;
    }
    if(confirm("배너를 등록하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/bannerManagement/insert.do' />");
        $("#frm").submit();
    }
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}"/>

<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="O"/>
</c:when><c:otherwise>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="F"/>
</c:otherwise></c:choose>
<input type="hidden" id="SCREEN_GUBUN" name="SCREEN_GUBUN" value=""/>
        <h2>● 사이트관리 > <strong>배너관리</strong></h2>
        <table class="table01">
            <tr>
                <th>직렬</th>
                <td class="tdLeft">
                <select name="CATEGORY_CD" id="CATEGORY_CD">
                    <option value="">선택</option>
                    <c:forEach items="${catekindlist}" var="item" varStatus="loop">
                        <option value="${item.CODE}">${item.NAME}</option>
                    </c:forEach>
                    <option value="018">김동진법원직</option>
                    <option value="" disabled="disabled">----------------</option>
                    <c:forEach items="${menukindlist}" var="item" varStatus="loop">
                        <option value="${item.CODE}">${item.NAME}</option>
                    </c:forEach>
                    <option value="" disabled="disabled">----------------</option>
                        <option value="MM_000">모바일홈</option>
                </select></td>
            </tr>
            <!--직렬선택 /-->
            <tr>
                <th width="10%">배너 영역</th>
                <td class="tdLeft">
                <select name="BANNER_SEL" id="BANNER_SEL">
                    <option value="">선택</option>
                <c:forEach items="${MAIN_BNNRs}" var="data" varStatus="status" >
                    <option value="${data.CODE_VAL}">${data.CODE_NM}</option>
                </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <th width="10%">번호</th>
                <td class="tdLeft tdValign"><input type="text" id="BANNER_NO" name="BANNER_NO" size="10" readonly="true"></td>
            </tr>
            <tr>
                <th>배너명</th>
                <td><input type="text" id="BANNER_TITLE" name="BANNER_TITLE" size="100%" value=""></td>
            </tr>
            <tr>
                <th>배너유형</th>
                <td class="tdLeft tdValign"><span class="tdLeft">
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="I" checked><span class="txt03">이미지등록</span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="L"><span class="txt04">강좌선택</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="B"><span class="txt05">게시판연결</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="H"><span class="txt06">HTML</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="P"><span class="txt07">교수</span></span>
                  <input type="radio" name="BANNER_TYP" id="BANNER_TYP" value="T"><span class="txt08">모의고사</span></span>
                </td>
            </tr>
            <%--<tr>
                <th>적용날짜</th>
                <td class="tdLeft tdValign">시작일 <input type="text" id="OPEN_STARTDATE" name="OPEN_STARTDATE" size="10">
                 ~ 종료일 <input type="text" id="OPEN_ENDDATE" name="OPEN_ENDDATE" size="10">
                  </td>
            </tr>--%>
            <tr>
                <th>적용여부</th>
                <td class="tdLeft tdValign"><span class="tdLeft">
                  <input type="radio" name="ISUSE" id="ISUSE" value="Y" checked><span class="txt03">적용</span>
                  <input type="radio" name="ISUSE" id="ISUSE" value="N"><span class="txt04">비적용</span></span>
                </td>
            </tr>
        </table>
        <!--//테이블--> 
    
        <!--버튼-->
        <ul class="boardBtns">
          <li><a href="javascript:fn_Submit();">확인</a></li>
          <li><a href="javascript:fn_List();">목록</a></li>
        </ul>
        <!--//버튼--> 
</form>
</div>
<!--//content --> 
