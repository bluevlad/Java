<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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

    $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminList.do"/>').submit();
}

//등록
function addMember() {
    $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminInsert.do"/>').submit();
}

//쪽지
function fn_message() {
    var tmp ="";
    var tmp_nm="";

    $("input[name=DEL_ARR]:checked").each(function (index){
        tmp += $(this).val() + ",";
        tmp_nm += $(this).parent().parent().find("#DELL_NM").text() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }
    if(confirm("쪽지를 보내시겠습니까?")) {
        var last = tmp.lastIndexOf(',');
        tmp = tmp.substr(0,last);
        var last_nm = tmp_nm.lastIndexOf(',');
        tmp_nm = tmp_nm.substr(0,last_nm);


        $("#MESSAGEID").val(tmp);
        $("#MESSAGENM").val(tmp_nm);
        window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=800,height=194 ');
        $('#searchFrm').attr('target' ,'message');
        $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminCheckMessage.pop"/>').submit();
    }
}

//메일
function fn_mail() {
    var tmp ="";
    var tmp_nm="";

    $("input[name=DEL_ARR]:checked").each(function (index){
        tmp += $(this).val() + ",";
        tmp_nm += $(this).parent().parent().find("#DELL_NM").text() + ",";
    });
    if(tmp == null || tmp == "" || tmp == undefined){
        alert("대상이 선택되지 않았습니다.");
        return;
    }
    if(confirm("메일 보내시겠습니까?")) {
        var last = tmp.lastIndexOf(',');
        tmp = tmp.substr(0,last);
        var last_nm = tmp_nm.lastIndexOf(',');
        tmp_nm = tmp_nm.substr(0,last_nm);


        $("#MESSAGEID").val(tmp);
        $("#MESSAGENM").val(tmp_nm);
        window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=800,height=300 ');
        $('#searchFrm').attr('target' ,'message');
        $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminCheckEmail.pop"/>').submit();
    }
}

//삭제
function fn_Delete(){
    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
            $("#searchFrm").attr("action", "<c:url value='/memberAdminManagement/memberAdminCheckDelete.do' />");
            $("#searchFrm").submit();
        }
    }else{
        alert("선택된 항목이 없습니다");
        return;
    }
}

// 회원 상세
function view(USER_ID) {
    $("#USER_ID").val(USER_ID);
    $('#searchFrm').attr('action','<c:url value="/memberAdminManagement/memberAdminDetail.do"/>').submit();
}

// 엑셀 다운로드
function fn_excel_down() {
    $("#searchFrm").attr("action", "<c:url value='/memberAdminManagement/excel.do' />");
    $("#searchFrm").submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 ><strong>관리자 조회</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="MESSAGEID" name="MESSAGEID" value="">
    <input type="hidden" id="MESSAGENM" name="MESSAGENM" value="">
    <input type="hidden" id="USER_ID" name="USER_ID" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">
                <input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>

    <!--//테이블-->
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:addMember();">등록</a></li>
        <li><a href="javascript:fn_mail();">메일발송</a></li>
        <li><a href="javascript:fn_message();">쪽지발송</a></li>
        <!-- <li><a href="javascript:fn_Delete();">삭제</a></li> -->
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    <table class="table02">
        <tr>
            <th width="85">
                <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />No
            </th>
            <th>회원ID</th>
            <th>이름</th>
            <th>별명</th>
            <th>사이트구분</th>
            <th>회원권한</th>
            <th>등록일</th>
            <th>사용여부</th>
        </tr>
        <tbody>
    <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
            <td>
                <input type="checkbox" name="DEL_ARR" value="${list.USER_ID}"  />
                ${totalCount - (( currentPage - 1) * pageRow) - status.index}
            </td>
            <td><a href="javascript:view('${list.USER_ID}')">${list.USER_ID}</a></td>
            <td id="DELL_NM">${list.USER_NM}</td>
            <td>${list.USER_NICKNM}</td>
            <td>
            	<c:choose>
	            	<c:when test="${list.USER_POSITION == 'ALL'}">통합</c:when>
	            	<c:when test="${list.USER_POSITION == 'PUB'}">공무원</c:when>
	            	<c:when test="${list.USER_POSITION == 'COP'}">경찰</c:when>
	            	<c:when test="${list.USER_POSITION == 'GWJ'}">광주캠퍼스</c:when>
	            	<c:otherwise>미지정</c:otherwise>
            	</c:choose>
            </td>
            <td>${list.ADMIN_NM}</td>
            <td>${list.REG_DT}</td>
            <td>${list.ISUSENM}</td>
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
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <!--//테이블-->

    <!-- paginate-->
        <c:if test="${not empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
   <!--//paginate-->
    </form>
</div>
<!--//content -->