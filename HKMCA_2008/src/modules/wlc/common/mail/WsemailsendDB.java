/*
 * Created on 2006. 11. 06.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.common.mail;

import java.util.Map;

import maf.MafUtil;
import maf.mdb.CommonDB;
import maf.mdb.MdbParameters;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbOCI8;
import maf.mdb.exceptions.MdbException;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;


public class WsemailsendDB extends CommonDB {
    //private Log logger = LogFactory.getLog(this.getClass());
 
	/**
     * 메일발송 정보를 저장한다.
     *
     * @param oDb
     * @param param
     * @return long
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
        String seq_mail_send_id = oDb.getSequence("seq_mail_send_id");
        param.put("send_id", seq_mail_send_id);

        String sql = " INSERT INTO wse_mail_send "   +
        "    (send_id, mail_title, mail_text, mail_bcc, mail_cc, send_dt, userid)"   +
        "  VALUES"   +
        "    (:send_id, :mail_title, '', :mail_bcc, :mail_cc, sysdate, :userid)"  ;
        oDb.executeUpdate(sql, param);     
 
        MdbParameters p = new MdbParameters();
        p.add("send_id", seq_mail_send_id);
        ((MdbOCI8) oDb).updateCLOB("wse_mail_send", "mail_text", (String)param.get("mail_text"), p);
 
        return (MafUtil.parseInt(seq_mail_send_id));
    }

    /**
     * 메일발송 이력 정보를 저장한다.
     *
     * @param oDb
     * @param param
     * @return long
     * @throws MdbException
     */
    public static int insertHistory(MdbDriver oDb, Map param) throws MdbException {
        String sql = " INSERT INTO wse_mail_history "   +
        "    (send_no, send_id, userid, leccode, user_nm, email, result, from_id, " +
        "     send_dt, mail_title, mail_text, mail_type, update_dt, update_id )"   +
        "  VALUES"   +
        "    (SEQ_MAIL_HISTORY_SEND_NO.NEXTVAL, :send_id, :userid, :leccode, :usernm, :usermail, :result, :from_id," +
        "     SYSDATE, :mail_title,'', :mail_type, SYSDATE, :update_id)"  ;

        int ret = oDb.executeUpdate(sql, param);
        
        MdbParameters p = new MdbParameters();
        p.add("send_id", (String)param.get("send_id"));
        ((MdbOCI8) oDb).updateCLOB("wse_mail_history", "mail_text", (String)param.get("mail_text"), p);
        
        return ret;
    }
}