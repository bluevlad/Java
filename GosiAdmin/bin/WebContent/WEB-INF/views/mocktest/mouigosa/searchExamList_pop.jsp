<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}"/>
<input type="hidden" id="SRCHCODE" name="SRCHCODE" value="${searchMap.SRCHCODE}">
<input type="hidden" id="SRCHTXT" name="SRCHTXT" value="${searchMap.SRCHTXT}">
<input type="hidden" id="S_ISEXAMTYPEON" name="S_ISEXAMTYPEON" value="${searchMap.S_ISEXAMTYPEON}">
<input type="hidden" id="S_ISEXAMTYPEOFF" name="S_ISEXAMTYPEOFF" value="${searchMap.S_ISEXAMTYPEOFF}">
    <h2>● 모의고사 > <strong>모의고사 불러오기</strong></h2>
     <!-- 검색 -->
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td width="45%">
                <label for="S_EXAMYEAR"></label>
                <select name="S_EXAMYEAR" id="S_EXAMYEAR">
                    <option value="">--년도--</option>
                    <c:forEach var="idx" begin="2013" end="2019" step="1" varStatus="status">
                        <option value="${idx}" <c:if test="${searchMap.S_EXAMYEAR == idx}">selected="selected"</c:if>>${idx}</option>
                    </c:forEach>
                </select>
                <label for="S_EXAMROUND"></label>
                <select name="S_EXAMROUND" id="S_EXAMROUND">
                    <option value="">--회차--</option>
                    <c:forEach var="idx" begin="1" end="20" step="1" varStatus="status">
                        <option value="${idx}" <c:if test="${searchMap.SEARCHFORM == idx}">selected="selected"</c:if>>${idx}</option>
                    </c:forEach>
                </select>
                <select name="S_CLASSCODE" id="S_CLASSCODE">
                    <option value="">--직급--</option>
                <c:forEach items="${registration_list}" var="data" varStatus="status">
                    <option value="${data.CODE}" <c:if test="${searchMap.S_CLASSCODE == data.CODE}">selected="selected"</c:if>>${data.NAME}</option>
                </c:forEach>
                </select>
            </td>
            <th>화면출력건수</th>
            <td>
                <input type="text" title="검색" type="text" id="pageRow" name="pageRow" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${searchMap.pageRow}" onkeypress="fn_RowNumCheck()">
                <input type="button" id="textfield3" name="textfield3" value="검색" onclick="fn_Search();"  >
            </td>
        </tr>
    </table>
    <!-- //검색 -->

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
            <td>${totalCount-((searchMap.currentPage-1)*searchMap.pageRow)-status.index}</td>
            <td>${item.EXAMYEAR}</td>
            <td>${item.EXAMROUND}</td>
            <td>${item.EXAMSTARTTIME}</td>
            <td>${item.NAME}</td>
            <td>${item.MOCKNAME}</td>
            <td>
              <c:if test="${!empty item.RECEIPTSTARTTIME1}">
              ${item.RECEIPTSTARTTIME0}.${item.RECEIPTSTARTTIME1}.${item.RECEIPTSTARTTIME2} ${item.RECEIPTSTARTTIME3}시
              </c:if>
              ~<br>
              <c:if test="${!empty item.RECEIPTENDTIME1}">
              ${item.RECEIPTENDTIME0}.${item.RECEIPTENDTIME1}.${item.RECEIPTENDTIME2} ${item.RECEIPTENDTIME3}시
              </c:if>
            </td>
            <td>${item.USEFLAG}</td>
            <td><input type="button" name="lecbtn" value="선택" onClick="fn_select('${item.MOCKCODE}','${item.MOCKNAME}');"></td>
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
    $("#frm").attr("action", "<c:url value='/mouigosa/reg/searchExamList.pop' />");
    $("#frm").submit();
}
//검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/mouigosa/reg/searchExamList.pop' />");
    $("#frm").submit();
}
function fn_select(code, name){
    $(top.opener.document).find("#${searchMap.SRCHCODE}").val(code);
    $(top.opener.document).find("#${searchMap.SRCHTXT}").val(name);

    self.close();
}
</script>