<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head></head>
<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
    <h2>● 교재 관리 > <strong>교재구매후기</strong></h2>
    
    <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>         
                <label for="SEARCHKIND"></label>
                <select name="SEARCHKIND" id="SEARCHKIND">
                    <option value="">--전체검색--</option>
                    <c:forEach items="${kindlist}" var="item" varStatus="loop">
                        <option value="${item.CODE}" <c:if test="${params.SEARCHKIND== item.CODE }">selected="selected"</c:if>>${item.NAME}</option>                    
                    </c:forEach>
                </select>
                <label for="SEARCHTYPE"></label>
                <select name="SEARCHTYPE" id="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>저자</option>
                    <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>교재코드</option>
                    <option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>교재명</option>
                </select>
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="30" title="검색어" onkeypress="fn_Enter()">
            </td>
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                 <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
                 <input type="button" onclick="fn_Search()" value="검색" />
            </td>
        </tr>
    </table>
    <!-- //검색 -->
        
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="85">No</th>
            <th>관리번호</th>
            <th>직종</th>
            <th>카테고리</th>
            <th>교재명</th>
            <th>저자</th>
            <th>평점</th>
        </tr>
        <tbody>
            <c:forEach items="${list}" var="item" varStatus="loop">
                <tr>
                    <td>${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
                    <td>${item.RSC_ID}</td>
                    <td>${item.CATEGORY_NM}</td>
                    <td>${item.LEARNING_NM}</td>
                    <td style="text-align:left;"><a href="javascript:fn_view('${item.SEQ}','${item.RSC_ID}');">${item.BOOK_NM}</a></td>
                    <td>${item.BOOK_AUTHOR}</td>
                    <td>
                    <c:forEach var="b" begin="1" end="${item.CHOICE_POINT}" step="1">
                        <img src="<c:url value="/resources/images/star.png"/>" border="0" />
                    </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr bgColor=#ffffff align=center> 
                    <td colspan="7">데이터가 존재하지 않습니다.</td>
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
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="SEQ" name="SEQ" value=""/>
<input type="hidden" id="RSC_ID" name="RSC_ID" value=""/>    
</form> 
</div>
<!--//content --> 

<script type="text/javascript">
//페이징
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#frm").attr("action", "<c:url value='/book/comment/list.do' />");
    $("#frm").submit();
}

// 검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/book/comment/list.do' />");
    $("#frm").submit();
}

// 수정폼
function fn_view(val1, val2){
    $("#SEQ").val(val1);
    $("#RSC_ID").val(val2);
    $("#frm").attr("action", "<c:url value='/book/comment/view.do' />");
    $("#frm").submit();
}

// 엔터키 검색
function fn_Enter(){
    $("#SEARCHTEXT").keyup(function(e)  {
        if(e.keyCode == 13) 
            fn_Search();
    });
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
</script>
