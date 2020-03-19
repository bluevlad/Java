/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 7.
 *
 */
package modules.community.mboard;

import java.io.File;

import maf.MafUtil;
import maf.lib.image.Thumbnail;
import maf.logger.Logging;
import maf.util.DateUtils;
import maf.util.FileUtils;
import maf.web.multipart.UploadedFile;
import maf.web.util.HTMLUtil;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;



/**
 * @author bluevlad
 * Create by 2004. 10. 7.
 * 
 */
public class MBoardLib {
    private static  RE RE_IMAGE = new RE("^image/");    

    static private  Log logger = LogFactory.getLog(MBoardLib.class);
    
    public MBoardLib() {
        RE_IMAGE.setMatchFlags( RE.MATCH_CASEINDEPENDENT );
    }
    /**
     * UploadedFile 의 손톱이미지를 저장
     */
    public static boolean  SaveThumbNail(UploadedFile uFile, String ThumbPath, int Width, int Height) 
    throws MBoardException
    {
        boolean chk = false;
    	try{
    		//Thumbnail 이미지 만드는 부분
    	    //복사할 곳에 디렉토리가 있는지 확인한다. 디렉토리가 없으면 만든다.
//    		String SaveThumbnailPath = uFile.getNewFilePath()+ java.io.File.separator + 
//    									ThumbPath +java.io.File.separator;
    		//logger.debug(SaveThumbnailPath);
    	    
    		if(!FileUtils.existDirectory(ThumbPath))
    	   	{
    		    FileUtils.createDirectory(ThumbPath);
    	        logger.info("Create Directory:"+ThumbPath);
    	   	}				
    		Logging.log(MBoardLib.class, uFile.getNewFilePath() + java.io.File.separator + uFile.getNewfilename() + " to " +ThumbPath +  java.io.File.separator + uFile.getNewfilename());		
    		chk =  Thumbnail.resizeFull(uFile.getNewFile(), 
    		        	ThumbPath , uFile.getNewfilename(),
    					Width, Height);
    	}
    	catch(Exception e)
        {
    	    logger.error(e);
    	    //throw new MBoardException(e);
        }
    	// TODO : Jeus의 설정 문제로 임시 처리 
    	chk = true;
    	return chk;
    }
    
    /**
     * 섬네일 까지 같이 지운다.
     * @param oMbbs
     * @param Filename
     * @param filePath
     */
    protected static void DeleteAttchFile(MBoardManager oMbbs, String Filename, String filePath){
        StringBuffer sPath = new StringBuffer(50);
		sPath.append(filePath);
		sPath.append(java.io.File.separator+oMbbs.getBidPath());
		String SaveFilePath = sPath.toString();
		
        fileDelete(SaveFilePath, Filename);
	    fileDelete(SaveFilePath + java.io.File.separator + MBoardManager.THUMB_PATH, Filename);
    }
    
    protected static boolean fileDelete(String absFile) {
        File f = new File(absFile);
        logger.debug("delete " + f.getAbsolutePath());
        if (f.exists()) {           
            if (f.delete()) return true;
            else return false;
        }  else {
            return true;
        }
    }    
    
    protected static boolean fileDelete(String abspath, String filename) {
        return fileDelete(abspath + java.io.File.separator + filename);
    }
    /**
     * Oracle.sql.DATE를 게시판에 맞게 
     *  full : yyyy/mm/dd hh24:mi
     *  date : yyyy/mm/dd
     *  short : mm/dd
     *  time : hh24:mi
     * auto : 같은 날이면 time을 다른 날이면 short를  
     * @param pDate
     * @param type
     * @return
     * @throws Exception
     */
    public static String getBoardDate(oracle.sql.DATE pDate, String type)
    {
        DateUtils cD = new DateUtils();
        type = type.toLowerCase();
        try {
	        if("full".equals(type)) {
	            return pDate.toText("YYYY/MM/DD HH24:MI",null);
	        } else if("date".equals(type)) {
	            return pDate.toText("YYYY/MM/DD",null);
	        } else if("short".equals(type)) {
	            return pDate.toText("MM/DD",null);
	        } else if("time".equals(type)) {
	            return pDate.toText("HH24:MI",null);
	        } else {
		        if(cD.getDate("yyyy/mm/dd").equals(pDate.toText("YYYY/MM/DD",null)) ){
		            return pDate.toText("HH24:MI",null);
		        } else {
		            return pDate.toText("MM/DD",null);
		        }
	        }
        } catch (Exception e) {
            logger.error(maf.logger.Trace.getStackTrace(e));
            return "";
        }
    }
    
