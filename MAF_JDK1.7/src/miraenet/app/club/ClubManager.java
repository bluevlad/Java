/*
 * ClubConstants.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import maf.MafUtil;
import maf.base.BaseHttpSession;
import maf.lib.image.Thumbnail;
import maf.lib.image.ThumbnailException;
import maf.lib.mail.EMailMessageBean;
import maf.lib.mail.MailSession;
import maf.lib.mail.SendMail;
import maf.mdb.drivers.MdbDriver;
import maf.web.context.MafConfig;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import miraenet.MiConfig;
import miraenet.app.club.beans.ClubBoardBean;
import miraenet.app.club.dao.ClubBoardDB;
import miraenet.app.mboard.MBoardManager;
import miraenet.app.mboard.dao.MbbsMetaDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author goindole
 * 
 * Club관련 상수 설정
 */
public class ClubManager {

    static  Log logger = LogFactory.getLog(ClubManager.class);
    /**
     * 최대 게시판수
     */
    public static final int MAX_BOARD_CNT = 7;
    public static final String DEFAULT_SKIN_PATH = "/club/skin";
    private String club_id = null;
    File x = null;

    public ClubManager(String club_id) {
        this.club_id = club_id;
    }

    /**
     * skin 파일을 저장한다.
     * 
     * @param club_path
     * @param skin
     * @throws Exception
     */
    public void saveSkin(MyHttpServletRequest req, UploadedFile skin) throws Exception {
        try {
            String path = getSkinPath( req );
            String fname = "U_" + club_id;
            skin.SaveAs( path, fname, true );
            logger.debug( skin.getNewFile().getAbsolutePath() );
        } catch (Exception e) {
            try {
                skin.deleteFile();
            } catch (Exception e1) {}
            try {
                skin.deleteNewFile();
            } catch (Exception e1) {}
            throw new ThumbnailException( e );
        }
    }

    /**
     * 로고 이미지를 저장 한다.
     * 
     * @param club_path
     * @param logo
     * @throws Exception
     */
    public void saveLogo(MyHttpServletRequest req, UploadedFile logo) throws Exception {
        try {
            String path = getSkinPath( req );
            String logoName = "logo_" + club_id;
            logo.SaveAs( path, logoName, true );
            Thumbnail.resizeFull( logo.getTempFile(), path, logoName, 126, 98 );
        } catch (Exception e) {
            try {
                logo.deleteFile();
            } catch (Exception e1) {}
            try {
                logo.deleteNewFile();
            } catch (Exception e1) {}
            throw new ThumbnailException( e );
        }
    }

    public void deleteClub(MdbDriver oDb) throws Exception {
        try {
            ClubBoardBean[] boards = ClubBoardDB.getClubBoardAll( oDb, club_id );
            oDb.setAutoCommit(false);
            // 게시판 하나씩 삭제
            for (int i = 0; i < boards.length - 1; i++) {
                MbbsMetaDB mdb = MbbsMetaDB.getInstance();
                mdb.delete( oDb, boards[i].getBid(), MafConfig.getRealPath( MBoardManager.ATT_FILE_PATH ) );
            }
            // club_member, club_board는 on delete cascade로 같이 지워짐
            
            String sql_cb = "delete from club_board where c_id = :c_id";
            List at = new ArrayList();
            at.add(club_id);
            oDb.executeUpdate(sql_cb, at);
            String sql_cm = "delete from club_member where c_id = :c_id";
            oDb.executeUpdate(sql_cm, at);
            String sql_cmm = "delete from club_master where c_id = :c_id";
            oDb.executeUpdate(sql_cmm, at);
            oDb.commit();
        } catch (Throwable e) {
        	oDb.rollback();
            throw new ClubException( e );
        }

    }

    /**
     * skin이 저장될 절대 경로를 돌려 준다.
     * 
     * @param req
     * @return
     */
    public String getSkinPath(MyHttpServletRequest req) {
        return req.getRealPath( DEFAULT_SKIN_PATH );
    }

    public int sendMail(MdbDriver oDb, String subject, String contents, BaseHttpSession sbean) throws Exception {
        int cnt = 0;
        List list = null;
        List at = new ArrayList();
        at.add( this.club_id );
        String sql = " select mu.email, mu.nm" + " from club_member cm, mst_user mu" + " where cm.usn = mu.usn"
                + " 	  and cm.c_id = :c_id and cm.is_auth = 'T'";
        list = oDb.selector( Map.class, sql, at );
        Session mailSession = MailSession.getMailSession( MiConfig.SMTP_SERVER );
        String from_nm = sbean.getNm();
        String from_email = sbean.getUserid() + "@postech.ac.kr";

        if (!MafUtil.empty( subject ) && !MafUtil.empty( contents )) for (int i = 0; i < list.size(); i++) {
            try {
                Map x = (Map) list.get( i );
                EMailMessageBean mailmsg = new EMailMessageBean();
                mailmsg.setFrom( SendMail.getMailAddress( from_nm, from_email ) );
                mailmsg.addTo( SendMail.getMailAddress( (String) x.get( "nm" ), (String) x.get( "email" ) ) );
                mailmsg.setSubject( subject );
                mailmsg.setContents( contents );
                SendMail.sendMail( mailSession, mailmsg );
                cnt++;
            } catch (Throwable e) {

            }
        }
        return cnt;
    }

}