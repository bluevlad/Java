<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>

<head>
<meta name="decorator" content="index">
<c:if test="${message ne null and message ne ''}">
    <script type="text/javascript">
        alert('${message}');
    </script>
</c:if>
<script type="text/javascript">
function fn_List(){
    //location.href = '<c:url value="/offExamReg/offExamList.do?searchType=${param.searchType}&searchText=${param.searchText}&currentPage=${param.currentPage}&pageRow=${param.pageRow}"/>';
    $("#frm").attr("action", "<c:url value='/offExamReg/offExamList.do' />");
    $("#frm").submit();
}

function fn_OffListPopup() {
    var mockCode = $("#mockCode").val();
    var mockName = $("#mockName").val();
    var classCode = $("#classCode").val();
    window.open("<c:url value='/offExamReg/offListPop.pop?mockCode="+mockCode+"&mockName="+mockName+"&classCode="+classCode+" ' />","_blank","scrollbars=no,toolbar=no,resizable=yes width=850,height=470");
}

function goExcelRead() {
    //파일이 없을경우
    if(  !$("#uploadFile").val()){
        alert("파일을 첨부한 후 등록하여 주시기 바랍니다.");
        return;
    }
    if(fileCheck() == true){
        //console.log('등록');
        $("#frm").attr("action", "<c:url value='/offExamReg/offExamExcelRead.do' />");
        $("#frm").submit();
    }
}

function goExcelDelete() {
    if (confirm("삭제 하시겠습니까?")){
        var dataString = $("#frm").serialize();
        //var dataString = $("#mockCode").serialize();
        $.ajax({
            type : 'POST' ,
            url : 'offExamDelete.do',
            data : dataString ,
            async : false,
            success : function(result){
                console.log(result);
                if(result.successmessage != null) {
                    alert(result.successmessage);
                    $("#frm").attr("action", "<c:url value='/offExamReg/offExamList.do' />");
                    $("#frm").submit();
                } else {
                    alert(result.errormessage);
                }
            }
        });
    }else{

    }
};

function fileCheck() {
    // 파일 확장자 체크하기. 엑셀 파일만 업로드 가능.
    var obj = document.getElementById('uploadFile');
    // 파일 경로를 obj로 받아온다.
    var lastIndex = -1;
    var filePaht = "";
    filePath = obj.value;
    lastIndex = filePath.lastIndexOf('.');
    extension = filePath.substring( lastIndex+1, filePath.len );
    if(!((extension.toLowerCase() == "xls")) && extension != "") {
        alert('xls 파일만 첨부가능 합니다.');
        //obj.select();
        // document.selection.clear();
        return false;
    }
    return true;
}
</script>
</head>

