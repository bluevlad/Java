<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="maf.aam.AAMManager" %>
<%@ page import="maf.aam.AAMLoader" %>
<%@ page import="maf.MafProperties" %>
<%
    AAMManager aam = AAMManager.getInstance();
    AAMLoader aaml =MafProperties.getAAMLoader();
    aaml.reload();
%>