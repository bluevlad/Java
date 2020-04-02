<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
// 대문자 체크
$.prototype.isCapital = function(){
    var chk = /[A-Z_]/;
    return chk.test(this.val());
};

function paramCheck(SITE_ID) {
    if($("#SITE_ID").val() == "") {
        alert("사이트ID를 입력해 주세요.");
        $("#SITE_ID").focus();
    }
    else if($("#SITE_ID").isCapital() == false){
        alert("사이트ID를 영문 대문자로 입력해 주세요.");
        $("#SITE_ID").focus();
    }
    else if($("#SITE_NM").val() == ""){
        alert("사이트를 입력해 주세요.");
        $("#SITE_NM").focus();
    }
    else {
        $("#DETAIL_SITE_ID").val(SITE_ID);
        $("#frm").attr("action", "<c:url value='/adminManagementAuth/authControlUpdateProcess.do' />");
        $("#frm").submit();
    }
}

function fn_authControlList(){
    $("#frm").attr("action", "<c:url value='/adminManagementAuth/authControlList.do' />");
    $("#frm").submit();
}

//deleteCode
function deleteAuth(SITE_ID){
    if(confirm("삭제하시겠습니까?")) {
        $("#DETAIL_SITE_ID").val(SITE_ID);
        $("#frm").attr("action", "<c:url value='/adminManagementAuth/authControlDelete.do' />");
        $("#frm").submit();
    } else {
        return false;
    }
}
</script>
</head>

<body>
<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="DETAIL_SITE_ID" name="DETAIL_SITE_ID" value="" />

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    <h2>● 운영자 관리 > <strong>권한관리</strong></h2>
        <table class="table01">
        <tr>
            <th>사이트ID</th>
            <td>
                    <input type="text" id="SITE_ID" name="SITE_ID"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:28% ;background:#FFECEC;" value="${detail.SITE_ID}" />
                    (☞영문 대문자[A~Z]만 사용하여 입력하십시요)
            </td>
        </tr>
        <tr>
            <th>공통코드명</th>
            <td>
                    <input type="text" id="SITE_NM" name="SITE_NM"  title="레이블 텍스트"  size="100"  maxlength="100" style="width:68%; background:#FFECEC;" value="${detail.SITE_NM}" />
            </td>
        </tr>
        <tr>
            <th>관리메뉴</th>
            <td>    <label for="ONOFF_DIV"> </label>
                    <select style="width:100px;"   id="ONOFF_DIV" name="ONOFF_DIV">
                        <option value="A" <c:if test="${detail.ONOFF_DIV == 'A'}">selected</c:if>>전체</option>
                        <option value="O" <c:if test="${detail.ONOFF_DIV == 'O'}">selected</c:if>>온라인</option>
                        <option value="F" <c:if test="${detail.ONOFF_DIV == 'F'}">selected</c:if>>오프라인</option>
                        <option value="T" <c:if test="${detail.ONOFF_DIV == 'T'}">selected</c:if>>모의고사</option>
                    </select>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td>    <label for="ISUSE"> </label>
                    <select style="width:100px;"   id="ISUSE" name="ISUSE">
                        <option value="Y" <c:if test="${detail.ISUSE == 'Y'}">selected</c:if>>활성</option>
                        <option value="N" <c:if test="${detail.ISUSE == 'N'}">selected</c:if>>비활성</option>
                    </select>
            </td>
        </tr>
        </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:paramCheck('${detail.SITE_ID}')">수정</a></li>
        <li><a href="javascript:deleteAuth('${detail.SITE_ID}')">삭제</a></li>
        <li><a href="javascript:fn_authControlList()">목록</a></li>
    </ul>
    <%-- <div style="float:left; width:100%; text-align:right; margin-top:16px;">
        <span class="btn_pack medium"><button type="button" onclick="javascript:paramCheck('${detail.SITE_ID}')">수정</button></span>
        <span class="btn_pack medium"><button type="button" onclick="javascript:deleteAuth('${detail.SITE_ID}')">삭제</button></span>
        <span class="btn_pack medium"><button type="button" onclick="javascript:fn_authControlList()">목록</button></span>
    </div> --%>
    <!--//버튼-->
</form>
</div>
<!--//content -->
</body>