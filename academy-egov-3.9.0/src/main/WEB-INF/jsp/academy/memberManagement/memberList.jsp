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

    if($("#S_TYPE").val() == "P") {
    	alert("전화번호 검색중입니다. 잠시만 기다려주세요...");
    	return;
    } else{
    	if ($("#SEARCHTYPE").val() == "PHONE_NO"){
			$("#S_TYPE").val("P");
    	}
    }
    
    $('#searchFrm').attr('action','<c:url value="/memberManagement/memberList.do"/>').submit();
}

//등록
function addMember() {
    $('#searchFrm').attr('action','<c:url value="/memberManagement/memberInsert.do"/>').submit();
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
        window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=1027,height=300 ');
        $('#searchFrm').attr('target' ,'message');
        $('#searchFrm').attr('action','<c:url value="/memberManagement/memberCheckMessage.pop"/>').submit();
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
        window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=1027,height=300 ');
        $('#searchFrm').attr('target' ,'message');
        $('#searchFrm').attr('action','<c:url value="/memberManagement/memberCheckEmail.pop"/>').submit();
    }
}

// 회원 상세
function view(USER_ID, MTYPE, MEMBER_TYPE) {
    $("#USER_ID").val(USER_ID);
    $("#MEMBER_TYPE").val(MEMBER_TYPE);
        
    if(MEMBER_TYPE !="NEW"){
        $('#searchFrm').attr('action','<c:url value="/memberManagement/oldMemberDetail.do"/>').submit();
    }else{
        $('#searchFrm').attr('action','<c:url value="/memberManagement/memberDetail.do"/>').submit();
    }
}

function popup_view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
        window.open('<c:url value="/productOrder/MemberView1.pop"/>?TOP_MENU_ID=${TOP_MENU_ID}&userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');

    }
}

// 엑셀 다운로드
function fn_excel_down() {
    $("#searchFrm").attr("action", "<c:url value='/memberManagement/excel.do' />");
    $("#searchFrm").submit();
}

</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 회원관리 > <strong>회원조회</strong></h2>

    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="MESSAGEID" name="MESSAGEID">
    <input type="hidden" id="MESSAGENM" name="MESSAGENM">
    <input type="hidden" id="USER_ID" name="USER_ID" value="" />

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="MEMBER_TYPE" name="MEMBER_TYPE" value="" />

    <input type="hidden" id="S_TYPE" name="S_TYPE" value="" />

    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                    <option value="PHONE_NO"  <c:if test="${params.SEARCHTYPE == 'PHONE_NO'}">selected</c:if>>휴대폰번호</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
                &nbsp;<input type="checkbox"  id="oldm" name="oldm" value="Y" <c:if test="${params.oldm == 'Y'}">checked</c:if>/>이관회원포함
            </td>
            <th width="15%">사이트구분</th>
            <td>
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="ALL" <c:if test="${params.USER_POSITION == 'ALL' }">checked</c:if>/>전체 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="PUB" <c:if test="${params.USER_POSITION == 'PUB' }">checked</c:if>/>공무원 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="COP" <c:if test="${params.USER_POSITION == 'COP' }">checked</c:if>/>경찰 &nbsp;
                <input type="radio" id="USER_POSITION" name="USER_POSITION" VALUE="CAM" <c:if test="${params.USER_POSITION == 'CAM' }">checked</c:if>/>캠퍼스 &nbsp;
            </td>
        </tr>
        <tr>
            <th width="15%">등록일</th>
            <td>
                <input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
                ~
                <input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
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
      <li><a href="javascript:addMember();">등록</a></li>
        <li><a href="javascript:fn_message();">쪽지발송</a></li>
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    <ul class="boardBtns">
            <b>*조회가 안되는 2013년 이전 가입회원은 [이관회원포함] 검색해주세요.</b><br>
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
                  <th>가입사이트</th>
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
                  <td><a href="javascript:view('${list.USER_ID}','${list.MTYPE}', '${list.MEMBER_TYPE }')">${list.USER_ID}</a></td>
                  <td id="DELL_NM"><a href="javascript:popup_view('${list.USER_ID}')">${list.USER_NM}</a></td>
 		                  <td>${list.BIRTH_DAY}</td>
		                  <td>${list.PHONE}</td>
		                  <td>${list.EMAIL}</td>
		                  <td>${list.ISOK_SMS}</td>
		                  <td>${list.ISOK_EMAIL}</td>
		                  <td>${list.C_NAME}</td>
		                  <td>${list.SITE_NM}</td>
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