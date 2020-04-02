<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

var S_EXAMYEAR = "${params.S_EXAMYEAR}";
var S_EXAMROUND = "${params.S_EXAMROUND}";
var S_ISEXAMTYPEON = "${params.S_ISEXAMTYPEON}";
var S_ISEXAMTYPEOFF = "${params.S_ISEXAMTYPEOFF}";
var S_CLASSCODE = "${params.S_CLASSCODE}";
var currentPage = "${params.currentPage}";
var pageRow = "${params.pageRow}";

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
    <input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="MOCKCODE" name="MOCKCODE">
    <input type="hidden" id="LECCODE" name="LECCODE" VALUE="Y">

    <!--content -->
    <div id="content">
      <h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

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
                <c:forEach var="i" begin="2016" end="2020" step="1">
                <option value="${i}" <c:if test="${params.S_EXAMYEAR == i}">selected</c:if>><c:out value="${i}"/></option>
				</c:forEach>
            </select>
            &nbsp;&nbsp;
            <select style="width:50px;"   id="S_EXAMROUND" name="S_EXAMROUND">
                <option value="">회차</option>
                <c:forEach var="i" begin="1" end="30" step="1">
                <option value="${i}" <c:if test="${params.S_EXAMROUND == i}">selected</c:if>><c:out value="${i}"/></option>
				</c:forEach>
            </select>
            &nbsp;&nbsp;
            <select style="width:110px;"   id="S_ISEXAMTYPEON" name="S_ISEXAMTYPEON">
                <option value="">온라인응시여부</option>
                <option value="1"  <c:if test="${params.S_ISEXAMTYPEON == '1'}">selected</c:if>>예</option>
                <option value="0"  <c:if test="${params.S_ISEXAMTYPEON == '0'}">selected</c:if>>아니요</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:120px;"   id="S_ISEXAMTYPEOFF" name="S_ISEXAMTYPEOFF">
                <option value="">오프라인응시여부</option>
                <option value="1"  <c:if test="${params.S_ISEXAMTYPEOFF == '1'}">selected</c:if>>예</option>
                <option value="0"  <c:if test="${params.S_ISEXAMTYPEOFF == '0'}">selected</c:if>>아니요</option>
            </select>
            &nbsp;&nbsp;
            <select style="width:50px;" id="S_CLASSCODE" name="S_CLASSCODE">
            <option value="">구분</option>
            <c:forEach items="${registration_list}"  var="registration_list">
                <option value="${registration_list.CODE }" <c:if test="${params.S_CLASSCODE == registration_list.CODE}">selected</c:if>>${registration_list.NAME }</option>
            </c:forEach>
            </select>
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input class="i_text" title="검색" type="text" id="pageRow" name="pageRow"  type="text" size="5" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }"  onkeypress="fn_checkEnter()">
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
            <th scope="col">응시인원<br>제출인원</th>
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
                <td><a href="javascript:goReq('${list.MOCKCODE }');">${list.STSCNT}</a><br>${list.RESCNT}</td>
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