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
    
	final String sql = " SELECT   *"   +
	"     FROM (SELECT     org_cd, p_org_cd, org_name, org_type, LEVEL lvl"   +
	"                 FROM bas_org"   +
	"           START WITH org_cd = :org_cd"   +
	"           CONNECT BY org_cd = PRIOR p_org_cd) x"   +
	" ORDER BY x.lvl DESC"  ;
	
    
	MdbDriver oDb = null;
	JSONObject jobj = null;
	jobj = new JSONObject();
    String destination = request.getParameter("destination");
    String p_org = request.getParameter("org_cd");
	try {
		oDb = Mdb.getMdbDriver();
        List list = null;
        Map param = new HashMap();
        param.put("org_cd", p_org);
        list = oDb.selector(Map.class, sql,param);
		if (list != null) {
            for(int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                Map rec = (Map) list.get(i);
                obj.put("code",rec.get("org_cd"));
                obj.put("name",rec.get("org_name"));
                jobj.put(rec.get("org_type"), obj);
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

    	 out.print(jobj);

    
%>

