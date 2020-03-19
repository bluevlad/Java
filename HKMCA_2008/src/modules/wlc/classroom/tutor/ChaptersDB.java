/*
 * Created on 2006. 9. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.util.StringUtils;

import oracle.jdbc.OracleTypes;

public class ChaptersDB extends CommonDB {
    
    /**
     * ��� ��������
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @param all
     * @return
     * @throws MdbException
     */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "SELECT a.lec_cd, a.sjt_cd, a.htmcode, a.itm_id, b.cenname itm_title, a.itm_sequence, a.chp_sdat, a.chp_edat,  " +
        "       b.htmurl, b.totpage, b.cnt_type, b.lrntime, b.daename, b.cenname, b.soname  " +
        " FROM wlc_lec_chp a, wlc_inx_lst b  " +
        " where a.itm_id = b.daecode  " +
        " and a.lec_cd = :lec_cd  " +
        " order by itm_sequence asc ";

        return oDb.selector(Map.class, sql, param);
    }

    /**
     * �ϳ��� ���ڵ� �о� ����
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT a.lec_cd, a.sjt_cd, a.htmcode, a.itm_id, b.cenname itm_title, a.itm_sequence, a.chp_sdat, a.chp_edat,  " +
        "       b.htmurl, b.totpage, b.cnt_type, a.itm_maxtimeallowed, b.daename, b.cenname, b.soname, b.cnt_height, b.cnt_width " +
        " FROM wlc_lec_chp a, wlc_inx_lst b  " +
        " where a.htmcode = b.htmcode " +
        " and a.itm_id = b.daecode" +
        " and a.lec_cd = :lec_cd  " +
        " and a.htmcode = :htmcode ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

	/**���Ǿ� ��ȣ ��������
	 */
	public static long getMaxItmId(MdbDriver oDb, Map param) throws MdbException {
		final  String sql = "SELECT NVL(MAX(to_number(itm_id)),0)+1 FROM wlc_lec_chp WHERE lec_cd = :lec_cd";
		return oDb.selectOneInt(sql, param);
	}
	
	/**���Ǿ� ���� ��������
	 */
	public static long getMaxItmSeq(MdbDriver oDb, Map param) throws MdbException {
		final  String sql = "SELECT NVL(MAX(itm_sequence),0)+1  FROM wlc_lec_chp WHERE lec_cd = :lec_cd";
		return oDb.selectOneInt(sql, param);
	}
	
    /**
     * ��� ��������
     *
     * @param oDb
     * @param param
     * @return list
     * @throws MdbException
     */
    public static List getLecChpList(MdbDriver oDb, Map param) throws MdbException {
        final String sql =
            " SELECT itm_id, itm_title, itm_lvl "   +
            "   FROM wlc_lec_chp"  +
            "   WHERE lec_cd = :lec_cd" +
            " order by itm_sequence ";

        return oDb.selector(Map.class, sql, param);
    }

    /**
     * �ϳ��� ���ڸ��� insert �Ѵ�.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {

		final String sql = "INSERT INTO wlc_lec_chp " +
		" (lec_cd, htmcode, sjt_cd, itm_id, item_title, itm_sequence, itm_maxtimeallowed, " +
		" chp_sdat, chp_edat, upt_dt, upt_id)" +
		" select :lec_cd, htmcode, sjt_cd, cenname, daecode, :itm_sequence, lrntime, " +
		" :chp_sdat, :chp_edat, sysdate, :usn" +
		" from wlc_inx_lst" +
		" where sjt_cd = :sjt_cd" +
		" and htmcode = :htmcode";
				
		return oDb.executeUpdate(sql, param);
    }

    /**
     * �ϳ��� ���ڵ带 ���� �Ѵ�.
     *
     * @param oDb
     * @param param
     * @return int
     * @throws MdbException
     */
    public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "delete from wlc_inx_lst where htmcode= = :htmcode";
        return oDb.executeUpdate(sql, param);
    }

    /**
     * �ϳ��� ���ڵ带 Update �Ѵ�.
     *
     * @param oDb
     * @param bean
     * @return int
     * @throws MdbException
     */
    public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = "UPDATE wlc_lec_chp SET " +
    			" chp_sdat                  = :chp_sdat, " +
    			" chp_edat                  = :chp_edat, " +
    			" upt_id                 = :upt_id " +
    			" WHERE lec_cd = :lec_cd AND htmcode = :htmcode" ;
    	
			return oDb.executeUpdate(sql, param);
    }

    /**
     * �Ϲ���������Ÿ�������� �����ϱ�
     *
     * @param oDb
     * @param param [leccode, itm_id]
     * @return message ó�����
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int delete(MdbDriver oDb, Map param) throws MdbException  {
    	final String sql ="delete from wlc_lec_chp where lec_cd = :lec_cd and htmcode = :htmcode";
       
        return oDb.executeUpdate(sql, param);
    }

    /**
     * ���� ��� ��������
     *
     * @param oDb
     * @param param
     * @return String
     * @throws MdbException
     */
    public static String getContent_type(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " SELECT cnt_type "   +
        "  FROM bas_lec "   +
        "  WHERE lec_cd = :lec_cd ";
        return (oDb.selectOne(sql, param));
    }

    /**
     * ��� ��������
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getSeList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql = "select a.*, b.sjt_nm " +
							" from wlc_inx_lst a, bas_sjt b " +
							" where a.sjt_cd = b.sjt_cd ";

        String sOrder = navigator.getOrderSql();
        if(null == sOrder || "".equals(sOrder) || (sOrder.trim()).equals((("ORDER BY null").trim()) )) {
            sOrder = " order by htmcode desc";
        }

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(getSeRecordCount(oDb, param, sWhere.toString()));
        navigator.sync();
    }

    /**
     * ��� ��������
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @param all
     * @return
     * @throws MdbException
     */
    public static void getSeList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql = " select * from (select a.*, b.sjt_nm  " +
        " from wlc_inx_lst a, bas_sjt b " +
        " where a.sjt_cd = b.sjt_cd ) x";

        String sOrder = navigator.getOrderSql();

        if(!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getSeRecordCount(oDb, param, sWhere.toString()));
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
     * @return long
     * @throws MdbException
     */
    private static long getSeRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final  String sql = "select count(*) from (select a.*, b.sjt_nm  " +
        " from wlc_inx_lst a, bas_sjt b " +
        " where a.sjt_cd = b.sjt_cd ) x " + sWhere;
        return oDb.selectOneInt(sql, param);
    }

    /**
     * ���Ƿ� ���� ����
     *
     * @param oDb
     * @param leccodes
     * @param htmcodes[] ������ ���� ��
     * @return int
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int multiInsert(MdbDriver oDb, String lec_cd, String sjt_cd, String[] htmcodes) throws MdbException  {

        int retValue = 0, result = 0;

        String sql = "{call sp_wlb_lec_chp_minst (?,?,?)}";
        CallableStatement   cstmt    = null;
        try {
            cstmt = oDb.prepareCall(sql);
            for(int j=0 ; j< htmcodes.length; j++) {
                cstmt.setString(1, lec_cd);
                cstmt.setString(2, sjt_cd);
                cstmt.setString(3, htmcodes[j]);
                result = cstmt.executeUpdate();

                if( result < 0) {
                    retValue = -1;
                    break;
                } else if(result == -80) {
                    retValue = -81;
                    break;
                } else {
                    retValue = retValue + result;
                }
            }
            if(result < 0) {
                oDb.rollback();
            } else {
                oDb.commit();
            }
        } catch (SQLException s) {
            retValue = 0;
            oDb.rollback();
        }finally{
            release(cstmt);
        }
        return retValue;
    }

    /**
     * ���Ƿ� ���� ����
     *
     * @param oDb
     * @param leccodes[] LECCODE # ITM_ID ��
     * @param sequences[] ������ ���� ��
     * @return int
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int updateSequence(MdbDriver oDb, String[] leccodes, String[] sequences) throws MdbException  {

        int retValue = 0, result = 0;
        int i = 0;

        String sql = "{call sp_wlb_lec_chp_sequence (?,?,?,?)}";
        CallableStatement   cstmt    = null;
        try {
            cstmt = oDb.prepareCall(sql);
            for(int j=0 ; j< leccodes.length; j++) {
                i = 1;
                String info[] = StringUtils.getItemArray(leccodes[j],'#');

                cstmt.setString(i++, info[0]);
                cstmt.setString(i++, info[1]);
                cstmt.setLong(i++,MafUtil.parseInt(sequences[j]));
                cstmt.registerOutParameter(i++,OracleTypes.INTEGER);
                result = cstmt.executeUpdate();
                if( result < 0) {
                    retValue = -1;
                    break;
                } else if(result == -80) {
                    retValue = -81;
                    break;
                } else {
                    retValue = retValue + result;
                }
            }
            if(result < 0) {
                oDb.rollback();
            } else {
                oDb.commit();
            }
        } catch (SQLException s) {
            retValue = 0;
            oDb.rollback();
        }finally{
            release(cstmt);
        }
        return retValue;
    }

}