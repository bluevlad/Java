/*
 * MailAttachFileBean.java, @ 2005-03-09
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.mail;

import java.io.File;

/**
 * @author bluevlad
 *
 * ���ϰ� , ���� ���ϸ��� ���� Bean
 */
public class MailAttachFileBean {

    private File file= null;
    private String fileName = null;
    
    /**
     * ���ϰ� ���ϸ����� ���� 
     * @param file
     * @param originFileName
     */
    public MailAttachFileBean(File file, String originFileName) {
        this.file = file;
        this.fileName =originFileName;
    }

    
    /**
     * @return file�� �����մϴ�.
     */
    public File getFile() {
        return file;
    }
    /**
     * @return fileName�� �����մϴ�.
     */
    public String getFileName() {
        return fileName;
    }
}
