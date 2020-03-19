package miraenet.app.webfolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import miraenet.app.webfolder.beans.FileBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class WebFolderLib {
	static private  Log logger = LogFactory.getLog(WebFolderLib.class);
	static final String PATH_SEP = "/";
	
    /**
     * �ش� ������ ���� List �� ���� �ش�. 
     * @param fullPath
     * @return FileBean[]
     */
    public static  List getList(String fullPath) {
        RE editableFile = new RE("\\.htm$|\\.html$|\\.jsp$|\\.css$|\\.js$|\\.txt$");
		editableFile.setMatchFlags( RE.MATCH_CASEINDEPENDENT );
		
		RE imgFile = new RE("\\.jpg$|\\.jpeg$|\\.gif$|\\.png$|\\.bmp$");
		imgFile.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		
		RE previewFile = new RE("\\.htm$|\\.html$|\\.jsp$");
		previewFile.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		if ( fullPath == null ) {
		    return null;
		}
		if (! File.separator.equals(fullPath.substring(fullPath.length()-1))) {
		    fullPath = fullPath + File.separator;
		}
		
		File file = new File(fullPath);
		String[] list = file.list();
		List fileList = new ArrayList();
		FileBean fb = null;
		if (list !=null) {
			for (int i=0; i < list.length; i++) {
				file = new File(fullPath + list[i]);
				if (!file.getName().equals("unknown")) {
					fb = new FileBean();
					fb.setDate(file.lastModified());
					fb.setSize(file.length() / 1024);
					fb.setFilename(file.getName());
					
					if (file.isDirectory()) {
						fb.setDirectory(true); 
					}
					if (editableFile.match(file.getName())) {
						fb.setFunction("edit");
					} else if (imgFile.match(file.getName())) {
						fb.setFunction("img");
					}
					if (previewFile.match(file.getName())) {
						fb.setFunction("edit-preview");
					}
					
					fileList.add(fb);
				}
			}
		}
		//return (FileBean[])	fileList.toArray(new FileBean[0]);
		return fileList;
	}
    
	/**
	 * ���� ����
	 
	 * use : cmd --> delete
	 */
	public static boolean delete(File f)  throws WebFolderException {
	    logger.debug("delete : "+ f.getAbsolutePath() );
		if (f.isDirectory()) {
			if (!f.delete()) {
			    throw new WebFolderException("Can't Delete " + f.getName());
			} else {
				return true;			
			}
		} else {
		 	return f.delete();
		}
	}
	
	/**
	 * ���丮�� ������ ���ԵǾ� �ִ��� �˻�
	 *
	 */
	public static boolean isContainFileDir(String fullPath, String[] list) {
		File file = null;
		for (int i=0; i < list.length; i++) {
			file = new File(fullPath + list[i]);
			if (file.isDirectory()) {
				File[] listFile = file.listFiles();
				if (listFile.length >0) return true;
			}
		}
		return false;
	}
	
	/**
	 * ������ ������ ���� �� �̸�����ÿ� Ư�����ڰ� ���ԵǾ� �ִ��� �˻�
	 * "." �� ������ ���Ѿ� �ϳ� �����̸� �� �����̸� �˻翡 �������� ����ϹǷ� �����Ѵ�.
	 * �����̸� �˻�� ������ ������.
	 */
	public static boolean isInValidateName(String name) {
		RE invalidName = new RE("\\\\|\\/|\\:|\\*|\\?|\\\"|\\'|\\<|\\>|\\||\\../");
		invalidName.setMatchFlags( RE.MATCH_CASEINDEPENDENT );
		return invalidName.match(name);
	}
	

}
