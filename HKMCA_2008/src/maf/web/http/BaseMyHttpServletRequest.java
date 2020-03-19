package maf.web.http;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import maf.MafProperties;
import maf.lib.i18n;
import maf.web.context.MafConfig;
import maf.web.multipart.MultiPartChecker;
import maf.web.multipart.SessionUpdatingProgressListener;
import maf.web.multipart.UploadedFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseMyHttpServletRequest extends HttpServletRequestWrapper {
	private Log logger = LogFactory.getLog(BaseMyHttpServletRequest.class);
	/**
	 * 최대 업로드 사이즈 100MB
	 */
	public static final long DEFAULT_MAX_POST_SIZE = 100 * 1024 * 1024; // 100 Meg
	/**
	 * 최대 메모리 사용량 2MB
	 */
	protected static final int DEFAULT_THRESHOLD_SIZE = 1024 * 1024 * 2; // 

	
	protected final String KEY_PARAMETERS_MAP = "KEY_PARAMETERS_MAP";
	protected final String KEY_PARAMETER_MAP = "KEY_PARAMETER_MAP";
	protected final String KEY_FILEITEM_MAP = "KEY_FILEITEM_MAP";
	
	private boolean lconvertCharacterEncoding = true;
	
	protected abstract boolean isMultipart();
	protected abstract void setMultipart(boolean multipart);
	protected abstract void addTotalAttachSize(long totalAttachSize);
	
	public BaseMyHttpServletRequest(HttpServletRequest arg0) {
	    super(arg0);
    }
	
	public void addTo() {
		super.setAttribute(this.getClass().getName(), this);
	}
	
	/**
	 * 
	 * @param request
	 * @param threshold
	 * @param fileSizeMax
	 * @param repositoryPath
	 */
	protected Map parsing(HttpServletRequest request, int sizeThreshold, long fileSizeMax, File repositoryPath, ProgressListener progressListener) 
	throws FileUploadException {
		
		Map parametersMap = new HashMap();
		Map parameterMap = new HashMap();
		Map fileItemMap = new HashMap();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Set factory constraints
		//메모리에  저장할  최대  size 
		if(sizeThreshold >= 0 ) {
			factory.setSizeThreshold(sizeThreshold);
		} 
		if(repositoryPath != null) {
			factory.setRepository(repositoryPath);
		}

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		if(fileSizeMax<0) {
			upload.setSizeMax(DEFAULT_MAX_POST_SIZE);
		} else {
			upload.setSizeMax(fileSizeMax);
		}
		if(progressListener != null) {
			upload.setProgressListener(progressListener);
		} else {
			SessionUpdatingProgressListener lisner = new SessionUpdatingProgressListener(request);
			upload.setProgressListener(lisner);
		}
		try {
			request.setCharacterEncoding(MafProperties.DEFAULT_CHARACTER_ENCODING);
		} catch (Exception _ignored) {
		}
		if (MultiPartChecker.isMultiPart(request)) {

				// Parse the request

				List /* FileItem */items = upload.parseRequest(request);
				if (items != null) {
					// Process the uploaded items
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
						DiskFileItem item = (DiskFileItem) iter.next();

						// processFormField 에서 일반 필드와 file필드 동시 처리
						// file의 경우 value에 파일명을 가짐 
						this.processFormField(item, parametersMap, parameterMap, fileItemMap);
						
						// 2007.05.30 UploadFile에서 처
						//item.delete();
					}
				}
			
			setMultipart(true);
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
		Map result = new HashMap();

		result.put(this.KEY_PARAMETERS_MAP, parametersMap);
		result.put(this.KEY_PARAMETER_MAP, parameterMap);
		result.put(this.KEY_FILEITEM_MAP, fileItemMap);
		return result;
	}
	


	/**
	 * file 외의 일반적인 폼필드를 처리 한다.
	 * 
	 * @param item
	 */
	private void processFormField(FileItem item, Map parametersMap, Map parameterMap, Map fileItemMap) {
		String value = null;
		
		if (!item.isFormField()) {
			String fileName = null;
			fileName = processUploadedFile(item, fileItemMap);
			value = fileName;
		} else {
			value = _ConvertCharacterEncoding(item.getString());
		}
		
		String name = item.getFieldName();
		String[] values = (String[]) parameterMap.get(name);
		if (values == null) {
			values = new String[] { value };
		} else {
			String[] tempValues = new String[values.length + 1];
			System.arraycopy(values, 0, tempValues, 0, values.length);
			tempValues[tempValues.length - 1] = value;
			values = tempValues;
		}
		// value = request.getParameter(name);
		// values = request.getParameterValues(name);
		parametersMap.put(name, value);
		parameterMap.put(name, values);

	}
	


	/**
	 * file field를 처리 한다.
	 * @param item
	 */
	private String  processUploadedFile(FileItem item, Map fileItemMap) {
		String name = item.getFieldName();
		String fileName = null;

//		UploadedFile uFile = CommonUploadFileParser.Multipart2UploadFile(item);
		UploadedFile uFile = new UploadedFile(item);
		if (uFile != null) {
			List existingValues = (List) fileItemMap.get(name);
			if (existingValues == null) {
				existingValues = new ArrayList();
				existingValues.add(uFile);
			} else {
				existingValues.add(uFile);
			}
			fileItemMap.put(name, existingValues);
			addTotalAttachSize(  item.getSize());
			fileName = uFile.getNewfilename();
		}
		return fileName;
	}
	
	
	private String _ConvertCharacterEncoding(String str) {
		if (str == null) return null;
		if (lconvertCharacterEncoding) {
			return i18n.convertTo(str, MafProperties.DEFAULT_CHARACTER_ENCODING);
		}
		return str;
	}
	
	
	
	
	
	/**
	 * 다음과 같이 getSErvletContext()이용 바람. String x = getServletContext().getRealPath("\");
	 */
	public String getRealPath(String arg0) {
		return MafConfig.getRealPath(arg0);
	}
	
}
