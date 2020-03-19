package maf.web.multipart;

import java.io.File;
import java.io.IOException;

public class UploadedFileUtil {
	/**
     *  새로운 파일 생성
     * @param f
     * @return
     */
	protected static  boolean createNewFile(File f) {
        try {
          return f.createNewFile();
        }
        catch (IOException ignored) {
          return false;
        }
      }   
    
    
	 /**
     * 중복된 파일명을 피해 이름 생성
     * @param f
     * @return
     */
    protected static File rename(File f) {
        if (createNewFile(f)) {
          return f;
        }
        String name = f.getName();
        String body = null;
        String ext = null;

        int dot = name.lastIndexOf(".");
        if (dot != -1) {
          body = name.substring(0, dot) + "_(";
          ext = ")" + name.substring(dot);  // includes "."
        }
        else {
          body = name + "_(";
          ext = ")";
        }

        // Increase the count until an empty spot is found.
        // Max out at 9999 to avoid an infinite loop caused by a persistent
        // IOException, like when the destination dir becomes non-writable.
        // We don't pass the exception up because our job is just to rename,
        // and the caller will hit any IOException in normal processing.
        int count = 0;
        while (!createNewFile(f) && count < 9999) {
          count++;
          String newName = body + count + ext;
          f = new File(f.getParent(), newName);
        }

        return f;
      }
    
    
    /**
     * temp 경로에 저장된 임시파일 삭제
     *
     */
    protected static void _deleteFile(String tempFilename) {
       try {
           java.io.File f = new java.io.File( tempFilename );
           if (f != null) {
               f.delete();
           }
       } catch (Throwable e) {

       }
   }
}
