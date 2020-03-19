<%@ page contentType="application/xml; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ page import="modules.etest.stat.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	BaseHttpSession sessionBean = MySession.getSessionBean(request, response);
	Map param = new HashMap();
	param.put("exmid", request.getParameter("exmid"));
	param.put("usn", sessionBean.getUsn());
	param.put("my", request.getParameter("rstscore100"));
	final String sql = " SELECT   s.sc_range * 10 sc_range, DECODE (s.sc_range, FLOOR (decode(:my,100,99.9,:my) / 10 +1), 'T', 'F') chk,"   +
	"          COUNT (rstscore10) cnt"   +
	"     FROM (SELECT r.exmid, FLOOR (decode(r.rstscore100,100,99.9,r.rstscore100)  / 10)+1 rstscore10"   +
	"             FROM exm_rst r, exm_mst m"   +
	"            WHERE r.exmid = :exmid"   +
	"              AND r.exmid = m.exmid"   +
	"              AND r.rst_status = 'F') x,"   +
	"          (SELECT ROWNUM sc_range"   +
	"             FROM all_objects"   +
	"            WHERE ROWNUM <= 10) s"   +
	"    WHERE s.sc_range = x.rstscore10(+)"   +
	" GROUP BY s.sc_range"  +
    " order by s.sc_range"  ;
	MdbDriver oDb = null;
	try {
		oDb = Mdb.getMdbDriver();
		request.setAttribute("list", oDb.selector(Map.class, sql, param));
	} finally {
		try {oDb.close();} catch (Exception ex) {}
		oDb = null;
	}
%>
<chart caption='Histogram' xAxisName='Score' yAxisName='peoples' showValues='1' decimals='0' formatNumberScale='0'>
    <c:forEach var="item" items="${list}">
    <c:if test="${item.chk == 'F'}">
    <set label='~<c:out value="${item.sc_range}"/>' value='<c:out value="${item.cnt}"/>'/>
    </c:if>
    <c:if test="${item.chk == 'T'}">
    <set label='~<c:out value="${item.sc_range}"/>' value='<c:out value="${item.cnt}"/>' color='000000'/>
    </c:if>
    </c:forEach>
</chart>