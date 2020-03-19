/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.etest;

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
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ���� ����
 *
 *
 *
 */
public class CrtManagerAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "etest.common";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
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
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {};
		// Order default �� ���� ��
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:exmid"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		ExmManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);

		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

	/**
	 * ��� ��������
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdStd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"exmid"};
		// Order default �� ���� ��
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("a:nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		CrtManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);

		result.setAttribute("navigator", navigator);
		result.setAttribute("exmid", param.get("exmid"));
		result.setForward("std");
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
		// ���� ������
		param.put("upt_id", super.sessionBean.getUsn());
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt += ExmManagerDB.updateOne(super.oDb, param);
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
	 * �ϳ��� record�� ���� �Ѵ�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdMakeLank(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = CrtManagerDB.makeLank(super.oDb, param);
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
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.lank.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.lank.fail", new Integer(rcnt)));
		}
	}

	/**
	 * �ϳ��� record�� ���� �Ѵ�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdMakeCrtVar(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		param.put("exmid", _req.getP("exmid"));
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = CrtManagerDB.makeCrtVar(super.oDb, param);
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
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.crt.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.crt.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdMakeRst(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
    	float [] rate; //����
    	int [] rnd; //�ݿø�
    	float [] gap; //����
    	int [] snd; //����
    	int [] lst; //����
    	int gap_num = 0; //������ġ
    	float max_num = 0; //�ְ�
    	float min_num = 0;
		
		Map param = _req.getKeyValueMap();

		try {
        	
			List rateList = CrtManagerDB.getRateList(super.oDb, param); // ���� ����Ʈ
			List rstList = CrtManagerDB.getRstList(super.oDb, param); // ���� ����Ʈ
        	int totRecCnt = CrtManagerDB.getStdCnt(super.oDb, param); //�̼�����ڼ�(�ο�)
        	int stCnt = CrtManagerDB.get1stCnt(super.oDb, param); //1�������ο�(���ο�)
			
            rate = new float[rateList.size()];
            rnd = new int[rateList.size()];

			for(int i=0; i < rateList.size(); i++) {
				Map rateMap = new HashMap();
				rateMap = (Map)rateList.get(i);
				rate[i] = MafUtil.parseFloat(rateMap.get("var").toString());
				rnd[i] = (int)(((MafUtil.parseInt(rateMap.get("var").toString()))*totRecCnt)/100 + 0.5);
			}
			if (rnd[10] == 0) {
				rnd[10] = 1; // �߾Ӱ��� �߽����� �����ϵ��� �ϱ� ����(�ο�1-6���� �ǹ̰� ����)
			}
			
			stCnt = 0;
			for(int i=0; i < 11; i++) {
				stCnt = stCnt + rnd[i]*2;
			}
			stCnt = stCnt - rnd[10]; //90����� �ѹ��� �����Ƿ�
			
			lst = new int[11];
	    	snd = new int[11];
			if (stCnt == totRecCnt) { //�ݿø��� ����� �״�� ����
				for(int i=0; i < 11; i++) {
					lst[i] = rnd[i];
				}
			} else { //���ο��� �ο��� ���̰� Ȧ���̸� 90���뿡 1�� ����(��Ī�� �̷絵�� �ϱ� ����)
	            snd = new int[11];
			    if ((totRecCnt - stCnt)%2 != 0){
			    	snd[10] = snd[10] + 1;
			    	stCnt = stCnt +1;
			    }

				//�ݿø��� ���̰� ���
			    gap = new float[11];
				for(int i=0; i < 10; i++) {
					gap[i] = rnd[i] - rate[i]*totRecCnt/100;
				}
				
			    if (( stCnt - totRecCnt ) > 0 ) { // �����ο��� ����ο����� �������
			    	
			    	while (stCnt - totRecCnt > 0 ) { //�ο��� ��������������
			    		gap_num = 9;
			    		max_num = 0;
		                for (int i = 9; i >= 0;  i--) { //���Ժ����� ���ʸ� �����Ѵ�.
		                	if ((gap[i] > max_num) & (snd[i] == 0)) { //���̰��� ���� ū �׸��� ã�������� ���Ѵ�.
		                		max_num = gap[i]; // ���̰��� ���� ū ���� ���ذ����� �Ѵ�.
		                		gap_num = i; //���ذ��� ��ġ�� �����Ѵ�.
		                	}
		                }
		                snd[gap_num] = -1; //������� �ο����� �Ѹ��� ����.
		                stCnt = stCnt - 2; // �¿� ��Ī�̱� ������ ��ü�ο������� 2���� ����.
	                }
			    	
			    } else { //�����ο��� ����ο����� �������
			    	
			    	while ( (stCnt - totRecCnt) < 0 ) { //�ο��� ��������������
			    		gap_num = 9;
			    		min_num = 0;
			    		for (int i = 9; i >= 0; i--) { //���Ժ����� ���ʸ� �����Ѵ�.
			    			if ((gap[i] < min_num) & (snd[i] == 0) ) { //���̰��� ���� ū �׸��� ã�������� ���Ѵ�.
			    				min_num = gap[i]; //���̰��� ���� ū ���� ���ذ����� �Ѵ�.
			    				gap_num = i; //���ذ��� ��ġ�� �����Ѵ�.
			    			}
			    		}
			    		
			    		snd[gap_num] = 1; //������� �ο����� �Ѹ��� ���Ѵ�.
			    		stCnt = stCnt + 2; //�¿� ��Ī�̱� ������ ��ü�ο������� 2���� ���Ѵ�.
			    	}
			    }
				
			}

			lst = new int[11];
		    for(int i = 0; i < 11; i++) {
		    	lst[i] = rnd[i] + snd[i];
		    }

			rcnt = CrtManagerDB.deleteLank(super.oDb, param);
		    int lank = 1;
		    
			for(int i=0; i <= 10; i++) { // 100 ~ 90
				for (int j = 1; j < lst[i]; j++) { //����ǥ �ۼ�
					param.put("last_num", lst[i]+"");
					param.put("score", (100-i)+"");
					rcnt = CrtManagerDB.setNum(super.oDb, param);
				}
				for (int j = 0; j < lst[i]; j++) { //������ �ο�ǥ �ۼ�
					param.put("lank", lank+"");
					param.put("score", (100-i)+"");
					rcnt = CrtManagerDB.setLank(super.oDb, param);
					lank = lank + 1;
				}
			}
			for(int i=9; i >= 0; i--) { // 89 ~ 80
				for (int j=1; j <= lst[i]; j++) { //����ǥ �ۼ�
					param.put("last_num", lst[i]+"");
					param.put("score", (80+i)+"");
					rcnt = CrtManagerDB.setNum(super.oDb, param);
				}
				for (int j=1; j <= lst[i]; j++) { //������ �ο�ǥ �ۼ�
					param.put("lank", lank+"");
					param.put("score", (80+i)+"");
					rcnt = CrtManagerDB.setLank(super.oDb, param);
					lank = lank + 1;
				}
			}

		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt>0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.rst.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.make.rst.fail", new Integer(rcnt)));
		}
	}
}