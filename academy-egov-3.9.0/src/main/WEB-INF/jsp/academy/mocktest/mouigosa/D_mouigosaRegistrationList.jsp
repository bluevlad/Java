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
    $('#SEARCH_TXT').keyup(function(e)  {
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

    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/D_mouigosaRegistrationList.do"/>').submit();
}

//등록
function add() {
    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/D_createRegistrationMouigosa.do"/>').submit();
}

//수정
function edit(MOCKCODE) {
    $("#MOCKCODE").val(MOCKCODE);
    $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/updateRegistrationMouigosa.do"/>?MOCKCODE='+ MOCKCODE).submit();
}

//삭제
function checkDelete() {
    var tmp ="";
    $("input[name=I_ITEMID]:checked").each(function (index){
        tmp += $(this).val() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }

    if(confirm("삭제하시겠습니까?")) {
        $("#deleteIds").val(tmp);		
        $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/D_deleteRegistrationMouigosa.do"/>').submit();
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

function fn_update(itemid) {
	 $("#ITEMID").val(itemid);
	 $('#searchFrm').attr('action','<c:url value="/mouigosa/reg/D_updateRegistrationMouigosa.do"/>?ITEMID='+ itemid).submit();
}

function PrintPopup(ITEMID) {
    var go_url = '<c:url value="/mouigosa/reg/D_printMouigosa.pop"/>?'
                + 'ITEMID=' + ITEMID;

    window.open(go_url, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=600,height=700');
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
    <input type="hidden" id="ITEMID" name="ITEMID">
    <input type="hidden" id="LECCODE" name="LECCODE" VALUE="Y">

    <!--content -->
    <div id="content">
      <h2>● 모의고사등록 > <strong>동형모의고사등록</strong></h2>

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
             <select id="SEARCH_TYPE" name="SEARCH_TYPE" class="must">
             <option value=""  <c:if test="${searchMap.SEARCH_TYPE == ''}">selected</c:if>>전체검색</option>
             <option value="lecNo"  <c:if test="${searchMap.SEARCH_TYPE == 'lecNo'}">selected</c:if>>강의코드</option>
             <option value="itemnm"  <c:if test="${searchMap.SEARCH_TYPE == 'itemnm'}">selected</c:if>>모의고사명</option>

            </select>
            <input type="text" name="SEARCH_TXT" id="SEARCH_TXT"  style="width: 55%" value="${searchMap.SEARCH_TXT }" onkeypress="fn_checkEnter()">


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
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
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
              <input id="allCheck" class="i_check" value="" type="checkbox" onclick="checkAll('allCheck', 'I_ITEMID')" />
            </div>
            </th>
            <th scope="col">No</th>
            <th scope="col">코드번호</th>
            <th scope="col">강의코드</th>
            <th scope="col">모의고사명</th>
            <th scope="col">문제보기</th>           
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
            <tr>
                <td>
                	<div class="item">
                		<input class="i_check" name="I_ITEMID"  value="${list.ITEMID }" type="checkbox" />
                	</div>
                </td>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${list.ITEMID }</td>
                <td>${list.LECCODE}</td>
                <td><a href="javascript:fn_update('${list.ITEMID }');">${list.ITEMNM}</a></td>
                <td><a href="#" onclick="PrintPopup('${list.ITEMID}')">문제</a></td>
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