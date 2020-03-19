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
    
	final String sql_rg = " select org_cd , org_name  from bas_org"
	        + " where org_type = 'RG'"+
	    	" ORDER BY org_name"  ;
    
	final String sql_normal =" SELECT   org_cd,"   +
    	"          DECODE (org_type,"   +
    	"                  'DI', '[' || SUBSTR (nation, 1, 10) || '] ' || org_name,"   +
    	"                  org_name"   +
    	"                 ) org_name"   +
    	"     FROM bas_org"   +
    	"    WHERE p_org_cd = (SELECT DECODE (org_type, 'TC', region, org_cd)"   +
    	"                        FROM bas_org"   +
    	"                       WHERE org_cd = :p_org_cd)"   +
    	" ORDER BY org_name"  ;
    final String sql_normal_kia =" SELECT   org_cd,"   +
        "          DECODE (org_type,"   +
        "                  'DI', '[' || NVL (SUBSTR (na.code_name, 0, 10), nation) || '] ' || org_name,"   +
        "                  org_name"   +
        "                 ) org_name"   +
        "     FROM bas_org, (SELECT *"   +
        "                      FROM maf_code_det"   +
        "                     WHERE group_cd = 'NATION_CODE') na"   +
        "    WHERE p_org_cd = (SELECT DECODE (org_type, 'TC', region, org_cd)"   +
        "                        FROM bas_org"   +
        "                       WHERE org_cd = :p_org_cd)"   +
        "      AND bas_org.nation = na.code_no(+)"   +
        " ORDER BY org_name"  ;
    
//    "           or  org_cd = :p_org_cd and p_org_cd is null    "+
	    
	MdbDriver oDb = null;
	JSONArray jarr = null;
    String destination = request.getParameter("destination");
    String p_org = request.getParameter("p_org");
    String nextcode = request.getParameter("nextcode");
    String nextname = request.getParameter("nextname");
	try {
		oDb = Mdb.getMdbDriver();
        List list = null;
        if(MafUtil.empty(p_org) && MafUtil.empty(destination)) {
    		list = oDb.selector(Map.class, sql_rg);
        } else {
            Map param = new HashMap();
            param.put("p_org_cd", p_org);
            if("hmc".equals(Project.COMPANY)) {
        	   list = oDb.selector(Map.class, sql_normal,param);
            } else {
            	list = oDb.selector(Map.class, sql_normal_kia,param);
            }
        }
		if (list != null) {
             jarr = new JSONArray();
             if("os_region".equals(destination)) {
            	 if("hmc".equals(Project.COMPANY)) {
            		 if("HMC00".equals(p_org) ) {
                         Map rec = new HashMap();
                         JSONObject obj = new JSONObject();                     
                         obj.put("code","HMC00");
                         obj.put("name","HYUNDAI MOTOR COMPANY");
                         jarr.add(obj);
            		 }
            	 } else {
            		 if("KMC00".equals(p_org)) {
                	 Map rec = new HashMap();
                	 JSONObject obj = new JSONObject();
                     obj.put("code","KMC00");
                     obj.put("name","Kia Motors Corp.");
                     jarr.add(obj);
            		 }
                 }
             }

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

