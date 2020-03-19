<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="ActionClassName" value="${className}Action" />
<c:set var="DaoClassName" value="${className}DB" />
<mh:length var="ColumnCnt" value="${columns}"/>

/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2009 Rainend All rights reserved.
 */
package <c:out value="${packageName}"/>;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class <c:out value="${DaoClassName}"/> extends BaseDAO {

	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		
		List list = null;

		final String sql = " select x.* from "
		                     " (select <c:forEach var="item" items="${columns}" varStatus="status"><mh:out value="${item.name}" case="lower"/><c:if test="${!status.last}">, </c:if></c:forEach> " +
		                      " from <c:out value="${tabsname}"/> " +
		                      " ) x";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(<c:out value="${DaoClassName}"/>.getRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = " select count(*) from " +
                             " (select <c:forEach var="item" items="${columns}" varStatus="status"><mh:out value="${item.name}" case="lower"/><c:if test="${!status.last}">, </c:if></c:forEach> " +
                              " from <c:out value="${tabsname}"/> " +
                              " ) x" + sWhere;
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
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select <c:forEach var="item" items="${columns}" varStatus="status"><mh:out value="${item.name}" case="lower"/><c:if test="${status.count != ColumnCnt}">, </c:if></c:forEach> " 
				+ "from <c:out value="${tabsname}"/> " 
		        + " where <c:forEach var="item" items="${pkColumns}" varStatus="status"><c:if test="${!status.first}"> and </c:if> <mh:out value="${item.name}" case="lower"/> = :<mh:out value="${item.name}" case="lower"/></c:forEach> ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
    
    /**
     * 하나의 레코드를 Insert 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO <c:out value="${tabsname}"/> "   +
        "    (<c:forEach var="item" items="${columns}" varStatus="status"><mh:out value="${item.name}" case="lower"/><c:if test="${status.count != ColumnCnt}">, </c:if> </c:forEach>"   +
        "    )  VALUES  ( " +
        " <c:forEach var="item" items="${columns}" varStatus="status">:<mh:out value="${item.name}" case="lower"/><c:if test="${status.count != ColumnCnt}">, </c:if> </c:forEach> )"  ;
        
        return oDb.executeUpdate(sql, param);
    }
    
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update <c:out value="${tabsname}"/> set "   +
			<c:forEach var="item" items="${columns}" varStatus="status">"<mh:out value="${item.name}" case="lower"/> = :<mh:out value="${item.name}" case="lower"/> <c:if test="${status.count != ColumnCnt}">, </c:if> </c:forEach> "+
            "where <c:forEach var="item" items="${pkColumns}" varStatus="status"><c:if test="${!status.first}"> and </c:if> <mh:out value="${item.name}" case="lower"/> = :<mh:out value="${item.name}" case="lower"/></c:forEach> ";
		return oDb.executeUpdate(sql, param);
	}

    /**
     * 하나의 레코드를 merging 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
        final String sql = "MERGE INTO <c:out value="${tabsname}"/> new " +
            "      USING (SELECT " +
            <c:forEach var="item" items="${columns}" varStatus="status">":<mh:out value="${item.name}" case="lower"/> <mh:out value="${item.name}" case="lower"/>,  " +
            </c:forEach>
        "               FROM DUAL) src " +
        "         ON (src.<c:forEach var="item" items="${pkColumns}" varStatus="status"><c:if test="${!status.first}"></c:if><mh:out value="${item.name}" case="lower"/></c:forEach> = " +
        " new.<c:forEach var="item" items="${pkColumns}" varStatus="status"><c:if test="${!status.first}"></c:if><mh:out value="${item.name}" case="lower"/></c:forEach>) " +
        "    WHEN MATCHED THEN " +
        "       UPDATE SET" +
            <c:forEach var="item" items="${columns}" varStatus="status">"<mh:out value="${item.name}" case="lower"/> = src.<mh:out value="${item.name}" case="lower"/>,  " +
            </c:forEach>
        "    WHEN NOT MATCHED THEN " +
        "       INSERT (" +
            <c:forEach var="item" items="${columns}" varStatus="status">"<mh:out value="${item.name}" case="lower"/><c:if test="${status.count != ColumnCnt}">, </c:if> " +
            </c:forEach>
        "       ) VALUES (" +
            <c:forEach var="item" items="${columns}" varStatus="status">":<mh:out value="${item.name}" case="lower"/><c:if test="${status.count != ColumnCnt}">, </c:if> " +
            </c:forEach>") ";
        
        return oDb.executeUpdate(sql, param);
    }
	
	/**
	 * 하나의 레코드를 삭제 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from <c:out value="${tabsname}"/>  " +
	            "where <c:forEach var="item" items="${pkColumns}" varStatus="status"><c:if test="${!status.first}"> and </c:if> <mh:out value="${item.name}" case="lower"/> = :<mh:out value="${item.name}" case="lower"/></c:forEach> ";
		return oDb.executeUpdate(sql, param);
	}
	
}