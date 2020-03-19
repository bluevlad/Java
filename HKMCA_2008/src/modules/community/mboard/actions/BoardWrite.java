/*
 * bluevlad Created on 2004. 10. 8.
 *  
 */
package modules.community.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import modules.community.mboard.BoardConfig;
import modules.community.mboard.MBoardAuth;
import modules.community.mboard.admin.BoardAction;
import modules.community.mboard.beans.MbbsAttachBean;
import modules.community.mboard.beans.MbbsDataBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad.
 *  
 */
public class BoardWrite extends BoardAction {
	private  Log logger = LogFactory.getLog(BoardView.class);

    public synchronized String doWork(MyHttpServletRequest _req, HttpServletResponse response) throws Exception {

        String cmd = _req.getParameter("cmd", BoardConfig.CMD_ADD);
        String c_index = _req.getParameter("c_index", listOp.get("c_index", ""));
        MbbsDataBean item = null;
        MbbsAttachBean[] attItems = null;
        String btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "write", item);
        super.map.put(BoardConfig.PARAM_BTN_AUTH, btnAuth);
        
        if (BoardConfig.CMD_ADD_REPLY.equals(cmd)) {
            if(MBoardAuth.chkReplyAuth(super.oMbbs.getBoard(), super.sessionBean)) {
	            listOp.put("cmd", BoardConfig.CMD_ADD_REPLY);
	            cmd = BoardConfig.CMD_ADD_REPLY;
            }
        } else {
            if ("".equals(c_index)) {
                listOp.put("cmd", BoardConfig.CMD_ADD);
                cmd = BoardConfig.CMD_ADD;
            } else {
                listOp.put("cmd", BoardConfig.CMD_UPDATE);
                cmd = BoardConfig.CMD_UPDATE;
            }
        }
        listOp.put("c_index", c_index);
        // 추가가 아닐경우:Edit, Reply는 DB에서 기존 정보 읽어 온다.

	    if (!BoardConfig.CMD_ADD.equals(cmd)) {
            item = oMbbs.getOne(c_index);
        } else {
            item = new MbbsDataBean();
//    	    logger.error("super.sessionBean : " + super.sessionBean);
    	    if (!"".equals(super.sessionBean) && super.sessionBean != null) {
    	    	item.setWname(super.sessionBean.getNm());
    	    }
        }
        
        if (BoardConfig.CMD_ADD_REPLY.equals(cmd) && item != null) {
            item.setC_subject("Re: " + item.getC_subject());
            item.setC_email("");
            item.setWname("");
            item.setC_content("");
        } else if (BoardConfig.CMD_UPDATE.equals(cmd)) {
            attItems = oMbbs.getAttchList(c_index);
            _req.setAttribute("attItems", attItems);
        }

        _req.setAttribute("c_index", c_index);
        _req.setAttribute("cmd", cmd);
        _req.setAttribute("item", item);
        //forward_url = oMbbs.oBoard.getFL_BOARD_TYPE();
        return "boardWrite";

    }

}