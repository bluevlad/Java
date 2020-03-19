/*
 * Created on 2006. 08. 21
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PagerAction extends MafAction {
	final private Log logger = LogFactory.getLog(PagerAction.class);

	SerializeHashtable listOp = null;

	private final String MESSAGE_BUNDLENAME = "common.message";
	private String msgOwner = "";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
		this.msgOwner = super.sessionBean.getUsn();
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}

	/**
	 * ��� ��������
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdListRcv(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = { "search_type", "msg_title", "search_txt" };

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("msg_dt", true));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("msg_rcv_usn", this.msgOwner);

		SqlWhereBuilder wb = oDb.getWhereBuilder();

		wb.addCond(Where.eq("msg_rcv_usn", ":msg_rcv_usn"));
		if("nm".equals(_req.getP("search_type"))) {
			wb.addCond(Where.like("msg_snd_nm",":search_txt"));
		} else if("userid".equals(_req.getP("search_type"))) {
			wb.addCond(Where.like("msg_snd_userid",":search_txt"));
		}
		wb.addCond(Where.like("msg_title", ":title"));

		PagerDB.getListRcv(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("listRcv");
	}
	/**
	 * ��� ��������
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdListSnd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = { "search_type", "msg_title", "search_txt" };

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("msg_dt", true));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("msg_snd_usn", this.msgOwner);

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("msg_snd_usn", ":msg_snd_usn"));
		if("nm".equals(_req.getP("search_type"))) {
			wb.addCond(Where.like("msg_rcv_nm",":search_txt"));
		} else if("userid".equals(_req.getP("search_type"))) {
			wb.addCond(Where.like("msg_rcv_userid",":search_txt"));
		}

		wb.addCond(Where.like("msg_title", ":title"));

		PagerDB.getListSnd(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("listSnd");
	}
	/**
	 * view
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdMsgViewSnd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("msg_no", _req.getP("msg_no"));
		result.setAttribute("item", PagerDB.getSndOne(super.oDb, param));
		result.setForward("msgViewSnd");
	}
	/**
	 * view
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdMsgViewRcv(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("msg_no", _req.getP("msg_no"));
		result.setAttribute("item", PagerDB.getRcvOne(super.oDb, param));
		result.setForward("msgViewRcv");
	}

	/**
	 * ����������
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdMsgWrite(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String msg_no_p = _req.getP("msg_no_p");
		if(!MafUtil.empty(msg_no_p)) {
			Map param = new HashMap();
			param.put("msg_no", msg_no_p);

			Map item = PagerDB.getRcvOne(super.oDb, param);
			item.put("msg_title", "");
			item.put("msg_info", "");
			result.setAttribute("item", item);
		}
		result.setForward("msgWrite");
	}

    /**
     * �׷�����������(WLC)
     *
     * @param _req
     * @param _res
     */
    public void cmdGrpMsgWrite(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String msg_no_p = _req.getP("msg_no_p");
        if(!MafUtil.empty(msg_no_p)) {
            Map param = new HashMap();
            param.put("msg_no", msg_no_p);

            Map item = PagerDB.getRcvOne(super.oDb, param);
            item.put("msg_title", "");
            item.put("msg_info", "");
            result.setAttribute("item", item);
        } else {
            String params[] = (String[])_req.getParameterValues("pager_send_id");//ID#�̸�
            StringBuffer userids = new StringBuffer();
            StringBuffer userinfo = new StringBuffer();
            if(params != null && params.length > 0) {
                for(int i=0; i < params.length; i++) {
                    String[] info = StringUtils.delimitedListToStringArray(params[i], "#");
                    userids.append(info[0]+";");
                    userinfo.append(info[1]+";");
                }
            }
            Map item = new HashMap();
            item.put("msg_snd_userid", userids.toString());
            item.put("msg_snd_nm", userinfo.toString());
            result.setAttribute("item", item);
        }

        result.setForward("msgWrite");
    }

	/**
	 * insert
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSendMsg(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		param.put("msg_snd_usn", this.msgOwner);
		param.put("file_name", "");
		boolean chk = false;
		String msg_rcv_userid = _req.getP("msg_rcv_userid");
		String[] vusn = null;
		
		if(MafUtil.empty(msg_rcv_userid)) {
			this.cmdMsgWrite(_req, _res);
		} else {

			String[] userids = msg_rcv_userid.split(";");
			int rcnt =0;

			try {
	        	oDb.setAutoCommit(false);
				for(int i = 0; i < userids.length; i++) {
					param.put("userid", userids[i]);
					param.put("msg_rcv_usn", PagerDB.RcvUserids(super.oDb, param));
					rcnt = PagerDB.sendMessage(super.oDb, param);
				}
	        	oDb.commit();
	        	chk = true;
			} catch (Exception e) {
	        	logger.debug(e);
	        	oDb.rollback();
			}

			if (chk) {
				result.setNext(super.controlAction+"?cmd=listSnd");
				result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
			} else {
				result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
			}
		}
	}

	/**
	 * update
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		param.put("msg_no", _req.getP("msg_no"));

		int rcnt = PagerDB.updateOne(super.oDb, param);

		if (rcnt > 0) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * ���� ���� ����
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdDeleteRcv(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		String[] id_chks = _req.getParameterValues("id_chk");
		param.put("msg_rcv_usn", this.msgOwner);
		boolean chk = false;

		int rcnt = 0;
		if(id_chks != null && id_chks.length > 0) {
			oDb.setAutoCommit(false);
			try {
				for(int i =0; i < id_chks.length; i++ ) {
					param.put("msg_no", id_chks[i]);
					rcnt += PagerDB.deleteOneRcv(super.oDb, param);
				}
				oDb.commit();
			} catch (Exception e) {
				oDb.rollback();
				logger.error(e.getMessage());
				rcnt = 0;
			}
			oDb.setAutoCommit(true);
			chk = true;
		} else {
			rcnt = PagerDB.deleteOneRcv(super.oDb, param);
			chk = true;
		}
		if (chk) {
			result.setNext(super.controlAction +"?"+ MafConstant.CMD_NAME +"=listRcv");
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
	}
	
	/**
	 * ���� ���� ����
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdDeleteSnd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] id_chks = _req.getParameterValues("id_chk");
		String msg_no = _req.getP("msg_no");
		Map param = new HashMap();
		param.put("msg_snd_usn", this.msgOwner);
		boolean chk = false;

		int rcnt = 0;
		if(id_chks != null && id_chks.length > 0) {
			oDb.setAutoCommit(false);
			try {
				for(int i =0; i < id_chks.length; i++ ) {
					param.put("msg_no", id_chks[i]);
					rcnt += PagerDB.deleteOneSnd(super.oDb, param);
				}
				oDb.commit();
			} catch (Exception e) {
				oDb.rollback();
				logger.error(e.getMessage());
				rcnt = 0;
			}
			oDb.setAutoCommit(true);
			chk = true;
		} else {
			param.put("msg_no", msg_no);
			rcnt = PagerDB.deleteOneSnd(super.oDb, param);
			chk = true;
		}
		if (chk) {
			result.setNext(super.controlAction +"?"+ MafConstant.CMD_NAME +"=listSnd");
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}

	/**
	 * �ּҷ� ����
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAddressBook(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = {"msg_grp_id", "search_type","search_txt"};

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("msg_rc_id", this.msgOwner);

		List groupList = PagerDB.getAddressGroup(super.oDb, this.msgOwner);
		result.setAttribute("groupList", groupList);

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("msg_rc_isdel", "'N'",true));
		wb.addCond(Where.eq("msg_rc_id", ":msg_rc_id"));

//		dao.getAddressGroupItem(oDb, msgGroupId)(super.oDb, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("addressBook");
	}
}
