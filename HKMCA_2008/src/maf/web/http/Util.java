package maf.web.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.web.multipart.UploadedFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util {
	private static Log logger = LogFactory.getLog(Util.class);
	/**
	 * 첨부된 모든 임시파일을 지운다.
	 * 
	 */
	protected static void _deleteAllAttachFile(Map fileItemMap) {
		if (fileItemMap != null) {
			Set set = fileItemMap.entrySet();
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
	 * Upload된 모든 파일 newPath에 저장한다.
	 * 
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	protected static UploadedFile[] _FileSaveAllTo(Map fileItemMap, String newPath, boolean canOverwrite) throws Exception {
		List rv = new ArrayList();
		
		if (fileItemMap != null) {
			Set set = fileItemMap.entrySet();
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
							rv.add(uf);
						}
					}
				}
			}
		}
		return (UploadedFile[]) rv.toArray(new UploadedFile[0]);
	}
	
	
	
}
