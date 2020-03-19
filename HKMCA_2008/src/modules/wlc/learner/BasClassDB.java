/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (??)????? All rights reserved.
 */
package modules.wlc.learner;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class BasClassDB extends BaseDAO {

    //private Log logger = LogFactory.getLog(this.getClass());

    /**
     * ��� ��������
     * @param oDb
     * @param param
     * @param sql
     * @param isAll
     * @return
     * @throws MdbException
     */
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {

        List list = null;

        final String sql = " SELECT * "   +
        "   FROM (SELECT crs.crs_cd, b.*"   +
        "           FROM bas_lec b, bas_sjt s, bas_crs crs"   +
        "          WHERE b.sjt_cd = s.sjt_cd"   +
        "            AND s.crs_cd = crs.crs_cd) x"  ;

        String sOrder = navigator.getOrderSql();

        if(!isAll) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(BasClassDB.getRecordCount(oDb, param, sWhere.toString()));
            navigator.sync();
        }

        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
    }

    /**
     * ���ڵ� ī��Ʈ ��������
     *
     * @param oDb
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final  String sql = " SELECT COUNT (*)"   +
		        "   FROM (SELECT crs.crs_cd, b.*"   +
        "                   FROM bas_lec b, bas_sjt s, bas_crs crs"   +
        "                   WHERE b.sjt_cd = s.sjt_cd"   +
        "                   AND s.crs_cd = crs.crs_cd) x"  
        		+ sWhere;
        return oDb.selectOneInt(sql, param);
    }

    

    /**
     * ����.���� ����Ʈ�� �����´�.
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static List getCrsSub(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " select 'ROOT' as crs_cd, 'ROOT' as crs_nm, "   +
        "  crs_cd as sjt_cd, crs_nm as sjt_nm "   +
        "   from bas_crs"   +
        "  union all"   +
        "  select a.crs_cd, a.crs_nm, b.sjt_cd, b.sjt_nm "   +
        "   from bas_crs a, bas_sjt b "   +
        "   where a.crs_cd = b.crs_cd"  ;
        return oDb.selector(Map.class, sql, param);
    }

    /**
     * ������û�� �Ѵ�.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
        int retValue = 0;
        int leccount = 0;
        
        CallableStatement   cstmt    = null;
        String sql = "{call sp_wcc_lec_req_insert (:usn,:lec_cd,?)}";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql1 = "SELECT count(usn)  "
            + "    FROM wlc_lec_req WHERE usn = :usn AND lec_cd=:lec_cd AND req_stat != 'CA' ";
        try {
            //���Ը����� ��� �̹� ������û�� �� �������� �˻�..
            if("R".equals(param.get("lec_type"))){
                pstmt = oDb.prepareStatement(sql1, param);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    leccount = rs.getInt(1);
                }
            }            
            //�̹� ��û������..
            if(leccount > 0){
                retValue = -99;
            } else {      
                cstmt = oDb.prepareCall(sql, param);
                cstmt.registerOutParameter(3, java.sql.Types.INTEGER);

                cstmt.executeUpdate();
                retValue = cstmt.getInt(3);
                if(retValue < 0) {
                    retValue = -1;
                }
            }
        } catch (SQLException s) {
            retValue = 0;
            throw new MdbException(s);
        }
        return retValue;
    }   
    
}