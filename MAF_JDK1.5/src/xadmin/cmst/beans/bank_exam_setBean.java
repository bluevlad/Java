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
public class bank_exam_setBean {

    //==========================================
    //== �ڵ� ���� 
    //==========================================
    

    /**
    * ��Ʈ��ȣ
    */  
    private int set_no = 0;

    /**
    * ���������ȣ
    */  
    private int exam_no = 0;

    /**
    * �������ڵ�
    */  
    private String sjtcode = null;

    /**
    * �����
    */  
    private String reg_id = null;

    /**
    * �������
    */  
    private Date reg_dt = null;


    ////////////////////////////////////////////////////////////////////////////////
    

    /**
    * Get set_no : ��Ʈ��ȣ
    * DB TYPE : NUMBER
    */
    public int getSet_no(){
        return this.set_no;
    }
    /**
    * Set set_no : ��Ʈ��ȣ
    * DB TYPE : NUMBER
    */
    public void setSet_no(int set_no){
        this.set_no = set_no;
    }

    /**
    * Get exam_no : ���������ȣ
    * DB TYPE : NUMBER
    */
    public int getExam_no(){
        return this.exam_no;
    }
    /**
    * Set exam_no : ���������ȣ
    * DB TYPE : NUMBER
    */
    public void setExam_no(int exam_no){
        this.exam_no = exam_no;
    }

    /**
    * Get sjtcode : �������ڵ�
    * DB TYPE : VARCHAR2
    */
    public String getSjtcode(){
        return this.sjtcode;
    }
    /**
    * Set sjtcode : �������ڵ�
    * DB TYPE : VARCHAR2
    */
    public void setSjtcode(String sjtcode){
        this.sjtcode = sjtcode;
    }

    /**
    * Get reg_id : �����
    * DB TYPE : VARCHAR2
    */
    public String getReg_id(){
        return this.reg_id;
    }
    /**
    * Set reg_id : �����
    * DB TYPE : VARCHAR2
    */
    public void setReg_id(String reg_id){
        this.reg_id = reg_id;
    }

    /**
    * Get reg_dt : �������
    * DB TYPE : DATE
    */
    public Date getReg_dt(){
        return this.reg_dt;
    }
    /**
    * Set reg_dt : �������
    * DB TYPE : DATE
    */
    public void setReg_dt(Date reg_dt){
        this.reg_dt = reg_dt;
    }

}
