package maf.context.i18n;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format.TextMode;
import maf.util.DateUtils;
import maf.util.FileUtils;

public class ResourceManager {
	static private Log logger = LogFactory.getLog(ResourceManager.class);

	public synchronized static boolean saveBundle(MafResourceBundle bundle) {
		boolean chk = false;
		File srcFile = null;
		File backupFile = null;
		File tempFile = null;
		// 기존 파일백업
		srcFile = new File(bundle.getConfigFileName());
		backupFile = new File(bundle.getConfigFileName()
		        + "."
		        + DateUtils.getCurrentDate()
		        + ".bak");
		backupFile = rename(backupFile);
		if (FileUtils.copyFile(srcFile, backupFile)) {
			// 임시 파일을 만든다.
			tempFile = new File(bundle.getConfigFileName() + ".tmp");
			
			Document doc = bundle.getDocoment();
			XMLOutputter outp = new XMLOutputter();
			Format jf = Format.getPrettyFormat();
			jf.setTextMode(TextMode.NORMALIZE);
			outp.setFormat(jf);
			FileOutputStream fstr = null;
			try {
				tempFile.createNewFile();
				//			outp.output(doc, System.out);
				fstr = new FileOutputStream(tempFile);
				
				outp.output(doc, fstr);
				chk = true;
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				if (fstr != null) {
					try {fstr.close();} catch (Exception e) {

					} 
				}
				fstr = null;
			}
			outp = null;
			// 기존 파일 삭제후 임시 파일을 원본에 복사 .
			if (chk) {
				chk = false;
				if( FileUtils.delFile(srcFile)) {
//					chk = tempFile.renameTo(new File(bundle.getConfigFileName()));
					File newFile = new File(bundle.getConfigFileName());
					try {
						newFile.createNewFile();
						if (newFile.canWrite()) {
							chk = FileUtils.copyFile(tempFile,newFile);
							FileUtils.delFile(tempFile);
						} else {
							logger.error("can't write new file");
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					logger.error("can't delete " + srcFile.getAbsolutePath() + "!!!");
				}
			}  else {
				logger.error("can't delete " + srcFile.getAbsolutePath() + "!!!");
			}
		} else {
			logger.error("can't make temp file");
		}
		return chk;
	}

	private static File rename(File f) {
		if (createNewFile(f)) {
			return f;
		}
		String name = f.getName();
		String body = null;
		String ext = null;
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot); // includes "."
		} else {
			body = name;
			ext = "";
		}
		// Increase the count until an empty spot is found.
		// Max out at 9999 to avoid an infinite loop caused by a persistent
		// IOException, like when the destination dir becomes non-writable.
		// We don't pass the exception up because our job is just to rename,
		// and the caller will hit any IOException in normal processing.
		int count = 0;
		while (!createNewFile(f) && count < 9999) {
			count++;
			String newName = body + count + ext;
			f = new File(f.getParent(), newName);
		}
		return f;
	}

	private static boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
