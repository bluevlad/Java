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
public class bank_setBean {

    //==========================================
    //== �ڵ� ���� 
    //==========================================
    

    /**
    * ��Ʈ��ȣ
    */  
    private int set_no = 0;

    /**
    * �������ڵ�
    */  
    private String sjtcode = null;

    /**
    * ��Ʈ����
    */  
    private String comments = null;

    /**
    * �������
    */  
    private Date reg_dt = null;

    /**
    * �����
    */  
    private String reg_id = null;


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
    * Get comments : ��Ʈ����
    * DB TYPE : VARCHAR2
    */
    public String getComments(){
        return this.comments;
    }
    /**
    * Set comments : ��Ʈ����
    * DB TYPE : VARCHAR2
    */
    public void setComments(String comments){
        this.comments = comments;
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

}
