/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class MafSiteDB {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static List getList(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select * from MAF_SITE ";
		
		return oDb.selector(Map.class, sql, param);
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
		final String sql = "select *   " 
				+ "from MAF_SITE " 
		        + " where site = :site  ";
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
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update MAF_SITE set "   +
				"site = :site  ,  " +
				"title = :title  ,  " +
				"short_desc = :short_desc  ,  " +
				"layout = :layout  ,  " +
				"url = :url  ,  " +
				"cust_cd = :cust_cd  " +
	            "where  site = :site ";

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
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO MAF_SITE "   +
        "    (site, site_title, layout, url) " +
        "  VALUES"   +
        "    (:site, :site_title, :layout, :url)"  ;
		
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
        final String sql = "MERGE INTO MAF_SITE new " +
            "      USING (SELECT " +
            " :site site, :site_title site_title, :layout layout, :url url  " +
        "               FROM DUAL) src " +
        "         ON (src.site = new.site) " +
        "    WHEN MATCHED THEN " +
        "       UPDATE SET " +
            " site_title = :site_title, layout = :layout, url = :url " +
        "    WHEN NOT MATCHED THEN " +
        "       INSERT (" +
            "site, site_title, layout, url " +
        "       ) VALUES (" +
            "src.site, src.site_title, src.layout, src.url " +
        "      ) ";
        
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
		final String sql = " delete from MAF_SITE  " +
	            "where  site = :site ";
		return oDb.executeUpdate(sql, param);
	}
	
}	