<%@ tag body-content="scriptless" pageEncoding="euc-kr"%>
<%@ tag import="java.util.List" %>
<%@ tag import="maf.web.tags.old.MiHtmlTag" %>
<%@ tag import="maf.web.mvc.beans.MvcInfo" %>
<%@ tag import="maf.web.mvc.beans.SiteInfo" %>
<%@ tag import="maf.configuration.CodeInfo" %>
<%@ tag import="maf.configuration.CodeDetailBean" %>
<%@ tag import="maf.logger.Logging" %>
<%@ tag import="maf.web.context.MStore" %>
<%@ attribute name="groupCd" required="true"%>
<%@ attribute name="value" %>
<%@ attribute name="site"%>
<%
	MvcInfo maf_info = (MvcInfo) request.getAttribute("MAF_INFO");
	StringBuffer sHtml = new StringBuffer(100);
	
	List list = null;
	CodeDetailBean ob = null;
	if(site == null) {
		SiteInfo t =  maf_info.getSiteInfo();
		if (t != null) {
			site = t.getSite();
		}
	}
	try {
		CodeInfo t = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
		list =  t.getCodeList(groupCd);
		if(list != null) {
			for(int i = 0; i<list.size() ; i++) {
				ob = (CodeDetailBean) list.get(i);
				sHtml.append(MiHtmlTag.getHtmlOption(ob.getCodeNo(),ob.getCodeName(),value));
			}
		}
	} catch (Throwable t) {

	}
	
%>
<%=sHtml.toString()%>	