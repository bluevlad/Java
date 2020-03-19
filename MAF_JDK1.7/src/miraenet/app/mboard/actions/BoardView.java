/*
 * �ۼ��� : �����
 * Created on 2004. 10. 8.
 *
 */
package miraenet.app.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.context.MafConfig;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.mboard.BoardConfig;
import miraenet.app.mboard.MBoardAuth;
import miraenet.app.mboard.beans.MbbsAttachBean;
import miraenet.app.mboard.beans.MbbsCommentBean;
import miraenet.app.mboard.beans.MbbsDataBean;
import miraenet.app.mboard.beans.MbbsDataRefListBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author �����(goindole@miraenet.com)
 * Create by 2004. 10. 8.
 * 
 */
public class BoardView extends MBoardAction{
	private  Log logger = LogFactory.getLog(BoardView.class);
    public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response)
	throws Exception    
	{

    	
    	String c_index = _req.getParameter( "c_index", listOp.get("c_index",""));
 
    	String btnAuth = null;
    	listOp.put("c_index", c_index);
    	listOp.put("v_pagedown",0+"");

    	try {
    	    MbbsDataBean iTem = oMbbs.getOne( c_index);
    	    if(iTem == null) {
    	    	 _req.setAttribute(MafConfig.MAF_ATTRIBUTE_MESSAGE, "�������� �ʴ� �Խù��Դϴ�.");
     		    return ERROR_MSG_PAGE;
    	    }
    	    if( "T".equals(iTem.getC_status()) ||
    	           	("W".equals(iTem.getC_status()) && "T".equals(oMbbs.getBoard().getFl_waste()))) {
    	        MbbsCommentBean[] cmtLists =  oMbbs.getCmtList(c_index);
    	        MbbsDataRefListBean[] refList = oMbbs.getRefList(c_index,2);
    	        MbbsAttachBean[] attItems = oMbbs.getAttchList(c_index);
		    	_req.setAttribute("data", iTem);
		    	_req.setAttribute("cmtLists", cmtLists);
		    	_req.setAttribute("refList",refList);
		    	_req.setAttribute("attItems",attItems);
    	    } else {
    	        _req.setAttribute(MafConfig.MAF_ATTRIBUTE_MESSAGE, "������ �Խù��Դϴ�.");
    		    return ERROR_MSG_PAGE;
    	    }
    	    
        	if(! MBoardAuth.chkReadAuth(oMbbs.getBoard(), super.sessionBean) ) {
        		// ���� �Խù��� ���� �� �ְ�.
        		if(!(super.sessionBean != null && iTem.getUsn() == super.sessionBean.getUsn())) {
	       			_req.setAttribute("message","�Խù� �б� ������ �����ϴ�.");
	           		return "error";
        		}
        	}
        	
    	    if(sessionBean == null ) {
    	    	btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "view");
    	    } else {
    	    	btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "view");
    	        
    	        
    	    } 
    	
    	    _req.setAttribute(BoardConfig.PARAM_BTN_AUTH, btnAuth.toString());    	    
    	} catch (Exception e) {
    	    logger.error(e.getMessage());
    	    _req.setAttribute(MafConfig.MAF_ATTRIBUTE_MESSAGE, "���뺸�� �� ������ �߻��Ͽ����ϴ�.(view)");
		    return ERROR_MSG_PAGE;
    	}
    	
    	super.map.put(BoardConfig.PARAM_BTN_AUTH, btnAuth.toString());
    	_req.setAttribute("body", "/mboard/view."+oMbbs.getBoard().getFl_board_type()+".jsp");
		return "boardView";//oMbbs.getBoard().getLayout();
	}

}
