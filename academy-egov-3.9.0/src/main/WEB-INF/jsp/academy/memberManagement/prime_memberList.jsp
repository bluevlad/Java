<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDATE"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
    initDatePicker1("EDATE");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

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

//All Checkbox Controller
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
}

//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        fn_Search();
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

    
    $('#searchFrm').attr('action','<c:url value="/memberManagement/prime_memberList.do"/>').submit();
}



// 엑셀 다운로드
function fn_excel_down() {
    $("#searchFrm").attr("action", "<c:url value='/memberManagement/prime_excel.do' />");
    $("#searchFrm").submit();
}

</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 > <strong>제휴회원 수강내역조회</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="MESSAGEID" name="MESSAGEID">
    <input type="hidden" id="MESSAGENM" name="MESSAGENM">
    <input type="hidden" id="USERID" name="USERID" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    <input type="hidden" id="S_TYPE" name="S_TYPE" value="" />

    <table class="table01">
        <tr>
            <th width="15%">수강일</th>
            <td>
                <input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
                ~
                <input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>&nbsp;&nbsp;&nbsp;
                <select id="COOPNM" name="COOPNM">
                	<option value="PRIME" <c:if test="${params.COOPNM eq 'PRIME' }">selected="selected"</c:if>>프라임</option>
                	<option value="CHR" <c:if test="${params.COOPNM eq 'CHR' }">selected="selected"</c:if>>참수리</option>
                </select>
            </td>    
            <th width="15%">검색</th>
             <td width="30%">
                <%-- <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()"> --%>
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td> 
        </tr>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    <table class="table02">
        <tr>
          		  <th width="85">No</th>
                  <th>회원ID</th>
                  <th>수강일</th>

        </tr>
      <tbody>
      <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
                <tr>
                  <td>
                        ${totalCount - (( currentPage - 1) * pageRow) - status.index}
                  </td>
 		                  <td>${list.USER_ID}</td>
		                  <td><fmt:formatDate value="${list.UPD_DT}" pattern="yyyy-MM-dd"/></td>

                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="4">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 <%-- (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>) --%></span></p>

    <!-- paginate-->
	<%--     
	<c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if> 
    --%>
    <!--//paginate-->
    </form>
</div>
<!--//content -->