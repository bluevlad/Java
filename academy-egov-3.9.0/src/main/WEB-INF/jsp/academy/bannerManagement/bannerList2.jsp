<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
  <!--content -->
  <div id="content">
    <h2>● 운영자관리 > <strong>배너관리</strong></h2>
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="CODE_ID" name="CODE_ID" value="">
    <input type="hidden" id="NEW_BANNER" name="NEW_BANNER" value="Y">

    <input type="hidden" id="SEQ" name="SEQ" value="" />
    <input type="hidden" id="P_SEQ" name="P_SEQ" value="" />
    <table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td width="35%">
            <select name="SEARCHCATEGORY" id="SEARCHCATEGORY">
                <option value="">전체</option>
                <c:forEach items="${catekindlist}" var="item" varStatus="loop">
                    <option value="${item.CODE}" <c:if test="${params.SEARCHCATEGORY eq item.CODE }">selected="selected"</c:if>>${item.NAME}</option>
                </c:forEach>
                <option value="" disabled="disabled">----------------</option>
                <c:forEach items="${menukindlist}" var="item" varStatus="loop">
                    <option value="${item.CODE}" <c:if test="${params.SEARCHCATEGORY eq item.CODE }">selected="selected"</c:if>>${item.NAME}</option>
                </c:forEach>
                <option value="" disabled="disabled">----------------</option>
                    <option value="MM_000" <c:if test="${params.SEARCHCATEGORY eq 'MM_000' }">selected="selected"</c:if>>모바일홈</option>
            </select>
            <select name="SEARCHBANNERNO" id="SEARCHBANNERNO">
                <option value="">선택</option>
              <c:forEach items="${MAIN_BNNRs}" var="data" varStatus="status" >
                <option value="${data.CODE_VAL}">${data.CODE_NM}</option>
              </c:forEach>
            </select>
          <th width="25%">화면출력건수</th>
          <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow}" onkeypress="fn_RowNumCheck(this.value);">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
          </td>
        </tr>
    </table>

    <ul class="boardBtns">
    	<c:choose>
    		<c:when test="${params.SEARCHCATEGORY eq 'OM_001'}">
    			<li><a href="javascript:banner_new_add('MAIN_BNNR_ROOT');">신규배너등록</a></li>
    		</c:when>
    		<c:when test="${params.SEARCHCATEGORY eq 'FM_001'}">
    			<li><a href="javascript:banner_new_add('FMAIN_BNNR_ROOT');">신규배너등록</a></li>
    		</c:when>
    		<c:when test="${params.SEARCHCATEGORY eq 'MM_000'}">
    			<li><a href="javascript:banner_new_add('MOBL_BNNR_ROOT');">신규배너등록</a></li>
    		</c:when>
    		<c:when test="${params.SEARCHCATEGORY eq '081'}">
    			<li><a href="javascript:banner_new_add('SUBM_BNNR_ROOT');">신규배너등록</a></li>
    		</c:when>
    		<c:when test="${params.SEARCHCATEGORY eq '082'}">
    			<li><a href="javascript:banner_new_add('FSUBM_BNNR_ROOT');">신규배너등록</a></li>
    		</c:when>
    	</c:choose>
        <li><a href="javascript:addBanner();">등록</a></li>
    </ul>

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
      <tr>
        <th width="60">No</th>
        <th width="120">직렬</th>
        <th width="100">배너번호</th>
        <th width="120">배너유형</th>
        <th>배너구분</th>
        <th>미리보기</th>
        <th width="70">등록갯수</th>
      </tr>
        <c:if test="${not empty list}">
          <c:forEach var="item" items="${list}" varStatus="status">
              <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${item.CATEGORY_NM}</td>
                <td><a href="javascript:view('${ item.SEQ }');">${item.BANNER_NO}</a></td>
                <td>
                <select name="BANNER_TYPS" id="BANNER_TYPS" onchange="alert('등록된 배너가 모두 삭제됩니다.');">
                  <option value="I" <c:if test="${ item.BANNER_TYP eq 'I'}">selected="selected"</c:if>>이미지등록</option>
                  <option value="L" <c:if test="${ item.BANNER_TYP eq 'L'}">selected="selected"</c:if>>강좌선택</option>
                  <option value="B" <c:if test="${ item.BANNER_TYP eq 'B'}">selected="selected"</c:if>>게시판연결</option>
                  <option value="H" <c:if test="${ item.BANNER_TYP eq 'H'}">selected="selected"</c:if>>HTML</option>
                  <option value="P" <c:if test="${ item.BANNER_TYP eq 'P'}">selected="selected"</c:if>>교수</option>
                  <option value="T" <c:if test="${ item.BANNER_TYP eq 'T'}">selected="selected"</c:if>>모의고사</option>
                </select>
                </td>
                <td class="tdLeft"><a href="javascript:fn_goSList('${ item.SEQ }');">${item.BANNER_TITLE}</a></td>
                <td>
                	<c:if test="${item.CATEGORY_CD eq 'OM_001' or item.CATEGORY_CD eq 'FM_001'
                	or item.CATEGORY_CD eq '081' or item.CATEGORY_CD eq '082' or item.CATEGORY_CD eq 'MM_000'}">
                		<a href="javascript:fn_preview('${ item.BANNER_NO }','${item.SCREEN_GUBUN}','${item.CATEGORY_CD}')">보기</a>
                	</c:if>
                </td>
                <td>${item.LINK_COUNT}</td>
                <input type="hidden" id="BANNER_NO" name="BANNER_NO" value="${ item.SEQ }#${ item.BANNER_NO }" />
                <input type="hidden" id="BANNER_TYP" name="BANNER_TYP" value="${ item.BANNER_TYP }" />
              </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="6">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
    </table>
    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_save();">저장</a></li>
    </ul>
    </form>
