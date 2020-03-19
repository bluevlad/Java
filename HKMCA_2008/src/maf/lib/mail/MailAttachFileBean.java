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
 * 파일과 , 원본 파일명을 가진 Bean
 */
public class MailAttachFileBean {

    private File file= null;
    private String fileName = null;
    
    /**
     * 파일과 파일명으로 생성 
     * @param file
     * @param originFileName
     */
    public MailAttachFileBean(File file, String originFileName) {
        this.file = file;
        this.fileName =originFileName;
    }

    
    /**
     * @return file을 리턴합니다.
     */
    public File getFile() {
        return file;
    }
    /**
     * @return fileName을 리턴합니다.
     */
    public String getFileName() {
        return fileName;
    }
}
