/*
 * Created on 2005. 5. 17.
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

public class lec_prfBean {

    //==========================================
    //== �ڵ� ���� 
    //==========================================
    

        
    private String leccode = null;

    /**
    * ������ USN
    */  
    private String usn = null;

    /**
    * P : ����(Professor), A:Ʃ��
    */  
    private String flag = "P";

        
    private Date update_dt = null;

        
    private String update_id = null;


    ////////////////////////////////////////////////////////////////////////////////
    

    /**
    * Get leccode : 
    * DB TYPE : VARCHAR2
    */
    public String getLeccode(){
        return this.leccode;
    }
    /**
    * Set leccode : 
    * DB TYPE : VARCHAR2
    */
    public void setLeccode(String leccode){
        this.leccode = leccode;
    }

    /**
    * Get usn : ������ USN
    * DB TYPE : VARCHAR2
    */
    public String getUsn(){
        return this.usn;
    }
    /**
    * Set usn : ������ USN
    * DB TYPE : VARCHAR2
    */
    public void setUsn(String usn){
        this.usn = usn;
    }

    /**
    * Get flag : P : ����(Professor), A:Ʃ��
    * DB TYPE : VARCHAR2
    */
    public String getFlag(){
        return this.flag;
    }
    /**
    * Set flag : P : ����(Professor), A:Ʃ��
    * DB TYPE : VARCHAR2
    */
    public void setFlag(String flag){
        this.flag = flag;
    }

    /**
    * Get update_dt : 
    * DB TYPE : DATE
    */
    public Date getUpdate_dt(){
        return this.update_dt;
    }
    /**
    * Set update_dt : 
    * DB TYPE : DATE
    */
    public void setUpdate_dt(Date update_dt){
        this.update_dt = update_dt;
    }

    /**
    * Get update_id : 
    * DB TYPE : VARCHAR2
    */
    public String getUpdate_id(){
        return this.update_id;
    }
    /**
    * Set update_id : 
    * DB TYPE : VARCHAR2
    */
    public void setUpdate_id(String update_id){
        this.update_id = update_id;
    }

}