</div>

<script type="text/javascript">
<c:choose><c:when test="${MENUTYPE eq 'OM_ROOT'}">
var ONOFF_DIV = "O";
</c:when><c:otherwise>
var ONOFF_DIV = "F";
</c:otherwise></c:choose>
$(document).ready(function(){
<c:if test="${params.SEARCHCATEGORY ne null and params.SEARCHCATEGORY ne ''}" >
    fn_callBannerCode();
</c:if>
    $('#SEARCHCATEGORY').on('change', function(){
        var gubun = 'S';
        if(($("select[name=SEARCHCATEGORY]").val()).indexOf('OM_001') > -1
           || ($("select[name=SEARCHCATEGORY]").val()).indexOf('FM_001') > -1 ) {
            gubun = 'M';
        } else if(($("select[name=SEARCHCATEGORY]").val()).indexOf('MM_000') > -1) {
            gubun = 'H';
        } else {
            gubun = 'S';
        }
        fn_getBannerCode(gubun);
    });
});

function fn_callBannerCode(){
    var sgubun = 'S';
    if(($("select[name=SEARCHCATEGORY]").val()).indexOf('OM_001') > -1
       || ($("select[name=SEARCHCATEGORY]").val()).indexOf('FM_001') > -1 ) {
        sgubun = 'M';
    } else if(($("select[name=SEARCHCATEGORY]").val()).indexOf('MM_000') > -1) {
        sgubun = 'H';
    } else {
        sgubun = 'S';
    }

    var params = '?ONOFF_DIV=' + ONOFF_DIV
               + '&SCREEN_GUBUN=' + sgubun
               + '&CATEGORY_CD=' + $("select[name=SEARCHCATEGORY]").val();
    $.ajax({
        type: "GET",
        url : '<c:url value="/bannerManagement/getBannerCode.json"/>'+params,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        async : false,
        success: function(RES) {
            if(RES.length  > 0){
                $('#SEARCHBANNERNO').find('option').remove();
                $('#SEARCHBANNERNO').find('option').end().append("<option value=''>선택</option>").val("");
                var sel_idx = 0;
                $.each(RES,function(idx, data){
                    var list = "";
                    if( data.CODE_CD == '${params.SEARCHBANNERNO}') {
                        sel_idx = (idx+1);
                    }
                    list +="<option value='" + data.CODE_CD +"' >" + data.CODE_NM + "</option>";
                    $('#SEARCHBANNERNO')
                        .find('option')
                        .end()
                        .append(list)
                        .val(data.CODE_CD);
                });
                $('#SEARCHBANNERNO').prop('selectedIndex',sel_idx);
            }else {
                alert("검색결과가 없습니다.")
                return;
            }
        },error: function(){
            alert("검색 실패");
            return;
        }
    });
}

