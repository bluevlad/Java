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
	 * @param fileSizeMax 최대 파일 사이즈 
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
	 * @param threshold 메모리에  저장할  최대  size
	 * @param fileSizeMax 최대 파일 사이즈  //임시 저장할 위치
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
	 * Key와 Value[] 로 돌려 준다. !!! 주의 value를 Array로 돌려줌 
	 */
	public final Map getParameterMap() {
		return this.parameterMap;
	}

	/**
	 * 해당 Key의 값들을 String[]으로 돌려준다. 
	 * **주의 : 만약 값이 없으면 null을 돌려 둔다.
	 */
	public String[] getParameterValues(String paramname) {
		if (this.isMultiPartContent()) {
			return (String[]) parameterMap.get(paramname);
		} else {
			return super.getParameterValues(paramname);
		}
	}

	/**
	 * 파라미터 key와 value 정보를 Map에 담아 돌려준다. (setter 대용) 같은 이름에 여러 값이 있을 경우 첫번째 값
	 * 
	 * @return
	 */
	public final Map getKeyValueMap() {
		return this.parametersMap;
	}

	
	/**
	 * UploadedFile의 List의 Map형태로 돌려줌 
	 * @return
	 */
	public final Map getFileParameterMap() {
		return this.fileItemMap;
	}

	/**
	 * getParameter(key)의 Short Type
	 * 
	 * @param key
	 * @return
	 */
	public String getP(String key) {
		return this.getParameter(key);
	}

	/**
	 * getParameter(key,def)의 Short Type
	 * 
	 * @param key
	 * @return
	 */
	public String getP(String key, String def) {
		return this.getParameter(key, def);
	}

	/**
	 * 파라미터의 값이 Null이거나 길이가 0이면 def 리턴
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
	 * multi-part 데이터인 경우, 같은 이름으로 value가 여러개 있으면, ","로 구분 되어 나온다.
	 */
	public String getParameter(String p0) {
		return (String) parametersMap.get(p0);
	}

	/**
	 *  getParameter한 값을 int로 변환하여 return한다.
	 *  Number format이 아니거나, null인 경우는 0을 return한다.
	 */
	public int getIntParameter(String p0) {
		return this.getIntParameter(p0, 0);
	}

	/**
	 *  getParameter한 값을 int로 변환하여 return한다.
	 *  Number format이 아니거나, null인 경우는 0을 return한다.
	 */
	public int getIntParameter(String p0, int def) {
		return MafUtil.parseInt(getParameter(p0), def);
	}

	/**
	 *  getParameter한 값을 int로 변환하여 return한다.
	 *  Number format이 아니거나, null인 경우는 0을 return한다.
	 */
	public long getLongParameter(String p0) {
		return this.getLongParameter(p0, 0);
	}

	/**
	 *  getParameter한 값을 int로 변환하여 return한다.
	 *  Number format이 아니거나, null인 경우는 0을 return한다.
	 */
	public long getLongParameter(String p0, int def) {
		return MafUtil.parseLong(getParameter(p0), def);
	}

	

	/**
	 * 현재 multipart인지 여부 
	 * true: multipart
	 * @return
	 */
	public boolean isMultiPartContent() {
		return this.multipart;
	}

	/**
	 * Upload된 모든 파일 newPath에 저장한다. 
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public UploadedFile[] FileSaveAllTo(String newPath) throws Exception {
		return this.FileSaveAllTo(newPath, false);
	}

	/**
	 * Upload된 모든 파일 newPath에 저장한다.
	 * 
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public UploadedFile[] FileSaveAllTo(String newPath, boolean canOverwrite) throws Exception {
		return Util._FileSaveAllTo(fileItemMap, newPath, canOverwrite);
	}

	/**
	 * file paramer의 name의 값중 첫번째것을 넘겨준다. 
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
	 * 총 첨부파일 용량 (KB)
	 * 
	 * @return
	 */
	public int getTotalAttachSize() {
		return totalAttachSize / 1024;
	}

	/**
	 * 첨부된 모든 임시파일을 지운다.
	 * 
	 */
	public void deleteAllAttachFile() {
		Util._deleteAllAttachFile(fileItemMap);
	}

	/**
	 * 해당 키값이 넘어 왔는지 확인 
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
	 * parameter의 key 목록을 돌려 준다.
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
