/*
 * Created on 2006. 5. 9.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.http;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.exception.MyHttpServletRequestException;
import maf.web.multipart.UploadedFile;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;

public class MyHttpServletRequest extends BaseMyHttpServletRequest {
	
	private boolean multipart = false;
	private int totalAttachSize = 0;
	
	
	/**
	 * 
	 */
	private Map parameterMap = null;
	private Map parametersMap = null;
	private Map fileItemMap = null;

	/**
	 * 
	 * @param HttpServletRequest request 
	 * @throws MyHttpServletRequestException
	 */
	public MyHttpServletRequest(HttpServletRequest request) throws MyHttpServletRequestException  {
		this(request, DEFAULT_THRESHOLD_SIZE, DEFAULT_MAX_POST_SIZE, null, null);
	}
	
	/**
	 * 
	 * @param request
	 * @param fileSizeMax �ִ� ���� ������ 
	 * @throws MyHttpServletRequestException
	 */
	public MyHttpServletRequest(HttpServletRequest request, long fileSizeMax) throws MyHttpServletRequestException  {
		this(request, DEFAULT_THRESHOLD_SIZE, fileSizeMax, null, null);
	}
	
	/**
	 * 
	 * @param request
	 * @param fileSizeMax
	 * @param progressListener (ProgressListener)
	 * @throws MyHttpServletRequestException
	 */
	public MyHttpServletRequest(HttpServletRequest request, long fileSizeMax, ProgressListener progressListener) throws MyHttpServletRequestException  {
		this(request, DEFAULT_THRESHOLD_SIZE, fileSizeMax, null, progressListener);
	}

	/**
	 * 
	 * @param request
	 * @param threshold �޸𸮿�  ������  �ִ�  size
	 * @param fileSizeMax �ִ� ���� ������  //�ӽ� ������ ��ġ
	 * @param repositoryPath 
	 * @throws MyHttpServletRequestException
	 */
	public MyHttpServletRequest(HttpServletRequest request, int threshold, long fileSizeMax,
	        File repositoryPath, ProgressListener progressListener) throws MyHttpServletRequestException {
		super(request);
		Map result = null;
		try {
			result = super.parsing(request, threshold, fileSizeMax, repositoryPath, progressListener);
		} catch (FileUploadException e ) {
			throw new MyHttpServletRequestException(e);
		}
		this.parameterMap = (Map) result.get(super.KEY_PARAMETER_MAP);
		this.parametersMap = (Map) result.get(super.KEY_PARAMETERS_MAP);
		this.fileItemMap = (Map) result.get(super.KEY_FILEITEM_MAP);
	}

	/**
	 * Key�� Value[] �� ���� �ش�. !!! ���� value�� Array�� ������ 
	 */
	public final Map getParameterMap() {
		return this.parameterMap;
	}

	/**
	 * �ش� Key�� ������ String[]���� �����ش�. 
	 * **���� : ���� ���� ������ null�� ���� �д�.
	 */
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

	
	/**
	 * UploadedFile�� List�� Map���·� ������ 
	 * @return
	 */
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
		return MafUtil.parseInt(getParameter(p0), def);
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
		return MafUtil.parseLong(getParameter(p0), def);
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
	public UploadedFile[] FileSaveAllTo(String newPath) throws Exception {
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
		return Util._FileSaveAllTo(fileItemMap, newPath, canOverwrite);
	}

	/**
	 * file paramer�� name�� ���� ù��°���� �Ѱ��ش�. 
	 * @param name
	 * @return
	 */
	public UploadedFile getFileParameter(String name) {
		try {
			List values = (List) this.fileItemMap.get(name);
			if (values == null || values.size() == 0) {
				return null;
			}
			return (UploadedFile) values.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param p0
	 * @return
	 */
	public UploadedFile[] getFileParameters(String p0) {
		try {
			List files = (List) this.fileItemMap.get(p0);
			if (files != null) {
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
		Util._deleteAllAttachFile(fileItemMap);
	}

	/**
	 * �ش� Ű���� �Ѿ� �Դ��� Ȯ�� 
	 * @param Key
	 * @return
	 */
	public boolean containsKey(String Key) {
		if (parametersMap.containsKey(Key)) {
			return true;
		} else if (fileItemMap.containsKey(Key)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * parameter�� key ����� ���� �ش�.
	 * @return
	 */
	public final String[] getKeys() {
		if( parametersMap != null) {
			Set set =  parametersMap.keySet();
			return StringUtils.toStringArray(set);
		} else { 
			return null;
		}
	}
	
	protected boolean isMultipart() {
		return multipart;
	}

	protected void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}

	protected void addTotalAttachSize(long totalAttachSize) {
		this.totalAttachSize += totalAttachSize;
	}
}