    public static String getBoardDate(oracle.sql.DATE pDate) {
        return getBoardDate(pDate, "auto");
    }
    
    /**
     * HtmlArea에서 온 파일 경로를 URL로 변경 한다.
     * @param aFiles MbbsAttachBean[]
     * @param c_content
     * @param URL
     * @return
     */
    protected static  String ArrangeContents(MbbsAttachBean[] aFiles, String bid, String c_content, String URL ) {
        if (aFiles != null) {
            for (int i = 0; i < aFiles.length; i++) {
                if (RE_IMAGE.match(aFiles[i].getContent_type())) {
                    String oFileName = aFiles[i].getOri_filename().replaceAll(" ", "%20");
                    String regex = "file:///[^\".]+" + maf.lib.regexTool.toNormalStr(oFileName);
                    org.apache.regexp.RE reg_NL = new org.apache.regexp.RE(regex);
                    StringBuffer sT = new StringBuffer();
                    sT.append(URL);
                    sT.append(MBoardManager.ATT_FILE_PATH);
                    sT.append("/" + bid + "/");
                    sT.append(HTMLUtil.rawURLEncode(aFiles[i].getReal_filename()));
                    c_content = reg_NL.subst(c_content, sT.toString());
                }

            }
        }
        
        return  c_content;
    }
    
    /**
     * 첨부파일 저장 ( 실경로에 저장, 손톱이미지도 만듬)
     * Attach_SaveFiles에서 호출됨 
     * @param oMbbs
     * @param uFile
     * @param MakeThumb Thumb생성 여부 
     * @return
     */
    protected static MbbsAttachBean Attach_SaveFile(UploadedFile uFile, String bid, 
            				boolean MakeThumb, String filePath, int width, int height) throws MBoardException{
        MbbsAttachBean oB = null;
        boolean chk=false;
        StringBuffer sPath = new StringBuffer(50);
		sPath.append(filePath);
		sPath.append(java.io.File.separator);
		sPath.append(bid);
		
		//sPath.append(java.io.File.separator);
		StringBuffer thumbFilePath = new StringBuffer(sPath.toString());
		 thumbFilePath = thumbFilePath.append(java.io.File.separator + MBoardManager.THUMB_PATH);
		 String fid = MafUtil.getGUID();
		//Logging.log(MBoardLib.class, sPath.toString() + "," +thumbFilePath.toString());
	    try {
	        if(uFile.SaveAs(sPath.toString(), fid)) {
//			if(uFile.SaveTo(sPath.toString())) {
			    
				if (MakeThumb && RE_IMAGE.match(uFile.getContentType())) {
				    chk = MBoardLib.SaveThumbNail(uFile, thumbFilePath.toString(), width, height );
				} else {
				    MakeThumb = false;
				    chk = true;
				}
			}
	    } catch (Exception e) {
	        uFile.deleteNewFile();
	        throw new MBoardException(e);
	    }
	    if (chk) {
	        oB = new MbbsAttachBean();
	        oB.setSavepath(sPath.toString()  );
	        oB.setReal_filename(uFile.getNewfilename());
	        oB.setThumbsavepath(thumbFilePath.toString() );
	        oB.setContent_type(uFile.getContentType());
	        oB.setFile_size(uFile.getFileSize());	        
	        oB.setOri_filename(uFile.getOriginalFileName());
	        oB.setThumbnail((MakeThumb)?"T":"F");
	        
	    }
	    return oB;
    }
    
    /**
     * attach 파일 삭제 삭제
     * 
     * @param attFiles MbbsAttachBean List;
     */
    protected static  void DeleteAttchFiles(MBoardManager oMbbs, MbbsAttachBean[] attFiles, String filePath)
    throws Exception
    {
        try{
	        if(attFiles !=null && attFiles.length > 0 ) {
	            for(int i = 0; i < attFiles.length; i++ ) {
	                try{
	                    DeleteAttchFile(oMbbs,attFiles[i].getReal_filename(), filePath);
	                } catch (Exception e) {
	                    
	                }
	                //fileDelete(attFiles[i].getSavePath(), attFiles[i].getREAL_FILENAME());
		            
	            }
	        }
        } catch (Exception e) {
            logger.error(maf.logger.Trace.getStackTrace(e));
            throw new MBoardException("임시파일 삭제중 오류가 발생하였습니니다.");
        }
    }
 
}
