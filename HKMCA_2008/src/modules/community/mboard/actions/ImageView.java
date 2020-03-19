/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 8.
 *
 */
package modules.community.mboard.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.web.http.MyHttpServletRequest;
import modules.community.mboard.admin.BoardAction;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.beans.MbbsDataBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




/**
 * @author bluevlad
 * Create by 2004. 10. 8.
 * 
 */
public class ImageView extends BoardAction{
	private Log logger = LogFactory.getLog(getClass());
    public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response)
	throws Exception    
	{
    	
    	String c_index = _req.getParameter( "c_index", listOp.get("c_index",""));
    	long seq = MafUtil.parseLong(_req.getP("seq"),0);
    	
    	try {
    	    MbbsDataBean iTem = oMbbs.getOne( c_index);
    	    if("T".equals(iTem.getC_status()) ||
    	           	("W".equals(iTem.getC_status()) && "T".equals(oMbbs.getBoard().getFl_waste()))) {
    	        MbbsAttachBean[] attItems = oMbbs.getAttchList(c_index);

    	        List aImages = new ArrayList();
    	        for(int i = 0; i < attItems.length; i++ ) {
    	            MbbsAttachBean at = attItems[i];
    	            if(!MafUtil.empty(at.getContent_type()) && at.getContent_type().indexOf("image") > -1) {
    	                aImages.add(at);    	           
    	                if(seq == 0) {
    	                	seq = at.getSeq();
    	                }
    	            }
    	        }
		    	_req.setAttribute("attItems",aImages);
    	    } else {
    	        _req.setAttribute("msg", "삭제된 게시물입니다.");
    		    return ERROR_MSG_PAGE;
    	    }
    	} catch (Exception e) {
    	    logger.error(maf.logger.Trace.getStackTrace(e));
    	    _req.setAttribute("message", "내용보기 중 오류가 발생하였습니다.(view)");
		    return ERROR_MSG_PAGE;
    	}
    	_req.setAttribute("seq", seq+"");
		return "ImageView";//oMbbs.getBoard().getLayout();
	}

}
