<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
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

    $('#searchFrm').attr('action','<c:url value="/adminManagementCode/commonCodeList.do"/>').submit();
}

//등록
function addCommonCode() {
    $('#searchFrm').attr('action','<c:url value="/adminManagementCode/commonCodeInsert.do"/>').submit();
}


function detailCommonCode(SYS_CD) {
    $('#searchFrm').attr('action','<c:url value="/adminManagementCode/commonCode.do"/>').submit();
}

//삭제
function checkDelete() {
    var tmp ="";
    $("input[name=CODE_NO]:checked").each(function (index){
        tmp += $(this).val() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }

    //alert("tmp:"+tmp);

    if(confirm("삭제하시겠습니까?")) {
        //$("#deleteIds").val($('input:checkbox[name^=ITEMID]:checked').fieldValue());
        var last = tmp.lastIndexOf(',');
        tmp = tmp.substr(0,last)
        $("#deleteIds").val(tmp);
        $('#searchFrm').attr('action','<c:url value="/adminManagementCode/commonCodeCheckDelete.do"/>').submit();
    }
}

// 코드 상세
function view(CODE_NO) {
    $("#DETAIL_CODE_NO").val(CODE_NO);
    $('#searchFrm').attr('action','<c:url value="/adminManagementCode/commonCodeDetail.do"/>').submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 운영자 관리 > <strong>코드관리</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
    <input type="hidden" id="deleteIds" name="deleteIds">
    <input type="hidden" id="USERID" name="USERID" value="${AdmUserInfo.USERID}" />
    <input type="hidden" id="DETAIL_CODE_NO" name="DETAIL_CODE_NO" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="CODE"  <c:if test="${searchMap.SEARCHTYPE == 'CODE'}">selected</c:if>>공통코드</option>
                    <option value="CODENM"  <c:if test="${searchMap.SEARCHTYPE == 'CODENM'}">selected</c:if>>공통코드명</option>
                    <option value="SETTINGCODE"  <c:if test="${searchMap.SEARCHTYPE == 'SETTINGCODE'}">selected</c:if>>설정코드명</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name=SEARCHKEYWORD  type="text" size="40"  value="${searchMap.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
      </table>
    </form>
    <!--//테이블-->
    <!--버튼-->

                <ul class="boardBtns">
                  <li><a href="javascript:addCommonCode();">등록</a></li>
                    <li><a href="javascript:checkDelete();">삭제</a></li>
                </ul>
   <!--              <div style="float:left; width:100%; text-align:right; margin-top:30px;">
                <span class="btn_pack medium"><button type="button" onclick="javascript:addCommonCode()">등록</button></span>&nbsp;
                <span class="btn_pack medium"><button type="button" onclick="javascript:checkDelete()">삭제</button></span>
                </div> -->
                <!--//버튼-->
    <!--테이블-->
    <!--//테이블-->
    <!--버튼-->
                <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
                <%-- <div style="float:left; width:100%; text-align:right; margin-top:30px;">
                    <span ><strong>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</strong></span>&nbsp;
                </div> --%>
                <!--//버튼-->
    <!--테이블-->

            <table class="table02">
                <tr>
                  <th width="85">
                      <input id="allCheck"  value="" type="checkbox" onclick="checkAll('allCheck', 'CODE_NO')" />No
                  </th>
                  <th>공통코드명</th>
                  <th>공통코드</th>
                  <th>설정코드명</th>
                  <th>설정코드</th>
                  <th>설정값</th>
                  <th>상태</th>
                  <th>등록일</th>
                </tr>
              <tbody>
              <c:if test="${not empty list}">
                  <c:forEach items="${list}" var="list" varStatus="status">
                        <tr>
                          <td>
                                <input  name="CODE_NO"  value="${list.CODE_NO}" type="checkbox" />
                                ${totalCount - (( currentPage - 1) * pageRow) - status.index}
                          </td>
                          <td>${list.SYS_NM}</td>
                          <td>${list.SYS_CD}</td>
                          <td><a href="javascript:view('${list.CODE_NO}')">${list.CODE_NM}</a></td>
                          <td>${list.CODE_CD}</td>
                          <td>${list.CODE_VAL}</td>
                          <td>${list.ISUSE}</td>
                          <td>${list.REG_DT}</td>
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

        <!-- paginate-->
            <c:if test="${not empty list}">
                <c:out value="${pagingHtml}" escapeXml="false" />
            </c:if>
       <!--//paginate-->
    </div>
  <!--//content -->