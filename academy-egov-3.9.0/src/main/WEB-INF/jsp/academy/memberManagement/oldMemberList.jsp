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

    $('#searchFrm').attr('action','<c:url value="/memberManagement/oldMemberList.do"/>').submit();
}

//삭제
function fn_Delete(){
    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
            $("#searchFrm").attr("action", "<c:url value='/memberManagement/memberCheckDelete.do' />");
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
    $('#searchFrm').attr('action','<c:url value="/memberManagement/oldMemberDetail.do"/>').submit();
}

function popup_view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
        window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');

    }
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 > <strong>이관회원조회</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="MESSAGEID" name="MESSAGEID">
    <input type="hidden" id="MESSAGENM" name="MESSAGENM">
    <input type="hidden" id="USER_ID" name="USER_ID" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td colspan="3">
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                    <option value="PHONE_NO"  <c:if test="${params.SEARCHTYPE == 'PHONE_NO'}">selected</c:if>>휴대폰번호</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            </td>
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
        <li><a href="javascript:fn_Delete();">삭제</a></li>
    </ul>
    <table class="table02">
        <tr>
          <th width="85">
              <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />No
          </th>
                  <th>회원ID</th>
                  <th>이름</th>
                  <th>생년월일</th>
                  <th>전화번호</th>
                  <th>이메일</th>
                  <th>문자수신여부</th>
                  <th>이메일수신여부</th>
                  <th>관심직렬</th>
                  <th>가입일</th>
                  <th>최근접속일</th>
                  <th>상태</th>
        </tr>
      <tbody>
      <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
                <tr>
                  <td>
                        <input type="checkbox" name="DEL_ARR" value="${list.USER_ID}" />
                        ${totalCount - (( currentPage - 1) * pageRow) - status.index}
                  </td>
                  <td><a href="javascript:view('${list.USER_ID}')">${list.USER_ID}</a></td>
                  <td id="DELL_NM"><a href="javascript:popup_view('${list.USER_ID}')">${list.USER_NM}</a></td>
 		                  <td>${list.BIRTH_DAY}</td>
		                  <td>${list.PHONE}</td>
		                  <td>${list.EMAIL}</td>
		                  <td>${list.ISOK_SMS}</td>
		                  <td>${list.ISOK_EMAIL}</td>
		                  <td>${list.C_NAME}</td>
		                  <td><fmt:formatDate value="${list.REG_DT}" pattern="yyyy-MM-dd"/></td>
		                  <td><fmt:formatDate value="${list.UPD_DT}" pattern="yyyy-MM-dd"/></td>
		                  <td>${list.ISUSENM}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="10">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>

    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
    </form>
</div>
<!--//content -->