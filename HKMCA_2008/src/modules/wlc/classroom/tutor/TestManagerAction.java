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
 * 강좌용 시험 관리자 
 * @author ubq
 *
 */
public class TestManagerAction extends BaseTutorClassAction{
	private Log logger = LogFactory.getLog(TestManagerAction.class);
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
		result.setAttribute("list", TestManagerDB.getExmmListforLecture(super.oDb, param));
		result.setForward("list");
    }
    
    /**
     * 시험 성적 보기 
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
     * 시험 성적 보기 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdStd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = {"s_userid", "s_nm"};
		// Order default 값 설정 시
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
	 * 하나의 record를 수정 한다.
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
     * 시험생성 
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
	 * 채점 및 상세 풀이 보기 
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
	 * 채점 
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
	 * 한사용자를 재응시 하도록 한다. 제출 답안삭제후 재시험 
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
	 * 한사용자를 이어볼수 있게 한다. 제출 답안 및 남은 시간은 그대로 
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
	 * 시험지 출력용 옵션 선택 페이지 
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
	 * HTML로 시험지 출력
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
	 * PDF로 시험지 출력
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