function fn_getBannerCode(gubun){
    var params = '?ONOFF_DIV=' + ONOFF_DIV
               + '&SCREEN_GUBUN=' + gubun
               + '&CATEGORY_CD=' + $("select[name=SEARCHCATEGORY]").val();
    $.ajax({
        type: "GET",
        url : '<c:url value="/bannerManagement/getBannerCode.json"/>'+params,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        async : false,
        success: function(RES) {
            if(RES.length  > 0){
                $('#SEARCHBANNERNO').find('option').remove();
                $('#SEARCHBANNERNO').find('option').end().append("<option value=''>선택</option>").val("");
                $.each(RES,function(idx, data){
                    var list = "";
                    list +="<option value='" + data.CODE_CD +"'>" + data.CODE_NM + "</option>";
                    $('#SEARCHBANNERNO')
                        .find('option')
                        .end()
                        .append(list)
                        .val(data.CODE_CD);
                });
                $('#SEARCHBANNERNO').prop('selectedIndex',0);
            }else {
                alert("검색결과가 없습니다.")
                return;
            }
        },error: function(){
            alert("검색 실패");
            return;
        }
    });
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

    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerMgtList.do"/>').submit();
}
//등록
function addBanner() {
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerAdd.do"/>').submit();
}
//변경
function fn_save(){
    if(confirm("등록된 배너가 모두 삭제됩니다. 변경하시겠습니까?")){
        $("#searchFrm").attr("action", "<c:url value='/bannerManagement/change.do' />");
        $("#searchFrm").submit();
    }
}
// 회원 상세
function view(SEQ) {
    $("#SEQ").val(SEQ);
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerView.do"/>').submit();
}
//하위 배너 목록
function fn_goSList(SEQ) {
    $("#P_SEQ").val(SEQ);
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/bannerSList.do"/>').submit();
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
function fn_preview(no,flag,code){
	var pre_url = "<c:url value='/bannerManagement/banner_preview.pop2?'/>" ;
	var checkparam = "PREVIEW_BANNER_NO="+no;
	if(flag=='H'){
		var params  = 'width=461, height=800';
		 params += ', top=100, left='+(screen.width/2);
		 checkparam += "&IS_PREVIEW=5";
		var url = pre_url+checkparam ;
	  	window.open(url,'main_preview', params);
	}else{
		var params  = 'width='+screen.width;
		 params += ', height='+screen.height;
		 params += ', top=0, left=0';
		if(ONOFF_DIV=='O'){
			if(flag=='M'){
				 checkparam += "&IS_PREVIEW=1";
				var url = pre_url+checkparam ;
			  	window.open(url,'main_preview', params);
			}else{
				 checkparam += "&IS_PREVIEW=2";
				var url = pre_url+checkparam ;
			  	window.open(url,'main_preview', params);
			}
		}else{
			if(flag=='M'){
				 checkparam += "&IS_PREVIEW=3";
				var url = pre_url+checkparam ;
			  	window.open(url,'main_preview', params);
			}else{
				 checkparam += "&IS_PREVIEW=4";
				var url = pre_url+checkparam ;
			  	window.open(url,'main_preview', params);
			}
			
		}
	}
}
//배너 신규등록
function banner_new_add(code) {
	$("#CODE_ID").val(code);
    $("#searchFrm").attr("action", "<c:url value='/bannerManagement/pass_codeAdd.do' />");
    $("#searchFrm").submit();
}
</script>
  <!--//content -->