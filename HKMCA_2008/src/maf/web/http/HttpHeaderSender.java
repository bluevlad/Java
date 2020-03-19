package maf.web.http;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

/**
 * response와 관련 된 header 를 client에 전송 한다.
 * @author bluevlad
 *
 */
public class HttpHeaderSender {
	private static final String IE_TYPE = "(MSIE)";
    private static final RE REG_IE = new org.apache.regexp.RE(IE_TYPE, RE.MATCH_CASEINDEPENDENT);
    private  static Log logger = LogFactory.getLog(HttpHeaderSender.class);
    
	/**
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @param isAttachment ( inline : false, attachment : true{기본) 여부 )
	 * @param isCache cache 여부 
	 */
	public static void setDownLoad( HttpServletRequest request, HttpServletResponse response, String fileName, 
				boolean isAttachment, boolean isCache){
		StringBuffer content_disposition = new StringBuffer(100);
		String user_agent = request.getHeader( "USER_AGENT" );
		if (user_agent == null) {
            user_agent = request.getHeader( "user-agent" );
        }
//		String charset = "utf-8";
		
        if (isAttachment) {
        	content_disposition.append("attachment; ");
        } else {
        	content_disposition.append("inline; ");
        }

        content_disposition.append("filename=\"");
//        if(user_agent != null && REG_IE.match(user_agent)) {
//        	String tfilename = i18n.convertTo(fileName,"UTF-8","euc-kr");
//        	content_disposition.append(i18n.convertTo(fileName,"euc-kr","8859_1"));
//        	
//        } else {
//        	content_disposition.append(i18n.convertTo(fileName,"UTF-8","8859_1")) ;
//        }
        
		try {
	        content_disposition.append(java.net.URLEncoder.encode(fileName, "UTF8"));
        } catch (UnsupportedEncodingException e) {
	        logger.error(e);
        }
        
		content_disposition.append("\"");
        StringBuffer contentType = new StringBuffer(30);
        contentType.append(MimeType.getMimeType( fileName));
		
        
        
        // IE 면 별도 처리 
        if(user_agent != null && REG_IE.match(user_agent)) {
        	response.setContentType("application/x-msdownload"); 
        	response.setHeader( "Content-Disposition", content_disposition.toString() );
        } else {
        	
        	response.setContentType(contentType.toString());
        	response.setHeader( "Content-Disposition", content_disposition.toString() );
        }
        logger.debug("contents-type:" + contentType.toString() + "\n" +
                     "Content-Disposition : " +  content_disposition.toString() + "\n"
                     );
        
       
        response.setHeader( "Content-Transfer-Encoding", "binary" );
        
        if(isCache) {
            response.setHeader( "Pragma", "cache" );
        } else {
            response.setHeader( "Cache-Control", "no-cache, must-revalidate" );
            response.setHeader( "Pragma", "no-cache" );
            response.setHeader( "Expires", "0" );
        }
		
	}
}
