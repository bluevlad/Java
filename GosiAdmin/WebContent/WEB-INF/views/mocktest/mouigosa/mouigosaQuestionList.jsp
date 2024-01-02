<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var sts = "${sts}";

var ITEMID = "${searchMap.ITEMID}";
var EXAMROUND = "${searchMap.EXAMROUND}";
var SUBJECT_CD = "${searchMap.SUBJECT_CD}";
var ENTRYNUM = "${searchMap.ENTRYNUM}";
var QUESTIONREGISTRATIONOPTION = "${searchMap.QUESTIONREGISTRATIONOPTION}";

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var S_currentPage = "${searchMap.S_currentPage}";
var S_pageRow = "${searchMap.S_pageRow}";

window.onload = function () {
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
                    $("#"+target).append('<option value="' + mouigosa.USER_ID + '" > ' + mouigosa.USER_NM + '</option>');
                    if(mouigosa.USER_ID == PROFCODE){
                        searchFrm.PROFCODE.options[index+1].selected = true;
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
    if(!formValidate("searchFrm")) return;

    var EXAMYEAR = $("#EXAMYEAR option:selected").val();
    var EXAMROUND = $("#EXAMROUND option:selected").val();
    var SUBJECT_CD = $("#SUBJECT_CD option:selected").val();

    var EXAMYEAR2 = "${searchMap.EXAMYEAR}";
    var EXAMROUND2 = "${searchMap.EXAMROUND}";
    var SUBJECT_CD2 = "${searchMap.SUBJECT_CD}";

    if (!$("#imgFile").val() == "") {
        $("#imgFile_sts").val("Y");
    }
    if (!$("#imgFile2").val() == "") {
        $("#imgFile2_sts").val("Y");
    }
    if(sts == "U") {
        $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaUpdate.do"/>').submit();
    }else{
        $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaInsert.do"/>').submit();
    }

    /* if(EXAMYEAR == EXAMYEAR2 && EXAMROUND == EXAMROUND2  && SUBJECT_CD == SUBJECT_CD2) {
        if(sts == "U") {
            $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaUpdate.do"/>').submit();
        }else{
            $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaInsert.do"/>').submit();
        }

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
                            if(sts == "U") {
                                $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaUpdate.do"/>').submit();
                            }else{
                                $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaInsert.do"/>').submit();
                            }
                        }
                    });
                }
            },
            error: function(d, json){
                alert("error: "+d.status);
            }
        });
    } */
}

//등록
function addPopup() {
    var go_url = '<c:url value="/mouigosa/createQuestionMouigosa.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&SUBJECT_CD=' + SUBJECT_CD
                + '&ITEMID=' + ITEMID 
                + '&S_EXAMYEAR=' + S_EXAMYEAR 
                + '&S_EXAMROUND=' + S_EXAMROUND 
                + '&S_searchFlag=' + S_searchFlag 
                + '&S_searchKeyWord=' + escape(encodeURIComponent(S_searchKeyWord)) 
                + '&S_currentPage=' + S_currentPage 
                + '&S_pageRow=' + S_pageRow;

    if(QUESTIONREGISTRATIONOPTION == "1" || QUESTIONREGISTRATIONOPTION == "2"){
        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=782,height=382');
    }else{
        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=780,height=597');
    }
}

//수정
function editPopup(ITEMID,QUESTIONNUMBER) {
    var go_url = '<c:url value="/mouigosa/updateQuestionMouigosa.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&SUBJECT_CD=' + SUBJECT_CD
                + '&ITEMID=' + ITEMID 
                + '&QUESTIONNUMBER=' + QUESTIONNUMBER 
                + '&S_EXAMYEAR=' + S_EXAMYEAR 
                + '&S_EXAMROUND=' + S_EXAMROUND 
                + '&S_searchFlag=' + S_searchFlag 
                + '&S_searchKeyWord=' + escape(encodeURIComponent(S_searchKeyWord)) 
                + '&S_currentPage=' + S_currentPage 
                + '&S_pageRow=' + S_pageRow;

    if(QUESTIONREGISTRATIONOPTION == "1" || QUESTIONREGISTRATIONOPTION == "2"){
        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=790,height=715');
    }else{
        window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=778,height=602');
    }
}

//문제지정
function goQestionAll() {
    if (ITEMID == "") {
        alert('과목을 먼저 저장해주세요.');
        return;
    }
    var go_url = '<c:url value="/mouigosa/updateQuestionAllMouigosa.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&ITEMID=' + ITEMID 
                + '&S_EXAMYEAR=' + S_EXAMYEAR 
                + '&S_EXAMROUND=' + S_EXAMROUND 
                + '&S_searchFlag=' + S_searchFlag 
                + '&S_searchKeyWord=' + escape(encodeURIComponent(S_searchKeyWord)) 
                + '&S_currentPage=' + S_currentPage 
                + '&S_pageRow=' + S_pageRow;

    window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=790,height=950');
}

//불러오기
function goQestionPop(ITEMID,QUESTIONNUMBER) {
    if (ITEMID == "") {
        alert('과목을 먼저 저장해주세요.');
        return;
    }
    var go_url = '<c:url value="/mouigosa/updateQuestionOnlyMouigosa.pop"/>?'
                + 'TOP_MENU_ID=' + $("#TOP_MENU_ID").val()
                + '&MENU_ID=' + $("#MENU_ID").val()
                + '&MENUTYPE=' + $("#MENUTYPE").val()
                + '&L_MENU_NM=' + $("#L_MENU_NM").val()
                + '&SUBJECT_CD=' + SUBJECT_CD
                + '&ITEMID=' + ITEMID 
                + '&QUESTIONNUMBER=' + QUESTIONNUMBER 
                + '&S_EXAMYEAR=' + S_EXAMYEAR 
                + '&S_EXAMROUND=' + S_EXAMROUND 
                + '&S_searchFlag=' + S_searchFlag 
                + '&S_searchKeyWord=' + escape(encodeURIComponent(S_searchKeyWord)) 
                + '&S_currentPage=' + S_currentPage 
                + '&S_pageRow=' + S_pageRow;

    window.open(go_url, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=795,height=950');
}

//목록
function goList() {
    $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaList.do"/>').submit();
}

//프린트 팝업
function PrintPopup(ITEMID,QUESTIONNUMBER,sts) {
    var go_url = '<c:url value="/mouigosa/printMouigosa.pop"/>?'
                + 'ITEMID=' + ITEMID
                + '&QUESTIONREGISTRATIONOPTION=' + QUESTIONREGISTRATIONOPTION 
                + '&QUESTIONNUMBER=' + QUESTIONNUMBER 
                + '&sts=' + sts;

    window.open(go_url, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=600,height=700');
}

function goFileDownload(filePath) {
    //alert("filePath:"+filePath);
    document.location.href = "<c:url value='/download.do' />?path="+filePath;
}
</script>
</head>

<form id="searchFrm" name="searchFrm" enctype="multipart/form-data" method="post">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="S_currentPage" name="S_currentPage" value="${searchMap.S_currentPage}">
    <input type="hidden" id="S_pageRow" name="S_pageRow" value="${searchMap.S_pageRow}">

    <input type="hidden" id="deleteIds" name="deleteIds">

    <input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
    <input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />

    <input type="hidden" id="S_EXAMYEAR" name="S_EXAMYEAR" value="${searchMap.S_EXAMYEAR}">
    <input type="hidden" id="S_EXAMROUND" name="S_EXAMROUND" value="${searchMap.S_EXAMROUND}">
    <input type="hidden" id="S_searchFlag" name="S_searchFlag" value="${searchMap.S_searchFlag}">
    <input type="hidden" id="S_searchKeyWord" name="S_searchKeyWord" value="${searchMap.S_searchKeyWord}">

    <input type="hidden" id="imgFile_sts" name="imgFile_sts" />
    <input type="hidden" id="imgFile2_sts" name="imgFile2_sts" />

  <!--content -->
  <div id="content">
    <h2>● 모의고사등록 > <strong>과목별 문제등록</strong></h2>
    <!--테이블-->

    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">년도/회차</th>
            <td scope="col" style="text-align:left;">
                <div class="item">
                <select id="EXAMYEAR" name="EXAMYEAR" class="must">
                    <option value="2013"  <c:if test="${searchMap.EXAMYEAR == '2013'}">selected</c:if>>2013</option>
                    <option value="2014"  <c:if test="${searchMap.EXAMYEAR == '2014'}">selected</c:if>>2014</option>
                    <option value="2015"  <c:if test="${searchMap.EXAMYEAR == '2015'}">selected</c:if>>2015</option>
                    <option value="2016"  <c:if test="${searchMap.EXAMYEAR == '2016'}">selected</c:if>>2016</option>
                    <option value="2017"  <c:if test="${searchMap.EXAMYEAR == '2017'}">selected</c:if>>2017</option>
                    <option value="2018"  <c:if test="${searchMap.EXAMYEAR == '2018'}">selected</c:if>>2018</option>
                    <option value="2019"  <c:if test="${searchMap.EXAMYEAR == '2019'}">selected</c:if>>2019</option>
                   </select>
                   /
                   <select id="EXAMROUND" name="EXAMROUND" class="must">
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
                    <option value="21"  <c:if test="${searchMap.EXAMROUND == '21'}">selected</c:if>>21</option>
                    <option value="22"  <c:if test="${searchMap.EXAMROUND == '22'}">selected</c:if>>22</option>
                    <option value="23"  <c:if test="${searchMap.EXAMROUND == '23'}">selected</c:if>>23</option>
                    <option value="24"  <c:if test="${searchMap.EXAMROUND == '24'}">selected</c:if>>24</option>
                    <option value="25"  <c:if test="${searchMap.EXAMROUND == '25'}">selected</c:if>>25</option>
                    <option value="26"  <c:if test="${searchMap.EXAMROUND == '26'}">selected</c:if>>26</option>
                    <option value="27"  <c:if test="${searchMap.EXAMROUND == '27'}">selected</c:if>>27</option>
                    <option value="28"  <c:if test="${searchMap.EXAMROUND == '28'}">selected</c:if>>28</option>
                    <option value="29"  <c:if test="${searchMap.EXAMROUND == '29'}">selected</c:if>>29</option>
                    <option value="30"  <c:if test="${searchMap.EXAMROUND == '30'}">selected</c:if>>30</option>
                   </select>
                </div>
            </td>

            <th scope="col">과목/강사/강사지급율</th>
            <td scope="col" style="text-align:left;">
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
                    /
                    <input id="FEE_PROF" name="FEE_PROF" type="text" class="i_text must" style="width:30px;" maxlength="5" title="강사료 지급율을 입력해 주세요" value="${searchMap.FEE_PROF}"/>%
                    /
                    <input id="CODE_NM" name="CODE_NM" type="text" class="i_text" style="width:30px;" value="${searchMap.CODE_NM}"/>
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>문제등록옵션</th>
            <td style="text-align:left;">
                <div class="item">
                <select id="QUESTIONREGISTRATIONOPTION" name="QUESTIONREGISTRATIONOPTION" class="must" >
                        <option value="1"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '1'}">selected</c:if>>옵션1</option>
                        <option value="2"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '2'}">selected</c:if>>옵션2</option>
                        <option value="3"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '3'}">selected</c:if>>옵션3</option>
                        <option value="4"  <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '4'}">selected</c:if>>옵션4</option>
                    </select>
                </div>
            </td>

            <th>문항 갯수</th>
            <td style="text-align:left;">
                <div class="item">
                <input id="QUESTIONNUM" name="QUESTIONNUM" type="text" class="i_text must" style="width:40px;" maxlength="2" title="문항 갯수를 입력해 주세요" value="${searchMap.QUESTIONNUM}" onkeyup="chk(this)" onblur="chk(this)"/>개
                </div>
            </td>
        </tr>
        <tr>
            <th>지문수</th>
            <td style="text-align:left;">
                <div class="item">
                <select id="ENTRYNUM" name="ENTRYNUM" class="must" >
                        <option value="4"  <c:if test="${searchMap.ENTRYNUM == '4'}">selected</c:if>>4</option>
                        <option value="5"  <c:if test="${searchMap.ENTRYNUM == '5'}">selected</c:if>>5</option>
                    </select>
                </div>
            </td>
            <th>개설여부</th>
            <td style="text-align:left;">
                <div class="item">
                <select id="OPENSTATE" name="OPENSTATE" class="must">
                        <option value="1"  <c:if test="${searchMap.OPENSTATE == '1'}">selected</c:if>>활성</option>
                        <option value="0"  <c:if test="${searchMap.OPENSTATE == '0'}">selected</c:if>>비활성</option>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <th>문제파일</th>
            <td style="text-align:left;">
                <div class="item">

                <input id="imgFile" name="imgFile" title="레이블 텍스트" type="file" />
                <c:if test="${searchMap.QUESTIONFID ne null }">
                    <a href="javascript:goFileDownload('<c:out value="${searchMap.FILEPATH }" />')">${searchMap.QUESTIONFILENAME }</a>
                </c:if>

                </div>
            </td>
            <th>해설파일</th>
            <td style="text-align:left;">
                <div class="item">

                <input id="imgFile2" name="imgFile2" title="레이블 텍스트" type="file" />
                <c:if test="${searchMap.ANSWERFID ne null }">
                    <a href="javascript:goFileDownload('<c:out value="${searchMap.FILEPATH2 }" />')">${searchMap.ANSWEREXPLAINFILENAME }</a>
                </c:if>

                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
      <c:if test="${searchMap.ITEMID ne null and searchMap.ITEMID ne ''}" >
        <li><a href="javascript:goQestionAll();">문제지정</a></li>
      </c:if>  
        <li><a href="javascript:goList();">목록</a></li>
        <li><a href="javascript:checkParam();">저장</a></li>
    </ul>
    <!--//버튼-->

    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="8%">
        <col width="20%">
        <col width="20%">
        <col width="8%">
        <col width="*">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">문제번호</th>
            <th scope="col">문제</th>
            <th scope="col">해설</th>
            <th scope="col">정답</th>
            <th scope="col">영역</th>
            <th scope="col">난이도</th>
            <th scope="col">저장</th>
            <th scope="col">불러오기</th>
        </tr>
        </thead>
        <tbody>
      <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
            <td>${list.QUESTIONNUMBER}</td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','1')">${searchMap.EXAMROUND}회 ${list.QUESTIONNUMBER}번</a></td>
            <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONNUMBER}','2')">${searchMap.EXAMROUND}회 ${list.QUESTIONNUMBER}번</a></td>
            <td>${list.ANSWERNUMBER}</td>
            <td>${list.QUESTIONRANGENAME}</td>
            <td>${list.LEVELDIFFICULTYNAME}</td>
            <td>
            <c:if test="${list.LEVELDIFFICULTYNAME eq null}"><span class="btn_pack small"><button type="button" onclick="addPopup('${list.ITEMID}','${list.QUESTIONNUMBER}')">등록</button></span></c:if>
            <c:if test="${list.LEVELDIFFICULTYNAME ne null}"><span class="btn_pack small"><button type="button" onclick="editPopup('${list.ITEMID}','${list.QUESTIONNUMBER}')">수정</button></span></c:if>
            </td>
            <td><span class="btn_pack small"><button type="button" onclick="goQestionPop('${list.ITEMID}','${list.QUESTIONNUMBER}')">불러오기</button></span></td>
        </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="8">데이터가 존재하지 않습니다.</td>
        </tr>
      </c:if>
        </tbody>
    </table>
    <!--//테이블-->

  </div>
  <!--//content -->
</form>
