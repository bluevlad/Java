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
public class bank_exam_setBean {

    //==========================================
    //== 자동 생성 
    //==========================================
    

    /**
    * 세트번호
    */  
    private int set_no = 0;

    /**
    * 문제은행번호
    */  
    private int exam_no = 0;

    /**
    * 교과목코드
    */  
    private String sjtcode = null;

    /**
    * 등록자
    */  
    private String reg_id = null;

    /**
    * 등록일자
    */  
    private Date reg_dt = null;


    ////////////////////////////////////////////////////////////////////////////////
    

    /**
    * Get set_no : 세트번호
    * DB TYPE : NUMBER
    */
    public int getSet_no(){
        return this.set_no;
    }
    /**
    * Set set_no : 세트번호
    * DB TYPE : NUMBER
    */
    public void setSet_no(int set_no){
        this.set_no = set_no;
    }

    /**
    * Get exam_no : 문제은행번호
    * DB TYPE : NUMBER
    */
    public int getExam_no(){
        return this.exam_no;
    }
    /**
    * Set exam_no : 문제은행번호
    * DB TYPE : NUMBER
    */
    public void setExam_no(int exam_no){
        this.exam_no = exam_no;
    }

    /**
    * Get sjtcode : 교과목코드
    * DB TYPE : VARCHAR2
    */
    public String getSjtcode(){
        return this.sjtcode;
    }
    /**
    * Set sjtcode : 교과목코드
    * DB TYPE : VARCHAR2
    */
    public void setSjtcode(String sjtcode){
        this.sjtcode = sjtcode;
    }

    /**
    * Get reg_id : 등록자
    * DB TYPE : VARCHAR2
    */
    public String getReg_id(){
        return this.reg_id;
    }
    /**
    * Set reg_id : 등록자
    * DB TYPE : VARCHAR2
    */
    public void setReg_id(String reg_id){
        this.reg_id = reg_id;
    }

    /**
    * Get reg_dt : 등록일자
    * DB TYPE : DATE
    */
    public Date getReg_dt(){
        return this.reg_dt;
    }
    /**
    * Set reg_dt : 등록일자
    * DB TYPE : DATE
    */
    public void setReg_dt(Date reg_dt){
        this.reg_dt = reg_dt;
    }

}
