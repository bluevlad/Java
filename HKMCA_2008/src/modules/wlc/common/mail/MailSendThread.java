package modules.wlc.common.mail;

/**
 *<PRE>
 * Filename       : MailSendThread.java
 * Class          : MailSendThread.class
 * Comment        : 메일 발송용 유틸
 * History        :
 * </PRE>
 * @version       : 1.0
 */
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import miraenet.AppConfig;



public class MailSendThread extends Thread {
    public WlcEmailMessageBean[] to;
    String textarea="";
    String textfield="";
    String lecname="";
    String name="";
    String userid="";
    String leccode="";
    String from="";
    String cc="";
    String bcc="";
    String mailtype="";
    boolean endFlag = false;
    // 메일발송정보 저장
    WsemailsendDB dao = null;
    MdbDriver oDb = null;

    public MailSendThread(WlcEmailMessageBean[] to, String subject, String message, String from){
        this.to = to;
        this.textarea = subject;
        this.textfield = message;
        this.from = from;

        // 메일발송정보 저장
        oDb = Mdb.getMdbDriver();

    }
    public void setCc(String cc){
        this.cc = cc;
    }
    public void setBcc(String bcc){
        this.bcc = bcc;
    }
    public boolean getEndFlag(){
        return endFlag;
    }
    public WlcEmailMessageBean[] getTo(){
        return to;
    }
    public void setLecname(String lecname){
        this.lecname = lecname;
    }
    public void setLeccode(String leccode){
        this.leccode = leccode;
    }

    public void run(){
        try {
            WlcSendMail mail = new WlcSendMail(from);
            for(int i = 0 ; i < to.length ; i++) {
                name = to[i].getUsernm();
                userid = to[i].getUserid();
                String context = textarea;
                String title = textfield;

                Enumeration en = to[i].getMapkeys();
                while(en.hasMoreElements()){
                    String key = (String)en.nextElement();
                    //String value = Util.toEN(to[i].getMapkey(key));
                    String value = to[i].getMapkey(key);

                    while(context.indexOf(key)>-1){
                        if(context.indexOf(key)>-1){
                            context = context.substring(0,context.indexOf(key))+value+ context.substring(context.indexOf(key)+key.length());
                        }
                    }
                    while(title.indexOf(key)>-1){
                        if(title.indexOf(key)>-1){
                            title = title.substring(0,title.indexOf(key)) + value+ title.substring(title.indexOf(key)+key.length());
                        }
                    }
                }

                while(context.indexOf(AppConfig.MAIL_MEMBER_NAME)>-1 || context.indexOf(AppConfig.MAIL_MEMBER_ID)>-1 || context.indexOf(AppConfig.MAIL_LECCODE)>-1 || context.indexOf(AppConfig.MAIL_LECNAME)>-1){
                    if(context.indexOf(AppConfig.MAIL_MEMBER_NAME)>-1){
                        context = context.substring(0,context.indexOf(AppConfig.MAIL_MEMBER_NAME))+name+ context.substring(context.indexOf(AppConfig.MAIL_MEMBER_NAME)+AppConfig.MAIL_MEMBER_NAME.length());
                    }
                    if(context.indexOf(AppConfig.MAIL_MEMBER_ID)>-1){
                        context = context.substring(0,context.indexOf(AppConfig.MAIL_MEMBER_ID))+userid+ context.substring(context.indexOf(AppConfig.MAIL_MEMBER_ID)+AppConfig.MAIL_MEMBER_ID.length());
                    }
                    if(context.indexOf(AppConfig.MAIL_LECCODE)>-1){
                        context = context.substring(0,context.indexOf(AppConfig.MAIL_LECCODE))+leccode+ context.substring(context.indexOf(AppConfig.MAIL_LECCODE)+AppConfig.MAIL_LECCODE.length());
                    }
                    if(context.indexOf(AppConfig.MAIL_LECNAME)>-1){
                        context = context.substring(0,context.indexOf(AppConfig.MAIL_LECNAME))+lecname+ context.substring(context.indexOf(AppConfig.MAIL_LECNAME)+AppConfig.MAIL_LECNAME.length());
                    }
                }

                while(title.indexOf(AppConfig.MAIL_MEMBER_NAME)>-1 || title.indexOf(AppConfig.MAIL_MEMBER_ID)>-1 || title.indexOf(AppConfig.MAIL_LECCODE)>-1 || title.indexOf(AppConfig.MAIL_LECNAME)>-1) {
                    if(title.indexOf(AppConfig.MAIL_MEMBER_NAME)>-1){
                        title = title.substring(0,title.indexOf(AppConfig.MAIL_MEMBER_NAME))+name+ title.substring(title.indexOf(AppConfig.MAIL_MEMBER_NAME)+AppConfig.MAIL_MEMBER_NAME.length());
                    }
                    if(title.indexOf(AppConfig.MAIL_MEMBER_ID)>-1){
                        title = title.substring(0,title.indexOf(AppConfig.MAIL_MEMBER_ID))+userid+ title.substring(title.indexOf(AppConfig.MAIL_MEMBER_ID)+AppConfig.MAIL_MEMBER_ID.length());
                    }
                    if(title.indexOf(AppConfig.MAIL_LECCODE)>-1){
                        title = title.substring(0,title.indexOf(AppConfig.MAIL_LECCODE))+leccode+ title.substring(title.indexOf(AppConfig.MAIL_LECCODE)+AppConfig.MAIL_LECCODE.length());
                    }
                    if(title.indexOf(AppConfig.MAIL_LECNAME)>-1){
                        title = title.substring(0,title.indexOf(AppConfig.MAIL_LECNAME))+lecname+ title.substring(title.indexOf(AppConfig.MAIL_LECNAME)+AppConfig.MAIL_LECNAME.length());
                    }
                }

                if(to[i].getUsermail().indexOf("@") != -1) {

                    Map mbean = new HashMap();

                    mbean.put("send_id", Long.toString(to[i].getSend_id()) ); // 메일내용 저장번호
                    mbean.put("userid", to[i].getUserid());  // 받는사람ID
                    mbean.put("leccode", to[i].getLeccode());  // 강의코드
                    mbean.put("usernm", name);   // 받는사람이름
                    mbean.put("usermail", to[i].getUsermail()); // 받는사람메일
                    mbean.put("from_id", from);  // 보내는 사람
                    mbean.put("mail_title", title);  // 타이틀
                    mbean.put("mail_text", context);  //
                    mbean.put("mail_type", to[i].getMail_type());  //
                    mbean.put("update_id", to[i].getUpdate_id());  //

                    int rs = mail.SendMail(to[i].getUsermail(), cc, bcc, title, context);
                    mbean.put("result", Integer.toString(rs));  //전송 결과

                    // 발송 메일 저장
                    if(to[i].getUsermail() != null && !"".equals(to[i].getUsermail()) && to[i].getSend_id() > 0) {
                    	WsemailsendDB.insertHistory(oDb, mbean);
                    }

                    to[i].setResult(rs);
                }
            }

        }catch(Exception e){
            e.printStackTrace(System.out);
        } finally {
            endFlag = true;
        }
    }
}
