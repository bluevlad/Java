<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="maf.web.mvc.view.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    response.setContentType(JsonViewer.CONTENTS_TYPE);
    response.setHeader("Cache-Control", "no-cache");
    
	BaseHttpSession sessionBean = MySession.getSessionBean(request, response);
    
	final String sql_rg = " select org_cd , org_name  from bas_org"
	        + " where org_type = 'RG'"+
	    	" ORDER BY org_name"  ;
    
	final String sql_normal = " SELECT   org_cd, org_name"   +
    	"     FROM bas_org"   +
    	"    WHERE p_org_cd = (select decode(org_type,'TC',region,org_cd) from bas_org where org_cd = :p_org_cd)"+
    	" ORDER BY org_name"  ;
    
    
    
	MdbDriver oDb = null;
	JSONArray jarr = null;
    String destination = request.getParameter("destination");
    String p_org = request.getParameter("p_org");
    String nextcode = request.getParameter("nextcode");
    String nextname = request.getParameter("nextname");
	try {
		oDb = Mdb.getMdbDriver();
        List list = null;
        if(MafUtil.empty(p_org) && MafUtil.empty(destination) ) {
    		list = oDb.selector(Map.class, sql_rg);
        } else {
            Map param = new HashMap();
            param.put("p_org_cd", p_org);
        	list = oDb.selector(Map.class, sql_normal,param);
        }
		if (list != null) {
             jarr = new JSONArray();
            for(int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                Map rec = (Map) list.get(i);
                obj.put("code",rec.get("org_cd"));
                obj.put("name",rec.get("org_name"));
                jarr.add(obj);
            }
		}
	} finally {
		try {
			oDb.close();
		} catch (Exception ex) {
		}
		oDb = null;
	}
//	JSONObject rv = new JSONObject();
//    rv.put("list", jarr);
//    out.print(rv.toString());   
    out.print("{\"list\":");
    if(jarr != null) {
    	 out.print(jarr.toString());
    }
    out.print(",\"destination\":\"" +destination +"\"" );
    out.print(",\"nextcode\":\"" +nextcode +"\"" );
    out.print(",\"nextname\":\"" +nextname +"\"" );
    out.print("}");
%>

