<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<title><fmt:message key='page.title'/></title>
	<link rel="stylesheet" href="${CPATH}/include/style.css" type="text/css">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="${CPATH}/js/lib.validate.js"></SCRIPT>
	<script language="javascript" src="${CPATH}/js/sub.common.js"></script>
	<script language="javascript">
		function setSize(){
			window.resizeTo(500,300);
		}
	</script>
</head>
<body background="${CPATH}/images/member/bg.gif" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" 
	marginwidth="0" marginheight="0" style="background-repeat: repeat-x;" onload="setSize();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <TBODY>
  <TR>
    <TD noWrap width=10 height=30></TD>
    <TD style="PADDING-BOTTOM: 3px" vAlign=bottom noWrap width="100%">사용자 ID 찾기</TD>
    <td width="150" align="right" valign="bottom" nowrap 
		style="PADDING-RIGHT: 10px; PADDING-BOTTOM: 5px"><A href="javascript:self.close();"><U>창 닫기</U></A> </td>
	</TR>
  	<TR>
    	<td height="2" colspan="3" bgcolor="#333399"></td>
	</TR>
	</TBODY>
</TABLE>	
<tiles:insert page="${content.file}" flush="true"/>
</html>