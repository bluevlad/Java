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
 * 시험 관리
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
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {};
		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:exmid"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		ExmManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);

		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdStd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"exmid"};
		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("a:nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		CrtManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);

		result.setAttribute("navigator", navigator);
		result.setAttribute("exmid", param.get("exmid"));
		result.setForward("std");
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
		// 최종 수정자
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
	 * 하나의 record를 수정 한다.
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
	 * 하나의 record를 수정 한다.
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
    	float [] rate; //비율
    	int [] rnd; //반올림
    	float [] gap; //차이
    	int [] snd; //보정
    	int [] lst; //배정
    	int gap_num = 0; //보정위치
    	float max_num = 0; //최고
    	float min_num = 0;
		
		Map param = _req.getKeyValueMap();

		try {
        	
			List rateList = CrtManagerDB.getRateList(super.oDb, param); // 비율 리스트
			List rstList = CrtManagerDB.getRstList(super.oDb, param); // 비율 리스트
        	int totRecCnt = CrtManagerDB.getStdCnt(super.oDb, param); //이수대상자수(인원)
        	int stCnt = CrtManagerDB.get1stCnt(super.oDb, param); //1차배정인원(총인원)
			
            rate = new float[rateList.size()];
            rnd = new int[rateList.size()];

			for(int i=0; i < rateList.size(); i++) {
				Map rateMap = new HashMap();
				rateMap = (Map)rateList.get(i);
				rate[i] = MafUtil.parseFloat(rateMap.get("var").toString());
				rnd[i] = (int)(((MafUtil.parseInt(rateMap.get("var").toString()))*totRecCnt)/100 + 0.5);
			}
			if (rnd[10] == 0) {
				rnd[10] = 1; // 중앙값을 중심으로 분포하도록 하기 위함(인원1-6까지 의미가 있음)
			}
			
			stCnt = 0;
			for(int i=0; i < 11; i++) {
				stCnt = stCnt + rnd[i]*2;
			}
			stCnt = stCnt - rnd[10]; //90점대는 한번만 있으므로
			
			lst = new int[11];
	    	snd = new int[11];
			if (stCnt == totRecCnt) { //반올림의 결과를 그대로 배정
				for(int i=0; i < 11; i++) {
					lst[i] = rnd[i];
				}
			} else { //총인원과 인원의 차이가 홀수이면 90점대에 1을 더함(대칭을 이루도록 하기 위함)
	            snd = new int[11];
			    if ((totRecCnt - stCnt)%2 != 0){
			    	snd[10] = snd[10] + 1;
			    	stCnt = stCnt +1;
			    }

				//반올림의 차이값 계산
			    gap = new float[11];
				for(int i=0; i < 10; i++) {
					gap[i] = rnd[i] - rate[i]*totRecCnt/100;
				}
				
			    if (( stCnt - totRecCnt ) > 0 ) { // 배정인원이 대상인원보다 많을경우
			    	
			    	while (stCnt - totRecCnt > 0 ) { //인원이 동일해질때까지
			    		gap_num = 9;
			    		max_num = 0;
		                for (int i = 9; i >= 0;  i--) { //정규분포의 한쪽만 실행한다.
		                	if ((gap[i] > max_num) & (snd[i] == 0)) { //차이값이 가장 큰 항목을 찾을때까지 비교한다.
		                		max_num = gap[i]; // 차이값이 가장 큰 값을 기준값으로 한다.
		                		gap_num = i; //기준값의 위치를 저장한다.
		                	}
		                }
		                snd[gap_num] = -1; //결과값의 인원에서 한명을 뺀다.
		                stCnt = stCnt - 2; // 좌우 대칭이기 때문에 전체인원에서는 2명을 뺀다.
	                }
			    	
			    } else { //배정인원이 대상인원보다 적을경우
			    	
			    	while ( (stCnt - totRecCnt) < 0 ) { //인원이 동일해질때까지
			    		gap_num = 9;
			    		min_num = 0;
			    		for (int i = 9; i >= 0; i--) { //정규분포의 한쪽만 실행한다.
			    			if ((gap[i] < min_num) & (snd[i] == 0) ) { //차이값이 가장 큰 항목을 찾을때까지 비교한다.
			    				min_num = gap[i]; //차이값이 가장 큰 값을 기준값으로 한다.
			    				gap_num = i; //기준값의 위치를 저장한다.
			    			}
			    		}
			    		
			    		snd[gap_num] = 1; //결과값의 인원에서 한명을 더한다.
			    		stCnt = stCnt + 2; //좌우 대칭이기 때문에 전체인원에서는 2명을 더한다.
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
				for (int j = 1; j < lst[i]; j++) { //조견표 작성
					param.put("last_num", lst[i]+"");
					param.put("score", (100-i)+"");
					rcnt = CrtManagerDB.setNum(super.oDb, param);
				}
				for (int j = 0; j < lst[i]; j++) { //순위별 인원표 작성
					param.put("lank", lank+"");
					param.put("score", (100-i)+"");
					rcnt = CrtManagerDB.setLank(super.oDb, param);
					lank = lank + 1;
				}
			}
			for(int i=9; i >= 0; i--) { // 89 ~ 80
				for (int j=1; j <= lst[i]; j++) { //조견표 작성
					param.put("last_num", lst[i]+"");
					param.put("score", (80+i)+"");
					rcnt = CrtManagerDB.setNum(super.oDb, param);
				}
				for (int j=1; j <= lst[i]; j++) { //순위별 인원표 작성
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