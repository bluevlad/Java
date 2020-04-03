<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="SRCHCODE" name="SRCHCODE" value="${params.SRCHCODE}">
<input type="hidden" id="SRCHTXT" name="SRCHTXT" value="${params.SRCHTXT}">

    <h2>● 강의제작 > <strong>단과불러오기</strong></h2>
 
     <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>            
                <label for="SEARCHKIND"></label>
                <select name="SEARCHKIND" id="SEARCHKIND">
                    <option value="">--직종검색--</option>
                    <c:forEach items="${kindlist}" var="item" varStatus="loop">
                        <option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>                   
                    </c:forEach>
                </select>
                <label for="SEARCHFORM"></label>
                <select name="SEARCHFORM" id="SEARCHFORM">
                    <option value="">--학습형태검색--</option>
                    <c:forEach items="${formlist}" var="item" varStatus="loop">
                        <option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>                   
                    </c:forEach>
                </select>               
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
                <input type="button" onclick="fn_Search()" value="검색" />
            </td>
        </tr>
    </table>
    <!-- //검색 --> 
          
    <p class="pInto01">&nbsp;</p>
          
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="85">항목</th>
            <th>직종</th>
            <th>학습형태</th>
            <th>강의명</th>
            <th>강사</th>
            <th width="85">선택</th>
        </tr>
        <tbody>
            <c:forEach items="${list}" var="item" varStatus="loop">
                <tr>
                    <td>${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
                    <td>${item.CATEGORY_NM}</td>
                    <td>${item.LEARNING_NM}</td>
                    <td>${item.SUBJECT_TITLE}</td>
                    <td>${item.SUBJECT_TEACHER_NM}</td>
                    <td><input type="button" name="lecbtn" value="선택" onClick="fn_select('${item.LECCODE}','${item.SUBJECT_TITLE}');"></td>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr bgColor=#ffffff align=center> 
                    <td colspan="6">데이터가 존재하지 않습니다.</td>
                </tr>
            </c:if>  
        </tbody>
    </table>      
    <!-- //테이블--> 
   
    <!-- paginate-->
    <c:if test="${!empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
    
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
    $("#frm").attr("action", "<c:url value='/lectureOff/searchLecList.pop' />");
    $("#frm").submit();
}

//검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/lectureOff/searchLecList.pop' />");
    $("#frm").submit();
}

//엔터키 검색
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