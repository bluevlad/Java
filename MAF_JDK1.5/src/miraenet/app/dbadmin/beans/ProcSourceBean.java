/*
 * ProcBean.java, @ 2005-03-24
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
     * @return line�� �����մϴ�.
     */
    public int getLine() {
        return line;
    }
    /**
     * @param line �����Ϸ��� line.
     */
    public void setLine(int line) {
        this.line = line;
    }
    /**
     * @return text�� �����մϴ�.
     */
    public String getText() {
        return text;
    }
    /**
     * @param text �����Ϸ��� text.
     */
    public void setText(String text) {
        this.text = text;
    }
}
