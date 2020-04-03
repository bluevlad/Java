<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="USER_ID" name="USER_ID" value="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SRCHCODE" name="SRCHCODE" value="${params.SRCHCODE}">
<input type="hidden" id="SRCHTXT" name="SRCHTXT" value="${params.SRCHTXT}">
<input type="hidden" id="ONOFFDIV" name="ONOFFDIV" value="${params.ONOFFDIV}">
    <h2>● 강의 관리 > <strong>강사관리</strong></h2>
    
    <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>            
                <label for="SEARCHTYPE"></label>
                <select name="SEARCHTYPE" id="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>ID</option>
                    <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>이름</option>
                </select>
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
            </td>
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
                <input type="button" onclick="fn_Search()" value="검색" />
            </td>
        </tr>
    </table>
    <!-- //검색 -->
          
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="10">No</th>
            <th>ID</th>
            <th width="40">이름</th>
            <th>과목</th>
            <th>분류</th>
            <th>상태(ON)</th>
            <th>상태(OFF)</th>
            <th>선택</th>
        </tr>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="loop">
            <tr>
                <td>${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
                <td>${item.USER_ID}</td>
                <td>${item.USER_NM}</td>
                <td>${item.SUBJECT_NM}</td>
                <td>${item.CATEGORY_NM}</td>
                <td>${item.ON_OPENYNNM}</td>
                <td>${item.OFF_OPENYNNM}</td>
                <td><input type="button" name="lecbtn" value="선택" onClick="fn_select('${item.USER_ID}','${item.USER_NM}');"></td>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center> 
                <td colspan="8">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>  
        </tbody>
    </table>      
    <!-- //테이블--> 
   
   <c:if test="${empty params.SEARCHCATEGORY}">
        <!-- paginate-->
        <c:if test="${!empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
        <!--//paginate-->
    </c:if>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->

</form> 
</div>
<!--//content --> 

<script type="text/javascript">
// 페이징
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#frm").attr("action", "<c:url value='/teacher/list_pop.pop' />");
    $("#frm").submit();
}

// All Checkbox Controller
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
}

// RowNum 숫자만 입력(엔터키 허용)
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

// 검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/teacher/list_pop.pop' />");
    $("#frm").submit();
}

// 엔터키 검색
function fn_Enter(){
    $("#SEARCHTEXT").keyup(function(e)  {
        if(e.keyCode == 13) 
            fn_Search();
    });
}

function fn_select(leccode, lecname){
    $(top.opener.document).find("#${params.SRCHCODE}").val(leccode);
    $(top.opener.document).find("#${params.SRCHTXT}").val(lecname);

    self.close();
}
</script>
