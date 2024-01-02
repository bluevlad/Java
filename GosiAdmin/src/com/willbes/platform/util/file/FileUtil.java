package com.willbes.platform.util.file;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.ImageIcon;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import static org.imgscalr.Scalr.*;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.service.MultipartFileService;

@Component("fileUtil")
public class FileUtil {

    @Autowired
    private MultipartFileService multipartFileservice;

    @Inject
    private FileSystemResource fsResource;	//DI

    public List<HashMap<String, Object>> uploadFiles(List<MultipartFile> uploadFileList, String rootPath, String subPath) throws IllegalStateException, IOException {
        List<HashMap<String, Object>> fileInfoList = new ArrayList<HashMap<String, Object>>();

        for(MultipartFile file:uploadFileList) {
            HashMap<String, Object> info = uploadFile(file, rootPath, subPath);
            fileInfoList.add(info);
        }

        return fileInfoList;
    }

    public HashMap<String, Object> uploadFile(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            String originalFileExt =originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = CommonUtil.getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + newFileName;
            String fileSubPath = subPath + newFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName", 		  originalFileName);
            info.put("fileFullPath", 	  fileSubPath);
            info.put("fileSize", 		  String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);
        }

        return info;
    }

    public HashMap<String, Object> uploadFile(MultipartFile uploadFile, String rootPath, String subPath, int width, int height, String opt)
            throws IllegalStateException, IOException {

        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            String originalFileExt =originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            String newFileName = CommonUtil.getCurrentDateTime() + "." + originalFileExt;

            String fileFullPath = path + newFileName;
            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            if(width == 0) width = 32;
            if(height == 0) height = 32;

            BufferedImage orgImage = ImageIO.read(newFile);
            BufferedImage thumbImg = Scalr.resize(orgImage, Method.QUALITY, Mode.AUTOMATIC,  width, height, Scalr.OP_ANTIALIAS);

            String thumbFileName = CommonUtil.getCurrentDateTime()+"_thumb"+width+"x"+height+ "." + originalFileExt;
            String thumbFullPath = path + thumbFileName;
            String thumbSubPath = subPath + thumbFileName;
            File thumbFile = new File(thumbFullPath);
            ImageIO.write(thumbImg, originalFileExt, thumbFile);

            info.put("fileName",       originalFileName);
            info.put("fileFullPath",    thumbSubPath);
            info.put("fileSize",         String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           "."+originalFileExt);
            info.put("fileNewName", thumbFileName);
        }

        return info;
    }

    public HashMap<String, Object> uploadFileForRealName(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            String originalFileExt =originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = CommonUtil.getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + originalFileName;
            String fileSubPath = subPath + originalFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName", 		  originalFileName);
            info.put("fileFullPath", 	  fileSubPath);
            info.put("fileSize", 		  String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);
        }

        return info;
    }

    public List<HashMap<String, Object>> uploadFilesWthField(List<MultipartFile> uploadFileList, String rootPath, String subPath) throws IllegalStateException, IOException {
        List<HashMap<String, Object>> fileInfoList = new ArrayList<HashMap<String, Object>>();

        for(MultipartFile file:uploadFileList) {
            HashMap<String, Object> info = uploadFileWthField(file, rootPath, subPath);
            fileInfoList.add(info);
        }

        return fileInfoList;
    }

    public HashMap<String, Object> uploadFileWthField(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            String originalFileExt =originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = CommonUtil.getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + newFileName;
            String fileSubPath = subPath + newFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName",       originalFileName);
            info.put("fileFullPath",    fileSubPath);
            info.put("fileSize",         String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);

            Object attachFileId = multipartFileservice.insertAttachFile(info);
            info.put("fileId",             attachFileId);
        }

        return info;
    }

    public void deleteFile(String fileFullPath) throws IllegalStateException, IOException {
        File f = new File(fileFullPath);
        if(f.exists())
            f.delete();
    }

    /** +
     * MethodName   : filemake(String filename, String msg)
     * Description  : 파일 업로드
     * @param       :
     * @return      :
     * @exception   :
     */
    public void filemake(String filename, String desc, String subPath)
    {
        PrintWriter logfile = null;
        boolean isappend = true; //new Boolean(DirectoryBundle.getProperty("DEBUG_APPEND","false")).booleanValue();
        try {
            String rootPath = fsResource.getPath() + subPath;
            File dir = new File(rootPath);
            if(!dir.exists()) dir.mkdirs();
            logfile = new PrintWriter(new FileWriter(rootPath + filename, isappend), true);
            logfile.println(desc);
            logfile.flush();
        } catch (Exception e)  {
            if(logfile != null) logfile.close();
            e.printStackTrace();
        } finally {
            if(logfile != null) logfile.close();
        }
    }

}
