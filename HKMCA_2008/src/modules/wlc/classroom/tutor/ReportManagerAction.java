package modules.wlc.classroom.tutor;

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
import maf.web.mvc.beans.ResultMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 강좌용 시험 관리자 
 * @author ubq
 *
 */
public class ReportManagerAction extends BaseTutorClassAction{
	private Log logger = LogFactory.getLog(ReportManagerAction.class);
	private final String MESSAGE_BUNDLENAME = "common.message";
    
    /**
     * 시험 리스트 보기 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", ReportDB.getList(super.oDb, param));
		result.setForward("list");
    }
    
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
		result.setForward("add");
	}
    
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
		param.put("rptcode", _req.getP("rptcode"));
		result.setAttribute("item", ReportDB.getOne(super.oDb, param));
		result.setAttribute("list", ReportDB.getRptBnk(super.oDb, param));
		result.setForward("edit");
	}
	
    /**
     * 시험 성적 보기 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdStd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = { "s_userid", "s_nm", "rptcode" };
		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("pds_cd", "RPT_GIV");

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("userid", ":s_userid"));
		wb.addCond(Where.like("nm", ":s_nm"));
		
		ReportDB.getStdList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("item", ReportDB.getOne(super.oDb, param));
		result.setAttribute("navigator", navigator);
		result.setAttribute("rptcode", param.get("rptcode"));
		result.setForward("std");
    }

	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
            param.put("upt_id", super.sessionBean.getUsn());
    		param.put("lec_cd", super.lectureInfo.getLec_cd());

            rcnt = ReportDB.insertOne(super.oDb, param);

			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 하나의 record를 수정 한다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		String rpt_chk[] = _req.getParameterValues("rpt_chk");
		param.put("upt_id", super.sessionBean.getUsn());
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			ReportDB.deleteQue(super.oDb, param);
			
			if(rpt_chk!=null) {
				for(int i=0;i < rpt_chk.length; i++) {
		        	param.put("quecode", rpt_chk[i]);
					ReportDB.insertQue(super.oDb, param);
				}
			}
			
			rcnt = ReportDB.updateOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
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

	/**
	 * 하나의 record를 수정 한다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = ReportDB.deleteOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * 채점 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdMarkScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] vusn = _req.getParameterValues("vusn");

		int rcnt = 0;

		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("rptcode", _req.getP("rptcode"));
		param.put("upt_id", super.sessionBean.getUsn());

		try {
			oDb.setAutoCommit(false);
			if(vusn != null) {
				for(int i = 0; i < vusn.length; i++) {
					//[correct_yn, usrscore, update_dt, update_id, exmid, usn,lecnumb, qseq]
					param.put("usn", vusn[i]);
					param.put("usrscore", _req.getP("usrscore_"+ vusn[i]));
					if ((_req.getP("file_"+ vusn[i])) != null && !"".equals(_req.getP("file_"+ vusn[i]))){
						rcnt = ReportDB.updatePoint(super.oDb,param);
					}
				}
			}
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt>0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "std");
			next.addParam("rptcode", _req.getP("rptcode"));
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
		}
	}
	
}