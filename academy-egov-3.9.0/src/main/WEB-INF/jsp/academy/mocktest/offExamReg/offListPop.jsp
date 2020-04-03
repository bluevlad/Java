<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<style>
.pop{position:relative;width:770px;min-width:770px;}
.table01{position:relative;width:770px;min-width:770px;}
.table02{position:relative;width:770px;min-width:770px;}
</style>
<script type="text/javascript">
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/offExamReg/offListPop.pop' />");
    $("#form").submit();
}
</script>
</head>

<div id="content_pop" style="width:770px;position:relative;">
    <h2>● <strong>${params.mockName}(${params.classCode})-${params.mockCode}</strong></h2>

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="8%">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">성명</th>
            <th scope="col">과목</th>
            <th scope="col">문항번호</th>
            <th scope="col">문제답변</th>
            <th scope="col">정답여부</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
        <form id="form" name="form" method="post">
            <input type="hidden" id="currentPage" name="currentPage">
            <input type="hidden" id="mockCode" name="mockCode" value="${mockCode}">
            <input type="hidden" id="mockName" name="mockName" value="${params.mockName}">
            <input type="hidden" id="classCode" name="classCode" value="${params.classCode}">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td><c:out value="${data.USER_NM}"/></td>
                <td><c:out value="${data.SUBJECT_NM}"/></td>
                <td><c:out value="${data.QUESTIONNUMBER}"/></td>
                <td><c:out value="${data.ANSWERNUMBER}"/></td>
                <td><c:out value="${data.CORRECTYN}"/></td>
            </tr>
            </c:forEach>
        </form>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="7">등록된 정보가 없습니다.</td>
            </tr>
        </c:if>
      </tbody>
    </table>

    <!--paging  -->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->

</div>
<!--//content -->
