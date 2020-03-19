<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="maf.base.BaseHttpSession"%>
<%@ page import="maf.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ page import="maf.web.session.MySession"%>
<%@ page import="maf.web.mvc.view.*"%>
<%@ page import="modules.common.jason.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    response.setContentType(JsonViewer.CONTENTS_TYPE);
    response.setHeader("Cache-Control", "no-cache");
    
	BaseHttpSession sessionBean = MySession.getSessionBean(request, response);
    
	final String sql_os_crs = "select crs_cd cd, crs_nm nm" +
	" from bas_crs " +
	" where active_yn = 'Y' ";
    
    final String sql_os_sjt = "select b.sjt_cd cd, sjt_nm nm" +
    " from bas_crs a, bas_sjt b " +
    " where a.crs_cd = b.crs_cd " +
    " and a.active_yn = 'Y' " +
    " and a.crs_cd = :cd ";
    
    final String sql_os_lec = "select c.lec_cd cd, c.lec_nm nm" +
    " from bas_crs a, bas_sjt b, bas_lec c " +
    " where a.crs_cd = b.crs_cd " +
    " and b.sjt_cd = c.sjt_cd " +
    " and a.active_yn = 'Y' " + 
    " and b.sjt_cd = :cd ";
    
	MdbDriver oDb = null;
	JSONArray jarr = null;
    String destination = request.getParameter("destination");
    String cd = request.getParameter("cd");
	try {
		oDb = Mdb.getMdbDriver();
        List list = null;
        
            Map param = new HashMap();
            if("os_crs".equals(destination)){
                list = oDb.selector(Map.class, sql_os_crs);
            }else if("os_sjt".equals(destination)){
                param.put("cd", cd);
                list = oDb.selector(Map.class, sql_os_sjt, param);
            }else if("os_lec".equals(destination)){
                param.put("cd", cd);
                list = oDb.selector(Map.class, sql_os_lec, param);
            } else {
                list = oDb.selector(Map.class, sql_os_crs);
                destination = "os_crs";
            }

        if (list != null) {
             jarr = new JSONArray();
            for(int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                Map rec = (Map) list.get(i);
                obj.put("cd",rec.get("cd"));
                obj.put("nm",rec.get("nm"));
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

    out.print("{\"list\":");
    if(jarr != null) {
    	 out.print(jarr.toString());
    }
    out.print(",\"destination\":\"" +destination +"\"" );
    out.print("}");
%>