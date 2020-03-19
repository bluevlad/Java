<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page import="maf.mdb.sqlhelper.*"%>
<%@page import="maf.mdb.sqlhelper.support.*"%>

<%
    Map param = new HashMap();
	String[] st = { "a", "b", "c" };
    param.put(st,st);
	SqlWhereBuilder sw = new OracleSqlWhereBuilder();
	sw.addCond(Where.in("tt", ":tt",true));
    out.print("===");
    out.print(sw.getWhereString(param));
    
%>
