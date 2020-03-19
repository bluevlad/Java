<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="jmf.aam.AAMManager" %>
<%@ page import="jmf.aam.AAMLoader" %>
<%@ page import="jmf.MafProperties" %>
<%
    AAMManager aam = AAMManager.getInstance();
    AAMLoader aaml =MafProperties.getAAMLoader();
    aaml.reload();
%>