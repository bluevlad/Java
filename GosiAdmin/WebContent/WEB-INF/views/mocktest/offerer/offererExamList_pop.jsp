<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="SRCHMOCCODE" name="SRCHMOCCODE" value="">
    <h2>● 신청자관리 > <strong>모의고사 불러오기</strong></h2>
    <p class="pInto01">&nbsp;</p>
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="85">No</th>
            <th>년</th>
            <th>회차</th>
            <th>시험일</th>
            <th>직급</th>
            <th>모의고사명</th>
            <th>접수기간</th>
            <th>개설여부</th>
            <th width="85">선택</th>
        </tr>
        <tbody>
    <c:forEach items="${list}" var="item" varStatus="status">
        <tr>
            <td>${totalCount-((params.currentPage-1)*params.pageRow)-status.index}</td>
            <td>${item.EXAMYEAR}</td>
            <td>${item.EXAMROUND}</td>
            <td>${item.EXAMSTARTTIME}</td>
            <td>${item.NAME}</td>
            <td>${item.MOCKNAME}</td>
            <td>
              <c:if test="${!empty item.RECEIPTSTARTTIME}">
                ${fn:substring(item.RECEIPTSTARTTIME,0,4)}-${fn:substring(item.RECEIPTSTARTTIME,4, 6)}-${fn:substring(item.RECEIPTSTARTTIME,6, 8)} ${fn:substring(item.RECEIPTSTARTTIME,8, 10)}시
              </c:if>
              ~<br>
              <c:if test="${!empty item.RECEIPTENDTIME}">
                ${fn:substring(item.RECEIPTENDTIME,0,4)}-${fn:substring(item.RECEIPTENDTIME,4, 6)}-${fn:substring(item.RECEIPTENDTIME,6, 8)} ${fn:substring(item.RECEIPTENDTIME,8, 10)}시
              </c:if>
            </td>
            <td>${item.USEFLAG}</td>
            <td><input type="button" name="lecbtn" value="선택" onClick="fn_select('${item.MOCKCODE}');"></td>
        </tr>
    </c:forEach>
    <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="9">데이터가 존재하지 않습니다.</td>
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
    $("#frm").attr("action", "<c:url value='/offerer/offererWriteMoui.pop' />");
    $("#frm").submit();
}

function fn_select(code){
    $("#SRCHMOCCODE").val(code);
    $("#frm").attr("action", "<c:url value='/offerer/offererWriteMoui.pop'/>");
    $("#frm").submit();
}
</script>