package egovframework.com.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;

/**
 * DCTM 파일 다운로드 샘플
 * 
 * @author 심재진
 *
 */
public class ContentECMTest {
    private String DOCBASE;
    private String USER;
    private String PASSWORD = "DM_PLUGIN=dctmauth/";
    private String DOWN_DIR;
    private String DOWN_PATH = "";
    private static PrintWriter out;
    
    public ContentECMTest(){}
    
    public void init_property()
    {
    	DOCBASE = "SmartRepository";
		USER = "xiper_service_user";
		DOWN_DIR = "C:/ECM_Test/download/";
    }
    
    public static void main(String args[]) 
    {
    	try{
    		ContentECMTest myTest = new ContentECMTest();
    		myTest.init_property();
    		myTest.process();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		System.exit(500);
    	}finally{
    	}
    }
    
    private void process()
    {
		try {
			log("...서버에 연결 중입니다...");
			
			String folderId = "0b02ea19825c1aaa";
			
			String docid = "0902ea19825c631a";

			String version = null;

			log("...폴더 정보를 확인 중 입니다...");
    		
		} catch (Exception e) {
			log("!!! ERROR !!! 다운로드 수행중 오류가 발생하였습니다 :" + e.getMessage());
			e.printStackTrace();
		}finally{
			log("...프로그램을 종료합니다...");
		}
    }
    
    /**
     * 파일다운로드
     */
    private void getFile(String folderPath, String version) throws Exception
    {  
    	//원본 파일명
    	String objectName = filterObjectName("");
    	//앞에 object_id 붙이기.
    	//objectName = objectId + "_" + objectName;

    	//String version = sysObj.getImplicitVersionLabel();
    	
    	//확장자 포함되어 있는가?
    	boolean flag = false;
    	if(objectName.indexOf(".")!= -1)
    	{
    		String ext = objectName.substring(objectName.lastIndexOf(".")+1);
    		if(ext.length() == 2 || ext.length() == 3 || ext.length() == 4)
    		{
    			flag = true;
    		}
    	}
    	if(flag == false)
    	{
	    	//확장자
	    	String ext = "";
	    	objectName = objectName + ext;
    	}
    	
    	//경로
    	//String folder = ((IDfFolder)sess.getObject(sysObj.getFolderId(0))).getFolderPath(0);
    	//folder = filterFolderName(folder);
    	folderPath = replace(folderPath, DOWN_PATH, "");
    	folderPath = filterFolderName(folderPath);
    	folderPath = replace(folderPath, "/", File.separator);
    	
    	String fileName = DOWN_DIR + folderPath + File.separator + (objectName);

    	File f = new File(fileName);

        File dir = f.getParentFile();
    	if(dir.exists() == false){
    		dir.mkdirs();
    	}
    	
    	try{
    		// 0바이트 파일 생성
    			f.createNewFile();
    			log("   [0] "+ f.getName());
    		// 파일 다운로드 및 DRM 적용
		    Files.setAttribute(f.toPath(), "lastModifiedTime", "");
		    Files.setAttribute(f.toPath(), "lastAccessTime", "");
		    Files.setAttribute(f.toPath(), "creationTime", "");
    	}catch(Exception e){
    		log("   !!! ERROR !!! 다운로드 에러 발생 : "+ e.getMessage());
    		log("");
    		log("   ...오류 원인을 파악 후 해결하세요...");
    		log("");
    	}
	}
    
    /**
     * 윈도우에서 파일명으로 사용할수 없는 특수문자 제거
     */
    public String filterObjectName(String objectName)
    {
    	objectName = objectName.replaceAll("[?]", ""); 
    	objectName = objectName.replaceAll("[<]", "");
    	objectName = objectName.replaceAll("[>]", "");
    	objectName = objectName.replaceAll("[:]", "");
    	objectName = objectName.replaceAll("[\"]", "");
    	objectName = objectName.replaceAll("[/]", "");
    	objectName = objectName.replaceAll("[*]", "");
    	objectName = objectName.replaceAll("[\\\\]", "");
    	objectName = objectName.replaceAll("[|]", "");
    	return objectName;
    }
    
    public String filterFolderName(String folderName)
    {
    	String [] s = folderName.split("[/]");
    	StringBuffer sb = new StringBuffer("/");
    	for(int i=0;i<s.length;i++)
    	{
    		String nm = s[i];
    		if(nm == null || nm.trim().length() == 0)
    		{
    			continue;
    		}
    		nm = nm.trim();
    		nm = filterObjectName(nm);
    		sb.append(nm).append("/");
    	}
    	if(sb.length() > 1){
    		sb.delete(sb.length()-1, sb.length());
    	}
    	return sb.toString();
    }
    
	public String replace(String line, String oldString, String newString) {
        if(line == null ) return line;
        if(line.equals("")) return line;
        if(oldString.equals(newString)) return line;
        if(oldString.equals("")) return line;
        int index=0;
        while((index = line.indexOf(oldString, index)) >= 0){
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());
            index += newString.length();
        }
        return line;
	}
    
    /**
     * Read input from the user.
     */
    public static String getInput(String question) throws IOException
    {
       // Input stream reader object.
       BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
       
       System.out.print(question);
       return stdIn.readLine();
    }  
    
    private static void log(String msg)
    {
    	System.out.println(msg);
    	if(out != null)
    	{
    		out.println(msg);
    	}
    }  

}
