<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
  <!--content -->
  <div id="content">
    <h2>● 사이트관리 > <strong>ON-AIR 등록</strong></h2>
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="SEQ" name="SEQ" value="" />

    <table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td width="55%">
            <select name="SEARCHYEAR" id="SEARCHYEAR">
                <option value="" <c:if test="${params.SEARCHYEAR eq '' }">selected="selected"</c:if>>----년</option>
                <option value="2016" <c:if test="${params.SEARCHYEAR eq '2016' }">selected="selected"</c:if>>2016년</option>
                <option value="2017" <c:if test="${params.SEARCHYEAR eq '2017' }">selected="selected"</c:if>>2017년</option>
                <option value="2018" <c:if test="${params.SEARCHYEAR eq '2018' }">selected="selected"</c:if>>2018년</option>
                <option value="2019" <c:if test="${params.SEARCHYEAR eq '2019' }">selected="selected"</c:if>>2019년</option>
                <option value="2020" <c:if test="${params.SEARCHYEAR eq '2020' }">selected="selected"</c:if>>2020년</option>
            </select>
            <select name="SEARCHMONTH" id="SEARCHMONTH">
                <option value="">--월</option>
                <option value="01" <c:if test="${params.SEARCHMONTH eq '01' }">selected="selected"</c:if>>01월</option>
                <option value="02" <c:if test="${params.SEARCHMONTH eq '02' }">selected="selected"</c:if>>02월</option>
                <option value="03" <c:if test="${params.SEARCHMONTH eq '03' }">selected="selected"</c:if>>03월</option>
                <option value="04" <c:if test="${params.SEARCHMONTH eq '04' }">selected="selected"</c:if>>04월</option>
                <option value="05" <c:if test="${params.SEARCHMONTH eq '05' }">selected="selected"</c:if>>05월</option>
                <option value="06" <c:if test="${params.SEARCHMONTH eq '06' }">selected="selected"</c:if>>06월</option>
                <option value="07" <c:if test="${params.SEARCHMONTH eq '07' }">selected="selected"</c:if>>07월</option>
                <option value="08" <c:if test="${params.SEARCHMONTH eq '08' }">selected="selected"</c:if>>08월</option>
                <option value="09" <c:if test="${params.SEARCHMONTH eq '09' }">selected="selected"</c:if>>09월</option>
                <option value="10" <c:if test="${params.SEARCHMONTH eq '10' }">selected="selected"</c:if>>10월</option>
                <option value="11" <c:if test="${params.SEARCHMONTH eq '11' }">selected="selected"</c:if>>11월</option>
                <option value="12" <c:if test="${params.SEARCHMONTH eq '12' }">selected="selected"</c:if>>12월</option>                
            </select>
            <select name="SEARCHISUSE" id="SEARCHISUSE">
                <option value="">진행여부</option>              
                <option value="Y" <c:if test="${params.SEARCHISUSE eq 'Y' }">selected="selected"</c:if>>진행</option>
                <%-- <option value="N" <c:if test="${params.SEARCHISUSE eq 'N' }">selected="selected"</c:if>>예정</option> --%>
                <option value="E" <c:if test="${params.SEARCHISUSE eq 'E' }">selected="selected"</c:if>>종료</option>
            </select>
            <select name="SEARCHKEYWORD" id="SEARCHKEYWORD">
                <option value="" <c:if test="${params.SEARCHKEYWORD eq '' }">selected="selected"</c:if>>전체</option>              
                <option value="ST" <c:if test="${params.SEARCHKEYWORD eq 'ST' }">selected="selected"</c:if>>강좌명</option>
                <option value="TN" <c:if test="${params.SEARCHKEYWORD eq 'TN' }">selected="selected"</c:if>>강사명</option>
            </select>
            <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT }" size="50" title="검색어" onkeypress="fn_RowNumCheck(this.value);">            
          <th width="15%">화면출력건수</th>
          <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow}" onkeypress="fn_RowNumCheck(this.value);">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
          </td>
        </tr>
    </table>

    <ul class="boardBtns">
        <li><a href="javascript:addBanner();">등록</a></li>
    </ul>

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
      <tr>
        <th width="60">No</th>
        <th width="60">월</th>
        <th width="210">일</th>
        <th width="100">노출시간</th>
        <th width="70">강사명</th>
        <th>강좌명</th>
        <th width="70">진행여부</th>
        <th width="70">등록일</th>
        <th width="70">등록자</th>
      </tr>
        <c:if test="${not empty list}">
          <c:forEach var="item" items="${list}" varStatus="status">
              <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${item.MONTH }월</td>
                <td>
					<c:forEach var="data" items="${list2}" varStatus="status">
						<c:if test="${data.SEQ eq item.SEQ }">${data.DAY } 일</c:if>
					</c:forEach>
				</td>
                <td>${fn:substring(item.S_TIME,0,2) }:${fn:substring(item.S_TIME,2,4) } ~ ${fn:substring(item.E_TIME,0,2) }:${fn:substring(item.E_TIME,2,4) }</td>
                <td >${item.TEACHER_NM }</td>
                <td class="tdLeft"><a href="javascript:fn_view('${item.SEQ }');">${item.SUBJECT_NM }</a></td>
                <td><c:if test="${item.ISUSE eq 'Y' }">진행</c:if><c:if test="${item.ISUSE eq 'N' }">예정</c:if><c:if test="${item.ISUSE eq 'E' }">종료</c:if></td>
                <td>${fn:substring(item.REG_DT,0,10) }</td>
                <td>${item.REG_NM }</td>          
              </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="9">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
    </table>
    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
	<ul class="boardBtns">
        <li><a href="javascript:addBanner();">등록</a></li>
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
    	alert(e.keyCode)
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}
//검색
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#searchFrm').attr('action','<c:url value="/bannerManagement/OnAir_Banner_Lst.do"/>').submit();
}
//등록
function addBanner() {
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/OnAir_BannerAdd.do"/>').submit();
}
//변경
function fn_view(SEQ) {
    $("#SEQ").val(SEQ);
    $('#searchFrm').attr('action','<c:url value="/bannerManagement/OnAir_BannerView.do"/>').submit();
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
</script>
  <!--//content -->