package maf.web.multipart;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.ProgressListener;

public class SessionUpdatingProgressListener implements ProgressListener {
	private HttpServletRequest requestRef = null;
	private long oldBytesRead = 0;
    
    public SessionUpdatingProgressListener(HttpServletRequest req) {
    	this.requestRef = req;
    }
    
	public void update(long pBytesRead, long pContentLength, int pItems) {
		
		if(Math.abs(pBytesRead - oldBytesRead ) > 1024 ) {
			if (requestRef != null) {
				requestRef.getSession().setAttribute("FileUpload.Progress.Items" ,
				                                     pItems + "");
				requestRef.getSession().setAttribute("FileUpload.Progress.BytesRead" ,
				                                     pBytesRead + "");
				requestRef.getSession().setAttribute("FileUpload.Progress.ContentLength" ,
				                                     pContentLength + "");
			}
		}
		oldBytesRead = pBytesRead;
	}
}
