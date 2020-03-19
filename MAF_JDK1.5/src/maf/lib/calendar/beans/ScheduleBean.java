/*
 * Created on 2005-01-07
 */
package maf.lib.calendar.beans;

import java.util.Date;


/**
 * 
 */
public class ScheduleBean {
    private String stype = null;			// �����׷�(P:����, S:����Ʈ)
    private String sid = null;				// ���� ���ó(USN �Ǵ� ���� ID)
    private long cid = 0;					// Sequence ID
    
    private Date cstartdate = null;		// ������
    private Date cenddate = null;			// ������

    private String cname = "";		// ���� ����
    private String cdesc = "";		// ���� ����
    
	/**
	 * @return Returns the nm.
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * @param nm The nm to set.
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}
	/**
	 * @return Returns the nm_eng.
	 */
	public String getNm_eng() {
		return nm_eng;
	}
	/**
	 * @param nm_eng The nm_eng to set.
	 */
	public void setNm_eng(String nm_eng) {
		this.nm_eng = nm_eng;
	}
    private String reg_usn = null;
    private Date reg_dt = null;
    private String cisshow = "T";	// ���¿� ǥ�ÿ���
    
    private String sday = null;		// ������
    private String eday = null;			// ������
    
    private String nm = null;    		//�������� (�л�, �а�(�а���), ����(���¸�))
    private String nm_eng = null; //���� ��������
    


	/**
	 * @return Returns the eday.
	 */
    public String getEday() {
		return eday;
	}
	/**
	 * @param eday The eday to set.
	 */
	public void setEday(String eday) {
		this.eday = eday;
	}
	/**
	 * @return Returns the sday.
	 */
	public String getSday() {
		return sday;
	}
	/**
	 * @param sday The sday to set.
	 */
	public void setSday(String sday) {
		this.sday = sday;
	}
    public ScheduleBean() {
        
    }
    
    public String getCdesc() {
        return cdesc;
    }
    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
    public Date getCenddate() {
        return cenddate;
    }
    public void setCenddate(Date cenddate) {
        this.cenddate = cenddate;
    }
    public long getCid() {
        return cid;
    }
    public void setCid(long cid) {
        this.cid = cid;
    }
    public String getCisshow() {
        return cisshow;
    }
    public void setCisshow(String cisshow) {
        this.cisshow = cisshow;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public Date getCstartdate() {
        return cstartdate;
    }
    public void setCstartdate(Date cstartdate) {
        this.cstartdate = cstartdate;
    }
    public Date getReg_dt() {
        return reg_dt;
    }
    public void setReg_dt(Date reg_dt) {
        this.reg_dt =  reg_dt;
    }
    public String getReg_usn() {
        return reg_usn;
    }
    public void setReg_usn(String reg_usn) {
        this.reg_usn = reg_usn;
    }
    /**
     * ���� ���ó(USN �Ǵ� ���� ID)
     * @return
     */
    public String getSid() {
        return sid;
    }
    /**
     * ���� ���ó(USN �Ǵ� ���� ID)
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }
    /**
     * �����׷�(P:����, S:����Ʈ,  C:����)
     * @return
     */
    public String getStype() {
        return stype;
    }
    /**
     * �����׷�(P:����, S:����Ʈ,  C:����)
     * @param stype
     */
    public void setStype(String stype) {
        this.stype = stype;
    }
}
