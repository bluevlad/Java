/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view;

import java.util.HashMap;
import java.util.Map;

import maf.exception.MafException;

public class MafView {
	public static final String DEFAULT_VIEW_TYPE = "html"; 
	private Map viewerMap = null;
	private static MafView instance = null; 
	
	private MafView() {
		viewerMap = new HashMap();
		viewerMap.put("html",HtmlViewer.class);
		viewerMap.put("jpdf",JasperPDFViewer.class);
		viewerMap.put("xml",XmlViewer.class);
		viewerMap.put("excel",ExcelViewer.class);
		viewerMap.put("dummy",DummyViewer.class);
	}
	
	private Object _getViewer(String type) throws Exception {
		if(viewerMap.containsKey(type)) {
			Class vclass = (Class) viewerMap.get(type);
			return vclass.newInstance();
		} else {
			throw new MafException(type + " is invalid view type!!!");
		}
	}
	
	public static Viewer getViewer(String type) throws Exception{
		if( instance == null) {
			instance = new MafView();
		}
		return (Viewer) instance._getViewer(type);
	}
}

