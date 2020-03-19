/*
 * �ۼ��� : �����
 * Created on 2004. 10. 12.
 *
 */
package miraenet.app.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.mboard.BoardConfig;
import miraenet.app.mboard.beans.MbbsCommentBean;
import miraenet.app.mboard.beans.MbbsDataBean;
import miraenet.app.mboard.exception.MBoardException;

/**
 * @author �����(goindole@miraenet.com)
 * Create by 2004. 10. 12.
 * 
 */
public class BoardAct  extends MBoardAction{
    
    public synchronized String doWork( MyHttpServletRequest _req, HttpServletResponse response)
	throws Exception    
	{
        String 			msg	= "Error!!!";
        String 			forward_url = null;
        String 			cmd = _req.getParameter("cmd","");
        String 			DELMODE = _req.getParameter("DELMODE","c");
        String 			passwd = _req.getParameter("passwd","");
        String 			c_index = listOp.get("c_index","");
        String chk =null;
        chk = "F";
        MbbsDataBean item = null;
        //Logging.log(this.getClass(), "[" + c_index +"]");
        
        try {
            forward_url = "/board/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
	        if (BoardConfig.CMD_CMT_ADD.equals(cmd)) {
	            cmt_add(_req);
	            msg = "�ڸ�Ʈ�� ��ϵǾ����ϴ�.";
	        } else if  (BoardConfig.CMD_CMT_DEL.equals(cmd)) {
	            cmt_del(_req);
	            msg = "�ڸ�Ʈ�� �����Ǿ����ϴ�.";
        	} else if  (BoardConfig.CMD_DEL.equals(cmd)) {
        	    item = oMbbs.getOneSimple(c_index);
        	    if(sessionBean != null && 
        	            (sessionBean.getUsn().equals(item.getUsn())  ||
        	            	"A".equals(super.auth))) {
        	        chk = "T";
        	    } else if (!MafUtil.empty(passwd)  &&
        	            (passwd.equals(item.getC_passwd()))) {
        	        chk = "T";
        	    }
        	    if("T".equals(chk)) {
	        	    oMbbs.DeleteItem(c_index, DELMODE);
					msg = "�����Ǿ����ϴ�("+DELMODE+")";
					forward_url = "/board/list.do?LISTOP="+this.listOpStr;
        	    } else {
        	        msg = "��ȣ�� Ʋ���ų� ������ �Խù��� �ƴմϴ�.";
        	    }
        	} else if  (BoardConfig.CMD_ADD_REPLY.equals(cmd)) {
				c_index = oMbbs.addItem(_req, c_index);
				msg = "�亯���� ��� �Ǿ����ϴ�." ;
				forward_url ="/board/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	} else if  (BoardConfig.CMD_ADD.equals(cmd)) {
				c_index = oMbbs.addItem(_req);
				msg = "�Խù��� �߰� �Ǿ����ϴ�." ;
				forward_url ="/board/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	} else if  (BoardConfig.CMD_UPDATE.equals(cmd)) {
        	    // ������ �Խù��� ���� �����ϴ�.
        	    item = oMbbs.getOneSimple(c_index);
        	    if(sessionBean != null && sessionBean.getUsn().equals(item.getUsn()) ) {
        	        chk = "T";
        	    } else if (!MafUtil.empty(passwd)  &&
        	            (passwd.equals(item.getC_passwd()))) {
        	        chk = "T";
        	    }
        	    if("T".equals(chk)) {
	        	    oMbbs.UpdateItem(_req, c_index);
					msg = "�Խù��� ���� �Ǿ����ϴ�.";
					forward_url = "/board/view.do?c_index="+c_index+"&LISTOP="+this.listOpStr;
        	    } else {
        	        msg = "��ȣ�� Ʋ���ų� ������ �Խù��� �ƴմϴ�.";
        	    }        	    
	    	} else {
				    msg = "�߸��� ��û�Դϴ�.(" + cmd + ")";
	    	}
            chk = "T";
	    } catch (Exception e){
	        throw new MBoardException("Error in BoardAct cmd = " + cmd + ", bid = " + super.oMbbs.getBid() + ", c_index = " + c_index, e);
        }
	    _req.setAttribute("next", forward_url);
	    _req.setAttribute("message", msg);
	    if("T".equals(chk)) {
	        return MSG_PAGE;
	    } else {
	        return ERROR_MSG_PAGE;
	    }
	}
    
    /**
     * Ŀ��Ʈ�� ���
     * @return
     * @throws Exception
     */
    private void cmt_add(MyHttpServletRequest _req)
    throws Exception    {
        String c_index = listOp.get( "c_index" ,"");
    	
    	MbbsCommentBean oB = new MbbsCommentBean();
    	Setter setter = new Setter(oB, _req);
    	setter.setProperty("*");
    	if( sessionBean != null ) {
	    	oB.setUsn(sessionBean.getUsn());
	    	oB.setWname(sessionBean.getNm()); // �ʸ�
    	}
    	oB.setC_index(c_index);
    	oB.setBid(super.oMbbs.getBid());
    	oB.setC_ip(_req.getRemoteAddr());
    	
    	oMbbs.CmtAdd(oB);
    }
    
    /**
     * Ŀ��Ʈ�� ����
     * @return
     * @throws Exception
     */
    private void cmt_del(MyHttpServletRequest _req)
    throws Exception    {
        String c_index = listOp.get( "c_index" ,"");
    	String c_id = _req.getParameter("c_id","");

    	oMbbs.CmtDel(c_index, c_id);
    }    
}
