<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page language="java"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="maf.web.mvc.*"%>
<%@page import="maf.menu.*"%>
<%@page import="maf.web.mvc.beans.*"%>
<%
    BaseHttpSession sessionBean =  MySession.getSessionBean( request, response );
    TreeMenu oTM = TreeMenu.getInstance();
    MenuItem[] childs = oTM.getTopMenus(sessionBean, request);
%>   
<script type='text/javascript' language='JavaScript'>
<!--
var ntopMenu = 
[
<% 
boolean first = true;
for(int i = 0; i < childs.length; i++ ) { 
    if( childs[i].isTopMenu() ) { 
        if(first) {
            first = false;
        } else {
            out.print(",_cmSplit,");
        }            
%>
        [null, '<%=childs[i].getTitle(request)%>','<%=childs[i].getUrl()%>',null,null,
<% 
        //MenuItem[] subChilds = childs[i].getChilds();
        MenuItem[] subChilds = oTM.getChilds(childs[i],sessionBean);
        if(subChilds != null) {
            for(int j = 0; j < subChilds.length; j++ ) {
                 if( subChilds[j].isTopMenu() ) {
                 	if(subChilds[j].isServlet()){%>
                 	[null,'<%=subChilds[j].getTitle(request)%>','<%=subChilds[j].getUrl()%>',null,null],
                 	<%}	else {%>
                 	[null,'<%=subChilds[j].getTitle(request)%>','<%=subChilds[j].getPage()%>','L',null],
                 	<%}
              	}
            }
        }
%>],<%
    }
} 
%>
];
//--></script>
<div id="div_topMenu"></div>
<div id="div_logo" onClick="goHome()" title="go Home" class="link"></div>
<div id="div_global">
<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td><img src='<c:url value="/images/global/home.gif"/>' onClick="goHome()" border="0" title="go home" class="link"/></td>
        <td><c:choose>
            <c:when test="${!empty sessionScope.msession}">
                <a href='<c:url value="/logout.do"/>'><img src='<c:url value="/images/global/logout.gif"/>' border="0" title="logout"/></a>
             </c:when>
             <c:otherwise>
                <a href='<c:url value="/login.do"/>'><img src='<c:url value="/images/global/login.gif"/>' border="0" title="login"/></a>
             </c:otherwise>
             </c:choose></td>
        <td><a href='<c:url value="/web/etc/sitemap.jsp"/>'><img src='<c:url value="/images/global/sitemap.gif"/>' border="0" title="sitemap"/></a></td>
        <td><a href='<c:url value="/web/etc/contact.jsp"/>'><img src='<c:url value="/images/global/contact.gif"/>' border="0" title="contact"/></a></td>
        <td ><img src='<c:url value="/images/global/language.gif"/>' id="languageSelector" border="0" title="change language" class="link" /> 
        <td nowrap style="font-size:11px;color:#ffffff" >
        <input type="text" name="divLangSelector"  id="divLangSelector" value="<mfmt:getLocale  type="lang"/>" readonly style="border:none"/></td>
    </tr>
</table>
</div>
<script>
    var t = getObject("divLangSelector");
    if(t) {
        Event.observe(window, 'load', function() {
            Event.observe('divLangSelector', 'focus', LangSelector.showLangList  );
            Event.observe('divLangSelector', 'blur', LangSelector.hideLangList );
            Event.observe('divLangList', 'mouseover', LangSelector.overLangSel); 
            Event.observe('divLangList', 'mouseout', LangSelector.outLangSel); 
            cmDraw ('div_topMenu', ntopMenu, 'hbr', cmThemeGray,'ThemeGray');
        });
    }
</script>
<div id="divLangList" style="">
            Loading...
</div>