package web.util.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import web.util.CommonUtil;

@Component("fileUtil")
public class FileUtil {

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

//            Object attachFileId = multipartFileservice.insertAttachFile(info);
//            info.put("fileId", attachFileId);
        }

        return info;
    }

    public void deleteFile(String fileFullPath) throws IllegalStateException, IOException {
        File f = new File(fileFullPath);
        if(f.exists())
            f.delete();
    }

}
