package modules.wlc.classroom.tutor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import maf.web.mvc.view.JsonViewer;
import maf.web.mvc.view.ViewerSupport;
import miraenet.AppConfig;
import modules.common.jason.JSONObject;
import modules.xadmin.etest.ExmManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ���¿� ���� ������ 
 * @author ubq
 *
 */
public class TestManagerAction extends BaseTutorClassAction{
	private Log logger = LogFactory.getLog(TestManagerAction.class);
	private final String MESSAGE_BUNDLENAME = "common.message";
    
    /**
     * ���� ����Ʈ ���� 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", TestManagerDB.getExmmListforLecture(super.oDb, param));
		result.setForward("list");
    }
    
    /**
     * ���� ���� ���� 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
//		param.put("exmid", super.lectureInfo.getExmid());
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
		
		Map exm = TestManagerDB.getOne(super.oDb, param);
		
		result.setAttribute("item", exm);
		result.setAttribute("exmStat", TestManagerDB.getTestResultByExmId(super.oDb, param));
		result.setForward("view");

    }
    
    /**
     * ���� ���� ���� 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdStd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = {"s_userid", "s_nm"};
		// Order default �� ���� ��
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		param.put("exmid", _req.getP("exmid"));
//		param.put("exmid", super.lectureInfo.getExmid());
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
		
		Map exm = TestManagerDB.getOne(super.oDb, param);
		result.setAttribute("item", exm);

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		
		wb.addCond(Where.like("userid", ":s_userid"));
		wb.addCond(Where.like("nm", ":s_nm"));
		
		TestManagerDB.getRstListforLecture(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("std");
    }
    
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("usn", super.sessionBean.getUsn());
        
        result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
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
		param.put("exmid", _req.getP("exmid"));
		
		result.setAttribute("item", TestManagerDB.getOne(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * �ϳ��� record�� ���� �Ѵ�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		param.put("exmid", _req.getP("exmid"));
		param.put("upt_id", super.sessionBean.getUsn());
		
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = TestManagerDB.updateOne(super.oDb, param);
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
            param.put("reg_id", super.sessionBean.getUsn());
            param.put("upt_id", super.sessionBean.getUsn());
    		param.put("lec_cd", super.lectureInfo.getLec_cd());

            rcnt = TestManagerDB.insertOne(super.oDb, param);

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
     * ������� 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdMaketest(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	boolean chk = false;
    	Map param = new HashMap();
    	int rcnt = 0;
    	param.put("usn", super.sessionBean.getUsn());
    	param.put("lec_cd", super.lectureInfo.getLec_cd());
    	param.put("exmpage", _req.getP("exmpage","1"));
    	try {
    		oDb.setAutoCommit(false);
    		rcnt = TestManagerDB.MakeExam(super.oDb, param);
    		oDb.commit();
    		chk = true;
    	} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress (super.controlAction);
	        next.addParam("cmd", "view");
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}

    }
    
	/**
	 * ä�� �� �� Ǯ�� ���� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdShowDetailScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"usn"};
		super.chkEmpty(_req, chks);
		Map param = _req.getKeyValueMap();
		param.put("exmid", _req.getP("exmid"));
//		param.put("exmid", super.lectureInfo.getExmid());
        param.put("lang", super.locale.getLanguage());
		
		Map item = TestManagerDB.getUserResult(super.oDb, param);
		if (item != null) {
			result.setAttribute("uitem", item);
	        result.setAttribute("items", TestManagerDB.getUserResultItem(super.oDb, param));
	        result.setForward("showdetail");
		} else {
			result.setSuccess(false, new ResultMessage("etest.common", "script.exm.notapply"));
		}
		
	}
	
	/**
	 * ä�� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdMarkScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] updateds = _req.getParameterValues("updated");
		String[] chks = {"usn"};
		int rcnt = 0;
		super.chkEmpty(_req, chks);
		Map param = _req.getKeyValueMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("upt_id", super.sessionBean.getUsn());
		
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			if(updateds != null) {
				for(int i = 0; i < updateds.length; i++) {
					//[correct_yn, usrscore, update_dt, update_id, exmid, usn,lecnumb, qseq]
					param.put("qseq", updateds[i]);
					param.put("correct_yn", "Y".equals(_req.getP("correct_" + updateds[i])) ?"Y":"N");
					param.put("usrscore", _req.getP("usrscore_"+ updateds[i]));
					rcnt += TestManagerDB.updateRstItems(super.oDb,param);
				}
			}
			rcnt = TestManagerDB.updateRst(super.oDb,param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "std");
			next.addParam("exmid", _req.getP("exmid"));
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * �ѻ���ڸ� ������ �ϵ��� �Ѵ�. ���� ��Ȼ����� ����� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSetRetry(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		param.put("exmid", _req.getP("exmid"));
		
		int i = TestManagerDB.setRetry(oDb, param);
		
		JSONObject jobj = new JSONObject();
    	_res.setContentType(JsonViewer.CONTENTS_TYPE);

    	_res.setHeader("Cache-Control", "no-cache");
    	if(i>0) {
    		jobj.put("qseq", _req.getP("qseq"));
    	}
    	
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		result.setForward("dummy");
	}
	
	/**
	 * �ѻ���ڸ� �̾�� �ְ� �Ѵ�. ���� ��� �� ���� �ð��� �״�� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSetContinue(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		param.put("exmid", _req.getP("exmid"));

		int i = TestManagerDB.setContinue(oDb, param);
		
		JSONObject jobj = new JSONObject();
    	_res.setContentType(JsonViewer.CONTENTS_TYPE);

    	_res.setHeader("Cache-Control", "no-cache");
    	if(i>0) {
 
    		jobj.put("qseq", _req.getP("qseq"));
    	}
    	
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		result.setForward("dummy");
	}

	
	/**
	 * ������ ��¿� �ɼ� ���� ������ 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdPrint(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
		param.put("exmid", _req.getP("exmid"));
		
		result.setAttribute("item", TestManagerDB.getOne(super.oDb, param));
		result.setAttribute("setlangs", TestManagerDB.getLangList(super.oDb, param));
		result.setForward("print");
	}
	
	/**
	 * HTML�� ������ ���
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdPrintHTML(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
		
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
        param.put("lang", super.locale.getLanguage());
        
		Map item = TestManagerDB.getOne(super.oDb, param);
		
		result.setAttribute("item", item);
		result.setAttribute("list", TestManagerDB.getPrintList(super.oDb, param));
		result.setForward("printHTML");
	}
	
	/**
	 * PDF�� ������ ���
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdPrintPDF(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
		
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
        param.put("lang", super.locale.getLanguage());

        result.setAttribute("list", TestManagerDB.getOne(super.oDb, param));
		
		Map item = TestManagerDB.getOne(super.oDb, param);
		
		param.put("p_filepath", _req.getRealPath(AppConfig.DEFAULT_EXAM_FILE_PATH) );
		param.put("p_coursename", item.get("exmtitle"));
		
		ViewerSupport.setPapameterMap(_req, param);
		ViewerSupport.setDataSourceList(_req, TestManagerDB.getPrintList(super.oDb, param));
		result.setForward("jpdf");
	}
}
