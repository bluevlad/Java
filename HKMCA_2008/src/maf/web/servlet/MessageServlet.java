package maf.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import maf.web.context.BaseHttpServlet;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.view.JsonViewer;
import modules.common.jason.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Javascript 다국어 지원용 
 * @author bluevlad
 *
 */
public class MessageServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bundle = request.getParameter("bundle");
		String key = request.getParameter("key");
		String param = request.getParameter("param");
		//logger.debug("bundle:" + bundle + ", key : " + key);
		ResultMessage msg =  new ResultMessage( bundle, key , param);
		JSONObject item=new JSONObject();
		item.put("message", msg.getMessage(request));
		response.setContentType(JsonViewer.CONTENTS_TYPE);
		response.setHeader("Cache-Control", "no-cache");
		
		ServletOutputStream ouputStream = response.getOutputStream();
        
        ouputStream.print(item.toString());
        ouputStream.close();
	}
}
