/*
 * 작성자 : bluevlad
 * Created on 2004. 10. 12.
 *
 */
package modules.community.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import modules.community.mboard.BoardConfig;
import modules.community.mboard.admin.BoardAction;
import modules.community.mboard.beans.MbbsCommentBean;
import modules.community.mboard.beans.MbbsDataBean;
import modules.community.mboard.exception.MBoardException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 * Create by 2004. 10. 12.
 * 
 */
public class BoardAct  extends BoardAction{
	private final String MESSAGE_BUNDLENAME = "common.message.board";
	private  Log logger = LogFactory.getLog(BoardView.class);

	public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response)
	throws Exception    
	{
        String msg	= "Error!!!";
        String forward_url = null;
        String cmd = _req.getParameter("cmd","");
        String DELMODE = _req.getParameter("DELMODE","c");
        String passwd = _req.getParameter("passwd","");
        String c_index = listOp.get("c_index","");
        String chk =null;
        chk = "F";
        MbbsDataBean item = null;
        
        try {
            forward_url = "/mboard/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
	        if (BoardConfig.CMD_CMT_ADD.equals(cmd)) { //메모글 등록
	            cmt_add(_req);
	            msg = "comment.added";
	        } else if  (BoardConfig.CMD_CMT_DEL.equals(cmd)) { //메모글 삭제
	            cmt_del(_req);
	            msg = "comment.deleted";
        	} else if (BoardConfig.CMD_DEL.equals(cmd)) { //게시물 삭제
        	    item = oMbbs.getOneSimple(c_index);
//        	    logger.error("super.auth : " + super.auth);
//        	    logger.error("sessionBean.getUserRole() : " + sessionBean.getUserRole().hasRole("ROOT") );
//        	    if(sessionBean != null && (sessionBean.getUsn().equals(item.getUsn()) || "A".equals(super.auth))) {
            	    if(sessionBean != null && (sessionBean.getUsn().equals(item.getUsn())
            	    		|| sessionBean.getUserRole().hasRole("ROOT"))) {
        	        chk = "T";
        	    } else if (!MafUtil.empty(passwd) && (passwd.equals(item.getC_passwd()))) {
        	        chk = "T";
        	    }
        	    if("T".equals(chk)) {
	        	    oMbbs.DeleteItem(c_index, DELMODE);
	        	    msg = "delete.ok";
					forward_url = "/mboard/list.do?LISTOP="+this.listOpStr;
        	    } else {
        	        msg = "delete.fail";
        	    }
        	} else if  (BoardConfig.CMD_ADD_REPLY.equals(cmd)) { //답변 등록
				c_index = oMbbs.addItem(_req, c_index);
				msg = "insert.ok";
				forward_url ="/mboard/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	} else if  (BoardConfig.CMD_ADD.equals(cmd)) { //게시물 등록
				c_index = oMbbs.addItem(_req);
				msg = "insert.ok";
				forward_url ="/mboard/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	} else if  (BoardConfig.CMD_UPDATE.equals(cmd)) { //게시물 수정
        	    item = oMbbs.getOneSimple(c_index);
        	    if(sessionBean != null && sessionBean.getUsn().equals(item.getUsn()) ) {
        	        chk = "T";
        	    } else if (!MafUtil.empty(passwd) && (passwd.equals(item.getC_passwd()))) {
        	        chk = "T";
        	    }
        	    if("T".equals(chk)) {
	        	    oMbbs.UpdateItem(_req, c_index);
					msg = "update.ok";
					forward_url = "/mboard/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	    } else {
        	        msg = "update.fail";
        	    }
	    	} else {
				    msg = "invalid.op";
	    	}
            chk = "T";
	    } catch (Exception e){
	        throw new MBoardException("Error in BoardAct cmd = " + cmd + ", bid = " + super.oMbbs.getBid() + ", c_index = " + c_index, e);
        }
	    _req.setAttribute("next", forward_url);
	    _req.setAttribute("message", new ResultMessage(this.MESSAGE_BUNDLENAME,msg).getMessage(_req));
	    
	    if("T".equals(chk)) {
	        return MSG_PAGE;
	    } else {
	        return ERROR_MSG_PAGE;
	    }
	}
    
    /**
     * 커멘트를 등록
     * @return
     * @throws Exception
     */
    private void cmt_add(MyHttpServletRequest _req) throws Exception    {
        String c_index = listOp.get( "c_index" ,"");
    	
    	MbbsCommentBean oB = new MbbsCommentBean();
    	Setter setter = new Setter(oB, _req);
    	
    	setter.setProperty("*");
    	if( sessionBean != null ) {
	    	oB.setUsn(sessionBean.getUsn());
	    	oB.setWname(sessionBean.getNm()); // 필명
    	}
    	oB.setC_index(c_index);
    	oB.setBid(super.oMbbs.getBid());
//    	oB.setBid("brd_user");
    	oB.setC_ip(_req.getRemoteAddr());
    	
    	oMbbs.CmtAdd(oB);
    }
    
    /**
     * 커멘트를 삭제
     * @return
     * @throws Exception
     */
    private void cmt_del(MyHttpServletRequest _req) throws Exception    {
        String c_index = listOp.get( "c_index" ,"");
    	String c_id = _req.getParameter("c_id","");

    	oMbbs.CmtDel(c_index, c_id);
    }    
}
