/*
 * Created on 2006. 11. 08
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view.support;

import java.util.List;
import java.util.Map;

import maf.MafUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ViewDataSource implements JRDataSource{
	private List data = null;
	private int index = 0;
	private Map record = null;
	/**
	 * Data Source에 Data를 지정한다.
	 * @param dataList
	 */

	public ViewDataSource(List dataList) {
		this.data = dataList;
	}
	public Object getFieldValue(String arg0) throws JRException {
		if(record != null && record instanceof Map) {
			return MafUtil.nvl(record.get(arg0),"");
		} else {
			return "";
		}
	}	
	
	public Object getFieldValue(JRField arg0) throws JRException {
		if(record != null && record instanceof Map) {
			
			return MafUtil.nvl(record.get(arg0.getName()),"");
		} else {
			return "";
		}
	}

	public boolean next() throws JRException {
		if (data == null || data.size() < index) {
			return false;
		}
		
		try {
			record = (Map) data.get(index++);
			return true;
		} catch (IndexOutOfBoundsException _ignore) {
			return false;
		} 
	}
	

}