<!--content -->
<div id="content">
    <h2>● 신청자관리 > <strong>OFF응시자등록</strong></h2>

    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">코드번호</th>
            <td scope="col" style="text-align:left;">${detailView.MOCKCODE }</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="col">모의고사명</th>
            <td scope="col" style="text-align:left;">${detailView.MOCKNAME } /${detailView.EXAMYEAR} / ${detailView.EXAMROUND} 회차</td>
        </tr>
        <tr>
            <th>시험직급</th>
            <td style="text-align:left;">${detailView.CLASSCODE }</td>
        </tr>
        <tr>
            <th>시작일</th>
            <td style="text-align:left;">${detailView.EXAMSTARTTIME}</td>
        </tr>
        <tr>
            <th>종료일</th>
            <td style="text-align:left;">${detailView.EXAMENDTIME}</td>
        </tr>
        <tr>
            <th>시험시간</th>
            <td style="text-align:left;">${detailView.EXAMTIME}(분)</td>
        </tr>
        <c:if test="${detailView.FILENAME ne null}">
        <tr>
            <th>등록파일명</th>
            <td style="text-align:left;">${detailView.FILENAME} / 파일등록ID :  ${detailView.REG_ID} / 등록시간 : <fmt:formatDate value="${detailView.REG_DT}" pattern="yyyy/MM/dd HH:mm" /> </td>
        </tr>
        </c:if>
        <%-- <tr>
            <th>회차</th>
            <td style="text-align:left;">${detailView.EXAMROUND}</td>
        </tr> --%>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href='javascript:fn_List()'>목록</a></li>
    </ul>
    <!--//버튼-->

    <!--테이블-->
    <c:if test="${detailView.FILENAME eq null}">
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="">
        </colgroup>
        <tbody>
        <tr>
            <th>일괄등록파일</th>
            <td style="text-align:left;">
                <form id="frm" name="frm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
                    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
                    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
                    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
                    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
                    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
                    <input type="hidden" id="searchType" name="searchType" value="${params.searchType}"/>
                    <input type="hidden" id="searchText" name="searchText" value="${params.searchText}"/>
                    <input type="hidden" id="mockCode" name="mockCode" value="${detailView.MOCKCODE }"/>
                    <input type="hidden" id="mockName" name="mockName" value="${detailView.MOCKNAME }"/>
                    <input type="hidden" id="classCode" name="classCode" value="${detailView.CLASSCODE}"/>
                    <input type="file" name="uploadFile" id="uploadFile">
                </form> <br /> (※ 등록할 엑셀파일 형식은 Excel 97 - 2003 통합문서이며, <b><font color="blue">모든 항목은 반드시 텍스트(TEXT) 형식이어야 합니다.</font></b>)
            </td>
        </tr>
        </tbody>
    </table>
    </c:if>
    <c:if test="${detailView.FILENAME ne null}">
        <form id="frm" name="frm" method="post" enctype="multipart/form-data">
            <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
            <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
            <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
            <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
            <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
            <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
            <input type="hidden" id="searchType" name="searchType" value="${params.searchType}"/>
            <input type="hidden" id="searchText" name="searchText" value="${params.searchText}"/>
            <input type="hidden" id="fileFullPath" name="fileFullPath" value="${detailView.FILEPATH}"/>
            <input type="hidden" id="mockCode" name="mockCode" value="${detailView.MOCKCODE }"/>
            <input type="hidden" id="mockName" name="mockName" value="${detailView.MOCKNAME }"/>
            <input type="hidden" id="classCode" name="classCode" value="${detailView.CLASSCODE}"/>
        </form>
    </c:if>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
    <c:if test="${detailView.FILENAME eq null}">
        <li><a href='javascript:goExcelRead()'>상세정보등록</a></li>
    </c:if>
    <c:if test="${detailView.FILENAME ne null}">
        <li><a href="javascript:fn_OffListPopup()">등록된상세정보보기</a></li>
        <li><a href='javascript:goExcelDelete()'>등록된상세정보 삭제</a></li>
    </c:if>
    </ul>
    <!--//버튼-->

    <c:if test="${not empty exlerrors or not empty dberrors}">
    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="*">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">고사구분</th>
            <th scope="col">과목명</th>
            <th scope="col">성명</th>
            <th scope="col">수험번호</th>
            <th scope="col">마킹정보</th>
            <th scope="col">오류정보</th>
        </tr>
        </thead>
        <tbody>
      <c:if test="${not empty exlerrors}">
        <c:forEach items="${exlerrors}" var="data" varStatus="status">
        <c:set var="errYN" value="${data.ERRS_YN}" />
         <tr>
            <td <c:if test="${errYN[0] eq 'Y'}">bgcolor="red"</c:if> >${data.DIV_CD}</td>
            <td <c:if test="${errYN[1] eq 'Y'}">bgcolor="red"</c:if> >${data.SUBJECT_NM}</td>
            <td <c:if test="${errYN[2] eq 'Y'}">bgcolor="red"</c:if> >${data.USER_NM}</td>
            <td <c:if test="${errYN[3] eq 'Y'}">bgcolor="red"</c:if> >${data.IDENTITY_ID}</td>
            <td <c:if test="${errYN[4] eq 'Y'}">bgcolor="red"</c:if> >${data.MARKINGS}</td>
            <td class="txt04">
            <c:if test="${data.ERR_DESC ne null and data.ERR_DESC ne '' }">
            ${data.ERR_DESC}:<c:forEach items="${data.ERRS_DESC}" var="err" varStatus="sttus">${err}  </c:forEach>
            </c:if>
            </td>
        </tr>
        </c:forEach>
      </c:if>
      <c:if test="${not empty dberrors}">
        <c:forEach items="${dberrors}" var="data" varStatus="status">
         <tr>
            <td>${data.DIV_CD}</td>
            <td>${data.SUBJECT_NM}</td>
            <td>${data.USER_NM}</td>
            <td>${data.IDENTITY_ID}</td>
            <td>${data.MARKINGS}</td>
            <td class="txt04">${data.ERR_DESC}</td>
        </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty exlerrors and empty dberrors}">
        <tr bgColor=#ffffff align=center>
            <td colspan="6">데이터가 존재하지 않습니다.</td>
        </tr>
      </c:if>
        </tbody>
    </table>
    <!--//테이블-->
    </c:if>

</div>
<!--//content -->
