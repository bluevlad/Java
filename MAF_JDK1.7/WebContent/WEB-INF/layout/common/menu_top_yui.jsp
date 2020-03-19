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
<div id="div_logo" onClick="goHome()" title="go Home" class="link">
</div>
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
        <td><a href='<c:url value="/sitemap.jsp"/>'><img src='<c:url value="/images/global/sitemap.gif"/>' border="0" title="sitemap"/></a></td>
        <td><a href='mailto:xkim@kim.comx'><img src='<c:url value="/images/global/contact.gif"/>' border="0" title="contact"/></a></td>
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
        });
    }
</script>
<div id="divLangList" style="">
            Loading...
</div>
<div id="div_topMenu">
<div id="hkmcaTopMenu" class="yuimenubar">
    <div class="bd">
        <ul class="first-of-type">
            <% for(int i = 0; i < childs.length; i++ ) { 
                if( childs[i].isTopMenu() ) { %>
            <li class="yuimenubaritem <%=(i==0)?"first-of-type":""%>"><%=oTM.genMenuHref(childs[i],"T",request)%>
            <% 
                MenuItem[] subChilds = childs[i].getChilds();
                if(subChilds != null) {
            %>
                <div id="<%=childs[i].getPgid()%>" class="yuimenu">
                <div class="bd">
                    <ul>
                    <%for(int j = 0; j < subChilds.length; j++ ) {
                           if( subChilds[j].isTopMenu() ) {%>
                        <li class="yuimenuitem "><%=oTM.genMenuHref(subChilds[j],"T", request)%></li>
                    <%  }
                    }%>
                    </ul>
                 </div>
                 </div>
                <%}%></li><%
                }
            } %>
        </ul>
    </div>
</div>
</div>
      
<!-- Namespace source file -->
<script type="text/javascript" src="/hkmca/js/yui/yahoo/yahoo.js"></script>
<!-- Dependency source files -->
<script type="text/javascript" src="/hkmca/js/yui/event/event.js"></script>
<script type="text/javascript" src="/hkmca/js/yui/dom/dom.js"></script>
<!-- Container source file -->
<script type="text/javascript" src="/hkmca/js/yui/container/container_core.js"></script>
<!-- Menu source file -->
<script type="text/javascript" src="/hkmca/js/yui/menu/menu.js"></script>
<!-- Page-specific script -->
<script type="text/javascript">
    YAHOO.example.onMenuBarReady = function() {
        // Instantiate and render the menu bar
        var oMenuBar = new YAHOO.widget.MenuBar("hkmcaTopMenu", { autosubmenudisplay:true, showdelay:10, hidedelay:150, lazyload:true });
        oMenuBar.render();
    };
    // Initialize and render the menu bar when it is available in the DOM
    YAHOO.util.Event.onContentReady("hkmcaTopMenu", YAHOO.example.onMenuBarReady);
</script>


