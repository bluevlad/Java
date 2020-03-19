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
public class bank_examBean {

    //==========================================
    //== �ڵ� ���� 
    //==========================================
    

    /**
    * ���������ȣ
    */  
    private int exam_no = 0;

    /**
    * ��������
    */  
    private String title = null;

    /**
    * ��������
    */  
    private String econtents = null;

    /**
    * ����
    */  
    private int answer = 0;

    /**
    * �������� 1:������, 2:�ְ���
    */  
    private String etype = null;

    /**
    * ��뿩�� 0:���, 1:���, 9:����
    */  
    private String status = null;

    /**
    * �������
    */  
    private Date reg_dt = null;

    /**
    * �����
    */  
    private String reg_id = null;

    /**
    * ��������
    */  
    private Date up_dt = null;

    /**
    * ������
    */  
    private String up_id = null;

    /**
    * ���
    */  
    private String note = null;

    /**
    * ÷���̹���
    */  
    private String img = null;

    /**
    * ��������ȣ
    */  
    private int sort_no = 0;

    /**
    * ����(���̵�)
    */  
    private int score = 0;


    ////////////////////////////////////////////////////////////////////////////////
    

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
    * Get title : ��������
    * DB TYPE : VARCHAR2
    */
    public String getTitle(){
        return this.title;
    }
    /**
    * Set title : ��������
    * DB TYPE : VARCHAR2
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * Get econtents : ��������
    * DB TYPE : VARCHAR2
    */
    public String getEcontents(){
        return this.econtents;
    }
    /**
    * Set econtents : ��������
    * DB TYPE : VARCHAR2
    */
    public void setEcontents(String econtents){
        this.econtents = econtents;
    }

    /**
    * Get answer : ����
    * DB TYPE : NUMBER
    */
    public int getAnswer(){
        return this.answer;
    }
    /**
    * Set answer : ����
    * DB TYPE : NUMBER
    */
    public void setAnswer(int answer){
        this.answer = answer;
    }

    /**
    * Get etype : �������� 1:������, 2:�ְ���
    * DB TYPE : CHAR
    */
    public String getEtype(){
        return this.etype;
    }
    /**
    * Set etype : �������� 1:������, 2:�ְ���
    * DB TYPE : CHAR
    */
    public void setEtype(String etype){
        this.etype = etype;
    }

    /**
    * Get status : ��뿩�� 0:���, 1:���, 9:����
    * DB TYPE : CHAR
    */
    public String getStatus(){
        return this.status;
    }
    /**
    * Set status : ��뿩�� 0:���, 1:���, 9:����
    * DB TYPE : CHAR
    */
    public void setStatus(String status){
        this.status = status;
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

    /**
    * Get up_dt : ��������
    * DB TYPE : DATE
    */
    public Date getUp_dt(){
        return this.up_dt;
    }
    /**
    * Set up_dt : ��������
    * DB TYPE : DATE
    */
    public void setUp_dt(Date up_dt){
        this.up_dt = up_dt;
    }

    /**
    * Get up_id : ������
    * DB TYPE : VARCHAR2
    */
    public String getUp_id(){
        return this.up_id;
    }
    /**
    * Set up_id : ������
    * DB TYPE : VARCHAR2
    */
    public void setUp_id(String up_id){
        this.up_id = up_id;
    }

    /**
    * Get note : ���
    * DB TYPE : VARCHAR2
    */
    public String getNote(){
        return this.note;
    }
    /**
    * Set note : ���
    * DB TYPE : VARCHAR2
    */
    public void setNote(String note){
        this.note = note;
    }

    /**
    * Get img : ÷���̹���
    * DB TYPE : VARCHAR2
    */
    public String getImg(){
        return this.img;
    }
    /**
    * Set img : ÷���̹���
    * DB TYPE : VARCHAR2
    */
    public void setImg(String img){
        this.img = img;
    }

    /**
    * Get sort_no : ��������ȣ
    * DB TYPE : NUMBER
    */
    public int getSort_no(){
        return this.sort_no;
    }
    /**
    * Set sort_no : ��������ȣ
    * DB TYPE : NUMBER
    */
    public void setSort_no(int sort_no){
        this.sort_no = sort_no;
    }

    /**
    * Get score : ����(���̵�)
    * DB TYPE : NUMBER
    */
    public int getScore(){
        return this.score;
    }
    /**
    * Set score : ����(���̵�)
    * DB TYPE : NUMBER
    */
    public void setScore(int score){
        this.score = score;
    }

}
