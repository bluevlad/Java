<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
var sts = "${sts}";

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

window.onload = function () {
    /* alert("S_EXAMYEAR:"+S_EXAMYEAR +"\n"+
            "S_EXAMROUND:"+S_EXAMROUND +"\n"+
            "S_searchFlag:"+S_searchFlag +"\n"+
            "S_searchKeyWord:"+S_searchKeyWord +"\n"+
            "currentPage:"+currentPage +"\n"+
            "pageRow:"+pageRow); */

    if(sts == "U") {
        getSubCode('SUBJECT_CD', 'PROFCODE', '<c:url value="/mouigosa/subCode.do"/>', 'U');
    }
}

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            $('#QUESTIONNUM').val("");
            $('#QUESTIONNUM').focus();
            return;
        } else {
            if (val < 1) {
                $('#QUESTIONNUM').val("");
                $('#QUESTIONNUM').focus();
                return;
            }
        }
    }
}

//비동기 교수 가져오기
function getSubCode(selectId, target, url, sts) {
    var SUBJECT_CD = "";

    if(sts == "I") {
        SUBJECT_CD = $("#"+selectId+" option:selected").val();
    }else{
        SUBJECT_CD = "${searchMap.SUBJECT_CD}";
    }
    if(SUBJECT_CD == "" || SUBJECT_CD == undefined) {
        return false;
    }

    $("#PROFCODE").html('').append("<option value=\"\">교수 선택</option>");

    var _url = url + '?SUBJECT_CD=' + SUBJECT_CD;
    var PROFCODE = "${searchMap.PROFCODE}";

    $.ajax({
        type : "GET",
        url : _url,
        dataType : "json",
        async : false,
        success : function(json){
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    $("#"+target).append('<option value="' + mouigosa.USER_ID + '" > ' + mouigosa.USERNAME + '</option>');

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

//년.회차,과목 중복체크
function checkParam() {
    if(!formValidate("popFrm")) return;

    var EXAMYEAR = $("#EXAMYEAR option:selected").val();
    var EXAMROUND = $("#EXAMROUND option:selected").val();
    var SUBJECT_CD = $("#SUBJECT_CD option:selected").val();

    var EXAMYEAR2 = "${searchMap.EXAMYEAR}";
    var EXAMROUND2 = "${searchMap.EXAMROUND}";
    var SUBJECT_CD2 = "${searchMap.SUBJECT_CD}";

    /*  alert("EXAMYEAR:"+$("#EXAMYEAR").val() +"\n"+
            "EXAMROUND:"+$("#EXAMROUND").val()+"\n"+
            "ITEMID:"+$("#ITEMID").val()); */

    if(EXAMYEAR == EXAMYEAR2 && EXAMROUND == EXAMROUND2  && SUBJECT_CD == SUBJECT_CD2) {
        opener.name="list";
        popFrm.target = opener.name;
        if(sts == "U") {
            $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaUpdate.do"/>').submit();
        }else{
            $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaInsert.do"/>').submit();
        }
        self.close();

    }else{
        var _url = '<c:url value="/mouigosa/duplication.do"/>?EXAMYEAR=' + EXAMYEAR + '&EXAMROUND=' + EXAMROUND + '&SUBJECT_CD=' + SUBJECT_CD;

        $.ajax({
            type : "GET",
            url : _url,
            dataType : "json",
            async : false,
            success : function(json){
                //alert("duplication.length:"+json.duplication.length);
                if(json && json.length > 0) {
                    $(json).each(function(index, mouigosa){
                        if(mouigosa.STSCNT2 > 0){
                            //alert("1");
                            alert("해당 년,회차,과목이 이미 등록되있습니다.");
                            return;
                        }else{
                            //alert("2");
                            opener.name="list";
                            popFrm.target = opener.name;
                            if(sts == "U") {
                                $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaUpdate.do"/>').submit();
                            }else{
                                $('#popFrm').attr('action','<c:url value="/mouigosa/mouigosaInsert.do"/>').submit();
                            }
                            self.close();
                        }
                    });
                }
            },
            error: function(d, json){
                alert("error: "+d.status);
            }
        });
    }
}
</script>
</head>

<!--팝업-->
<form id="popFrm" name="popFrm" method="post">
<input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />

<input type="hidden" id="S_EXAMYEAR" name="S_EXAMYEAR" value="${searchMap.S_EXAMYEAR}" />
<input type="hidden" id="S_EXAMROUND" name="S_EXAMROUND" value="${searchMap.S_EXAMROUND}" />
<input type="hidden" id="S_searchFlag" name="S_searchFlag" value="${searchMap.S_searchFlag}" />
<input type="hidden" id="S_searchKeyWord" name="S_searchKeyWord" value="${searchMap.S_searchKeyWord}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

    <div id="content_pop" style="width:770px;position:relative;">
    <h2>
    <c:choose>
        <c:when test="${sts == 'I'}">● <strong>과목별 설정 등록</strong></c:when>
        <c:when test="${sts == 'U'}">● <strong>과목별 설정 수정</strong></c:when>
    </c:choose>
    </h2>

    <!--테이블-->
    <table class="table01">
        <colgroup>
            <col width="28%">
            <col width="72%">
        </colgroup>
        <tbody>
        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문제등록 옵션</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="QUESTIONREGISTRATIONOPTION" name="QUESTIONREGISTRATIONOPTION" class="must" >
                        <option value="1"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '1'}">selected</c:if>>옵션1</option>
                        <option value="2"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '2'}">selected</c:if>>옵션2</option>
                        <option value="3"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '3'}">selected</c:if>>옵션3</option>
                        <option value="4"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '4'}">selected</c:if>>옵션4</option>
                    </select>
                </div>
            </td>
        </tr>

        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">년/회차</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="EXAMYEAR" name="EXAMYEAR" class="must" <c:if test="${searchMap.STSCNT >  0 }">readOnly="readOnly"</c:if>>
                        <option value="2013"  <c:if test="${searchMap.EXAMYEAR == '2013'}">selected</c:if>>2013</option>
                        <option value="2014"  <c:if test="${searchMap.EXAMYEAR == '2014'}">selected</c:if>>2014</option>
                        <option value="2015"  <c:if test="${searchMap.EXAMYEAR == '2015'}">selected</c:if>>2015</option>
                        <option value="2016"  <c:if test="${searchMap.EXAMYEAR == '2016'}">selected</c:if>>2016</option>
                        <option value="2017"  <c:if test="${searchMap.EXAMYEAR == '2017'}">selected</c:if>>2017</option>
                        <option value="2018"  <c:if test="${searchMap.EXAMYEAR == '2018'}">selected</c:if>>2018</option>
                        <option value="2019"  <c:if test="${searchMap.EXAMYEAR == '2019'}">selected</c:if>>2019</option>
                    </select>
                    /
                    <select id="EXAMROUND" name="EXAMROUND" class="must" <c:if test="${searchMap.STSCNT >  0 }">readOnly="readOnly"</c:if>>
                        <option value="1"  <c:if test="${searchMap.EXAMROUND == '1'}">selected</c:if>>1</option>
                        <option value="2"  <c:if test="${searchMap.EXAMROUND == '2'}">selected</c:if>>2</option>
                        <option value="3"  <c:if test="${searchMap.EXAMROUND == '3'}">selected</c:if>>3</option>
                        <option value="4"  <c:if test="${searchMap.EXAMROUND == '4'}">selected</c:if>>4</option>
                        <option value="5"  <c:if test="${searchMap.EXAMROUND == '5'}">selected</c:if>>5</option>
                        <option value="6"  <c:if test="${searchMap.EXAMROUND == '6'}">selected</c:if>>6</option>
                        <option value="7"  <c:if test="${searchMap.EXAMROUND == '7'}">selected</c:if>>7</option>
                        <option value="8"  <c:if test="${searchMap.EXAMROUND == '8'}">selected</c:if>>8</option>
                        <option value="9"  <c:if test="${searchMap.EXAMROUND == '9'}">selected</c:if>>9</option>
                        <option value="10"  <c:if test="${searchMap.EXAMROUND == '10'}">selected</c:if>>10</option>
                        <option value="11"  <c:if test="${searchMap.EXAMROUND == '11'}">selected</c:if>>11</option>
                        <option value="12"  <c:if test="${searchMap.EXAMROUND == '12'}">selected</c:if>>12</option>
                        <option value="13"  <c:if test="${searchMap.EXAMROUND == '13'}">selected</c:if>>13</option>
                        <option value="14"  <c:if test="${searchMap.EXAMROUND == '14'}">selected</c:if>>14</option>
                        <option value="15"  <c:if test="${searchMap.EXAMROUND == '15'}">selected</c:if>>15</option>
                        <option value="16"  <c:if test="${searchMap.EXAMROUND == '16'}">selected</c:if>>16</option>
                        <option value="17"  <c:if test="${searchMap.EXAMROUND == '17'}">selected</c:if>>17</option>
                        <option value="18"  <c:if test="${searchMap.EXAMROUND == '18'}">selected</c:if>>18</option>
                        <option value="19"  <c:if test="${searchMap.EXAMROUND == '19'}">selected</c:if>>19</option>
                        <option value="20"  <c:if test="${searchMap.EXAMROUND == '20'}">selected</c:if>>20</option>
                    </select>
                </div>
            </td>
        </tr>

        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">과목/강사</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="SUBJECT_CD" name="SUBJECT_CD" class="must"  title="과목을 선택해 주십시오." onchange="getSubCode('SUBJECT_CD', 'PROFCODE', '<c:url value="/mouigosa/subCode.do"/>', 'I')">
                        <option value="">과목 선택  </option>
                    <c:forEach items="${subject_list}"  var="subject_list">
                        <option value="${subject_list.SUBJECT_CD }" <c:if test="${searchMap.SUBJECT_CD == subject_list.SUBJECT_CD}">selected</c:if>>${subject_list.SUBJECT_NM }</option>
                    </c:forEach>
                    </select>
                    /
                    <select id="PROFCODE" name="PROFCODE" class="must" title="교수를 선택해 주십시오." >
                        <option value="">교수 선택</option>
                    </select>
                </div>
            </td>
        </tr>

        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">지문수</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="ENTRYNUM" name="ENTRYNUM" class="must" >
                        <option value="4"  <c:if test="${searchMap.ENTRYNUM == '4'}">selected</c:if>>4</option>
                        <option value="5"  <c:if test="${searchMap.ENTRYNUM == '5'}">selected</c:if>>5</option>
                    </select>
                </div>
            </td>
        </tr>

        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">문항 갯수</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item"><input id="QUESTIONNUM" name="QUESTIONNUM" type="text" class="i_text must" style="width:10%;" maxlength="2" title="문제지정을 입력해 주세요" value="${searchMap.QUESTIONNUM}" onkeyup="chk(this)" onblur="chk(this)"/>개</div>
            </td>
        </tr>

        <tr>
            <th style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">개설여부</th>
            <td style="table-layout:fixed; text-align:left; vertical-align:middle;" scope="row">
                <div class="item">
                    <select id="OPENSTATE" name="OPENSTATE" class="must">
                        <option value="1"  <c:if test="${searchMap.OPENSTATE == '1'}">selected</c:if>>활성</option>
                        <option value="0"  <c:if test="${searchMap.OPENSTATE == '0'}">selected</c:if>>비활성</option>
                    </select>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:checkParam();">저장</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->

    <!-- <a class="clse" href="#" onclick="self.close()"> <img alt="닫기" src="<c:url value="/resources/img/common/btn_close_layer3.gif"/>" width="19" height="19"></a> -->
</div>
</form>
<!--//팝업-->
