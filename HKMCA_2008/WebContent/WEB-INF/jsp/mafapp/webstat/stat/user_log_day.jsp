<%@ page contentType="application/xml; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.MafUtil"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="modules.xadmin.webstat.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%
	Calendar cal = Calendar.getInstance();
	Map param = new HashMap();
	String mm = request.getParameter("mm");
	if(MafUtil.empty(mm)) {
		mm = (cal.get(Calendar.MONTH)+1)+"";
	}
	if(mm.length()<2) {
		mm = "0" + mm;
	}
    
	param.put("yyyy", MafUtil.nvl(request.getParameter("yyyy"),cal.get(Calendar.YEAR)+""));
	param.put("mm",  mm);

	MdbDriver oDb = null;
	try {
		oDb = Mdb.getMdbDriver();
		request.setAttribute("list", WebStatDB.getLogInDaily(oDb, param));
	} finally {
		try {oDb.close();} catch (Exception ex) {}
		oDb = null;
	}
%>
<graph 
	caption='login / day' 
	xAxisName='Day' 
	yAxisName='Count' 
	showNames='1'
	baseFontSize='12'
	outCnvBaseFontSze='14'
	formatNumber="1"
	decimalPrecision="0"
	formatNumberScale="1">
<c:forEach var="item" items="${list}" varStatus="status">
<set name='<mh:out value="${item.category}" beginIndex="8" endIndex="10"/>' value='<c:out value="${item.cnt}"/>' color='0099FF' />
</c:forEach>
</graph>