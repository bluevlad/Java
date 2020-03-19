<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/error.jsp"%>
<%@ page import="modules.community.mboard.MBoardManager"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%
	MBoardManager oMbbs = (MBoardManager) request.getAttribute("oMbbs");
	String layout = "tiles/sub_board.jsp";
%>
<jh:import url="${layout}" />