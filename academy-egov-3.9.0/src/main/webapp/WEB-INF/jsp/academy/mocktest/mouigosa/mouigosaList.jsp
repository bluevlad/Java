<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

var S_EXAMYEAR = "${searchMap.S_EXAMYEAR}";
var S_EXAMROUND = "${searchMap.S_EXAMROUND}";
var S_searchFlag = "${searchMap.S_searchFlag}";
var S_searchKeyWord = "${searchMap.S_searchKeyWord}";
var S_currentPage = "${searchMap.S_currentPage}";
var S_pageRow = "${searchMap.S_pageRow}";

window.onload = function () {
}

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            $('#S_pageRow').val("");
            $('#S_pageRow').focus();
            return;
        } else {
            if (val < 1) {
                $('#S_pageRow').val("");
                $('#S_pageRow').focus();
                return;
            }
        }
    }
}

//엔터키 검색
function fn_checkEnter(){
    $('#S_searchKeyWord').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });

    $('#S_pageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}

//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#S_currentPage").val(1);
    else $("#S_currentPage").val(page);
    $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaList.do"/>').submit();
}

//등록
function add() {
    //window.open('<c:url value="/mouigosa/createMouigosa.pop"/>', '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=335,height=320');
    $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaQuestionList.do"/>').submit();
}

//수정
//function editPopup(ITEMID) {
//    window.open('<c:url value="/mouigosa/updateMouigosa.pop"/>?ITEMID=' + ITEMID + '&S_EXAMYEAR=' + S_EXAMYEAR + '&S_EXAMROUND=' + S_EXAMROUND + '&S_searchFlag=' + S_searchFlag + '&S_searchKeyWord=' + escape(encodeURIComponent(S_searchKeyWord)) + '&currentPage=' + S_currentPage + '&pageRow=' + S_pageRow, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=335,height=320');
//}

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
        //$("#deleteIds").val($('input:checkbox[name^=ITEMID]:checked').fieldValue());
        $("#deleteIds").val(tmp);
        $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaDelete.do"/>').submit();
    }
}

// 문제 목록
function view(ITEMID) {
    $("#ITEMID").val(ITEMID);
    $('#searchFrm').attr('action','<c:url value="/mouigosa/mouigosaQuestionList.do"/>').submit();
}

//프린트 팝업
function PrintPopup(ITEMID, QUESTIONREGISTRATIONOPTION) {
    var go_url = '<c:url value="/mouigosa/printMouigosa.pop"/>?'
                + 'ITEMID=' + ITEMID
                + '&QUESTIONREGISTRATIONOPTION=' + QUESTIONREGISTRATIONOPTION 
                + '&sts=1';

    window.open(go_url, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=600,height=700');
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 모의고사등록 > <strong>과목별 문제등록</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="get" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="<c:out  value="${TOP_MENU_ID}"/>" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="<c:out  value="${MENU_ID}"/>" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="<c:out  value="${MENUTYPE}"/>" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="<c:out  value="${L_MENU_NM}"/>" />
    <input type="hidden" id="S_currentPage" name="S_currentPage"  value="<c:out  value="${searchMap.S_currentPage}"/>">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="USER_ID" name="USER_ID" value="${AdmUserInfo.USER_ID}" />
    <input type="hidden" id="ITEMID" name="ITEMID" value="" />
    <table class="table01">
        <caption></caption>
        <colgroup>
        <col width="15%">
        <col width="*">
        <col width="15%">
        <col width="30%">
        </colgroup>
        <tr>
            <th scope="col">검색</th>
            <td scope="col" style="text-align:left;">
                <input type="hidden" name="_method" value="get">
                <select style="width:80px;"   id="S_EXAMYEAR" name="S_EXAMYEAR">
                    <option value="">년도</option>
                    <option value="2016"  <c:if test="${searchMap.S_EXAMYEAR == '2016'}">selected</c:if>>2016</option>
                    <option value="2017"  <c:if test="${searchMap.S_EXAMYEAR == '2017'}">selected</c:if>>2017</option>
                    <option value="2018"  <c:if test="${searchMap.S_EXAMYEAR == '2018'}">selected</c:if>>2018</option>
                    <option value="2019"  <c:if test="${searchMap.S_EXAMYEAR == '2019'}">selected</c:if>>2019</option>
                </select>
                &nbsp;
                <select style="width:80px;"   id="S_EXAMROUND" name="S_EXAMROUND">
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
                &nbsp;
                 <select style="width:100px;"  id="S_searchFlag" name="S_searchFlag">
                    <option value="1" <c:if test="${searchMap.S_searchFlag == '1'}">selected</c:if>>과목</option>
                    <option value="2" <c:if test="${searchMap.S_searchFlag == '2'}">selected</c:if>>강사</option>
                </select>
                <input class="i_text"  title="검색" type="text" id="S_searchKeyWord" name="S_searchKeyWord"  type="text" style="width:160px;"  value="${searchMap.S_searchKeyWord}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input class="i_text" title="검색" type="text" id="S_pageRow" name="S_pageRow"  type="text" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.S_pageRow }" onkeypress="fn_checkEnter()">
                <input type="button" onclick="goList(1)" value="검색" />
            </td>
        </tr>
    </table>
    </form>
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
        <caption>
        </caption>
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
                <!-- 
                <th scope="col">모의고사코드</th>
                 -->
                <th scope="col">년</th>
                <th scope="col">회차</th>
                <th scope="col">과목</th>
                <th scope="col">강사</th>
                <th scope="col">모의고사 과목명</th>
                <th scope="col">지문수</th>
                <th scope="col">ON/OFF</th>
                <th scope="col">등록일</th>
                <th scope="col">개설여부</th>
                <th scope="col">문제보기</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
            <tr>
                <td><div class="item">
                  <input class="i_check" name="I_ITEMID"  value="${list.ITEMID }" type="checkbox" />
                </div></td>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <!-- 
                <td>${list.MOCKCODE}</td>
                 -->
                <td>${list.EXAMYEAR}</td>
                <td>${list.EXAMROUND}</td>
                <td>${list.SUBJECT_NM}</td>
                <td>${list.PROF_NM}</td>
                <td style="text-align:left;"><a href="javascript:view('${list.ITEMID}')">${list.EXAMYEAR}년 ${list.EXAMROUND}회차 ${list.SUBJECT_NM} (${list.PROF_NM}-${list.CODE_NM})</a></td>
                <td>${list.ENTRYNUM}</td>
                <td>${list.ON_CNT}/${list.OFF_CNT}</td>
                <td>${list.REG_DT}</td>
                <td>${list.OPENSTATE}</td>
                <td><a href="#" onclick="PrintPopup('${list.ITEMID}','${list.QUESTIONREGISTRATIONOPTION}')">문제</a></td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="13">데이터가 존재하지 않습니다.</td>
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
  <!--//content -->