<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page language="java"%>
<%@page import="jmf.base.BaseHttpSession"%>
<%@page import="jmf.web.session.MySession"%>
<%@page import="jmf.web.mvc.*"%>
<%@page import="jmf.menu.*"%>
<%@page import="jmf.web.mvc.beans.*"%>
<%
    BaseHttpSession sessionBean =  MySession.getSessionBean( request, response );
    TreeMenu oTM = TreeMenu.getInstance();
    MenuItem[] childs = oTM.getTopMenus(sessionBean, request);
%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Network Throughput: Local Hosts - Data Received</TITLE>
<META HTTP-EQUIV=REFRESH CONTENT=120>
<META HTTP-EQUIV=Pragma CONTENT=no-cache>
<META HTTP-EQUIV=Cache-Control CONTENT=no-cache>
<link rel="stylesheet" href="theme.css" TYPE="text/css">
<script type="text/javascript" language="JavaScript" SRC="theme.js"></script>
<script type="text/javascript" language="JavaScript" SRC="JSCookMenu.js"></script>
<script type="text/javascript" language="JavaScript"><!--
var ntopMenu = 
[
<% 
for(int i = 0; i < childs.length; i++ ) { 
    if( childs[i].isTopMenu() ) { %>
        [null, '<%=childs[i].getTitle(request)%>','href',null,null,
<% 
        MenuItem[] subChilds = childs[i].getChilds();
        if(subChilds != null) {
            for(int j = 0; j < subChilds.length; j++ ) {
                 if( subChilds[j].isTopMenu() ) {%>
                 [null,'<%=subChilds[j].getTitle(request)%>','',null,null],
<%              }
            }
        }
%>],<%
    }
} 
%>
];


--></script>
</head><body link="blue" vlink="blue">
<table border="0" width="100%" cellpadding="0" cellspacing="0">

 <tr>
  <th class="leftmenuitem">
   <div id="ntopMenuID">Rut Row - bad mojo Scooby!</div>
<script type="text/javascript" language="JavaScript"><!--
        cmDraw ('ntopMenuID', ntopMenu, 'hbr', cmThemeOffice, 'ThemeOffice');
-->
</script></th>

 </tr>
</table>