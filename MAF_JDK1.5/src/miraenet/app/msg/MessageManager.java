/*
 * �ۼ��� ��¥: 2005-2-18
 *  
 */
package miraenet.app.msg;

import maf.base.BaseHttpSession;
import maf.lib.mail.exception.SendMailException;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.app.msg.beans.MessageBean;
import miraenet.app.pager.PagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ����/����/���� ������ ���� ó���ϴ� Manager.
 * 
 * �ۼ��� : �ڱ��� �ۼ��� ��¥ : 2005-02-18
 */
public class MessageManager {
	private  Log logger = LogFactory.getLog(MessageManager.class);
    private BaseHttpSession session = null;
    /**
     * ���� �޽���
     */
    public final static String MESSAGE_TYPE_SMS = "SMS";
    /**
     * e-mail
     */
    public final static String MESSAGE_TYPE_MAIL = "MAIL";
    /**
     * ���� 
     */
    public final static String MESSAGE_TYPE_PAGER = "PAGER";
    
    public MessageManager(BaseHttpSession session) {
        this.session = session;
    }

    /**
     * �� type�� ���� ó�� method
     * 
     * @param msgs
     * @param type :
     *  MessageManager.MESSAGE_TYPE_XXX
     */
    public int sendMessage(MdbDriver oDb, MessageBean msgs, String type)
        throws MdbException, SendMailException {
    	int cnt  = 0;
        if (MESSAGE_TYPE_SMS.equals(type)) {
            SMSManager mng = SMSManager.getInstance();
            cnt = mng.sendMessage(msgs, session);
            mng = null;
        } else if (MESSAGE_TYPE_MAIL.equals(type)) {
            MailManager mng = MailManager.getInstance();
            cnt = mng.sendmail(msgs, session);
            mng = null;
        } else if (MESSAGE_TYPE_PAGER.equals(type)) {
            
        	cnt = PagerDB.sendMessage(oDb, msgs, session);
        }
        return cnt;
    }
}