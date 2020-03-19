/*
 * ClubBoardBean.java, @ 2005-03-18
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.beans;

/**
 * @author goindole
 * 
 */
public class ClubBoardBean {
    private String c_id, bid, is_public, c_title, c_type, is_use;
    private int position;


    public String getFl_board_type() {
//        return fl_board_type;
        return "normal";
    }

    public void setFl_board_type(String fl_board_type) {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getIs_public() {
        return is_public;
    }

    public void setIs_public(String is_public) {
        this.is_public = is_public;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }
}