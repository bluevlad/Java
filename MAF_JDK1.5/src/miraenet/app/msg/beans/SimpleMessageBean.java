/*
 * �ۼ��� ��¥: 2004-12-29
 *

 */
package miraenet.app.msg.beans;

import java.util.Date;

/**
 * ���� ������ Bean
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class SimpleMessageBean {
    private String msgid = "";		//������ȣ 
    private String deleted = ""; 	//��������
    private String usn_snd = "";//������ ��� �ڵ�
    private String usn_rcv = ""; //�޴� ��� �ڵ�
    private String nm_snd = ""; //������ ��� �̸�
    private String nm_rcv = "";  //�޴� ��� �̸�
    private String title= "";		   //����
    private String contents = "";//����
    private Date reg_dt; //�����Ͻ�
    private Date rcv_dt; //�����Ͻ�
    
	/**
	 * @return Returns the contents.
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents The contents to set.
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return Returns the deleted.
	 */
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted The deleted to set.
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return Returns the msgid.
	 */
	public String getMsgid() {
		return msgid;
	}
	/**
	 * @param msgid The msgid to set.
	 */
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	/**
	 * @return Returns the nm_rcv.
	 */
	public String getNm_rcv() {
		return nm_rcv;
	}
	/**
	 * @param nm_rcv The nm_rcv to set.
	 */
	public void setNm_rcv(String nm_rcv) {
		this.nm_rcv = nm_rcv;
	}
	/**
	 * @return Returns the nm_snd.
	 */
	public String getNm_snd() {
		return nm_snd;
	}
	/**
	 * @param nm_snd The nm_snd to set.
	 */
	public void setNm_snd(String nm_snd) {
		this.nm_snd = nm_snd;
	}
	/**
	 * @return Returns the rcv_dt.
	 */
	public Date getRcv_dt() {
		return rcv_dt;
	}
	/**
	 * @param rcv_dt The rcv_dt to set.
	 */
	public void setRcv_dt(Date rcv_dt) {
		this.rcv_dt = rcv_dt;
	}
	/**
	 * @return Returns the reg_dt.
	 */
	public Date getReg_dt() {
		return reg_dt;
	}
	/**
	 * @param reg_dt The reg_dt to set.
	 */
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the usn_rcv.
	 */
	public String getUsn_rcv() {
		return usn_rcv;
	}
	/**
	 * @param usn_rcv The usn_rcv to set.
	 */
	public void setUsn_rcv(String usn_rcv) {
		this.usn_rcv = usn_rcv;
	}
	/**
	 * @return Returns the usn_snd.
	 */
	public String getUsn_snd() {
		return usn_snd;
	}
	/**
	 * @param usn_snd The usn_snd to set.
	 */
	public void setUsn_snd(String usn_snd) {
		this.usn_snd = usn_snd;
	}
}
