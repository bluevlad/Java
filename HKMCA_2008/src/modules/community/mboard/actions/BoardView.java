/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 8.
 *
 */
package modules.community.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import modules.community.mboard.BoardConfig;
import modules.community.mboard.MBoardAuth;
import modules.community.mboard.admin.BoardAction;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.beans.MbbsCommentBean;
import modules.community.mboard.beans.MbbsDataBean;
import modules.community.mboard.beans.MbbsDataRefListBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * Create by 2004. 10. 8.
 * 
 */
public class BoardView extends BoardAction{
	private  Log logger = LogFactory.getLog(BoardView.class);
	private final String MESSAGE_BUNDLENAME = "board";

    public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response) throws Exception    
	{
    	String c_index = _req.getParameter( "c_index", listOp.get("c_index",""));
    	String btnAuth = null;
    	listOp.put("c_index", c_index);
    	listOp.put("v_pagedown",0+"");

    	try {
    	    MbbsDataBean item = oMbbs.getOne(c_index);
    	    if(item == null) {
       			_req.setAttribute("message", "존재하지 않는 게시물입니다.");
     		    return ERROR_MSG_PAGE;
    	    }
    	    if( "T".equals(item.getC_status()) || ("W".equals(item.getC_status()) && "T".equals(oMbbs.getBoard().getFl_waste()))) {
    	        MbbsCommentBean[] cmtLists =  oMbbs.getCmtList(c_index);
    	        MbbsDataRefListBean[] refList = oMbbs.getRefList(c_index,2);
    	        MbbsAttachBean[] attItems = oMbbs.getAttchList(c_index);
		    	_req.setAttribute("data", item);
		    	_req.setAttribute("cmtLists", cmtLists);
		    	_req.setAttribute("refList",refList);
		    	_req.setAttribute("attItems",attItems);
    	    } else {
       			_req.setAttribute("message", "삭제된 게시물입니다.");
    		    return ERROR_MSG_PAGE;
    	    }
    	    
        	if(! MBoardAuth.chkReadAuth(oMbbs.getBoard(), super.sessionBean) ) {
        		// 본인 게시물은 읽을 수 있게.
//    			logger.debug("item.getOrgusn() : " + item.getOrgusn());
        		if(!((super.sessionBean.getUsn() != null && item.getUsn().equals(super.sessionBean.getUsn())) )) {
	       			_req.setAttribute("message", "게시물 읽기 권한이 없습니다.");
	           		return "error";
        		}
        	}
        	
   	    	btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "view", item);
    	
    	    _req.setAttribute(BoardConfig.PARAM_BTN_AUTH, btnAuth.toString());    	    
    	} catch (Exception e) {
    	    logger.error(e.getMessage());
   			_req.setAttribute("message", "내용보기 중 오류가 발생하였습니다.");
		    return ERROR_MSG_PAGE;
    	}
    	
    	super.map.put(BoardConfig.PARAM_BTN_AUTH, btnAuth.toString());
		return "boardView";
	}
}