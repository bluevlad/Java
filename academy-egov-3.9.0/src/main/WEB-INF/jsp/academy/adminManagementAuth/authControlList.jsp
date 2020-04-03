<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
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
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        goList(1);
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

    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlList.do"/>').submit();
}

//등록
function addAuth() {
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlInsert.do"/>').submit();
}


function detailCommonCode(SYS_CD) {
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/commonCode.do"/>').submit();
}

//삭제
function checkDelete() {
    var tmp ="";
    $("input[name=SITE_ID]:checked").each(function (index){
        tmp += "'"+$(this).val()+"'" + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }
    if(confirm("삭제하시겠습니까?")) {
        //$("#deleteIds").val($('input:checkbox[name^=ITEMID]:checked').fieldValue());
        var last = tmp.lastIndexOf(',');
        tmp = tmp.substr(0,last)
        $("#deleteIds").val(tmp);
        $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlCheckDelete.do"/>').submit();
    }
}

// 코드 상세
function view(SITE_ID) {
    $("#DETAIL_SITE_ID").val(SITE_ID);
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlDetail.do"/>').submit();
}

function fn_authMenuOn(SITE_ID) {
    $("#DETAIL_SITE_ID").val(SITE_ID);
    $("#MENU_ONOFF").val("ON");
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlMenuList.do"/>').submit();
}
function fn_authMenuOff(SITE_ID) {
    $("#DETAIL_SITE_ID").val(SITE_ID);
    $("#MENU_ONOFF").val("OFF");
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlMenuList.do"/>').submit();
}
function fn_authMenuTest(SITE_ID) {
    $("#DETAIL_SITE_ID").val(SITE_ID);
    $("#MENU_ONOFF").val("TEST");
    $('#searchFrm').attr('action','<c:url value="/adminManagementAuth/authControlMenuList.do"/>').submit();
}
</script>
</head>
  <!--content -->
  <div id="content">
    <h2>● 운영자 관리 > <strong>권한관리</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="USERID" name="USERID" value="${AdmUserInfo.USERID}" />
    <input type="hidden" id="DETAIL_SITE_ID" name="DETAIL_SITE_ID" value="" />
    <input type="hidden" id="MENU_ONOFF" name="MENU_ONOFF" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>
                <select  id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="SITE_ID"  <c:if test="${searchMap.SEARCHTYPE == 'SITE_ID'}">selected</c:if>>사이트ID</option>
                    <option value="SITE_NM"  <c:if test="${searchMap.SEARCHTYPE == 'SITE_NM'}">selected</c:if>>사이트명</option>
                </select>
                <input  title="검색" size="40" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  value="${searchMap.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input class="i_text"  title="검색" type="text" id="pageRow" name="pageRow"   maxlength="2"  size="5" onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
      </table>
    </form>
    <!--//테이블-->
    <!--버튼-->
        <ul class="boardBtns">
            <li><a href="javascript:addAuth()">등록</a></li>
            <li><a href="javascript:checkDelete()">삭제</a></li>
        </ul>
                <!-- <div style="float:left; width:100%; text-align:right; margin-top:30px;">
                <span class="btn_pack medium"><button type="button" onclick="javascript:addAuth()">등록</button></span>&nbsp;
                <span class="btn_pack medium"><button type="button" onclick="javascript:checkDelete()">삭제</button></span>
                </div> -->
                <!--//버튼-->

    <!--버튼-->
        <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <!--//버튼-->
    <!--테이블-->
          <table class="table02">
                <tr>
                  <th width="85">
                      <input id="allCheck"  value="" type="checkbox" onclick="checkAll('allCheck', 'SITE_ID')" />No
                  </th>
                  <th>사이트ID</th>
                  <th>사이트</th>
                  <th>관리메뉴</th>
                  <th>상태</th>
                  <th width="15%">등록일</th>
                  <th width="25%">등록</th>
                </tr>
              <c:if test="${not empty list}">
                  <c:forEach items="${list}" var="list" varStatus="status">
                        <tr>
                          <td>
                                <input  name="SITE_ID"  value="${list.SITE_ID}" type="checkbox" />
                                ${totalCount - (( currentPage - 1) * pageRow) - status.index}
                          </td>
                          <td><a href="javascript:view('${list.SITE_ID}')">${list.SITE_ID}</a></td>
                          <td>${list.SITE_NM}</td>
                          <td>${list.ONOFF_DIV_NM}</td>
                          <td>
                                <c:if test="${list.ISUSE =='Y'}">활성</c:if>
                                <c:if test="${list.ISUSE =='N'}">비활성</c:if>
                          </td>
                          <td>${list.REG_DT}</td>
                          <td>
                                <c:if test="${list.ONOFF_DIV =='A'}">
                                    <input type="button" onclick='javascript:fn_authMenuOn("${list.SITE_ID}");' value="온라인" />
                                    <input type="button" onclick='javascript:fn_authMenuOff("${list.SITE_ID}");' value="오프라인" />
                                    <input type="button" onclick='javascript:fn_authMenuTest("${list.SITE_ID}");' value="모의고사" />
                                </c:if>
                                <c:if test="${list.ONOFF_DIV =='O'}">
                                    <input type="button" onclick='javascript:fn_authMenuOn("${list.SITE_ID}");' value="온라인" />
                                </c:if>
                                <c:if test="${list.ONOFF_DIV =='F'}">
                                    <input type="button" onclick='javascript:fn_authMenuOff("${list.SITE_ID}");' value="오프라인" />
                                </c:if>
                                <c:if test="${list.ONOFF_DIV =='T'}">
                                    <input type="button" onclick='javascript:fn_authMenuTest("${list.SITE_ID}");' value="모의고사" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty list}">
                    <tr bgColor=#ffffff align=center>
                        <td colspan="8">데이터가 존재하지 않습니다.</td>
                    </tr>
                </c:if>
            </table>
          <!--//테이블-->

        <!-- paginate-->
            <c:if test="${not empty list}">
                <c:out value="${pagingHtml}" escapeXml="false" />
            </c:if>
       <!--//paginate-->
  </div>
  <!--//content -->