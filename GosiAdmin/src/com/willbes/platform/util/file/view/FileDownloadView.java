package com.willbes.platform.util.file.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.willbes.cmm.service.FileVO;

public class FileDownloadView extends AbstractView{

    private Logger logger = LoggerFactory.getLogger(getClass());
    public FileDownloadView(){
        setContentType("application/download; utf-8");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,	HttpServletRequest request, HttpServletResponse response)throws Exception {
        FileVO file = (FileVO)model.get("downloadFile");

        response.setContentType(getContentType());
        response.setContentLength((int) file.getFILE_SIZE());

        String browser = getBrowser(request);
        String fileName = file.getFILE_NM();
        String encodedFilename = null;
        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) {		// IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < fileName.length(); i++) {
                char c = fileName.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            throw new IOException("Not supported browser");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            File f = new File(file.getFILE_PATH());
            fis = new FileInputStream(f);
            FileCopyUtils.copy(fis, out);

        } catch (Exception e) {
            logger.error("file error" , e);
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        } //try end

        out.flush();
    }

    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

}//end class
