<%@ tag body-content="scriptless" pageEncoding="euc-kr"%>
<%@ tag import="maf.web.mvc.beans.SiteInfo" %>
<%@ tag import="maf.web.mvc.beans.MvcInfo" %>
<%@ tag import="maf.configuration.TableDictionaryBean" %>
<%@ tag import="maf.configuration.TableDictionary" %>
<%@ tag import="maf.web.context.MStore" %>
<%@ attribute name="key" required="true"%>
<%@ attribute name="site"%>
<%@ attribute name="type"%>
<%
	MvcInfo maf_info = (MvcInfo) request.getAttribute("MAF_INFO");
	TableDictionaryBean rv = null;
	if(site == null) {
		SiteInfo t =  maf_info.getSiteInfo();
		if (t != null) {
			site = t.getSite();
		}
	}
	try {
		TableDictionary t = (TableDictionary) MStore.getInstance().getConfig(MStore.KEY_TABLE_DICTIONARY);
		rv =  t.getInfo(site, key);
	} catch (Throwable t) {
	}

%>
<%=rv%>	