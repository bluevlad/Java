<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="modules.wlc.classroom.BaseClassAction"%>
<%@page import="modules.wlc.classroom.beans.LectureInfo"%>
<%@page import="modules.wlc.classroom.support.LecUtil"%>
<%@page import="maf.util.SessionUtil"%>

<%
    BaseHttpSession ms = MySession.getSessionBean(request, response);
 
    final String sql = "select bid, subject" +
	" from wlc_mbbs_meta "+
	" where lec_cd = :lec_cd";

    MdbDriver oDb = null;
    try {
        oDb = Mdb.getMdbDriver();
        Map param = new HashMap();
        param.put("lec_cd", SessionUtil.getLec_cd(ms));
        
        request.setAttribute("brd", oDb.selector(Map.class, sql, param));

    } finally {
        try {
        	oDb.close();
        } catch (Exception ex) {
        }
        oDb = null;
    }
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:20px">
    <c:forEach var="brd" items="${brd}">
    <tr style="cursor:pointer">
        <td class="menu_l3">
            <a href="/wlc.common/list.do?bid=<c:out value="${brd.bid}"/>"><c:out value="${brd.subject}"/></a>
        </td>
    </tr>
    </c:forEach>
</table>