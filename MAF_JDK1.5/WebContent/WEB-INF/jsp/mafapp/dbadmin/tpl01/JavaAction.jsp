<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<c:set var="ActionClassName" value="${className}Action" />
<c:set var="DaoClassName" value="${className}DB" />
/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package <c:out value="${packageName}"/>;
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


/**
* 
*
*
*
*/
public class <c:out value="${ActionClassName}"/> extends MafAction {
    final private Log  logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
		
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	
	/**
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {<c:forEach var="item" items="${searchColumnsList}" varStatus="status">"<mh:out value="s_${item.name}" case="lower"/>"<c:if test="${!status.last}">, </c:if></c:forEach>};

		// Order default 값 설정 시
		//NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:###"));
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

        <c:forEach var="item" items="${columns}" varStatus="status"> <c:if test="${item.search}">
        	wb.addCond(Where.like("<mh:out value="${item.name}" case="lower"/>",":s_<mh:out value="${item.name}" case="lower"/>"));</c:if></c:forEach>

		<c:out value="${DaoClassName}"/>.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
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
		<c:forEach var="item" items="${pkColumns}" varStatus="status">param.put("<mh:out value="${item.name}" case="lower"/>", _req.getP("<mh:out value="${item.name}" case="lower"/>"));
		</c:forEach>		
		result.setAttribute("item", <c:out value="${DaoClassName}"/>.getOne(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		<c:forEach var="item" items="${pkColumns}" varStatus="status">param.put("<mh:out value="${item.name}" case="lower"/>", _req.getP("<mh:out value="${item.name}" case="lower"/>"));
		</c:forEach>
		result.setAttribute("item", <c:out value="${DaoClassName}"/>.getOne(super.oDb, param));
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
		<c:forEach var="item" items="${pkColumns}" varStatus="status">param.put("<mh:out value="${item.name}" case="lower"/>", _req.getP("<mh:out value="${item.name}" case="lower"/>"));
		</c:forEach>
		
		// 최종 수정자
		param.put("update_usn",super.sessionBean.getUsn());
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
			rcnt += <c:out value="${DaoClassName}"/>.updateOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
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
			rcnt += <c:out value="${DaoClassName}"/>.insertOne(super.oDb, param);
			// 최초 등록자 
			param.put("reg_usn",super.sessionBean.getUsn());
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("add");
	}
	
//	/**
//	 * delete
//	 * 
//	 * @param _req
//	 * @param _res
//	 */	
//	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
//
//		Map param = _req.getKeyValueMap();
//		<c:forEach var="item" items="${pkColumns}" varStatus="status">param.put("<mh:out value="${item.name}" case="lower"/>", _req.getP("<mh:out value="${item.name}" case="lower"/>"));
//		</c:forEach>	
//		int rcnt= 0;
//		boolean chk = false;
//		try {
//			oDb.setAutoCommit(false);
//			rcnt += <c:out value="${DaoClassName}"/>.deleteOne(super.oDb, param);
//			oDb.commit();
//			chk = true;
//		} catch ( Exception e) {
//			oDb.rollback();
//			logger.error(e.getMessage());
//			rcnt = 0;
//		} finally {
//			oDb.setAutoCommit(true);
//		}
//		if(chk ) {
//			UrlAddress next = new UrlAddress (super.controlAction);
//			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
//			result.setNext(next);
//			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
//		} else {
//			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
//		}
//	}		

} 