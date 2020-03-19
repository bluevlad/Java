<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app.${fn:replace(LISTOP.ht.table_name,'_','')}.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.app.dbadmin.beans.ColumnBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
<mh:replace var="dao" value="LISTOP.ht.table_name" before="_" after=""/>
<c:set var="dao" value="${dao}DB"/>
public class <c:out value="${dao}"/> {
	private static <c:out value="${dao}"/> instance;
	private Log logger = LogFactory.getLog(this.getClass());
	
	private <c:out value="${dao}"/>() {
	}

	public static synchronized ${fn:replace(LISTOP.ht.table_name,'_','')}DB getInstance() {
		if (instance == null) instance = new ${fn:replace(LISTOP.ht.table_name,'_','')}DB();
		return instance;
	}
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String[] searchFields) throws MdbException {
		
		List list = null;

		final String sql = " select * from ${LISTOP.ht.table_name} ";
		StringBuffer sWhere = new StringBuffer();
		String t = null;
		
		for(int i = 0 ;i < searchFields.length; i++) {
			t = (String) param.get(searchFields[i]);
			if(!MafUtil.empty(t)) {
				if (sWhere.length() > 0 ) {
					sWhere.append(" and " );
				} else {
					sWhere.append(" where ");
				}
				sWhere.append( " instr(" + searchFields[i] + ", :" + searchFields[i] + ") > 0" );
			}
		}
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
		navigator.sync();
		
	}
	
	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from ${LISTOP.ht.table_name} " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public Map getOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = "select * from ${LISTOP.ht.table_name} " 
		             + "where <c:forEach var="item" items="${navigator.list}" varStatus="status"><c:if test="${item.pk > '0'}"> ${fn:toLowerCase(item.name)} = :${fn:toLowerCase(item.name)} and</c:if></c:forEach> ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int updateOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " update ${LISTOP.ht.table_name} set "   +
		<c:forEach var="item" items="${navigator.list}" varStatus="status"><c:if test="${item.pk == '0'}"> " ${fn:toLowerCase(item.name)} = :${fn:toLowerCase(item.name)} ," +</c:if>
		</c:forEach>
						 "where <c:forEach var="item" items="${navigator.list}" varStatus="status"><c:if test="${item.pk > '0'}"> ${fn:toLowerCase(item.name)} = :${fn:toLowerCase(item.name)} and</c:if></c:forEach> ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int InsertOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " INSERT INTO ${LISTOP.ht.table_name}"   +
		"    (<c:forEach var="item" items="${navigator.list}" varStatus="status">${fn:toLowerCase(item.name)},</c:forEach>)"   +
		"  VALUES"   +
		"    (<c:forEach var="item" items="${navigator.list}" varStatus="status">:${fn:toLowerCase(item.name)},</c:forEach>)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
}