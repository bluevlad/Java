/*
 * ClubBoardDB.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.web.http.MyHttpServletRequest;
import modules.community.club.ClubException;
import modules.community.club.beans.ClubBoardBean;
import modules.community.mboard.MBoardManager;
import modules.community.mboard.dao.MbbsMetaDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author UBQ
 *  
 */
public class ClubBoardDB {
    static  Log logger = LogFactory.getLog(ClubBoardDB.class);


    /**
     * Ŭ�� �Խ��� ����
     * 
     * @param bean
     * @param usn
     * @return
     * @throws SQLException
     */
    public static void createBoard(MdbDriver oDb, ClubBoardBean bean, String usn)
            throws MdbException {

        String sql = "{call MI_CLUB.CREATE_BOARD (?, ?, ?, ?, ?)}";

        CallableStatement pstmt = null;

        try {

            pstmt = oDb.prepareCall( sql );
            int i = 0;
            pstmt.setString( ++i, bean.getC_title() );
            pstmt.setString( ++i, bean.getFl_board_type() );
            pstmt.setString( ++i, bean.getC_id() );
            pstmt.setString( ++i, "PENS" );
            pstmt.setString( ++i, usn );

            pstmt.executeQuery();
            pstmt.close();
            pstmt = null;

        } catch (SQLException e) {
            logger.error( e.getMessage() );

            throw new MdbException( e );
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException s) {}
            }
        } //end try
    }

    /**
     * Club�� �Խ��� ����
     * @param oDb
     * @param c_id
     * @param bid
     * @throws MdbException
     * @throws ClubException
     */
    public static void deleteBoard(MdbDriver oDb, MyHttpServletRequest req, String c_id, String bid) 
    throws Exception {
        List at = new ArrayList();
        String sql1 = "delete from mbbs_meta where bid = :bid";
        String sql2 = "delete from club_board where bid = :bid and c_id = :c_id";
        String sql3 = "select count(*) from club_board where bid = :bid and c_id = :c_id";
        try {
            at.add( bid );
            at.add( c_id );
            int chk = oDb.selectOneInt( sql3, at );
            if (chk == 1) {
                MbbsMetaDB mdb = MbbsMetaDB.getInstance();
                mdb.delete( oDb, bid, req.getRealPath( MBoardManager.ATT_FILE_PATH ) );
                at.clear();
                oDb.setAutoCommit( false );
                at.add( bid );
                oDb.executeUpdate( sql1, at );
                at.add( c_id );
                oDb.executeUpdate( sql2, at );
                oDb.commit();
            } else {
                logger.error("invalid clud or bid");
                throw new ClubException( "club invalid" );
            }
        } catch (MdbException e) {
            oDb.rollback();
            logger.error( e.getMessage() );
            throw new MdbException( e );
        } finally {
            oDb.setAutoCommit( true );
        }
    }

    /**
     * �Խ��� �̸� ����
     * 
     * @param oDb
     * @param c_id
     * @param bid
     * @param fl_board_type
     * @param c_title
     * @throws MdbException
     */
    public static void modifyBoard(MdbDriver oDb, String c_id, String bid, String fl_board_type,
            String c_title) throws MdbException {
        List at = new ArrayList();
        String sql1 = "update club_board set C_TITLE = :c_title where c_id = :c_id and bid=:bid";
        String sql2 = "update mbbs_meta set SUBJECT = :c_title, FL_BOARD_TYPE = :type  where  bid=:bid";
        try {
            oDb.setAutoCommit( false );
            at.add( c_title );
            at.add( c_id );
            at.add( bid );
            int cnt = oDb.executeUpdate( sql1, at );
            // �ش� club�� �Խ������� Ȯ��
            if (cnt > 0) {
                at.clear();
                at.add( c_title );
                at.add( fl_board_type );
                at.add( bid );
                oDb.executeUpdate( sql2, at );
            }
        } catch (Exception e) {
            oDb.rollback();
            logger.error( e.getMessage() );
            throw new MdbException( e );
        } finally {
            oDb.setAutoCommit( true );
        }

    }

    /**
     * Ŭ������ ���Ǵ� �Խ��� ����(������ΰ͸�)
     * 
     * @param code
     * @return
     * @throws SQLException
     */
    public static ClubBoardBean[] getClubBoards(MdbDriver oDb, String c_id) throws MdbException {
        List list = null;
        List at = new ArrayList();
        at.add( c_id );
        String sql = " SELECT   /*+INDEX(cb PK_CLUB_BOARD) FIRST_ROWS*/"
                + "          cb.c_id, cb.bid, cb.is_public, cb.c_title, cb.POSITION, cb.c_type,"
                + "          cb.is_use, mm.fl_board_type" 
                + "     FROM club_board cb, mbbs_meta mm"
                + "    WHERE cb.bid = mm.bid AND c_id = :c_id and cb.is_use='T'" 
                + " ORDER BY POSITION";

        try {
            list = oDb.selector( ClubBoardBean.class, sql, at );

        } catch (Exception e) {
            logger.error( e.getMessage() );
            throw new MdbException( e );
        } finally {

        }

        return (ClubBoardBean[]) list.toArray( new ClubBoardBean[0] );
    }
    
    /**
     * Ŭ������ ���Ǵ� �Խ��� ����(���)
     * 
     * @param code
     * @return
     * @throws SQLException
     */
    public static ClubBoardBean[] getClubBoardAll(MdbDriver oDb, String c_id) throws MdbException {
        List list = null;
        List at = new ArrayList();
        at.add( c_id );
        String sql = " SELECT   /*+INDEX(cb PK_CLUB_BOARD) FIRST_ROWS*/"
                + "          cb.c_id, cb.bid, cb.is_public, cb.c_title, cb.POSITION, cb.c_type,"
                + "          cb.is_use, mm.fl_board_type" 
                + "     FROM club_board cb, mbbs_meta mm"
                + "    WHERE cb.bid = mm.bid AND c_id = ? " 
                + " ORDER BY POSITION";

        try {
            list = oDb.selector( ClubBoardBean.class, sql, at );

        } catch (Exception e) {
            logger.error( e.getMessage() );
            throw new MdbException( e );
        } finally {

        }

        return (ClubBoardBean[]) list.toArray( new ClubBoardBean[0] );
    }
    /**
     * �Խ��� ����
     * 
     * @param bid
     * @return
     * @throws SQLException
     */
    public static ClubBoardBean getBoardInfo(MdbDriver oDb, String c_id, String bid)
            throws MdbException {
        String sql = " SELECT cb.bid, cb.c_title, cb.c_type, mm.FL_BOARD_TYPE"
                + "   FROM club_board cb, mbbs_meta mm"
                + "  WHERE cb.bid = :bid and cb.c_id = :c_id  " + "    AND cb.bid = mm.bid";

        List at = new ArrayList();
        at.add(bid);
        at.add(c_id);
        ClubBoardBean b = null;
        try {
            b = (ClubBoardBean) oDb.selectorOne( ClubBoardBean.class, sql, at );

        } catch (Exception e) {
            logger.error( e.getMessage() );
            throw new MdbException( e );
        } finally {

        }

        return b;
    }
    
    /**
     * Ŭ�� �Խ��� ��뿩�� ����
     * 
     * @param isUse
     * @param cid
     * @return
     * @throws SQLException
     */
    public static void boardUsableConf(MdbDriver oDb, String[] bids, String cid) throws MdbException {
        String sql1 = "UPDATE club_board SET is_use='F' WHERE c_type='U' AND c_id=?";
        String sql2= "UPDATE club_board SET is_use='T' WHERE c_type='U' AND c_id=? AND bid=?";
        List at = new ArrayList();

        try {
            oDb.setAutoCommit(false);
            at.add(cid);
            oDb.executeUpdate(sql1, at);
                for (int i = 0; i < bids.length; i++) {
                    at.clear();
                    at.add(cid);
                    at.add(bids[i]);
                    oDb.executeUpdate(sql2, at);
                }
                oDb.commit();
        } catch (MdbException e) {
            oDb.rollback();
            logger.error(e.getMessage());
            throw e;
        } finally {
            oDb.setAutoCommit(true);
        } //end try

    }
}