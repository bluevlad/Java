/*
 * 작성된 날짜: 2005-01-27
 *
 */
package miraenet.app.msg;

import maf.MafUtil;
import maf.base.BaseHttpSession;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import miraenet.app.msg.beans.MessageBean;
import miraenet.app.msg.beans.MsgAddress;
import miraenet.app.msg.beans.SMSBean;
import miraenet.app.msg.dao.SmsDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author goindole
 *
 */
public class SMSManager {
    private static SMSManager instance = new SMSManager();

	private  Log logger = LogFactory.getLog(SMSManager.class);
    private SMSManager() {}
    public static synchronized SMSManager getInstance() {
        return instance;
    }
    //여러명에게 문자 보내기
    public int sendMessage(MessageBean msg, BaseHttpSession session) {
        MdbDriver oDb = Mdb.getMdbDriver("sms",MdbDriver.DMBS_JDBC);
        SmsDB idb = new SmsDB();
        int s = 0;
        try {
            if(msg != null ) {
                MsgAddress[] to = msg.getTo();
                if (to != null) {
		            for(int i =0; i<to.length; i++ ) {
		                String status = null;
		                try {
			                idb.sendSMS(oDb, 
			                        msg.getFrom().getNm(), msg.getFrom().getAddress(),
			                        to[i].getNm(), to[i].getAddress(), 
			                        msg.getContents(),
			                        session.getUsn());
			                s++;
			                status = MessageLogger.STATUS_OK;
		                } catch (Exception e) {
		                    status = MessageLogger.STATUS_FAIL;
		                }
		                //ype, reg_ip, fromUsn,  fromAddr, destUsn, destAddr, subject, status, guid
		                MessageLogger.log("sms", session.getLoginIP(),
		                        session.getUsn(), msg.getFrom().getAddress(),
		                        to[i].getUsn(), to[i].getAddress(),
		                        msg.getSubject(), status, MafUtil.getGUID()	                        
		                        );
		            } // for
//		            logger.debug(to.length + " SMS message's sent!!!");

                } else {
                    logger.debug("receiver is null !!!");
                } 
            }else {
                logger.debug("msg is null !!!");
            }
        } catch (Exception e ) {
            
        } finally {
            if (oDb != null)try {oDb.close();} catch (Exception ex) {}

        }
        return s;
    }
    //한명에게 문자 보내기
    public void sendMessage(SMSBean msg, String userid) {
        MdbDriver oDb = Mdb.getMdbDriver("sms",MdbDriver.DMBS_JDBC);
        SmsDB idb = new SmsDB();
        try {
                idb.sendSMS(oDb,
                        msg.getSender_nm(), msg.getSender_no(),
                        msg.getReceiver_nm(), msg.getReceiver_no(),
                        msg.getMsg(),
                        userid
                        );
        } catch (Exception e ) {
            
        } finally {
            if (oDb != null)try {oDb.close();} catch (Exception ex) {}

        }
    }


}
