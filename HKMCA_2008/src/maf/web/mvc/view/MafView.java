/*
 * Created on 2006. 11. 09
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.view;

import java.util.HashMap;
import java.util.Map;

import maf.exception.MafException;


public class MafView {
	public static final String VIEW_TYPE_HTML = "html"; 
	public static final String VIEW_TYPE_JPDF = "jpdf"; 
	public static final String VIEW_TYPE_XML = "xml"; 
	public static final String VIEW_TYPE_EXCEL = "excel"; 
	public static final String VIEW_TYPE_DUMMY = "dummy"; 
	public static final String VIEW_TYPE_JSON = "json"; 

	public static final String DEFAULT_VIEW_TYPE = VIEW_TYPE_HTML; 
	private Map viewerMap = null;
	private static MafView instance = null; 
	
	private MafView() {
		viewerMap = new HashMap();
		viewerMap.put(VIEW_TYPE_HTML,HtmlViewer.class);
		viewerMap.put(VIEW_TYPE_JPDF,JasperPDFViewer.class);
		viewerMap.put(VIEW_TYPE_XML,XmlViewer.class);
		viewerMap.put(VIEW_TYPE_EXCEL,ExcelViewer.class);
		viewerMap.put(VIEW_TYPE_DUMMY,DummyViewer.class);
		viewerMap.put(VIEW_TYPE_JSON,JsonViewer.class);
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

