/*
 * Created on 2006. 09. 20
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.sgen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.CookieUtil;
import miraenet.app.dbadmin.beans.ColumnBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NeoSgen extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	private String packageName = null; 
	private String className = null; 
    
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.packageName = _req.getP("packageName", CookieUtil.getValue(_req, "packageName"));
		this.className = StringUtils.capitalize(_req.getP("className", CookieUtil.getValue(_req, "className")));
		
		CookieUtil.setCookieValue(_res, "packageName", this.packageName);
		CookieUtil.setCookieValue(_res, "className", this.className);
		_req.setAttribute("packageName", this.packageName);
		_req.setAttribute("className", this.className);
		String param = _req.getP("colinfo");
		List selColumnsList = makeColumnInfo(param);
		result.setAttribute("selColumns", selColumnsList);
		result.setAttribute("columns", selColumnsList);
	}
	
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {

	}	

	public void getJavaSource(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {

		String mCmd = _req.getP("cmd","default");

				String mTableName=StringUtils.replace("nnn","_","");

				String fileName = "";
				if("JavaAction".equals(mCmd)) {
					fileName=mTableName + "Action.java";
				}else if ( "JavaDB".equals(mCmd)) {
					fileName=mTableName + "DB.java";		
				}else if ( "JavaActionConfig".equals(mCmd)) {
					fileName=mTableName + ".xml";	
				}else if ( "JavaResourceConfig".equals(mCmd)) {
					fileName="table."+mTableName + ".xml";					
				}else if ( "JspList".equals(mCmd)) {
					fileName="list.jsp";					
				}else if ( "JspView".equals(mCmd)) {
					fileName="view.jsp";					
				}else if ( "JspEdit".equals(mCmd)) {
					fileName="edit.jsp";					
				}else if ( "JspAdd".equals(mCmd)) {
					fileName="add.jsp";
				}
//				result.setFilename(fileName);

				result.setAttribute("tabsname", "dual");	
		result.setAttribute("source", "t");
		result.setForward(mCmd);

	}
	
	/**
	 * 넘겨온 스트링을 기준으로 column 정보를 만듬.
	 * @param param
	 * @return
	 */
	private List makeColumnInfo(String param) {
		List list = new ArrayList();
		if(param != null) {
			param.replaceAll("(\015\012)|(\015)|(\012)", "");
			String[] columns = param.split(",");
			for(int i =0; i < columns.length; i ++) {
				String[] t = StringUtils.delimitedListToStringArray(columns[i], "|");
				if ( t != null) {
					ColumnBean b = new ColumnBean();
					b.setName(t[0]);
					if(t.length > 1) {
						b.setComments(t[1]);
					}
					if(t.length > 2) {
						String opt = t[2];
						if(opt.indexOf("L") >= 0 ) {
							b.setList(true);
						}
						if(opt.indexOf("S") >= 0 ) {
							b.setSearch(true);
						}
						if(opt.indexOf("P") >= 0 ) {
							b.setPk("1");
						}
					}
					list.add(b);
				}
			}
		}
		return list;
	}
}

