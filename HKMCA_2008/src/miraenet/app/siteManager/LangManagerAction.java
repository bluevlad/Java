package miraenet.app.siteManager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LangManagerAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = { "s_code", "s_active_yn", "s_allnames", };
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:code"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("code", ":s_code"));
		wb.addCond(Where.like("active_yn", ":s_active_yn"));
		wb.addCond(Where.like("allnames", ":s_allnames"));
		LangManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		boolean chk = false;
		
		String codes[] = _req.getParameterValues("codes");
		int rcnt = 0;
		if(codes != null) {
			try {
				String key = null;
				oDb.setAutoCommit(false);
				
				param.put("update_usn", super.sessionBean.getUsn());
				for(int i = 0; i < codes.length; i++) {
					// code2_, active_yn_, local_name, allnames
					key = codes[i];
					param.put("code", key);
					param.put("code2", _req.getP("code2_" + key));
					param.put("active_yn", _req.getP("active_yn_" + key));
					param.put("allnames", _req.getP("allnames_" + key));
					param.put("local_name", _req.getP("local_name_" + key));
					rcnt += LangManagerDB.updateOne(super.oDb, param);
				}
				
				oDb.commit();
				chk = true;
			} catch (Exception e) {
				oDb.rollback();
				logger.error(e.getMessage());
				rcnt = 0;
			} finally {
				oDb.setAutoCommit(true);
			}
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
}
