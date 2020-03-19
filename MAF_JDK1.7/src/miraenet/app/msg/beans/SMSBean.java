/*
 * SMS 관련 기능 모음 
 * 작성된 날짜: 2005-01-27
 *
 */
package miraenet.app.msg.beans;

public class SMSBean {
    String sender_id = null;
    String sender_no = null;
    String sender_nm = null;
    String receiver_no = null;
    String receiver_nm = null;
    String msg = null;
    
    public SMSBean() {
        
    }
    public SMSBean(String sender_id, String sender_no, String sender_nm, 
            				String receiver_no, String receiver_nm, 
            				String msg){
        this.sender_id = sender_id;
        this.sender_no = sender_no;
        this.sender_nm = sender_nm;
        this.receiver_no = receiver_no;
        this.receiver_nm = receiver_nm;
        this.msg = msg;
        
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getReceiver_nm() {
        return receiver_nm;
    }
    public void setReceiver_nm(String receiver_nm) {
        this.receiver_nm = receiver_nm;
    }
    public String getReceiver_no() {
        return receiver_no;
    }
    public void setReceiver_no(String receiver_no) {
        this.receiver_no = receiver_no;
    }
    public String getSender_id() {
        return sender_id;
    }
    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
    public String getSender_nm() {
        return sender_nm;
    }
    public void setSender_nm(String sender_nm) {
        this.sender_nm = sender_nm;
    }
    public String getSender_no() {
        return sender_no;
    }
    public void setSender_no(String sender_no) {
        this.sender_no = sender_no;
    }
}
