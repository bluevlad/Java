package modules.xadmin.etest;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.etest.ETestCommon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SetChooseAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
		
		result.setAttribute("frm_id", _req.getP("frm_id"));
		result.setAttribute("elm_id", _req.getP("elm_id"));
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

		final String[] searchFields = {};
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("setowner", super.sessionBean.getUsn());
		SqlWhereBuilder wb = oDb.getWhereBuilder();
        
		SetManagerDB.getListforChooser(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * 하나의 record를 읽어와서 보여준다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("setid", _req.getP("setid"));
		result.setAttribute("item", SetManagerDB.getOne(super.oDb, param));
		result.setAttribute("setitems", SetManagerDB.getSetItems(super.oDb, param));
		result.setAttribute("maxcnt", SetManagerDB.getQueMaxCnt(super.oDb, param));
		
		result.setForward("view");
	}

	


}
