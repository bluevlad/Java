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
public class bank_examBean {

    //==========================================
    //== 자동 생성 
    //==========================================
    

    /**
    * 문제은행번호
    */  
    private int exam_no = 0;

    /**
    * 문제제목
    */  
    private String title = null;

    /**
    * 문제설명
    */  
    private String econtents = null;

    /**
    * 정답
    */  
    private int answer = 0;

    /**
    * 문제유형 1:객관식, 2:주관식
    */  
    private String etype = null;

    /**
    * 사용여부 0:등록, 1:사용, 9:삭제
    */  
    private String status = null;

    /**
    * 등록일자
    */  
    private Date reg_dt = null;

    /**
    * 등록자
    */  
    private String reg_id = null;

    /**
    * 수정일자
    */  
    private Date up_dt = null;

    /**
    * 수정자
    */  
    private String up_id = null;

    /**
    * 비고
    */  
    private String note = null;

    /**
    * 첨부이미지
    */  
    private String img = null;

    /**
    * 문제집번호
    */  
    private int sort_no = 0;

    /**
    * 점수(난이도)
    */  
    private int score = 0;


    ////////////////////////////////////////////////////////////////////////////////
    

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
    * Get title : 문제제목
    * DB TYPE : VARCHAR2
    */
    public String getTitle(){
        return this.title;
    }
    /**
    * Set title : 문제제목
    * DB TYPE : VARCHAR2
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * Get econtents : 문제설명
    * DB TYPE : VARCHAR2
    */
    public String getEcontents(){
        return this.econtents;
    }
    /**
    * Set econtents : 문제설명
    * DB TYPE : VARCHAR2
    */
    public void setEcontents(String econtents){
        this.econtents = econtents;
    }

    /**
    * Get answer : 정답
    * DB TYPE : NUMBER
    */
    public int getAnswer(){
        return this.answer;
    }
    /**
    * Set answer : 정답
    * DB TYPE : NUMBER
    */
    public void setAnswer(int answer){
        this.answer = answer;
    }

    /**
    * Get etype : 문제유형 1:객관식, 2:주관식
    * DB TYPE : CHAR
    */
    public String getEtype(){
        return this.etype;
    }
    /**
    * Set etype : 문제유형 1:객관식, 2:주관식
    * DB TYPE : CHAR
    */
    public void setEtype(String etype){
        this.etype = etype;
    }

    /**
    * Get status : 사용여부 0:등록, 1:사용, 9:삭제
    * DB TYPE : CHAR
    */
    public String getStatus(){
        return this.status;
    }
    /**
    * Set status : 사용여부 0:등록, 1:사용, 9:삭제
    * DB TYPE : CHAR
    */
    public void setStatus(String status){
        this.status = status;
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
    * Get up_dt : 수정일자
    * DB TYPE : DATE
    */
    public Date getUp_dt(){
        return this.up_dt;
    }
    /**
    * Set up_dt : 수정일자
    * DB TYPE : DATE
    */
    public void setUp_dt(Date up_dt){
        this.up_dt = up_dt;
    }

    /**
    * Get up_id : 수정자
    * DB TYPE : VARCHAR2
    */
    public String getUp_id(){
        return this.up_id;
    }
    /**
    * Set up_id : 수정자
    * DB TYPE : VARCHAR2
    */
    public void setUp_id(String up_id){
        this.up_id = up_id;
    }

    /**
    * Get note : 비고
    * DB TYPE : VARCHAR2
    */
    public String getNote(){
        return this.note;
    }
    /**
    * Set note : 비고
    * DB TYPE : VARCHAR2
    */
    public void setNote(String note){
        this.note = note;
    }

    /**
    * Get img : 첨부이미지
    * DB TYPE : VARCHAR2
    */
    public String getImg(){
        return this.img;
    }
    /**
    * Set img : 첨부이미지
    * DB TYPE : VARCHAR2
    */
    public void setImg(String img){
        this.img = img;
    }

    /**
    * Get sort_no : 문제집번호
    * DB TYPE : NUMBER
    */
    public int getSort_no(){
        return this.sort_no;
    }
    /**
    * Set sort_no : 문제집번호
    * DB TYPE : NUMBER
    */
    public void setSort_no(int sort_no){
        this.sort_no = sort_no;
    }

    /**
    * Get score : 점수(난이도)
    * DB TYPE : NUMBER
    */
    public int getScore(){
        return this.score;
    }
    /**
    * Set score : 점수(난이도)
    * DB TYPE : NUMBER
    */
    public void setScore(int score){
        this.score = score;
    }

}
