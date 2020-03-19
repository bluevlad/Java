<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<fmt:setBundle  var="m" basename="resources.msg"/>
<fmt:setBundle var="a" basename="resources.message"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=<fmt:message key='page.charset'/>">
	<link rel="stylesheet" href="${CPATH}/css/st02_common.css" type="text/css">
	<title><fmt:message bundle = "${m}" key="msg.title"/></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style3 {
	font-family: "µ¸¿ò";
	font-weight: bold;
	font-size: 11px;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
	function setSize() {
		w = 460; h = 567;
		window.resizeTo(w, h);
		
		with(document.body)
	    {
	        window.resizeTo(w * 2 - clientWidth, h * 2 - clientHeight);
	    }
	}
//-->
</script>
<script language="javascript" type="text/JavaScript" src="${CPATH}/js/sub.common.js"></script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" onLoad="setSize()">
<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD COLSPAN=6><IMG SRC="${CPATH}/images/msg/pptop_1.jpg" WIDTH=460 HEIGHT=48 ALT=""></TD>
	</TR>
	<TR>
		<TD ROWSPAN=2><a href="${CPATH}/msgmst/rcvlist.do" target="_self"><IMG SRC="${CPATH}/images/msg/pptop_2.jpg" WIDTH=66 HEIGHT=32 ALT=""></a></TD>
		<TD><a href="${CPATH}/msgmst/rcvlist.do" target="_self"><IMG SRC="${CPATH}/images/msg/pptop_3.jpg" WIDTH=86 HEIGHT=27 BORDER=0 ALT=""></A></TD>
		<TD><a href="${CPATH}/msgmst/sndlist.do" target="_self"><IMG SRC="${CPATH}/images/msg/pptop_4.jpg" WIDTH=78 HEIGHT=27 BORDER=0 ALT=""></A></TD>
		<TD><a href="${CPATH}/msgmst/writeForm.do" target="_self"><IMG SRC="${CPATH}/images/msg/pptop_5.jpg" WIDTH=80 HEIGHT=27 BORDER=0 ALT=""></A></TD>
		<TD><a href="${CPATH}/msgmst/msgmnglist.do" target="_self"><IMG SRC="${CPATH}/images/msg/pptop_6.jpg" WIDTH=79 HEIGHT=27 BORDER=0 ALT=""></A></TD>
		<TD ROWSPAN=2><IMG SRC="${CPATH}/images/msg/pptop_7.jpg" WIDTH=71 HEIGHT=32 ALT=""></TD>
	</TR>
	<TR>
		<TD COLSPAN=4><IMG SRC="${CPATH}/images/msg/pptop_8.jpg" WIDTH=323 HEIGHT=5 ALT=""></TD>
	</TR>
</TABLE>
<tiles:insert page="${content.file}" flush="true"/>
</body>
</html>