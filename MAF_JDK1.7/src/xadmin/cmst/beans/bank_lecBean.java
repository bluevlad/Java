/*
 * Created on 2005. 6. 11.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author xxx
 *
 */
public class bank_lecBean {

    private int exam_no = 0;

        
    private String leccode = null;

        
    private Date reg_dt = null;

        
    private String reg_id = null;

        
    private String note = null;


    ////////////////////////////////////////////////////////////////////////////////
    

    /**
    * Get exam_no : 
    * DB TYPE : NUMBER
    */
    public int getExam_no(){
        return this.exam_no;
    }
    /**
    * Set exam_no : 
    * DB TYPE : NUMBER
    */
    public void setExam_no(int exam_no){
        this.exam_no = exam_no;
    }

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
    * Get reg_dt : 
    * DB TYPE : DATE
    */
    public Date getReg_dt(){
        return this.reg_dt;
    }
    /**
    * Set reg_dt : 
    * DB TYPE : DATE
    */
    public void setReg_dt(Date reg_dt){
        this.reg_dt = reg_dt;
    }

    /**
    * Get reg_id : 
    * DB TYPE : VARCHAR2
    */
    public String getReg_id(){
        return this.reg_id;
    }
    /**
    * Set reg_id : 
    * DB TYPE : VARCHAR2
    */
    public void setReg_id(String reg_id){
        this.reg_id = reg_id;
    }

    /**
    * Get note : 
    * DB TYPE : VARCHAR2
    */
    public String getNote(){
        return this.note;
    }
    /**
    * Set note : 
    * DB TYPE : VARCHAR2
    */
    public void setNote(String note){
        this.note = note;
    }

}
