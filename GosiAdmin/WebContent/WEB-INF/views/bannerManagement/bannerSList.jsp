<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/libs/jquery/jquery.jui_dropdown.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/libs/jquery/jquery.jui_dropdown.min.js'/>"></script>

<script type="text/javascript">
$(function() {
  launchOnMouseEnter: true;  
  $("#menu_drop1").jui_dropdown({
    launcher_id: 'launcher1',
    launcher_container_id: 'launcher1_container',
    menu_id: 'menu1',
    containerClass: 'container1',
    menuClass: 'menu1',
    onSelect: function(event, data) {
      if(data.index == 1) {
        if($("[id^='chk_isuse1']:checked").length > 0) {
            $(":checkbox[id='chk_isuse2']").attr("checked", false);
            $('#CHK_ISUSE').val("Y");
        } else {
            if($('#CHK_ISUSE').val() == "Y") {
                $('#CHK_ISUSE').val("");
            }
        }
      } else if(data.index == 2) {
        if($("[id^='chk_isuse2']:checked").length > 0) {
            $(":checkbox[id='chk_isuse1']").attr("checked", false);
            $('#CHK_ISUSE').val("N");
        } else {
            if($('#CHK_ISUSE').val() == "N") {
                $('#CHK_ISUSE').val("");
            }
        }
      }
    }
  });
});
$(document).ready(function(){
	$("#select_all").change(function(){
		if($(this).is(":checked")==true) {
  		  $(":checkbox[name='SEL_ITEM_IDX']").attr("checked", true);
  	  }else{
  		  $(":checkbox[name='SEL_ITEM_IDX']").attr("checked", false);
  	  }
	});
});
//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {
        if (val.match(/^\d+$/gi) == null) {
            $('#subPageRow').val("");
            $('#subPageRow').focus();
            return;
        } else {
            if (val < 1) {
                $('#subPageRow').val("");
                $('#subPageRow').focus();
                return;
            }
        }
    }
}
//엔터키 검색
function fn_checkEnter(){
    $('#searchText').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
    $('#subPageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}
//검색
function goList(page) {
    var searchText = $("#searchText").val();
    $("#SEARCHTEXT").val(searchText);

    if(typeof(page) == "undefined") $("#subCurrentPage").val(1);
    else $("#subCurrentPage").val(page);

    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerSList.do"/>').submit();
}
//등록
function addBanner() {
    var searchText = $("#searchText").val();
    $("#SEARCHTEXT").val(searchText);

    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerInsertView.do"/>').submit();
}
//삭제
function fn_Delete(){
    var searchText = $("#searchText").val();
    $("#SEARCHTEXT").val(searchText);

    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
            $("#searchFrm").attr("action", "<c:url value='/bannerManagement/bannerCheckDelete.do' />");
            $("#searchFrm").submit();
        }
    }else{
        alert("선택된 항목이 없습니다");
        return;
    }
}
// 상세
function view(SEQ) {
    var searchText = $("#searchText").val();
    $("#SEARCHTEXT").val(searchText);

    $("#SEQ").val(SEQ);
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerDetail.do"/>').submit();
}
// 수정
function fn_update() {
    var searchText = $("#searchText").val();
    $("#SEARCHTEXT").val(searchText);

    if(confirm("변경 사항을 저장하시겠습니까?")){
        $('#searchFrm').attr('action','<c:url value="/bannerManagement/update_order.do"/>').submit();
    }
}
//하위 배너 목록
function fn_goList() {
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerMgtList.do"/>').submit();
}
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        goList(1);
        return;
    }
    if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#subPageRow").val("${params.subPageRow}");
    }
}
</script>

  <!--content -->
  <div id="content">
    <h2>● 사이트관리 > <strong>배너관리</strong></h2>
    <h2>${view[0].CATEGORY_NM} | <strong>${view[0].BANNER_TITLE}</strong></h2>
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" >
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
    <input type="hidden" id="subCurrentPage" name="subCurrentPage" value="${params.subCurrentPage}">

    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
    <input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>

    <input type="hidden" id="SEQ" name="SEQ" value="" />
    <input type="hidden" id="P_SEQ" name="P_SEQ" value="${params.P_SEQ}" />
    <input type="hidden" id="CHK_ISUSE" name="CHK_ISUSE" value="" />
    <table class="table01">
        <tr>
          <th width="10%">배너명</th>
          <td width="30%">
          <input type="text" id="searchText" name="searchText" class="i_text" title="배너명" size="35" value="${params.SEARCHTEXT}" onKeyPress="fn_checkEnter()" />
          </td>
          <th width="10%">배너순서</th>
          <td width="10%">
          <input type="text" id="SEARCHROLIDX" name="SEARCHROLIDX" title="검색" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${params.SEARCHROLIDX}" onkeypress="fn_RowNumCheck(this.value);">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="25%">
                <input type="text" title="검색" id="subPageRow" name="subPageRow" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${params.subPageRow }" onkeypress="fn_RowNumCheck(this.value);">
                <input type="button" id="textfield3" name="textfield3" value="검색" onclick="goList(1)"  >
          </td>
        </tr>
        <tr>
        	<th>적용구분</th>
        	<td colspan="5">
	        	<select name="SEARCHISUSE" id="SEARCHISUSE">
	                <option value="">선택</option>
	                <option value="Y" <c:if test="${params.SEARCHISUSE == 'Y'}">selected = "selected"</c:if>>적용</option>
	                <option value="N" <c:if test="${params.SEARCHISUSE == 'N'}">selected = "selected"</c:if>>미적용</option>
	            </select>
            </td>
        </tr>
    </table>

    <p class="pInto01">&nbsp;</p>
    <input type="checkbox" id="select_all" name="select_all" style="margin-left: 2"> 전체선택
    <table class="table02">
      <tr>
        <th width="60">No</th>
        <th>배너명</th>
        <th width="90">시작일</th>
        <th width="90">종료일</th>
        <th width="90">등록일</th>
        <th width="120">
            <div id="menu_drop1">
              <div id="launcher1_container">
                <div id="launcher1" onclick="javascript:void(0); return false;">적용여부</div>
              </div>
              <ul id="menu1">
                <li id="opt_1">
                    <div style="display: table-row">
                      <input type="checkbox" id="chk_isuse1" name="chk_isuse1" style="margin-left: 2">
                      <label for="chk_isuse1" style="display: table-cell; text-align: left; width: 100%;"> 적용</label>
                    </div>
                </li>
                <li id="opt_2">
                    <div style="display: table-row">
                      <input type="checkbox" id="chk_isuse2" name="chk_isuse2" style="margin-left: 2">
                      <label for="chk_isuse2" style="display: table-cell; text-align: left; width: 100%;"> 미적용</label>
                    </div>
                </li>
              </ul>
            </div>
        </th>
        <th width="120">배너순서</th>
        <th width="70">클릭카운트</th>
      </tr>
        <c:if test="${not empty list}">
          <c:forEach var="item" items="${list}" varStatus="status">
              <tr>
                <td>${totalCount - (( subCurrentPage - 1) * subPageRow) - status.index}</td>
                <td class="tdLeft"><a href="javascript:view('${ item.SEQ }');">${item.BANNER_SUBTITLE}</a></td>
                <td>${item.BANNER_SDT}</td>
                <td>${item.BANNER_EDT}</td>
                <td>${item.UPD_DT}</td>
                <td class="txt04">
                	<input type="checkbox" id="SEL_ITEM_IDX" name="SEL_ITEM_IDX" value="${item.SEQ}_${item.ROL_IDX}" style="margin-left: 2">
                    <c:if test="${ item.ISUSE eq 'N' }">미적용</c:if>
                    <c:if test="${ item.ISUSE eq 'Y' }">적용</c:if>
                </td>
                <td><input type="text" id="ROL_IDX" name="ROL_IDX" size="2" maxlength="2" value="${item.ROL_IDX}"/></td>
                <td>${item.CLICK_CNT}</td>
                <input type="hidden" id="ITEM_IDX" name="ITEM_IDX" value="${item.SEQ}"/>
              </tr>
          </c:forEach>
        </c:if>

        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="8">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
    </table>
    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
    <!--//paginate-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_update();">저장</a></li>
        <li><a href="javascript:addBanner();">등록</a></li>
        <li><a href="javascript:fn_goList();">목록</a></li>
    </ul>

    </form>
</div>
  <!--//content -->