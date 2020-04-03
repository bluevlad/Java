<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
var SUBJECTTYPEDIVISION = "${searchMap.SUBJECTTYPEDIVISION}";
var CLASSCODE = "${searchMap.CLASSCODE}";
var EXAMYEAR = "${searchMap.EXAMYEAR}";
var EXAMROUND = "${searchMap.EXAMROUND}";
var SUBJECT_CD = "${searchMap.SUBJECT_CD}";
var PROFCODE = "${searchMap.PROFCODE}";

window.onload = function () {
    if(EXAMYEAR != "") {
        getSubCode('EXAMYEAR', 'EXAMROUND', '<c:url value="/mouigosa/reg/getRegistrationExamRound.do"/>');
        getSubCode2('EXAMROUND', 'SUBJECT_CD', '<c:url value="/mouigosa/reg/getRegistrationSubject.do"/>');
        if(SUBJECT_CD != "") {
            getSubCode3('SUBJECT_CD', 'PROFCODE', '<c:url value="/mouigosa/subCode.do"/>');
        }
    }
}

//비동기 회차 가져오기
function getSubCode(selectId, target, url) {
    /* if(sts == "I") {
        EXAMYEAR = $("#"+selectId+" option:selected").val();
    }else{
        EXAMYEAR = "${searchMap.EXAMYEAR}";
    } */

    if(EXAMYEAR == "" || EXAMYEAR == undefined) {
        return;
    }

    $("#EXAMROUND").html('').append("<option value=\"\">회차</option>");
    var _url = url + '?EXAMYEAR=' + EXAMYEAR;

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<option value="' + mouigosa.EXAMROUND + '" > ' + mouigosa.EXAMROUND + '</option>');

                    if(mouigosa.EXAMROUND == EXAMROUND){
                        popFrm.EXAMROUND.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });

    if(EXAMYEAR == "" || EXAMYEAR == undefined || EXAMROUND == "" || EXAMROUND == undefined) {
        return;
    }
    $("#SUBJECT_CD").html('').append("<option value=\"\">과목 선택</option>");
    
    var url = '<c:url value="/mouigosa/reg/getRegistrationSubject.do"/>';
    var _url = url + '?EXAMYEAR=' + EXAMYEAR + '&EXAMROUND=' + EXAMROUND;

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa2){
                    $("#SUBJECT_CD").append('<option value="' + mouigosa2.SUBJECT_CD + '" > ' + mouigosa2.SUBJECT_NM + '</option>');
                    if(mouigosa2.SUBJECT_CD == SUBJECT_CD){
                        popFrm.SUBJECT_CD.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });

    if(SUBJECT_CD == "" || SUBJECT_CD == undefined) {
        return;
    }

    $("#PROFCODE").html('').append("<option value=\"\">교수 선택</option>");
    var url = '<c:url value="/mouigosa/subCode.do"/>';
    var _url = url + '?SUBJECT_CD=' + SUBJECT_CD;

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa3){
                    $("#PROFCODE").append('<option value="' + mouigosa3.USER_ID + '" > ' + mouigosa3.USER_NM + '</option>');

                    if(mouigosa3.USER_ID == PROFCODE){
                        popFrm.PROFCODE.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//비동기 과목 가져오기
function getSubCode1(selectId, target, url) {

    /* if(sts == "I") {
        EXAMROUND = $("#"+selectId+" option:selected").val();
    }else{
        EXAMROUND = "${searchMap.EXAMROUND}";
    } */

    var EXAMYEAR = $("#"+selectId+" option:selected").val();
    if(EXAMYEAR == "" || EXAMYEAR == undefined) {
        return false;
    }
    $("#EXAMROUND").html('').append("<option value=\"\">회차</option>");
    $("#SUBJECT_CD").html('').append("<option value=\"\">과목 선택</option>");
    $("#PROFCODE").html('').append("<option value=\"\">교수 선택</option>");
    var _url = url + '?EXAMYEAR=' + EXAMYEAR;

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<option value="' + mouigosa.EXAMROUND + '" > ' + mouigosa.EXAMROUND + '</option>');

                    if(mouigosa.EXAMROUND == EXAMYEAR){
                        popFrm.EXAMROUND.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//비동기 과목 가져오기
function getSubCode2(selectId, target, url) {

    /* if(sts == "I") {
        EXAMROUND = $("#"+selectId+" option:selected").val();
    }else{
        EXAMROUND = "${searchMap.EXAMROUND}";
    } */

    var EXAMYEAR = $("#EXAMYEAR option:selected").val();
    var EXAMROUND = $("#EXAMROUND option:selected").val();
    if(EXAMYEAR == "" || EXAMYEAR == undefined && EXAMROUND == "" || EXAMROUND == undefined) {
        return false;
    }

    $("#SUBJECT_CD").html('').append("<option value=\"\">과목 선택</option>");
    $("#PROFCODE").html('').append("<option value=\"\">교수 선택</option>");

    var _url = url + '?EXAMYEAR=' + EXAMYEAR + '&EXAMROUND=' + EXAMROUND + '&CLASSCODE=' + $("#CLASSCODE").val() + '&SUBJECTTYPEDIVISION=' + $("#SUBJECTTYPEDIVISION").val();

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<option value="' + mouigosa.SUBJECT_CD + '" > ' + mouigosa.SUBJECT_NM + '</option>');

                    if(mouigosa.SUBJECT_CD == SUBJECT_CD){
                        popFrm.SUBJECT_CD.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//비동기 강사 가져오기
function getSubCode3(selectId, target, url) {

    /* if(sts == "I") {
        EXAMYEAR = $("#"+selectId+" option:selected").val();
    }else{
        EXAMYEAR = "${searchMap.EXAMYEAR}";
    } */

    var SUBJECT_CD = $("#SUBJECT_CD option:selected").val();
    if(SUBJECT_CD == "" || SUBJECT_CD == undefined) {
        return false;
    }

    $("#PROFCODE").html('').append("<option value=\"\">교수 선택</option>");
    var _url = url + '?SUBJECT_CD=' + SUBJECT_CD;

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<option value="' + mouigosa.USER_ID + '" > ' + mouigosa.USER_NM + '</option>');

                    if(mouigosa.USER_ID == PROFCODE){
                        popFrm.PROFCODE.options[index+1].selected = true;
                    }
                });
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#popFrm').attr('action','<c:url value="/mouigosa/reg/updateRegistrationOnlyMouigosa.pop"/>').submit();
}

//추가
function checkParam(ITEMID,SUBJECT_CD,SUBJECT_NM,USERNAME) {
    $(opener.location).attr("href","javascript:goparam('"+ITEMID+"','"+SUBJECT_CD+"','"+SUBJECT_NM+"','"+USERNAME+"','"+SUBJECTTYPEDIVISION+"');");
    self.close();
}
</script>
</head>

<!--팝업-->
<form id="popFrm" name="popFrm" enctype="multipart/form-data" method="post">
<input type="hidden" id="currentPage" name="currentPage">
<input type="hidden" id="SUBJECTTYPEDIVISION" name="SUBJECTTYPEDIVISION" value="${searchMap.SUBJECTTYPEDIVISION}" />
<input type="hidden" id="CLASSCODE" name="CLASSCODE" value="${searchMap.CLASSCODE}" />

    <div id="content_pop" style="width:770px;position:relative;">
    <h2>● <strong>필수/선택 과목 불러오기</strong></h2>

    <!--테이블-->
    <table class="table01">
        <caption>
        </caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
            <input type="hidden" name="_method" value="get">
            <div class="item" style="margin-left:0px;">
                <select style="width:55px;"   id="EXAMYEAR" name="EXAMYEAR" onchange="getSubCode1('EXAMYEAR', 'EXAMROUND', '<c:url value="/mouigosa/reg/getRegistrationExamRound.do"/>')">
                    <option value="">년도</option>
                    <c:forEach items="${exam_list}"  var="exam_list">
                        <option value="${exam_list.EXAMYEAR }" <c:if test="${searchMap.EXAMYEAR == exam_list.EXAMYEAR}">selected</c:if>>${exam_list.EXAMYEAR }</option>
                    </c:forEach>
                </select>
                &nbsp;
                <select style="width:50px;"   id="EXAMROUND" name="EXAMROUND" onchange="getSubCode2('EXAMROUND', 'SUBJECT_CD', '<c:url value="/mouigosa/reg/getRegistrationSubject.do"/>')">
                    <option value="">회차</option>
                </select>
                &nbsp;
                <select id="SUBJECT_CD" name="SUBJECT_CD" onchange="getSubCode3('SUBJECT_CD', 'PROFCODE', '<c:url value="/mouigosa/subCode.do"/>')">
                    <option value="">과목 선택  </option>
                </select>
                &nbsp;
                <select id="PROFCODE" name="PROFCODE">
                    <option value="">교수 선택</option>
                </select>
                &nbsp;&nbsp;
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
            </div>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--테이블-->
    <table class="table02">
        <caption>
        </caption>
        <colgroup>
        <col width="20%">
        <col width="20%">
        <col width="*">
        <col width="25%">
        <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">년도</th>
            <th scope="col">회차</th>
            <th scope="col">과목(문제수)</th>
            <th scope="col">강사(구분)</th>
            <th scope="col">선택</th>
        </tr>
        </thead>
        <tbody>
    <c:if test="${not empty list}">
      <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
          <td>${list.EXAMYEAR}</td>
          <td>${list.EXAMROUND}</td>
          <td>${list.SUBJECT_NM}</td>
          <td>${list.PROF_NM}</td>
          <td><span class="btn_pack small"><button type="button" onclick="checkParam('${list.ITEMID}','${list.SUBJECT_CD}','${list.SUBJECT_NM}','${list.PROF_NM}')">추가</button></span></td>
        </tr>
      </c:forEach>
    </c:if>
    <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="5">데이터가 존재하지 않습니다.</td>
        </tr>
    </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
   <!--//paginate-->

    <!-- <a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->
</div>
</form>
<!--//팝업-->