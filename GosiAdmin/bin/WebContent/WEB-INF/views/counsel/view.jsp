<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
//목록
function fn_List() {
    $('#frm').attr('action', '<c:url value="/counsel/list.do"/>').submit();
}
//상담신청자 조회
function listUser() {
    $('#frm').attr('action', '<c:url value="/counsel/userlist.do"/>').submit();
}
//회원 상세
function usrView(userid, sch_day, ts_idx) {
    window.open('<c:url value="/counsel/MemberView.pop"/>?USER_ID=' + userid + '&SCH_DAY=' + sch_day + '&TS_IDX=' + ts_idx, 
            '_blank', 'location=no,resizable=no,width=800,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
}
</script>
</head>

<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" /> 
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" /> 
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
<input type="hidden" id="SCH_DAY" name="SCH_DAY" value="" />
    <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    <!--버튼-->
    <!--  <ul class="boardBtns">
        <li><a href="javascript:listUser();">신청자조회</a></li>
    </ul> -->
    <!-- 테이블 -->
    <table class="table02">
        <tr>
            <th width="200">시간</th>
            <th>예약현황</th>
        </tr>
        <tbody>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="list" varStatus="loop">
                <tr>
                    <td><a href="javascript:view('${list.TIME_SET}')">${list.TIME_SET}</a></td>
                    <td>
                        <c:forEach items="${rst}" var="rst" varStatus="status">
                        <c:if test="${list.TS_IDX == rst.TS_IDX}">
                        <a href="javascript:usrView('${rst.USER_ID}', '${rst.SCH_DAY}', '${rst.TS_IDX}')">&nbsp;[&nbsp;<b>${rst.USER_NM}(${rst.USER_ID})</b>&nbsp;]&nbsp;</a>
                        </c:if>
                        </c:forEach>
                    </td>
                </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty list}">
                <tr bgColor=#ffffff align=center>
                    <td colspan="7">예정된 상담이 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->

</form>
</div>
<!--//content -->
