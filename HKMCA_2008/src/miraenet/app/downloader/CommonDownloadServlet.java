/*
 * @(#) DownLoad.java 2005-02-28
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 */

package miraenet.app.downloader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.downloader.HttpFileSender;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 자료실 다운 받기
 * 
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-28
 */
public class CommonDownloadServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478980056162558735L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		String fullfileName = request.getParameter("file");
		String pds_cd = request.getParameter("pds_cd");
		String main_cd = request.getParameter("main_cd");
		String sub_cd = request.getParameter("sub_cd");
		String file_id = request.getParameter("file_id");

		String rfilename = getFileName(pds_cd, main_cd, sub_cd, file_id);
//		logger.debug("pds_cd : " + pds_cd + ", main_cd : " +main_cd + ", sub_cd : " + sub_cd + "fileid : " +fileid);
//		logger.debug("rfilename : " + rfilename);
		String fileName = fullfileName.substring(fullfileName.lastIndexOf("/") + 1);
		String filePath = fullfileName.substring(0, fullfileName.lastIndexOf("/"));

		String fullpath = getServletContext().getRealPath(filePath);
		String downloadFileName = MafUtil.empty(rfilename) ? fileName : rfilename;

		File file = new File(fullpath, fileName);

		HttpFileSender sm = new HttpFileSender(request, response);
		boolean dnyn = false;
        if ("save".equals(cmd) || "down".equals(cmd)) {
			dnyn = true;
		}

		sm.send(file, downloadFileName, dnyn);
	}

    private String getFileName(String pds_cd, String main_cd, String sub_cd, String file_id) {
        String sql = "SELECT org_filename " +
        		" from FILE_ATT " +
        		" where pds_cd = :pds_cd " +
        		" and main_cd = :main_cd " +
        		" and sub_cd = :sub_cd " +
        		" and file_id = :file_id";
        
        Map param = new HashMap();
        param.put("pds_cd", pds_cd);
        param.put("main_cd", main_cd);
        param.put("sub_cd", sub_cd);
        param.put("file_id", file_id);
        MdbDriver oDb = null;
        try {
        	oDb = Mdb.getMdbDriver();
            return (String) oDb.selectOne( sql, param );
        } catch (Exception e) {
            return null;
        } finally {
			if (oDb != null) {try {oDb.close();} catch (Exception e) {};}
        }
    }

    /**
     * attach 파일 삭제 삭제
     * 
     * @param attFiles MbbsAttachBean List;
     */
    public static void DeleteAttchFiles(MbbsAttachBean[] attFiles, String filePath)
    throws Exception
    {
        try{
	        if(attFiles !=null && attFiles.length > 0 ) {
	            for(int i = 0; i < attFiles.length; i++ ) {
	                try{
	                    DeleteAttchFile(attFiles[i].getReal_filename(), filePath);
	                } catch (Exception e) {
	                    
	                }
	                //fileDelete(attFiles[i].getSavePath(), attFiles[i].getREAL_FILENAME());
		            
	            }
	        }
        } catch (Exception e) {
//            logger .error(maf.logger.Trace.getStackTrace(e));
            throw new MBoardException("임시파일 삭제중 오류가 발생하였습니니다.");
        }
    }

    /**
     * @param oMbbs
     * @param Filename
     * @param filePath
     */
    public static void DeleteAttchFile(String Filename, String filePath){
        StringBuffer sPath = new StringBuffer(50);
		sPath.append(filePath);
		sPath.append(java.io.File.separator);
		String SaveFilePath = sPath.toString();
		
        fileDelete(SaveFilePath, Filename);
    }
    
    public static boolean fileDelete(String absFile) {
        File f = new File(absFile);
//        logger.debug("delete " + f.getAbsolutePath());
        if (f.exists()) {           
            if (f.delete()) return true;
            else return false;
        }  else {
            return true;
        }
    }    
    
    public static boolean fileDelete(String abspath, String filename) {
        return fileDelete(abspath + java.io.File.separator + filename);
    }
}