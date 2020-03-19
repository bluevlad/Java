/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.i18n;
import maf.mdb.drivers.MdbSetter;
import maf.util.StringUtils;
import maf.web.http.HttpHeaderSender;
import maf.web.http.MimeType;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.view.excel.ExcelDrawer;
import miraenet.app.dbadmin.beans.ColumnBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

import test.Dual_calBean;


/**
 * @author goindole, 2005-02-02
 *  
 */
public class TableInfo extends DbAdminAction {
    final private Log  logger = LogFactory.getLog(TableInfo.class);
    
	private void init (MyHttpServletRequest _req, HttpServletResponse response)  throws MafException {
		List allColumnsList = super.oa.getColumnInfo(super.oDb, super.owner,super.tabsname);
		String[] selColumn = _req.getParameterValues("selColumn");
		String[] listColumn = _req.getParameterValues("listColumn");
		String[] searchColumn = _req.getParameterValues("searchColumn");
		if(searchColumn == null) {
			searchColumn = new String[] {};
		}
		if(listColumn == null) {
			listColumn = new String[] {};
		}
		if(selColumn == null) {
			selColumn = new String[] {};
		}
//		logger.debug("selCOlumns : " + StringUtils.arrayToCommaDelimitedString(selColumn) + "\n" +
//		             "allColumns : "  + allColumnsList );
		ColumnBean row = null;
		List selColumnsList = new ArrayList();
		
		if(selColumn == null) {
			for(int i = 0; i < allColumnsList.size(); i++) {
				row = (ColumnBean) allColumnsList.get(i);
				row.setAttr1("T");
				row.setList(true);
				selColumnsList.add(row);
				
			}
		} else {
			if( allColumnsList != null  ) {
				for(int i = 0; i < allColumnsList.size(); i++) {
					row = (ColumnBean) allColumnsList.get(i);
				
					for(int j = 0; j < listColumn.length; j ++) {
						if(row.getName().equals(listColumn[j])) {
							row.setAttr1("T");
							row.setList(true);
							
						}
					}
					for(int j = 0; j < searchColumn.length; j ++) {
						if(row.getName().equals(searchColumn[j])) {
							row.setAttr1("T");
							row.setSearch(true);
							
						}
					}
					for(int j = 0; j < selColumn.length; j ++) {
						if(row.getName().equals(selColumn[j])) {
							row.setAttr1("T");

							selColumnsList.add(row);
						}
					}	
					
				}
			}
		}

		result.setAttribute("columns", allColumnsList);
		result.setAttribute("selColumns", selColumnsList);

	}
	public void getBeans(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		result.setForward("javabean");
	}
	public void getSQL(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		result.setForward("javabean");
	}
	public void getSTMT(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		Dual_calBean b = new Dual_calBean();
		b.setV_holiday("2004-05-05");
		b.setV_day(1);
		String sql = _req.getP("sql");
		MdbSetter s = new MdbSetter();
		s.setCall(sql, b);
		List where = null;
		if(sql != null) {
	        RE re = new RE ("(:[\\w*]+)|(:[@\\w*]+)");
	        where = new ArrayList();
	        while (re.match(sql)) {
	        	sql = re.subst(sql, "?", RE.REPLACE_FIRSTONLY);
	    
	            String key = re.getParen(0).substring(1);
	            where.add( key);    
	        }
		}
		result.setAttribute("stmt", where);
		result.setForward("javastmt");
	}	
	public void getIndex(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		result.setAttribute("index", super.oa.getIndexInfo(super.oDb, super.owner,
		                           						super.tabsname));
		                           				result.setForward("indexInfo");
	}
	public void getExcel(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		String tables[] = _req.getParameterValues("table_nm");
		Map tableInfo = new HashMap();
		if(tables != null) {

			for(int i=0; i< tables.length; i ++ ) {
				//logger.debug("Table : " + tables[i]);
				tableInfo.put(tables[i], super.oa.getColumnInfo(oDb, super.owner,
					tables[i]) );
			}
		}
		result.setAttribute("tableInfo", tableInfo);
		result.setAttribute("tables", super.oa.getTableList(oDb, super.owner));

//		result.setForward("excel");
		GenExcel dr = new GenExcel(tableInfo , super.oa.getTableList(oDb, super.owner) );
		HttpHeaderSender.setDownLoad(_req, response, "table.xls", true, false);
		
		try {
			ServletOutputStream ouputStream = response.getOutputStream();
			try {
				dr.write(ouputStream);
				ouputStream.flush();
			} finally {
				if (ouputStream != null) {
					try {
						ouputStream.close();
					} catch (IOException ex) {
					}
				}
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			//			logger.error(e.getMessage());
		}
		result.setForward("dummy");
		 
	}	
	public void getForm(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		result.setForward("form");	
	}	
	
	public void getSqlInfo(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		result.setForward("sql");
	}	
	public void getJavaSource(MyHttpServletRequest _req, HttpServletResponse response) throws MafException  {
		this.init(_req, response);
		String mCmd = _req.getP("cmd");
		if("down".equals(_req.getP("type"))) {
			result.setDownload(true);
			result.setUseTemplate(false);
			
			if(tabsname != null) {
				String mTableName=StringUtils.replace(StringUtils.capitalize(tabsname),"_","");

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
				result.setFilename(fileName);
			}
		}
		result.setAttribute("source", "t");
		result.setForward(mCmd);	
	}
	
	
}
