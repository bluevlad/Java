<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
// 대문자 체크
$.prototype.isCapital = function(){
    var chk = /[A-Z_]/;
    return chk.test(this.val());
};

function paramCheck(CODE_NO) {
/*  if($("#SYS_CD").val() == "") {
        alert("공통코드를 입력해 주세요.");
        $("#SYS_CD").focus();
    }
    else if($("#SYS_CD").isCapital() == false){
        alert("공통코드를 영문 대문자로 입력해 주세요.");
        $("#SYS_NM").focus();
    }
    else  */
    if($("#SYS_NM").val() == ""){
        alert("공통코드명을 입력해 주세요.");
        $("#SYS_NM").focus();
        return;
    } else if($("#CODE_CD").val() == ""){
        alert("설정코드를 입력해 주세요.");
        $("#CODE_CD").focus();
        return;
    } else if($("#CODE_NM").val() == ""){
        alert("설정코드명을 입력해 주세요.");
        $("#CODE_NM").focus();
        return;
    //} else if($("#CODE_VAL").val() == ""){
    //    alert("설정값을 입력해 주세요.");
    //    $("#CODE_VAL").focus();
    //    return;
    } else {
        $("#DETAIL_CODE_NO").val(CODE_NO);
        $("#frm").attr("action", "<c:url value='/adminManagementCode/commonCodeUpdateProcess.do' />");
        $("#frm").submit();
    }
}

function fn_commonCodeList(){
    $("#frm").attr("action", "<c:url value='/adminManagementCode/commonCodeList.do' />");
    $("#frm").submit();
}

//deleteCode
function deleteCode(CODE_NO){
    if(confirm("삭제하시겠습니까?")) {
        $("#DETAIL_CODE_NO").val(CODE_NO);
        $("#frm").attr("action", "<c:url value='/adminManagementCode/commonCodeDelete.do' />");
        $("#frm").submit();
    }
}

function fn_commonCodeList(){
    $("#frm").attr("action", "<c:url value='/adminManagementCode/commonCodeList.do' />");
    $("#frm").submit();
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="DETAIL_CODE_NO" name="DETAIL_CODE_NO" value="" />

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

<h2>● 운영자 관리 > <strong>코드관리</strong></h2>
<!--//테이블-->
        <table class="table01">
        <tr>
            <th >공통코드</th>
            <td >
                    ${detail.SYS_CD}
            </td>
        </tr>
        <tr>
            <th >공통코드명</th>
            <td >
                    <input type="text" id="SYS_NM" name="SYS_NM"  title="레이블 텍스트"  size="100" maxlength="100" style="width:68%; background:#FFECEC;" value="${detail.SYS_NM}" />
            </td>
        </tr>
        <tr>
            <th >설정코드</th>
            <td >
                    <input type="text" id="CODE_CD" name="CODE_CD"  title="레이블 텍스트" size="20" maxlength="20" style="width:28%; background:#FFECEC;" value="${detail.CODE_CD}"  />
            </td>
        </tr>
        <tr>
            <th >설정코드명</th>
            <td >
                    <input type="text" id="CODE_NM" name="CODE_NM"  title="레이블 텍스트"  style="width:68%; background:#FFECEC;" value="${detail.CODE_NM}"  />
            </td>
        </tr>
        <tr>
            <th>설정값</th>
            <td>
                    <input type="text" id="CODE_VAL" name="CODE_VAL"  title="레이블 텍스트"  style="width:68%; background:#FFECEC;" value="${detail.CODE_VAL}" />
            </td>
        </tr>
        <tr>
            <th >코드설명</th>
            <td >
                    <input type="text" id="CODE_INFO" name="CODE_INFO"  title="레이블 텍스트"  style="width:68%;"  value="<c:out value="${detail.CODE_INFO}"/>" />
            </td>
        </tr>
        <tr>
            <th >상태</th>
            <td ><label for="ISUSE"></label>
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
      <li><a href="javascript:paramCheck('${detail.CODE_NO}');">수정</a></li>
      <li><a href="javascript:deleteCode('${detail.CODE_NO}');">삭제</a></li>
      <li><a href="javascript:fn_commonCodeList();">목록</a></li>
    </ul>
    <!--//버튼-->
</form>
</div>
<!--//content -->
