/*
 * 작성자 : 김상준 Created on 2004. 10. 8.
 *  
 */
package miraenet.app.mboard.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import miraenet.app.mboard.BoardConfig;
import miraenet.app.mboard.MBoardAuth;
import miraenet.app.mboard.beans.MbbsAttachBean;
import miraenet.app.mboard.beans.MbbsDataBean;

/**
 * @author 김상준(goindole@miraenet.com) Create by 2004. 10. 8.
 *  
 */
public class BoardWrite extends MBoardAction {

    public synchronized String doWork(MyHttpServletRequest _req, HttpServletResponse response)
        throws Exception {

        String cmd = _req.getParameter("cmd", BoardConfig.CMD_ADD);
        String c_index = _req.getParameter("c_index", listOp.get("c_index", ""));
        MbbsDataBean item = null;
        MbbsAttachBean[] attItems = null;
        String btnAuth = MBoardAuth.getBthAuth(super.oMbbs.getBoard(), super.sessionBean, "write");
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
            item.setWname(super.sessionBean.getNm());
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