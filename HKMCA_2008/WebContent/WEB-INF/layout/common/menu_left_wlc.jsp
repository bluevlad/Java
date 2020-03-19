<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<%@page language="java"%>
<%@page import="maf.web.mvc.*"%>
<%@page import="maf.menu.*"%>
<%@page import="maf.web.mvc.beans.*"%>
<%
    MvcInfo content = (MvcInfo) request.getAttribute("MAF_INFO");
    if (content == null) {
        content = new MvcInfo(request, response);
        SiteInfo site = new SiteInfo();
        String pgid = request.getParameter("pgid");
        site.setSite("www");
        content.setSiteInfo(site);
        content.setPgid(pgid);
    }

    TreeMenu oTM = TreeMenu.getInstance(content.getSite());
//    oTM = (TreeMenu) content.getTreeMenu();
    
    if( oTM == null ) oTM   = TreeMenu.getInstance(content.getSite()); 

    StringBuffer submenu_ids = new StringBuffer (); 
    MenuItem CurMenu = oTM.getMenu(content.getPgid());
    MenuItem oMenu = null;

    if(CurMenu != null) {
        oMenu = oTM.getTopMenu(CurMenu);
    }
    if (oMenu != null ) {
        out.print("<h1>" + oMenu.getTitle(request) + "</h1>");
%>
    <div id="div_lecinfo">[<c:out value="${Lecture.lec_cd}"/>]<br><c:out value="${Lecture.lec_nm}"/></div>
<%
        for(int i = 0; i < oMenu.getChildCount(); i++ ) {
            MenuItem oT = oMenu.getChild(i);
            if (oT != null && oT.isLeftMenu()) {
                if (submenu_ids.length() > 0 ) {
                    submenu_ids.append(",");
                }
                submenu_ids.append("'");
                submenu_ids.append(oT.getPgid());
                submenu_ids.append("'");
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:20px">
    <tr style="cursor:pointer" >
        <td class="menu_l3" onClick="javascript:showMenu('<%=oT.getPgid()%>')" >
        <%
            if(oT.hasChild()) {
                out.print(oT.getTitle(request));
            } else {
                out.print(oTM.genMenuHref(oT,"L", request) );
            }
        %>
        </td>
    </tr>
</table>
<%
            if(oT.hasChild()) {
%>
<div id="l3m_<%=oT.getPgid()%>" style="position:absolute; visibility:hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:30px" >
<%
                for(int j = 0; j < oT.getChildCount(); j++ ) {
                    MenuItem oT2 = oT.getChild(j);
                    if (oT2 != null) {
%> 
                            <tr><td class="menu_l4">&nbsp;<%=oTM.genMenuHref(oT2,"L", request)%></td></tr>
                            <tr><td height="1" ></td></tr>
<%
                    }
                    if(oT2.hasChild()) {
                        out.print(printSub(oT2,  oTM, request));
                    }
                }
%>
</table>
</div>
<%                  
            } // if hasChild
        }
        }
    }
%>
<SCRIPT LANGUAGE="JavaScript">
var submenu_ids = new Array(<%=submenu_ids.toString()%>);
function clearMenu()
{
    for(i=0; i<submenu_ids.length; i++) {
        obj = document.getElementById('l3m_'+submenu_ids[i]);
        if(obj!=null) {
            if(obj.style) {
                obj.style.visibility='hidden';
                obj.style.position = 'absolute';
            }
        }
    }
}
function showMenu(id) {
    clearMenu();
    obj = document.getElementById('l3m_'+id);
    if(obj!=null) {
        if(obj.style) {
            obj.style.visibility='visible';
            obj.style.position = '';
        }
    }   
}

function KMM_openBrWindow(theURL,features) { //v2.0
  var oWin = window.open(theURL,'window',features);
  oWin.focus();
}

addEvent(window,'load',function() {
        showMenu('<%=oTM.getL3PGID(CurMenu)%>');
    });
</script>

<%!private String printSub(MenuItem oT,  TreeMenu oTM,  HttpServletRequest request) {
    StringBuffer sRv = new StringBuffer(20);
    StringBuffer sRv2 = new StringBuffer(20);
    MenuItem oT2;
    
    for(int j = 0; j < oT.getChildCount(); j++ ) { 
        oT2 = oT.getChild(j);
        if (oT2 != null) { 
            if(oT2.isLink() && oT2.isLeftMenu()) {
                sRv.append("<tr><td class='menu_l5'>");
                sRv.append(oTM.genMenuHref(oT2,"L", request) );
                sRv.append("</td></tr>");
            }
        }
    }
    
    if (sRv.length() > 0 ) {
        sRv2.append("<tr><td><table border=0 cellspacing=0 cellpadding=0>");
        sRv2.append(sRv);
        sRv2.append("</table></td></tr>");
    }
    return sRv2.toString();
}%>