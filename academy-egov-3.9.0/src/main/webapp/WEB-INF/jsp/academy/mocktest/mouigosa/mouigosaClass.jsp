<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
function fn_SbjPopup(category_code) {
    window.open("<c:url value='/mouigosa/mouigosaClassSubject.pop?category_code="+category_code+"' />","_blank","scrollbars=yes,toolbar=no,resizable=yes width=450,height=470");
}
</script>
</head>
<div id="content">
    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="10%">
        <col width="15%">
        <col width="10%">
        <col width="*">
        <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">코드</th>
            <th scope="col">직종명</th>
            <th scope="col">모의고사등록여부</th>
            <th scope="col">과목</th>
            <th scope="col">과목등록</th>
        </tr>
        </thead>
        <tbody>
      <c:if test="${not empty ClassList}">
        <c:forEach items="${ClassList}" var="list" varStatus="status">
        <c:if test="${list.LVL > 2}">
        <tr>
            <td>${list.CODE}</td>
            <td>${list.NAME}</td>
            <td>
            	<input id="CLASSSERIESCODE" name="CLASSSERIESCODE" value="${list.CLASSSERIESCODE}" type="checkbox" <c:if test="${list.CODE == list.CLASSSERIESCODE}">checked="checked"</c:if> />
            </td>
            <td style="text-align:left;">
		        <c:forEach items="${SubjectList}" var="sbj" varStatus="status">
		        	<c:if test="${list.CODE == sbj.CLASSSERIESCODE }">
		            	<c:choose>
		            	<c:when test="${sbj.SUBJECTTYPEDIVISION == '1' }">
				            <b>${sbj.SUBJECT_NM}</b>
		            	</c:when>
		            	<c:otherwise>
				            ${sbj.SUBJECT_NM}
		            	</c:otherwise>
		            	</c:choose>
			            &nbsp;&nbsp;
		            </c:if>
            	</c:forEach>
            </td>
            <td><a href="javascript:fn_SbjPopup('${list.CODE}')">[등록]</a></td>
        </tr>
        </c:if>
        </c:forEach>
      </c:if>
      <c:if test="${empty ClassList}">
        <tr bgColor=#ffffff align=center>
            <td colspan="8">데이터가 존재하지 않습니다.</td>
        </tr>
      </c:if>
        </tbody>
    </table>
    <!--//테이블-->
</div>