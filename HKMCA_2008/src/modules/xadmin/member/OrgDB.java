/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.member;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class OrgDB extends BaseDAO {

    /**
     * 목록 가져오기
     * @param oDb
     * @param param
     * @param sql
     * @param isAll
     * @return
     * @throws MdbException
     */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "SELECT org_cd, p_org_cd, org_name, nation, active_flag, address"
				+ " FROM bas_org "
				+ " START WITH org_cd = 'UBQ'"
				+ " CONNECT BY PRIOR org_cd = p_org_cd"
				+ " order siblings by org_name, org_cd"  ;
        
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
        final String sql = "select * " 
                + " from BAS_ORG " 
                + " where org_cd = :org_cd ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }
    
    /**
     * 조직 정보를 변경한다.
     * 
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertOrg(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " insert bas_org " +
                " (org_cd, p_org_cd, org_name, nation, active_flag, address) " +
                " values (:org_cd, :p_org_cd, :org_name, :nation, :active_flag, :address) ";
        return oDb.executeUpdate(sql, param);
    }
    
    /**
     * 조직 정보를 변경한다.
     * 
     * @param param
     * @return
     * @throws MdbException
     */
    public static int updateOrg(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " update bas_org " +
                " set address = :address " +
                " where org_cd = :org_cd ";
        return oDb.executeUpdate(sql, param);
    }
    
    /**
     * 조직 정보를 변경한다.
     * 
     * @param param
     * @return
     * @throws MdbException
     */
    public static int deleteOrg(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " delete bas_org " +
                " where org_cd = :org_cd ";
        return oDb.executeUpdate(sql, param);
    }
    
}       