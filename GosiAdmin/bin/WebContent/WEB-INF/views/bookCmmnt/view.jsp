<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<h2>● 교재 관리 > <strong>교재구매후기</strong></h2>
<!--//테이블--> 
    <table class="table02">
        <tr>
            <th>관리번호</th>
            <th>직종</th>
            <th>카테고리</th>
            <th>교재명</th>
            <th>저자</th>
        </tr>
        <tbody>
        <c:forEach items="${viewlist}" var="item" varStatus="loop">
            <tr <c:if test="${item.RSC_ID eq view[0].RSC_ID}">style='background:#FFECEC;'</c:if>>
                <td>${item.RSC_ID}</td>
                <td>${item.CATEGORY_NM}</td>
                <td>${item.LEARNING_NM}</td>
                <td><a href="javascript:fn_view('${item.SEQ}','${item.RSC_ID}');">${item.BOOK_NM}</a></td>
                <td>${item.BOOK_AUTHOR}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>      
    <!--//테이블--> 
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPageReply}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table01">
   <c:if test="${not empty cmmntList}">
        <c:forEach items="${cmmntList}" var="data" varStatus="status">
        <tr>
            <td width="30">
                ${totalCount - (( currentPageReply - 1) * pageRowReply) - status.index} 
            </td>
            <td width="606" height="20" style="padding-left:40px">
            <!-- 제목 -->
            <b>${data.USER_NAME}</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!-- 날짜 -->
            <font color="9a9a9a">
                ${data.REG_DT}
            </font> &nbsp;
            <font color="9a9a9a">
                             평점 : 
                    <c:forEach var="b" begin="1" end="${data.CHOICE_POINT}" step="1">
                        <img src="<c:url value="/resources/images/star.png"/>" border="0" />
                    </c:forEach>                                      
            </font>
            </td>
            <td width="87">
                <input type="button" onclick='javascript:fn_delete("${data.SEQ}","${data.RSC_ID}");' value="삭제" />
            </td>
        </tr>
        <tr>
            <td width="693" colspan="3" style="padding-left:70px;">
                ${data.CONTENT}
            </td>
        </tr>
        </c:forEach>
    </c:if>
        
    <c:if test="${empty cmmntList}">
         <tr bgColor=#ffffff align=center> 
            <td colspan="8">데이터가 존재하지 않습니다.</td>
        </tr>
    </c:if>  
    </table>
    <!-- paginate-->
    <c:if test="${not empty cmmntList}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
    
    <!--버튼-->
    <ul class="boardBtns">
      <li><a href="javascript:fn_list();">목록</a></li>
    </ul>
    <!--//버튼--> 
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPageReply" name="currentPageReply" value="${currentPageReply}">
<input type="hidden" id="pageRowReply" name="pageRowReply" value="${pageRowReply}">
<input type="hidden" id="RSC_ID" name="RSC_ID" value="${params.RSC_ID}" />  
<input type="hidden" id="DELETE_RSC_ID" name="DELETE_RSC_ID" value="" />  
<input type="hidden" id="DELETE_SEQ" name="DELETE_SEQ" value="" />  
</form>
</div>
<!--//content --> 
<script type="text/javascript">
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPageReply").val(1);
    else $("#currentPageReply").val(page);
    
    $('#frm').attr('action','<c:url value="/book/comment/view.do"/>').submit();
}

function fn_list(){
    $("#frm").attr("action", "<c:url value='/book/comment/list.do' />");
    $("#frm").submit();
}

function fn_view(val1, val2){
    $("#SEQ").val(val1);
    $("#RSC_ID").val(val2);
    $("#frm").attr("action", "<c:url value='/book/comment/view.do' />");
    $("#frm").submit();
}

function fn_delete(SEQ, RSC_ID){
    if(confirm("삭제하시겠습니까?")) {
        $("#DELETE_SEQ").val(SEQ);
        $("#DELETE_RSC_ID").val(RSC_ID);
        $("#frm").attr("action", "<c:url value='/book/comment/delete.do' />");
        $("#frm").submit();
    } else {
        return false;
    }
}

</script>