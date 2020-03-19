/*
 * Created on 2005. 11. 7.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import maf.base.BaseObject;
import maf.web.http.UrlAddress;
import maf.web.mvc.view.MafView;
import maf.web.mvc.view.ViewerSupport;
import modules.common.jason.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleResult extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1876114697624613808L;
	private final Log logger = LogFactory.getLog(SimpleResult.class);
	// ���� ����
	private boolean success = true;

//	// �ѷ��� �޽��� Key
//	protected String message = null;
	private ResultMessage message = null;

	// request�� ����� ����
	private HashMap resultMap = null;

	// View page (view-info name); 
	private String forward = "error"; 

	// �޽��� �ѷ��ְ� ����
	private String next = null;
	
	// Href�� window���� target(message �� error�� ��츸 )
	private String target = null;
	
	// Request�� �� Parameter
	private Map param = null;
	
	private String contentsType = "HTML";
	private String filename = null;
	private boolean download = false;
	private boolean cache = false;
	private boolean useTemplate = true;
	
	private List dataSource = null;
	
//	public SimpleResult(MyHttpServletRequest _req) {
//		this.param = _req.getKeyValueMap();
//	}

	public Object get(String key) {
		if (resultMap == null || key == null)
			return null;
		return resultMap.get(key);
	}


//	public void setMessage(String bundleName, String message, String[] param) {
//		this.message = message;
//	}
	public void setMessage(ResultMessage message) {
		this.message = message;
	}
	/**
	 * ������ ���� ���θ� ����
	 * false�� ��� forward�� error�� �ٲ�
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
		forward = (success)?"message":"error";
	}

	/**
	 *  ���࿩�ο� �޽����� ����
	 *  1) success�� true => forward�� message, 
	 *  2) success�� fasel => forward�� error
	 *  *** success�� ��� setNext�� ������ �ֱ� �ٶ�.
	 * @param success
	 * @param message
	 */
	public void setSuccess(boolean success, ResultMessage message) {
		this.success = success;
		this.message = message;
		forward = (success)?"message":"error";
	}

	/**
	 * 
	 * @param e
	 * @param message ����ڿ��� �ѷ��� �޽��� 
	 */
	public void setError(Throwable e, ResultMessage message) {
		this.success = false;
		this.message = message;
	}
	
	public void setError(Throwable e) {
		this.success = false;

	}
	
	public String  getMessage(HttpServletRequest _req) {
		if(this.message != null) {
			return this.message.getMessage(_req);
		} else {
			return null;
		}
	}

	public boolean isSuccess() {
		return this.success;
	}
	
	public boolean haveMessage() {
		return (this.message != null) ? true:false;
	}

	public void setAttribute(String key, Object obj) {
		if (key == null || obj == null)
			return;
		if (resultMap == null) {
			resultMap = new HashMap();
		}
		resultMap.put(key, obj);
	}

	public Set entrySet() {
		if (resultMap != null) {
			return resultMap.entrySet();
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the forward.
	 */
	public String getForward() {
		return forward;
	}


	/**
	 * @param forward The forward to set.
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}

	/**
	 * @return Returns the next.
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @param next The next to set.
	 */
	public void setNext(String next) {
		this.next = next;
	}
	/**
	 * @param next The next to set.
	 */
	public void setNext(UrlAddress nextUrl) {
		if(nextUrl != null) {
			this.next = nextUrl.getHref();
		}
	}
	public void destroy() {
		if (resultMap != null) {
			resultMap.clear();
			resultMap = null;
		}
		if( param != null) {
			param.clear();
			param = null;
		}
	}

	/**
	 * @return Returns the target.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target The target to set.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return Returns the param.
	 */
	public Map getParam() {
		return param;
	}
	
	public String getP(String key) {
		if(param != null) {
			return  (String) param.get(key);
		} else {
			return null;
		}
	}


	/**
	 * @return the cache
	 */
	public boolean isCache() {
		return cache;
	}


	/**
	 * @param cache the cache to set
	 */
	public void setCache(boolean cache) {
		this.cache = cache;
	}


	/**
	 * @return the contentsType
	 */
	public String getContentsType() {
		return contentsType;
	}


	/**
	 * @param contentsType the contentsType to set
	 */
	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}




	/**
	 * @return the download
	 */
	public boolean isDownload() {
		return download;
	}


	/**
	 * @param download the download to set
	 */
	public void setDownload(boolean download) {
		this.download = download;
	}


	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}


	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}


	/**
	 * @return the useTemplate
	 */
	public boolean isUseTemplate() {
		return useTemplate;
	}


	/**
	 * @param useTemplate the useTemplate to set
	 */
	public void setUseTemplate(boolean useTemplate) {
		this.useTemplate = useTemplate;
	}
	/**
	 * View �ܿ� datasource�� �Ѱ��� 
	 * @param data
	 */
	public void setDataSource(List data) {
		this.dataSource = data;
	}
	
	public List getDataSource() {
		return this.dataSource;
	}
	
	/**
	 * dataSource�� ���� ���θ� ������ 
	 * @return
	 */
	public boolean hasDataSource() {
		if(this.dataSource == null ) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * XML������ �����ϰ� ContentsType�� xml�� �ٲ� �ش�. 
	 * @param doc
	 */
	public void setXmlDoc(org.jdom.Document doc) {
		this.setAttribute(ViewerSupport.XML_DOC_KEY, doc);
		this.setContentsType(MafView.VIEW_TYPE_XML);
	}
	
	/**
	 * JSON������ �����Ѵ�.. 
	 * @param doc
	 */
	public void setJson(JSONObject doc) {
		this.setAttribute(ViewerSupport.JSON_KEY, doc);
		
	}
}

