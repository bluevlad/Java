/**
 *
 */
package modules.wlc.common.mail;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author miraenet
 *
 */
public class WlcEmailMessageBean {
    /** 수신자 메일 주소*/
    private String usermail;

    /** 발송자 아이디 */
    private String userid;
    /** 발송자 이름 */
    private String usernm;
    /** 발송자 아이디 */
    private String update_id;
    /** 발송자 메일 주소*/
    private String from_id;

    /** 강의 코드 */
    private String leccode;
    /** 강의명 */
    private String lecname;

    /** 구분자 */
    private Hashtable mapping = new Hashtable();
    /** 메일발송번호 */
    private long send_id;

    /** 메일성격[A:경고, I:알림]*/
    private String mail_type;
    /** 메일형태[H:html, T:text]*/
    private String mail_format;

    /** 결과 */
    private int result = 9;

    public void setUsermail(String args){
        this.usermail = args;
    }
    public String getUsermail() {
        return usermail ;
    }

    public void setUserid(String args)  {
        this.userid = args;
    }
    public String getUserid()   {
        return userid ;
    }
    public void setUsernm(String args)  {
        this.usernm = args;
    }
    public String getUsernm()  {
        return usernm ;
    }
    public void setUpdate_id(String args)  {
        this.update_id = args;
    }
    public String getUpdate_id() {
        return update_id ;
    }
    public void setFrom_id(String args) {
        this.from_id = args;
    }
    public String getFrom_id()  {
        return from_id;
    }

    public void setLeccode(String leccode)  {
        this.leccode = leccode;
    }
    public String getLeccode()  {
        return leccode ;
    }
    public void setLecname(String args)  {
        this.lecname = args;
    }
    public String getLecname()  {
        return lecname ;
    }

    public void setMapping(String key, String value)    {
        mapping.put(key,value);
    }
    public Enumeration getMapkeys() {
        return mapping.keys() ;
    }
    public String getMapkey(String key) {
        return (String)mapping.get(key);
    }
    public void setSend_id(long args) {
        this.send_id = args;
    }
    public long getSend_id() {
        return send_id ;
    }

    public void setMail_type(String args) {
        this.mail_type = args;
    }
    public String getMail_type() {
        return mail_type;
    }
    public void setMail_format(String args) {
        this.mail_format = args;
    }
    public String getMail_format()  {
        return mail_format;
    }
    public void setResult(int result)   {
        this.result = result;
    }
    public int getResult()  {
        return result ;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<br>FROM_ID="+ from_id);
        sb.append("<br>USERMAIL="+ usermail);
        sb.append("<br>USERID="+ userid);
        sb.append("<br>USERNM="+ usernm);
        sb.append("<br>LECNAME="+ lecname);
        sb.append("<br>LECCODE="+ leccode);
        sb.append("<br>SEND_ID="+ send_id);
        sb.append("<br>MAIL_TYPE="+ mail_type);
        sb.append("<br>MAIL_FORMAT="+ mail_format);
        sb.append("<br>RESULT="+ result);
        return sb.toString();
    }
}