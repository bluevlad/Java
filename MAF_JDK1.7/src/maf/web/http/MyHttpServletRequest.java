/*
 * Created on 2006. 5. 9.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.http;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import maf.MafUtil;
import maf.lib.i18n;
import maf.web.context.MafConfig;
import maf.web.multipart.CommonUploadFileParser;
import maf.web.multipart.MultiPartChecker;
import maf.web.multipart.UploadedFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
	// private Logger logger = Logger.getLogger(this.getClass());
	private Log logger = LogFactory.getLog(getClass());

	private boolean multipart = false;

	private int maxPostSize;

	private int totalAttachSize = 0;

	private boolean lconvertCharacterEncoding = true;

	/**
	 * for Multipartrequest
	 */
	private final long DEFAULT_MAX_POST_SIZE = 100 * 1024 * 1024; // 10 Meg

	private final String DEFAULT_CHARACTER_ENCODING = "MS949"; // "KSC5601"; //

	/**
	 * 
	 */
	private HashMap parameterMap = null;

	private HashMap parametersMap = null;

	private HashMap fileItemMap = null;

	public MyHttpServletRequest(HttpServletRequest request) {

		this(request, -1, -1, null);
	}

	public MyHttpServletRequest(HttpServletRequest request, int threshold, int max, String repositoryPath) {
		// request.setCharacterEncoding(this.DEFAULT_CHARACTER_ENCODING);
		super(request);
		this.parsing(request, threshold, max, repositoryPath);
	}

	/**
	 * 
	 * @param request
	 * @param threshold
	 * @param max
	 * @param repositoryPath
	 */
	private void parsing(HttpServletRequest request, int threshold, int max, String repositoryPath) {

		parametersMap = new HashMap();
		parameterMap = new HashMap();
		fileItemMap = new HashMap();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Set factory constraints
		factory.setSizeThreshold(threshold);
//		File file = new File("d:\\tt");
//		factory.setRepository(file);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(DEFAULT_MAX_POST_SIZE);
		try {
			request.setCharacterEncoding(this.DEFAULT_CHARACTER_ENCODING);
		} catch (Exception _ignored) {
		}
		if (MultiPartChecker.isMultiPart(request)) {
			try {
				// Parse the request

				List /* FileItem */items = upload.parseRequest(request);
				if (items != null) {
					// Process the uploaded items
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
						DiskFileItem item = (DiskFileItem) iter.next();

						// processFormField ���� �Ϲ� �ʵ�� file�ʵ� ���� ó��
						// file�� ��� value�� ���ϸ��� ���� 
						this.processFormField(item);
						/*if (item.isFormField()) {
//							logger.debug("    " + item.getFieldName() + " is isFormField" );
							this.processFormField(item);
						} else {
							logger.debug("    " + item.getFieldName() + " is FILE" );
							this.processUploadedFile(item);
							
						}*/
						// request.setAttribute(item.getFieldName(), item);
						item.delete();
					}
				}
			} catch (FileUploadException fe) {
				logger.error(fe.getMessage());
			} catch (Exception ne) {
				throw new RuntimeException(ne);
			}
			multipart = true;
			//addTo();
		} else {
			Enumeration eu = request.getParameterNames();
			if (eu != null) {
				while (eu.hasMoreElements()) {
					String attName = (String) eu.nextElement();
					parametersMap.put(attName, request.getParameter(attName));
					parameterMap.put(attName, request.getParameterValues(attName));
				}
			}
		}

	}

	/**
	 * file ���� �Ϲ����� ���ʵ带 ó�� �Ѵ�.
	 * 
	 * @param item
	 */
	private void processFormField(DiskFileItem item) {
		String value = null;
		if (!item.isFormField()) {
			String fileName = null;
			logger.debug("    " + item.getFieldName() + " is FILE" );
			fileName = this.processUploadedFile(item);
			value = fileName;
		} else {
			value = _ConvertCharacterEncoding(item.getString());
		}
		
		String name = item.getFieldName();
		String[] values = (String[]) this.parameterMap.get(name);
		if (values == null) {
			values = new String[] { value };
		} else {
			String[] tempValues = new String[values.length + 1];
			System.arraycopy(values, 0, tempValues, 0, 1);
			tempValues[tempValues.length - 1] = value;
			values = tempValues;
		}
		// value = request.getParameter(name);
		// values = request.getParameterValues(name);
		parametersMap.put(name, value);
		parameterMap.put(name, values);

	}

	/**
	 * file field�� ó�� �Ѵ�.
	 * @param item
	 */
	private String  processUploadedFile(DiskFileItem item) {
		String name = item.getFieldName();
		String fileName = null;

		UploadedFile uFile = CommonUploadFileParser.Multipart2UploadFile(item);
		if (uFile != null) {
			List existingValues = (List) this.fileItemMap.get(name);
			if (existingValues == null) {
				existingValues = new ArrayList();
				existingValues.add(uFile);
			} else {
				existingValues.add(uFile);
			}
			this.fileItemMap.put(name, existingValues);
			this.totalAttachSize += item.getSize();
			fileName = uFile.getNewfilename();
		}
		return fileName;
	}

	public void addTo() {
		super.setAttribute(this.getClass().getName(), this);
	}

	/**
	 * Key�� Value[] �� ���� �ش�.
	 */
	public Map getParameterMap() {

		return this.parameterMap;
	}

	public String[] getParameterValues(String paramname) {
		if (this.isMultiPartContent()) {
			return (String[]) parameterMap.get(paramname);
		} else {
			return super.getParameterValues(paramname);
		}
	}
	/**
	 * �Ķ���� key�� value ������ Map�� ��� �����ش�. (setter ���) ���� �̸��� ���� ���� ���� ��� ù��° ��
	 * 
	 * @return
	 */
	public final Map getKeyValueMap() {
		return this.parametersMap;
	}

	public final Map getFileParameterMap() {
		return this.fileItemMap;
	}

	/**
	 * getParameter(key)�� Short Type
	 * 
	 * @param key
	 * @return
	 */
	public String getP(String key) {
		return this.getParameter(key);
	}

	/**
	 * getParameter(key,def)�� Short Type
	 * 
	 * @param key
	 * @return
	 */
	public String getP(String key, String def) {
		return this.getParameter(key, def);
	}

	/**
	 * �Ķ������ ���� Null�̰ų� ���̰� 0�̸� def ����
	 * 
	 * @param p0
	 * @param def
	 * @return
	 */
	public String getParameter(String p0, String def) {
		String s = this.getParameter(p0);
		return (s == null || s.length() == 0) ? def : s;
	}

	/**
	 * multi-part �������� ���, ���� �̸����� value�� ������ ������, ","�� ���� �Ǿ� ���´�.
	 */
	public String getParameter(String p0) {
		return (String) parametersMap.get(p0);
	}
	/**
	*  getParameter�� ���� int�� ��ȯ�Ͽ� return�Ѵ�.
	*  Number format�� �ƴϰų�, null�� ���� 0�� return�Ѵ�.
	*/
	public int getIntParameter(String p0) {
		return this.getIntParameter(p0, 0);
	}
	/**
	*  getParameter�� ���� int�� ��ȯ�Ͽ� return�Ѵ�.
	*  Number format�� �ƴϰų�, null�� ���� 0�� return�Ѵ�.
	*/
	public int getIntParameter(String p0, int def) {
		return MafUtil.parseInt(getParameter(p0),def);
	}
	/**
	*  getParameter�� ���� int�� ��ȯ�Ͽ� return�Ѵ�.
	*  Number format�� �ƴϰų�, null�� ���� 0�� return�Ѵ�.
	*/
	public long getLongParameter(String p0) {
		return this.getLongParameter(p0, 0);
	}
	/**
	*  getParameter�� ���� int�� ��ȯ�Ͽ� return�Ѵ�.
	*  Number format�� �ƴϰų�, null�� ���� 0�� return�Ѵ�.
	*/
	public long getLongParameter(String p0, int def) {
		return MafUtil.parseLong(getParameter(p0),def);
	}	
	/**
	 * ������ ���� getSErvletContext()�̿� �ٶ�. String x = getServletContext().getRealPath("\");
	 */
	public String getRealPath(String arg0) {
		return MafConfig.getRealPath(arg0);
	}

	public Map getFileParameterNames() {
		return this.fileItemMap;
	}

	/**
	 * ���� multipart���� ���� 
	 * true: multipart
	 * @return
	 */
	public boolean isMultiPartContent() {
		return this.multipart;
	}
    /**
     * Upload�� ��� ���� newPath�� �����Ѵ�. 
     * @param newPath
     * @return
     * @throws Exception
     */
    public UploadedFile[] FileSaveAllTo( String newPath ) 
    throws Exception {
        return this.FileSaveAllTo(newPath, false);
    }
	/**
	 * Upload�� ��� ���� newPath�� �����Ѵ�.
	 * 
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public UploadedFile[] FileSaveAllTo(String newPath, boolean canOverwrite) throws Exception {
		List rv = new ArrayList();
		
		if (this.fileItemMap != null) {
			Set set = this.fileItemMap.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				logger.debug("name : " + me.getKey() + ", FileName = " + me.getValue());
				List files = (List) me.getValue();
				if(files != null) {
					for (int j = 0 ; j < files.size(); j ++ ) {
						UploadedFile uf = (UploadedFile) files.get(j);
						if (uf != null ) {
							uf.SaveTo(newPath);
						}
					}
				}
			}
		}
		return (UploadedFile[]) rv.toArray(new UploadedFile[0]);
	}

	/**
	 * file paramer�� name�� ���� ù��°���� �Ѱ��ش�. 
	 * @param name
	 * @return
	 */
	public UploadedFile getFileParameter(String name) {
		try {
			List values = (List) fileItemMap.get(name);
			if (values == null || values.size() == 0) {
				return null;
			}
			return (UploadedFile) values.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public UploadedFile[] getFileParameters(String p0) {
		try {
			List files = (List)fileItemMap.get(p0);
			if(files != null) {
				return (UploadedFile[]) files.toArray(new UploadedFile[0]);
			} else {
				return null;
			}
		} catch (Exception e) {
			return new UploadedFile[0];
		}
	}

	/**
	 * �� ÷������ �뷮 (KB)
	 * 
	 * @return
	 */
	public int getTotalAttachSize() {
		return totalAttachSize / 1024;
	}

	/**
	 * ÷�ε� ��� �ӽ������� �����.
	 * 
	 */
	public void deleteAllAttachFile() {
		if (fileItemMap != null) {
			Set set = this.fileItemMap.keySet();
			if (set != null) {
				Iterator i = set.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();

					List files = (List) me.getValue();
					if (files != null) {
						for (int j = 0; j < files.size(); j++) {
							UploadedFile t = (UploadedFile) files.get(j);
							try {
								t.deleteFile();
							} catch (Exception _ignored) {
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * �ش� Ű���� �Ѿ� �Դ��� Ȯ�� 
	 * @param Key
	 * @return
	 */
	public boolean containsKey(String Key) {
		if (parametersMap.containsKey(Key)) {
			return true;
		} else if(fileItemMap.containsKey(Key)){
			return true;
		} else{
			return false;
		}
		
	}
	private String _ConvertCharacterEncoding(String str) {
		if (str == null) return null;
		if (lconvertCharacterEncoding) {
			return i18n.convertTo(str, DEFAULT_CHARACTER_ENCODING);
		}
		return str;
	}
}
