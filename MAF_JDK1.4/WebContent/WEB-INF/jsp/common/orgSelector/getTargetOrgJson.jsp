<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="maf.web.mvc.view.*"%>
<%@ page import="modules.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    response.setContentType(JsonViewer.CONTENTS_TYPE);
    response.setHeader("Cache-Control", "no-cache");
    
	BaseHttpSession sessionBean = MySession.getSessionBean(request, response);
    
	final String sql_rg = " SELECT   DECODE (org_type,"   +
    	"                  'DI', '[' || NVL (SUBSTR (na.code_name, 0, 10), nation) || '] ' || org_name,"   +
    	"                  org_name"   +
    	"                 ) org_name,"   +
    	"          bas_org.org_cd"   +
    	"     FROM bas_org, (SELECT *"   +
    	"                      FROM maf_code_det"   +
    	"                     WHERE group_cd = 'NATION_CODE') na"   +
    	"    WHERE p_org_cd = :region"   +
    	"      AND bas_org.nation = na.code_no(+)"   +
    	" 	 AND active_flag = 'Y'"   +
    	" ORDER BY org_name"  ;
    
	
	    
	MdbDriver oDb = null;
	JSONArray jarr = null;
    String region = request.getParameter("region");

	try {
		oDb = Mdb.getMdbDriver();
        List list = null;
        Map param = new HashMap();
        param.put ("region", region);
       	list = oDb.selector(Map.class, sql_rg, param);

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

    out.print("}");
%>

