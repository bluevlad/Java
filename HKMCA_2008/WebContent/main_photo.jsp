<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="modules.community.mboard.MBoardManager"%>
<%
final String sql = "select * from (   " +
"select a.*, b.subject, b.fl_board_type, b.pgid, c.thumbnail, c.real_filename  " +
" from mbbs_data a, mbbs_meta b, mbbs_attach c, jmf_menu d   " +
" where a.bid = b.bid    " +
" and a.bid = c.bid  " +
" and a.c_index = c.c_index  " +
" and b.pgid = d.pgid   " +
" and b.is_use = 'T'   " +
" and d.isuse = 'T'   " +
" and c.thumbnail = 'T' " +
" order by c_date desc   " +
" ) where rownum <=8  ";

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
<table border="0" cellpadding="2" cellspacing="2" width="900">    
    <tr>
        <c:forEach var="item" items="${list}" varStatus="status">
        <td>
        <!-- 사진1 start -->
            <c:url var="imgUrl" value="/pds/board/${item.bid}/thumb/${item.real_filename}"/>
            <a href='/board/view.do?bid=<jh:out value="${item.bid}"/>&c_index=<jh:out value="${item.c_index}"/>'><img src='<c:out value="${imgUrl}"/>' border="0" 
                style="cursor: pointer;" hspace="5" vspace="5" border="0" width="80" 
                onError="this.src='/WEB-INF/jsp/mboard/images/no_image.gif'"></a>
            <br><mh:out value="${item.c_subject}" bytes="20"/>
        <!-- 사진1 end -->
        </td>
        </c:forEach>
    </tr>
</table>