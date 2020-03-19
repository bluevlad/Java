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
//          out.print(",_cmSplit,");
        }            
%>
        [null, '<%=childs[i].getTitle(request)%>','<%=childs[i].getUrl()%>',null,null,
<% 
        //MenuItem[] subChilds = childs[i].getChilds();
        MenuItem[] subChilds = oTM.getChilds(childs[i],sessionBean);
        if(subChilds != null) {
            for(int j = 0; j < subChilds.length; j++ ) {
                 if( subChilds[j].isTopMenu() ) {
                	 %>
                 	[null,'<%=subChilds[j].getTitle(request)%>','<%=subChilds[j].getUrl()%>',null,null],
                 	<%
              	}
            }
        }
%>],<%
    }
} 
%>
];

function doSearch() {
    var frm = getObject("searchform");
    if(frm) {
        frm.submit();
//        return true;
    }     
}

function clearText(thefield){
    if (thefield.defaultValue==thefield.value)
    thefield.value = ""
    } 

//-->
</script>
<div id="div_topMenu"></div>
<div id="div_logo" onClick="goHome()" title="go Home" class="link">MAF</div>
<div id="div_global">
<form id="searchform" name="searchform" method="post" action="/main/search.do" onsubmit="return doSearch();return false;">
<input type="hidden" name="cmd" value="list" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="20">&nbsp;</td>
        <td width="300" height="19">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
	                <td class="right"><mf:input type="text" name="src_txt" onfocus="clearText(this)" cssStyle="width:200px;height:18px;border:3px solid #da5d2e;" value=""/></td>
                    <td width="5">&nbsp;</td>
	                <td><img src='<c:url value="/maf_images/common/btn_srch.gif"/>' onclick="doSearch();" alt="검색"></td>
                </tr>
            </table>
        </td>
        <td width="30">&nbsp;</td>
        <td><a href="javascript:goHome()"><mfmt:message bundle="common.title" key="home" /></a>&nbsp;&nbsp;</td>
        <td>|&nbsp;&nbsp;
            <c:choose>
            <c:when test="${!empty sessionScope.msession}">
                <a href='<c:url value="/logout.do"/>'><mfmt:message bundle="common.title" key="logout" /></a>
             </c:when>
             <c:otherwise>
                <a href='<c:url value="/login.do"/>'><mfmt:message bundle="common.title" key="login" /></a>
             </c:otherwise>
             </c:choose>
        </td>
        <td>|&nbsp;&nbsp;
            <c:choose>
            <c:when test="${!empty sessionScope.msession}">
                <a href='<c:url value="/member/member.do?cmd=edit"/>'><mfmt:message bundle="common.title" key="mypage" /></a>
            </c:when>
            <c:otherwise>
                <a href='<c:url value="/member/member.do"/>'><mfmt:message bundle="common.title" key="join" /></a>
            </c:otherwise>
            </c:choose>
        </td>
        <td>|&nbsp;&nbsp;<a href='<c:url value="/SiteMap.do"/>'><mfmt:message bundle="common.title" key="sitemap" /></a>&nbsp;&nbsp;</td>
        <td>|&nbsp;&nbsp;<mfmt:message bundle="common" key="title.language" />
            <input type="text" name="divLangSelector" id="divLangSelector" value="<mfmt:getLocale type="lang"/>" readonly style="border:none; color:#0d2067;"/></td>
    </tr>
</table>
</form>
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