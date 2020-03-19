/*
 * Created on 2006. 5. 15.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.multipart;

import java.io.File;

import maf.util.StringUtils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUploadFileParser {
	private final static String TMP_FILE_PREFIX = "MI_";
	private static final Log logger = LogFactory.getLog(CommonUploadFileParser.class);
	
	public static UploadedFile Multipart2UploadFile(DiskFileItem item) {
		UploadedFile uFile  = null;
		try {
			if(item!= null && item.getSize() > 0 ) {
				File tmpFile = File.createTempFile(TMP_FILE_PREFIX, null);
				tmpFile.deleteOnExit();
				item.write(tmpFile);
				uFile = new UploadedFile();
//				logger.debug("ori filename : " + item.getName());
//				logger.debug("ori filename file : " + StringUtils.getFilename(item.getName()));
//				logger.debug("sep : " + StringUtils.FOLDER_SEPARATOR);
				String fileName = StringUtils.getFilename(item.getName());
				uFile.setUploadedFile(tmpFile.getAbsolutePath() , fileName,item.getContentType(), item.getSize());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return uFile;		
	}
}

