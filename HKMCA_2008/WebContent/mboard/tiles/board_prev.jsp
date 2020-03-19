<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ page import="modules.community.mboard.MBoardManager"%>
<%
	MBoardManager oMbbs = (MBoardManager) request.getAttribute("oMbbs");
	String PATH_CPATH = request.getContextPath();
//	"oMbbs.oBoard.getGRP()
%>
<link href="<%=PATH_CPATH%>/mboard/css/board_01.css" rel="stylesheet" type="text/css"></link>