/*
 * Created on 2005. 6. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author xxx
 *
 */
public class bank_sjtBean {

    private int exam_no = 0;

        
    private String sjtcode = null;

        
    private Date reg_dt = null;

        
    private String reg_id = null;

        
    private String chapter = null;


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
    * Get sjtcode : 
    * DB TYPE : VARCHAR2
    */
    public String getSjtcode(){
        return this.sjtcode;
    }
    /**
    * Set sjtcode : 
    * DB TYPE : VARCHAR2
    */
    public void setSjtcode(String sjtcode){
        this.sjtcode = sjtcode;
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
    * Get chapter : 
    * DB TYPE : VARCHAR2
    */
    public String getChapter(){
        return this.chapter;
    }
    /**
    * Set chapter : 
    * DB TYPE : VARCHAR2
    */
    public void setChapter(String chapter){
        this.chapter = chapter;
    }

}
