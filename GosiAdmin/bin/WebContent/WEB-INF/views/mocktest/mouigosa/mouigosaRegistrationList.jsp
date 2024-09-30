<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_ISEXAMTYPEON = "${searchMap.S_ISEXAMTYPEON}";
var S_ISEXAMTYPEOFF = "${searchMap.S_ISEXAMTYPEOFF}";
var S_CLASSCODE = "${searchMap.S_CLASSCODE}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var s_totalCount = "${totalCount}";
var s_currentPage = "${currentPage}";
var s_pageRow = "${pageRow}";

window.onload = function () {
}

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
    $('#searchKeyWord').keyup(function(e)  {
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

//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/mouigosaRegistrationList.do"/>').submit();
}

//등록
function add() {
    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/createRegistrationMouigosa.do"/>').submit();
}

//수정
function edit(MOCKCODE) {
    $("#MOCKCODE").val(MOCKCODE);
    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/updateRegistrationMouigosa.do"/>?MOCKCODE='+ MOCKCODE).submit();
}

//삭제
function checkDelete() {
    var tmp ="";
    $("input[name=I_MOCKCODE]:checked").each(function (index){
        tmp += $(this).val() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }

    if(confirm("삭제하시겠습니까?")) {
        $("#deleteIds").val(tmp);

        $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/deleteRegistrationMouigosa.do"/>').submit();
    }
}

//검색
function goReq(mockcode) {
    $("#MOCKCODE").val(mockcode);
    $("#TOP_MENU_ID").val("TM_004");
    $("#MENU_ID").val("TM_004_001");
    $("#MENUTYPE").val("TM_ROOT");
    $("#L_MENU_NM").val("신청자관리T");
	$('#searchFrm').attr('action','<c:url value="/offerer/offererList.do"/>?MENU_NM=신청자관리').submit();
}
</script>
</head>

<form id="searchFrm" name="searchFrm" method="get" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="MOCKCODE" name="MOCKCODE">
    <input type="hidden" id="LECCODE" name="LECCODE" VALUE="Y">

    <!--content -->
    <div id="content">
      <h2>● 모의고사등록 > <strong>모의고사등록</strong></h2>

    <!--테이블-->
    <table class="table01">
        <caption></caption>
        <colgroup>
        <col width="15%">
        <col width="*">
        <col width="15%">
        <col width="30%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">검색</th>
            <td scope="col" style="text-align:left;">
            <input type="hidden" name="_method" value="get">
            <select style="width:55px;"   id="S_EXAMYEAR" name="S_EXAMYEAR">
                <option value="">년도</option>
                <option value="2013"  <c:if test="${searchMap.S_EXAMYEAR == '2013'}">selected</c:if>>2013</option>
                <option value="2014"  <c:if test="${searchMap.S_EXAMYEAR == '2014'}">selected</c:if>>2014</option>
                <option value="2015"  <c:if test="${searchMap.S_EXAMYEAR == '2015'}">selected</c:if>>2015</option>
                <option value="2016"  <c:if test="${searchMap.S_EXAMYEAR == '2016'}">selected</c:if>>2016</option>
                <option value="2017"  <c:if test="${searchMap.S_EXAMYEAR == '2017'}">selected</c:if>>2017</option>
                <option value="2018"  <c:if test="${searchMap.S_EXAMYEAR == '2018'}">selected</c:if>>2018</option>
                <option value="2019"  <c:if test="${searchMap.S_EXAMYEAR == '2019'}">selected</c:if>>2019</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:50px;"   id="S_EXAMROUND" name="S_EXAMROUND">
                <option value="">회차</option>
                <option value="1"  <c:if test="${searchMap.S_EXAMROUND == '1'}">selected</c:if>>1</option>
                <option value="2"  <c:if test="${searchMap.S_EXAMROUND == '2'}">selected</c:if>>2</option>
                <option value="3"  <c:if test="${searchMap.S_EXAMROUND == '3'}">selected</c:if>>3</option>
                <option value="4"  <c:if test="${searchMap.S_EXAMROUND == '4'}">selected</c:if>>4</option>
                <option value="5"  <c:if test="${searchMap.S_EXAMROUND == '5'}">selected</c:if>>5</option>
                <option value="6"  <c:if test="${searchMap.S_EXAMROUND == '6'}">selected</c:if>>6</option>
                <option value="7"  <c:if test="${searchMap.S_EXAMROUND == '7'}">selected</c:if>>7</option>
                <option value="8"  <c:if test="${searchMap.S_EXAMROUND == '8'}">selected</c:if>>8</option>
                <option value="9"  <c:if test="${searchMap.S_EXAMROUND == '9'}">selected</c:if>>9</option>
                <option value="10"  <c:if test="${searchMap.S_EXAMROUND == '10'}">selected</c:if>>10</option>
                <option value="11"  <c:if test="${searchMap.S_EXAMROUND == '11'}">selected</c:if>>11</option>
                <option value="12"  <c:if test="${searchMap.S_EXAMROUND == '12'}">selected</c:if>>12</option>
                <option value="13"  <c:if test="${searchMap.S_EXAMROUND == '13'}">selected</c:if>>13</option>
                <option value="14"  <c:if test="${searchMap.S_EXAMROUND == '14'}">selected</c:if>>14</option>
                <option value="15"  <c:if test="${searchMap.S_EXAMROUND == '15'}">selected</c:if>>15</option>
                <option value="16"  <c:if test="${searchMap.S_EXAMROUND == '16'}">selected</c:if>>16</option>
                <option value="17"  <c:if test="${searchMap.S_EXAMROUND == '17'}">selected</c:if>>17</option>
                <option value="18"  <c:if test="${searchMap.S_EXAMROUND == '18'}">selected</c:if>>18</option>
                <option value="19"  <c:if test="${searchMap.S_EXAMROUND == '19'}">selected</c:if>>19</option>
                <option value="20"  <c:if test="${searchMap.S_EXAMROUND == '20'}">selected</c:if>>20</option>
                <option value="21"  <c:if test="${searchMap.S_EXAMROUND == '21'}">selected</c:if>>21</option>
                <option value="22"  <c:if test="${searchMap.S_EXAMROUND == '22'}">selected</c:if>>22</option>
                <option value="23"  <c:if test="${searchMap.S_EXAMROUND == '23'}">selected</c:if>>23</option>
                <option value="24"  <c:if test="${searchMap.S_EXAMROUND == '24'}">selected</c:if>>24</option>
                <option value="25"  <c:if test="${searchMap.S_EXAMROUND == '25'}">selected</c:if>>25</option>
                <option value="26"  <c:if test="${searchMap.S_EXAMROUND == '26'}">selected</c:if>>26</option>
                <option value="27"  <c:if test="${searchMap.S_EXAMROUND == '27'}">selected</c:if>>27</option>
                <option value="28"  <c:if test="${searchMap.S_EXAMROUND == '28'}">selected</c:if>>28</option>
                <option value="29"  <c:if test="${searchMap.S_EXAMROUND == '29'}">selected</c:if>>29</option>
                <option value="30"  <c:if test="${searchMap.S_EXAMROUND == '30'}">selected</c:if>>30</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:110px;"   id="S_ISEXAMTYPEON" name="S_ISEXAMTYPEON">
                <option value="">온라인응시여부</option>
                <option value="1"  <c:if test="${searchMap.S_ISEXAMTYPEON == '1'}">selected</c:if>>예</option>
                <option value="0"  <c:if test="${searchMap.S_ISEXAMTYPEON == '0'}">selected</c:if>>아니요</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:120px;"   id="S_ISEXAMTYPEOFF" name="S_ISEXAMTYPEOFF">
                <option value="">오프라인응시여부</option>
                <option value="1"  <c:if test="${searchMap.S_ISEXAMTYPEOFF == '1'}">selected</c:if>>예</option>
                <option value="0"  <c:if test="${searchMap.S_ISEXAMTYPEOFF == '0'}">selected</c:if>>아니요</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:50px;" id="S_CLASSCODE" name="S_CLASSCODE">
            <option value="">직급</option>
            <c:forEach items="${registration_list}"  var="registration_list">
                <option value="${registration_list.CODE }" <c:if test="${searchMap.S_CLASSCODE == registration_list.CODE}">selected</c:if>>${registration_list.NAME }</option>
            </c:forEach>
            </select>
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input class="i_text" title="검색" type="text" id="pageRow" name="pageRow"  type="text" size="5" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow }"  onkeypress="fn_checkEnter()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
            </td>
        </tr>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:add();">등록</a></li>
        <li><a href="javascript:checkDelete();">삭제</a></li>
    </ul>
    <!--//버튼-->

    <!--테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="5%">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">
            <div class="item">
              <input id="allCheck" class="i_check" value="" type="checkbox" onclick="checkAll('allCheck', 'I_MOCKCODE')" />
            </div>
            </th>
            <th scope="col">No</th>
            <th scope="col">모의고사코드</th>
            <th scope="col">년</th>
            <th scope="col">회차</th>
            <th scope="col">시험일</th>
            <th scope="col">온라인<br>응시가능</th>
            <th scope="col">오프라인<br>응시가능</th>
            <th scope="col">시험<br>시간</th>
            <th scope="col">직급</th>
            <th scope="col">모의고사명</th>
            <th scope="col">접수기간</th>
            <th scope="col">응시<br>인원</th>
            <th scope="col">등록일</th>
            <th scope="col">개설<br>여부</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
            <tr>
                <td><div class="item">
                <input class="i_check" name="I_MOCKCODE"  value="${list.MOCKCODE }" type="checkbox" />
                </div></td>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${list.MOCKCODE}</td>
                <td>${list.EXAMYEAR}</td>
                <td>${list.EXAMROUND}</td>
                <td>${list.EXAMSTARTTIME}</td>
                <td>${(list.ISEXAMTYPEON == '1') ? '예' : '아니요' }</td>
                <td>${(list.ISEXAMTYPEOFF == '1') ? '예' : '아니요' }</td>
                <td>${list.EXAMTIME}분</td>
                <td>${list.NAME}</td>
                <td style="text-align:left;"><a href="javascript:edit('${list.MOCKCODE}')">${list.MOCKNAME}</a></td>
                <td>
                  <c:if test="${!empty list.RECEIPTSTARTTIME1}">
                  ${list.RECEIPTSTARTTIME1}/${list.RECEIPTSTARTTIME2} ${list.RECEIPTSTARTTIME3}시
                  </c:if>
                  ~<br>
                  <c:if test="${!empty list.RECEIPTENDTIME1}">
                  ${list.RECEIPTENDTIME1}/${list.RECEIPTENDTIME2} ${list.RECEIPTENDTIME3}시
                  </c:if>
                </td>
                <td><a href="javascript:goReq('${list.MOCKCODE }');">${list.STSCNT}</a></td>
                <td>${list.REG_DT}</td>
                <td>${list.USEFLAG}</td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="14">데이터가 존재하지 않습니다.</td>
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

  </div>
  </form>
  <!--//content -->