/*
 * FileSender.java, @ 2005-03-14
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.downloader;

import java.io.File;
import java.io.FileInputStream;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.lib.i18n;
import maf.lib.system.FileInfoBean;
import maf.web.http.MimeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;


/**
 * @author goindole
 *  파일 전송 프로토콜 
 */
public class HttpFileSender {
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    private static final RE REG_IE = new org.apache.regexp.RE("(MSIE 5.0|MSIE 5.1|MSIE 5.5|MSIE 6.0)", RE.MATCH_CASEINDEPENDENT );
    
	private  Log logger = LogFactory.getLog(HttpFileSender.class);
	
    public HttpFileSender(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void send(File file, FileInfoBean fb, String cmd)  {
        send(file,fb,cmd, true);
    }
    public void send(File file, FileInfoBean fb, String cmd, boolean cache) {
        String rawFileName  = null;
        try {
            String os = request.getHeader( "USER_AGENT" );
            if (os == null) {
                os = request.getHeader( "user-agent" );
            }
            if (file != null && file.exists()) {
                if (file.canRead()) {
                    String dnyn = null;
                    if ("save".equals( cmd ) || "down".equals( cmd )) {
                        dnyn = "attachment; ";
                    } else {
                        dnyn = "inline; ";
                    }
                    rawFileName = fb.getFilename();
//                    rawFileName = java.net.URLEncoder.encode( fb.getFilename(), "8859_1" );
//                    fb.setFilename("시험용 .hwp");
//                    rawFileName = "시험용 .hwp";
                    if(REG_IE.match(os)) {
                    	rawFileName = i18n.convertTo(fb.getFilename(),"euc-kr","8859_1") ;
                    } else {
                    	rawFileName = i18n.convertTo(fb.getFilename(),"utf-8","8859_1") ;
                    }
                    rawFileName = "filename=\"" + rawFileName.replaceAll("\"","'") + "\"";
//                    Logging.log(this.getClass(), os + " FileName: " + rawFileName);
                    
                	if(REG_IE.match(os)) {
                	    if( os.indexOf("MSIE 5.5") != -1 ) {
//                	        Logging.log(this.getClass(), "Download MSIE 5.5");
                            response.setContentType("doesn/matter");
                            response.setHeader( "Content-Disposition",  rawFileName  );
                	    } else if (os.indexOf("MSIE 5.0") != -1) {
//                	        Logging.log(this.getClass(), "Download MSIE 5.0");
//                            response.setContentType("file/unknown");
                	        response.setContentType("doesn/matter");
//                            response.setContentType("application/x-msdownload"); 
                            response.setHeader( "Content-Disposition",  "attachment;"+ rawFileName );
                	    }else if (os.indexOf("MSIE 5.1") != -1) {
//                	        Logging.log(this.getClass(), "Download MSIE 5.1");
                            response.setContentType("file/unknown");
                            response.setHeader( "Content-Disposition", "attachment;"+ rawFileName  );
                	    }else if (os.indexOf("MSIE 6.0") != -1) {
//                	        Logging.log(this.getClass(), "Download MSIE 6.0");
                	        response.setContentType("application/x-msdownload"); 
                	        response.setHeader( "Content-Disposition", dnyn + " "+ rawFileName);
                	    }
                    } else {

                        response.setContentType( MimeType.getMimeType( fb.getFilename() ) );
	                    response.setHeader( "Content-Disposition", dnyn +" "+ rawFileName );
                    }
                    response.setHeader( "Content-Length", file.length() + "" );
                    response.setHeader( "Content-Transfer-Encoding", "binary" );
                    if(cache) {
                        response.setHeader( "Pragma", "cache" );
                    } else {
	                    response.setHeader( "Cache-Control", "no-cache, must-revalidate" );
	                    response.setHeader( "Pragma", "no-cache" );
	                    response.setHeader( "Expires", "0" );
                    }
                    FileInputStream in = null;
                    ServletOutputStream output = null;
                    try {
						in = new FileInputStream(file);
						output = response.getOutputStream();
						byte[] b = new byte[16 * 1024];
						int numRead = in.read(b);
						while (numRead != -1) {
							output.write(b, 0, numRead);
							numRead = in.read(b);
						}
						output.flush();
					} finally {
						if(output != null) try {output.close();} catch(Exception _ignore){}
						if(in != null) try {in.close();} catch(Exception _ignore){}
						output = null;
						in = null;
					}
					
                    //Logging.log(this.getClass(), "Sent!!!");
                }
            } else {
                response.sendError( HttpServletResponse.SC_NOT_FOUND );
            }
        } catch (Throwable e) {

            logger.error(e.getMessage());
            try{
                response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            } catch (Exception x) {
                
            }
        }
    }

}