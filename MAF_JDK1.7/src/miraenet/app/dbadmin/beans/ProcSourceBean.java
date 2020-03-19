/*
 * ProcBean.java, @ 2005-03-24
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbadmin.beans;

/**
 * @author goindole
 *
 */
public class ProcSourceBean {
    private int line = 0;
    private String text = null;
    
    
    /**
     * @return line을 리턴합니다.
     */
    public int getLine() {
        return line;
    }
    /**
     * @param line 설정하려는 line.
     */
    public void setLine(int line) {
        this.line = line;
    }
    /**
     * @return text을 리턴합니다.
     */
    public String getText() {
        return text;
    }
    /**
     * @param text 설정하려는 text.
     */
    public void setText(String text) {
        this.text = text;
    }
}
