<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%
final String sql = "select * from ( " +
	"select a.*, b.subject, b.fl_board_type, b.pgid   " +
	" from mbbs_data a, mbbs_meta b, jmf_menu c  " +
	" where a.bid = b.bid   " +
	" and b.pgid = c.pgid  " +
	" and b.is_use = 'T'  " +
	" and c.isuse = 'T'  " +
	" and b.fl_board_type = :fl_board_type " +
	" order by c_date desc  " +
	") " +
	" where rownum <= 10 ";

    MdbDriver oDb = null;
    try {
        oDb = Mdb.getMdbDriver();
        Map param = new HashMap();
        param.put("fl_board_type", request.getParameter("fl_board_type"));
        request.setAttribute("list", oDb.selector(Map.class, sql, param));
    } finally {
        try {
            oDb.close();
        } catch (Exception ex) {
        }
        oDb = null;
    }
%>

<table border="0" cellpadding="2" cellspacing="2" width="100%">    
    <c:forEach var="item" items="${list}">
    <tr>
        <td>
            <mh:betweenHour var="t" value="${item.c_date}"></mh:betweenHour>
            <li>
            <c:if test="${t<24}">
            &nbsp;<img src="/mboard/images/icon/notice_new.gif" alt="" width="25" height="13" border="0" valign="absmiddle"><b>
            </c:if>
                &nbsp;<a href="/board/view.do?bid=<jh:out value="${item.bid}"/>&c_index=<mh:out value="${item.c_index}"/>">
                [<mh:out value="${item.subject}"/>]&nbsp;<mh:out value="${item.c_subject}"/></a></li>
        </td>
    </tr>
    </c:forEach>
</table>